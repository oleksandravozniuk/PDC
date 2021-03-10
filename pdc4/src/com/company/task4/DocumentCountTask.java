package com.company.task4;

import java.util.concurrent.RecursiveTask;

class DocumentCountTask extends RecursiveTask<ITWords> {
    private final Document document;
    private final WordCounter wordCounter = new WordCounter();

    DocumentCountTask(Document document) {
        super();
        this.document = document;
    }

    @Override
    protected ITWords compute() {
        return wordCounter.statDocCount(document);
    }
}