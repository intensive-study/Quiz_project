package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name="USER_INFO")
@DynamicInsert
@NoArgsConstructor
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
//    @ColumnDefault("T")
    private String activation;
    private Integer totalScore;
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp registerDate;

}
