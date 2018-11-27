package com.javacourse.institutes;

import com.javacourse.Document;
import com.javacourse.DocumentManager;

import java.util.*;

/**
 * Created by Yuliia Tesliuk on 11/26/2018
 */
public abstract class AbstractInstitute  implements Runnable {
    private String name;
    private ArrayList<Document> documents;
    private DocumentManager docManager;
    private boolean needToWaitBeforeNewRequest = false;

    AbstractInstitute(String name, DocumentManager docManager) {
        this.name = name;
        this.docManager = docManager;
        this.documents = new ArrayList<>();
    }

    @Override
    public void run() {
        while(getNewDocument(getDocManager())){
            if(needToWaitBeforeNewRequest){
                waitBeforeNewRequest(100);
            }
        }
    }

    protected boolean getNewDocument(DocumentManager manager){
        try {
            manager.offerDocument(this);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAccepted(Document document){
        return takeDocumentForRevision(document);
    }

    protected abstract boolean takeDocumentForRevision(Document document);

    private void waitBeforeNewRequest(int millis) {
        try {
            Thread.sleep(millis);
            needToWaitBeforeNewRequest = false;
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
    public DocumentManager getDocManager() {
        return docManager;
    }
    public void setDocManager(DocumentManager docManager) {
        this.docManager = docManager;
    }
    public boolean isNeedToWaitBeforeNewRequest() {
        return needToWaitBeforeNewRequest;
    }
    public void setNeedToWaitBeforeNewRequest(boolean needToWaitBeforeNewRequest) {
        this.needToWaitBeforeNewRequest = needToWaitBeforeNewRequest;
    }
}
