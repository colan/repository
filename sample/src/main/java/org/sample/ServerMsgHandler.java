package org.sample;


import org.apache.mina.core.session.IoSession;
import org.sample.handler.MsgHandler;
import org.sample.protocol.DemoMsg12;


public class ServerMsgHandler implements MsgHandler {

	@Override
	public void recvMsg(IoSession session, Object message) throws Exception {
		System.out.println("server recv msg: :" + message);
		
		DemoMsg12 msg = new DemoMsg12();
		msg.setCode(200);
		msg.setMsg("�ɹ�");
		
		session.write(msg);
	}

	@Override
	public void sentMsg(IoSession session, Object message) throws Exception {
		System.out.println("server send msg: :" + message);
	}
}
