package com.stlesnik.core.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CASSETTES")
public class Cassette {
    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE")
    private CassetteType type;

    public enum CassetteType {
        Recycling, In, RetractReject
    }

    @OneToMany(mappedBy = "cassette_id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Counter> counters;

    public Cassette(){
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CassetteType getType() {
        return type;
    }

    public void setType(CassetteType type) {
        this.type = type;
    }
}

