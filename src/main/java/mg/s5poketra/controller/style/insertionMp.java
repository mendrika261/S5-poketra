package mg.s5poketra.controller.style;

import database.core.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.s5poketra.DaoConfig;
import mg.s5poketra.exception.ValidationException;
import mg.s5poketra.model.MPStyle;
import mg.s5poketra.model.MatierePremiere;
import mg.s5poketra.model.Style;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/style/insertion-matiere-premiere")
public class insertionMp extends HttpServlet {
    final String FORM = "/style/insertionMpForm.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection dbConnection = null;
        try {
            dbConnection = DaoConfig.DATABASE.createConnection();

            req.setAttribute("styleList", new Style().getAll(dbConnection));
            req.setAttribute("matierePremiereList", new MatierePremiere().getAll(dbConnection));

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
            req.setAttribute("matierePremiereList", new MatierePremiere().getAll(dbConnection));


            MPStyle mpStyle = new MPStyle();
            mpStyle.setIdMatierePremiere(req.getParameter("idMatierePremiere"));
            mpStyle.setIdStyle(req.getParameter("idStyle"));
            if(mpStyle.exists(dbConnection))
                throw new ValidationException("Ce matière première est déjà associé à ce style");
            mpStyle.save(dbConnection);

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
            req.getRequestDispatcher(FORM).forward(req, resp);
        }
    }
}
