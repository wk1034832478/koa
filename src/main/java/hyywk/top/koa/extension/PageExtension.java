package hyywk.top.koa.extension;

import org.springframework.data.domain.Page;

/**
 * 页的扩展
 * @param <T> 页对象中的数据类型
 */
public class PageExtension<T> {
    private Page<T> page;
    private Integer pageSize; // 当前每页查询限定数量
    private Integer pageNumber; // 当前每页查询限定数量
    public PageExtension( Page<T> page, Integer pageSize, Integer pageNumber) {
        this.page = page;
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public PageExtension(Page<T> page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
