<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<title>codevengers 회원가입</title>
<style type="text/css">
#jointable table {
    width: 600px;
}
#jointable th {
    text-align: right;
    background-color: orange;
}
#jointable td input {
    border: 1px solid seagreen;
}
</style>
<link rel="stylesheet"
    href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
    src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script type="text/javascript" src="jquery.ui.datepicker-ko.js"></script>
<script type="text/javascript">
    $(document)
            .ready(
                    function() {
                    	$.datepicker.setDefaults({
                    	    dateFormat: 'yy-mm-dd' //Input Display Format 변경
                    	    ,showOtherMonths: true
                    	});
                        $("#btn_join")
                                .click(
                                        function() {
                                            var tel1_pattern = /(^01[016789]$)/; //정규식
 
                                            //alert("ok");
                                            if ($("#id").val() == "") {
                                                alert("아이디를 꼭 입력하세요!");
                                                $("#id").focus();
                                            } else if ($("#pwd").val() == "") {
                                                alert("비밀번호를 꼭 입력하세요!");
                                                $("#pwd").focus();
 
                                            } else if ($("#pwd2").val() == "") {
                                                alert("비밀번호확인 을 꼭 입력하세요!");
                                                $("#pwd2").focus();
 
                                            } else if ($("#pwd").val() != $(
                                                    "#pwd2").val()) {
                                                alert("비밀번호와 비밀번호 확인이 일치하지않습니다.");
                                                $("#pwd").val("");
                                                $("#pwd2").val("");
                                                $("#pwd1").focus();
 
                                            } else if ($("#name").val() == "") {
                                                alert("이름을 꼭 입력하세요!");
                                                $("#name").focus();
 
                                            } else if ($("#mem_birth").val() == "") {
                                                alert("생일을  꼭 입력하세요!");
                                                $("#mem_birth").focus();
 
                                            } else if ($("#tel1").val() == "") {
                                                alert("전화번호 첫번째자리 입력하세요!");
                                                $("#tel1").focus();
 
                                            } else if ($("#tel1").val().length != 3) {
 
                                                alert("전화1을 3개의 숫자로 꼭 입력하세요!");
                                                $("#tel1").val("");
                                                $("#tel1").focus();
 
                                            } else if (isNaN($("#tel1").val())) {
                                                alert("전화1을 3개의 숫자로 꼭 입력하세요!");
                                                $("#tel1").val("");
                                                $("#tel1").focus();
 
                                            } else if (!tel1_pattern.test($(
                                                    "#tel1").val())) {
                                                alert("010만 가능합니다.")
                                                $("#tel1").val("")
                                                $("#tel1").focus();
                                            }
 
                                            else if ($("#tel2").val() == "") {
                                                alert("전화번호 두번째자리를 입력하세요!");
                                                $("#tel2").focus();
 
                                            } else if ($("#tel1").val().length != 3) {
 
                                                alert("전화1을 3개의 숫자로 꼭 입력하세요!");
                                                $("#tel2").val("");
                                                $("#tel2").focus();
 
                                            } else if (isNaN($("#tel2").val())) {
                                                alert("전화2을 3~4개의 숫자로 꼭 입력하세요!");
                                                $("#tel2").val("");
                                                $("#tel2").focus();
 
                                            } else if ($("#tel2").val() != ""
                                                    & $("#tel2").val().length > 4
                                                    & isNaN($("#tel2").val())) {
                                                alert("okkkkkkk");
                                                //var pattern = /(^010$)/; //정규식 슬래쉬(/)로 시작해서, 슬래쉬(/)로 끝났다.
                                                var tel1 = $("#tel2").val();
                                                if (!pattern.test(tel1)) {
                                                    //틀리면
                                                    alert("010만 가능합니다.")
                                                    $("#te2").val("")
                                                    $("#te2").focus();
                                                }
                                            }
 
                                            else if ($("#tel3").val() == "") {
                                                alert("전화번호 세번째자리를 입력하세요!");
                                                $("#tel3").focus();
 
                                            } else if ($("#mem_zipcode").val() == "") {
                                                alert("우편번호를 꼭 입력하세요!");
                                                $("#mem_zipcode").focus();
 
                                            }else if ($("#mem_address1").val() == "") {
                                                alert("주소를 꼭 입력하세요!");
                                                $("#mem_address1").focus();
 
                                            } else if ($("#mem_address2").val() == "") {
                                                alert("상세주소를 꼭 입력하세요!");
                                                $("#mem_address2").focus();
 
                                            }  else if ($("#yes").is(':checked') == false
                                                    && $("#no").is(':checked') == false) {
                                     			   alert("이메일수신 여부를  꼭 선택하세요!");
                                        
                                    		} else if ($("#yess").is(':checked') == false
                                                    && $("#noo").is(':checked') == false) {
                                                alert("문자수신 여부를  꼭 선택하세요!");
                                                
                                            } else if ($("#man").is(':checked') == false
                                                    && $("#woman").is(':checked') == false) {
                                                alert("성별를  꼭 선택하세요!");
                                                
                                            } else if ($("#email").val() == "") {
                                                alert("이메일을 꼭 입력하세요!");
                                                $("#email").focus();
                                                
                                            } else {
                                                alert("가입되었습니다");
                                            }
                                        });
                      
                        $("#mem_birth").datepicker();
                        
                    });
</script>
</head>
<body>
	<%@ include file="main_top.jsp" %>
    <h1>회원 가입</h1>
    <form action="./MemberAdd.do" method="post" name="signupform">
        <table id="jointable">
            <tr>
                <th>아이디</th>
                <td>
                    <input type="text" name="mem_id" id="id" size="12" maxlength="12" />
                </td>
            </tr>
            
            <tr>
                <th>비밀번호</th>
                <td>
                    <input type="password" name="mem_passwd" id="pwd" size="12" maxlength="12" />
                </td>
            </tr>
            
            <tr>
                <th>비밀번호확인</th>
                <td>
                    <input type="password"  id="pwd2" size="12" maxlength="12" />
                </td>
            </tr>
            
            <tr>
                <th>이름</th>
                <td>
                    <input type="text" name="mem_name" id="name" size="12" maxlength="12" />
                </td>
            </tr>
            
              <tr>
                <th>생일</th>
                <td>
                    <input type="text" name="mem_birth" id="mem_birth" size="8" maxlength="8" />
                </td>
            </tr>
                                                                             
            
            <tr>
                <th>전화번호</th>
                <td>
                    <input type="text" name="mem_tel1" id="tel1" size="3"maxlength="3" />- 
                    <input type="text" name="mem_tel2" id="tel2"size="4" maxlength="4" />- 
                    <input type="text" name="mem_tel3" id="tel3" size="4" maxlength="4" />
                </td>
            </tr>
            
            
            <tr>
                <th>우편번호</th>
                <td>
                    <input type="text" name="mem_zipcode" id="mem_zipcode" size="12" maxlength="12" />
                </td>
            </tr>
            
            <tr>
                <th>주소<br>상세주소</th>
                <td>
                    <input type="text" name="mem_address1" id="mem_address1" size="50" maxlength="05" /><pre></pre>
                    <input type="text" name="mem_address2" id="mem_address2" size="50" maxlength="50" />
                </td>
            </tr>
            

          
            
            <tr>
                <th>성별</th>
                <td>
                    <input type="radio" name="mem_gender" id="man"  value="남자" checked="checked">남자 
                    <input type="radio" name="mem_gender" id="woman"  value="여자">여자
                </td>
            </tr>
            
            <tr>
                <th>이메일</th>
                <td>
                    <input type="text" name="mem_email" id="email" size="50"maxlength="50" />
                    
                </td>
            </tr>
 
 
            <tr>
                <th>이메일 수신여부</th>
                <td><input type="radio" name="mem_receive_email" id="yes" value="1">수신
                    <input type="radio" name="mem_receive_email" id="no" value="0">미수신
                </td>
            </tr>
 			
 			<tr>
                <th>문자 수신여부</th>
                <td><input type="radio" name="mem_receive_sms" id="yess" value="1">수신
                    <input type="radio" name="mem_receive_sms" id="noo" value="1">미수신
                </td>
            </tr>
 
 
            
            <tr>
 
                <td colspan="2">
                    <input type="submit" id="btn_join" value="가입">
                    <input type="reset" id="btn_cancel" value="취소">
                </td>
            </tr>
        </table>
    </form>
    
    
    
    
    
</body>
</html>