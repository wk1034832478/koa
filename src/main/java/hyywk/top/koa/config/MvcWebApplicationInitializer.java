package hyywk.top.koa.config;
import hyywk.top.koa.test.WebSecurityConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
@Configuration
public class MvcWebApplicationInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {
    private Logger logger = LoggerFactory.getLogger( hyywk.top.koa.test.MvcWebApplicationInitializer.class);
    @Override
    protected Class<?>[] getRootConfigClasses() {
        this.logger.info("正在加载安全配置-----");
        return new Class[] { WebSecurityConfig.class };
    }

    // ... other overrides ...

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }
}