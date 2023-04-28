package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import com.example.demo.model.SelectedPostId;

@Entity
@Table(name = "selected_posts")
@IdClass(SelectedPostId.class)
public class SelectedPost {
    @Id
    @Column(name = "id")
    private Integer id;

    @Id
    @Column(name = "package_name")
    private String packageName;

    @Column(name = "position")
    private Integer position;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    public SelectedPost() {
        // Default constructor
    }

    public SelectedPost(Integer id, String packageName, Integer position, Boolean active, Date createdAt, Date updatedAt) {
        this.id = id;
        this.packageName = packageName;
        this.position = position;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Equals and HashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectedPost that = (SelectedPost) o;
        return Objects.equals(id, that.id) && Objects.equals(packageName, that.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, packageName);
    }
}
