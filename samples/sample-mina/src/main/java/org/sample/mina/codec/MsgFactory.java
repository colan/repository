package org.sample.mina.codec;

public interface MsgFactory {
	public abstract Class<?> getClass(int binMsgCode);
}
