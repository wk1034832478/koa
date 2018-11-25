package hyywk.top.koa.converter;

import hyywk.top.koa.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 日期转化器
 */
@Component
public class DateConverter implements Converter<String,Date> {
    @Autowired
    private DateUtil dateUtil;
    @Override
    public Date convert(String s) {
        try {
            return this.dateUtil.tryParseDate( s );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
