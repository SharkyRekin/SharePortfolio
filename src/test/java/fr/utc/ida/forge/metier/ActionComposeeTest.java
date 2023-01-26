/*
 * Copyright 2023 Croain - Bernault
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        assertThrows(IllegalArgumentException.class, () -> ac.enrgComposition(null, 0),
                "testEnregistrerActionNull");
    }

    @Test
    void testEnregistrerActionPercent() {
        ActionComposee ac = new ActionComposee("Action composee");
        ActionSimple a = new ActionSimple("BPN");
        assertThrows(IllegalArgumentException.class, () -> ac.enrgComposition(a, 2),
                "testEnregistrerActionPercent");
        assertThrows(IllegalArgumentException.class, () -> ac.enrgComposition(a, -2),
                "testEnregistrerActionPercent");
    }

    @Test
    void testEnregistrerActionPercentTotal() {
        ActionComposee ac = new ActionComposee("Action composee");
        ActionSimple a = new ActionSimple("BPN");
        ActionSimple b = new ActionSimple("B");
        ac.enrgComposition(a, 1);
        assertThrows(IllegalArgumentException.class, () -> ac.enrgComposition(b, 0.5),
                "testEnregistrerActionPercentTotal");
    }

    @Test
    void testEquals() {
        ActionComposee a = new ActionComposee("action 1");
        ActionComposee b = new ActionComposee("action 1");
        ActionComposee c = new ActionComposee("action 2");

        assertEquals(a, a, "testEquals");
        assertEquals(a, b, "testEquals");
        assertNotEquals(a, c, "testEquals");
    }

    @Test
    void testEqualsNull() {
        ActionComposee a = new ActionComposee("action 1");
        assertNotEquals(null, a, "testEqualsNull");
    }

    @Test
    void testEqualsNotSameObject() {
        ActionComposee a = new ActionComposee("action 1");
        Jour j = new Jour(2020, 1);
        assertNotEquals(j, a, "testEqualsNotSameObject");
    }
}
