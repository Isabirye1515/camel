package com.camel.common.object;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao<T,ID extends Serializable> {
    List<T> getAll();
    List<T> getAllMatching(Map<String,Object> criteria, boolean asc);
    List<T> getPage(int page,int pageSize);
    int getCount();
    int getCountMatching(Map<String,Object> criteria, boolean asc);
}
