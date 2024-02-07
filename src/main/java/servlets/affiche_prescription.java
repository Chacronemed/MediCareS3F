package servlets;

import beans.prescription;
import beans.rdv_dash;
import dao.dao_factory;
import dao.prescription_dao;
import dao.rdv_dao;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet(name = "affiche_prescription", value = "/affiche_prescription")
public class affiche_prescription extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dao_factory daoFactory = dao_factory.getInstance();
        prescription_dao prescriptionDao = daoFactory.get_prescription_dao();
        List<prescription> list_pres = prescriptionDao.get_prescription(1);
        request.setAttribute("list_pres", list_pres);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/affiche_prescription.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}