package fr.utc.ida.forge.metier;

import static org.junit.jupiter.api.Assertions.*;

public class PortefeuilleTest {
    public void testGutterGame() throws Exception {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        assertEquals(1,portefeuille.mapLignes.get(action));
      }
    
}
