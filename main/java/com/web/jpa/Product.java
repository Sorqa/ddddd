package com.web.jpa;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="product")
public class Product
{
        @Id
        @SequenceGenerator(sequenceName = "prod_id_seq", allocationSize = 1, name="prod_id_gen")
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prod_id_gen")
        private int id;
        //@Column(name="pname")
        private String name;
        private int price;
        private String description;

}
