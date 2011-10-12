package org.sample;

import org.apache.mina.core.session.IoSession;
import org.sample.handler.MsgHandler;


public class ClientMsgHandler implements MsgHandler {

	@Override
	public void recvMsg(IoSession session, Object message) throws Exception {
		System.out.println("client recv msg: " + message);
		session.close(true);
	}

	@Override
	public void sentMsg(IoSession session, Object message) throws Exception {
		System.out.println("client sent msg: " + message);
	}
}
