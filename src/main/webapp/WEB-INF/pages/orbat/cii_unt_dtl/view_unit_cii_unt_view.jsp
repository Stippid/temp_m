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
										<label class=" form-control-label"><b>Level1</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="level_1"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group" > 
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Level2</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="level_2"></label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Level3</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="level_3"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Level4</b> </label>
									</div>
									<div class="col-12 col-md-6">
										<label id="level_4"></label>
									</div>
								</div>
							</div>
						</div>
						
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Level5</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="level_5"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Level6</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="level_6"></label>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Level7</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="level_7"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Level8</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="level_8"></label>
									</div>
								</div>
							</div>
						</div>
						
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Level9</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="level_9"></label>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Level10</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="level_10"></label>
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
				                  		<label class=" form-control-label"><b> Date (From)</b></label>
				                	</div>
				                	<div class="col-12 col-md-6">
				                		<fmt:parseDate pattern="yyyy-MM-dd" value="${viewUnitCMD.from_date}" var="comm_depart_date" />
										 <label id=from_date><fmt:formatDate value="${comm_depart_date}" pattern="dd-MM-yyyy" /> </label> 
				                	</div>
				              	</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group" id="movDate">
	                				<div class="col col-md-6">
	                  					<label class=" form-control-label"><b> Date (To)</b></label>
	                				</div>
	                				<div class="col-12 col-md-6">
	                					<fmt:parseDate pattern="yyyy-MM-dd" value="${viewUnitCMD.to_date}" var="compltn_arrv_date" />
										<label id="to_Date"><fmt:formatDate value="${compltn_arrv_date}" pattern="dd-MM-yyyy" /></label>
										
									</div>
	             				</div>
							</div>
						</div>
						<div class="col-md-12">
							
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-6">
										<label class=" form-control-label"><b>Remarks</b></label>
									</div>
									<div class="col-12 col-md-6">
										<label id="address" > ${viewUnitCMD.remarks}</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="card-body " style="border: 1px solid rgba(0, 0, 0, .125);">
						<div class="col-12 col-md-12">
							<a href="view_unit_profile_cii" class="btn btn-danger btn-sm"> Close </a>
						</div>
					</div>
				</div>
			
		</div>
	</div>

<!-- //////////////////////////////////////////// AUTO COMPLETE //////////////////////// -->

<script>
$(document).ready(function() {
	
	
	
 	if('${viewUnitCMD.from_date}'.trim() == ""){
  		$("#from_date").text('NA'); 
 	}
 	
 	if('${viewUnitCMD.to_date}'.trim() == ""){
  		$("#to_Date").text('NA'); 
 	}
    	   
	if('${level1}'.trim() == ""){
		$("#level_1").text('NA');
	}else{
		$("#level_1").text('${level1}');
	}
	if('${level2}'.trim() == ""){
		$("#level_2").text('NA');
	}else{
		$("#level_2").text('${level2}');
	}
	
	if('${level3}'.trim() == ""){
		$("#level_3").text('NA');
	}else{
		$("#level_3").text('${level3}');
	}
	
	if('${level4}'.trim() == ""){
		$("#level_4").text("NA");
	}else{
		$("#level_4").text('${level4}');
	}
	
	if('${level5}'.trim() == ""){
		$("#level_5").text("NA");
	}else{
		$("#level_5").text('${level5}');
	}
	if('${level6}'.trim() == ""){
		$("#level_6").text("NA");
	}else{
		$("#level_6").text('${level6}');
	}
	
	if('${level7}'.trim() == ""){
		$("#level_7").text("NA");
	}else{
		$("#level_7").text('${level7}');
	}
	
	if('${level8}'.trim() == ""){
		$("#level_8").text("NA");
	}else{
		$("#level_8").text('${level8}');
	}
	if('${level9}'.trim() == ""){
		$("#level_9").text("NA");
	}else{
		$("#level_9").text('${level9}');
	}
	
	if('${level10}'.trim() == ""){
		$("#level_10").text("NA");
	}else{
		$("#level_10").text('${level10}');
	}

	if('${viewUnitCMD.remarks}'.trim() == ""){
    	$("#remarks").text("NA");
	}
	
	
});
     
/* function getDownloadImage(letter)
{  
	document.getElementById("letter").value=letter;
	document.getElementById("getDownloadImageForm").submit();
} */
</script>
<%-- <c:url value="getDownloadImageOrbatViewUnit" var="imageDownloadUrl" />
 <form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id">
	<input type="hidden" name="id" id="id" value="${viewUnitCMD.id}"/>
	<input type="hidden" name="letter" id="letter" value="0"/>
</form:form> --%> 