package ven.shop.model;


import java.sql.Date;

public class QnABoardVO {


	private int qbrd_num;
	private String mem_id;
	private int item_num;
	private String qbrd_title;
	private String qbrd_passwd;
	private String qbrd_content;
	private String qbrd_file;
	private String old_file;
	private String qbrd_category;
	private String qbrd_answer;
	private Date qbrd_date;
	private String qbrd_secret;
	public int getQbrd_num() {
		return qbrd_num;
	}
	public void setQbrd_num(int qbrd_num) {
		this.qbrd_num = qbrd_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public String getQbrd_title() {
		return qbrd_title;
	}
	public void setQbrd_title(String qbrd_title) {
		this.qbrd_title = qbrd_title;
	}
	public String getQbrd_passwd() {
		return qbrd_passwd;
	}
	public void setQbrd_passwd(String qbrd_passwd) {
		this.qbrd_passwd = qbrd_passwd;
	}
	public String getQbrd_content() {
		return qbrd_content;
	}
	public void setQbrd_content(String qbrd_content) {
		this.qbrd_content = qbrd_content;
	}
	public String getQbrd_file() {
		return qbrd_file;
	}
	public void setQbrd_file(String qbrd_file) {
		this.qbrd_file = qbrd_file;
	}
	public String getOld_file() {
		return old_file;
	}
	public void setOld_file(String old_file) {
		this.old_file = old_file;
	}
	public String getQbrd_category() {
		return qbrd_category;
	}
	public void setQbrd_category(String qbrd_category) {
		this.qbrd_category = qbrd_category;
	}
	public String getQbrd_answer() {
		return qbrd_answer;
	}
	public void setQbrd_answer(String qbrd_answer) {
		this.qbrd_answer = qbrd_answer;
	}
	public Date getQbrd_date() {
		return qbrd_date;
	}
	public void setQbrd_date(Date qbrd_date) {
		this.qbrd_date = qbrd_date;
	}
	
	public String getQbrd_secret() {
		return qbrd_secret;
	}
	public void setQbrd_secret(String qbrd_secret) {
		this.qbrd_secret = qbrd_secret;
	}

	
	
	
}
