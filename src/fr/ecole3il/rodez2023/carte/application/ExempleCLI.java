package fr.ecole3il.rodez2023.carte.application;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeAEtoile;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;

/**
 * Cette classe fournit un exemple d'utilisation des algorithmes de recherche de chemin
 * avec une carte générée aléatoirement. Elle illustre comment utiliser l'adaptateur d'algorithme
 * pour trouver un chemin entre deux points sur une carte et afficher le résultat.
 */
public class ExempleCLI {

    /**
     * Méthode principale pour exécuter l'exemple d'utilisation des algorithmes de recherche de chemin.
     *
     * @param args les arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        GenerateurCarte generateur = new GenerateurCarte();
        Carte test = generateur.genererCarte(100,100);
        AlgorithmeChemin algoChemin = new AlgorithmeDijkstra();
        Chemin chemin = AdaptateurAlgorithme.trouverChemin(algoChemin,test, 0, 0, 50, 50);
        chemin.afficherChemin();
        chemin = AdaptateurAlgorithme.trouverChemin(algoChemin,test, 0, 0, 50, 50);
        chemin.afficherChemin();
    }
}

