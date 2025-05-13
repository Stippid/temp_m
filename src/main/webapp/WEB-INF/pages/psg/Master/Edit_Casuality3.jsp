<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="Edit_Casuality3" id="Edit_Casuality3" action="Edit_Casuality3_Action" method="post" class="form-horizontal" commandName="Edit_Casuality3_CMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE CASUALTY3</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Casualty1</label>
						                </div>
						                <div class="col-md-8">
						                  <select name="casuality1_id" id="casuality1_id" class="form-control" onchange="fn_Casuality2();" >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getCasuality1}" varStatus="num">
												   <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Casualty2</label>
						                </div>
						                <div class="col-md-8">
						                  <select name="casuality2_id" id="casuality2_id" class="form-control-sm form-control">
												<option value="0">--Select--</option>
											</select>
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Casualty3 </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
						                 <input type="text" id="casuality3" name="casuality3" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);"> 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
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
							<a href="Casuality3_Url" class="btn btn-danger btn-sm">Cancel</a>
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();">	              		 
			            </div> 		
	        	</div>
			</div>
	</div>

</form:form>


<script>
function validate() {
	if ($("select#casuality1_id").val() == 0) {
		alert("Please Select Casualty1.");
		$("select#casuality1_id").focus();
		return false;
	}
	if ($("select#casuality2_id").val() == 0) {
		alert("Please Select Casualty2.");
		$("select#casuality2_id").focus();
		return false;
	}
	if ($("input#casuality3").val().trim() == "") {
		alert("Please Enter Casualty3.");
		$("input#casuality3").focus();
		return false;
	}
	/* if ($("select#status").val() == "inactive") {
		alert("Only Select Active Status");
		$("select#status").focus();
		return false;
	} */
	return true;
}
$(document).ready(function() {
	$("#casuality3").val('${Edit_DistrictCMD.casuality3}');
	$("#casuality1_id").val('${Edit_DistrictCMD.casuality1_id}');
	$("#id").val('${Edit_DistrictCMD.id}');
	$("#casuality2_id").val('${Edit_DistrictCMD.casuality2_id}');
	$("#status").val('${Edit_DistrictCMD.status}');
	
	   $.post("getCasuality2?" + key + "=" + value, {casuality1_id : '${Edit_DistrictCMD.casuality1_id}'}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for (var i = 0; i < j.length; i++) {
				
				if( j[i][0] == '${Edit_DistrictCMD.casuality2_id}')
					options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" selected >'+ j[i][1] + '</option>';
				else
					options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
					
			}
			$("select#casuality2_id").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {
     });
	 
});
function fn_Casuality2() {
	var text = $("#casuality1_id option:selected").text();
	var casuality1_id = $('select#casuality1_id').val();
	        $.post("getCasuality2?" + key + "=" + value, {casuality1_id : casuality1_id}).done(function(j) {
						var options = '<option   value="0">' + "--Select--" + '</option>';
						for (var i = 0; i < j.length; i++) {
							options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
						}
						$("select#casuality2_id").html(options);
					}).fail(function(xhr, textStatus, errorThrown) {
			});
}
</script>

