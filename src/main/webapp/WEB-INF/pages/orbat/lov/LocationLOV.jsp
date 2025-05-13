<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Location LOV</title>
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/miso/assets/css/font-awesome.min.css">
<link rel="stylesheet" href="js/miso/assets/scss/style.css">
	

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/JS_CSS/jquery-3.3.1.js"></script>
<script src="js/JS_CSS/jquery.dataTables.js"></script>

<style type="text/css">
	table.dataTable, table.dataTable th, table.dataTable td {
		-webkit-box-sizing: content-box;
		-moz-box-sizing: content-box;
		box-sizing: content-box;
		/* width: 80px; */
		text-align: center;
	}
	.dataTables_scrollHead {
			overflow-y:scroll !important;
			overflow-x:hidden !important;
	}
	.DataTables_sort_wrapper{
		/* width : 80px; */
	}
	table.dataTable tr.odd {
  			background-color: #f0e2f3;
	}
	table.dataTable thead {
  			background-color: #9c27b0;
  			color: white;
	}
	.dataTables_paginate.paging_full_numbers{
		margin-top: 0.755em;
	}
	.dataTables_paginate.paging_full_numbers a{
		background-color: #9c27b0;
		border: 1px solid #9c27b0;
		color: #fff;
		border-radius: 5px;
		padding: 3px 10px;
		margin-right: 5px;
	}
	.dataTables_paginate.paging_full_numbers a:hover{
		background-color: #cb5adf;
		border-color: #cb5adf;
	}
	.dataTables_info{
		color:#9c27b0 !important;
		font-weight: bold;
	}
	
	.print_btn{
	  margin:0 auto;
	}
	.print_btn input{
	  background-color: #9c27b0;
         border-color: #9c27b0;
	}
	.print_btn input:hover{
		background-color: #cb5adf;
		border-color: #cb5adf;
	}
</style>
<style>
 	.search_header{
		padding: .75rem 1.25rem;
		margin-bottom: 0;
		background-color: rgba(0,0,0,.03);
		border-bottom: 1px solid rgba(0,0,0,.125);
		text-align: center;
		position: relative;
	}
 	.search_content{
		display: flex;
	 	padding: 1.25rem;
	 	align-items: center;
	 	padding-bottom: 0;
 	}
	.search_content label{
		display: inline-block;
	 	margin-bottom: .5rem;
 	}
 	.form-control{
		display: block;
		width: 100%;
		padding: .375rem .75rem;
		font-size: 1rem;
		line-height: 1.5;
		color: #495057;
		background-color: #fff;
		background-clip: padding-box;
		border: 1px solid #ced4da;
		border-radius: .25rem;
		transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
 	}
 	.search_buttons{
		text-align: right;
		position: absolute;
    		top: 9px;
    		right: 9px;
 	}
 	.search_buttons_footer{
		text-align: right;
 	}
 	.search_content label{
		width: 100%;
 	}
 	.search_content input{
		width: 100%;
 	}
 	.search_header h3{
		margin: 0;
		margin-right: 55px;
 	}
 	.btn{
   		text-align: center;
   		transition: all .15s ease-in-out;
   		cursor: pointer;
		padding: 2px 8px;
   		font-size: .875rem;
   		line-height: 1.5;
		color: #fff;
 	}
 	.btn-success{
   		background-color: #28a745;
   		border: 1px solid #28a745;
 	}
 	.btn-success:hover{
		background-color: #1e7e34;
   		border-color: #1c7430;
 	}
 	.btn-primary{
   		background-color: #0069d9;
		border: 1px solid #0069d9;
 	}
 	.btn-primary:hover{
   		background-color: #0d4d92;
		border-color:  #0d4d92;
	} 
</style>
<script>
	function filterGlobal () {
	    $('#example').DataTable().search().draw();
	}
	function filterColumn ( i ) {
		$('#example').DataTable().column( i ).search($('#col'+i+'_filter').val()).draw();
	}
 	$(document).ready(function() {
		if('${list}' != ""){
			$('#loc123').val('${loc1}');
			$('#nrs123').val('${nrs1}');
			$('#example').show();
			$('#example').DataTable();
		}else{
			$('#example').hide();
		}
	});

	function lovLocationFn(){
		$("#loc1").val($("#loc123").val());
	    $("#nrs1").val($("#nrs123").val());
	    document.getElementById('locLOVForm').submit();
	}
	function setid(loc,nrs_name,loc_code,trn_type,typeofloc,nrscode){
		$("#nrs").val(nrs_name);
		$("#loc").val(loc);
		$("#loc_code").val(loc_code);
		$("#Trn").val(trn_type)
		$("#type_of_loc").val(typeofloc);
		$("#nrs_code").val(nrscode);
		return true;
	}  
  	function CloseMySelf(){
		var loc = $("#loc").val();
		var nrs_name = $("#nrs").val();
		var loc_code = $("#loc_code").val();
		var trnType = $("#Trn").val();
		var typeofloc = $("#type_of_loc").val();
		var nrscode = $("#nrs_code").val();
		
		if(loc == ""){
			alert("Please select one Location");
			return false;
		}
		if(nrs_name == ""){
			alert("Please select one Location");
			return false;
		}
		if(loc_code == ""){
			alert("Please select one Location");
			return false;
		}
		if(trnType == ""){
			alert("Please select one Location");
			return false;
		}
		if(typeofloc == ""){
			alert("Please select one Location");
			return false;
		}
		if(nrscode == ""){
			alert("Please select one Location");
			return false;
		}
	    try {
	        window.opener.HandlePopupResult(loc,nrs_name,loc_code,trnType,typeofloc,nrscode);
	    }
	    catch (err) {}
	    window.close();
	    return false;
   	}
	function Close(){
		try {
  	        window.opener.HandlePopupResult('','','','','','');
  	    }
  	    catch (err) {}
  	    window.close();
  	    return false; 
  		return true;
	}
	</script>
</head>
<body>
<div class="animated fadeIn">
	<div class="card-header" align="center" style="border: 1px solid rgba(0,0,0,.125);"><h5><b>Search Location And NRS</b></h5></div>
	<div class="col-md-12" style="padding-top: 10px;">
		<div class="col-md-2"></div>
		<div class="col-md-2" align="right">
			<label for="text-input" class=" form-control-label"><b>Location</b></label>
		</div>
		<div class="col-md-2">
			<input type="text" class="column_filter" id="loc123" maxlength="100" class="form-control">
		</div>
		<div class="col-md-2" align="right">
			<label for="text-input" class=" form-control-label"><B>NRS</B></label>
		</div>
		<div class="col-md-2">
			<input type="text" class="column_filter" id="nrs123" maxlength="100" class="form-control">
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="col-md-12" style="padding-top: 10px;">
		<div class="col-md-4">
		</div>
		<div class="col-md-4" align="center">
			<input type="button" class="btn btn-primary btn-sm" onclick="lovLocationFn();" value="Find">
			<input type="submit" class="btn btn-primary btn-sm" onclick="return CloseMySelf();"  value="Select" >
			<input type="button" class="btn btn-success btn-sm" onclick="Close();" value="Close">
		</div>
		<div class="col-md-4">
		</div>
	</div>
	<div class="col-md-12">
		<hr>
	</div>
	<div class="col-md-12">
		<table id="example" class="display" style="width:100%;font-size: 10;">
        <thead style="font-size: 12px;">
            <tr>
				<th style="font-size: 15px">Ser No</th>
				<th style="font-size: 15px">Loc</th>
				<th style="font-size: 15px">Nearest Rly Stn</th>
				<th style="font-size: 15px">Loc Code</th>
				<th style="font-size: 15px">Trn Type</th>
				<th style="font-size: 15px">Type of Loc</th>
				<th style="font-size: 15px">Nrs Code</th>
			</tr>
		</thead>
		<tbody style="font-size: 10px;">
			<c:forEach var="item" items="${list}" varStatus="num" >
				<tr>
					<th><input type="radio"  style="width:1.4em;height:1.4em;background-color:red;" name="radio_name" id="radio" onclick="setid('${item[0]}','${item[1]}','${item[2]}','${item[3]}','${item[4]}','${item[5]}');"></th>
					<th>${item[0]} </th>
					<th>${item[1]} </th>
					<th>${item[2]} </th>
					<th>${item[3]} </th>
					<th>${item[4]} </th>
					<th>${item[5]} </th>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>
	<div>
		<input type="hidden" id="nrs" name="nrs"  class="form-control">
		<input type="hidden" id="loc" name="loc" class="form-control">
		<input type="hidden" id="loc_code" name="loc_code"  class="form-control">
		<input type="hidden" id="Trn" name="Trn"  class="form-control">
		<input type="hidden" id="type_of_loc" name="type_of_loc"  class="form-control">
		<input type="hidden" id="nrs_code" name="nrs_code"  class="form-control"> 
		<div class="search_buttons_footer"></div>
 	</div>
 	
 	<c:url value="locationLOVList" var="locLOVUrl" />
	<form:form action="${locLOVUrl}" method="post" id="locLOVForm" name="locLOVForm" modelAttribute="loc1">
		<input type="hidden" name="loc1" id="loc1" value="0"/>
		<input type="hidden" name="nrs1" id="nrs1" value="0"/>
	</form:form> 
</body>
</html>