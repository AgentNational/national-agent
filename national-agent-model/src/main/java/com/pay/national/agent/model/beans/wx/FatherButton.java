package com.pay.national.agent.model.beans.wx;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class FatherButton extends BaseButton {
    private String button;//可能直接一个父按钮做响应
    private List<SonButton> sonButtons;//可能有多个子按钮

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    @JSONField(name="sub_button")
    public List<SonButton> getSonButtons() {
        return sonButtons;
    }

    public void setSonButtons(List<SonButton> sonButtons) {
        this.sonButtons = sonButtons;
    }

    @Override
    public String toString() {
        return "FatherButton{" +
                "button='" + button + '\'' +
                ", sonButtons=" + sonButtons +
                '}';
    }
}
