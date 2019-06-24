
package com.meng.virtualTopic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Description: TODO
 *
 * @author shuaimeng
 * @since 24.06.2019
 */
public class VsubTopic {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认连接
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // 默认密码
    //    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL; // 默认连接地址 为 failover://tcp://localhost:61616
    private static final String BROKER_URL = "failover://tcp://localhost:61616"; // 指定连接地址 (my VM)

    public static void main(String args[]) throws JMSException {
        ConnectionFactory connectionFactory; // 连接工程，生产Connection
        Connection connection = null; // 连接
        Session session; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地
        MessageConsumer messageConsumer; // 消息消费者

        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // 消费消息不需要事务，自动确认
        Queue topicA = session.createQueue("Consumer.A.VirtualTopic.TEST");
        Queue topicB = session.createQueue("Consumer.A.VirtualTopic.TEST");

        try {
            messageConsumer = session.createConsumer(topicA); // 创建消息消费者

            messageConsumer.setMessageListener(message -> {
                try {
                    System.out.println("A：收到消息" + ((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });// 注册消息监听
        } catch (JMSException e) {
            e.printStackTrace();
        }

        try {

            messageConsumer = session.createConsumer(topicB); // 创建消息消费者

            messageConsumer.setMessageListener(message -> {
                try {
                    System.out.println("B收到消息：" + ((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            });// 注册消息监听
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
