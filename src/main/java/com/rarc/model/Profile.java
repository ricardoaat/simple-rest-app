package com.rarc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import com.rarc.model.auth.User;

@Entity
@Table(name="profiles")
public class Profile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profiles_seq")
    @SequenceGenerator(name = "profiles_seq", sequenceName = "profiles_seq", allocationSize = 1)    
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "bio", length = 500)
    @Size(min = 4, max = 500)
    private String bio;

    @Column(name = "address", length = 300)
    @Size(min = 4, max = 300)
    private String address;


    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public User getUser(){
        return user;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getBio(){
        return bio;
    }

    public void setBio(String bio){
        this.bio = bio;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
}