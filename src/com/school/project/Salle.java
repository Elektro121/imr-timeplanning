package com.school.project;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created on 05/02/2015.
 */
public class Salle extends Ressource {

    /**
     * Enumeration de tout les types de Salles disponibles 
     */
    public enum TypeSalle {
        AMPHI("Amphithéatre"),
        CM_TD("Salle de CM-TD"),
        DS_TD("Salle de DS-TD"),
        TP("Salle de TP"),
        LANGUES("Laboratoire de langues");

        private String name = "";

        TypeSalle(String name) {
            this.name = name;
            
        }
        @Override
        public String toString() {
            return name;
        }
    }

    /**
     * Enumeration de tout les équipements possibles dans une salle.
     */
    public enum Equipements {
        TAB_CRAIE("Tableau pour craie"),
        TAB_MARQUEUR("Tableau pour marqueur"),
        VID_PROJ("Vidéo-Projecteur"),
        VISIOCONF("Equipement Visio-Conférence");
        
        private String name = "";

        Equipements(String name) {
            this.name = name;

        }
        @Override
        public String toString() {
            return name;
        }
        
    }
    TypeSalle type;
    boolean bComputersOnWindows = false;
    int capacity = 40;
    ArrayList<Equipements> equipements = null;

    /**
     * Constructeur de l'objet salle
     * @param nom Nom de la salle
     * @param type Type de salle (voir l'énumérateur ci-dessus)
     * @param capacity Capacité de la salle en personnes
     * @param equipements Equipements présents dans la salle
     */
    public Salle(String nom, TypeSalle type, int capacity, Collection<Equipements> equipements) {
        
        
        this(nom, type, capacity);

        this.equipements = new ArrayList<Equipements>();
        this.equipements.addAll(equipements);
        
    }

    /**
     * Constructeur de l'objet salle
     * @param nom Nom de la salle
     * @param type Type de salle (voir l'énumérateur ci-dessus)
     * @param capacity Capacité de la salle en personnes
     */
    public Salle(String nom, TypeSalle type, int capacity) {
        this(nom, type);
        this.capacity = capacity;
    }

    /**
     * Constructeur de l'objet salle
     * @param nom Nom de la salle
     * @param type Type de salle (voir l'énumérateur ci-dessus)
     */
    public Salle(String nom, TypeSalle type) {
        super(nom);
        this.type = type;
        this.capacity = 40;
        this.equipements = new ArrayList<Equipements>();
    }
}
