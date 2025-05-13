<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


<form:form name="ba_no_amendment" id="ba_no_amendment" action="ba_no_amendmentAction" method="post" class="form-horizontal" commandName="ba_no_amendmentCMD">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					   <div class="card-header"><h3>BA No Amendment</h3></div>
					<div class="card-body card-block">
				<div class="col-md-12">
	          			<div class="col-md-6">
	          				  <div class="row form-group">
								<div class="col col-md-4">
									<label for="text-input" class="form-control-label"><strong
										style="color: red;">* </strong>Vehicle Category </label>
								</div>

								<div class="col col-md-8">
									<div class="form-check-inline form-check">
										<label for="inline-radio1" class="form-check-label ">
											<input type="radio" id="inline-radios" onClick="vehCatChange();" name="inline-radios"	value="A" class="form-check-input">A
										</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio2"
											class="form-check-label "> <input type="radio"
											id="inline-radios" onClick="vehCatChange();" name="inline-radios" value="B"class="form-check-input">B
										</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio2"
											class="form-check-label "> <input type="radio"
											id="inline-radios" onClick="vehCatChange();" name="inline-radios" value="C"class="form-check-input">C
										</label>
										<input type="hidden" id="hdveh_cat" name="hdveh_cat"class="form-control autocomplete" autocomplete="off" maxlength="10">
									</div>
								</div>
							</div>
	          		   </div>
	          		   <div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>BA No</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="ba_no" name="ba_no"class="form-control autocomplete" autocomplete="off" maxlength="10" oninput="this.value = this.value.toUpperCase()">
								</div>
							</div>
						</div>
	          	  </div>
             </div>
             <div class="col-md-12" id="typeofissue" style="display: none;" >
             		<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Type of Issue </label>
									</div>
									<div class="col-12 col-md-8">
										<select name="type_of_issue" id="type_of_issue" class="form-control-sm form-control">
											<option value="">--Select--</option>	
											<c:forEach var="item" items="${getMvcrpartCissuetypeList1}" varStatus="num" >
												<option value="${item[0]}" >${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
				</div>

					<div style="margin-left: 600px; padding-bottom: 10px;">
						<input type="button" class="btn btn-primary btn-sm" value="Revert" id="se_data" onclick="return revertData();">&emsp;&emsp;
						<input type="button" class="btn btn-primary btn-sm" value="Search" id="se_data" onclick="return validate();">
					</div>

					<div class="card-body card-block" style="display: none;" id="kk">
						<div class="col-md-6 ">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label"><strong
										style="color: red;"></strong>Year of induction </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="year_of_induction"name="year_of_induction" placeholder=""class="form-control autocomplete" maxlength="2" onkeypress="return isNumber0_9Only(event);" autocomplete="off">
									<input type="hidden" id="m_digit" name="m_digit" placeholder=""class="form-control autocomplete">
								</div>
							</div>
						</div>
						<div class="col-md-6 col-md-offset-1">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">Suffix </label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="suffix" name="suffix" class="form-control autocomplete" maxlength="1" autocomplete="off" oninput="isalpha();">
								</div>
							</div>
						</div>
					</div>

					<div class="form-control card-footer" >
						<a href="ba_no_amendment" class="btn btn-success btn-sm" >Clear</a> 
						<input type="submit" class="btn btn-primary btn-sm" value="Update"  id="update_btn">
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<div class="container" align="center">
	<div class="card">
		<div class="card-header">
			<h3>UPDATE BA NO KM</h3>
		</div>
		<div class="card-body card-block">
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col col-md-4">
							<label for="text-input" class="form-control-label"><strong style="color: red;">* </strong>Vehicle Category </label>
						</div>
						<div class="col col-md-8">
							<div class="form-check-inline form-check">
								<label for="inline-radio1" class="form-check-label">
								<input type="radio" id="inline-radiosKms" onClick="vehCatChangeKms();" name="inline-radiosKms" value="A" class="form-check-input">A
								</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio2" class="form-check-label">
								<input type="radio" id="inline-radiosKms" onClick="vehCatChangeKms();" name="inline-radiosKms" value="B" class="form-check-input" checked="checked">B
								</label>&nbsp;&nbsp;&nbsp; <label for="inline-radio2" class="form-check-label"> 
								<input type="radio" id="inline-radiosKms" onClick="vehCatChangeKms();" name="inline-radiosKms" value="C" class="form-check-input">C
								</label> <input type="hidden" id="hdveh_catKms" name="hdveh_catKms" class="form-control autocomplete" autocomplete="off" maxlength="10">
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col col-md-4">
							<label class="form-control-label"><strong style="color: red;">*</strong>BA No</label>
						</div>
						<div class="col-12 col-md-8">
							<input type="text" id="ba_noKms" name="ba_noKms" class="form-control autocomplete" autocomplete="off" maxlength="10">
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="card-body card-block">
			<div class="col-md-6 ">
				<div class="row form-group">
					<div class="col col-md-6">
						<label class=" form-control-label"><strong
							style="color: red;"></strong>Old KMs</label>
					</div>
					<div class="col-12 col-md-6">
						<input type="text" id="old_kms" name="old_kms"
							class="form-control" autocomplete="off" readonly="readonly">
					</div>
				</div>
			</div>
			<div class="col-md-6 col-md-offset-1">
				<div class="row form-group">
					<div class="col col-md-6">
						<label class=" form-control-label">New KMs </label>
					</div>
					<div class="col-12 col-md-6">
						<input type="text" id="new_kms" name="new_kms"
							class="form-control" onkeypress="return isNumber0_9Only(event);"
							autocomplete="off">
					</div>
				</div>
			</div>
		</div>
		<div class="card-body card-block" id="classificationBlock">
			<div class="col-md-6 ">
				<div class="row form-group">
					<div class="col col-md-6">
						<label class=" form-control-label"><strong
							style="color: red;"></strong>Old CL</label>
					</div>
					<div class="col-12 col-md-6">
						<select id='old_classification' name='old_classification' class="form-control" style="pointer-events:none;"> 
							<option value='1'>I</option>
							<option value='2'>II</option><option value='3'>III</option>
							<option value='4'>IV</option><option value='5'>V</option>
							<option value='6'>VI</option><option value='7'>VII</option>
						</select>
					</div>
				</div>
			</div>
			<div class="col-md-6 col-md-offset-1" >
				<div class="row form-group">
					<div class="col col-md-6">
						<label class=" form-control-label">New CL</label>
					</div>
					<div class="col-12 col-md-6">
						<select id='new_classification' name='new_classification' class="form-control"> 
							<option value='1'>I</option>
							<option value='2'>II</option><option value='3'>III</option>
							<option value='4'>IV</option><option value='5'>V</option>
							<option value='6'>VI</option><option value='7'>VII</option>
						</select>
					</div>
				</div>
			</div>
		</div>
		<div class="form-control card-footer">
			<a href="ba_no_amendment" class="btn btn-success btn-sm">Clear</a>
			<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return updateKMs()">
		</div>
	</div>
</div>
<script>
	function updateKMs() {
		var vehcat = $("#hdveh_cat").val();
		var old_kms = $("#old_kms").val();
		var new_kms = $("#new_kms").val();
		var ba_noKms = $("#ba_noKms").val();
		var old_classification = $("#old_classification").val();
		var new_classification = $("#new_classification").val();
		$.ajax({
			type : 'POST',
			url : "updateKMSandClassificationFromBaNo?" + key + "=" + value,
			data : {
				vehcat : vehcat,
				ba_no : ba_noKms,
				new_kms : new_kms,
				new_classification : new_classification
			},success : function(response){
				alert(response);
				if(response=="EM/BA No Updated"){
					$("#old_kms").val("0");
					$("#new_kms").val("0");
					$("#ba_noKms").val("");
					$("#old_classification").val("1");
					$("#new_classification").val("1");
				}
			}
		});
		
		return false;
	}

	function vehCatChangeKms() {
		$("#old_kms").val("0");
		$("#new_kms").val("0");
		$("#ba_noKms").val("");
		$("#old_classification").val("1");
		$("#new_classification").val("1");
		
		var a = $("input[name='inline-radiosKms']:checked").val();
		$("#hdveh_cat").val(a);
		
		if(a == "A"){
			$("#classificationBlock").show();
		}
		if(a == "B"){
			$("#classificationBlock").show();
		}
		if(a == "C"){
			$("#classificationBlock").hide();
		}
	}

	function vehCatChange() {
		var a = $("input[name='inline-radios']:checked").val();
		$("#hdveh_cat").val(a);
		if (a == "B") {
			$("#typeofissue").show();
		} else {
			$("#typeofissue").hide();
		}
	}

	function isNumber0_9Only(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (!(charCode >= 48 && charCode <= 57) && charCode != 8) {
			return false;
		}
		return true;
	}

	function isalpha() {
		var suffix = document.getElementById("suffix").value;
		suffix = suffix.replace(/[^A-Za-z]/g, "").toUpperCase();
		document.getElementById("suffix").value = suffix;
	}

	function validate() {
		var a = $("input[name='inline-radios']:checked").val();
		if (a == 0 || a == "") {
			alert('Please Choose Vehicle  category ');
			return false;
		}
		if ($("#ba_no").val() == "") {
			alert("Please Enter BA NO");
			return false;
		}
		var ba_no = document.getElementById("ba_no").value;
		$.ajax({
			type : 'POST',
			url : "getSearchBaNoReqChild?" + key + "=" + value,
			data : {
				a : a,
				ba_no : ba_no
			},
			success : function(response){
				if (response == "Success dtl"){
					$("#update_btn").show();
					document.getElementById('kk').style.display = 'block';
					$('#ba_no').attr('readonly', true);
					$('input[name=inline-radios]').attr("disabled", true);
					$('#se_data').attr("disabled", true);
					var demo_var = $("#ba_no").val();
					var len = demo_var.toString().length;
					var aa = demo_var.substring(0, 2);
					var bb = demo_var.substring(len - 1, len);
					var cc = demo_var.substring(2, 9);
					$("#year_of_induction").val(aa);
					$("#suffix").val(bb);
					$("#m_digit").val(cc);
				} else {
					alert("Data Does Not Exists");
				}
			}
		});
	}
	function revertData() {
		var a = $("input[name='inline-radios']:checked").val();
		if (a == 0 || a == "") {
			alert('Please Choose Vehicle  category ');
			return false;
		}
		if ($("#ba_no").val() == "") {
			alert("Please Enter BA NO");
			return false;
		}
		var ba_no = document.getElementById("ba_no").value;
		$.ajax({
			type : 'POST',
			url : "RevertDataBa_noWise?" + key + "=" + value,
			data : {
				a : a,
				ba_no : ba_no
			},
			success : function(response) {
				alert(response);
			}
		});
	}
</script>

<script>
       $(document).ready(function() {
    	   $("#update_btn").hide();
    	   try{
    			if(window.location.href.includes("msg="))
    			{
    				var url = window.location.href.split("?msg")[0];
    				window.location = url;
    			} 	
    		}
    		catch (e) {
    		}  		
   });  
</script>

<script>
$(function() {
	/*   NITIN V4 15/11/2022  */
	
	/* 	$("#ba_no").keyup(function(){
	 		var ba_no = this.value;
			var unit_nameAuto=$("#ba_no");
	 		var a = $("input[name='inline-radios']:checked").val();
			if(a == undefined){
				alert("Please Select Vehicle Category.");
				document.getElementById("ba_no").value="";
	       	  	unit_nameAuto.val("");	
				return false;
			}
			unit_nameAuto.autocomplete({
				source: function( request, response ) {
			    	$.ajax({
			        	type: 'POST',
			        	url: "getAllBaNoByVehCatList?"+key+"="+value,
			        	data: {ba_no:ba_no,veh_cat:a},
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
						document.getElementById("ba_no").value="";
						unit_nameAuto.val("");	        	  
						unit_nameAuto.focus();
						return false;	             
					}   	         
				}, 
				select: function( event, ui ) {
			    	var ba_no = ui.item.value;	     
				}	
			});
		}); */
		
		/*   NITIN V4 15/11/2022  */



 	
	$("#ba_noKms").keyup(function(){
 		var ba_no = this.value;
		var unit_nameAuto=$("#ba_noKms");
 		var a = $("input[name='inline-radiosKms']:checked").val();
		if(a == undefined){
			alert("Please Select Vehicle Category.");
			document.getElementById("ba_noKms").value="";
       	  	unit_nameAuto.val("");	
			return false;
		}
		unit_nameAuto.autocomplete({
			source: function( request, response ) {
		    	$.ajax({
		        	type: 'POST',
		        	url: "getAllBaNoByVehCatList?"+key+"="+value,
		        	data: {ba_no:ba_no,veh_cat:a},
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
					document.getElementById("ba_noKms").value="";
					unit_nameAuto.val("");	        	  
					unit_nameAuto.focus();
					return false;	             
				}   	         
			}, 
			select: function( event, ui ) {
		    	var ba_no = ui.item.value;
		    	$.post("getKms_classificationfromBaNo?"+key+"="+value, {ba_no:ba_no,veh_cat:a}).done(function(j){				
		    		if(j.length > 0){
		    			if(a=="A"){
			    			$("#old_kms").val(j[0].vehcl_kms_run);
			    			$("#new_kms").val(j[0].vehcl_kms_run);
			    			$("#old_classification").val(j[0].vehcl_classfctn);
			    			$("#new_classification").val(j[0].vehcl_classfctn);
		    			}
		    			if(a=="B"){
			    			$("#old_kms").val(j[0].kms_run);
			    			$("#new_kms").val(j[0].kms_run);
			    			$("#old_classification").val(j[0].classification);
			    			$("#new_classification").val(j[0].classification);
		    			}
		    			if(a=="C"){
			    			$("#old_kms").val(j[0].total_km_run);
			    			$("#new_kms").val(j[0].total_km_run);
			    		}
		    		}
		    	}).fail(function(xhr, textStatus, errorThrown) {
		    		
		    	});
			}	
		});
	});
});
</script> 

