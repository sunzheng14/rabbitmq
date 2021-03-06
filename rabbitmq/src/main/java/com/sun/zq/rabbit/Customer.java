package com.sun.zq.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;

public class Customer {
	private static final String QUEUE_NAME = "test.queue";

	public static void main(String[] args) throws IOException, TimeoutException {
		// 创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		// 设置相关信息
		factory.setHost("localhost");
		// 创建一个连接
		Connection connection = factory.newConnection();
		// 创建一个通道
		Channel channel = connection.createChannel();
		
		//声明要关注的队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        System.out.println("Customer Waiting Received messages");
        
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				String message = new String(body,"UTF-8");
				System.out.println("Customer Received '" + message + "'");
			}
		};
		
		channel.basicConsume(QUEUE_NAME, true, consumer);
		
		//关闭通道
		channel.close();
		//关闭连接
		connection.close();
	}

}
