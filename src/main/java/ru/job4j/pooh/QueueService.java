package ru.job4j.pooh;

public class QueueService implements Service {
    @Override
    public Resp process(Req req) {
        System.out.println("start Req\n---------------------------");

        System.out.println(req.mode());
        System.out.println(req.method());

        System.out.println("------------------------------------\nend Req");
        return null;
    }
}