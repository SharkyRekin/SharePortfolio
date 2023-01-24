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

public class JourTest {

    @Test
    public void testGetAnnee() {
        Jour j = new Jour(2019, 1);
        assertEquals(2019, j.annee());
    }

    @Test
    public void testGetNoJour() {
        Jour j = new Jour(2019, 1);
        assertEquals(1, j.noJour());
    }

    @Test
    public void testEquals() {
        Jour j1 = new Jour(2019, 1);
        Jour j2 = new Jour(2019, 1);
        Jour j3 = new Jour(2019, 2);
        Jour j4 = new Jour(2018, 1);
        assertEquals(j1, j2);
        assertNotEquals(j1, j3);
        assertNotEquals(j1, j4);
    }

    @Test
    public void testHashCode() {
        Jour j1 = new Jour(2019, 1);
        Jour j2 = new Jour(2019, 1);
        Jour j3 = new Jour(2019, 2);
        Jour j4 = new Jour(2018, 1);
        assertEquals(j1.hashCode(), j2.hashCode());
        assertNotEquals(j1.hashCode(), j3.hashCode());
        assertNotEquals(j1.hashCode(), j4.hashCode());
    }

    @Test
    public void testToString() {
        Jour j1 = new Jour(2019, 1);
        assertEquals("Annee 2019 jour 1", j1.toString());
    }

    @Test
    public void testIllegalJour() {
        assertThrows(IllegalArgumentException.class, () -> new Jour(2019, 0));
        assertThrows(IllegalArgumentException.class, () -> new Jour(2019, 367));
    }

    @Test
    public void testIllegalAnnee() {
        assertThrows(IllegalArgumentException.class, () -> new Jour(1900, 1));
    }
}