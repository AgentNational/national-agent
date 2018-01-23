package com.pay.national.agent.model.beans.wx;

public class SonButton extends BaseButton {
    private String sub_button;

    public String getSub_button() {
        return sub_button;
    }

    public void setSub_button(String sub_button) {
        this.sub_button = sub_button;
    }

    @Override
    public String toString() {
        return "SonButton{" +
                "sub_button='" + sub_button + '\'' +
                '}';
    }
}
