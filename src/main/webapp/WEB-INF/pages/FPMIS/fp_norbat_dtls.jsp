<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/select2/select2.min.css">
<script>
var roleid="${roleid}";
var roleSusNo="${roleSusNo}";
var role = "${role}";
</script>
<body onload="setMenu();">
<form:form name="CreateUnitProfileFP" id="CreateUnitProfileFP" action="createNewBudgetHolder?${_csrf.parameterName}=${_csrf.token}" class="fp-form" method='POST'>

	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">
					<div class="card-header mms-card-header" style="padding:20px;">
			             <h5> <span id="lbladd"></span>CREATE BRANCHES FOR OWN HQs</h5>
					     <h6><span style="font-size: 14px;color:yellow;">(This Screen will be used to create branches within HQ and Units directly under HQ)</span></h6>
			             
			        </div> 
							<div class="card-body card-block ncard-body-bg">
							<input type="hidden" id="form_code_control1" name="form_code_control1">
							<input type="hidden" id="fmn_sus_no1" name="fmn_sus_no1">
							<input type="hidden" id="psus_no" name="psus_no">
								<div class="col-md-12">
	            					<div class="col-md-6">
	            						<div class="row form-group">
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> What to Create </label>
											</div>
											<div class="col-12 col-md-8">			
												<select name="entity_type" id="entity_type"  class="form-control-sm form-control" title="Entity">
													<option value="1">Branch</option>
													<option value="2">HQ/Unit</option>
												</select>
											</div>
										</div>
	            					</div>
	            					<div class="col-md-6">
	            						 <div class="row form-group" >
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> HQ Type</label>
											</div>
											<div class="col-12 col-md-8">
												<select name="hq_type" id="hq_type"  class="form-control-sm form-control" title="Select HQ Type">
												<option value="">-- Select HQ Level --</option>
												<c:if test="${roleid =='358'}">
				                               				<option class="hq" value="-1">Top Level</option>
				                               				<option class="hq" value="0">Dte</option>
				                               				<option class="br" value="0">Dte Br</option>
															<option class="br" value="1">Command Br</option>
				                               				<option class="br" value="2">Corps Br</option>
				                               				<option class="br" value="3">Div Br</option>
				                               				<option class="br" value="4">Bde Br </option>
				                               				<option class="hq" value="5">Unit</option>
				                               		</c:if>
				                               		<c:if test="${roleid =='357'}">
				                               				<option class="hq" value="0">Dte</option>
				                               				<option class="br" value="0">Dte Br</option>
															<option class="br" value="1">Command Br</option>
				                               				<option class="br" value="2">Corps Br</option>
				                               				<option class="br" value="3">Div Br</option>
				                               				<option class="br" value="4">Bde Br </option>
				                               				<option class="hq" value="5">Unit</option>
				                               		</c:if>
													<c:if test="${roleid =='356'}">
				                               				<option class="hq" value="0">Dte</option>
				                               				<option class="br" value="0">Dte Br</option>
															<option class="br" value="1">Command Br</option>
				                               				<option class="br" value="2">Corps Br</option>
				                               				<option class="br" value="3">Div Br</option>
				                               				<option class="br" value="4">Bde Br </option>
				                               				<option class="hq" value="5">Unit</option>
				                               		</c:if>
													<c:if test="${roleid =='336'}">
				                               				<option class="hq" value="-1">Top Level</option>
				                               				<option class="hq" value="0">Dte</option>
				                               				<option class="br" value="0">Dte Br</option>
															<option class="br" value="1">Command Br</option>
				                               				<option class="br" value="2">Corps Br</option>
				                               				<option class="br" value="3">Div Br</option>
				                               				<option class="br" value="4">Bde Br </option>
				                               				<option class="hq" value="5">Unit</option>
				                               		</c:if>
				                               		<c:if test="${roleid =='310'}">
				                               				<option class="hq" value="-1">Top Level</option>
				                               				<option class="hq" value="0">Dte</option>
				                               				<option class="br" value="0">Dte Br</option>
															<option class="br" value="1">Command Br</option>
				                               				<option class="br" value="2">Corps Br</option>
				                               				<option class="br" value="3">Div Br</option>
				                               				<option class="br" value="4">Bde Br </option>
				                               				<option class="hq" value="5">Unit</option>
				                               		</c:if>
													<c:if test="${roleid =='5'}">
															<option class="br" value="1">Command Br</option>
				                               				<option class="br" value="2">Corps Br</option>
				                               				<option class="br" value="3">Div Br</option>
				                               				<option class="br" value="4">Bde Br </option>
				                               				<option class="hq" value="5">Unit</option>
													</c:if>
													<c:if test="${roleid =='6'}">
				                               				<option class="br" value="2">Corps Br</option>
				                               				<option class="br" value="3">Div Br</option>
				                               				<option class="br" value="4">Bde Br </option>
				                               				<option class="hq" value="5">Unit</option>
													</c:if>
													<c:if test="${roleid =='7'}">
				                               				<option class="br" value="2">Corps Br</option>
				                               				<option class="br" value="3">Div Br</option>
				                               				<option class="br" value="4">Bde Br </option>
				                               				<option class="hq" value="5">Unit</option>
													</c:if>
													<c:if test="${roleid =='8'}">
				                               				<option class="br" value="4">Bde Br </option>
				                               				<option class="hq" value="5">Unit</option>
													</c:if>
		                  						</select>
											</div>
										</div>
	            					</div>
	            				</div>
	            				
	            				<div class="col-md-12">
	            				
	            					<div class="col-md-6">
	            						 <div class="row form-group" >
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> Under Formation</label>
											</div>
											<div class="col-md-8">
											
												<select name="form_code_control" id="form_code_control" class="form-control-sm form-control" title="Select Under Formation">
													<option value="-1">--Select--</option>
												</select>
											</div>
										</div>
	            					</div>
	            					
	            					<div class="col-md-6">
	            						 <div class="row form-group" >
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> Branch</label>
											</div>
											<div class="col-md-8">
												<select id="branch" class="form-control-sm form-control" title="Select Branch">
													<option value="-1">--Select Branch--</option>
													<c:forEach var="item" items="${branchs}" varStatus="num">
														<option value="${item[0]}">${item[1]}</option>
										        	</c:forEach>
												</select>
											</div>
										</div>
	            					</div>
						       </div>
							
								<div class="col-md-12">
	            					<div class="col-md-8">
	            						<div class="row form-group">
											<div class="col col-md-3">
												<label class=" form-control-label"><strong style="color: red;">*</strong> Unit Name </label>
											</div>
											<div class="col-12 col-md-9">			
												<input type="text" id="unit_name" name="unit_name" maxlength="100" size="50" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Branch / Unit Name" class="form-control autocomplete char-counter1" autocomplete="off" title="Enter Unit Name.">
											</div>
										</div>
	            					</div>
	            					<div class="col-md-4">
	            						 <div class="row form-group" >
											<div class="col col-md-4">
												<label class=" form-control-label"><strong style="color: red;">*</strong> Unique No (Auto Genr)</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="text" id="sus_no" name="sus_no" maxlength="8" style="font-family: 'FontAwesome',Arial;" class="form-control autocomplete" autocomplete="off" readonly title="Generate Sus No.">
											</div>
										</div>
	            					</div>
	            				</div>
	            				
	            				
						       <div class="col-md-12 from-group">
							       <div class="col-md-6">
			            				<div class="row form-group">
								                	<div class="col col-md-4">
								                  		<label class=" form-control-label"><strong style="color: red;">*</strong> Status</label> <br>
								                  	</div>
								                	<div class="col-12 col-md-8">
								                		<select name="status_sus_no" id="status_sus_no"  class="form-control-sm form-control" title="Select Status.">
						                               		<option value="Active">Active</option>
						                               		<option value="Inactive">Inactive</option>
				                  						</select>
								                	</div>
								       </div>
							       </div>
							        <div class="col-md-6">
							        	<input type="button" style="text-align:center;float:center;" value="Generate Unique No" onclick="return getSusIncr();" title="Generate Sus No.">
							        </div>
						       </div>
						       
				        		<div class="col-md-12 from-group">
								<div class="col-md-2">
									<label for="text-input" class=" form-control-label">Remarks</label>
								</div>
								<div class="col-md-10">
									<textarea rows="2" cols="250" class="form-control char-counter" placeholder="Enter Your Remarks..." id="remarks" name="remarks" maxlength="255" style="resize:none;" title="Enter Remarks."></textarea>
									</div>
								</div>
	              	</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-primary btn-sm nGlow" value="Clear" title="Click to Clear Data.">   
	              		<input type="submit" id="btnsave" class="btn btn-success btn-sm nGlow" value="Save" onclick="return validate();" title="Click to Save Data.">
	              	</div> 
	        	</div>
			</div>
		</div>
	</div>
</form:form>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/common/select2/select2.min.js" type="text/javascript"></script>
<script>
function validate(){
	if($("#sus_no").val() == ""){
		alert("Please Generate SUS No.");
		$("#sus_no").focus();
		return false;
    }	
	if($("#unit_name").val() == ""){
		alert("Please Enter the Unit Name.");
		$("#unit_name").focus();
		return false;
    }
	if($("#hq_type").val() == ""){
		alert("Please Select Hq Type.");
		$("#hq_type").focus();
		return false;
    }
	if($("#form_code_control").val() == "-1"){
		alert("Please Select Under Formation.");
		$("#form_code_control").focus();
		return false;
    }
	$("#psus_no").val($("#form_code_control :selected").attr("name"));
}

function getFormation() {
	var hq_type = $("#hq_type").val();
	var hq_type1 = $("#hq_type :selected").text();
	
	var form_code_control = $("#form_code_control1").val();
	$("#form_code_control").attr('readonly',false);
		
	if (hq_type =='5') {
		$.post("getAllFormationListAuto?"+key+"="+value,function(j) {
			var options = "";
			var a = [];
			var enc = j[j.length - 1].substring(0, 16);
			for (var i = 0; i < j.length; i++) {
				a[i] = dec(enc, j[i]);
			}
				var data = a[0].split(",");
				var datap;
				  for (var i = 0; i < data.length - 1; i++) {
					datap = data[i].split(":");
					options += '<option value="'+datap[1]+'" name="' + datap[2]+ '" >'+ datap[0] + '</option>';
				}  
				$("#sus_no").val("");
				$("#form_code_control").html(options);
			});			
		} else {
			$.post("getAllFormationList?"+key+"="+value, {a1 : hq_type, a2 : form_code_control},function(j) {
				var options = '';
				var a = [];
				var enc = j[j.length - 1].substring(0, 16);
				for (var i = 0; i < j.length; i++) {
					a[i] = dec(enc, j[i]);
				}
				var data = a[0].split(",");
				var datap;
				
				for (var i = 0; i < data.length; i++) {
					datap = data[i].split(":");
					options += '<option value="'+datap[1]+'" name="' + datap[2]+ '" >'+ datap[0] + '</option>';
				}
				$("#sus_no").val("");
				$("#form_code_control").html(options);
			});
		}
}

function getFormationAuto() {
	$("#form_code_control").attr('readonly',false);
	$.post("getAllFormationListAuto?"+key+"="+value,function(j) {
		var options = "";
		var a = [];
		var enc = j[j.length - 1].substring(0, 16);
		for (var i = 0; i < j.length-1; i++) {
			a[i] = dec(enc, j[i]);
		}
		var data = a[0].split(",");
		var datap;
		  for (var i = 0; i < data.length - 1; i++) {
			datap = data[i].split(":");
			options += '<option value="'+datap[1]+'" name="' + datap[2]+ '" >'+ datap[0] + '</option>';
			document.getElementById("form_code_control1").value=datap[1];
			document.getElementById("fmn_sus_no1").value=datap[2];			
		}  
		$("#sus_no").val("");
		$("#form_code_control").html(options);
	});
}

function getSusIncr() {
	
	if($("#hq_type").val() == ""){
		alert("Please Select HQ Type");
		$("#hq_type").focus();
		return false;
    }
	if($("#form_code_control").val() == "-1"){
		alert("Please Select Formation");
		$("#form_code_control").focus();
		return false;
    }
	if($("#entity_type").val() == "1" && $("#branch").val()== "-1"){
		alert("Please Select Branch");
		$("#branch").focus();
		return false;
	}
	
	if($("#unit_name").val() == ""){
		alert("Please Enter Unit Name");
		$("#unit_name").focus();
		return false;
    }
	
	if(confirm("You are about to generate SUS No for "+$("#unit_name").val()+"\n Do you wish to proceed?"))
	{
		var sus_no = $("#sus_no").val();
		var hq_type1 = $("#hq_type :selected").text();
		
		if($("#entity_type").val() == "1" && (hq_type1.indexOf("Br") > -1)){
			var formation_sus = $("#form_code_control :selected").attr("name");
			var branch = $("#branch").val();
			var sus = Number($("#hq_type").val()) > 0 ? formation_sus.substring(1,formation_sus.length-1)+branch : formation_sus.replace(formation_sus.charAt(1)+formation_sus.charAt(2),branch);
			$("#sus_no").val(sus);
		}
		else{
			$.post("generateSUSNo?"+key+"="+value, {hq_type : $("#hq_type").val()},function(j) {
				$("#sus_no").val(j);
				$("#sus_no").attr("readonly","readonly");
			});		
		}
	}
	else
		return false;
}

      $(document).ready(function() {      
    	  
    	  getFormationAuto();
    	  $(".hq").hide().attr("disabled",true); 
    	  $(".char-counter").charCounter();
    	  $('#form_code_control,#branch').select2();
    	  $("#unit_name").attr("readonly",true); 
    	  
    	  
    	  // HQ Change
    	  $('#hq_type').change(function(){
    		
    		  if($("#hq_type").val() != ""){
    			var hq = $("#hq_type :selected").text();
    			if(hq==="Dte"||hq==="Top Level"||hq==="Unit"){
    				$("#branch").attr("disabled",true);
    				$("#unit_name").removeAttr("readonly");
    				getFormationAuto();
    			}
    			else{
    				$("#branch").removeAttr("disabled");
    				$("#unit_name").attr("readonly",true);
    				getFormation();
    			}
    			$("#sus_no").val("");
    		}
    		else{
    			getFormationAuto();
    		}
    		$("#branch").val(-1);
        	$("#branch").trigger("change");
    		$("#unit_name").val("");
    		
    	  });
    	  
    	  
    	  $("#entity_type").change(function(){
    		  $("#branch").val("-1");
    		  $("#hq_type").val("");
    		  $("#branch").trigger("change");
    		  $("#unit_name").val("");
    		  $("#sus_no").val("");
    		  
    		  if($(this).val() == "1"){
        		  $("#branch").removeAttr("disabled");
        		  $("#unit_name").attr("readonly",true);
        		  $(".hq").hide().attr("disabled",true);
        		  $(".br").show().removeAttr("disabled");
        	  }
        	  else{
        		  $("#unit_name").removeAttr("readonly");
        		  $("#branch").attr("disabled",true);
        		  $(".hq").show().removeAttr("disabled");
        		  $(".br").hide().attr("disabled",true);
        	  }
    	  });
    	  
    	  
    	  $('#form_code_control').change(function(){    			
  			$("#sus_no").val("");
  			$("#psus_no").val($("#form_code_control :selected").attr("name"));
  		  });
    	  
    	  
    	  $("#form_code_control,#branch").change(function(){
    		  if($("#entity_type").val() == "1"){
    			var branch = $("#branch").val(),name ="";
    			if(branch != -1){
    				name = $("#form_code_control :selected").text()+"/"+$("#branch :selected").text();	  
    			}
    			$("#unit_name").val(name.toUpperCase());
    			$("#sus_no").val("");
        	  }
    	  });
    	  
    	  
    	  var unit_name=$("#unit_name");
    	  unit_name.autocomplete({
    			source: function(request,response) {
    				$.ajax({
    		        	type: 'POST',
    		        	url: "getAllOrbatUnitSusList?"+key+"="+value,
    		        	data: {param:unit_name.val()},
    		          	success: function(data){
    		          		var dataList = [],meta="",row="",sus="",unit="";
    		          		for(var i = 0;i<data.length;i++){
    		          			row = data[i];
    		          			if(i==0)
    		          				meta = row["meta"];
    		          			sus = dec(meta,row["sus_no"]);
    		          			unit = dec(meta,row["unit"]);
    		        			dataList.push({label: unit+' - '+sus,value:unit});
    		        		}
    		        		response(dataList); 
    		          }
    		        });
    		    },
    		    change: function(event, ui) {
    		    	var ret = !ui.item ? true : false;
    		    	$("#sus_no").val("");
    		    	return ret;
    		    },
    		    select: function( event, ui) {
    		    	if(ui.item){
    		    		alert("The selected branch/unit already exists");
    		    		unit_name.val("");
    		        	return false;	             
    		        }
    		    },
    		    minLength: 1,
    		    autoFill: true   	     
    		});
    		
    	});
</script>
