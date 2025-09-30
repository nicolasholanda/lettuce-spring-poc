package com.github.nicolasholanda.lettuce_spring_poc;

import io.lettuce.core.Consumer;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

public class AsyncApiExample {

    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost");
        RedisAsyncCommands<String, String> commands = client.connect().async();

        RedisFuture<String> future = commands.get("key");

        future.thenAccept((value) -> System.out.println(value));
    }
}
