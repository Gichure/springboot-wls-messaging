/**
 * 
 */
package com.pgichure.poc.services;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.pgichure.poc.models.Message;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Paul
 *
 */
@Slf4j
@Component
@Transactional
public class JmsQueueListener {
	
    @JmsListener(destination = "jms/NTSAQueue")
    public void listenToMessages(@Valid Message message){
        String text = "MESSAGE RECEIVED AT: "+ LocalDateTime.now()+". MSG: "+message;
        log.info(text);
    }
}
