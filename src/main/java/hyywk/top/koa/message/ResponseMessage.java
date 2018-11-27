package hyywk.top.koa.message;

public class ResponseMessage {
    private String msg;
    private int code;
    private Object object;
    private PageMessage pageMessage;
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
    public ResponseMessage(Object object, PageMessage pageMessage, int code) {
        this.object = object;
        this.pageMessage = pageMessage;
        this.code = code;
    }
    public ResponseMessage(Object object, int code) {
        this.object = object;
        this.code = code;
    }

    public PageMessage getPage() {
        return pageMessage;
    }

    public void setPage(PageMessage pageMessage) {
        this.pageMessage = pageMessage;
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
