package com.javacourse.institutes;

import com.javacourse.*;

/**
 * Created by Yuliia Tesliuk on 11/26/2018
 */
public class MathsInstitute extends AbstractInstitute {

    public MathsInstitute(String name, DocumentManager docManager) {
        super(name, docManager);
    }

    @Override
    protected boolean takeDocumentForRevision(Document document) {
        if (document.getSpeciality() == ScientistSpeciality.MATHEMATICIAN) {
            getDocuments().add(document);
            return true;
        } else {
            setNeedToWaitBeforeNewRequest(true);
            return false;
        }
    }
}
