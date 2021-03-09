package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class FolderSearchTask extends RecursiveTask<Statistics> {
    private final Folder folder;

    FolderSearchTask(Folder folder) {
        super();
        this.folder = folder;
    }

    @Override
    protected Statistics compute() {
        Statistics statistics = new Statistics();
        List<RecursiveTask<Statistics>> forks = new LinkedList<>();
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
        for (RecursiveTask<Statistics> task : forks) {
            statistics.mergeStat(task.join());
        }
        return statistics;
    }
}