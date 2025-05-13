<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/amin_module/rbac/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
	$(document).ready(function() {
		$('html').bind('cut copy paste', function(e) {
			e.preventDefault();
		});
		$("html").on("contextmenu", function(e) {
			return false;
		});
		$('#role_text').keyup(function() {
			this.value = this.value.toUpperCase();
		});
		watermarkreport();
		try {
			if (window.location.href.includes("msg=")) {
				var url = window.location.href.split("?msg")[0];
				window.location = url;
			}
		} catch (e) {}
	});
</script>
<form:form name="role_mst" id="role_mst" action="role_mstAction" method='POST' commandName="role_mstCMD">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header"> <h5>Role Master</h5></div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Role Name <strong style="color: red;">*</strong></label>
							</div>
							<div class="col-12 col-md-6">
								<input id="role" name="role" maxlength="45" class="form-control" autocomplete="off">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Role Type <strong style="color: red;">*</strong></label>
							</div>
							<div class="col-12 col-md-6">
								<select name="role_type" class="form-control" id="role_type">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getRoleType}" varStatus="num">
										<option value="${item.role_type}">${item.role_type}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6">
								<label for="text-input">Access Level <strong style="color: red;">*</strong></label>
							</div>
							<div class="col-12 col-md-6">
								<select name="access_lvl" class="form-control" id="access_lvl" onchange="access_lev(this.value)">
									<option value="">--Select--</option>
									<option value="Unit">Unit</option>
									<option value="Formation">Formation</option>
									<option value="Line_dte">Line Dte</option>
									<option value="MISO">MISO</option>
									<option value="Depot">DEPOT</option>
									<option value="CTPartI">CT Part I</option>
									<option value="CTPartII">CT Part II</option>
									<option value="DG">DG</option>
									<option value="HeadQuarter">Head Quarter</option>
									<option value="CIN">CIN</option>
									<option value="DGMS">DGMS</option>
									
								</select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6" id="sub_lev" style="display: none">
								<label for="text-input">Please Select <strong style="color: red;">*</strong></label>
							</div>
							<div class="col-12 col-md-6">
								<select name="formation_se" class="form-control" id="formation_se" style="display: none" onchange="value_pass(this.value)">
									<option value="">--Select--</option>
									<option value="Command">Command</option>
									<option value="Corps">Corps</option>
									<option value="Division">Division</option>
									<option value="Brigade">Brigade</option>
								</select> 
								<select name="line_dte_se" class="form-control" id="line_dte_se" style="display: none" onchange="value_pass(this.value)">
									<option value="">--Select--</option>
									<option value="Arm">Arm</option>
									<!-- <option value="Staff">Staff</option> -->
								</select> 
								<select name="deopot_se" class="form-control" id="deopot_se" style="display: none" onchange="value_pass(this.value)">
									<option value="">--Select--</option>
									<option value="TMS">TMS</option>
									<option value="MMS">MMS</option>
								</select> 
								<select name="miso_se" class="form-control" id="miso_se" style="display: none" onchange="value_pass(this.value)">
									<option value="">--Select--</option>
									<option value="Medical">Medical</option>
								</select>
							</div>
						</div>
					</div>
				</div>
				<!-- <div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-6" id="sub_lev1" style="display: none">
								<label for="text-input">Please Select <strong style="color: red;">*</strong></label>
							</div>
							<div class="col-12 col-md-6">
								<select name="staff_se" class="form-control" id="staff_se" style="display: none" onchange="value_pass1(this.value)">
									<option value="">--Select--</option>
									<option value="MGO">MGO</option>
									<option value="SD">SD</option>
									<option value="WE">WE</option>
								</select>
							</div>
						</div>
					</div>
				</div> -->
				<input id="sub_lvl_text" name="sub_access_lvl" type="hidden">
				<input id="staff_text" name="staff_lvl" type="hidden">
			</div>
			<div class="card-footer" align="center">
				<input type="reset" class="btn btn-success btn-sm" value="Clear">
				<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return isValid();">
			</div>
		</div>
	</div>
</form:form>
<div class="container">
	<div class="col-md-6">
		<input id="searchInput" type="text" style="font-family: 'FontAwesome', Arial; margin-bottom: 5px;" placeholder="&#xF002; Search Word" size="35" width="20%" class="form-control">
	</div>
</div>
<div class="container">
	<div class="col-md-12">
		<div id="divShow"></div>
		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>
			<table id="RoleReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
				<thead style="background-color: #9c27b0; color: white;">
					<tr style="font-size: 15px;">
						<th width="10%" style="text-align: center;">Ser No</th>
						<th width="30%">Role Name</th>
						<th width="10%">Role Type</th>
						<th width="20%">Access Level</th>
						<th width="30%">Sub Access Level</th>
						<!-- <th width="20">Staff Level</th> -->
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr style="font-size: 15px;">
							<td width="10%" align="center">${num.index+1}</td>
							<td width="30%">${item.role}</td>
							<td width="10%" align="center">${item.role_type}</td>
							<td width="20%" align="center">${item.access_lvl}</td>
							<td width="30%" align="center">${item.sub_access_lvl}</td>
							<%-- <td width="20">${item.staff_lvl}</td> --%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script>
$(document).ready(function () {
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#RoleReport tbody tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
});
</script>
<script>
    function access_lev(v)
	{
    	document.getElementById('sub_lvl_text').value="";
		if(v == "Formation"){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('formation_se').style.display='block';
			document.getElementById('line_dte_se').style.display='none'; 
			document.getElementById('miso_se').style.display='none';
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('dg_se').style.display='none';
			/* document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none'; */
    		document.getElementById('staff_text').value="";
		}
		else if(v == "Line_dte" ){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('line_dte_se').style.display='block';
			document.getElementById('formation_se').style.display='none';
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('miso_se').style.display='none';
		}
		else if(v == "Depot" ){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';
			document.getElementById('deopot_se').style.display='block';	
			document.getElementById('miso_se').style.display='none';
			/* document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none'; */
    		document.getElementById('staff_text').value="";
		}
		else if(v == "Unit" ){
			document.getElementById('sub_lev').style.display='none';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';			
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('miso_se').style.display='none';
			/* document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none'; */
    		document.getElementById('staff_text').value="";
		}
		else if(v == "MISO" || v == "DG" ){
			document.getElementById('sub_lev').style.display='block';
			document.getElementById('miso_se').style.display='block';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';		
			document.getElementById('deopot_se').style.display='none';	
			/* document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none'; */
    		document.getElementById('staff_text').value="";
		}
		else if(v == "CTPartI" ){
			document.getElementById('sub_lev').style.display='none';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';		
			document.getElementById('deopot_se').style.display='none';
			document.getElementById('miso_se').style.display='none';
			/* document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none'; */
    		document.getElementById('staff_text').value="";
		}
		else if(v == "CTPartII" ){
			document.getElementById('sub_lev').style.display='none';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';		
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('miso_se').style.display='none';
			/* document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none'; */
    		document.getElementById('staff_text').value="";
		}
		else if(v == "HeadQuarter" ){
			document.getElementById('sub_lev').style.display='none';
			document.getElementById('line_dte_se').style.display='none';
			document.getElementById('formation_se').style.display='none';		
			document.getElementById('deopot_se').style.display='none';	
			document.getElementById('miso_se').style.display='none';
			/* document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none'; */
    		document.getElementById('staff_text').value="";
		}
	} 
    function value_pass(v){
    	document.getElementById('sub_lvl_text').value =v;
    	/* if(v == "Staff"){
    		document.getElementById('sub_lev1').style.display='block';
    		document.getElementById('staff_se').style.display='block';
    	}
    	else{
    		document.getElementById('sub_lev1').style.display='none';
    		document.getElementById('staff_se').style.display='none';
    		document.getElementById('staff_text').value="";
    	} */
    }
    
   /*  function value_pass1(v){
    	document.getElementById('staff_text').value =v;
    } */

	function isValid()
   	{	
		if($("input#role").val()==""){
  			alert("Please Enter Role");
  			$("input#role").focus();
  			return false;
		}
	   	if($("select#role_type").val()==" "){
   			alert("Please Select Role Type");
   			$("select#role_type").focus();
   			return false;
   		}  
    	if($("select#role_type").val()=="Other" && $("#role_text").val() == ""){
    		alert("please Enter Role type");
    		return false;
    	}
    	if($("select#access_lvl").val()==""){
   			alert("Please Select Access Level");
   			$("select#role_type").focus();
   			return false;
   		}  
    	if($("select#access_lvl").val() != "Unit" && $("select#access_lvl").val() != "MISO" && $("select#access_lvl").val() != "CTPartI" && $("select#access_lvl").val() != "CTPartII" && $("select#access_lvl").val() != "DG" &&  $("select#access_lvl").val() != "HeadQuarter" && $("select#access_lvl").val() != "CIN" && $("select#access_lvl").val() != "DGMS"){
	    	if($("input#sub_lvl_text").val() == ""){
	    		alert("Please Select Sub Access Level");   			
	   			return false;
	    	}
    	}
    	/* if(document.getElementById('sub_lvl_text').value == "Staff"){
	    	if($("staff_text").val()==""){
	    		alert("Please select sub Access Level ");   			
	    		return false;
	     	}
    	} */
    	return true;
   	}
</script>