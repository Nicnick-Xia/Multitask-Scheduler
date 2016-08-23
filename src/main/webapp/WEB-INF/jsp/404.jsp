<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8" />
<title>南航信息中心研究院</title>
<jsp:include page="context-path.jsp" flush="false" />
<base href="${pageContext.request.contextPath}/" />
<link href="<c:url value='/css/reset.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/style.css' />" rel="stylesheet" type="text/css" />
<link href="<c:url value='/css/error.css' />" rel="stylesheet" type="text/css" />
<script src="<c:url value='/js/jquery.min.js' />" type="text/javascript"></script>
</head>
<body>
<header>
<div id="header">
</div>
</header>

<section>
    <div class="slide_banner_2"></div>
</section>

<section>
    <div class="crumb">
        <div class="commWidth">
            <div class="crumb_inner"></div>
        </div>
    </div>
</section>

<section>
<div class="err_404">
    <div class="error_box block_box">
        <div id="err_txt">
            <span class="hide">404 <c:out value="${requestScope['javax.servlet.forward.request_uri']}"/></span>
            <p class="cn">很抱歉，未找到指定页面</p>
            <p class="en">Sorry, page not found</p>
        </div>
    </div>
</div>
</section>

<footer>
<div id="footer">
</div>
</footer>
</body>
</html>
