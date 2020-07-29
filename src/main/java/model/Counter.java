package model;

import javax.persistence.*;

@Entity
@Table(name = "COUNTERS")
public class Counter {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "VALUEe")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CASSETTE_ID")
    private Cassette cassette_id;

    public Counter(){
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Cassette getC_id() {
        return cassette_id;
    }

    public void setC_id(Cassette c_id) {
        this.cassette_id = c_id;
    }
}
