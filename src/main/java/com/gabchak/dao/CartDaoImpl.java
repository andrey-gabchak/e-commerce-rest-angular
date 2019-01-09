package com.gabchak.dao;

import com.gabchak.model.Cart;
import com.gabchak.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class CartDaoImpl implements CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Cart findCart(User user) {
        return sessionFactory.getCurrentSession()
                .createQuery("from carts c join fetch c.cartDetails where c.user =:user", Cart.class)
                .setParameter("user", user)
                .uniqueResult();
    }

    @Override
    public void saveOrUpdateCart(Cart cart) {
        sessionFactory.getCurrentSession().saveOrUpdate(cart);
    }

    @Override
    public void deleteProductByProductCode(Long userId, String productCode) {

    }
}
