package com.harun.macit.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name= "KULLANICILAR")
@Data
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
    @Column(name="ISIM",length = 100)
    private String firstName;
    @Column(name = "SOYISIM",length = 100)
    private String lastName;
}
