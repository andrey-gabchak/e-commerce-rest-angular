package com.gabchak.models;

import com.gabchak.controllers.external.model.CartDto;
import com.gabchak.controllers.external.model.ProductDto;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("FK_CUSTOMER_ID")
    private User user;
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<CartDetails> cartDetails = new ArrayList<>();
    @Column(name = "AMOUNT")
    private Double amount;

    public static Cart of(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setUser(cartDto.getUser());
        cart.setAmount(cartDto.getAmount());

        Map<ProductDto, Integer> products = cartDto.getProducts();

        for (Map.Entry<ProductDto, Integer> productDtoIntegerEntry : products.entrySet()) {
            ProductDto productDto = productDtoIntegerEntry.getKey();
            Product product = Product.of(productDto);
            Integer quantity = productDtoIntegerEntry.getValue();
            cart.setProductAndQuantity(product, quantity);
        }

        return cart;
    }

    public void setProductAndQuantity(Product product, Integer quantity) {

        CartDetails cartDetails = createCartDetails(product);
        cartDetails.setQuantity(quantity);

        if (this.cartDetails.contains(cartDetails)) {
            int index = this.cartDetails.indexOf(cartDetails);
            this.cartDetails.set(index, cartDetails);
        } else {
            this.cartDetails.add(cartDetails);
        }
    }

    public void deleteProduct(Product product) {
        CartDetails cartDetails = createCartDetails(product);
        this.cartDetails.remove(cartDetails);
    }

    private CartDetails createCartDetails(Product product) {
        CartDetails cartDetails = CartDetails.empty();
        CartDetailsId cartDetailsId = CartDetailsId.getEmpty();
        cartDetailsId.setFkCartId(this.getId());
        cartDetails.setProduct(product);
        cartDetailsId.setFkProductId(product.getId());
        cartDetails.setCartDetailsId(cartDetailsId);
        return cartDetails;
    }
}
