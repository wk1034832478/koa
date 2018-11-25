package hyywk.top.koa.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonUtil {
    private Gson gson;
    protected JsonUtil() {
        this.gson = new GsonBuilder().registerTypeAdapterFactory( HibernateProxyTypeAdapter.FACTORY).create();
    }
    public String convertObjectToString( Object object ) {
        return this.gson.toJson( object );
    }

}
