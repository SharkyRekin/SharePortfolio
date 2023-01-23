/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.utc.ida.forge.metier;
import static org.junit.jupiter.api.Assertions.*;


/**
 *
 * @author junot
 */
public class ActionSimpleTest {
    /*
    * test de la fonction
    */
    public void testEnregistrerCours() throws Exception {   
        ActionSimple a = new ActionSimple("Action simple test");
        Jour j = new Jour(2022,1);
        a.enrgCours(j, 18);
        assertEquals(18 ,a.valeur(j));
      }

}
