package com.company.TextAnalysis;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ITWords {

    public List<String> itWords = Arrays.asList("programming","it","variable","thread","computer","system","software","server");


    public boolean checkWord(String word){
        for (String itWord : itWords){
            if(itWord.equalsIgnoreCase(word)){
                return true;
            }
        }
        return false;
    }
}
