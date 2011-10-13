package org.sample.mina.protocol;

import org.apache.mina.core.buffer.IoBuffer;

public class DemoMsg12 extends BinMsgBody {
	
	private int code;
	private String msg;
	
	@Override
	public int msgBodyCode() {
		return 12;
	}

	@Override
	public void readBody(IoBuffer ioBuffer) {
		code = ioBuffer.getInt();
		msg = getString(ioBuffer);
	}

	@Override
	public IoBuffer writeBody() {
		IoBuffer buf = IoBuffer.allocate(1024, false);
		buf.putInt(code);
		putString(buf, msg);
		buf.flip();
		
		return buf;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
