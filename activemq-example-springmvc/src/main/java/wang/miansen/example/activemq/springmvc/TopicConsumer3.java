package wang.miansen.example.activemq.springmvc;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * @author miansen.wang
 * @date 2020-05-13
 */
public class TopicConsumer3 implements MessageListener {

	@Override
	public void onMessage(Message message) {
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
