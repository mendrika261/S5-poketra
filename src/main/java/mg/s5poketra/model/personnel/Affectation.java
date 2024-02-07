package mg.s5poketra.model.personnel;

import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

import java.time.LocalDate;

public class Affectation extends GenericDAO {
    String idservice;
    String idPersonnel;
    LocalDate dateAffectation;

    public String getIdservice() {
        return idservice;
    }

    public void setIdservice(String idservice) throws ValidationException {
        if (idservice == null || idservice.isEmpty()) {
            throw new ValidationException("Le service ne peut pas être vide");
        }
        this.idservice = idservice;
    }

    public String getIdPersonnel() {
        return idPersonnel;
    }

    public void setIdPersonnel(String idPersonnel) throws ValidationException {
        if (idPersonnel == null || idPersonnel.isEmpty()) {
            throw new ValidationException("Le personnel ne peut pas être vide");
        }
        this.idPersonnel = idPersonnel;
    }

    public LocalDate getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(LocalDate dateAffectation) throws ValidationException {
        if (dateAffectation == null) {
            throw new ValidationException("La date d'affectation ne peut pas être vide");
        }
        this.dateAffectation = dateAffectation;
    }
}
