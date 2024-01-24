package mg.s5poketra.model.stock;

import database.core.GenericDAO;

public class v_stock extends GenericDAO {
  private String id;
  private String nom;
  private double sum;

  public String getId() { return id; }

  public void setId(String id) { this.id = id; }

  public String getNom() { return nom; }

  public void setNom(String nom) { this.nom = nom; }

  public double getSum() { return sum; }

  public void setSum(double sum) { this.sum = sum; }
}
