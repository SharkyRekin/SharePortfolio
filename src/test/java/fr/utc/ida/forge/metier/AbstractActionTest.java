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

class AbstractActionTest {

    @Test
    void testGetLibelle() {
        AbstractAction a = new AbstractActionClassTest("action 1");
        assertEquals("action 1", a.getLibelle(), "testGetLibelle");
    }

    @Test
    void testValeur() {
        AbstractAction a = new AbstractActionClassTest("action 1");
        assertEquals(0, a.valeur(null), "testValeur");
    }

    @Test
    void testHashCode() {
        AbstractAction a = new AbstractActionClassTest("action 1");
        AbstractAction b = new AbstractActionClassTest("action 1");
        AbstractAction c = new AbstractActionClassTest("action 2");
        assertEquals(a.hashCode(), a.hashCode(), "testHashCode");
        assertEquals(a.hashCode(), b.hashCode(), "testHashCode");
        assertNotEquals(a.hashCode(), c.hashCode(), "testHashCode");
    }

    @Test
    void testEquals() {
        AbstractAction a = new AbstractActionClassTest("action 1");
        AbstractAction b = new AbstractActionClassTest("action 1");
        AbstractAction c = new AbstractActionClassTest("action 2");

        assertEquals(a, a, "testEquals");
        assertEquals(a, b, "testEquals");
        assertNotEquals(a, c, "testEquals");
    }

    @Test
    void testEqualsNull() {
        AbstractAction a = new AbstractActionClassTest("action 1");
        assertFalse(a.equals(null), "testEqualsNull");
    }

    @Test
    void testEqualsNotSameObject() {
        AbstractAction a = new AbstractActionClassTest("action 1");
        ActionComposee b = new ActionComposee("action 2");
        assertFalse(a.equals(b), "testEqualsNotSameObject");
    }

    @Test
    void testToString() {
        AbstractAction a = new AbstractActionClassTest("action 1");
        assertEquals("action 1", a.toString(), "testToString");
    }

    @Test
    void testCompareTo() {
        AbstractAction a = new AbstractActionClassTest("action 1");
        AbstractAction b = new AbstractActionClassTest("action 1");
        assertEquals(0, a.compareTo(b), "testCompareTo");
    }

    private static class AbstractActionClassTest extends AbstractAction {

        public AbstractActionClassTest(String libelle) {
            super(libelle);
        }

        @Override
        public double valeur(Jour j) {
            return 0;
        }
    }
}