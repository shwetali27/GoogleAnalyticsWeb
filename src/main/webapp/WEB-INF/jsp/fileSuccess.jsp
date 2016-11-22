<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<h2>Success</h2>

	<%-- <c:if test="${not empty gaReportInputInfoArrayList}">
 --%>
	<%-- <table border="1" align="center">

		<tr>
			<td><b>GaId</b></td>
			<td><b>GaDiscription</b></td>
			<td><b>list</b></td>
		</tr>
		<c:forEach var="listValue" items="${gaReportResponseFetcherObject}">
			<tr>
				<td>${listValue.mGaID}</td>
				<td>${listValue.mGaDiscription}</td>
				<td>${listValue.mMetricArraList}</td>
			</tr>
		</c:forEach>
	</table> --%>
	<%-- 	</c:if> --%>
</body>
</html>