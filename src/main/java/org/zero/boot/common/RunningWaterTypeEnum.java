package org.zero.boot.common;

/**
 * 
 * @date 2017年11月20日 下午10:04:36
 * @author zero
 */
public enum RunningWaterTypeEnum {
	INCOME((byte)1, "入账"),
	OUTCOME((byte)2, "出账"),
	UNDEFINED((byte)0, "未定义"),
	;
	private byte type;
	private String name;
	private RunningWaterTypeEnum(byte type, String name) {
		this.type = type;
		this.name = name;
	}
	public byte getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public static RunningWaterTypeEnum parse(byte type) {
		RunningWaterTypeEnum[] values = values();
		for(RunningWaterTypeEnum item : values) {
			if(item.getType() == type) {
				return item;
			}
		}
		return UNDEFINED;
	}
}
