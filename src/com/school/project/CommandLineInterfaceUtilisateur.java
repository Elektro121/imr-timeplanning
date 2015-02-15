package com.school.project;

import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Created on 31/01/2015.
 */
public class CommandLineInterfaceUtilisateur {
    static int executionDuProgramme = 1;
    static Calendrier cal;
    public static void main(String[] args) {
        while(true) {
            Integer reponse = Integer.parseInt(prompt("1. Lancer les tests\n" +
                    "2. Lancer un nouveau calendrier\n" +
                    "3. Quitter"));
            if ( reponse == 1) {
                Calendrier.main(new String[0]);
            }
            else if ( reponse == 2 ) {
                nouveauCalendrier();
            }
            else {
                break;
            }
        }
    }
    
    public static String prompt(String choix) {
        Scanner entree = new Scanner(System.in);
        System.out.println(choix);
        System.out.println("Entree :");
        return entree.next();
        
    }
    
    public static void nouveauCalendrier() {
        cal = new Calendrier();
        boolean fin = false;
        while(!fin) {
            Integer reponse = Integer.parseInt(prompt(  
                    "1. Creer un objet\n" + 
                    "2. Lister tout les pavés pour un enseignant\n" +
                    "3. Lister les pavés pour un enseignant étant donné la matière et la promotion \n" +
                    "4. Lister les pavés pour une salle donnée\n" + 
                    "5. Quitter"));
            switch (reponse) {
                case 1:
                    creerObjet();
                    break;
                case 2:
                    listePaveEnseignant();
                    break;
                case 3:
                    listePaveEnseignantPlus();
                    break;
                case 4:
                    listePaveSalle();
                    break;
                case 5:
                    fin = true;
                    break;
            }
        }
        
    }

    private static void creerObjet() {
        boolean fin = false;
        while(!fin) {
            Integer reponse = Integer.parseInt(prompt(
                    "1. Intervenant\n" +
                    "2. Salle\n" +
                    "3. Groupe \n" +
                    "4. Matière\n" +
                    "5. Contrainte\n" +
                    "6. Pave \n" +
                    "7. Quitter \n")
            );
            switch (reponse) {
                case 1:
                    creerIntervenant();
                    break;
                case 2:
                    creerSalle();
                    break;
                case 3:
                    creerGroupe();
                    break;
                case 4:
                    creerMatiere();
                    break;
                case 5:
                    creerContrainte();
                    break;
                case 6:
                    creerPave();
                    break;
                case 7:
                    fin = true;
                    break;
            }
        }
    }

    private static void creerContrainte() {
        System.out.println("Interface utilisateur pas encore implémentée");
    }
    
    private static void creerPave() {
        System.out.println("Interface utilisateur pas encore implémentée");
    }
    
    private static void creerSalle() {
        System.out.println("Interface utilisateur pas encore implémentée");
    }

    private static void creerGroupe() {
        cal.addGroupeEtudiant(
                prompt("Donnez le nom du groupe"),
                Integer.parseInt(prompt("Donnez l'année de promotion"))
                );
    }

    private static void creerIntervenant() {
        cal.addIntervenant(prompt("Donnez le nom de l'intervenant"));
    }

    private static void creerMatiere() {
        cal.addMatieres(prompt("Donnez le nom de la matière"));
    }    

    

    private static void listePaveEnseignant() {
        cal.listeDesPaves(
                cal.getIntervenant(prompt("Donnez le nom de l'intervenant"))
        );
    }

    private static void listePaveEnseignantPlus() {
        cal.listeDesPaves(
                cal.getIntervenant(prompt("Donnez le nom de l'intervenant")),
                cal.getMatiere(prompt("Donnez le nom de la matière")),
                Integer.parseInt(prompt("Donnez l'année de promotion"))
        );
    }

    private static void listePaveSalle() {
        cal.listeDesPaves(cal.getSalle(prompt("Donnez le nom de la salle")));
    }
}
