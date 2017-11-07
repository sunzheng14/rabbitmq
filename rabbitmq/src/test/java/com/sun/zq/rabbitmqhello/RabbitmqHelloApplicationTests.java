package com.sun.zq.rabbitmqhello;

import com.sun.zq.task.Receiver;
import com.sun.zq.task.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RabbitmqHelloApplication.class)
public class RabbitmqHelloApplicationTests {
	@Autowired
	private Sender sender;

	@Test
	public void hello(){
		sender.send();

	}

}
