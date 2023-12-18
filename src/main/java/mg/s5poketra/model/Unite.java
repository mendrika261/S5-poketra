package mg.s5poketra.model;

import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

public class Unite extends GenericDAO {
    String nom;

    public Unite() {
    }

    public Unite(String nom) throws ValidationException {
        setNom(nom);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws ValidationException {
        if (nom == null || nom.isEmpty())
            throw new ValidationException("Le nom de l'unité ne peut pas être vide");
        this.nom = nom;
    }
}
