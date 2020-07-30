package ven.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import ven.shop.model.MemberVO;



public class AdminDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	//데이터베이스 연결
	public AdminDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc");
		} catch (Exception e) {
			System.out.println("connection err:" + e);
		}
	}
	//관리자 로그인
	public boolean admin_login(String admin_id,String admin_pass){

		boolean b = false;

		try {
			String sql = "select * from admin where admin_id = ? and admin_pass = ?";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, admin_id);
			pstmt.setString(2, admin_pass);
			rs = pstmt.executeQuery();
			b=rs.next();

		} catch (Exception e) {
			System.out.println("admin_login err : " + e);
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return b;
	}
	
	//관리자 - 회원목록 전체 출력
	public ArrayList<MemberVO> getMemberAll(){

		ArrayList<MemberVO> list = new ArrayList<MemberVO>();

		try {
			String sql = "select * from member";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()){
				MemberVO vo =  new MemberVO();
				vo.setMem_id(rs.getString("mem_id"));
				//vo.setMem_num(rs.getInt("mem_num"));
				vo.setMem_passwd(rs.getString("mem_passwd"));
				vo.setMem_name(rs.getString("mem_name"));
				//vo.setMem_birth(rs.getInt("mem_birth"));
				vo.setMem_tel1(rs.getInt("mem_tel1"));
				vo.setMem_tel2(rs.getInt("mem_tel2"));
				vo.setMem_tel3(rs.getInt("mem_tel3"));
				vo.setMem_zipcode(rs.getInt("mem_zipcode"));
				vo.setMem_address1(rs.getString("mem_address1"));
				vo.setMem_address2(rs.getString("mem_address2"));
				//vo.setMem_gender(rs.getString("mem_gender"));
				vo.setMem_email(rs.getString("mem_email"));
				//vo.setMem_email_ck(rs.getInt("mem_email_ck"));
				//vo.setMem_grade(rs.getString("mem_grade"));
				//vo.setMem_point(rs.getInt("mem_point"));
				//vo.setMem_receive_email(rs.getInt("mem_receive_email"));
				//vo.setMem_receive_sms(rs.getInt("mem_receive_sms"));
				//vo.setMem_register_datetime(rs.getDate("mem_register_datetime"));
				//vo.setMem_adminmemo(rs.getString("mem_adminmemo"));
				//vo.setMem_group(rs.getString("mem_group"));
				//vo.setMem_manager(rs.getInt("mem_manager"));
				list.add(vo);
			}
		} catch (Exception e) {
			System.out.println("getMemberAll err : " + e);
		} finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
	//회원 수정 - 회원 정보 가저오기
		public MemberVO getData(String id){
			MemberVO vo = null;
			try {
				String sql = "select mem_id, mem_passwd, mem_name, mem_tel1, mem_tel2, mem_tel3, mem_zipcode, mem_address1, mem_address2, mem_email from member where mem_id like ?";
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()){
					vo = new MemberVO();
					vo.setMem_id(rs.getString("mem_id"));
					vo.setMem_passwd(rs.getString("mem_passwd"));
					vo.setMem_name(rs.getString("mem_name"));
					vo.setMem_tel1(rs.getInt("mem_tel1"));
					vo.setMem_tel2(rs.getInt("mem_tel2"));
					vo.setMem_tel3(rs.getInt("mem_tel3"));
					vo.setMem_zipcode(rs.getInt("mem_zipcode"));
					vo.setMem_address1(rs.getString("mem_address1"));
					vo.setMem_address2(rs.getString("mem_address2"));
					vo.setMem_email(rs.getString("mem_email"));
				}
				
			} catch (Exception e) {
				System.out.println("getData err : " + e);
			} finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			return vo;
		}
		//회원정보 수정 - 수정하기
		public boolean modifyData(MemberVO memberVO){
			boolean b = false;
			try {
				String sql = "update member set mem_passwd=?,mem_name=?, mem_email=?, mem_tel1=?, mem_tel2=?, mem_tel3=?, mem_zipcode=?, mem_address1=?, mem_address2=? where mem_id=?";
				System.out.println(memberVO.getMem_passwd());
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberVO.getMem_passwd());
				pstmt.setString(2, memberVO.getMem_name());
				pstmt.setString(3, memberVO.getMem_email());
				pstmt.setInt(4, memberVO.getMem_tel1());
				pstmt.setInt(5, memberVO.getMem_tel2());
				pstmt.setInt(6, memberVO.getMem_tel3());
				pstmt.setInt(7, memberVO.getMem_zipcode());
				pstmt.setString(8, memberVO.getMem_address1());
				pstmt.setString(9, memberVO.getMem_address2());
				pstmt.setString(10, memberVO.getMem_id());
				if(pstmt.executeUpdate()>0) b=true;
			} catch (Exception e) {
				System.out.println("modifyData err : " + e);
			} finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return b;
		}
		
		//회원 탈퇴 - 탈퇴하기
		public boolean deleteData(String mem_id){
			boolean b = false;
			try {
				String sql = "delete from member where mem_id = ?";
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, mem_id);
				int re = pstmt.executeUpdate();
				if(re>0) b = true;
				
			} catch (Exception e) {
				System.out.println("deleteData err : " + e);
			} finally {
				try {
					if(rs!=null)rs.close();
					if(pstmt!=null)pstmt.close();
					if(conn!=null)conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			return b;
		}
}
