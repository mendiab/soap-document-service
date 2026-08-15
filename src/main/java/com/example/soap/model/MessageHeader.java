package com.example.soap.model;

import java.time.LocalDateTime;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.Setter;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MessageHeader", namespace = "http://example.com/document")
@XmlRootElement(name = "MessageHeader", namespace = "http://example.com/document")
public class MessageHeader {


    private String messageId;

    private String sender;

    private String receiver;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime sendDate;


    public String getMessageId() {
        return messageId;
    }


    public String getSender() {
        return sender;
    }


	public String getReceiver() {
		return receiver;
	}


	public LocalDateTime getSendDate() {
		return sendDate;
	}


	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}


	public void setSender(String sender) {
		this.sender = sender;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public void setSendDate(LocalDateTime sendDate) {
		this.sendDate = sendDate;
	}


}