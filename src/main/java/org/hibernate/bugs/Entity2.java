package org.hibernate.bugs;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Entity2 extends AbstractEntity {
    @ManyToOne(fetch = LAZY)
    private Entity1 parent;

    @OneToMany(mappedBy = "parent", cascade = ALL, orphanRemoval = true)
    Set<Entity3> children = new HashSet<>();

    public Entity1 getParent() {
        return parent;
    }

    public void setParent(Entity1 parent) {
        this.parent = parent;
    }

    public Set<Entity3> getChildren() {
        return children;
    }

    public void setChildren(Set<Entity3> children) {
        this.children = children;
    }
}
