package org.am;

import org.am.mongo.FooDocument;
import org.am.mongo.IFoosCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@SpringBootApplication
public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        logger.debug("my debug log");
        SpringApplication.run(App.class, args);
    }

    @Bean
    public Disposable insertFoo(IFoosCollection foosCollection) {
        return Flux.interval(Duration.ofSeconds(1), Schedulers.newSingle("foo-insert", false))
                .concatMap(x -> foosCollection.insert(
                        new FooDocument().setFooName("num_" + x + "_at_" + System.currentTimeMillis())
                ))
                .doOnNext(doc -> logger.info("insert foo : {}", doc))
                .doOnError(e -> logger.error("error when inserting foos"))
                .doOnComplete(() -> logger.info("done inserting foos"))
                .subscribe();
    }

    @Bean
    public Disposable printAllFoos(IFoosCollection foosCollection) {
        return foosCollection.findAll()
                .doOnComplete(() -> logger.info("all foos : completed"))
                .subscribe(d -> logger.info("next foo : {}", d));
    }
    @Bean
    public Object runLock() {
        return Flux.interval(Duration.ofSeconds(1), Schedulers.newSingle("heartbeat", false))
                .doOnNext(l -> logger.info("app alive for {} seconds", l))
                .subscribe();
    }
}
