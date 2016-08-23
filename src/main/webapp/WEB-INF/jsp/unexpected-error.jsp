<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>南航信息中心研究院</title>
</head>
<body>
<p align="center">
访问 <c:out value="${requestScope['javax.servlet.forward.request_uri']}"/> 时<br />
出错了，你懂的。 <br />
${excpetion}<br />
<img alt="WTF!" src="<c:url value='/images/http-500-bg.jpg'/>"></p>
</body>
</html>