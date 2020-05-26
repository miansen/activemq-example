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
public class QueueConsumer {

	// 监听队列1的消息
	@JmsListener(destination = "springboot-activemq-queue-1", containerFactory = "jmsListenerContainerQueue")
	public void receiveQueueMessage1(Message message) {
		if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			try {
				System.out.println("队列消费者1收到了消息：" + mapMessage.getString("message"));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	// 监听队列2的消息
	@JmsListener(destination = "springboot-activemq-queue-2", containerFactory = "jmsListenerContainerQueue")
	public void receiveQueueMessage2(Message message) {
		if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			try {
				System.out.println("队列消费者2收到了消息：" + mapMessage.getString("message"));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	// 监听队列2的消息
	@JmsListener(destination = "springboot-activemq-queue-2", containerFactory = "jmsListenerContainerQueue")
	public void receiveQueueMessage3(Message message) {
		if (message instanceof MapMessage) {
			MapMessage mapMessage = (MapMessage) message;
			try {
				System.out.println("队列消费者3收到了消息：" + mapMessage.getString("message"));
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

}
