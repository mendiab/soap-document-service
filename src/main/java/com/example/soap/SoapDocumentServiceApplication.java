package com.example.soap;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.soap.model.Attachment;
import com.example.soap.model.MessageHeader;
import com.example.soap.model.SubmitDocumentRequest;
import com.example.soap.model.SubmitDocumentResponse;
import com.example.soap.service.DocumentPort;

import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;

@SpringBootApplication
public class SoapDocumentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoapDocumentServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner testDocumentClient(DocumentPort documentPort) {
		return args -> {

			Thread.startVirtualThread(() -> {
				try {
					SubmitDocumentRequest request = new SubmitDocumentRequest();

					request.setTitle("MENOUER TEST REQUEST");
					request.setDescription("MENOUER REQUEST DESCRIPTION");

					addAttachments(request);

					MessageHeader header = new MessageHeader();

					header.setMessageId("MESSAGE ID: 1111111111111111");
					header.setReceiver("MENOUER RECEIVER");
					header.setSender("MENOUER SENDER");
					header.setSendDate(LocalDateTime.now());

					System.out.println("Calling SOAP service...");

					SubmitDocumentResponse response = documentPort.submitDocument(request, header);

					System.out.println("SOAP response: " + response);

				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		};
	}

	private void addAttachments(SubmitDocumentRequest request) throws IOException {
		List<Attachment> attachments = new ArrayList<>();

		Path[] files = { Path.of("attachments/DB_Rechnung_451963121758.pdf"), Path.of("attachments/DB_Ticket_599328187534.pdf") };
		for (Path path : files) {

			FileDataSource dataSource = new FileDataSource(path.toFile());

			DataHandler dataHandler = new DataHandler(dataSource);

			Attachment attachment = new Attachment();

			attachment.setFilename(path.getFileName().toString());

			attachment.setMimeType(Files.probeContentType(path));

			attachment.setContent(dataHandler);

			attachments.add(attachment);
		}

		request.setAttachments(attachments);
	}

}
