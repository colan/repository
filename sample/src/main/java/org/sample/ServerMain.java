package org.sample;

import java.io.IOException;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.sample.client.Client;
import org.sample.codec.BinMsgBodyDecoder;
import org.sample.codec.BinMsgBodyEncoder;
import org.sample.codec.DefaultBinMsgFactory;
import org.sample.codec.MsgBodyProtocolCodecFactory;
import org.sample.codec.MsgFactory;
import org.sample.protocol.DemoMsg12;
import org.sample.server.Server;



public class ServerMain {
	
	public static void main(String[] args) throws IOException {
		
		MsgFactory msgFactory = new DefaultBinMsgFactory();
		ServerMsgHandler serverMsgHandler = new ServerMsgHandler();
		
		Server server = new Server(8080, serverMsgHandler, new MsgBodyProtocolCodecFactory(msgFactory));
		server.startup();
		
		System.out.println("listening to 8080...");
	}
}
