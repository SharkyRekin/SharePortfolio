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

import java.util.HashMap;
import java.util.Map;

/**
 * Portefeuille class.
 */
public class Portefeuille {
    /**
     * Map to keep all the Action and number of it.
     */
    private final HashMap<Action, Integer> mapLignes;

    /**
     * Constructor of Portefeuille class.
     */
    public Portefeuille() {
        this.mapLignes = new HashMap<>();
    }

    /**
     * Méthode permettant d'ajouter des actions à son portefeuille.
     *
     * @param a   : L'action à acheter
     * @param qte : Qte d'actions à acheter
     * @throws AchatImpossibleException Achat Impossible
     */
    public void acheter(final Action a, final int qte) throws AchatImpossibleException {
        if (qte < 0) {
            throw new AchatImpossibleException("Achat inférieur à 0");
        } else {
            if (this.mapLignes.containsKey(a)) {
                Integer qtyAlreadyPossessed = this.mapLignes.get(a);
                this.mapLignes.replace(a, qtyAlreadyPossessed + qte);
            } else {
                this.mapLignes.put(a, qte);
            }
        }
    }

    /**
     * Fonction permettant d'afficher le portefeuille de manière compréhensible.
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Mes actions : \n");
        for (Map.Entry<Action, Integer> entry : this.mapLignes.entrySet()) {
            sb.append(entry.getKey());
            sb.append(" : ");
            sb.append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Méthode pour vendre les actions du portefeuille.
     * @param a : L'action à vendre
     * @param qte : Quantité d'actions à vendre
     * @throws VenteImpossibleException Vente impossible
     */
    public void vendre(final Action a, final int qte) throws VenteImpossibleException {
        if (this.mapLignes.containsKey(a)) {
            Integer qtyAlreadyPossessed = this.mapLignes.get(a);
            if (qtyAlreadyPossessed - qte < 0) {
                throw new VenteImpossibleException("Vente impossible, montant trop important");
            } else {
                this.mapLignes.replace(a, qtyAlreadyPossessed - qte);
            }
        } else {
            throw new VenteImpossibleException("Vente impossible, vous ne possédez pas cette action");
        }
    }

    /**
     * Fonction pour afficher la quantité possédée d'une action donnée dans le portefeuille.
     * @param a : L'action dont on veut consulter la quantité
     * @return String
     */
    public String getQteAchat(final Action a) {
        int quantite;
        if (this.mapLignes.containsKey(a)) {
            quantite = this.mapLignes.get(a);
            return String.format("Vous avez %d" + " de l'action %s", quantite, a.getLibelle());
        }
        return "Cette action n'est pas contenue dans le portefeuille";
    }

    /**
     * Get the value of a portefeuille
     * @param j : Le jour pour lequel on veut la valeur de notre portefeuille
     * @return float
     */
    public double valeur(final Jour j) {
        double total = 0;
        for (Map.Entry<Action, Integer> entry : this.mapLignes.entrySet()) {
            total += entry.getValue() * entry.getKey().valeur(j);
        }
        return total;
    }

    /**
     * Getter of MapLignes.
     * @return MapLignes
     */
    public Map<Action, Integer> getMapLignes() {
        return mapLignes;
    }
}
