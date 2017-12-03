package org.zero.boot.domain.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 流水记录请求内容
 * @date 2017年11月20日 下午9:44:30
 * @author zero
 */
public class RunningWaterPostRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String owner;
	private BigDecimal amount;
	private Date datetime;
	private String title;
	private String forname;
	private String mark;
	private Byte type;

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getForname() {
		return forname;
	}

	public void setForname(String forname) {
		this.forname = forname;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "RunningWaterPostRequest [owner=" + owner + ", amount=" + amount + ", datetime=" + datetime + ", title="
				+ title + ", forname=" + forname + ", mark=" + mark + ", type=" + type + "]";
	}

	
}
