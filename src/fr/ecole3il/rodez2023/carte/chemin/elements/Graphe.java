package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graphe<E> {
    private Map<Noeud<E>, List<Arete<E>>> adjacence;

    public Graphe() {
        adjacence = new HashMap<>();
    }

    public void ajouterNoeud(Noeud<E> noeud) {
        if (!adjacence.containsKey(noeud)) {
            adjacence.put(noeud, new ArrayList<>());
        }
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        adjacence.get(depart).add(new Arete<>(arrivee, cout));
        // Pour un graphe non orienté, ajouter également l'arête dans l'autre sens :
        // adjacence.get(arrivee).add(new Arete<>(depart, cout));
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        for (Arete<E> arete : adjacence.get(depart)) {
            if (arete.getNoeud().equals(arrivee)) {
                return arete.getCout();
            }
        }
        return Double.POSITIVE_INFINITY; // ou une valeur représentant l'absence de l'arête
    }

    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(adjacence.keySet());
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        List<Noeud<E>> voisins = new ArrayList<>();
        for (Arete<E> arete : adjacence.getOrDefault(noeud, new ArrayList<>())) {
            voisins.add(arete.getNoeud());
        }
        return voisins;
    }

    private static class Arete<E> {
        private Noeud<E> noeud;
        private double cout;

        public Arete(Noeud<E> noeud, double cout) {
            this.noeud = noeud;
            this.cout = cout;
        }

        public Noeud<E> getNoeud() {
            return noeud;
        }

        public double getCout() {
            return cout;
        }
    }
}

