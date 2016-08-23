<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${contextPathDefine!='defined'}"><c:set var="contextPathDefine" value="defined" scope="request" />
<% long lastAccessedTime = session.getLastAccessedTime();%>
<script type="text/javascript">
    window.contextPath = "${pageContext.request.contextPath}";
    window.lastServerTimestamp = <%=lastAccessedTime%>;
</script>
</c:if>