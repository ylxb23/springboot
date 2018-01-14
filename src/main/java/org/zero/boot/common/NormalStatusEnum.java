package org.zero.boot.common;

/**
 * 
 * @date 2017年12月28日 下午11:05:31
 * @author zero
 */
public enum NormalStatusEnum {
	OK((byte) 1, "正常"),
	FROZEN((byte) 2, "冻结"),
	;
	private byte status;
	private String desc;
	private NormalStatusEnum(byte s, String d) {
		this.status = s;
		this.desc = d;
	}
	public byte getStatus() {
		return status;
	}
	public String getDesc() {
		return desc;
	}
	public static NormalStatusEnum valueOf(Byte s) {
		if(s == null) {
			throw new IllegalArgumentException("状态值未定义");
		}
		NormalStatusEnum[] values = values();
		for(NormalStatusEnum item : values) {
			if(s == item.getStatus()) {
				return item;
			}
		}
		throw new IllegalArgumentException("状态值[" + s + "]未定义");
	}
	public boolean isOk() {
		if(this.status == 1) {
			return true;
		}
		return false;
	}
	public static boolean isOk(Byte s) {
		if(s == null || s != 1) {
			return false;
		}
		return true;
	}
}
