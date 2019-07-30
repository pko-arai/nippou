package controllers.reports;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ReportFavo;
import utils.DBUtil;


/**
 * Servlet implementation class ReportsFavoServlet
 */
@WebServlet("/reports/favo")
public class ReportsFavoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsFavoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       EntityManager em = DBUtil.createEntityManager();

       ReportFavo rf = new ReportFavo();
       rf.setUser_id((int) request.getSession().getAttribute("login_employee"));

       rf.setArticle_id((int)request.getSession().getAttribute("id"));

       Timestamp currentTime = new Timestamp(System.currentTimeMillis());
       rf.setCreated_at(currentTime);
       rf.setUpdated_at(currentTime);

       em.close();

       RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
       rd.forward(request, response);

       request.getSession().setAttribute("flush", "お気に入りに追加されました");






















    }

}
