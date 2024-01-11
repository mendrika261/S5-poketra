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
        String sql = "SELECT * FROM v_modele_mp WHERE \"idMatierePremiere\" = ?";
        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, idMatierePremiere);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<MpModelView> mpModelViews = new ArrayList<>();
        MpModelView mpModelView;
        while (resultSet.next()) {
            mpModelView = new MpModelView();

            mpModelView.setIdFormat(resultSet.getString("id_format"));
            mpModelView.setNomModele(resultSet.getString("nom_modele"));
            mpModelView.setNomFormat(resultSet.getString("nom_format"));
            mpModelView.setIdStyle(resultSet.getString("id_style"));
            mpModelView.setNomStyle(resultSet.getString("nom_style"));
            mpModelView.setQuantite(resultSet.getDouble("quantite"));
            mpModelView.setIdModele(resultSet.getString("id_modele"));
            mpModelView.setIdMatierePremiere(resultSet.getString("idMatierePremiere"));

            mpModelViews.add(mpModelView);
        }
        resultSet.close();
        preparedStatement.close();

        return mpModelViews;
    }

    public static List<MpModelView> getListeModelParPrix(DBConnection dbConnection, double min, double max) throws SQLException {
        String sql = "SELECT * FROM v_prix_format WHERE prix BETWEEN ? AND ?";
        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
        preparedStatement.setDouble(1, min);
        preparedStatement.setDouble(2, max);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<MpModelView> mpModelViews = new ArrayList<>();
        MpModelView mpModelView;
        while (resultSet.next()) {
            mpModelView = new MpModelView();

            mpModelView.setIdFormat(resultSet.getString("id_format"));
            mpModelView.setNomModele(resultSet.getString("nom_modele"));
            mpModelView.setNomFormat(resultSet.getString("nom_format"));
            mpModelView.setNomStyle(resultSet.getString("nom_style"));
            mpModelView.setPrix(resultSet.getDouble("prix"));

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
