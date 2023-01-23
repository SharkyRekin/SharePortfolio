/*
 * Copyright 2023 ICHCHOU - JUNOT
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
