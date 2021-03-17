package com.company.task4;



import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        WordCounter wordCounter = new WordCounter();
        Folder folder = Folder.fromDirectory(new File("C:\\Users\\Oleksandra\\Desktop\\PDC\\PDC\\pdc4\\documents\\proga"));


        List<Double> statistics =  wordCounter.countStatInParallel(folder);


        printResult(statistics);

    }

    private static void printResult(List<Double> result){
        for(Double doc : result){
            System.out.println(doc);
        }
    }
}
