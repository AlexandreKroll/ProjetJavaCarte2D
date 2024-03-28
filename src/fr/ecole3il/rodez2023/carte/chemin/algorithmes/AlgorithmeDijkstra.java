package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.*;

/**
 * Cette classe implémente l'algorithme de Dijkstra pour trouver un chemin dans un graphe.
 * Cet algorithme détermine le chemin le plus court entre un nœud de départ et un nœud d'arrivée.
 *
 * @param <E> le type des éléments dans le graphe
 */
public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    /**
     * Trouve un chemin dans un graphe entre un nœud de départ et un nœud d'arrivée en utilisant l'algorithme de Dijkstra.
     *
     * @param graphe   le graphe dans lequel rechercher le chemin
     * @param depart   le nœud de départ du chemin
     * @param arrivee  le nœud d'arrivée du chemin
     * @return une liste de nœuds représentant le chemin trouvé
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(couts::get));
        Set<Noeud<E>> dejaVus = new HashSet<>();

        // Initialisation des structures de données
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        couts.put(depart, 0.0);
        filePriorite.add(depart);

        // Exploration des noeuds
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();
            if (noeudActuel.equals(arrivee)) {
                break; // Arrêt si on atteint le noeud d'arrivée
            }
            if (dejaVus.contains(noeudActuel)) {
                continue; // Évite de revisiter un noeud déjà traité
            }
            dejaVus.add(noeudActuel);

            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                double nouveauCout = couts.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                if (nouveauCout < couts.get(voisin)) {
                    couts.put(voisin, nouveauCout);
                    predecesseurs.put(voisin, noeudActuel);
                    filePriorite.add(voisin);
                }
            }
        }

        // Reconstruction du chemin le plus court
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            chemin.add(noeud);
            noeud = predecesseurs.get(noeud);
        }
        Collections.reverse(chemin);
        return chemin;
    }

    /**
     * Cette méthode n'est pas implémentée pour cet algorithme car il fonctionne uniquement sur des graphes et non des cartes.
     *
     * @param carte la carte sur laquelle rechercher le chemin
     * @param x     la coordonnée x du point de départ
     * @param y     la coordonnée y du point de départ
     * @param x2    la coordonnée x du point d'arrivée
     * @param y2    la coordonnée y du point d'arrivée
     * @return null
     */
    @Override
    public Chemin trouverChemin(Carte carte, int x, int y, int x2, int y2) {
        return null;
    }
}
