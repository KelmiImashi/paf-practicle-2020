$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	}  
	$("#alertError").hide(); }); 
 

$(document).on("click", "#btnSave", function(event) 
{  

	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 

	var status = validateHospitalForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 

	var type = ($("#hidHidSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( 
	{  
		url : "HospitalsAPI",  
		type : type,  
		data : $("#formHospital").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onHospitalSaveComplete(response.responseText, status);  
		} 
	}); 
}); 

function onHospitalSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divItemsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidHidSave").val("");  
	$("#formHospital")[0].reset(); 
} 
 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidHidSave").val($(this).closest("tr").find('#hidHidUpdate').val());     
	$("#hname").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#no").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#street").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#city").val($(this).closest("tr").find('td:eq(3)').text());
	$("#hos_charges").val($(this).closest("tr").find('td:eq(4)').text());
}); 


$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "HospitalsAPI",   
		type : "DELETE",   
		data : "hid=" + $(this).data("hid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onHospitalDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onHospitalDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divItemsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}

function validateHospitalForm() 
{  
	
	if ($("#hname").val().trim() == "")  
	{   
		return "Insert Hospital name.";  
	} 
 

	if ($("#no").val().trim() == "")  
	{   
		return "Insert Phone number.";  
	} 
	if ($("#street").val().trim() == "")  
	{   
		return "Insert Street.";  
	} 
	if ($("#city").val().trim() == "")  
	{   
		return "Insert City.";  
	} 
	if ($("#hname").val().trim() == "")  
	{   
		return "Insert Hospital name.";  
	} 
	if ($("#no").val().trim() == "")  
	{   
		return "Insert Phone number.";  
	} 
	if ($("#city").val().trim() == "")  
	{   
		return "Insert City.";  
	} 
 
	if ($("#hos_charges").val().trim() == "")  
	{   
		return "Insert Hospital Charges.";  
	} 
 
	var tmpPrice = $("#hos_charges").val().trim();  
	if (!$.isNumeric(tmpPrice))  
	{   
		return "Insert a numerical value for Hospital Charges.";  
	} 
 
	$("#hos_charges").val(parseFloat(tmpPrice).toFixed(2)); 

 
	

	return true; 
}