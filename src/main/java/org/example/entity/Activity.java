package org.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "activities")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean active;

    public Activity() {}

    public Activity(String name, boolean active) {
        this.name = name;
        this.active = active;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public boolean isActive() { return active; }

    public void setName(String name) { this.name = name; }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public String toString() {
        return "Activity{" + "id=" + id + ", name='" + name + '\'' + ", active=" + active + '}';
    }
}

