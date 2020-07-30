package ven.shop.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import ven.shop.model.MallItemVO;
import ven.shop.model.MallOrderVO;

public class ItemDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;

	public ItemDAO() {

		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc");
		} catch (Exception e) {
			System.out.println("connect err : " + e);
		}
	}
	//전체 상품 목록 출력
		public ArrayList<MallItemVO> getProductAll(){
			ArrayList<MallItemVO> list = new ArrayList<MallItemVO>();
			try {
				String sql = "select * from mall_item";
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()){
					MallItemVO vo = new MallItemVO();
					vo.setNo(rs.getInt("no"));
					vo.setName(rs.getString("name"));
					vo.setDetail(rs.getString("detail"));
					vo.setPrice(rs.getString("price"));
					vo.setSdate(rs.getString("sdate"));
					vo.setImage(rs.getString("image"));
					vo.setStock(rs.getString("stock"));
					list.add(vo);
				}
				
			} catch (Exception e) {
				System.out.println("getProductAll err : " + e);
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
		
		//상품상세보기
		public MallItemVO getProduct(int no){
			MallItemVO vo = null;
			try {
				String sql = "select * from mall_item where no = ?";
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				if(rs.next()){
					vo = new MallItemVO();
					vo.setNo(rs.getInt("no"));
					vo.setName(rs.getString("name"));
					vo.setPrice(rs.getString("price"));
					vo.setDetail(rs.getString("detail"));
					vo.setSdate(rs.getString("sdate"));
					vo.setStock(rs.getString("stock"));
					vo.setImage(rs.getString("image"));
				}
			} catch (Exception e) {
				System.out.println("getProduct err : ");
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
		//주문 시  재고량 수정

		public void reduceProduct(MallOrderVO VO){
			
			try {
				String sql = "update mall_item set stock=(stock-?) where no=?";

				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, VO.getQuantity());
				pstmt.setString(2, VO.getProduct_no());
				pstmt.executeUpdate();

			} catch (Exception e) {
				System.out.println("reduceProduct err : " + e);
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
		
		//미리보기 파일 삽입

		public String insertPreviewImg(HttpServletRequest request){

			String imageName = "noImage.jsp";

			try {
				//파일 저장 경로
				ServletContext context = request.getSession().getServletContext();

				String fileDir = context.getRealPath("/images/preview");
				//파일 저장
				MultipartRequest multi = new MultipartRequest(
						request,  //객체
						fileDir,  //저장경로 
						5 * 4096 * 4096,  //파일 최대 크기
						"UTF-8"   //파일 인코딩
				);
				//이미지가 있을 경우, 리턴값 설정
				if(multi.getFilesystemName("image")!=null)

					//저장된 미리보기 파일명 출력
					imageName = multi.getFilesystemName("image");
			} catch (Exception e) {
				System.out.println("previewImage err : " + e);
			} 
			return imageName;
		}
		
		//미리보기 파일 삭제
		public void deletePreviewImg(HttpServletRequest request){

			//파일 경로
			ServletContext context = request.getSession().getServletContext();
			String fileDir = context.getRealPath("/images/preview");

			//preview 폴더 내용은 유지할 필요가 없기 때문에 상시로 비워줌
			File file = new File(fileDir);
			if(file.isDirectory()){
				for(File f:file.listFiles()){
					f.delete();
				}
			}
		}
		
		//제품 추가

		public boolean insertProduct(HttpServletRequest request){
			boolean b = false;

			try {
				//웹의 루트경로
				ServletContext context = request.getSession().getServletContext();
				String uploadDir = context.getRealPath("/images/product");

				//파일 저장
				MultipartRequest multi = new MultipartRequest(
						request,  //객체
						uploadDir,  //저장경로 
						5 * 2048 * 2048,  //파일 최대 크기
						"UTF-8",   //파일 인코딩
						new DefaultFileRenamePolicy() //중복 파일 처리 인터페이스
				);
				//데이터베이스 처리
				String sql = "insert into mall_item(no,name,price,detail,sdate,stock,image) "
						+ "values (?,?,?,?,sysdate,?,?)";
//				String sql = "insert into webshop_product values(?,?,?,?,sysdate,?,?)";
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("no"));
				pstmt.setString(2, multi.getParameter("name"));
				pstmt.setString(3, multi.getParameter("price"));
				pstmt.setString(4, multi.getParameter("detail"));
				pstmt.setString(5, multi.getParameter("stock"));
				//이미지가 없을 경우, 준비중 이미지로 대체
				if(multi.getFilesystemName("image")==null){
					pstmt.setString(6, "noimage.jpg");
				} else {
					pstmt.setString(6, multi.getFilesystemName("image"));
				}
				if(pstmt.executeUpdate()>0)b=true;
			} catch (Exception e) {
				System.out.println("insertProduct err : " + e);
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
		
		//제품 수정

		public boolean updateProduct(HttpServletRequest request){

			boolean b=false;

			try {
				//웹의 루트경로
				ServletContext context = request.getSession().getServletContext();
				String uploadDir = context.getRealPath("/images/product");

				//파일 저장
				MultipartRequest multi = new MultipartRequest(
						request,  //객체
						uploadDir,  //저장경로 
						5 * 4096 * 4096,  //파일 최대 크기
						"UTF-8",   //파일 인코딩
						new DefaultFileRenamePolicy() //중복 파일 처리 인터페이스
				);
				conn = ds.getConnection();

				//이미지가 수정되지 않았을 경우
				if(multi.getFilesystemName("image")==null){
					String sql = "update mall_item set name=?, price=?, detail=?, stock=? where no=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, multi.getParameter("name"));
					pstmt.setString(2, multi.getParameter("price"));
					pstmt.setString(3, multi.getParameter("detail"));
					pstmt.setString(4, multi.getParameter("stock"));
					pstmt.setString(5, multi.getParameter("no"));

				} else {
					String sql = "update mall_item set name=?, price=?, detail=?, stock=?, image=? where no=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, multi.getParameter("name"));
					pstmt.setString(2, multi.getParameter("price"));
					pstmt.setString(3, multi.getParameter("detail"));
					pstmt.setString(4, multi.getParameter("stock"));
					pstmt.setString(5, multi.getFilesystemName("image"));
					pstmt.setString(6, multi.getParameter("no"));
				}
				if(pstmt.executeUpdate()>0)b=true;

			} catch (Exception e) {
				System.out.println("updateProduct err : " + e);
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
		
		//제품 삭제
		public boolean deleteProduct(String no){

			boolean b=false;

			try {
				String sql = "delete from mall_item where no=?";

				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, no);

				if(pstmt.executeUpdate()>0)b=true;
			} catch (Exception e) {
				System.out.println("deleteProduct err : " +e);
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
