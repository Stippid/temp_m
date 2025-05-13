<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/ChkDateLessThan.js" type="text/javascript"></script>

<form:form name="Miso_Orbat_Unt_DtlForm" id="Miso_Orbat_Unt_DtlForm" action="misoOrbatUntDtlAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="misoOrbatUntDtlCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container">
	    	<div class="row" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5><b>NEW RAISING DETAILS</b><br></h5><h6><span style="font-size:12px;color:red;">(To be entered by SD Dte / MISO)</span></h6><strong>Schedule Details</strong> </div>
	          			<div class="card-body card-block">
	          				<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label"><strong style="color: red;">*</strong> GOI Letter No</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="goi_letter_no" name="goi_letter_no" maxlength="250" placeholder="GOI Letter No" class="form-control">
	                					</div>
	              					</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label"><strong style="color: red;">*</strong> Auth Letter No</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="letter_no" name="letter_no" maxlength="250" placeholder="Auth Letter No" class="form-control">
	                					</div>
              						</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> GOI Letter Date</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="date" id="date_goi_letter" name="date_goi_letter" class="form-control" max='${date}'>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Letter Date</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="date" id="sanction_date" name="sanction_date" class="form-control"  max='${date}'>
										</div>
	  								</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-5">
	                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Upload GOI Letter</label>
	                					</div>
	                					<div class="col-12 col-md-7">
	                  						<input type="file" id="upload_goi_letter" maxlength="250" name="upload_goi_letter" class="form-control">
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-5">
	                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Upload Auth Letter</label>
	                					</div>
	                					<div class="col-12 col-md-7">
	                  						<input type="file" id="upload_auth_letter" maxlength="250" name="upload_auth_letter" class="form-control">
										</div>
	  								</div>
								</div>
							</div>
	            		</div>
						<div class="card-header" style="border: 1px solid rgba(0,0,0,.125);"> <strong>Details Of The Unit </strong></div>
								<div class="card-body card-block">
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
		                						<div class="col col-md-4">
		                  							<label class=" form-control-label"><strong style="color: red;">*</strong> Entity</label>
		                						</div>
		                						<div class="col-12 col-md-8">
		                 							<select name="level_in_hierarchy" id="level_in_hierarchy" class="form-control-sm form-control">
									                    <option value="0">Please select</option>
									                    <option value="Command">Command</option>
									                    <option value="Corps">Corps</option>
									                    <option value="Division">Division</option>
									                    <option value="Brigade">Brigade</option>
									                    <option value="Unit">Unit</option>
									               	</select>
		                						</div>
		              						</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
							                	<div class="col col-md-4">
							                	  <label class=" form-control-label"><strong style="color: red;">*</strong> Name Of Entity</label>
							                	</div>
							                	<div class="col-12 col-md-8">
							                	  <input type="text" id="unit_name" name="unit_name" maxlength="100" class="form-control" autocomplete="off">
							                	</div>
							              	</div>
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
												<div class="col col-md-6">
													<label for="text-input" class=" form-control-label">Unit Under Army HQ</label>
												</div>
												<div class="col col-md-6">
													<div class="row form-group">
														<div class="col-md-5" align="center">
															<input type="radio" id="inline-radio1" name="unit_army_hq" value="Y" >&emsp;Yes
														</div>
														<div class="col-md-5" align="center">
															<input type="radio" id="inline-radio2" name="unit_army_hq" value="N" >&emsp; No
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
		                						<div class="col col-md-4">
		                  							<label class=" form-control-label"><strong style="color: red;">*</strong> Op Comd</label>
		                						</div>
										        <div class="col-12 col-md-8">
										        	<select id="op_comd" name="op_comd" class="form-control">
										        		<option value="">--Select--</option>
					                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
										        	</select>
									            </div>
										    </div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
								                <div class="col col-md-4">
								                  <label class=" form-control-label">Op Corps</label>
								                </div>
								                <div class="col-12 col-md-8">
				                 					<select id="op_corps" name="op_corps" class="form-control">
					                                    <option value="0">--Select--</option>
				                                	</select>
				                				</div>
							              	</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
										    	<div class="col col-md-4">
										        	<label class=" form-control-label">Op Div</label>
										      	</div>
										        <div class="col-12 col-md-8">
										        	<select id="op_div" name="op_div" class="form-control">
					                                   <option value="0">--Select--</option>
										          	</select>
										    	</div>
		              						</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
								                <div class="col col-md-4">
								                  <label class=" form-control-label">Op Bde</label>
								                </div>
					                			<div class="col-12 col-md-8">
					                 				<select id="op_bde" name="op_bde" class="form-control">
					                                    <option value="0">--Select--</option>
					                                </select>
					                			</div>
					              			</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
										    	<div class="col col-md-4">
										        	<label class=" form-control-label"><strong style="color: red;">*</strong> Cont Comd</label>
										       	</div>
										        <div class="col-12 col-md-8">
										            <select id="cont_comd" name="cont_comd" class="form-control">
						                                <option value="">--Select--</option>
					                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
						                        	</select>
										    	</div>
										  	</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
					                			<div class="col col-md-4">
					                  				<label class=" form-control-label">Cont Corps</label>
					               				</div>
					                			<div class="col-12 col-md-8">
					                 				<select id="cont_corps" name="cont_corps" class="form-control">
					                                   <option value="0">--Select--</option>
					                                </select>
					                			</div>
					              			</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
								                <div class="col col-md-4">
								                  <label class=" form-control-label">Cont Div</label>
								                </div>
								                <div class="col-12 col-md-8">
								                 	<select id="cont_div" name="cont_div" class="form-control">
					                                    <option value="0">--Select--</option>
				                               		</select>
								                </div>
								            </div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
				                				<div class="col col-md-4">
				                  					<label class=" form-control-label">Cont Bde</label>
				                				</div>
				                				<div class="col-12 col-md-8">
				                 					<select id="cont_bde" name="cont_bde" class="form-control">
				                                    	<option value="0">--Select--</option>
				                                	</select>
				                				</div>
				              				</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
								                <div class="col col-md-4">
								                  <label class=" form-control-label"><strong style="color: red;">*</strong> Adm Comd</label>
								                </div>
								                <div class="col-12 col-md-8">
								                 	<select id="adm_comd" name="adm_comd" class="form-control">
					                                    <option value="">--Select--</option>
					                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
			                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
			                  							</c:forEach>
					                                </select>
								                </div>
								           	</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
				                				<div class="col col-md-4">
				                  					<label class=" form-control-label">Adm Corps</label>
				                				</div>
				                				<div class="col-12 col-md-8">
				                 					<select id="adm_corps" name="adm_corps" class="form-control">
				                                    	<option value="0">--Select--</option>
					                                </select>
				                				</div>
				              				</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
								                <div class="col col-md-4">
								                  <label class=" form-control-label">Adm Div</label>
								                </div>
								                <div class="col-12 col-md-8">
								                 	<select  id="adm_div" name="adm_div" class="form-control">
					                                    <option value="0">--Select--</option>
					                                </select>
					                			</div>
	            							</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
				                				<div class="col col-md-4">
				                  					<label class=" form-control-label">Adm Bde</label>
				                				</div>
				                				<div class="col-12 col-md-8">
				                 					<select id="adm_bde" name="adm_bde" class="form-control">
				                                    	<option value="0">--Select--</option>
						                          	</select>
				                				</div>
				              				</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
								                <div class="col col-md-4">
								                  <label class=" form-control-label"><strong style="color: red;">*</strong> Parent Arm</label>
								                </div>
								                <div class="col-12 col-md-8">
								                 	<select id="parent_arm" name="parent_arm" class="form-control">
					                                    <option value="0">--Select--</option>
					                                    <c:forEach var="item" items="${getPrantArmList}" varStatus="num" >
			                  								<option value="${item.code}">${item.code} - ${item.code_value}</option>
			                  							</c:forEach>
					                                </select>
	              								</div>
	            							</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
				                				<div class="col col-md-4">
				                  					<label class=" form-control-label"><strong style="color: red;">*</strong> Type Of Arm</label>
				                				</div>
				                				<div class="col-12 col-md-8">
				                 					<select id="type_of_arm" name="type_of_arm" class="form-control">
				                                    	 <option value="0">--Select--</option>
					                                </select>
				                				</div>
				              				</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
								                <div class="col col-md-4">
								                  <label class=" form-control-label"><strong style="color: red;">*</strong> Arm Name</label>
								                </div>
	              								<div class="col-12 col-md-8">
	               									<select id="arm_name" name="arm_name"  class="form-control">
					                                	<option value="0">--Select--</option>
					                               		<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
			                  								<option value="${item.arm_code}" >${item.arm_code} - ${item.arm_desc}</option>
			                  							</c:forEach>
					                                </select>
	              								</div>              								
	         								</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
				                				<div class="col col-md-4">
				                  					<label class=" form-control-label"><strong style="color: red;">*</strong> Arm Code</label>
				                				</div>
				                				<div class="col-12 col-md-8">
				                  					<input type="text" id="arm_code" name="arm_code" maxlength="4"  class="form-control" autocomplete="off" readonly="readonly">
				                				</div>
				              				</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
              									<div class="col col-md-4">
              										<label for="selectLg" class=" form-control-label">Type Of Unit</label>
              									</div>
           										<div class="col-12 col-md-8">
                									<select name="type_force" id="type_force"  class="form-control-sm form-control">
								                    	<option value="">--Select--</option>
					                               		<c:forEach var="item" items="${getTypeOfUnitList}">
			                  								<option value="${item[0]}" >${item[0]} - ${item[1]}</option>
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
													<label for="text-input" class=" form-control-label">CT Part I/II</label>
												</div>
												<div class="col col-md-8">
													<div class="form-check-inline form-check">
														<label for="inline-radio1" class="form-check-label ">
															<input type="radio" id="radio1" name="ct_part_i_ii" maxlength="8" value="CTPartI" class="form-control form-check-input">CT Part I</label>&nbsp;&nbsp;&nbsp; 
														<label for="inline-radio2" class="form-check-label "> 
															<input type="radio" id="radio2" name="ct_part_i_ii" maxlength="8" value="CTPartII" class="form-control form-check-input">CT Part II</label> &nbsp;&nbsp;&nbsp; 
														<label for="inline-radio3" class="form-check-label "> 
															<input type="radio" id="radio3" name="ct_part_i_ii" maxlength="8" value="Others" class="form-control form-check-input">Others</label>
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
								                <div class="col col-md-4">
								                  	<label class=" form-control-label"><strong style="color: red;">*</strong> Loc</label>
								                </div>
								                <div class="col-12 col-md-8">
								                 	<!-- <select id="code" name="code" class="form-control"> </select>-->
								                 	<input type="hidden" id="code" name="code" maxlength="5" class="form-control" style="width:100%;" >
								                 	<input type="text" id="loc" name="loc" class="form-control" maxlength="400" style="width:86%;display: inline-block;" readonly="readonly">
						              				<i class="fa fa-search" onclick="openLocLOV();"></i>
	              								</div>
	            							</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
				                				<div class="col col-md-4">
				                  					<label class=" form-control-label">NRS</label>
				                				</div>
				                				<div class="col-12 col-md-8">
				                  					<input type="hidden" id="nrs_code" name="nrs_code" maxlength="5" class="form-control">
				                  					<input type="text" id="nrs_name" name="nrs_name" maxlength="400" class="form-control" autocomplete="off" readonly="readonly">
				                				</div>
				              				</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
								                <div class="col col-md-4">
								                  	<label class=" form-control-label"><strong style="color: red;">*</strong> Type Of Loc</label>
								                </div>
								                <div class="col-12 col-md-8">
								                  	<input type="text" id="type_of_location" name="type_of_location" maxlength="50" class="form-control" readonly="readonly">
								                </div>
							              	</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
				                				<div class="col col-md-4">
				                  					<label class=" form-control-label">Trn Type</label>
				                				</div>		
				                				<div class="col-12 col-md-8">
				                  					<input type="text" id="modification" name="modification" maxlength="100" class="form-control" readonly="readonly">
				                				</div>
				              				</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
							                	<div class="col col-md-4">
							                  		<label class=" form-control-label"><strong style="color: red;">*</strong> New Raising Date (From)</label>
							                	</div>
							                	<div class="col-12 col-md-8">
							                  		<input type="date" id="comm_depart_date" name="comm_depart_date" class="form-control">
							                	</div>
							              	</div>
										</div>
										<div class="col-md-6">
											<div class="row form-group">
				                				<div class="col col-md-4">
				                  					<label class=" form-control-label">New Raising Date (To)</label>
				                				</div>
				                				<div class="col-12 col-md-8">
				                  					<input type="date" id="compltn_arrv_date" name="compltn_arrv_date" onchange="return checkdate(this,comm_depart_date)" class="form-control" >
				                				</div>
				             				</div>
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="col-md-6">
											<div class="row form-group">
							                	<div class="col col-md-4">
							                  		<label class=" form-control-label">Address</label>
							                	</div>
							                	<div class="col-12 col-md-8">
							                 		<textarea class="form-control" id="address" name="address" maxlength="200"></textarea>
							                	</div>
							            	</div>
										</div>
									</div>
									<div class="col-md-5 col-md-offset-1">
						            	<div class="row form-group" style="display: none;">
			                				<div class="col col-md-12">
			                					<input type="text" id="cont_aname" name="cont_aname"  maxlength="100" class="form-control">
			                					<input type="text" id="cont_bname" name="cont_bname"  maxlength="100" class="form-control">
			                					<input type="text" id="cont_cname" name="cont_cname"  maxlength="100" class="form-control">
			                					<input type="text" id="cont_dname" name="cont_dname"  maxlength="100" class="form-control">
			                				</div>
			                			</div>
			                		</div>
								</div> 
						<div class="card-footer" align="center" class="form-control">
							<input type="reset" class="btn btn-success btn-sm" value="Clear">   
		              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();" >
		              	</div> 
	        	</div>
			</div>
		</div>
	</div>
</form:form>
<script>
function validate(){
	if($("#sanction_date").val() > '${date}'){
		alert("Please Don't enter Future Date");
		$("#sanction_date").focus();
		return false;
    }
	if($("#date_goi_letter").val() > '${date}'){
		alert("Please Don't enter Future Date");
		$("#date_goi_letter").focus();
		return false;
    }
	if($("#goi_letter_no").val() == ""){
		alert("Please Enter the GOI Letter No.");
		$("#goi_letter_no").focus();
		return false;
	}
	if($("#letter_no").val() == ""){
		alert("Please Enter the Auth Letter No.");
		$("#letter_no").focus();
		return false;
	}
	if($("#date_goi_letter").val() == ""){
		alert("Please Select Date of GOI Letter");
		$("#date_goi_letter").focus();
		return false;
    }
	if($("#sanction_date").val() == ""){
		alert("Please Select Date of Letter");
		$("#sanction_date").focus();
		return false;
    }
	if($("#upload_goi_letter").get(0).files.length === 0){
		alert("Please Upload Document of GOI Letter");
		$("#upload_goi_letter").focus();
		return false;
    } 
	if($("#upload_auth_letter").get(0).files.length === 0){
		alert("Please Upload Document of Auth Letter");
		$("#upload_auth_letter").focus();
		return false;
    } 
	if($("#level_in_hierarchy").val() == "0"){
		alert("Please Select Entity");
		$("#level_in_hierarchy").focus();
		return false;
    }
	if($("#unit_name").val() == ""){
		alert("Please Enter Name Of Unit");
		$("#unit_name").focus();
		return false;
    }
	var st;
	var unit_name1 = $("#unit_name").val();
	if(unit_name1 != ""){
		$.ajax({
			url : "checkifExistUnitName?"+key+"="+value,
			type : 'POST',
			data : {unit_name:unit_name1,sus_no:""},
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
	
	if($("#level_in_hierarchy").val() != "Command"){
		if($("#op_comd").val() == ""){
			alert("Please Select Operation Command");
			$("#op_comd").focus();
			return false;
	    }
		if($("#cont_comd").val() == ""){
			alert("Please Select Control Command");
			$("#cont_comd").focus();
			return false;
	    }
		if($("#adm_comd").val() == ""){
			alert("Please Select Admin Command");
			$("#adm_comd").focus();
			return false;
	    }
    }
	
	if($("#parent_arm").val() == "0"){
		alert("Please Select Parent Arm");
		$("#parent_arm").focus();
		return false;
    }
	if($("#type_of_arm").val() == "0"){
		alert("Please Select Type of Arm");
		$("#type_of_arm").focus();
		return false;
    }
	if($("#arm_name").val() == "0"){
		alert("Please Select Arm Name");
		$("#arm_name").focus();
		return false;
    }
	if($("#code").val() == ""){
		alert("Please Select Loc");
		$("#code").focus();
		return false;
    }
	if($("#type_of_location").val() == ""){
		alert("Please Enter Type of Loc");
		$("#type_of_location").focus();
		return false;
    }
	if($("#comm_depart_date").val() == ""){
		alert("Please Select Commencement Date (DD-MM-YYYY)");
		$("#comm_depart_date").focus();
		return false;
    }
	
	var from = document.getElementById("comm_depart_date").value;
	var to = document.getElementById("compltn_arrv_date").value;
	if ((Date.parse(from) > Date.parse(to))) {
		alert('You cannot select To date less than From Date.');
		document.getElementById("compltn_arrv_date").focus();
		return false;
	}
	
	$("#op_comd").attr('disabled',false).css("background-color", "white");
	$("#op_corps").attr('disabled',false).css("background-color", "white");
	$("#op_div").attr('disabled',false).css("background-color", "white");
	$("#op_bde").attr('disabled',false).css("background-color", "white");
	
	$("#cont_comd").attr('disabled',false).css("background-color", "white");
	$("#cont_corps").attr('disabled',false).css("background-color", "white");
	$("#cont_div").attr('disabled',false).css("background-color", "white");
	$("#cont_bde").attr('disabled',false).css("background-color", "white");
	
	$("#adm_comd").attr('disabled',false).css("background-color", "white");
	$("#adm_corps").attr('disabled',false).css("background-color", "white");
	$("#adm_div").attr('disabled',false).css("background-color", "white");
	$("#adm_bde").attr('disabled',false).css("background-color", "white");
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
	
	
    $('select#parent_arm').change(function() {
    	var code = 0;
       	code = this.value;    	   	
        $.post("getTypeOfArmArmList?"+key+"="+value,{code:code}, function(j) {
        	var length = j.length-1;
        	var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
        	for ( var i = 0; i < length ;i++) {
				options += '<option value="' + dec(j[length][0],j[i][0]) + '" >'+ dec(j[length][0],j[i][0]) +" - "+  dec(j[length][0],j[i][1]) + '</option>';
			}	
			$("select#type_of_arm").html(options); 
		}); 
	});
	    
    $('select#arm_name').change(function() {
           $("#arm_code").val(this.value);
   	});
   	    
	$('select#level_in_hierarchy').change(function() {
        var value = this.value;
       	if(value == "Command"){
       		$("#op_comd").attr('disabled',true).css("background-color", "grey");
       		$("#op_corps").attr('disabled',true).css("background-color", "grey");
			$("#op_div").attr('disabled',true).css("background-color", "grey");
			$("#op_bde").attr('disabled',true).css("background-color", "grey");
			
			$("#cont_comd").attr('disabled',true).css("background-color", "grey");
			$("#cont_corps").attr('disabled',true).css("background-color", "grey");
			$("#cont_div").attr('disabled',true).css("background-color", "grey");
			$("#cont_bde").attr('disabled',true).css("background-color", "grey");
			
			$("#adm_comd").attr('disabled',true).css("background-color", "grey");
			$("#adm_corps").attr('disabled',true).css("background-color", "grey");
			$("#adm_div").attr('disabled',true).css("background-color", "grey");
			$("#adm_bde").attr('disabled',true).css("background-color", "grey");
		}
		if(value == "Corps"){
			$("#op_comd").attr('disabled',false).css("background-color", "white");
		        		$("#op_corps").attr('disabled',true).css("background-color", "grey");
			$("#op_div").attr('disabled',true).css("background-color", "grey");
			$("#op_bde").attr('disabled',true).css("background-color", "grey");
			
			$("#cont_comd").attr('disabled',false).css("background-color", "white");
			$("#cont_corps").attr('disabled',true).css("background-color", "grey");
			$("#cont_div").attr('disabled',true).css("background-color", "grey");
			$("#cont_bde").attr('disabled',true).css("background-color", "grey");
			
			$("#adm_comd").attr('disabled',false).css("background-color", "white");
			$("#adm_corps").attr('disabled',true).css("background-color", "grey");
			$("#adm_div").attr('disabled',true).css("background-color", "grey");
			$("#adm_bde").attr('disabled',true).css("background-color", "grey");
		}
		if(value == "Division"){
			$("#op_comd").attr('disabled',false).css("background-color", "white");
		        		$("#op_corps").attr('disabled',false).css("background-color", "white");
			$("#op_div").attr('disabled',true).css("background-color", "grey");
			$("#op_bde").attr('disabled',true).css("background-color", "grey");
			
			$("#cont_comd").attr('disabled',false).css("background-color", "white");
		        		$("#cont_corps").attr('disabled',false).css("background-color", "white");
			$("#cont_div").attr('disabled',true).css("background-color", "grey");
			$("#cont_bde").attr('disabled',true).css("background-color", "grey");
			
			$("#adm_comd").attr('disabled',false).css("background-color", "white");
		        		$("#adm_corps").attr('disabled',false).css("background-color", "white");
			$("#adm_div").attr('disabled',true).css("background-color", "grey");
			$("#adm_bde").attr('disabled',true).css("background-color", "grey");
		}
		if(value == "Brigade"){
			$("#op_comd").attr('disabled',false).css("background-color", "white");
		        		$("#op_corps").attr('disabled',false).css("background-color", "white");
			$("#op_div").attr('disabled',false).css("background-color", "white");
			$("#op_bde").attr('disabled',true).css("background-color", "grey");
			
			$("#cont_comd").attr('disabled',false).css("background-color", "white");
		        		$("#cont_corps").attr('disabled',false).css("background-color", "white");
			$("#cont_div").attr('disabled',false).css("background-color", "white");
			$("#cont_bde").attr('disabled',true).css("background-color", "grey");
			
			$("#adm_comd").attr('disabled',false).css("background-color", "white");
		        		$("#adm_corps").attr('disabled',false).css("background-color", "white");
			$("#adm_div").attr('disabled',false).css("background-color", "white");
			$("#adm_bde").attr('disabled',true).css("background-color", "grey");
		}
		if(value == "Unit"){
			$("#op_comd").attr('disabled',false).css("background-color", "white");
			$("#op_corps").attr('disabled',false).css("background-color", "white");
			$("#op_div").attr('disabled',false).css("background-color", "white");
			$("#op_bde").attr('disabled',false).css("background-color", "white");
			
			$("#cont_comd").attr('disabled',false).css("background-color", "white");
			$("#cont_corps").attr('disabled',false).css("background-color", "white");
			$("#cont_div").attr('disabled',false).css("background-color", "white");
			$("#cont_bde").attr('disabled',false).css("background-color", "white");
			
			$("#adm_comd").attr('disabled',false).css("background-color", "white");
			$("#adm_corps").attr('disabled',false).css("background-color", "white");
			$("#adm_div").attr('disabled',false).css("background-color", "white");
			$("#adm_bde").attr('disabled',false).css("background-color", "white");
		}
	});
});
       
var popupWindow = null
function openLocLOV(url) {
	popupWindow = window.open("locationLOV", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=900,height=600,fullscreen=no");
}
function HandlePopupResult(loc,nrs_name,loc_code,trn_type,typeofloc,nrscode) {
    $("#code").val(loc_code);
	$("#nrs_name").val(nrs_name);
	$("#modification").val(trn_type);
	$("#type_of_location").val(typeofloc);
	$("#loc").val(loc);
	$("#nrs_code").val(nrscode);
}
function parent_disable() {
	if(popupWindow && !popupWindow.closed)
		popupWindow.focus();
}
</script>

<script>
$(document).ready(function() { 	
	var ddd = '${date}';
	$("#sanction_date").change(function(){
		if($("#sanction_date").val() > '${date}'){
			$("#sanction_date").val("");
			alert("You Can't select Future Date!");
			$("#sanction_date").focus();
			return false;
		}
	});	
	$("#date_goi_letter").change(function(){
		if($("#date_goi_letter").val() > '${date}'){
			$("#date_goi_letter").val("");
			alert("You Can't select Future Date!");
			$("#date_goi_letter").focus();
			return false;
		}
	});	
});	
</script>