package com.lightbend.akka.mydb;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.TestActorRef;
import akka.testkit.javadsl.TestKit;
import com.lightbend.akka.mydb.entity.SetRequest;
import com.lightbend.akka.mydb.receiver.AkkaDb;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AkkaDbTest {
    static ActorSystem system;

    @BeforeClass
    public static void setup() {
        system = ActorSystem.create();
    }

    @AfterClass
    public static void teardown() {
        TestKit.shutdownActorSystem(system);
        system = null;
    }

    @Test
    public void akkaDbTest(){
        TestActorRef<AkkaDb> actorRef = TestActorRef.create(system,Props.create(AkkaDb.class));
        actorRef.tell(new SetRequest("key","value"),ActorRef.noSender());
        AkkaDb db = actorRef.underlyingActor();
        assertEquals("value", db.map.get("key"));
    }
}
