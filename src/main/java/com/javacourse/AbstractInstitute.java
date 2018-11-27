package com.javacourse;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by Yuliia Tesliuk on 11/26/2018
 */
public abstract class AbstractInstitute  implements Runnable {
    private String name;
    private ArrayList<Document> documents;
    private DocumentManager docManager;
    private boolean waitBeforeRequest = false;

    public AbstractInstitute(String name, DocumentManager docManager) {
        this.name = name;
        this.docManager = docManager;
        this.documents = new ArrayList<>();
    }

    public abstract boolean takeDocumentForRevision(Document document);

    public boolean getNewDocument(DocumentManager manager){
        try {
            manager.offerDocument(this);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public void run() {
        while(getNewDocument(getDocManager())){
            if(waitBeforeRequest){
                waitBeforeNewRequest(100);
            }
        }
    }

    private void waitBeforeNewRequest(int millis) {
        try {
            Thread.sleep(millis);
            waitBeforeRequest = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }
    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }
    public DocumentManager getDocManager() {
        return docManager;
    }
    public void setDocManager(DocumentManager docManager) {
        this.docManager = docManager;
    }
    public boolean isWaitBeforeRequest() {
        return waitBeforeRequest;
    }
    public void setWaitBeforeRequest(boolean waitBeforeRequest) {
        this.waitBeforeRequest = waitBeforeRequest;
    }
}
