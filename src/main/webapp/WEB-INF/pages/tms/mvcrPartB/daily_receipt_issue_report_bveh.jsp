<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form id="dailyReceiptIssueReport" name="dailyReceiptIssueReport" action="#" method='POST' commandName="#">
	<div class="animated fadeIn">
		<div >
			<div class="container" align="center">
				<div class="card">
					<div class="card-header"><h5>B VEHICLE DAILY RECEIPT / ISSUE REPORT</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by DEPOT)</span></h6></div>
					 <div class="card-header"><strong>Receive / Issue</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<div class="form-check-inline form-check"
											style="display: inline-block; width: 700px;">
											<label for="inline-radio1" class="form-check-label">
												<input type="radio" id="inline-radios" name="inline-radios" value="1" class="form-control form-check-input" onclick="ClearField();">Free Stock
											</label> &nbsp;&nbsp;&nbsp; 
											<label for="inline-radio1" class="form-check-label "> 
												<input type="radio" id="inline-radios" name="inline-radios" value="2" class="form-control form-check-input" onclick="ClearField();">Issue to Unit
											</label>&nbsp;&nbsp;&nbsp; 
											<label for="inline-radio1" class="form-check-label "> 
												<input type="radio" id="inline-radios" name="inline-radios" value="3" class="form-control form-check-input" onclick="ClearField();">Receive From Unit
											</label>&nbsp;&nbsp;&nbsp; 
											<label for="inline-radio1" class="form-check-label "> 
												<input type="radio" id="inline-radios" name="inline-radios" value="4" class="form-control form-check-input" onclick="ClearField();">Auction 
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					 <div class="card-header"><strong>Basic Details</strong></div>					
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" style="padding-top: 10px;">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>DRR/DIR Ser No </label>
									</div>
									<div class="col-12 col-md-8">
										<span id="drr_dir_ser_nolbl"></span>
										<input type="text" id="drr_dir_ser_no" name="drr_dir_ser_no" placeholder="" class="form-control autocomplete" autocomplete="off" style="width: 70%" maxlength="50">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Depot SUS No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="8">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Depot Name </label>
									</div>
									<div class="col-12 col-md-8">
									<textarea id="unit_name" name="unit_name" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" maxlength="100"></textarea>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Report as on </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="date_report" name="date_report" class="form-control" value="${date}" readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-md-6"></div>
						</div>
				<div class="col-md-6 col-md-offset-1"></div>
					</div>
						 <div class="card-header"><strong>Vehicle Daily Issue / Receipt Details</strong></div>					
					<div class="card-body card-block">

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Issue Against </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="issue_against_rio"
											name="issue_against_rio" placeholder=""
											class="form-control autocomplete" autocomplete="off"
											readonly="readonly" maxlength="10">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group" style="padding-top: 10px;">
									<div class="col col-md-4">
										<label class=" form-control-label">Other Agency </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="other_agency" name="other_agency"
											placeholder="" class="form-control" readonly="readonly" maxlength="50">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Unit SUS No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_sus_no" name="unit_sus_no"
											placeholder="" class="form-control autocomplete"
											autocomplete="off" readonly="readonly" maxlength="8">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Name </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="unit_sus_name" name="unit_sus_name"
											placeholder="" class="form-control autocomplete"
											autocomplete="off" readonly="readonly" maxlength="100">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>BA No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="ba_no" name="ba_no" maxlength="10"
											placeholder="" class="form-control autocomplete"
											autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Chassis No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="chassis_no" name="chassis_no"
											placeholder="" class="form-control" readonly="readonly" maxlength="20">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Engine No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="engine_no" name="engine_no"
											placeholder="" class="form-control autocomplete"
											autocomplete="off" readonly="readonly" maxlength="20">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Km Run </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="kms_run" name="kms_run" placeholder=""
											class="form-control" readonly="readonly" maxlength="10">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">MCT  </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="mct_no" name="mct_no" placeholder=""
											class="form-control" readonly="readonly" maxlength="10">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Std Nomenclature </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="std_nomenclature"
											name="std_nomenclature" placeholder="" class="form-control"
											readonly="readonly" maxlength="300">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Classification </label>
									</div>
									<div class="col-12 col-md-8">
										<select name="cl" id="cl" class="form-control-sm form-control">
											<option value="">--Select--</option>
											<option value="1">1</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6"></div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Remarks </label>
									</div>
									<div class="col-12 col-md-8">
										<textarea class="form-control" name="remarks" id="remarks" maxlength="255"></textarea>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Auth </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="authority" name="authority" placeholder="" class="form-control" autocomplete="off" maxlength="50">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="right">
					<input type="hidden" id="count" name="count"> 
					<a class="btn btn-success" id="addReceiptDetails" >Save</a>
				</div>
				<div class="card-body card-block"
					style="overflow: auto; display: none;" id="bveh">
					<div class="col s12" style="">
						<table
							class="table no-margin table-striped  table-hover  table-bordered"
							id="add_reports">
							<thead>
								<tr>
									<th style="text-align: center;">Ser No</th>
									<th style="text-align: center;">Receive / Issue</th>
									<th style="text-align: center;">From / To</th>
									<th style="display: none;">Receive / Issue</th>
									<th style="display: none;">From / To</th>
									<th style="display: none;">Issue Against</th>
									<th style="display: none;">Other Agency</th>
									<th style="display: none;">Unit's SUS No</th>
									<th style="display: none;">Unit Name</th>
									<th style="text-align: center;">BA No</th>
									<th style="display: none;">Chassis No</th>
									<th style="display: none;">Engine No</th>
									<th style="display: none;" style="display:none;">Kms Run</th>
									<th style="display: none;">MCT </th>
									<th style="display: none;">Std Nomenclature</th>
									<th style="display: none;">Classification</th>
									<th style="display: none;">Remarks</th>
									<th style="display: none;">Auth</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div class="form-control card-footer" align="center">
					<a href="bveh_daily_receipt_issue_report" type="reset" class="btn btn-success btn-sm"> Clear </a> 
					<input type="hidden" id="count" name="count"> 
					<button type="button" class="btn btn-primary btn-sm" onclick="reloadPage();">Reload</button>
					
				</div>
			</div>
		</div>
	</div>
</form:form>
<script>
	function validate() {
		var a = $("input[name='inline-radios']:checked").val();
		if ($('input[name="inline-radios"]:checked').length == 0) {
			alert('Please Select Receive / Issue. ');
			return false;
		}
		if ($("#drr_dir_ser_no").val() == "") {
			alert("Please Enter the DRR/DIR Ser No.");
			$("#drr_dir_ser_no").focus();
			return false;
		}
		if ($("#sus_no").val() == "") {
			alert("Please Enter the Vehicle Depot SUS No.");
			$("#sus_no").focus();
			return false;
		}
		if ($("#drr_dir_ser_no").val() != "" && $("#sus_no").val() != "" && $("#unit_name").val() != "" && $("#ba_no").val() != "") {
			$('input[name=inline-radios]').attr("disabled", true);
			$('#drr_dir_ser_no').attr('readonly', true);
			$('#sus_no').attr('readonly', true);
			$('#unit_name').attr('readonly', true);
		}
		if(a == 2 || a == 3){
			if ($("#unit_sus_no").val() == "") {
				alert("Please Enter the Unit SUS No.");
				$("#unit_sus_no").focus();
				return false;
			}
		}
		if ($("#ba_no").val() == "") {
			alert("Please Enter the BA No.");
			$("#ba_no").focus();
			return false;
		}	
		if ($("#cl").val() == "") {
			alert("Please Select Classification.");
			$("#cl").focus();
			return false;
		}		
		if ($("#authority").val() == "") {
			alert("Please Enter the Authority No.");
			$("#authority").focus();
			return false;
		}		
		return true;
	}
</script>
<script>
$(function() {
	// Depot SUS No
	
	$("#sus_no").keyup(function(){
		var sus_no = this.value;
		var susNoAuto=$("#sus_no");
		var a = $("input[name='inline-radios']:checked").val();
		if(a == undefined){
			alert("Please Select Receive/Issue.");
			document.getElementById("unit_name").value="";
       	  	susNoAuto.val("");
			return false;
		}
		susNoAuto.autocomplete({
			source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
				url: "getDepotSUSNoDetailList?"+key+"="+value,
		        data: {sus_no:sus_no},
				success: function( data ) {
					var susval = [];
		        	  var length = data.length-1;
		        	  if(data.length != 0){
			        		var enc = data[length].substring(0,16);
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
		       		alert("Please Enter Approved Depot SUS No.");
		       	  	document.getElementById("unit_name").value="";
		       	 	document.getElementById("ba_no").value = "";
					document.getElementById("chassis_no").value = "";
					document.getElementById("engine_no").value = "";
					document.getElementById("kms_run").value = "";
					document.getElementById("mct_no").value = "";
					document.getElementById("std_nomenclature").value = "";
		       	  	susNoAuto.val("");	        	  
		       	  	susNoAuto.focus();
		       	  	return false;	             
		       	}   	         
		    }, 
			select: function( event, ui ) {
	    	 	var sus_no = ui.item.value;
	    	
	    	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
	    	 			 		if(j == ""){
	    	 			      	 	alert("Please Enter Approved Depot SUS No.");
	    			        	  	document.getElementById("unit_name").value="";
	    			        	  	document.getElementById("ba_no").value = "";
	    							document.getElementById("chassis_no").value = "";
	    							document.getElementById("engine_no").value = "";
	    							document.getElementById("kms_run").value = "";
	    							document.getElementById("mct_no").value = "";
	    							document.getElementById("std_nomenclature").value = "";
	    			        	  	susNoAuto.val("");
	    			        	  	susNoAuto.focus();
	    	 			      	}else{
		    	 	   	        	var length = j.length-1;
		    	    				var enc = j[length].substring(0,16);
		    	    				$("#unit_name").val(dec(enc,j[0]));
		    	 	   	        		if(a == 1 || a == 2 || a==4){
		    	 	   	        			
		    	 	   	        			Ba_noAutoComplate(sus_no);
		    	 	   	        		}
	    	 			      	}	
	    	 				}).fail(function(xhr, textStatus, errorThrown) {
	    	 				});
			} 	     
		});	
	});	
	// Depot Unit Name
	
 	$("#unit_name").keyup(function(){
 		var unit_name = this.value;
		var unit_nameAuto=$("#unit_name");
 		var a = $("input[name='inline-radios']:checked").val();
		if(a == undefined){
			alert("Please Select Receive/Issue.");
			document.getElementById("sus_no").value="";
       	  	unit_nameAuto.val("");
			return false;
		}
		unit_nameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url:  "getDepotNameDetailList?"+key+"="+value,
		        data: {unit_name:unit_name},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  if(data.length != 0){
			        		var enc = data[length].substring(0,16);
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
		        	  	alert("Please Enter Approved Unit Name.");
		        	  	document.getElementById("sus_no").value="";
		        	  	document.getElementById("ba_no").value = "";
						document.getElementById("chassis_no").value = "";
						document.getElementById("engine_no").value = "";
						document.getElementById("kms_run").value = "";
						document.getElementById("mct_no").value = "";
						document.getElementById("std_nomenclature").value = "";
		        	  	unit_nameAuto.val("");	        	  
		        	  	unit_nameAuto.focus();
		        	  	return false;	             
		          	}   	         
		      }, 
		      select: function( event, ui ) {
		    	  var target_unit_name = ui.item.value;
		    	
		    	 		 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:ui.item.value}).done(function(j) {				
		    	 		 		if(j == ""){
		    		    	 		alert("Please Enter Approved Unit Name.");
		    			        	document.getElementById("sus_no").value="";
		    			        	document.getElementById("ba_no").value = "";
		    						document.getElementById("chassis_no").value = "";
		    						document.getElementById("engine_no").value = "";
		    						document.getElementById("kms_run").value = "";
		    						document.getElementById("mct_no").value = "";
		    						document.getElementById("std_nomenclature").value = "";
		    			        	unit_nameAuto.val("");	        	  
		    			        	unit_nameAuto.focus();
		    		    	 	}else{
		    		    	 		var length = j.length-1;
			        				var enc = j[length].substring(0,16);
			        				$("#sus_no").val(dec(enc,j[0]));
			    			 			if(a == 1 || a == 2 || a==4){
			    			 				Ba_noAutoComplate(dec(enc,j[0]));
			     	   	        		}
		    				 	}
		    	 			}).fail(function(xhr, textStatus, errorThrown) {
		    	 			});
		  	} 	     
		}); 			
	});	
	// Unit SUS No

	$("#unit_sus_no").keyup(function(){
		var unit_sus_no = this.value;
		var susNoAuto=$("#unit_sus_no");
		var a = $("input[name='inline-radios']:checked").val();
		if(a == undefined){
			alert("Please Select Receive/Issue.");
			document.getElementById("unit_sus_name").value="";
      	  	susNoAuto.val("");
			return false;
		}
		susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getTargetSUSNoList?"+key+"="+value,
		        data: {sus_no:unit_sus_no},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  if(data.length != 0){
			        		var enc = data[length].substring(0,16);
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
		        	  alert("Please Enter Approved Unit SUS No.");
		        	  document.getElementById("unit_sus_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		    }, 
			select: function( event, ui ) {
	    	 	var unit_sus_no = ui.item.value;
	    	 	
	    	 			 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}).done(function(j) {				
	    	 			 		if(j == ""){
	    	 			      	 	alert("Please Enter Approved Unit SUS No.");
	    			        	  	document.getElementById("unit_sus_name").value="";
	    			        	  	susNoAuto.val("");
	    			        	  	susNoAuto.focus();
	    	 			      	}else{
		    	 	   	        	var length = j.length-1;
		    	    				var enc = j[length].substring(0,16);
		    	    				$("#unit_sus_name").val(dec(enc,j[0]));	
		    	 	   	        		if(a == 3){
		    			 					Ba_noAutoComplate(unit_sus_no);
		    		   	        		}
	    	 	   	        	}
	    	 				}).fail(function(xhr, textStatus, errorThrown) {
	    	 				});
			} 	     
		});	
	});	
	// Unit Unit Name
	
 	$("#unit_sus_name").keyup(function(){
 		var unit_sus_name = this.value;
		var unit_nameAuto=$("#unit_sus_name");
 		var a = $("input[name='inline-radios']:checked").val();
		if(a == undefined){
			alert("Please Select Receive/Issue.");
			document.getElementById("unit_sus_no").value="";
       	  	unit_nameAuto.val("");	
			return false;
		}
		unit_nameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getTargetUnitsNameActiveList?"+key+"="+value,
		        data: {unit_name:unit_sus_name},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  if(data.length != 0){
			        		var enc = data[length].substring(0,16);
			        	}
			        	for(var i = 0;i<data.length;i++){
			        		susval.push(dec(enc,data[i]));
			        	}
			        	response(susval); 
		          }
		        });
		      },
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Unit Name.");
		        	  document.getElementById("unit_sus_no").value="";
		        	  unit_nameAuto.val("");	        	  
		        	  unit_nameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    		var target_unit_name = ui.item.value;
		    	
		    				 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:ui.item.value}).done(function(j) {				
		    				 		if(j == ""){
		    			    	 		alert("Please Enter Approved Unit Name.");
		    					        document.getElementById("unit_sus_no").value="";
		    					        unit_nameAuto.val("");	        	  
		    					        unit_nameAuto.focus();
		    			    	 	}else{
		    					 		var length = j.length-1;
		    				        	var enc = j[length].substring(0,16);
		    				        	$("#unit_sus_no").val(dec(enc,j[0]));
		    					 		if(a == 3){
		    			 					Ba_noAutoComplate(dec(enc,j[0]));
		    		   	        		}
		    			    	 	}
		    					}).fail(function(xhr, textStatus, errorThrown) {
		    					});
		      } 	     
		}); 			
	});
});

function Ba_noAutoComplate(sus_no){

	$("#ba_no").keyup(function(){
 		var ba_no = this.value;
		var ba_noAuto=$("#ba_no");
 		var a = $("input[name='inline-radios']:checked").val();
		if(a == undefined){
			alert("Please Select Receive/Issue.");
			ba_noAuto.val("");	
			return false;
		}
		var Ba_nourl = "";
		var data = "";
		
		if(a==1){
			Ba_nourl = "getBaNoListFreeStockCase1?"+key+"="+value;
			data = {'ba_no':ba_no};
		}
		if(a==2){
			Ba_nourl = "getBaNoListIssueUnitCase2?"+key+"="+value;
			data = {'sus_no':sus_no,'ba_no':ba_no};
		}
		if(a == 3){
			Ba_nourl = "getcase3BaNoDtlList?"+key+"="+value;
			data = {'unit_sus_no':sus_no,'ba_no':ba_no};
		}
		if(a== 4){
			Ba_nourl = "getcase4BaNoDtlList?"+key+"="+value;
			data = {'sus_no':sus_no,'ba_no':ba_no};
		}
		ba_noAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: Ba_nourl,
		        data: data,
		          	success: function( data ) {
			        	var susval = [];
			        	var length = data.length-1;
			        	if(data.length != 0){
				        	var enc = data[length].substring(0,16);
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
		        	  alert("Please Enter Approved BA No.");
		        	  ba_noAuto.val("");	        	  
		        	  ba_noAuto.focus();
		        	  return false;	             
		        }   	         
		    }, 
		    select: function( event, ui ) {
		      	var ba_no = ui.item.value;
		      	document.getElementById("chassis_no").value = "";
				document.getElementById("engine_no").value = "";
				document.getElementById("mct_no").value = "";
				document.getElementById("kms_run").value = 0;
				$().BaDetailsList(ba_no,a);
			} 	     
		});  			
	});
}

$.fn.BaDetailsList = function(ba_no,a) {
 	$.post("getBaDetailsList?"+key+"="+value, {ba_no:ba_no}).done(function(j) {				
		var length = j.length-1;
		var enc = j[length][0].substring(0,16);
		$("#engine_no").val(dec(enc,j[0][0]));
		$("#chassis_no").val(dec(enc,j[0][1]));
		$("#mct_no").val(dec(enc,j[0][2]));
					
		document.getElementById("std_nomenclature").value = "";
		var mct = dec(enc,j[0][2]);
		$.post("getStdNomenList?"+key+"="+value, {mct : dec(enc,j[0][2])}).done(function(k) {				
			var length = k.length-1;
		   	var enc = k[length].substring(0,16);
		   	$("#std_nomenclature").val(dec(enc,k[0]));
		}).fail(function(xhr, textStatus, errorThrown) {
		}).fail(function(xhr, textStatus, errorThrown) {
		});

		if(a==3){
		 	$.post("getcase3KmList?"+key+"="+value, {ba_no:ba_no}).done(function(k) {				
		 		for (var l = 0; l < k.length; l++) {
					$("#kms_run").val(k[l]);
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}
		if(a == 4){
		 	$.post("getcase4KmList?"+key+"="+value, {ba_no:ba_no}).done(function(k) {				
		 		for (var l = 0; l < k.length; l++) {
					$("#kms_run").val(k[l]);
				}	
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		}
	});
};
</script>
<script>
$(function() {
	$("input[type='radio']").click(function() {
		var a = $("input[name='inline-radios']:checked").val();
		var today = new Date('${date}');
		var year = today.getFullYear();
		if (a == 1) {
			$('#unit_sus_no').attr('readonly', true);
			$('#unit_sus_name').attr('readonly', true);
			$('#other_agency').attr('readonly', true);
			$('#cl').val("1");
			$('#cl').attr("disabled", true);
			year = year + "-" + "DRR" + "-"
			
			$("#drr_dir_ser_nolbl").text(year);
		} else if (a == 2) {
			$('#other_agency').attr('readonly', true);
			year = year + "-" + "DIR" + "-"
			$("#drr_dir_ser_nolbl").text(year);
			$('#unit_sus_no').attr('readonly', false);
			$('#unit_sus_name').attr('readonly', false);
			$('#cl').attr("disabled", true);
			$('#cl').val("1");
		} else if (a == 3) {
			$('#other_agency').attr('readonly', true);
			year = year + "-" + "DRR" + "-"
			$("#drr_dir_ser_nolbl").text(year);
			$('#unit_sus_no').attr('readonly', false);
			$('#unit_sus_name').attr('readonly', false);
			$('#cl').attr("disabled", false);
			$("#cl option[value='1']").attr("disabled",true);
			$("#cl option[value='1']").hide();
		} else if (a == 4) {
			year = year + "-" + "DIR" + "-"
			$("#drr_dir_ser_nolbl").text(year);
			$('#other_agency').attr('readonly', false);
			$('#cl').attr("disabled", false);
			$("#cl option[value='1']").attr("disabled", true);
			$("#cl option[value='1']").hide();
			$('#cl').val("5");
		}
	});
});

function reloadPage() {
	location.reload(true);
}
</script>
<script>
$(document).ready(function() {
	$('#cl').attr("disabled", true);
	var max_fields = 100; //maximum input boxes allowed
	var x = 0; //initlal text box count
	$("#addReceiptDetails").click(function() {		
		var sus_no = document.getElementById("sus_no").value;
		var unit_name = document.getElementById("unit_name").value;
		var a = $("input[name='inline-radios']:checked").val();
		var issue_against_rio = document.getElementById("issue_against_rio").value;
		var other_agency = document.getElementById("other_agency").value;
		var unit_sus_no = document.getElementById("unit_sus_no").value;
		var unit_sus_name = document.getElementById("unit_sus_name").value;
		var ba_no = document.getElementById("ba_no").value;
		var chassis_no = document.getElementById("chassis_no").value;
		var engine_no = document.getElementById("engine_no").value;
		var kms_run = document.getElementById("kms_run").value;
		var mct_no = document.getElementById("mct_no").value;
		var std_nomenclature = document.getElementById("std_nomenclature").value;
		var cl = document.getElementById("cl").value;
		var remarks = document.getElementById("remarks").value;
		var authority = document.getElementById("authority").value;		
		var qty = document.getElementById("count").value;	
	
		var drr_dir_ser_no = $("#drr_dir_ser_nolbl").text()+$("#drr_dir_ser_no").val();	
		var addStatus = "";
		
		var data = validate();
		
	     
		if (a != "" && drr_dir_ser_no != "" && sus_no != "" && ba_no != "" && authority != "" && data == true) {
			 
			$.ajax({
				type : 'POST',
				url : "addDailyReceiveIssueReport?"+key+"="+value,
				data : {
					drr_dir_ser_no : drr_dir_ser_no,
					sus_no : sus_no,
					unit_name : unit_name,
					a : a,
					issue_against_rio : issue_against_rio,
					other_agency : other_agency,
					unit_sus_no : unit_sus_no,
					unit_sus_name : unit_sus_name,
					ba_no : ba_no,
					kms_run : kms_run,
					cl : cl,
					remarks : remarks,
					authority : authority,
					qty :qty
				},
				success : function(response) {
					if (response == "Failure dtl") {
						alert("Data Already Exists.");
					} 
					else if(response == "Success")
					{
						document.getElementById('bveh').style.display = 'block';
						document.getElementById("issue_against_rio").value = "";
						document.getElementById("other_agency").value = "";
					 	document.getElementById("ba_no").value = "";
						document.getElementById("chassis_no").value = "";
						document.getElementById("engine_no").value = "";
						document.getElementById("kms_run").value = "";
						document.getElementById("mct_no").value = "";
						document.getElementById("std_nomenclature").value = "";
						document.getElementById("remarks").value = "";
						
						if (a == 1) {
							var aa = "Free Stock";
							var bb = sus_no;
							$('#cl').prop('selectedIndex',1);
						} else if (a == 2) {
							var aa = "Issue To Unit";
							var bb = unit_sus_no;
							$('#cl').prop('selectedIndex',1);
						} else if (a == 3) {
							var aa = "Receive From Unit";
							var bb = unit_sus_no;
							$('#cl').prop('selectedIndex',0);
						} else if (a == 4) {
							var aa = "Auction Out";
							var bb = sus_no;
							$('#cl').prop('selectedIndex',2);
						}
						if (x < max_fields) { //max input box allowed
							if (a != "" && ba_no != "" && sus_no != "") {
								x++; //text box increment
								document.getElementById("count").value = x;
								$("table#add_reports").append(
									'<tr id="'+x+'"><td style="text-align:center;">'
											+ x
											+ '</td>'
											+ '<td style="text-align:center;"><input name="data1'+x+'" id="data1'+x+'" value="'+aa+'" readonly="true" style="text-align:center;"/></td>'
											+ '<td style="text-align:center;"><input name="data2'+x+'" id="data2'+x+'" value="'+bb+'" readonly="true" style="text-align:center;"/></td>'
											+ '<td style="display:none;"><input name="inline-radios'+x+'" id="inline-radios'+x+'" value="'+a+'" /></td>'
											+ '<td style="display:none;"><input name="issue_against_rio'+x+'" id="issue_against_rio'+x+'" value="'+issue_against_rio+'" /></td>'
											+ '<td style="display:none;"><input name="other_agency'+x+'" id="other_agency'+x+'" value="'+other_agency+'" /></td>'
											+ '<td style="display:none;"><input name="unit_sus_no'+x+'" id="unit_sus_no'+x+'" value="'+unit_sus_no+'" /></td>'
											+ '<td style="display:none;"><input name="unit_sus_name'+x+'" id="unit_sus_name'+x+'" value="'+unit_sus_name+'" /></td>'
											+ '<td style="text-align:center;"><input name="ba_no'+x+'" id="ba_no'+x+'" value="'+ba_no+'" readonly="true" style="text-align:center;"/></td>'
											+ '<td style="display:none;"><input name="chassis_no'+x+'" id="chassis_no'+x+'"  value="'+chassis_no+'"  /></td>'
											+ '<td style="display:none;"><input name="engine_no'+x+'" id="engine_no'+x+'" value="'+engine_no+'" /></td>'
											+ '<td style="display:none;"><input name="kms_run'+x+'" id="kms_run'+x+'" value="'+kms_run+'" /></td>'
											+ '<td style="display:none;"><input name="mct_no'+x+'" id="mct_no'+x+'" value="'+mct_no+'" /></td>'
											+ '<td style="display:none;"><input name="std_nomenclature'+x+'" id="std_nomenclature'+x+'" value="'+std_nomenclature+'" /></td>'
											+ '<td style="display:none;"><input name="cl'+x+'" id="cl'+x+'" value="'+cl+'" /></td>'
											+ '<td style="display:none;"><input name="remarks'+x+'" id="remarks'+x+'" value="'+remarks+'" /></td>'
											+ '<td style="display:none;"><input name="authority'+x+'" id="authority'+x+'" value="'+authority+'" /></td>'
											+ '</tr>');
								alert("Data Saved Successfully.");
							}
							else {
								alert("Please Fill Manadatory Fields.");
							}
						} else {
							alert("Add More Details of Relife Details Limit is 100.")
						}
					}
					else
					{
						alert(response);
					}
					
				}
			});
		}
	});
});

function ClearField()
{
	document.getElementById('bveh').style.display = 'block';
	document.getElementById("issue_against_rio").value = "";
	document.getElementById("drr_dir_ser_no").value = "";
	document.getElementById("sus_no").value = "";
	document.getElementById("unit_name").value = "";
	document.getElementById("other_agency").value = "";
 	document.getElementById("ba_no").value = "";
	document.getElementById("chassis_no").value = "";
	document.getElementById("engine_no").value = "";
	document.getElementById("kms_run").value = "";
	document.getElementById("mct_no").value = "";
	document.getElementById("std_nomenclature").value = "";
	document.getElementById("remarks").value = "";
	document.getElementById("authority").value = "";
	document.getElementById("unit_sus_no").value = "";
	document.getElementById("unit_sus_name").value = "";
	
}

</script>