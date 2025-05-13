<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<script type="text/javascript" src="js/Calender/jquery-ui.js" ></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/ChkDateLessThan.js" type="text/javascript"></script>

<form:form name="Miso_Orbat_Unt_DtlForm" id="Miso_Orbat_Unt_DtlForm" action="misoOrbatConversionAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="misoConverUntDtlCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
	    		<div class="card">
	    			<h5><b></b> </h5>
	          		<div class="card-header"><h5><b>Conversion/ Re-Designation / Re-Structure</b><br></h5><h6><span style="font-size:12px;color:red;">(To be entered by SD Dte / MISO)</span></h6><strong>Schedule Details</strong> </div>
	          		<div class="card-body card-block">
	          			<div class="col-md-12">
	          				<div class="col-md-6">
		          				<div class="row form-group">
	                					<div class="col col-md-4">
	                						<label class=" form-control-label"><strong style="color: red;">*</strong> GOI Letter No</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<textarea id="goi_letter_no" name="goi_letter_no" placeholder="GOI Letter No" maxlength="250" class="form-control" rows="1"></textarea>
	                					</div>
	              				</div>
	          				</div>
	          				<div class="col-md-6">
	          				<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">*</strong> Auth Letter No</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<textarea id="letter_no" name="letter_no" maxlength="250" placeholder="Auth Letter No" class="form-control" rows="1"></textarea>
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
                  						<input type="date" id="sanction_date" name="sanction_date" class="form-control" max='${date}'>
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
	          			<div class="col-md-12">
	          				<div class="col-md-6">
		          				<div class="row form-group">
	                				<div class="col col-md-4">
	                  					<label class=" form-control-label"><strong style="color: red;">*</strong> Scenario</label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                 					<select id="type_of_letter" name="type_of_letter" class="form-control-sm form-control" onchange="TypeOfLetterChange()"  style="width:50%;" >
	                 						<option value="0">--Select--</option>
	                 						<option value="1">Re-designation</option>
	                 						<option value="2">Conversion</option>
	                 						<option value="11">Re-Structure</option>
	                 					</select>
	                 				</div>
	              				</div>
	          				</div>
	          				<div class="col-md-6">
	          					<input type="button" class="btn btn-primary btn-sm" value="Reload" onclick="reloadPage();">   
	          				</div>
	          			</div>
	          		</div>
	          		<div class="card-header"  id="TypeOfLetterOnClickDiv" style="display: none;"> <strong>Unit Selection</strong>  </div>
					<div class="card-body card-block"  id="TypeOfLetterOnClickDiv"  style="display: none;">
	            		<div class="col-md-12">
			        		<div class="col-md-6">
			        			<div class="row form-group">
	                				<div class="col col-md-4">
	                					<label class=" form-control-label"><strong style="color: red;">*</strong> Source Unit Name</label>
	                				</div>
	                				<div class="col-12 col-md-8 autocomplete">
	                				   	<input type="text" id="source_unit_name" name="source_unit_name" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Active Unit" class="form-control autocomplete" autocomplete="off">
									</div>
	              				</div>
			        		</div>
			        		<div class="col-md-6">
		        				<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">*</strong> Source SUS No</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="source_sus_no" name="source_sus_no" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Active SUS NO" class="form-control autocomplete"  autocomplete="off">
                					</div>
              					</div>
			          		</div>
			          	</div>
			          	<div class="col-md-12" id="unit_nameDiv">
		          			<div class="col-md-6">
		          				<div class="row form-group">
                					<div class="col col-md-4">
                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Target Unit Name</label>
                					</div>
                					<div class="col-12 col-md-8">
                						
                  						<input type="text" id="unit_name" name="unit_name" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Target Unit Name" class="form-control autocomplete"  autocomplete="off">
									</div>
								</div>
		          			</div>
		          			<div class="col-md-6">
		          				<div class="row form-group">
                					<div class="col col-md-4">
                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Target SUS No</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="sus_no" name="sus_no" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Target SUS No" class="form-control autocomplete"  autocomplete="off">
									</div>
	  							</div>
		          			</div>
	          			</div>
			     	</div>
					<div class="card-header"  id="TypeOfLetterOnClickDiv" style="border: 1px solid rgba(0,0,0,.125); display: none;"> <strong>Details Of The Unit </strong></div>
						<div class="card-body card-block"  id="TypeOfLetterOnClickDiv"  style="display: none;">
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
											<!-- <input type="text" id="op_comd" name="op_comd" placeholder="" class="form-control"> -->
											<select id="op_comd" name="op_comd" class="form-control-sm form-control">
												<option value="">--Select--</option>
			                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
		                 							<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
		                 						</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group" >
										<div class="col col-md-4">
											<label class=" form-control-label">Op Corps</label>
										</div>
										<div class="col-12 col-md-8">
											<select id="op_corps" name="op_corps" class="form-control-sm form-control" >
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
								        	<select data-placeholder="Choose a Country..." id="op_div" name="op_div" class="form-control-sm form-control"  >
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
			                 				<select data-placeholder="Choose a Country..." id="op_bde" name="op_bde" class="form-control-sm form-control" >
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
								            <select id="cont_comd" name="cont_comd" class="form-control-sm form-control" >
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
					                 				<select data-placeholder="Choose a Country..." id="cont_corps" name="cont_corps" class="form-control-sm form-control" >
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
								                 	<select data-placeholder="Choose a Country..." id="cont_div" name="cont_div" class="form-control-sm form-control" >
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
	                 					<select data-placeholder="Choose a Country..." id="cont_bde" name="cont_bde" class="form-control-sm form-control" >
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
					                 	<select id="adm_comd" name="adm_comd" class="form-control-sm form-control" >
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
	                 					<select id="adm_corps" name="adm_corps" class="form-control-sm form-control" >
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
					                 	<select id="adm_div" name="adm_div" class="form-control-sm form-control" >
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
	                 					<select id="adm_bde" name="adm_bde" class="form-control-sm form-control" >
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
					                 	<select id="parent_arm" name="parent_arm" class="form-control-sm form-control" >
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
	                 					<select id="type_of_arm" name="type_of_arm" class="form-control-sm form-control">
		                                    <option value="0">--Select--</option>
		                                </select>
		                                <!-- <input type="text" id="type_of_arm" name="type_of_arm" placeholder="" class="form-control"> -->
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
   										<input type="hidden" id="arm_code" name="arm_code" maxlength="4" class="form-control">
    									<select id="arm_name" name="arm_name"  maxlength="100" class="form-control-sm form-control">
                            				<option value="0">--Select--</option>
                  							<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
                  								<option value="${item.arm_code}" > ${item.arm_code} - ${item.arm_desc}</option>
                  							</c:forEach>
                         				</select>
   									</div>
        						</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
          							<div class="col col-md-4">
          								<label for="selectLg" class=" form-control-label">Type Of Unit</label>
          							</div>
       								<div class="col-12 col-md-8">
            							<select name="type_force" id="type_force" class="form-control-sm form-control">
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
					                  	<label class=" form-control-label"><strong style="color: red;">*</strong> Loc</label>
					                </div>
					                <div class="col-12 col-md-8">
					                 	<input type="hidden" id="code" name="code" maxlength="5" class="form-control" style="width:100%;" >
					                 	<input type="text" id="loc" name="loc" maxlength="400" class="form-control" style="width:86%;display: inline-block;" readonly="readonly">
			              				<i class="fa fa-search" onclick="openLocLOV();"></i>
            						</div>
          						</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group" >
	                				<div class="col col-md-4">
	                  					<label class=" form-control-label">NRS</label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                  					<input type="hidden" id="nrs_code" name="nrs_code" maxlength="5" class="form-control">
	                  					<input type="text" id="nrs_name" name="nrs_name" maxlength="400" class="form-control" readonly="readonly">
	                				</div>
	              				</div>
							</div>
						</div>		
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
					                <div class="col col-md-4">
					                  	<label class="form-control-label"><strong style="color: red;">*</strong> Type Of Loc</label>
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
										<label for="text-input" class=" form-control-label">CT Part I/II</label>
									</div>
	
									<div class="col col-md-8">
										<div class="form-check-inline form-check">
											<label for="inline-radio1" class="form-check-label ">
												<input type="radio" id="radio1" name="ct_part_i_ii" maxlength="8" value="CTPartI" class="form-control form-check-input">CT Part I</label>&nbsp;&nbsp;&nbsp; 
											<label for="inline-radio2" class="form-check-label "> 
												<input type="radio" id="radio2" name="ct_part_i_ii" maxlength="8" value="CTPartII" class="form-control form-check-input">CT Part II</label> &nbsp;&nbsp;&nbsp; 
											<label for="inline-radio3" class="form-check-label "> 
												<input type="radio" id="radio3" name="ct_part_i_ii" maxlength="8" value="Others" class="form-control form-check-input">Others
											</label>
										</div>
									</div>
								</div>
							</div>
						</div>		
						<div class="col-md-12">
							<div class="col-md-6">
	              				<div class="row form-group">
				                	<div class="col col-md-4">
				                  		<label class=" form-control-label"><strong style="color: red;">*</strong> <span id="dateLabel"></span> Date (From)</label>
				                	</div>
				                	<div class="col-12 col-md-8">
				                		<!-- <input type="hidden" id="compltn_arrv_date" name="compltn_arrv_date" placeholder="" class="form-control"> -->
				                  		<input type="date" id="comm_depart_date" name="comm_depart_date" class="form-control">
				                	</div>
				            	</div>
				        	</div>
				        	<div class="col-md-6">
	              				<div class="row form-group">
				                	<div class="col col-md-4">
				                  		<label class=" form-control-label"><span id="dateLabel1"></span> Date (To) </label>
				                	</div>
				                	<div class="col-12 col-md-8">
				                		<input type="date" id="compltn_arrv_date" name="compltn_arrv_date" onchange="return checkdate(this,comm_depart_date)" class="form-control">
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
              			<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">
              		</div> 
	        	</div>
			</div>
		</div>
	</div>
</form:form>
<script>
function reloadPage() {
	location.reload(true);
}
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
	
	if($("#type_of_letter").val() == "0"){
		alert("Please Select Scenario");
		$("#type_of_letter").focus();
		return false;
    }
	
	var target_unitname = document.getElementById("unit_name").value;
	var source_unitname = document.getElementById("source_unit_name").value;
	if(target_unitname == source_unitname)
	{
		alert("Please Change the Target Unit Name.");
		return false;
	}
	var st;
	var unit_name1 = $("#unit_name").val();
	if(unit_name1 != "" && $("#type_of_letter").val() == "11"){
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
	if($("#arm_name").val() == "0"){
		alert("Please Select Arm Name");
		$("#arm_name").focus();
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
	return true;
}
</script>

<script>
var strtypeOfLetter;
function TypeOfLetterChange()
{
	clearAll();
	$("div#TypeOfLetterOnClickDiv").show();
	var e = document.getElementById("type_of_letter").value;
	if(e == '1')  // 'Re-designation')
	{
		$("#dateLabel").text("Re-designation");
		$("#dateLabel1").text("Re-designation");
		$("#sus_no").attr('readonly',true);
		$("#parent_arm").attr('disabled',true);
		$("#type_of_arm").attr('disabled',true);
	}
	if(e == '2') // 'Conversion')
	{
		$("#dateLabel").text("Conversion");
		$("#dateLabel1").text("Conversion");
		$("#sus_no").attr('readonly',false);
		$("#parent_arm").attr('disabled',true);
		$("#type_of_arm").attr('disabled',true);
	}
	if(e == '11') // 'Re-Structure')
	{
		$("#dateLabel").text("Re-Structure");
		$("#dateLabel1").text("Re-Structure");
		$("#sus_no").attr('readonly',true);
		$("#parent_arm").attr('disabled',false);
		$("#type_of_arm").attr('disabled',false);
	}
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
	   	var code = this.value;    	   	
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

  /* //////////////////////////////////DETAIL OF UNIT/////////////////////////////////////////////////////////////// */
$("#unit_name").keyup(function(){
	var unit_name = this.value;
	var e = document.getElementById("type_of_letter");
	strtypeOfLetter = e.options[e.selectedIndex].text;
			
	if(strtypeOfLetter == 'Conversion')
	{
		var susNoAuto=$("#unit_name");
		susNoAuto.autocomplete({
		source: function( request, response ) {
	        $.ajax({
		        type: 'POST',
		        url: "getUnitsNameInactiveList?"+key+"="+value,
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
	       		if(strtypeOfLetter == 'Conversion')
	       		{
	       			alert("Please Enter Inactive Unit Name");
		        	document.getElementById("sus_no").value="";
		        	susNoAuto.val("");	        	  
		        	susNoAuto.focus();
		        	clearAll();
	       		}
	       		return false;	             
	     	}   	         
	  	}, 
      	select: function( event, ui ) {
      		if(strtypeOfLetter == 'Conversion')
	       	{ 
        		getOrbatDetailsFromUnitName(ui.item.value);
		       }
	      	} 	     
		});
	}
}); 
		$("input#sus_no").keyup(function(){
			var sus_no = this.value;
			var e = document.getElementById("type_of_letter");
			strtypeOfLetter = e.options[e.selectedIndex].text;
			if(strtypeOfLetter == 'Conversion')
			{
				 var unitNameAuto=$("#sus_no");
				  unitNameAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        type: 'POST',
				        url: "getSusNoInactiveList?"+key+"="+value,
				        data: {sus_no:sus_no},
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
								var e = document.getElementById("type_of_letter");
				  				strtypeOfLetter = e.options[e.selectedIndex].text;
					        	if(strtypeOfLetter == 'Conversion')
						       	{	
					        		alert("Please Enter Inactive SUS NO");
					        	  	$("#unit_name").val("");
					        	  	unitNameAuto.val("");	        	  
					        	  	unitNameAuto.focus();
					        	  	clearAll();
					        	  	return false;
						       	}
				          }   	         
				      }, 
				      select: function( event, ui ) {
				      	var sus_no = ui.item.value;
				      	var e = document.getElementById("type_of_letter");
						strtypeOfLetter = e.options[e.selectedIndex].text;
						
				      	if(strtypeOfLetter == 'Conversion')
				       	{
					      	$.post("getInactiveSusNoList?"+key+"="+value,{sus_no:sus_no}, function(j) {
			   	        		$("#unit_name").val(j[0]);
			   	        		getOrbatDetailsFromUnitName(j[0]);
						    });
				       	}
					} 	     
				});
			}
		});
  
		///Source Unit Name 
		$("#source_unit_name").keypress(function(){
			var unit_name = this.value;
			var susNoAuto=$("#source_unit_name");
			susNoAuto.autocomplete({
				source: function( request, response ) {
					$.ajax({
						type: 'POST',
				        url: "getUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
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
		        		alert("Please Enter Active Unit Name");
		        	  	document.getElementById("source_sus_no").value="";
		        	  	susNoAuto.val("");	        	  
		        	  	susNoAuto.focus();
		        	  	return false;	             
		          	}   	         
		      	}, 
				select: function( event, ui ) {
			     	$(this).val(ui.item.value);
			      	var unitName = ui.item.value;
			      	
			    	$.post("getActiveSusNoFromUnitName?"+key+"="+value,{unit_name:unitName}, function(j) {
			    		if(j.length != 0){
			    			var length = j.length-1;
							var enc = j[length][0].substring(0,16);
	 	        			$("#source_sus_no").val(dec(enc,j[0][0]));
	 	        	 		var e = document.getElementById("type_of_letter");
			   	        	var unit_name = document.getElementById("unit_name");
			   	        	var strtypeOfLetter = e.options[e.selectedIndex].text;
			   	        	var x = document.getElementsByClassName("example");
			   	        	
			   	        	if(strtypeOfLetter == 'Re-designation')
		   	        		{
			   	        		unit_name.classList.remove("autocomplete");
			   	        		$("#unit_name").val(unitName);
			   	        		$("#sus_no").val(dec(enc,j[0][0]));
			   	        		unitdtllist();
			   	        	}
			   	        	else{
		   	        			$("#source_unit_name").val(unitName);
		   	        		}
			    		}
	 	        	});
				} 	     
			});
		}); 
			
		// Source Sus No auto
		$("input#source_sus_no").keyup(function(){
			var sus_no = this.value;
			var unitNameAuto=$("#source_sus_no");
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
			        	  alert("Please Enter Active SUS NO");
			        	  $("#source_unit_name").val("");
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
		   	        			var e = document.getElementById("type_of_letter");
				   	        	var strtypeOfLetter = e.options[e.selectedIndex].text;
				   	        	if(strtypeOfLetter == 'Re-designation')
			   	        		{
		   	        	 			$("#source_unit_name").val(dec(enc,j[0]));
		   	        	 			$("#unit_name").val(dec(enc,j[0]));
		   	        	 			$("#sus_no").val(sus_no);
		   	        	 			unitdtllist();
			   	        		}else{
			   	        			$("#source_unit_name").val(dec(enc,j[0]));
			   	        		}
		   	        	 	
		   	        	});
					}
			    });
			});
       });
     
var Opcom,Opcorps,Opdiv,Opbde,Contcom,Contcorps,Contdiv,Contbde,Admincom,Admincorps,Admindiv,Adminbde,ParentArm,TypeOfArm;
function getOrbatDetailsFromUnitName(unitName){	
   	$.post("getUnitDetailsInactiveList?"+key+"="+value,{unitName:unitName}, function(j) {
   		for ( var i = 0; i < j.length; i++) {
   	 		$("#sus_no").val(j[i].sus_no);
   	 		
   	 		var forcodeOperation = j[i].form_code_operation;
   	 		var forcodeControl = j[i].form_code_control;
   	 		var forcodeAdmin = j[i].form_code_admin;
   	 		var Arm = j[i].sus_no;
   	 		var code_location = j[i].code;
   	 		var unit_under_armhq = j[i].unit_army_hq;
   	 		
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
       
        	$('#op_comd option[value="'+Opcom+'"]').attr("selected", "selected");
    	   	getOPCorps(Opcorps);
	   	   	getOPDiv(Opdiv);
    	   	getOPBde(Opbde);
    	  
        	 	
    	 	$('#cont_comd option[value="'+ Contcom +'"]').attr("selected", "selected");
    	 	var cont_cname = $("#cont_comd").find('option:selected').attr("name");    	 
    		$("#cont_cname").val(cont_cname);
        
	        getCONTCorps(Contcorps);
		    getCONTDiv(Contdiv);
		    getCONTBde(Contbde);
		     	
		    $('#adm_comd option[value="'+ Admincom +'"]').attr("selected", "selected");
        	getADMCorps(Admincorps);
           	getADMDiv(Admindiv);
           	getADMBde(Adminbde);
       
        	var ct_part = j[i].ct_part_i_ii;
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
       	
       		ParentArm = Arm[0] + Arm[1]; 
        	TypeOfArm = Arm[0] + Arm[1] + Arm[2] + Arm[3]; 
       	
       	 	$('#parent_arm option[value="'+ ParentArm +'"]').attr("selected", "selected");
       	
       	 	$.post("getTypeOfArmArmList?"+key+"="+value,{code:ParentArm}, function(j) {
         		var length = j.length-1;
         		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if( TypeOfArm == dec(j[length][0],j[i][0])){
						options += '<option value="' + dec(j[length][0],j[i][0]) + '" selected=selected>'+ dec(j[length][0],j[i][0]) +" - "+  dec(j[length][0],j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(j[length][0],j[i][0]) + '" >'+ dec(j[length][0],j[i][0]) +" - "+  dec(j[length][0],j[i][1]) + '</option>';
					}
				}	
				$("select#type_of_arm").html(options); 
			});
       	
	     	$.post("getLOC_NRS_TypeLOC_TrnType?"+key+"="+value,{locCode:code_location}, function(j) {
	     		$("#code").val(j[0][2]);
	     		$("#loc").val(j[0][0]);
	     		$("#nrs_code").val(j[0][5]);
	     		$("#nrs_name").val(j[0][1]);
	     		$('#type_of_location').val(j[0][4]);
	   	     	$('#modification').val(j[0][3]);
		   	});
	     	
	     	var arm_code = j[i].arm_code;
	     	$("input#arm_code").val(arm_code); 
	     	$("select#arm_name").val(arm_code)
	     	
	     	var type_force = j[i].type_force;
	     	$("select#type_force").val(type_force);
	     	$("#compltn_arrv_date").val(j[i].compltn_arrv_date);
		}
	});
}
       	
</script>

<script>
function unitdtllist()
{
	var unitName = document.getElementById("source_unit_name").value;
	$.post("getUnitDetailsList?"+key+"="+value,{unitName:unitName}, function(j) {
		if(j.length > 0) {
			var length = j.length-1;
			var enc = j[length].substring(0,16);
			
    		$("#sus_no").val(dec(enc,j[1]));
	 	
	 		var forcodeOperation = dec(enc,j[4]);
	 		var forcodeControl = dec(enc,j[5]);
	 		var forcodeAdmin = dec(enc,j[6]);
	 		var Arm = dec(enc,j[1]);
	 		var code_location = dec(enc,j[8]);
	 		var unit_under_armhq = dec(enc,j[3]);
	 		
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
	   	        	
			var ct_part = dec(enc,j[15]);
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
			
     	 	$('#op_comd option[value="'+Opcom+'"]').attr("selected", "selected");
    	   	getOPCorps(Opcorps);
	   	   	getOPDiv(Opdiv);
    	   	getOPBde(Opbde);
    	    	
    	 	$('#cont_comd option[value="'+ Contcom +'"]').attr("selected", "selected");
    		var cont_cname = $("#cont_comd").find('option:selected').attr("name");    	 
   			$("#cont_cname").val(cont_cname);
	        getCONTCorps(Contcorps);
		    getCONTDiv(Contdiv);
		    getCONTBde(Contbde);
		    	
		    $('#adm_comd option[value="'+ Admincom +'"]').attr("selected", "selected");
	        getADMCorps(Admincorps);
	        getADMDiv(Admindiv);
	        getADMBde(Adminbde);
	        
	   	    ParentArm = Arm[0] + Arm[1]; 
	   	    TypeOfArm = Arm[0] + Arm[1] + Arm[2] + Arm[3]; 
	   	        	
			$('#parent_arm option[value="'+ ParentArm +'"]').attr("selected", "selected");
			$.post("getTypeOfArmArmList?"+key+"="+value,{code:ParentArm}, function(j) {
	         		var length = j.length-1;
	         		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < length; i++) {
					if( TypeOfArm == dec(j[length][0],j[i][0])){
						options += '<option value="' + dec(j[length][0],j[i][0]) + '" selected=selected>'+ dec(j[length][0],j[i][0]) +" - "+  dec(j[length][0],j[i][1]) + '</option>';
					}else{
						options += '<option value="' + dec(j[length][0],j[i][0]) + '" >'+ dec(j[length][0],j[i][0]) +" - "+  dec(j[length][0],j[i][1]) + '</option>';
					}
				}	
				$("select#type_of_arm").html(options); 
			});

	     	$("#code").val(dec(enc,j[8]));
	     	$("#loc").val(dec(enc,j[9]));
	     	$("#nrs_code").val(dec(enc,j[10]));
	     	$("#nrs_name").val(dec(enc,j[11]));
	     	$('#type_of_location').val(dec(enc,j[12]));
	   	   	$('#modification').val(dec(enc,j[13]));
		 
	   	   	$("input#arm_code").val(dec(enc,j[7])); 
   	     	$("select#arm_name").val(dec(enc,j[7]))
   	     	
   	     	var type_force = dec(enc,j[14]);
   	     	$("select#type_force").val(type_force);
   	    	$("#compltn_arrv_date").val(dec(enc,j[20]));
		}else{
			alert('Data Not Available');
		}
   	});
}
	    
	    	
function clearAll(){
   	$("#source_unit_name").val("");	
   	$("#source_sus_no").val("");	
   	$("#unit_name").val("");	
   	$("#sus_no").val("");	
   	
   	$("#inline-radio1").prop("checked", false);
   	$("#inline-radio2").prop("checked", false);
   	
   	$("#op_comd").val("");	
   	$("#op_corps").val("0");	
   	$("#op_div").val("0");	
   	$("#op_bde").val("0");
   	
   	$("#cont_comd").val("");	
   	$("#cont_corps").val("0");	
   	$("#cont_div").val("0");	
   	$("#cont_bde").val("0");	
   	
   	$("#adm_comd").val("");	
   	$("#adm_corps").val("0");	
   	$("#adm_div").val("0");	
   	$("#adm_bde").val("0");	
   	
   	$("#parent_arm").val("0");	
   	$("#type_of_arm").val("0");	
   	$("#arm_name").val("0");
   	$("#type_force").val("");
   	
   	$("#code").val("");	
   	$("#nrs_name").val("");	
   	$("#type_of_location").val("");	
   	$("#modification").val("");	
   	
   	$("#radio1").prop("checked", false);
   	$("#radio2").prop("checked", false);
   	$("#radio3").prop("checked", false);

   	$("#comm_depart_date").val("");
   	$("#address").val("");
}
		
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
