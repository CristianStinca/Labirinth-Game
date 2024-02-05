package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import res.ResourceLoader;
import database.Database;
import database.HighScore;
import java.util.Set;

public class Game {
    private final HashMap<String, HashMap<Integer, GameLevel>> gameLevels;
    private GameLevel gameLevel = null;
    
    int interval = 0;
    boolean timerStopped = false;
    
    private Database database;
    
    private int levelsPassed = 0;
    private int totalTime = 0;
    
    public ArrayList<HighScore> getHighScores() {
        return database.getHighScores();
    }
    
    public final int modSeconds() {
        totalTime ++;
        return interval++;
    }
    
    public final int getSeconds() {
        return interval;
    }
    
    public final void nullifySeconds() {
        interval = 0;
        timerStopped = false;
    }
    
    public final boolean hasTimerStopped() {
        return timerStopped;
    }
    
    public final void stopTimer() {
        timerStopped = true;
    }

    public Game() {
        levelsPassed = 0;
        totalTime = 0;
        database = new Database();
        interval = 0;
        timerStopped = false;
        gameLevels = new HashMap<>();
        readLevels();
    }

    public void loadGame(GameID gameID){
        levelsPassed = 0;
        totalTime = 0;
        gameLevel = new GameLevel(gameLevels.get(gameID.difficulty).get(gameID.level));
        this.nullifySeconds();
    }
    
    public GameID loadGame() {
        levelsPassed++;
        GameID id = getNextId(gameLevel.gameID);
        if (id != null) {
            gameLevel = new GameLevel(gameLevels.get(id.difficulty).get(id.level));
            this.nullifySeconds();
        }
        return id;
    }
    
    private GameID getNextId(GameID currID) {
//        System.out.println(String.valueOf(currID.difficulty) + " " + String.valueOf(currID.level));
        Set difficulty = gameLevels.keySet();
        Object prevD = null;
        Object prevL = null;
        for (Object d : difficulty) {
            Set levels = gameLevels.get((String) d).keySet();
            for (Object l : levels) {
//                System.out.println(prevD + " " + String.valueOf(prevL));
//                System.out.println(String.valueOf(prevD == currID.difficulty) + " " + String.valueOf(prevL == (Integer) currID.level));
                if (currID.difficulty.equals((String) prevD) && prevL == (Integer) currID.level) {
                    GameID gID = new GameID((String) d, (int) l);
//                    System.out.println();
                    return gID;
                }
                prevL = l;
                prevD = d;
            }
        }
        
//        System.out.println();
        return null;
    }
    
    public void printGameLevel(){ gameLevel.printLevel(); }
    
    public boolean step(Direction d){
        return (gameLevel.movePlayer(d));
    }
    
    public boolean moveDragon() throws GameLevel.InvalidDragonPositionException {
        return (gameLevel.moveDragon());
    }
    
    public boolean arePalyerDragonAdjacent() {
        return (gameLevel.arePalyerDragonAdjacent());
    }
    
    public boolean gameEnd(boolean playerWin, String name) {
        if (playerWin == false) {
            database.addRecord(name, levelsPassed, totalTime);
            ArrayList<HighScore> db = database.getHighScores();
//            for (HighScore e : db) {
//                System.out.println(e);
//            }
        }
        gameLevel.gameEnd(playerWin);
        if (playerWin == true) {
            if (this.loadGame() == null) {
                return true;
            }
        }
        return false;
    }
    
    public Collection<String> getDifficulties(){ return gameLevels.keySet(); }
    
    public Collection<Integer> getLevelsOfDifficulty(String difficulty){
        if (!gameLevels.containsKey(difficulty)) return null;
        return gameLevels.get(difficulty).keySet();
    }
    
    public boolean isLevelLoaded(){ return gameLevel != null; }
    public int getLevelRows(){ return gameLevel.rows; }
    public int getLevelCols(){ return gameLevel.cols; }
    public LevelItem getItem(int row, int col){ return gameLevel.level[row][col]; }
    public GameID getGameID(){ return (gameLevel != null) ? gameLevel.gameID : null; }
    public boolean hasGameEnded(){ return gameLevel.hasGameEnded(); }
    
    public boolean endCondition() {
        return gameLevel.isPlayerOnExit();
    }

    public Position getPlayerPos(){
        return new Position(gameLevel.player.x, gameLevel.player.y); 
    }
    
    public Position getDragonPos(){
        return new Position(gameLevel.dragon.x, gameLevel.dragon.y); 
    }

    private void readLevels(){
        //ClassLoader cl = getClass().getClassLoader();
        InputStream is;// = cl.getResourceAsStream("res/levels.txt");
        is = ResourceLoader.loadResource("res/levels.txt");
        
        try (Scanner sc = new Scanner(is)){
            String line = readNextLine(sc);
            ArrayList<String> gameLevelRows = new ArrayList<>();
            
            while (!line.isEmpty()){
                GameID id = readGameID(line);
                if (id == null) return;
                
                // System.out.println(id.difficulty + " " + id.id);

                gameLevelRows.clear();
                line = readNextLine(sc);
                while (!line.isEmpty() && line.trim().charAt(0) != ';'){
                    gameLevelRows.add(line);                    
                    line = readNextLine(sc);
                }
                addNewGameLevel(new GameLevel(gameLevelRows, id));
            }
            //if (is != null) is.close();
        } catch (Exception e){
            System.out.println(e);
        }
        
    }
    
    private void addNewGameLevel(GameLevel gameLevel){
        HashMap<Integer, GameLevel> levelsOfDifficulty;
        if (gameLevels.containsKey(gameLevel.gameID.difficulty)){
            levelsOfDifficulty = gameLevels.get(gameLevel.gameID.difficulty);
            levelsOfDifficulty.put(gameLevel.gameID.level, gameLevel);
        } else {
            levelsOfDifficulty = new HashMap<>();
            levelsOfDifficulty.put(gameLevel.gameID.level, gameLevel);
            gameLevels.put(gameLevel.gameID.difficulty, levelsOfDifficulty);
        }
    }
  
    private String readNextLine(Scanner sc){
        String line = "";
        while (sc.hasNextLine() && line.trim().isEmpty()){
            line = sc.nextLine();
        }
        return line;
    }
    
    private GameID readGameID(String line){
        line = line.trim();
        if (line.isEmpty() || line.charAt(0) != ';') return null;
        Scanner s = new Scanner(line);
        s.next(); // skip ';'
        if (!s.hasNext()) return null;
        String difficulty = s.next().toUpperCase();
        if (!s.hasNextInt()) return null;
        int id = s.nextInt();
        return new GameID(difficulty, id);
    }    
    
    public boolean isPlayerOnExit() {
        return gameLevel.isPlayerOnExit();
    }
    
    public int getCellLuminosity(int row, int col) {
        if (gameLevel.lightMap[row][col] == (gameLevel.VISION_RANGE + 1)) return 100;
        return ((gameLevel.lightMap[row][col] * 70) / (gameLevel.VISION_RANGE + 1));
    }
    
    public boolean isCellOutOfVision(int row, int col) {
        return gameLevel.lightMap[row][col] > gameLevel.VISION_RANGE;
    }
}
