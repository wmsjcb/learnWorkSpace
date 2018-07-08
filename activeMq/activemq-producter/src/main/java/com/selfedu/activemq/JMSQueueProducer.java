package com.selfedu.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSQueueProducer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.1.155:61616");
        Connection connection = null;
        try {
            connection= connectionFactory.createConnection();
            connection.start();
            //创建session
            Session session =connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
           //创建目的地
            Destination destination = session.createQueue("myQuenue");
            //创建发送者
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //创建发送消息
            TextMessage message = session.createTextMessage("hell world");
            producer.send(message);
            session.commit();

         }catch (Exception e){
           e.printStackTrace();

        }finally {
            if(connection !=null){
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }



    }
}
