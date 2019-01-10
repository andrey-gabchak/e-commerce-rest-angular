package com.gabchak.dao.impl;

import com.gabchak.dao.CategoryDao;
import com.gabchak.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {

    private SessionFactory sessionFactory;

    @Autowired
    public CategoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<List<Category>> findAll() {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .createNativeQuery("SELECT ID, CATEGORY_NAME FROM CATEGORIES",
                Category.class).list());
    }

    @Override
    public Category findByIdWithProductList(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from category c join fetch c.productList where c.id =:id", Category.class)
                .setParameter("ID", id)
                .uniqueResult();
    }

    @Override
    public void addCategory(Category category) {
        sessionFactory.getCurrentSession().save(category);
    }

    @Override
    public void update(Category category) {
        sessionFactory.getCurrentSession().update(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .createQuery("from Category where id =:id", Category.class)
                .setParameter("id", id)
                .uniqueResult());
    }

    @Override
    public Optional<Category> findByName(String name) {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .createQuery("from Category where categoryName =:name", Category.class)
                .setParameter("name", name)
                .uniqueResult());
    }

    @Override
    public Optional<Category> findByNameWithProductList(String name) {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .createQuery("from Category c join fetch c.productList where c.name =:name", Category.class)
                .setParameter("name", name)
                .uniqueResult());
    }

    @Override
    public void deleteByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(currentSession.load(Category.class, name));
        currentSession.flush();
    }
}
