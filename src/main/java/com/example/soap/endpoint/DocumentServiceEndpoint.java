package com.example.soap.endpoint;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;

import com.example.soap.model.Attachment;
import com.example.soap.model.MessageHeader;
import com.example.soap.model.SubmitDocumentRequest;
import com.example.soap.model.SubmitDocumentResponse;
import com.example.soap.service.DocumentPort;

import jakarta.activation.DataHandler;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.ws.soap.MTOM;

@Component
@WebService(targetNamespace = "http://example.com/document", serviceName = "DocumentService")   // MENOUER: DocumentService name of service in WSDL!!!!
@MTOM  // MENOUER: IMPORTANT HIER MTOM CONFIG:
public class DocumentServiceEndpoint implements DocumentPort {

	public SubmitDocumentResponse submitDocument (

			@WebParam(name = "request") SubmitDocumentRequest request,                 // MENOUER: HERE YOU DEFINE MAPPING BETWEEN XML AND CLASS
			@WebParam(name = "MessageHeader", header = true) MessageHeader header      // MENOUER: HERE YOU DEFINE MAPPING BETWEEN XML AND CLASS

	) {

		System.out.println(header.getMessageId());

		System.out.println(request.getTitle());

		for (Attachment attachment : request.getAttachments()) {

//            System.out.println(
//                a.getContent().getName());

			System.out.println(attachment.getFilename());
			
			saveFile(attachment);

		}

		return new SubmitDocumentResponse("SUCCESS", "ABC-123");

	}

	private void saveFile(Attachment attachment) {
		
		System.out.println("================================");
		System.out.println("filename  = [" + attachment.getFilename() + "]");
		System.out.println("mimeType  = [" + attachment.getMimeType() + "]");
		System.out.println("DH type   = [" + attachment.getContent().getContentType() + "]");
		System.out.println("DH name   = [" + attachment.getContent().getName() + "]");
		System.out.println("================================");
		
		DataHandler dataHandler = attachment.getContent();
		
		try(InputStream inStream = dataHandler.getInputStream())
		{
			Path targetPath = Path.of("attachments").resolve(attachment.getContent().getName());
			Files.copy(inStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Copy Successful");
		}
		catch(IOException e)
		{
			System.out.println("Copy Error");
			System.out.println(e.getStackTrace());
	
		}
		
	}

}
