package ven.shop.model;

import java.sql.Date;

public class PointVO {
	
	private int point_num;
	
	private String mem_id;
	
	private String point_content;
	
	private int point_score;
	
	private String point_action;
	
	private Date point_time;
	

	public int getPoint_num() {
		return point_num;
	}

	public void setPoint_num(int point_num) {
		this.point_num = point_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getPoint_content() {
		return point_content;
	}

	public void setPoint_content(String point_content) {
		this.point_content = point_content;
	}

	public int getPoint_score() {
		return point_score;
	}

	public void setPoint_score(int point_score) {
		this.point_score = point_score;
	}

	public String getPoint_action() {
		return point_action;
	}

	public void setPoint_action(String point_action) {
		this.point_action = point_action;
	}

	public Date getPoint_time() {
		return point_time;
	}

	public void setPoint_time(Date point_time) {
		this.point_time = point_time;
	}
	
	

}
