package org.sample.protocol;

import org.apache.mina.core.buffer.IoBuffer;

public class DemoMsg11 extends BinMsgBody {
	
	private int count;
	private String name;
	
	@Override
	public int msgBodyCode() {
		return 11;
	}

	@Override
	public void readBody(IoBuffer ioBuffer) {
		count = ioBuffer.getInt();
		name = getString(ioBuffer);
	}

	@Override
	public IoBuffer writeBody() {
		IoBuffer buf = IoBuffer.allocate(1024, false);
		buf.setAutoExpand(true);
		buf.putInt(count);
		putString(buf, name);
		buf.flip();
		
		return buf;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
