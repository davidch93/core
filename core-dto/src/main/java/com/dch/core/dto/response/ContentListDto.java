package com.dch.core.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(description = "Defined base response message from the endpoint that containing list of object")
public class ContentListDto<T> {

    @Schema(description = "Size of page")
    private int pageSize;

    @Schema(description = "Number of page")
    private int pageNumber;

    @Schema(description = "Size of the list")
    private int actualSize;

    @Schema(description = "List of data")
    private List<T> contentList;

    /**
     * Instantiates a new Content List DTO.
     */
    public ContentListDto() {
    }

    /**
     * Instantiates a new Content List DTO.
     *
     * @param pageSize    the page size
     * @param pageNumber  the page number
     * @param actualSize  the actual size
     * @param contentList the content list
     */
    public ContentListDto(int pageSize, int pageNumber, int actualSize, List<T> contentList) {
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
        this.actualSize = actualSize;
        this.contentList = contentList;
    }

    /**
     * Get page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Set page size.
     *
     * @param pageSize the page size
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * Get page number.
     *
     * @return the page number
     */
    public int getPageNumber() {
        return pageNumber;
    }

    /**
     * Set page number.
     *
     * @param pageNumber the page number
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * Get actual size.
     *
     * @return the actual size
     */
    public int getActualSize() {
        return actualSize;
    }

    /**
     * Set actual size.
     *
     * @param actualSize the actual size
     */
    public void setActualSize(int actualSize) {
        this.actualSize = actualSize;
    }

    /**
     * Get content list.
     *
     * @return the content list
     */
    public List<T> getContentList() {
        return contentList;
    }

    /**
     * Set content list.
     *
     * @param contentList the content list
     */
    public void setContentList(List<T> contentList) {
        this.contentList = contentList;
    }
}
