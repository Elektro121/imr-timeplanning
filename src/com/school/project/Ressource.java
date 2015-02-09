package com.school.project;

/**
 * Created on 05/02/2015.
 */
public abstract class Ressource {
    String nom;
    
    public Ressource(String nom) {
        this.nom = nom;
        
    }

    public boolean equals(Ressource o) {
        return (this.nom == o.nom);
    }
}