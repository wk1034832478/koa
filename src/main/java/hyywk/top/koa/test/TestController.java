package hyywk.top.koa.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

// @RestController
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);
    // @Autowired
    private HttpServletRequest request;
    // @GetMapping("/remote/get/user")
    public Object getRemoteUser() {
        return request.getRemoteUser();
    }
    // @GetMapping("/principal/get/user")
    public Object getUserPrincipal() {
        return request.getUserPrincipal().toString();
    }
    // @GetMapping("/logout/get/user")
    public Object logout() {
        this.logger.info( "用户退出");
        try {
            request.logout();
        } catch ( Exception e ) {
            this.logger.info( "退出失败！");
        }
        return "退出成功";
    }

    // @GetMapping("/login/get/user")
    public Object login(@RequestParam("username") String username,@RequestParam("password") String password ) {
        this.logger.info( "用户登陆");
        try {
            request.login(username, password); // Cannot perform login for 'user' already authenticated as 'user'
            return "登陆验证成功";
        } catch ( Exception e ) {
            this.logger.info( "登陆失败！{} ", e.getMessage());
            return "登陆验证失败";
        }
    }
}
