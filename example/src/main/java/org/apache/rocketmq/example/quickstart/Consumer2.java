package org.apache.rocketmq.example.quickstart;

import java.util.List;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author rtw
 * @since 2019/2/23
 */
public class Consumer2 {
    public static void main(String[] args) throws InterruptedException, MQClientException {
        String[] consumerArr = {"MyConsumer"};
        for (String consumer : consumerArr) {
            final String name = consumer;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Consumer2.startAConsumer(name);
                }
            };
            thread.start();

        }
        Thread.sleep(1000);
        String[] consumerArr2 = {"MyConsumer2"};
        for (String consumer : consumerArr2) {
            final String name = consumer;
            Thread thread = new Thread() {
                @Override
                public void run() {
                    Consumer2.startAConsumer2(name);
                }
            };
            thread.start();
        }
    }

    private static void startAConsumer(String consumerGroup){
        try {
            /*
             * Instantiate with specified consumer group name.
             */
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

            /*
             * Specify name server addresses.
             * <p/>
             *
             * Alternatively, you may specify name server addresses via exporting environmental variable: NAMESRV_ADDR
             * <pre>
             * {@code
             * consumer.setNamesrvAddr("name-server1-ip:9876;name-server2-ip:9876");
             * }
             * </pre>
             */
            consumer.setNamesrvAddr("127.0.0.1:9876");

            consumer.setMessageModel(MessageModel.CLUSTERING);
            /*
             * Specify where to start in case the specified consumer group is a brand new one.
             */
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            /*
             * Subscribe one more more topics to consume.  *标识所有的Tag
             */
            consumer.subscribe("MyTopic", "*");

            /*
             *  Register callback to execute on arrival of messages fetched from brokers.
             */
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            /*
             *  Launch the consumer instance.
             */
            consumer.start();

            System.out.printf(consumerGroup + "Consumer Started.%n");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    private static void startAConsumer2(String consumerGroup){
        try {
            /*
             * Instantiate with specified consumer group name.
             */
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

            /*
             * Specify name server addresses.
             * <p/>
             *
             * Alternatively, you may specify name server addresses via exporting environmental variable: NAMESRV_ADDR
             * <pre>
             * {@code
             * consumer.setNamesrvAddr("name-server1-ip:9876;name-server2-ip:9876");
             * }
             * </pre>
             */
            consumer.setNamesrvAddr("127.0.0.1:9876");

            consumer.setMessageModel(MessageModel.CLUSTERING);
            /*
             * Specify where to start in case the specified consumer group is a brand new one.
             */
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);

            /*
             * Subscribe one more more topics to consume.  *标识所有的Tag
             */
            consumer.subscribe("MyTopic", "*");

            /*
             *  Register callback to execute on arrival of messages fetched from brokers.
             */
            consumer.registerMessageListener(new MessageListenerConcurrently() {

                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });

            /*
             *  Launch the consumer instance.
             */
            consumer.start();

            System.out.printf(consumerGroup + "Consumer2 Started.%n");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
