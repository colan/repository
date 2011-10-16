package org.sample.mina.codec;

import org.sample.mina.protocol.BinMsgBody;
import org.sample.mina.protocol.DemoMsg11;
import org.sample.mina.protocol.DemoMsg12;


public class DefaultBinMsgFactory implements MsgFactory {
	
	@Override
	public Class<? extends BinMsgBody> getClass(int binMsgCode) {
		switch(binMsgCode) {
		case 11: return DemoMsg11.class;
		case 12: return DemoMsg12.class;
		}
		
		return null;
	}
}
