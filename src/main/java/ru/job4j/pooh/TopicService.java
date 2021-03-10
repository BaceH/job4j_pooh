package ru.job4j.pooh;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TopicService implements Service {
    private final Map<String, ConcurrentLinkedQueue<String>> topic = new ConcurrentHashMap<>();
    private final Map<Integer,Map<String, ConcurrentLinkedQueue<String>>> consumerTopic = new ConcurrentHashMap<>();

    private boolean put(String name, String text) {

        if (!topic.containsKey(name)) {
            topic.put(name, new ConcurrentLinkedQueue<>());
            if (!consumerTopic.isEmpty()){
                for (Map.Entry<Integer,Map<String, ConcurrentLinkedQueue<String>>> entry : consumerTopic.entrySet()) {
                    entry.getValue().put(name, new ConcurrentLinkedQueue<>());
                }
            }
        }
        if (!consumerTopic.isEmpty()){
            for (Map.Entry<Integer,Map<String, ConcurrentLinkedQueue<String>>> entry : consumerTopic.entrySet()) {
                entry.getValue().get(name).offer(text);
            }
        }
        return topic.get(name).offer(text);
    }

    private String take(Req req) {
        if (!consumerTopic.containsKey(req.getIdConsumer())){
            Map<String, ConcurrentLinkedQueue<String>> topicId = new ConcurrentHashMap<>();

            for (Map.Entry<String, ConcurrentLinkedQueue<String>> entry : topic.entrySet()) {
                topicId.put(entry.getKey(),  new ConcurrentLinkedQueue<>(entry.getValue()));

            }

            consumerTopic.put(req.getIdConsumer(), topicId);
        }

        return consumerTopic.get(req.getIdConsumer()).get(req.getNameQueue()).poll();
    }

    @Override
    public Resp process(Req req) {
        System.out.println("TopicService process");
        int status = 200;
        String text;
        switch (req.method()) {
            case "GET":
                text = take(req);
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