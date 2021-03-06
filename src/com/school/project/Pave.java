package com.school.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * Created on 05/02/2015.
 */
public class Pave {
    
    public enum TypeCours {
        CM("Cours Magistral"),
        TD("Travaux Dirigés"),
        TP("Travaux Pratiques"),
        PROJET("Projet"),
        DS("Devoir Surveillé");

        private String name = "";

        TypeCours(String name) {
            this.name = name;

        }
        @Override
        public String toString() {
            return name;
        }
    }
    
    TypeCours type;
    Salle salle;
    java.util.ArrayList<Intervenant> intervenants;
    java.util.ArrayList<GroupeEtudiants> groupes;
    PlageHoraire plage;
    java.util.GregorianCalendar jour;
    Matiere matiere;

    private Pave(TypeCours type, Matiere matiere, PlageHoraire plage, GregorianCalendar jour,
                 Salle salle) {

        this.type = type;

        this.matiere = matiere;

        this.plage = plage;

        this.jour = jour;

        this.salle = salle;

    }
    
    public Pave(TypeCours type, Matiere matiere, PlageHoraire plage, GregorianCalendar jour,
                Salle salle, Collection<Intervenant> plusieursIntervenants, Collection<GroupeEtudiants> plusieursGroupesEtudiants) {
        this(type, matiere, plage, jour, salle);

        this.intervenants = new ArrayList<Intervenant>();
        this.intervenants.addAll(plusieursIntervenants);

        this.groupes = new ArrayList<GroupeEtudiants>();
        this.groupes.addAll(plusieursGroupesEtudiants);
    }

    public Pave(TypeCours type, Matiere matiere, PlageHoraire plage, GregorianCalendar jour,
                Salle salle, Intervenant intervenant, GroupeEtudiants groupeEtudiants) {
        this(type, matiere, plage, jour, salle);

        this.intervenants = new ArrayList<Intervenant>();
        this.intervenants.add(intervenant);

        this.groupes = new ArrayList<GroupeEtudiants>();
        this.groupes.add(groupeEtudiants);
    }

    /**
     * Vérifie si la ressource sélectionnée par son nom est présente 
     * @param nom
     * @return
     */
    public boolean ressourceEstPresente(String nom) {
        // On regarde la salle
        if(nom == salle.nom) {
            return true;
        }
        // Itération de tout les intervenants du pavé
        for (Intervenant i:this.intervenants) {
            if (nom == i.nom) {
                return true;
            }
        }
        // Itération de tout les groupes du pavé
        for (GroupeEtudiants g:this.groupes) {
            if (nom == g.nom) {
                return true;
            }
        }
        // Si aucune des conditions n'ont pas fait retourné une valeur, on retourne false
        return false;
    }

    /**
     * Fonction qui vérifie qu'aucun objets dans le pavé n'est nul
     * @return
     */
    public boolean verifNull() {
        boolean bPavePossedePasDeNull = true;

        if(this.salle == null) {
            bPavePossedePasDeNull = false;
            return bPavePossedePasDeNull;
        }

        if (this.jour == null) {
            bPavePossedePasDeNull = false;
            return bPavePossedePasDeNull;
        }

        for(Intervenant i: this.intervenants) {
            if (i == null) {
                bPavePossedePasDeNull = false;
                return bPavePossedePasDeNull;
            }
        }

        for(GroupeEtudiants g: this.groupes) {
            if (g == null) {
                bPavePossedePasDeNull = false;
                return bPavePossedePasDeNull;
            }
        }

        return bPavePossedePasDeNull;
    }
    @Override
    public String toString() {
        String listeIntervenants = "";
        String listeGroupes = "";
        String jour = "";
        
        for(Intervenant i: intervenants) {
            listeIntervenants += i.nom;
            listeIntervenants += "; ";
        }
        listeIntervenants = listeIntervenants.substring(0, listeIntervenants.length()-2);

        for (GroupeEtudiants g: groupes) {
            listeGroupes += g.nom;
            listeGroupes += "; ";
        }
        listeGroupes = listeGroupes.substring(0, listeGroupes.length()-2);
        
        switch (this.jour.get(GregorianCalendar.DAY_OF_WEEK)) {
            case GregorianCalendar.MONDAY:
                jour = "lundi";
                break;
            case GregorianCalendar.TUESDAY:
                jour = "mardi";
                break;
            case GregorianCalendar.WEDNESDAY:
                jour = "mercredi";
                break;
            case GregorianCalendar.THURSDAY:
                jour = "jeudi";
                break;
            case GregorianCalendar.FRIDAY:
                jour = "vendredi";
                break;
            case GregorianCalendar.SATURDAY:
                jour = "samedi";
                break;
            case GregorianCalendar.SUNDAY:
                jour = "dimanche";
                break;
        }
        
        return  type + " de " + matiere + " en " + salle + "\n" +
                "Le " + jour + " " + this.jour.get(Calendar.DAY_OF_MONTH) + "/" + (this.jour.get(Calendar.MONTH)+1) + "/" + this.jour.get(Calendar.YEAR) + " à " + plage + "\n" +
                "Avec " +  listeIntervenants + " pour " + listeGroupes;
                
    }
}
