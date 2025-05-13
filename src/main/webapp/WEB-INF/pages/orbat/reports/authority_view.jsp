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
		width: 90px;
		text-align: center;
		font-size: 10px;
	}
	.dataTables_scrollHead {
		overflow-y:scroll !important;
		overflow-x:hidden !important;
	}
	.DataTables_sort_wrapper{
		width : 90px; 
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

<form action="" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn">
		
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>Search Auth Letter </h5></div>
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
	                  						<label class=" form-control-label"><strong style="color: red;">*</strong>  Status </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<select name="status_sus_no" id="status_sus_no" class="form-control-sm form-control">
                  								<option value="">--Select--</option>
                  								<option value="Pending">Pending</option>
							                    <option value="Active">Active</option>
							                    <option value="Inactive">Inactive</option>
							                    <option value="INVALID">INVALID</option>
							                    <option value="All">ALL</option> 
								            </select>
	                					</div>
	              					</div>
	              				</div>
	              				<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label"> Scenario </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                 						<select name="action" id="action"  class="form-control-sm form-control">
	                 							<option value="">--Select--</option>
			                                    <c:forEach var="item" items="${getType_Of_LetterList}" varStatus="num" >
	                  								<option value="${item.label}" >${item.label}</option>
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
											<label class=" form-control-label">Auth Letter </label>
										</div>
										<div class="col-12 col-md-8">			
											<input type="text" id="auth_letter" name="auth_letter" maxlength="100" placeholder="Search Auth letter" class="form-control autocomplete" >
										</div>
									</div>
		          				</div>
		          			</div>
						   
						   
						  
						</div>
						<div class="card-footer" align="center">
							<a href="authorityDocument_View" type="reset" class="btn btn-success btn-sm"> Clear </a> 
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm"  onclick="return getFormationwiseReport();"  value="Search" />
		              	</div>
			            <div class="card-body">
			            	<div class="" id="FormationReport" >
									<datatables:table id="selection" url="getAuthoritySearchRpt" serverSide="true" pipelining="true" pipeSize="3" row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_" displayLength="5"  jqueryUI="true" lengthMenu="5,10,25,50,100,200,500,1000"
			    					bDestroy="true" filterable="false" sortable="false" processing="true" scrollY="398" scrollX="100%" scrollCollapse="true"  border="1" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true" >  
									    <datatables:column title="Ser No" property="id" searchable="false" visible="false" />
									    <datatables:column title="SUS No" property="sus_no" />
									    <datatables:column title="Unit Name" property="unit_name"  />
									    <datatables:column title="Letter No" property="letter_no"  />
									    <datatables:column title="Approved On" property="approved_rejected_on" renderFunction="ParseDateColumn"  />
									    <datatables:column title="Status" property="status_sus_no"  />
									    <datatables:column title="SUS Version" property="sus_version"  />
									    <datatables:column title="Action" property="action"  />
									</datatables:table>
                        			<br/>
		        				</div>
			           	 	</div>
						</div>
	        		</div>
			</div>
		</form>
		
	    <c:url value="search_authDocument_profileSetSession" var="reloadUrl" />
		<form:form action="${reloadUrl}" method="post" id="reloadReport" name="reloadReport">
			<input type="hidden" name="sus_no1" id="sus_no1" />
			<input type="hidden" name="unit_name1" id="unit_name1" />
			<input type="hidden" name="status_sus_no1" id="status_sus_no1" />
			<input type="hidden" name="action1" id="action1" />
			<input type="hidden" name="to1" id="to1" />
			<input type="hidden" name="auth_letter1" id="auth_letter1" />
		</form:form>

<script>

	$(document).ready(function() {   
    	if('${status_sus_no1}' == ""){
			$("#FormationReport").hide();
		}
    	$("#sus_no").val('${sus_no1}');
    	$("#unit_name").val('${unit_name1}');
    	$("#status_sus_no").val('${status_sus_no1}');
    	$("#action").val('${action1}');
    	$("#approved_rejected_on_from").val('${from1}');
    	$("#approved_rejected_on_to").val('${to1}');
    	$("#auth_letter").val('${letter_no}');
    	
    	if('${cont_comd1}' !=""){
    		$("select#cont_comd").val('${cont_comd1}');
    		getCONTCorps('${cont_comd1}');
    	}
    	if('${cont_corps1}' != ""){
	    	getCONTDiv('${cont_corps1}');
    	}
	    if('${cont_div1}' != ""){
	   		getCONTBde('${cont_div1}'); 
	    }
	  
       	
       	if('${action1}' != ""){
       		$("select#action").val('${action1}');
       	}else{
       		$("select#action").val("");
       	}
   	  
   	   	$('select#cont_comd').change(function() {
   	   		var fcode = this.value;
   	       	getCONTCorps(fcode);
   	    	
   	       	fcode += "00";	
   	   		getCONTDiv(fcode);
   	       	
   	       	fcode += "000";	
   	   		getCONTBde(fcode);
   	   	});
   	   	$('select#cont_corps').change(function() {
   	   	   	var fcode = this.value;
   	       	getCONTDiv(fcode);
   	       	
   	       	fcode += "000";	
   	   		getCONTBde(fcode);
   	   	});
   	   	$('select#cont_div').change(function() {
   	   		var fcode = this.value;    	   	
   		   	getCONTBde(fcode);
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
 		
 		function getFormationwiseReport() {
 			debugger;
 			var a = $("#status_sus_no").val();
 			console.log(a);
 			if($("#status_sus_no").val() == ""){
 				$("div#FormationReport").hide();
 				alert("please select Status");
 				$("#status_sus_no").focus();
 				return false;
 			}
 			
  	    	document.getElementById('unit_name1').value = $("#unit_name").val();
  	    	document.getElementById('sus_no1').value = $("#sus_no").val();
  	    	document.getElementById('status_sus_no1').value = $("#status_sus_no").val();
  	    	document.getElementById('action1').value = $("#action").val();
  	    	document.getElementById('to1').value = $("#approved_rejected_on_to").val();
  	    	document.getElementById('auth_letter1').value = $("#auth_letter").val();
  	    	$("#WaitLoader").show();
  	    	document.getElementById('reloadReport').submit();
  			$("div#FormationReport").show();
  			return false;	
  		}
</script>

<script>
	function clearAll(){
    	$("#unit_name").val("");	
    	$("#sus_no").val("");	
    	$("#action").val("");
    }
	function ParseDateColumn(data, type, row) {
		 var d = new Date(data);
		    var date=d.getDate()+"-"+(d.getMonth()+1)+"-"+d.getFullYear();
		    if(date=="NaN-NaN-NaN")
		    	date="";
		    return date;
	}
</script>