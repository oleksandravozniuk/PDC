package com.company.task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ITWords {

    public List<String> itWords = Arrays.asList("programming","it","variable","thread","computer","system","software","server");
    public List<String> documentNames;

    public ITWords(){
        documentNames = new ArrayList<>();
    }

    public void checkWord(String word, String documentName){
        for (String itWord : itWords){
            if(itWord.equalsIgnoreCase(word)){
                if(!documentNames.contains(documentName)){
                    documentNames.add(documentName);
                }
            }
        }
    }
}
