<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/ChkDateLessThan.js" type="text/javascript"></script>

<form:form name="Miso_Orbat_Unt_DtlForm" id="Miso_Orbat_Unt_DtlForm" action="misoReOrganisationDtlAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="misoOrbatUntDtlCMD" enctype="multipart/form-data">
	<div class="animated fadeIn" onFocus="parent_disable();" onclick="parent_disable();">
		<div class="container" align="center">
	    	<div class="row">
	    		<div class="card">
	    			<div class="card-header"><h5><b>RE-ORGANISATION SCHEDULE</b><br></h5><h6><span style="font-size:12px;color:red;">(To be entered by SD Dte / MISO)</span></h6><strong>Schedule Details</strong> </div>
	    			<div class="card-body card-block">
	          			<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">*</strong> Re-Org Schedule No </label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="letter_no" name="letter_no" maxlength="250" placeholder="Re-Org Schedule No" class="form-control">
                					</div>
              					</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">*</strong> GOI Letter No </label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="goi_letter_no" name="goi_letter_no" maxlength="250" placeholder="GOI Letter No" class="form-control">
                					</div>
              					</div>
	          				</div>
	          			</div>
	          			
	          			<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> GOI Letter Date (From) </label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="date" id="date_goi_letter" name="date_goi_letter" class="form-control" max="${date}">
									</div>
  								</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Letter Date (To) </label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="date" id="sanction_date" name="sanction_date" class="form-control" max="${date}">
									</div>
								</div>
	          				</div>
	          			</div>
	          			
	          			<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Upload Re-Org Schedule Letter</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="file" id="upload_goi_letter" name="upload_goi_letter" maxlength="250" class="form-control">
									</div>
								</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Upload GOI Letter</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="file" id="upload_auth_letter" name="upload_auth_letter" maxlength="250" class="form-control">
									</div>
  								</div>
	          				</div>
	          			</div>	
	            </div>
	         	<div class="card-header"> <strong>Details of Units to Re-Organized</strong> </div>
	         	<div class="card-body ">
	          		
	          		<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">	                
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label"><strong style="color: red;">*</strong> Re-Org Type/Condition</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                 						<select name="re_org_type" id="re_org_type" class="form-control-sm form-control" >
								        	    <option value="0">--Select--</option>
								                <option value="1">Re-organised into an existing unit</option>
									            <option value="2">Re-organised to create a new unit</option>								                
								            </select>
	                					</div>
									</div>
	          				</div>
	          		</div>
	          		
	          		<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div  class="row form-group"  >
	                				<div class="col-12 col-md-4">
	                  					<label class=" form-control-label"><strong style="color: red;">*</strong> Source Unit Name </label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                  						<!-- <input type="text" id="source_unit_name" name="source_unit_name" placeholder="Unit Name" class="form-control autocomplete" autocomplete="off"> -->	                  					
	                  					<input type="text" id="source_unit_name" name="source_unit_name" maxlength="100" class="form-control" style="width:86%;display: inline-block;" > 
                						<i class="fa fa-search" onclick="openUnitnameSelection('source');"></i>
	                				</div>
	              				</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group" >
	                				<div class="col-12 col-md-4">
						            	<label class=" form-control-label"><strong style="color: red;">*</strong> SUS No </label>
						            </div>
						            <div class="col-12 col-md-8">
	                  						<!-- <input type="text" id="source_sus_no" name="source_sus_no" maxlength="8" placeholder="SUS No" class="form-control autocomplete"  autocomplete="off"> -->
	                  					<input type="text" id="source_sus_no" name="source_sus_no" maxlength="8" class="form-control" style="width:86%;display: inline-block;" > 
                						<i class="fa fa-search" onclick="openUnitnameSelection('source');"></i>
	                				</div>
						        </div>
	          				</div>
	          		</div>
						<div  class="col-md-12" style="text-align: center;">
							<input type="reset" class="btn btn-success btn-sm" value="Clear">   
				           	<input type="button" class="btn btn-primary btn-sm" value="Add Unit" onClick="return addUnit();">
			            </div>
								
						<div   class="col-md-12" ><br></div>
						<div  class="col-md-12" align="center" >
							<input type="hidden" id="count" name="count" >
							<table id="reOrgTable" class="table no-margin table-striped  table-hover  table-bordered" >
								<thead id="tableadd"  style="background-color: #E8E8E8; color: black;">
									<tr>
									    <th style="font-size: 15px">Ser No</th>
										<th style="font-size: 15px">SUS No</th>
										<th style="font-size: 15px">Unit Name</th>
										<th style="font-size: 15px">Action</th>
									</tr>
								</thead>
								<tbody >
								</tbody>
							</table>
						</div>
				<!-- </form> -->
       			</div>
	         	<div id="container" class="card-header" style="border: 1px solid rgba(0,0,0,.125); display: none;"> <strong><label id="labelcon"></label> </strong></div>
	         	<div id="container" class="card-body card-block" style="display: none;">
	         	
	         		<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group" id="Existing" style="display:none;">
		                			<div class="col col-md-4">
		                  				<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Unit Name </label>
		                			</div>
		                			<div class="col-12 col-md-8">
		                  						<!-- <input type="text" id="target_unit_name" name="target_unit_name" class="form-control autocomplete"  autocomplete="off"> -->
		                  				<input type="text" id="target_unit_name" name="target_unit_name" class="form-control" style="width:86%;display: inline-block;" > 
                						<i class="fa fa-search" onclick="openUnitnameSelection('target');"></i>
									</div>
								</div>
								<div class="row form-group" id="createUnit" style="display:none;">
	                				<div class="col col-md-4">
	                  					<label class=" form-control-label"><strong style="color: red;">*</strong> Entity </label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                 					<select name="level_in_hierarchy" id="level_in_hierarchy" class="form-control-sm form-control">
									        <option value="Unit">Unit</option>
								       	</select>
	                				</div>
	              				</div>
	          				</div>
	          				<div class="col-md-6">
	          					
	              				<div class="row form-group" id="Existing" style="display:none;">
				                	<div class="col col-md-4">
				                		<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
				                	</div>
				                	<div class="col-12 col-md-8">
				                		<input type="text" id="target_sus_no" maxlength="8" name="target_sus_no" class="form-control autocomplete">
									</div>
								</div>
								<div class="row form-group" id="createUnit" style="display:none;">
							    	<div class="col col-md-4">
								      	  <label class=" form-control-label"><strong style="color: red;">*</strong> Name Of Entity </label>
								   	</div>
								   	<div class="col-12 col-md-8">
								     	  <input type="text" id="unit_name" name="unit_name" maxlength="100" class="form-control">
								   	</div>
								</div>
	          				</div>
	          		</div>
	          		
	          		<div class="col-md-12">
	          				<div class="col-md-6">
	          				
				  				<div class="row form-group">
								<div class="col col-md-5">
									<label for="text-input" class=" form-control-label">Unit Under Army HQ</label>
								</div>
								<div class="col col-md-6">
									<div class="row form-group" >
										<div class="col col-md-2">
											<label class=" form-control-label">Yes</label>
										</div>
										<div class="col-12 col-md-3">
											<input type="radio" id="inline-radio1" name="unit_army_hq" value="Y" class=" form-control form-check-input">
										</div>
										<div class="col col-md-2">
											<label class=" form-control-label">No</label>
										</div>
										<div class="col-12 col-md-3">
											<input type="radio" id="inline-radio2" name="unit_army_hq" value="N" class="form-control form-check-input">
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
	                  					<label class=" form-control-label"><strong style="color: red;">*</strong> Op Comd </label>
	                				</div>
									<div class="col-12 col-md-8">
									    <select data-placeholder="Choose a Country..." id="op_comd" name="op_comd" class="form-control-sm form-control"  >
									     	<option value="">--Select--</option>
		                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
	                 							<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
	                 						</c:forEach>
								    	</select>
								    </div>
									     	<!--<div class="col-12 col-md-2" >
									     	 	<a data-toggle="modal" data-target="#commandView" onclick="return getCommand();" ><img src="js/miso/images/searchImg.jpg" style="height: 25px;" data-target="#remoteSensingView"></a>
									     	</div> -->
								</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group" >
									<div class="col col-md-4">
										<label class=" form-control-label">Op Corps</label>
									</div>
									<div class="col-12 col-md-8">
					                 	<select data-placeholder="Choose a Country..." id="op_corps" name="op_corps" class="form-control-sm form-control"  >
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
									  	<select data-placeholder="Choose a Country..." id="op_div" name="op_div" class="form-control-sm form-control" >
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
									   	<label class=" form-control-label"><strong style="color: red;">*</strong> Cont Comd </label>
									</div>
									<div class="col-12 col-md-8">
									    <select data-placeholder="Choose a Country..." id="cont_comd" name="cont_comd" class="form-control-sm form-control">
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
						                <select data-placeholder="Choose a Country..." id="cont_corps" name="cont_corps" class="form-control-sm form-control">
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
									    <select data-placeholder="Choose a Country..." id="cont_div" name="cont_div" class="form-control-sm form-control">
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
					                	<select data-placeholder="Choose a Country..." id="cont_bde" name="cont_bde" class="form-control-sm form-control">
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
										<label class=" form-control-label"><strong style="color: red;">*</strong> Adm Comd </label>
									</div>
									<div class="col-12 col-md-8">
									  	<select data-placeholder="Choose a Country..." id="adm_comd" name="adm_comd" class="form-control-sm form-control">
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
					                	<select data-placeholder="Choose a Country..." id="adm_corps" name="adm_corps" class="form-control-sm form-control">
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
									    <select data-placeholder="Choose a Country..."  id="adm_div" name="adm_div" class="form-control-sm form-control">
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
					            		<select id="adm_bde" name="adm_bde" class="form-control-sm form-control">
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
								        <label class=" form-control-label"><strong style="color: red;">*</strong> Parent Arm </label>
								    </div>
								   	<div class="col-12 col-md-8">
					                 	<select id="parent_arm" name="parent_arm" class="form-control-sm form-control">
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
					                	<label class=" form-control-label"><strong style="color: red;">*</strong> Type Of Arm </label>
					                </div>
					                <div class="col-12 col-md-8">
	                 					<select id="type_of_arm" name="type_of_arm" class="form-control-sm form-control" tabindex="1" ></select>
	                				</div>
					        	</div>
	          				</div>
	          			</div>
	          			
	          			<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
					                <div class="col col-md-4">
					                  <label class=" form-control-label"><strong style="color: red;">*</strong> Arm Name </label>
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
	                  					<label class=" form-control-label"><strong style="color: red;">*</strong> Arm Code </label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                  					<input type="text" id="arm_code" name="arm_code"  maxlength="4" class="form-control" autocomplete="off" readonly="readonly">
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
                  						<select name="type_force" id="type_force"  class="form-control-sm form-control" >
								           	<option value="">--Select--</option>
                              				<c:forEach var="item" items="${getTypeOfUnitList}">
               									<option value="${item[0]}" >${item[0]} - ${item[1]}</option>
               								</c:forEach>
								      	</select>
          							</div>
          						</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group" id="createUnit" style="display:none;">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">CT Part I/II</label>
									</div>               
									<div class="col col-md-8">
								        <div class="form-check-inline form-check">
								            <label for="inline-radio1" class="form-check-label ">
								               	<input type="radio" id="inline-radio1" name="ct_part_i_ii" maxlength="8" value="CTPartI" class=" form-control form-check-input">CT Part I
								            </label>&nbsp;&nbsp;&nbsp;
								            <label for="inline-radio2" class="form-check-label ">
								            	<input type="radio" id="inline-radio2" name="ct_part_i_ii" maxlength="8" value="CTPartII" class=" form-control form-check-input">CT Part II
								            </label>
															&nbsp;&nbsp;&nbsp;
								           	<label for="inline-radio2" class="form-check-label ">
								                   	<input type="radio" id="inline-radio2" name="ct_part_i_ii" maxlength="8" value="Others" class=" form-control form-check-input">Others
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
									   	<label class=" form-control-label"><strong style="color: red;">*</strong> Loc</label>
									</div>
									<div class="col-12 col-md-8" >
									                 	<!-- <select data-placeholder="Choose a Country..." id="code" name="code" class="standardSelect" tabindex="1" style="width:50%;"></select> -->
									   	<input type="hidden" id="code" name="code" maxlength="5" class="form-control" style="width:100%;" > 
						              	<input type="text" id="loc" name="loc" maxlength="400" class="form-control" style="width:86%;display: inline-block;" readonly="readonly">
						              	<i class="fa fa-search" onclick="openLocLOV();"></i> 
                					</div>
                				</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
								     <div class="col col-md-4">
								          	<label class=" form-control-label"><strong style="color: red;">*</strong> Type Of Loc </label>
								     </div>
								     <div class="col-12 col-md-8">
								          	<input type="text" id="type_of_location" name="type_of_location" maxlength="50"  class="form-control" readonly="readonly">
								     </div>
								</div>
	          				</div>
	          			</div>
	          			
	          			<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
					                <div class="col col-md-4">
					                	<label class=" form-control-label">NRS</label>
					                </div>
					                <div class="col-12 col-md-8">
					                	<input type="hidden" id="nrs_code" name="nrs_code" maxlength="5"  class="form-control">
					                	<input type="text" id="nrs_name" name="nrs_name"  maxlength="400" class="form-control" readonly="readonly">
					                </div>
					           </div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
					                <div class="col col-md-4">
					                	<label class=" form-control-label">Trn Type</label>
					                </div>		
					                <div class="col-12 col-md-8">
					                	<input type="text" id="modification" name="modification" maxlength="100"  class="form-control" readonly="readonly">
				    				</div>
					            </div>
	          				</div>
	          			</div>
						
						<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
								    <div class="col col-md-4">
								    	<label class=" form-control-label"><strong style="color: red;">*</strong> Re-Organisation Date (From) </label>
								   	</div>
								  	<div class="col-12 col-md-8">
								   		<input type="date" id="comm_depart_date" name="comm_depart_date"  class="form-control">
								   	</div>
								</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
					                <div class="col col-md-4">
					                	<label class=" form-control-label">Re-Organisation Date (To)</label>
					                </div>
					                <div class="col-12 col-md-8">
					                	<input type="date" id="compltn_arrv_date" name="compltn_arrv_date" onchange="return checkdate(this,comm_depart_date)"  class="form-control">
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
							
              						</div>
              						<div  class="card-footer" align="center" >
										<input type="reset" class="btn btn-success btn-sm" value="Clear">   
					              		<input type="submit" class="btn btn-primary btn-sm" value="Merge" onclick="return validate();"> 
					              	</div> 
					
	         	
		            
	        	</div>
			</div>
		</div>
		</div>
	
</form:form>
<script>
var popupWindow=null;
function openArmLOV(url) {
	var myWindow = window.open("armLOV", "MsgWindow", "width=500,height=600");
}
function openLocLOV(url) {
	var myWindow = window.open("locationLOV", "MsgWindow", "width=500,height=600");
}
function HandlePopupResult(loc,nrs_name,loc_code,trn_type,typeofloc,nrscode) {
    $("#code").val(loc_code);
	$("#nrs_name").val(nrs_name);
	$("#modification").val(trn_type);
	$("#type_of_location").val(typeofloc);
	$("#loc").val(loc);
	$("#nrs_code").val(nrscode);
}

var unitStatus;
// Unit NameLOV
function HandlePopupResultUnitName(unit_name,sus_no) 
{
	if(unitStatus == "target"){
		$("#target_unit_name").val(unit_name);
		$("#target_sus_no").val(sus_no);
		target(unit_name);
	}
	if(unitStatus == "source"){
		$("#source_unit_name").val(unit_name);
		$("#source_sus_no").val(sus_no);
		
	}
}

function openUnitnameSelection(st) {
	unitStatus = st;
	popupWindow = window.open("LovUnit_SusNo", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=200,left=500,width=900,height=600,fullscreen=no");
}

function parent_disable() {
	if(popupWindow && !popupWindow.closed)
		popupWindow.focus();
}

</script>
<script>
function HandlePopupResultpa(code_value,code) {
   	$("#parent_armh").val(code_value);
	$("#parent_arm").val(code);
}

function openParentArmSelection() {
	popupWindow = window.open("searchParentArm", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=yes,top=200,left=500,width=900,height=600");
}
</script>  

<script>
function HandlePopupResultTYpaeofArm(code_value,code) {
   	$("#type_of_armh").val(code_value);
	$("#type_of_arm").val(code);
}
function openParentArmSelection1() {
	var parent_armh = $("#parent_armh").val();
	if(parent_armh == ''){
		alert("Please select Parent Arm!");
  	}
  	else{
		popupWindow = window.open("searchTypeOfArm?code="+parent_armh,"_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=yes,top=500,left=500,width=400,height=400");
  	}
}
</script>
<script>
function validate(){
	if($("#letter_no").val() == ""){
		alert("Please Enter the Auth Letter No.");
		$("#letter_no").focus();
		return false;
	}
	if($("#goi_letter_no").val() == ""){
		alert("Please Enter the GOI Letter No.");
		$("#goi_letter_no").focus();
		return false;
	}
	if($("#sanction_date").val() == ""){
		alert("Please Select Date of Letter");
		$("#sanction_date").focus();
		return false;
    }
	if($("#date_goi_letter").val() == ""){
		alert("Please Select Date of GOI Letter");
		$("#date_goi_letter").focus();
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
	
	if($("#re_org_type").val() == ""){
		alert("Please Select Re-Org Type/Condition");
		$("#re_org_type").focus();
		return false;
    }
	
	if($("#source_unit_name").val() == "0"){
		alert("Please Select Unit Name");
		$("#source_unit_name").focus();
		return false;
    }
	if($("#source_sus_no").val() == "0"){
		alert("Please Select Sus No.");
		$("#source_sus_no").focus();
		return false;
    }
	
	if($("#op_comd").val() == "0"){
		alert("Please Select Operation Command");
		$("#op_comd").focus();
		return false;
    }
	if($("#cont_comd").val() == "0"){
		alert("Please Select Control Command");
		$("#cont_comd").focus();
		return false;
    }
	if($("#adm_comd").val() == "0"){
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
		alert("Please Select Re-Organisation Date (From)");
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
	$(document).ready(function() {
       $("#upload_auth_letter").change(function(){	
    		checkFileExt(this);
    	});
    	$("#upload_goi_letter").change(function(){	
    		checkFileExt(this);
    	});
    
    	var fcode ="0";
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
    	    $('select#arm_name').change(function() {
   	            $("#arm_code").val(this.value);
    	   	});
    	    $('#target_sus_no').change(function() {
   	        	var unitName = this.value;
   	        	$.post("getTypeOfArmandParentArmDtlList?"+key+"="+value,{code_type:parent_arm,code:ParentArm}, function(j) {
   	        		for ( var i = 0; i < j.length; i++) {
   	        	 		$("#parent_arm").val(j);
   	        	 		var forcode = j;
   	        	 	}
   	        	});
   	    	});
    	     $('#source_unit_name').change(function() {
   	        	var unitName = this.value;
   	        	$.post("getActiveSusNoFromUnitName?"+key+"="+value,{unit_name:unitName}, function(j) {
   	        		if(j.length != 0){
   	        			var length = j.length-1;
						var enc = j[length][0].substring(0,16);
			   			$("#source_sus_no").val(dec(enc,j[0][0]));
   	        		}
   	        	});
   	        });
    	    $('#target_unit_name').change(function() {
    	    	var unitName = this.value;
    	    	$.post("getActiveSusNoFromUnitName?"+key+"="+value,{unit_name:unitName}, function(j) {
    	    		if(j.length != 0){
   	        			var length = j.length-1;
						var enc = j[length][0].substring(0,16);
			   			$("#target_sus_no").val(dec(enc,j[0][0]));
    	    		}
   	        	});
   	        });
       });
     
var Opcom,Opcorps,Opdiv,Opbde,Contcom,Contcorps,Contdiv,Contbde,Admincom,Admincorps,Admindiv,Adminbde,ParentArm,TypeOfArm;
function target(unitName)
{
	$.post("getUnitDetailsList?"+key+"="+value,{unitName:unitName}, function(j) {
		if(j.length > 0) {        	
	       	var length = j.length-1;
			var enc = j[length].substring(0,16);
		 			
	        $("#target_sus_no").val(dec(enc,j[1]));
       	 		
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
 	        	
 	        	
 	        $('#op_comd option[value="'+Opcom+'"]').attr("selected", "selected");
 	    	getOPCorps(Opcorps);
 		   	getOPDiv(Opdiv);
 	    	getOPBde(Opbde);
 	    	  
 	        	 	
 	    	$('#cont_comd option[value="'+ Contcom +'"]').attr("selected", "selected");
  	        getCONTCorps(Contcorps);
  		    getCONTDiv(Contdiv);
  		    getCONTBde(Contbde);
  		     	
  		    $('#adm_comd option[value="'+ Admincom +'"]').attr("selected", "selected");
  	        getADMCorps(Admincorps);
  	        getADMDiv(Admindiv);
  	        getADMBde(Adminbde);
  	        
  	        $("#code").val(dec(enc,j[8]));
    		$("#loc").val(dec(enc,j[9]));
    		$("#nrs_code").val(dec(enc,j[10]));
    		$("#nrs_name").val(dec(enc,j[11]));
    		$('#type_of_location').val(dec(enc,j[12]));
  	     	$('#modification').val(dec(enc,j[13]));
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
 	         	
 	        var arm_code = dec(enc,j[7]);
   	   		$("#arm_code").val(arm_code);
   	  		$("select#arm_name").val(arm_code);
  	     
   	 		var type_force = dec(enc,j[14]);
  			$("select#type_force").val(type_force);
		}else{
			alert('Data Not Available');
		}
   	});
	    }
</script>

<script>
$("#re_org_type").change(function(){
	   	if($(this).val()=="2")
	   	{    
		   	$("label#labelcon").text("Details Of The New Unit");
	       	$("div#createUnit").show();
	       	$("div#container").show();
	      	$("div#Existing").hide();
	       	$("#parent_armh").attr('disabled',false).css("background-color", "white");
        	$("#type_of_armh").attr('disabled',false).css("background-color", "white");       	
        	
		}
		else
	    {
	    	$("label#labelcon").text("Select Unit You wanted to Merge into");
	       	$("div#createUnit").hide();
        	$("div#container").show();
        	$("div#Existing").show();  
        	$("#parent_armh").attr('disabled',true).css("background-color", "grey");
        	$("#type_of_armh").attr('disabled',true).css("background-color", "grey");
	    }
	   $('#reOrgTable tbody').empty();
	  
	});
</script>
<script>
var max_fields  = 100; 
var x = 0;
var flage = 0;
function unitadd()
{
	
}
function addUnit(){
	if($("#source_unit_name").val() == ""){
		alert("Please Select Unit Name");
		$("#source_unit_name").focus();
		return false;
    }
	if($("#source_sus_no").val() == ""){
		alert("Please Select Sus No.");
		$("#source_sus_no").focus();
		return false;
    }
	var source_unitName = $("#source_unit_name").val();
	var source_susNo = $("#source_sus_no").val();
	if(x != 0){
		for(var i = 0;i<x;i++){
			var sus_no123 = $("#sus_no_"+(i+1)).val();
			if(source_susNo == sus_no123){
				alert("Unit Name Already Exist! \n please select another one.");
				flage = 1;
				return false;
			}
		}
		if(flage == 0){
			x++; 
			$("table#reOrgTable").append('<tr id="'+x+'"><td class="text-center">0'+x+'</td><td><input type="text" name="sus_no_'+x+'" id="sus_no_'+x+'" value="'+source_susNo+'" class="form-control" readonly/></td> <td><input id="unit_name_'+x+'" name="unit_name_'+x+'" value="'+source_unitName+'" class="form-control" readonly/></td><td><input type="button" class="btn btn-primary btn-sm" value="Delete" onclick="deleteRow(this)"/></td></tr>');
	    	$("#count").val(x);
		}
	}else{
		if(x < max_fields){ 
		   	x++; 
		    $("table#reOrgTable").append('<tr id="'+x+'"><td class="text-center">0'+x+'</td><td><input name="sus_no_'+x+'" id="sus_no_'+x+'" value="'+source_susNo+'" class="form-control" readonly/></td> <td><input id="unit_name_'+x+'" name="unit_name_'+x+'" value="'+source_unitName+'" class="form-control" readonly/></td><td><input type="button" class="btn btn-primary btn-sm" value="Delete" onclick="deleteRow(this)"/></td></tr>');
		    $("#count").val(x);
		}
	}
	document.getElementById("source_unit_name").value="";
	document.getElementById("source_sus_no").value="";
	if (confirm("Do you want to add more Units?") )
		{unitadd();}
	else{ return false;}

	return false;
}
</script>
<script>
function deleteRow(r) {
  var i = r.parentNode.parentNode.rowIndex;
  document.getElementById("reOrgTable").deleteRow(i);
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