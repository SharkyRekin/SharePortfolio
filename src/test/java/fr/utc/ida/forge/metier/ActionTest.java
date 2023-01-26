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

class ActionTest {

    @Test
    void testGetLibelle() {
        Action a = new ActionClassTest("action 1");
        assertEquals("action 1", a.getLibelle(), "testGetLibelle");
    }

    @Test
    void testValeur() {
        Action a = new ActionClassTest("action 1");
        assertEquals(0, a.valeur(null), "testValeur");
    }

    @Test
    void testHashCode() {
        Action a = new ActionClassTest("action 1");
        Action b = new ActionClassTest("action 1");
        Action c = new ActionClassTest("action 2");
        assertEquals(a.hashCode(), a.hashCode(), "testHashCode");
        assertEquals(a.hashCode(), b.hashCode(), "testHashCode");
        assertNotEquals(a.hashCode(), c.hashCode(), "testHashCode");
    }

    @Test
    void testEquals() {
        Action a = new ActionClassTest("action 1");
        Action b = new ActionClassTest("action 1");
        Action c = new ActionClassTest("action 2");

        assertEquals(a, a, "testEquals");
        assertEquals(a, b, "testEquals");
        assertNotEquals(a, c, "testEquals");
    }

    @Test
    void testEqualsNull() {
        Action a = new ActionClassTest("action 1");
        assertNotEquals(null, a, "testEqualsNull");
    }

    @Test
    void testEqualsNotSameObject() {
        Action a = new ActionClassTest("action 1");
        Jour j = new Jour(2020, 1);
        assertNotEquals(j, a, "testEqualsNotSameObject");
    }

    @Test
    void testToString() {
        Action a = new ActionClassTest("action 1");
        assertEquals("action 1", a.toString(), "testToString");
    }

    @Test
    void testCompareTo() {
        Action a = new ActionClassTest("action 1");
        Action b = new ActionClassTest("action 1");
        assertEquals(a, b, "testCompareTo");
    }

    private static class ActionClassTest extends Action {

        public ActionClassTest(String libelle) {
            super(libelle);
        }

        @Override
        public double valeur(Jour j) {
            return 0;
        }
    }
}