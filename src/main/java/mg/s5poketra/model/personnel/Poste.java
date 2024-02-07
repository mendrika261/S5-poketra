package mg.s5poketra.model.personnel;

import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

public class Poste extends GenericDAO {
        String nom;
        double coefficient;

        double anciennete;

    public double getAnciennete() {
        return anciennete;
    }

    public void setAnciennete(double anciennete) throws ValidationException {
        if (anciennete < 0) throw new ValidationException("L'ancienneté ne peut pas être négatif");
        this.anciennete = anciennete;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws ValidationException {
        if (nom == null || nom.isEmpty()) {
            throw new ValidationException("Le nom ne peut pas être vide");
        }
        this.nom = nom;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(double coefficient) throws ValidationException {
        if (coefficient < 0) throw new ValidationException("Le coefficient ne peut pas être négatif");
        this.coefficient = coefficient;
    }
}
