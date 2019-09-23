package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.ReportFavo;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsFavoDestroyServlet
 */
@WebServlet("/reports/destroy")
public class ReportsFavoDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsFavoDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        int report_id = Integer.parseInt(request.getParameter("report_id"));

        Employee employee_id = (Employee) request.getSession().getAttribute("login_employee");

        Integer id = em.createNamedQuery("id_select" ,  java.lang.Integer .class)
                .setParameter("report_id" , report_id)
                .setParameter("employee" , employee_id)
                .getSingleResult();


        ReportFavo rf = em.find(ReportFavo.class , id);

        em.getTransaction().begin();
        em.remove(rf);
        em.getTransaction().commit();
        em.close();

        request.getSession().setAttribute("flush","いいねを取り消しました");

        response.sendRedirect(request.getContextPath() + "/reports/index");

    }
}