package org.sample.protocol;

import org.apache.mina.core.buffer.IoBuffer;

public abstract class BinMsgBody implements MsgBody {
	
	private byte msgType = 1;
	
	public abstract void readBody(IoBuffer ioBuffer);
	public abstract IoBuffer writeBody();
	
	
	@Override
	public void readData(IoBuffer ioBuffer) {
		ioBuffer.position(0);
		
		ioBuffer.get();
		ioBuffer.getInt();
		ioBuffer.getInt();
		
		readBody(ioBuffer);
	}
	@Override
	public IoBuffer writeData() {
		IoBuffer body = writeBody();
		
		byte[] bodyByte = new byte[body.limit()];
		body.get(bodyByte);
		
		IoBuffer ioBuffer = IoBuffer.allocate(1024, false);
		ioBuffer.setAutoExpand(true);
		ioBuffer.put(msgType);
		ioBuffer.putInt(msgBodyCode());
		ioBuffer.putInt(body.limit());
		ioBuffer.put(bodyByte);
		
		ioBuffer.flip();
		System.out.println(ioBuffer.limit());
		
		return ioBuffer;
	}
	
	public void putString(IoBuffer ioBuffer, String str) {
		if(str == null) {
			ioBuffer.putInt(-1);
		} else {
			ioBuffer.putInt(str.length());
			for(char c : str.toCharArray()) {
				ioBuffer.putChar(c);
			}
		}
	}
	
	public String getString(IoBuffer ioBuffer) {
		int length = ioBuffer.getInt();
		if(length == -1) {
			return null;
		} else {
			char[] chars = new char[length];
			for(int i = 0; i < length; i++) {
				chars[i] = ioBuffer.getChar();
			}
			
			return new String(chars);
		}
	}
	
	public byte getMsgType() {
		return msgType;
	}
	public void setMsgType(byte msgType) {
		this.msgType = msgType;
	}
}
