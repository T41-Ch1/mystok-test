package pac1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchResultServlet
 */
@WebServlet("/SearchResultServlet")
public class SearchResultServlet extends HttpServlet {

	//static final String URL="jdbc:mysql://localhost/j2a1_gradedb?useSSL=false&serverTimezone=JST";

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response)
			throws ServletException, IOException {

		String[] inputData;
		String searchMode;
		String sql = "";
		ArrayList<Integer> recipeID = new ArrayList<Integer>();
		ArrayList<String> recipeTitle = new ArrayList<String>();
		ArrayList<String> recipeText = new ArrayList<String>();

		String input = request.getParameter("input"); //入力されたデータを格納
		input = input.replace("　", " "); //全角スペースを半角スペースに置換
		while (input.contains("  ")) input = input.replace("  ", " "); //スペースが連続していたら1つに圧縮
		while (input.charAt(0) == ' ') input = input.substring(1); //スペースから始まっていたら削る
		if(input.length() > 0){
			inputData = input.split("\\s"); //inputが1文字以上なら半角スペースで分割
		} else {
			inputData = new String[0]; //inputが0文字ならinputDataは要素数0 splitで生成すると要素数が1になる
		}
		//System.out.println(inputData.length);
		searchMode = request.getParameter("searchMode");

		if (inputData.length == 0) {
			//トップページに遷移する処理 今は検索結果ページに遷移している 後で修正
			request.setAttribute("recipeTitle", recipeTitle);
			request.setAttribute("recipeText", recipeText);
			RequestDispatcher rd_top = request.getRequestDispatcher("searchResult.jsp"); //ここ修正ポイント "searchResult.jsp"を"main.jsp"に
			rd_top.forward(request, response);
			return;
		} else if (searchMode.equals("ryouri")) {
			//料理名テーブルから検索
			sql = "select RyouriID from RyourimeiTB where Ryourimei in ('" + inputData[0];
			for (int i = 1; i < inputData.length; i++) {
				sql += "', '" + inputData[i];
			}
			sql += "');";
		} else {
			//食材名テーブルから検索
			sql = "select RyouriID, count(RyouriID) from BunryouTB where SyokuzaiID in (select SyokuzaiID from SyokuzaiTB where Syokuzaimei in ('" + inputData[0];
			for (int i = 1; i < inputData.length; i++) {
				sql += "', '" + inputData[i];
			}
			sql += "')) group by RyouriID having count(RyouriID) = " + inputData.length;
		}
		System.out.println(sql);

		//表示レシピ検索SQLの実行
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/j2a1b?serverTimezone=JST","root","password");
			System.out.println("connection success");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				recipeID.add(rs.getInt("RyouriID"));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (rs!=null) {
				try {
					rs.close();
				}catch (SQLException sqlEX) {
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				}catch (SQLException sqlEX) {
				}
			}

			if (conn != null) {

				try {
					conn.close();
				}catch (SQLException sqlEX) {
				}
			}
		}
		for (int i = 0; i < recipeID.size(); i++) {
			System.out.println(recipeID.get(i));
		}

		if (recipeID.size() > 0) {
			//レシピ概要検索SQL(料理名)
			sql = "select Ryourimei from RyourimeiTB where RyouriID in ('" + recipeID.get(0);
			for (int i = 1; i < recipeID.size(); i++) {
				sql += "', '" + recipeID.get(i);
			}
			sql += "')";
			System.out.println(sql);

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/j2a1b?serverTimezone=JST","root","password");
				System.out.println("connection success");
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					recipeTitle.add(rs.getString("Ryourimei"));
				}

			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (rs!=null) {
					try {
						rs.close();
					}catch (SQLException sqlEX) {
					}
				}

				if (stmt != null) {
					try {
						stmt.close();
					}catch (SQLException sqlEX) {
					}
				}

				if (conn != null) {

					try {
						conn.close();
					}catch (SQLException sqlEX) {
					}
				}
			}
			for (int i = 0; i < recipeTitle.size(); i++) {
				System.out.println(recipeTitle.get(i));
			}

			//レシピ概要検索SQL(作り方)
			sql = "select Tukurikata from TukurikataTB where RyouriID in ('" + recipeID.get(0);
			for (int i = 1; i < recipeID.size(); i++) {
				sql += "', '" + recipeID.get(i);
			}
			sql += "')";
			System.out.println(sql);

			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/j2a1b?serverTimezone=JST","root","password");
				System.out.println("connection success");
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					recipeText.add(rs.getString("Tukurikata"));
				}

			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				if (rs!=null) {
					try {
						rs.close();
					}catch (SQLException sqlEX) {
					}
				}

				if (stmt != null) {
					try {
						stmt.close();
					}catch (SQLException sqlEX) {
					}
				}

				if (conn != null) {

					try {
						conn.close();
					}catch (SQLException sqlEX) {
					}
				}
			}
			for (int i = 0; i < recipeText.size(); i++) {
				System.out.println(recipeText.get(i));
			}
		}

		request.setAttribute("recipeTitle", recipeTitle);
		request.setAttribute("recipeText", recipeText);
		RequestDispatcher rd_result = request.getRequestDispatcher("searchResult.jsp");
		rd_result.forward(request, response);

	}

}
