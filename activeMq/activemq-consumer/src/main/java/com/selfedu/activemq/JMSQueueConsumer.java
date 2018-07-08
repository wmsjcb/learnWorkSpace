package com.selfedu.activemq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSQueueConsumer {
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

            //创建接收者
            MessageConsumer consumer = session.createConsumer(destination);
            TextMessage message = (TextMessage)consumer.receive();
            System.out.println(message.getText());
            //session.commit();
            session.close();
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
