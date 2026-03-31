package org.example.models;

import org.example.exceptions.SoldeInsuffisantException;

public class Courant {
    private String numero;
    private double solde;
    private double ligneDeCredit;
    private Personne titulaire;

    public Courant(String numero, double solde, double ligneDeCredit, Personne titulaire) {
        this.numero = numero;
        this.setLigneDeCredit(ligneDeCredit);
        this.setSolde(solde);
        this.titulaire = titulaire;
    }

    public Courant(String numero, Personne titulaire) {
        this.numero = numero;
        this.titulaire = titulaire;
        this.solde = 0;
        this.ligneDeCredit = 0;
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


}
