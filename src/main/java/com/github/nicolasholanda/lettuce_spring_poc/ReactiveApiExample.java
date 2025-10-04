package com.github.nicolasholanda.lettuce_spring_poc;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import reactor.core.publisher.Flux;

public class ReactiveApiExample {

    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost");
        RedisReactiveCommands<String, String> reactive = client.connect().reactive();

        try {
            Flux.range(1, 5)
                    .flatMap(i -> reactive.incr("counter"))
                    .doOnNext(v -> System.out.println("Counter value: " + v))
                    .blockLast(); // Wait for all operations to complete
        } finally {
            client.shutdown();
        }
    }
}
