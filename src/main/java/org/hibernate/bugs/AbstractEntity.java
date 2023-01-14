package org.hibernate.bugs;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import static jakarta.persistence.GenerationType.SEQUENCE;

@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "#" + getId();
    }
}
