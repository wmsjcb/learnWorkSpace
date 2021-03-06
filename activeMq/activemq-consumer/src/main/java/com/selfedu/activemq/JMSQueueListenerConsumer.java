package com.selfedu.activemq;


import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JMSQueueListenerConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory =
                new ActiveMQConnectionFactory("tcp://192.168.1.155:61616");
        Connection connection = null;
        try {
            connection= connectionFactory.createConnection();
            connection.start();
            //创建session
            Session session =connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
           //创建目的地
            Destination destination = session.createQueue("myQuenue");

            //创建接收者
            MessageConsumer consumer = session.createConsumer(destination);
           //监听
            MessageListener messageListener=new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println(((TextMessage)message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            };
             consumer.setMessageListener(messageListener);
             //手动阻塞进程
             System.in.read();
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
