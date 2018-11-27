package com.javacourse;

/**
 * Created by Yuliia Tesliuk on 11/26/2018
 */
public class Document {
    private int id;
    private DocumentType type;

    public Document(int id, DocumentType type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }
}


