package com.enation.app.javashop.consumer.core.event;

import java.util.List;

/**
 * 帮助变化
 * @author fk
 * @version v2.0
 * @since v7.0.0
 * 2018年3月23日 上午10:25:08
 */
public interface HelpChangeEvent {

	/**
	 * 帮助变化
	 * @param articeids
	 */
    void helpChange(List<Long> articeids);
}
