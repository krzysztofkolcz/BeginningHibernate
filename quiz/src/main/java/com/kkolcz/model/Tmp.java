package com.kkolcz.model;

import javax.persistence.*;

@Entity
@Table(name="tmp")
public class Tmp {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    public int getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Column(name = "pseudo")
    private String pseudo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tmp)) return false;

        Tmp tmp = (Tmp) o;

        return id == tmp.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
