package com.example.demo.viewmodel.admin.message;


import com.example.demo.base.BasePage;

public class MessagePageRequestVM extends BasePage {
    private String sendUserName;

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }
}
