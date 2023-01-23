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
import org.junit.jupiter.api.Test;
/**
 *
 * @author junot
 */
public class ActionComposeTest {
    
    @Test
    public void enregistrerActionComposeTest() throws Exception { 
        ActionComposee ac = new ActionComposee("Action composée test");
        ActionSimple a = new ActionSimple("Action Simple");
        Jour j = new Jour(2022,1);
        ac.enrgComposition(a, 20);
        assertEquals(20 ,ac.valeur(j));
    }
    
}
