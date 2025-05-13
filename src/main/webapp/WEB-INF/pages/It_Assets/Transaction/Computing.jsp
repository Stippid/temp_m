<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<link rel="stylesheet" href="js/layout/css/animation.css">
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<style>
.ui-tooltip {
	position: absolute;
	top: 110px;
	left: 100px;
}
</style>
<form:form name="MainAssets" id="MainAssets" action="MainAssetsAction?${_csrf.parameterName}=${_csrf.token}"
	method="post" class="form-horizontal" commandName="MainAssetsCmd" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5 id="save">ADD COMPUTING ASSETS</h5>
					<h5 id="update">UPDATE/EDIT COMPUTING ASSETS</h5>
					<!-- 					<h6 class="enter_by"> -->
					<!-- 						<span style="font-size: 12px; color: red;">(To be entered by MISO)</span> -->
					<!-- 					</h6> -->
				</div>
				<form:hidden id="id" path="id" class="form-control autocomplete"
					autocomplete="off"></form:hidden>
					
					
					<input type="hidden" id="make_mstr_id" name="make_mstr_id" class="form-control autocomplete" autocomplete="off">
					
					<input type="hidden" id="model_mstr_id" name="model_mstr_id" class="form-control autocomplete" autocomplete="off">
					
					<input type="hidden" id="pro_type_id" name="pro_type_id" class="form-control autocomplete" autocomplete="off">
					
					<input type="hidden" id="office_mstr_id" name="office_mstr_id" class="form-control autocomplete" autocomplete="off">
					
					<input type="hidden" id="os_mstr_id" name="os_mstr_id" class="form-control autocomplete" autocomplete="off">
					
					<input type="hidden" id="u_file_hid" name="u_file_hid" value="${MainAssetsCmd.u_file}" class="form-control autocomplete" autocomplete="off">

					<input type="hidden" id="session_username" name="session_username" class="form-control autocomplete" value="${session_username}">				
					
					
				<div class="card-body card-block">
					<div class="col-md-12">
									<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
												</div>
												<div class="col-md-8">
												<!-- 	<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50">				 -->
														
									<input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" style="display: none">				
										   <label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label> 	
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong> Unit SUS No </label>
												</div>
												<div class="col-md-8">
											<label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off"   style="display: none"> 
												
												</div>
											</div>
										</div>
								</div>
										
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Assets Type </label>
								</div>
								<div class="col-md-8">
									<form:select path="asset_type" id="asset_type"
										class="form-control" onchange="fn_makeName();">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${ComputingAssetList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Make/Brand Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="make_name" id="make_name"
										class="form-control" onchange="fn_modelName();fn_brand_other();">
										<form:option value="0">--Select--</form:option>

									</form:select>
								</div>
							</div>
						</div>
					</div>
					
						<div class="col-md-12">
							<div class="col-md-6" id = "brand_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
										style="color: red;">* </strong>Make/Brand Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()"
					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete" 
					                	autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
						</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Model Name</label>
								</div>
								<div class="col-md-8">
									<form:select path="model_name" id="model_name" onchange="fn_make_other();"
										class="form-control">
										<form:option value="0">--Select--</form:option>

									</form:select>
								</div>
							</div>
						</div>
					
						<div class="col-md-6" id = "model_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
										style="color: red;">* </strong>Model Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="model_other" name="model_other" oninput="this.value = this.value.toUpperCase()"
					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchModelOther();" class="form-control autocomplete" 
					                	autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Processor Type</label>
								</div>
								<div class="col-md-8">
									<form:select path="processor_type" id="processor_type" onchange="fn_pro_type_other();"
										class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${processor_typeList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6" id = "pro_type_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
										style="color: red;">* </strong>Processor Type Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="pro_type_other" name="pro_type_other" oninput="this.value = this.value.toUpperCase()"
					                	onkeypress="return onlyAlphaNumeric(event);" class="form-control autocomplete" onchange="searchProcessorTypeOther();"
					                	autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
						</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>RAM Capacity </label>
								</div>
								<div class="col-md-8">
									<form:select path="ram_capi" id="ram_capi" class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${ramList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>HDD Capacity </label>
								</div>
								<div class="col-md-8">
									<form:select path="hdd_capi" id="hdd_capi" class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${hddList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Operating System </label>
								</div>
								<div class="col-md-8">
									<form:select path="operating_system" id="operating_system" onchange="fn_os_other();"
										class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${osList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6" id = "os_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
										style="color: red;">* </strong>Operating System Other</label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="os_other" name="os_other" oninput="this.value = this.value.toUpperCase()"
					                	onkeypress="return onlyAlphaNumeric(event);" class="form-control autocomplete" onchange="searchOperatingSystemTypeOther();"
					                	autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
						</div>
						<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>Office</label>
								</div>
								<div class="col-md-8">
									<form:select path="office" id="office" onchange="fn_office_other();" class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${officeList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
							<div class="col-md-6" id = "office_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
										style="color: red;">* </strong>Office Other </label>
						                </div>
						                <div class="col-md-8">
					                	<input type="text" id="office_other" name="office_other" oninput="this.value = this.value.toUpperCase()"
					                	onkeypress="return onlyAlphaNumeric(event);" class="form-control autocomplete"  onchange="searchOfficeTypeOther();"
					                	autocomplete="off" maxlength="50" >
					                	 </div>
						            </div>
	              				</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>AntiVirus Installed </label>
								</div>
								<div class="col-md-8" onclick="anti_show();">
									<form:radiobutton id="antiviruscheck1" value="Yes"
										path="antiviruscheck"></form:radiobutton>
									&nbsp <label for="yes">Yes</label>&nbsp
									<form:radiobutton id="antiviruscheck2" path="antiviruscheck"
										value="No" checked="checked"></form:radiobutton>
									&nbsp <label for="no">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="AntiVirusDiv" style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>AntiVirus</label>
								</div>
								<div class="col-md-8">
									<form:select path="antivirus" id="antivirus"
										class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${AntivirusList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>OS/Firmware Activation and
										subsequent Patch Updation Mechanism </label>
								</div>
								<div class="col-md-8">
									<form:select path="os_patch" id="os_patch" class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${osFirmwareList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Dply Envt as Applicable</label>
								</div>
								<div class="col-md-8">
									<form:select path="dply_envt" id="dply_envt"
										class="form-control">
										<form:option value="0">--Select--</form:option>
										<c:forEach var="item" items="${dplyEnvList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					<!-- 					<div class="col-md-12"> -->
					<!-- 						<div class="col-md-6"> -->
					<!-- 							<div class="row form-group"> -->
					<!-- 								<div class="col-md-4"> -->
					<!-- 									<label class=" form-control-label"><strong style="color: red;"> </strong>Connecting Technology</label> -->
					<!-- 								</div> -->
					<!-- 								<div class="col-md-8"> -->
					<%-- 									<form:select path="connecting_tech" id="connecting_tech" class="form-control"> --%>
					<%-- 									<form:option value="0" >--select--</form:option> --%>
					<%-- 										<c:forEach var="item" items="${getConnectingTechList}" varStatus="num"> --%>
					<%-- 											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option> --%>
					<%-- 										</c:forEach> --%>
					<%-- 									</form:select> --%>
					<!-- 								</div> -->
					<!-- 							</div> -->
					<!-- 						</div> -->
					<!--  						<div class="col-md-6"> -->
					<!-- 							<div class="row form-group"> -->
					<!-- 								<div class="col-md-4"> -->
					<!-- 									<label class=" form-control-label"><strong style="color: red;"> </strong>Dimension</label> -->
					<!-- 								</div> -->
					<!-- 								<div class="col-md-8"> -->
					<%-- 									<form:input type="text" id="dimension" path="dimension" class="form-control autocomplete" autocomplete="off"></form:input> --%>
					<!-- 								</div> -->
					<!-- 							</div> -->
					<!-- 						</div> -->
					<!-- 					</div> -->

					<!-- 					<div class="col-md-12"> -->
					<!-- 						<div class="col-md-6"> -->
					<!-- 							<div class="row form-group"> -->
					<!-- 								<div class="col-md-4"> -->
					<!-- 									<label class=" form-control-label"><strong style="color: red;">* </strong>Ethernet Port</label> -->
					<!-- 								</div> -->
					<!-- 								<div class="col-md-8"> -->
					<%-- 									<form:select path="ethernet_port" id="ethernet_port" class="form-control"> --%>
					<%-- 									<form:option value="0" >--select--</form:option> --%>
					<%-- 										<c:forEach var="item" items="${getEthernetPortList}" varStatus="num"> --%>
					<%-- 											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option> --%>
					<%-- 										</c:forEach> --%>
					<%-- 									</form:select> --%>
					<!-- 								</div> -->
					<!-- 							</div> -->
					<!-- 						</div> -->
					<!--  						<div class="col-md-6"> -->
					<!-- 							<div class="row form-group"> -->
					<!-- 								<div class="col-md-4"> -->
					<!-- 									<label class=" form-control-label"><strong style="color: red;">* </strong>Part Number</label> -->
					<!-- 								</div> -->
					<!-- 								<div class="col-md-8"> -->
					<%-- 									<form:input type="text" id="part_no" path="part_no" class="form-control autocomplete" autocomplete="off"></form:input> --%>
					<!-- 								</div> -->
					<!-- 							</div> -->
					<!-- 						</div> -->
					<!-- 					</div> -->
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>RAM Slot Quantity</label>
								</div>
								<div class="col-md-8">
									<form:input type="number" id="ram_slot_qty" path="ram_slot_qty" onkeypress="return isNumber(event)"
										min="1" max="12" class="form-control autocomplete"
										autocomplete="off"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"></strong>CD Drive</label>
								</div>
								<div class="col-md-8">
									<div class="col-md-8">
										<form:radiobutton id="scan" value="Yes" path="cd_drive"></form:radiobutton>
										&nbsp <label for="yes">Yes</label>&nbsp
										<form:radiobutton id="scan" path="cd_drive" value="No"
											checked="checked"></form:radiobutton>
										&nbsp <label for="no">No</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Warranty</label>
								</div>
								<div class="col-md-8" onclick="warrenty_show();">
									<form:radiobutton id="Warranty1" value="Yes"
										path="warrantycheck" checked="checked"></form:radiobutton>
									&nbsp <label for="yes">Yes</label>&nbsp
									<form:radiobutton id="Warranty2" path="warrantycheck"
										value="No"></form:radiobutton>
									&nbsp <label for="no">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="WarrantyDiv">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Warranty Upto</label>
								</div>
								<div class="col-md-8">
									<!-- 									<input type="Date" id="warranty" name="warranty" class="form-control autocomplete" autocomplete="off"></input> -->
									<input type="text" name="warranty_dt" id="warranty"
										maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control" style="width: 94%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');"
										aria-required="true" autocomplete="off"
										style="color: rgb(0, 0, 0);">

								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
					
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Username</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="username" name="username" class="form-control autocomplete"
										autocomplete="off"></input>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Domain Username</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="domain_username" name="domain_username" class="form-control autocomplete"
										autocomplete="off"></input>
								</div>
							</div>
						</div>
					
					</div>

					<div class="col-md-12">
						<div class="col-md-6" id="countDiv" style="display: none;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label" ><strong
										style="color: red;">* </strong>Total Count</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="assetcount" name="assetcount"
										onkeyup="addMultiAsset()" onblur="counthover()" value="1"
										onkeypress="return isNumber(event)"
										class="form-control autocomplete" autocomplete="off"></input>
								</div>
							</div>
						</div>

					</div>


					<div class="" id="multiAssetDiv">
						<div class="row"
							style="border: solid #b7b6b0 5px;padding: 10px;margin: 5px;">
							<!-- 					<input type="text" id="ch_id1" name="ch_id1" class="form-control autocomplete" autocomplete="off" style="display: none;"></input> -->
							<div id="printableArea2">
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong>Model Number</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="model_number1" name="model_number1" maxlength="20" 
												class="form-control autocomplete"   oninput="this.value = this.value.toUpperCase()" autocomplete="off" onkeypress=" return onlyAlphaNumeric(event, this);alphanumeric(this);"></input>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;">* </strong>Machine No.</label>
										</div>
										<div class="col-md-8">
											<input type="text" id="machine_number1"
												name="machine_number1" 
												class="form-control autocomplete machineno_class" maxlength="30"  onchange="return validateCode();"
												autocomplete="off" class="form-control autocomplete" oninput="this.value = this.value.toUpperCase()" onkeypress="return onlyAlphaNumeric(event, this)" onchange="searchMachineNumber();"></input>
										</div>
									</div>
								</div>
							</div>
							<div class="col-md-12">

								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong
												style="color: red;"> </strong>MAC Address</label>
										</div>
										<div class="col-md-8">
											<input title="Enter MAC Address in given format  8C-EC-4B-48-01-00. Type getmac in command prompt and press Enter to get MAC Address." oninput="this.value = this.value.toUpperCase()"
												type="text" id="mac_address1" name="mac_address1"
												class="form-control autocomplete"
												onkeyup="makeMacAddress(this);"
													onchange="return validateCodeMac();"
												onkeypress="makeMacAddress(this);return /[0-9a-fA-F]/i.test(event.key);return onlyAlphaNumeric(event, this);"
												maxlength="17" autocomplete="off"></input>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label id="nonIpDisplay" class=" form-control-label"><strong
												style="color: red;"> </strong>IP Address </label> <label
												id="ipDisplay" class=" form-control-label"
												style="display: none;"><strong style="color: red;">*
											</strong>IP Address </label>
										</div>
										<div class="col-md-8">

											<input title="Enter IP Address in given format 192.168.151.191. Type ipconfig in command prompt and press Enter to get IP Address."
												type="text" id="ip_address1" name="ip_address1"
												maxlength="15" class="form-control autocomplete"
												onchange="ValidateIPaddress(this);" autocomplete="off"></input>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>

				<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_warrenty"
									onclick="divN(this)"><b>SERVICE DETAILS</b></a>
							</h4>
						</div>
						<div id="collapsewarrantey" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Serviceable State</label>
												</div>
												<div class="col-md-8">
													<form:select path="s_state" id="s_state"
														class="form-control" onchange="serviceStChange();">
														<form:option value="0">--Select--</form:option>
														<c:forEach var="item" items="${getServiceable_StateList}"
															varStatus="num">
															<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
														</c:forEach>
													</form:select>

												</div>
											</div>
										</div>
										<div class="col-md-6" id="un_show" style="display: none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Un-serviceable State</label>
												</div>
												<div class="col-md-8">
													<form:select path="unserviceable_state"
														id="unserviceable_state" class="form-control" onchange="selectBer();">
														<form:option value="0">--Select--</form:option>
														<c:forEach var="item" items="${UNServiceableList}"
															varStatus="num">
															<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
														</c:forEach>
													</form:select>

												</div>
											</div>
										</div>
									</div>
					
					
					<div class="col-md-12" id="file_show" style="display: none;">
						<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Upload Document</label>
												</div>
												<div class="col-md-8">
													<input type = "file" id="u_file1" name="u_file1" />
													
													 <i class="fa fa-download"><input type="button" class="btn btn-success btn-sm"  value="File" onclick="download_file();" /></i>          
													
<!-- 													<label id="lbl_u_file_id"></label> -->
												</div>
											</div>
										</div>
									</div>
									
								

								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="col-md-6" id="unsv_div" style="display: none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Un-servicable from</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="unsv_from_dt1" id="unsv_from_dt1"
										maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
										class="form-control" style="width: 94%; display: inline;"
										onfocus="this.style.color='#000000'"
										onblur="clickrecall(this,'DD/MM/YYYY');"
										onkeyup="clickclear(this, 'DD/MM/YYYY')"
										onchange="clickrecall(this,'DD/MM/YYYY');"
										aria-required="true" autocomplete="off"
										style="color: rgb(0, 0, 0);">
												</div>
											</div>
										</div>
										</div>
									</div>
							</div>
						</div>
					</div>

				</div>




				<div class="panel-group" id="accordion4">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_budget"
									onclick="divN(this)"><b>PROCUREMENT DETAILS</b></a>
							</h4>
						</div>
						<div id="collapse1budget" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
								<div class="row">

									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;"> </strong>Proc Cost (&#8377) </label>
												</div>
												<div class="col-md-8">
													<form:input title="Approx. Procurement Cost" type="text" id="b_cost" path="b_cost"
														onkeypress="return isNumber(event)"
														class="form-control autocomplete" autocomplete="off"></form:input>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">*</strong>Proc Date</label>
												</div>
												<div class="col-md-8">
													<!-- 									<input type="Date" id=proc_date name="proc_date" class="form-control autocomplete" autocomplete="off"></input> -->
													<input type="text" name="proc_dt" id="proc_date"
														maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control" style="width: 94%; display: inline;"
														onfocus="this.style.color='#000000'"
														onblur="clickrecall(this,'DD/MM/YYYY');"
														onkeyup="clickclear(this, 'DD/MM/YYYY')"
														onchange="clickrecall(this,'DD/MM/YYYY');"
														aria-required="true" autocomplete="off"
														style="color: rgb(0, 0, 0);">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Budget Head</label>
												</div>
												<div class="col-md-8">
													<form:select path="b_head" id="b_head" class="form-control"
														onchange="fn_B_Head()">
														<form:option value="0">--Select--</form:option>
														<c:forEach var="item" items="${getBudgetHeadList}"
															varStatus="num">
															<form:option value="${item[1]}" name="${item[1]}">${item[1]}</form:option>
														</c:forEach>
													</form:select>
													<%-- 									<form:input type="text" id="b_head" path="b_head" class="form-control autocomplete" autocomplete="off"></form:input> --%>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>Budget Code</label>
												</div>
												<div class="col-md-8">
													<form:select path="b_code" id="b_code" class="form-control">
														<form:option value="0">--Select--</form:option>
														<%-- 											 <c:forEach var="item" items="${UNServiceableList}" varStatus="num"> --%>
														<%-- 											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option> --%>
														<%-- 										</c:forEach> --%>
													</form:select>
													<%-- 									<form:input type="text" id="b_code" path="b_code" class="form-control autocomplete" autocomplete="off"></form:input> --%>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
		
				
<!-- 				FORMATTING -->
				
				<div class="panel-group" id="accordion7">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div6">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion7" href="#" id="f_details"
									onclick="divN(this)"><b>FORMATTING DETAILS</b></a>
							</h4>
						</div>
						<div id="collapse1formatting" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
								<div class="row">

									<div class="col-md-12">
										
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Last Format Done</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="lfdDate" id="lfdDate"
														maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control" style="width: 90%; display: inline;"
														onfocus="this.style.color='#000000'"
														onblur="clickrecall(this,'DD/MM/YYYY');"
														onkeyup="clickclear(this, 'DD/MM/YYYY')"
														onchange="clickrecall(this,'DD/MM/YYYY');"
														aria-required="true" autocomplete="off"
														style="color: rgb(0, 0, 0);">
												</div>
											</div>
										</div>
										
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Authority (Details Of Autority)</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="adoa" name="adoa"
														class="form-control autocomplete" autocomplete="off"></input>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Reason For Formatting</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="rff" name="rff" class="form-control autocomplete" autocomplete="off"></input>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				
<!-- 				CYBER AUDIT -->
				
				<div class="panel-group" id="accordion8">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div7">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion8" href="#" id="cy_details"
									onclick="divN(this)"><b>CYBER AUDIT DETAILS</b></a>
							</h4>
						</div>
						<div id="collapse1cyber" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
								<div class="row">

									<div class="col-md-12">
										
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Date Of Last Cyber Audit</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="dolcaDate" id="dolcaDate"
														maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control" style="width: 90%; display: inline;"
														onfocus="this.style.color='#000000'"
														onblur="clickrecall(this,'DD/MM/YYYY');"
														onkeyup="clickclear(this, 'DD/MM/YYYY')"
														onchange="clickrecall(this,'DD/MM/YYYY');"
														aria-required="true" autocomplete="off"
														style="color: rgb(0, 0, 0);">
												</div>
											</div>
										</div>
										
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Type Of Audit</label>
												</div>
												<div class="col-md-8">
<!-- 													<input type="text" id="toa" name="toa" class="form-control autocomplete" autocomplete="off"></input> -->
													<select name="toa" id="toa" class="form-control" >
														<option value="0">--Select--</option>
														<option value="Internal">Internal</option>
														<option value="External">External</option>
													</select>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Audit Done By (Agency Name)</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="adban" name="adban" class="form-control autocomplete" autocomplete="off"></input>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
				
<!-- 				PC LOGBOOK				 -->

				<div class="panel-group" id="accordion9">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div8">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion9" href="#" id="pc_details"
									onclick="divN(this)"><b>PC LOG BOOK</b></a>
							</h4>
						</div>
						<div id="collapse1pc" class="panel-collapse collapse">
							<div class="card-body card-block">
								<br>
								<div class="row">

									<div class="col-md-12">
										
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Type</label>
												</div>
												<div class="col-md-8">
													<select name="typeUR" id="typeUR" class="form-control">
													<option value="0">Upgradation</option>
													<option value="1">Repair</option>
													</select>
												</div>
											</div>
										</div>
										
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Date Of Upgradation/Repair</label>
												</div>
												<div class="col-md-8">
													<input type="text" name="urDate" id="urDate"
														maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
														class="form-control" style="width: 90%; display: inline;"
														onfocus="this.style.color='#000000'"
														onblur="clickrecall(this,'DD/MM/YYYY');"
														onkeyup="clickclear(this, 'DD/MM/YYYY')"
														onchange="clickrecall(this,'DD/MM/YYYY');"
														aria-required="true" autocomplete="off"
														style="color: rgb(0, 0, 0);">
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Component Upgraded</label>
												</div>
												<div class="col-md-8">
													<select name="compUp" id="compUp" class="form-control">
													<option value="0">RAM</option>
													<option value="1">HDD</option>
													<option value="2">Graphics Card</option>
													<option value="3">SW</option>
													<option value="4">Others</option>
													</select>
												</div>
											</div>
										</div>
										
										<div id="otherCompDiv" class="col-md-6" style="display:none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Other Component</label>
												</div>
												<div class="col-md-8">
													<input type="text" id="othComp" name="othComp" class="form-control autocomplete" autocomplete="off"></input>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
				
				
				
				
				
			</div>


			<div class="card-footer" align="center" class="form-control">
				<a href="Search_Computing_AssetsUrl" id="clear_1" class="btn btn-success btn-sm">Back</a> 
				<input type="submit" class="btn btn-primary btn-sm" value="Save" id="save_btn" onclick="return Validate();">
				<input type="submit" class="btn btn-primary btn-sm" value="Save"  id="update_btn" onclick="return Validate();">
				<i class="fa fa-download"></i><input type="button" class="btn btn-primary btn-sm" value="Print Data" id="btn_p" onclick="printDiv();">
			</div>
		</div>

	</div>

</form:form>

<script>

var compUpSelect = document.getElementById('compUp');
var otherCompDiv = document.getElementById('otherCompDiv');

compUpSelect.addEventListener('change', () => {
	if (compUpSelect.value === '4') { // Assuming 'Others' has value '1'. Adjust if different.
    	otherCompDiv.style.display = 'block';
  	} else {
    	otherCompDiv.style.display = 'none';
  	}
});


$(document).ready(function() {
	
	var r =('${roleAccess}');
	if( r == "Unit"){
		
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
		 
	}
	else  {
		
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
		
	}
	
   var sus_nop = '${roleSusNo}';
  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_nop:sus_nop}).done(function(data) {
  		var l=data.length-1;
  		var enc = data[l].substring(0,16);    	   	 
  	 	$("#unit_name").text(dec(enc,data[0]));
  		}).fail(function(xhr, textStatus, errorThrown) {
	  }); 
	
	var id=$('#id').val();
	if(id == 0)
		{
		$('#save').show();
		$('#update').hide();
		$('#save_btn').show();
		$('#clear_1').hide();
		$('#update_btn').hide();
		}
	else{
		$('#save').hide();
		$('#update').show();
		$('#clear_1').show();
		$('#save_btn').hide();
		$('#update_btn').show();
		
	}
	
	$.ajaxSetup({
		 async : false
});
	
		$("#mac_address1").tooltip();
		$("#ip_address1").tooltip();
		$("#b_cost").tooltip();
		$("#mac_address1").css('cursor','pointer');
		$("#ip_address1").css('cursor','pointer');
		$("#b_cost").css('cursor','pointer');


	warrenty_show();
	datepicketDate('warranty');
	datepicketDate('proc_date');
	datepicketDate('unsv_from_dt1');
	
	datepicketDate('date_of_commission');
	datepicketDate('date_of_retirement');
	datepicketDate('dt_of_approval');
	datepicketDate('dt_of_sanction');
	datepicketDate('dt_of_purchase');
	
	datepicketDate('lfdDate');
	datepicketDate('dolcaDate');
	datepicketDate('urDate');
	
	 $( "#warranty" ).datepicker( "option", "maxDate", null);
	
// 	ValidateIPaddress(this);
	if('${MainAssetsCmd.id}' != "0"){
		$("#countDiv").hide();
		$("#model_number1").val('${MainAssetsCmd.model_number}');
		$("#machine_number1").val('${MainAssetsCmd.machine_number}');
		$("#mac_address1").val('${MainAssetsCmd.mac_address}');
		$("#ip_address1").val('${MainAssetsCmd.ip_address}');
	}
	
	$("#warranty").val((ParseDateColumncommission('${MainAssetsCmd.warranty}')));	
	$("input[name='warrantycheck'][value='${MainAssetsCmd.warrantycheck}']").prop("checked",true);
	$("#proc_date").val((ParseDateColumncommission('${MainAssetsCmd.proc_date}')));	
	

	//** BISG AHM **// 
	if ('${MainAssetsCmd.s_state}' == "" || '${MainAssetsCmd.s_state}' == null || '${MainAssetsCmd.s_state}' == "0"){
		$("select#s_state").val(0);
	}else{
		$("select#s_state").val('${MainAssetsCmd.s_state}');
	}
	
	$("select#unserviceable_state").val(('${MainAssetsCmd.unserviceable_state}'));
	$("#unsv_from_dt1").val((ParseDateColumncommission('${MainAssetsCmd.unsv_from_dt}')));	
	
	//** END BISG AHM **// 
	
// 		$("#warranty").val(('${MainAssetsCmd.warranty}').substring(0, 10));	
// 		$("#proc_date").val(('${MainAssetsCmd.proc_date}').substring(0, 10));	
		$("input[name='antiviruscheck'][value='${MainAssetsCmd.antiviruscheck}']").prop("checked",true);
		anti_show();
		$("input[name='cd_drive'][value='${MainAssetsCmd.cd_drive}']").prop("checked",true);
		
		fn_makeName();	
		$("select#make_name").val('${MainAssetsCmd.make_name}');
		fn_brand_other();
		$("#brand_other").val('${make_mstr_other}');	
		fn_modelName();
		$("select#model_name").val('${MainAssetsCmd.model_name}');	
		fn_make_other();
		$("#model_other").val('${model_mstr_other}');	
		
		fn_pro_type_other();
		$("#pro_type_other").val('${pro_type_other}');	
			
		fn_os_other();
		$("#os_other").val('${os_mstr_other}');	


		fn_office_other();
		$("#office_other").val('${office_mstr_other}');	
		
		
		serviceStChange();
		
		fn_B_Head();
		
// 		-----fucntion written by bisag delhi
		$("select#b_code").val('${MainAssetsCmd.b_code}');	
		
		$("select#operating_system").on("change", function() {
			if($("select#operating_system").val()==0){
				 $("#newLabel").hide();
			}
			else{
		    $("#newLabel").show();
			}
		    
		 
	    });
		
		selectBer();

		
		
		
		$("#make_mstr_id").val('${make_mstr_id}');	
		$("#model_mstr_id").val('${model_mstr_id}');	
		$("#pro_type_id").val('${pro_type_id}');	
		$("#office_mstr_id").val('${office_mstr_id}');	
		$("#os_mstr_id").val('${os_mstr_id}');
		
// 		$("#u_file_hid").val();
		
// 		$("#lbl_u_file_id").text('${MainAssetsCmd.u_file}');
		
// 		-----fucntion written by bisag delhi

	$("#unit_sus_no").val('${MainAssetsCmd.sus_no}');
		
		var sus_no ='${MainAssetsCmd.sus_no}';
		getunit(sus_no);
		
		var text = $("#make_name option:selected").text();
		
		$("#adoa").val('${MainAssetsCmd.details_of_auth}');
		$("#rff").val('${MainAssetsCmd.reason_for_formatting}');
		$("#toa").val('${MainAssetsCmd.type_of_audit}');
		$("#adban").val('${MainAssetsCmd.audit_done_by}');
		$("#typeUR").val('${MainAssetsCmd.type_upgrade_repair}');
		$("#compUp").val('${MainAssetsCmd.comp_upgrade_repair}');
		$("#othComp").val('${MainAssetsCmd.comp_other}');
		

		$("#lfdDate").val((ParseDateColumncommission('${MainAssetsCmd.last_format_done}')));
		$("#dolcaDate").val((ParseDateColumncommission('${MainAssetsCmd.last_cyber_audit_date}')));
		$("#urDate").val((ParseDateColumncommission('${MainAssetsCmd.upgrade_repair_date}')));
		
		function getunit(val) {	
	        $.post("getTargetUnitNameList?"+key+"="+value, {
	        	sus_no : sus_no
	    }, function(j) {
	            
	            

	    }).done(function(j) {
	            for (var i = 0; i < j.length; i++) {
					   var length = j.length-1;
		                   var enc = j[length].substring(0,16); 
		                   $("#unit_name").val(dec(enc,j[0])); 
				}
	    }).fail(function(xhr, textStatus, errorThrown) {
	    });
	}  
		

		$("select#dply_envt").on("change", function() {
			if($("select#dply_envt").val()==7){
				 $("label#ipDisplay").show();
					$("label#nonIpDisplay").hide();

			}
			else if($("select#dply_envt").val()==9){
				 $("label#ipDisplay").show();
					$("label#nonIpDisplay").hide();			}
			else{
				$("label#ipDisplay").hide();
				$("label#nonIpDisplay").show();
			}
			
		 
	    });
	
});
</script>

<script>




function ValidateIPaddress(ipaddress) {  
	var ip = new RegExp(/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/)
	  if (ip.test(ipaddress.value)) {  
	    return true;  
	   
	  }  
	  else{
	 	alert("You have entered an invalid IP Address!")  
		$("#ip_address1").focus();
		$("#ip_address1").val("");
		  return false;
	  }
	
	} 

function ValidateMACAddress(value){
	
regex=/^([0-9a-f]{2}([:-]|$)){6}$|([0-9a-f]{4}([.]|$)){3}$/i;


	
	if (regex.test(value)){
		return true;
	}
	else{
		return false;
	}
	
}

function makeMacAddress(obj){
	
	 if(obj.value!=''){
	 var val=obj.value.split('-').join('');
	 var v = val.match(/.{1,2}/g).join("-");
	 obj.value=v;
	 }
}


</script>

<script>
function Validate(){
// 	alert($("#MainAssets").serialize());
	if($("select#dply_envt").val()==7 ||$("select#dply_envt").val()==9 ){
	 	if( $("#ip_address1").val()==""){
		
		 	alert("Please Select IP Address ")  
			$("#ip_address1").focus();
			return false;
		 
	}
		
	}
	
	if($("#asset_type").val()==0 || $("#asset_type").val()==""){
		alert("Please Select Computing Assets Type ");
		$("#asset_type").focus();
		return false;
	}
	
	
	if($("#make_name").val()==0 || $("#make_name").val()==""){
		alert("Please Select Make/Brand Name ");
		$("#make_name").focus();
		return false;
	}
	
	 var text = $("#make_name option:selected").text();
		
		if(text.toUpperCase() == "OTHERS"){
			if( $("#brand_other").val().trim() == ""){
				alert("Please Enter Make Name Other. ");
				$("#brand_other").focus();
				return false;
			}
		
	}
	
	if($("#model_name").val()==0 || $("#model_name").val()==""){
		alert("Please Select Model Name ");
		$("#model_name").focus();
		return false;
	}
	
	
	var text = $("#model_name option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		if( $("#model_other").val().trim() == ""){
			alert("Please Enter Model Name Other. ");
			$("#model_other").focus();
			return false;
		}
	
}
	
// 	if(!ValidateMACAddress( $("#mac_address").val())){
// 	 	alert("You have entered an invalid MAC Address!")  
// 		$("#mac_address").focus();
// 		return false;
// 	 }
	
// 	if( $("#ip_address").val()!=""){
// 		 if(!ValidateIPaddress( $("#ip_address").val())){
// 		 	alert("You have entered an invalid IP Address!")  
// 			$("#ip_address").focus();
// 			return false;
// 		 }
// 	}

	if($("#office").val()==0 || $("#office").val()==""){
		alert("Please Select Office Type ");
		$("#office").focus();
		return false;
	}

		 
		
	if($("#processor_type").val()==0 || $("#processor_type").val()==""){
		alert("Please Select Processor Type ");
		$("#processor_type").focus();
		return false;
	}
	var text = $("#processor_type option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		if( $("#pro_type_other").val().trim() == ""){
			alert("Please Enter Processor Type Other. ");
			$("#pro_type_other").focus();
			return false;
		}
	
	}
	
	
	if($("#ram_capi").val()==0 || $("#ram_capi").val()==""){
		alert("Please Select RAM Capacity  ");
		$("#ram_capi").focus();
		return false;
	}
	if($("#hdd_capi").val()==0 || $("#hdd_capi").val()==""){
		alert("Please Select HDD Capacity ");
		$("#hdd_capi").focus();
		return false;
	}
	
	if($("#operating_system").val()==0 || $("#operating_system").val()==""){
		alert("Please Select Operating System  ");
		$("#operating_system").focus();
		return false;
	}
	var text = $("#operating_system option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		if( $("#os_other").val().trim() == ""){
			alert("Please Enter Operating System Other. ");
			$("#os_other").focus();
			return false;
		}
	
	}
	
	
var text = $("#office option:selected").text();
	
	if(text.toUpperCase() == "OTHERS"){
		if( $("#office_other").val().trim() == ""){
			alert("Please Enter Office Other. ");
			$("#office_other").focus();
			return false;
		}
	
	}
	
	var antivirusChecked=$('input[name="antiviruscheck"]:checked').val();
	
	if(antivirusChecked.toUpperCase()=="YES"){
		if($("#antivirus").val()==0 || $("#antivirus").val()==""){
			alert("Please Select AntiVirus  ");
			$("#antivirus").focus();
			return false;
		}
	}
	else{
		$("#antivirus").val('0');
	}
	
	
	if($("#os_patch").val()==0 || $("#os_patch").val()==""){
		alert("Please Select OS/Firmware Activation and Subsequent Patch Updation Mechanism   ");
		$("#os_patch").focus();
		return false;
	}
	
	if($("#dply_envt").val()==0 || $("#dply_envt").val()==""){
		alert("Please Select Dply Envt as Applicable  ");
		$("#dply_envt").focus();
		return false;
	}
	
// 	if($("#ethernet_port").val()==0 || $("#ethernet_port").val()==""){
// 		alert("Please Select Ethernet Port");
// 		$("#ethernet_port").focus();
// 		return false;
// 	}
// 	if($("#part_no").val()==0 || $("#part_no").val()==""){
// 		alert("Please Enter Part Number");
// 		$("#part_no").focus();
// 		return false;
// 	}
	
// 	if( $("#ram_slot_qty").val()=="" || $("#ram_slot_qty").val()=="0"){
// 		alert("Please Enter RAM Slot Quantity");
// 		$("#ram_slot_qty").focus();
// 		return false;
// 	}
	 
	 var warrantycheckChecked=$('input[name="warrantycheck"]:checked').val();
		
		if(warrantycheckChecked.toUpperCase()=="YES"){
			if($("#warranty").val()==0 || $("#warranty").val()=="" || $("#warranty").val() == "DD/MM/YYYY"){
				alert("Please Select Warranty Upto ");
				$("#warranty").focus();
				return false;
			}
		}
		else{
			$("#warranty").val('');
		}
		
	 
		
	
	if (!$("input[name='cd_drive']").is(':checked')) {
		   alert('Please Check CD Drive!');
		}
	
	
	
	
	
var len=$("#assetcount").val();
	
	var i=1;
	for(i;i<=len;i++)
		{
// 		if( $("#model_number"+i).val().trim() == ""){
// 			alert("Please Enter Model Number ");
// 			$("#model_number"+i).focus();
// 			return false;
// 		}
		
		if( $("#machine_number"+i).val().trim() == ""){
			alert("Please Enter Machine No. ");
			$("#machine_number"+i).focus();
			return false;
		}
		
		
		
		
		 function validateCode() {
			   
			   
//	 		   var machine_number = $("#machine_number1").val();
			   
			   var  testString = $("input#machine_number1"+i).val();
				 if (/\d/.test(testString) && /[a-zA-Z]/.test(testString)) {
				
				 }
				 else{
					 alert("Please enter letters and numbers only.");
				 }
		   }
		 
		   
		   function validateCodeMac() {
			   
			   
//	 		   var machine_number = $("#model_number1").val();
			   
			   var  testString = $("input#mac_address"+i).val();
				 if (/\d/.test(testString) && /[a-zA-Z]/.test(testString)) {
				
				 }
				 else{
					 alert("Please enter letters and numbers only.");
				 }
		   }
		
		
// 		if( $("#mac_address"+i).val()==""){
// 			alert("Please Enter MAC Address");
// 			$("#mac_address"+i).focus();
// 			return false;
// 		}
		
// 	 	if(!ValidateMACAddress( $("#mac_address"+i).val())){
// 	 	alert("You have entered an invalid MAC Address!")  
// 		$("#mac_address"+i).focus();
// 		return false;
// 	 	}
	
// 		if( $("#ip_address1").val()!=""){
// 			 if(!ValidateIPaddress( $("#ip_address1").val())){
// 			 	alert("You have entered an invalid IP Address!")  
// 				$("#ip_address1").focus();
// 				return false;
// 			 }
// 			}
		
		
		for(j=i+1;j<=len;j++){
			if( $("#machine_number"+i).val() == $("#machine_number"+j).val()){
				alert("You have Entered Duplicate Machine Number!")  
				$("#machine_number"+j).focus();
				return false;
			}
		}
	
	}
	

	
// 	$('.machineno_class').each(function(i, obj) {
// 		$("#fromdate"+(i+1)).val(formateDate(obj.value));
// 		 });
	
	
	
	
	
	if( $("#b_cost").val()=="0" ){
		alert("Proc Cost Must be Greater Than Zero");
		$("#b_cost").focus();
		return false;
	} else if ($("#b_cost").val() > 1000000000) {
		alert("Proc Cost cannot be greater Than 100 Crores");
		$("#b_cost").focus();
		return false;
	}

	
	if( $("#s_state").val()=="" || $("#s_state").val()=="0"){
		alert("Please Select Serviceable State");
		$("#s_state").focus();
		return false;
	}
	
	
	if($("#s_state").val()==2){
		if( $("#unserviceable_state").val()=="" || $("#unserviceable_state").val()=="0"){
			alert("Please Select UN-Serviceable State");
			$("#unserviceable_state").focus();
			return false;
		}
		if($("#unsv_from_dt1").val()==0 || $("#unsv_from_dt1").val()=="" || $("#unsv_from_dt1").val() == "DD/MM/YYYY"){
			alert("Please Select UN-Serviceable From Date");
			$("#unsv_from_dt1").focus();
			return false;
		}
	
}
	if( $("#proc_date").val() == "" || $("#proc_date").val() == "DD/MM/YYYY"){
		alert("Please Select Proc Date");
		$("#proc_date").focus();
		return false;
	}

	if( $("#b_head").val()=="0"){
		alert("Please Select Budget Head");
		$("#b_head").focus();
		return false;
	}
	
	if( $("#b_code").val()=="0"){
		alert("Please Select Budget Code");
		$("#b_code").focus();
		return false;
	}
	

	 
	if($("#proc_date").val()==0 || $("#proc_date").val()=="" || $("#proc_date").val() == "DD/MM/YYYY"){
		alert("Please enter Procurement date");
		$("#proc_date").focus();
		return false;
	}		

	
}






 function divN(obj){
 	var id = obj.id;
 	 var sib_id = $("#"+id).parent().parent().next().attr('id');
 	var hasC=$("#"+sib_id).hasClass("show");
 		$(".panel-collapse").removeClass("show");
 		 $('.droparrow').each(function(i, obj) {
 			 $("#"+obj.id).attr("class", "droparrow collapsed");
 			 });
 	
 		
 		if (hasC) {	
 		$("#"+id).addClass( " collapsed");		 
 		}  else {				
 			$("#"+sib_id).addClass("show");	
 			$("#"+id).removeClass("collapsed");
 	    }
 		
 		
 }
 function fn_makeName() {
		
		
		var assets_name = $("select#asset_type").val();
		var selectedOptionText = $("select#asset_type option:selected").text();
		
		$.post("getMakeNameList?" + key + "=" + value, {
			assets_name: assets_name
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#make_name").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 
 function fn_modelName() {
		
		var make_name = $("select#make_name").val();
		
		$.post("getModelNameList?" + key + "=" + value, {
			make_name: make_name
		}).done(function(j) {
		
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#model_name").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 
 function serviceStChange()
 {
	
	 var a =$("select#s_state").val();
	 if(a == '2')
		 {
		    $("#un_show").show();
		    $("#unsv_div").show();
		 }
	 else
		 {
		 $("#un_show").hide();
		 $("#unsv_div").hide();
		 $("#file_show").hide();
		 $('#unserviceable_state').val('0');
// 		 alert( $('#unserviceable_state').val('0'));
		 }
 }
 
 function anti_show()
 {
	 if ($("#antiviruscheck1").prop("checked")) {
		 $("#AntiVirusDiv").show();
			}
		else{
			$('#AntiVirusDiv').hide();
			$('#antivirus').val('0');
		}
	
 }
 
 function fn_B_Head() {
		
		
		var b_head = $("select#b_head").val();
		$.post("getBudgetCodeList?" + key + "=" + value, {
			b_head: b_head
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#b_code").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
 
 
 
 function counthover(){
	 Assetcount = $("#assetcount").val();
	 if (Assetcount=="" ) {
		 $("#assetcount").val("1");
		}
 }
 
 var Assetcount = 1;
 function addMultiAsset() {	 		
		Assetcount = $("#assetcount").val();
		if (Assetcount=="0" ) {
			$("#assetcount").val("1");
			Assetcount = 1;
// 			return false;
		}
		if (Assetcount=="" ) {
			Assetcount = 1;
		}
		
			var options="";
			for(var i = 1; i <= parseInt(Assetcount); i++) {
				options += '<div class="row" style="border: solid #b7b6b0 5px;padding: 10px;margin: 5px;">'+
						'<input type="text" id="ch_id'+i+'" name="ch_id'+i+'" class="form-control autocomplete" autocomplete="off" style="display: none;"></input>'+
						'<div class="col-md-12"><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
							'<label class=" form-control-label"><strong style="color: red;"> </strong>Model Number</label>	</div>'+
						'<div class="col-md-8">'+
						'<input type="text" id="model_number'+i+'" name="model_number'+i+'" class="form-control autocomplete" oninput="this.value = this.value.toUpperCase()"  onkeypress="return onlyAlphaNumeric(event, this)" maxlength="20"></input>'+
						'</div></div></div><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
						'	<label class=" form-control-label"><strong style="color: red;">* </strong>Machine No.</label>'+
						'</div><div class="col-md-8">'+
						'	<input type="text" class="form-control autocomplete" id="machine_number'+i+'"  onchange="return validateCode();" name="machine_number'+i+'" maxlength="50" oninput="this.value = this.value.toUpperCase()"  onkeypress="return onlyAlphaNumeric(event, this)" maxlength="20"></input>'+
						'</div></div></div></div><div class="col-md-12"><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
						'	<label class=" form-control-label"><strong style="color: red;"></strong>MAC Address</label>'+
						'</div><div class="col-md-8">'+
						'	<input title="Enter MAC Address in given format  8C-EC-4B-48-01-00. Type getmac in command prompt and press Enter to get MAC Address." type="text" id="mac_address'+i+'" name="mac_address'+i+'"  onchange="return validateCodeMac();" class="form-control autocomplete" oninput="this.value = this.value.toUpperCase()" onkeyup="makeMacAddress(this);" onkeypress="makeMacAddress(this);return /[0-9a-fA-F]/i.test(event.key);return onlyAlphaNumeric(event, this);" maxlength="17" autocomplete="off"></input>'+
						'</div></div></div><div class="col-md-6"><div class="row form-group"><div class="col-md-4">'+
						'	<label class=" form-control-label"><strong style="color: red;"> </strong>IP Address </label>'+
						'</div><div class="col-md-8">'+
						'	<input title="Enter IP Address in given format 192.168.151.191. Type ipconfig in command prompt and press Enter to get IP Address." type="text" id="ip_address'+i+'" name="ip_address'+i+'" maxlength="15" onkeypress="return /[.0-9]/i.test(event.key);" class="form-control autocomplete" autocomplete="off"></input>'+
						'</div></div></div></div></div>';


			}
			$("#multiAssetDiv").html(options);
	} 
 
 
 

	

	function warrenty_show()
	{
		 if ($("#Warranty1").prop("checked")) {
			 $("#WarrantyDiv").show();
				}
			else{
				$('#WarrantyDiv').hide();
				$('#warranty').val('DD/MM/YYYY');
			}
		
	}
 
	
	
	
 
 function onlyAlphabets(e, t) {
	    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
	}
	
	function onlyAlphaNumeric(e, t) {	
	    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || (e.charCode >= 48 && e.charCode <= 57 ) || e.charCode == 32;   
	}
	function isNumber(evt) {
		evt = (evt) ? evt : window.event;
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
		}
		return true;
		}
	
	
	
	function searchMachineNumber(){
	
		var machine_number = $("#machine_number1").val();
		$.post("getAllMachineNumberComputing?" + key + "=" + value, {
			machine_number: machine_number
		}).done(function(j) {
						
	 		 if(j.length > 0){
	 			 
	 			 alert("This Machine No already Exist.");
	 			$("#machine_number1").val("");
	 		 } 		 
		}).fail(function(xhr, textStatus, errorThrown) {}); 

	}
	
	 function fn_brand_other() {
		 var text = $("#make_name option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#brand_other_hid").show();
			}
			else{
				$("#brand_other_hid").hide();
				$("#brand_other").val('');
			}
	 	}
	 
	 function fn_make_other() {
		 var text = $("#model_name option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#model_other_hid").show();
			}
			else{
				$("#model_other_hid").hide();
				$("#model_other").val('');
			}
	 	}
	 function fn_pro_type_other() {
		 var text = $("#processor_type option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#pro_type_other_hid").show();
			}
			else{
				$("#pro_type_other_hid").hide();
				$("#pro_type_other").val('');
			}
	 	}
	 function fn_os_other() {
		 var text = $("#operating_system option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#os_other_hid").show();
			}
			else{
				$("#os_other_hid").hide();
				$("#os_other").val('');
			}
	 	}
	 function fn_office_other() {
		 var text = $("#office option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#office_other_hid").show();
			}
			else{
				$("#office_other_hid").hide();
				$("#office_other").val('');
			}
	 	}
	 

	 
	   function validateCode() {
		   
		   
// 		   var machine_number = $("#model_number1").val();
		   
		   var  testString = $("input#machine_number1").val();
			 if (/\d/.test(testString) && /[a-zA-Z]/.test(testString)) {
			
			 }
			 else{
				 alert("Please enter letters and numbers only.");
			 }
	   }
	 
	   
	   function validateCodeMac() {
		   
		   
// 		   var machine_number = $("#model_number1").val();
		   
		   var  testString = $("input#mac_address1").val();
			 if (/\d/.test(testString) && /[a-zA-Z]/.test(testString)) {
			
			 }
			 else{
				 alert("Please enter letters and numbers only.");
			 }
	   }
	   
	   
	   
	   
	   
	   
		function searchMakeOther(){
		
			var asset_type = $("#asset_type").val();
			var brand_other = $("#brand_other").val();
			$.post("getBrandOtherList?" + key + "=" + value, {
				asset_type:asset_type,brand_other: brand_other
			}).done(function(j) {
							
		 		 if(j.length > 0){
		 			 
		 			 alert("This Make Name Already Exists.");
		 			$("#brand_other").val("");
		 		 } 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
		
		
		
		function searchModelOther(){

			var asset_type = $("#asset_type").val();
			var model_other = $("#model_other").val();
			$.post("getModelOtherList?" + key + "=" + value, {
				asset_type:asset_type,model_other: model_other
			}).done(function(j) {
							
		 		 if(j.length > 0){
		 			 
		 			 alert("This Model Name Already Exists.");
		 			$("#model_other").val("");
		 		 } 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
		
		
		function searchProcessorTypeOther(){
		
			var pro_type_other = $("#pro_type_other").val();
			$.post("getProcessorTypeOtherList?" + key + "=" + value, {
				pro_type_other:pro_type_other
			}).done(function(j) {
							
		 		 if(j.length > 0){
		 			 
		 			 alert("This ProcessorType Already Exists.");
		 			$("#pro_type_other").val("");
		 		 } 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
	   
		
		function searchOperatingSystemTypeOther(){
			
			var os_other = $("#os_other").val();
			$.post("getOperatingSystemOtherList?" + key + "=" + value, {
				os_other:os_other
			}).done(function(j) {
							
		 		 if(j.length > 0){
		 			 
		 			 alert("This OperatingSystem Already Exists.");
		 			$("#os_other").val("");
		 		 } 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
		
		
	function searchOfficeTypeOther(){
	
			var office_other = $("#office_other").val();
			$.post("getOfficeOtherList?" + key + "=" + value, {
				office_other:office_other
			}).done(function(j) {
							
		 		 if(j.length > 0){
		 			 
		 			 alert("This Office Already Exists.");
		 			$("#office_other").val("");
		 		 } 		 
			}).fail(function(xhr, textStatus, errorThrown) {}); 

		}
		
		
	 function selectBer()
	 {
	
		 var a =$("select#unserviceable_state").val();
		 var b =$("select#s_state").val();
		 if(a == '1' && b == '2')
			 {
			   
			    $("#file_show").show();
			 }
		 else
			 {
			 $("#file_show").hide();
			 }
	 }
	 
	 
	 
	 
	 
	 /////For File Download
	 
	 
	 
	 function download_file() {
		
			var id = $("#id").val(); 
			
			var pdfView="BERFileDownloadDemo?id="+id;

				
			    fileName="TEST_DOC";
			    fileURL=pdfView;
			    if (!window.ActiveXObject) {
			        var save = document.createElement('a');
			        save.href = fileURL;
			        save.target = '_blank';
			        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
			        save.download = fileName || filename;
				       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
							document.location = save.href; 
						}else{
					        var evt = new MouseEvent('click', {
					            'view': window,
					            'bubbles': true,
					            'cancelable': false
					        });
					        save.dispatchEvent(evt);
					        (window.URL || window.webkitURL).revokeObjectURL(save.href);
						}	
			    }
			    else if ( !! window.ActiveXObject && document.execCommand)     {
			        var _window = window.open(fileURL, '_blank');
			        _window.document.close();
			        _window.document.execCommand('SaveAs', true, fileName || fileURL)
			        _window.close();
			    }

			
			
			location.reload();
		}
	 
	 
	 
	 
 </script>
 
 <script>
	
		// Unit SUS No

		$("#unit_sus_no").keyup(function(){
			var sus_no = this.value;
			var susNoAuto=$("#unit_sus_no");

			susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
		                  var length = data.length-1;
		                  var enc = data[length].substring(0,16);
		                        for(var i = 0;i<data.length;i++){
		                                susval.push(dec(enc,data[i]));
		                        }
		                        var dataCountry1 = susval.join("|");
		                        var myResponse = []; 
						        var autoTextVal=susNoAuto.val();
						$.each(dataCountry1.toString().split("|"), function(i,e){
							var newE = e.substring(0, autoTextVal.length);
							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
							  myResponse.push(e);
							}
						});      	          
						response( myResponse ); 
			          }
			        });
			      },
				  minLength: 1,
			      autoFill: true,
			      change: function(event, ui) {
			    	 if (ui.item) {   	        	  
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit SUS No.");
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			    }, 
				select: function( event, ui ) {
					var sus_no = ui.item.value;			      	
					 $.post("getTargetUnitNameList?"+key+"="+value, {
						 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#unit_name").val(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
		         });
				} 	     
			});	
		});

		// unit name
		 $("input#unit_name").keypress(function(){
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
					        	  alert("Please Enter Approved Unit Name.");
					        	  document.getElementById("unit_name").value="";
					        	  susNoAuto.val("");	        	  
					        	  susNoAuto.focus();
					        	  return false;	             
					          }   	         
					      }, 
					      select: function( event, ui ) {
					    	 var unit_name = ui.item.value;
					    
						            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
						            }).done(function(data) {
						            	var length = data.length-1;
							        	var enc = data[length].substring(0,16);
							        	$("#unit_sus_no").val(dec(enc,data[0]));
						            }).fail(function(xhr, textStatus, errorThrown) {
						            });
					      } 	     
					}); 			
			});
		
		
		 function printDiv() {
			    
			    // Get the values from the input fields
			    var lfdDate1Value = document.getElementById('lfdDate').value;
			    var adoa1Value = document.getElementById('adoa').value;
			    var rff1Value = document.getElementById('rff').value;
			    var dolcaDate1Value = document.getElementById('dolcaDate').value;
			    
			    var toa1Text = '';
			    var toa1Select = document.getElementById('toa');
			    if (toa1Select && toa1Select.selectedIndex > -1) {
			        toa1Text = toa1Select.options[toa1Select.selectedIndex].text;
			    }
				
			    var adban1Value = document.getElementById('adban').value;

			    var typeUR1Text = '';
			    var typeUR1Select = document.getElementById('typeUR');
			    if(typeUR1Select && typeUR1Select.selectedIndex > -1){
			        typeUR1Text = typeUR1Select.options[typeUR1Select.selectedIndex].text;
			    }

			    var urDate1Value = document.getElementById('urDate').value;
			    
			    var compUp1Text = '';
			    var compUp1Select = document.getElementById('compUp');
			    if(compUp1Select && compUp1Select.selectedIndex > -1){
			       compUp1Text = compUp1Select.options[compUp1Select.selectedIndex].text;
			    }

			    var othComp1Value = document.getElementById('othComp').value;
			    
			    var makeNameText = '';
				var makeNameSelect = document.getElementById('make_name');
				if(makeNameSelect && makeNameSelect.selectedIndex > -1){
					makeNameText = makeNameSelect.options[makeNameSelect.selectedIndex].text;
			    }
				
			    var brandOtherValue = document.getElementById('brand_other').value;
			    
			    var modelNameText = '';
				var modelNameSelect = document.getElementById('model_name');
				if(modelNameSelect && modelNameSelect.selectedIndex > -1){
					modelNameText = modelNameSelect.options[modelNameSelect.selectedIndex].text;
			    }
			    
			    var modelOtherValue = document.getElementById('model_other').value;
			    var brandOtherDivDisplay = document.getElementById('brand_other_hid').style.display;
			    var modelOtherDivDisplay = document.getElementById('model_other_hid').style.display;
			    
			    
			    var makeNameValue = document.getElementById('make_name').value;
				var makeNameText = document.getElementById('make_name').options[document.getElementById('make_name').selectedIndex].text;
			    var brandOtherValue = document.getElementById('brand_other').value;
			    var modelNameValue = document.getElementById('model_name').value;
				var modelNameText = document.getElementById('model_name').options[document.getElementById('model_name').selectedIndex].text;
			    var modelOtherValue = document.getElementById('model_other').value;
			    var brandOtherDivDisplay = document.getElementById('brand_other_hid').style.display;
			    var modelOtherDivDisplay = document.getElementById('model_other_hid').style.display;
			    var modelNumber1Value = document.getElementById('model_number1').value;
				var machineNumber1Value = document.getElementById('machine_number1').value;
				var macAddress1Value = document.getElementById('mac_address1').value;
				var ipAddress1Value = document.getElementById('ip_address1').value;
				
				// Get date and time
				var now = new Date();
				var formattedDate = now.toLocaleDateString();
				var formattedTime = now.toLocaleTimeString();
				var session_username = document.getElementById('session_username').value;
				
				// Determine the heading based on the 'id' value
			    var idElement = document.getElementById('id');
			    var headingText = '';
			    if (idElement && idElement.value == 0) {
			        var saveHeader = document.getElementById('save');
			        headingText = saveHeader ? saveHeader.textContent : 'COMPUTING ASSETS DETAILS';
			    } else {
			        var updateHeader = document.getElementById('update');
			        headingText = updateHeader ? updateHeader.textContent : 'COMPUTING ASSETS DETAILS';
			    }
				
			    popupWindow = window
			            .open(
			                    '',
			                    '_blank',
			                    'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
			    popupWindow.document.open();
			    popupWindow.document
			            .write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;} .col-md-12 {display: inline-flex;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'
								+ '<div class="watermark" style="position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%) rotate(-45deg); font-size: 4em; color: rgba(0, 0, 0, 0.2); pointer-events: none; white-space: nowrap;">'
								+ session_username + ' ' + formattedDate + ' ' + formattedTime
								+ '</div>'
			                    + '<tr><td align="left" style="padding-right:50px;"><img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;"></td><td align="center"><h4 style="text-decoration: underline;font-size: 22px;"></h4></td>'
			                    + '<td ><img src="js/miso/images/dgis-logo.png" style="margin-top:-56px;float:right; max-width: 155px; height: 50px;"> </td></tr>'
			                    + '<h5 style="text-decoration: underline;text-align:center;"><b>RESTRICTED</b></h5>'
			                    + '<h1 style="font-size:28px; text-align:center;">' + headingText + '</h1><br> '
			                	// NEW ADDITION - Make/Model Details
								+ '<div class="col-md-12">'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Make/Brand Name</strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>' + makeNameText + '</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ (brandOtherDivDisplay === 'none' ? '' :
								   '<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Make/Brand Other</strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>' + brandOtherValue + '</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>' )
								+ '</div>'
								+ '<div class="col-md-12">'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Model Name</strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>' + modelNameText + '</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ (modelOtherDivDisplay === 'none' ? '' :
								   '<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Model Other</strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>' + modelOtherValue + '</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>' )
								+ '</div>'
								//NEW ADDITION - Hardware Details
								+ '<div class="col-md-12">'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Model Number: </strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ modelNumber1Value +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Machine No.: </strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ machineNumber1Value +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '</div>'
								+ '<div class="col-md-12">'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>MAC Address: </strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ macAddress1Value +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>IP Address: </strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ ipAddress1Value +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '</div>'
			                 // Previous details
			                 	+ '<h5>FORMATTING DETAILS</h5>'
								+ '<div class="col-md-12">'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Last Format Done: </strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ lfdDate1Value +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Authority (Details Of Autority): </strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ adoa1Value +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '</div>'
								+ '<div class="col-md-12">'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Reason For Formatting: </strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ rff1Value +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '</div>'
								//New Details
								+ '<h5>CYBER AUDIT DETAILS</h5>'
								+ '<div class="col-md-12">'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Date Of Last Cyber Audit: </strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ dolcaDate1Value +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Type Of Audit: </strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ toa1Text +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '</div>'
								+ '<div class="col-md-12">'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Audit Done By (Agency Name): </strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ adban1Value +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '</div>'
								// New Addition
								+ '<h5>PC LOG BOOK</h5>'
								+ '<div class="col-md-12">'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Type</strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>' + typeUR1Text + '</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Date Of Upgradation/Repair</strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ urDate1Value +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+ '</div>'
								+ '<div class="col-md-12">'
								+ '	<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Component Upgraded</strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>' + compUp1Text + '</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>'
								+   (compUp1Select && compUp1Select.selectedIndex === '4' ?
									'<div class="col-md-6">'
								+ '		<div class="row form-group">'
								+ '			<div class="col-md-4">'
								+ '				<label class=" form-control-label"><strong>Other Component</strong></label>'
								+ '			</div>'
								+ '			<div class="col-md-8">'
								+ '				<label>'+ othComp1Value +'</label>'
								+ '			</div>'
								+ '		</div>'
								+ '	</div>' : '' )
								+ '</div>'
								+ '</html>');
			    popupWindow.document.close();
			}

		</script>
 
 
 
