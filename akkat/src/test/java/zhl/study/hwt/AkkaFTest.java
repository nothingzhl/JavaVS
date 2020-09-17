package zhl.study.hwt;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Akka的第一次使用
 */
public class AkkaFTest {

    @Test
    void testActor() throws IOException {
        ActorSystem system = ActorSystem.create("testSystem");

        ActorRef firstRef = system.actorOf(PrintMyActorRefActor.props(), "first-actor");
        System.out.println("First: " + firstRef);
        firstRef.tell("printit", ActorRef.noSender());

        System.out.println(">>> Press ENTER to exit <<<");
        try {
            System.in.read();
        } finally {
            system.terminate();
        }
    }

    static class PrintMyActorRefActor extends AbstractActor {
        public static Props props() {
            return Props.create(PrintMyActorRefActor.class, PrintMyActorRefActor::new);
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .matchEquals("printit", p -> {
                        ActorRef secondRef = getContext().actorOf(Props.empty(), "second-actor");
                        System.out.println("Second: " + secondRef);
                    })
                    .build();
        }

    }

    @Test
    void testActors() {
        ActorSystem system = ActorSystem.create("testSystem");
        ActorRef first = system.actorOf(StartStopActor1.props(), "first");
        first.tell("stop", ActorRef.noSender());
    }

    static class StartStopActor1 extends AbstractActor {
        static Props props() {
            return Props.create(StartStopActor1.class, StartStopActor1::new);
        }

        @Override
        public void preStart() {
            System.out.println("first started");
            getContext().actorOf(StartStopActor2.props(), "second");
        }

        @Override
        public void postStop() {
            System.out.println("first stopped");
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .matchEquals("stop", s -> {
                        getContext().stop(getSelf());
                    })
                    .build();
        }
    }

    static class StartStopActor2 extends AbstractActor {

        static Props props() {
            return Props.create(StartStopActor2.class, StartStopActor2::new);
        }

        @Override
        public void preStart() {
            System.out.println("second started");
        }

        @Override
        public void postStop() {
            System.out.println("second stopped");
        }

        // Actor.emptyBehavior is a useful placeholder when we don't
        // want to handle any messages in the actor.
        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .build();
        }
    }

    @Test
    void testFail() {
        ActorSystem system = ActorSystem.create("testSystem");
        ActorRef supervisingActor = system.actorOf(SupervisingActor.props(), "supervising-actor");
        supervisingActor.tell("failChild", ActorRef.noSender());
    }

    static class SupervisingActor extends AbstractActor {
        static Props props() {
            return Props.create(SupervisingActor.class, SupervisingActor::new);
        }

        ActorRef child = getContext().actorOf(SupervisedActor.props(), "supervised-actor");

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .matchEquals("failChild", f -> {
                        child.tell("fail", getSelf());
                    })
                    .build();
        }
    }

    static class SupervisedActor extends AbstractActor {
        static Props props() {
            return Props.create(SupervisedActor.class, SupervisedActor::new);
        }

        @Override
        public void preStart() {
            System.out.println("supervised actor started");
        }

        @Override
        public void postStop() {
            System.out.println("supervised actor stopped");
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .matchEquals("fail", f -> {
                        System.out.println("supervised actor fails now");
                        throw new Exception("I failed!");
                    })
                    .build();
        }
    }
}
