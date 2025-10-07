import io.lettuce.core.RedisClient;
import io.lettuce.core.api.async.RedisAsyncCommands;

void main() {
    RedisClient client = RedisClient.create("redis://localhost");
    RedisAsyncCommands<String, String> async = client.connect().async();

    new Thread(() -> {
        int i = 1;
        while (true) {
            async.publish("events", "Event #" + i++);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }).start();
}