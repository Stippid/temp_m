<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script src="js/cue/watermarkForinbulddatatbl.js"></script>
 <link rel="stylesheet" href="js/cue/cueWatermark.css">
	
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">  
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
	
	 body {
    user-select: none; /* Disable text selection for the whole page */
}
table {
    user-select: none; /* Disable text selection for the table */
} 
</style>
<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">
			<div  align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>Publication Of Orbat : Part II</h5></div>
	          			<div class="card-body">
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
								    	<div class="col col-md-4">
								        	<label class=" form-control-label">Command </label>
								       	</div>
								        <div class="col-12 col-md-8">
								        	<select id="cont_comd" name="cont_comd" class="form-control-sm form-control" >
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
			                  				<label class="form-control-label">Corps</label>
			               				</div>
			                			<div class="col-12 col-md-8">
			                 				<select id="cont_corps" name="cont_corps" class="form-control-sm form-control" >
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
						                  <label class=" form-control-label">Division</label>
						                </div>
						                <div class="col-12 col-md-8">
						                 	<select id="cont_div" name="cont_div" class="form-control-sm form-control" >
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
		                  					<label class=" form-control-label">Brigade</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="cont_bde" name="cont_bde" class="form-control-sm form-control" >
		                 						${selectBde}
		                 						<c:forEach var="item" items="${getBdeList}" varStatus="num" >
	                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                  							</c:forEach>
		                 					</select>
		                				</div>
					            	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
		                				<div class="col col-md-4">
		                  					<label class=" form-control-label">Line Dte</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="line_dte_sus" name="line_dte_sus" class="form-control-sm form-control" >
		                 						${selectLine_dte}
		                 						<c:forEach var="item" items="${getLine_DteList}" varStatus="num" >
	                  								<option value="${item.line_dte_sus}"  name="${item.line_dte_name}" >${item.line_dte_name}</option>
	                  							</c:forEach>
		                 					</select>
		                				</div>
					            	</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
		                				<div class="col col-md-4">
		                  					<label class=" form-control-label">Location</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="location" name="location" class="form-control-sm form-control" >
		                 						<option value="0">--Select--</option>
			                                    <c:forEach var="item" items="${getlocList}" varStatus="num" >
	                  								<option value="${item[0]}">${item[1]}</option>
	                  							</c:forEach>
		                 					</select>
		                				</div>
					            	</div>
								</div>
							</div>
						</div>
						<div class="card-footer" align="center">
							<a href="OrbatDetails_Report_Formation_Pub_CTPartII" type="reset" class="btn btn-success btn-sm" style="border-radius: 5px;"> Clear </a> 
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" style="border-radius: 5px;" onclick="return getOrbatDetailsReport();"  value="Search" /> 
		              		
			            </div>
			            <div class="card-body">
			            <p style="color: red; font-size: 16px;">Warning: Screenshots of this page is not allowed. </p>
			            	<div class="" id="OrbatDetailsReport" >
								<datatables:table id="selection" url="getOrbatDetails_Formation_Rpt_Publications_CTPARTII" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_" displayLength="25"  jqueryUI="true" lengthMenu="5,10,25,50,100,200,500,1000"
				    					bDestroy="true" filterable="false" sortable="false" processing="true" scrollY="398" scrollX="100%" scrollCollapse="true"  border="1" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true" >  
										    <datatables:column title="Ser No" property="id" searchable="false" visible="false" />
										    <datatables:column title="SUS No" property="sus_no" />
										    <datatables:column title="Unit Name" property="unit_name" />
										    <datatables:column title="Command" property="cmd_name" />
										    <datatables:column title="Corps" property="coprs_name" />
										    <datatables:column title="Div" property="div_name" />
										    <datatables:column title="Bde" property="bde_name" />
										    <datatables:column title="Loc" property="location" />
										    <datatables:column title="NRS" property="nrs" />
										    <datatables:column title="CTPart" property="ctpart" />
										    <datatables:column title="Arm" property="arm" />
										    <datatables:column title="Arm Desc" property="arm_desc" />
										    <datatables:column title="Line Dte" property="line_dte" />
										    
									</datatables:table>    
                        			<br/>
		        				</div>
			           	 	</div>
						</div>
	        		</div>
			</div>
		</form>
		
	    <c:url value="search_OrbatDetails_profileSetSession_Publications_CTPARTII" var="reloadUrl" />
		<form:form action="${reloadUrl}" method="post" id="reloadReport" name="reloadReport">
			<input type="hidden" name="sus_no1" id="sus_no1" />
			<input type="hidden" name="unit_name1" id="unit_name1" />
			<input type="hidden" name="cont_comd1" id="cont_comd1" />
			<input type="hidden" name="cont_corps1" id="cont_corps1" />
			<input type="hidden" name="cont_div1" id="cont_div1" />
			<input type="hidden" name="cont_bde1" id="cont_bde1" />
			<input type="hidden" name="line_dte_sus1" id="line_dte_sus1" />
			<input type="hidden" name="location1" id="location1" />
		</form:form>

<script>

	$(document).ready(function() {   
		$("#OrbatDetailsReport").show();
		
    	$("#sus_no").val('${sus_no1}');
    	$("#unit_name").val('${unit_name1}');
    	
    	if('${location1}' != ""){
    		$("#location").val('${location1}');
    	}
    	
    	
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
       		 	
       		 if(('${cont_comd1}' != "" && '${cont_corps1}' != "") && ('${cont_div1}' === "0" && '${cont_bde1}' != "")){
	    	    	console.log("Inside bde not null condition : ");
	    	    	var fcode2 =  $("#cont_corps").val();
	    	    	fcode2 += "000";
					getCONTBde(fcode2);
	        	}
	       	}
	   	    if('${cont_div1}' != ""){
	   	    	getCONTBde('${cont_div1}');
	   	    }
    	}
    	if('${roleSubAccess}' == 'Command')
    	{
    		$("#cont_comd").attr("disabled", true);
    		if('${cont_comd1}' != ""){
    			debugger;
    	    	$("#cont_comd").val('${cont_comd1}');
    	    	getCONTCorps('${cont_comd1}');
            	
    	    	 if(('${cont_comd1}' != "" && '${cont_corps1}' === "0" ) && '${cont_div1}' != ""){
    	    	    	var fcode1 =  $("#cont_comd").val();
    	    	    	fcode1 += "00";
    		    		getCONTDiv(fcode1);
    	        	}
    	    	    
    	    	    if(('${cont_comd1}' != "" && '${cont_corps1}' === "0") && ('${cont_div1}' === "0" && '${cont_bde1}' != "")){
    	    	    	var fcode2 =  $("#cont_comd").val();
    	    	    	fcode2 += "00000";
    					getCONTBde(fcode2);
    	        	}
    	    	
        	}
        	if('${cont_corps1}' != ""){
        		 getCONTDiv('${cont_corps1}');
        	}
    	    if('${cont_div1}' != ""){
    	    	getCONTBde('${cont_div1}');
    	    }
    	}
    	if('${roleAccess}' == 'MISO' || '${roleAccess}' == 'HeadQuarter' || '${roleAccess}' == 'Line_dte')
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
    	    
    	    if(('${cont_comd1}' != "" && '${cont_corps1}' === "0" ) && '${cont_div1}' != ""){
    	    	var fcode1 =  $("#cont_comd").val();
    	    	fcode1 += "00";
	    		getCONTDiv(fcode1);
        	}
    	    
    	    if(('${cont_comd1}' != "" && '${cont_corps1}' === "0") && ('${cont_div1}' === "0" && '${cont_bde1}' != "")){
    	    	var fcode2 =  $("#cont_comd").val();
    	    	fcode2 += "00000";
				getCONTBde(fcode2);
        	}
    	    
    	    if(('${cont_comd1}' != "" && '${cont_corps1}' != "") && ('${cont_div1}' === "0" && '${cont_bde1}' != "")){
    	    	var fcode2 =  $("#cont_corps").val();
    	    	fcode2 += "000";
				getCONTBde(fcode2);
        	}
    	    
    	    if('${line_dte_sus1}' != ""){
        		$("#line_dte_sus").val('${line_dte_sus1}');
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
 		
 		function getOrbatDetailsReport() {
 			$("div#OrbatDetailsReport").show();
 			document.getElementById('unit_name1').value = $("#unit_name").val();
  	    	document.getElementById('sus_no1').value = $("#sus_no").val();
  	    	document.getElementById('cont_comd1').value = $("#cont_comd").val();
  	    	document.getElementById('cont_corps1').value = $("#cont_corps").val();
  	    	document.getElementById('cont_div1').value = $("#cont_div").val();
  	    	document.getElementById('cont_bde1').value = $("#cont_bde").val();
  	    	document.getElementById('line_dte_sus1').value = $("#line_dte_sus").val();
  	    	document.getElementById('location1').value = $("#location").val();
  	    	$("#WaitLoader").show();
  	    	document.getElementById('reloadReport').submit();
  			$("div#OrbatDetailsReport").show();
  			return false;	
  		}
</script>

<script>
	function clearAll(){
    	$("#unit_name").val("");	
    	$("#sus_no").val("");	
    	$("#cont_comd").val("-1");	
    	$("#cont_corps").val("-1");	
    	$("#cont_div").val("-1");	
    	$("#cont_bde").val("-1");
    	$("#line_dte_sus").val("-1");
    	$("#location").val("0");
    }
	
	document.addEventListener('contextmenu', function (e) {
	    e.preventDefault();
	});
	document.addEventListener('selectstart', function (e) {
	    e.preventDefault();
	});
</script>




<script>

document.addEventListener('contextmenu', function (e) {
    e.preventDefault();
});
document.addEventListener('selectstart', function (e) {
    e.preventDefault();
});

 document.addEventListener("blur", function () {
    document.body.style.filter = "blur(10px)";
});

document.addEventListener("focus", function () {
    document.body.style.filter = "none";
}); 

document.addEventListener('keydown', (e) => {
    if (e.key === 'PrintScreen') {
        alert('Screenshots are not allowed!');
        e.preventDefault();
       // navigator.clipboard.writeText("");
    }
});




document.addEventListener('keydown', (e) => {
    if (e.key === 'F12') {
        e.preventDefault();
    }
    if (e.ctrlKey && e.shiftKey && e.key === 'I') {
        e.preventDefault();
    }
});


 
document.addEventListener('keydown', (e) => {
	    if (e.ctrlKey && e.shiftKey && e.key === 'S') {
	        e.preventDefault();
	        alert('Screenshots are disabled.');
	    }
	});
	

	
	document.addEventListener('keydown', (e) => {
    if (e.metaKey && e.shiftKey && e.key === 'S') {
        e.preventDefault(); 
        alert('Windows + Shift + S is disabled!');
    }
});


	
	document.addEventListener('dragstart', (event) => {
    event.preventDefault(); 
});

document.addEventListener('drop', (event) => {
    event.preventDefault(); 
});




document.addEventListener('keydown', (event) => {
    if (event.ctrlKey && (event.key === 'a' || event.key === 'c')) {
        event.preventDefault(); 
    }
});

/* $(document).ready( function(){
	
	$(document).on('keydown',function(e) {
		const allowedKeys = /^[a-zA-Z0-9]$/; 
	    const allowedSpecialKeys = ['Shift','Backspace']; 

	    
	    
	    if (!allowedKeys.test(event.key) && !allowedSpecialKeys.includes(event.key)) {
	    	event.preventDefault(); 
	    	navigator.clipboard.writeText("");
	    	alert('This key is not allowed!');
	    	navigator.clipboard.writeText("");
	    }
	    
	    document.addEventListener('dragstart', (event) => {
	        event.preventDefault(); 
	    });

	    document.addEventListener('drop', (event) => {
	        event.preventDefault(); 
	    });
	    
	    
	     if (e.key === 'PrintScreen') {
	         alert('Screenshots are not allowed!');
	         e.preventDefault();
	         navigator.clipboard.writeText("");
	      }
	 
	     document.addEventListener("blur", function () {
	    	 document.getElementById('OrbatDetailsReport').style.filter = "blur(10px)";
	    	});

	    	document.addEventListener("focus", function () {
	    		document.getElementById('OrbatDetailsReport').style.filter = "none";
	    	}); 

	    
	});
}); */

</script>

