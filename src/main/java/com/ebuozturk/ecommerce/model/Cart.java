package com.ebuozturk.ecommerce.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String no;
    private Date expiryDate;
    private String cvc;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Cart(String name, String no, Date expiryDate, String cvc,User user) {
        this.name = name;
        this.no = no;
        this.expiryDate = expiryDate;
        this.cvc = cvc;
        this.user = user;
    }

    public Cart(Long id, String name, String no, Date expiryDate, String cvc,User user) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.expiryDate = expiryDate;
        this.cvc = cvc;
        this.user = user;
    }


}
