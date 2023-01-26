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
import java.util.Objects;

/**
 *
 * @author perussel
 */
public class ActionSimple extends Action {
    /**
     * Map to contain the Cours of an Action.
     */
    private final Map<Jour, Double> mapCours;

    /**
     * Constructor of the class ActionSimple.
     * @param libelle libelle
     */
    public ActionSimple(final String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        this.mapCours = new HashMap<>();
    }


    /**
     * Méthode pour enregistrer la valeur d'une ActionSimple pour un jour donné.
     * @param j : Jour pour lequel enregistrer la valeur de l'action
     * @param v : Valeur de l'action
     */
    public void enrgCours(final Jour j, final double v) {
        if (j == null) {
            throw new IllegalArgumentException("Jour null");
        }
        if (v < 0) {
            throw new IllegalArgumentException("Valeur négative");
        }
        if (!this.mapCours.containsKey(j)) {
            this.mapCours.put(j, v);
        } else {
            throw new IllegalArgumentException("Cours déjà enregistré pour ce jour");
        }
    }

    /**
     * Fonction pour connaître la valeur d'une action simple.
     * @param j : Jour pour lequel on veut connaitre la valeur de l'action
     * @return float
     */
    @Override
    public double valeur(final Jour j) {
        if (j == null) {
            throw new IllegalArgumentException("Jour null");
        }
        if (this.mapCours.containsKey(j)) {
            return this.mapCours.get(j);
        } else {
            throw new IllegalArgumentException("Pas de cours pour ce jour");
        }
    }

    /**
     * See if two class are equals.
     * @param o Objet
     * @return true if equals false else
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ActionSimple that = (ActionSimple) o;
        return mapCours.equals(that.mapCours);
    }

    /**
     * Hash the object.
     * @return HashCode of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mapCours);
    }
}
