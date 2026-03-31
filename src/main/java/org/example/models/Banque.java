package org.example.models;

import java.util.HashMap;
import java.util.Map;

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
}