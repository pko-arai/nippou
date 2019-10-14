package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Report;
import models.ReportComment;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCommentEdit
 */
@WebServlet("/reports/commentedit")
public class ReportsCommentEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsCommentEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        EntityManager em = DBUtil.createEntityManager();

        System.out.println(request.getParameter("id"));

        Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
        int report_id = r.getId();

        Integer c_id = em.createNamedQuery("comment_select" , Integer.class)
                .setParameter("report_id", report_id)
                .getSingleResult();

        ReportComment rc = em.find(ReportComment.class , c_id);

        em.close();

        request.setAttribute("reportcomment", rc);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/commentedit.jsp");
        rd.forward(request, response);
    }
}
