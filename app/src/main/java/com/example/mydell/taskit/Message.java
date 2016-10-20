package com.example.mydell.taskit;


public class  Message {
    private String message;
    private String sender;

    public Message() {
      /*Blank default constructor essential for Firebase*/
    }
    //Getters and setters
    public String getmessage(){return message;}
    public String getsender(){return sender;}


    public void setMessage(String message){this.message= message;}
    public void setSender(String sender) {
        this.sender = sender;
    }
}
