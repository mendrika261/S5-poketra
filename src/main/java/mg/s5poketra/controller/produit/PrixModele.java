package mg.s5poketra.controller.produit;

import database.core.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.s5poketra.DaoConfig;
import mg.s5poketra.exception.ValidationException;
import mg.s5poketra.model.MatierePremiere;
import mg.s5poketra.model.produit.Modele;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/listeParPrix/produit")
public class PrixModele extends HttpServlet {
    final String FORM = "/modele/PrixModele.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection dbConnection = null;

        try {
            dbConnection = DaoConfig.DATABASE.createConnection();

        } catch (SQLException e) {
            req.setAttribute("error",  "Erreur lors de la récuperation de donnée");
            dbConnection.rollback();
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        req.getRequestDispatcher(FORM).forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection dbConnection = null;
        try {
            dbConnection = DaoConfig.DATABASE.createConnection();

            double min = Double.parseDouble(req.getParameter("min"));
            double max = Double.parseDouble(req.getParameter("max"));

            if (min < 0 || max < 0)
                throw new ValidationException("Le prix minimum et maximum ne peut être négatif");

            if (min > max)
                throw new ValidationException("Le prix minimum doit être inférieur ou égal au prix maximum");

            req.setAttribute("modelViewList", Modele.getListeModelParPrix(dbConnection, min, max));
            req.setAttribute("min", min);
            req.setAttribute("max", max);
        } catch (SQLException e) {
            req.setAttribute("error",  "Erreur lors de la récuperation de donnée");
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
