<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<title>File Upload</title>
</head>
<style type="text/css">
	<%@include file = "/WEB-INF/css/type.css"%>
</style>
<body>
 <header>
 	<br/>
   <h1>Upload Json file here</h1>
   
    </header><br>
	<form:form method="post" action="savefile" enctype="multipart/form-data">
	StartDate:<input type="date" name="startDate"><form:errors path="startDate" cssClass="errors"/>
	EndDate:<input type="date" name="endDate"><form:errors path="endDate" cssClass="errors"/>
	<br/><br/>
		<input name="file" id="fileToUpload" type="file" />
		
		<input type="submit" value="Upload" style="background-color:#adebad;width: 5em">
		<br/>
	</form:form>
	
			
</body>
</html>
