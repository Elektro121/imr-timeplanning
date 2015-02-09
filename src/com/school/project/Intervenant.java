package com.school.project;

/**
 * Created on 05/02/2015.
 */
public class Intervenant extends Ressource {
    public Intervenant(String nom) {
        super(nom);
    }

    @Override
    public String toString() {
        return "Intervenant " + nom;
    }
}
