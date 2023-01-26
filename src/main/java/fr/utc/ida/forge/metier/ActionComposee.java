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

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author perussel
 */
public class ActionComposee extends Action {
    // attribut lien
    Map<ActionSimple, Float> mapPanier;

    public ActionComposee(String libelle) {
        super(libelle);
        this.mapPanier = new HashMap();
    }

    /**
     * Méthode pour définir la composition d'une action composée
     * @param as : Action simple 
     * @param pourcentage : Pourcentage de l'action simple contenu dans l'action composée 
     */
    public void enrgComposition(ActionSimple as, float pourcentage) {
        this.mapPanier.put(as, pourcentage);
    }

    /**
     * Fonction permettant de calculer et retourner la valeur d'une action composée pour un jour donné
     * @param j : Jour 
     * @return float
     */
    @Override
    public float valeur(Jour j) {
        float valeur = 0;
        for (Map.Entry<ActionSimple, Float> entry : this.mapPanier.entrySet()) {
            valeur += entry.getValue() * entry.getKey().valeur(j);
        }
        return valeur;
    }

    /**
     * Méthode pour récupérer la composition d'une action de manière compréhensible
     * @return String
     */
    public String getComposition() {
        String output = "";
        for (var action : this.mapPanier.entrySet()) {
            output += action.getKey().getLibelle()+":"+action.getValue()*100+"%\n";
        }
        return output;
    }
    
    /**
     * Méthode pour récupérer Map Panier
     * @return Map<ActionSimple, Float>
     */
    public Map<ActionSimple, Float> getMapPanier() {
        return this.mapPanier;
    }
}
