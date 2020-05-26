package wang.miansen.example.activemq.springboot.config;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnectionFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

/**
 * @author miansen.wang
 * @date 2020-05-13
 */
@Configuration
@EnableJms // 开启JMS适配的注解
public class ActiveMQConfig {

	@Value("${spring.activemq.brokerUrl}")
	private String brokerUrl;

	@Value("${spring.activemq.user}")
	private String user;

	@Value("${spring.activemq.password}")
	private String password;

	@Value("${jms.pool.maxConnections}")
	private int maxConnections;

	// 队列1
	@Bean(name = "springboot-activemq-queue-1")
	public Queue queue1() {
		return new ActiveMQQueue("springboot-activemq-queue-1");
	}

	// 队列2
	@Bean(name = "springboot-activemq-queue-2")
	public Queue queue2() {
		return new ActiveMQQueue("springboot-activemq-queue-2");
	}

	// 主题1
	@Bean(name = "springboot-activemq-topic-1")
	public Topic topic1() {
		return new ActiveMQTopic("springboot-activemq-topic-1");
	}

	// 主题2
	@Bean(name = "springboot-activemq-topic-2")
	public Topic topic2() {
		return new ActiveMQTopic("springboot-activemq-topic-2");
	}

	// 真正可以产生连接的工厂
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		return new ActiveMQConnectionFactory(user, password, brokerUrl);
	}

	// ActiveMQ 提供的连接池
	@Bean
	public PooledConnectionFactory pooledConnectionFactory(ActiveMQConnectionFactory connectionFactory) {
		PooledConnectionFactory pool = new PooledConnectionFactory();
		pool.setConnectionFactory(connectionFactory);
		pool.setMaxConnections(maxConnections);
		return pool;
	}

	// 队列监听容器
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(PooledConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setPubSubDomain(false);
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}

	// 主题监听容器
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(PooledConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setPubSubDomain(true);
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}

	// 主题监听容器（持久化订阅）
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerSubscriptionDurable1(
			PooledConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setPubSubDomain(true);
		factory.setConnectionFactory(connectionFactory);
		// 持久化订阅
		factory.setSubscriptionDurable(true);
		// 持久订阅必须指定一个 clientId
		factory.setClientId("springboot-activemq-topic-clientId-1");
		return factory;
	}

	// 主题监听容器（持久化订阅）
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerSubscriptionDurable2(
			PooledConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setPubSubDomain(true);
		factory.setConnectionFactory(connectionFactory);
		// 持久化订阅
		factory.setSubscriptionDurable(true);
		// 持久订阅必须指定一个 clientId
		factory.setClientId("springboot-activemq-topic-clientId-2");
		return factory;
	}
	
	@Bean
    public JmsMessagingTemplate jmsMessagingTemplate(PooledConnectionFactory connectionFactory){
        return new JmsMessagingTemplate(connectionFactory);
    }

}
