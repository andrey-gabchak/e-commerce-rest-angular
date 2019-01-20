package com.gabchak.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class CartDetailsId implements Serializable {

    private Long fkCartId;
    private Long fkProductId;

    public static CartDetailsId getEmpty() {
        return new CartDetailsId();
    }
}
