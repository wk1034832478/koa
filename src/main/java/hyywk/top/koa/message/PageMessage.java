package hyywk.top.koa.message;

public class PageMessage {
    private Integer totalPages; // 是根据当前的pageSize决定的！
    private Long totalElements; // 当前元素总数
    private Integer currentPageSize; // 当前页面数量
    private Integer currentPage; // 当前页

    public PageMessage(Integer totalPages, Long totalElements, Integer currentPageSize) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.currentPageSize = currentPageSize;
    }

    public PageMessage(Integer totalPages, Long totalElements, Integer currentPageSize, Integer currentPage) {
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.currentPageSize = currentPageSize;
        this.currentPage = currentPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getCurrentPageSize() {
        return currentPageSize;
    }

    public void setCurrentPageSize(Integer currentPageSize) {
        this.currentPageSize = currentPageSize;
    }
}
