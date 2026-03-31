package org.example.models;

import java.util.Objects;

public abstract class Compte {

    private String numero;
    private double solde;
    private Personne titulaire;

    public Compte(String numero, double solde, Personne titulaire) {
        this.numero = numero;
        this.setSolde(solde);
        this.titulaire = titulaire;
    }

    public Compte(String numero, Personne titulaire) {
        this.numero = numero;
        this.titulaire = titulaire;
        this.setSolde(0);

    }

    public String getNumero() {
        return numero;
    }

    public double getSolde() {
        return solde;
    }

    public Personne getTitulaire() {
        return titulaire;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    protected void setSolde(double solde) {
        this.solde = solde;
    }

    public void setTitulaire(Personne titulaire) {
        this.titulaire = titulaire;
    }

    public abstract void retrait(double montant);

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return Double.compare(getSolde(), compte.getSolde()) == 0 && Objects.equals(getNumero(), compte.getNumero()) && Objects.equals(getTitulaire(), compte.getTitulaire());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumero(), getSolde(), getTitulaire());
    }

    @Override
    public String toString() {
        return "Compte{" +
                "numero='" + numero + '\'' +
                ", solde=" + solde +
                ", titulaire=" + titulaire +
                '}';
    }

    public void depot(double montant){
        if (montant < 0){
            throw new IllegalArgumentException("Montant invalide");
        }
        setSolde(getSolde() + montant);
    }

    protected abstract double calculInteret();

    public void appliquerInteret(){
        this.setSolde(this.getSolde() + calculInteret());
    }
}
