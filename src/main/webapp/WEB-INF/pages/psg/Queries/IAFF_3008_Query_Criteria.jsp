<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<style type="text/css">
	 table.dataTable, table.dataTable th, table.dataTable td {
		-webkit-box-sizing: content-box;
		-moz-box-sizing: content-box;
		box-sizing: content-box;
		width: 90px;
		text-align: center;
		font-size: 10px;
	}
	
</style>

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">		
	    	<div class="col-md-12" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>IAFF 3008 Search Report</h5></div>
	          			<div class="card-body">
		          			<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Cont Comd </label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="cont_comd" name="cont_comd" class="form-control" >
								            	${selectcomd}
									            <c:forEach var="item" items="${getCommandList}" varStatus="num" >
									            	<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
	                  						</select>
								    	</div>
								  	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
			                			<div class="col col-md-4">
			                  				<label class="form-control-label">Cont Corps</label>
			               				</div>
			                			<div class="col-12 col-md-8">
			                 				<select id="cont_corps" name="cont_corps" class="form-control" >
			                 					${selectcorps}
			                 					<c:forEach var="item" items="${getCorpsList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
			                 				</select>
			                			</div>
					              	</div>
								</div>
							</div>
	          				<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
						                <div class="col col-md-4">
						                  <label class=" form-control-label">Cont Div</label>
						                </div>
						                <div class="col-12 col-md-8">
						                 	<select id="cont_div" name="cont_div" class="form-control" >
						                 		${selectDiv}
						                 		<c:forEach var="item" items="${getDivList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
						                 	</select>
						                </div>
						            </div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
		                				<div class="col col-md-4">
		                  					<label class=" form-control-label">Cont Bde</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="cont_bde" name="cont_bde" class="form-control" >
		                 						${selectBde}
		                 						<c:forEach var="item" items="${getBdeList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
		                 					</select>
		                				</div>
					            	</div>
								</div>
							</div>
							<div class="col-md-12">
		          				<div class="col-md-6">
		          					<div class="row form-group">
										<div class="col col-md-4">
											<label class=" form-control-label">Unit Name </label>
										</div>
										<div class="col-12 col-md-8">			
											<input type="text" id="unit_name" name="unit_name" maxlength="100" placeholder="select Unit Name" class="form-control autocomplete" >
										</div>
									</div>
		          				</div>
		          				<div class="col-md-6">
		          					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">SUS No</label>
							            </div>
							            <div class="col-12 col-md-8">
											<input type="text" id="sus_no" name="sus_no" maxlength="8" placeholder="Select SUS No" class="form-control autocomplete" >
										</div>
	              					</div>
		          				</div>
		          			</div>						
	              			<div class="col-md-12">	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Month </label>
						                </div>
						                <div class="col-md-8">						                		
						                   <select id="month" name="month" class="required form-control" >
						                   		<option value="0">--Select--</option>
												<option value="1">January</option>
											    <option value="2">February</option>
											    <option value="3">March</option>
											    <option value="4">April</option>
											    <option value="5">May</option>
											    <option value="6">June</option>
											    <option value="7">July</option>
											    <option value="8">August</option>
											    <option value="9">September</option>
											    <option value="10">October</option>
											    <option value="11">November</option>
											    <option value="12">December</option>												
										</select>
						                </div>
						            </div>
	              				</div>               					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Year </label>
						                </div>
						                <div class="col-md-8">
						                	<input type="text" id="year" name="year" class="form-control autocomplete" maxlength = "4" onkeypress="return validateNumber(event, this)" autocomplete="off" > 						                  
						                </div>
						            </div>
	              				</div>	             					              				
	              			</div>             			
						</div>
						<div class="card-footer" align="center">
							<a href="Record_Service_Query" type="reset" class="btn btn-success btn-sm"> Clear </a> 
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm"  id="btn-reload"  value="Search" />
		              	</div>			      
					</div>
	        	</div>
			</div>				
		<div class="col-md-12">
			<table id="IAFF_3008_Serving_Reporttbl" class="display table no-margin table-striped  table-hover  table-bordered ">
				<thead>
			        <tr>
			                          <th>Appt </th>		                         
			                         <th> Rank </th>
			                         <th> Name </th>
			                         			                       
			                         <th> Personal No </th>
			                         <th> CDA A/C No </th>
			                         <th> Arm/Service </th>
			                         
			                         <th> Date of Tos </th>
			                         <th> Date of Birth </th>
			                         <th> Date of Comm </th>
			                         
			                         <th> Date of Sen </th>
			                         <th> Date of Appt </th>
			                         <th> Medical Cat </th>
						<!-- <th>Comd </th>
						<th>Corps/Area </th>
						<th>Div/ Sub Area </th>
						
						<th>Bde </th>
						<th>Unit </th>
						<th>SUS No </th>
						
						<th>Cadet No </th> 
						<th>Personal No </th>
						<th>Rank </th>
						<th>Name </th> -->
			    	</tr>
				</thead>
			</table>
		</div>
		<div class="col-md-12">
			<table id="IAFF_3008_Supernumerary_Reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>
                         <th> Appt </th>	                         
                         <th> Rank </th>
                         <th> Name </th>			                       
                         <th> Personal No </th>
                         <th> CDA A/C No </th>
                         <th> Arm/Service </th>
                         <th> Date of Tos </th>
                         <th> Date of Birth </th>
                         <th> Date of Comm </th>
                         <th> Date of Sen </th>
                         <th> Date of Appt </th>
                         <th> Medical Cat </th>
			    </tr>
				</thead>
			</table>
		</div>
		<div class="col-md-12">
			<table id="IAFF_3008_Re_Employeement_Reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>
			             <th> Appt</th>		                         
                         <th> Present Rank </th>
                         <th> Name </th>			                       
                         <th> Personal No </th>
                         <th> CDA A/C No </th>
                         <th> Arm/Service </th>
                         <th> Date of TOS </th>
                         <th> Date of Birth </th>
                         <th> Date of Comm </th>
                         <th> Date of Sen </th>			                         
                         <th> Date of Appt </th>
                         <th> Medical Cat </th>
			    </tr>
				</thead>
			</table>
		</div>	
		<div class="col-md-12">
			<table id="IAFF_3008_Deserter_Reporttbl" class="display table no-margin table-striped  table-hover table-bordered ">
				<thead>
			        <tr>
			             <th> Appt </th>	                         
                         <th> Rank </th>
                         <th> Name </th>			                       
                         <th> Personal No </th>
                         <th> CDA A/C No </th>
                         <th> Arm/Service </th>
                         <th> Date of Tos </th>
                         <th> Date of Birth </th>
                         <th> Date of Comm </th>
                         <th> Date of Sen </th>
                         <th> Date of Appt </th>
                         <th> Medical Cat </th>
			    </tr>
				</thead>
			</table>
		</div>
		
	</form>

<script>
$(document).ready(function() {  
	
	mockjax1('IAFF_3008_Serving_Reporttbl');
	table = dataTable('IAFF_3008_Serving_Reporttbl');
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	});
	
	$.ajaxSetup({
		async : false
	});
	
	mockjax1('IAFF_3008_Supernumerary_Reporttbl');
	table1 = dataTable('IAFF_3008_Supernumerary_Reporttbl');
	$('#btn-reload').on('click', function(){
		table1.ajax.reload();
	});
	
	$.ajaxSetup({
		async : false
	});
	
	mockjax1('IAFF_3008_Re_Employeement_Reporttbl');
	table1 = dataTable('IAFF_3008_Re_Employeement_Reporttbl');
	$('#btn-reload').on('click', function(){
		table1.ajax.reload();
	});
	
	$.ajaxSetup({
		async : false
	});
	
	mockjax1('IAFF_3008_Deserter_Reporttbl');
	table1 = dataTable('IAFF_3008_Deserter_Reporttbl');
	$('#btn-reload').on('click', function(){
		table1.ajax.reload();
	});
	
	
	if('${roleSubAccess}' == 'Brigade')
	{
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true); 
		$("#cont_div").attr("disabled", true); 
		$("#cont_bde").attr("disabled", true);
	}
	if('${roleSubAccess}' == 'Division')
	{
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true); 
		$("#cont_div").attr("disabled", true); 
		if('${cont_div1}' != ""){
   	    	getCONTBde('${cont_div1}');
   	    }
	}
	if('${roleSubAccess}' == 'Corps')
	{
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		if('${cont_corps1}' != ""){
   		 	getCONTDiv('${cont_corps1}');
       	}
   	    if('${cont_div1}' != ""){
   	    	getCONTBde('${cont_div1}');
   	    }
	}
	if('${roleSubAccess}' == 'Command')
	{
		$("#cont_comd").attr("disabled", true);
		if('${cont_comd1}' != ""){
	    	$("#cont_comd").val('${cont_comd1}');
	    	getCONTCorps('${cont_comd1}');
    	}
    	if('${cont_corps1}' != ""){
    		 getCONTDiv('${cont_corps1}');
    	}
	    if('${cont_div1}' != ""){
	    	getCONTBde('${cont_div1}');
	    }
	}
	if('${roleAccess}' == 'MISO' || '${roleAccess}' == 'HeadQuarter')
	{
		if('${cont_comd1}' != ""){
	    	$("#cont_comd").val('${cont_comd1}');
	    	getCONTCorps('${cont_comd1}');
    	}
    	if('${cont_corps1}' != ""){
    		 getCONTDiv('${cont_corps1}');
    	}
	    if('${cont_div1}' != ""){
	    	getCONTBde('${cont_div1}');
	    }
	}
	
	var select = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
   	$('select#cont_comd').change(function() {
	   		var fcode = this.value;
	   		if(fcode == "0"){
	   			$("select#cont_corps").html(select);
	   			$("select#cont_div").html(select);
	   			$("select#cont_bde").html(select);
   		}else{	
   			$("select#cont_corps").html(select);
	   			$("select#cont_div").html(select);
	   			$("select#cont_bde").html(select);
	   			
	   			getCONTCorps(fcode);
	    	
	       		fcode += "00";	
	   			getCONTDiv(fcode);
	       	
	       		fcode += "000";	
	   			getCONTBde(fcode);
   		}
	   	});
	   	$('select#cont_corps').change(function() {
	   	   	var fcode = this.value;
   	   	if(fcode == "0"){
   	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	}else{
	   		$("select#cont_div").html(select);
   	   		$("select#cont_bde").html(select);
	   	   		getCONTDiv(fcode);
	       		fcode += "000";	
	   			getCONTBde(fcode);
	   	}
	   	});
	   	$('select#cont_div').change(function() {
	   		var fcode = this.value;    	   	
	   		if(fcode == "0"){
	 		$("select#cont_bde").html(select)
	   	}else{
	   		$("select#cont_bde").html(select)
		   		getCONTBde(fcode);
	   	}
		});
});

function getCONTCorps(fcode){
 	var fcode1 = fcode[0];
		$.post("getCorpsDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
				var enc = j[length][0].substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				
				for ( var i = 0; i < length; i++) {
					if('${cont_corps1}' ==  dec(enc,j[i][0])){
						options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
					}
				}	
				$("select#cont_corps").html(options);
			}
		});
 } 
 function getCONTDiv(fcode){
 	var fcode1 = fcode[0] + fcode[1] + fcode[2];
	   	$.post("getDivDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
	   		if(j != ""){
 		   	var length = j.length-1;
			var enc = j[length][0].substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			for ( var i = 0; i < length; i++) {
				if('${cont_div1}' ==  dec(enc,j[i][0])){
					options += '<option value="' + dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'" selected=selected >'+ dec(enc,j[i][1]) + '</option>';
				}else{
					options += '<option value="' + dec(enc,j[i][0]) +'" >'+ dec(enc,j[i][1]) + '</option>';
				}
			}
		   		$("select#cont_div").html(options);
	   		}
		});
 } 
	function getCONTBde(fcode){
		var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
		$.post("getBdeDetailsList?"+key+"="+value,{fcode:fcode1}, function(j) {
			if(j != ""){
				var length = j.length-1;
			var enc = j[length][0].substring(0,16);
			var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
			jQuery("select#cont_bde").html(options);
			for ( var i = 0; i < length; i++) {
				if('${cont_bde1}' ==  dec(enc,j[i][0])){
					options += '<option value="' +  dec(enc,j[i][0])+ '" name="'+dec(enc,j[i][1])+'" selected=selected>'+  dec(enc,j[i][1]) + '</option>';
					$("#cont_bname").val(dec(enc,j[i][1]));
				}else{
					options += '<option value="' +  dec(enc,j[i][0]) +'" name="'+dec(enc,j[i][1])+'">'+ dec(enc,j[i][1]) + '</option>';
				}
			}	
			$("select#cont_bde").html(options);
			}
	});
}
	
function data(tableName){
	if(tableName == "IAFF_3008_Serving_Reporttbl"){
		jsondata = [];
		var table = $('#'+tableName).DataTable();
		var info = table.page.info();
	
		var currentPage = info.page;
	    var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
			
		var cont_comd=$("#cont_comd").val();
	 	var cont_corps=$("#cont_corps").val();
	 	var cont_div=$("#cont_div").val();
	 	var cont_bde=$("#cont_bde").val();
	 	var unit_name=$("#unit_name").val();
	 	var sus_no=$("#sus_no").val();
	 	var month=$("#month").val();	 	
		var year=$("#year").val();	
		
		$.post("getIAFF_3008_Serving_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,month:month,year:year},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].appointment, j[i].rank,j[i].name, j[i].personal_no,j[i].cda_acc_no,j[i].parent_arm,j[i].dt_of_tos,j[i].dt_of_birth
				,j[i].dt_of_commission,j[i].dt_of_seniority,j[i].dt_of_appointment,j[i].med_cat]);
			}
		});
		$.post("getIAFF_3008_Serving_TotalCount?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,month:month,year:year},function(j) {
			FilteredRecords = j;
		});
	}
	 if(tableName == "IAFF_3008_Supernumerary_Reporttbl"){
		jsondata = [];
		var table = $('#'+tableName).DataTable();
		var info = table.page.info();
	
		var currentPage = info.page;
	    var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
			
		var cont_comd=$("#cont_comd").val();
	 	var cont_corps=$("#cont_corps").val();
	 	var cont_div=$("#cont_div").val();
	 	var cont_bde=$("#cont_bde").val();
	 	var unit_name=$("#unit_name").val();
	 	var sus_no=$("#sus_no").val();
	 	var month=$("#month").val();	 	
		var year=$("#year").val();	

		$.post("getIAFF_3008_Supernumerary_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,month:month,year:year},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].appointment, j[i].rank,j[i].name, j[i].personal_no,j[i].cda_acc_no,j[i].parent_arm,j[i].dt_of_tos,j[i].dt_of_birth
					,j[i].dt_of_commission,j[i].dt_of_seniority,j[i].dt_of_appointment,j[i].med_cat]);
			}
		});
		$.post("getIAFF_3008_Supernumerary_TotalCount?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,month:month,year:year},function(j) {
			FilteredRecords = j;
		});
	}
	 if(tableName == "IAFF_3008_Re_Employeement_Reporttbl"){
			jsondata = [];
			var table = $('#'+tableName).DataTable();
			var info = table.page.info();
		
			var currentPage = info.page;
		    var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = order[0][0] + 1;
			var orderType = order[0][1];
				
			var cont_comd=$("#cont_comd").val();
		 	var cont_corps=$("#cont_corps").val();
		 	var cont_div=$("#cont_div").val();
		 	var cont_bde=$("#cont_bde").val();
		 	var unit_name=$("#unit_name").val();
		 	var sus_no=$("#sus_no").val();
		 	var month=$("#month").val();	 	
			var year=$("#year").val();	

			$.post("getIAFF_3008_Re_Employeement_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
				cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,month:month,year:year},function(j) {
				
				for (var i = 0; i < j.length; i++) {
					jsondata.push([j[i].appointment, j[i].rank,j[i].name, j[i].personal_no,j[i].cda_acc_no,j[i].parent_arm,j[i].dt_of_tos,j[i].dt_of_birth
						,j[i].dt_of_commission,j[i].dt_of_seniority,j[i].dt_of_appointment,j[i].med_cat]);
				}
			});
			$.post("getIAFF_3008_Re_Employeement_TotalCount?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,month:month,year:year},function(j) {
				FilteredRecords = j;
			});
		} 
	
	if(tableName == "IAFF_3008_Deserter_Reporttbl"){
		jsondata = [];
		var table = $('#'+tableName).DataTable();
		var info = table.page.info();
	
		var currentPage = info.page;
	    var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
			
		var cont_comd=$("#cont_comd").val();
	 	var cont_corps=$("#cont_corps").val();
	 	var cont_div=$("#cont_div").val();
	 	var cont_bde=$("#cont_bde").val();
	 	var unit_name=$("#unit_name").val();
	 	var sus_no=$("#sus_no").val();
	 	var month=$("#month").val();	 	
		var year=$("#year").val();	

		$.post("getIAFF_3008_Deserter_ReportDataList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,
			cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,month:month,year:year},function(j) {
			
			for (var i = 0; i < j.length; i++) {
				jsondata.push([j[i].appointment, j[i].rank,j[i].name, j[i].personal_no,j[i].cda_acc_no,j[i].parent_arm,j[i].dt_of_tos,j[i].dt_of_birth
					,j[i].dt_of_commission,j[i].dt_of_seniority,j[i].dt_of_appointment,j[i].med_cat]);
			}
		});
		$.post("getIAFF_3008_Deserter_TotalCount?"+key+"="+value,{Search:Search,cont_comd:cont_comd,cont_corps:cont_corps,cont_div:cont_div,cont_bde:cont_bde,unit_name:unit_name,sus_no:sus_no,month:month,year:year},function(j) {
			FilteredRecords = j;
		});
	}
	
}	
		
</script>
<script>
$(function() {
	$("#unit_name").keypress(function(){
		var unit_name = this.value;
			 var susNoAuto=$("#unit_name");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  var enc = "";
				        	if(data.length != 0){
				        		enc = data[length].substring(0,16);
				        	}
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
				        response( susval ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit Name");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
				     
				      	var unit_name = ui.item.value;			      	
						 $.post("getActiveSusNoFromUnitName?"+key+"="+value, {unit_name:unit_name}, function(j) {
			                
			         }).done(function(j) {
			        	var length = j.length-1;
			         	var enc = j[length][0].substring(0,16);
			   			$("#sus_no").val(dec(enc,j[0][0]));
			                 
			         }).fail(function(xhr, textStatus, errorThrown) {
			         });
				      	
				     }
			      /* select: function( event, ui ) {
			      	$(this).val(ui.item.value);
			        getOrbatDetailsFromUnitName($(this).val());
			  	} 	 */     
			});
		}); 
	
	 // Source Sus No auto
	$("input#sus_no").keyup(function(){
		var sus_no = this.value;
		var unitNameAuto=$("#sus_no");
		unitNameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getSusNoActiveList?"+key+"="+value,
		        data: {sus_no:sus_no},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  var enc = "";
			        	if(data.length != 0){
			        		enc = data[length].substring(0,16);
			        	}
			        	for(var i = 0;i<data.length;i++){
			        		susval.push(dec(enc,data[i]));
			        	}
			        response( susval ); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved SUS NO");
		        	  $("#unit_name").val("");
		        	  unitNameAuto.val("");	        	  
		        	  unitNameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	var sus_no = ui.item.value;
		      	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
		       		var length = j.length-1;
					var enc = j[length].substring(0,16);
			   		$("#unit_name").val(dec(enc,j[0]));
			   		//getOrbatDetailsFromUnitName(dec(enc,j[0]))
			   	});
		     }
		});
	});
	 
	$("input#personnel_no").keypress(function(){	
		var roleSus = $("#sus_no").val();
		var personnel_no = this.value;		
			 var susNoAuto=$("#personnel_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getpersonnel_noListApprovedReport?"+key+"="+value, data: {personnel_no:personnel_no,roleSus:roleSus},			    
			          success: function( data ) {
			        	 
			        	 var susval = [];
			        	  var length = data.length-1;
			        	  var enc = data[length].substring(0,16);
				        	for(var i = 0;i<data.length;i++){
				        		susval.push(dec(enc,data[i]));
				        	}
						response( susval ); 
			          }
			        });
			      },
			      minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Personal No");
			        	  document.getElementById("personnel_no").value="";
			        	  
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	
			      } 	     
			}); 
			  
	});
});

</script>



 