package mg.s5poketra;

import database.core.DBConnection;
import database.core.Database;
import database.exception.SQL.AttributeMissingException;
import database.exception.SQL.AttributeTypeNotExistingException;
import database.provider.PostgreSQL;
import java.sql.SQLException;
import mg.s5poketra.exception.ValidationException;
import mg.s5poketra.model.MPStyle;
import mg.s5poketra.model.MatierePremiere;
import mg.s5poketra.model.Style;
import mg.s5poketra.model.Unite;
import mg.s5poketra.model.produit.Format;
import mg.s5poketra.model.produit.Modele;
import mg.s5poketra.model.produit.MpModele;
import mg.s5poketra.model.service.Service;
import mg.s5poketra.model.service.ServiceParProduit;
import mg.s5poketra.model.stock.MouvementStock;

public class DaoConfig {
  public static final Database DATABASE =
      new PostgreSQL("localhost", "5432", "poketra", "mendrika", "");

  public static void main(String[] args)
      throws SQLException, ValidationException,
             AttributeTypeNotExistingException, AttributeMissingException {
    DBConnection dbConnection = DATABASE.createConnection();

    createTables(dbConnection);

    dbConnection.commit();
    dbConnection.close();
  }

  public static void createTables(DBConnection dbConnection)
      throws SQLException, AttributeTypeNotExistingException,
             AttributeMissingException, ValidationException {
    dbConnection.getDatabase().createSequenceFunction(
        dbConnection.getConnection());

    Unite unite = new Unite();
    unite.createTable(dbConnection);

    MatierePremiere matierePremiere = new MatierePremiere();
    matierePremiere.createTable(dbConnection);

    Style style = new Style();
    style.createTable(dbConnection);

    MPStyle mpStyle = new MPStyle();
    mpStyle.createTable(dbConnection);

    Modele modele = new Modele();
    modele.createTable(dbConnection);

    Format format = new Format();
    format.createTable(dbConnection);

    MpModele mpModele = new MpModele();
    mpModele.createTable(dbConnection);

    /*dbConnection.getDatabase().execute(dbConnection.getConnection(),
            "create view style_mp as select \"id_style\", \"idMatierePremiere\"
    id_mp, nom nom_mp from \"MPStyle\" JOIN \"MatierePremiere\" mp on
    \"idMatierePremiere\" = mp.id");

    dbConnection.getDatabase().execute(dbConnection.getConnection(),
            "create view v_liste as select m.id idModele, m.nom nom, f.id
    idFormat, f.nom nomFormat, mp.id idMatierePremiere, mp.nom
    nomMatierePremiere, mp.quantite quantite from \"Modele\" m JOIN \"Format\" f
    on m.\"idStyle\" = f.\"idModele\" JOIN \"MpModele\" mp on f.id =
    mp.\"idFormat\"");
     */
    MouvementStock mouvementStock = new MouvementStock();
    mouvementStock.createTable(dbConnection);

    Service service = new Service();
    service.createTable(dbConnection);

    ServiceParProduit serviceParProduit = new ServiceParProduit();
    serviceParProduit.createTable(dbConnection);
  }
}
