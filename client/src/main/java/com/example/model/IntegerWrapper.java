package com.example.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class IntegerWrapper {
    @XmlElement
    private int value;

    public IntegerWrapper(int value) {
        this.value = value;
    }

    public IntegerWrapper() {
    }

    public int getValue() {
        return value;
    }
}
