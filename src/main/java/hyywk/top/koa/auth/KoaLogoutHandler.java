package hyywk.top.koa.auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KoaLogoutHandler implements LogoutSuccessHandler, LogoutHandler {
    private Logger logger = LoggerFactory.getLogger(KoaLogoutHandler.class );
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        this.logger.info("退出登陆成功！");
        authentication.setAuthenticated(false);
        httpServletResponse.setContentType("text/plain; charset=utf-8"); // 防止字符编码出现问题
        try {
            httpServletResponse.getWriter().write("退出成功！");
        } catch (Exception e ) {
            this.logger.info("---{}---", e.getMessage());
        }
    }

    @Override
    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
        this.logger.info("用户退出登陆！");
    }
}
