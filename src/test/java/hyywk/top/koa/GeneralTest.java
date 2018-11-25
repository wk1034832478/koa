package hyywk.top.koa;

import hyywk.top.koa.util.DateUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Nov 21, 2018 8:00:00 AM
public class GeneralTest {
    private Logger logger = LoggerFactory.getLogger( GeneralTest.class );
    @Test
    public void test1() {
        Pattern pattern = Pattern.compile( "^([a-zA-Z]{3})\\s([0-9]{1,2}),\\s([0-9]{4})\\s{1}([0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2})\\s([a-zA-Z]{2})$");
        Matcher matcher = pattern.matcher("Nov 21, 2018 8:00:00 AM");
        this.logger.info( matcher.matches() + "" );
        for( int i = 1; i <  matcher.groupCount() + 1; i++ ) {
            this.logger.info( matcher.group(i));
        }
    }

    @Test
    public void test2()  {
        Date date = new DateUtil().tryParseDate("Nov 21, 2018 8:00:00 PM");
        this.logger.info( date.toString() );
    }
}
