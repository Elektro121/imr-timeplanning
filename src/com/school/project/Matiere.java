package com.school.project;

/**
 * Created on 05/02/2015.
 */
public class Matiere extends Ressource {
    public Matiere(String nom) {
        super(nom);
        
    }

    @Override
    public String toString() {
        return "Matiere " + nom;
    }
}
