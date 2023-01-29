/*
 * Copyright 2023 ICHCHOU - JUNOT - CROAIN - BERNAULT
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

class ActionSimpleTest {

    @Test
    void testValeur() {
        ActionSimple a = new ActionSimple("Action test");
        Jour j = new Jour(2022,21);
        a.enrgCours(j,1);
        assertEquals(1,a.valeur(j), "testValeur");
    }

    @Test
    void testValeurJourNull() {
        ActionSimple a = new ActionSimple("Action test");
        assertThrows(IllegalArgumentException.class, () -> a.valeur(null), "testValeurJourNull");
    }

    @Test
    void testValeurJourNotExist() {
        ActionSimple a = new ActionSimple("Action test");
        Jour j = new Jour(2022,21);
        assertThrows(IllegalArgumentException.class, () -> a.valeur(j), "testValeurJourNotExist");
    }

    @Test
    void testEnregistrerCours() {
        ActionSimple a = new ActionSimple("Action test");
        Jour j = new Jour(2022,1);
        a.enrgCours(j, 18);
        assertEquals(18 ,a.valeur(j), "testEnregistrerCours");
    }


    @Test
    void testEnrJourJourNull() {
        ActionSimple a = new ActionSimple("Action test");
        assertThrows(IllegalArgumentException.class, () -> a.enrgCours(null, 18), "testEnrJourJourNull");
    }

    @Test
    void testEnrJourValeurNegative() {
        ActionSimple a = new ActionSimple("Action test");
        Jour j = new Jour(2022,21);
        assertThrows(IllegalArgumentException.class, () -> a.enrgCours(j, -18), "testEnrJourValeurNegative");
    }

    @Test
    void testEnrJourCoursDejaEnregistrerCeJour() {
        ActionSimple a = new ActionSimple("Action test");
        Jour j = new Jour(2022,21);
        a.enrgCours(j, 18);
        assertThrows(IllegalArgumentException.class, () -> a.enrgCours(j, 18), "testEnrJourCoursDejaEnregistrerCeJour");
    }

    @Test
    void testEquals() {
        ActionSimple a = new ActionSimple("action 1");
        ActionSimple b = new ActionSimple("action 1");
        ActionSimple c = new ActionSimple("action 2");

        assertEquals(a, a, "testEquals");
        assertEquals(a, b, "testEquals");
        assertNotEquals(a, c, "testEquals");
    }

    @Test
    void testEqualsNull() {
        ActionSimple a = new ActionSimple("action 1");
        assertNotEquals(null, a, "testEqualsNull");
    }

    @Test
    void testEqualsNotSameObject() {
        ActionSimple a = new ActionSimple("action 1");
        ActionComposee b = new ActionComposee("action 2");
        assertNotEquals(a, b, "testEqualsNotSameObject");
    }
}
