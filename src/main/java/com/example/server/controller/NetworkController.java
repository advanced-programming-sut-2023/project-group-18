package com.example.server.controller;

import com.example.client.controller.GameController;
import com.example.client.model.Request;
import jakarta.xml.bind.JAXB;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javafx.scene.chart.PieChart;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
        Request request = Request.fromJson(json);
        Method method = null;
        Method[] methods = GameController.class.getDeclaredMethods();
        for (Method method1 : methods) {
            if (method1.getName().equals(method.getName())) {
                method = method1;
                break;
            }
        }
        GameController gameController = GameController.getInstance();
//        Object object = method.invoke();
        return "good night";
    }

    public synchronized int getId() {
        return id++;
    }
}
