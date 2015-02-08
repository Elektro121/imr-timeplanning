package com.school.project;

/**
 * Created on 05/02/2015.
 */
public class Pave {
    public enum TypeCours {
        CM, TD, TP, PROJET, DS,
    }
    private TypeCours type;
    private Salle salle;
    private java.util.ArrayList<Intervenant> intervenants;
    private java.util.ArrayList<GroupeEtudiants> groupes;
    private PlageHoraire plage;
    private java.util.GregorianCalendar jour;
    private Matiere matiere;
}
