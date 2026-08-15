package com.example.soap.config;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.soap.service.DocumentPort;

@Configuration
public class SoapClientConfiguration {

    @Bean
    public DocumentPort documentPort() {

        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        factory.setServiceClass(DocumentPort.class);
        factory.setAddress("http://localhost:8080/services/documents");

        factory.setProperties(
            java.util.Map.of(
                "mtom-enabled", Boolean.TRUE
            )
        );

        return factory.create(DocumentPort.class);
    }
}
