package service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import repository.CrudMapper;

/**
 * CRUD service that uses a MyBatis Mapper implementation
 * You should extend it and inject your Repository bean by overriding setRepository
 *
 * @param <T> Your resource class to manage, usually an entity class
 * @param <ID> Resource id type, usually Long or String
 * @param <R> The repository class
 */
@Transactional(readOnly = true)
public class CrudServiceImpl<T, ID extends Serializable, R extends CrudMapper<T, ID>> implements
        CrudService<T, ID> {

    protected R repository;

    /**
     * @param repository
     *            the repository to set
     */
    public void setRepository(R repository) {
        this.repository = repository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public T create(T resource) {
        repository.insertSelective(resource);
        return resource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = false)
    public T update(T resource) {
        repository.updateByPrimaryKey(resource);
        return resource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void delete(ID id) {
        repository.deleteByPrimaryKey(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T findOne(ID id) {
        T entity = repository.selectByPrimaryKey(id);
        if (entity == null) {
            throw new RuntimeException(id + "：找不到相应的资源");
        }
        return entity;
    }
    
}
