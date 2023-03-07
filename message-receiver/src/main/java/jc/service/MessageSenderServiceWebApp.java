package jc.service;

import com.google.gson.Gson;
import jc.entity.Message;
import jc.infra.RabbitConfiguration;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class MessageSenderServiceWebApp {

	@Autowired
	private Gson gson;

	@Autowired
	private final RabbitTemplate rabbitTemplate;

	public MessageSenderServiceWebApp(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void publish(Message m) {
		rabbitTemplate.convertAndSend(
				RabbitConfiguration.Names.exchange,
				RabbitConfiguration.Names.bindingWebApp,
				m);
		log.info("message {} published into WebApp queue.", m.getId());
	}
}
