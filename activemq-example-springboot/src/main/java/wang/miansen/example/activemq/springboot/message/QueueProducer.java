package wang.miansen.example.activemq.springboot.message;

import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {

	@Autowired
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Resource(name = "springboot-activemq-queue-1")
	private Queue queue1;
	
	@Resource(name = "springboot-activemq-queue-2")
	private Queue queue2;
	
	// 发送消息到队列1
	public void sendQueueMessage1(final Map<String, Object> mapMessage) {
		jmsMessagingTemplate.convertAndSend(queue1, mapMessage);
	}
	
	// 发送消息到队列2
	public void sendQueueMessage2(final Map<String, Object> mapMessage) {
		jmsMessagingTemplate.convertAndSend(queue2, mapMessage);
	}

}
