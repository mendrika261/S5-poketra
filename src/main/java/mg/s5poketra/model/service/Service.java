package mg.s5poketra.model.service;
import database.core.DBConnection;
import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
public class Service extends GenericDAO {
    String nomService;
    Double prixHoraire;

    public Service() {
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) throws ValidationException {
        if (nomService == null || nomService.isEmpty())
            throw new ValidationException("Le nom du service ne peut pas être vide");
        this.nomService = nomService;
    }

    public Double getPrixHoraire() {
        return prixHoraire;
    }

    public void setPrixHoraire(Double prixHoraire) throws ValidationException {
        if (prixHoraire == null || prixHoraire < 0)
            throw new ValidationException("Le prix horaire ne peut pas être négatif");
        this.prixHoraire = prixHoraire;
    }

    public Service(String nomService, Double prixHoraire) throws ValidationException {
        setNomService(nomService);
        setPrixHoraire(prixHoraire);
    }
}
