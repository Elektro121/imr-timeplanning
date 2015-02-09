package com.school.project;

import java.util.ArrayList;
import java.util.Calendar;
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

    @Override
    public String toString() {
        String resultat=null;
        resultat += "Contrainte de" + ressourceContrainte + "pour les horaires suivants :\n";
        for(PlageHoraire p:plagesContraintes) {
            resultat += p;
            resultat += "\n";
        }
        if(jour == null) {
            resultat += "Pour les périodes suivantes : \n";
            for (Integer i : repetitionJoursSemaine) {
                switch (i) {
                    case GregorianCalendar.MONDAY:
                        resultat += "Lundi";
                        break;
                    case GregorianCalendar.TUESDAY:
                        resultat += "Mardi";
                        break;
                    case GregorianCalendar.WEDNESDAY:
                        resultat += "Mercredi";
                        break;
                    case GregorianCalendar.THURSDAY:
                        resultat += "Jeudi";
                        break;
                    case GregorianCalendar.FRIDAY:
                        resultat += "Vendredi";
                        break;
                    case GregorianCalendar.SATURDAY:
                        resultat += "Samedi";
                        break;
                    case GregorianCalendar.SUNDAY:
                        resultat += "Dimanche";
                        break;
                }
                resultat += "\n";
            }
        }
        else {
                resultat += "Le "+ jour.get(Calendar.DAY_OF_MONTH) + "/" + jour.get(Calendar.MONTH) + "/" + jour.get(Calendar.YEAR) +"\n";
            }
        return resultat;
    }
    
    public boolean verifContrainte(Pave paveAVerifier) {
        if (this.repetitionJoursSemaine == null) {
            if ((this.jour.equals(paveAVerifier.jour)) {
                for(PlageHoraire plageContrainte: this.plagesContraintes) {
                    if(plageContrainte == paveAVerifier.plage) {
                        return false;
                    }
                }
            }
        }
        if (this.jour == null) {
            for(Integer jourBloque: repetitionJoursSemaine) {
                if((jourBloque == this.jour.get(GregorianCalendar.DAY_OF_WEEK))) {
                    for(PlageHoraire plageContrainte: this.plagesContraintes) {
                        if(plageContrainte == paveAVerifier.plage) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    
}
