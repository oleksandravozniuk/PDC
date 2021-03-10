package com.company.task4;

import java.util.concurrent.ForkJoinPool;

public class WordCounter {

    private final ForkJoinPool forkJoinPool = new ForkJoinPool();
    private ITWords ITWords = new ITWords();

    ITWords countStatInParallel(Folder folder) {
        return forkJoinPool.invoke(new FolderSearchTask(folder));
    }

    String[] wordsIn(String line) {
        return line.trim().split("(\\s|\\p{Punct})+");
    }

    ITWords countStatOnSingleThread(Folder folder) {
        for (Folder subFolder : folder.getSubFolders()) {
            countStatOnSingleThread(subFolder);
        }
        for (Document document : folder.getDocuments()) {
            ITWords.mergeStat(statDocCount(document));
        }
        return ITWords;
    }

    ITWords statDocCount(Document document) {
        ITWords docITWords = new ITWords();
        for (String line : document.getLines()) {
            for (String word : wordsIn(line)) {
                docITWords.checkWord(word,document.);
            }
        }
        return docITWords;
    }
}