package org.sample.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.sample.mina.protocol.BinMsgBody;


public class BinMsgBodyEncoder<T extends BinMsgBody> implements MessageEncoder<T> {

	@Override
	public void encode(IoSession ioSession, BinMsgBody msgBody, ProtocolEncoderOutput output)
			throws Exception {
		
		output.write( msgBody.writeData() );
	}
}
