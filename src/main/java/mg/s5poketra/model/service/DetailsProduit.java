package mg.s5poketra.model.service;

import database.core.DBConnection;
import database.core.GenericDAO;
import mg.s5poketra.model.produit.MpModelView;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetailsProduit extends GenericDAO {
    String idFormat;
    double cout;
    double prixVente;
    double marge;
    String nomFormat;

    String nomModele;
    String nomStyle;

    public String getIdFormat() {
        return idFormat;
    }

    public void setIdFormat(String idFormat) {
        this.idFormat = idFormat;
    }

    public double getCout() {
        return cout;
    }

    public void setCout(double cout) {
        this.cout = cout;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public double getMarge() {
        return marge;
    }

    public void setMarge(double marge) {
        this.marge = marge;
    }

    public String getNomFormat() {
        return nomFormat;
    }

    public void setNomFormat(String nomFormat) {
        this.nomFormat = nomFormat;
    }

    public String getNomModele() {
        return nomModele;
    }

    public void setNomModele(String nomModele) {
        this.nomModele = nomModele;
    }

    public String getNomStyle() {
        return nomStyle;
    }

    public void setNomStyle(String nomStyle) {
        this.nomStyle = nomStyle;
    }

    public static List<DetailsProduit> getProduitBenefice(double min, double max, DBConnection connection) throws SQLException {
        String sql="SELECT * FROM v_cout_produit WHERE marge BETWEEN ? AND ?";
        PreparedStatement preparedStatement = connection.getConnection().prepareStatement(sql);
        preparedStatement.setDouble(1, min);
        preparedStatement.setDouble(2, max);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<DetailsProduit> liste = new ArrayList<>();
        DetailsProduit detail;
        while (resultSet.next()) {
            detail = new DetailsProduit();

            detail.setCout(resultSet.getDouble(2));
            detail.setIdFormat(resultSet.getString(1));
            detail.setPrixVente(resultSet.getDouble(3));
            detail.setMarge(resultSet.getDouble(4));
            detail.setNomFormat(resultSet.getString(5));
            detail.setNomModele(resultSet.getString(6));
            detail.setNomStyle(resultSet.getString(7));

            liste.add(detail);
        }
        resultSet.close();
        preparedStatement.close();

        return liste;
    }
}
