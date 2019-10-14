package controllers.reports;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.ReportComment;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCommentCreateServlet
 */
@WebServlet("/reports/commentcreate")
public class ReportsCommentCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsCommentCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        ReportComment rc = new ReportComment();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        rc.setCreated_at(currentTime);
        rc.setComment(request.getParameter("comment"));
        rc.setReport_id(Integer.valueOf(request.getParameter("report_id")));
        rc.setEmployee_id((Employee)request.getSession().getAttribute("login_employee"));
        rc.setUpdated_at(currentTime);

        request.setAttribute("reportcomment", rc);

        em.getTransaction().begin();
        em.persist(rc);
        em.getTransaction().commit();
        em.close();

        request.getSession().setAttribute("flush", "コメントが追加されました");

        response.sendRedirect(request.getContextPath() + "/reports/index");
    }
    }

