package com.example.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BooleanWrapper {
    @XmlElement
    private boolean value;
    public BooleanWrapper () {
    }

    public BooleanWrapper (boolean value) {
        this.value = value;
    }

    public boolean isValue() {
        return value;
    }
}
