package mg.s5poketra.controller.personnel;

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
import mg.s5poketra.model.personnel.Personnel;
import mg.s5poketra.model.personnel.Poste;
import mg.s5poketra.model.produit.Format;
import mg.s5poketra.model.produit.Modele;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/personnel/personnel")
public class InsertPersonnel extends HttpServlet {
    final String FORM = "/personnel/Personnel.jsp";
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection dbConnection = null;
        try {
            dbConnection = DaoConfig.DATABASE.createConnection();


            Personnel poste = new Personnel();
            poste.setNom(req.getParameter("nom"));
            poste.setDateNaissance(LocalDate.parse(req.getParameter("dateNaissance")));
            poste.save(dbConnection);

            dbConnection.commit();
        /*} catch (ValidationException e) {
            req.setAttribute("error",  e.getMessage());
            dbConnection.rollback();*/
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            req.setAttribute("error",  "Erreur lors de l'ajout");
            dbConnection.rollback();
            e.printStackTrace();
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.close();
            req.getRequestDispatcher(FORM).forward(req, resp);}
    }
}
