package com.hospital.dao;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by WangCheng on 2018/3/13.
 */

public interface GenericDao<T,PK extends Serializable> {
    // -------------------- 基本检索、增加、修改、删除操作 --------------------

    // 根据主键获取实体。如果没有相应的实体，返回 null。
    public T get(PK id);

    // 根据主键获取实体。如果没有相应的实体，抛出异常。
    public T load(PK id);

    // 获取全部实体。
    public List<T> loadAll();

    // 更新实体
    public void update(T entity);

    // 存储实体到数据库
    public Integer save(T entity);

    // 删除指定的实体
    public void delete(PK id);

    // 强制立即更新缓冲数据到数据库（否则仅在事务提交时才更新）
    public void flush();
}
