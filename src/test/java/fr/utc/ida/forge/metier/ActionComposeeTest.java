package fr.utc.ida.forge.metier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActionComposeeTest {
    @Test
    public void testPourcentageAffichage() {
        ActionComposee action = new ActionComposee("Action test");
        ActionSimple axa = new ActionSimple("AXA");
        ActionSimple lcl = new ActionSimple("LCL");
        action.enrgComposition(axa, (float) 0.5);
        action.enrgComposition(lcl, (float) 0.5);
        System.out.println(action.getComposition());
        assertTrue("AXA:50.0%\nLCL:50.0%\n".equals(action.getComposition()) || "LCL:50.0%\nAXA:50.0%\n".equals(action.getComposition()));
    }

    @Test
    public void testEnregistrerActionCompose() {
        ActionComposee ac = new ActionComposee("Action composée test");
        ActionSimple a = new ActionSimple("Action Simple 1");
        ActionSimple b = new ActionSimple("Action Simple 2");
        Jour j = new Jour(2022,1);
        ac.enrgComposition(a, 0.5f);
        ac.enrgComposition(b, 0.5f);
        a.enrgCours(j, 10);
        b.enrgCours(j, 20);
        assertEquals(15 , ac.valeur(j));
    }

    @Test
    public void testValeurActionComposee() {
        ActionComposee ac = new ActionComposee("Action composée test");
        ActionSimple a = new ActionSimple("Action Simple 1");
        ActionSimple b = new ActionSimple("Action Simple 2");
        ac.enrgComposition(a, 0.5f);
        ac.enrgComposition(b, 0.5f);
        Jour j = new Jour(2022,1);
        a.enrgCours(j, 10);
        b.enrgCours(j, 20);
        assertEquals(15, ac.valeur(j));
    }

    @Test
    public void testValeurSansAction() {
        ActionComposee ac = new ActionComposee("Action composée test");
        Jour j = new Jour(2022,1);
        assertEquals(0, ac.valeur(j));
    }

    @Test
    public void testValeurJourNull() {
        ActionComposee ac = new ActionComposee("Action composée test");
        ActionSimple a = new ActionSimple("Action Simple 1");
        ActionSimple b = new ActionSimple("Action Simple 2");
        ac.enrgComposition(a, 0.5f);
        ac.enrgComposition(b, 0.5f);
        assertThrows(IllegalArgumentException.class, () -> ac.valeur(null));
    }

    @Test
    public void testValeurSansCours() {
        ActionComposee ac = new ActionComposee("Action composée test");
        ActionSimple a = new ActionSimple("Action Simple 1");
        ActionSimple b = new ActionSimple("Action Simple 2");
        ac.enrgComposition(a, 0.5f);
        ac.enrgComposition(b, 0.5f);
        Jour j = new Jour(2022,1);
        assertThrows(IllegalArgumentException.class, () -> ac.valeur(j));
    }
}
