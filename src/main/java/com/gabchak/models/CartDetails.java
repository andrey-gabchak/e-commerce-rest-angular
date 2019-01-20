package com.gabchak.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@Entity
@Table(name = "CARTS_DETAILS")
public class CartDetails {

    @EmbeddedId
    private CartDetailsId cartDetailsId;
    @Column(name = "QUANTITY")
    private Integer quantity;

    @MapsId("fkCartId")
    @JoinColumn(name = "FK_CARD_ID")
    @ManyToOne
    private Cart cart;

    @MapsId("fkProductId")
    @JoinColumn(name = "FK_PRODUCT_ID")
    @ManyToOne
    private Product product;

    public static CartDetails empty() {
        return new CartDetails();
    }
}
