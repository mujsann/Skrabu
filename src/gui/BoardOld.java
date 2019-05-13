/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;

/**
 *
 * @author Conrad Joye Thomas
 */
public class BoardOld extends javax.swing.JPanel {
ScrabbleValues boxType;
boolean firstPlay = true;
private Box boxes[][] = new Box[15][15];
private String[][] board = {
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
private ArrayList<int[]> lettersPlayed = new ArrayList<>();
private int numberOfTilesPlayed = 0;
    /**
     * Creates new form Board
     */
    public BoardOld() {
        initComponents();
        initBoard();
    }
    
    private void initBoard(){
        for(int i = 0; i < board.length; i++){
              for (int j = 0; j< board[i].length; j++){
                  switch(board[i][j]){
                      case "blank":
                          boxes[i][j] = new Box(ScrabbleValues.BLANK);
                          break;
                      case "dls":
                          boxes[i][j] = new Box(ScrabbleValues.DOULBE_LETTER_SCORE);
                          break;
                      case "dws":
                          boxes[i][j] = new Box(ScrabbleValues.DOULBE_WORD_SCORE);
                          break;
                      case "star":
                          boxes[i][j] = new Box(ScrabbleValues.STAR);
                          break;
                      case "tls":
                          boxes[i][j] = new Box(ScrabbleValues.TRIPLE_LETTER_SCORE);
                          break;
                      case "tws":
                          boxes[i][j] = new Box(ScrabbleValues.TRIPLE_WORD_SCORE);
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
    
    
    private boolean isValidFirstPlay(){
        for (int a[] : lettersPlayed){
                if(a[0] == 7 && a[1] == 7){
                      return true;
                }
        }
        return false;
    }
    
    public boolean getBoardTile(){
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
            if(validateTilesPlayed(lettersPlayed.get(0)[0], lettersPlayed.get(0)[1])){
                    lockPlayedTile();
                    return true;
            }
            numberOfTilesPlayed = 0;
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
        ArrayList c = lettersPlayed;
        lettersPlayed.removeAll(c);
    }
    
    private int[] findATile(){
       for(int i = 0 ; i <= boxes.length; i++){
           for(int j = 0 ; j <= boxes[i].length; j++){
               if(!boxes[i][j].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)||
                      !boxes[i][j].getBoxTile().isTileLocked()){
                    return  new int[]{i, j};
               }
           }
        }
        return new int[]{-1, -1};
    }
    
    private boolean isTileConnectToPlayedTile(int x, int y){
        return  boxes[(x+1 > 14 ? x : x + 1)][y].getBoxTile().isTileLocked() ||
                boxes[(x-1 < 0  ? x : x - 1)][y].getBoxTile().isTileLocked() ||
                boxes[x][(y+1 > 14 ? y : y + 1)].getBoxTile().isTileLocked() ||
                boxes[x][(y-1 < 0  ? y : y - 1)].getBoxTile().isTileLocked();
    }
    
    private boolean isTileConnectToUnPlayedTile(int x, int y){
        return  !boxes[(x+1 > 14 ? x : x + 1)][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) ||
                !boxes[(x-1 < 0  ? x : x - 1)][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) ||
                !boxes[x][(y+1 > 14 ? y : y + 1)].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) ||
                !boxes[x][(y-1 < 0  ? y : y - 1)].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY);
    }
    
    private boolean validateTilesPlayed(int x, int y){
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
        
        if(numberOfTilesPlayed == 1){
              if((x + 1 > 14 ? false : boxes[x + 1][y].getBoxTile().isTileLocked())||
                 (x - 1 < 0  ? false : boxes[x - 1][y].getBoxTile().isTileLocked())||
                 (y + 1 > 14 ? false : boxes[x][y + 1].getBoxTile().isTileLocked())||
                 (y - 1 < 0  ? false : boxes[x][y - 1].getBoxTile().isTileLocked())){
                    System.out.println(searchTileHorizontal(x, y)); 
                    System.out.println(searchTileVertical(x, y));
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
                           System.out.println(searchTileHorizontal(x, y)); 
                           System.out.println(searchTileVertical(x, y)); 
                    }else{
                        if(!boxes[x][y].getBoxTile().isTileLocked()){
                            System.out.println(searchTileVertical(x, y));
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
                           System.out.println(searchTileVertical(x, y)); 
                           System.out.println(searchTileHorizontal(x, y)); 
                    }else{
                          if(!boxes[x][y].getBoxTile().isTileLocked()){
                                System.out.println(searchTileHorizontal(x, y));
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
        System.out.println("DirectionX:" + directionx);
        System.out.println("DirectionY:" + directiony);
        System.out.println("Result:" + result);
        return result;
    }
    
//    private boolean validateTilesPlayed(int x, int y){
//        boolean directionx =   !boxes[(x + 1 > 14 ? x : x + 1)][y].getBoxTile().isTileLocked() &&
//                               !boxes[(x + 1 > 14 ? x : x + 1)][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)||
//                               !boxes[(x - 1 < 0  ? x : x - 1)][y].getBoxTile().isTileLocked()&&
//                               !boxes[(x - 1 < 0  ? x : x - 1)][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY);
//        
//        boolean directiony =   !boxes[x][(y + 1 > 14 ? y : y + 1)].getBoxTile().isTileLocked() &&
//                               !boxes[x][(y + 1 > 14 ? y : y + 1)].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) ||
//                               !boxes[x][(y - 1 < 0  ? y : y - 1)].getBoxTile().isTileLocked()&&
//                               !boxes[x][(y - 1 < 0  ? y : y - 1)].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY);
//        
//        
//        boolean result = true;
//        if(isFirstPlay()){
//            result = isValidFirstPlay();
//            if(!result){
//                return false;
//            }
//        }
//        if(numberOfTilesPlayed == 1){
//            if( !boxes[x][(y + 1 > 14 ? y : y + 1)].getBoxTile().isTileLocked() ||
//                !boxes[x][(y - 1 < 0  ? y : y - 1)].getBoxTile().isTileLocked() ||
//                !boxes[(x + 1 > 14 ? x : x + 1)][y].getBoxTile().isTileLocked() ||
//                !boxes[(x - 1 < 0  ? x : x - 1)][y].getBoxTile().isTileLocked()){
//                System.out.println(searchTile(x, y, ScrabbleValues.HORIZONTAL));
//                System.out.println(searchTile(x, y, ScrabbleValues.VERICAL));
//            }else{
//                return false;
//            }
//            
//        }else if(directionx){
//                while(!boxes[(x - 1 < 0  ? x : x -1)][y].getBoxTile().isTileLocked() &&
//                      !boxes[(x - 1 < 0  ? x : x -1)][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
//                       --x;
//                }
//                for(int i = 1; i <= numberOfTilesPlayed; i++){
//                    //System.out.println(boxes[x][y].getBoxTile().getLetter() +" "+ x +" "+ y);
//                    if(i == 1){
//                           System.out.println(searchTile(x, y, ScrabbleValues.HORIZONTAL)); 
//                    }else{
//                           System.out.println(searchTile(x, y, ScrabbleValues.VERICAL));
//                    }    
//                    if(boxes[x++][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
//                           result = false;
//                    }
//                }
//        }else if(directiony){
//                while(!boxes[x][(y - 1 < 0  ? y : y -1)].getBoxTile().isTileLocked() &&
//                      !boxes[x][(y - 1 < 0  ? y : y -1)].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
//                       --y;
//                }
//                for(int i = 1; i <= numberOfTilesPlayed; i++){
//                    System.out.println(boxes[x][y].getBoxTile().getLetter() +" "+ x +" "+ y);
//                        if(i == 1){
//                           System.out.println(searchTile(x, y, ScrabbleValues.HORIZONTAL)); 
//                        }else{
//                           System.out.println(searchTile(x, y, ScrabbleValues.VERICAL));
//                        }
//                        if(boxes[x][y++].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
//                           result = false;
//                        }
//                }
//        }else{
//            if(numberOfTilesPlayed > 1){
//                result = false;
//            }
//        }
//        System.out.println("DirectionX:" + directionx);
//        System.out.println("DirectionY:" + directiony);
//        System.out.println("Result:" + result);
//        return result;
//    }
    
    private boolean validateTilesPlayedToPreviouslyPlayed(int x, int y){
        boolean directionx =   !boxes[(x + 1 > 14 ? x : x + 1)][y].getBoxTile().isTileLocked() &&
                               !boxes[(x + 1 > 14  ? x : x + 1)][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)||
                               !boxes[(x - 1 < 0  ? x : x - 1)][y].getBoxTile().isTileLocked()&&
                               !boxes[(x - 1 < 0  ? x : x - 1)][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY);
        
        boolean directiony =   !boxes[x][(y + 1 > 14 ? y : y + 1)].getBoxTile().isTileLocked() &&
                               !boxes[x][(y + 1 > 14 ? y : y + 1)].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) ||
                               !boxes[x][(y - 1 < 0  ? y : y - 1)].getBoxTile().isTileLocked()&&
                               !boxes[x][(y - 1 < 0  ? y : y - 1)].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY);
        boolean result = true;
        if(isFirstPlay()){
            result = isValidFirstPlay();
        }
        if(directionx){
                while(!boxes[(x - 1 < 0  ? x : x -1)][y].getBoxTile().isTileLocked() &&
                      !boxes[(x - 1 < 0  ? x : x -1)][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                       --x;
                }
                for(int i = 1; i <= numberOfTilesPlayed; i++){
                    System.out.println(boxes[x][y].getBoxTile().getLetter() +" "+ x +" "+ y);
                        if(boxes[x++][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                           result = false;
                        }
                }
        }else if(directiony){
                while(!boxes[x][(y - 1 < 0  ? y : y -1)].getBoxTile().isTileLocked() &&
                      !boxes[x][(y - 1 < 0  ? y : y -1)].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                       --y;
                }
                for(int i = 1; i <= numberOfTilesPlayed; i++){
                    System.out.println(boxes[x][y].getBoxTile().getLetter() +" "+ x +" "+ y);
                        if(boxes[x][y++].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                           result = false;
                        }
                }
        }else if(numberOfTilesPlayed > 1){
            result = false;
        }
        System.out.println("DirectionX:" + directionx);
        System.out.println("DirectionY:" + directiony);
        System.out.println("Result:" + result);
        return result;
    }
    
    private String searchTileVertical(int x, int y){
        String words = "";
            while((x + 1 <= 14) && !boxes[x][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                   words+= boxes[x++][y].getBoxTile().getLetter();
            }
        
        return (words.trim().isEmpty()) ? "" : words;
    }
    private String searchTileHorizontal(int x, int y){
        String words = "";
            while((y + 1 <= 14) && !boxes[x][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                   words+= boxes[x][y++].getBoxTile().getLetter();
            }
        
        return (words.trim().isEmpty()) ? "" : words;
    }
    
    private boolean validatePlay(int x, int y){
        boolean error = true;
        int attachedx = x;
        int attachedy = y;
            if(
              (( x + 1 > 14 ? false : 
                    (!boxes[x + 1][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)) | !boxes[x + 1][y].getBoxTile().isTileLocked()) 
                                                                            |
              (x - 1 < 0 ? false : 
                    (!boxes[x - 1][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) | !boxes[x + 1][y].getBoxTile().isTileLocked()))))
            {
                   int increment = 1;
                   while((x - increment) >= 0 && !boxes[x-increment][y].getBoxTile().isTileLocked() &&
                           !boxes[x-increment][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                       ++increment;
                   }
                   x -= increment;
                    for(int j = 1; j <= numberOfTilesPlayed; j++){
                           System.out.println(boxes[x+1][y].getBoxTile().getLetter() +" "+ (x+1) +" "+ y);
                           if(boxes[++x][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                               error = false;
                           }
                    }
            }else{
                   int increment = 1;
                   while((y - increment) >= 0 && !boxes[x][y-increment].getBoxTile().isTileLocked() &&
                           !boxes[x][y-increment].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                       ++increment;
                   }
                   y -= increment;
                   for(int j = 1 ; j <= numberOfTilesPlayed; j++){
                       System.out.println("here "+boxes[x][y+1].getBoxTile().getLetter() +" "+ x +" "+ (y+1));
                       if(boxes[x][++y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                           error = false;
                       }
                   }
                 }
            if(isFirstPlay()){
                return (isValidFirstPlay() && error);
            }
           return (error && isAttached(attachedx, attachedy, (numberOfTilesPlayed == 1))) ;
    }
    
    private boolean isAttached(int x, int y, boolean playedMoreThanOne){
        
        if(playedMoreThanOne){
            return scanBoard(x, y);
        }else{
            if(
              (( x + 1 > 14 ? false : 
                    (!boxes[x + 1][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)) | !boxes[x + 1][y].getBoxTile().isTileLocked()) 
                                                                            |
              (x - 1 < 0 ? false : 
                    (!boxes[x - 1][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY) | !boxes[x + 1][y].getBoxTile().isTileLocked()))))
            {
                    int increment = 1;
                    while((x - increment) >= 0 && !boxes[x-increment][y].getBoxTile().isTileLocked() &&
                           !boxes[x-increment][y].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                       ++increment;
                   }
                    x -= increment;
                     for(int j = 1; j <= numberOfTilesPlayed; j++){
                            //System.out.println(boxes[x+1][y].getBoxTile().getLetter() +" "+ (x+1) +" "+ y);
                            if(scanBoard(++x, y)){
                                return true;
                            }
                     }
             }else{
                    int increment = 1;
                    while((y - increment) >= 0 && !boxes[x][y-increment].getBoxTile().isTileLocked() &&
                           !boxes[x][y-increment].getBoxTile().getLetter().equals(ScrabbleValues.DEFAULT_EMPTY)){
                       ++increment;
                    }
                    y -= increment;
                    for(int j = 1 ; j <= numberOfTilesPlayed; j++){
                        //System.out.println(boxes[x][y+1].getBoxTile().getLetter() +" "+ x +" "+ (y+1));
                        if(scanBoard(x, ++y)){
                             return true;
                        }
                    }
                  }
        }
             
        return false;
    }
    
    private boolean scanBoard(int x, int y){
        if(x == 0 && y == 0){
                if(boxes[x + 1][y].getBoxTile().isTileLocked()){
                        return true;
                }else if(boxes[x][y + 1].getBoxTile().isTileLocked()){
                        return true;
                }
            }else if(x == 14 && y == 0) {
                if(boxes[x - 1][y].getBoxTile().isTileLocked()){
                        return true;
                }else if(boxes[x][y + 1].getBoxTile().isTileLocked()){
                        return true;
                }
            }else if(x == 0 && y == 14) {
                if(boxes[x + 1][y].getBoxTile().isTileLocked()){
                        return true;
                }else if(boxes[x][y - 1].getBoxTile().isTileLocked()){
                        return true;
                }
            }else if(x == 14 && y == 14) {
                if(boxes[x - 1][y].getBoxTile().isTileLocked()){
                        return true;
                }else if(boxes[x][y - 1].getBoxTile().isTileLocked()){
                        return true;
                }
            }else if(x == 0 && y > 0){
                if(boxes[x + 1][y].getBoxTile().isTileLocked()){
                        return true;
                }else if(boxes[x][y - 1].getBoxTile().isTileLocked()){
                        return true;
                }else if(boxes[x][y + 1].getBoxTile().isTileLocked()){
                        return true;
                }
            }else if(x > 0 && y == 0){
                if(boxes[x - 1][y].getBoxTile().isTileLocked()){
                        return true;
                }else if(boxes[x + 1][y].getBoxTile().isTileLocked()){
                        return true;
                }else if(boxes[x][y + 1].getBoxTile().isTileLocked()){
                        return true;
                }
            }else if(x > 0 && y > 0){
                if(boxes[x - 1][y].getBoxTile().isTileLocked()){
                        return true;
                }else if(boxes[x + 1][y].getBoxTile().isTileLocked()){
                        return true;
                }else if(boxes[x][y + 1].getBoxTile().isTileLocked()){
                        return true;
                }else if(boxes[x][y - 1].getBoxTile().isTileLocked()){
                        return true;
                }
            }
        return false;
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
            .addComponent(boardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel boardPanel;
    // End of variables declaration//GEN-END:variables
}
