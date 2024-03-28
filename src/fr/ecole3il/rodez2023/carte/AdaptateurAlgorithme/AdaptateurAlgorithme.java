package fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.*;

import java.util.ArrayList;
import java.util.List;

public class AdaptateurAlgorithme{

    /**
     * trouve le chemin pour aller de la case une vers la deux
     * @param algorithme algorithme à utiliser
     * @param carte carte sur laquelle on ce situe
     * @param xDepart coordonée x de départ
     * @param yDepart coordonné y de départ
     * @param xArrivee coordonnée x d'arrivé
     * @param yArrivee coordonnée y d'arrivé
     * @return le chemin trouvé
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
     * crée le graphe à partir d'une carte
     * @param carte carte pour créer le graphe
     * @return un Graphe
     */
    static Graphe<Case> creerGraphe(Carte carte){
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();

        // Création des nœuds
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                graphe.ajouterNoeud(new Noeud<Case>(caseActuelle));
            }
        }

        // Pour je ne sais quelle raison, si je n'utilise pas de double boucle, ça ne fonctionne pas
        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                Case caseActuelle = new Case(carte.getTuile(x, y), x, y);
                ajouterAretesVoisines(graphe, caseActuelle, x, y, largeur, hauteur);
            }
        }

        return graphe;
    }

    /**
     * ajoute toute les arrêtes voisines
     * @param graphe graphe sur lequel travailler
     * @param currentCase case actuel
     * @param x coordonée x
     * @param y coordonée y
     * @param largeur largeur de la case
     * @param hauteur hauteur de la case
     */
    static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur){
        Noeud<Case> currentNode = null;
        for (Noeud<Case> noeud : graphe.getNoeuds()) {
            Case c = noeud.getValeur();
            if (c.equals(currentCase)) {
                currentNode = noeud;
                break;
            }
        }

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
     * calcul le cout
     * @param from a partir de
     * @param to jusqua
     * @return cout calculé
     */
    static double calculerCout(Case from, Case to){
        return Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY());
    }

    /**
     * Affiche le chemin
     * @param chemin chemin a afficher
     */
    static void afficherChemin(List<Noeud<Case>> chemin){
        chemin.forEach(noeud -> {
            Case caseActuelle = noeud.getValeur();
            System.out.println("Case: x = " + caseActuelle.getX() + ", y = " + caseActuelle.getY());
        });
    }
}