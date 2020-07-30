package ven.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import ven.shop.model.MemberVO;

public class MemberDAO {
	MemberVO memberVO = new MemberVO();
	
	//기본메소드 일단 DB가 연결됐는지 확인한다.
	public MemberDAO() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc");
			System.out.println(dataSource + "연결되었습니다!!");
		} catch (NamingException e) {
			System.out.println("DB 연결 실패 유감 ㅠㅠ");
			e.printStackTrace();
		}
	}

	public boolean memberInsert(MemberVO memberVO) {
		int mem_num=0;
		String sql="";
		int result = 0;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection=dataSource.getConnection();
			//회원 번호의 최댓값 조회 글 등록할때 번호 순차적으로 지정
			sql="select max(mem_num) from member";
			preparedStatement = connection.prepareStatement(sql);
			resultSet=preparedStatement.executeQuery();
			
			//만약 값이 있다면
			if(resultSet.next()) {
				//회원의 최댓값num에서 +1한다.그뒤로 저장해야하니깐
				mem_num=resultSet.getInt(1)+1;
			} else {
				//다음값으없다면 지금쓰는글이 첫번째니깐 1번
				mem_num=1;
			}
			// 일단 검색했으면 종료 왜냐 한번더 검색해서 데이터를 가지고오기위해서
			preparedStatement.close();
			
			
			//번호를 정했으면 이제 작성된 데이터를 넣는다.
			sql="insert into member(mem_num,mem_id,mem_passwd,mem_name,mem_birth,mem_tel1,mem_tel2,mem_tel3,mem_zipcode,mem_address1,mem_address2,mem_gender,mem_email,mem_email_ck,mem_grade,mem_point,mem_receive_email,mem_receive_sms,mem_register_datetime,mem_adminmemo,mem_group,mem_manager)";
			sql+=" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?,?,?)";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, mem_num);
			preparedStatement.setString(2, memberVO.getMem_id());
			preparedStatement.setString(3, memberVO.getMem_passwd());
			preparedStatement.setString(4, memberVO.getMem_name());
			preparedStatement.setDate(5, memberVO.getMem_birth());
			preparedStatement.setInt(6, memberVO.getMem_tel1());
			preparedStatement.setInt(7, memberVO.getMem_tel2());
			preparedStatement.setInt(8, memberVO.getMem_tel3());
			preparedStatement.setInt(9, memberVO.getMem_zipcode());
			preparedStatement.setString(10, memberVO.getMem_address1());
			preparedStatement.setString(11, memberVO.getMem_address2());
			preparedStatement.setString(12, memberVO.getMem_gender());
			preparedStatement.setString(13, memberVO.getMem_email());
			preparedStatement.setInt(14, memberVO.getMem_email_ck());
			preparedStatement.setString(15, memberVO.getMem_grade());
			preparedStatement.setInt(16, memberVO.getMem_point());
			preparedStatement.setInt(17, memberVO.getMem_receive_email());
			preparedStatement.setInt(18, memberVO.getMem_receive_sms());
			preparedStatement.setString(19, memberVO.getMem_adminmemo());
			preparedStatement.setString(20, memberVO.getMem_group());
			preparedStatement.setInt(21, memberVO.getMem_manager());
			
			result = preparedStatement.executeUpdate();
			if (result==0) {
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("회원가입 실패했다 ㅜㅜ");
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("DB에 문제있다ㅜㅜ");
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	public boolean emailCheck(MemberVO memberVO) {
		String sql="";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		System.out.println(memberVO.getMem_email());
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection=dataSource.getConnection();
			
			//회원 번호의 최댓값 조회 글 등록할때 번호 순차적으로 지정
			sql="update member set mem_email_ck = 1 where mem_email = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberVO.getMem_email());
			resultSet=preparedStatement.executeQuery();
			
			
		} catch (Exception e) {
			System.out.println("emailCheck 에러");
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("emailCheck DB에러");
				e.printStackTrace();
			}
		}
		return false; 
	}
	
	
	public boolean loginCheck(MemberVO memberVO) {
		String sql="";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		System.out.println(memberVO.getMem_id());
		System.out.println(memberVO.getMem_passwd());
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection=dataSource.getConnection();
			
			//회원 번호의 최댓값 조회 글 등록할때 번호 순차적으로 지정
			sql="select mem_id, mem_passwd from member where mem_id = ? and mem_passwd = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberVO.getMem_id());
			preparedStatement.setString(2, memberVO.getMem_passwd());
			
			resultSet=preparedStatement.executeQuery();
			int result = preparedStatement.executeUpdate();
			
			if (result==0) {
				System.out.println("비밀번호나 아이디가 틀렸습니다.");
				return false;
			}else {
				System.out.println("로그인 되었습니다.");
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("emailCheck 에러");
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("emailCheck DB에러");
				e.printStackTrace();
			}
		}
		return false;
		
	}
	
	
	public boolean memberInfo(MemberVO memberVO) {
		String sql="";
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection=dataSource.getConnection();

			
			sql="select mem_num,mem_id,mem_passwd,mem_name,mem_birth,mem_tel1,mem_tel2,mem_tel3,mem_zipcode,mem_address1,mem_address2,mem_gender,mem_email,mem_email_ck,mem_grade,mem_point,mem_receive_email,mem_receive_sms,mem_register_datetime from member";
			sql+=" where mem_id = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberVO.getMem_id());
			
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				memberVO.setMem_id(resultSet.getString("mem_id"));
				memberVO.setMem_passwd(resultSet.getString("mem_passwd"));
				memberVO.setMem_name(resultSet.getString("mem_name"));
				memberVO.setMem_birth(resultSet.getDate("mem_birth"));
				memberVO.setMem_tel1(resultSet.getInt("mem_tel1"));
				memberVO.setMem_tel2(resultSet.getInt("mem_tel2"));
				memberVO.setMem_tel3(resultSet.getInt("mem_tel3"));
				memberVO.setMem_zipcode(resultSet.getInt("mem_zipcode"));
				memberVO.setMem_address1(resultSet.getString("mem_address1"));
				memberVO.setMem_address2(resultSet.getString("mem_address2"));
				memberVO.setMem_gender(resultSet.getString("mem_gender"));
				memberVO.setMem_email(resultSet.getString("mem_email"));
				memberVO.setMem_email_ck(resultSet.getInt("mem_email_ck"));
				memberVO.setMem_grade(resultSet.getString("mem_grade"));
				memberVO.setMem_point(resultSet.getInt("mem_point"));
				memberVO.setMem_receive_email(resultSet.getInt("mem_receive_email"));
				memberVO.setMem_receive_sms(resultSet.getInt("mem_receive_sms"));
				memberVO.setMem_register_datetime(resultSet.getDate("mem_register_datetime"));
			}	
		} catch (Exception e) {
			System.out.println("memberInfo 에러");
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("DB에 문제있다ㅜㅜ");
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean memberDelete(MemberVO memberVO) {
		String sql="";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		System.out.println(memberVO.getMem_id());
		System.out.println(memberVO.getMem_passwd());
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection=dataSource.getConnection();
			
			sql="select mem_id, mem_passwd from member where mem_id = ? and mem_passwd = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberVO.getMem_id());
			preparedStatement.setString(2, memberVO.getMem_passwd());
			resultSet=preparedStatement.executeQuery();
			int result = preparedStatement.executeUpdate();
			
			if (result==0) {
				System.out.println("비밀번호가 틀렸습니다.");
				return false;
			}else {
				System.out.println("삭제되었습니다.");
				preparedStatement.close();
				sql = "delete from member where mem_id=?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, memberVO.getMem_id());
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("memberDelete 에러");
			e.printStackTrace();
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("memberDelete DB에러");
				e.printStackTrace();
			}
		}
		return false;
		
	}
	
	public boolean memberModityChange(MemberVO memberVO) {
		String sql="";
		int result = 0;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc");
			connection=dataSource.getConnection();

			sql = "update member set mem_passwd=?,mem_name=?,mem_birth=?,mem_tel1=?,mem_tel2=?,mem_tel3=?,mem_zipcode=?,mem_address1=?,mem_address2=?,mem_gender=?,mem_email=?,mem_receive_email=?,mem_receive_sms=?";
			sql += " where mem_id=?";
			
			
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, memberVO.getMem_passwd());
			preparedStatement.setString(2, memberVO.getMem_name());
			preparedStatement.setDate(3, memberVO.getMem_birth());
			preparedStatement.setInt(4, memberVO.getMem_tel1());
			preparedStatement.setInt(5, memberVO.getMem_tel2());
			preparedStatement.setInt(6, memberVO.getMem_tel3());
			preparedStatement.setInt(7, memberVO.getMem_zipcode());
			preparedStatement.setString(8, memberVO.getMem_address1());
			preparedStatement.setString(9, memberVO.getMem_address2());
			preparedStatement.setString(10, memberVO.getMem_gender());
			preparedStatement.setString(11, memberVO.getMem_email());
			preparedStatement.setInt(12, memberVO.getMem_receive_email());
			preparedStatement.setInt(13, memberVO.getMem_receive_sms());
			preparedStatement.setString(14, memberVO.getMem_id());
			
			
			result = preparedStatement.executeUpdate();
			if (result==0) {
				return false;
			}else {
				return true;
			}
		} catch (Exception e) {
			System.out.println("회원수정 실패했다 ㅜㅜ");
			e.printStackTrace();
		}finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("DB에 문제있다ㅜㅜ");
				e.printStackTrace();
			}
		}
		return false;
	}
	//id 체크
		public boolean checkId(String mem_id){
			Connection conn = null;
			PreparedStatement preparedStatement= null;
			ResultSet resultSet = null;
			DataSource dataSource = null;
			boolean b = false;
			try {
				String sql = "select mem_id from member where mem_id like ?";
				conn = dataSource.getConnection();
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, mem_id);
				resultSet = preparedStatement.executeQuery();
				b=resultSet.next();
			} catch (Exception e) {
				System.out.println("checkId err : " + e);
			} finally {
				try {
					if(resultSet!=null)resultSet.close();
					if(preparedStatement!=null)preparedStatement.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return b;
		}
	
		//회원 수정 - 회원 정보 가저오기
		public MemberVO getData(String mem_id){
			Connection conn = null;
			PreparedStatement preparedStatement= null;
			ResultSet resultSet = null;
			DataSource dataSource = null;
			MemberVO memberVO = null;
			try {
				String sql = "select mem_id, mem_passwd, mem_name, mem_birth, mem_tel1, mem_tel2, mem_tel3, mem_zipcode, mem_address1, mem_address2, mem_gender, mem_email from member where mem_id like ?";
				conn = dataSource.getConnection();
				preparedStatement = conn.prepareStatement(sql);
				preparedStatement.setString(1, mem_id);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					memberVO = new MemberVO();
					memberVO.setMem_id(resultSet.getString("mem_id"));
					memberVO.setMem_passwd(resultSet.getString("mem_passwd"));
					memberVO.setMem_name(resultSet.getString("mem_name"));
					memberVO.setMem_birth(resultSet.getDate("mem_birth"));
					memberVO.setMem_tel1(resultSet.getInt("mem_tel1"));
					memberVO.setMem_tel2(resultSet.getInt("mem_tel2"));
					memberVO.setMem_tel3(resultSet.getInt("mem_tel3"));
					memberVO.setMem_zipcode(resultSet.getInt("mem_zipcode"));
					memberVO.setMem_address1(resultSet.getString("mem_address1"));
					memberVO.setMem_address2(resultSet.getString("mem_address2"));
					memberVO.setMem_gender(resultSet.getString("mem_gender"));
					memberVO.setMem_email(resultSet.getString("mem_email"));
				}
				
			} catch (Exception e) {
				System.out.println("getData err : " + e);
			} finally {
				try {
					if(resultSet!=null)resultSet.close();
					if(preparedStatement!=null)preparedStatement.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return memberVO;
		}
	
}
