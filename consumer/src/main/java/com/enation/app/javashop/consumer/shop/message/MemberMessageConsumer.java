package com.enation.app.javashop.consumer.shop.message;

import com.enation.app.javashop.consumer.core.event.MemberMessageEvent;
import com.enation.app.javashop.client.member.MemberClient;
import com.enation.app.javashop.client.member.MemberNoticeLogClient;
import com.enation.app.javashop.client.system.MessageClient;
import com.enation.app.javashop.model.system.dos.Message;
import com.enation.app.javashop.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员站内消息consumer
 *
 * @author fk
 * @version v6.4
 * @since v6.4
 * 2017年10月14日 上午11:27:15
 */
@Component
public class MemberMessageConsumer implements MemberMessageEvent {

    @Autowired
    private MessageClient messageClient;

    @Autowired
    private MemberNoticeLogClient memberNoticeLogClient;

    @Autowired
    private MemberClient memberClient;


    @Override
    public void memberMessage(Long messageId) {
        Message message = messageClient.get(messageId);
        if (message != null) {
            //发送类型    0  全站   1   部分
            Integer sendType = message.getSendType();
            List<String> memberIdsRes = new ArrayList<String>();
            String memberIds;
            if (sendType != null && sendType.equals(0)) {
                memberIdsRes = memberClient.queryAllMemberIds();
            } else {
                memberIds = message.getMemberIds();
//                    获取所有会员ID的字符串
                String[] memberIdsArray = memberIds.split(",");
//                    添加到List里
                for (String s : memberIdsArray) {
                    memberIdsRes.add(s);
                }
            }
            if (memberIdsRes.size() > 0) {
                //                非空验证
                String msgContent = message.getContent();
                for (String id : memberIdsRes) {
                    memberNoticeLogClient.add(msgContent, message.getSendTime(), StringUtil.toLong(id, false), message.getTitle());
                }
            }
        }
    }
}
