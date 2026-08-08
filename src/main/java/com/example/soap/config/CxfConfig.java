package com.example.soap.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.soap.endpoint.DocumentServiceEndpoint;

import jakarta.xml.ws.Endpoint;

@Configuration
public class CxfConfig {

	@Bean
	public Endpoint documentEndpoint(Bus bus, DocumentServiceEndpoint service) {

		EndpointImpl endpoint = new EndpointImpl(bus, service);

		endpoint.publish("/documents");

//        endpoint.getFeatures()
//                .add(new MTOMFeature());

		// Properties
		Map<String, Object> properties = new HashMap<>();
		
//		If you don't configure anything
//
//		For attachments, CXF's documented default is:
//
//		attachment-memory-threshold = 1024 KB
//		attachment-directory        = not specified
//		attachment-max-count        = 50

		// 1 MB: attachments <= 1 MB can remain in memory
		properties.put("attachment-memory-threshold", 1024 * 1024);

		// Where CXF stores attachments exceeding the threshold
		properties.put("attachment-directory", "C:/workspaces/soap-attachments");

		// Maximum size of a single attachment
		properties.put("attachment-max-size", 50L * 1024 * 1024); // 50 MB

		properties.put("attachment-max-count", 10);

		endpoint.setProperties(properties);

		return endpoint;
	}
}
