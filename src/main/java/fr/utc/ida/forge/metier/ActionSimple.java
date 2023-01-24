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

import fr.utc.ida.forge.exception.EnrgCoursException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class ActionSimple extends Action {

    // attribut lien
    private final Map<Jour, Float> mapCours;

    public ActionSimple(String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        this.mapCours = new HashMap<>();
    }
    
    
    /** 
     * Méthode pour enregistrer la valeur d'une ActionSimple pour un jour donné
     * @param j : Jour pour lequel enregistrer la valeur de l'action 
     * @param v : Valeur de l'action 
     */
    // enrg possible si pas de cours pour ce jour
    public void enrgCours(Jour j, float v) throws EnrgCoursException{
        if (j == null) {
            throw new EnrgCoursException("Jour null");
        }
        if (v < 0) {
            throw new EnrgCoursException("Valeur négative");
        }
        if (!this.mapCours.containsKey(j)) {
            this.mapCours.put(j, v);
        } else {
            throw new EnrgCoursException("Cours déjà enregistré pour ce jour");
        }
    }
    
    
    /** 
     * Fonction pour connaître la valeur d'une action simple 
     * @param j : Jour pour lequel on veut connaitre la valeur de l'action
     * @return float
     */
    @Override
    public float valeur(Jour j) {
        if (j == null)
            throw new IllegalArgumentException("Jour null");

        if (this.mapCours.containsKey(j)) {
            return this.mapCours.get(j);
        } else {
            throw new IllegalArgumentException("Pas de cours pour ce jour");
        }
    }
}
