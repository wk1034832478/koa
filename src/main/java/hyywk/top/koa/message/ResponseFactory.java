package hyywk.top.koa.message;

import hyywk.top.koa.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseFactory {
    private JsonUtil jsonUtil;
    public String create(String msg, int code) {
        return jsonUtil.convertObjectToString( new ResponseMessage(msg,code) );
    }
    public String create(Object obj, int code) {
        return jsonUtil.convertObjectToString( new ResponseMessage(obj,code) );
    }
    public String create(Object obj,PageMessage pageMessage, int code) {
        return jsonUtil.convertObjectToString( new ResponseMessage(obj,pageMessage,code) );
    }
    public String createByMsg(String msg) {
        return jsonUtil.convertObjectToString( new ResponseMessage(msg) );
    }
    @Autowired
    public void setJsonUtil(JsonUtil jsonUtil) {
        this.jsonUtil = jsonUtil;
    }
}
