/*
 * Project Name: mqtt
 * File Name: QuereConsumerDemo.java
 * Class Name: QuereConsumerDemo
 *
 * Copyright 2018 Hengtian Software Inc
 *
 * Licensed under the Hengtiansoft
 *
 * http://www.hengtiansoft.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.meng.quere;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Description: TODO
 *
 * @author shuaimeng
 * @since 19.06.2019
 */
public class QuereConsumerDemo {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER; // 默认连接
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD; // 默认密码
    //    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL; // 默认连接地址 为 failover://tcp://localhost:61616
    private static final String BROKER_URL = "failover://tcp://localhost:61616"; // 指定连接地址 (my VM)

    public static void main(String args[]) {
//        consumer1();
        consumer2();


    }


    /**
     * consumer方式1
     * 死循环方式去消费
     */
    private static void consumer1() {
        ConnectionFactory connectionFactory; // 连接工程，生产Connection
        Connection connection = null; // 连接
        Session session; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地
        MessageConsumer messageConsumer; // 消息消费者

        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // 消费消息不需要事务，自动确认
            destination = session.createQueue("FirstQueue"); // 创建消息队列

            messageConsumer = session.createConsumer(destination); // 创建消息消费者

            while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);// 设置延时为100s
                if (textMessage != null) { // 接收到消息
                    System.out.println("接收的消息：" + textMessage.getText());
                } else {
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * 消费方式二
     * 设置监听
     */
    private static void consumer2() {
        ConnectionFactory connectionFactory; // 连接工程，生产Connection
        Connection connection = null; // 连接
        Session session; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地
        MessageConsumer messageConsumer; // 消息消费者

        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); // 消费消息不需要事务，自动确认
            destination = session.createQueue("FirstQueue"); // 创建消息队列

            messageConsumer = session.createConsumer(destination); // 创建消息消费者

            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("收到消息：" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });// 注册消息监听
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
