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
 * ActionComposee Class.
 */
public class ActionComposee extends Action {
    /**
     * Const to transform to percentage.
     */
    private static final int PERCENT = 100;

    /**
     * Map to keep all the ActionSimple and their percentage.
     */
    private final Map<ActionSimple, Double> mapPanier;

    /**
     * Follow the percent of actions.
     */
    private double currentFill;

    /**
     * Constructor of ActionComposee.
     * @param libelle String
     */
    public ActionComposee(final String libelle) {
        super(libelle);
        this.mapPanier = new HashMap<>();
        this.currentFill = 0;
    }

    /**
     * Méthode pour définir la composition d'une action composée.
     * @param as : Action simple
     * @param pourcentage : Pourcentage de l'action simple contenu dans l'action composée
     */
    public void enrgComposition(final ActionSimple as, final double pourcentage) {
        if (pourcentage < 0 || pourcentage > 1) {
            throw new IllegalArgumentException("Le pourcentage doit être compris entre 0 et 1");
        }
        if (as == null) {
            throw new IllegalArgumentException("L'ActionSimple ne doit pas etre null");
        }
        if (this.currentFill + pourcentage > 1) {
            throw new IllegalArgumentException("Le pourcentage total d'action ne doit pas dépasser 1 (Pourcentage actuel :" + this.currentFill + ")");
        }
        this.currentFill += pourcentage;
        this.mapPanier.put(as, pourcentage);
    }

    /**
     * Fonction permettant de calculer et retourner la valeur d'une action composée pour un jour donné.
     *
     * @param j : Jour
     * @return float
     */
    @Override
    public double valeur(final Jour j) {
        double valeur = 0;
        for (Map.Entry<ActionSimple, Double> entry : this.mapPanier.entrySet()) {
            valeur += entry.getValue() * entry.getKey().valeur(j);
        }
        return valeur;
    }

    /**
     * Méthode pour récupérer la composition d'une action de manière compréhensible.
     * @return String
     */
    public String getComposition() {
        StringBuilder sb = new StringBuilder();
        for (var action : this.mapPanier.entrySet()) {
            sb.append(action.getKey().getLibelle());
            sb.append(":");
            sb.append(action.getValue() * PERCENT);
            sb.append("%\n");
        }
        return sb.toString();
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
        ActionComposee that = (ActionComposee) o;
        return mapPanier.equals(that.mapPanier);
    }

    /**
     * Hash the object.
     * @return HashCode of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mapPanier);
    }
}
