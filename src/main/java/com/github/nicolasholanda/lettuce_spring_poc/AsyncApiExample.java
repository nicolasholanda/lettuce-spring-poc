package com.github.nicolasholanda.lettuce_spring_poc;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.concurrent.ExecutionException;

public class AsyncApiExample {

    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost");
        RedisAsyncCommands<String, String> commands = client.connect().async();

        try {
            // Set key and wait for completion
            RedisFuture<String> setFuture = commands.set("key", "value");
            setFuture.thenRun(() -> System.out.println("Key set successfully"));

            // Wait for set operation to complete
            setFuture.get();

            // Get key
            RedisFuture<String> future = commands.get("key");
            future.thenAccept(value -> System.out.println("Value retrieved: " + value));

            // Wait for get operation to complete
            String result = future.get();
            System.out.println("Final result: " + result);

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error executing Redis commands: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cleanup
            client.shutdown();
        }
    }
}
