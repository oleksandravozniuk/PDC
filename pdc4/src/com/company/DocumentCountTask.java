package com.company;

import java.util.concurrent.RecursiveTask;

class DocumentCountTask extends RecursiveTask<Statistics> {
    private final Document document;
    private final WordCounter wordCounter = new WordCounter();

    DocumentCountTask(Document document) {
        super();
        this.document = document;
    }

    @Override
    protected Statistics compute() {
        return wordCounter.statDocCount(document);
    }
}