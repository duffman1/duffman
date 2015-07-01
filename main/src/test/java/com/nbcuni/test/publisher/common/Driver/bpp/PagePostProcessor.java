package com.nbcuni.test.publisher.common.Driver.bpp;

import com.nbcuni.test.publisher.common.Driver.component.annotations.Page;
import com.nbcuni.test.publisher.common.Driver.configuration.SeleniumContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

/**
 * Created by kiryl_zayets on 6/30/15.
 */
public class PagePostProcessor implements BeanPostProcessor {

    @Autowired
    ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        Page page = o.getClass().getSuperclass().getAnnotation(Page.class);
        if (page != null) {
            Object beanDefinition = configurableListableBeanFactory.getBean("seleniumContext");
            WebDriver webDriver = ((SeleniumContext)beanDefinition).webDriver();
            PageFactory.initElements(new HtmlElementDecorator(webDriver), o);
        }

        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        return o;
    }
}
