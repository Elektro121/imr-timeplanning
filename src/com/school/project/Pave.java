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
    
    private TypeCours type;
    private Salle salle;
    private java.util.ArrayList<Intervenant> intervenants;
    private java.util.ArrayList<GroupeEtudiants> groupes;
    private PlageHoraire plage;
    private java.util.GregorianCalendar jour;
    private Matiere matiere;

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
    
    @Override
    public String toString() {
        String listeIntervenants = null;
        String listeGroupes = null;
        for(Intervenant i: intervenants) {
            listeIntervenants += i.nom;
            listeIntervenants += ";";
        }

        for (GroupeEtudiants g: groupes) {
            listeGroupes += g.nom;
            listeGroupes += ";";
        }
        
        return  "---- Pave ----\n" +
                type + " " + matiere + " en " + salle +
                "Le "+ jour.get(Calendar.DAY_OF_MONTH) + "/" + jour.get(Calendar.MONTH) + "/" + jour.get(Calendar.YEAR) + " à " + plage + "\n" +
                "Avec " +  listeIntervenants + "pour " + listeGroupes;
                
    }
}
