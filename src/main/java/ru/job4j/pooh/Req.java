package ru.job4j.pooh;

import java.util.Arrays;
import java.util.List;

public class Req {
    private final String text;
    private final String reqMode;
    private final String reqMethod;
    private final String nameQueue;
    private final String message;
    private final int idConsumer;

    private final String param;


    public Req(String text) {

        List<String> reqLines = splitReq(text, "\n");
        this.param = reqLines.get(0);

        List<String> paramList = splitReq(splitReq(param, " ").get(1), "/");

        this.reqMode = paramList.get(1);
        this.reqMethod = splitReq(param, " ").get(0);
        this.nameQueue = paramList.get(2);
        this.message =  reqLines.get(reqLines.size() - 1);
        this.text = text;

        if (paramList.size() >= 4) {
            idConsumer = Integer.parseInt(paramList.get(3));
        } else {
            idConsumer = -1;
        }
    }

    public String valueOf(String key) {
        return null;
    }

    public String mode() {
        return reqMode;
    }

    public String method() {
        return reqMethod;
    }

    public String getText() {
        return text;
    }

    public String getParam() {
        return param;
    }

    public String getNameQueue() {
        return nameQueue;
    }

    public String getMessage() {
        return message;
    }
    public int getIdConsumer() {
        return idConsumer;
    }
    private List<String> splitReq(String str, String regex){
        return Arrays.asList(str.split(regex));
    }
}