package hyywk.top.koa.test;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 默认的配置提供的功能：
 * There really isn’t much to this configuration, but it does a lot. You can find a summary of the features below:
 *
 * Require authentication to every URL in your application
 * Generate a login form for you
 * Allow the user with the Username user and the Password password to authenticate with form based authentication
 * Allow the user to logout
 * CSRF attack prevention
 * Session Fixation protection
 * Security Header integration
 *
 * HTTP Strict Transport Security for secure requests
 * X-Content-Type-Options integration
 * Cache Control (can be overridden later by your application to allow caching of your static resources)
 * X-XSS-Protection integration
 * X-Frame-Options integration to help prevent Clickjacking
 * Integrate with the following Servlet API methods
 *
 * HttpServletRequest#getRemoteUser()
 * HttpServletRequest.html#getUserPrincipal()
 * HttpServletRequest.html#isUserInRole(java.lang.String)
 * HttpServletRequest.html#login(java.lang.String, java.lang.String)
 * HttpServletRequest.html#logout()
 */
// @EnableWebSecurity // 激活当前应用的安全保护
public class WebSecurityConfig implements WebMvcConfigurer {
    // @Bean
    public UserDetailsService userDetailsService() throws Exception {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("ADMIN").build());
//        manager.createUser(User.withDefaultPasswordEncoder().username("root").password("root").roles("USER", "STU").build());
        return new TestUserDetailService();
    }

    // @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
