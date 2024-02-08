/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Properties;

/**
 *
 * @author Christ
 */
public class HighScores {

    int maxScores;
    PreparedStatement insertStatement;
    PreparedStatement deleteStatement;
    Connection connection;

    public HighScores(int maxScores) throws SQLException {
        this.maxScores = maxScores;
        Properties connectionProps = new Properties();
        // Add new user -> MySQL workbench (Menu: Server / Users and priviliges)
        //                             Tab: Administrative roles -> Check "DBA" option
        connectionProps.put("user", "root");
        connectionProps.put("password", "password");
        connectionProps.put("serverTimezone", "UTC");
        String dbURL = "jdbc:mysql://localhost:3306/highscores";
        connection = DriverManager.getConnection(dbURL, connectionProps);
        
        
        String insertQuery = "INSERT INTO HIGHSCORES (TOTALTIME, NAME, SCORE) VALUES (?, ?, ?)";
        insertStatement = connection.prepareStatement(insertQuery);
        String score = "1; DELETE FROM HIGHSCORES;";
        String deleteQuery = "DELETE FROM HIGHSCORES WHERE score=? AND totalTime=?;"; // + score;
        deleteStatement = connection.prepareStatement(deleteQuery);
    }

    public ArrayList<HighScore> getHighScores() throws SQLException {
        String query = "SELECT * FROM HIGHSCORES ORDER BY SCORE DESC";
        ArrayList<HighScore> highScores = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            String name = results.getString("NAME");
            int score = results.getInt("SCORE");
            int totalTime = results.getInt("TOTALTIME");
            highScores.add(new HighScore(name, score, totalTime));
        }
        sortHighScores(highScores);
        return highScores;
    }

    public void putHighScore(String name, int score, int totalTime) throws SQLException {
        ArrayList<HighScore> highScores = getHighScores();
        if (highScores.size() < maxScores) {
            insertScore(name, score, totalTime);
        } else {
//            for (HighScore h : highScores) {
//                System.out.println(h.toString());
//            }
            HighScore leastScore = highScores.get(highScores.size() - 1);
            if (leastScore.getScore() < score || (leastScore.getScore() == score && leastScore.getTotalTime() > score)) {
                deleteScores(leastScore);
                insertScore(name, score, totalTime);
            }
        }
    }

    /**
     * Sort the high scores in descending order.
     * @param highScores 
     */
    private void sortHighScores(ArrayList<HighScore> highScores) {
        Collections.sort(highScores, new Comparator<HighScore>() {
            @Override
            public int compare(HighScore t, HighScore t1) {
                int diff = t1.getScore() - t.getScore();
                if (diff == 0) {
                    return t.getTotalTime() - t1.getTotalTime();
                }
                return diff;
            }
        });
    }

    private void insertScore(String name, int score, int totalTime) throws SQLException {
        //Timestamp ts = new Timestamp(System.currentTimeMillis());
        insertStatement.setInt(1, totalTime);
        insertStatement.setString(2, name);
        insertStatement.setInt(3, score);
        insertStatement.executeUpdate();
    }

    /**
     * Deletes all the highscores with score.
     *
     * @param score
     */
    private void deleteScores(HighScore player) throws SQLException {
        deleteStatement.setInt(1, player.getScore());
        deleteStatement.setInt(2, player.getTotalTime());
        deleteStatement.executeUpdate();
    }
}
