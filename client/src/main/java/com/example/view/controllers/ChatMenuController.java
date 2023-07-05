package com.example.view.controllers;

import com.example.controller.ChatMenuMethods;
import com.example.model.UsersData;
import com.example.model.chat.*;
import com.example.view.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.IOException;

public class ChatMenuController {
    @FXML
    private Button deleteButton;
    @FXML
    private Button sendButton;
    @FXML
    private VBox buttonVBox;
    @FXML
    private TextField chatTextField;
    @FXML
    private BorderPane borderPane;
    private VBox messagesVBox;
    private final UsersData usersData = UsersData.getInstance();
    private final ChatMenuMethods chatMenuMethods = ChatMenuMethods.getInstance();
    private final DataChat dataChat = DataChat.getInstance();

    @FXML
    public void initialize() {
        if (chatMenuMethods.getPublicChat() != null) {
            System.out.println("public chat");
        } else if (chatMenuMethods.getPrivateChat() != null) {
            System.out.println("private chat");
        } else if (chatMenuMethods.getRoom() != null) {
            System.out.println("room chat");
        }
        messagesVBox = new VBox();
        messagesVBox.setSpacing(20);
        refresh();
        borderPane.setCenter(messagesVBox);
    }

    public void back() throws IOException {
        chatMenuMethods.setPublicChat(null);
        chatMenuMethods.setPrivateChat(null);
        chatMenuMethods.setRoom(null);
        Main.goToMenu("MainMenu");
    }

    public void sendMessage() {
        String text = chatTextField.getText();
        chatTextField.setText("");
        Label label = new Label(usersData.getLoggedInUser().getUsername() + ":" + text);
        messagesVBox.getChildren().add(label);
        if (chatMenuMethods.getPublicChat() != null) {
            chatMenuMethods.setPublicChat(dataChat.addPublicMessage(usersData.getLoggedInUser().getUsername(), text));
        } else if (chatMenuMethods.getPrivateChat() != null) {
            chatMenuMethods.setPrivateChat(dataChat.addPrivateMessage(usersData.getLoggedInUser().getUsername(), text,
                    chatMenuMethods.getPrivateChat().getMembers().get(0).getUsername(), chatMenuMethods.getPrivateChat().getMembers().get(1).getUsername()));
        } else if (chatMenuMethods.getRoom() != null) {
            chatMenuMethods.setRoom(dataChat.addRoomMessage(usersData.getLoggedInUser().getUsername(), text, chatMenuMethods.getRoom().getName()));
        }
        refresh();
    }

    public void refresh() {
        messagesVBox.getChildren().removeAll(messagesVBox.getChildren());
        if (chatMenuMethods.getRoom() != null) {
            messagesVBox.getChildren().add(new Label("Room name: " + chatMenuMethods.getRoom().getName()));
        }
        Chat chat = null;
        if (chatMenuMethods.getPublicChat() != null) {
            chatMenuMethods.setPublicChat(dataChat.getPublicChat());
            chat = chatMenuMethods.getPublicChat();
        } else if (chatMenuMethods.getPrivateChat() != null) {
            chatMenuMethods.setPrivateChat(dataChat.privateChat(chatMenuMethods.getPrivateChat().getMembers().get(0).getUsername(),
                    chatMenuMethods.getPrivateChat().getMembers().get(1).getUsername()));
            chat = chatMenuMethods.getPrivateChat();
        } else if (chatMenuMethods.getRoom() != null) {
            chatMenuMethods.setRoom(dataChat.getRoom(chatMenuMethods.getRoom().getName()));
            chat = chatMenuMethods.getRoom();
        }
        for (Message message : chat.getMessages()) {
            ImageView avatar = new ImageView(new Image(message.getSender().getAvatarPath()));
            avatar.setFitWidth(25);
            avatar.setFitHeight(25);
            Label time = new Label(message.getTime());
            time.setFont(new Font(4));
            System.out.println("message time is: " + message.getTime());
            Label label = new Label(message.getSender().getUsername() + ":" + message.getText());
            HBox hBox = new HBox(avatar, label, time);
            hBox.setSpacing(8);
            label.setOnMouseClicked(mouseEvent -> {
                if (message.getSender().getUsername().equals(usersData.getLoggedInUser().getUsername())) {
                    if (sendButton.getText().equals("send")) {
                        deleteButton.setVisible(true);
                        deleteButton.setOnMouseClicked(mouseEvent1 -> {
                            if (chatMenuMethods.getPublicChat() != null) {
                                chatMenuMethods.setPublicChat(dataChat.deletePublicMessage(message.getTime()));
                            } else if (chatMenuMethods.getPrivateChat() != null) {
                                chatMenuMethods.setPrivateChat(dataChat.deletePrivateMessage(message.getTime(), chatMenuMethods
                                        .getPrivateChat().getMembers().get(0).getUsername(), chatMenuMethods.getPrivateChat().getMembers().get(1).getUsername()));
                            } else if (chatMenuMethods.getRoom() != null) {
                                chatMenuMethods.setRoom(dataChat.deleteRoomMessage(message.getTime(), chatMenuMethods.getRoom().getName()));
                            }
                            deleteButton.setVisible(false);
                            setSendButton();
                            refresh();
                        });
                        sendButton.setText("edit");
                        sendButton.setOnMouseClicked(mouseEvent1 -> {
                            String text = chatTextField.getText();
                            if (chatMenuMethods.getPublicChat() != null) {
                                chatMenuMethods.setPublicChat(dataChat.editPublicMessage(message.getTime(), text));
                            } else if (chatMenuMethods.getPrivateChat() != null) {
                                chatMenuMethods.setPrivateChat(dataChat.editPrivateMessage(message.getTime(), text, chatMenuMethods
                                        .getPrivateChat().getMembers().get(0).getUsername(), chatMenuMethods.getPrivateChat().getMembers().get(1).getUsername()));
                            } else if (chatMenuMethods.getRoom() != null) {
                                chatMenuMethods.setRoom(dataChat.editRoomMessage(message.getTime(), text, chatMenuMethods.getRoom().getName()));
                            }
                            chatTextField.setText("");
                            label.setText(message.getSender().getUsername() + ":" + text);
                            setSendButton();
                        });
                    } else {
                        deleteButton.setVisible(false);
                        setSendButton();
                    }
                }
            });
            messagesVBox.getChildren().add(hBox);
        }
    }

    private void setSendButton() {
        sendButton.setText("send");
        sendButton.setOnMouseClicked(mouseEvent12 -> {
            sendMessage();
            refresh();
        });
    }

    public void delete(MouseEvent mouseEvent) {

    }
}
