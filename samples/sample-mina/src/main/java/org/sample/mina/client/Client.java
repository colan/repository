package org.sample.mina.client;


import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.sample.mina.handler.BizHandler;
import org.sample.mina.handler.MsgHandler;

public class Client {
	
	public static final int CONNECT_TIMEOUT = 3000;

	private int port;
	private String ip;
	private MsgHandler msgHandler;
	private ProtocolCodecFactory protocolCodecFactory;
	
	SocketConnector connector;

	public Client(int port, String ip, MsgHandler msgHandler,
			ProtocolCodecFactory protocolCodecFactory) {
		super();
		this.port = port;
		this.ip = ip;
		this.msgHandler = msgHandler;
		this.protocolCodecFactory = protocolCodecFactory;
	}

	public IoSession startup() throws UnknownHostException {
		connector = new NioSocketConnector();
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(protocolCodecFactory));
		connector.setHandler(new BizHandler(msgHandler));
		ConnectFuture connectFuture = connector.connect(new InetSocketAddress(InetAddress.getByName(ip), port));
        connectFuture.awaitUninterruptibly(CONNECT_TIMEOUT);
        
        IoSession session = connectFuture.getSession();
        return session;
	}
	
	public void dispose() {
		if(connector != null) {
			connector.dispose();
		}
	}
}
