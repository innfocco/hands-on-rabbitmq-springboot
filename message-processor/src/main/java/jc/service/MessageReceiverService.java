package jc.service;

import com.google.gson.Gson;
import jc.infra.RabbitConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jc.entity.Message;
import lombok.extern.log4j.Log4j2;
import java.util.Objects;

@Service
@Log4j2
public class MessageReceiverService {

	@Autowired
	private Gson gson;

	@RabbitListener(queues = RabbitConfiguration.Names.exchangeToQueueMobile)
	public void recievedMessageMobile(String message) throws Exception {
		log.info("Processing message to mobile: " + message);
		Message m = gson.fromJson(message, Message.class);
		if (Objects.isNull(m.getHeader())) {
			throw new Exception("message received with no header");
		}
	}

	@RabbitListener(queues = RabbitConfiguration.Names.exchangeToQueueWebApp)
	public void recievedMessageWebApp(String message) throws Exception {
		log.info("Processing message to WebApp: " + message);
		Message m = gson.fromJson(message, Message.class);
		if (Objects.isNull(m.getHeader())) {
			throw new Exception("message received with no header");
		}
	}
}
