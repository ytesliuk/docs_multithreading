package com.javacourse.institutes;

import com.javacourse.*;
import java.util.Random;

/**
 * Created by Yuliia Tesliuk on 11/26/2018
 */
public class GeneralInstitute extends AbstractInstitute {
    private Random random = new Random();
    private int randomRequest;

    public GeneralInstitute(String name, DocumentManager docManager) {
        super(name, docManager);
        randomRequest = random.nextInt(5) + 1;
    }

    @Override
    protected boolean takeDocumentForRevision(Document document) {
        if(randomRequest > 0){
            getDocuments().add(document);
            randomRequest--;
            return true;
        } else {
            setNeedToWaitBeforeNewRequest(true);
            randomRequest = random.nextInt(5) + 1;
            return false;
        }
    }
}
