<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>


<form:form name="ViewPeripheralAssets" id="ViewPeripheralAssets"
	action="ViewPeripheralAction" method="post" class="form-horizontal"
	commandName="ViewPeripheralCmd">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>VIEW PERIPHERAL ASSETS</h5>
				</div>
				<form:hidden id="id" path="id" class="form-control autocomplete"
					autocomplete="off"></form:hidden>
				<div class="card-body card-block" id="printableArea">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Peripheral Type</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblAsset_type"></label>
									<form:select path="assets_type" id="assets_type"
										onchange="fn_Peripheral();fn_makeName();" class="form-control"
										style="display: none;">

										<c:forEach var="item" items="${getPeripheral}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Type of HW</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbltype_of_hw"></label>
									<form:select path="type_of_hw" id="type_of_hw"
										onchange="fn_type();" class="form-control"
										style="display: none;">

										<c:forEach var="item" items="${hardwareListDDL}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
					<div class="col-md-6" id = "peri_hw_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
										style="color: red;"></strong><strong>Type of HW Other </strong></label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()" -->
<!-- 					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete"  -->
<!-- 					                	autocomplete="off" maxlength="50" > -->
					                	
					                	<label id="lblhw_other">${type_hw_mstr}</label>
					                	 </div>
						            </div>
	              				</div>
	              			
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Year of Proc</strong> </label>
								</div>
								<div class="col-md-8">
								
								<label id="lblyear_of_proc">${ViewPeripheralCmd.year_of_proc}</label>
								

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Year of
										Manufacturing </strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblyear_of_manufacturing">${ViewPeripheralCmd.year_of_manufacturing}</label>
									
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"><strong> Make Name</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmake_name"></label>
									<form:select path="make_name" id="make_name"
										class="form-control" style="display:none;">
										<c:forEach var="item" items="${getMakeName}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Model Name</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmodel_name"></label>
									<form:select path="model_name" id="model_name"
										class="form-control" style="display:none;">
										<c:forEach var="item" items="${getModelNameList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
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
										style="color: red;"></strong><strong>Make/Brand Other </strong></label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()" -->
<!-- 					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete"  -->
<!-- 					                	autocomplete="off" maxlength="50" > -->
					                	
					                	<label id="lblbrand_other">${make_mstr_other}</label>
					                	 </div>
						            </div>
	              				</div>
	              				<div class="col-md-6" id = "model_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
										style="color: red;"></strong><strong>Make/Brand Other </strong></label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()" -->
<!-- 					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete"  -->
<!-- 					                	autocomplete="off" maxlength="50" > -->
					                	
					                	<label id="lblmodel_other">${model_mstr_other}</label>
					                	 </div>
						            </div>
	              				</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Machine No.</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmachine_no" style="word-break: break-all;">${ViewPeripheralCmd.machine_no}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Model Number</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmodel_no" style="word-break: break-all;">${ViewPeripheralCmd.model_no}</label>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Remarks </strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblremarks">${ViewPeripheralCmd.remarks}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Type</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbltype"></label>
									<form:select path="type" id="type" class="form-control"
										style="display:none;">

										<c:forEach var="item" items="${Type1}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>
					</div>
		<div class="col-md-12">
					<div class="col-md-6" id = "model_type_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong
										style="color: red;"></strong><strong>Type Other </strong></label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()" -->
<!-- 					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete"  -->
<!-- 					                	autocomplete="off" maxlength="50" > -->
					                	
					                	<label id="lblmodel_type_other">${type_mstr}</label>
					                	 </div>
						            </div>
	              				</div>
	              			
					</div>
					<div class="col-md-12">
						<div class="col-md-6" id="up" style="display: none">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> UPS Capacity </strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblups_capacity"></label>
									<form:select path="ups_capacity" id="ups_capacity"
										class="form-control" style="display:none;">

										<c:forEach var="item" items="${UpsCapacity}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12">
						<div class="col-md-6" style="display: none;" id="mono">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Monochrome/Color</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmonochrome_color">${ViewPeripheralCmd.monochrome_color}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> IS Networked </strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblis_networked">${ViewPeripheralCmd.is_networked}</label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> IP Address(For
										Networked Printers Only)</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblip_address">${ViewPeripheralCmd.ip_address}</label>
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12" style="display: none;" id="pro">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Hardware Interface</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblhwinterface"></label>
									<div class="col-md-6" style="display: none;">
									
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Capacity(Lumens)</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblcapacity">${ViewPeripheralCmd.capacity}</label>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" style="display: none;" id="mf">
						<div class="col-md-2">
							<label class=" form-control-label"><strong> Features Avlb with
								MFD</strong></label>
						</div>
						<div class="1">
							<b><label><strong>Print</strong></label></b>
						</div>
						<div class="col-md-2">
							<label id="lblprint1">${ViewPeripheralCmd.print}</label> <input
								type="radio" id="print" value="Yes" name="print"
								style="display: none;" />&nbsp <label for="yes"
								style="display: none;">Yes</label>&nbsp <input type="radio"
								id="print" name="print" value="No" checked="checked"
								style="display: none;" />&nbsp <label for="no"
								style="display: none;">No</label>
						</div>
						<div class="1">
							<b><label><strong>Scan</strong></label></b>
						</div>
						<div class="col-md-2">
							<label id="lblscan1">${ViewPeripheralCmd.scan}</label> <input
								type="radio" id="scan" value="Yes" name="scan"
								style="display: none;" />&nbsp <label for="yes"
								style="display: none;">Yes</label>&nbsp <input type="radio"
								id="scan" name="scan" value="No" checked="checked"
								style="display: none;" />&nbsp <label for="no"
								style="display: none;">No</label>
						</div>

						<div class="1">
							<b><label><strong>Photography</strong></label></b>
						</div>
						<div class="col-md-2">
							<label id="lblphotography1">${ViewPeripheralCmd.photography}</label>
							<input type="radio" id="photography" value="Yes"
								name="photography" style="display: none;" />&nbsp <label
								for="yes" style="display: none;">Yes</label>&nbsp <input
								type="radio" id="photography" name="photography" value="No"
								style="display: none;" checked="checked" />&nbsp <label
								for="no" style="display: none;">No</label>
						</div>

						<div class="1">
							<b><label><strong>Colour</strong></label></b>
						</div>
						<div class="col-md-2">
							<label id="lblcolour1">${ViewPeripheralCmd.colour}</label> <input
								type="radio" id="colour" value="Yes" name="colour"
								style="display: none;">&nbsp <label for="yes"
								style="display: none;">Yes</label>&nbsp <input type="radio"
								id="colour" name="colour" value="No" style="display: none;"
								checked="checked" />&nbsp <label for="no"
								style="display: none;">No</label>
						</div>
					</div>
					<div class="col-md-12" style="display: none;" id="no">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Network Features</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_network_features"></label>
									
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> No Of Ports</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblno_of_ports">${ViewPeripheralCmd.no_of_ports}</label>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6" style="display: none;" id="max">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Max Paper Size</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblpaper_size"></label>
									<form:select path="paper_size" id="paper_size"
										class="form-control" style="display:none;">

										<c:forEach var="item" items="${getPaper_SizeList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>

								</div>
							</div>
						</div>
						<div class="col-md-6" style="display: none;" id="ty">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Display Type</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbldisplay_type">${ViewPeripheralCmd.display_type}</label>
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12" style="display: none;" id="vis">

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Display Size</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbldisplay_size">${ViewPeripheralCmd.display_size}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Display Connector</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbldisplay_connector"></label> <select
										name="display_connector" id="display_connector"
										class="form-control" style="display: none;">

										<option value="VGA">VGA</option>
										<option value="HDMI">HDMI</option>
										<option value="VGA&HDMI BOTH">VGA&HDMI BOTH</option>
									</select>

								</div>
							</div>
						</div>
					</div>



					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Warranty </strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblwarrantycheck">${ViewPeripheralCmd.warrantycheck}</label>

								</div>
							</div>
						</div>
                           <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Warranty Upto
											:</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblwarranty"></label>
								</div>
							</div>
						</div>
						
					</div>
					

										<div class="col-md-12">
											<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Serviceable State</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbls_state"></label> <form:select path="s_state" id="s_state" class="form-control"
										style="display:none;">
										<c:forEach var="item" items="${ServiceableList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</form:select>

								</div>
							</div>
						</div>
						<div class="col-md-6" style="display:none;" id="uni">
												<div class="row form-group">
													<div class="col-md-4">
														<label class=" form-control-label"><strong>UN-Serviceable State</strong></label>
													</div>
														<div class="col-md-8">
														<label id="lblunserviceable_state"></label>
					                						<form:select path="unserviceable_state" id="unserviceable_state" class="form-control" style="display: none;">

																 <c:forEach var="item" items="${UNServiceableList}" varStatus="num">
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
														style="color: red;">* </strong><strong>Upload Document</strong></label>
												</div>
												<div class="col-md-8">
<!-- 													<input type = "file" id="u_file1" name="u_file1" /> -->
													
													 <i class="fa fa-download"><input type="button" class="btn btn-success btn-sm"  value="File" onclick="download_file();" /></i>          
													
<!-- 													<label id="lbl_u_file_id"></label> -->
												</div>
											</div>
										</div>
									</div>
											<div class="col-md-12">
										<div class="col-md-6" id="unsv_div" style="display: none;">
											<div class="row form-group">
												<div class="col-md-4">
													<label class=" form-control-label"><strong>Un-servicable from </strong></label>
												</div>
										<label id="lblunsv_from_dt1"></label>
											</div>
										</div>
										</div>
										
							
										
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Budget Head</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_head">${ViewPeripheralCmd.b_head}</label>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Budget Code</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_code"></label>
									<form:select path="b_code" id="b_code" class="form-control"
										style="display:none;">
										<c:forEach var="item" items="${getBudgetCodeList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
									<label class=" form-control-label"><strong> Proc Cost</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_cost" style="word-break: break-all;">${ViewPeripheralCmd.b_cost}</label>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Proc Date</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_proc_date"></label>
									<select name="ethernet_port" id="ethernet_port"
										class="form-control" style="display: none;">

										<c:forEach var="item" items="${hw_interfaceList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>

					</div>
					<div class="col-md-12" style="display: none;" id=net>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Ethernet Port</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_ethernate_port">${ViewPeripheralCmd.ethernet_port}</label>
								<%-- 	<select name="ethernet_port" id="ethernet_port"
										class="form-control" style="display: none;">

										<c:forEach var="item" items="${hw_interfaceList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</select> --%>
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Management Layer</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_management_layer">${ViewPeripheralCmd.management_layer}</label>
									<select name="management_layer" id="management_layer"
										class="form-control" style="display: none;">

										<c:forEach var="item" items="${hw_interfaceList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6" style="display: none;" id="res">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Resolution</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblresolution"></label>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong> Sus No</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_sus_no">${ViewPeripheralCmd.sus_no}</label>
								</div>
							</div>
						</div>
						<div class="col-md-6" style="display: none;" id="con">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Connectivity Type</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblconnectivity_type"></label>
									<form:select path="connectivity_type" id="connectivity_type"
										class="form-control" style="display:none;">

										<c:forEach var="item" items="${getConnectivity_TypeList}"
											varStatus="num">
											<form:option value="${item[0]}" name="${item[1]}">${item[1]}</form:option>
										</c:forEach>
									</form:select>
								</div>
							</div>
						</div>


					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Search_PeripheralUrl" class="btn btn-success btn-sm">Back</a> <i
						class="fa fa-download "></i><input type="button"
						class="btn btn-primary btn-sm" value="Print Page" id="btn_p"
						onclick="printDiv();">


				</div>
			</div>
		</div>
	</div>

</form:form>

<script>
	$(document)
			.ready(
					function() {
						$("#assets_type").val(
								'${ViewPeripheralCmd.assets_type}');
						$("#make_name").val('${ViewPeripheralCmd.make_name}');
						$("#s_state").val('${ViewPeripheralCmd.s_state}');
						$("#model_name").val('${ViewPeripheralCmd.model_name}');
						$("#b_code").val('${ViewPeripheralCmd.b_code}');
						$("#type_of_hw").val('${ViewPeripheralCmd.type_of_hw}');
						$("#type").val('${ViewPeripheralCmd.type}');
						var asset_type = $("select#assets_type option:selected")
								.text();

						if (asset_type == 'UPS') {

							$("#up").show();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();
						} else if (asset_type == "PRINTERS") {

							$("#mono").show();
							$("#max").show();
							$("#con").show();

							$("#mf").hide();
							$("#up").hide();

						} else if (asset_type == 'MFD') {

							$("#mf").show();
							$("#up").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();

						}

						else if (asset_type == 'PROJECTION SYS') {

							$("#pro").show();
							$("#up").hide();
							$("#mf").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();

						} else if (asset_type == 'VISUALIZER') {

							$("#vis").show();
							$("#res").show();
							$("#pro").hide();
							$("#up").hide();
							$("#mf").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();

						} else if (asset_type == 'NETWORK COMPONENTS') {
							$("#net").show();
							$("#no").show();
							$("#vis").hide();
							$("#res").hide();
							$("#pro").hide();
							$("#up").hide();
							$("#mf").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();

						} else if (asset_type == 'MONITOR') {
							$("#ty").show();
							$("#net").hide();
							$("#no").hide();
							$("#vis").show();
							$("#res").hide();
							$("#pro").hide();
							$("#up").hide();
							$("#mf").hide();
							$("#mono").hide();
							$("#con").hide();
							$("#max").hide();

						}
					
						fn_brand_other();
						var make_name = $("#make_name option:selected").text();
						fn_make_other();
						var model_name = $("#model_name option:selected").text();
						var b_code = $("#b_code option:selected").text();
						var type_of_hw = $("#type_of_hw option:selected").text();
						fn_type_hw_other();

						var make_name = $("#make_name option:selected").text();
						var model_name = $("#model_name option:selected").text();
						var type = $("#type option:selected").text();
						fn_model_type_other();
						
						
						var ups_capacity = $("#ups_capacity option:selected").text();
						var paper_size = $("#paper_size option:selected").text();
						var display_connector = $("#display_connector option:selected").text();

// 						var hardware_interface = $("#hw_interface option:selected").text();
						var ethernet_port = $("#ethernet_port option:selected")
								.text();
						var management_layer = $(
								"#management_layer option:selected").text();
						var network_features = $(
								"#network_features option:selected").text();
						var v_display_connector = $(
								"#v_display_connector option:selected").text();

						var s_state = $("#s_state option:selected").text();
						if(s_state=="Un-serviceable"){
							$("#uni").show();
							 $("#unsv_div").show();
						}
						
						
						 selectBer();
						
						var unserviceable_state = $(
								"#unserviceable_state option:selected").text();
						
						var unsv_from_dt1 = '${ViewPeripheralCmd.unsv_from_dt}';
						$("#lblunsv_from_dt1").text(ParseDateColumncommission(unsv_from_dt1));
						
						
						var connectivity_type = $(
								"#connectivity_type option:selected").text();
						var budget_code = $("#b_code option:selected").text();

						var lblb_code = $("#b_code option:selected").text();

						$("#lblb_code").text(lblb_code);
						$("#lblAsset_type").text(asset_type);
						$("#lbltype_of_hw").text(type_of_hw);
						$("#lblmake_name").text(make_name);
						$("#lblmodel_name").text(model_name);
						$("#lbltype").text(type);
						$("#lblups_capacity").text(ups_capacity);
						$("#lblpaper_size").text(paper_size);
						$("#lbldisplay_connector").text(display_connector);

						$("#lbls_state").text(s_state);


						$("#lblunserviceable_state").text(unserviceable_state);
						var a='${hw_int_data[0][0]}'
						$("#lblhwinterface").text(a);
						var a='${ntwrk_ftr_data[0][0]}'
						$("#lblb_network_features").text(a);
						$("#lblv_display_connector").text(v_display_connector);
						$("#lblconnectivity_type").text(connectivity_type);

						var scan = $('input[name=scan]').val();
						$("#lblscan").text(scan);

						var print = $('input[name=print]').val();
						$("#lblprint").text(print);
						var colour = $('input[name=colour]').val();
						$("#lblcolour").text(colour);
						var photography = $('input[name=photography]').val();
						$("#lblphotography").text(photography);
						var print = $('input[name=print]').val();
						$("#lblprint").text(print);
						var print = $('input[name=print]').val();
						$("#lblprint").text(print);

						var lbl_warrenty = '${ViewPeripheralCmd.warranty}';
						wd = lbl_warrenty.substring(0, 10);
						$("#lblwarranty").text(ParseDateColumncommission(wd));

						var lblb_proc_dt = '${ViewPeripheralCmd.proc_date}';
						proc_dt = lblb_proc_dt.substring(0, 10);
						$("#lblb_proc_date").text(ParseDateColumncommission(proc_dt));

					});


	function printDiv() {
		//	$("#SearchViewtr").hide();
		//	$("#tdheaderView").show();
		//	$("#headerView").hide();
		//	$("#btn_e").hide();
		//	$("#btn_p").hide();
		//	$("#pId").hide();

		let innerContents = document.getElementById('printableArea').innerHTML;
		popupWindow = window
				.open(
						'',
						'_blank',
						'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWindow.document.open();
		popupWindow.document
				.write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;} .col-md-12 {display: inline-flex;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'
						+ innerContents + '</html>');
		popupWindow.document.close();
	}

	function ParseDateColumncommission(data) {

		var date = "";
		if (data != null && data != "") {
			var d = new Date(data);
			var day = ('0' + d.getDate()).slice(-2);
			var month = ('0' + (d.getMonth() + 1)).slice(-2);
			var year = "" + d.getFullYear();
			date = day + "/" + month + "/" + year;
		}
		return date;
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
	 
	
	 function fn_type_hw_other() {
		 var text = $("#type_of_hw option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#peri_hw_hid").show();
			}
			else{
				$("#peri_hw_hid").hide();
				$("#peri_hw_other").val('');
			}
	 	}
	 function fn_model_type_other() {
		 var text = $("#type option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#model_type_other_hid").show();
			}
			else{
				$("#model_type_other_hid").hide();
				$("#model_type_other").val('');
			}
	 	}
	 
	 
	 
	 function fn_connectivity_type_other() {
		 var text = $("#connectivity_type option:selected").text();
			
			if(text.toUpperCase() == "OTHERS"){
				$("#connect_other_hid").show();
			}
			else{
				$("#connect_other_hid").hide();
				$("#connectivity_other").val('');
			}
	 	}
	 
	 
	 
		/////For File Download
	 function selectBer()
		 {
			 
			 var a =$("select#unserviceable_state").val();
			 if(a == '1')
				 {
				   
				    $("#file_show").show();
				 }
			 else
				 {
				 $("#file_show").hide();
				 }
		 }
	 
	 
	 function download_file() {
		 
			var id = $("#id").val(); 
			var pdfView="BERFileDownloadDemoPeripheral?id="+id;

				
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



