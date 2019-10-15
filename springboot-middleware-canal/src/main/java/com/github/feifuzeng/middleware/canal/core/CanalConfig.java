package com.github.feifuzeng.middleware.canal.core;

import com.alibaba.otter.canal.common.utils.AddressUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author feifz
 * @version 1.0.0
 * @Description canal 相关配置
 * @createTime 2019年09月04日 17:09:00
 */
@Data
public class CanalConfig {

    /**
     * 主机地址
     */
    private String hostname = AddressUtils.getHostIp();
    /**
     * 端口号
     */
    private int port = 11111;
    /**
     * 描述
     */
    private String destination = StringUtils.EMPTY;
    /**
     * 用户名
     */
    private String username = StringUtils.EMPTY;
    /**
     * 密码
     */
    private String password = StringUtils.EMPTY;

    /**
     * 客户端过滤器,如果传空,则表示不过滤.
     */
    private String subscribe = StringUtils.EMPTY;
    /**
     * 客户端接收binlog的起始batchid,默认值0
     */
    private long batchid = NumberUtils.LONG_ZERO;
}
