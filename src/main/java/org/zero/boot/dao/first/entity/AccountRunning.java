package org.zero.boot.dao.first.entity;

import java.math.BigDecimal;
import java.util.Date;

public class AccountRunning {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_running.id
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_running.owner
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    private String owner;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_running.amount
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    private BigDecimal amount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_running.current_amount
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    private BigDecimal currentAmount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_running.datetime
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    private Date datetime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_running.title
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_running.forname
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    private String forname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_running.mark
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    private String mark;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_running.type
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    private Byte type;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column account_running.deleted
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    private Byte deleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_running.id
     *
     * @return the value of account_running.id
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_running.id
     *
     * @param id the value for account_running.id
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_running.owner
     *
     * @return the value of account_running.owner
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public String getOwner() {
        return owner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_running.owner
     *
     * @param owner the value for account_running.owner
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public void setOwner(String owner) {
        this.owner = owner == null ? null : owner.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_running.amount
     *
     * @return the value of account_running.amount
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_running.amount
     *
     * @param amount the value for account_running.amount
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_running.current_amount
     *
     * @return the value of account_running.current_amount
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_running.current_amount
     *
     * @param currentAmount the value for account_running.current_amount
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_running.datetime
     *
     * @return the value of account_running.datetime
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public Date getDatetime() {
        return datetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_running.datetime
     *
     * @param datetime the value for account_running.datetime
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_running.title
     *
     * @return the value of account_running.title
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_running.title
     *
     * @param title the value for account_running.title
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_running.forname
     *
     * @return the value of account_running.forname
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public String getForname() {
        return forname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_running.forname
     *
     * @param forname the value for account_running.forname
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public void setForname(String forname) {
        this.forname = forname == null ? null : forname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_running.mark
     *
     * @return the value of account_running.mark
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public String getMark() {
        return mark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_running.mark
     *
     * @param mark the value for account_running.mark
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public void setMark(String mark) {
        this.mark = mark == null ? null : mark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_running.type
     *
     * @return the value of account_running.type
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public Byte getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_running.type
     *
     * @param type the value for account_running.type
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public void setType(Byte type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column account_running.deleted
     *
     * @return the value of account_running.deleted
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column account_running.deleted
     *
     * @param deleted the value for account_running.deleted
     *
     * @mbg.generated Mon Nov 20 22:47:19 CST 2017
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}