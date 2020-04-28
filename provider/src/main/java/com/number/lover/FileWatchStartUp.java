package com.number.lover;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class FileWatchStartUp implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        FileWatch fileWatch = contextRefreshedEvent.getApplicationContext().getBean(FileWatch.class);
        try {
            fileWatch.watch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
