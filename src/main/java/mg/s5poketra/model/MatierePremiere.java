package mg.s5poketra.model;

import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

public class MatierePremiere extends GenericDAO {
    String nom;
    String idUnite;

    public MatierePremiere() {
    }

    public MatierePremiere(String nom, String idUnite) throws ValidationException {
        setNom(nom);
        setIdUnite(idUnite);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws ValidationException {
        if (nom == null || nom.isEmpty())
            throw new ValidationException("Le nom de la matière première ne peut pas être vide");
        this.nom = nom;
    }

    public String getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(String idUnite) throws ValidationException {
        if (idUnite == null || idUnite.isEmpty())
            throw new ValidationException("L'identifiant de l'unité de la matière première ne peut pas être vide");
        this.idUnite = idUnite;
    }
}
