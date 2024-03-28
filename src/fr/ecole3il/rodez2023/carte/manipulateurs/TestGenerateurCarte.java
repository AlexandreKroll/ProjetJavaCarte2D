package fr.ecole3il.rodez2023.carte.manipulateurs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;

public class TestGenerateurCarte {

    @Test
    public void testGenererCarte() {
        Carte carte = GenerateurCarte.genererCarte();
        assertNotNull(carte);
        assertEquals(GenerateurCarte.LARGEUR_PAR_DEFAUT, carte.getLargeur());
        assertEquals(GenerateurCarte.HAUTEUR_PAR_DEFAUT, carte.getHauteur());
    }

    @Test
    public void testGenererCarteCustom() {
        int largeur = 15;
        int hauteur = 20;
        Carte carte = GenerateurCarte.genererCarte(largeur, hauteur);
        assertNotNull(carte);
        assertEquals(largeur, carte.getLargeur());
        assertEquals(hauteur, carte.getHauteur());
    }
}

