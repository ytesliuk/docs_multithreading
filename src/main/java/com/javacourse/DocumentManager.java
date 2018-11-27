package com.javacourse;

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
        Collections.shuffle(documents);
        for (int i = 0; i < documents.size(); i++) {
            documents.get(i).setId(i);
        }
        documentsQueue = new LinkedList<>();
        currentDocumentNumber = new AtomicInteger(0);
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

    public Deque<Document> getQueue() {
        return documentsQueue;
    }

    public void updateQueue() {
        if (documentsQueue.size() <= 25) {
            while (documentsQueue.size() < QUEUE_SIZE && hasAvailableDocument()) {
                addToQueue();
            }
            System.out.println("\nupdated; " + currentDocumentNumber + "\n");
        }
    }

    public void offerDocument(AbstractInstitute institute) throws NoSuchElementException {
        lock.lock();
        System.out.print("locked   ");
        try {
            if (institute.takeDocumentForRevision(documentsQueue.getFirst())) {
                documentsQueue.pollFirst();
            }
        } finally {
            lock.unlock();
            System.out.println("                     unlock");
        }
    }
}