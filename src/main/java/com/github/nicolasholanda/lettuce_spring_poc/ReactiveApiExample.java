package com.github.nicolasholanda.lettuce_spring_poc;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.reactive.RedisReactiveCommands;

public class ReactiveApiExample {

    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost");
        RedisReactiveCommands<String, String> commands = client.connect().reactive();

        commands.get("counter")
                .map(Integer::parseInt)
                .flatMap(count -> commands.set("counter", String.valueOf(count + 1)))
                .subscribe();
    }
}
