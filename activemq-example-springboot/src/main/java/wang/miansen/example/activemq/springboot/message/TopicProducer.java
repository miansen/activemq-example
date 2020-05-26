package wang.miansen.example.activemq.springboot.message;

import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Resource(name = "springboot-activemq-topic-1")
	private Topic topic1;
	
	@Resource(name = "springboot-activemq-topic-2")
	private Topic topic2;
	
	// 发送消息到主题1
	public void sendTopicMessage1(final Map<String, Object> mapMessage) {
		jmsMessagingTemplate.convertAndSend(topic1, mapMessage);
	}
	
	// 发送消息到主题2
	public void sendTopicMessage2(final Map<String, Object> mapMessage) {
		jmsMessagingTemplate.convertAndSend(topic2, mapMessage);
	}

}
