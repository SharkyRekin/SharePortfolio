package fr.utc.ida.forge.metier;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testVendreUneActionNonPossedee() throws VenteImpossibleException {
        VenteImpossibleException exception = assertThrows(VenteImpossibleException.class, () -> {
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
    public void testVendreUneActionEnTropGrosseQuantite() throws VenteImpossibleException {
        VenteImpossibleException exception = assertThrows(VenteImpossibleException.class, () -> {
            Action action = new ActionSimple("Action test");
            Portefeuille portefeuille = new Portefeuille();
            portefeuille.acheter(action, 1);
            portefeuille.vendre(action, 2);
        });
    }

}
