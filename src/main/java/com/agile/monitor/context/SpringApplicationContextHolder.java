package com.agile.monitor.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取Spring Bean的工具类
 * 
 * @author lihaitao
 * @since 2019-04-22
 */
@Component
public class SpringApplicationContextHolder implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringApplicationContextHolder.context = context;
	}

	public static <T> T getBean(Class<T> requiredType) {
		return context == null ? null : context.getBean(requiredType);
	}

	public static String[] getBeanDefinitionNames() {
        return context.getBeanDefinitionNames();
    }
	
}
