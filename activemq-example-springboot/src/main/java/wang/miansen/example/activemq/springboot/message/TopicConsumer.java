package wang.miansen.example.activemq.springboot.message;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author miansen.wang
 * @date 2020-05-13
 */
@Component
public class TopicConsumer {

	// 监听主题1的消息
	@JmsListener(destination = "springboot-activemq-topic-1", containerFactory = "jmsListenerContainerTopic")
	public void receiveQueueMessage1(Message message) {
		if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			try {
				System.out.println("主题消费者1收到了消息：" + mapMessage.getString("message"));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	// 监听主题2的消息
	@JmsListener(destination = "springboot-activemq-topic-2", containerFactory = "jmsListenerContainerSubscriptionDurable1")
	public void receiveQueueMessage2(Message message) {
		if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			try {
				System.out.println("主题消费者2收到了消息：" + mapMessage.getString("message"));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	// 监听主题2的消息
	@JmsListener(destination = "springboot-activemq-topic-2", containerFactory = "jmsListenerContainerSubscriptionDurable2")
	public void receiveQueueMessage3(Message message) {
		if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			try {
				System.out.println("主题消费者3收到了消息：" + mapMessage.getString("message"));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
