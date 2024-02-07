package mg.s5poketra.model.personnel;

import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Personnel extends GenericDAO {
    String nom;
    LocalDate dateNaissance;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws ValidationException {
        if (nom == null || nom.isEmpty()) {
            throw new ValidationException("Le nom ne peut pas être vide");
        }
        this.nom = nom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) throws ValidationException {
        if (dateNaissance == null) {
            throw new ValidationException("La date de naissance ne peut pas être vide");
        }
        this.dateNaissance = dateNaissance;
    }
}
