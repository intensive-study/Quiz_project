package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Builder
@Table(name="USER_INFO")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
//    @Id
//    @Column(nullable = false, length = 50)
//    private String email;
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "username", nullable = false, length = 50)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "nickname", length = 50)
    private String nickname;
//    @ColumnDefault("T")
//    private String activation;
    @Column(name = "activated")
    private boolean activated;
    private float totalScore;
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp registerDate;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name= "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name="authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

}
