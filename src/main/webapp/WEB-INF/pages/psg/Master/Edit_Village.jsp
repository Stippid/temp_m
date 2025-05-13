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

<form:form name="Edit_Village" id="Edit_Village" action="Edit_Village_Action" method="post" class="form-horizontal" commandName="Edit_VillageCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Update Village</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> COUNTRY NAME</label>
						                </div>
						                <div class="col-md-8">
						                    <select name="country_id" id="country_id" class="form-control-sm form-control"   >
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
						                    <select name="state_id" id="state_id" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${state_id}" varStatus="num">
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
						                 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">	
						                    <select name="city_id" id="city_id" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${city_id}" varStatus="num">
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
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> VILLAGE NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="village_name" name="village_name" maxlength="50" oninput="this.value = this.value.toUpperCase()" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              				
	              			
	              			</div>
	              		
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="Village" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validate();"> <!-- onclick="return validate();" -->		              		 
			            </div> 	 		
	        	</div>
			</div>
	</div>


	</form:form>
	

	
	<Script>
	
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
		
		if ($("select#city_id").val() == 0) {
			alert("Please Select Tehsil");
			$("select#city_id").focus();
			return false;
		}
		
		if ($("input#village_name").val() == "") {
			alert("Please Enter Village");
			$("input#village_name").focus();
			return false;
		}
		
		return true;
	}
	
	
	$(document).ready(function() {
		$("#country_id").val('${Edit_VillageCMD.country_id}');
		$("#state_id").val('${Edit_VillageCMD.state_id}');
		$("#district_id").val('${Edit_VillageCMD.district_id}');
		$("#city_id").val('${Edit_VillageCMD.city_id}');
		$("#village_name").val('${Edit_VillageCMD.village_name}');
		$("#id").val('${Edit_VillageCMD.village_id}');
	});
	</Script>


