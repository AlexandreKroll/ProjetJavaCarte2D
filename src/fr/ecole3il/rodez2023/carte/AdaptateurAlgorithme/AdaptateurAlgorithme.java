package fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe représente un adaptateur pour utiliser un algorithme de recherche de chemin
 * sur une carte de jeu. Elle permet de trouver un chemin entre deux cases sur la carte.
 */
public class AdaptateurAlgorithme {

    /**
     * Trouve le chemin pour aller de la case de départ à la case d'arrivée sur la carte donnée,
     * en utilisant l'algorithme spécifié.
     *
     * @param algorithme l'algorithme à utiliser pour trouver le chemin
     * @param carte la carte sur laquelle se situe le chemin
     * @param xDepart la coordonnée x de la case de départ
     * @param yDepart la coordonnée y de la case de départ
     * @param xArrivee la coordonnée x de la case d'arrivée
     * @param yArrivee la coordonnée y de la case d'arrivée
     * @return le chemin trouvé entre les deux cases
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee){
        Graphe<Case> graphe = creerGraphe(carte);
        Noeud<Case> noeudDepart = graphe.getNoeud(xDepart, yDepart);
        Noeud<Case> noeudArrivee = graphe.getNoeud(xArrivee, yArrivee);
        List<Noeud<Case>> cheminNoeuds = algorithme.trouverChemin(graphe, noeudDepart, noeudArrivee);
        List<Case> cheminCases = new ArrayList<>();
        for (Noeud<Case> noeud : cheminNoeuds) {
            cheminCases.add(noeud.getValeur());
        }

        return new Chemin(cheminCases);
    }

    /**
     * Crée le graphe à partir d'une carte.
     *
     * @param carte la carte pour créer le graphe
     * @return un graphe représentant la carte
     */
    static Graphe<Case> creerGraphe(Carte carte){
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        // Création des nœuds
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                graphe.ajouterNoeud(new Noeud<>(caseActuelle));
            }
        }

        // Création des arêtes entre les nœuds
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                ajouterAretesVoisines(graphe, caseActuelle, x, y, largeur, hauteur);
            }
        }

        return graphe;
    }

    /**
     * Ajoute toutes les arêtes voisines pour un nœud donné.
     *
     * @param graphe le graphe sur lequel travailler
     * @param currentCase la case actuelle
     * @param x la coordonnée x de la case
     * @param y la coordonnée y de la case
     * @param largeur la largeur de la carte
     * @param hauteur la hauteur de la carte
     */
    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur){
        Noeud<Case> currentNode = graphe.getNoeud(currentCase.getX(), currentCase.getY());

        assert currentNode != null;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                Noeud<Case> neighborNode = graphe.getNoeud(newX, newY);
                if (neighborNode != null) {
                    Case neighborCase = neighborNode.getValeur();
                    double cost = calculerCout(currentCase, neighborCase);
                    graphe.ajouterArete(currentNode, neighborNode, cost);
                    currentNode.ajouterVoisin(neighborNode);
                }
            }
        }
    }

    /**
     * Calcule le coût entre deux cases.
     *
     * @param from la case de départ
     * @param to la case d'arrivée
     * @return le coût calculé entre les deux cases
     */
    static double calculerCout(Case from, Case to){
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    /**
     * Affiche les cases du chemin.
     *
     * @param chemin la liste des nœuds représentant le chemin
     */
    static void afficherChemin(List<Noeud<Case>> chemin){
        chemin.forEach(noeud -> {
            Case caseActuelle = noeud.getValeur();
            System.out.println("Case: x = " + caseActuelle.getX() + ", y = " + caseActuelle.getY());
        });
    }
}
