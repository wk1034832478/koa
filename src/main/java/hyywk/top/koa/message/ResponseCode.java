package hyywk.top.koa.message;

public interface ResponseCode {
    // 还未登陆
    int  LOGINOUT = 1001;
    // 成功
    int  SUCCESS = 1002;
    // 缺失信息
    int  LOST = 1003;
    // 错误
    int  ERROR = 1004;
    // 已经注册过
    int HAS_REGISTRIED = 1005;
    // 请稍后再次注册的
    int WAITTING = 1006;
}
