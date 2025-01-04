package com.workintech.s18d4.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address", schema = "fsweb")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "street")
    @NotNull
    private String street;

    @Column(name = "no")
    @NotNull
    private Integer no;

    @Column(name = "city")
    @NotNull
    private String city;

    @Column(name = "country")
    @NotNull
    private String country;

    @Column(name = "description")
    private String description;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH,CascadeType.PERSIST, CascadeType.REFRESH})
    private Customer customer;
}