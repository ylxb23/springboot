package org.zero.boot.web.init.conf.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 连接池配置信息(TODO)
 * @date 2017年11月3日 下午1:57:52
 * @author zero
 */
@Configuration
@ConfigurationProperties(prefix = "datasource.druid")
public class DruidConfig {

}
