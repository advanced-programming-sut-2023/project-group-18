package com.example.model;

import com.example.model.chat.Chat;
import com.example.model.chat.PrivateChat;
import com.example.model.chat.PublicChat;
import com.example.model.chat.Room;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.ArrayList;

@XmlRootElement
public class User implements PasswordRecoveryQuestions {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private int highscore;
    private String avatarPath;
    private int recoveryQuestionNumber;
    private String recoveryAnswer;
    private int score;
    private ArrayList<User> friends = new ArrayList<>();
    @XmlElement
    private ArrayList<User> requests = new ArrayList<>();
    private ArrayList<Chat> chats = new ArrayList<>();
    public User() {
    }
    protected User(String username, String password, String nickname, String email, String slogan, int recoveryQuestionNumber, String recoveryAnswer) {
        this.username = username;
        setPassword(password);
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        this.recoveryQuestionNumber = recoveryQuestionNumber;
        this.recoveryAnswer = SHA256Cryptographic(recoveryAnswer);
        this.highscore = 0;
        this.score = 0;
        avatarPath = User.class.getResource("/avatars/1.png").toExternalForm();
        friends = new ArrayList<>();
        requests = new ArrayList<>();
        chats = new ArrayList<>();
        chats.add(PublicChat.getInstance());
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getHighscore() {
        return highscore;
    }
    public String getPassword() {
        return password;
    }

    public String getRecoveryQuestion() {
        return PASSWORD_RECOVERY_QUESTIONS[recoveryQuestionNumber];
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = SHA256Cryptographic(password);
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void addScore(int score){
        this.score += score;
    }

    public int getScore() {
        return score;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getAvatarPath() {
        System.out.println(avatarPath);
        return avatarPath;
    }

    public void setScore(int score) {
        if (this.highscore < score)
            this.highscore = score;
        this.score = score;
    }


    public boolean isPasswordCorrect(String password) {
        return this.password.equals(SHA256Cryptographic(password));
    }

    public boolean isRecoveryAnswerCorrect(String recoveryAnswer) {
        return this.recoveryAnswer.equals(SHA256Cryptographic(recoveryAnswer));
    }


    private String SHA256Cryptographic(String input) {
        return input;
//        MessageDigest digest;
//        final String algorithmName = "SHA-256";
//        try {
//            digest = MessageDigest.getInstance(algorithmName);
//            byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
//            return new String(encodedhash, StandardCharsets.UTF_8);
//        } catch (NoSuchAlgorithmException e) {
//            System.err.println("There is no algorithm with name: " + algorithmName);
//        }
//        return null;
    }

    public void setAvatar(File avatar) {
        this.avatarPath = avatar.getPath();
    }

    public void setAvatar(ImageView avatar) {
    }

    public File getAvatar() {
        return new File(avatarPath);
    }


    public ArrayList<User> getFriends() {
        return friends;
    }

    public ArrayList<User> getRequests() {
        return requests;
    }

    public void addFriend(User user){
        friends.add(user);
    }

    public void addRequest(User user){
        requests.add(user);
    }

    public void acceptRequest(User user){
        requests.remove(user);
        friends.add(user);
    }

    public void declineRequest(User user){
        requests.remove(user);
    }

    public ArrayList<Chat> searchChats(String name){
        ArrayList<Chat> properChats = new ArrayList<>();
        for (Chat chat : chats){
            if (chat instanceof PublicChat && "public chat".startsWith(name)){
                properChats.add(chat);
            }
            if (chat instanceof PrivateChat){
                for (User user : chat.getMembers()){
                    if (user.getUsername().startsWith(name))
                        properChats.add(chat);
                }
            }
            if (chat instanceof Room room){
                if (room.getName().startsWith(name))
                    properChats.add(chat);
            }
        }
        return properChats;
    }

    public ArrayList<Room> getRooms(){
        ArrayList<Room> rooms = new ArrayList<>();
        for (Chat chat : chats){
            if (chat instanceof Room){
                rooms.add((Room) chat);
            }
        }
        return rooms;
    }
}
