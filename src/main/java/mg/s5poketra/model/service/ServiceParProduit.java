package mg.s5poketra.model.service;

import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

public class ServiceParProduit extends GenericDAO {
  String idService;
  Double heure;
  String idModele;
  int personnel;

  public String getIdService() { return idService; }

  public void setIdService(String idService) throws ValidationException {
    if (idService == null || idService.isEmpty())
      throw new ValidationException("L'id du service ne peut pas être vide");
    this.idService = idService;
  }

  public Double getHeure() { return heure; }

  public void setHeure(Double heure) throws ValidationException {
    if (heure == null || heure < 0)
      throw new ValidationException(
          "Le nombre d'heure ne peut pas être vide ou négatif");
    this.heure = heure;
  }

  public String getIdModele() { return idModele; }

  public void setIdModele(String idModele) throws ValidationException {
    if (idModele == null || idModele.isEmpty())
      throw new ValidationException("L'id du modele ne peut pas être vide");
    this.idModele = idModele;
  }

  public int getPersonnel() { return personnel; }

  public void setPersonnel(int personnel) throws ValidationException {
    if (personnel < 0)
      throw new ValidationException(
          "Le nombre de personnel ne peut pas être vide ou négatif");
    this.personnel = personnel;
  }
}
