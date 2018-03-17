package cz.microshop.users.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersConfiguration {
    @Bean
    @ConditionalOnMissingBean(UsersConfigurationProperties.class)
    public UsersConfigurationProperties frameworkMesosConfigProperties() {
        return new UsersConfigurationProperties();
    }
}