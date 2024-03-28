package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.*;

/**
 * Cette classe représente l'algorithme A* pour trouver un chemin dans un graphe.
 * L'algorithme A* utilise une recherche heuristique pour trouver le chemin le plus court entre deux points.
 * Il est efficace pour les graphes de grande taille.
 *
 * @param <E> le type des éléments dans le graphe
 */
public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {
    private Noeud<E> arrivee; // Noeud d'arrivée

    /**
     * Constructeur de la classe AlgorithmeAEtoile.
     *
     * @param arrivee le noeud d'arrivée pour le chemin
     */
    public AlgorithmeAEtoile(Noeud<E> arrivee) {
        this.arrivee = arrivee;
    }

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Double> coutsActuel = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>((n1, n2) -> (int) (couts.get(n1) - coutsActuel.get(n2)));
        Set<Noeud<E>> dejaVus = new HashSet<>();

        // Initialisation des structures de données
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
            coutsActuel.put(noeud, Double.POSITIVE_INFINITY);
            predecesseurs.put(noeud, null);
        }
        couts.put(depart, 0.0);
        filePriorite.add(depart);

        // Boucle principale
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudActuel = filePriorite.poll();
            if (noeudActuel.equals(this.arrivee)) {
                break; // Arrêt si on atteint le noeud d'arrivée
            }
            if (dejaVus.contains(noeudActuel)) {
                continue; // Évite de revisiter un noeud déjà traité
            }
            dejaVus.add(noeudActuel);

            // Mise à jour des coûts
            for (Noeud<E> voisin : graphe.getVoisins(noeudActuel)) {
                if (!dejaVus.contains(voisin)) {
                    double nouveauCout = couts.get(noeudActuel) + graphe.getCoutArete(noeudActuel, voisin);
                    if (nouveauCout < couts.get(voisin)) {
                        couts.put(voisin, nouveauCout);
                        coutsActuel.put(voisin, nouveauCout);
                        predecesseurs.put(voisin, null);
                        filePriorite.add(voisin);
                    }
                }
            }
        }

        // Reconstruction du chemin le plus court
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeud = this.arrivee;
        while (noeud != null) {
            chemin.add(noeud);
            noeud = predecesseurs.get(noeud);
        }
        Collections.reverse(chemin);
        return chemin;
    }

    @Override
    public Chemin trouverChemin(Carte carte, int x, int y, int x2, int y2) {
        // Cette méthode n'est pas implémentée pour cet algorithme
        // car il fonctionne uniquement sur des graphes et non des cartes
        return null;
    }
}


