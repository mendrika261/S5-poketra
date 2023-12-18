package mg.s5poketra.model;

import database.core.DBConnection;
import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class MPStyle extends GenericDAO {
    String idStyle;
    String idMatierePremiere;

    public MPStyle() {
    }

    public MPStyle(String idStyle, String idMatierePremiere) throws ValidationException {
        setIdStyle(idStyle);
        setIdMatierePremiere(idMatierePremiere);
    }

    public boolean exists(DBConnection dbConnection) throws ValidationException {
        try {
            Object o = new MPStyle().get(dbConnection, "\"idStyle\" = '" + getIdStyle() + "' AND \"idMatierePremiere\" = '" + getIdMatierePremiere() + "'");
            if (o != null)
                return true;
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
            throw new ValidationException("Erreur lors de la vérification de l'existance de la matière première");
        }
        return false;
    }

    public String getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(String idStyle) throws ValidationException {
        if(idStyle == null || idStyle.isEmpty())
            throw new ValidationException("Le style ne peut pas être vide");
        this.idStyle = idStyle;
    }

    public String getIdMatierePremiere() {
        return idMatierePremiere;
    }

    public void setIdMatierePremiere(String idMatierePremiere) throws ValidationException {
        if(idMatierePremiere == null || idMatierePremiere.isEmpty())
            throw new ValidationException("La matière première ne peut pas être vide");
        this.idMatierePremiere = idMatierePremiere;
    }
}
