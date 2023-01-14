package org.hibernate.bugs;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Entity
public class Entity1 extends AbstractEntity {
    @OneToMany(mappedBy = "parent", cascade = ALL, orphanRemoval = true)
    private Set<Entity2> children = new HashSet<>();

    public Set<Entity2> getChildren() {
        return children;
    }

    public void setChildren(Set<Entity2> children) {
        this.children = children;
    }
}
