package team.fuyuki23.mate.common.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

  @Bean
  public FilterRegistrationBean<AccessLoggingFilter> accessLoggingFilter() {
    FilterRegistrationBean<AccessLoggingFilter> registrationBean = new FilterRegistrationBean<>();
    registrationBean.setFilter(new AccessLoggingFilter());
    registrationBean.addUrlPatterns("/*");
    return registrationBean;
  }

}
