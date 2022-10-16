package com.trainingapps.cropdeal.cropService.messageQ;

import java.util.Date;

public class CustomMessage {

    private String messageId;
    public CustomMessage() {
		
	}
	public CustomMessage(String messageId, String message, Date messageDate) {
		super();
		this.messageId = messageId;
		this.message = message;
		this.messageDate = messageDate;
	}
	private String message;
    private Date messageDate;
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
}
