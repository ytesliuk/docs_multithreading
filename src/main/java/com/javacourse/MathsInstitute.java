package com.javacourse;

/**
 * Created by Yuliia Tesliuk on 11/26/2018
 */
public class MathsInstitute extends AbstractInstitute {

    public MathsInstitute(String name, DocumentManager docManager) {
        super(name, docManager);
    }

    @Override
    public boolean takeDocumentForRevision(Document document) {
        if (document.getType() == DocumentType.MATHS) {
            getDocuments().add(document);
            System.out.print(this.getName() + " get " + document.getId() + " " + document.getType());

            return true;
        } else {
            System.out.print(this.getName() + " stopped on " + document.getId() + " " + document.getType());
            setWaitBeforeRequest(true);
            return false;
        }
    }
}
