package fr.utc.ida.forge.metier;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ActionComposeeTest {
    @Test
    public void testPourcentageAffichage() throws Exception {
        ActionComposee action = new ActionComposee("Action test");
        ActionSimple axa = new ActionSimple("AXA");
        ActionSimple lcl = new ActionSimple("LCL");
        action.enrgComposition(axa, (float) 0.5);
        action.enrgComposition(lcl, (float) 0.5);
        System.out.println(action.getComposition());
        assertTrue("AXA:50.0%\nLCL:50.0%\n".equals(action.getComposition()) || "LCL:50.0%\nAXA:50.0%\n".equals(action.getComposition()));
    }
}
