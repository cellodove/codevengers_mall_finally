package ven.shop.model;

import java.sql.Date;

public class MemberVO {

	private int mem_num;
	
	private String mem_id;
	
	private String mem_passwd;
	
	private String mem_name;
	
	private Date mem_birth;
	
	private int  mem_tel1;
	
	private int mem_tel2;
	
	private int mem_tel3;
	
	private int mem_zipcode;
	
	private String mem_address1;
	
	private String mem_address2;
	
	private String mem_gender;
	
	private String mem_email;
	
	private int mem_email_ck;
	
	private String mem_grade;
	
	private int mem_point;
	
	private int mem_receive_email;
	
	private int mem_receive_sms;
	
	private Date mem_register_datetime;
	
	private String mem_adminmemo;
	
	private String mem_group;
	
	private int mem_manager;
	
///////////////////////////////////////////////////////////////////////////////////
	//인증코드 저장변수
	private String authenticationKey;
	
	public String getAuthenticationKey() {
		return authenticationKey;
	}

	public void setAuthenticationKey(String authenticationKey) {
		this.authenticationKey = authenticationKey;
	}
///////////////////////////////////////////////////////////////////////////////////
	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_passwd() {
		return mem_passwd;
	}

	public void setMem_passwd(String mem_passwd) {
		this.mem_passwd = mem_passwd;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public Date getMem_birth() {
		return mem_birth;
	}

	public void setMem_birth(Date mem_birth) {
		this.mem_birth = mem_birth;
	}

	public int getMem_tel1() {
		return mem_tel1;
	}

	public void setMem_tel1(int mem_tel1) {
		this.mem_tel1 = mem_tel1;
	}

	public int getMem_tel2() {
		return mem_tel2;
	}

	public void setMem_tel2(int mem_tel2) {
		this.mem_tel2 = mem_tel2;
	}

	public int getMem_tel3() {
		return mem_tel3;
	}

	public void setMem_tel3(int mem_tel3) {
		this.mem_tel3 = mem_tel3;
	}

	public int getMem_zipcode() {
		return mem_zipcode;
	}

	public void setMem_zipcode(int mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}

	public String getMem_address1() {
		return mem_address1;
	}

	public void setMem_address1(String mem_address1) {
		this.mem_address1 = mem_address1;
	}

	public String getMem_address2() {
		return mem_address2;
	}

	public void setMem_address2(String mem_address2) {
		this.mem_address2 = mem_address2;
	}

	public String getMem_gender() {
		return mem_gender;
	}

	public void setMem_gender(String mem_gender) {
		this.mem_gender = mem_gender;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public int getMem_email_ck() {
		return mem_email_ck;
	}

	public void setMem_email_ck(int mem_email_ck) {
		this.mem_email_ck = mem_email_ck;
	}

	public String getMem_grade() {
		return mem_grade;
	}

	public void setMem_grade(String mem_grade) {
		this.mem_grade = mem_grade;
	}

	public int getMem_point() {
		return mem_point;
	}

	public void setMem_point(int mem_point) {
		this.mem_point = mem_point;
	}

	public int getMem_receive_email() {
		return mem_receive_email;
	}

	public void setMem_receive_email(int mem_receive_email) {
		this.mem_receive_email = mem_receive_email;
	}

	public int getMem_receive_sms() {
		return mem_receive_sms;
	}

	public void setMem_receive_sms(int mem_receive_sms) {
		this.mem_receive_sms = mem_receive_sms;
	}

	public Date getMem_register_datetime() {
		return mem_register_datetime;
	}

	public void setMem_register_datetime(Date mem_register_datetime) {
		this.mem_register_datetime = mem_register_datetime;
	}

	public String getMem_adminmemo() {
		return mem_adminmemo;
	}

	public void setMem_adminmemo(String mem_adminmemo) {
		this.mem_adminmemo = mem_adminmemo;
	}

	public String getMem_group() {
		return mem_group;
	}

	public void setMem_group(String mem_group) {
		this.mem_group = mem_group;
	}

	public int getMem_manager() {
		return mem_manager;
	}

	public void setMem_manager(int mem_manager) {
		this.mem_manager = mem_manager;
	}

	
}
