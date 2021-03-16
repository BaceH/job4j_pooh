package ru.job4j.pooh;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final Map<String, ConcurrentLinkedQueue<String>> queue = new ConcurrentHashMap<>();

    private boolean put(String name, String text) {
        queue.putIfAbsent(name, new ConcurrentLinkedQueue<>());
        return queue.get(name).offer(text);
    }

    private String take(String name) {
        String result = null;
        if (queue.containsKey(name)) {
            result = queue.get(name).poll();
        }
        return result;
    }

    @Override
    public Resp process(Req req) {
        System.out.println("QueueService process");
        int status = 200;
        String text;
        switch (req.method()) {
            case "GET":
                text = take(req.getNameQueue());
                if (text == null) {
                    status = 404;
                    text = "\n\n queue is empty";
                } else {
                    text = "\n\n" + text;
                }
                break;
            case "POST":
                if (!put(req.getNameQueue(), req.getMessage()))
                    status = 404;
                text = req.getText();
                break;
            default:
                status = 404;
                text = "null";

        }

        return new Resp(text, status);
    }
}