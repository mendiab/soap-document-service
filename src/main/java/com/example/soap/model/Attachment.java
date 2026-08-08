package com.example.soap.model;

import jakarta.activation.DataHandler;
import jakarta.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "attachment")
public class Attachment {


    private String filename;


    private String mimeType;


    @XmlMimeType("application/octet-stream")
    private DataHandler content;


    public DataHandler getContent() {
        return content;
    }


	public String getFilename() {
		return filename;
	}


	public String getMimeType() {
		return mimeType;
	}

}
