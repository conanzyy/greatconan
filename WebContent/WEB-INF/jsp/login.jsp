<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<script type="text/javascript" src="<%=basePath%>js/jquery-1.7.1.min.js"></script>
</head>
<body>

${Msg} ---${test_user.userName}---中文

<script>
var reOrExgReasonCode=<c:if test="${empty code}">''</c:if><c:if test="${!empty code}">${code}</c:if>;
$.ajax({
	type : "POST",
	url : "<%=basePath%>json/getJson.json",
	data : {
		req : reOrExgReasonCode
	},
	async : false,
	cache : false,
	dataType : "json",
	success : function(data) {
		if (data.resultCode == 1) {
			alert("成功");
			for(i=0;i<data.result.length;i++){
					alert(data.result[i].userName);
				}
			console.info(data.result);
		} else {
			alert("失败");
		}
	}
});

</script>
</body>
</html>