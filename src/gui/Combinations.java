/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;

/**
 *
 * @author Conrad Joye Thomas
 */
public class Combinations {
    private static ArrayList<String> words = new ArrayList();
     // print all subsets of the characters in s
    public static ArrayList<String> combination1(String s) { 
        setWords();
        return combination1("", s); 
    }

    // add all subsets of the remaining elements, with given prefix to an Arraylist
    private static ArrayList<String> combination1(String prefix, String s) {
        if (s.length() > 0) {
            words.add(prefix + s.charAt(0));
            combination1(prefix + s.charAt(0), s.substring(1));
            combination1(prefix,               s.substring(1));
        }
        return words;
    }  

    // alternate implementation
    public static ArrayList<String> combination2(String s) { 
        setWords();
        return combination2("", s); 
    }
    
    private static ArrayList<String> combination2(String prefix, String s) {
        words.add(prefix);
        for (int i = 0; i < s.length(); i++)
            combination2(prefix + s.charAt(i), s.substring(i + 1));
        return words;
    }  

    public static void setWords() {
        Combinations.words = new ArrayList();
    }
    
//    public static void main(String ... arg){
//        ArrayList<String> w = combination1("hduejnbk");
//        for (String w1 : w) {
//            System.out.println(w1);
//        }
//    }
 }
