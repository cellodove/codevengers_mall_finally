package ven.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import ven.shop.model.MallOrderVO;

public class OrderDAO {

		private Connection conn;
		private PreparedStatement pstmt;
		private ResultSet rs;
		private DataSource ds;

		public OrderDAO() {

			try {
				Context context = new InitialContext();
				ds = (DataSource)context.lookup("java:comp/env/jdbc");
			} catch (Exception e) {
				System.out.println("connect err : " + e);
			}
		}
		
		//주문하기
			public void insertOrder(MallOrderVO orderVO){

				try {
					String sql = "insert into mall_order(product_no, quantity, sdate, state, mem_id) values(?,?,sysdate,?,?)";
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, orderVO.getProduct_no());
					pstmt.setString(2, orderVO.getQuantity());
					pstmt.setString(3, "1");
					pstmt.setString(4, orderVO.getMem_id());
					pstmt.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("insertOrder err : " + e);
				} finally {
					try {
						if(rs!=null)rs.close();
						if(pstmt!=null)pstmt.close();
						if(conn!=null)conn.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
			//주문 내역 보기

			public ArrayList<MallOrderVO> getOrder(String mem_id){

				ArrayList<MallOrderVO> list = new ArrayList<MallOrderVO>();

				try {
					String sql = "select * from mall_order where mem_id=? order by no desc";
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, mem_id);
					rs = pstmt.executeQuery();

					while(rs.next()){
						MallOrderVO orderVO = new MallOrderVO();
						orderVO.setNo(rs.getInt("no"));
						orderVO.setProduct_no(rs.getString("product_no"));
						orderVO.setQuantity(rs.getString("quantity"));
						orderVO.setSdate(rs.getString("sdate"));
						orderVO.setState(rs.getString("state"));
						orderVO.setMem_id(rs.getString("mem_id"));
						list.add(orderVO);

					}
				} catch (Exception e) {
					System.out.println("getOrder err : " + e);
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
			
			//주문 전체보기

			public ArrayList<MallOrderVO> getOrderAll(){

				ArrayList<MallOrderVO> list = new ArrayList<MallOrderVO>();

				try {

					String sql = "select * from mall_order";

					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();

					while(rs.next()){
						MallOrderVO orderVO = new MallOrderVO();
						orderVO.setNo(rs.getInt("no"));
						orderVO.setProduct_no(rs.getString("product_no"));
						orderVO.setQuantity(rs.getString("quantity"));
						orderVO.setSdate(rs.getString("sdate"));
						orderVO.setState(rs.getString("state"));
						orderVO.setMem_id(rs.getString("mem_id"));
						list.add(orderVO);

					}
				} catch (Exception e) {
					System.out.println("getOrderAll err : " + e);
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
			
			//주문 상세보기

			public MallOrderVO getOrderDetail(int no){

				MallOrderVO orderVO = new MallOrderVO();

				try {

					String sql = "select * from mall_order where no = ?";
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, no);
					rs = pstmt.executeQuery();

					if(rs.next()){
						orderVO.setNo(rs.getInt("no"));
						orderVO.setProduct_no(rs.getString("product_no"));
						orderVO.setQuantity(rs.getString("quantity"));
						orderVO.setSdate(rs.getString("date"));
						orderVO.setState(rs.getString("state"));
						orderVO.setMem_id(rs.getString("mem_id"));

					}
				} catch (Exception e) {
					System.out.println("getOrderDetail err : " + e);
				} finally {
					try {
						if(rs!=null)rs.close();
						if(pstmt!=null)pstmt.close();
						if(conn!=null)conn.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				return orderVO;
			}
			
			//주문 상태 수정

			public boolean updateOrder(int no, String state){

				boolean b = false;

				try {

					String sql = "update mall_order set state = ? where no = ?";
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, state);
					pstmt.setInt(2, no);

					if(pstmt.executeUpdate()>0)b = true;
				} catch (Exception e) {
					System.out.println("updateOrder err : " + e);
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
			
			//주문 삭제

			public boolean deleteOrder(MallOrderVO orderVO){

				boolean b = false;

				try {
					conn = ds.getConnection();

					//주문 삭제 시 상품 재고량 복귀
					String sql = "update mall_item set stock = stock+? where no = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, orderVO.getQuantity());
					pstmt.setString(2, orderVO.getProduct_no());

					if(pstmt.executeUpdate()<=0){
						return false;
					}

					//주문 삭제
					String delsql = "delete from mall_order where no=?";

					pstmt = conn.prepareStatement(delsql);
					pstmt.setInt(1, orderVO.getNo());

					if(pstmt.executeUpdate()>0)b = true;
				} catch (Exception e) {
					System.out.println("deleteOrder err : " + e);
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
