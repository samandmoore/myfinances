package com.myfinances.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.Objects;

/**
 * @author Sam Moore
 * @since 8/8/13 1:18 AM
 */
@Entity
@Table(name = "categories")
public class Category {
    private Long id;
    private String title;

    private CategoryType type;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getTitle());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;  // test for reference equality
        if (obj == null) return false; // test for null

        if (!(obj instanceof Category)) {
            return false;
        }

        final Category other = (Category) obj;

        return Objects.equal(getId(), other.getId())
                && Objects.equal(getTitle(), other.getTitle());
    }
}
