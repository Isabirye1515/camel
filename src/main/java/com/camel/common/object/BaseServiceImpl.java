package com.camel.common.object;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

    private BaseDao<T, ID> baseDao;

    /**
     * Setter method for dependency injection. 
     * Subclasses can override this or use Spring's qualifier routing to bind their concrete DAO.
     */
    @Autowired
    public void setBaseDao(BaseDao<T, ID> baseDao) {
        this.baseDao = baseDao;
    }

    protected BaseDao<T, ID> getDao() {
        if (this.baseDao == null) {
            throw new IllegalStateException("BaseDao has not been initialized on this Service implementation.");
        }
        return this.baseDao;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAll() {
        return getDao().getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getAllMatching(Map<String, Object> criteria, boolean asc) {
        return getDao().getAllMatching(criteria, asc);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getPage(int pagesize, int sortorder) {
        return getDao().getPage(pagesize, sortorder);
    }

    @Override
    @Transactional(readOnly = true)
    public int getCount() {
        return getDao().getCount();
    }

    @Override
    @Transactional(readOnly = true)
    public int getCountMatching(Map<String, Object> criteria, boolean asc) {
        return getDao().getCountMatching(criteria, asc);
    }
}