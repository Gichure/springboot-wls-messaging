/**
 * 
 */
package com.pgichure.poc.controllers;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pgichure.poc.models.Message;

/**
 * @author Paul
 *
 */
@RestController
@RequestMapping("/jms/queue")
public class JmsController {

	@Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping
    public String queueMessage(@RequestBody @Valid Message message){
        prepareFinalMsgForDefaultQ(message);
        jmsTemplate.convertAndSend(message);
        return "Message Queue!";

    }

    private void prepareFinalMsgForDefaultQ(@RequestBody @NotNull @Valid Message message) {
        String msg = message.getMessage();
        String finalMsg = msg + ". MSG FOR QUEUE.";
        message.setMessage(finalMsg);

    }
    
}
