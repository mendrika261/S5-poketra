package mg.s5poketra.model.produit;

import database.core.DBConnection;
import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Modele extends GenericDAO {
    String nom;
    String idStyle;

    public Modele(String nom, String idStyle) throws ValidationException {
        this.setNom(nom);
        this.setIdStyle(idStyle);
    }

    public Modele() {

    }

    public static List<MpModelView> getListeModele(DBConnection dbConnection, String idMatierePremiere) throws SQLException {
        String sql = null;
        if(idMatierePremiere != null){
             sql = "SELECT * FROM v_liste WHERE \"idMatierePremiere\" = ?";
        }
        else {
            sql = "SELECT * FROM v_liste";
        }
        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
        if(idMatierePremiere != null){
            preparedStatement.setString(1, idMatierePremiere);
        }
        ResultSet resultSet = preparedStatement.executeQuery();

        List<MpModelView> mpModelViews = new ArrayList<>();
        MpModelView mpModelView;
        while (resultSet.next()) {
            mpModelView = new MpModelView();

            mpModelView.setIdFormat(resultSet.getString("idFormat"));
            mpModelView.setNomModele(resultSet.getString("nom"));
            mpModelView.setNomFormat(resultSet.getString("nomFormat"));

            if(idMatierePremiere != null) {
                mpModelView.setQuantite(resultSet.getDouble("quantite"));
                mpModelView.setIdModele(resultSet.getString("idModele"));
                mpModelView.setIdMatierePremiere(resultSet.getString("idMatierePremiere"));
            }

            mpModelViews.add(mpModelView);
        }
        resultSet.close();
        preparedStatement.close();

        return mpModelViews;
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

    public String getIdStyle() {
        return idStyle;
    }

    public void setIdStyle(String idStyle) throws ValidationException {
        if (idStyle == null || idStyle.isEmpty()) {
            throw new ValidationException("L'id du style ne peut pas être vide");
        }
        this.idStyle = idStyle;
    }
}
