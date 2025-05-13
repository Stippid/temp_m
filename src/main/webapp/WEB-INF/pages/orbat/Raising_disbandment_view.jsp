<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<div class="animated fadeIn">
		<div class="" align="center">
			<div class="container">
				<div class="card">
					<div class="card-header"><h5><b>View ${scenarioV} Details</b><br></h5><h6><span style="font-size:12px;color:red;">(To be entered by SD Dte / MISO)</span></h6><strong>Schedule Details</strong> </div>
					<div class="card-body">
						<div class="col-md-6" id="goi_letter_no">	
							<div class="row form-group">
								<div class="col col-md-6" align="left"  >
									<label class=" form-control-label"><b>GOI Letter No</b></label>
								</div>
								<div class="col-12 col-md-6" align="left">
									${viewShdulCMD[0].goi_letter_no}
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6" align="left">
									<label class=" form-control-label"><b>Auth Letter No</b></label>
								</div>
								<div class="col-12 col-md-6" align="left">
									${viewShdulCMD[0].letter_no} 
								</div>
							</div>
						</div>
						<div class="col-md-6" id="date_goi_letter">
							<div class="row form-group">
								<div class="col col-md-6" align="left">
									<label for="text-input" class=" form-control-label"><b>GOI Letter Date</b></label>
								</div>
								<div class="col-12 col-md-6" align="left">	
									<fmt:parseDate pattern="yyyy-MM-dd" value="${viewShdulCMD[0].date_goi_letter}" var="date_goi_letter" />
									<fmt:formatDate value="${date_goi_letter}" pattern="dd-MM-yyyy" />
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6" align="left">
									<label for="text-input" class=" form-control-label"><b>Auth Letter Date</b></label>
								</div>
								<div class="col-12 col-md-6" align="left">
									<fmt:parseDate pattern="yyyy-MM-dd" value="${viewShdulCMD[0].sanction_date}" var="sanction_date" />
									<fmt:formatDate value="${sanction_date}" pattern="dd-MM-yyyy" />
								</div>
							</div>
						</div>
						<div class="col-md-6" id="goi_doc">
							<div class="row form-group">
								<div class="col col-md-6" align="left">
									<label for="text-input" class=" form-control-label"><b>Upload GOI Letter </b></label>
								</div>
								<div class="col-12 col-md-6" align="left">
									<c:if test="${Goi_Doc == 'YES'}">
										<a hreF="#" onclick="getDownloadImage('goi')" class="btn btn-primary btn-sm">Download</a>
									</c:if>
									<c:if test="${Goi_Doc == 'NO'}">
										File Not Uploaded
									</c:if>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="auth_doc">	
							<div class="row form-group">
								<div class="col col-md-6" align="left">
									<label for="text-input" class=" form-control-label"><b>Upload Auth Letter</b> </label>
								</div>
								<div class="col-12 col-md-6" align="left">
									<c:if test="${Auth_Doc == 'YES'}">
										<a hreF="#" onclick="getDownloadImage('auth')" class="btn btn-primary btn-sm">Download</a>
									</c:if>
									<c:if test="${Auth_Doc == 'NO'}">
										File Not Uploaded
									</c:if>
								</div>
							</div>
						</div>
					</div>
					<div class="card-header"
						style="border: 1px solid rgba(0, 0, 0, .125);">
						<strong>Details Of The Unit </strong>
					</div>

					<div class="card-body">
						<div class="col-md-6" align="left">
							<div class="row form-group" id="source_unit_name" style="display: none;">
								<div class="col col-md-6">
									<b>Source Unit Name</b> 
								</div>
								<div class="col-md-6">
									<label id="source_unit_name">${source_unit_name}</label>
								</div>
							</div>
							<div class="row form-group" >
								<div class="col col-md-6" >
									<label class=" form-control-label"><b><span id="target"></span> Unit Name</b></label>
								</div>
								<div class="col-12 col-md-6" >
									${viewUnitCMD.unit_name}
								</div>
							</div>
							<div class="row form-group" id="unit_army_hq">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label"><b>Unit Under Army HQ </b></label>
								</div>
								<div class="col col-md-6">
									<div class="form-check-inline form-check">
										<label for="inline-radio1" class="form-check-label ">
										 	${viewUnitCMD.unit_army_hq}
										</label> 
									</div>
								</div>
							</div>
							
							<div class="row form-group" id="op_comd">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Op Comd </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="op_comd"></label>
								</div>
							</div>
							<div class="row form-group" id="op_div">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Op Div </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="op_div"></label>
								</div>
							</div>
							<div class="row form-group" id="cont_comd1" >
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Cont Comd </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="cont_comd"></label>
								</div>
							</div>
							<div class="row form-group" id="cont_div1" >
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Cont Div </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="cont_div"></label>
								</div>
							</div>
							<div class="row form-group" id="adm_comd" >
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Adm Comd </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="adm_comd"></label>
								</div>
							</div>
							<div class="row form-group" id="adm_div">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Adm Div </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="adm_div"></label>
								</div>
							</div>
							<div class="row form-group" id="parent_arm">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Parent Arm </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="parent_arm">${parent_arm}</label>
								</div>
							</div>
							<div class="row form-group" id="arm_name">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Arm Name </b></label>
								</div>
                				<div class="col-12 col-md-6">
                					<label id="arm_name">${arm_name}</label>
                 				</div>
           					</div>
							<div class="row form-group" id="code">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Loc </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="code">${LOC_NRS_TypeLOC_TrnType[0]}</label>
								</div>
							</div>

							<div class="row form-group" id="type_of_location">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Type of Loc </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="type_of_location">${LOC_NRS_TypeLOC_TrnType[4]}</label>
								</div>
							</div>
							<div class="row form-group" id="stateDiv" style="display: none">
			                	<div class="col col-md-6">
			                  		<label class=" form-control-label"><b>State</b></label>
			                	</div>
			                	<div class="col-12 col-md-6">
			                		${state}
			                	</div>
			              	</div>
							<div class="row form-group" id="ct_part_i_ii">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label"><b>CT Part I/II </b></label>
								</div>

								<div class="col col-md-6">
									<div class="form-check-inline form-check">
										<label for="inline-radio1" class="form-check-label ">
											${viewUnitCMD.ct_part_i_ii}
										</label>	 
									</div>
								</div>
							</div>
							<div class="row form-group" id="comm_depart_date">
			                	<div class="col col-md-6">
			                  		<label class=" form-control-label"><b><span id="datelabelFrom"></span> Date (From) </b></label>
			                	</div>
			                	<div class="col-12 col-md-6">
			                		<fmt:parseDate pattern="yyyy-MM-dd" value="${viewUnitCMD.comm_depart_date}" var="comm_depart_date" />
									<fmt:formatDate value="${comm_depart_date}" pattern="dd-MM-yyyy" />
			                	</div>
			              	</div>
			              	<div class="row form-group" >
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Address </b></label>
								</div>
								<div class="col-12 col-md-6">
									
									<label id="address" > ${address}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-md-offset-1" align="left">
							<div class="row form-group" id="source_sus_no" style="display: none;">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Source SUS No </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="source_sus_no">${source_sus_no}</label>
								</div>
							</div>
							<div class="row form-group" >
								<div class="col col-md-6">
									<label class=" form-control-label"><b><span id="target1"></span> SUS No </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="sus_no">${viewUnitCMD.sus_no}</label>
								</div>
							</div>

							<div class="row form-group" style="padding-top: 40px;" id="op_corps">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Op Corps  </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="op_corps"></label>
								</div>
							</div>
							<div class="row form-group" id="op_bde">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Op Bde </b> </label>
								</div>
								<div class="col-12 col-md-6">
									<label id="op_bde"></label>
								</div>
							</div>
							<div class="row form-group" id="cont_corps1" >
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Cont Corps </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="cont_corps"></label>
								</div>
							</div>
							<div class="row form-group" id="cont_bde1" 	>
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Cont Bde </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="cont_bde"></label>
								</div>
							</div> 
							<div class="row form-group" id="adm_corps">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Adm Corps </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="adm_corps"></label>
								</div>
							</div>
							<div class="row form-group" id="adm_bde">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Adm Bde </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="adm_bde"></label>
								</div>
							</div>
							<div class="row form-group" id="type_of_arm">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Type of Arm</b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="type_of_arm">${type_of_arm}</label>
								</div>
							</div>
							<div class="row form-group" id="arm_code">
								<div class="col col-md-6">
									<label for="selectLg" class=" form-control-label"><b>Arm Code</b></label>
								</div>
								<div class="col-12 col-md-6">
									${viewUnitCMD.arm_code}
								</div>
							</div>
							<div class="row form-group" id="nrs_name">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>NRS </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="nrs_name">${LOC_NRS_TypeLOC_TrnType[1]}</label>
								</div>
							</div>
							<div class="row form-group" id="modification">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Trn Type </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="modification">${LOC_NRS_TypeLOC_TrnType[3]}</label>
								</div>
							</div>
							<div class="row form-group" id="type_force">
								<div class="col col-md-6">
									<label for="selectLg" class=" form-control-label"><b>Type of Unit </b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="type_force">${type_force}</label>
								</div>
							</div>
							
							<div class="row form-group" id="districtDiv" style="display: none">
								<div class="col col-md-6">
									<label class="form-control-label"><b>District</b></label>
								</div>
								<div class="col-12 col-md-6">
									<label>${district}</label>
								</div>
							</div>
							<div class="row form-group" id="toDate">
                				<div class="col col-md-6">
                  					<label class=" form-control-label"><b> <span id="datelabelTo"></span> Date (To)</b></label>
                				</div>
                				<div class="col-12 col-md-6">
                					<fmt:parseDate pattern="yyyy-MM-dd" value="${viewUnitCMD.compltn_arrv_date}" var="compltn_arrv_date" />
									<fmt:formatDate value="${compltn_arrv_date}" pattern="dd-MM-yyyy" />
                				</div>
             				</div>
						</div>
					</div>
					
					<div class="card-body " style="border: 1px solid rgba(0, 0, 0, .125);"  align="center">
						<div class="col-12 col-md-12">
							<a href="SearchRaising_disbandment" class="btn btn-danger btn-sm"> Close </a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- //////////////////////////////////////////// AUTO COMPLETE //////////////////////// -->

<script>
$(document).ready(function() {
	
	if('${scenarioV}' == "Unit Profile" ){
		
		$("div#goi_letter_no").hide();
		$("div#date_goi_letter").hide();
		$("div#goi_doc").hide();
		$("div#auth_doc").hide();
		
		$("div#unit_army_hq").hide();
		$("div#op_comd").hide();
		$("div#op_corps").hide();
		$("div#op_div").hide();
		$("div#op_bde").hide();
		
		$("div#adm_comd").hide();
		$("div#adm_corps").hide();
		$("div#adm_div").hide();
		$("div#adm_bde").hide();
		
		$("div#cont_comd1").hide();
		$("div#cont_corps1").hide();
		$("div#cont_div1").hide();
		$("div#cont_bde1").hide();
		
		$("div#arm_code").hide();
		
		$("div#parent_arm").hide();
		$("div#type_of_arm").hide();
		
		$("div#comm_depart_date").hide();
		$("div#compltn_arrv_date").hide();
		$("div#toDate").hide();
		
		$("div#stateDiv").show();
		$("div#districtDiv").show();
		
		$("div#code").hide();
		$("div#nrs_name").hide();
		$("div#type_of_location").hide();
		$("div#modification").hide();
	}
	
	
	if('${scenarioV}' == "Main Body Move(MISO)"){
		$("#datelabelFrom").text("Departure");
		$("#datelabelTo").text("Arrival");
		
		$("div#goi_letter_no").hide();
		$("div#date_goi_letter").hide();
		$("div#goi_doc").hide();
		$("div#stateDiv").show();
		$("div#districtDiv").show();
	}else{
		$("#datelabelTo").text('${scenarioV}');
		$("#datelabelFrom").text('${scenarioV}');
	}
	
	
	if('${scenarioV}' == "Amendment"){
		$("div#goi_letter_no").hide();
		$("div#date_goi_letter").hide();
		$("div#goi_doc").hide();
		$("div#stateDiv").show();
		$("div#districtDiv").show();
	}else{
		$("#datelabelTo").text('${scenarioV}');
		$("#datelabelFrom").text('${scenarioV}');
	}
	if('${scenarioV}' == "Re-Orbatting"){
		$("div#goi_letter_no").hide();
		$("div#date_goi_letter").hide();
		$("div#goi_doc").hide();
		$("div#stateDiv").show();
		$("div#districtDiv").show();
	}else{
		$("#datelabelTo").text('${scenarioV}');
		$("#datelabelFrom").text('${scenarioV}');
	}
	
	
	
	
	if('${scenarioV}' == "New Raising"){
  		$("#toDate").show();
  		$("#unit_army_hq").show();
  	}
	if('${scenarioV}' == "Conversion" || '${scenarioV}' == "Re-designation" || '${scenarioV}' == "Re-Structuring" ){
		$("div#source_unit_name").show();
   		$("div#source_sus_no").show();
   		$("#target").text("Target");
   		$("#target1").text("Target");
   	}

	
	if('${op_comd}' == ""){
		$("label#op_comd").text('NA');
	}else{
		$("label#op_comd").text('${op_comd}');
	}
	if('${cont_comd}' == ""){
		$("label#cont_comd").text('NA');
	}else{
		$("label#cont_comd").text('${cont_comd}');
	}
	if('${adm_comd}' == ""){
		$("label#adm_comd").text('NA');
	}else{
		$("label#adm_comd").text('${adm_comd}');
	}
	
	if('${op_corps}' == ""){
		$("label#op_corps").text("NA");
	}else{
		$("label#op_corps").text('${op_corps}');
	}
	if('${cont_corps}' == ""){
		$("label#cont_corps").text("NA");
	}else{
		$("label#cont_corps").text('${cont_corps}');
	}
	if('${adm_corps}' == ""){
		$("label#adm_corps").text("NA");
	}else{
		$("label#adm_corps").text('${adm_corps}');
	}
	
	if('${op_div}' == ""){
		$("label#op_div").text("NA");
	}else{
		$("label#op_div").text('${op_div}');
	}
	if('${cont_div}' == ""){
		$("label#cont_div").text("NA");
	}else{
		$("label#cont_div").text('${cont_div}');
	}
	if('${adm_div}' == ""){
		$("label#adm_div").text("NA");
	}else{
		$("label#adm_div").text('${adm_div}');
	}
	
	if('${op_bde}' == ""){
		$("label#op_bde").text("NA");
	}else{
		$("label#op_bde").text('${op_bde}');
	}
	if('${cont_bde}' == ""){
		$("label#cont_bde").text("NA");
	}else{
		$("#cont_bde").text('${cont_bde}');
	}
	if('${adm_bde}' == ""){
		$("label#adm_bde").text("NA");
	}else{
		$("label#adm_bde").text('${adm_bde}');
	}

	if('${scenarioV}' == "Disbandment"){
		$("div#unit_army_hq").hide();
		$("div#op_comd").hide();
		$("div#op_corps").hide();
		$("div#op_div").hide();
		$("div#op_bde").hide();
		
		$("div#adm_comd").hide();
		$("div#adm_corps").hide();
		$("div#adm_div").hide();
		$("div#adm_bde").hide();
		
		$("div#cont_comd1").show();
		$("div#cont_corps1").show();
		$("div#cont_div1").show();
		$("div#cont_bde1").show();
		
		
		$("div#arm_name").hide();
		$("div#arm_code").hide();
		$("div#code").hide();
		$("div#nrs_name").hide();
		$("div#type_of_location").hide();
		$("div#modification").hide();
		$("div#ct_part_i_ii").hide();
		
		$("div#cont_comd").addClass("disabledbutton");
		$("div#cont_corps").addClass("disabledbutton");
		$("div#cont_div").addClass("disabledbutton");
		$("div#cont_bde").addClass("disabledbutton");
		
		$("div#type_force").addClass("disabledbutton");
	}
	if('${scenarioV}' == "Extend Raising/Disbandment"){
		$("div#goi_letter_no").hide();
		$("div#date_goi_letter").hide();
		$("div#goi_doc").hide();
		$("div#unit_army_hq").hide();
		$("div#op_comd").hide();
		$("div#op_corps").hide();
		$("div#op_div").hide();
		$("div#op_bde").hide();
		
		$("div#adm_comd").hide();
		$("div#adm_corps").hide();
		$("div#adm_div").hide();
		$("div#adm_bde").hide();
		
		$("div#cont_comd1").show();
		$("div#cont_corps1").show();
		$("div#cont_div1").show();
		$("div#cont_bde1").show();
		
		
		$("div#arm_name").hide();
		$("div#arm_code").hide();
		$("div#code").hide();
		$("div#nrs_name").hide();
		$("div#type_of_location").hide();
		$("div#modification").hide();
		$("div#ct_part_i_ii").hide();
		
		$("div#cont_comd").addClass("disabledbutton");
		$("div#cont_corps").addClass("disabledbutton");
		$("div#cont_div").addClass("disabledbutton");
		$("div#cont_bde").addClass("disabledbutton");
		
		$("div#type_force").addClass("disabledbutton");
	}
});
     
function getDownloadImage(letter)
{  
	document.getElementById("letter").value=letter;
	document.getElementById("scenarioV").value='${scenarioV}';
	document.getElementById("getDownloadImageForm").submit();
}  
/* function getDownloadImage(letter)
{  
	$.ajax({
		url : "getDownloadImageOrbat?"+key+"="+value,
		type : 'POST',
		data : {vid:'${viewUnitCMD.id}',letter:letter,scenarioV:'${scenarioV}'},
		success : function(data) {
			alert(data);
		},
		async : false,
	});
}   */
</script>
<c:url value="getDownloadImageOrbat" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id">
	<input type="hidden" name="vid" id="vid" value="${viewUnitCMD.id}"/>
	<input type="hidden" name="letter" id="letter" value="0"/>
	<input type="hidden" name="scenarioV" id="scenarioV" value="0"/>
</form:form>