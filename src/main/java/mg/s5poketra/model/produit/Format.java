package mg.s5poketra.model.produit;

import database.core.GenericDAO;
import mg.s5poketra.exception.ValidationException;

public class Format extends GenericDAO {
   String nomFormat;
   String idModele;

    public Format() {

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
