<%@ page import="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospitals Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/items.js"></script> 
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1>HOSPITAL MANAGEMENT</h1>
				<form id="formHospital" name="formHospital" method="post" action="hospitals.jsp">  
					Hospital name:  
					<input id="hname" name="hname" type="text" class="form-control form-control-sm">  
					<br> Phone number:  
					<input id="no" name="no" type="text" class="form-control form-control-sm"> 
					<br> Street:  
					<input id="street" name="street" type="text" class="form-control form-control-sm">   
					<br> City:  
					<input id="city" name="city" type="text" class="form-control form-control-sm">  
					<br> Hospital Charges:  
					<input id="hos_charges" name="hos_charges" type="text" class="form-control form-control-sm">  
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidHidSave" name="hidHidSave" value=""> 
				</form>
				
				<div id="alertSuccess" class="alert alert-success">
					<%
						out.print(session.getAttribute("statusMsg"));
					%>
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divItemsGrid">
					<%
						Hospital hospitalObj = new Hospital();
						out.print(hospitalObj.readHospitals());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>