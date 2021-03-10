package com.company.task4;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class FolderSearchTask extends RecursiveTask<ITWords> {
    private final Folder folder;

    FolderSearchTask(Folder folder) {
        super();
        this.folder = folder;
    }

    @Override
    protected ITWords compute() {
        ITWords ITWords = new ITWords();
        List<RecursiveTask<ITWords>> forks = new LinkedList<>();
        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(subFolder);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocuments()) {
            DocumentCountTask task = new DocumentCountTask(document);
            forks.add(task);
            task.fork();
        }
        for (RecursiveTask<ITWords> task : forks) {
            ITWords.mergeStat(task.join());
        }
        return ITWords;
    }
}