package pac1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Mainpage
 */
@WebServlet("/Mainpage")
public class Mainpage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {
		/*
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WebContent/NewFile.jsp");
		dispatcher.forward(request,response);
		*/

		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\"/>");
		out.println("<title>メインページ</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action = \"MainPage\" method=\"get\">");
		out.println("<input type=\"text\" name=\"input\" size=20>");
		out.println("<input type=\"submit\" value=\"呼び出し\"><br>");
		out.println("<input type=\"radio\" name=\"searchMode\" value=\"syokuzai\" checked>食材名検索");
		out.println("<input type=\"radio\" name=\"searchMode\" value=\"ryouri\">料理名検索");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");



	}

}
