package hyywk.top.koa.service;

import org.springframework.data.domain.PageRequest;

public class ServiceParent {
    protected final Integer PAGE_INDEX = 0;
    protected final Integer DEFAULT_PAGE_SIZE = 0;

    /**
     * 转化为标准请求页
     * @param page 页码
     * @param pageSize 页大小
     * @return
     */
    protected PageRequest getPage(Integer page, Integer pageSize ) {
        if ( page == null  || page < 0) {
            page = this.PAGE_INDEX;
        }
        if ( pageSize == null  || pageSize < 1 ) {
            pageSize = this.DEFAULT_PAGE_SIZE;
        }
        return PageRequest.of(page,pageSize);
    }
}
