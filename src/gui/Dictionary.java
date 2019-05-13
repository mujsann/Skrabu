/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Conrad Joye Thomas
 */
public class Dictionary {

    private final ArrayList<String> wordList;
    private String dictionaryType;
    public static final String ENGLISH = "ENGLISH";
    public static final String YORUBA = "YORUBA";
    private final String ENGLISH_DICTIONARY = "EnglishDictionary.txt";
    private final String YORUBA_DICTIONARY = "YorubaDictionary.txt";
    
    public Dictionary(String dictionaryType) throws Exception{
        this.wordList = new ArrayList<>();
        setDictionaryType(dictionaryType);
    }

    private void setDictionary(String dictionaryName){
        Scanner s = new Scanner(getClass().getResourceAsStream(dictionaryName));
        while(s.hasNext()){
            wordList.add(s.nextLine());
        }
    }
    
    public boolean findWord(String word){
        return wordList.contains(word.toUpperCase(Locale.ROOT));
    }

    public final void setDictionaryType(String dictionaryType) throws Exception{
        if(dictionaryType.equalsIgnoreCase(ENGLISH)){
            dictionaryType = dictionaryType.toUpperCase(Locale.ROOT);
        }else if(dictionaryType.equalsIgnoreCase(YORUBA)){
            dictionaryType = dictionaryType.toUpperCase(Locale.ROOT);
        }
        this.dictionaryType = dictionaryType;
        switch(dictionaryType){
            case ENGLISH:
                setDictionary(ENGLISH_DICTIONARY);
                break;
            case YORUBA:
                setDictionary(YORUBA_DICTIONARY);
                break;
            default:
                throw new Exception("Invalid Dictionary!");
        }
    }
}
