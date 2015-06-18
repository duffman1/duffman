package com.nbcuni.test.publisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by kiryl_zayets on 6/17/15.
 */

@Component
public class SiteMap {

    @Value("${app.url}")
    String baseUrl;
    @Value("${app.modules}")
    String modules;
    @Value("${app.config}")
    String config;

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getModulesUrl() {
        return modules;
    }

    public String getConfigUrl() {
        return config;
    }
};



