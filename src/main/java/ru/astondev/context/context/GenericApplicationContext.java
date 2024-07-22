package ru.astondev.context.context;

import ru.astondev.context.interfaces.ApplicationListener;
import ru.astondev.context.interfaces.BeanFactoryPostProcessor;
import ru.astondev.context.interfaces.ConfigurableEnvironment;
import ru.astondev.context.interfaces.MessageSourceResolvable;
import ru.astondev.context.interfaces.ObjectProvider;
import ru.astondev.context.interfaces.ProtocolResolver;
import ru.astondev.context.classes.ResolvableType;
import ru.astondev.context.exceptions.BeansException;
import ru.astondev.context.exceptions.NoSuchBeanDefinitionException;
import ru.astondev.context.exceptions.NoSuchMessageException;
import ru.astondev.context.factories.AutowireCapableBeanFactory;
import ru.astondev.context.factories.BeanFactory;
import ru.astondev.context.factories.ConfigurableListableBeanFactory;

import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class GenericApplicationContext extends AbstractApplicationContext {
    @Override
    public String getId() {
        return null;
    }

    @Override
    public String getApplicationName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public long getStartupDate() {
        return 0;
    }

    @Override
    public ApplicationContext getParent() {
        return null;
    }

    @Override
    public AutowireCapableBeanFactory getAutowireCapableBeanFactory() throws IllegalStateException {
        return null;
    }

    @Override
    public void publishEvent(Object event) {

    }

    @Override
    public Object getBean(String name) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return null;
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return null;
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        return null;
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType) {
        return null;
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return false;
    }

    @Override
    public boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isPrototype(String name) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isTypeMatch(String name, ResolvableType typeToMatch) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public boolean isTypeMatch(String name, Class<?> typeToMatch) throws NoSuchBeanDefinitionException {
        return false;
    }

    @Override
    public Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public Class<?> getType(String name, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public String[] getAliases(String name) {
        return new String[0];
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public void setEnvironment(ConfigurableEnvironment environment) {

    }

    @Override
    public void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor) {

    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {

    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {

    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {

    }

    @Override
    public void addProtocolResolver(ProtocolResolver resolver) {

    }

    @Override
    public void refresh() throws BeansException, IllegalStateException {

    }

    @Override
    public void registerShutdownHook() {

    }

    @Override
    public void close() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return null;
    }

    @Override
    public BeanFactory getParentBeanFactory() {
        return null;
    }

    @Override
    public boolean containsLocalBean(String name) {
        return false;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return false;
    }

    @Override
    public int getBeanDefinitionCount() {
        return 0;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(Class<T> requiredType, boolean allowEagerInit) {
        return null;
    }

    @Override
    public <T> ObjectProvider<T> getBeanProvider(ResolvableType requiredType, boolean allowEagerInit) {
        return null;
    }

    @Override
    public String[] getBeanNamesForType(ResolvableType type) {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(ResolvableType type, boolean includeNonSingletons, boolean allowEagerInit) {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        return new String[0];
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type, boolean includeNonSingletons, boolean allowEagerInit) {
        return new String[0];
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return null;
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type, boolean includeNonSingletons, boolean allowEagerInit) throws BeansException {
        return null;
    }

    @Override
    public String[] getBeanNamesForAnnotation(Class<? extends Annotation> annotationType) {
        return new String[0];
    }

    @Override
    public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) throws BeansException {
        return null;
    }

    @Override
    public <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public <A extends Annotation> A findAnnotationOnBean(String beanName, Class<A> annotationType, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public <A extends Annotation> Set<A> findAllAnnotationsOnBean(String beanName, Class<A> annotationType, boolean allowFactoryBeanInit) throws NoSuchBeanDefinitionException {
        return null;
    }

    @Override
    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        return null;
    }

    @Override
    public String getMessage(String code, Object[] args, Locale locale) throws NoSuchMessageException {
        return null;
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable, Locale locale) throws NoSuchMessageException {
        return null;
    }
}
