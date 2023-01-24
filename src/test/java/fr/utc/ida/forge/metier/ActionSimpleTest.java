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
    public void testValeur() {
        ActionSimple a = new ActionSimple("Action test");
        Jour j = new Jour(2022,21);
        a.enrgCours(j,1);
        assertEquals(1,a.valeur(j));
    }

    @Test
    public void testValeurJourNull() {
        ActionSimple a = new ActionSimple("Action test");
        assertThrows(IllegalArgumentException.class, () -> a.valeur(null));
    }

    @Test
    public void testValeurJourNotExist() {
        ActionSimple a = new ActionSimple("Action test");
        Jour j = new Jour(2022,21);
        assertThrows(IllegalArgumentException.class, () -> a.valeur(j));
    }

    @Test
    public void testEnregistrerCours() {
        ActionSimple a = new ActionSimple("Action test");
        Jour j = new Jour(2022,1);
        a.enrgCours(j, 18);
        assertEquals(18 ,a.valeur(j));
    }


    @Test
    void testEnrJourJourNull() throws EnrgCoursException {
        EnrgCoursException exception = assertThrows(EnrgCoursException.class, () -> {
            ActionSimple a = new ActionSimple("Action test");
            a.enrgCours(null, 18);
        });
    }

    @Test
    void testEnrJourValeurNegative() throws EnrgCoursException {
        EnrgCoursException exception = assertThrows(EnrgCoursException.class, () -> {
            ActionSimple a = new ActionSimple("Action test");
            Jour j = new Jour(2022,21);
            a.enrgCours(j, -18);
        });
    }

    @Test
    void testEnrJourCoursDejaEnregistrerCeJour() throws EnrgCoursException {
        ActionSimple a = new ActionSimple("Action test");
        Jour j = new Jour(2022,21);
        a.enrgCours(j, 18);

        EnrgCoursException exception = assertThrows(EnrgCoursException.class, () -> {
            a.enrgCours(j, 18);
        });
    }

}
