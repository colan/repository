package org.sample.mina;

import java.net.UnknownHostException;

import org.apache.mina.core.session.IoSession;
import org.sample.mina.client.Client;
import org.sample.mina.codec.DefaultBinMsgFactory;
import org.sample.mina.codec.MsgBodyProtocolCodecFactory;
import org.sample.mina.codec.MsgFactory;
import org.sample.mina.protocol.DemoMsg11;


public class ClientMain {
	public static void main(String[] args) throws UnknownHostException, InterruptedException {
		
		ClientMsgHandler msgHandler = new ClientMsgHandler();
		
		MsgFactory msgFactory = new DefaultBinMsgFactory();
		Client client = new Client(8080, "localhost", msgHandler, new MsgBodyProtocolCodecFactory(msgFactory));
		IoSession session = client.startup();
		
		DemoMsg11 msg = new DemoMsg11();
		msg.setCount(1024);
		msg.setName("xuxiaolei");
		
		session.write(msg);
		
		session.getCloseFuture().awaitUninterruptibly();
		client.dispose();
	}
}
