package fr.utc.ida.forge.metier;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PortefeuilleTest {

    @Test
    public void testGutterGame() throws Exception {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        assertEquals(1,portefeuille.mapLignes.get(action));
    }

    @Test
    void testToString() {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        StringBuilder sb = new StringBuilder("Mes actions : \n");
        sb.append("Action test : 1\n");
        assertEquals(sb.toString(),portefeuille.toString());
    }

    @Test
    void testToStringType() {
        Action action = new ActionSimple("Action test");
        Portefeuille portefeuille = new Portefeuille();
        portefeuille.acheter(action, 1);
        assertInstanceOf(String.class, portefeuille.toString());
    }
}
