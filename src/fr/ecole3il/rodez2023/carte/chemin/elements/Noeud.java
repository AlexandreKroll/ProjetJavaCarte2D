package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente un nœud dans un graphe.
 *
 * @param <E> le type de la valeur associée au nœud
 */
public class Noeud<E> {
    private E valeur; // La valeur associée au nœud
    private List<Noeud<E>> voisins; // La liste des nœuds voisins

    /**
     * Constructeur de la classe Noeud.
     *
     * @param valeur la valeur associée au nœud
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    /**
     * Méthode pour obtenir la valeur du nœud.
     *
     * @return la valeur du nœud
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Méthode pour obtenir la liste des nœuds voisins.
     *
     * @return la liste des nœuds voisins
     */
    public List<Noeud<E>> getVoisins() {
        return voisins;
    }

    /**
     * Méthode pour ajouter un nœud voisin.
     *
     * @param voisin le nœud voisin à ajouter
     */
    public void ajouterVoisin(Noeud<E> voisin) {
        voisins.add(voisin);
    }
}
