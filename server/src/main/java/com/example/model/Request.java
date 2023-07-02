package com.example.model;

import com.google.gson.Gson;
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
    private String controllerName;
    @XmlElement
    private String methodName;
    @XmlElement
    private ArrayList<String> arguments;
    @XmlElement
    private ArrayList<Class> classes;
    public Request() {
    }

    public Request(String controllerName, String methodName, Object... arguments) {
        this.controllerName = controllerName;
        this.methodName = methodName;
        classes = new ArrayList<>();
        String[] objects = new String[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            StringWriter stringWriter = new StringWriter();
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.marshal(this, stringWriter);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
            objects[i] = stringWriter.toString();
            classes.add(arguments[i].getClass());
        }
        this.arguments = new ArrayList<>(Arrays.asList(objects));
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

    public ArrayList<String> getArguments() {
        return this.arguments;
    }

    public String getControllerName() {
        return controllerName;
    }

    public ArrayList<Class> getClasses() {
        return classes;
    }
}