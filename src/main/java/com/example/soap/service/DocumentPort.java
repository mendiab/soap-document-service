package com.example.soap.service;


import com.example.soap.model.MessageHeader;
import com.example.soap.model.SubmitDocumentRequest;
import com.example.soap.model.SubmitDocumentResponse;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@WebService(
    name = "DocumentPort",
    targetNamespace = "http://example.com/document"
)
public interface DocumentPort {

    @WebMethod(operationName = "submitDocument")
    SubmitDocumentResponse submitDocument(

        @WebParam(
            name = "request"
        )
        SubmitDocumentRequest request,

        @WebParam(
            name = "MessageHeader",
            // targetNamespace = "http://example.com/document",
            header = true
        )
        MessageHeader header
    );
}
