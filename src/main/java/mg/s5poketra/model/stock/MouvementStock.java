package mg.s5poketra.model.stock;

import database.core.DBConnection;
import database.core.GenericDAO;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import mg.s5poketra.exception.ValidationException;
import mg.s5poketra.model.MatierePremiere;
import mg.s5poketra.model.produit.Modele;
import mg.s5poketra.model.produit.MpModelView;

public class MouvementStock extends GenericDAO {
  private LocalDateTime localDateTime = LocalDateTime.now();
  private String idMatierePremiere;
  private double quantite;
  private double prixU = 0;

  public LocalDateTime getLocalDateTime() { return localDateTime; }

  public void setLocalDateTime(LocalDateTime localDateTime) {
    this.localDateTime = localDateTime;
  }

  public String getIdMatierePremiere() { return idMatierePremiere; }

  public void setIdMatierePremiere(String idMatierePremiere) {
    this.idMatierePremiere = idMatierePremiere;
  }

  public double getQuantite() { return quantite; }

  public void setQuantite(double quantite) { this.quantite = quantite; }

  public void setQuantite(String quantite) {
    setQuantite(Double.parseDouble(quantite));
  }

  public double getPrixU() { return prixU; }

  public void setPrixU(double prixU) throws ValidationException {
    if (prixU < 0)
      throw new ValidationException(
          "Le prix unitaire ne peut pas être négatif");
    this.prixU = prixU;
  }

  public static void entrer(String idMatierePremiere, double quantite,
                            DBConnection dbConnection)
      throws SQLException, InvocationTargetException, NoSuchMethodException,
             IllegalAccessException, ValidationException {
    MouvementStock mouvementStock = new MouvementStock();
    if (quantite <= 0)
      throw new ValidationException(
          "La quantité ne peut être négatif ou nulle");
    mouvementStock.setQuantite(quantite);
    mouvementStock.setIdMatierePremiere(idMatierePremiere);
    mouvementStock.save(dbConnection);
  }

  public static void entrer(String idMatierePremiere, double quantite,
                            DBConnection dbConnection,
                            LocalDateTime localDateTime)
      throws SQLException, InvocationTargetException, NoSuchMethodException,
             IllegalAccessException, ValidationException {
    MouvementStock mouvementStock = new MouvementStock();
    if (quantite <= 0)
      throw new ValidationException(
          "La quantité ne peut être négatif ou nulle");
    mouvementStock.setQuantite(quantite);
    mouvementStock.setIdMatierePremiere(idMatierePremiere);
    mouvementStock.setLocalDateTime(localDateTime);
    mouvementStock.save(dbConnection);
  }

  public static v_stock getReste(String idMatierePremiere,
                                 DBConnection dbConnection)
      throws SQLException, InvocationTargetException, NoSuchMethodException,
             InstantiationException, IllegalAccessException {
    return (v_stock) new v_stock().get(dbConnection,
                                       "\"id\" = '" + idMatierePremiere + "'");
  }

  public static void sortir(String idMatierePremiere, double quantite,
                            DBConnection dbConnection)
      throws SQLException, InvocationTargetException, NoSuchMethodException,
             IllegalAccessException, ValidationException,
             InstantiationException {
    if (quantite <= 0)
      throw new ValidationException(
          "La quantité ne peut être négatif ou nulle");
    MatierePremiere mp = (MatierePremiere) new MatierePremiere().getById(
        dbConnection, idMatierePremiere);
    double reste =
        MouvementStock.getReste(idMatierePremiere, dbConnection).getSum();
    if (reste < quantite)
      throw new ValidationException(
          "La quantité de matière première: " + mp.getNom() +
          " est insuffisante de " + (quantite - reste));
    MouvementStock mouvementStock = new MouvementStock();
    mouvementStock.setQuantite(-quantite);
    mouvementStock.setIdMatierePremiere(idMatierePremiere);
    mouvementStock.save(dbConnection);
  }

  public static void sortirModele(String idModele, double quantite,
                                  DBConnection dbConnection)
      throws ValidationException, SQLException, InvocationTargetException,
             NoSuchMethodException, IllegalAccessException,
             InstantiationException {
    if (quantite <= 0)
      throw new ValidationException(
          "La quantité ne peut être négatif ou nulle");
    List<MpModelView> mps = Modele.getMatierePremiere(dbConnection, idModele);
    System.out.println(mps);
    for (MpModelView mpId : mps) {
      sortir(mpId.getIdMatierePremiere(), mpId.getQuantite() * quantite,
             dbConnection);
    }
  }
}
