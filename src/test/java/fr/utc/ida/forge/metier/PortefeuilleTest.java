package fr.utc.ida.forge.metier;

import static org.junit.jupiter.api.Assertions.*;

import fr.utc.ida.forge.exception.AchatImpossibleException;
import fr.utc.ida.forge.exception.VenteImpossibleException;
import org.junit.jupiter.api.Test;

class PortefeuilleTest {
    @Test
    void testAchatUnique() throws AchatImpossibleException {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        assertEquals(1, portefeuille.getMapLignes().get(action), "testAchatUnique");
    }

    @Test
    void testAchatPlusieursFoisLaMemeAction() throws AchatImpossibleException {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        portefeuille.acheter(action, 1);
        assertEquals(2, portefeuille.getMapLignes().get(action), "testAchatPlusieursFoisLaMemeAction");
    }

    @Test
    void testAcheterUneQuantiteNegativeDaction() {
        assertThrows(AchatImpossibleException.class, () -> {
            Action action = new ActionSimple("Action test");
            Portefeuille portefeuille = new Portefeuille();
            portefeuille.acheter(action, -1);
        });
    }

    @Test
    void testVendreUneActionNonPossedee() {
        assertThrows(VenteImpossibleException.class, () -> {
            Action action = new ActionSimple("Action test");
            Portefeuille portefeuille = new Portefeuille();
            portefeuille.vendre(action, 1);
        }, "testVendreUneActionNonPossedee");
    }

    @Test
    void testVendreUneActionEnQuantiteNegative() {
        assertThrows(VenteImpossibleException.class, () -> {
            Action action = new ActionSimple("Action test");
            Portefeuille portefeuille = new Portefeuille();
            portefeuille.vendre(action,-1);
        });
    }

    @Test
    void testVendreUneActionPossedee() throws AchatImpossibleException {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        try {
            portefeuille.vendre(action, 1);
        } catch (VenteImpossibleException e) {
            fail();
        }
        assertEquals(0,portefeuille.getMapLignes().get(action), "testVendreUneActionPossedee");
    }

    @Test
    void testVendreUneActionEnTropGrosseQuantite() {
        assertThrows(VenteImpossibleException.class, () -> {
            Action action = new ActionSimple("Action test");
            Portefeuille portefeuille = new Portefeuille();
            portefeuille.acheter(action, 1);
            portefeuille.vendre(action, 2);
        }, "testVendreUneActionEnTropGrosseQuantite");
    }

    @Test
    void testToString() throws AchatImpossibleException {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        assertEquals("""
                Mes actions :\s
                Action test : 1
                """,portefeuille.toString(), "testToString");
    }

    @Test
    void testToStringType() throws AchatImpossibleException {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        assertInstanceOf(String.class, portefeuille.toString(), "testToStringType");
    }

    @Test
    void testValeurActionSimple() throws AchatImpossibleException {
        ActionSimple a = new ActionSimple("Action test");
        Portefeuille p = new Portefeuille();
        Jour j = new Jour(2022, 24);
        a.enrgCours(j, 10);
        p.acheter(a, 1);
        assertEquals(10, p.valeur(j), "testValeurActionSimple");
    }

    @Test
    void testValeurActionComposee() throws AchatImpossibleException {
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
        assertEquals(15, p.valeur(j), "testValeurActionComposee");
    }

    @Test
    void testValeurActionSimpleComposee() throws AchatImpossibleException {
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
        assertEquals(25, p.valeur(j), "testValeurActionSimpleComposee");
    }

    @Test
    void testValeurActionComposeeComposee() throws AchatImpossibleException {
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
        assertEquals(30, p.valeur(j), "testValeurActionComposeeComposee");
    }

    @Test
    void testValeurJourNull() throws AchatImpossibleException {
        ActionSimple a = new ActionSimple("Action test 1");
        Portefeuille p = new Portefeuille();
        p.acheter(a, 1);
        assertThrows(IllegalArgumentException.class, () -> p.valeur(null), "testValeurJourNull");
    }

    @Test
    void testValeurPasAction() {
        Portefeuille p = new Portefeuille();
        Jour j = new Jour(2022, 24);
        assertEquals(0, p.valeur(j), "testValeurPasAction");
    }

    @Test
    void testValeurPasCours() throws AchatImpossibleException {
        ActionSimple a = new ActionSimple("Action test 1");
        Portefeuille p = new Portefeuille();
        Jour j = new Jour(2022, 24);
        p.acheter(a, 1);
        assertThrows(IllegalArgumentException.class, () -> p.valeur(j), "testValeurPasCours");
    }
    @Test
    void testQuantiteAchat() throws AchatImpossibleException {
        Action action = new ActionSimple("AXA");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        assertEquals("Vous avez 1 de l'action AXA", portefeuille.getQteAchat(action), "testQuantiteAchat");
    }

    @Test
    void testQuantiteAchatNonPossede() {
        Action action = new ActionSimple("AXA");
        Portefeuille portefeuille = new Portefeuille();
        assertEquals("Cette action n'est pas contenue dans le portefeuille",
                portefeuille.getQteAchat(action), "testQuantiteAchatNonPossede");
    }
}
