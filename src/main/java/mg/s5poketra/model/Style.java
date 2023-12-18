package mg.s5poketra.model;

import database.core.DBConnection;
import database.core.GenericDAO;
import database.exception.object.NotIdentifiedInDatabaseException;
import mg.s5poketra.exception.ValidationException;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Style extends GenericDAO {
    String nom;

    public Style() {
    }

    public Style(String nom) throws ValidationException {
        setNom(nom);
    }

    public static List<MatierePremiere> getMatierePremierePossible(DBConnection dbConnection, String idStyle) throws ValidationException, SQLException {
        String sql = "SELECT * FROM style_mp WHERE id_style = ?";
        PreparedStatement preparedStatement = dbConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1, idStyle);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<MatierePremiere> matierePremieres = new ArrayList<>();
        MatierePremiere matierePremiere;
        while (resultSet.next()) {
            matierePremiere = new MatierePremiere();
            matierePremiere.setId(resultSet.getString("id_mp"));
            matierePremiere.setNom(resultSet.getString("nom"));
            matierePremieres.add(matierePremiere);
        }
        resultSet.close();
        preparedStatement.close();

        return matierePremieres;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) throws ValidationException {
        if (nom == null || nom.isEmpty())
            throw new ValidationException("Le nom du style ne peut pas être vide");
        this.nom = nom;
    }
}
