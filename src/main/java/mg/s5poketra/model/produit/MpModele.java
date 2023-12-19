package mg.s5poketra.model.produit;

import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

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

    public MpModele( String idFormat, String idMatierePremiere, Double quantite) throws ValidationException {
        this.setIdFormat(idFormat);
        this.setIdMatierePremiere(idMatierePremiere);
        this.setQuantite(quantite);
    }
}
