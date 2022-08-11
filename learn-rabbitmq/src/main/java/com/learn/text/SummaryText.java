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
     *  1、简单队列模式    2、Work Queues   3、订阅/分发模式   4、路由模式  5、主题模式  6、请求/应答模式
     * Work Queues
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
     *              boolean durable = true;
     *              channel.queueDeclare(RabbitConnectionUtils.QUEUE_NAME, durable, false ,false,null);
     *              注：若设置要持久化的队列已存在且为非持久化队列，则需要删除该队列后重新创建。
     *          消息持久化
     *              当队列持久化时，若消息未添加持久化，那么在工作线程执行失败时，消息还是会丢失。
     *              那么实现消息持久化需要在信道发布消息时，将MessageProperties.PERSISTENT_BASIC属性添加上。
     *              channel.basicPublish("", RabbitConnectionUtils.QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, queueMsg.getBytes());
     *      d.不公平分发\
     *          当RabbitMQ采用轮询分发策略分发消息并不适合某种场景时
     *          (例如俩个消费者处理消息的速度有很大差别，轮询分发会造成其中一个消费者在很大一部分时间内处于空闲状态)，
     *          我们可以采用不公平分发，在消费者接收消息(channel.basicConsume)之前设置参数：channel.basicQos(1);
     *          channel.basicQos的参数默认为0，当为0时，为轮询分发。
     *          int prefetchCount = 1;
     *          channel.basicQos(prefetchCount);
     *      e.预取值
     *          当RabbitMQ生产者发送多条消息，消费者直接指定本身消费的消息个数。
     *          预取值设置参数：channel.basicQos(prefetch)
     *          当prefetch为0时，为轮询分发。
     *          当prefetch为1时，为不公平分发。
     *          当prefetch大于1时，为预取值。指定同一时间信道中的消息上限为prefetch条消息。
     *          int prefetchCount = 2;
     *          channel.basicQos(prefetchCount);
     *  发布确认
     *      为达到持久化的目的，第一设置队列必须持久化，第二设置队列中的消息必须持久化，第三是发布确认。只有前俩条是不能包含保证
     *      消息持久化的。因为不能保证生产者发布的消息是否到达了队列中（发布消息时RabbitMQ宕机），所以为保证消息的持久化，必须有
     *      第三条发布确认。
     *      发布确认策略
     *          a.开启发布确认方法
     *              发布确认默认是没有开启的，开启需要调用confirmSelect。
     *              Channel channel = RabbitConnectionUtils.createChannel();
     *              //开启发布确认
     *              channel.confirmSelect();
     *              //发布消息
     *              channel.basicPublish("", RabbitConnectionUtils.QUEUE_NAME, MessageProperties.PERSISTENT_BASIC, queueMsg.getBytes());
     *              //发布确认
     *              boolean flag = channel.waitForConfirms();
     *          b.单个发布确认
     *              单个发布确认是一种同步确认发布的方式，也就是发布一个消息之后只有它被确认发布，后续的消息才会发布。
     *              waitForConfirmOrDie(long)这个方法只有在消息被确认之后才返回，若在指定时间范围内消息没有被确认，那么它将抛出异常。
     *              缺点：发布速度特别的慢；这种方式最多提供每秒不超过数百条发布消息的吞吐量。
     *          c.批量发布确认
     *              相对于单个发布确认，批量发布确认可以极大的提高吞吐量。
     *              缺点：当发送故障导致发布出现问题时，我们必须将这个批量的消息保存到内存中，已记录重要的信息而后重新发布。批量发布确认也是同步的，同样会造成阻塞消息的发布
     *          d.异步发布确认
     *              异步确认相对于单个、批量的编程逻辑复杂，但性价比最高。无论是可靠性和效率都优于单个和批量确认。
     *              异步发布确认是利用回调函数来达到消息的可靠性的。这个中间件同样通过回调函数来保证是否发布成功。
     *              异步发布确认在发布消息前需要用到消息的监听器，来监听消息的成功与否
     *                  单参：只监听成功的消息
     *                  channel.addConfirmListener(ConfirmListener listener);
     *                  多参：成功和失败的消息都监听
     *                  channel.addConfirmListener(ConfirmCallback ackCallback, ConfirmCallback nackCallback1);
     *          e.处理异步未确认消息
     *              将未确认的消息放入一个基于内存的能够被发布线程访问的队列中。
     *              如:并发链入式队列 ConcurrentLinkedQueue 这个队列在confirm callbacks(确认回调)与发布线程之间进行消息传递
     * 订阅/分发模式
     *  交换机（Exchanges）
     *      RabbitMQ传递消息模型的核心思想：生产者生产的消息从不会直接发送给队列。实际上，通常生产者甚至都不知道这些消息传递到那些队列中。
     *      相反，生产者只是将消息发送到交换机（Exchanges）；交换机的工作就是一方面接收生产者发布的消息，另一方面将这些消息推入队列。
     *      交换机必须确切知道如何处理这些消息。是将这些消息放入特定队列或放入许多队列中或丢弃，都由交换机类型决定。
     *      类型：
     *          直接（direct）类型（路由类型）、主题（topic）类型、标题（headers）类型、扇出（fanout）类型（发布/订阅类型）
     *      默认交换机（Exchanges）也称无名称交换机（Exchanges），通常用空字符串（""）进行标识。
     *       channel.basicPublish("", RabbitConnectionUtils.QUEUE_NAME, null, queueMsg.getBytes());
     *      第一个参数就是交换机的名称。空字符串标识默认交换机；消息能路由发送到队列中，其实是由routingKey(bindingKey)指定的。
     *      在没有指定交换机时，其实routingKey就是队列名称。若指定交换机时，第二个参数就不能写队列名称，要为routingKey名称。
     *  临时队列
     *      临时队列就是没有持久化且一旦断开消费者的连接，就会被删除的队列。
     *      创建临时队列方式：String queueName = channel.queueDeclare().getQueue();
     *  绑定（bindings）
     *      bindings其实就是交换机（Exchanges）与队列（Queue）之间的捆绑关系（桥梁）
     *  fanout（扇出）
     *      广播模式，交换机会将消息发送到所有与该交换机绑定的队列中。
     *      当fanout扇出类型交换机与队列进行绑定时将routingKey设置为""，
     *      当生产者和消费者的routingKey都设置为""，将消息发送给交换机，指定交换机的名称和routingKey，消费者都可以消费成功
     *      当生产者和多个消费者设置的routingKey都不相同时，将消息发送给交换机，指定交换机和routingKey，多个消费者还是可以消费成功
     *      由此上可以看出，fanout类型的交换机与routingKey无关，所以fanout类型的交换机转发消息速度最快，是最高效的。
     *  direct（路由）
     *      直接类型也称路由类型的交换机，交换机会将消息发送到与该交换机绑定的指定routingKey的队列中。
     *      多重绑定
     *          当direct exchange绑定多个队列的routingkey如果都相同，在这种情况下虽然绑定的交换机类型是direct，但它的表现就和fanout类型的交换机类似，就和广播差不多。
     *          当绑定多个队列的routingKey不相同，则生产者发送消息时指定的routingKey是那个消费者，则那个
     *          消费者才会消费成功。
     *  topic（主题）
     *      主题交换机相对于扇出（fanout）交换机和路由（direct）交换机而言，更加的灵活。为解决direct交换机的局限性（例如：接收日志类型有info.base和info.adv，其中一个队列
     *      只想接收info.base，这时direct交换机就办不到了）这时只能使用topic交换机。
     *      要求
     *          发送消息到topic交换机的routingKey不可以随意命名，必须满足一定的要求：一个单词列表，中间用点号隔开（例如：abds.wodeld.wesd和layz.stock.usr）
     *          这些单词可以是任意单词，有无词义都可以。本单词列表最大长度是255个字节。
     *          单词列表中的匹配符：、
     *              *（星号）可以匹配一个单词（abds.wodeld.wesd等于*.wodeld.* 而abds.wodeld.wesd.usr就等于*.wodeld.*）
     *              #（井号）可以匹配多个单词（abds.wodeld.wesd等于abds.#）
     *              注：
     *                  当一个队列中的routingKey为#，那么该队列将接收所有的消息，类似fanout。
     *                  当一个队列中的routingKey没有出现#和*，那么该队列绑定类型就是direct。
     *              由此可以看出，topic的强大之处就在于topic交换机包含了fanout和direct。
     *  死信队列
     *      死信就是无法被消费的消息。
     *      一般来说，生产者将消息发送到broker或队列中，消费者从队列中取出消息进行消费，但某些时候因为特定的原因造成队列中的某些消息无法被消费，、
     *      这些无法被消费的消息若没有后续处理，就变成了死信，有私信自然就有了私信队列。
     *      场景：
     *          为保证业务的消息数据不丢失，需要使用到RabbitMQ的死信队列机制，当消息发生异常时，将消息投入到死信队列中。
     *      产生死信来源：
     *          1、消息TTL（存活时间）过期
     *          2、队列达到最大长度（队列满了，无法再添加消息到MQ中）
     *          3、消息被拒绝（消息在手动应答时进行了否定应答basicReject或basicNack）并且requeue=false
     *  延迟队列
     *      其实设置消息的TTL（存活时间）过期也是延迟队列。
     *
     */
}
