package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ecole3il.rodez2023.carte.elements.Case;

public class Graphe<E> {
    private Map<Noeud<E>, Map<Noeud<E>,Double>> adjacence;

    public Graphe() {
        adjacence = new HashMap<>();
    }

    public void ajouterNoeud(Noeud<E> noeud) {
        if (!adjacence.containsKey(noeud)) {
            adjacence.put(noeud, new HashMap<>());
        }
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        adjacence.get(depart).put(arrivee, cout);
        // Pour un graphe non orienté, ajouter également l'arête dans l'autre sens :
        // adjacence.get(arrivee).add(new Arete<>(depart, cout));
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        
            if (adjacence.containsKey(depart) && adjacence.get(depart).containsKey(arrivee)) {
                return adjacence.get(depart).get(arrivee);
            }
        
        return Double.POSITIVE_INFINITY; // ou une valeur représentant l'absence de l'arête
    }

    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(adjacence.keySet());
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
    	if(adjacence.containsKey(noeud)) {
    		return new ArrayList(adjacence.get(noeud).keySet());
    	}
        
        return new ArrayList();
    }

	public Noeud<E> getNoeud(int x, int y) {
		for (Noeud<E> noeud : this.getNoeuds()) {
			Case caseActuelle = (Case) noeud.getValeur();
			if(caseActuelle.getX() == x && caseActuelle.getY() == y) {
				return noeud;
			}
		}
		return null;
	}

}

