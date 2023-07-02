package com.example.server.controller;

import com.example.server.controller.GameController;
import com.example.server.model.Request;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

    private String process(String json) {
        Request request = Request.fromXml(json);
        Object controller = GameController.getInstance();
        Method method = null;
        Method[] methods = GameController.class.getDeclaredMethods();
        for (Method method1 : methods) {
            if (method1.getName().equals(request.getMethodName())) {
                method = method1;
                break;
            }
        }
        ArrayList<String> argumentArrayList = request.getArguments();
        Object[] arguments = new Object[argumentArrayList.size()];
        for (String argument : argumentArrayList) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance();
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Object result = method.invoke(controller, arguments);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        GameController gameController = GameController.getInstance();
//        Object object = method.invoke();
        return "good night";
    }

    public synchronized int getId() {
        return id++;
    }
}
