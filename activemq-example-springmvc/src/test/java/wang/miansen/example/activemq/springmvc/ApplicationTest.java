package wang.miansen.example.activemq.springmvc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Queue;
import javax.jms.Topic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import wang.miansen.example.activemq.springmvc.QueueProducer;
import wang.miansen.example.activemq.springmvc.TopicProducer;

/**
 * @author miansen.wang
 * @date 2020-05-13
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:application-context.xml"})
public class ApplicationTest {

	@Resource(name = "activeMQQueue1")
	private Queue queue1;

	@Resource(name = "activeMQQueue2")
	private Queue queue2;

	@Resource(name = "activeMQTopic1")
	private Topic topic1;

	@Resource(name = "activeMQTopic2")
	private Topic topic2;

	@Autowired
	private QueueProducer queueProducer;

	@Autowired
	private TopicProducer topicProducer;

	private Map<String, Object> mapMessage;

	@Before
	public void initMessage() throws Exception {
		mapMessage = new HashMap<>();
		mapMessage.put("message", "Hello ActiveMQ - " + new Date().getTime());
	}

	// 发送消息到队列1
	@Test
	public void queue1() throws Exception {
		queueProducer.sendMessage(queue1, mapMessage);
	}

	// 发送消息到队列2
	@Test
	public void queue2() throws Exception {
		queueProducer.sendMessage(queue2, mapMessage);
	}

	// 发送消息到主题1
	@Test
	public void topic1() throws Exception {
		topicProducer.sendMessage(topic1, mapMessage);
	}

	// 发送消息到主题2
	@Test
	public void topic2() throws Exception {
		topicProducer.sendMessage(topic2, mapMessage);
	}

}
