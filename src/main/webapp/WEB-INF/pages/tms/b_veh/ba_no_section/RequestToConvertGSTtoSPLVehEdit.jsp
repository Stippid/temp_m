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

		
<form:form name="tmsbanumdir" id="tmsbanumdir" action="editconvertRequestGstosplUpdateAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="editconvertRequestGstosplVehDTLCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5>CONVERSION GS TO SPL VEH : UPDATE</h5> <h6 class="enter_by">(To be entered by MISO)</h6></div><div class="card-header"><strong style="float:center;">Basic Details</strong> </div>
	            		<div class="card-body card-block" id="mainViewSelection" style="display: block;">
	            			<div class="col-md-12">
								<div class="col-md-6">
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
								<div class="col-md-6">
									<div class="row form-group">
	                					<div class="col col-md-4">
                  							<label class=" form-control-label">Unit Name  </label>
	                					</div>
	                						<div class="col-12 col-md-8">
										<textarea id="unit_name" name="unit_name" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" ></textarea>
									     </div>
	  								</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-6">
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
                             <div class="col-md-6" style="display: none;">
                                      <div class="row form-group">
	                					<div class="col col-md-4">
                  							<label class=" form-control-label"><strong style="color: red;">* </strong>Recd From</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						
	                  							<input type="hidden" id="received_from1" name="received_from1" placeholder="" value="${editconvertRequestGstosplVehDTLCMD.received_from}" class="form-control autocomplete" autocomplete="off">               					
	                			
	                  						<input type="text" readonly="readonly" class="form-control" id="received_from" value = "" name="received_from"  placeholder="" maxlength="50">
	                  					</div>
	  								</div>
                                                  </div>
                                          </div>
							
						</div>
						
						<div class="card-header" align="center"> <strong>Vehicle Details</strong> </div>
							
						<div class="card-body card-block">
	            			<div class="col-md-12">
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label">Old BA No </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="old_ba_no" readonly="readonly" name="old_ba_no" placeholder="" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.old_ba_no}" class="form-control autocomplete" autocomplete="off" maxlength="10">
	                					</div>
	              					</div>
                                                  </div>
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Chassis No</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="chasis_no" name="chasis_no" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.chasis_no}"  placeholder="" class="form-control" maxlength="20">
										</div>
	  								</div>
                                                  </div>
                                          </div>
							<div class="col-md-12">
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Engine No  </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" class="form-control" id="engine_no" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.engine_no}" name="engine_no" placeholder="" maxlength="20">
										</div>
									</div>
                                                  </div>
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label"> Class Code of Old Veh </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" class="form-control" id="old_vcode" readonly="readonly"  name="old_vcode" placeholder="">
										</div>
									</div>
                                                  </div>
                                          </div>
	              					
	              					<div class="col-md-12">
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Old MCT  </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" class="form-control" id="old_mct_number" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.old_mct_number}"  name="old_mct_number" onchange="getstdNomenclature()" placeholder="" maxlength="10">
										</div>
									</div>
									
                                                  </div>
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label">Old Nomenclature </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="old_std_nomclature" name="old_std_nomclature" readonly="readonly"  placeholder="" class="form-control">
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
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Remarks  </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="hidden" class="form-control" id="rem" readonly="readonly" value="${editconvertRequestGstosplVehDTLCMD.remarks}" name="old_vcode" placeholder="">
	                  					
	                  						<textarea class="form-control" id="remarks" name="remarks" maxlength="255" ></textarea> 
								
										</div>
									</div>
                                                  </div>
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label for="text-input" class=" form-control-label">New MCT </label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input type="text" class="form-control autocomplete" autocomplete="off" value="${editconvertRequestGstosplVehDTLCMD.new_mct_number}"  id="new_mct_number" name="new_mct_number" placeholder="" maxlength="10">
											</div>
										</div>
                                                  </div>
                                          </div>
	              					
	  								<div class="col-md-12">
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label class=" form-control-label">Auth Of MoD</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input type="text" class="form-control" id="auth_letter_no" value="${editconvertRequestGstosplVehDTLCMD.auth_letter_no}" name="auth_letter_no" placeholder="" maxlength="10">
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
		                  						<textarea rows="2" id="new_nomencatre" readonly="readonly"  value="${editconvertRequestGstosplVehDTLCMD.new_nomencatre}" name="new_nomencatre" maxlength="100" style="width: 100%;"></textarea>
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
		                  						<input type="text" id="convrsn_done" name="convrsn_done" placeholder="" value="${editconvertRequestGstosplVehDTLCMD.convrsn_done}" class="form-control" maxlength="5">
											</div>
		  								</div>
                                                  </div>
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label for="text-input" class=" form-control-label"> <strong style="color: red;">* </strong>Class Code of New Veh</label>
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
												<label class=" form-control-label">Front View Image<br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions are allowed.)</span></label>
											</div>
											<div class="col-12 col-md-8">
												<input type="file" id="front_view_image" name="front_view_image1" class="form-control">
											</div>
										</div>
                                                  </div>
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
											<div class="col col-md-4">
												<label class=" form-control-label">Rear View Image<br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions are allowed.)</span></label>
											</div>
											<div class="col-12 col-md-8">
												<input type="file" id="top_view_image1" name="side_view_image1" class="form-control">
											</div>
										</div>
                                                  </div>
                                          </div>
									<div class="col-md-12">
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
											<div class="col col-md-4">
												<label class=" form-control-label">Left Side View Image<br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions are allowed.)</span></label>
											</div>
											<div class="col-12 col-md-8">
												<input type="file" id="back_view_image1" name="back_view_image1" class="form-control">
											</div>
										</div>
                                                  </div>
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
												<div class="col col-md-4">
													<label class=" form-control-label">Right Side View Image<br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions are allowed.)</span></label>
												</div>
												<div class="col-12 col-md-8">
													<input type="file" id="top_view_image1" name="top_view_image1" class="form-control">
												</div>
											</div>
                                                  </div>
                                          </div>
                                          <div class="col-md-12">
                                                  <div class="col-md-6">
                                                  <div class="row form-group">
											<div class="col col-md-4">
												<label class=" form-control-label">Front View Image<br> <span style="font-size: 10px;font-size:12px;color:red">(Only *.jpg, *.jpeg and *.png file extensions are allowed.)</span></label>
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
					
	            					</div> 
							</div>
						</div>
					</div>
						<div  class="form-control card-footer" align="center" id="btnhide" style="display: block;">
						<div class="form-control card-footer" align="center">
							<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return check();"> 
							<input type="hidden" id="count" name="count">
						</div>
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
   		 else if(ba_no[2] == "E")
			 {
						$("input#vehicle_class_code").val("R");
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
		var veh_class_code= $("#vehicle_class_code").val();
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
		        url: "getMctNoDetailListbyclasscode?"+key+"="+value,
		        data: {mct:mct ,type_of_vehicle:type_of_vehicle,veh_class_code:veh_class_code},
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
									           		 else if(ba_no[2] == "E")
								           			 {
								           						$("input#vehicle_class_code").val("R");
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
</script>