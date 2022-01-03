package com.example.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "QUIZ_CATEGORY")
@Getter @Setter
@NoArgsConstructor
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_num")
    private Long categoryNum;

    @Column(name = "category_name",nullable = false, unique = true)
    private String categoryName;
}
