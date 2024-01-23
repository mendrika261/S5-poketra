package mg.s5poketra.controller.service;

import database.core.DBConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mg.s5poketra.DaoConfig;
import mg.s5poketra.exception.ValidationException;
import mg.s5poketra.model.produit.Modele;
import mg.s5poketra.model.service.Service;
import mg.s5poketra.model.service.ServiceParProduit;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

@WebServlet("/service/insertionParProduit")
public class InsertionParProduit extends HttpServlet {
    final String FORM = "/service/serviceProduit.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBConnection dbConnection = null;

        try {
            dbConnection = DaoConfig.DATABASE.createConnection();
            req.setAttribute("serviceList", new Service().getAll(dbConnection));
            req.setAttribute("modeleList", new Modele().getAll(dbConnection));

        } catch (SQLException e) {
            req.setAttribute("error",  "Erreur lors de la récuperation de donnée");
            dbConnection.rollback();
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
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
            req.setAttribute("serviceList", new Service().getAll(dbConnection));
            req.setAttribute("modeleList", new Modele().getAll(dbConnection));

            ServiceParProduit service = new ServiceParProduit();
           service.setIdService(req.getParameter("idService"));
           service.setHeure(Double.parseDouble(req.getParameter("heure")));
           service.setIdModele(req.getParameter("idModele"));
           service.setPersonnel(Integer.parseInt(req.getParameter("personnel")));
           service.save(dbConnection);

            dbConnection.commit();
        } catch (ValidationException e) {
            req.setAttribute("error",  e.getMessage());
            dbConnection.rollback();
        } catch (SQLException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            req.setAttribute("error",  "Erreur lors de l'ajout");
            dbConnection.rollback();
            e.printStackTrace();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } finally {
            dbConnection.close();
            req.getRequestDispatcher(FORM).forward(req, resp);}
    }
}
