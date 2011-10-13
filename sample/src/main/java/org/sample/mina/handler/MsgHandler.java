package org.sample.mina.handler;

import org.apache.mina.core.session.IoSession;

public interface MsgHandler {
	
	public void sentMsg(IoSession session, Object message) throws Exception;
	
	public void recvMsg(IoSession session, Object message) throws Exception;
}
