package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import models.ReportComment;
import models.ReportFavo;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

        int report_id = r.getId();

        Employee employee_id = (Employee) request.getSession().getAttribute("login_employee");

        long reportcomment_count = em.createNamedQuery("comment_count" , Long.class)
                .setParameter("report_id" , report_id)
                .getSingleResult();

        try{Integer id = em.createNamedQuery("id_select" ,  java.lang.Integer.class)
                .setParameter("report_id" , report_id)
                .setParameter("employee" , employee_id)
                .getSingleResult();
        ReportFavo rf = em.find(ReportFavo.class , id);
        request.setAttribute("reportfavo", rf);
        }catch(Exception ignored){}

        try{Integer c_id = em.createNamedQuery("comment_select" , Integer.class)
                .setParameter("report_id", report_id)
                .getSingleResult();
        ReportComment rc = em.find(ReportComment.class , c_id);
        request.setAttribute("reportcomment", rc);
        }catch(Exception ignored){}

        long reports_count = (long)em.createNamedQuery("getReportsCount", Long.class)
                .getSingleResult();
        em.close();
        request.setAttribute("reports_count", reports_count);
        request.setAttribute("reportcomment_count", reportcomment_count);
        request.setAttribute("report", r);
        request.setAttribute("_token", request.getSession().getId());
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
        rd.forward(request, response);
    }
}

