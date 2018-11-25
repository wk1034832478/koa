package hyywk.top.koa.config;

import hyywk.top.koa.auth.KoaLogoutHandler;
import hyywk.top.koa.test.handler.TestLogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    private final String[] EXCLUDE_PATTERN = {
            "/api/user/login",
            "/api/user/get",
            "/api/user/logout",
            "/api/account/save",
            "/api/user/capture",
            "/api/user/validate_capture",
            "/resources/**",
            "/resources/static/**",
            "/static/**",
    };
    /**
     * 配置如何认证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(this.EXCLUDE_PATTERN).permitAll()
                .antMatchers("/api/**").authenticated()
                .and() // 如果启用防csrf攻击，则必须使用POST请求？？？？
                .logout() // 退出登陆设置
                .addLogoutHandler(  new KoaLogoutHandler() ) // 退出登陆设置
                .logoutSuccessHandler( new KoaLogoutHandler() ) // 登陆退出成功设置
                .deleteCookies( "JSESSIONID" ) // 删除该cookie信息（ ...String 类）
                .and()
                .csrf().disable() // 禁用CSRF
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/login") // 指定登陆处接口
                .permitAll()
                .and();
    }
}
