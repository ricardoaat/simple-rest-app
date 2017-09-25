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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.joda.ser.DateTimeSerializer;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import com.rarc.model.auth.User;

@Entity
@Table(name="profiles")
public class Profile {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profiles_seq")
    @SequenceGenerator(name = "profiles_seq", sequenceName = "profiles_seq", allocationSize = 1)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "bio", length = 500)
    @Size(min = 2, max = 500)
    private String bio;

    @Column(name = "address", length = 300)
    @Size(min = 4, max = 300)
    private String address;

    @Column(name = "country", length = 300)
    @Size(min = 2, max = 300)
    private String country;

    @Column(name = "photoUrl", length = 300)
    @Size(min = 2, max = 300)
    private String photoUrl;

    @Column(name = "sex", length = 300)
    @Size(min = 2, max = 300)
    private String sex;

    @Column(name = "birthdate")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = DateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")    
    private DateTime birthdate;

    @Column(name = "phone", length = 30)
    @Size(min = 5, max = 30)
    private String phone;

    @Column(name = "website", length = 500)
    @Size(min = 2, max = 500)
    private String website;

    public Long getId() {
        return this.id;
     }
   
     public void setId(Long value) {
        this.id = value;
     }

     public User getUser() {
        return this.user;
     }
   
     public void setUser(User value) {
        this.user = value;
     }

    public String getAddress() {
        return this.address;
     }
   
     public void setAddress(String value) {
        this.address = value;
     }
   
     public String getCountry() {
        return this.country;
     }
   
     public void setCountry(String value) {
        this.country = value;
     }
   
     public String getSex() {
        return this.sex;
     }
   
     public void setSex(String value) {
        this.sex = value;
     }
   
     public DateTime getBirthdate() {
        return this.birthdate;
     }
   
     public void setBirthdate(DateTime value) {
        this.birthdate = value;
     }
   
     public String getPhone() {
        return this.phone;
     }
   
     public void setPhone(String value) {
        this.phone = value;
     }
   
     public String getPhotoUrl() {
        return this.photoUrl;
     }
   
     public void setPhotoUrl(String value) {
        this.photoUrl = value;
     }
   
     public String getWebsite() {
        return this.website;
     }
   
     public void setWebsite(String value) {
        this.website = value;
     }
   
     public String getBio() {
        return this.bio;
     }
   
     public void setBio(String value) {
        this.bio = value;
     }
}