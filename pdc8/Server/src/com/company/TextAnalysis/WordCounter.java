package com.company.TextAnalysis;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class WordCounter {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();
    private List<Double> result = new ArrayList<Double>();

    public List<Double> countStatInParallel(Folder folder) {
        return forkJoinPool.invoke(new FolderSearchTask(folder));
    }

    String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    List<Double> countStatOnSingleThread(Folder folder) {
        for (Folder subFolder : folder.getSubFolders()) {
            countStatOnSingleThread(subFolder);
        }
        for (Document document : folder.getDocuments()) {
            result.add(statDocCount(document));
        }
        return result;
    }

    double statDocCount(Document document) {
        int wordCounter = 0;
        int match = 0;

        ITWords docITWords = new ITWords();
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                if(docITWords.checkWord(word)){
                    match++;
                }
                wordCounter++;
            }
        }
        //System.out.println(match);
        //System.out.println(wordCounter);
        return (double) match/wordCounter;
    }
}