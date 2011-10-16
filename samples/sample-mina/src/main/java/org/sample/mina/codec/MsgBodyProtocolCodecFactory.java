package org.sample.mina.codec;

import org.apache.mina.filter.codec.demux.DemuxingProtocolCodecFactory;
import org.sample.mina.protocol.BinMsgBody;


public class MsgBodyProtocolCodecFactory extends DemuxingProtocolCodecFactory {

	public MsgBodyProtocolCodecFactory(MsgFactory msgFactory) {
		super();
		
		addMessageEncoder(BinMsgBody.class, BinMsgBodyEncoder.class);
		addMessageDecoder(new BinMsgBodyDecoder(msgFactory));
	}
}
