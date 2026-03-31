package org.example.models;

import org.example.exceptions.SoldeInsuffisantException;

import java.util.Objects;

public class Courant extends Compte {

    private double ligneDeCredit;


    public Courant(String numero, double solde,Personne titulaire, double ligneDeCredit) {
        super(numero, solde, titulaire);
        this.setLigneDeCredit(ligneDeCredit);

    }

    public Courant(String numero, Personne titulaire) {
        super(numero, 0, titulaire);
        this.setLigneDeCredit(0);
    }



    public double getLigneDeCredit() {
        return ligneDeCredit;
    }


    public void setLigneDeCredit(double ligneDeCredit) {
        if (ligneDeCredit < 0) {
            throw new IllegalArgumentException("Le credit ne peut pas être négatif");
        }
        this.ligneDeCredit = ligneDeCredit;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Courant courant = (Courant) o;
        return Double.compare(getLigneDeCredit(), courant.getLigneDeCredit()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLigneDeCredit());
    }


    @Override
    public String toString() {
        return super.toString().replace("}", "") +
                ", ligneDeCredit=" + ligneDeCredit + '}';
    }

    @Override
    public void retrait(double montant){
        if (montant < 0){
            throw new IllegalArgumentException("Le montant du retrait ne peut pas être négatif");
        }
        if ((this.getSolde() + this.getLigneDeCredit()) < montant){
            throw new SoldeInsuffisantException("Fonds insuffisants");
        }
        this.setSolde(this.getSolde() - montant);
    }





    public static double  calculerSommeSoldes(Courant compte1, Courant compte2){
        double s1 = compte1.getSolde() < 0 ? 0 : compte1.getSolde();
        double s2 = compte2.getSolde() < 0 ? 0 : compte2.getSolde();
        return s1 + s2;
    }


    @Override
    protected double calculInteret() {
        if (getSolde() > 0) {
            return getSolde() * 0.03;   // 3%
        } else {
            return getSolde() * 0.0975; // 9,75% (dette)
        }
    }

}
