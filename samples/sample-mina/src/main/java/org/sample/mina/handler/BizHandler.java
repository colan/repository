package org.sample.mina.handler;


import org.apache.mina.core.session.IoSession;
import org.apache.mina.handler.demux.DemuxingIoHandler;

public class BizHandler extends DemuxingIoHandler {
	
	MsgHandler msgHandler;
	
	public BizHandler(MsgHandler msgHandler) {
		super();
		this.msgHandler = msgHandler;
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		System.out.println("messageReceived");
		msgHandler.recvMsg(session, message);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		msgHandler.sentMsg(session, message);
	}
}
