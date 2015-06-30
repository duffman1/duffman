package com.nbcuni.test.publisher.common.Driver.bpp;

import com.nbcuni.test.publisher.pageobjects.UserLogin;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by kiryl_zayets on 6/29/15.
 */

@Configuration
public class PageComponent {

    @Autowired
    SeleniumContext context;

    @Bean
    @Scope(value = "prototype")
    public UserLogin userLogin(){
        return new UserLogin(context.webDriver());
    }

//    @Bean
//    public UserLoginManager userLoginManager(){
//        return new UserLoginManager(){
//            @Override
//            public UserLogin createPage() {
//                return userLogin();
//            }
//        };
//    }
}
