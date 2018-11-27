package hyywk.top.koa.util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class DateUtil {
    private Logger logger = LoggerFactory.getLogger( DateUtil.class );
    private Pattern pattern = null;
    private Pattern timestampPattern = null;
    public  DateUtil() {
        this.pattern = Pattern.compile( DateRegex.date1);
        this.timestampPattern = Pattern.compile( DateRegex.TIMESTAMP);
    }
    public Date tryParseDate( String dateString ) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = null;
        try {
            date = sdf.parse( dateString);
            return date;
        } catch( Exception e) {
            this.logger.info("SimpleDateFormat无法解析当前日期:{}",dateString);
        }
        try {
            Matcher matcher = this.pattern.matcher( dateString );
        if ( matcher.matches()) {
                String month = matcher.group(1);// 月份
                int monthIndex = this.getMonthIndex(month);
                int dayOfMonth = Integer.parseInt( matcher.group(2) ) ;
                int year = Integer.parseInt( matcher.group(3) ) ;
                int hour = Integer.parseInt( matcher.group(4) ) ;
                int minute = Integer.parseInt( matcher.group(5) ) ;
                int second = Integer.parseInt( matcher.group(6) ) ;
                String morOrAft = matcher.group(7);
                date =new Date( year, monthIndex, dayOfMonth);
                date.setHours(hour);
                date.setMinutes(minute);
                date.setSeconds(second);
                if ( morOrAft == DateRegex.Afternoon ) {
                    date.setHours( date.getHours() + 12 );
                }
                this.logger.info("正则表达式正确匹配当前日期:{}",dateString);
                return date;
                }
        } catch (Exception e) {
            this.logger.info("正则表达式无法解析当前日期:{}",dateString);
            // e.printStackTrace();
        }
        try {
            Matcher matcher = this.timestampPattern.matcher( dateString );
            if ( matcher.matches() ) {
                date = new Date();
                date.setTime( Long.parseLong( dateString ));
                return date;
            }
        } catch( Exception e ) {
            // e.printStackTrace();
        }

        try {
            return DateFormat.getInstance().parse( dateString );
        } catch (Exception e) {
           //  e.printStackTrace();
        }

        try {
            return new Date( dateString );
        }catch (Exception e) {
            // e.printStackTrace();
        }
        // 如果无法解析，则返回空
        this.logger.info( "无法解析" );
        return null;
    }


    public int getMonthIndex( String month ) throws  Exception{
        for ( int i = 0; i < DateRegex.monthes.length; i++) {
            if ( month.equals( DateRegex.monthes[i] )  ) {
                return i;
            }
        }
        this.logger.info( "日期转化器转换失败" );
       throw new Exception( "未知的月份:" + month );
    }
}
