package mg.s5poketra.controller.service;

import database.core.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import mg.s5poketra.DaoConfig;
import mg.s5poketra.exception.ValidationException;
import mg.s5poketra.model.produit.Modele;
import mg.s5poketra.model.service.DetailsProduit;

@WebServlet("/listeServiceDetails/produit")
public class DetailsPrix extends HttpServlet {
  final String FORM = "/service/ListDetails.jsp";
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    DBConnection dbConnection = null;

    try {
      dbConnection = DaoConfig.DATABASE.createConnection();

    } catch (SQLException e) {
      req.setAttribute("error", "Erreur lors de la récuperation de donnée");
      dbConnection.rollback();
      e.printStackTrace();
    } finally {
      dbConnection.close();
    }

    req.getRequestDispatcher(FORM).forward(req, resp);
  }
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    DBConnection dbConnection = null;
    try {
      dbConnection = DaoConfig.DATABASE.createConnection();

      double min = Double.parseDouble(req.getParameter("min"));
      double max = Double.parseDouble(req.getParameter("max"));

      if (min < 0 || max < 0)
        throw new ValidationException(
            "Le prix minimum et maximum ne peut être négatif");

      if (min > max)
        throw new ValidationException(
            "Le prix minimum doit être inférieur ou égal au prix maximum");

      req.setAttribute("detailsList", DetailsProduit.getProduitBenefice(
                                          min, max, dbConnection));
      req.setAttribute("min", min);
      req.setAttribute("max", max);
    } catch (SQLException e) {
      req.setAttribute("error", "Erreur lors de la récuperation de donnée");
      dbConnection.rollback();
      e.printStackTrace();
    } catch (ValidationException e) {
      req.setAttribute("error", e.getMessage());
    } finally {
      dbConnection.close();
      req.getRequestDispatcher(FORM).forward(req, resp);
    }
  }
}
