package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.List;

public class Noeud<E> {
    private E valeur;
    private List<Noeud<E>> voisins;

    // Constructeur
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    // Méthode pour obtenir la valeur du nœud
    public E getValeur() {
        return valeur;
    }

    // Méthode pour obtenir la liste des nœuds voisins
    public List<Noeud<E>> getVoisins() {
        return voisins;
    }

    // Méthode pour ajouter un nœud voisin
    public void ajouterVoisin(Noeud<E> voisin) {
        voisins.add(voisin);
    }
}
