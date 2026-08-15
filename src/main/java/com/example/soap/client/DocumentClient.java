package com.example.soap.client;

import org.springframework.stereotype.Service;

import com.example.soap.model.MessageHeader;
import com.example.soap.model.SubmitDocumentRequest;
import com.example.soap.model.SubmitDocumentResponse;
import com.example.soap.service.DocumentPort;

@Service
public class DocumentClient {

    private final DocumentPort documentPort;

    public DocumentClient(DocumentPort documentPort) {
        this.documentPort = documentPort;
    }

    public SubmitDocumentResponse submit(
            SubmitDocumentRequest request,
            MessageHeader header) {

        return documentPort.submitDocument(request, header);
    }
}
