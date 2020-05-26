package wang.miansen.example.activemq.javaclient;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * @author miansen.wang
 * @date 2020-05-06
 */
public class QueueConsumer {

	public static void main(String[] args) throws JMSException {

		// 1.创建连接工厂，需要传入ip和端口号，这里我们使用默认的
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);

		// 2.使用连接工厂创建一个连接对象
		Connection connection = connectionFactory.createConnection();

		// 3.开启连接
		connection.start();

		// 4.使用连接对象创建会话对象。
		Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

		// 5.使用会话对象创建目标对象，这里我们创建的是 queue，也就是点对点的模式。与生产者保持一致，并且名称也要一致。
		Destination destination = session.createQueue("queue-test-01");

		// 6.使用会话对象创建消费者对象
		MessageConsumer consumer = session.createConsumer(destination);

		// 7.通过监听器的方式来接收消息
		consumer.setMessageListener(new MessageListener() {

			@Override
			public void onMessage(Message message) {
				try {
					if (message instanceof TextMessage) {
						System.out.println("已消费消息：" + ((TextMessage) message).getText());
						message.acknowledge();
						// session.commit();
					}
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
