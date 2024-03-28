package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {
    private Noeud<E> arrivee; // Noeud d'arrivée

    public AlgorithmeAEtoile(Noeud<E> arrivee) {
        this.arrivee = arrivee;
    }

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        Map<Noeud<E>, Double> couts = new HashMap<>();
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>();
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(this::coutTotalEstime));
        Set<Noeud<E>> dejaVus = new HashSet<>();

        // Initialisation des structures de données
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY);
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
                        predecesseurs.put(voisin, noeudActuel);
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

    // Méthode pour calculer le coût total estimé d'un nœud (coût réel + estimation heuristique)
    private double coutTotalEstime(Noeud<E> noeud) {
        // Utilisation de la distance euclidienne comme heuristique
        // Dans cet exemple, nous supposons que les nœuds ont des positions (x, y)
        // Remplacer les valeurs 0.0 et 0.0 par les positions réelles des nœuds
        double distanceEuclidienne = Math.sqrt(Math.pow(noeud.getPositionX() - arrivee.getPositionX(), 2) +
                                               Math.pow(noeud.getPositionY() - arrivee.getPositionY(), 2));
        return distanceEuclidienne;
    }

	@Override
	public Chemin trouverChemin(Carte carte, int x, int y, int x2, int y2) {
		// TODO Auto-generated method stub
		return null;
	}
}

