package com.example.restapi.testModels;

public class TransactionModel {

	private Integer transactionId;
	private Integer userId;
	private String description;
	private Integer amount;
	
	public TransactionModel(Integer transactionId, Integer userId, String description, Integer amount) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.description = description;
		this.amount = amount;
	}

	public TransactionModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the transactionId
	 */
	public Integer getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the userId
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the amount
	 */
	public Integer getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	
}
