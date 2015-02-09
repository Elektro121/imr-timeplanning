package com.school.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * Created on 05/02/2015.
 */
public class Contrainte {
    private ArrayList<PlageHoraire> plagesContraintes;
    private ArrayList<Integer> repetitionJoursSemaine;
    private GregorianCalendar jour;
    private Ressource ressourceContrainte;
    private String raison;

    private Contrainte(Ressource ressourceContrainte, String raison) {
        this.ressourceContrainte = ressourceContrainte;
        this.raison = raison;
    }

    private Contrainte(Ressource ressourceContrainte, String raison, Collection<PlageHoraire> plageContrainte) {
        this(ressourceContrainte, raison);
        this.plagesContraintes.addAll(plageContrainte);
    }


    /**
     * Création d'une contrainte d'une seule journée pour une ou plusieurs plages horaires.
     * @param ressourceContrainte Ressource contrainte (Matière, Prof, etc)
     * @param raison Raison de la contrainte
     * @param plageContrainte Collection de PlageHoraires ou la contrainte sera effective
     * @param jour Jour ou la contrainte sera effective
     */
    public Contrainte(Ressource ressourceContrainte, String raison, Collection<PlageHoraire> plageContrainte, GregorianCalendar jour) {
        this(ressourceContrainte, raison, plageContrainte);

        this.jour = jour;
        
        this.repetitionJoursSemaine = null;
    }

    /**
     * Création d'une contrainte d'un ou plusieurs jours de la semaine pour une ou plusieurs plages horaires.
     * @param ressourceContrainte Ressource contrainte (Matière, Prof, etc)
     * @param raison Raison de la contrainte
     * @param plageContrainte Collection de PlageHoraires ou la contrainte sera effective
     * @param repetitionJoursSemaine Tableau constitué des jours ou cette contrainte doit être répétée
     */
    public Contrainte(Ressource ressourceContrainte, String raison, Collection<PlageHoraire> plageContrainte, Collection<Integer> repetitionJoursSemaine) {
        this(ressourceContrainte, raison, plageContrainte);
        
        this.jour = null;
        
        this.repetitionJoursSemaine = new ArrayList<Integer>();
        this.repetitionJoursSemaine.addAll(repetitionJoursSemaine);
    }
}
