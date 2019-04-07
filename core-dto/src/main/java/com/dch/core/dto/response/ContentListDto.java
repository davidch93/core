package com.dch.core.dto.response;

import java.util.List;

/**
 * Class that defined response message from server containing list object and
 * the return type should be written straight to the HTTP response body.
 *
 * @param <T> the object to store.
 * @author David.Christianto
 * @version 2.0.0
 * @since 1.0.0
 */
public class ContentListDto<T> {

    private int pageSize;
    private int pageNumber;
    private int actualSize;
    private List<T> contentList;

    public ContentListDto() {
    }

    public ContentListDto(int pageSize, int pageNumber, int actualSize, List<T> contentList) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.actualSize = actualSize;
        this.contentList = contentList;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the pageNumber
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * @param pageNumber the pageNumber to set
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * @return the actualSize
     */
    public int getActualSize() {
        return actualSize;
    }

    /**
     * @param actualSize the actualSize to set
     */
    public void setActualSize(int actualSize) {
        this.actualSize = actualSize;
    }

    /**
     * @return the contentList
     */
    public List<T> getContentList() {
        return contentList;
    }

    /**
     * @param contentList the contentList to set
     */
    public void setContentList(List<T> contentList) {
        this.contentList = contentList;
    }
}
