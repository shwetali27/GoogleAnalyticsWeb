<%@page import="com.bridgelabz.model.SummaryReportModel"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
	<style type="text/css">
			<%@include file="/WEB-INF/css/type.css"%>
	</style>
	</head>
	<body>
		<h1>Summary Report</h1>
		<table border="1" align="center">
	
			<%
				List<SummaryReportModel> summaryRepostList = (List<SummaryReportModel>) request
						.getAttribute("summaryReportModellist");
				SummaryReportModel summaryReportModel = new SummaryReportModel();
			%>
	
			<tr background-color:black>
				<td>Task\Dates</td>
				<%
					if (summaryRepostList.size() != 0) {
						for (int i = 0; i < summaryRepostList.get(0).getDates().size(); i++) {
				%>
				<td><%=summaryRepostList.get(0).getDates().get(i)%></td>
				<%
					}
					}
				%>
			</tr>
			<%
				for (int i = 0; i < summaryRepostList.size(); i++) {
					summaryReportModel = summaryRepostList.get(i);
			%>
			<tr>
				<td><%=summaryReportModel.getmGaDiscription()%></td>
				<%
					for (int j = 0; j < summaryReportModel.getDates().size(); j++) {
				%>
	
				<td><%=summaryReportModel.getTotalCount().get(j)%></td>
	
	
				<%
					}
				%>
			</tr>
			<%
				}
			%>
		</table>
	</body>
</html>