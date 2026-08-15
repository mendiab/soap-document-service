package com.example.soap.model;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlType(name = "SubmitDocumentResponseType", namespace = "http://example.com/document")
@XmlRootElement(name = "SubmitDocumentResponse", namespace = "http://example.com/document")
public class SubmitDocumentResponse {

	private String status;

	private String reference;

	// Required by JAXB
	public SubmitDocumentResponse() {
	}

	public SubmitDocumentResponse(String status, String reference) {

		this.status = status;
		this.reference = reference;
	}

	public String getStatus() {
		return status;
	}

	public String getReference() {
		return reference;
	}

	@Override
	public String toString() {
		return "SubmitDocumentResponse [status=" + status + ", reference=" + reference + "]";
	}

}
