package com.sun.zq.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
	private static final String QUEUE_NAME = "test.queue";
	
	public static void main(String[] args) throws IOException, TimeoutException {
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置相关信息
		factory.setHost("localhost");
		//创建一个连接
		Connection connection = factory.newConnection();
		//创建一个通道
		Channel channel = connection.createChannel();
		//声明一个队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		String message = "hello rabbitmq";
		//发送消息到队列中
		channel.basicPublish("", QUEUE_NAME, null,message.getBytes("UTF-8"));
		
		System.out.println("Producer send '" + message +"'");
		
		//关闭通道
		channel.close();
		//关闭连接
		connection.close();
		
	}

}
