<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="Edit_City" id="Edit_City" action="Edit_City_Action" method="post" class="form-horizontal" commandName="Edit_City_CMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Update Tehsil</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> COUNTRY NAME</label>
						                </div>
						                <div class="col-md-8">
						                <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                   <select name="country_id" id="country_id" class="form-control-sm form-control"  onchange="fn_pre_domicile_Country();"  >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${country_id}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> STATE NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="state_id" id="state_id" class="form-control-sm form-control"  onchange="fn_pre_domicile_state();"  >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getMedStateName}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> DISTRICT NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="district_id" id="district_id" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${district_id}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>  
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> TEHSIL NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="city_name" name="city_name" maxlength="50" class="form-control autocomplete" oninput="this.value = this.value.toUpperCase()" autocomplete="off" onkeypress="return onlyAlphabets(event);"> 
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			<div class="col-md-12">	  
	              			<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
<!-- 												<option value="0">--Select--</option> -->
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								</div>
            			</div>
									
						 <div class="card-footer" align="center" class="form-control">
							<a href="City" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validate();"> <!-- onclick="return validate();" -->		              		 
			            </div> 		
	        	</div>
			</div>
	</div>
</form:form>

<script>

$(document).ready(function() {
	$("#country_id").val('${Edit_City_CMD.country_id}');
	$("#state_id").val('${Edit_City_CMD.state_id}');
	$("#district_id").val('${Edit_City_CMD.district_id}');
	$("#city_name").val('${Edit_City_CMD.city_name}');
	$("#id").val('${Edit_City_CMD.city_id}');
});

function Validate() {
	if ($("select#country_id").val() == 0) {
		alert("Please Select Country");
		$("select#country_id").focus();
		return false;
	}
	if ($("select#state_id").val() == 0) {
		alert("Please Select State");
		$("select#state_id").focus();
		return false;
	}
	if ($("select#district_id").val() == 0) {
		alert("Please Select District");
		$("select#district_id").focus();
		return false;
	}
	
	if ($("input#city_name").val().trim() == "") {
		alert("Please Enter Tehsil Name");
		$("input#city_name").focus();
		return false;
	}
/* 	if ($("select#status").val() == "inactive") {
		alert("Only Select Active Status");
		$("select#status").focus();
		return false;
	} */
	
	return true;
}

function fn_pre_domicile_Country() {
	 var text = $("#country_id option:selected").text();
		
//		if(text == "OTHERS"){
//			$("#country_id").show();
//		}
//		else{
//			$("#country_id").hide();
//		}

	
	var contry_id = $('select#country_id').val();
	$.post("getStateListFormcon1?" + key + "=" + value, {
		contry_id: contry_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#state_id").html(options);
		 fn_pre_domicile_state();
		 /*fn_pre_domicile_district(); */
	}).fail(function(xhr, textStatus, errorThrown) {});
}

function fn_pre_domicile_state() {
	 var text = $("#state_id option:selected").text();
		
	/* 	if(text == "OTHERS"){
			$("#district_id").show();
		}
		else{
			$("#district_id").hide();
		} */
	
	var state_id = $('select#state_id').val();
	$.post("getDistrictListFormState1?" + key + "=" + value, {
		state_id: state_id
	}).done(function(j) {
		var options = '<option   value="0">' + "--Select--" + '</option>';
		for(var i = 0; i < j.length; i++) {
			options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
		}
		$("select#district_id").html(options);
		fn_pre_domicile_district();
	}).fail(function(xhr, textStatus, errorThrown) {});
}

</script>




