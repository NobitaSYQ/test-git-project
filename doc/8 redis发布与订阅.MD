# redis发布与订阅
redis发布与订阅功能，消息发布后，会在各个服务各个节点订阅成功。

### 发布
```
@Autowired
private StringRedisTemplate redisTemplate;

public send(){
    //第一个参数是渠道名称，第二个参数是发布内容，字符串类型
        redisTemplate.convertAndSend(RedisChannel.SENSITIVE_WORDS, JsonUtil.objectToJson(msg));
}
注意，只能使用StringRedisTemplate发布，且只能发布String类型，发布对象可借助JsonUtil工具。
```

### 订阅
```
@Component
public class SensitiveReceiver implements RedisMsgReceiver {

    @Override
    public String getChannelName() {
        return RedisChannel.SENSITIVE_WORDS;
    }

    @Override
    public void receiveMsg(String message) {
        //订阅消息成功后执行
    }
}
```
实现RedisMsgReceiver接口，使用@Component注解由Spring容器管理，实现接口的方法，注意渠道名称与发布渠道名称相同