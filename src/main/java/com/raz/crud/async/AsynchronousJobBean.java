package com.raz.crud.async;

import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class AsynchronousJobBean {

    @Async
    public void start(Long id) {
        for (int i = 0; i < 10; i++) {
//            logger.info(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
