package org.sample.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.sample.protocol.BinMsgBody;


public class BinMsgBodyDecoder implements MessageDecoder {
	
	MsgFactory msgFactory;
	
	public BinMsgBodyDecoder(MsgFactory msgFactory) {
		super();
		this.msgFactory = msgFactory;
	}

	@Override
	public MessageDecoderResult decodable(IoSession ioSession, IoBuffer buf) {
		if(buf.remaining() >= 9) {
			byte msgType = buf.get();
			if(msgType != 1) {
				return NOT_OK;
			} 
			buf.getInt(); //msgBodyCode
			int length = buf.getInt();
			if(buf.remaining() >= length) {
				return OK;
			} else {
				return NEED_DATA;
			}
		} else {
			return NEED_DATA;
		}
	}

	@Override
	public MessageDecoderResult decode(IoSession ioSession, IoBuffer buf,
			ProtocolDecoderOutput output) throws Exception {
		buf.get();
		int msgBodyCode = buf.getInt();
		
		Class clazz = msgFactory.getClass(msgBodyCode);
		BinMsgBody binMsgBody = (BinMsgBody) clazz.newInstance();
		binMsgBody.readData(buf);
		
		output.write(binMsgBody);
		
		return OK;
	}

	@Override
	public void finishDecode(IoSession arg0, ProtocolDecoderOutput arg1)
			throws Exception {
		//do nothing
	}
}
