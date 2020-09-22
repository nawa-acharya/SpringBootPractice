package com.example.bootpractice.model;

import javax.persistence.*;
import java.util.List;

/**
 * @author Nawa
 * on 9/21/20.
 * (c)Marathon Computer Systems
 */

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private List<UserHistory> userHistoryList;

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<UserHistory> getUserHistoryList() {
        return userHistoryList;
    }

    public void setUserHistoryList(List<UserHistory> userHistoryList) {
        this.userHistoryList = userHistoryList;
    }
}
