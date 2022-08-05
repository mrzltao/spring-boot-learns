package com.learn.text;

/**
 * @Title SummaryText
 * @Description RibbitMQ纪要
 * @Author ZLT
 * @Date 2022/8/5 14:46
 * @Version 1.0
 */
public class SummaryText {
    /**
     * RabbitMQ纪要
     * RabbitMQ是一个消息代理:它接受和转发消息.
     * 核心：
     *  1、简单队列模式、
     *  2、Work Queues
     *      工作队列模式（竞争的消费者模式、任务队列模式）
     *      a.轮询分发消息
     *      b.消息应答
     *          就是消费者在接收到消息并且处理该消息之后，告知RabbitMQ，然后RabbitMQ将该消息删除。
     *          分类：
     *              1）自动应答：
     *                  消息发送后立即被认为已经传送成功；这种模式需要在高吞吐量和数据传输安全性方面做权衡。因为这种模式如果消费者未能成功消费，那么消息就会丢失；
     *                  另一方面这种模式可以传递过载的消息，没有对消息进行限制，那么这样消费者就有可能因为接收太多还来不及处理的消息，导致消息积压，使内存耗尽，最终这些消费者线程会被操作系统杀死；
     *                  所以这种模式仅适用与消费者能够高效并以某种速率能够处理这些消息的情况下使用。
     *              2）手动应答：
     *                  消费者处理完消息之后需要手动告知RabbitMQ消息已经处理。
     *                  Channel.basicAck(用于肯定确认)
     *                      RabbitMQ已知消息被成功消费，可以丢弃
     *                  Channel.basicNack(用于否定确认)
     *                  Channel.basicReject(用于否定确认)
     *                      basicReject与basicNack相比少一个参数(Multiple批量处理)，不处理该消息直接拒绝，可以丢弃
     *              3）Multiple(批量应答)
     *                  手动应答的好处就是可以批量应答来减少网络拥堵。
     *                  Channel.basicAck(deliveryTag, multiple);
     *                  当multiple为true时，代表批量应答Channel上未应答的消息。无论Channel上的其它消息是否被成功消费，所以批量应答模式容易造成消息丢失。不推荐。
     *                  当multiple为false时，代表只应答当前消费的消息。
     *              4）自动重新入队
     *                  消费者由于某些因素失去连接（通道关闭、连接关闭或TCP丢失）导致消息未能成功消费，消费者未发送Ack确认，那么RabbitMQ会将该消息重新放入队列排队，分发给其它可以处理该消息的消费者去消费。所以、即使某一个消费者偶尔异常，也可以确保消息不会丢失。
     *      c.持久化
     *          队列持久化
     *              实现队列持久化需要在声明队列时将durable设置为true
     *              channel.queueDeclare(RabbitConnectionUtils.QUEUE_NAME, true, false ,false,null);
     *              注：若设置要持久化的队列已存在且为非持久化队列，则需要删除该队列后重新创建。
     *          消息持久化
     *  3、订阅/分发模式
     *  4、路由模式
     *  5、主题模式
     *  6、请求/应答模式
     *
     *
     */
}
