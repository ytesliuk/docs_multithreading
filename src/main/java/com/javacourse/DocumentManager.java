package com.javacourse;

import com.javacourse.institutes.AbstractInstitute;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

/**
 * Created by Yuliia Tesliuk on 11/26/2018
 */
public class DocumentManager {
    private final int QUEUE_SIZE = 50;

    private List<Document> documents;
    private Deque<Document> documentsQueue;
    private AtomicInteger currentDocumentNumber;
    private Lock lock = new ReentrantLock();

    DocumentManager(List<Document> documents) {
        this.documents = documents;
        documentsQueue = new LinkedList<>();
        currentDocumentNumber = new AtomicInteger(0);
        shuffleDocuments(documents);
    }

    private void shuffleDocuments(List<Document> documents) {
        Collections.shuffle(documents);
        for (int i = 0; i < documents.size(); i++) {
            documents.get(i).setId(i);
        }
    }

    public void createQueue() {
        for (int i = 0; i < QUEUE_SIZE; i++) {
            addToQueue();
        }
        Thread thread = new Thread(() -> {
            while (hasAvailableDocument()) {
                updateQueue();
            }
        });

        thread.setDaemon(true);
        thread.start();
    }

    private boolean addToQueue() {
        return documentsQueue.add(documents.get(currentDocumentNumber.getAndIncrement()));
    }

    private boolean hasAvailableDocument() {
        return documents.size() > currentDocumentNumber.get();
    }

    public void updateQueue() {
        if (documentsQueue.size() <= 25) {
            while (documentsQueue.size() < QUEUE_SIZE && hasAvailableDocument()) {
                addToQueue();
            }
            System.out.println("\nupdated: current document number " + currentDocumentNumber + "\n");
        }
    }

    public void offerDocument(AbstractInstitute institute) throws NoSuchElementException {
        lock.lock();
        System.out.print("locked   ");
        try {
            Document nextDocument = documentsQueue.getFirst();
            if (institute.isAccepted(nextDocument)) {
                System.out.print(institute.getName() + " get " + nextDocument.getId() + " " + nextDocument.getSpeciality());
                documentsQueue.pollFirst();
            } else {
                System.out.print(institute.getName() + " stopped on " + nextDocument.getId() + " " + nextDocument.getSpeciality());
            }
        } finally {
            lock.unlock();
            System.out.println("                     unlock");
        }
    }
}