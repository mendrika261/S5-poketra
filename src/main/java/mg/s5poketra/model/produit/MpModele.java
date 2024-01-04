package mg.s5poketra.model.produit;

import database.core.DBConnection;
import database.core.GenericDAO;
import database.exception.object.NotIdentifiedInDatabaseException;
import mg.s5poketra.exception.ValidationException;
import mg.s5poketra.model.MatierePremiere;
import mg.s5poketra.model.Style;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class MpModele extends GenericDAO {
    String idFormat;
    String idMatierePremiere;
    Double quantite;

    public MpModele() {

    }

    public String getIdFormat() {
        return idFormat;
    }

    public void setIdFormat(String idFormat) throws ValidationException {
        if (idFormat == null || idFormat.isEmpty()) {
            throw new ValidationException("L'id du Format ne peut pas être vide");
        }
        this.idFormat = idFormat;
    }

    public String getIdMatierePremiere() {
        return idMatierePremiere;
    }

    public void setIdMatierePremiere(String idMatierePremiere) throws ValidationException {
        if (idMatierePremiere == null || idMatierePremiere.isEmpty()) {
            throw new ValidationException("L'id du matiere premiere ne peut pas être vide");
        }
        this.idMatierePremiere = idMatierePremiere;
    }

    public Double getQuantite() {
        return quantite;
    }

    public void setQuantite(Double quantite) throws ValidationException {
        if (quantite == null) {
            throw new ValidationException("La quantité ne peut pas être vide");
        }
        if (quantite <= 0) {
            throw new ValidationException("La quantité ne peut pas être négatif ou 0");
        }
        this.quantite = quantite;
    }

    public MpModele(String idFormat, String idMatierePremiere, Double quantite) throws ValidationException {
        this.setIdFormat(idFormat);
        this.setIdMatierePremiere(idMatierePremiere);
        this.setQuantite(quantite);
    }

    private static boolean verifyMpPossible(String idStyle, String idMatierePremiere, DBConnection dbConnection) throws ValidationException, SQLException, NotIdentifiedInDatabaseException {
        List<MatierePremiere> matierePremiereList = Style.getMatierePremierePossible(dbConnection, idStyle);
        for (MatierePremiere matierePremiere : matierePremiereList) {
            if (matierePremiere.getId().equals(idMatierePremiere)) {
                return true;
            }
        }
        return false;
    }

    public void save(String idStyle, DBConnection dbConnection) throws SQLException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, ValidationException, NotIdentifiedInDatabaseException {
        if (!verifyMpPossible(idStyle, getIdMatierePremiere(), dbConnection))
            throw new ValidationException("La matière premiere n'est pas compatible avec le style");
        super.save(dbConnection);
    }
}
