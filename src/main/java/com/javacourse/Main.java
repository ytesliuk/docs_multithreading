package com.javacourse;

import java.util.*;

/**
 * Created by Yuliia Tesliuk on 11/26/2018
 */
public class Main {

    public static void main(String[] args){
        List<Document> documents = fillingDocumentLibrary();
        DocumentManager manager = new DocumentManager(documents);
        AbstractInstitute mathsInst = new MathsInstitute("Maths Institute", manager);
        AbstractInstitute biologyInst = new BiologyInstitute("Biology Institute", manager);
        AbstractInstitute genInst = new GeneralInstitute("General Institute", manager);

        manager.createQueue();

        Thread th1 = new Thread(mathsInst, "MathsInst Thread");
        Thread th2 = new Thread(biologyInst, "BiologyInst Thread");
        Thread th3 = new Thread(genInst, "GenInst Thread");

        th1.start();
        th2.start();
        th3.start();

        try {
            th1.join();
            th2.join();
            th3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(manager.getQueue().size());
        System.out.println(mathsInst.getName() + " has " + mathsInst.getDocuments().size() + " documents");
        System.out.println(biologyInst.getName() + " has " + biologyInst.getDocuments().size() + " documents");
        System.out.println(genInst.getName() + " has " + genInst.getDocuments().size() + " documents");

        System.out.println("sum: " + (mathsInst.getDocuments().size() + biologyInst.getDocuments().size() + genInst.getDocuments().size()));

    }

    private static List<Document> fillingDocumentLibrary() {
        List<Document> documents = new ArrayList<>(450);
        for (int i = 0; i < 200; i++) {
            documents.add(new Document(i, DocumentType.BIOLOGY));
        }
        for (int i = 200; i < 450; i++) {
            documents.add(new Document(i, DocumentType.MATHS));
        }
        return documents;
    }


}
