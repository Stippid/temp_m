<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<style>
#tableshow {
	padding-left: 0;
}

#getSearchReport {
	border: 0;
	width: 100%;
	height: 500px;
	display: block;
	overflow: auto;
}
</style>

<form:form name="export_data" id="export_data" method='POST'>
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>Export data to Excel</h5>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><span style="font-size:12px;color:red;">*</span>Table Name </label>
								</div>
								<div class="col-md-8">
									<select name="table_name" id="table_name"
										onchange="return Search();" class="form-control">
										<option value="">--Select--</option>
										<option value="PRF">PRF GROUP MASTER</option>
										<option value="MAIN">MCT MAIN MASTER</option>
										<option value="MAKE">MAKE MASTER</option>
										<option value="MODEL">MODEL MASTER</option>
										<option value="NODAL">NODAL DTE/DEPOT MASTER</option>
										<option value="AGENCY">REQUESTING AGENCY MASTER</option>
										<option value="UPLOAD">UPLOAD DOCUMENT MVCR</option>
										<option value="UPLOADA">UPLOAD DOCUMENT FMCR</option>

									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6" style="display:none;" id="status_div">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Status </label>
								</div>
								<div class="col-md-8">
									<select name="table_status" id="table_status"
										onchange="return Search();" class="form-control">										
										<option value="0">Pending</option>
										<option value="1">Downloaded</option>						
									</select>
								</div>
							</div>
						</div>
						
					</div>
				</div>
				<div class="card-footer" align="center">
					<button id="export" type="button" class="btn btn-success btn-sm" onclick="fnExcelReport();">Export</button>
				</div>
				<div class="card-body" align="center" id="tableshow">
					<div align="right"><h6>Total Count : ${list.size()}</h6></div>
					<table id="getSearchReport" class="table no-margin table-striped  table-hover  table-bordered">
						<thead>
							<tr>
								<c:forEach var="item1" items="${listth}" varStatus="num1">
									<th style="width:10%;text-align: center;">${item1}</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="item1" items="${list}" begin="1" varStatus="num1">
								<tr id="tab${num1.index}">
									<c:forEach var="item2" items="${item1}" varStatus="num2">
										<td style="width: 10%;text-align: center;">${item2}</td>
									</c:forEach>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</form:form>

<c:url value="getAttributeFromMctMainMaster" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="tbname" id="tbname" value="0" />
	<input type="hidden" name="statusname" id="statusname" value="0" />
</form:form>

<c:url value="getDownloadImageMVCR" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id">
	<input type="hidden" name="id" id="id" value="0"/>
</form:form> 

<c:url value="getDownloadImageMCR_Report" var="imageDownloadUrl1" />
<form:form action="${imageDownloadUrl1}" method="post" id="getDownloadImageForm1" name="getDownloadImageForm1" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/>
</form:form> 
<script>
function getDownloadImageMVCR1(id){
	document.getElementById("id").value = id;
	document.getElementById("getDownloadImageForm").submit();
}
function Search(){    
	if($("#table_name").val() == ""){
		alert("Please Select Table Name.");
		$("#table_name").focus();
		return false;
	}  
	if($("#table_name").val() == "UPLOAD"){
		$("#status_div").show();
		$("#statusname").val($("#table_status").val()); 
	}  
	if($("#table_name").val() == "UPLOADA"){
		$("#status_div").show();
		$("#statusname").val($("#table_status").val()); 
	}		
	$("#tbname").val($("#table_name").val()); 
	jQuery("#WaitLoader").show();
	var kj =   document.getElementById('searchForm').submit();
}
function fnExcelReport()
{
	if($("#table_name").val() == ""){
		alert("Please Select Table Name.");
		$("#table_name").focus();
		return false;
	} 
	var tab_text="<table><tr>";
    var textRange; var j=0;
    tab = document.getElementById('getSearchReport'); 
	
    for(j = 0 ; j < tab.rows.length ; j++) 
    {
		tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
    }
    tab_text=tab_text+"</table>";
    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 
    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)) 
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",true,"Say Thanks to Sumit.xls");
    }  
    else               
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));  
    return (sa);
}
function getDownloadImageMCR1(id){  

	document.getElementById("id1").value = id;
	document.getElementById("getDownloadImageForm1").submit();
} 
</script>

<script>    
$(document).ready(function() {
	$('#table_name').val('${table_name}');
	var status='${statusname}';
	if(status!='')
		{
		$('#table_status').val(status);
		}
	$("#tab0").css("background-color", "#9c27b0");
	$("#tab0").css("color", "white");
	$("#tab0").css("font-size", "12px");
	if($("#table_name").val() == "UPLOAD"){	
			$("#status_div").show();
		}  
		if($("#table_name").val() == "UPLOADA"){
			$("#status_div").show();
		}
});
</script>