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

public class ActionTest {

    @Test
    public void getLibelle() {
        Action a = new ActionClassTest("action 1");
        assertEquals("action 1", a.getLibelle());
    }

    @Test
    public void valeur() {
        Action a = new ActionClassTest("action 1");
        assertEquals(0, a.valeur(null));
    }

    @Test
    public void testHashCode() {
        Action a = new ActionClassTest("action 1");
        Action b = new ActionClassTest("action 1");
        Action c = new ActionClassTest("action 2");
        assertEquals(a.hashCode(), a.hashCode());
        assertEquals(a.hashCode(), b.hashCode());
        assertNotEquals(a.hashCode(), c.hashCode());
    }

    @Test
    public void testEquals() {
        Action a = new ActionClassTest("action 1");
        Action b = new ActionClassTest("action 1");
        Action c = new ActionClassTest("action 2");

        assertEquals(a, a);
        assertEquals(a, b);
        assertNotEquals(a, c);
    }

    @Test
    public void testToString() {
        Action a = new ActionClassTest("action 1");
        assertEquals("action 1", a.toString());
    }

    private static class ActionClassTest extends Action {

        public ActionClassTest(String libelle) {
            super(libelle);
        }

        @Override
        public float valeur(Jour j) {
            return 0;
        }
    }
}