package hyywk.top.koa.test;

import hyywk.top.koa.test.handler.TestLogoutHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/**
 * 配置如何认证
 * 以下是默认配置，
 * 1. 认证所有请求，
 * 2. 可通过表单登陆，
 * 3. 也可通过http基本认证
 * http
 *         .authorizeRequests()
 *             .anyRequest().authenticated()
 *             .and()
 *         .formLogin()
 *             .and()
 *         .httpBasic();
 *
 * 当配置好WebSecurityConfigurerAdapter时，登陆退出也已经自动配置好了
 * 访问 /logout就会退出，也可以通过HttpServletRequest#logout方法退出
 * 退出时执行的操作：
 *  1. Invalidating the http session
 *  2. 清除所有的RememberMe authentication认证信息
 *  3. 清除 SecurityContextHolder
 *  4. 重定向到 /login?logout (调用HttpServletRequestApi就不会了)
 *
 *  LogoutHandler 退出登陆处理接口
 *  LogoutSuccessHandler 成功退出处理接口，处理重定向或服务器内部转发，几乎和
 *                       LogoutHandler一样，但是可能会引起一场信息
 */
// @Configuration
public class TestWebSecurityConfiguerAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 授权无认证访问
                .antMatchers("/fail.html").permitAll()
                .antMatchers("/admin.html").hasRole("ADMIN") // ADMIN权限访问
                .antMatchers("/user.html").hasRole("USER") // USER权限访问
                .antMatchers("/login/get/user").permitAll()
                .anyRequest().authenticated()
                .and() // 如果启用防csrf攻击，则必须使用POST请求？？？？
                .logout() // 退出登陆设置
                .addLogoutHandler(  new TestLogoutHandler() ) // 退出登陆设置
                .logoutSuccessHandler( new TestLogoutHandler() ) // 登陆退出成功设置
                .deleteCookies( "JSESSIONID" ) // 删除该cookie信息（ ...String 类）
                .and()
                .formLogin()
                // .loginPage("/login.html")  // 指定登陆页
                .loginProcessingUrl("/login") // 指定登陆处接口
                .successForwardUrl("/success.html") // 登陆成功的url
                .failureForwardUrl("/fail.html") // 登陆失败的url
                .permitAll()
                .and();

    }
}
