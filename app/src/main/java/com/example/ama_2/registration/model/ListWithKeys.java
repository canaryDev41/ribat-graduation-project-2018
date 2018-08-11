package com.example.ama_2.registration.model;

public class ListWithKeys {

    public String string;
    public Object key;

    public ListWithKeys(String stringPart, Object keyPart) {
        string = stringPart;
        key = keyPart;
    }

    @Override
    public String toString() {
        return string;
    }

}
