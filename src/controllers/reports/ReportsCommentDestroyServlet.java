package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ReportComment;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsCommentDestroyServlet
 */
@WebServlet("/reports/commentdestroy")
public class ReportsCommentDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsCommentDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();

        ReportComment rc = em.find(ReportComment.class, Integer.valueOf(request.getParameter("comment_destroy_id")));

        em.getTransaction().begin();
        em.remove(rc);
        em.getTransaction().commit();
        em.close();

        request.getSession().setAttribute("flush", "コメントを削除しました");

        response.sendRedirect(request.getContextPath() + "/reports/index");

    }
}