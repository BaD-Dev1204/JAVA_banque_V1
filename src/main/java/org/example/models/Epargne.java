package org.example.models;

import org.example.exceptions.SoldeInsuffisantException;

import java.time.LocalDate;
import java.util.Objects;

public class Epargne extends Compte {
    private LocalDate dateDernierRetrait;

    public Epargne(String numero, double solde,Personne titulaire, LocalDate dateDernierRetrait) {
        super(numero, solde, titulaire);
        this.dateDernierRetrait = dateDernierRetrait;
    }

    public LocalDate getDateDernierRetrait() {
        return dateDernierRetrait;
    }

    public void setDateDernierRetrait(LocalDate dateDernierRetrait) {
        this.dateDernierRetrait = dateDernierRetrait;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epargne epargne = (Epargne) o;
        return Objects.equals(getDateDernierRetrait(), epargne.getDateDernierRetrait());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getDateDernierRetrait());
    }

    @Override
    public String toString() {
        return super.toString().replace("}", "") +
                ", dateDernierRetrait=" + dateDernierRetrait + '}';
    }

    @Override
    protected double calculInteret() {
        return getSolde() * 0.045; // 4,5%
    }

    @Override
    public void retrait(double montant) {
        if (montant < 0) {
            throw new IllegalArgumentException("Montant invalide");
        }

        if (getSolde() < montant) {
            throw new SoldeInsuffisantException("Fonds insuffisants");
        }

        setSolde(getSolde() - montant);
        setDateDernierRetrait(LocalDate.now());
    }
}
