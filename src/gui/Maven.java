/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author Conrad Joye Thomas
 */
public class Maven {
    private final Board board;
    private String [][]boxes;  
    private final Rack rack;
    private String words = "";
    Dictionary diction;
    private final String[][] boardArrangement = {
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
        public Maven(Board board, String language) throws Exception {
            this.board = board;
            rack = new Rack();
            boxes = board.getBoxesAsString();
            diction = new Dictionary(language);
        }
        
        public boolean getBoardTile(){
            ArrayList<String> wordplay = createWords();
            ArrayList<String[]> newWordplay = new ArrayList<>();
            ArrayList<String[]> allWordPlayed = new ArrayList<>();
            for(int i = 0; i < boxes.length; i++){
                for(String []wo : createGamePlayY(0, i, wordplay)){
                        allWordPlayed.add(wo);
                        //System.out.println(wo[0] + " : " + wo[1] + " : " + wo[2]);
                }
                for(String []wo : createGamePlayX(i, 0, wordplay)){
                        allWordPlayed.add(wo);    
                        //System.out.println(wo[0] + " : " + wo[1] + " : " + wo[2]);
                }
             }
            for(String[] word: allWordPlayed){
                boxes = board.getBoxesAsString();
                String wordPlayed = word[0].trim();
                int index_x = Integer.parseInt(word[1]);
                int index_y = Integer.parseInt(word[2]);
                String direction = word[3];
                for(char a : wordPlayed.toCharArray()){
                    if(direction.equals("x")){
                        if(boxes[index_x][index_y].contains(":LOCKED")){
                            index_y++;
                        }else{
                            boxes[index_x][index_y++] = ""+a;
                        }
                    }else{
                        if(boxes[index_x][index_y].contains(":LOCKED")){
                            index_x++;
                        }else{
                            boxes[index_x++][index_y] = ""+a;
                        }
                    }
                }
                index_x = Integer.parseInt(word[1]);
                index_y = Integer.parseInt(word[2]);
                int score = 0;
                Object[] searchH;
                Object[] searchV;
                boolean proceed = true;
                for(int i = 1; i <= wordPlayed.length(); i++){
                    if(direction.equals("x")){
                        if(i == 1){
                            searchH = searchTileHorizontal(index_x, index_y);
                            searchV = searchTileVertical(index_x, index_y++);
                            if(!diction.findWord(searchH[0].toString())){
                                proceed = false;
                            }
                            if(searchV[0].toString().length() > 1 && !(diction.findWord(searchV[0].toString()))){
                                proceed = false;
                            }
                            if(proceed){
                                score+= calculateScore((HashMap<Integer[], Integer>)searchH[1]);
                                score+= calculateScore((HashMap<Integer[], Integer>)searchV[1]);
                            }else{
                                break;
                            }
                        }else{
                            searchV = searchTileVertical(index_x, index_y++);
                            if(searchV[0].toString().length() > 1 && !diction.findWord(searchV[0].toString())){
                                    proceed = false;
                            }
                            if(proceed){
                                if(searchV[0].toString().length() > 1){
                                    score+= calculateScore((HashMap<Integer[], Integer>)searchV[1]);
                                }
                            }else{
                                break;
                            }
                        }
                    }else{
                        if(i == 1){
                            searchV = searchTileVertical(index_x, index_y);
                            searchH = searchTileHorizontal(index_x++, index_y);
                            if(!(diction.findWord(searchV[0].toString()))){
                                proceed = false;
                            }
                            if(searchH[0].toString().length() > 1 && !diction.findWord(searchH[0].toString())){
                                proceed = false;
                            }
                            if(proceed){
                                score+= calculateScore((HashMap<Integer[], Integer>)searchH[1]);
                                score+= calculateScore((HashMap<Integer[], Integer>)searchV[1]);
                            }else{
                                break;
                            }
                        }else{
                            searchH = searchTileHorizontal(index_x++, index_y);
                            if(searchH[0].toString().length() > 1 && !(diction.findWord(searchH[0].toString()))){
                                proceed = false;
                            }
                            if(proceed){
                                if(searchH[0].toString().length() > 1){
                                    score+= calculateScore((HashMap<Integer[], Integer>)searchH[1]);
                                }
                            }else{
                                break;
                            }
                        }
                    }
                }
                if(proceed){
                    newWordplay.add(new String[]{score+"", word[0], word[1], word[2], word[3]});
                }
            }
            Collections.sort(newWordplay, new Comparator<String[]>() {
                @Override
                public int compare(final String[] entry1, final String[] entry2) {
                    return Integer.compare(Integer.parseInt(entry1[0]), Integer.parseInt(entry2[0]));
                }
            });
//            for(String []wo : newWordplay){
//                    System.out.println(wo[0] + " : " + wo[1] + " : " + wo[2] + " : " + wo[3] + " : " + wo[4]);
//            }
            Collections.reverse(newWordplay);
            String[]play = newWordplay.get(0);
            board.makePlay(Integer.parseInt(play[2]), Integer.parseInt(play[3]), play[1], play[4], Integer.parseInt(play[0]));
            rack.replenishRack();
            
            return true;
        }

        
        private ArrayList<String> createWords(){
            words = "";
            ArrayList<Tile> rackWords = rack.getRack();
            for (Tile t : rackWords){
                words += t.getLetter().toString();
            }
            return Combinations.combination1(words);
        }
        
        private ArrayList<String[]> createGamePlayY(int x, int y, ArrayList<String> result){
            ArrayList<String[]> playables = new ArrayList<>();
            boxes = board.getBoxesAsString();
            for (String resultWord : result) {
                for (int j = 0; j < boxes[y].length; j++){
                    int end = j;
                    boolean tobreak = false;
                    for(char a : resultWord.toCharArray()){
                        if(resultWord.length() > (boxes[y].length - j)){
                            tobreak = true;
                            break;
                        }
                        if(boxes[end][y].contains(":LOCKED")){
                            if(!boxes[end][y].replace(":LOCKED", "").equalsIgnoreCase(a+"")){
                                tobreak = true;
                            }
                            end++;
                        }else{
                            boxes[end++][y] = ""+a;
                        }
                    }
                    if(tobreak){
                        boxes = board.getBoxesAsString();
                        continue;
                    }else{
                        if(validateTilesPlayed(j, y)){
                            playables.add(new String[]{resultWord, String.valueOf(j), String.valueOf(y), "y"});
                        } 
                    }  
                    boxes = board.getBoxesAsString();
                }
            }
            return playables;
         }
        
        private ArrayList<String[]> createGamePlayX(int x, int y, ArrayList<String> result){
            ArrayList<String[]> playables = new ArrayList<>();
            boxes = board.getBoxesAsString();
            for (String resultWord : result) {
                for (int j = 0; j < boxes[x].length; j++){
                    int end = j;
                    boolean tobreak = false;
                    for(char a : resultWord.toCharArray()){
                        if(resultWord.length() > (boxes[x].length - j)){
                            tobreak = true;
                            break;
                        }
                        if(boxes[x][end].contains(":LOCKED")){
                            if(!boxes[x][end].replace(":LOCKED", "").equalsIgnoreCase(a+"")){
                                tobreak = true;
                            }
                            end++;
                        }else{
                            boxes[x][end++] = ""+a;
                        }
                    }
                    if(tobreak){
                        boxes = board.getBoxesAsString();
                        continue;
                    }else{
                        if(validateTilesPlayed(x, j)){  
                                playables.add(new String[]{resultWord, String.valueOf(x), String.valueOf(j), "x"});
                            } 
                        }  
                    boxes = board.getBoxesAsString();
                }
            }
            return playables;
         }

    public int calculateScore(HashMap<Integer[], Integer> map){
        boolean isDouble = false;
        boolean isTripple = false;
        int score = 0;
        for(Integer[] key : map.keySet()){
            String valueType = boardArrangement[key[0]][key[1]];
            Integer value = map.get(key);
                switch(valueType){
                    case "blank":
                          score += value;
                          break;
                      case "dls":
                          if(boxes[key[0]][key[1]].contains(":LOCKED")){
                             score += value; 
                          }else{
                             score += value * 2;
                          }
                          break;
                      case "dws":
                          if(boxes[key[0]][key[1]].contains(":LOCKED")){
                             score += value; 
                          }else{
                             score += value;
                             isDouble = true;
                          }
                          break;
                      case "star":
                          score += value;
                          break;
                      case "tls":
                          if(boxes[key[0]][key[1]].contains(":LOCKED")){
                             score += value; 
                          }else{
                             score += value * 3;
                          }
                          break;
                      case "tws":
                          if(boxes[key[0]][key[1]].contains(":LOCKED")){
                             score += value; 
                          }else{
                             score += value;
                             isTripple = true;
                          }
                          break;
                }
            }
        if(isDouble){
            score *= 2;
        }
        if(isTripple){
            score *= 3;
        }
        return score;
    }
    
private Object[] searchTileVertical(int x, int y){
        String words = "";
        HashMap<Integer[], Integer> score = new HashMap<>();
        while((x - 1  > 0) && !boxes[x - 1][y].equals("EMPTY")){
             --x;
        }
        while((x  < 15) && !boxes[x][y].equals("EMPTY")){
            score.put(new Integer[]{x,y}, Tile.getLetterValue(boxes[x][y]));  
            words+= boxes[x++][y].replace(":LOCKED", "");
        }
        return new Object[]{words, score};
    }
    
    private Object[] searchTileHorizontal(int x, int y){
        
        String words = "";
        HashMap<Integer[], Integer> score = new HashMap<>();
        while((y - 1 > 0) && !boxes[x][y - 1].equals("EMPTY")){
             --y;
        }
        while((y < 15) && !boxes[x][y].equals("EMPTY")){
            score.put(new Integer[]{x,y}, Tile.getLetterValue(boxes[x][y].replace(":LOCKED", "")));
            words+= boxes[x][y++].replace(":LOCKED", "");
        }
        
        return new Object[]{words, score};
    }
    
    private boolean validateTilesPlayed(int x, int y){
        boolean directionx = false;
        boolean directiony = false;
        ArrayList<int[]> lettersPlayed = new ArrayList<>();
        int numberOfTilesPlayed = 0;
        int xcounter = 0;
        int ycounter = 0;
        for(int i = 0; i < boxes.length; i++){
                for (int j = 0; j< boxes[i].length; j++){
                    if(!boxes[i][j].equals("EMPTY") && !boxes[i][j].contains(":LOCKED")){
                            lettersPlayed.add(new int[]{i,j});
                            ++numberOfTilesPlayed;
                        }
                }
         }
        if(lettersPlayed.size() <= 0){
            return false;
        }
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
        
        if(directionx & directiony){
            return false;
        }else if(numberOfTilesPlayed == 1){
            return  (x + 1 > 14 ? false : boxes[x + 1][y].contains(":LOCKED"))||
                    (x - 1 < 0  ? false : boxes[x - 1][y].contains(":LOCKED"))||
                    (y + 1 > 14 ? false : boxes[x][y + 1].contains(":LOCKED"))||
                    (y - 1 < 0  ? false : boxes[x][y - 1].contains(":LOCKED")); 
        }else if(directionx){
            for(int i = 0; i <= 14; i++){
                if(boxes[x][i].contains(":LOCKED") || boxes[x][i].equals("EMPTY")){
                }else{
                    y = i;
                    break;
                }
            }
            int playedCounter  = 0;
            boolean isconnected = false;
            for(int i = y; i <= 14; i++){
                 if(boxes[x][i].equals("EMPTY")){
                     break;
                 }
                 if(!boxes[x][i].contains(":LOCKED")){
                     ++playedCounter;
                 }
                 if((i + 1 <= 14)){
                     isconnected =  (boxes[x][i + 1].contains(":LOCKED"))||
                                    (i - 1 < 0  ? false : boxes[x][i - 1].contains(":LOCKED"))||
                                    (x + 1 > 14 ? false : boxes[x + 1][y].contains(":LOCKED"))||
                                    (x - 1 < 0  ? false : boxes[x - 1][y].contains(":LOCKED")); 
                 }      
            }
            return (playedCounter == numberOfTilesPlayed) && isconnected;
        }else if(directiony){
            for(int i = 0; i <= 14; i++){
                if(boxes[i][y].contains(":LOCKED") || boxes[i][y].equals("EMPTY")){
                }else{
                    x = i;
                    break;
                }
            }
            int playedCounter  = 0;
            boolean isconnected = false;
            for(int i = x; i <= 14; i++){
                 if(boxes[i][y].equals("EMPTY")){
                     break;
                 }
                 if(!boxes[i][y].contains(":LOCKED")){
                     ++playedCounter;
                 }
                 if((i + 1 <= 14)){
                    isconnected =   (boxes[i + 1][y].contains(":LOCKED"))||
                                    (i - 1 < 0  ? false : boxes[i - 1][y].contains(":LOCKED"))||
                                    (y + 1 > 14 ? false : boxes[i][y + 1].contains(":LOCKED"))||
                                    (y - 1 < 0  ? false : boxes[i][y - 1].contains(":LOCKED")); 
                 } 
            }
            return (playedCounter == numberOfTilesPlayed) && isconnected;
        }
        return false;
    }
}
