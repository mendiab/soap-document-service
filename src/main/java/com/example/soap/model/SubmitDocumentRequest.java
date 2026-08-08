package com.example.soap.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubmitDocumentRequestType", namespace = "http://example.com/document")
@XmlRootElement(name = "SubmitDocumentRequest", namespace = "http://example.com/document")
public class SubmitDocumentRequest {

	private String title;

	private String description;

	@XmlElementWrapper(name = "attachments")  // MENOUER: I added only this to allow a soap-request like: <attachments> <attachment> ...</attachment></attachments>
	@XmlElement(name = "attachment")
	private List<Attachment> attachments;

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

}
