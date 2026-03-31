package org.example.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Banque {
    private String nom;
    private Map<String, Courant> comptes;

    public Banque(String nom) {
        this.nom = nom;
        this.comptes = new HashMap<>();
    }

    public Banque(String nom, Map<String, Courant> comptes) {
        this.nom = nom;
        this.comptes = comptes;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Map<String, Courant> getComptes() {
        return comptes;
    }

    public void setComptes(Map<String, Courant> comptes) {
        this.comptes = comptes;
    }

    public void ajouter(Courant courant) {
        comptes.put(courant.getNumero(), courant);
    }

    public void supprimer(String numero) {
        comptes.remove(numero);
    }

    public Courant getCompte(String numero) {
        return comptes.get(numero);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Banque banque = (Banque) o;
        return Objects.equals(getNom(), banque.getNom()) && Objects.equals(getComptes(), banque.getComptes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNom(), getComptes());
    }

    @Override
    public String toString() {
        return "Banque{" +
                "nom='" + nom + '\'' +
                ", comptes=" + comptes +
                '}';
    }



    public double avoirDesComptes(Personne titulaire){
        double total = 0;
        for (Courant compte : comptes.values()){
            if (compte.getTitulaire().equals(titulaire)){
                total += compte.getSolde() < 0 ? 0 : compte.getSolde();

            }
        }
        return total;
    }

}