package mg.s5poketra.controller.style;

import database.core.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.s5poketra.DaoConfig;
import mg.s5poketra.exception.ValidationException;
import mg.s5poketra.model.Style;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/style/insertion")
public class InsertionStyle extends HttpServlet {
    final String FORM = "/style/styleForm.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection dbConnection = null;
        try {
            dbConnection = DaoConfig.DATABASE.createConnection();

            Style style = new Style();
            style.setNom(req.getParameter("nom"));
            style.save(dbConnection);

            dbConnection.commit();
        } catch (ValidationException e) {
            req.setAttribute("error",  e.getMessage());
            dbConnection.rollback();
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            req.setAttribute("error",  "Erreur lors de l'ajout");
            dbConnection.rollback();
            e.printStackTrace();
        } finally {
            dbConnection.close();
            req.getRequestDispatcher(FORM).forward(req, resp);
        }
    }
}
