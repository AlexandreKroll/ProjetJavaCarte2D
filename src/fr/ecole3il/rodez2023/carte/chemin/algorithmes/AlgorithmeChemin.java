package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.List;

/**
 * Cette interface définit les méthodes nécessaires à tout algorithme de recherche de chemin.
 * Les classes implémentant cette interface doivent fournir une méthode pour trouver un chemin
 * dans un graphe et une méthode pour trouver un chemin sur une carte.
 *
 * @param <E> le type des éléments dans le graphe
 */
public interface AlgorithmeChemin<E> {
    
    /**
     * Trouve un chemin dans un graphe entre un nœud de départ et un nœud d'arrivée.
     *
     * @param graphe   le graphe dans lequel rechercher le chemin
     * @param depart   le nœud de départ du chemin
     * @param arrivee  le nœud d'arrivée du chemin
     * @return une liste de nœuds représentant le chemin trouvé
     */
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);

    /**
     * Trouve un chemin sur une carte entre deux points spécifiés par leurs coordonnées.
     *
     * @param carte la carte sur laquelle rechercher le chemin
     * @param x     la coordonnée x du point de départ
     * @param y     la coordonnée y du point de départ
     * @param x2    la coordonnée x du point d'arrivée
     * @param y2    la coordonnée y du point d'arrivée
     * @return le chemin trouvé sous forme d'un objet Chemin
     */
    Chemin trouverChemin(Carte carte, int x, int y, int x2, int y2);

}


