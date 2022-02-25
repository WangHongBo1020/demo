package com.example.whb_demo.beanconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * \* KDA
 * \* User: wanghongbo
 * \* Date: 2022/2/25
 * \* Time: 11:25
 * \* Description:
 * \
 */
@Configuration
public class PasswordEncoderConfig {

    private static final Logger logger = LoggerFactory.getLogger(PasswordEncoderConfig.class);

    /*@Bean
    public PasswordEncoder passwordEncoder() {

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        if (passwordEncoder instanceof DelegatingPasswordEncoder) {
            ((DelegatingPasswordEncoder)passwordEncoder).setDefaultPasswordEncoderForMatches(NoOpPasswordEncoder.getInstance());
        }

        logger.debug("PasswordEncoderConfig passwordEncoder is {}", JSONObject.toJSONString(passwordEncoder));
        return passwordEncoder;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
