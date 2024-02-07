package servlets;

import beans.maladie;
import dao.dossier_medicale_dao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.dao_factory;
import java.io.IOException;
import java.util.List;


@WebServlet(name = "dossiermedical", value = "/dossiermedical")
public class dossiermedical extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id_patient = Integer.parseInt(request.getParameter("id"));

        dao_factory daoFactory = dao_factory.getInstance();
        dossier_medicale_dao dossierMedicaleDao = daoFactory.get_dossier_medicale_dao();

        List<maladie> maladies = dossierMedicaleDao.listerMaladiesParPatient(id_patient);
        request.setAttribute("maladies",maladies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/mesMaladies.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}