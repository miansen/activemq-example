package wang.miansen.example.activemq.springmvc;

import java.util.Map;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

public class QueueProducer {

	private JmsTemplate jmsTemplate;
	
	public void sendMessage(final Queue queue, final Map<String, Object> mapMessage) {
		jmsTemplate.convertAndSend(queue, mapMessage);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
}
