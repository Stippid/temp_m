
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
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<link rel="stylesheet" href="js/layout/css/animation.css">

<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<script>

var username="${username}";

</script>


<form:form name="MainAssets" id="MainAssets" action="updateAssetsAction"
	method="post" class="form-horizontal" commandName="AppMainAssetsCmd">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>EDIT IT CENSUS RETURN</h5>
				</div>
				<form:hidden id="id" path="id" class="form-control autocomplete"
					autocomplete="off"></form:hidden>
					
					<input type="hidden" id="session_username" name="session_username" class="form-control autocomplete" value="${session_username}">
					
				<input type="hidden" id="c_id" name="c_id"
					class="form-control autocomplete" autocomplete="off" />
				
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Computing
											Assets Type :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblAsset_type"></label>
									<form:select path="asset_type" id="asset_type"
										class="form-control" onchange="fn_makeName();"
										style="display: none;">

										<c:forEach var="item" items="${ComputingAssetList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					
					<div id="printableArea1">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong>Make
												Name :</strong></label>
									</div>
									<div class="col-md-8">
										<label id="lblmake_name"></label>
										<form:select path="make_name" id="make_name" name="make_name"
											class="form-control" onchange="fn_modelName();"
											style="display:none;">
											<c:forEach var="item" items="${MakeList}" varStatus="num">
												<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
											</c:forEach>
										</form:select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong>Model
												Name :</strong></label>
									</div>
									<div class="col-md-8">
										<label id="lblmodel_name"></label>
										<form:select path="model_name" id="model_name"
											class="form-control" style="display:none;">
											<c:forEach var="item" items="${ModelList}" varStatus="num">
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
										<label class=" form-control-label"><strong>Machine
												No. :</strong></label>
									</div>
									<div class="col-md-8">
										 <label id="lblmachine_number">${AppMainAssetsCmd.machine_number}</label>
										<%-- <form:input type="text" id="machine_number" path="machine_number" class="form-control autocomplete" autocomplete="off"></form:input> --%>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong>Mac
												Address :</strong></label>
									</div>
									<div class="col-md-8">
										<label id="lblmac_address">${AppMainAssetsCmd.mac_address}</label>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong>IP
												Address :</strong></label>
									</div>
									<div class="col-md-8">
										<label id="lblip_address">${AppMainAssetsCmd.ip_address}</label>
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong>Model
												Number :</strong></label>
									</div>
									<div class="col-md-8">
										  <label id="lblmodel_number">${AppMainAssetsCmd.model_number}</label> 
										 <%-- <form:input type="text" id="model_number" path="model_number" class="form-control autocomplete" autocomplete="off"></form:input> --%> 
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Processor
											Type :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblprocessor_type"></label>
									<form:select path="processor_type" id="processor_type"
										class="form-control" style="display:none;">

										<c:forEach var="item" items="${processor_typeList}"
											varStatus="num">
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
									<label class=" form-control-label"><strong>RAM
											Capacity :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblram_capi"></label>
									<form:select path="ram_capi" id="ram_capi" class="form-control"
										style="display:none;">

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
									<label class=" form-control-label"><strong>HDD
											Capacity :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblhdd_capi"></label>
									<form:select path="hdd_capi" id="hdd_capi" class="form-control"
										style="display:none;">

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
									<label class=" form-control-label"><strong>Operating
											System :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbloperating_system"></label>
									<form:select path="operating_system" id="operating_system"
										class="form-control" style="display:none;">

										<c:forEach var="item" items="${osList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Office
											:</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbloffice"></label>
									<form:select path="office" id="office" class="form-control"
										style="display:none;">

										<c:forEach var="item" items="${officeList}" varStatus="num">
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
									<label class=" form-control-label"><strong>AntiVirus
											Installed :</strong> </label>
								</div>
								<div class="col-md-8">
									<label id="lblantiviruscheck"></label>
									<form:radiobutton id="anti_virus" value="Yes" path="antiviruscheck"
										style="display:none;"></form:radiobutton>
									&nbsp <label for="yes" style="display: none;">Yes</label>&nbsp
									<form:radiobutton id="anti_virus" path="antiviruscheck" value="No"
										checked="checked" style="display:none;"></form:radiobutton>
									&nbsp <label for="no" style="display: none;">No</label>
								</div>
							</div>
						</div>
						<div class="col-md-6" id="">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>AntiVirus
											:</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblantivirus"></label>
									<form:select path="antivirus" id="antivirus"
										class="form-control" style="display:none;">

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
									<label class=" form-control-label"><strong>OS/Framware
											Activation and subsequent Patch Updation Mechanism :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblos_patch"></label>
									<form:select path="os_patch" id="os_patch" class="form-control"
										style="display:none;">

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
									<label class=" form-control-label"><strong>Dply
											Envt as Applicable :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbldply_envt"></label>
									<form:select path="dply_envt" id="dply_envt"
										class="form-control" style="display:none;">

										<c:forEach var="item" items="${dplyEnvList}" varStatus="num">
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
									<label class=" form-control-label"><strong>Ram
											Slot Quantity :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblpart_rem_slot_qty">${AppMainAssetsCmd.ram_slot_qty}</label>

								</div>
							</div>
						</div>
					</div>

<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Budget
											Head :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_head"></label>
									<form:select path="b_head" id="b_head" 
										class="form-control" style="display:none;">

										<c:forEach var="item" items="${getBudgetHeadList}"
											varStatus="num">
											<form:option value="${item[0]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Budget
											Code :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_code"></label>
									<form:select path="b_code" id="b_code" name="b_code"
										class="form-control" style="display:none;">
										<c:forEach var="item" items="${getBudgetCodeList}"
											varStatus="num">
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
									<label class=" form-control-label"><strong>Budget
											Cost :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_cost" >${AppMainAssetsCmd.b_cost}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>CD
											Drive :</strong></label>
								</div>
								<div class="col-md-8">
									<div class="col-md-8">
										<label id="lblscan"></label> <input type="radio" id="scan"
											value="Yes" name="cd_drive" style="display: none;" />&nbsp <label
											for="yes" style="display: none;">Yes</label>&nbsp <input
											type="radio" id="scan" name="cd_drive" value="No"
											checked="checked" style="display: none;" />&nbsp <label
											for="no" style="display: none;">No</label>
									</div>
								</div>
							</div>
						</div>
					</div>
<!-- 					HERE -->
<!-- 					<div class="col-md-12"> -->
					
<!-- 					<div class="col-md-6"> -->
<!-- 						<div class="row form-group"> -->
<!-- 							<div class="col-md-4"> -->
<!-- 								<label class=" form-control-label"><strong>Username :</strong></label> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-8"> -->
<%-- 								<label id="username_lb" >${AppMainAssetsCmd.usernameid}</label> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					
<!-- 					<div class="col-md-6"> -->
<!-- 						<div class="row form-group"> -->
<!-- 							<div class="col-md-4"> -->
<!-- 								<label class=" form-control-label"><strong>Domain Username :</strong></label> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-8"> -->
<%-- 								<label id="domain_lb" >${AppMainAssetsCmd.domain_username}</label> --%>
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
					
<!-- 					</div> -->

				</div>
				
				
					<div class="panel-group" id="accordion5">
					<div class="panel panel-default" id="a_div1">
						<div class="panel-heading">
							<h4 class="panel-title main_title red" id="a_div5">
								<a class="droparrow collapsed" data-toggle="collapse"
									data-parent="#accordion4" href="#" id="a_computing"
									onclick="divN(this)"><b>COMPUTING DETAILS</b></a>

							</h4>
						</div>
						
						
						<div id="collapsecomputing" class="panel-collapse collapse">
							<div class="card-body card-block">

					<div class="row">
					
					 <!---------21.05 pranay-------------------------------------------- -->  
					
										<div class="col-md-12">
										<c:if test="${username == 'admin1_it'}">
							<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label id="nonIpDisplay" class=" form-control-label"><strong
												style="color: red;"> </strong>Computing Assets Type : </label> <label
												id="ipDisplay" class=" form-control-label"
												style="display: none;"><strong style="color: red;">*
											</strong>Computing Assets Type : </label>
										</div>
										<div class="col-md-8">
										
										<c:if test="${username == 'admin1_it'}">
										<select name="asset_type" id="asset_type"
										class="form-control" >
										<c:forEach var="item" items="${ComputingAssetList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
										</select>
										</c:if>
									
										
										</div>
									</div>
								</div>
								
								<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Model Number : </label>
								</div>
								<div class="col-md-8">
								
									<c:if test="${username == 'admin1_it'}">
									<form:input type="text" id="model_number" path="model_number" class="form-control autocomplete" autocomplete="off"></form:input>
									</c:if>
								</div>
							</div>
						</div>
				</c:if>
					</div>
					
					
					
					<div class="col-md-12">
					<c:if test="${username == 'admin1_it'}">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Make
											Name :</label>
								</div>
								<div class="col-md-8">
									
									<select path="make_name" id="make_name" name="make_name"
										class="form-control" >
										<c:forEach var="item" items="${MakeList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</select>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Model
											Name :</label>
								</div>
								<div class="col-md-8">
									
									<select path="model_name" id="model_name"
										class="form-control">
										<c:forEach var="item" items="${ModelList}" varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</select>
									
								</div>
							</div>
						</div>
						</c:if>
					</div>
					
					
					<div class="col-md-12">
					<c:if test="${username == 'admin1_it'}">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Machine No. :</label>
								</div>
								<div class="col-md-8">
									 
									<form:input type="text" id="machine_number" path="machine_number" class="form-control autocomplete" autocomplete="off"></form:input>
									
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Mac Address :</label>
									
								</div>
								<div class="col-md-8">
								
									<form:input type="text" id="mac_address" path="mac_address" class="form-control autocomplete" autocomplete="off"></form:input>
								
								</div>
							</div>
						</div>
						</c:if>
					</div>
					
					<!------end21.05------------------------------------------------  --> 
					
					
					<div class="col-md-12">
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

											<input title="IP Adddress should be in the mentioned format."
												type="text" id="ip_address1" name="ip_address1"
												maxlength="15" class="form-control autocomplete"
												onchange="ValidateIPaddress(this);" autocomplete="off"></input>
										</div>
									</div>
								</div>
								
			 <!----------------- 21.05 pranay------------------------------>
								
								 <div class="col-md-6">
								 <c:if test="${username == 'admin1_it'}">
								<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Processor
											Type :</label>
								</div>
								 <div class="col-md-8">
										
									<select path="processor_type" id="processor_type"
										class="form-control">
										
										<c:forEach var="item" items="${processor_typeList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach> 
										
									</select>
									
								
									
								</div>
							</div> 
								
			
								
								</c:if>
						</div>
		<!-- end 21.05 ------------------------------> 
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>HDD Capacity </label>
								</div>
								<div class="col-md-8">
									<select name="hdd_capi1" id="hdd_capi1" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${hddList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>RAM Capacity </label>
								</div>
								<div class="col-md-8">
									<select name="ram_capi1" id="ram_capi1" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${ramList}" varStatus="num">
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
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>Operating System </label>
								</div>
								<div class="col-md-8">
									<select name="operating_system1" id="operating_system1"
										class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${osList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"> </strong>Office</label>
								</div>
								<div class="col-md-8">
									<select name="office1" id="office1" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${officeList}" varStatus="num">
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
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>AntiVirus Installed </label>
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
									<select name="antivirus1" id="antivirus1"
										class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${AntivirusList}" varStatus="num">
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
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>OS/Firmware Activation and
										subsequent Patch Updation Mechanism </label>
								</div>
								<div class="col-md-8">
									<select name="os_patch1" id="os_patch1" class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${osFirmwareList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
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
									<select name="dply_envt1" id="dply_envt1"
										class="form-control">
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${dplyEnvList}" varStatus="num">
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
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>RAM Slot Quantity</label>
								</div>
								<div class="col-md-8">
									<form:input type="number" id="ram_slot_qty" path="ram_slot_qty" onkeypress="return isNumber(event)"
										min="1" max="1000" class="form-control autocomplete"
										autocomplete="off"></form:input>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong>CD Drive</label>
								</div>
								<div class="col-md-8">
									
									<div class="col-md-8">
									<label >Yes</label> <input class=" " type="radio"
										 id="scan1" value="Yes"
										name="cd_drive1"> <label >No</label> <input
										class=" " type="radio"
										id="scan1" value="No" name="cd_drive1" checked>
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
														style="color: red;">* </strong>Budget Head</label>
												</div>
												<div class="col-md-8">
													<select name="b_head1" id="b_head1" class="form-control"
														onchange="fn_B_Head()">
														<option value="0">--Select--</option>
														<c:forEach var="item" items="${getBudgetHeadList}"
															varStatus="num">
															<option value="${item[1]}" name="${item[1]}">${item[1]}</option>
														</c:forEach>
													</select>
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
													<select name="b_code1" id="b_code1" class="form-control">
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
													<label class=" form-control-label"><strong
														style="color: red;"> </strong>Proc Cost</label>
												</div>
												<div class="col-md-8">
													<form:input type="text" id="b_cost" path="b_cost"
														onkeypress="return isNumber(event)"
														class="form-control autocomplete" autocomplete="off"></form:input>
												</div>
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
									onclick="divN(this)"><b>WARRANTY DETAILS</b></a>

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
														style="color: red;">* </strong>Warranty</label>
												</div>
												<div class="col-md-8" onclick="warrenty_show();">
													<form:radiobutton id="Warranty1" value="Yes"
														path="warrantycheck" checked="checked"></form:radiobutton>
													&nbsp <label for="yes" checked>Yes</label>&nbsp
													<form:radiobutton id="Warranty2" path="warrantycheck"
														value="No"></form:radiobutton>
													&nbsp <label for="no">No</label>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="WarrantyDiv">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label">Warranty</label>
												</div>
												<div class="col-md-8">
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
									data-parent="#accordion4" href="#" id="a_service"
									onclick="divN(this)"><b>SERVICEABLE DETAILS</b></a>
							</h4>
						</div>
						<div id="collapseservice" class="panel-collapse collapse">
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
													<select name="s_state" id="s_state" class="form-control"
														onchange="servicablechange();fn_Servicable(this);">
														<option value="0">--Select--</option>
														<option value="1">Serviceable</option>
														<option value="2">UN-Serviceable</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6" id="un_show" style="display: none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong
														style="color: red;">* </strong>UN-Serviceable State</label>
												</div>
												<div class="col-md-8">
													<form:select path="unserviceable_state"
														id="unserviceable_state" class="form-control">
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
									
									<!-- 							** BISAG AHM ** -->
							
							<div class="col-md-12">
							<div class="col-md-6"  id="un_show1" style="display:none;">
								<div class="row form-group">
									<div class="col-md-4" >
										<label class=" form-control-label"><strong style="color: red;">* </strong>UN-Servicable From Date</label>
									</div>
									<div class="col-md-8">
								<input type="text" name="unsv_from_dt1" id="unsv_from_dt1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
									
								</div>
									</div>
								</div>								
								<div class="col-md-6"  id="un_show2" style="display:none;">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>UN-Servicable To Date</label>
										</div>
											<div class="col-md-8">
 								<input type="text" name="unsv_to_dt1" id="unsv_to_dt1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY');" class="form-control" style="width: 94%;display: inline;"
 							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')"  
 							onchange="clickrecall(this,'DD/MM/YYYY'); fn_date_future(this);" " aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
									
 								</div> 
 									</div>
 								</div>
							</div>	
							
							<!-- 							** END BISAG AHM ** -->
									
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
									<div id="printableArea2">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label">Last Format Done</label>
													</div>
													<div class="col-md-8">
														<input type="text" name="lfdDate1" id="lfdDate1"
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
														<input type="text" id="adoa1" name="adoa1"
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
														<input type="text" id="rff1" name="rff1" class="form-control autocomplete" autocomplete="off"></input>
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
									<div id="printableArea3">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label">Date Of Last Cyber Audit</label>
													</div>
													<div class="col-md-8">
														<input type="text" name="dolcaDate1" id="dolcaDate1"
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
														<select name="toa1" id="toa1" class="form-control" >
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
														<input type="text" id="adban1" name="adban1" class="form-control autocomplete" autocomplete="off"></input>
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
				
<!-- 				PC LOG BOOK				 -->

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
									<div id="printableArea4">
										<div class="col-md-12">
											<div class="col-md-6">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label">Type</label>
													</div>
													<div class="col-md-8">
														<select name="typeUR1" id="typeUR1" class="form-control">
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
														<input type="text" name="urDate1" id="urDate1"
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
														<select name="compUp1" id="compUp1" class="form-control">
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
														<input type="text" id="othComp1" name="othComp1" class="form-control autocomplete" autocomplete="off"></input>
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
				
				
				

			</div>

			<div class="card-footer" align="center" class="form-control">
				<a href="SearchApp_Computing_AssetsUrl" class="btn btn-success btn-sm">Back</a>
				<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return Validate();">
				<i class="fa fa-download"></i><input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();">
			</div>
		</div>
	
	</div>


</form:form>
<script>
	$(document).ready(function() {
		servicablechange()
		$("#id").val('${e_id}')

	});
</script>
<script>
	function servicablechange() {
		var s_state = $("#s_state option:selected").text();

		if (s_state == "UN-Serviceable") {
			$("#un_show").show();
			$("#un_show1").show();
			$("#un_show2").hide();
		} else {
			$("#un_show").hide();
			$("#un_show1").hide();
		}
	}
	
	function fn_B_Head() {
		
		var b_head = $("select#b_head1").val();
		$.post("getBudgetCodeList1?" + key + "=" + value, {
			b_head: b_head
		}).done(function(j) {
			var options = '<option   value="0">' + "--Select--" + '</option>';
			for(var i = 0; i < j.length; i++) {
				options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
			}
			$("select#b_code1").html(options);
		}).fail(function(xhr, textStatus, errorThrown) {});
	}
</script>


<script>

//** BISAG AHM **//

function Validate(){
	
	
	
	if($("select#dply_envt1").val()==7 ||$("select#dply_envt1").val()==9 ){
	 	if( $("#ip_address1").val()==""){
		
		 	alert("Please Select IP Address ")  
			$("#ip_address1").focus();
			return false;
		 
	}
		
		
	}
	
	if($("#ram_capi1").val()==0 || $("#ram_capi1").val()==""){
		alert("Please Select RAM Capacity  ");
		$("#ram_capi1").focus();
		return false;
	}
	
	
	if($("#hdd_capi1").val()==0 || $("#hdd_capi1").val()==""){
		alert("Please Select HDD Capacity ");
		$("#hdd_capi1").focus();
		return false;
	}
	
	
	
	if($("#os_patch1").val()==0 || $("#os_patch1").val()==""){
		alert("Please Select OS/Firmware Activation and Subsequent Patch Updation Mechanism   ");
		$("#os_patch1").focus();
		return false;
	}
	
	if($("#dply_envt1").val()==0 || $("#dply_envt1").val()==""){
		alert("Please Select Dply Envt as Applicable  ");
		$("#dply_envt1").focus();
		return false;
	}
	
// 	if($("#s_state").val()==1){
// 		if ($("input#unsv_to_dt1").val() == "" || $("#unsv_to_dt1").val() == "DD/MM/YYYY") {
// 			alert("Please Select UN-Serviceable To Date");
// 			$("#unsv_to_dt1").focus();
// 			return false;
// 		}
		
// 	}

	if( $("#ram_slot_qty").val()=="" || $("#ram_slot_qty").val()=="0"){
		alert("Please Enter RAM Slot Quantity");
		$("#ram_slot_qty").focus();
		return false;
	}
	
	
	if (!$("input[name='cd_drive1']").is(':checked')) {
		   alert('Please Check CD Drive!');
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
	
	if($("#s_state").val()==2){
		if( $("#unserviceable_state").val()=="" || $("#unserviceable_state").val()=="0"){
			alert("Please Select UN-Serviceable State");
			$("#unserviceable_state").focus();
			return false;
		}
	}

	if($("#s_state").val()==2){
			if ($("input#unsv_from_dt1").val() == "" || $("#unsv_from_dt1").val() == "DD/MM/YYYY") {
			alert("Please Select UN-Serviceable From Date");
			$("#unsv_from_dt1").focus();
			return false;
		}
	}

}


function fn_Servicable(obj) {



if(obj.value == '1')
	{
	var p_id =$("input#id").val();
	var s_state =$("select#s_state").val();
	var unservice_state =$("select#unserviceable_state").val();
	var unsv_from_dt1 =$("input#unsv_from_dt1").val();
	
	$.post("getServicable?" + key + "=" + value, {
		p_id: p_id
	}).done(function(j) {
		
		if(j != "" && j != null){
		
			$("#un_show2").show();
		}
		else {
		
			$("#un_show2").hide();
		}
			
	});
	}
}

//** END BISAG AHM **//




	function divN(obj) {
		var id = obj.id;
		var sib_id = $("#" + id).parent().parent().next().attr('id');
		var hasC = $("#" + sib_id).hasClass("show");
		$(".panel-collapse").removeClass("show");
		$('.droparrow').each(function(i, obj) {
			$("#" + obj.id).attr("class", "droparrow collapsed");
		});

		if (hasC) {
			$("#" + id).addClass(" collapsed");
		} else {
			$("#" + sib_id).addClass("show");
			$("#" + id).removeClass("collapsed");
		}

	}

	function warrenty_show() {
		if ($("#Warranty1").prop("checked")) {
			$("#WarrantyDiv").show();
		
		} else {
			$('#WarrantyDiv').hide();
			$('#warranty').val('DD/MM/YYYY');
		}

	}

	function fn_makeName() {

		var assets_name = $("select#asset_type").val();
		$
				.post("getMakeNameList?" + key + "=" + value, {
					assets_name : assets_name
				})
				.done(
						function(j) {
							var options = '';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#make_name").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}

	function fn_modelName() {

		var make_name = $("select#make_name").val();
		$
				.post("getModelNameList?" + key + "=" + value, {
					make_name : make_name
				})
				.done(
						function(j) {

							var options = '';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#model_name").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
	
	


	$(document).ready(function() {
		datepicketDate('warranty');
		datepicketDate('unsv_from_dt1');
		datepicketDate('unsv_to_dt1');
		datepicketDate('lfdDate1');
		datepicketDate('dolcaDate1');
		datepicketDate('urDate1');
		 
		
		 $( "#warranty" ).datepicker( "option", "maxDate", null);
			
		 
	
		$("#asset_type").val("${AppMainAssetsCmd.asset_type}");
		var asset_type = $("#asset_type option:selected").text();
		$.ajaxSetup({
			async : false
		});

		
		fn_makeName(); 
		var make_name = $("#make_name option:selected").text();
		fn_modelName();
		var model_name = $("#model_name option:selected").text();
		var processor_type = $("#processor_type option:selected").text();
		var ram_capi = $("#ram_capi option:selected").text();
		var hdd_capi = $("#hdd_capi option:selected").text();
		var operating_system = $("#operating_system option:selected").text();
		var office = $("#office option:selected").text();

		$("#antivirus").val("${AppMainAssetsCmd.antivirus}");
		
		var antivirus = $("#antivirus option:selected").text();
		var os_patch = $("#os_patch option:selected").text();
		var dply_envt = $("#dply_envt option:selected").text();

		var connecting_tech = $("#connecting_tech option:selected").text();
		var ethernet_port = $("#ethernet_port:selected").text();
		var s_state = $("#s_state option:selected").text();
		
		
		
		$("#unsv_from_dt1").val("${AppMainAssetsCmd.unsv_from_dt}");
		$("#unsv_from_dt1").val(ParseDateColumncommission('${unsv_from_dt1}'))
		
		
		
		
		$("#lblAsset_type").text(asset_type);
		$("#lblmake_name").text(make_name);
		$("#lblmodel_name").text(model_name);
		$("#lblprocessor_type").text(processor_type);
		$("#lblram_capi").text(ram_capi);
		$("#lblhdd_capi").text(hdd_capi);
		$("#lbloperating_system").text(operating_system);
		$("#lbloffice").text(office);
		$("#lblantivirus").text(antivirus);
		$("#lblos_patch").text(os_patch);

		$("#lbldply_envt").text(dply_envt);
		$("#lbls_state").text(s_state);

		$("#lblconnecting_tech").text(connecting_tech);
		$("#lblethernet_port").text(ethernet_port);
		
		
	
		
		$("input[name='cd_drive'][value='${AppMainAssetsCmd.cd_drive}']").prop("checked",true);
		
		$("#scan").val("${AppMainAssetsCmd.cd_drive}");
		var scan = $('input[name=cd_drive]').val();
		$("#lblscan").text(scan);
		

		
		
		$("#lblb_head").text("${AppMainAssetsCmd.b_head}");
		
		$("select#b_code").val("${AppMainAssetsCmd.b_code}");
		var b_code = $("#b_code option:selected").text();
		$("#lblb_code").text(b_code);
		
		$("input[name='antiviruscheck'][value='${AppMainAssetsCmd.antiviruscheck}']").prop("checked",true);
		anti_show();
		$("#anti_virus").val("${AppMainAssetsCmd.antiviruscheck}");
			var anti_virus = $('input[name=antiviruscheck]').val();
		
				$("#lblantiviruscheck").text(anti_virus);

	
				
				
				$("select#operating_system").on("change", function() {
					if($("select#operating_system").val()==0){
						 $("#newLabel").hide();
					}
					else{
				    $("#newLabel").show();
					}
				    
				 
			    });
				
				
		$("#s_state").val('${service_state1}')
		servicablechange();
		$("#unserviceable_state").val('${unservice_state1}')
		$("#warranty").val(ParseDateColumncommission('${warranty1}'))
		$("#unsv_from_dt1").val(ParseDateColumncommission('${unsv_from_dt1}'))
 		$("#unsv_to_dt1").val(ParseDateColumncommission('${unsv_to_dt1}'))
		
		
		$("input[name='warrantycheck'][value='${AppMainAssetsCmd.warrantycheck}']").prop("checked", true);
		
		$("#c_id").val('${ch_id1}');
		if (parseInt('${ch_list_size}') > 0) {
			
			
			if('${ch_list[0].status}'==1){
			 	
		 		$("#c_id").val('0');
		 		}

			$("#s_state").val('${ch_list[0].service_state}');
			servicablechange();
			$("#unserviceable_state").val('${ch_list[0].unservice_state}');
			$("#warranty").val(ParseDateColumncommission('${ch_list[0].warranty}'));
			$("#unsv_from_dt1").val(ParseDateColumncommission('${ch_list[0].unsv_from_dt}'));
	 	  	 fn_Servicable(1);
	 	  	$("#unsv_to_dt1").val(ParseDateColumncommission('${ch_list[0].unsv_to_dt}'));
	 	  	

		} else {
			$("#c_id").val('0');

		}
	
		if('${ch_list[0].service_state}'==1 && ('${ch_list[0].unsv_to_dt}' != "" && '${ch_list[0].unsv_to_dt}' != null))
			{
				$("#un_show2").show();
			}
		
		
		
 		$("#ip_address1").tooltip();
		 
		 $("#ip_address1").css('cursor','pointer');
		 
		 $("#ip_address1").val('${AppMainAssetsCmd.ip_address}');
		
		 
		 $("select#ram_capi1").val("${AppMainAssetsCmd.ram_capi}");
		 
		 $("select#hdd_capi1").val("${AppMainAssetsCmd.hdd_capi}");
		 
		 $("select#operating_system1").val("${AppMainAssetsCmd.operating_system}");
		 
		 $("select#office1").val("${AppMainAssetsCmd.office}");
		 
		 $("#antivirus1").val("${AppMainAssetsCmd.antivirus}");
		 
		 $("select#os_patch1").val("${AppMainAssetsCmd.os_patch}");
		 
		 $("select#dply_envt1").val("${AppMainAssetsCmd.dply_envt}");
		 
	
		 
		 $("select#b_head1").val('${AppMainAssetsCmd.b_head}');
		 fn_B_Head();
			
			$("select#b_code1").val('${AppMainAssetsCmd.b_code}');
		
		 
		 
		 $("input[name='cd_drive1'][value='${AppMainAssetsCmd.cd_drive}']").prop("checked",true);
			var scan1 = $('input[name=cd_drive1]').val();
			$("#scan1").text(scan1);
			$("#adoa1").val('${AppMainAssetsCmd.details_of_auth}');
			$("#rff1").val('${AppMainAssetsCmd.reason_for_formatting}');
			$("#toa1").val('${AppMainAssetsCmd.type_of_audit}');
			$("#adban1").val('${AppMainAssetsCmd.audit_done_by}');
			$("#typeUR1").val('${AppMainAssetsCmd.type_upgrade_repair}');
			$("#compUp1").val('${AppMainAssetsCmd.comp_upgrade_repair}');
			$("#othComp1").val('${AppMainAssetsCmd.comp_other}');
			$("#lfdDate1").val((ParseDateColumncommission('${AppMainAssetsCmd.last_format_done}')));
			$("#dolcaDate1").val((ParseDateColumncommission('${AppMainAssetsCmd.last_cyber_audit_date}')));
			$("#urDate1").val((ParseDateColumncommission('${AppMainAssetsCmd.upgrade_repair_date}')));
		
		
		$("select#dply_envt1").on("change", function() {
			if($("select#dply_envt1").val()==7){
				 $("label#ipDisplay").show();
					$("label#nonIpDisplay").hide();

			}
			else if($("select#dply_envt1").val()==9){
				 $("label#ipDisplay").show();
					$("label#nonIpDisplay").hide();			}
			else{
				$("label#ipDisplay").hide();
				$("label#nonIpDisplay").show();
			}
			
		 
	    });

	});
	
	
	function ValidateIPaddress(ipaddress) {  
		var ip = new RegExp(/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/)
		  if (ip.test(ipaddress.value)) {  
		    return true;  
		   
		  }  
		  else{
		 	alert("You have entered an invalid IP Address!")  
			$("#ip_address1").focus();
		
			  return false;
		  }
		}
	
	 
	 function anti_show()
	 {
		
		 
		 if ($("#antiviruscheck1").prop("checked")) {
			 $("#AntiVirusDiv").show();
				}
			else{
				$('#AntiVirusDiv').hide();
				$('#antivirus1').val('0');
			}
		
	 }
	 
	 
	 function printDiv() {
		    var area1 = document.getElementById('printableArea1').innerHTML;

		    // Get the values from the input fields
		    var lfdDate1Value = document.getElementById('lfdDate1').value;
		    var adoa1Value = document.getElementById('adoa1').value;
		    var rff1Value = document.getElementById('rff1').value;
		    var dolcaDate1Value = document.getElementById('dolcaDate1').value;
		    
		    var toa1Text = '';
		    var toa1Select = document.getElementById('toa1');
		    if (toa1Select && toa1Select.selectedIndex > -1) {
		        toa1Text = toa1Select.options[toa1Select.selectedIndex].text;
		    }
		    
		    var adban1Value = document.getElementById('adban1').value;
		    
		    var typeUR1Text = '';
		    var typeUR1Select = document.getElementById('typeUR1');
		    if(typeUR1Select && typeUR1Select.selectedIndex > -1){
		        typeUR1Text = typeUR1Select.options[typeUR1Select.selectedIndex].text;
		    }
		    
		    var urDate1Value = document.getElementById('urDate1').value;
		    
		    var compUp1Text = '';
		    var compUp1Select = document.getElementById('compUp1');
		    if(compUp1Select && compUp1Select.selectedIndex > -1){
		       compUp1Text = compUp1Select.options[compUp1Select.selectedIndex].text;
		    }
		    
		    var othComp1Value = document.getElementById('othComp1').value;
			
		 	// Get date and time
			var now = new Date();
			var formattedDate = now.toLocaleDateString();
			var formattedTime = now.toLocaleTimeString();
			var session_username = document.getElementById('session_username').value;
		    
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
		                    + '<h1 style="font-size:28px; text-align:center;">EDIT COMPUTING ASSETS DETAILS</h1><br> '
		                    + area1
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
							+ '				<label class=" form-control-label"><strong>Type: </strong></label>'
							+ '			</div>'
							+ '			<div class="col-md-8">'
							+ '				<label>' + typeUR1Text + '</label>'
							+ '			</div>'
							+ '		</div>'
							+ '	</div>'
							+ '	<div class="col-md-6">'
							+ '		<div class="row form-group">'
							+ '			<div class="col-md-4">'
							+ '				<label class=" form-control-label"><strong>Date Of Upgradation/Repair: </strong></label>'
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
							+ '				<label class=" form-control-label"><strong>Component Upgraded: </strong></label>'
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
							+ '				<label class=" form-control-label"><strong>Other Component: </strong></label>'
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