package org.example.models;

import org.example.exceptions.SoldeInsuffisantException;

import java.util.Objects;

public class Courant {
    private String numero;
    private double solde;
    private double ligneDeCredit;
    private Personne titulaire;

    public Courant() {
    }

    public Courant(String numero, double solde, double ligneDeCredit, Personne titulaire) {
        this.numero = numero;
        this.setLigneDeCredit(ligneDeCredit);
        this.setSolde(solde);
        this.titulaire = titulaire;
    }

    public Courant(String numero, Personne titulaire) {
        this(numero, 0, 0, titulaire);
    }

    public String getNumero() {
        return numero;
    }

    public double getSolde() {
        return solde;
    }

    public double getLigneDeCredit() {
        return ligneDeCredit;
    }

    public Personne getTitulaire() {
        return titulaire;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    private void setSolde(double solde) {
        if (solde + this.getLigneDeCredit() < 0) {
            throw new IllegalArgumentException("Dépassement du découvert autorisé");
        }
        this.solde = solde;
    }

    public void setLigneDeCredit(double ligneDeCredit) {
        if (ligneDeCredit < 0) {
            throw new IllegalArgumentException("Le credit ne peut pas être négatif");
        }
        this.ligneDeCredit = ligneDeCredit;
    }

    public void setTitulaire(Personne titulaire) {
        this.titulaire = titulaire;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Courant courant = (Courant) o;
        return Double.compare(getSolde(), courant.getSolde()) == 0 && Double.compare(getLigneDeCredit(), courant.getLigneDeCredit()) == 0 && Objects.equals(getNumero(), courant.getNumero()) && Objects.equals(getTitulaire(), courant.getTitulaire());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumero(), getSolde(), getLigneDeCredit(), getTitulaire());
    }

    @Override
    public String toString() {
        return "Courant{" +
                "numero='" + numero + '\'' +
                ", solde=" + solde +
                ", ligneDeCredit=" + ligneDeCredit +
                ", titulaire=" + titulaire +
                '}';
    }

    public void retrait(double montant){
        if (montant < 0){
            throw new IllegalArgumentException("Le montant du retrait ne peut pas être négatif");
        }
        if ((this.getSolde() + this.getLigneDeCredit()) < montant){
            throw new SoldeInsuffisantException("Fonds insuffisants");
        }
        this.setSolde(this.getSolde() - montant);
    }


    public void depot(double montant){
        if (montant < 0){
            throw new IllegalArgumentException("Le montant du dépot ne peut pas être négatif");
        }
        this.setSolde(this.getSolde() + montant);

    }

    public static double  calculerSommeSoldes(Courant compte1, Courant compte2){
        double s1 = compte1.getSolde() < 0 ? 0 : compte1.getSolde();
        double s2 = compte2.getSolde() < 0 ? 0 : compte2.getSolde();
        return s1 + s2;
    }


}
