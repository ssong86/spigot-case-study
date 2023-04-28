package com.example.demo.model;

import java.io.Serializable;
import java.util.Objects;

public class SelectedPostId implements Serializable {
    private Integer id;
    private String packageName;

    public SelectedPostId() {
        // Default constructor
    }

    public SelectedPostId(Integer id, String packageName) {
        this.id = id;
        this.packageName = packageName;
    }

    // Equals and HashCode methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectedPostId that = (SelectedPostId) o;
        return Objects.equals(id, that.id) && Objects.equals(packageName, that.packageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, packageName);
    }
}
