# AMQP操作文档
### 一、发送消息：（示例：TestReceiver.java）
声明注入
```
 @Autowired 
  private AmqpTemplate amqpTemplate;
```
发送消息
```
this.amqpTemplate.convertAndSend(AmqpExchange.TEST_EXCHANGE,
                AmqpExchange.TEST_EXCHANGE + "_ROUTING",
                putMessage);
```
发送消息，第一个参数为枚举，String类型，代表接收的交换器名字。（为静态常量，位于AmqpExchange）
第二个参数为字符串，指定接收路由，String类型，自行指定一个有意义的字串，全剧唯一 （命名规范：交换机名称后缀_QUEUE，例如：MEMBER_LOGIN_QUEUE） 第三个参数为发送的对象，object类型，接收器处理所需要的参数。无参需要写空字符串

### 二、处理消息（事例：TestReceiver.java）
```
@RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = AmqpExchange.TEST_EXCHANGE + "_QUEUE"),
            exchange = @Exchange(value = AmqpExchange.TEST_EXCHANGE, type = ExchangeTypes.FANOUT)
    ))
    public void receiver(String message) {
        cache.put(cacheKey, message);
    }
```
Queue.value 命名规范：业务
```
RabbitListener(bindings = @QueueBinding( value = @Queue(value = "queue自定义名称，消费者定义，注意唯一"), exchange = @Exchange(value = "交换机名称，静态常量")))
```

### 三、消息路由名称的定义
#### 路由的名字需要定义在以下常量中

+ 路径 com.enation.app.javashop.core.base.AmqpExchange
+ 内容
```
    /**
     * PC首页变化消息
     */
    public static final String PC_INDEX_CHANGE = "PC_INDEX_CHANGE";

    /**
     * 移动端首页变化消息
     */
    public static final String MOBILE_INDEX_CHANGE = "MOBILE_INDEX_CHANGE";

    /**
     * 商品变化消息
     */
    public static final String GOODS_CHANGE = "GOODS_CHANGE";

    /**
     * 商品变化消息附带原因
     */
    public static final String GOODS_CHANGE_REASON = "GOODS_CHANGE_REASON";

    /**
     * 帮助变化消息
     */
    public static final String HELP_CHANGE = "HELP_CHANGE";


    /**
     * 页面生成消息
     */
    public static final String PAGE_CREATE = "PAGE_CREATE";

    /**
     * 索引生成消息
     */
    public static final String INDEX_CREATE = "INDEX_CREATE";

    /**
     * 订单创建消息
     * 没有入库
     */
    public static final String ORDER_CREATE = "ORDER_CREATE";

    /**
     * 入库失败消息
     * 入库失败
     */
    public static final String ORDER_INTODB_ERROR = "ORDER_INTODB_ERROR";

    /**
     * 订单状态变化消息
     * 带入库的
     */
    public static final String ORDER_STATUS_CHANGE = "ORDER_STATUS_CHANGE";

    /**
     * 会员登录消息
     */
    public static final String MEMEBER_LOGIN = "MEMEBER_LOGIN";

    /**
     * 会员注册消息
     */
    public static final String MEMEBER_REGISTER = "MEMEBER_REGISTER";

    /**
     * 店铺变更消息
     */
    public static final String SHOP_CHANGE_REGISTER = "SHOP_CHANGE_REGISTER";

    /**
     * 分类变更消息
     */
    public static final String GOODS_CATEGORY_CHANGE = "GOODS_CATEGORY_CHANGE";

    /**
     * 售后状态改变消息
     */
    public static final String REFUND_STATUS_CHANGE = "REFUND_STATUS_CHANGE";

    /**
     * 发送站内信息
     */
    public static final String MEMBER_MESSAGE = "MEMBER_MESSAGE";

    /**
     * 发送手机短信消息
     */
    public static final String _SEND_MESSAGE = "_SEND_MESSAGE";

    /**
     * 邮件发送消息
     */
    public static final String EMAIL_SEND_MESSAGE = "EMAIL_SEND_MESSAGE";

    /**
     * 商品评论消息
     */
    public static final String GOODS_COMMENT_COMPLETE = "GOODS_COMMENT_COMPLETE";

    /**
     * 网上支付
     */
    public static final String ONLINE_PAY = "ONLINE_PAY";

    /**
     * 完善个人资料
     */
    public static final String MEMBER_INFO_COMPLETE = "MEMBER_INFO_COMPLETE";
```
### 四、延时任务
延时任务的作用是可以指定一个任务在具体时间被触发。

比如促销活动开始时主动触发一些动作，如写入缓存等。

定义延时任务需要用到TimeTrigger接口，用法如下：
```
@Autowired
private TimeTrigger timeTrigger;

public void main(){
       //创建活动即通知mq
        PintuanChangeMsg pintuanChangeMsg = new PintuanChangeMsg();
        pintuanChangeMsg.setPintuanId(id);
        pintuanChangeMsg.setOptionType(1);
    //第一个参数，执行器
    //第二个参数，执行器执行调用的参数
    //第三个参数，执行日期
    //第四个参数，唯一key，可以为空，如果不需要操作更新或者删除延时任务的话

        //新增定时任务
        timeTrigger.add("pintuanTimeTriggerExecute", pintuanChangeMsg,                 activeDO.getStartTime(),"TIME_TRIGGER_PINTUAN_"+id);
        //修改定时任务   
        timeTrigger.edit("pintuanTimeTriggerExecute", pintuanChangeMsg, activeDO.getStartTime(),"TIME_TRIGGER_PINTUAN_"+id);
        //删除
        timeTrigger.del("TIME_TRIGGER_PINTUAN_"+id);

}
```
在上述接口的第一个参数是一个执行器的beanName ,当延迟任务被触发时，这个具体的执行器会被调用，执行器的实现举例如下：
```
/**
 * 执行器
 *
 * @author Chopper
 * @version v1.0
 * @since v7.0
 * 2019-02-13 下午5:34
 */
@Component
public class PintuanTimeTriggerExecuter implements TimeTriggerExecuter {

    /**
     * 执行任务
     *
     * @param object 任务参数
     */
    @Override
    public void execute(Object object) {
        //这里的object就是在定义延迟任务时传递过来的msg
    }
}
```