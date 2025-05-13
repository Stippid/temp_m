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
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>


<form:form name="ViewAssets" id="ViewAssets" action="ViewAssetsAction"
	method="post" class="form-horizontal" commandName="ViewAssetsCmd">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>VIEW COMPUTING ASSETS</h5>
				</div>
				<form:hidden id="id" path="id" class="form-control autocomplete"
					autocomplete="off"></form:hidden>
				<div class="card-body card-block" id="printableArea">
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
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Model
											Number :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmodel_number">${ViewAssetsCmd.model_number}</label>
								</div>
							</div>
						</div>
					</div>
					
					
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
									<label class=" form-control-label"><strong>Machine
											No. :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblmachine_number">${ViewAssetsCmd.machine_number}</label>
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
									<label id="lblmac_address">${ViewAssetsCmd.mac_address}</label>
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
									<label id="lblip_address">${ViewAssetsCmd.ip_address}</label>
								</div>
							</div>
						</div>
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
					<div class="col-md-6" id = "pro_type_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                   <strong>Processor
											Type Other:</strong></label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()" -->
<!-- 					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete"  -->
<!-- 					                	autocomplete="off" maxlength="50" > -->
					                	
					                	<label id="lblprocessor_other">${pro_type_other}</label>
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
				
					
					
					<div class="col-md-6" id = "os_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                  <label class=" form-control-label"><strong>Operating
											System :</strong></label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()" -->
<!-- 					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete"  -->
<!-- 					                	autocomplete="off" maxlength="50" > -->
					                	
					                	<label id="lblos_other">${os_mstr_other}</label>
					                	 </div>
						            </div>
	              				</div>
	              					<div class="col-md-6" id = "office_other_hid" style="display: none;">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                  <label class=" form-control-label"><strong>Office
											Other :</strong></label>
						                </div>
						                <div class="col-md-8">
<!-- 					                	<input type="text" id="brand_other" name="brand_other" oninput="this.value = this.value.toUpperCase()" -->
<!-- 					                	onkeypress="return onlyAlphaNumeric(event);" onchange="searchMakeOther();" class="form-control autocomplete"  -->
<!-- 					                	autocomplete="off" maxlength="50" > -->
					                	
					                	<label id="lbloffice_other">${office_mstr_other}</label>
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
						<div class="col-md-6" id="AntiVirusDiv">
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
									<label class=" form-control-label"><strong>OS/Firmware Activation and
										subsequent Patch Updation Mechanism :</strong></label>
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
									<label class=" form-control-label"><strong>Warranty
											:</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblwarrantycheck">${ViewAssetsCmd.warrantycheck}</label>


								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Warranty
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
									<label class=" form-control-label"><strong>Serviceable
											State :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lbls_state"></label>
									<form:select path="s_state" id="s_state" class="form-control"
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
									<label class=" form-control-label"><strong>Budget
											Head :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_head"></label>
									<form:select path="b_head" id="b_head" name="b_head"
										class="form-control" style="display:none;"
										onchange="fn_bHead();">

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
									<label class=" form-control-label"><strong>Proc
											Cost :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblb_cost" style="word-break: break-all";>${ViewAssetsCmd.b_cost}</label>
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
					

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Ram
											Slot Quantity :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblpart_rem_slot_qty">${ViewAssetsCmd.ram_slot_qty}</label>

								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Sus
											No :</strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblsus_no">${ViewAssetsCmd.sus_no}</label>

								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong>Proc
											Date : </strong></label>
								</div>
								<div class="col-md-8">
									<label id="lblproc_date">${ViewAssetsCmd.proc_date}</label>

								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Search_Computing_AssetsUrl" class="btn btn-success btn-sm">Back</a>
					<i class="fa fa-download"></i><input type="button"
						class="btn btn-primary btn-sm" value="Print Page" id="btn_p"
						onclick="printDiv();">

				</div>
			</div>
		</div>
	</div>

</form:form>

<script>
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

	function fn_bHead() {

		
		var b_head = $("select#b_head").val();
		$
				.post("getBudgetCodeList?" + key + "=" + value, {
					b_head : b_head
				})
				.done(
						function(j) {

							var options = '';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >'
										+ j[i][1] + '</option>';
							}
							$("select#b_code").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
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
	function printDiv() {
		
		let innerContents = document.getElementById('printableArea').innerHTML;
		popupWindow = window
				.open(
						'',
						'_blank',
						'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWindow.document.open();
		popupWindow.document
				.write('<html><head><link rel="stylesheet" href="js/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;} .col-md-12 {display: inline-flex;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'
						+ '<tr><td align="left" style="padding-right:50px;"><img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;"></td><td align="center"><h4 style="text-decoration: underline;font-size: 22px;"></h4></td>'
						+ '<td ><img src="js/miso/images/dgis-logo.png" style="margin-top:-56px;float:right; max-width: 155px; height: 50px;"> </td></tr>'
						+ '<h5 style="text-decoration: underline;text-align:center;"><b>RESTRICTED</b></h5>'
						+ '<h1 style="font-size:28px; text-align:center;">VIEW COMPUTING ASSETS DETAILS</h1><br> '
						+ innerContents + '</html>');
		popupWindow.document.close();

		
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
	 
	 
	 
	 
	$(document).ready(function() {

		
		
		$("#asset_type").val("${ViewAssetsCmd.asset_type}");
		var asset_type = $("#asset_type option:selected").text();
		$("#s_state").val('${ViewAssetsCmd.s_state}');
		var asset_type = $("#asset_type option:selected").text();
		
		fn_brand_other();
		var make_name = $("#make_name option:selected").text();
		fn_make_other();
		
		var model_name = $("#model_name option:selected").text();
		var processor_type = $("#processor_type option:selected").text();
		fn_pro_type_other();
		var ram_capi = $("#ram_capi option:selected").text();
		var hdd_capi = $("#hdd_capi option:selected").text();
		
		fn_os_other();
		var operating_system = $("#operating_system option:selected").text();
		
		
		
		$("#office").val("${ViewAssetsCmd.office}");
		
		
		
		var office = $("#office option:selected").text();
		fn_office_other();
		$("#antivirus").val("${ViewAssetsCmd.antivirus}");
		var antivirus = $("#antivirus option:selected").text();
		var os_patch = $("#os_patch option:selected").text();
		var dply_envt = $("#dply_envt option:selected").text();
	
		var s_state = $("#s_state option:selected").text();
		if(s_state=="Un-serviceable"){
			$("#uni").show();
			 $("#unsv_div").show();
		}
		
		selectBer();
		var unserviceable_state = $("#unserviceable_state option:selected").text();
		
		var unsv_from_dt1 = '${ViewAssetsCmd.unsv_from_dt}';
		$("#lblunsv_from_dt1").text(ParseDateColumncommission(unsv_from_dt1));
		
		var connecting_tech = $("#connecting_tech option:selected").text();
		var ethernet_port = $("#ethernet_port option:selected").text();

		var warrenty_dt = '${ViewAssetsCmd.warranty}';

		warrenty_dt = warrenty_dt.substring(0, 10);
		$("#lblwarranty").text(ParseDateColumncommission(warrenty_dt));

        
		$("#b_head").val("${ViewAssetsCmd.b_code}");
		var b_head = $("#b_head option:selected").text();
		$("#lblb_head").text(b_head);

		var b_code = $("#b_code option:selected").text();
		$("#lblb_code").text(b_code);

		var proc_date = '${ViewAssetsCmd.proc_date}';
		proc_date = proc_date.substring(0, 10);
		$("#lblproc_date").text(ParseDateColumncommission(proc_date));
		if(s_state=="Un-serviceable"){
			$("#uni").show();
		}
		
	

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
		$("#lblunserviceable_state").text(unserviceable_state);
		$("#lblconnecting_tech").text(connecting_tech);
		$("#lblethernet_port").text(ethernet_port);

		$("#scan").val("${ViewAssetsCmd.cd_drive}");
		
		var scan = $('input[name=cd_drive]').val();
		
		
		$("#lblscan").text(scan);
		
	$("#anti_virus").val("${ViewAssetsCmd.antiviruscheck}");
	var anti_virus = $('input[name=antiviruscheck]').val();
		
				$("#lblantiviruscheck").text(anti_virus);


	});
	
	
	
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
			var pdfView="BERFileDownloadDemo?id="+id;
			alert(pdfView)

				
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
