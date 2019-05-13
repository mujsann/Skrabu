/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Conrad Joye Thomas
 */
public class Board extends javax.swing.JPanel {
    private static final long serialVersionUID = -7299257992993422348L;
    ScrabbleValues boxType;
    boolean firstPlay = true;
    private final Box boxes[][] = new Box[15][15];
    private final String[][] board = {
        {"tws", "blank", "blank", "dls", "blank", "blank", "blank", "tws", "blank", "blank", "blank", "dls", "blank", "blank", "tws"},
        {"blank", "dws", "blank", "blank", "blank", "tls", "blank", "blank", "blank", "tls", "blank", "blank", "blank", "dws", "blank"},
        {"blank", "blank", "dws", "blank", "blank", "blank", "dls", "blank", "dls", "blank", "blank", "blank", "dws", "blank", "blank"},
        {"dls", "blank", "blank", "dws", "blank", "blank", "blank", "dls", "blank", "blank", "blank", "dws", "blank", "blank", "dls"},
        {"blank", "blank", "blank", "blank", "dws", "blank", "blank", "blank", "blank", "blank", "dws", "blank", "blank", "blank", "blank"},
        {"blank", "tls", "blank", "blank", "blank", "tls", "blank", "blank", "blank", "tls", "blank", "blank", "blank", "tls", "blank"},
        {"blank", "blank", "dls", "blank", "blank", "blank", "dls",  "blank", "dls", "blank", "blank", "blank", "dls", "blank", "blank"},
        {"tws", "blank", "blank", "dls", "blank", "blank", "blank", "star", "blank", "blank", "blank", "dls", "blank", "blank", "tws"},
        {"blank", "blank", "dls", "blank", "blank", "blank", "dls",  "blank", "dls", "blank", "blank", "blank", "dls", "blank", "blank"},
        {"blank", "tls", "blank", "blank", "blank", "tls", "blank", "blank", "blank", "tls", "blank", "blank", "blank", "tls", "blank"},
        {"blank", "blank", "blank", "blank", "dws", "blank", "blank", "blank", "blank", "blank", "dws", "blank", "blank", "blank", "blank"},
        {"dls", "blank", "blank", "dws", "blank", "blank", "blank", "dls", "blank", "blank", "blank", "dws", "blank", "blank", "dls"},
        {"blank", "blank", "dws", "blank", "blank", "blank", "dls", "blank", "dls", "blank", "blank", "blank", "dws", "blank", "blank"},
        {"blank", "dws", "blank", "blank", "blank", "tls", "blank", "blank", "blank", "tls", "blank", "blank", "blank", "dws", "blank"},
        {"tws", "blank", "blank", "dls", "blank", "blank", "blank", "tws", "blank", "blank", "blank", "dls", "blank", "blank", "tws"}
    };
private final ArrayList<int[]> lettersPlayed = new ArrayList<>();
private int numberOfTilesPlayed = 0;
private ScrabblePane scrabble;
private int score = 0;
private final HashMap <Integer[], String> scoringMap = new HashMap();
    /**
     * Creates new form Board
     */
    public Board() {
        initComponents();
        initBoard();
    }

    public void setScrabble(ScrabblePane scrabble) {
        this.scrabble = scrabble;
    }
    public void makePlay(int x, int y, String word, String direction, int score){
        Tile t = null;
        for(char a : word.toCharArray()){
            if(direction.equals("x")){
                if(boxes[x][y].getBoxTile().isTileLocked()){
                        y++;
                    }else{
                        t = new Tile(new String(new char[]{a}));
                        boxes[x][y++].setBox(t);
             }
            }else{
                if(boxes[x][y].getBoxTile().isTileLocked()){
                        x++;
                    }else{
                        t = new Tile(new String(new char[]{a}));
                        boxes[x++][y].setBox(t);
             }
            }   
        }
        getBoardTile(true);
        lockPlayedTile();
        revalidate();
    }
    
    public Box[][] getBoxes() {
        Box box[][] = new Box[15][15];
        for(int i = 0; i < board.length; i++){
              for (int j = 0; j< board[i].length; j++){
                  switch(board[i][j]){
                      case "blank":
                          box[i][j] = new Box(ScrabbleValues.BLANK);
                          box[i][j].setBox(new Tile(boxes[i][j].getBoxTile()));
                          break;
                      case "dls":
                          box[i][j] = new Box(ScrabbleValues.DOULBE_LETTER_SCORE);
                          box[i][j].setBox(new Tile(boxes[i][j].getBoxTile()));
                          break;
                      case "dws":
                          box[i][j] = new Box(ScrabbleValues.DOULBE_WORD_SCORE);
                          box[i][j].setBox(new Tile(boxes[i][j].getBoxTile()));
                          break;
                      case "star":
                          box[i][j] = new Box(ScrabbleValues.STAR);
                          box[i][j].setBox(new Tile(boxes[i][j].getBoxTile()));
                          break;
                      case "tls":
                          box[i][j] = new Box(ScrabbleValues.TRIPLE_LETTER_SCORE);
                          box[i][j].setBox(new Tile(boxes[i][j].getBoxTile()));
                          break;
                      case "tws":
                          box[i][j] = new Box(ScrabbleValues.TRIPLE_WORD_SCORE);
                          box[i][j].setBox(new Tile(boxes[i][j].getBoxTile()));
                          break;
                  }
              }
        }
        return box;
    }
    
    public String[][] getBoxesAsString() {
        String box[][] = new String[15][15];
        for(int i = 0; i < boxes.length; i++){
              for (int j = 0; j < boxes[i].length; j++){
                  if(boxes[i][j].getBoxTile().isTileLocked()){
                      box[i][j] = boxes[i][j].getBoxTile().getLetter().toString() + ":LOCKED";
                  }else{
                      box[i][j] = "EMPTY";
                  }
              }
        }
        return box;
    }
    
    private void initBoard(){
        for(int i = 0; i < board.length; i++){
              for (int j = 0; j< board[i].length; j++){
                  switch(board[i][j]){
                      case "blank":
                          boxes[i][j] = new Box(ScrabbleValues.BLANK);
                          scoringMap.put(new Integer[]{i, j}, "blank");
                          break;
                      case "dls":
                          boxes[i][j] = new Box(ScrabbleValues.DOULBE_LETTER_SCORE);
                          scoringMap.put(new Integer[]{i, j}, "dls");
                          break;
                      case "dws":
                          boxes[i][j] = new Box(ScrabbleValues.DOULBE_WORD_SCORE);
                          scoringMap.put(new Integer[]{i, j}, "dws");
                          break;
                      case "star":
                          boxes[i][j] = new Box(ScrabbleValues.STAR);
                          scoringMap.put(new Integer[]{i, j}, "star");
                          break;
                      case "tls":
                          boxes[i][j] = new Box(ScrabbleValues.TRIPLE_LETTER_SCORE);
                          scoringMap.put(new Integer[]{i, j}, "tls");
                          break;
                      case "tws":
                          boxes[i][j] = new Box(ScrabbleValues.TRIPLE_WORD_SCORE);
                          scoringMap.put(new Integer[]{i, j}, "tws");
                          break;
                  }
                  boardPanel.add(boxes[i][j]);
              }
        }
    }
    
    public boolean isFirstPlay() {
        return firstPlay;
    }

    public void setFirstPlay(boolean firstPlay) {
        this.firstPlay = firstPlay;
    }

    public HashMap<Integer[], String> getScoringMap() {
        return scoringMap;
    }
    
    public boolean isValidFirstPlay(){
        for (int a[] : lettersPlayed){
                if(a[0] == 7 && a[1] == 7){
                      return true;
                }
        }
        return false;
    }
    
    public int calculateScore(HashMap<Integer[], Integer> map){
        boolean isDouble = false;
        boolean isTripple = false;
        int score = 0;
        for(Integer[] key : map.keySet()){
            String valueType = board[key[0]][key[1]];
            Integer value = map.get(key);
                switch(valueType){
                    case "blank":
                          score += value;
                          break;
                      case "dls":
                          score += value * 2;
                          break;
                      case "dws":
                          score += value;
                          isDouble = true;
                          break;
                      case "star":
                          score += value;
                          break;
                      case "tls":
                          score += value * 3;
                          break;
                      case "tws":
                          score += value;
                          isTripple = true;
                          break;
                }
            }
        if(isDouble || isFirstPlay()){
            score *= 2;
        }
        if(isTripple){
            score *= 3;
        }
        return score;
    }
    
    public boolean getBoardTile(boolean maven){
         for(int i = 0; i < boxes.length; i++){
                for (int j = 0; j< boxes[i].length; j++){
                    if(!boxes[i][j].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) && !boxes[i][j].getBoxTile().isTileLocked()){
                            lettersPlayed.add(new int[]{i,j});
                            ++numberOfTilesPlayed;
                        }
                }
         }
         if(numberOfTilesPlayed <= 0){
             numberOfTilesPlayed = 0;
             ArrayList c = lettersPlayed;
             lettersPlayed.removeAll(c);
             return false;
         }else{
            if(validateTilesPlayed(lettersPlayed.get(0)[0], lettersPlayed.get(0)[1], maven)){
                if(maven){
                    scrabble.addScoreTableMaven(score);
                }else{
                    scrabble.addScoreTable(score);
                }
                    
                lockPlayedTile();
                return true;
            }
            numberOfTilesPlayed = 0;
            score = 0;
            ArrayList c = lettersPlayed;
            lettersPlayed.removeAll(c);
            return false;
       }
    }
    
    private void lockPlayedTile(){
        for (int a[] : lettersPlayed){
                boxes[a[0]][a[1]].getBoxTile().setTileLocked(true);
        }
        resetPlay();
    }
    
    private void resetPlay(){
        setFirstPlay(false);
        numberOfTilesPlayed = 0;
        score = 0;
        ArrayList c = lettersPlayed;
        lettersPlayed.removeAll(c);
    }
    
    private boolean validateTilesPlayed(int x, int y, boolean maven){
        boolean directionx = false;
        boolean directiony = false;
        boolean result = false;
        int xcounter = 0;
        int ycounter = 0;
        int []testValue = lettersPlayed.get(0);
        for(int[] i : lettersPlayed){
            if(i[0] == testValue[0]){
                ++xcounter;
            }
            if(i[1] == testValue[1]){
                ++ycounter;
            }
        }
        if(xcounter > 1){
            directionx = true;
        }
        if(ycounter > 1){
            directiony = true;
        }
        
        if(isFirstPlay()){
            if(!isValidFirstPlay()){
                return false;
            }
        }
        
        if(numberOfTilesPlayed == 1){
              if((x + 1 > 14 ? false : boxes[x + 1][y].getBoxTile().isTileLocked())||
                 (x - 1 < 0  ? false : boxes[x - 1][y].getBoxTile().isTileLocked())||
                 (y + 1 > 14 ? false : boxes[x][y + 1].getBoxTile().isTileLocked())||
                 (y - 1 < 0  ? false : boxes[x][y - 1].getBoxTile().isTileLocked())){
                    Object []sh = searchTileHorizontal(x, y); 
                    Object []sv = searchTileVertical(x, y);
                    if(((String)sh[0]).trim().length()> 1){
                            if(maven){
                                scrabble.addWordTableMaven(((String)sh[0]));
                            }else{
                               scrabble.addWordTable(((String)sh[0])); 
                            }
                            score += calculateScore(((HashMap<Integer[], Integer>)sh[1]));
                        }
                    if(((String)sv[0]).trim().length()> 1){
                            if(maven){
                                scrabble.addWordTableMaven(((String)sv[0]));
                            }else{
                               scrabble.addWordTable(((String)sv[0])); 
                            }
                            score += calculateScore(((HashMap<Integer[], Integer>)sv[1]));
                        }
                    result = true;
              }else{
                  result = false;
              }
        }else{
        if(directionx){
            for(int i = 0; i < 15; i++){
                if(!boxes[i][y].getBoxTile().isTileLocked() && 
                    !boxes[i][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                    x = i;
                    break;
                }
            }
                for(int i = 1; i <= numberOfTilesPlayed; i++){
                    //System.out.println(boxes[x][y].getBoxTile().getLetter() +" "+ x +" "+ y);
                    result = true;
                    if(i == 1){
                        Object[] word1 = searchTileHorizontal(x, y);
                        Object[] word2 = searchTileVertical(x, y);
                        if(((String)word1[0]).trim().length()> 1){
                            if(maven){
                                scrabble.addWordTableMaven(((String)word1[0]));
                                System.out.println("sdf");
                            }else{
                                scrabble.addWordTable(((String)word1[0]));
                            }
                            score += calculateScore(((HashMap<Integer[], Integer>)word1[1]));
                        }
                        if(((String)word2[0]).trim().length()> 1){
                            if(maven){
                                scrabble.addWordTableMaven(((String)word2[0]));
                            }else{
                                scrabble.addWordTable(((String)word2[0]));
                            }
                            score += calculateScore(((HashMap<Integer[], Integer>)word2[1]));
                        }
                    }else{
                        if(!boxes[x][y].getBoxTile().isTileLocked()){
                            Object[] word = searchTileVertical(x, y);
                            if(((String)word[0]).trim().length()> 1){
                                if(maven){
                                    scrabble.addWordTableMaven(((String)word[0]));
                                }else{
                                    scrabble.addWordTable(((String)word[0]));
                                }
                                score += calculateScore(((HashMap<Integer[], Integer>)word[1]));
                            }
                        }
                    }    
                    if(boxes[x][y++].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                           result = false;
                    }
                }
        }else if(directiony){
                for(int i = 0; i < 15; i++){
                    if(!boxes[x][i].getBoxTile().isTileLocked() && 
                        !boxes[x][i].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                        y = i;
                        break;
                    }
                }
                for(int i = 1; i <= numberOfTilesPlayed ; i++){
                    //System.out.println(boxes[x][y].getBoxTile().getLetter() +" "+ x +" "+ y);
                    result = true;
                    if(i == 1){
                        Object[] word1 = searchTileVertical(x, y);
                        Object[] word2 = searchTileHorizontal(x, y);
                        if(((String)word1[0]).trim().length()> 1){
                            if(maven){
                                scrabble.addWordTableMaven(((String)word1[0]));
                            }else{
                                scrabble.addWordTable(((String)word1[0]));
                            }
                            score += calculateScore(((HashMap<Integer[], Integer>)word1[1]));
                        }
                        if(((String)word2[0]).trim().length()> 1){
                            if(maven){
                                scrabble.addWordTableMaven(((String)word2[0]));
                            }else{
                                scrabble.addWordTable(((String)word2[0]));
                            }
                            score += calculateScore(((HashMap<Integer[], Integer>)word2[1]));
                        }
                    }else{
                          if(!boxes[x][y].getBoxTile().isTileLocked()){
                                Object[] word = searchTileHorizontal(x, y);
                                if(((String)word[0]).trim().length()> 1){
                                    if(maven){
                                        scrabble.addWordTableMaven(((String)word[0]));
                                    }else{
                                        scrabble.addWordTable(((String)word[0]));
                                    }
                                    score += calculateScore(((HashMap<Integer[], Integer>)word[1]));
                                }
                          }      
                    }
                    if(boxes[x++][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                           result = false;
                           break;
                    }
                }
            }else if(directionx && directiony){
                result = false;
            }
        }
        if(result && isFirstPlay()){
            setFirstPlay(false);
        }
//        System.out.println("DirectionX:" + directionx);
//        System.out.println("DirectionY:" + directiony);
//        System.out.println("Result:" + result);
          return result;
    }
    
    public boolean validateTilesPlayed(int x, int y, Box[][] boxes, int numberOfTilesPlayed, ArrayList<int[]> lettersPlayed){
        boolean directionx = false;
        boolean directiony = false;
        boolean result = false;
        int xcounter = 0;
        int ycounter = 0;
        int []testValue = lettersPlayed.get(0);
        for(int[] i : lettersPlayed){
            if(i[0] == testValue[0]){
                ++xcounter;
            }
            if(i[1] == testValue[1]){
                ++ycounter;
            }
        }
        if(xcounter > 1){
            directionx = true;
        }
        if(ycounter > 1){
            directiony = true;
        }
        
        if(isFirstPlay()){
            if(!isValidFirstPlay()){
                return false;
            }
        }
        
        if(numberOfTilesPlayed == 1){
              if((x + 1 > 14 ? false : boxes[x + 1][y].getBoxTile().isTileLocked())||
                 (x - 1 < 0  ? false : boxes[x - 1][y].getBoxTile().isTileLocked())||
                 (y + 1 > 14 ? false : boxes[x][y + 1].getBoxTile().isTileLocked())||
                 (y - 1 < 0  ? false : boxes[x][y - 1].getBoxTile().isTileLocked())){
                    result = true;
              }else{
                  result = false;
              }
        }else{
        if(directionx){
            for(int i = 0; i < 15; i++){
                    if(!boxes[i][y].getBoxTile().isTileLocked() && 
                        !boxes[i][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                        x = i;
                        break;
                    }
                }
                for(int i = 1; i <= numberOfTilesPlayed; i++){
                    //System.out.println(boxes[x][y].getBoxTile().getLetter() +" "+ x +" "+ y);
                    result = true;
                    if(boxes[x][y++].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                           result = false;
                    }
                }
        }else if(directiony){
                for(int i = 0; i < 15; i++){
                    if(!boxes[x][i].getBoxTile().isTileLocked() && 
                        !boxes[x][i].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                        y = i;
                        break;
                    }
                }
                for(int i = 1; i <= numberOfTilesPlayed ; i++){
                    //System.out.println(boxes[x][y].getBoxTile().getLetter() +" "+ x +" "+ y);
                    result = true;
                    if(boxes[x++][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                           result = false;
                           break;
                    }
                }
            }else if(directionx && directiony){
                result = false;
            }
        }
        if(result && isFirstPlay()){
            setFirstPlay(false);
        }
//        System.out.println("DirectionX:" + directionx);
//        System.out.println("DirectionY:" + directiony);
//        System.out.println("Result:" + result);
          return result;
    }
    
    private Object[] searchTileVertical(int x, int y){
        String words = "";
        HashMap<Integer[], Integer> score = new HashMap<>();
        while(!boxes[(x - 1 < 0  ? x : x -1)][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
             --x;
        }
        while((x  < 15) && !boxes[x][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                score.put(new Integer[]{x,y}, boxes[x][y].getBoxTileValue());  
                words+= boxes[x++][y].getBoxTile().getLetter();
        }
        return new Object[]{words, score};
    }
    
    private Object[] searchTileHorizontal(int x, int y){
        String words = "";
        HashMap<Integer[], Integer> score = new HashMap<>();
        while(!boxes[x][(y - 1 < 0  ? y : y - 1)].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                       --y;
        }
        while((y < 15) && !boxes[x][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                score.put(new Integer[]{x,y}, boxes[x][y].getBoxTileValue());
                words+= boxes[x][y++].getBoxTile().getLetter();
        }
        return new Object[]{words, score};
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boardPanel = new javax.swing.JPanel();

        boardPanel.setBackground(new java.awt.Color(255, 255, 255));
        boardPanel.setLayout(new java.awt.GridLayout(15, 15, 2, 2));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    // End of variables declaration//GEN-END:variables
}
