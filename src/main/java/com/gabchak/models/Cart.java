package com.gabchak.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CARTS")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("FK_CUSTOMER_ID")
    private User user;
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    private List<CartDetails> cartDetails = new ArrayList<>();
    @Column(name = "AMOUNT")
    private Double amount;

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
        CartDetails cartDetails = new CartDetails();
        CartDetailsId cartDetailsId = new CartDetailsId();

        cartDetailsId.setFkCartId(this.getId());
        cartDetailsId.setFkProductId(product.getId());

        cartDetails.setProduct(product);
        cartDetails.setCartDetailsId(cartDetailsId);

        return cartDetails;
    }
}
