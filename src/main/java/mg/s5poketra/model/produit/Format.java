package mg.s5poketra.model.produit;

import database.core.DBConnection;
import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Format extends GenericDAO {
   String nomFormat;
   String idModele;

    public Format() {
    }

    public static List<MpModelView> getListeModele(DBConnection dbConnection) throws SQLException {
        String sql = "SELECT * FROM v_format";
        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
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

            mpModelViews.add(mpModelView);
        }
        resultSet.close();
        preparedStatement.close();

        return mpModelViews;
    }

    public String getNomFormat() {
        return nomFormat;
    }

    public void setNomFormat(String nomFormat) throws ValidationException {
        if (nomFormat == null || nomFormat.isEmpty()) {
            throw new ValidationException("Le nom ne peut pas être vide");
        }
        this.nomFormat = nomFormat;
    }

    public String getIdModele() {
        return idModele;
    }

    public void setIdModele(String idModele) throws ValidationException {
        if (idModele == null || idModele.isEmpty()) {
            throw new ValidationException("L'id du style ne peut pas être vide");
        }
        this.idModele = idModele;
    }

    public Format(String nomFormat, String idModele) throws ValidationException {
        this.setNomFormat(nomFormat);
        this.setIdModele(idModele);
    }
}
