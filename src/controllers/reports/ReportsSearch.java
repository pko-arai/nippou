package controllers.reports;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsSearch
 */
@WebServlet("/reports/search")
public class ReportsSearch extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsSearch() {
        super();
        // TODO Auto-generated constructor stub
    }


    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

      //パラメータを取得し変数へ格納
        String employee_name = request.getParameter("search");

        //検索結果画面にいいねの数を表示させる
        List<Object[] > reports_favocount = em.createNamedQuery("favo_id_count", Object[].class)
                .getResultList();

        HashMap<Integer, String> map=new HashMap<Integer, String>();
        for (Object[] results : reports_favocount) {
            map.put(new Integer(results[0].toString()), results[1].toString());
        }


        //クエリの結果を変数へ格納、該当する名前のレコードを取得
        try{List<Employee> employee_search = em.createNamedQuery("getEmployeesName" , Employee.class)
                .setParameter("name",employee_name + "%" )
                .getResultList();

        //レポートテーブル内データ取得
        List<Report> r_employee_id = em.createNamedQuery("getCreateUser" , Report.class)
                .setParameter("employee", employee_search)
                .getResultList();

      //検索結果の数を取得
        long search_reports_count = (long)em.createNamedQuery("getSearchReportsCount", Long.class)
                .setParameter("employee", employee_search)
                .getSingleResult();

        em.close();

        request.setAttribute("reports", r_employee_id);
        request.setAttribute("reports_favocount", map);
        request.setAttribute("reports_count", search_reports_count);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
        rd.forward(request, response);}

        //エラー処理
        catch(Exception ignored){
            request.setAttribute("flush", "お探しのデータはみつかりません");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/index.jsp");
            rd.forward(request, response);
        }
    }
}