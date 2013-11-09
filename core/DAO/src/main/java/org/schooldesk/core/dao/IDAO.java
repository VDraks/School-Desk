package org.schooldesk.core.dao;

import org.schooldesk.core.dto.IDTO;

public interface IDAO<T extends IDTO> {
    public T save(T entity);
    public T update(T entity);
    public T loadById(Long id);
    public void delete(Long id);
}
