package wang.miansen.example.activemq.javaclient;

import java.util.Date;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class QueueProducer {

	public static void main(String[] args) {
		
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		
		try {
			// 1.创建连接工厂，需要传入ip和端口号，这里我们使用默认的
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);
			
			// 2.使用连接工厂创建一个连接对象
			connection = connectionFactory.createConnection();
			
			// 3.开启连接
			connection.start();
			
			// 4.使用连接对象创建会话对象。第一个参数传入true，表示开启事务。
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			
			// 5.使用会话对象创建目标对象，这里我们创建的是 queue，也就是点对点的模式。除此之外还有一对多模式：createTopic()
			Destination destination = session.createQueue("queue-test-01");
			
			// 6.使用会话对象创建生产者对象
			producer = session.createProducer(destination);
			
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			// 7.使用会话对象创建一个文本消息对象
			TextMessage textMessage = session.createTextMessage();
			
			String text = "Hello ActiveMQ - " + new Date().getTime();
			
			// 8.设置消息内容
			textMessage.setText(text);
			
			// 9.发送消息
			producer.send(textMessage);
			
			// 10.在session关闭之前提交事务
			session.commit();
			
			System.out.println("已生产消息：" + text);
			
		} catch (JMSException e) {
			try {
				session.rollback();
			} catch (JMSException e1) {
				// 回滚失败时怎么做
			}
		} finally {
			// 10.关闭资源
			if (producer != null) {
				try {
					producer.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			if (session != null) {
				try {
					session.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
