package org.sample.protocol;

import org.apache.mina.core.buffer.IoBuffer;

public interface MsgBody {
	
	public void readData(IoBuffer ioBuffer);
	
	public IoBuffer writeData();
	
	public abstract int msgBodyCode();
}
