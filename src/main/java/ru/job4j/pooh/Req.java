package ru.job4j.pooh;

public class Req {
    private final String text;

    public Req(String text) {
        this.text = text;
    }

    public String valueOf(String key) {
        if (text.contains("key")) {
            return "key";
        }
        return null;
    }

    public String mode() {
        if (text.contains("queue"))
            return "queue";
        return "topic";
    }

    public String method() {
        if (valueOf("GET") == null){
            return "POST";
        }
        return "GET";
    }
}