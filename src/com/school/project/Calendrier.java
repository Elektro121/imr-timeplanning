package com.school.project;

import java.util.*;

/**
 * Created on 05/02/2015.
 */
public class Calendrier {
    //Ces données peuvent être parcourus comme on le souhaite
    //Ici je met en palce des listes
    ArrayList<Contrainte> listeContraintes;
    ArrayList<Pave> listePaves;
    //Ces données doivent ppouvoir être uniques et accessibles facilement
    // Ici je met des dictionnaires avec pour index le nom de l'objet
    HashMap<String, Intervenant> listeIntervenants;
    HashMap<String, Salle> listeSalles;
    HashMap<String, Matiere> listeMatieres;
    HashMap<String, GroupeEtudiants> listeGroupes;

    /**
     * Contructeur qui initialise l'objet calendrier 
     */
    public Calendrier() {
        listeIntervenants = new HashMap<String, Intervenant>();
        listeGroupes = new HashMap<String, GroupeEtudiants>();
        listeMatieres = new HashMap<String, Matiere>();
        listeSalles = new HashMap<String, Salle>();
        listePaves = new ArrayList<Pave>();
        listeContraintes = new ArrayList<Contrainte>();
    }

    /*
     * Liste d'ajouteurs
     */
    
    public void addSalle(String nom, Salle.TypeSalle type) {
        this.listeSalles.put(nom, new Salle(nom, type));
    }
    
    public void addSalle(String nom, Salle.TypeSalle type, int nbPersonnes) {
        this.listeSalles.put(nom, new Salle(nom, type, nbPersonnes));
    }

    public void addIntervenant(String nom) {
        this.listeIntervenants.put(nom, new Intervenant(nom));
    }

    public void addMatieres(String nom) {
        this.listeMatieres.put(nom, new Matiere(nom));
    }
    
    public void addGroupeEtudiant(String nom, int promotion) {
        this.listeGroupes.put(nom, new GroupeEtudiants(nom, promotion));
    }
    
    public void addGroupeEtudiant(String nom, int promotion, String groupe) {
        this.listeGroupes.put(nom, new GroupeEtudiants(nom, promotion, groupe));
    }

    /**
     * Fonction qui vérifie que le pavé n'entre pas en conflit avec les contraintes mises en place 
     * @param paveAVerifier
     * @return
     */
    public boolean verifContraintes(Pave paveAVerifier) {
        boolean bPaveEstSansContraintes = true;
        for(Contrainte c: listeContraintes) {
           if (!(c.verifContrainte(paveAVerifier)))
               bPaveEstSansContraintes = false;
        }
        return bPaveEstSansContraintes;
    }

    /**
     * Fonction qui vérifie si le pavé n'est pas un doublon dans la liste 
     * @param paveAVerifier
     * @return
     */
    public boolean verifDoublon(Pave paveAVerifier) {
        //On part du principe que le pavé n'est pas un doublon
        boolean bPaveNestPasDoublon = true; 
        for (Pave p: listePaves) { //On fait une boucle sur chacuns des pavés de la liste
            if ((paveAVerifier.jour.equals(p.jour)) && (p.plage == paveAVerifier.plage)) { //Si le jour est égal ainsi que la plage horaire
                if(paveAVerifier.salle == p.salle) { //Si le cours se passe dans la même salle
                    System.out.println("Une salle ne peut être occupée deux fois au même moment !");
                    bPaveNestPasDoublon = false;
                    //On sait déjà qu'il est un doublon
                    //On retourne l'information au plus vite
                    return bPaveNestPasDoublon;
                }
                for(Intervenant i: paveAVerifier.intervenants) { // On boucle les intervenants des deux pavés
                    for (Intervenant j: p.intervenants) {
                        if(i == j) { // Si un intervenant est sur deux pavés en même temps
                            System.out.println("Un intervenant ne peut être à deux cours en même temps !");
                            bPaveNestPasDoublon = false;
                            //On sait déjà qu'il est un doublon
                            //On retourne l'information au plus vite
                            return bPaveNestPasDoublon;
                        }
                    }
                }
                for(GroupeEtudiants e: paveAVerifier.groupes) { // On boucle les intervenants des deux pavés
                    for (GroupeEtudiants f: p.groupes) {
                        if(e == f) { // Si un étudiant est sur deux pavés en même temps
                            System.out.println("Un groupe d'étudiants ne peut être à deux cours en même temps !");
                            bPaveNestPasDoublon = false;
                            //On sait déjà qu'il est un doublon
                            //On retourne l'information au plus vite
                            return bPaveNestPasDoublon;
                        }
                    }
                }
            }
        }
        //Si on a tout vérifié, c'est ok
        return bPaveNestPasDoublon;
    }

    /**
     *  
     * @param type Type du cours
     * @param matiere Matière du cours
     * @param plage Plage horaire du cours
     * @param jour Jour du cours
     * @param salle Salle ou se fait le cours
     * @param plusieursIntervenants Liste des intervenants
     * @param plusieursGroupesEtudiants Liste des groupes à assister à ce cours
     */
    public void addPave(Pave.TypeCours type, Matiere matiere, PlageHoraire plage, GregorianCalendar jour,
                        Salle salle, Collection<Intervenant> plusieursIntervenants, Collection<GroupeEtudiants> plusieursGroupesEtudiants) {
        Pave newPave = new Pave(type, matiere, plage, jour, salle, plusieursIntervenants, plusieursGroupesEtudiants);
        if(newPave.verifNull()) {
            if(this.verifContraintes(newPave)) {
                if(this.verifDoublon(newPave)){
                    this.listePaves.add(newPave);
                }
                else {
                    System.out.println("Impossible de créer un pavé : des ressources sont déjà occupés pour d'autres pavés");
                }
            }
            else {
                System.out.println("Impossible de créer un pavé : contraintes sur le pavé que l'on souhaite de créer.");
            }
        }
        else {
            System.out.println("Impossible de créer un pavé : un des paramêtres est nul.");
        }
        
    }

    /**
     * Fonction addPave qui appelle la méthode du même nom 
     * @param type
     * @param matiere
     * @param plage
     * @param jour
     * @param salle
     * @param intervenant Intervenant à faire ce cours
     * @param groupeEtudiant Groupe à assister à ce cours
     */
    public void addPave(Pave.TypeCours type, Matiere matiere, PlageHoraire plage, GregorianCalendar jour,
                        Salle salle, Intervenant intervenant, GroupeEtudiants groupeEtudiant) {
        
        ArrayList<Intervenant> plusieursIntervenants = new ArrayList<Intervenant>();
        plusieursIntervenants.add(intervenant);
        ArrayList<GroupeEtudiants> plusieursGroupeEtudiants = new ArrayList<GroupeEtudiants>();
        plusieursGroupeEtudiants.add(groupeEtudiant);
        
        addPave(type, matiere, plage, jour, salle, plusieursIntervenants, plusieursGroupeEtudiants);
    }
    
    /**
     * Ajoute une contrainte dans le calendrier 
     * @param ressourceContrainte l'objet de la ressource contrainte
     * @param raison la raison de la contrainte
     * @param plageContrainte les plage contraintes
     * @param jour le jour de la contrainte
     */
    public void addContrainte(Ressource ressourceContrainte, String raison, Collection<PlageHoraire> plageContrainte, GregorianCalendar jour) {
        this.listeContraintes.add(new Contrainte(ressourceContrainte, raison, plageContrainte, jour));
    }

    /**
     * Ajoute une contrainte dans le calendrier 
     * @param ressourceContrainte l'objet de la ressource contrainte
     * @param raison la raison de la contrainte
     * @param plageContrainte les plage contraintes
     * @param repetitionJoursSemaine la répétition dans la journée ou la semaine
     */
    public void addContrainte(Ressource ressourceContrainte, String raison, Collection<PlageHoraire> plageContrainte, Collection<Integer> repetitionJoursSemaine) {
        this.listeContraintes.add(new Contrainte(ressourceContrainte, raison, plageContrainte, repetitionJoursSemaine));
    }

    /**
     * Récupère l'objet de la Hastable
     * @param nom
     * @return
     */
    public Salle getSalle(String nom) {
        return this.listeSalles.get(nom);
    }

    /**
     * Récupère l'objet de la Hastable
     * @param nom
     * @return
     */
    public Intervenant getIntervenant(String nom) {
        return this.listeIntervenants.get(nom);
    }
    
    /**
     * Récupère l'objet de la Hastable
     * @param nom
     * @return
     */
    public GroupeEtudiants getGroupeEtudiants(String nom) {
        return this.listeGroupes.get(nom);
    }

    /**
     * Récupère l'objet de la Hastable
     * @param nom
     * @return
     */
    public Matiere getMatiere(String nom) {
        return this.listeMatieres.get(nom);
    }

    /**
     * Fonction qui affiche le pavé sélectioné
     * @param i index de la liste listePaves
     */
    public void showPave(int i){
        System.out.println("[Pave]------------["+i+"]");
        System.out.println(listePaves.get(i));
    }

    /**
     * Liste les pavés selon l'intervenant
     * @param intervenant Intervenant sélectionné
     */
    public void listeDesPaves(Intervenant intervenant) {
        Pave paveSelectionne;
        for (int i = 0; i < listePaves.size(); i++) {
            paveSelectionne = listePaves.get(i);
            for(Intervenant intervenantListe:paveSelectionne.intervenants) {
                if (intervenant == intervenantListe) {
                    this.showPave(i);
                    break;
                }
            } 
        }
    }

    /**
     * Liste les pavés en fonction de l'intervenant, de la matière et de la promotion 
     * @param intervenant Intervenant sélectionné
     * @param matiere Matière sélectionnée
     * @param promotion Pomotion sélectionnée
     */
    public void listeDesPaves(Intervenant intervenant, Matiere matiere, int promotion) {
        Pave paveSelectionne;
        //Nous avons besoin de l'index de chaque pavé, d'ou la boucle for
        for (int i = 0; i < listePaves.size(); i++) {
            paveSelectionne = listePaves.get(i);
            //Première itération pour vérifier l'intervenant
            for (Intervenant intervenantSelectionne : paveSelectionne.intervenants) {
                if (intervenant == intervenantSelectionne) {
                    //Seconde itération pour vérifier la promotion des groupes
                    for (GroupeEtudiants etudiantsSelectionne : paveSelectionne.groupes) {
                        if (promotion == etudiantsSelectionne.promotion) {
                            //Si les deux conditions sont respectés, vérifier la matière
                            if (matiere == paveSelectionne.matiere) {
                                this.showPave(i);
                            }
                            //Nous n'avons pas besoin d'afficher deux fois le même pavé
                            //Si les deux groupes d'étudiants ont une année identique
                            break;
                        }
                    }
                    //Il n'est pas possible d'avoir deux fois le même intervenant sur un même pavé
                    //On s'arrête donc dès que l'on a trouvé la première occurence
                    break;
                }
            }
        }
    }

    /**
     * Liste les pavés en fonction du groupe d'étudiant et d'une plage temporelle
     * @param etudiants Groupe d'étudiants à sélectionner
     * @param debut GregorianCalendar du point de début de la sélection
     * @param fin GregorianCalendar du point de fin de la sélection
     */
    public void listeDesPaves(GroupeEtudiants etudiants, GregorianCalendar debut, GregorianCalendar fin) {
        Pave paveSelectionne;
        //Nous avons besoin de l'index de chaque pavé, d'ou la boucle for
        for (int i = 0; i < this.listePaves.size() ; i++) {
            paveSelectionne = listePaves.get(i);
            //Si le jour du pavé est compris entre le début et la fin
            if (paveSelectionne.jour.after(debut) && paveSelectionne.jour.before(fin)) {
                //Intération des groupes du pavé sélectionné
                for (GroupeEtudiants groupeSelectionne:paveSelectionne.groupes) {
                    if(groupeSelectionne == etudiants) {
                        this.showPave(i);
                        //On sait qu'il n'y a qu'une occurence possible d'un groupe d'étudiants dans un pavé
                        //On a pas besoin de regarder les autres groupes
                        break;
                    }
                }
            }
        }
        
    }

    /**
     * Liste les pavés en fonction de la salle
     * @param salle 
     */
    public void listeDesPaves(Salle salle) {
        Pave paveSelectionne;
        //Nous avons besoin de l'index de chaque pavé, d'ou la boucle for
        for (int i = 0; i < listePaves.size(); i++) {
            paveSelectionne = listePaves.get(i);
            //Simple vérification
            if (salle == paveSelectionne.salle) {
                this.showPave(i);
                //Il est nécessaire de parcourir toute la liste des pavés
            }
        }
    }

    /**
     * Ajoute un intervenant au pavé à modifier
     * @param paveAModifier Pavé à modifier
     * @param intervenant Intervenant à ajouter
     */
    public void ajouterIntervenant(Pave paveAModifier, Intervenant intervenant ) {
        /*
         * On utilise la méthode ajouterIntervenant qui prend en paramêtre une liste d'intervenants
         */
        ArrayList<Intervenant> plusieursIntervenants = new ArrayList<Intervenant>();
        plusieursIntervenants.add(intervenant);
        ajouterIntervenant(paveAModifier, plusieursIntervenants);
    }
    
    /**
     * Ajoute plusieurs intervenants au pavé à modifier
     * @param paveAModifier Pavé à modifier
     * @param plusieursIntervenant Intervenants à ajouter
     */
    public void ajouterIntervenant(Pave paveAModifier, Collection<Intervenant> plusieursIntervenant ) {
        /**
         * Boucle ou l'on vérifie si il a pas deux fois l'intervenant avant de le rajouter 
         */
        for (Intervenant i:paveAModifier.intervenants) {
            for(Intervenant j:plusieursIntervenant) {
                if( i != j ) {
                    paveAModifier.intervenants.add(j);
                }
            }
        }
    }

    /**
     * Fonction qui permet de tester la classe Calendrier 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("START");
        Calendrier cal = new Calendrier();
        cal.addGroupeEtudiant("IMR1-RT", 2014, "IMR");
        cal.addGroupeEtudiant("IMR2", 2015, "IMR");
        cal.addIntervenant("M. ALAIN");
        cal.addIntervenant("M. ROCACHER");
        cal.addIntervenant("Mme BODROS");
        cal.addSalle("102H IMR", Salle.TypeSalle.CM_TD);
        cal.addSalle("126C", Salle.TypeSalle.AMPHI, 140);
        cal.addMatieres("Algorithme");
        cal.addMatieres("Systèmes d'Exploitation");
        cal.addMatieres("Anglais");
        cal.addPave(Pave.TypeCours.CM, cal.getMatiere("Algorithme"),
                PlageHoraire.P_8H15_10H15,
                new GregorianCalendar(2014, 11, 12),
                cal.getSalle("126C"),
                cal.getIntervenant("M. ROCACHER"),
                cal.getGroupeEtudiants("IMR1-RT"));
        cal.listeDesPaves(cal.getSalle("126C"));
        
        System.out.println("-----------------------------------------");
        
        cal.addPave(Pave.TypeCours.TD, cal.getMatiere("Systèmes d'Exploitation"),
                PlageHoraire.P_8H15_10H15,
                new GregorianCalendar(2014, 11, 12),
                cal.getSalle("102H IMR"),
                cal.getIntervenant("M. ALAIN"),
                cal.getGroupeEtudiants("IMR2"));
        cal.addPave(Pave.TypeCours.PROJET, cal.getMatiere("Anglais"),
                PlageHoraire.P_10H30_12H30,
                new GregorianCalendar(2014, 11, 12),
                cal.getSalle("102H IMR"),
                cal.getIntervenant("Mme BODROS"),
                cal.getGroupeEtudiants("IMR1-RT"));
        
        System.out.println("-----------------------------------------");
        
        cal.listeDesPaves(cal.getSalle("102H IMR"));
        
        System.out.println("-----------------------------------------");
        
        cal.listeDesPaves(cal.getIntervenant("Mme BODROS"));

        cal.addContrainte(cal.getIntervenant("Mme BODROS"),
                "Cours à l'IUT",
                new ArrayList<PlageHoraire>(Arrays.asList(PlageHoraire.P_14H00_16H00, PlageHoraire.P_16H15_18H15)),
                new ArrayList<Integer>(Arrays.asList(GregorianCalendar.THURSDAY, GregorianCalendar.TUESDAY)));
        System.out.println(cal.listeContraintes.get(0));

        cal.addPave(Pave.TypeCours.PROJET, cal.getMatiere("Anglais"),
                PlageHoraire.P_14H00_16H00,
                new GregorianCalendar(2015, 2, 12),
                cal.getSalle("102H IMR"),
                cal.getIntervenant("Mme BODROS"),
                cal.getGroupeEtudiants("IMR1-RT"));
        
        cal.listeDesPaves(cal.getIntervenant("Mme BODROS"));
        
    }
    
}
