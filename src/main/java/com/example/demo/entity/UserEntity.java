package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name="users")
public class UserEntity {
//    @Id
//    @Column(nullable = false, length = 50)
//    private String email;
    @Id
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private String password;
//    @ColumnDefault(value="T")
    private String activation;
    private Integer totalScore;
    @Column(nullable = true, updatable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    private Date registerDate;


}
