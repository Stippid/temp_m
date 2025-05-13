<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">


	<div class="animated fadeIn">
		<div class="container" align="center">
			
				<div class="card">
					<div class="card-header"><h5><b>View Unit Details</b></h5><strong>Schedule Details</strong></div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6" align="left">
										<label class=" form-control-label"><b>Auth Letter No </b></label>
									</div>
									<div class="col-12 col-md-6" align="left">
										${viewShdulCMD[0].letter_no} 
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6" align="left">
										<label class=" form-control-label"><b>GOI Letter No</b></label>
									</div>
									<div class="col-12 col-md-6" align="left">
										${viewShdulCMD[0].goi_letter_no}
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
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
							<div class="col-md-6">
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
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6" align="left">
										<label for="text-input" class=" form-control-label"><b>Upload Auth Letter</b></label>
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
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6" align="left">
										<label for="text-input" class=" form-control-label"><b>Upload GOI Letter</b></label>
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
						</div>
					</div>
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"> <strong>Details Of The ${statusSusDetails} Unit </strong> </div>
					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6" >
										<label class=" form-control-label"><b>Unit Name</b></label>
									</div>
									<div class="col-12 col-md-6" >
										${viewUnitCMD.unit_name}
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group" >
								<div class="col col-md-6">
									<label class=" form-control-label"><b>SUS No</b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="sus_no">${viewUnitCMD.sus_no}</label>
								</div>
							</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<%-- <div class="row form-group" >
									<div class="col col-md-6">
										<label for="text-input" class=" form-control-label"><b>Unit Under Army HQ :</b></label>
									</div>
									<div class="col col-md-6">
										<div class="form-check-inline form-check">
											<label for="inline-radio1" class="form-check-label ">
											 	${viewUnitCMD.unit_army_hq}
											</label> 
										</div>
									</div>
								</div> --%>
							</div>
							<div class="col-md-6">
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Op Comd</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="op_comd"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group" > 
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Op Corps</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="op_corps"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Op Div</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="op_div"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Op Bde</b> </label>
									</div>
									<div class="col-12 col-md-6">
										<label id="op_bde"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<!-- <div class="row form-group" >
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Cont Comd</b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="cont_comd"></label>
								</div>
							</div>-->
							</div>
							<div class="col-md-6">
								<!-- <div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Cont Corps</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="cont_corps"></label>
									</div>
								</div> -->
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<!--<div class="row form-group" >
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Cont Div</b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="cont_div"></label>
								</div>
							</div> -->
							</div>
							<div class="col-md-6">
								<!-- 
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Cont Bde</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="cont_bde"></label>
									</div>
								</div> -->
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Adm Comd</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="adm_comd"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Adm Corps</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="adm_corps"></label>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Adm Div</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="adm_div"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Adm Bde</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="adm_bde"></label>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Parent Arm</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="parent_arm">${parent_arm}</label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Type Of Arm</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="type_of_arm">${type_of_arm}</label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Loc</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="code">${LOC_NRS_TypeLOC_TrnType[0]}</label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><b>NRS</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="nrs_name">${LOC_NRS_TypeLOC_TrnType[1]}</label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Type of Loc</b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="type_of_location">${LOC_NRS_TypeLOC_TrnType[4]}</label>
								</div>
							</div> 
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label"><b>Trn Type</b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="modification">${LOC_NRS_TypeLOC_TrnType[3]}</label>
								</div>
							</div> 
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label for="text-input" class=" form-control-label"><b>CT Part I/II</b></label>
									</div>
	
									<div class="col col-md-6">
										<div class="form-check-inline form-check">
											<label for="inline-radio1" class="form-check-label ">
												${viewUnitCMD.ct_part_i_ii}
											</label>	 
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
								<div class="col col-md-6">
									<label for="selectLg" class=" form-control-label"><b>Type of Unit</b></label>
								</div>
								<div class="col-12 col-md-6">
									<label id="type_force">${type_force}</label>
								</div>
							</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Arm Name</b></label>
									</div>
	                				<div class="col-12 col-md-6">
	                					<label id="arm_name">${arm_name}</label>
	                 				</div>
	           					</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label for="selectLg" class=" form-control-label"><b>Arm Code</b></label>
									</div>
									<div class="col-12 col-md-6">
										${viewUnitCMD.arm_code}
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
				                	<div class="col col-md-6">
				                  		<label class=" form-control-label"><b>${screenName} Date (From)</b></label>
				                	</div>
				                	<div class="col-12 col-md-6">
				                		<fmt:parseDate pattern="yyyy-MM-dd" value="${viewUnitCMD.comm_depart_date}" var="comm_depart_date" />
										<fmt:formatDate value="${comm_depart_date}" pattern="dd-MM-yyyy" />
				                	</div>
				              	</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group" id="movDate">
	                				<div class="col col-md-6">
	                  					<label class=" form-control-label"><b>${screenName} Date (To)</b></label>
	                				</div>
	                				<div class="col-12 col-md-6">
	                					<fmt:parseDate pattern="yyyy-MM-dd" value="${viewUnitCMD.compltn_arrv_date}" var="compltn_arrv_date" />
										<label id="toDate"><fmt:formatDate value="${compltn_arrv_date}" pattern="dd-MM-yyyy" /></label>
										
									</div>
	             				</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
				                	<div class="col col-md-6">
				                  		<label class=" form-control-label"><b>Last Updated Date</b></label>
				                	</div>
				                	<div class="col-12 col-md-6">
				                		<fmt:parseDate pattern="yyyy-MM-dd" value="${viewShdulCMD[0].approved_rejected_on}" var="approved_rejected_on" />
										<fmt:formatDate value="${approved_rejected_on}" pattern="dd-MM-yyyy" />
				                	</div>
				              	</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Address</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="address" > ${viewUnitCMD.address}</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card-body " style="border: 1px solid rgba(0, 0, 0, .125);">
						<div class="col-12 col-md-12">
							<a href="view_unit_profile" class="btn btn-danger btn-sm"> Close </a>
						</div>
					</div>
				</div>
			
		</div>
	</div>

<!-- //////////////////////////////////////////// AUTO COMPLETE //////////////////////// -->

<script>
$(document).ready(function() {
	
	if('${screenName}' == "Disbandment"){
		$("div#movDate").hide();
	}
	
 	if('${viewUnitCMD.compltn_arrv_date}' == ""){
  		$("#toDate").text('NA'); 
 	}
    	   
	if('${op_comd}' == ""){
		$("#op_comd").text('NA');
	}else{
		$("#op_comd").text('${op_comd}');
	}
	
	if('${cont_comd}' == ""){
		$("#cont_comd").text('NA');
	}else{
		$("#cont_comd").text('${cont_comd}');
	}
	
	if('${adm_comd}' == ""){
		$("#adm_comd").text('NA');
	}else{
		$("#adm_comd").text('${adm_comd}');
	}
	
	if('${op_corps}' == ""){
		$("#op_corps").text("NA");
	}else{
		$("#op_corps").text('${op_corps}');
	}
	if('${cont_corps}' == ""){
		$("#cont_corps").text("NA");
	}else{
		$("#cont_corps").text('${cont_corps}');
	}
	if('${adm_corps}' == ""){
		$("#adm_corps").text("NA");
	}else{
		$("#adm_corps").text('${adm_corps}');
	}
	
	if('${op_div}' == ""){
		$("#op_div").text("NA");
	}else{
		$("#op_div").text('${op_div}');
	}
	if('${cont_div}' == ""){
		$("#cont_div").text("NA");
	}else{
		$("#cont_div").text('${cont_div}');
	}
	if('${adm_div}' == ""){
		$("#adm_div").text("NA");
	}else{
		$("#adm_div").text('${adm_div}');
	}
	
	if('${op_bde}' == ""){
		$("#op_bde").text("NA");
	}else{
		$("#op_bde").text('${op_bde}');
	}
	if('${cont_bde}' == ""){
		$("#cont_bde").text("NA");
	}else{
		$("#cont_bde").text('${cont_bde}');
	}
	if('${adm_bde}' == ""){
		$("#adm_bde").text("NA");
	}else{
		$("#adm_bde").text('${adm_bde}');
	}
	
	if('${viewUnitCMD.address}' == ""){
    	$("#address").text("NA");
	}
});
     
function getDownloadImage(letter)
{  
	document.getElementById("letter").value=letter;
	document.getElementById("getDownloadImageForm").submit();
}
</script>
<c:url value="getDownloadImageOrbatViewUnit" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id">
	<input type="hidden" name="id" id="id" value="${viewUnitCMD.id}"/>
	<input type="hidden" name="letter" id="letter" value="0"/>
</form:form> 