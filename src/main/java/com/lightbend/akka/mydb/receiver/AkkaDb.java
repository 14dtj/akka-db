package com.lightbend.akka.mydb.receiver;

import akka.actor.AbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.lightbend.akka.mydb.entity.SetRequest;

import java.util.HashMap;
import java.util.Map;

public class AkkaDb extends AbstractActor {
    protected final LoggingAdapter log = Logging.getLogger(context().system(), this);
    public final Map<String, Object> map = new HashMap<>();

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(SetRequest.class, message -> {
            log.info("Received Set request: {}", message);
            map.put(message.getKey(), message.getValue());
        }).matchAny(o -> log.info("received unknown message: {}", o)).build();
    }


}
