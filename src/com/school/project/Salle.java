package com.school.project;

/**
 * Created on 05/02/2015.
 */
public class Salle extends Ressource {
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
    boolean bComputersOnWindows;
    int capacity;
    
}
