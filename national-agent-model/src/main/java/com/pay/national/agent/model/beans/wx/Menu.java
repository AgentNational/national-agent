package com.pay.national.agent.model.beans.wx;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class Menu {
    private List<FatherButton> fatherButtons;

    @JSONField(name="button")
    public List<FatherButton> getFatherButtons() {
        return fatherButtons;
    }

    public void setFatherButtons(List<FatherButton> fatherButtons) {
        this.fatherButtons = fatherButtons;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "fatherButtons=" + fatherButtons +
                '}';
    }
}
