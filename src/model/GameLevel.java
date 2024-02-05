package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class GameLevel {
    public class InvalidDragonPositionException extends Exception {
        public InvalidDragonPositionException(String errorMessage) {
            super(errorMessage);
        }

        public InvalidDragonPositionException() {
            super();
        }
    }
    public final GameID        gameID;
    public final int           rows, cols;
    public final LevelItem[][] level;
    public Position            player = null;
    public Position            dragon = null;
    private boolean            gameEnded;
    private Direction          dragonDirection = Direction.UP;
    public short[][]           lightMap; //0..vR+1
    public final short         VISION_RANGE = 3; //how far can the player see
    
    public GameLevel(ArrayList<String> gameLevelRows, GameID gameID){
        this.gameID = gameID;
        int c = 0;
        for (String s : gameLevelRows) if (s.length() > c) c = s.length();
        rows = gameLevelRows.size();
        cols = c;
        level = new LevelItem[rows][cols];
        player = new Position(1, rows-2);
        gameEnded = false;
        
        for (int i = 0; i < rows; i++){
            String s = gameLevelRows.get(i);
            for (int j = 0; j < s.length(); j++){
                switch (s.charAt(j)){
                    case '#': level[i][j] = LevelItem.WALL; break;
                    case '.': level[i][j] = LevelItem.DESTINATION; break;
                    default:  level[i][j] = LevelItem.EMPTY; break;
                }
            }
            for (int j = s.length(); j < cols; j++){
                level[i][j] = LevelItem.EMPTY;
            }
        }
        
        lightMap = new short[rows][cols];
        
        Position p = null;
        do {
            p = new Position((int) (Math.random() * (cols-1)), (int) (Math.random() * (rows-1)));
        } while (!(level[p.y][p.x] == LevelItem.EMPTY && (p.x > (cols/2) || p.y < (rows/2))));
        dragon = p;
    }

    public GameLevel(GameLevel gl) {
        gameID = gl.gameID;
        rows = gl.rows;
        cols = gl.cols;
        level = new LevelItem[rows][cols];
        player = new Position(gl.player.x, gl.player.y);
        
        for (int i = 0; i < rows; i++){
            System.arraycopy(gl.level[i], 0, level[i], 0, cols);
        }
        
        lightMap = new short[rows][cols];
        updateLighMap();
        
        Position p = null;
        do {
            p = new Position((int) (Math.random() * (cols-1)), (int) (Math.random() * (rows-1)));
        } while (!(level[p.y][p.x] == LevelItem.EMPTY && (p.x > (cols/2) || p.y < (rows/2))));
        dragon = p;
        
    }

    public boolean isValidPosition(Position p){
        return (p.x >= 0 && p.y >= 0 && p.x < cols && p.y < rows);
    }
    
    public boolean isFree(Position p, boolean isDragon){
        if (!isValidPosition(p)) return false;
        LevelItem li = level[p.y][p.x];
        return ((li == LevelItem.EMPTY || (li == LevelItem.DESTINATION && !isDragon)) && !((p.x == player.x && p.y == player.y) || (p.x == dragon.x && p.y == dragon.y)));
    }
    
    public boolean movePlayer(Direction d){
        Position curr = player;
        Position next = curr.translate(d);
        if (isFree(next, false)) {
            player = next;
            updateLighMap();
            return true;
        } 
        return false;
    }
    
    public boolean moveDragon() throws InvalidDragonPositionException {
        Position curr = dragon;
        Position next = curr.translate(dragonDirection);

        int cnt = 0;
        while (!isFree(next, true)) {
            if (cnt >= 4) throw new InvalidDragonPositionException("Dragon is Trapped");
            cnt++;
            dragonDirection = Direction.rotateCW(dragonDirection);
//                System.out.println(dragonDirection);
            next = curr.translate(dragonDirection);
            
        }
        
        dragon = next;
        
        return true;
    }
    
    public boolean arePalyerDragonAdjacent() {
        return ((dragon.x - player.x == 1 && dragon.y - player.y == 0) || (dragon.x - player.x == 0 && dragon.y - player.y == 1) || (dragon.x - player.x == -1 && dragon.y - player.y == 0) || (dragon.x - player.x == 0 && dragon.y - player.y == -1));
    }
    
    public boolean updateLighMap() {
        if (player == null) return false;
        
        LinkedList<Position> q = new LinkedList<>();
        q.add(player);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                lightMap[i][j] = VISION_RANGE+1;
            }
        }
        lightMap[player.y][player.x] = 0;
        
        while (!q.isEmpty()) {
            Position e = q.pop();
            for (Direction d : Direction.getAllDirections()) {
                Position next = e.translate(d);
                if (isValidPosition(next)) {
                    short val = (short) (lightMap[e.y][e.x]+1);
                    
                    if (val >= lightMap[next.y][next.x]) {
                        continue;
                    }
                    
                    lightMap[next.y][next.x] = val;
                    
                    if (!(level[next.y][next.x] == LevelItem.WALL || lightMap[next.y][next.x] >= VISION_RANGE)) {
                        q.add(next);
                    }
                }
            }
        }
        
        return true;
    }
    
    public void gameEnd(boolean playerWin) {
        gameEnded = true;
    }
    
    public void printLevel(){
        int x = player.x, y = player.y;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (i == y && j == x)
                    System.out.print('@');
                else 
                    System.out.print(level[i][j].representation);
            }
            System.out.println("");
        }
    }
    
    public boolean hasGameEnded() {
        return gameEnded;
    }
    
    public boolean isPlayerOnExit() {
        return gameEnded = level[player.y][player.x] == LevelItem.DESTINATION;
    }
}
