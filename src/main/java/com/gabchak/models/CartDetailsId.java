package com.gabchak.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class CartDetailsId implements Serializable {

    private Integer fkCartId;
    private Integer fkProductId;

}
