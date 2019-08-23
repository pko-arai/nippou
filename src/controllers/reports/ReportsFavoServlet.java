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
       int report_id = Integer.parseInt(request.getParameter("report_id"));
       Employee employee_id = (Employee) request.getSession().getAttribute("login_employee");
       long report_id_count = em.createNamedQuery("checkRegisteredReport_id",Long.class)
               .setParameter("report_id", report_id)
               .setParameter("employee", employee_id)
               .getSingleResult();

       if(report_id_count > 1){
           em.close();
           request.getSession().setAttribute("flush", "既にいいねを押してます");
           response.sendRedirect(request.getContextPath() + "/reports/index");
       }else{

       rf.setReport_id(report_id);
       rf.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
       Timestamp currentTime = new Timestamp(System.currentTimeMillis());
       rf.setCreated_at(currentTime);
       rf.setUpdated_at(currentTime);

       request.setAttribute("reportfavo", rf);

       em.getTransaction().begin();
       em.persist(rf);
       em.getTransaction().commit();
       em.close();

       request.getSession().setAttribute("flush", "お気に入りに追加されました");
       response.sendRedirect(request.getContextPath() + "/reports/index");
    }
    }
}
