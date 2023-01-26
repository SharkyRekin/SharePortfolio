package fr.utc.ida.forge.metier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActionComposeeTest {
    @Test
    void testPourcentageAffichage() {
        ActionComposee action = new ActionComposee("Action test");
        ActionSimple axa = new ActionSimple("AXA");
        ActionSimple lcl = new ActionSimple("LCL");
        action.enrgComposition(axa, (float) 0.5);
        action.enrgComposition(lcl, (float) 0.5);
        System.out.println(action.getComposition());
        assertTrue("AXA:50.0%\nLCL:50.0%\n".equals(action.getComposition())
                || "LCL:50.0%\nAXA:50.0%\n".equals(action.getComposition()),
                "testPourcentageAffichage");
    }

    @Test
    void testEnregistrerActionCompose() {
        ActionComposee ac = new ActionComposee("Action composée test");
        ActionSimple a = new ActionSimple("Action Simple 1");
        ActionSimple b = new ActionSimple("Action Simple 2");
        Jour j = new Jour(2022,1);
        ac.enrgComposition(a, 0.5f);
        ac.enrgComposition(b, 0.5f);
        a.enrgCours(j, 10);
        b.enrgCours(j, 20);
        assertEquals(15 , ac.valeur(j), "testEnregistrerActionCompose");
    }

    @Test
    void testValeurActionComposee() {
        ActionComposee ac = new ActionComposee("Action composée test");
        ActionSimple a = new ActionSimple("Action Simple 1");
        ActionSimple b = new ActionSimple("Action Simple 2");
        ac.enrgComposition(a, 0.5f);
        ac.enrgComposition(b, 0.5f);
        Jour j = new Jour(2022,1);
        a.enrgCours(j, 10);
        b.enrgCours(j, 20);
        assertEquals(15, ac.valeur(j), "testValeurActionComposee");
    }

    @Test
    void testValeurSansAction() {
        ActionComposee ac = new ActionComposee("Action composée test");
        Jour j = new Jour(2022,1);
        assertEquals(0, ac.valeur(j), "testValeurSansAction");
    }

    @Test
    void testValeurJourNull() {
        ActionComposee ac = new ActionComposee("Action composée test");
        ActionSimple a = new ActionSimple("Action Simple 1");
        ActionSimple b = new ActionSimple("Action Simple 2");
        ac.enrgComposition(a, 0.5f);
        ac.enrgComposition(b, 0.5f);
        assertThrows(IllegalArgumentException.class, () -> ac.valeur(null), "testValeurJourNull");
    }

    @Test
    void testValeurSansCours() {
        ActionComposee ac = new ActionComposee("Action composée test");
        ActionSimple a = new ActionSimple("Action Simple 1");
        ActionSimple b = new ActionSimple("Action Simple 2");
        ac.enrgComposition(a, 0.5f);
        ac.enrgComposition(b, 0.5f);
        Jour j = new Jour(2022,1);
        assertThrows(IllegalArgumentException.class, () -> ac.valeur(j), "testValeurSansCours");
    }
    
    @Test
    void testEnregistrerActionNull() {
        ActionComposee ac = new ActionComposee("Action composée test");
        assertThrows(IllegalArgumentException.class, () -> ac.enrgComposition(null, 0));
    }

    @Test
    void testEnregistrerActionPercent() {
        ActionComposee ac = new ActionComposee("Action composee");
        ActionSimple a = new ActionSimple("BPN");
        assertThrows(IllegalArgumentException.class, () -> ac.enrgComposition(a, 2));
        assertThrows(IllegalArgumentException.class, () -> ac.enrgComposition(a, -2));
    }

    @Test
    void testEnregistrerActionPercentTotal() {
        ActionComposee ac = new ActionComposee("Action composee");
        ActionSimple a = new ActionSimple("BPN");
        ActionSimple b = new ActionSimple("B");
        ac.enrgComposition(a, 1);
        assertThrows(IllegalArgumentException.class, () -> ac.enrgComposition(b, 0.5));
    }
}
