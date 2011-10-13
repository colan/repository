package org.sample.mina;

import java.io.IOException;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.sample.mina.client.Client;
import org.sample.mina.codec.BinMsgBodyDecoder;
import org.sample.mina.codec.BinMsgBodyEncoder;
import org.sample.mina.codec.DefaultBinMsgFactory;
import org.sample.mina.codec.MsgBodyProtocolCodecFactory;
import org.sample.mina.codec.MsgFactory;
import org.sample.mina.protocol.DemoMsg12;
import org.sample.mina.server.Server;



public class ServerMain {
	
	public static void main(String[] args) throws IOException {
		
		MsgFactory msgFactory = new DefaultBinMsgFactory();
		ServerMsgHandler serverMsgHandler = new ServerMsgHandler();
		
		Server server = new Server(8080, serverMsgHandler, new MsgBodyProtocolCodecFactory(msgFactory));
		server.startup();
		
		System.out.println("listening to 8080...");
	}
}
