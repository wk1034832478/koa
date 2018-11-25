package hyywk.top.koa.util;

public interface DateRegex {
    // Nov 21, 2018 8:00:00 AM
    String date1 = "([a-zA-Z]{3})\\s([0-9]{1,2}),\\s([0-9]{4})\\s{1}([0-9]{1,2}):([0-9]{1,2}):([0-9]{1,2})\\s([a-zA-Z]{2})$";
    String TIMESTAMP = "^[0-9]{10,15}$"; // 时间戳
    // 英文的月份
    String[] monthes = {"Jan","Feb","Mar","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
    String Morning = "AM";
    String Afternoon= "PM";
}
