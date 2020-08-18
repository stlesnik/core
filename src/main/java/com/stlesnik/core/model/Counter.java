package com.stlesnik.core.model;

import javax.persistence.*;

@Entity
@Table(name = "COUNTERS")
public class Counter {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "VALUEe")
    private int value;

    @Column(name ="NUMBER")
    private int number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASSETTE_ID")
    private Cassette cassette_id;

    public Counter(){
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Cassette getC_id() {
        return cassette_id;
    }

    public void setC_id(Cassette c_id) {
        this.cassette_id = c_id;
    }
}
