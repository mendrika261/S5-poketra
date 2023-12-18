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
import mg.s5poketra.model.Unite;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/style/liste-matiere-premiere")
public class listeMp extends HttpServlet {
    public static final String JSP = "/style/listeMp.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection dbConnection = null;
        try {
            dbConnection = DaoConfig.DATABASE.createConnection();

            req.setAttribute("styleList", new Style().getAll(dbConnection));

            if(req.getParameter("idStyle") != null)
                req.setAttribute("matierePremiereList", Style.getMatierePremierePossible(dbConnection, req.getParameter("idStyle")));

        } catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException | ValidationException e) {
            req.setAttribute("error",  "Erreur lors de la récuperation de donnée");
            dbConnection.rollback();
            e.printStackTrace();
        } finally {
            dbConnection.close();
        }

        req.getRequestDispatcher(JSP).forward(req, resp);
    }
}
