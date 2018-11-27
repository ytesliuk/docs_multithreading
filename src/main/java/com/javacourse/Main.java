package com.javacourse;

import com.javacourse.institutes.*;

import java.util.*;

/**
 * Created by Yuliia Tesliuk on 11/26/2018
 */
public class Main {
    private static List<AbstractInstitute> institutes = new ArrayList<>();

    public static void main(String[] args){
        List<Document> documents = fillingDocumentLibrary();
        DocumentManager manager = new DocumentManager(documents);
        manager.createQueue();

        institutes.add(new MathsInstitute("Maths Institute", manager));
        institutes.add(new BiologyInstitute("Biology Institute", manager));
        institutes.add(new GeneralInstitute("General Institute", manager));

        Thread[] threads = new Thread[institutes.size()];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(institutes.get(i));
        }

        for (Thread th: threads) {
            th.start();
        }

        try {
            for (Thread th: threads) {
                th.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printStatistic();

    }


    private static List<Document> fillingDocumentLibrary() {
        List<Document> documents = new ArrayList<>(450);
        for (int i = 0; i < 200; i++) {
            documents.add(new Document("name - " + i, ScientistSpeciality.BIOLOGIST));
        }
        for (int i = 200; i < 450; i++) {
            documents.add(new Document("name - " + i, ScientistSpeciality.MATHEMATICIAN));
        }
        return documents;
    }


    private static void printStatistic() {
        int totalNumberOfDocuments = 0;
        for (AbstractInstitute i : institutes){
            System.out.println(i.getName() + " has " + i.getDocuments().size() + " documents");
            totalNumberOfDocuments += i.getDocuments().size();
        }
        System.out.println("Total amount of collected documents: " + totalNumberOfDocuments);
    }


}
