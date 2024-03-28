package fr.ecole3il.rodez2023.carte.elements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Tuile;

public class TestCarte {

    @Test
    public void testEqualsAndHashCode() {
        // Créer deux matrices de tuiles identiques
        Tuile[][] tuiles1 = {
            {Tuile.DESERT, Tuile.PLAINE, Tuile.FORET},
            {Tuile.MONTAGNES, Tuile.FORET, Tuile.PLAINE},
            {Tuile.PLAINE, Tuile.DESERT, Tuile.DESERT}
        };

        Tuile[][] tuiles2 = {
            {Tuile.DESERT, Tuile.PLAINE, Tuile.FORET},
            {Tuile.MONTAGNES, Tuile.FORET, Tuile.PLAINE},
            {Tuile.PLAINE, Tuile.DESERT, Tuile.DESERT}
        };

        // Créer deux instances de Carte avec les matrices de tuiles
        Carte carte1 = new Carte(tuiles1);
        Carte carte2 = new Carte(tuiles2);

        // Vérifier que les cartes sont égales
        assertTrue(carte1.equals(carte2));
        assertTrue(carte2.equals(carte1));

        // Vérifier que les hashs des cartes sont égaux
        assertEquals(carte1.hashCode(), carte2.hashCode());

        // Modifier une tuile dans la deuxième carte
        tuiles2[0][0] = Tuile.FORET;

        // Créer une nouvelle instance de Carte avec la deuxième matrice de tuiles modifiée
        Carte carte3 = new Carte(tuiles2);

        // Vérifier que les cartes ne sont pas égales après la modification
        assertFalse(carte1.equals(carte3));
        assertFalse(carte3.equals(carte1));

        // Vérifier que les hashs des cartes sont différents après la modification
        assertFalse(carte1.hashCode() == carte3.hashCode());
    }
}
