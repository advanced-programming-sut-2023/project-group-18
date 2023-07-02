package com.example.client.controller;

import com.example.client.model.Request;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class NetworkController {
    private final static int PORT_NUMBER = 6000;
    private Socket socket;
    private int id;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private static NetworkController networkController;

    private NetworkController() {

    }

    public static NetworkController getInstance() {
        return networkController == null ? networkController = new NetworkController() : networkController;
    }

    public void initializeNetwork() {
        try {
            socket = new Socket("localhost", PORT_NUMBER);
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            id = dataInputStream.readInt();
            System.out.println(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void terminateNetwork() {
        try {
            socket.close();
            dataInputStream.close();
            dataOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object transferData(Request request) {
        System.out.println(request.getMethodName());
        byte[] data = request.toXml().getBytes();
        try {
            dataOutputStream.writeInt(data.length);
            dataOutputStream.write(data);
            dataOutputStream.flush();
            Method method = null;
            Method[] methods;
            if (request.getControllerName().equals("GameController")) {
                methods = GameController.class.getDeclaredMethods();
            } else {
                methods = SignupMethods.class.getDeclaredMethods();
            }
            for (Method method1 : methods) {
                if (method1.getName().equals(request.getMethodName())) {
                    method = method1;
                    break;
                }
            }
            data = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(data);
            if (method.getReturnType() == void.class) {
                return null;
            }
            String xmlString = new String(data);

            JAXBContext jaxbContext = JAXBContext.newInstance(method.getReturnType());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(xmlString);
            return unmarshaller.unmarshal(reader);
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> ArrayList<T> transferData(Request request, Class<T[]> clazz) {
        System.out.println(request.getMethodName());
        byte[] data = request.toXml().getBytes();
        try {
            dataOutputStream.writeInt(data.length);
            dataOutputStream.write(data);
            dataOutputStream.flush();
            Method method = null;
            Method[] methods;
            if (request.getControllerName().equals("GameController")) {
                methods = GameController.class.getDeclaredMethods();
            } else {
                methods = SignupMethods.class.getDeclaredMethods();
            }
            for (Method method1 : methods) {
                if (method1.getName().equals(request.getMethodName())) {
                    method = method1;
                    break;
                }
            }
            data = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(data);
            if (method.getReturnType() == void.class) {
                return null;
            }
            String xmlString = new String(data);

            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(xmlString);
            return new ArrayList<T>((Collection<? extends T>) Arrays.asList(unmarshaller.unmarshal(reader)));
        } catch (IOException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public int getId() {
        return id;
    }
}