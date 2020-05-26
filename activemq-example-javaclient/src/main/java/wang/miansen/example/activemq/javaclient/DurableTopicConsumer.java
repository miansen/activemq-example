package wang.miansen.example.activemq.javaclient;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author miansen.wang
 * @date 2020-05-06
 */
public class DurableTopicConsumer {

	public static void main(String[] args) throws JMSException {

		// 1.创建连接工厂，需要传入ip和端口号，这里我们使用默认的
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);

		// 2.使用连接工厂创建一个连接对象
		Connection connection = connectionFactory.createConnection();
		
		// 3.设置ClientID
		
		connection.setClientID("client2");

		// 4.开启连接
		connection.start();

		// 5.使用连接对象创建会话对象
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 6.使用会话对象创建目的地对象，生产者与消费者的名称要保持一致。
		Topic topic = session.createTopic("topic-test-01");

		// 7.使用会话对象创建主题订阅者
		TopicSubscriber subscriber = session.createDurableSubscriber(topic, "subscriber-2");

		// 8.通过监听器的方式来接收消息
		subscriber.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				try {
					if (message instanceof TextMessage) {
						System.out.println("已接收消息：" + ((TextMessage) message).getText());
					}
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
