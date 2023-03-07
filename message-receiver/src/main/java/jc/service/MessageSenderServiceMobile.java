package jc.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import jc.entity.Message;
import jc.infra.RabbitConfiguration;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MessageSenderServiceMobile {

	@Autowired
	private final RabbitTemplate rabbitTemplate; 
	
	public MessageSenderServiceMobile(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void publish(Message m) { 
		rabbitTemplate.convertAndSend(
				RabbitConfiguration.Names.exchange,
				RabbitConfiguration.Names.bindingMobile,
				m);
		log.info("message {} published into Mobile queue.", m.getId());
	}

}
