package com.gabchak.dao;

import com.gabchak.model.Order;
import com.gabchak.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderDaoImpl implements OrderDao {

    private JdbcTemplate jdbcTemplate;
    private ProductDao productDao;
    private UserDao userDao;

    @Autowired
    public OrderDaoImpl(JdbcTemplate jdbcTemplate, ProductDao productDao, UserDao userDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.productDao = productDao;
        this.userDao = userDao;
    }

    @Override
    public long create(Order order) {
        return (long) jdbcTemplate.update(connection -> {
            PreparedStatement orderStatement = null;
            PreparedStatement productsStatement;
            try {
                connection.setAutoCommit(false);
                orderStatement = connection
                        .prepareStatement("INSERT INTO ORDERS (CUSTOMER_ID, ORDER_DATE, ORDER_AMOUNT, ORDER_COMMENT) VALUES (?, ?, ?, ?);",
                                Statement.RETURN_GENERATED_KEYS);
                orderStatement.setLong(1, order.getCustomer().getId());

                LocalDate localDate = LocalDateTime.now().toLocalDate();
                orderStatement.setDate(2, Date.valueOf(localDate));

                orderStatement.setDouble(3, order.getOrderAmount());
                orderStatement.setString(4, order.getOrderComment());

                orderStatement.executeUpdate();
                ResultSet generatedKeys = orderStatement.getGeneratedKeys();
                Long orderId = null;
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getLong("ORDER_ID");
                } else {
                    connection.rollback();
                }

                if (orderId != null) {

                    for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
                        Product product = entry.getKey();
                        Integer quantity = entry.getValue();

                        productsStatement = connection.prepareStatement(
                                "INSERT INTO ORDER_TO_PRODUCT (FK_ORDER_ID, FK_PRODUCT_ID, PRODUCT_QUANTITY) VALUES (?, ?, ?)");
                        productsStatement.setLong(1, orderId);
                        productsStatement.setLong(2, product.getId());
                        productsStatement.setInt(3, quantity);

                        productsStatement.executeUpdate();
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException el) {
                    el.printStackTrace();
                    throw new RuntimeException(el.getMessage());
                }
            }

            return orderStatement;
        });
    }

    @Override
    public void update(Order order) {
        jdbcTemplate.update(connection -> {
            PreparedStatement orderStatement = null;
            PreparedStatement orderToProductStatement;
            try {
                connection.setAutoCommit(false);

                orderStatement = connection.prepareStatement(
                        "UPDATE ORDERS SET CUSTOMER_ID = ?, ORDER_DATE = ?, ORDER_AMOUNT = ?, ORDER_COMMENT = ? WHERE ORDER_ID =?",
                        Statement.RETURN_GENERATED_KEYS);

                orderStatement.setLong(1, order.getCustomer().getId());
                orderStatement.setDate(2, Date.valueOf(order.getOrderDate()));
                orderStatement.setDouble(3, order.getOrderAmount());
                orderStatement.setString(4, order.getOrderComment());
                orderStatement.setLong(5, order.getOrderId());

                orderStatement.executeUpdate();

                ResultSet generatedKeys = orderStatement.getGeneratedKeys();

                if (generatedKeys.next()) {

                    for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
                        Product product = entry.getKey();
                        Integer quantity = entry.getValue();

                        orderToProductStatement = connection.prepareStatement(
                                "UPDATE ORDER_TO_PRODUCT SET FK_PRODUCT_ID = ?, PRODUCT_QUANTITY = ? WHERE FK_ORDER_ID = ?");
                        orderToProductStatement.setLong(1, product.getId());
                        orderToProductStatement.setInt(2, quantity);
                        orderToProductStatement.setLong(3, order.getOrderId());

                        orderToProductStatement.executeUpdate();
                    }
                }

                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException el) {
                    el.printStackTrace();
                    throw new RuntimeException(el.getMessage());
                }
            }
            return orderStatement;
        });
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(connection -> {
            PreparedStatement orderStatement = null;
            PreparedStatement orderToProductStatement;
            try {
                connection.setAutoCommit(false);

                orderToProductStatement = connection.prepareStatement(
                        "DELETE FROM ORDER_TO_PRODUCT WHERE FK_ORDER_ID = ?");
                orderToProductStatement.setLong(1, id);
                orderToProductStatement.executeUpdate();

                orderStatement = connection.prepareStatement(
                        "DELETE FROM ORDERS WHERE ORDER_ID = ?");
                orderStatement.setLong(1, id);
                orderStatement.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                try {
                    connection.rollback();
                } catch (SQLException el) {
                    el.printStackTrace();
                    throw new RuntimeException(el.getMessage());
                }
            }
            return orderStatement;
        });
    }

    @Override
    public Order findById(Long id) {

        Order order = jdbcTemplate.queryForObject(
                "SELECT ORDER_ID, CUSTOMER_ID, ORDER_DATE, ORDER_AMOUNT, ORDER_COMMENT FROM ORDERS WHERE ORDER_ID = ?",
                new Object[]{id}, (rs, rowNum) -> new Order(
                        rs.getLong("ORDER_ID"),
                        userDao.findById(rs.getLong("CUSTOMER_ID")),
                        rs.getDate("ORDER_DATE").toLocalDate(),
                        rs.getString("ORDER_COMMENT"),
                        rs.getDouble("ORDER_AMOUNT")
                ));

        if (order != null) {
            getProductAndQuantity(order);
        }

        return order;
    }

    @Override
    public List<Order> findAll() {
        return jdbcTemplate.query(
                "SELECT ORDER_ID, CUSTOMER_ID, ORDER_AMOUNT, ORDER_COMMENT, ORDER_DATE FROM ORDERS",
                (rs, rowNum) -> new Order(
                        rs.getLong("ORDER_ID"),
                        userDao.findById(rs.getLong("CUSTOMER_ID")),
                        rs.getDate("ORDER_DATE").toLocalDate(),
                        rs.getString("ORDER_COMMENT"),
                        rs.getDouble("ORDER_AMOUNT")
                ));
    }

    private void getProductAndQuantity(Order order) {
        Map<Product, Integer> products = new HashMap<>();
        Long orderId = order.getOrderId();

        jdbcTemplate.query("SELECT FK_PRODUCT_ID, PRODUCT_QUANTITY FROM ORDER_TO_PRODUCT WHERE FK_ORDER_ID = ?",
                new Object[]{orderId}, (rs, rowNum) -> products.put(
                        productDao.findById(rs.getLong("FK_PRODUCT_ID")),
                        rs.getInt("PRODUCT_QUANTITY")
                ));

        order.setProducts(products);
    }
}
