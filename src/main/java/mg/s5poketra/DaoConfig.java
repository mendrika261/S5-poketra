package mg.s5poketra;

import database.core.DBConnection;
import database.core.Database;
import database.exception.SQL.AttributeMissingException;
import database.exception.SQL.AttributeTypeNotExistingException;
import database.provider.PostgreSQL;
import mg.s5poketra.exception.ValidationException;
import mg.s5poketra.model.MPStyle;
import mg.s5poketra.model.MatierePremiere;
import mg.s5poketra.model.Style;
import mg.s5poketra.model.Unite;

import java.sql.SQLException;

public class DaoConfig {
    public static final Database DATABASE = new PostgreSQL("localhost", "5432", "poketra", "mendrika", "");

    public static void main(String[] args) throws SQLException, ValidationException, AttributeTypeNotExistingException, AttributeMissingException {
        DBConnection dbConnection = DATABASE.createConnection();

        createTables(dbConnection);

        dbConnection.commit();
        dbConnection.close();
    }

    public static void createTables(DBConnection dbConnection) throws SQLException, AttributeTypeNotExistingException, AttributeMissingException {
        dbConnection.getDatabase().createSequenceFunction(dbConnection.getConnection());

        Unite unite = new Unite();
        unite.createTable(dbConnection);

        MatierePremiere matierePremiere = new MatierePremiere();
        matierePremiere.createTable(dbConnection);

        Style style = new Style();
        style.createTable(dbConnection);

        MPStyle mpStyle = new MPStyle();
        mpStyle.createTable(dbConnection);
    }
}
