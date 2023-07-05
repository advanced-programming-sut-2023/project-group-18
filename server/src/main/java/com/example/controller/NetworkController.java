package com.example.controller;

import com.example.model.Request;
import com.example.model.chat.Chat;
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
                            if (response == null) {
                                response = "null";
                            }
                            data = response.getBytes();
                            dataOutputStream.writeInt(data.length);
                            dataOutputStream.write(data);
                            dataOutputStream.flush();
                        }
                    } catch (IOException e) {
                        System.out.println("connection failed");
//                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            System.out.println("connection failed");
//            throw new RuntimeException(e);
        }
    }

    private String process(String xml) {
        Request request = Request.fromXml(xml);
        System.out.println();
        System.out.println("controller name: " + request.getController());
        System.out.println("request method name: " + request.getMethodName());
        Class controller = request.getController();
        Method method = null;
        Method[] methods = controller.getDeclaredMethods();
        System.out.println("the controller class is:" + controller);
        Method instance = null;
        for (Method method1 : methods) {
            System.out.println("method in method array: " + method1.getName());
            if (method1.getName().equals(request.getMethodName())) {
                method = method1;
            }
            if (method1.getName().equals("getInstance"))
                instance = method1;
        }
        System.out.println();
        ArrayList<Object> argumentArrayList = request.getArguments();
        Object[] arguments;
        if (argumentArrayList == null) {
            arguments = null;
        } else
            arguments = argumentArrayList.toArray();
        try {
            boolean access = method.canAccess(instance.invoke(null));
            method.setAccessible(true);
            Object result = method.invoke(instance.invoke(null), arguments);
            if (result instanceof Chat) {
                System.out.println("it is instance of chat--------------------------- chat id: " + ((Chat) result).getId());
            }
            if (result == null)
                return null;
            method.setAccessible(access);
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