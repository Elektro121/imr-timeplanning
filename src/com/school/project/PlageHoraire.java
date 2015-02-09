package com.school.project;

/**
 * Created on 05/02/2015.
 */
public enum PlageHoraire {
    P_8H15_10H15("8h15-10h15"),
    P_10H30_12H30("10h30-12h30"),
    P_14H00_16H00("14h00-16h00"),
    P_16H15_18H15("16h15-18h15");
    
    private String nom;
    PlageHoraire(String nom){
        this.nom = nom;
        
    }

    @Override
    public String toString() {
        return nom;
    }
}
