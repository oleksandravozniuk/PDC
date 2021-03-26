package com.company.TextAnalysis;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DocumentCountTask extends RecursiveTask<List<Double>> {
    private final Document document;
    private final WordCounter wordCounter = new WordCounter();

    DocumentCountTask(Document document) {
        super();
        this.document = document;
    }

    @Override
    protected List<Double> compute() {
        return new ArrayList<>(Arrays.asList(wordCounter.statDocCount(document)));
    }
}