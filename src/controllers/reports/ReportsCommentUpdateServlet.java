package controllers.reports;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ReportComment;
import utils.DBUtil;

/**
 * Servlet implementation class ReportCommentUpdateServlet
 */
@WebServlet("/reports/commentupdate")
public class ReportsCommentUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsCommentUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = DBUtil.createEntityManager();
        ReportComment rc = em.find(ReportComment.class, Integer.parseInt(request.getParameter("comment_id")));
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        rc.setComment(request.getParameter("comment"));
        rc.setUpdated_at(currentTime);

        em.getTransaction().begin();
        em.getTransaction().commit();
        em.close();
        request.getSession().setAttribute("flush", "更新が完了しました。");

        response.sendRedirect(request.getContextPath() + "/reports/index");

    }

}
