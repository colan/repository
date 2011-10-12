package org.sample.server;


import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.sample.handler.BizHandler;
import org.sample.handler.MsgHandler;

public class Server {
	
	private int port;
	private MsgHandler msgHandler;
	private ProtocolCodecFactory protocolCodecFactory;

	public Server(int port, MsgHandler msgHandler,ProtocolCodecFactory protocolCodecFactory) {
		super();
		this.port = port;
		this.msgHandler = msgHandler;
		this.protocolCodecFactory = protocolCodecFactory;
	}

	public void startup() throws IOException {
		// ����һ���������Server��Socket,��NIO
		SocketAcceptor acceptor = new NioSocketAcceptor();
		
		// ����������ݵĹ�����
		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		
		// �趨������
		ProtocolCodecFilter filter = new ProtocolCodecFilter(protocolCodecFactory);
		
		chain.addLast("codec",filter);
		
		// �趨�������˵���Ϣ������:һ��DemuxingIoHandler����
		acceptor.setHandler(new BizHandler(msgHandler));
		
		acceptor.setReuseAddress(true);
		acceptor.getSessionConfig().setTcpNoDelay( true );
		
		// �󶨶˿�,����������
		acceptor.bind(new InetSocketAddress(port));
	}
}
