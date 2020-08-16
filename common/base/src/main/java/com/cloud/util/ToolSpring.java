package com.cloud.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Spring工具类 方便在非spring管理环境中获取bean
 *
 * @author fjj
 * @date 2020/6/3 22:38
 */
public class ToolSpring implements BeanFactoryPostProcessor {

    /**
     * Spring应用上下文环境
     */
    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        ToolSpring.beanFactory = beanFactory;
    }

    /**
     * 获取对象
     *
     * @param name
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name) {
        return (T) beanFactory.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class clz) {
        return (T) beanFactory.getBean(clz);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     *
     * @param name
     * @return
     */
    public static boolean containBean(String name) {
        return beanFactory.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     *
     * @param name
     * @return
     */
    public static boolean isSingleton(String name) {
        return beanFactory.isSingleton(name);
    }

    /**
     * 获取注册对象的类型
     *
     * @param name
     * @return
     */
    public static Class<?> getType(String name) {
        return beanFactory.getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名，则返回这些别名
     *
     * @param name
     * @return
     */
    public static String[] getAliases(String name) {
        return beanFactory.getAliases(name);
    }


}
