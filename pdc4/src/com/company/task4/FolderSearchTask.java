package com.company.task4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSearchTask extends RecursiveTask<List<Double>> {
    private final Folder folder;
    private List<Double> results;

    FolderSearchTask(Folder folder) {
        super();
        this.folder = folder;
        results = new ArrayList<>();
    }

    FolderSearchTask(Folder folder, List<Double> results) {
        super();
        this.folder = folder;
        this.results = results;
    }

    @Override
    protected List<Double> compute() {
        List<RecursiveTask<List<Double>>> forks = new LinkedList<>();
        for (Folder subFolder : folder.getSubFolders()) {
            FolderSearchTask task = new FolderSearchTask(subFolder, results);
            forks.add(task);
            task.fork();
        }
        for (Document document : folder.getDocuments()) {
            DocumentCountTask task = new DocumentCountTask(document);
            forks.add(task);
            task.fork();
        }
        for (RecursiveTask<List<Double>> task : forks) {
            results.add(task.join().stream().findFirst().orElse(0.0));
        }
        return results;
    }
}