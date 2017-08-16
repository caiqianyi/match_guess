package com.ct;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoRestTemplateCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.util.WebUtils;


@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableOAuth2Sso
public class RibbonApplication extends SpringBootServletInitializer{
	
	 /**
     * 修改DispatcherServlet默认配置
     *
     * @param dispatcherServlet
     * @return
     * @author cqy
     * @create  2017年7月26日
     */
    @Bean
    public ServletRegistrationBean dispatcherRegistration(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet);
        registration.getUrlMappings().clear();
        registration.addUrlMappings("*.json");
        return registration;
    }
    
    @Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }
	 
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
	
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
        	.authorizeRequests()
        	//Allow access to all static resources without authentication
        	.antMatchers("/","/**/*.html").permitAll()
        	.anyRequest().authenticated()
        	.and()
				.csrf().csrfTokenRepository(csrfTokenRepository())
			.and()
				.addFilterAfter(csrfHeaderFilter(), SessionManagementFilter.class);
		}

		private Filter csrfHeaderFilter() {
			return new OncePerRequestFilter() {
				@Override
				protected void doFilterInternal(HttpServletRequest request,
						HttpServletResponse response, FilterChain filterChain)
						throws ServletException, IOException {
					CsrfToken csrf = (CsrfToken) request.getAttribute(CsrfToken.class
							.getName());
					if (csrf != null) {
						Cookie cookie = WebUtils.getCookie(request, "XSRF-TOKEN");
						String token = csrf.getToken();
						System.out.println(token);
						if (cookie == null || token != null && !token.equals(cookie.getValue())) {
							cookie = new Cookie("XSRF-TOKEN", token);
							cookie.setPath("/");
							response.addCookie(cookie);
						}
					}
					filterChain.doFilter(request, response);
				}
			};
		}

		private CsrfTokenRepository csrfTokenRepository() {
			HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
			repository.setHeaderName("X-XSRF-TOKEN");
			return repository;
		}
	}
}

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class WorkaroundRestTemplateCustomizer implements UserInfoRestTemplateCustomizer {

	@Override
	public void customize(OAuth2RestTemplate template) {
		template.setInterceptors(new ArrayList<>(template.getInterceptors()));
	}

}
 