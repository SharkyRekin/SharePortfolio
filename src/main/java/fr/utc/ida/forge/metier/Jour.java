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

/**
 * @author perussel
 */
public record Jour(int annee, int noJour) {

    public Jour {
        if (annee < 1975) {
            throw new IllegalArgumentException("Pas d'année avant 1975");
        }
        if (noJour < 1 || noJour > 365) {
            throw new IllegalArgumentException("Le jour ne peut être négatif ou supérieur à 365");
        }
    }

    /**
     * Get the value of annee
     *
     * @return the value of annee
     */
    @Override
    public int annee() {
        return annee;
    }


    /**
     * Get the value of noJour
     *
     * @return the value of noJour
     */
    @Override
    public int noJour() {
        return noJour;
    }


    /**
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
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
}
