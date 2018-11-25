package hyywk.top.koa.message;

public class ResponseMessage {
    private String msg;
    private int code;
    private Object object;
    public ResponseMessage(String msg) {
        this.msg = msg;
    }
    public static ResponseMessage createMsg( String msg ) {
        return new ResponseMessage( msg );
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
    public ResponseMessage(String msg, int code) {
        this.msg = msg;
        this.code = code;
}
    public ResponseMessage(Object object, int code) {
        this.object = object;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
