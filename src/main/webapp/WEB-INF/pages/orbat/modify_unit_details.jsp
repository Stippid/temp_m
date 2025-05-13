<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/ChkDateLessThan.js" type="text/javascript"></script>

<style>
.disabledbutton {
    pointer-events: none;
    opacity: 0.6;
}
</style>

<form:form name="updateUnitDetailsForm" id="updateUnitDetailsForm" action="updateUnitDetailsAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="updateUnitDetailsCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">	
				<div class="card">
					<div class="card-header"><h5><b>MODIFY ${scenarioM} DETAILS</b><br></h5><h6><span style="font-size:12px;color:red;">(To be entered by SD Dte / MISO)</span></h6><strong>Schedule Details</strong> </div>
					<div style="display: none;" class="col-md-12">
						<input type="text" id="unit_id" name="unit_id" value="${viewUnitCMD.id}">
						<input type="hidden" id="shdul_id" name="shdul_id" value="${viewShdulCMD[0].id}">
						<input type="hidden" id="type_of_letter" name="type_of_letter" value="${viewShdulCMD[0].type_of_letter}">
						<input type="hidden" id="upload_auth_letter" name="upload_auth_letter" value="${viewShdulCMD[0].upload_auth_letter}">
						<input type="hidden" id="upload_goi_letter" name="upload_goi_letter" value="${viewShdulCMD[0].upload_goi_letter}" >
						<input type="hidden" id="created_by" name="created_by" value="${viewShdulCMD[0].created_by}" >
						<input type="hidden" id="created_on" name="created_on" value="${viewShdulCMD[0].created_on}" >
						<input type="hidden" id="sus_version" name="sus_version" value="${viewUnitCMD.sus_version}"> 
						<input type="hidden" id="creation_on" name="creation_on" value="${viewUnitCMD.creation_on}"> 
						<input type="hidden" id="creation_by" name="creation_by" value="${viewUnitCMD.creation_by}"> 
						<input type="hidden" id="sus_no_for_comb_disint" name="sus_no_for_comb_disint" value="${viewUnitCMD.sus_no_for_comb_disint}">
						<input type="hidden" id="sus_no" name="sus_no" value="${viewUnitCMD.sus_no}" maxlength="8" >
						
						<input type="hidden" id="level_in_hierarchy" name="level_in_hierarchy" value="${getFormation}">
						<input type="hidden" id="op" name="op" value="${viewUnitCMD.form_code_operation}">
						<input type="hidden" id="cont" name="cont" value="${viewUnitCMD.form_code_control}">
						<input type="hidden" id="adm" name="adm" value="${viewUnitCMD.form_code_admin}">
						
						
						<input type="hidden" id="scenarioMScreen" name="scenarioMScreen" value="${scenarioM}">
						
						
					</div>
					<div class="card-body">
						
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Auth Letter No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="letter_no" name="letter_no" maxlength="250" class="form-control" value="${viewShdulCMD[0].letter_no}">
									</div>
								</div>
							</div>
							<div class="col-md-6" id="goi_letter_no">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> GOI Letter No</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="goi_letter_no" name="goi_letter_no" maxlength="250" placeholder="GOI Letter No" class="form-control" value="${viewShdulCMD[0].goi_letter_no}">
									</div>
								</div>
							</div>
						
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Auth Letter Date</label>
									</div>
									<div class="col-12 col-md-8">
										<fmt:parseDate pattern="yyyy-MM-dd" value="${viewShdulCMD[0].sanction_date}" var="sanction_date"  />
										<input type="date" id="sanction_date" name="sanction_date" class="form-control"  value="<fmt:formatDate value="${sanction_date}" pattern="yyyy-MM-dd" />" max="${date}" >
									</div>
								</div>
							</div>
							<div class="col-md-6" id="date_goi_letter">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> GOI Letter Date</label>
									</div>
									<div class="col-12 col-md-8">
										<fmt:parseDate pattern="yyyy-MM-dd" value="${viewShdulCMD[0].date_goi_letter}" var="date_goi_letter" />
										<input type="date" id="date_goi_letter" name="date_goi_letter" class="form-control"  value="<fmt:formatDate value="${date_goi_letter}" pattern="yyyy-MM-dd" />"  max="${date}">
										<%-- <input type="date" id="date_goi_letter" name="date_goi_letter" class="form-control" value="${viewShdulCMD[0].date_goi_letter}"> --%>
									</div>
								</div>
							</div>
						
							<div class="col-md-6">
								<div class="row form-group" id="auth_doc">
									<div class="col col-md-5">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Upload Auth Letter</label>
									</div>
									<div class="col-12 col-md-7">
										<input type="file" id="upload_auth_letter" name="upload_auth_letter" maxlength="250" class="form-control"> <br>
										
										<c:if test="${Auth_Doc == 'YES'}">
											<a hreF="#" onclick="getDownloadImage('auth','${viewUnitCMD.id}')" class="btn btn-primary btn-sm">Download Auth Letter</a>
										</c:if>
										<c:if test="${Auth_Doc == 'NO'}">
											File Not Uploaded
										</c:if>
									</div>
								</div>
							</div>
							<div class="col-md-6" id="goi_doc">
								<div class="row form-group">
									<div class="col col-md-5">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Upload GOI Letter</label>
									</div>
									<div class="col-12 col-md-7">
										<input type="file" id="upload_goi_letter" name="upload_goi_letter" maxlength="250" class="form-control" > <br>
										<c:if test="${Goi_Doc == 'YES'}">
											<a hreF="#" onclick="getDownloadImage('goi','${viewUnitCMD.id}')" class="btn btn-primary btn-sm">Download GOI Letter</a>
										</c:if>
										<c:if test="${Goi_Doc == 'NO'}">
											File Not Uploaded
										</c:if>
									</div>
								</div>
							</div>
						
					</div>
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"> <strong>Details Of The Unit </strong> </div>
					<div class="card-body card-block">
						<div class="col-md-6">
							<div class="row form-group" id="source_unit_name" style="display: none;">
								<div class="col col-md-4">Source Unit Name </div>
								<div class="col-md-8"> <label id="source_unit_name" class="form-control" style="background-color: #e9ecef;">${source_unit_name}</label> </div>
							</div>
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><span id="target"></span><strong style="color: red;">*</strong> Unit Name</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="unit_name" name="unit_name" maxlength="100" class="form-control autocomplete" autocomplete="off" value="${viewUnitCMD.unit_name}" >
								</div>
							</div>
							<div class="row form-group" id="unit_army_hq">
								<div class="col col-md-5">
									<label for="text-input" class=" form-control-label">Unit Under Army HQ</label>
								</div>
								<div class="col col-md-6">
									<div class="form-check-inline form-check">
										<label for="inline-radio1" class="form-check-label ">
											<input type="radio" id="inline-radio1" name="unit_army_hq" value="Y" class="form-check-input">Yes
										</label>&nbsp;&nbsp; 
										
										<label for="inline-radio2" class="form-check-label "> 
											<input type="radio" id="inline-radio2" name="unit_army_hq" value="N" class="form-check-input">No
										</label>
									</div>
								</div>
							</div>
							
							<div class="row form-group" id="op_comd">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Op Comd</label>
								</div>
								<div class="col-12 col-md-8">
									<select  id="op_comd"	name="op_comd" class="form-control-sm form-control" tabindex="1">
										<option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
                 							<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                 						</c:forEach>
									</select>
								</div>
							</div>
							<div class="row form-group" id="op_div">
								<div class="col col-md-4">
									<label class=" form-control-label">Op Div</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="op_div"	name="op_div" class="form-control-sm form-control" tabindex="1"	>
										<option value="0">--Select--</option>
										
									</select>
								</div>
							</div>
							<div class="row form-group" id="cont_comd">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Cont Comd</label>
								</div>
								<div class="col-12 col-md-8">
									<select  id="cont_comd"	name="cont_comd" class="form-control-sm form-control" tabindex="1"	>
										<option value="">--Select--</option>
		                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
	                 							<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                 						</c:forEach>
									</select>
								</div>
							</div>

							<div class="row form-group" id="cont_div">
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Div</label>
								</div>
								<div class="col-12 col-md-8">
									<select  id="cont_div"	name="cont_div" class="form-control-sm form-control" tabindex="1"	>
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
							<div class="row form-group" id="adm_comd">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Adm Comd</label>
								</div>
								<div class="col-12 col-md-8">
									<select  id="adm_comd"	name="adm_comd" class="form-control-sm form-control" tabindex="1" >
										<option value="">--Select--</option>
		                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
	                 							<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                 						</c:forEach>
									</select>
								</div>
							</div>
							<div class="row form-group" id="adm_div">
								<div class="col col-md-4">
									<label class=" form-control-label">Adm Div</label>
								</div>
								<div class="col-12 col-md-8">
									<select  id="adm_div" name="adm_div" class="form-control-sm form-control" tabindex="1"	>
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
							<div class="row form-group" id="parent_arm">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Parent Arm </label>
								</div>
								<div class="col-12 col-md-8">
									<label class="form-control" style="background-color: #e9ecef;">${parent_arm}</label>
								</div>
							</div>
							<div class="row form-group" id="arm_name">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Arm Name</label>
								</div>
                				<div class="col-12 col-md-8">
                 					<select  id="arm_name" name="arm_name"  class="form-control-sm form-control" tabindex="1" >
						                <option value="0">--Select--</option>
               							<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
               								<option value="${item.arm_code}" >${item.arm_code} - ${item.arm_desc}</option>
               							</c:forEach>
						            </select>
                				</div>
           					</div>
							<div class="row form-group" id="loc">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Loc</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="hidden" id="code" name="code" maxlength="5" class="form-control" value="${viewUnitCMD.code}" style="width:100%;"  >
				                 	<input type="text" id="loc" name="loc" maxlength="400" class="form-control" style="width:86%;display: inline-block;" value="${LOC_NRS_TypeLOC_TrnType[0]}" readonly="readonly">
		              				<i class="fa fa-search" onclick="openLocLOV();"></i>
								</div>
							</div>
							<div class="row form-group" id="type_of_location">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Type Of Loc </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="type_of_location" name="type_of_location" maxlength="50" value="${LOC_NRS_TypeLOC_TrnType[4]}" class="form-control" autocomplete="off" readonly="readonly">
								</div>
							</div>
							<div class="row form-group" id="stateDiv" style="display: none;">
			                	<div class="col col-md-4">
			                  		<label class=" form-control-label"> State</label>
			                	</div>
			                	<div class="col-12 col-md-8">
			                 		<select name="state" id="state" class="form-control-sm form-control" onchange="DistrictList();"></select>
		            			</div>
		          			</div>
							<div class="row form-group" id="ct_part_i_ii">
								<div class="col col-md-3">
									<label for="text-input" class=" form-control-label">CT Part I/II</label>
								</div>

								<div class="col col-md-9" style="height: 40px;">
									<div class="form-check-inline form-check">
										<label for="inline-radio1" class="form-check-label ">
											<input type="radio" id="radio1" name="ct_part_i_ii" maxlength="8" value="CTPartI" class="form-check-input">CT Part I</label>&nbsp;&nbsp;&nbsp; 
										<label for="inline-radio2" class="form-check-label "> 
											<input type="radio" id="radio2" name="ct_part_i_ii" maxlength="8" value="CTPartII" class="form-check-input">CT Part II</label> &nbsp;&nbsp;&nbsp; 
										<label for="inline-radio3" class="form-check-label "> 
											<input type="radio" id="radio3" name="ct_part_i_ii" maxlength="8" value="Others" class="form-check-input">Others
										</label>
									</div>
								</div>
							</div>
							<div class="row form-group" id="comm_depart_date">
			                	<div class="col col-md-4">
			                  		<label class=" form-control-label"><strong style="color: red;">*</strong> <span id="datelabelFrom"></span> Date (From)</label>
			                	</div>
			                	<div class="col-12 col-md-8">
			                		<fmt:parseDate pattern="yyyy-MM-dd" value="${viewUnitCMD.comm_depart_date}" var="comm_depart_date" />
									<input type="date" id="comm_depart_date" name="comm_depart_date"  class="form-control"  value="<fmt:formatDate value="${comm_depart_date}" pattern="yyyy-MM-dd" />" >
			                  	</div>
			              	</div>
							<div class="row form-group" id="address">
			                	<div class="col col-md-4">
			                  		<label class=" form-control-label">Address</label>
			                	</div>
			                	<div class="col-12 col-md-8">
			                 		<textarea class="form-control" id="address" name="address" maxlength="200">${viewUnitCMD.address}</textarea>
			                	</div>
			            	</div>
						</div>
						<div class="col-md-6 col-md-offset-1">
							<div class="row form-group" id="source_sus_no" style="display: none;">
								<div class="col col-md-4">
									<label class=" form-control-label">Source SUS No </label>
								</div>
								<div class="col-12 col-md-8">
									<label id="source_sus_no" class="form-control" style="background-color: #e9ecef;">${source_sus_no}</label>
								</div>
							</div>
							<div class="row form-group" >
								<div class="col col-md-4">
									<label class=" form-control-label"><span id="target1"></span><strong style="color: red;">*</strong>SUS No</label>
								</div>
								<div class="col-12 col-md-8">
									<label class="form-control" style="background-color: #e9ecef;">${viewUnitCMD.sus_no}</label>
								</div>
							</div>

							<div class="row form-group" style="padding-top: 45px;" id="op_corps">
								<div class="col col-md-4">
									<label class=" form-control-label">Op Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select  id="op_corps" name="op_corps" class="form-control-sm form-control" tabindex="1" >
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
							<div class="row form-group" id="op_bde">
								<div class="col col-md-4">
									<label class=" form-control-label">Op Bde</label>
								</div>
								<div class="col-12 col-md-8">
									<select  id="op_bde" name="op_bde" class="form-control-sm form-control" tabindex="1" >
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
							<div class="row form-group"  id="cont_corps">
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select  id="cont_corps" name="cont_corps" class="form-control-sm form-control" tabindex="1" >
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
							<div class="row form-group" id="cont_bde">
								<div class="col col-md-4">
									<label class=" form-control-label">Cont Bde</label>
								</div>
								<div class="col-12 col-md-8">
									<select  id="cont_bde" name="cont_bde" class="form-control-sm form-control" tabindex="1" >
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
							<div class="row form-group" id="adm_corps">
								<div class="col col-md-4">
									<label class=" form-control-label">Adm Corps</label>
								</div>
								<div class="col-12 col-md-8">
									<select  id="adm_corps" name="adm_corps" class="form-control-sm form-control" tabindex="1" >
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
							<div class="row form-group" id="adm_bde">
								<div class="col col-md-4">
									<label class=" form-control-label">Adm Bde</label>
								</div>
								<div class="col-12 col-md-8">
									<select  id="adm_bde"	name="adm_bde" class="form-control-sm form-control" tabindex="1" >
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
							<div class="row form-group" id="type_of_arm">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Type Of Arm </label>
								</div>
								<div class="col-12 col-md-8">
									<label class="form-control" style="background-color: #e9ecef;">${type_of_arm}</label>
								</div>
							</div>
							<div class="row form-group" id="arm_code">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Arm Code</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="arm_code" name="arm_code" maxlength="4" class="form-control" value="${viewUnitCMD.arm_code}" readonly="readonly">
								</div>
							</div>
							<div class="row form-group" id="nrs_name">
								<div class="col col-md-4">
									<label class=" form-control-label">NRS</label>
								</div>
								<div class="col-12 col-md-8">
									<input type="hidden" id="nrs_code" name="nrs_code" maxlength="5" class="form-control" value="${viewUnitCMD.nrs_code}">
				                  	<input type="text" id="nrs_name" name="nrs_name" maxlength="400" class="form-control" value="${LOC_NRS_TypeLOC_TrnType[1]}" readonly="readonly">
								</div>
							</div>
							<div class="row form-group" id="modification">
								<div class="col col-md-4">
									<label class=" form-control-label">Trn Type</label>
								</div>
								<div class="col-12 col-md-8">
									<!-- <input type="text" id="modification" name="modification" class="form-control"> -->
									<input type="text" id="modification" name="modification" maxlength="100" class="form-control" value="${LOC_NRS_TypeLOC_TrnType[3]}" readonly="readonly">
								</div>
							</div>
							<div class="row form-group" id="type_force">
								<div class="col col-md-4">
									<label for="selectLg" class=" form-control-label">Type Of Unit</label>
								</div>
								<div class="col-12 col-md-8">
									<select name="type_force" id="type_force" maxlength="10" class="form-control-sm form-control">
										<option value="">--Select--</option>
                           				<c:forEach var="item" items="${getTypeOfUnitList}">
            								<option value="${item[0]}" >${item[0]} - ${item[1]}</option>
            							</c:forEach>
									</select>
								</div>
							</div>
							
							<div class="row form-group" style="display: none" id="districtDiv">
		 						<div class="col col-md-4">
		   							<label class=" form-control-label"> District</label>
		 						</div>
		 						<div class="col-12 col-md-8">
		  							<select name="district" id="district" class="form-control-sm form-control">
		  								<option value=""> --Select--</option>
		  							</select>
		 						</div>
							</div>
							
							<div class="row form-group" id="compltn_arrv_date">
                				<div class="col col-md-4">
                  					<label class=" form-control-label"><span id="datelabelTo"></span> Date (To)</label>
                				</div>
                				<div class="col-12 col-md-8">
                					<fmt:parseDate pattern="yyyy-MM-dd" value="${viewUnitCMD.compltn_arrv_date}" var="compltn_arrv_date" />
									<input type="date" id="compltn_arrv_date" name="compltn_arrv_date"  class="form-control" onchange="return checkdate(this,comm_depart_date)" value="<fmt:formatDate value="${compltn_arrv_date}" pattern="yyyy-MM-dd" />" >
                  				</div>
             				</div>
							
						</div>
						<div class="col-md-5 col-md-offset-1">							
							<div class="row form-group" style="display: none;">
                				<div class="col col-md-12">
                					<input type="text" id="cont_aname" name="cont_aname"  maxlength="100" class="form-control" value="${viewShdulCMD[0].level_a}">
                					<input type="text" id="cont_bname" name="cont_bname"  maxlength="100" class="form-control" value="${viewShdulCMD[0].level_b}">
                					<input type="text" id="cont_cname" name="cont_cname"  maxlength="100" class="form-control" value="${viewUnitCMD.level_c}">
                					<input type="text" id="cont_dname" name="cont_dname"  maxlength="100" class="form-control"  value="${viewUnitCMD.level_d}">
                				</div>
                			</div>
						</div>
					</div>
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();"> 
						<a href="SearchRaising_disbandment" type="reset" class="btn btn-danger btn-sm"> Cancel </a>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>
function validate(){
	if('${scenarioM}' != "Amendment" & '${scenarioM}' != "Main Body Move(MISO)" & '${scenarioM}' != "Extend Raising/Disbandment" & '${scenarioM}' != "Unit Profile" & '${scenarioM}' != "Re-Orbatting"){
		if($("input#goi_letter_no").val() == ""){
			alert("Please Enter the GOI Letter No.");
			$("#goi_letter_no").focus();
			return false;
		}
		if($("input#date_goi_letter").val() == ""){
			alert("Please Select Date of GOI Letter");
			$("#date_goi_letter").focus();
			return false;
	    }
	}
	
	if($("input#letter_no").val() == ""){
		alert("Please Enter the Auth Letter No.");
		$("#letter_no").focus();
		return false;
	}
	
	if($("input#sanction_date").val() == ""){
		alert("Please Select Date of Letter");
		$("#sanction_date").focus();
		return false;
    }
	if($("input#unit_name").val() == ""){
		alert("Please Enter Name Of Unit");
		$("#unit_name").focus();
		return false;
    }
	var st;
	var unit_name = $("input#unit_name").val();
	var sus_no  = '${viewUnitCMD.sus_no}';
	if(unit_name != "" && sus_no != ""){
		$.ajax({
			url : "checkifExistUnitName?"+key+"="+value,
			type : 'POST',
			data : {unit_name:unit_name,sus_no:sus_no},
			success : function(data) {
				if(data == true){
					st = true;
				}
			},
			async : false,
		});
		if(st == true){
			alert("Unit Name Already Exists");
			return false;
		}
	}
	if($("select#op_comd").val() == ""){
		alert("Please Select Operation Command");
		$("#op_comd").focus();
		return false;
    }
	if($("select#cont_comd").val() == ""){
		alert("Please Select Control Command");
		$("#cont_comd").focus();
		return false;
    }
	if($("select#adm_comd").val() == ""){
		alert("Please Select Admin Command");
		$("#adm_comd").focus();
		return false;
    }
	if($("select#arm_name").val() == "0"){
		alert("Please Select Arm Name");
		$("#arm_name").focus();
		return false;
    }
	if($("input#code").val() == ""){
		alert("Please Select Loc");
		$("#code").focus();
		return false;
    }
	if($("input#type_of_location").val() == ""){
		alert("Please Enter Type of Loc");
		$("#type_of_location").focus();
		return false;
    }
	if($("input#comm_depart_date").val() == ""){
		alert("Please Select Commencement Date (DD-MM-YYYY)");
		$("#comm_depart_date").focus();
		return false;
    }
	var from = $("input#comm_depart_date").val();
	var to =  $("input#compltn_arrv_date").val();
	if (Date.parse(from) > Date.parse(to)) {
		alert('You cannot select To dete less than From Date.');
		document.getElementById("compltn_arrv_date").focus();
		return false;
	}
	return true;
}
</script>

<script>
	$(document).ready(function() {
		
		$("#upload_auth_letter").change(function(){	
			checkFileExt(this);
		});
		$("#upload_goi_letter").change(function(){	
			checkFileExt(this);
		});
		
		
		if('${getFormation}' == "Command" || '${getFormation}' == "Corps" || '${getFormation}' == "Division" || '${getFormation}' == "Brigade"){
       		$("select#op_comd").attr('disabled',true).css("background-color", "grey");
       		$("select#op_corps").attr('disabled',true).css("background-color", "grey");
			$("select#op_div").attr('disabled',true).css("background-color", "grey");
			$("select#op_bde").attr('disabled',true).css("background-color", "grey");
			
			$("select#cont_comd").attr('disabled',true).css("background-color", "grey");
			$("select#cont_corps").attr('disabled',true).css("background-color", "grey");
			$("select#cont_div").attr('disabled',true).css("background-color", "grey");
			$("select#cont_bde").attr('disabled',true).css("background-color", "grey");
			
			$("select#adm_comd").attr('disabled',true).css("background-color", "grey");
			$("select#adm_corps").attr('disabled',true).css("background-color", "grey");
			$("select#adm_div").attr('disabled',true).css("background-color", "grey");
			$("select#adm_bde").attr('disabled',true).css("background-color", "grey");
		}
		if('${getFormation}' == "Unit"){
			$("select#op_comd").attr('disabled',false).css("background-color", "white");
			$("select#op_corps").attr('disabled',false).css("background-color", "white");
			$("select#op_div").attr('disabled',false).css("background-color", "white");
			$("select#op_bde").attr('disabled',false).css("background-color", "white");
			
			$("select#cont_comd").attr('disabled',false).css("background-color", "white");
			$("select#cont_corps").attr('disabled',false).css("background-color", "white");
			$("select#cont_div").attr('disabled',false).css("background-color", "white");
			$("select#cont_bde").attr('disabled',false).css("background-color", "white");
			
			$("select#adm_comd").attr('disabled',false).css("background-color", "white");
			$("select#adm_corps").attr('disabled',false).css("background-color", "white");
			$("select#adm_div").attr('disabled',false).css("background-color", "white");
			$("select#adm_bde").attr('disabled',false).css("background-color", "white");
		}
		
		if('${scenarioM}' == "Extend Raising/Disbandment"){
			$("div#goi_letter_no").hide();
			$("div#date_goi_letter").hide();
			$("div#goi_doc").hide();
		}
		if('${scenarioM}' == "Re-Orbatting"){
			$("div#goi_letter_no").hide();
			$("div#date_goi_letter").hide();
			$("div#goi_doc").hide();
			StateList();
			DistrictList('${viewUnitCMD.state}');
			$("div#stateDiv").show();
			$("div#districtDiv").show();
		}else{
			$("span#datelabelTo").text('${scenarioM}');
			$("span#datelabelFrom").text('${scenarioM}');
		}
		if( '${scenarioM}' == "Unit Profile" ){
			
			StateList();
			DistrictList('${viewUnitCMD.state}');
			$("div#stateDiv").show();
			$("div#districtDiv").show();
			
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
			
			$("div#cont_comd").hide();
			$("div#cont_corps").hide();
			$("div#cont_div").hide();
			$("div#cont_bde").hide();
			
			$("div#arm_code").hide();
			
			$("div#parent_arm").hide();
			$("div#type_of_arm").hide();
			
			$("div#comm_depart_date").hide();
			$("div#compltn_arrv_date").hide();
			
			
			
			$("div#loc").hide();
			$("div#nrs_name").hide();
			$("div#type_of_location").hide();
			$("div#modification").hide();
		}
		
		if('${scenarioM}' == "Main Body Move(MISO)"  ){
			$("span#datelabelFrom").text("Departure");
			$("span#datelabelTo").text("Arrival");
			
			$("div#goi_letter_no").hide();
			$("div#date_goi_letter").hide();
			$("div#goi_doc").hide();
			
			StateList();
			DistrictList('${viewUnitCMD.state}');
			$("div#stateDiv").show();
			$("div#districtDiv").show();
		}else{
			$("span#datelabelTo").text('${scenarioM}');
			$("span#datelabelFrom").text('${scenarioM}');
		}
		
		if('${scenarioM}' == "Amendment"  ){
			$("div#goi_letter_no").hide();
			$("div#date_goi_letter").hide();
			$("div#goi_doc").hide();
			
			StateList();
			DistrictList('${viewUnitCMD.state}');
			$("div#stateDiv").show();
			$("div#districtDiv").show();
		}else{
			$("span#datelabelTo").text('${scenarioM}');
			$("span#datelabelFrom").text('${scenarioM}');
		}
		
		var scenarioM = '${scenarioM}';
		if(scenarioM == "New Raising" || scenarioM == "Re-designation" || scenarioM == "Re-Structuring" ){
			$("#unit_name").attr('readonly',false);
		}else{
			$("#unit_name").attr('readonly',true);
		}
    	   
		var source_sus_no = '${viewUnitCMD.sus_no_for_comb_disint}';
		if(source_sus_no != ""){
			if(scenarioM == "Conversion" || scenarioM == "Re-designation" || scenarioM == "Re-Structuring"){
				$("div#source_unit_name").show();
   				$("div#source_sus_no").show();
   				$("#target").text("Target");
   				$("#target1").text("Target");
   		   }
		}
		
	
		if(scenarioM == "Disbandment" || scenarioM == "Extend Raising/Disbandment"){
			$("div#unit_army_hq").hide();
			$("div#op_comd").hide();
			$("div#op_corps").hide();
			$("div#op_div").hide();
			$("div#op_bde").hide();
			
			$("div#adm_comd").hide();
			$("div#adm_corps").hide();
			$("div#adm_div").hide();
			$("div#adm_bde").hide();
			
			$("div#arm_name").hide();
			$("div#arm_code").hide();
			$("div#loc").hide();
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
    	 
 	    $('select#parent_arm').change(function() {
	       	var code = this.value;    	   	
 	        $.post("getTypeOfArmArmList?"+key+"="+value,{code:code}, function(j) {
 	        	var length = j.length-1;
 				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
 				for ( var i = 0; i < length; i++) {
 					options += '<option value="' + dec(j[length][0],j[i][0]) + '" >'+ dec(j[length][0],j[i][0]) +" - "+  dec(j[length][0],j[i][1]) + '</option>';
 				}	
 				$("select#type_of_arm").html(options); 
 			});
 		});
 	   	
 	    
 	    $("select#arm_name").val('${viewUnitCMD.arm_code}');
 	    $('select#arm_name').change(function() {
 	    	$("input#arm_code").val(this.value);
 	   	});
 	    
  		var unit_under_armhq = '${viewUnitCMD.unit_army_hq}';
	 	if(unit_under_armhq == 'Y')
	 	{
	 		$("#inline-radio1").prop("checked", true);
    		$("#inline-radio1").attr('checked', 'checked');
	 	}
	 	else
	 	{
     		$("#inline-radio2").prop("checked", true);
  			$("#inline-radio2").attr('checked', 'checked');
	 	}
	 		
	 	var ct_part = '${viewUnitCMD.ct_part_i_ii}';
		if(ct_part == 'CTPartI')
		{	       	 				
			$("#radio1").prop("checked", true);
			$("#radio1").attr('checked', 'checked');
		}
		else if(ct_part == 'CTPartII')
		{
			$("#radio2").prop("checked", true);
			$("#radio2").attr('checked', 'checked');
		}
		else 
		{
			$("#radio3").prop("checked", true);
			$("#radio3").attr('checked', 'checked');
		}
 	 	debugger;
		var forcodeOperation = '${viewUnitCMD.form_code_operation}'; 
	 	var forcodeControl = '${viewUnitCMD.form_code_control}'; 
	 	var forcodeAdmin = '${viewUnitCMD.form_code_admin}'; 
	 		console.log(forcodeAdmin);
     	Opcom = forcodeOperation[0];
     	Opcorps = forcodeOperation[0] +forcodeOperation[1] + forcodeOperation[2];
     	Opdiv = forcodeOperation[0] +forcodeOperation[1] + forcodeOperation[2] + forcodeOperation[3] + forcodeOperation[4] + forcodeOperation[5];
     	Opbde = forcodeOperation ;
     	
     	Contcom =forcodeControl[0];
     	Contcorps=forcodeControl[0] + forcodeControl[1] + forcodeControl[2];
     	Contdiv= forcodeControl[0] + forcodeControl[1] + forcodeControl[2] + forcodeControl[3] + forcodeControl[4] + forcodeControl[5];;
     	Contbde=forcodeControl;
     	
		Admincom = forcodeAdmin[0];
     	Admincorps = forcodeAdmin[0] + forcodeAdmin[1] + forcodeAdmin[2];
     	Admindiv = forcodeAdmin[0] + forcodeAdmin[1] + forcodeAdmin[2] + forcodeAdmin[3] + forcodeAdmin[4] + forcodeAdmin[5];
     	Adminbde = forcodeAdmin;
     	debugger;
     	$('#op_comd option[value="'+Opcom+'"]').attr("selected", "selected");
     	getCorps(Opcom,Opcorps,"op_corps","0");
     	getDiv(Opcorps,Opdiv,"op_div","0");
     	getBde(Opdiv,Opbde,"op_bde","0");
     	//getOPCorps(Opcorps);
 	   	//getOPDiv(Opdiv);
 	   	//getOPBde(Opbde);
     		
 	 	$('#cont_comd option[value="'+ Contcom +'"]').attr("selected", "selected");
 	 	var cont_cname = $("#cont_comd").find('option:selected').attr("name");    	 
 		$("#cont_cname").val(cont_cname);
 		getCorps(Contcom,Contcorps,"cont_corps","1");
 		getDiv(Contcorps,Contdiv,"cont_div","1");
     	getBde(Contdiv,Contbde,"cont_bde","1");
 	 	//getCONTCorps(Contcorps);
     	//getCONTDiv(Contdiv);
  		//getCONTBde(Contbde);
  
  		$('#adm_comd option[value="'+ Admincom +'"]').attr("selected", "selected");
     	getCorps(Admincom,Admincorps,"adm_corps","0");
     	getDiv(Admincorps,Admindiv,"adm_div","0");
     	getBde(Admindiv,Adminbde,"adm_bde","0");
     	//getADMCorps(Admincorps);
     	//getADMDiv(Admindiv);
     	//getADMBde(Adminbde);
        $("select#type_force").val('${viewUnitCMD.type_force}');

    });
	
	
	function getCorps(comd,corps,labelCorps,status){
		var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
	    <c:forEach var="item" items="${getCorpsList}" varStatus="num" >
	    	var fcorps = '${item[0]}';
	    	if(fcorps[0] == comd){	
	    		if(corps == '${item[0]}'){
	    			options += '<option value="${item[0]}"  name="${item[1]}"  selected=selected>${item[1]}</option>';
	    			if(status = "1"){
	    				jQuery("#cont_aname").val('${item[1]}');
	    			}
	    		}else{
	    			options += '<option value="${item[0]}"  name="${item[1]}">${item[1]}</option>';
	    		}
	    	}
		</c:forEach>
		$("select#"+labelCorps).html(options); 
	}
	function getDiv(corps,div,labelDiv,status){
		var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
	    <c:forEach var="item" items="${getDivList}" varStatus="num" >
	    	var fcode = '${item[0]}';
	    	var fdiv = fcode[0] + fcode[1] + fcode[2];
	    	if(fdiv == corps){	
	    		if(div == '${item[0]}'){
	    			options += '<option value="${item[0]}"  name="${item[1]}" selected=selected>${item[1]}</option>';
	    			if(status = "1"){
	    				jQuery("#cont_dname").val('${item[1]}');
	    			}
	    		}else{
	    			options += '<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>';
	    		}
	    	}
		</c:forEach>
		$("select#"+labelDiv).html(options); 
	}
	function getBde(div,bde,labelBde,status){
		var options = '<option value="'+"0"+'">'+ "--Select--" + '</option>';
	    <c:forEach var="item" items="${getBdeList}" varStatus="num" >
	    	var fcode = '${item[0]}';
    		var fbde = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
    		if(fbde == div){	
	    		if(bde == '${item[0]}'){
	    			options += '<option value="${item[0]}"  name="${item[1]}" selected=selected>${item[1]}</option>';
	    			if(status = "1"){
	    				jQuery("#cont_bname").val('${item[1]}');
	    			}
	    		}else{
	    			options += '<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>';
	    		}
	    	}
		</c:forEach>
		$("select#"+labelBde).html(options); 
	}
	
     
    var popupWindow = null
 	function openLocLOV(url) {
 		popupWindow = window.open("locationLOV", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=900,height=600,fullscreen=no");
 	}
 	function HandlePopupResult(loc,nrs_name,loc_code,trn_type,typeofloc,nrscode) {
 		$("input#code").val(loc_code);
 		$("input#nrs_name").val(nrs_name);
 		$("input#modification").val(trn_type);
 		$("input#type_of_location").val(typeofloc);
 		$("input#loc").val(loc);
 		$("input#nrs_code").val(nrscode);
 	}
    function getDownloadImage(letter,id)
	{  
		document.getElementById("mid").value=id;
		document.getElementById("letter").value=letter;
		document.getElementById("scenarioM").value='${scenarioM}';
		document.getElementById("getDownloadImageForm").submit();
	}
    
    function StateList(){		
		var options="";
		$.post("getStateList?"+key+"="+value, function(j) {		
			options = '<option value="">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length; i++) {
				if('${viewUnitCMD.state}' == j[i].stcode11){
					options += '<option value="' + j[i].stcode11+ '" name="'+j[i].stname+'" selected=selected>'+ j[i].stname + '</option>';
				}else{
					options += '<option value="' + j[i].stcode11+ '" name="'+j[i].stname+'">'+ j[i].stname + '</option>';	
				}
				
			}				
			$("select#state").html(options);	
		});
	}

	function DistrictList(st1){
		var st="";
		if(st1 != "" & st1 != undefined){
			st=st1;	
		}else{
			st=$("select#state").val();	
		}
		$("select#district").empty();
		if(st!='--Select--' || st !="" || st != null){
			$.post("getStateWiseDistrictNames?"+key+"="+value, {stateId : st}, function(j) {			
				var options = '<option value="">'+ "--Select--" + '</option>';
				for ( var i = 0; i < j.length; i++) {
					if('${viewUnitCMD.district}' == j[i].distId){
						options += '<option value="' + j[i].distId + '" name="'+j[i].distName+'" selected=selected>'+ j[i].distName + '</option>';
					}else{
						options += '<option value="' + j[i].distId + '" name="'+j[i].distName+'">'+ j[i].distName + '</option>';
					}
				}
				$("select#district").html(options);
			});
		} 
		else
		{
			var opt = '<option value="">'+ "--Select--" + '</option>';
			$("select#district").append("");
		}
	}
</script>

<c:url value="getDownloadImageOrbatModify" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id">
	<input type="hidden" name="mid" id="mid" value="0"/>
	<input type="hidden" name="letter" id="letter" value="0"/>
	<input type="hidden" name="scenarioM" id="scenarioM" value="0"/>
</form:form>