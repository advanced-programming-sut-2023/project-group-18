package com.example.controller;

import com.example.model.Request;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class NetworkController {
    private final static int PORT_NUMBER = 6000;
    private Socket socket;
    private ServerSocket serverSocket;
    private int id;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private static NetworkController networkController;

    private NetworkController() {

    }

    public static NetworkController getInstance() {
        return networkController == null ? networkController = new NetworkController() : networkController;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(PORT_NUMBER);
            while (true) {
                socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        dataInputStream = new DataInputStream(socket.getInputStream());
                        dataOutputStream = new DataOutputStream(socket.getOutputStream());
                        dataOutputStream.writeInt(getId());
                        while (true) {
                            byte[] data = new byte[dataInputStream.readInt()];
                            dataInputStream.readFully(data);
                            String xmlString = new String(data);
                            String response = process(xmlString);
                            data = response.getBytes();
                            dataOutputStream.writeInt(data.length);
                            dataOutputStream.write(data);
                            dataOutputStream.flush();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String process(String xml) {
        Request request = Request.fromXml(xml);
        System.out.println("controller name: " + request.getController());
        System.out.println("request method name: " + request.getMethodName());
        Object controller = request.getController();
        Method method = null;
        Method[] methods = controller.getClass().getDeclaredMethods();
        for (Method method1 : methods) {
            System.out.println("method in method array: " + method1.getName());
            if (method1.getName().equals(request.getMethodName())) {
                method = method1;
                break;
            }
        }
        ArrayList<Object> argumentArrayList = request.getArguments();
        System.out.println("argument in networkController " + argumentArrayList.get(0));
        Object[] arguments = new Object[argumentArrayList.size()];
        arguments = argumentArrayList.toArray();
        System.out.println("argumentArrayList size: " + argumentArrayList.size());
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(request.getClasses().get(0));
//            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//            StringReader stringReader = new StringReader(argumentArrayList.get(0));
//            String test = (String) unmarshaller.unmarshal(stringReader);
//            System.out.println("test is: " + test);
//        } catch (JAXBException e) {
//            throw new RuntimeException(e);
//        }
//        for (int i = 0; i < argumentArrayList.size(); i++) {
//            System.out.println("xml argument: " + argumentArrayList.get(i));
//            System.out.println("class: " + request.getClasses().get(i));
//            try {
//                JAXBContext jaxbContext = JAXBContext.newInstance(request.getClasses().get(i));
//                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
//                StringReader stringReader = new StringReader(argumentArrayList.get(i));
//                arguments[i] = unmarshaller.unmarshal(stringReader);
//            } catch (JAXBException e) {
//                throw new RuntimeException(e);
//            }
//        }
        try {
//            boolean access = method.canAccess();
            method.setAccessible(true);
            System.out.println(arguments[0]);
            Object result = method.invoke(controller, arguments);
            method.setAccessible(false);
            StringWriter stringWriter = new StringWriter();
            JAXBContext.newInstance(method.getReturnType()).createMarshaller().marshal(result, stringWriter);
            return stringWriter.toString();
        } catch (IllegalAccessException | InvocationTargetException | JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized int getId() {
        return id++;
    }
}