package jc.infra;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.*;

@Configuration
public class RabbitConfiguration {

	public static class Names {
		public static final String deadLetterExchange = "deadLetterExchange";
		public static final String exchange = "messageExchange";
		public static final String exchangeToQueueMobile = "message.queue.mobile";
		public static final String exchangeToQueueWebApp = "message.queue.webapp";
		protected static final String dlqQueue = "deadLetter.queue";
		protected static final String bindingMobile = "message-binder-mobile";
		protected static final String bindingWebApp = "message-binder-web";
		protected static final String dlqBinding = "dlq-binder";
	}

	@Bean
	DirectExchange deadLetterExchange() {
		return new DirectExchange(Names.deadLetterExchange);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(Names.exchange);
	}

	@Bean
	Queue dlq() {
		return QueueBuilder.durable(Names.dlqQueue).build();
	}

	@Bean
	Queue queueMobile() {
		return QueueBuilder
				.durable(Names.exchangeToQueueMobile)
				.withArgument("x-dead-letter-exchange", Names.deadLetterExchange)
				.withArgument("x-dead-letter-routing-key", Names.dlqBinding)
				.build();
	}

	@Bean
	Queue queueWebApp() {
		return QueueBuilder
				.nonDurable(Names.exchangeToQueueWebApp)
				.build();
	}

	@Bean
	Binding DLQbinding() {
		return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with(Names.dlqBinding);
	}

	@Bean
	Binding bindingMobile() {
		return BindingBuilder.bind(queueMobile()).to(exchange()).with(Names.bindingMobile);
	}

	@Bean
	Binding bindingWebApp() {
		return BindingBuilder.bind(queueWebApp()).to(exchange()).with(Names.bindingWebApp);
	}

}
