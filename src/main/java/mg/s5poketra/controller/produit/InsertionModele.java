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
import mg.s5poketra.model.Style;
import mg.s5poketra.model.Unite;
import mg.s5poketra.model.produit.Modele;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/modele/insertion")
public class InsertionModele extends HttpServlet {
    final String FORM = "/modele/InsertionModele.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection dbConnection = null;

        try {
            dbConnection = DaoConfig.DATABASE.createConnection();
            req.setAttribute("styleList", new Style().getAll(dbConnection));

        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            req.setAttribute("error",  "Erreur lors de la récuperation de donnée");
            dbConnection.rollback();
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        req.getRequestDispatcher(FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection dbConnection = null;
        try {
            dbConnection = DaoConfig.DATABASE.createConnection();
            req.setAttribute("styleList", new Style().getAll(dbConnection));


            Modele matierePremiere = new Modele();
            matierePremiere.setNom(req.getParameter("nom"));
            matierePremiere.setIdStyle(req.getParameter("idStyle"));
            matierePremiere.save(dbConnection);

            dbConnection.commit();
        } catch (ValidationException e) {
            req.setAttribute("error",  e.getMessage());
            dbConnection.rollback();
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                 InstantiationException e) {
            req.setAttribute("error",  "Erreur lors de l'ajout");
            dbConnection.rollback();
            e.printStackTrace();
        } finally {
            dbConnection.close();
            req.getRequestDispatcher(FORM).forward(req, resp);}
    }
}
