import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.RedisPubSubListener;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

void main() throws InterruptedException {
    final CountDownLatch latch = new CountDownLatch(10);

    RedisClient client = RedisClient.create("redis://localhost");
    StatefulRedisPubSubConnection<String, String> connection = client.connectPubSub();

    RedisPubSubListener<String, String> listener = new RedisPubSubAdapter<>() {

        @Override
        public void message(String channel, String message) {
            System.out.printf("Channel: %s, Message: %s%n", channel, message);
            latch.countDown();
        }
    };

    connection.addListener(listener);
    RedisPubSubCommands<String, String> sync = connection.sync();
    sync.subscribe("events");

    latch.await();
}