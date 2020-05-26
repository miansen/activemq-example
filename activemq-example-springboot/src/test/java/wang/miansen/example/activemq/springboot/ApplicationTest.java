package wang.miansen.example.activemq.springboot;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import wang.miansen.example.activemq.springboot.message.QueueProducer;
import wang.miansen.example.activemq.springboot.message.TopicProducer;

/**
 * @author miansen.wang
 * @date 2020-05-13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

	@Autowired
	private QueueProducer queueProducer;
	
	@Autowired
	private TopicProducer topicProducer;
	
	// 发送消息到队列1
	@Test
	public void sendQueueMessage1() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello ActiveMQ，我是队列消息1");
		queueProducer.sendQueueMessage1(map);
	}
	
	// 发送消息到队列2
	@Test
	public void sendQueueMessage2() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello ActiveMQ，我是队列消息2");
		queueProducer.sendQueueMessage2(map);
		queueProducer.sendQueueMessage2(map);
		queueProducer.sendQueueMessage2(map);
	}
	
	// 发送消息到主题1
	@Test
	public void sendTopicMessage1() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello ActiveMQ，我是主题消息1");
		topicProducer.sendTopicMessage1(map);
	}
	
	// 发送消息到主题2
	@Test
	public void sendTopicMessage2() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("message", "Hello ActiveMQ，我是主题消息2");
		topicProducer.sendTopicMessage2(map);
	}
	
}
