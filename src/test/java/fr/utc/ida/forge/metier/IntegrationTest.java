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

import fr.utc.ida.forge.exception.AchatImpossibleException;
import fr.utc.ida.forge.exception.VenteImpossibleException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class IntegrationTest {

    @Test
    void testRun() throws VenteImpossibleException, AchatImpossibleException {
        ActionSimple bnp = new ActionSimple("BNP");
        ActionSimple axa = new ActionSimple("AXA");
        ActionComposee bqAss = new ActionComposee("Banque-Assurance");

        assertEquals("BNP", bnp.getLibelle());
        assertEquals("AXA", axa.getLibelle());
        assertEquals("Banque-Assurance", bqAss.getLibelle());

        Jour j1 = new Jour(2014, 1);
        Jour j2 = new Jour(2014, 2);

        assertNotEquals(j1, j2);

        bqAss.enrgComposition(axa, 0.3f);
        bqAss.enrgComposition(bnp, 0.7f);

        axa.enrgCours(j1, 200);
        axa.enrgCours(j2, 250);
        bnp.enrgCours(j1, 100);
        bnp.enrgCours(j2, 200);

        assertEquals(200, axa.valeur(j1));
        assertNotEquals(100, bnp.valeur(j2));

        Portefeuille p;
        p = new Portefeuille();
        p.acheter(axa, 10);
        p.acheter(bnp, 20);
        p.acheter(bqAss, 5);
        p.acheter(bqAss, 15);
        p.vendre(axa, 5);
        p.vendre(axa, 5);

        assertThrows(VenteImpossibleException.class, () -> p.vendre(axa, 5));
        assertThrows(VenteImpossibleException.class, () -> p.vendre(bnp, 50));
    }
}
