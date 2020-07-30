package ven.shop.dao;

import java.util.Hashtable;

import ven.shop.model.MallOrderVO;



public class WishListDAO {
	//Hashtable : key, value 형식으로 데이터를 처리
		private Hashtable<String,MallOrderVO> hCart = new Hashtable<String,MallOrderVO>();

		//카트에 추가
		public void addCart(MallOrderVO orderVO){

			//key값 : product_no, value값 : OrderBean
			String product_no = orderVO.getProduct_no();
			int quantity = Integer.parseInt(orderVO.getQuantity());

			//주문수량이 0보다 크면
			if(quantity >0){ 

				//카트에 동일상품이 있으면
				if(hCart.containsKey(product_no)){

					//카트의 기존 상품번호의 orderbean을 가져옴
					MallOrderVO tempVO = (MallOrderVO)hCart.get(product_no);

					//현재 갯수 + 기존 상품의 갯수
					quantity += Integer.parseInt(tempVO.getQuantity());

					//기존상품 갯수 = 현재갯수 + 기존상품 갯수
					tempVO.setQuantity(Integer.toString(quantity));

					//카트에 추가
					hCart.put(product_no, tempVO);

				//카트에 동일상품이 없으면
				} else {
					hCart.put(product_no, orderVO);
				}
			}
		}
		//카트 목록 출력
		public Hashtable<String,MallOrderVO> getCartList() {
			return hCart;
		}
		//카트 내용 수정
		public void updateCart(MallOrderVO orderVO) {
			String product_no = orderVO.getProduct_no();
			hCart.put(product_no, orderVO);
		}
		//카트 내용 삭제
		public void deleteCart(MallOrderVO orderVO) {
			String product_no = orderVO.getProduct_no();
			hCart.remove(product_no);
		}
}
