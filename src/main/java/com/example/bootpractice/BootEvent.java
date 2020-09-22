package com.example.bootpractice;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.desktop.AppForegroundEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Nawa
 * on 9/22/20.
 * (c)Marathon Computer Systems
 */

@Component
public class BootEvent {

    // application ready event is triggered everytime the application starts
    @EventListener
    public void onApplicationStart(ApplicationReadyEvent event) {
        Logger.getLogger(this.getClass().getSimpleName()).log(Level.INFO, "Application Started : " + event.getApplicationContext().getStartupDate());
    }

    @EventListener
    public void onApplicationFail(AppForegroundEvent event) {
        Logger.getLogger(this.getClass().getSimpleName()).log(Level.SEVERE, "Application Failed in : " + event.getSource().getClass().getSimpleName());
    }
}
