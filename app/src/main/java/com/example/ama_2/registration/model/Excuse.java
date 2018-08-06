package com.example.ama_2.registration.model;

public class Excuse {

    private String from, to, type, attach, status;

    public Excuse(String from, String to, String type, String attach, String status) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.attach = attach;
        this.status = status;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Excuse Class[ type = " + type + ", from = " + from + ", to = " + to + ", attach" + attach + ", status = " + status;
    }
}
