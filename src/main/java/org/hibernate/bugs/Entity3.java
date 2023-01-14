package org.hibernate.bugs;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import static jakarta.persistence.FetchType.LAZY;

@Entity
public class Entity3 extends AbstractEntity {
    @ManyToOne(fetch = LAZY)
    private Entity2 parent;

    public Entity2 getParent() {
        return parent;
    }

    public void setParent(Entity2 parent) {
        this.parent = parent;
    }
}
