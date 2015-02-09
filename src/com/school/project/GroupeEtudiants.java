package com.school.project;

/**
 * Created on 05/02/2015.
 */
public class GroupeEtudiants extends Ressource {
    int promotion;
    String groupe;

    public GroupeEtudiants(String nom, int promotion, String groupe) {
        this(nom, promotion);
        this.groupe = groupe;
        
    }

    public GroupeEtudiants(String nom, int promotion) {
        super(nom);
        this.promotion = promotion;
    }
}
