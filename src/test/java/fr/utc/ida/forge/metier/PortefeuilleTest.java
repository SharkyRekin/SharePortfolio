package fr.utc.ida.forge.metier;

import static org.junit.jupiter.api.Assertions.*;

import fr.utc.ida.forge.exception.VenteImpossibleException;
import org.junit.jupiter.api.Test;

public class PortefeuilleTest {
    @Test
    public void testAchatUnique() throws Exception {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        assertEquals(1, portefeuille.mapLignes.get(action));
    }

    @Test
    public void testAchatPlusieursFoisLaMemeAction() throws Exception {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        portefeuille.acheter(action, 1);
        assertEquals(2, portefeuille.mapLignes.get(action));
    }

    @Test
    public void testVendreUneActionNonPossedee() {
        assertThrows(VenteImpossibleException.class, () -> {
            Action action = new ActionSimple("Action test");
            Portefeuille portefeuille = new Portefeuille();
            portefeuille.vendre(action, 1);
        });
    }

    @Test
    public void testVendreUneActionPossedee() throws VenteImpossibleException {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        portefeuille.vendre(action, 1);
        assertEquals(0,portefeuille.mapLignes.get(action));
    }

    @Test
    public void testVendreUneActionEnTropGrosseQuantite() {
        assertThrows(VenteImpossibleException.class, () -> {
            Action action = new ActionSimple("Action test");
            Portefeuille portefeuille = new Portefeuille();
            portefeuille.acheter(action, 1);
            portefeuille.vendre(action, 2);
        });
    }

    @Test
    public void testToString() {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        StringBuilder sb = new StringBuilder("Mes actions : \n");
        sb.append("Action test : 1\n");
        assertEquals(sb.toString(),portefeuille.toString());
    }

    @Test
    public void testToStringType() {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        assertInstanceOf(String.class, portefeuille.toString());
    }

    @Test
    public void testValeurActionSimple() {
        ActionSimple a = new ActionSimple("Action test");
        Portefeuille p = new Portefeuille();
        Jour j = new Jour(2022, 24);
        a.enrgCours(j, 10);
        p.acheter(a, 1);
        assertEquals(10, p.valeur(j));
    }

    @Test
    public void testValeurActionComposee() {
        ActionSimple a = new ActionSimple("Action test 1");
        ActionSimple b = new ActionSimple("Action test 2");
        ActionComposee ac = new ActionComposee("Action composee test");
        Portefeuille p = new Portefeuille();
        Jour j = new Jour(2022, 24);
        ac.enrgComposition(a, 0.5f);
        ac.enrgComposition(b, 0.5f);
        a.enrgCours(j, 10);
        b.enrgCours(j, 20);
        p.acheter(ac, 1);
        assertEquals(15, p.valeur(j));
    }

    @Test
    public void testValeurActionSimpleComposee() {
        ActionSimple a = new ActionSimple("Action test 1");
        ActionSimple b = new ActionSimple("Action test 2");
        ActionSimple c = new ActionSimple("Action test 3");
        ActionComposee ac = new ActionComposee("Action composee test");
        Portefeuille p = new Portefeuille();
        Jour j = new Jour(2022, 24);
        ac.enrgComposition(a, 0.5f);
        ac.enrgComposition(b, 0.5f);
        a.enrgCours(j, 10);
        b.enrgCours(j, 20);
        c.enrgCours(j, 10);
        p.acheter(ac, 1);
        p.acheter(c, 1);
        assertEquals(25, p.valeur(j));
    }

    @Test
    public void testValeurActionComposeeComposee() {
        ActionSimple a = new ActionSimple("Action test 1");
        ActionSimple b = new ActionSimple("Action test 2");
        ActionSimple c = new ActionSimple("Action test 3");
        ActionSimple d = new ActionSimple("Action test 4");
        ActionComposee ac = new ActionComposee("Action composee test 1");
        ActionComposee ac2 = new ActionComposee("Action composee test 2");
        Portefeuille p = new Portefeuille();
        Jour j = new Jour(2022, 24);
        ac.enrgComposition(a, 0.5f);
        ac.enrgComposition(b, 0.5f);
        ac2.enrgComposition(c, 0.5f);
        ac2.enrgComposition(d, 0.5f);
        a.enrgCours(j, 10);
        b.enrgCours(j, 20);
        c.enrgCours(j, 10);
        d.enrgCours(j, 20);
        p.acheter(ac, 1);
        p.acheter(ac2, 1);
        assertEquals(30, p.valeur(j));
    }

    @Test
    public void testValeurJourNull() {
        ActionSimple a = new ActionSimple("Action test 1");
        Portefeuille p = new Portefeuille();
        p.acheter(a, 1);
        assertThrows(IllegalArgumentException.class, () -> p.valeur(null));
    }

    @Test
    public void testValeurPasAction() {
        Portefeuille p = new Portefeuille();
        Jour j = new Jour(2022, 24);
        assertEquals(0, p.valeur(j));
    }

    @Test
    public void testValeurPasCours() {
        ActionSimple a = new ActionSimple("Action test 1");
        Portefeuille p = new Portefeuille();
        Jour j = new Jour(2022, 24);
        p.acheter(a, 1);
        assertThrows(IllegalArgumentException.class, () -> p.valeur(j));
    }
    @Test
    public void testQuantiteAchat() {
        Action action = new ActionSimple("AXA");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        assertEquals("Vous avez 1 de l'action AXA", portefeuille.getQteAchat(action));
    }

    @Test
    public void testQuantiteAchatNonPossede() {
        Action action = new ActionSimple("AXA");
        Portefeuille portefeuille = new Portefeuille();
        assertEquals("Cette action n'est pas contenue dans le portefeuille", portefeuille.getQteAchat(action));
    }
}
