package wang.miansen.example.activemq.springmvc;

import java.util.Map;

import javax.jms.Topic;

import org.springframework.jms.core.JmsTemplate;

public class TopicProducer {

	private JmsTemplate jmsTemplate;
	
	public void sendMessage(final Topic topic, final Map<String, Object> mapMessage) {
		jmsTemplate.convertAndSend(topic, mapMessage);
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
}
