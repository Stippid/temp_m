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
	    			<div class="card-header"><h5>Orbat Data Search Report</h5></div>
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
								        	<label class=" form-control-label">Cont Comd </label>
								       	</div>
								        <div class="col-12 col-md-8">
								            <select id="cont_comd" name="cont_comd" class="form-control-sm form-control" >
									            <option value="">--Select--</option>
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
			                  				<label class=" form-control-label">Cont Corps</label>
			               				</div>
			                			<div class="col-12 col-md-8">
			                 				<select id="cont_corps" name="cont_corps" class="form-control-sm form-control" ></select>
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
						                 	<select id="cont_div" name="cont_div" class="form-control-sm form-control" ></select>
						                </div>
						            </div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
		                				<div class="col col-md-4">
		                  					<label class=" form-control-label">Cont Bde</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="cont_bde" name="cont_bde" class="form-control-sm form-control" ></select>
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
	                  						<label for="text-input" class=" form-control-label">Date (From) </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="date" id="approved_rejected_on_from" name="approved_rejected_on_from" class="form-control">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Date (To) </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="date" id="approved_rejected_on_to" name="approved_rejected_on_to" class="form-control">
										</div>
	  								</div>
								</div>
							</div>
						</div>
						<div class="card-footer" align="center">
							<a href="CommandCorSearch_Report" type="reset" class="btn btn-success btn-sm"> Clear </a> 
		              		<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm"  onclick="return getFormationwiseReport();"  value="Search" />
		              	</div>
			            <div class="card-body">
			            	<div class="" id="FormationReport" >
									<datatables:table id="selection" url="getAllOrbatDataSearchRpt" serverSide="true" pipelining="true" pipeSize="3" row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_" displayLength="5"  jqueryUI="true" lengthMenu="5,10,25,50,100,200,500,1000"
			    					bDestroy="true" filterable="false" sortable="false" processing="true" scrollY="398" scrollX="100%" scrollCollapse="true"  border="1" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true" >  
									    <datatables:column title="Ser No" property="id" searchable="false" visible="false" />
									    <datatables:column title="SUS No" property="sus_no" />
									    <datatables:column title="Unit Name" property="unit_name"  />
									    <datatables:column title="Command" property="cmd_name"  />
									    <datatables:column title="Corps" property="coprs_name"  />
									    <datatables:column title="Div" property="div_name"  />
									    <datatables:column title="Bde" property="bde_name"  />
									    <datatables:column title="Loc" property="location"  />
									    <datatables:column title="NRS" property="nrs"  />
									    <datatables:column title="Approved On" property="approved_rejected_on" renderFunction="ParseDateColumn"  />
									    <datatables:column title="Creation On" property="creation_on" renderFunction="ParseDateColumn"  />
									    <datatables:column title="Status" property="status_sus_no"  />
									    <datatables:column title="Type Of Force" property="type_of_force" />
									    <datatables:column title="CT Part I/II" property="ct_part_i_ii"  />
									    <datatables:column title="SUS Version" property="sus_version"  />
									    <datatables:column title="Date (From)" property="comm_depart_date" renderFunction="ParseDateColumn" />
									    <datatables:column title="Action" property="action"  />
									    <datatables:column title="Arm Code" property="arm_code"  />
									    <datatables:column title="Arm Name" property="arm_desc"  />
									</datatables:table>
                        			<br/>
		        				</div>
			           	 	</div>
						</div>
	        		</div>
			</div>
		</form>
		
	    <c:url value="search_Formationwise_profileSetSession" var="reloadUrl" />
		<form:form action="${reloadUrl}" method="post" id="reloadReport" name="reloadReport">
			<input type="hidden" name="sus_no1" id="sus_no1" />
			<input type="hidden" name="unit_name1" id="unit_name1" />
			<input type="hidden" name="cont_comd1" id="cont_comd1" />
			<input type="hidden" name="cont_corps1" id="cont_corps1" />
			<input type="hidden" name="cont_div1" id="cont_div1" />
			<input type="hidden" name="cont_bde1" id="cont_bde1" />
			<input type="hidden" name="status_sus_no1" id="status_sus_no1" />
			<input type="hidden" name="action1" id="action1" />
			<input type="hidden" name="from1" id="from1" />
			<input type="hidden" name="to1" id="to1" />
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
 			
 			if($("#status_sus_no").val() == ""){
 				$("div#FormationReport").hide();
 				alert("please select Status");
 				$("#status_sus_no").focus();
 				return false;
 			}
 			
  	    	document.getElementById('unit_name1').value = $("#unit_name").val();
  	    	document.getElementById('sus_no1').value = $("#sus_no").val();
  	    	document.getElementById('cont_comd1').value = $("#cont_comd").val();
  	    	document.getElementById('cont_corps1').value = $("#cont_corps").val();
  	    	document.getElementById('cont_div1').value = $("#cont_div").val();
  	    	document.getElementById('cont_bde1').value = $("#cont_bde").val();
  	    	document.getElementById('status_sus_no1').value = $("#status_sus_no").val();
  	    	document.getElementById('action1').value = $("#action").val();
  	    	document.getElementById('from1').value = $("#approved_rejected_on_from").val();
  	    	document.getElementById('to1').value = $("#approved_rejected_on_to").val();
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
    	$("#cont_comd").val("-1");	
    	$("#cont_corps").val("-1");	
    	$("#cont_div").val("-1");	
    	$("#cont_bde").val("-1");
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