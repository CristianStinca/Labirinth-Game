/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author Christ
 */
public class HighScore {
    
    private final String name;
    private final int score;
    private final int totalTime;

    public HighScore(String name, int score, int totalTime) {
        this.name = name;
        this.score = score;
        this.totalTime = totalTime;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
    public int getTotalTime() {
        return totalTime;
    }

    @Override
    public String toString() {
        return "HighScore{" + "name=" + name + ", score=" + score + ", time=" + totalTime + '}';
    }
    

}
