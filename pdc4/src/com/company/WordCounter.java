package com.company;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class WordCounter {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();
    private Statistics statistics = new Statistics();

    Statistics countStatInParallel(Folder folder) {
        return forkJoinPool.invoke(new FolderSearchTask(folder));
    }

    String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    Statistics countStatOnSingleThread(Folder folder) {
        for (Folder subFolder : folder.getSubFolders()) {
            countStatOnSingleThread(subFolder);
        }
        for (Document document : folder.getDocuments()) {
            statistics.mergeStat(statDocCount(document));
        }
        return statistics;
    }

    Statistics statDocCount(Document document) {
        Statistics docStatistics = new Statistics();
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                if(word.length()>0){
                    docStatistics.count++;
                    if (word.length() < docStatistics.minValue) {
                        docStatistics.minValue = word.length();
                    }
                    if(word.length() > docStatistics.maxValue){
                        docStatistics.maxValue = word.length();
                    }
                    if(docStatistics.countLength.containsKey(word.length())){
                        docStatistics.countLength.replace(word.length(), docStatistics.countLength.get(word.length()) + 1);
                    } else{
                        docStatistics.countLength.put(word.length(),1);
                    }
                }
            }
        }
        return docStatistics;
    }
}