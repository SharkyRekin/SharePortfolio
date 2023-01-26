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

import java.util.Objects;

/**
 * Class Jour.
 * @param annee
 * @param noJour
 */
public record Jour(int annee, int noJour) {
    /**
     * Const to set the min year.
     */
    private static final int MIN_YEAR = 1975;

    /**
     * Const to set the max number of day in a year.
     */
    private static final int MAX_DAY = 365;

    /**
     * Verification of parameters for the constructor.
     * @param annee Annee
     * @param noJour noJour
     */
    public Jour {
        if (annee < MIN_YEAR) {
            throw new IllegalArgumentException("Pas d'année avant 1975");
        }
        if (noJour < 1 || noJour > MAX_DAY) {
            throw new IllegalArgumentException("Le jour ne peut être négatif ou supérieur à 365");
        }
    }

    /**
     * @param obj Object
     * @return boolean
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jour other = (Jour) obj;
        if (this.annee != other.annee) {
            return false;
        }
        return this.noJour == other.noJour;
    }

    @Override
    public String toString() {
        return "Annee " + annee + " jour " + noJour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(annee, noJour);
    }
}
