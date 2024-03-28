package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ecole3il.rodez2023.carte.elements.Case;

/**
 * Cette classe représente un graphe utilisé pour la recherche de chemins.
 * Un graphe est composé de nœuds reliés entre eux par des arêtes, qui représentent les connexions entre les éléments du graphe.
 *
 * @param <E> le type d'éléments associés aux nœuds du graphe
 */
public class Graphe<E> {
    private Map<Noeud<E>, Map<Noeud<E>,Double>> adjacence;

    /**
     * Constructeur par défaut qui initialise un nouveau graphe.
     */
    public Graphe() {
        adjacence = new HashMap<>();
    }

    /**
     * Ajoute un nœud au graphe s'il n'existe pas déjà.
     *
     * @param noeud le nœud à ajouter
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        if (!adjacence.containsKey(noeud)) {
            adjacence.put(noeud, new HashMap<>());
        }
    }

    /**
     * Ajoute une arête entre deux nœuds avec un coût spécifié.
     *
     * @param depart   le nœud de départ de l'arête
     * @param arrivee  le nœud d'arrivée de l'arête
     * @param cout     le coût de l'arête
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        adjacence.get(depart).put(arrivee, cout);
        // Pour un graphe non orienté, ajouter également l'arête dans l'autre sens :
        // adjacence.get(arrivee).add(new Arete<>(depart, cout));
    }

    /**
     * Obtient le coût de l'arête entre deux nœuds.
     *
     * @param depart   le nœud de départ de l'arête
     * @param arrivee  le nœud d'arrivée de l'arête
     * @return le coût de l'arête s'il existe, sinon Double.POSITIVE_INFINITY
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        if (adjacence.containsKey(depart) && adjacence.get(depart).containsKey(arrivee)) {
            return adjacence.get(depart).get(arrivee);
        }
        return Double.POSITIVE_INFINITY; // ou une valeur représentant l'absence de l'arête
    }

    /**
     * Obtient la liste des nœuds du graphe.
     *
     * @return la liste des nœuds du graphe
     */
    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(adjacence.keySet());
    }

    /**
     * Obtient la liste des nœuds voisins d'un nœud donné.
     *
     * @param noeud le nœud dont on veut obtenir les voisins
     * @return la liste des nœuds voisins de celui donné
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (adjacence.containsKey(noeud)) {
            return new ArrayList(adjacence.get(noeud).keySet());
        }
        return new ArrayList();
    }

    /**
     * Obtient le nœud du graphe situé aux coordonnées spécifiées.
     *
     * @param x la coordonnée x du nœud
     * @param y la coordonnée y du nœud
     * @return le nœud situé aux coordonnées spécifiées, ou null s'il n'existe pas
     */
    public Noeud<E> getNoeud(int x, int y) {
        for (Noeud<E> noeud : this.getNoeuds()) {
            Case caseActuelle = (Case) noeud.getValeur();
            if (caseActuelle.getX() == x && caseActuelle.getY() == y) {
                return noeud;
            }
        }
        return null;
    }
}


