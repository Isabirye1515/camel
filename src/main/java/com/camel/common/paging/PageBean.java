package com.camel.common.paging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageBean<T> {
    private int page;
    private int pageSize;
    private int totalItems;
    private int totalPages;
    private List<T> items;
    private List<Map<String,Object>> searchToPage = new ArrayList<>();
    public PageBean(){}
    public PageBean(int page, int pageSize, int totalItems, int totalPages, List<T> items) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.items = items;
    }
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalItems() {
        return totalItems;
    }
    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }
    public int getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
    public List<T> getItems() {
        return items;
    }
    public void setItems(List<T> items) {
        this.items = items;
    }
    public List<Map<String, Object>> getSearchToPage() {
        return searchToPage;
    }
    public void setSearchToPage(List<Map<String, Object>> searchToPage) {
        this.searchToPage = searchToPage;
    }

    public void convertCreteriaToSearchMap(Map<String,Object> criteria){
        if(criteria == null || criteria.isEmpty()) return;

        for(Map.Entry<String,Object> entry:criteria.entrySet()){
            Map<String,Object> paramMap = new HashMap<>();
            paramMap.put("paramKey", entry.getKey());
            paramMap.put("paramValue",entry.getValue());
            this.searchToPage.add(paramMap);
        }
    }

    
    
}
