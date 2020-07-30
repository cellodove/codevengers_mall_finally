package ven.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ven.shop.model.QnABoardVO;


public class BoardDAO {

	public BoardDAO() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			System.out.println(dataSource + "연결");
		} catch (Exception e) {
			System.out.println("ㄴㄴ" + e);
			return;
		}
	}

	public int getListCount() {
		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select count(*) from qna_board";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				i = resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("글의 개수 구하기 실패: " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	public List<QnABoardVO> getBoardlist(int page, int limit) {

		List<QnABoardVO> list = new ArrayList<QnABoardVO>();

		int startrow = (page - 1) * 10 + 1;

		int endrow = startrow + limit - 1;

		Connection connection = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select * from (select rownum rnum, qbrd_num, mem_id, item_num, qbrd_title, qbrd_content,";
			sql += " qbrd_file, qbrd_category, qbrd_date, qbrd_answer, qbrd_secret";
			sql += " from (select * from qna_board order by qbrd_answer desc, qbrd_secret asc))";
			sql += " where rnum>=? and rnum<=?";
			
				
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startrow);
			preparedStatement.setInt(2, endrow);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				QnABoardVO qnABoardVO = new QnABoardVO();
				qnABoardVO.setQbrd_num(resultSet.getInt("qbrd_num"));
				qnABoardVO.setMem_id(resultSet.getString("mem_id"));
				qnABoardVO.setItem_num(resultSet.getInt("item_num"));
				qnABoardVO.setQbrd_title(resultSet.getString("qbrd_title"));
				qnABoardVO.setQbrd_content(resultSet.getString("qbrd_content"));
				qnABoardVO.setQbrd_file(resultSet.getString("qbrd_file"));
				qnABoardVO.setQbrd_category(resultSet.getString("qbrd_category"));
				qnABoardVO.setQbrd_date(resultSet.getDate("qbrd_date"));
				qnABoardVO.setQbrd_answer(resultSet.getString("qbrd_answer"));
				qnABoardVO.setQbrd_secret(resultSet.getString("qbrd_secret"));
				list.add(qnABoardVO);

			}
			return list;
		} catch (Exception e) {
			System.out.println(" 목록 보기 실패 : " + e);
		} finally {
			try {
				connection.close();
				preparedStatement.close();
				resultSet.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return null;

	}

	public boolean boardinsert(QnABoardVO qnABoardVO) {

		int num = 0;

		String sql = "";

		int result = 0;

		Connection connection = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			sql = "select max(qbrd_num) from qna_board";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				num = resultSet.getInt(1) + 1;
			} else {
				num = 1;
			}
			preparedStatement.close();
			sql = "insert into qna_board (qbrd_num,mem_id,qbrd_passwd,qbrd_title,qbrd_content,qbrd_file,";
			sql += "qbrd_answer,qbrd_category,qbrd_secret,qbrd_date)";
			sql += " values(?,?,?,?,?,?,?,?,?,sysdate)";
		
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			preparedStatement.setString(2, qnABoardVO.getMem_id());
			preparedStatement.setString(3, qnABoardVO.getQbrd_passwd());
			preparedStatement.setString(4, qnABoardVO.getQbrd_title());
			preparedStatement.setString(5, qnABoardVO.getQbrd_content());
			preparedStatement.setString(6, qnABoardVO.getQbrd_file());
			preparedStatement.setString(7, qnABoardVO.getQbrd_answer());
			preparedStatement.setString(8, qnABoardVO.getQbrd_category());
			preparedStatement.setString(9, qnABoardVO.getQbrd_secret());
			result = preparedStatement.executeUpdate();
			if (result == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("글 등록 실패!:" + e);
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public QnABoardVO getDetail(int num) {

		QnABoardVO qnABoardVO = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext( );
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			String sql = "select * from qna_board where qbrd_num = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			resultSet = preparedStatement.executeQuery( );
			if (resultSet.next()) {
				qnABoardVO = new QnABoardVO();
				qnABoardVO.setQbrd_num(resultSet.getInt("qbrd_num"));
				qnABoardVO.setMem_id(resultSet.getString("mem_id"));
				qnABoardVO.setItem_num(resultSet.getInt("item_num"));
				qnABoardVO.setQbrd_title(resultSet.getString("qbrd_title"));
				qnABoardVO.setQbrd_passwd(resultSet.getString("qbrd_passwd"));
				qnABoardVO.setQbrd_content(resultSet.getString("qbrd_content"));
				qnABoardVO.setQbrd_file(resultSet.getString("qbrd_file"));
				qnABoardVO.setQbrd_category(resultSet.getString("qbrd_category"));
				qnABoardVO.setQbrd_date(resultSet.getDate("qbrd_date"));
				qnABoardVO.setQbrd_secret(resultSet.getString("qbrd_secret"));
				
	}
			return qnABoardVO;
		} catch (Exception e) {
			System.out.println("글 내용 보기 실패 : " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return null;

	}
	public void setReadCountUpdate(int num) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
		Context context = new InitialContext( );
		DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
		connection = dataSource.getConnection( );
		String sql = "update qna_board  where qbrd_num = " + num;
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.executeUpdate( );
		} catch (Exception e) {
		System.out.println("조회수 업데이트 실패 : " + e);
		} finally {
			try {
		 preparedStatement.close( );
		 connection.close( );
		 } catch (SQLException e2) {
			 e2.printStackTrace( );
			 }
			}
		}
	
	

	public int boardReply(QnABoardVO qnABoardVO) {
		String sql = "";
		int qbrd_num =0;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext( );
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection( );
			sql = "select max(qbrd_num) from qna_board";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				qbrd_num = resultSet.getInt(1) + 1;
			}else {
				qbrd_num = 1;
			}
			preparedStatement.close();
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.executeUpdate();
			sql = "insert into qna_board (qbrd_num,qbrd_passwd,qbrd_title,qbrd_content,";
			sql += "qbrd_file,qbrd_answer,qbrd_secret,qbrd_category,qbrd_date) ";
			sql += " values(?,?,?,?,?,?,?,?,sysdate)";
			System.out.println("답글DAO"+qnABoardVO.getQbrd_title());
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, qbrd_num);
			preparedStatement.setString(2, qnABoardVO.getQbrd_passwd());
			preparedStatement.setString(3, qnABoardVO.getQbrd_title());
			preparedStatement.setString(4, qnABoardVO.getQbrd_content());
			preparedStatement.setString(5, qnABoardVO.getQbrd_file());
			preparedStatement.setString(6, qnABoardVO.getQbrd_answer());
			preparedStatement.setString(7, qnABoardVO.getQbrd_secret());
			preparedStatement.setString(8, qnABoardVO.getQbrd_category());
			preparedStatement.executeUpdate();
			return qbrd_num;
		} catch (Exception e) {
			System.out.println("글 답변 실패" + e);
		}finally {
			try {
				resultSet.close();
				connection.close();
				preparedStatement.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return 0;

	}

	public boolean boardModify(QnABoardVO qnABoardVO) {
	

		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "update qna_board set mem_id=?, qbrd_title=?, qbrd_content=?, ";
			sql += "where qbrd_num=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, qnABoardVO.getMem_id());
			preparedStatement.setString(2, qnABoardVO.getQbrd_title());
			preparedStatement.setString(3, qnABoardVO.getQbrd_content());
			preparedStatement.setString(4, qnABoardVO.getQbrd_file());
			preparedStatement.setInt(5, qnABoardVO.getQbrd_num());
			preparedStatement.executeUpdate();
			return true;
		} catch (Exception e) {
			System.out.println("글 수정 실패 : " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public boolean isBoardWriter(int num, String pass) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select * from qna_board where qbrd_num=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, num);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();

			if (pass.equals(resultSet.getString("qbrd_passwd"))) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("글쓴이 확인 실패 : " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;

	}

	public boolean boardDelete(int qbrd_num) {
		int result = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "delete from qna_board where qbrd_num=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, qbrd_num);
			result = preparedStatement.executeUpdate();
			if (result == 0) {
				return false;
			}
			return true;
		} catch (Exception e) {
			System.out.println("글 삭제 실패 : " + e);
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}

	public List<QnABoardVO> getSearchList(String keyword, String keyfield, int page, int limit) {
		String searchCall = "";

		if (!"".equals(keyword)) {
			if ("all".equals(keyfield)) {
				searchCall = "(qbrd_title like '%' || '" + keyword + "' || '%' ) or ( mem_id like '%' || '" + keyword
						+ "' || '%') or ( qbrd_content like '%' || '" + keyword + "' || '%')";
			} else if ("qbrd_title".equals(keyfield)) {
				searchCall = " qbrd_title like '%' || '" + keyword + "' || '%'";
			} else if ("name".equals(keyfield)) {
				searchCall = " mem_id like '%' || '" + keyword + "' || '%'";
			} else if ("qbrd_content".equals(keyfield)) {
				searchCall = " qbrd_content like '%' || '" + keyword + "' || '%'";
			}
		}

		List<QnABoardVO> list = new ArrayList<QnABoardVO>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select * from (select rownum rnum, qbrd_num, mem_id, item_num, qbrd_title, qbrd_content,";
			sql += " qbrd_file, qbrd_category, qbrd_date, qbrd_answer, qbrd_secret";
			sql += " from (select * from qna_board order by qbrd_answer desc, qbrd_secret asc)";
			sql += " where " + searchCall + ")";
			sql += " where rnum>=? and rnum<=?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, startrow);
			preparedStatement.setInt(2, endrow);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				QnABoardVO qnABoardVO = new QnABoardVO();
				qnABoardVO = new QnABoardVO();
				qnABoardVO.setQbrd_num(resultSet.getInt("qbrd_num"));
				qnABoardVO.setMem_id(resultSet.getString("mem_id"));
				qnABoardVO.setItem_num(resultSet.getInt("item_num"));
				qnABoardVO.setQbrd_title(resultSet.getString("qbrd_title"));
				qnABoardVO.setQbrd_content(resultSet.getString("qbrd_content"));
				qnABoardVO.setQbrd_file(resultSet.getString("qbrd_file"));
				qnABoardVO.setQbrd_category(resultSet.getString("qbrd_category"));
				qnABoardVO.setQbrd_date(resultSet.getDate("qbrd_date"));
				qnABoardVO.setQbrd_answer(resultSet.getString("qbrd_answer"));
				qnABoardVO.setQbrd_secret(resultSet.getString("qbrd_secret"));
				list.add(qnABoardVO);
			}
			return list;
		} catch (Exception e) {
			System.out.println("search 에러" + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return null;
	}

	public int getSearchListCount(String keyword, String keyfield) {
		String searchCall = "";
		if (!"".equals(keyword)) {
			if("all".equals(keyfield)) {
			searchCall = "(qbrd_title like '%' || '" + keyword + "' || '%') or (mem_id like '%' || '" + keyword + "' || '%') or (qbrd_content like '%' || '" + keyword + "' || '%')";
		}else if("qbrd_title".equals(keyfield)) {
			searchCall = " qbrd_title like '%' || '" + keyword + "' || '%'";
		}else if("name".equals(keyfield)) {
			searchCall = " mem_id like '%' || '" + keyword + "' || '%'";
		}else if("qbrd_content".equals(keyfield)) {
			searchCall = " qbrd_content like '%' || '" + keyword + "' || '%'";
		}
	}
		int i = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			connection = dataSource.getConnection();
			String sql = "select count(*) from qna_board where" + searchCall;
			System.out.println("연결되었습니다.");
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				i = resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("글의 개수 구하기 실패: " + e);
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
}
