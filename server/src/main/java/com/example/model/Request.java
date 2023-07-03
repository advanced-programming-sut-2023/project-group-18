package com.example.model;

import jakarta.xml.bind.*;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;

@XmlRootElement
public class Request {
    @XmlElement
    private Class controller;
    @XmlElement
    private String methodName;
    @XmlElement
    private ArrayList<Object> arguments;
    public Request() {
    }

    public Request(Class controller, String methodName, Object... arguments) {
        this.controller = controller;
        this.methodName = methodName;
        this.arguments = new ArrayList<>(Arrays.asList(arguments));
    }

    public String toXml() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(this, stringWriter);
            return stringWriter.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static Request fromXml(String xml) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Request.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            return (Request) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMethodName() {
        return this.methodName;
    }

    public ArrayList<Object> getArguments() {
        return this.arguments;
    }

    public Class getController() {
        return controller;
    }
}