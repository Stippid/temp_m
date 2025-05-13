<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>


<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

		
<form:form name="tmsbanumdir" id="tmsbanumdir" action="ApproveConvReqOfVeh?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="editconvertRequestGstosplVehDTLCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>CONVERSION GS TO SPL VEH : APPROVE</h5> <h6 class="enter_by">(To be entered by MISO)</h6></div><div class="card-header"><strong style="float:center;">Basic Details</strong> </div>
	            		<div class="card-body card-block" id="mainViewSelection" style="display: block;">
	            			<div class="col-md-12">
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
                  							<label class=" form-control-label">SUS No   </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                						<input type="hidden" id="id" name="id" placeholder="SUS No" value="${editconvertRequestGstosplVehDTLCMD.id}" >
	                  						<input type="text" readonly="readonly" id="sus_no" name="sus_no" placeholder="SUS No" value="${editconvertRequestGstosplVehDTLCMD.sus_no}" class="form-control autocomplete" autocomplete="off" maxlength="8">
	                					</div>
									</div>
								</div>
								<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
                  							<label class=" form-control-label">Unit Name  </label>
	                					</div>
	                						<div class="col-12 col-md-8">
										<textarea id="unit_name" name="unit_name" readonly="readonly" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" ></textarea>
									     </div>
	  								</div>
								</div>
							
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
	                  						<label class=" form-control-label">Date Of Request</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="hidden" id="dte_of_reqst1" name="dte_of_reqst1" placeholder="" value="${editconvertRequestGstosplVehDTLCMD.dte_of_reqst}"  min='1899-01-01' max='${date}' class="form-control autocomplete" autocomplete="off">               					
	                			
	                  						<input type="text" readonly="readonly" id="dte_of_reqst" name="dte_of_reqst" placeholder=""  class="form-control autocomplete" autocomplete="off">               					
	                			
										</div>
	  								</div>
									</div>
                               </div>
                             <div class="col-md-6" style="display: none;">
                                      <div class="row form-group">
	                					<div class="col col-md-4">
                  							<label class=" form-control-label">Recd From</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						
	                  							<input type="hidden" id="received_from1" name="received_from1" placeholder="" value="${editconvertRequestGstosplVehDTLCMD.received_from}" class="form-control autocomplete" autocomplete="off">               					
	                			
	                  						<input type="text" readonly="readonly" class="form-control" id="received_from" value = "" name="received_from"  placeholder="" maxlength="50">
	                  					</div>
	  								</div>
                                 </div>
                                 
                                 <div class="col-md-12">
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>PRF Nomenclature: </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="hidden" id="prf_code" readonly="readonly" name="prf_code" value="${editconvertRequestGstosplVehDTLCMD.prf_code}" class="form-control" autocomplete="off">
										<input type="text" id="prf_nomen" readonly="readonly" name="prf_nomen" value="" class="form-control" autocomplete="off">
<!-- 										<select  id="prf_nomen" readonly="readonly" class="form-control-sm form-control selectpicker"  style="width: 100%"> -->
<!-- 											<option value="-1">--Select--</option> -->
<%-- 												<c:forEach var="item" items="${getAllPRFList}" --%>
<%-- 													varStatus="num"> --%>
<%-- 													<option value="${item[0]}" name="${item[1]}">${item[1]}</option> --%>
<%-- 												</c:forEach> --%>
<!-- 										</select> -->
									</div>
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>MCT Nomenclature: </label>
									</div>
									<div class="col-12 col-md-8">
									<input type="hidden" id="mct_code" readonly="readonly" name="mct_code" value="${editconvertRequestGstosplVehDTLCMD.mct_nomen}" class="form-control" autocomplete="off">
									<input type="text" id="mct_nomen" readonly="readonly" name="mct_nomen" class="form-control" autocomplete="off">
<%-- 										<select  readonly="readonly" id="mct_nomen" value="${editconvertRequestGstosplVehDTLCMD.mct_nomen}" class="form-control-sm form-control selectpicker" style="width: 100%"> --%>
<!-- 										</select> -->
									</div>
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong
											style="color: red;">* </strong>Current BA No: </label>
									</div>
									<div class="col-12 col-md-8">
									
									<input type="text" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.old_ba_no}" class="form-control" id="old_ba_no" name="old_ba_no"  placeholder="" maxlength="50">
<!-- 										<select readonly="readonly" data-placeholder="Choose Ba Number" id="old_ba_no" name="old_ba_no"  class="form-control-sm form-control"> -->
<%-- 											<option value="${editconvertRequestGstosplVehDTLCMD.old_ba_no}"></option> --%>
<!-- 										</select> -->
<!-- 										<input type="text" id="old_ba_no" name="old_ba_no" class="form-control" autocomplete="off"> -->
<!-- 										<select data-placeholder="Choose Ba Number" id="old_ba_no" name="old_ba_no" class="form-control-sm form-control"> -->
<!-- 											<option value="0">--Select--</option> -->
<!-- 										</select> -->
									</div>
								</div>
	
							</div>
							
							
                                </div>
                                <div class="col-md-12">
						<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
									<label class=" form-control-label">Cont Comd: </label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_comd" readonly="readonly" name="cont_comd" class="form-control">
										${selectcomd}
										<c:forEach var="item"   items="${getCommandList}"
											varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
									<label class="form-control-label">Cont Corps:</label>
								</div>
								<div class="col-12 col-md-8">
									<select id="cont_corps" readonly="readonly" name="cont_corps" class="form-control">
										${selectcorps}
										<c:forEach var="item" items="${getCorpsList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						
						
						<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
									<label class=" form-control-label">Cont Div:</label>
								</div>
								<div class="col-12 col-md-8">
									<select readonly="readonly" id="cont_div" name="cont_div" class="form-control">
										${selectDiv}
										<c:forEach var="item" items="${getDivList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						</div>
						 <div class="col-md-12">
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Cont Bde:</label>
									</div>
									<div class="col-12 col-md-8">
										<select readonly="readonly" id="cont_bde" name="cont_bde" class="form-control">
											${selectBde}
											<c:forEach var="item"  items="${getBdeList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Line Dte </label>
									</div>
									<div class="col-12 col-md-8">
									<input type="text" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.line_dte_sus}" class="form-control" id="line_dte_sus_no"  name="line_dte_sus_no"  placeholder="" maxlength="50">
										<!-- <input type="text" id="sus_no" name="sus_no"   maxlength="8" class="form-control" autocomplete="off"> -->
<!-- 										<select readonly="readonly" id="line_dte_sus_no" name="line_dte_sus_no" class="form-control-sm form-control" > -->
<!-- 										<option value="0">--Select--</option> -->
<%-- 										<c:forEach var="item" items="${getLine_DteList}" varStatus="num"> --%>
<%-- 										<option value="${item.line_dte_sus}" >${item.line_dte_name}</option> --%>
<%-- 										</c:forEach> --%>
<!-- 									</select> -->
									</div>
								</div>
							</div>
					</div>
						
						</div>
						
						<div class="card-header" align="center"> <strong>Vehicle Details</strong> </div>
							
						<div class="card-body card-block">
	            			<div class="col-md-12">
<!--                                                   <div class="col-md-6"> -->
<!--                                                   <div class="row form-group"> -->
<!-- 	                					<div class="col col-md-4"> -->
<!-- 	                  						<label class=" form-control-label">Old BA No </label> -->
<!-- 	                					</div> -->
<!-- 	                					<div class="col-12 col-md-8"> -->
<%-- 	                  						<input type="text" id="old_ba_no" readonly="readonly" name="old_ba_no" placeholder="" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.old_ba_no}" class="form-control autocomplete" autocomplete="off" maxlength="10"> --%>
<!-- 	                					</div> -->
<!-- 	              					</div> -->
<!--                                                   </div> -->
                                                  <div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Chassis No</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="chasis_no" name="chasis_no" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.old_chasis_no}"  placeholder="" class="form-control" maxlength="20">
										</div>
	  								</div>
                                                  </div>
<!--                                           </div> -->
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Engine No  </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" class="form-control" id="engine_no" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.old_engine_no}" name="engine_no" placeholder="" maxlength="20">
										</div>
									</div>
                                   </div>
                                   
                                   <div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Nomenclature: </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="old_std_nomclature" name="old_std_nomclature" readonly="readonly"  placeholder="" class="form-control">
										</div>
	  								</div>
                               </div>
                                                  
                                   
<!--                                                   <div class="col-md-6"> -->
<!--                                                   <div class="row form-group"> -->
<!-- 	                					<div class="col col-md-4"> -->
<!-- 	                  						<label for="text-input" class=" form-control-label"> Class Code of Old Veh </label> -->
<!-- 	                					</div> -->
<!-- 	                					<div class="col-12 col-md-8"> -->
<!-- 	                  						<input type="text" class="form-control" id="old_vcode" readonly="readonly"  name="old_vcode" placeholder=""> -->
<!-- 										</div> -->
<!-- 									</div> -->
<!--                                                   </div> -->
                                          </div>
                                          
                                          <div class="col-md-12">
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Km: 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" class="form-control" id="km" readonly="readonly" name="km" placeholder="" maxlength="20">
									</div>
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Cl: 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" class="form-control" id="cl" readonly="readonly" name="cl" placeholder="" maxlength="20">
									</div>
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;"></strong>Vintage: 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" class="form-control" id="vintage" readonly="readonly" name="vintage" placeholder="" maxlength="20">
									</div>
								</div>
							</div>
							</div>
	              					
	              		<div class="col-md-12">
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">MCT Number:  </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" readonly="readonly" class="form-control" id="old_mct_number" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.old_mct_number}"  name="old_mct_number" onchange="getstdNomenclature()" placeholder="" maxlength="10">
										</div>
									</div>
								</div>
								
								<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">UE: </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="ue" name="ue" class="form-control" autocomplete="off" readonly>
									</div>
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">UH: </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="uh" name="uh" class="form-control" autocomplete="off" readonly>
									</div>
								</div>
							</div>
							
						</div>
						
                                          </div>
	              					
	              					
	              					<div class="col-md-12">
                                                  <div class="col-md-6" style="display: none;">
                                                  <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Justification  </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="hidden" class="form-control" id="just" value="${editconvertRequestGstosplVehDTLCMD.justification}" name="old_vcode" placeholder="">
	                  				
	                  						<textarea class="form-control" value="k" id="justification" name="justification" maxlength="50"></textarea> 
										</div>
									</div>
                                                  </div>
                                                  <div class="col-md-6" style="display: none;">
                                                  <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Financial Impact  </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  					<input type="hidden" class="form-control" id="finance" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.financial_impact}" name="finance" placeholder="">
	                  						<textarea  class="form-control" value="k" id="financial_impact" name="financial_impact" maxlength="50"></textarea> 
									</div>
									</div>
                                                  </div>
                                          </div>
	              					
	              					
	              					<div class="col-md-12">
                                                  <div class="col-md-6" style="display: none;">
                                                  <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Conversion/Modification Carried out  </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="hidden" class="form-control" id="conv" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.conv_modCarriedOut}" name="old_vcode" placeholder="">
	                  				
	                  						<textarea class="form-control" value="k" id="conv_modCarriedOut" name="conv_modCarriedOut" maxlength="10"></textarea> 
									
										</div>
									</div>
                                                  </div>
                                                  <div class="col-md-6" style="display: none;">
                                                  <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">New Nomenclature of Veh after Mod as auth in WE  </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="hidden" class="form-control" id="newstd" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.newstdnomencltr_veh_aftrmod_as_auth_we}" name="newstd" placeholder="">
	                  				
	                  						<textarea class="form-control" value="k" id="newstdnomencltr_veh_aftrmod_as_auth_we" name="newstdnomencltr_veh_aftrmod_as_auth_we" maxlength="10"></textarea> 
									</div>
											</div>
                                 				</div>
                                          </div>
									
									<div id = "forMISO_MGO_1" style="display: block;">	
									<div class="col-md-12">
<!--                                                   <div class="col-md-6"> -->
<!--                                                   <div class="row form-group"> -->
<!-- 	                					<div class="col col-md-4"> -->
<!-- 	                  						<label for="text-input" class=" form-control-label">Remarks  </label> -->
<!-- 	                					</div> -->
<!-- 	                					<div class="col-12 col-md-8"> -->
<%-- 	                  						<input type="hidden" class="form-control" id="rem" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.remarks}" name="old_vcode" placeholder=""> --%>
	                  					
<!-- 	                  						<textarea class="form-control"  readonly="readonly" id="remarks" name="remarks" maxlength="255" ></textarea>  -->
								
<!-- 										</div> -->
<!-- 									</div> -->
<!--                                                   </div> -->

								<div class="card-header"><strong>Proposed Conversion</strong></div>
											<div></div>

<!--                                     <div class="col-md-6"> -->
<!--                                          <div class="row form-group"> -->
<!-- 		                					<div class="col col-md-4"> -->
<!-- 		                  						<label for="text-input" class=" form-control-label">New MCT </label> -->
<!-- 		                					</div> -->
<!-- 		                					<div class="col-12 col-md-8"> -->
<%-- 		                  						<input type="text" class="form-control autocomplete"  readonly="readonly" autocomplete="off" value="${editconvertRequestGstosplVehDTLCMD.new_mct_number}"  id="new_mct_number" name="new_mct_number" placeholder="" maxlength="10"> --%>
<!-- 											</div> -->
<!-- 										</div> -->
<!--                                       </div> -->
<!--                                    </div> -->

					<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">
											MCT Nomenclature: </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" readonly="readonly" class="form-control" id="old_mct_number_p"
											name="old_mct_number_p"  value="${editconvertRequestGstosplVehDTLCMD.new_nomencatre}"
											placeholder="" maxlength="10">
											
									</div>
									<input type="hidden" class="form-control" id="old_mct_new_p"  value="${editconvertRequestGstosplVehDTLCMD.new_mct_number}"
											name="old_mct_new_p"  
											placeholder="" maxlength="10">
								</div>
							</div>
								<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">MCT UE: </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="mct_ue" name="mct_ue" class="form-control" autocomplete="off" readonly>
									</div>
								</div>
							</div>
							
							<div class="col-md-4">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">MCT UH: </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="mct_uh" name="mct_uh" class="form-control" autocomplete="off" readonly>
									</div>
								</div>
							</div>
							
						</div>
					</div>
             
	              					<div  id="divhid" style="display: none;">
	  								<div class="col-md-12">
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label class=" form-control-label">Auth Of MoD</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input type="text" class="form-control" readonly="readonly" id="auth_letter_no" value="${editconvertRequestGstosplVehDTLCMD.auth_letter_no}" name="auth_letter_no" placeholder="" maxlength="10">
		                					</div>
		              					</div>
                                                  </div>
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label for="text-input" class=" form-control-label">Nomenclature </label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<%-- <input type="text" class="form-control" id="new_nomencatre" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.new_nomencatre}"  name="new_nomencatre" placeholder="" maxlength="100"> --%>
		                  						<%-- <input type="text" class="form-control" id="new_nomencatre" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.new_nomencatre}"  name="new_nomencatre" placeholder="" maxlength="100"> --%>
		                  						<%-- <textarea rows="2" id="new_nomencatre" readonly="readonly"  value="${editconvertRequestGstosplVehDTLCMD.new_nomencatre}" name="new_nomencatre" maxlength="100" style="width: 100%;"></textarea> --%>
											   <input class="form-control" id="new_nomencatre"  name="new_nomencatre" readonly="readonly"  value="${editconvertRequestGstosplVehDTLCMD.new_nomencatre}" >
											</div>
										</div>
                                                  </div>
                                          </div>
	  								<div class="col-md-12">
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label for="text-input" class=" form-control-label">Conversion Mod Done </label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input type="text" id="convrsn_done"  readonly="readonly" name="convrsn_done" placeholder="" value="${editconvertRequestGstosplVehDTLCMD.convrsn_done}" class="form-control" maxlength="5">
											</div>
		  								</div>
                                                  </div>
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label for="text-input" class=" form-control-label"> Class Code of New Veh</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input class="form-control" id="vehicle_class_code"  name="vehicle_class_code" readonly="readonly" maxlength="2">
	                  						</div>
		  								</div>
                                                  </div>
                                          </div>
									
									
									
                                          <div class="col-md-12">
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
											<div class="col col-md-4">
												<label class=" form-control-label">Front View Image<br> </label>
											</div>
											<div class="col-12 col-md-8">
												<input type="hidden"  id="frontimg"  name="frontimg">
												<img  src="kmlFileDownloadConvertBA?kmlFileId4=${editconvertRequestGstosplVehDTLCMD.old_ba_no}&fildname=front_view_image"  class="pull-right" style="height: 100px;"/>
												
											</div>
										</div>
                                                  </div>
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
												<div class="col col-md-4">
													<label class=" form-control-label">Rear View Image</label>
												</div>
												<div class="col-12 col-md-8">
													<input type="hidden" id="side_view_image1" name="top_view_image1" class="form-control">
													<input type="hidden" id="level_in_hierarchy" name="level_in_hierarchy" class="form-control">
													<input type="hidden" id="childtb_id" name="childtb_id" class="form-control">
													<input type="hidden" id="convert_req_id" name="convert_req_id" class="form-control">
												<img  src="kmlFileDownloadConvertBA?kmlFileId4=${editconvertRequestGstosplVehDTLCMD.old_ba_no}&fildname=top_view_image"  class="pull-right" style="height: 100px;"/>
												
												</div>
											</div>
                                                  </div>
                                          </div>
                                          
                                          <div class="col-md-12">
                                                  <div class="col-md-6">
                                                  
											<div class="row form-group">
												<div class="col col-md-4">
													<label class=" form-control-label">Left Side View Image</label>
												</div>
												<div class="col-12 col-md-8">
													<input type="hidden"  id="frontimg"  name="backimg">
													
													<img  src="kmlFileDownloadConvertBA?kmlFileId4=${editconvertRequestGstosplVehDTLCMD.old_ba_no}&fildname=back_view_image"  class="pull-right" style="height: 100px;"/>
												
												</div>
											</div>
                                                  </div>
                                                  <div class="col-md-6">
                                                  	
										
										<div class="row form-group">
											<div class="col col-md-4">
												<label class=" form-control-label">Right Side View Image</label>
											</div>
											<div class="col-12 col-md-8">
												<input type="hidden" id="side_view_image1" name="side_view_image1" class="form-control">
												<img  src="kmlFileDownloadConvertBA?kmlFileId4=${editconvertRequestGstosplVehDTLCMD.old_ba_no}&fildname=side_view_image"  class="pull-right" style="height: 100px;"/>
												
											</div>
										</div>
                                                  </div>
                                          </div>
                                          
                                                               <div class="card-body card-block" style="overflow: auto;">
						<div class="col s12" style="">
			              	<table class="table no-margin table-striped  table-hover  table-bordered"  >
				                <thead>
				                  	<tr align="center">				                  	
				                   		<th width="27%"><strong style="color: red;">*</strong> New Engine No</th>
				                    	<th width="27%"><strong style="color: red;">*</strong> New Chassis No</th>
				                     	<th width="20%"><strong style="color: red;">*</strong> Pencil Rubbing of Engine <br> <span style="font-size: 10px;">(Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed)</span></th> 
				                     	<th width="20%"><strong style="color: red;">*</strong> Pencil Rubbing of Chassis <br> <span style="font-size: 10px;">(Only *.jpg, *.jpeg and *.png file extensions and file size max 2MB are allowed)</span></th> 
				                  	</tr>
				                   <tr style="background-color: white;">
				                  		
				                  		<td width="27%"><input id="engine_no1" name="engine_no1" maxlength="20" class="form-control" autocomplete="off" style="text-transform: uppercase;" value ="${editconvertRequestGstosplVehDTLCMD.new_engine_no}" readonly></td>
		        						<td width="27%"><input id="chasis_no1" name="chasis_no1" maxlength="20" class="form-control" autocomplete="off" style="text-transform: uppercase;" value="${editconvertRequestGstosplVehDTLCMD.new_engine_no}" readonly></td>
		        						<td width="20%"><img  src="kmlFileDownloadConvertBA?kmlFileId4=${editconvertRequestGstosplVehDTLCMD.old_ba_no}&fildname=engine_view_image"  class="pull-right" style="height: 100px;"/>
												</td>
		        						<td width="20%"><img  src="kmlFileDownloadConvertBA?kmlFileId4=${editconvertRequestGstosplVehDTLCMD.old_ba_no}&fildname=chasis_view_image"  class="pull-right" style="height: 100px;"/></td>
									
									</tr>
								</thead>
								
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
                                          </div>
					
	            					</div> 
							</div>
						</div>
					</div>
						<div  class="form-control card-footer" align="center" id="btnhide" style="display: block;">					
							<input type="submit" class="btn btn-primary btn-sm" value="Approve" onclick="return check();">							
							<input type="hidden" id="count" name="count">					
						</div>
						<div  class="form-control card-footer" align="center" id="btnhideback" style="display: block;">
					
							<a href="search_convert_veh_1" class="btn btn-success btn-sm" >Back</a>  
						</div>
					
					</div> 
				</div>
	</div>

</form:form>
<script>

function myFunction() {
	var count = 0;

	var old_ba_no = $("#old_ba_no").val();

		 	$.post("getChk_ba_no_dublication?"+key+"="+value, {old_ba_no:old_ba_no}).done(function(j) {				
		 		count = j;
				$("input#count").val(j);	
			}).fail(function(xhr, textStatus, errorThrown) {
			});	
}
</script>
<script>
function validate(){
	
	
	var old_mct_number = $("#old_mct_number").val();
	var new_mct_number = $("#new_mct_number").val();
	if(old_mct_number == new_mct_number)
	{
		alert("Old and New Mct Number can't be same , Please Change New Mct No.");
		$("#new_nomencatre").text("");
			return false;
		}
	
	var count = $("#count").val();
	if(count > 0)
	{
		alert("This Ba No Record is  Already Exist.");
		return false;
	}
	
	if($("#remarks").val() == ""){
		alert("Please Enter Remarks");
		$("#remarks").focus();
		return false;
    }
	return true;
}	
</script>	
<script>

Date.prototype.addMonths = function (value) {
    var n = this.getDate();
    this.setDate(1);
    this.setMonth(this.getMonth() + value);
    this.setDate(Math.min(n, this.getDaysInMonth()));
    
    var datestring =  this.getFullYear() + "-" +  ("0"+(this.getMonth()+1)).slice(-2) + "-" + ("0" + this.getDate()).slice(-2); 
    
 
    return datestring;
};
Date.isLeapYear = function (year) { 
    return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0)); 
};

Date.getDaysInMonth = function (year, month) {
    return [31, (Date.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
};

Date.prototype.isLeapYear = function () { 
    return Date.isLeapYear(this.getFullYear()); 
};

Date.prototype.getDaysInMonth = function () { 
    return Date.getDaysInMonth(this.getFullYear(), this.getMonth());
};

$(document).ready(function() {	

  

	if ('${view}' == 1){
		btnhide.style.display = "none";
		
	}else
		{
		btnhide.style.display = "block";
		}
	
if ('${roleAccess}' == 'MISO') {
		
		if ('${month1}' != 0) {
			$("#month").val('${month1}');
			$("#report_all").show();
		}
		if ('${year1}' != '') {
			$("#year").val('${year1}');
			$("#report_all").show();
		}

	}
	if ('${roleAccess}' == 'Unit') {debugger;
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		$("#cont_bde").attr("disabled", true);
		$("#sus_no").attr("disabled", true);
		$("#unit_name").attr("disabled", true);

		$("#sus_no").val('${sus_no}');
		$("#unit_name").val('${unit_name}');
	  
		
		if ('${cmd_sus}' != "") {
			
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
			$("#report_all").show();
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
		}
		if ('${div_sus}' != "") {
			$("#hd_div_sus").val('${div_sus}');
			getCONTBde('${div_sus}');
		}
		if ('${bde_sus}' != "") {
			$("#hd_bde_sus").val('${bde_sus}');
		}
	}debugger;
	if ('${roleSubAccess}' == 'Brigade') {
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		$("#cont_bde").attr("disabled", true);

		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
		}
		if ('${div_sus}' != "") {
			$("#hd_div_sus").val('${div_sus}');
			getCONTBde('${div_sus}');
		}
		if ('${bde_sus}' != "") {
			$("#hd_bde_sus").val('${bde_sus}');
		}

	}
	if ('${roleSubAccess}' == 'Division') {
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
		}
		if ('${div_sus}' != "") {
			$("#hd_div_sus").val('${div_sus}');
			getCONTBde('${div_sus}');
		}

	}
	if ('${roleSubAccess}' == 'Corps') {debugger;
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);

		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
		if ('${corp_sus}' != "") {
			$("#hd_corp_sus").val('${corp_sus}');
			getCONTDiv('${corp_sus}');
		}

	}
	if ('${roleSubAccess}' == 'Command') {
		$("#cont_comd").attr("disabled", true);
		if ('${cmd_sus}' != "") {
			$("#report_all").show();
			$("#hd_cmd_sus").val('${cmd_sus}');
			getCONTCorps('${cmd_sus}');
		}
	}


	if('${roleAccess}' == 'MISO' || '${roleAccess}' == 'HeadQuarter')
	{
		if('${cont_comd1}' != ""){
	    	$("#cont_comd").val('${cont_comd1}');
	    	getCONTCorps('${cont_comd1}');
		}
		if('${cont_corps1}' != ""){
			 getCONTDiv('${cont_corps1}');
		}
	    if('${cont_div1}' != ""){
	    	getCONTBde('${cont_div1}');
	    }
	}

	var select = '<option value="' + "0" + '">'
			+ "--Select--" + '</option>';
	$('select#cont_comd').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_corps").html(select);
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);
		} else {
			$("select#cont_corps").html(select);
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);

			$("#hd_cmd_sus").val(fcode);

			getCONTCorps(fcode);

			fcode += "00";
			getCONTDiv(fcode);

			fcode += "000";
			getCONTBde(fcode);
		}
	});
	$('select#cont_corps').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);
		} else {
			$("select#cont_div").html(select);
			$("select#cont_bde").html(select);

			$("#hd_corp_sus").val(fcode);
			getCONTDiv(fcode);
			fcode += "000";
			getCONTBde(fcode);
		}
	});
	$('select#cont_div').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {

			$("select#cont_bde").html(select);
			$("#hd_div_sus").val(fcode);
			getCONTBde(fcode);
		}
	});

	$('select#cont_bde').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {
			$("#hd_bde_sus").val(fcode);
		}
	});

	if ('${sus_no}' != "") {
		$("#unit_sus_no").val('${sus_no}');

		$.post("getActiveUnitNameFromSusNo?" + key + "="
				+ value, {
			sus_no : '${sus_no}'
		}, function(j) {
			var length = j.length - 1;
			var enc = j[length].substring(0, 16);
			$("#unit_name").val(dec(enc, j[0]));
			//getOrbatDetailsFromUnitName(dec(enc,j[0]))
			//var a = dec(enc,j[0]);
			//getCommand(a);	
		});

		$("#unit_sus_no").attr("disabled", true);
		$("#unit_name").attr("disabled", true);
	}

	/* 	$('#unit_sus_no').change(function(){
		var y = this.value;
		getCommand(y);
	}); */
	/* $('#unit_name').change(function(){
		var y = this.value;
		alert("fdfd----"+y);
		$.post("getTargetSUSFromUNITNAME?"+key+"="+value,{y:y},function(j) {
			alert("fdfd-gfghf---"+j);
			var enc = j[j.length-1].substring(0,16);
			var a = dec(enc,j[0]);
			getCommand(a);		
		});
		
	}); */ 
	if ('${cmd_sus}' != "" && '${cmd_sus}' != "0") {
		$("#cont_comd").val('${cmd_sus}');
		$("#hd_cmd_sus").val('${cmd_sus}');
		$("#cont_comd").attr("disabled", true);
		getCONTCorps('${cmd_sus}');
	}
	if ('${corp_sus}' != "" && '${corp_sus}' != "0") {
		$("#cont_corps").val('${corp_sus}');
		$("#hd_corp_sus").val('${corp_sus}');
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		getCONTDiv('${corp_sus}');
	}
	if ('${div_sus}' != "" && '${div_sus}' != "0") {
		$("#cont_div").val('${div_sus}');
		$("#hd_div_sus").val('${div_sus}');
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		getCONTBde('${div_sus}');
	}
	if ('${bde_sus}' != "" && '${bde_sus}' != "0") {
		$("#cont_bde").val('${bde_sus}');
		$("#hd_bde_sus").val('${bde_sus}');
		$("#cont_comd").attr("disabled", true);
		$("#cont_corps").attr("disabled", true);
		$("#cont_div").attr("disabled", true);
		$("#cont_bde").attr("disabled", true);
	}
	
/* 	if('${roleAccess}' == 'MISO'){
		divhid.style.display = "block";
	}else{
		divhid.style.display="none";
	} */
	
	if ($('#engine_no1').val()) {
        divhid.style.display = "block";
    } else {
        divhid.style.display = "none";
    }
	$("#front_view_image1").change(function(){	
		checkFileExtImage(this);
	});
	$("#back_view_image1").change(function(){	
		checkFileExtImage(this);
	});
	$("#side_view_image1").change(function(){	
		checkFileExtImage(this);
	});
	$("#top_view_image1").change(function(){	
		checkFileExtImage(this);
	});
	
	   	 var unit_name = $("#unit_name").text();
		 var ba_no = $("#old_ba_no").val();
		
		 $("input#old_vcode").val(ba_no[2]);
		 
		 var dte_of_reqst = $("#dte_of_reqst1").val();
		 var received_from = null;
		 
		 var date = new Date(dte_of_reqst);    
	     var formattedtoday = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();
	     var formattedtoday1 =date.getFullYear()  + '-' + (date.getMonth() + 1)  + '-' + date.getDate() ;
	     var date = new Date(received_from);    
	     var received_from = date.getDate() + '-' + (date.getMonth() + 1) + '-' + date.getFullYear();
	     var received_from1 =date.getFullYear()  + '-' + (date.getMonth() + 1)  + '-' + date.getDate() ;

		 $("input#dte_of_reqst").val(formattedtoday1);
		 $("input#received_from").val(received_from1);
		
   		 if(ba_no[2] == "B")
   			 {
   					 $("input#vehicle_class_code").val("F");
   			 }
   		 else if(ba_no[2] == "C")
           		{
   					 $("input#vehicle_class_code").val("N");
   				 }
   		 else if(ba_no[2] == "D")
   			 {
   						$("input#vehicle_class_code").val("P");
   			 }
   		 else
   			 {
   			 		alert("Old Vehicle Code is not Valid.");
   			 }
	 
	 	$("textarea#justification").val($("input#just").val());
	 	$("textarea#remarks").val($("input#rem").val());
	 	$("textarea#financial_impact").val($("input#finance").val());
	 	$("textarea#newstdnomencltr_veh_aftrmod_as_auth_we").val($("input#newstd").val());
	 	$("textarea#conv_modCarriedOut").val($("input#conv").val());
	     $("#level_in_hierarchy").val('${level_in_hierarchy}');
	     $("#childtb_id").val('${childtb_id}');
	     $("#convert_req_id").val('${convert_req_id}');
	 	var ba_no = $("#old_ba_no").val();
		var sus_no = $("#sus_no").val();
		$("#unit_name").val('${unit_name}');	

			 	$.post("getImageOfVehFromBa_nochildreq?"+key+"="+value, {ba_no:ba_no}).done(function(j) {				
			 		for ( var i = 0; i < j.length; i++) {
		       	 		 $("#frontview1").val(j[0]);
			       	 	$("#backview1").val(j[1]);
			       	 	$("#sideview1").val(j[2]);
			    	 	$("#topview1").val(j[3]);       	 		
					}
				}).fail(function(xhr, textStatus, errorThrown) {
		});
});	

function clearAll()
{
	var tab = $("#SearchPersReport > tbody");
 	tab.empty(); 
 	
 	localStorage.clear();
	localStorage.Abandon();
}
</script>

<script>
$(function() {
	
	var ba_no = $("#old_ba_no").val();
		 	$.post("getEngineChasisFromBANoList?"+key+"="+value, {ba_no:ba_no}).done(function(j) {				
		 		var length = j.length-1;
		       	var enc = j[length][0].substring(0,16);
		       	$("#old_std_nomclature").val(dec(enc,j[0][3]));
			}).fail(function(xhr, textStatus, errorThrown) {
		});
		
	$("input#new_mct_number").keypress(function(){
		var mct = this.value;
		var mct_numberAuto=$("#new_mct_number");
		var type_of_vehicle = "B";
		if(type_of_vehicle == "0"){
			alert("please Select  Type of Vehicle.");
			$("#veh_cat").focus();
			 $("#new_nomencatre").text("");
        	 mct_numberAuto.val("");
			return false;
		}

		mct_numberAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getMctNoDetailList?"+key+"="+value,
		        data: {mct:mct ,type_of_vehicle:type_of_vehicle},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  if(data.length != 0){
			        		var enc = data[length].substring(0,16);
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
		        	  alert("Please Enter Valid MCT No.");
		        	  $("#new_nomencatre").text("");
		        	  mct_numberAuto.val("");	        	  
		        	  mct_numberAuto.focus();
		        	  return false;	             
		    	}   	         
		    }, 
		  	
		    select: function( event, ui ) {
		    	/* var mct_num = ui.item.value;
		    	
		    			 	$.post("getMctNotoStdNomenclatureList?"+key+"="+value, {mct_num:mct_num}).done(function(j) {				
		    			 		var length = j.length-1;
					        	var enc = j[length].substring(0,16);
					        	$("#new_nomencatre").text(dec(enc,j[0]));	
		    				}).fail(function(xhr, textStatus, errorThrown) {
		    			}); */
		    			
		    	var mct = ui.item.value;
			 	$.post("getstdNomenclatureOfTmsList?"+key+"="+value, {mct:mct}).done(function(j) {				
			 		$("#new_nomencatre").text(j);
				}).fail(function(xhr, textStatus, errorThrown) {});		
		    }
		    
		    
		});
	});
		
});		

			/* $('#new_mct_number').change(function() {
				var mct = this.value;
						 	$.post("getstdNomenclatureOfTmsList?"+key+"="+value, {mct:mct}).done(function(j) {				
						 		$("#new_nomencatre").text(j);
							}).fail(function(xhr, textStatus, errorThrown) {
					});
			}); */
			
			
			var mct_old = $("#old_mct_number").val();
			var sus_no = $("#sus_no").val();
			var new_mct =  $("#old_mct_new_p").val();
		//	alert(new_mct);

			$.post("getUEFromMCT?" + key + "=" + value, { mct : new_mct, sus_no : sus_no }, function(data) {
			}).done(function(data) {
				if (data.length == 0) {
					$("#mct_ue").val(0);
				} else {
					var length = data.length - 1;
					var enc = data[length]
							.substring(0,
									16);
					$("#mct_ue").val(dec(enc,data[0]));
				}
			}).fail(function(xhr, textStatus,errorThrown) {
			});
			
			
			
			$.post("getUEFromMCT?" + key + "=" + value, { mct : mct_old, sus_no : sus_no }, function(data) {
							}).done(function(data) {
								if (data.length == 0) {
									$("#ue").val(0);
								} else {
									var length = data.length - 1;
									var enc = data[length]
											.substring(0,
													16);
									$("#ue").val(dec(enc,data[0]));
								}
							}).fail(function(xhr, textStatus,errorThrown) {
							});

			$.post("getUHFromMCT?" + key + "=" + value, {mct : mct_old,sus_no : sus_no
			}, function(data1) {
			}).done(function(data1) {
				var length = data1.length - 1;
				var enc = data1[length].substring(0, 16);

				if (dec(enc, data1[0]) == "") {
					$("#uh").val(0);
				} else {
					$("#uh").val(dec(enc, data1[0]));
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
			
			$.post("getUHFromMCT?" + key + "=" + value, {mct : new_mct,sus_no : sus_no
			}, function(data1) {
			}).done(function(data1) {
				var length = data1.length - 1;
				var enc = data1[length].substring(0, 16);

				if (dec(enc, data1[0]) == "") {
					$("#mct_uh").val(0);
				} else {
					$("#mct_uh").val(dec(enc, data1[0]));
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
			var ba_no = $("#old_ba_no").val();
			$.post("GetClasification?" + key + "=" + value,{ba_no : ba_no,sus_no : sus_no
					}, function(data1) {
					}).done(function(data1) {
				var length = data1.length - 1;
				var enc = data1[length].substring(0, 16);

				if (dec(enc, data1[0]) == "") {
					$("#cl").val(0);
				} else {
					$("#cl").val(dec(enc, data1[0]));
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});

			$.post("GetKm?" + key + "=" + value, {
				ba_no : ba_no,
				sus_no : sus_no
			}, function(data1) {
			}).done(function(data1) {
				var length = data1.length - 1;
				var enc = data1[length].substring(0, 16);

				if (dec(enc, data1[0]) == "") {
					$("#km").val(0);
				} else {
					$("#km").val(dec(enc, data1[0]));
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			});
			
			var currentDate = new Date();
			var ba_no_cal = parseInt(ba_no.substring(0, 2)); // Convert to integer

			var currentYear = currentDate.getFullYear();
			var lastTwoDigits = parseInt(currentYear.toString()
					.substring(2)); // Convert to integer
			var year_add = 100;

			var vintage_cal = year_add + lastTwoDigits - ba_no_cal;
			
			var vintagelasttwodig =  ("0" + (vintage_cal % 100)).slice(-2) ;
			var final_vintag = parseInt(vintagelasttwodig.toString());
			$("#vintage").val(final_vintag + 1);
			

			
			
			 var prf_code = $("#prf_code").val();
			
			$.post("GetPrfNc?" + key + "=" + value, {
				prf_code : prf_code
			}, function(data) {
			}).done(function(data) {
				if (data.length == 0) {
					$("#prf_nomen").val(0);
				} else {
					var length = data.length - 1;
					var enc = data[length].substring(0, 16);
					$("#prf_nomen").val(dec(enc, data[0]));
				}
			}).fail(function(xhr, textStatus, errorThrown) {
			}); 
			
			
			 var mct_code = $("#mct_code").val();
			
				
				$.post("GetMctNc?" + key + "=" + value, {
					mct_code : mct_code
				}, function(data) {
				}).done(function(data) {
					if (data.length == 0) {
						$("#mct_nomen").val(0);
					} else {
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						$("#mct_nomen").val(dec(enc, data[0]));
					
					}
				}).fail(function(xhr, textStatus, errorThrown) {
				});
			
			
			
			
			
			

			
			$('#sus_no').change(function() {
				var sus_no = this.value;
		
						 	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
						 		if(j != "")
								{
									var length = j.length-1;
						        	var enc = j[length].substring(0,16);
						        	$("#unit_name").val(dec(enc,j[0]));	
								
								 		$.ajax({
										    type: 'POST',
										    url: "getBA_NumberList?"+key+"="+value,
										    data: {val : sus_no },
										 
										    success: function(response) {
										        response.sort();
										      	var countryArray = response;
										      	var dataCountry = {};
										      	for (var i = 0; i < countryArray.length; i++) {
										        	dataCountry[countryArray[i]] = countryArray[i]; //countryArray[i].flag or null
										      	}
										      
										      	$('#old_ba_no.autocomplete').autocomplete({
										        	data: dataCountry,
										        	limit : 10
										      	});
										    }
										});
								 	$('#old_ba_no').change(function() {
											var ba_no = this.value;
									
											 	$.post("getEngineChasisFromBANoList?"+key+"="+value, {ba_no:ba_no}).done(function(j) {				
											 		var length = j.length-1;
											         var enc = j[length][0].substring(0,16);
											        
											          $("#chasis_no").val(dec(enc,j[0][0]));
											          $("input#engine_no").val(dec(enc,j[0][1]));
											          $("#old_mct_number").val(dec(enc,j[0][2]));
											          $("#old_std_nomclature").val(dec(enc,j[0][3]));
											          $("#old_vcode").val(ba_no[2]);
									           		 if(ba_no[2] == "B")
									           			 {
									           					 $("input#vehicle_class_code").val("F");
									           			 }
									           		 else if(ba_no[2] == "C")
											           		{
									           					 $("input#vehicle_class_code").val("N");
									           				 }
									           		 else if(ba_no[2] == "D")
									           			 {
									           						$("input#vehicle_class_code").val("P");
									           			 }
									           		 else
									           			 {
									           			 		alert("old vehicle code is not valid.");
									           			 }
									}).fail(function(xhr, textStatus, errorThrown) {
									});
							});
						}
							}).fail(function(xhr, textStatus, errorThrown) {
						});							
	});
		
		function checkFileExtImage(file) {
		  	var ext = file.value.match(/\.([^\.]+)$/)[1];
			switch (ext) {
			  	case 'jpg':
			  	case 'jpeg':
			  	case 'png':
			  	case 'JPG':
			  	case 'JPEG':
			  	case 'PNG':
			 	alert('Allowed');
			    break;
			  	default:
			    	alert('Only *.jpg, *.jpeg and *.png file extensions allowed');
			   	file.value = "";
			}
		}
		
		function getCONTCorps(fcode) {
			var fcode1 = fcode[0];
			$.post("getCorpsDetailsList?" + key + "=" + value, {
				fcode : fcode1
			}, function(j) {
				if (j != "") {
					var length = j.length - 1;
					var enc = j[length][0].substring(0, 16);
					var options = '<option value="' + "0" + '">' + "--Select--"
							+ '</option>';

					for (var i = 0; i < length; i++) {
						if ('${corp_sus}' == dec(enc, j[i][0])) {
							options += '<option value="' + dec(enc, j[i][0])
									+ '" name="' + dec(enc, j[i][1])
									+ '" selected=selected >' + dec(enc, j[i][1])
									+ '</option>';
						} else {
							options += '<option value="' + dec(enc, j[i][0])
									+ '" >' + dec(enc, j[i][1]) + '</option>';
						}
					}
					$("select#cont_corps").html(options);
				}
			});
		}
		function getCONTDiv(fcode) {
			var fcode1 = fcode[0] + fcode[1] + fcode[2];
			$.post("getDivDetailsList?" + key + "=" + value, {
				fcode : fcode1
			}, function(j) {
				if (j != "") {
					var length = j.length - 1;
					var enc = j[length][0].substring(0, 16);
					var options = '<option value="' + "0" + '">' + "--Select--"
							+ '</option>';
					for (var i = 0; i < length; i++) {
						if ('${div_sus}' == dec(enc, j[i][0])) {
							options += '<option value="' + dec(enc, j[i][0])
									+ '" name="' + dec(enc, j[i][1])
									+ '" selected=selected >' + dec(enc, j[i][1])
									+ '</option>';
						} else {
							options += '<option value="' + dec(enc, j[i][0])
									+ '" >' + dec(enc, j[i][1]) + '</option>';
						}
					}
					$("select#cont_div").html(options);
				}
			});
		}
		function getCONTBde(fcode) {
			var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4]
					+ fcode[5];
			$.post("getBdeDetailsList?" + key + "=" + value, {
				fcode : fcode1
			}, function(j) {
				if (j != "") {
					var length = j.length - 1;
					var enc = j[length][0].substring(0, 16);
					var options = '<option value="' + "0" + '">' + "--Select--"
							+ '</option>';
					jQuery("select#cont_bde").html(options);
					for (var i = 0; i < length; i++) {
						if ('${bde_sus}' == dec(enc, j[i][0])) {
							options += '<option value="' + dec(enc, j[i][0])
									+ '" name="' + dec(enc, j[i][1])
									+ '" selected=selected>' + dec(enc, j[i][1])
									+ '</option>';
							$("#cont_bname").val(dec(enc, j[i][1]));
						} else {
							options += '<option value="' + dec(enc, j[i][0])
									+ '" name="' + dec(enc, j[i][1]) + '">'
									+ dec(enc, j[i][1]) + '</option>';
						}
					}
					$("select#cont_bde").html(options);
				}
			});
		}
		
</script>