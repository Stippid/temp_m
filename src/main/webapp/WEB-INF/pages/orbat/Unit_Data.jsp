<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">

 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<!-- <script src="js/Calender/jquery-2.2.3.min.js"></script> -->
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script> 


<form:form name="Unit_DataForm" id="Unit_DataForm" action="Unit_DataAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="UnitDataCMD" enctype="multipart/form-data">
<div class="container-fluid" align="center">
			<div class="row">
	<div class="card">
			<div class="card-header"><h5><b>UNIT'S DATA</b><br></h5></div>
				<div class="card-body card-block">
						<div class="col-md-12">
	          				<div class="col-md-6">
	          				   <div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Unit Name</label>
								</div>
								<div class="col-12 col-md-8">

									<input type="text" id="unit_name" name="unit_name" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Unit Name" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
	          				</div>
	          				<div class="col-md-6">
          				     	<div class="row form-group">
					                <div class="col col-md-4">
					                  <label class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
					                </div>
					               <div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" maxlength="8" class="form-control autocomplete" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search SUS No" autocomplete="off">
									</div>
					          	</div>
     				</div>
     			</div>
     					<div class="col-md-12">
	          				<div class="col-md-6">
	          				   <div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Regiment</label>
								</div>
								<div class="col-12 col-md-8">

									<input type="text" id="regiment" name="regiment" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="Enter Regiment Name" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
	          				</div>
	          				<div class="col-md-6">
          				     	<div class="row form-group">
					                <div class="col col-md-4">
					                  <label class=" form-control-label"><strong style="color: red;">*</strong>Arm</label>
					                </div>
					              <div class="col-12 col-md-8">
	                  						<select  id="arm"  name="arm" class="form-control" >
	                  						<option value="0">--Select--</option>
												<c:forEach var="item" items="${getParentArmList}" varStatus="num">
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
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Date Of Raising</label>
								</div>
								<div class="col-12 col-md-8">

									
									<input type="text" name="date_of_raising" id="date_of_raising" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</div>
							</div>
	          				</div>
	          				<div class="col-md-6">
          				     	<div class="row form-group">
					                <div class="col col-md-4">
					                  <label class=" form-control-label"><strong style="color: red;">*</strong>Important Events</label>
					                </div>
					              <div class="col-12 col-md-8">
	                  						<select  id="imp_events"  name="imp_events" class="form-control" >
	                  						<option value="0">--Select--</option>
	                  						<option value="1">Silver Jubilee</option>
	                  						<option value="2">Golden Jubilee</option>
	                  						<option value="3">Diamond Jubilee</option>
	                  						<option value="4">Centenary Jubilee</option>

	                  						</select>
										</div>
					          	</div>
     				</div>
     			</div>
     			<br><br>
     			<br><br>
     			
				<div  align="center">     
							<label class=" form-control-label" style="margin-right: 100px;text-align:center;"><h4>Previous Locations Served</h4></label>     
     			</div>
     			<div class="col-md-12">
						 <div class="row form-group">

							<table id="previous_loc_table" class="table-bordered "
								style="margin-top: 10px; width: 100%;">

								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th>Station</th>
										<th>Type</th>
										<th>Classification</th>
										<th>Comd</th>
										<th>Corps</th>
										<th>Area</th>
										<th>Div</th>	
										<th>Sub Area</th>	
										<th>Brigade</th>
										<th>From Date</th>
										<th>To Date</th>
										<th class="hide-action">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="prev_locs_tbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_sibling1">
										<td class="sib_srno">1</td>
										<td><input type="text" id="station1"
											name="station1" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()"
											autocomplete="off"></td>
										<td>
										<input type="text" id="type1"
											name="type1" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()"
											autocomplete="off">

										</td>
										<td><input type="text" id="classification1"
											name="classification1" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()"
											autocomplete="off"></td>

										<td><select id="comd1" name="comd1" class="form-control">
										        		<option value="">--Select--</option>
					                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
										        	</select></td>

										<td><select id="corps1" name="corps1" class="form-control">
					                                    <option value="0">--Select--</option>
					                                     <c:forEach var="item" items="${getCorpsList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
				                                	</select></td>
										<td>
										<input type="text" id="comd1"
											name="comd1" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()"
											autocomplete="off">
										</td>
										
											
										<td><select id="div1" name="div1" class="form-control">
					                                   <option value="0">--Select--</option>
					                                    <c:forEach var="item" items="${getDivList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
										          	</select>
											</td>
											<td><input type="text" id="area1"
											name="area1" class="form-control autocomplete"
											maxlength="50" autocomplete="off"></td>
											<td><select id="bde1" name="bde1" class="form-control">
					                                    <option value="0">--Select--</option>
					                                     <c:forEach var="item" items="${getBdeList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
					                                </select></td>
					                                
											<td><input
											type="text" name="from_date1" id="from_date1"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control" style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">
											</td>
												<td><input
											type="text" name="to_date1" id="to_date1"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control" style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">
											</td>
										<td class="hide-action"><a
											class="btn btn-primary btn-sm" value="SAVE" title="SAVE"
											id="family_save1" onclick="family_save_fn(1);"><i
												class="fa fa-save"></i></a> <a style="display: none;"
											class="btn btn-success btn-sm" value="ADD" title="ADD"
											id="family_add1" onclick="family_add_fn(1);"><i
												class="fa fa-plus"></i></a> <a style="display: none;"
											class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
											id="family_remove1" onclick="family_remove_fn(1);"><i
												class="fa fa-trash"></i></a></td>
									</tr>
								</tbody>
							</table>
							</div>
							</div>
     			<div  align="center">     
							<label class=" form-control-label" style="margin-right: 100px;text-align:center;"><h4>Present Location</h4></label>     
     			</div>
     		<div class="col-md-12">
						 <div class="row form-group">

							<table id="present_loc_table" class="table-bordered "
								style="margin-top: 10px; width: 100%;">

								<thead style="color: white; text-align: center;">
									<tr>
										<th>Sr No</th>
										<th>Station</th>
										<th>Type</th>
										<th>Classification</th>
										<th>Comd</th>
										<th>Corps</th>
										<th>Area</th>
										<th>Div</th>	
										<th>Sub Area</th>	
										<th>Brigade</th>	
																	
										<th>From Date</th>
										<th>To Date</th>
										<th>Tenure</th>
										<th class="hide-action">Action</th>
									</tr>
								</thead>
								<tbody data-spy="scroll" id="family_sibtbody"
									style="min-height: 46px; max-height: 650px; text-align: center;">
									<tr id="tr_sibling1">
										<td class="sib_srno">1</td>
										<td><input type="text" id="station2"
											name="station2" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()"
											autocomplete="off"></td>
										<td>
										<input type="text" id="type2"
											name="type2" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()"
											autocomplete="off">

										</td>
										<td><input type="text" id="sib_name1"
											name="sib_name1" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()"
											autocomplete="off"></td>

										<td><select id="comd2" name="comd2" class="form-control">
										        		<option value="">--Select--</option>
					                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
										        	</select></td>

										<td><select id="corps2" name="corps2" class="form-control">
					                                    <option value="0">--Select--</option>
					                                    <c:forEach var="item" items="${getCorpsList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
				                                	</select></td>
										<td>
										<input type="text" id="sib_name1"
											name="sib_name1" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()"
											autocomplete="off">
										</td>
										
										<td>	<select id="div2" name="div2" class="form-control">
					                                   <option value="0">--Select--</option>
					                                    <c:forEach var="item" items="${getDivList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
										          	</select>
											</td>
											 <td><input type="text" id="sib_name1"
											name="sib_name1" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()"
											autocomplete="off"></td>
										
											<td>	<select id="op_bde" name="op_bde" class="form-control">
					                                    <option value="0">--Select--</option>
					                                     <c:forEach var="item" items="${getBdeList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
					                                </select>
					                                </td>
					                                	<td>
					                               <input
											type="text" name="from_date2" id="from_date2"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control" style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');validateDate_BackDate(this.value,this);"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">
											</td>
												<td width="140px"><input
											type="text" name="to_date2" id="to_date2"
											maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
											class="form-control" style="width: 85%; display: inline;"
											onfocus="this.style.color='#000000'"
											onblur="clickrecall(this,'DD/MM/YYYY');"
											onkeyup="clickclear(this, 'DD/MM/YYYY')"
											onchange="clickrecall(this,'DD/MM/YYYY');validateDate_FutureDate(this.value,this);"
											aria-required="true" autocomplete="off"
											style="color: rgb(0, 0, 0);" value="DD/MM/YYYY">
											</td>
											
											<td><input type="text" id="sib_name1"
											name="sib_name1" class="form-control autocomplete"
											onkeypress="return onlyAlphabets(event);" maxlength="50" 
											oninput="this.value = this.value.toUpperCase()"
											autocomplete="off"></td>
											
										<td class="hide-action"><a
											class="btn btn-primary btn-sm" value="SAVE" title="SAVE"
											id="family_save1" onclick="family_save_fn(1);"><i
												class="fa fa-save"></i></a> <a style="display: none;"
											class="btn btn-success btn-sm" value="ADD" title="ADD"
											id="family_add1" onclick="family_add_fn(1);"><i
												class="fa fa-plus"></i></a> <a style="display: none;"
											class="btn btn-danger btn-sm" value="REMOVE" title="REMOVE"
											id="family_remove1" onclick="family_remove_fn(1);"><i
												class="fa fa-trash"></i></a></td>
									</tr>
								</tbody>
							</table>
							</div>
							</div>
     			</div>

     			 <div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">
	              	</div>
     			</div>
     			
     			</div>
     			</div>
	          			
</form:form>

<script>


function family_add_fn(q) {
	if($('#family_add' + q).length) {
		$("#family_add" + q).hide();
	}
	if(q != 0) sib_srno += 1;
	sib = q + 1;
	$("table#family_table").append('<tr id="tr_sibling' + sib + '">' + '<td class="sib_srno">' + sib_srno + '</td>' 
			+ '<td> <input type="sib_name' + sib + '" id="sib_name' + sib + '" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()" class="form-control" maxlength="50" autocomplete= "off" maxlength="50">'
		+ '<td>'
		
		+ ' <input type="text" name="sib_date_of_birth' + sib + '" id="sib_date_of_birth' + sib + '" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 85%;display: inline;" ' + '	onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');validateDate_BackDate(this.value,this);" onkeyup="clickclear(this, \'DD/MM/YYYY\')" ' + '	onchange="clickrecall(this,\'DD/MM/YYYY\');validateDate_FutureDate(this.value,this);" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY">' + '</td>' + '<td> <select name="sib_relationship' + sib + '" id="sib_relationship' + sib + '" class="form-control-sm form-control"   >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getFamily_siblings}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '	id="sib_adhar_number' + sib + '" name="sib_adhar_number' + sib + '" ' + '	class="form-control autocomplete" maxlength="14"  ' + '		onkeydown="return AvoidSpace(event)" onkeypress="return isAadhar(this,event); " autocomplete="off"></td> ' + '<td> ' + '	<input type="text" id="sib_pan_no' + sib + '" name="sib_pan_no' + sib + '" ' + '	class="form-control autocomplete" autocomplete="off" ' + ' 	onkeydown="return AvoidSpace(event)"	onchange=" isPAN(this); " ' + '	oninput="this.value = this.value.toUpperCase()" ' + '	maxlength="10"> ' + '	</td> ' + '<td style="display:none;"><input type="text" id="sib_ch_id' + sib + '" name="sib_ch_id' + sib + '"   value="0" class="form-control autocomplete" autocomplete="off" ></td>' 

		+ '<td><input type="checkbox" ' + '	id="ser-ex' + sib + '" name="ser-ex' + sib + '" ' + '	value="Yes"  ' + '	onclick="siblingCB(' + sib + ');"></td> '
		+ '<td> <select name="sibling_service' + sib + '" id="sibling_service' + sib + '" class="form-control"  onchange = "otherfunction(' + sib + ')" >' + '<option value="0">--Select--</option>' + '<c:forEach var="item" items="${getExservicemenList}" varStatus="num">' + '<option value="${item[0]}" name="${item[1]}">${item[1]}</option>' + '</c:forEach></select></td>' + '<td><input type="text" ' + '	maxlength="9" min="7"  id="sibling_personal_no' + sib + '" name="sibling_personal_no' + sib + '" ' + '	class="form-control" autocomplete="off" ></td> '
		+ '<td><input type="text" ' + '	id="other_sibling_ser' + sib + '" name="other_sibling_ser' + sib + '" ' + '	class="form-control" onkeypress="return onlyAlphaNumeric(event);" 	 autocomplete="off" maxlength="50" ></td>'
		+ '<td class="hide-action"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "family_save' + sib + '" onclick="family_save_fn(' + sib + ');" ><i class="fa fa-save"></i></a>' + '<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "family_add' + sib + '" onclick="family_add_fn(' + sib + ');" ><i class="fa fa-plus"></i></a>' + '<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "family_remove' + sib + '" onclick="family_remove_fn(' + sib + ');"><i class="fa fa-trash"></i></a>' + '</td></tr>');
	datepicketDate('sib_date_of_birth' + sib);
	siblingCB(sib);
	otherfunction(sib);
}
jQuery(function($){ 
	 datepicketDate('from_date1');
	 datepicketDate('from_date2');
	 datepicketDate('to_date2');
	 datepicketDate('to_date1');
	 datepicketDate('date_of_raising');
	 
});
$(document).ready(function() {
	
	  var today = new Date();
	    var day = String(today.getDate()).padStart(2, '0');
	    var month = String(today.getMonth() + 1).padStart(2, '0'); // Months are zero-based
	    var year = today.getFullYear();

	 
	    var formattedDate = day + '-' + month + '-' + year;

	    $("#to_date2").val(formattedDate);
	
	 $("#unit_name").keypress(function(){
			var unit_name = this.value;
				 var susNoAuto=$("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  var enc = data[length].substring(0,16);
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
				        	  document.getElementById("unit_name").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						 var target_unit_name = ui.item.value;

					
						$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {
							target_unit_name:target_unit_name
						}).done(function(j) {
							var length = j.length-1;
				        	var enc = j[length].substring(0,16);
				        	$("#sus_no").val(dec(enc,j[0]));
						}).fail(function(xhr, textStatus, errorThrown) {
						});
					} 	     
				}); 			
			});
	
	
   // Source Sus No auto
	$("input#sus_no").keyup(function(){
		var sus_no = this.value;
		var unitNameAuto=$("#sus_no");
		unitNameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getSusNoActiveList?"+key+"="+value,
		        data: {sus_no:sus_no},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  var enc = "";
			        	if(data.length != 0){
			        		enc = data[length].substring(0,16);
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
		        	  alert("Please Enter Approved SUS NO");
		        	  $("#unit_name").val("");
		        	  unitNameAuto.val("");	        	  
		        	  unitNameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	var sus_no = ui.item.value;
		      	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
		       		var length = j.length-1;
					var enc = j[length].substring(0,16);
			   		$("#unit_name").val(dec(enc,j[0]));
			   		getOrbatDetailsFromUnitName(dec(enc,j[0]))
		       	});
		      	
		     }
		});
	});
	
});

</script>