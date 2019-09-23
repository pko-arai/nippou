package controllers.employees;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import utils.DBUtil;

/**
 * Servlet implementation class EmployeesSearch
 */
@WebServlet("/employees/search")
public class EmployeesSearch extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeesSearch() {
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

      //クエリの結果を変数へ格納、該当する名前のレコードを取得
        try{List<Employee> employee_search_result = em.createNamedQuery("getEmployeesName" , Employee.class)
                .setParameter("name",employee_name + "%" )
                .getResultList();

        System.out.println(employee_search_result);

      //検索結果の数を取得
        long search_employees_count = (long)em.createNamedQuery("getSearchEmployeesCount", Long.class)
                .setParameter("name_count", employee_name + "%")
                .getSingleResult();
        em.close();

        request.setAttribute("employees", employee_search_result);
        request.setAttribute("employees_count", search_employees_count);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/index.jsp");
        rd.forward(request, response);}
        catch(Exception ignored){
            request.setAttribute("flush", "お探しのデータはみつかりません");
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/employees/index.jsp");
            rd.forward(request, response);
        }


    }

}
