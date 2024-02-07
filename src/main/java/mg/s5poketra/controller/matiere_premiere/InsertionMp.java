package mg.s5poketra.controller.matiere_premiere;

import database.core.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import mg.s5poketra.DaoConfig;
import mg.s5poketra.exception.ValidationException;
import mg.s5poketra.model.MatierePremiere;
import mg.s5poketra.model.Unite;

@WebServlet("/matiere_premiere/insertion")
public class InsertionMp extends HttpServlet {
  final String FORM = "/matiere_premiere/mpForm.jsp";
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    DBConnection dbConnection = null;

    try {
      dbConnection = DaoConfig.DATABASE.createConnection();
      req.setAttribute("uniteList", new Unite().getAll(dbConnection));

    } catch (SQLException | InvocationTargetException | NoSuchMethodException |
             InstantiationException | IllegalAccessException e) {
      req.setAttribute("error", "Erreur lors de la récuperation de donnée");
      dbConnection.rollback();
      e.printStackTrace();
    } finally {
      dbConnection.close();
    }

    req.getRequestDispatcher(FORM).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    DBConnection dbConnection = null;
    try {
      dbConnection = DaoConfig.DATABASE.createConnection();
      req.setAttribute("uniteList", new Unite().getAll(dbConnection));

      MatierePremiere matierePremiere = new MatierePremiere();
      matierePremiere.setNom(req.getParameter("nom"));
      matierePremiere.setIdUnite(req.getParameter("idUnite"));
      matierePremiere.setPrix(Double.parseDouble(req.getParameter("prix")));
      matierePremiere.save(dbConnection);

      dbConnection.commit();
    } catch (ValidationException e) {
      req.setAttribute("error", e.getMessage());
      dbConnection.rollback();
    } catch (SQLException | InvocationTargetException | NoSuchMethodException |
             IllegalAccessException | InstantiationException e) {
      req.setAttribute("error", "Erreur lors de l'ajout");
      dbConnection.rollback();
      e.printStackTrace();
    } finally {
      dbConnection.close();
      req.getRequestDispatcher(FORM).forward(req, resp);
    }
  }
}
