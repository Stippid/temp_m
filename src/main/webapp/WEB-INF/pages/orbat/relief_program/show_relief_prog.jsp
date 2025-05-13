<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="updateRelief" action="updateRelief" method="post" commandName="relief_progCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">
		    		<div style="display: none;">
            				<form:input path="sd_status"/>
            				<form:input path="sd_created_by"/>
            				<form:input path="sd_created_on"/>
            				<form:input path="sd_updated_on"/>
            				<form:input path="sd_updated_by"/>
            				<form:input path="approved_by_sd"/>
            				<form:input path="approved_on_sd"/>
            				<form:input path="miso_status"/>
            				<form:input path="miso_approved_on"/>
            				<form:input path="miso_approved_by"/>
            				<input  type="hidden" id="id" name="id"  value="${relief_progCMD.id}" class="form-control">
					        <input  type="hidden" id="mode_of_tpt_check" name="mode_of_tpt_check"  value="${relief_progCMD.mode_of_tpt}" class="form-control">
           			</div>
    			    <h5><b>UPDATE RELIEF PROGRAME</b></h5>
          		    <div class="card-header"> <strong>Relief Pgme Auth Details</strong> </div>
          			<div class="card-body card-block">
            			<div class="col-md-6">
							<div class="row form-group">
               					<div class="col col-md-4">
                 					<label class=" form-control-label"><strong style="color: red;">* </strong>Auth Letter No </label>
               					</div>
               					<div class="col-12 col-md-8">
                 					<input type="text" id="auth_let_no" name="auth_let_no"  maxlength="250" class="form-control" value="${relief_progCMD.auth_let_no}" readonly>
               					</div>
             				 </div>
           					<div class="row form-group" style="padding-top: 10px;">
               			         <div class="col col-md-4">
                 			           <label class="form-control-label"><strong style="color: red;">* </strong>Period (From)</label>
              					</div>
						        <div class="col-12 col-md-8">
                  					<input type="month" id="period_from" name="period_from" maxlength="10" class="form-control"  value="${relief_progCMD.period_from}" readonly>
                				</div>
							</div>
							<div class="row form-group" style="padding-top: 10px;">
               			         <div class="col col-md-4">
                 			           <label class="form-control-label"><strong style="color: red;">* </strong>Ser No of letter </label>
              					</div>
						        <div class="col-12 col-md-8">
                  					<input type="text" id="ser_no" name="ser_no" maxlength="10" class="form-control"  value="${relief_progCMD.ser_no}" readonly>
                				</div>
							</div>							
						</div>
					 	<div class="col-md-6 col-md-offset-1">
							<div class="row form-group">
               					<div class="col col-md-4">
                 					<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date (From) </label>
               					</div>
               					<div class="col-12 col-md-8">
                 					<input type="date" id="date1" name="date1"  maxlength="10" class="form-control" value="${relief_progCMD.date1}" readonly>
								</div>
 							</div>
							<div class="row form-group">
              			    	<div class="col col-md-4">
                					<label class=" form-control-label"><strong style="color: red;">* </strong>Period (To)</label>
								</div>
								<div class="col-12 col-md-8">
                 					<input type="month" id="period_to" name="period_to" maxlength="10" class="form-control" pattern="mm/yyyy" value="${relief_progCMD.period_to}" readonly>
								</div>
							</div> 
            			    <div class="row form-group">
                				<%-- <div class="col col-md-4">
                  					<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Download Document</label>
                				</div>
                				<div class="col-12 col-md-8">
                				   <input type="text" id="arr_dep_report" name="arr_dep_report" class="form-control"   readonly="readonly" value="${relief_progCMD.arr_dep_report}">
                  					<a hreF="#" onclick="getDownloadImage('${relief_progCMD.id}')" class="btn btn-primary btn-sm">Download</a>
								</div> --%>
  							</div>
						</div>
          			</div>
			        <div class="card-header" style="border: 1px solid rgba(0,0,0,.125);"> <strong>Relief Pgme Details</strong></div>
						<div class="card-body card-block">
							<div class="col-md-6">
   						        <div class="row form-group">
                                	<div class="col col-md-4">
                                                <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No </label>
                                     </div>
                                    <div class="col-12 col-md-8">
  						               <input type="text" id="sus_no" name="sus_no" maxlength="8"  class="form-control autocomplete" value="${relief_progCMD.sus_no}" autocomplete="off" >
     					            </div>
                                </div>
                                     
								<div class="row form-group">
     								<div class="col col-md-4">
       									<label class=" form-control-label"><strong style="color: red;">* </strong>Imdt Higher FMN</label>
     								</div>
             						<div class="col-12 col-md-8">
       						        	<select  class="form-control-sm form-control" tabindex="1" id="imdt_fmn" name="imdt_fmn">
       						            	<option value="0">--Select--</option>
											<c:forEach var="item" items="${getImdtFmnList}" varStatus="num" >
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
											</c:forEach>
                    					</select>	
     					            </div>
        						</div>
								<div class="row form-group">
				                <div class="col col-md-4">
				                  <label class=" form-control-label"><strong style="color: red;">* </strong>Mode of Tpt </label>
				                </div>
						      	<div class="col-12 col-md-8">
		                			<div class="form-check-inline form-check">
                				        <label for="inline-checkbox1" class="form-check-label ">
                				        														<input type="checkbox" id="air" name="mode_of_tpt" value="air"  class="form-check-input air">Air</label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-checkbox2" class="form-check-label "><input type="checkbox" id="train" name="mode_of_tpt" value="train" class="form-check-input train">Train</label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-checkbox3" class="form-check-label "><input type="checkbox" id="road" name="mode_of_tpt" value="road" class="form-check-input road">Road</label>&nbsp;&nbsp;&nbsp;
		                                <label for="inline-checkbox4" class="form-check-label "><input type="checkbox" id="others" name="mode_of_tpt" value="others" class="form-check-input others">Others</label>&nbsp;&nbsp;&nbsp;
              						</div>          
              					</div>
            				</div>
            				<div class="row form-group">
				                <div class="col col-md-4">
				                  <label class=" form-control-label"><strong style="color: red;">* </strong>Indn/De-Indn pd</label>
				                </div>
				                <div class="col-12 col-md-8">
              						        	<select  class="form-control-sm form-control" tabindex="1" id="indn_de_indn_pd" name="indn_de_indn_pd">
	                                    <option value="0 Week">0 Week</option>
	                                    <option value="1 Week">1 Week</option>
	                                    <option value="2 Week">2 Week</option>
	                                    <option value="3 Week">3 Week</option>
	                                    <option value="4 Week">4 Week</option>
	                                    <option value="5 Week">5 Week</option>
	                                    <option value="6 Week">6 Week</option>
	                                    <option value="7 Week">7 Week</option>
	                                    <option value="8 Week">8 Week</option>
	                                    <option value="9 Week">9 Week</option>
	                                    <option value="10 Week">10 Week</option>
	                                    <option value="11 Week">11 Week</option>
	                                    <option value="12 Week">12 Week</option>
	                                    <option value="13 Week">13 Week</option>
	                                    <option value="14 Week">14 Week</option>
	                                    <option value="15 Week">15 Week</option>
				                	</select>	                					            
				                </div>
				          	</div>
				          	
				          	
				          <div class="row form-group">
			                    <div class="col col-md-4">
	                                <label class=" form-control-label" placeholder="Unit move on relief or NMB date" ><strong style="color: red;">* </strong>On Relief</label>
			                    </div>
                			    <div class="col-12 col-md-8">
                			             <label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
										<input type="radio" id="answer1" name="relief_yes_no"  value="1" class="form-check-input"  placeholder="Yes,Unit move on relief not on NMB date">Yes
										<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
									    <label for="inline-radio1" class="form-check-label "> </label> 
									    <input type="radio" id="answer2" name="relief_yes_no" value="0"  class="form-check-input" placeholder="Unit move on NMB date">No
						                	   								            
								 </div>
              			  </div>
						              	
		                      <div class="row form-group">
	           								   <div class="col col-md-4">
	                                                    <label class=" form-control-label"><strong style="color: red;">* </strong> To Location </label>
	                                              </div>
	                                              <div class="col-12 col-md-8">
	              						            <input type="text" id="code" name="code" maxlength="5" class="form-control"  style="width:86%;display: inline-block;" readonly="readonly" value="${LOC_NRS_TypeLOC_TrnType[0]}">
						                			<input type="hidden" id="loc" name="loc" maxlength="100" class="form-control" style="width:100%;" value="${relief_progCMD.loc}">
						                			<input type="hidden" id="typ_of_stn" name="type_of_stn" placeholder="" class="form-control" value="${relief_progCMD.typ_of_stn}">
	              						            <input type="hidden" id="typ_of_terrain" name="type_of_terrain" placeholder="" class="form-control" value="${relief_progCMD.typ_of_terrain}">						                			
				              			<i class="fa fa-search" onclick="openLocLOV();"></i>	     
	            					            </div>
	                          </div>
	                          
	                          
	                          
 	                         <div class="row form-group">
	                                             <div class="col col-md-4">
	                                                      <label class=" form-control-label">Type of Cl </label>
	                                             </div>
	                                             <div class="col-12 col-md-8">
	                                             	<select class="form-control-sm form-control" tabindex="1" id="type_of_cl" name ="type_of_cl">
																<option value="NA">Not Avbl</option>	                                             	
							                                    <option value="Siachen">Siachen</option>                 						               
							                                    <option value="HAA">HAA</option>
							                                    <option value="Fd">Fd</option>
							                                    <option value="CI">CI</option>
							                                    <option value="LC">LC</option>							                                    
							                                    <option value="A">A</option>
																<option value="B">B</option>
							                                    <option value="C">C</option>							                                    
							                      				</select>	 
	            					          </div>
	      	                 </div>
 	      	                  
	                               <div class="row form-group">
	           								   <div class="col col-md-4">
	                                                    <label class=" form-control-label">Move of Adv Party Date</label>
	                                              </div>
	                                              <div class="col-12 col-md-8">
	              						             <input type="date" id="mov_of_adv_party_dt" name="mov_of_adv_party_dt" placeholder="" class="form-control" onchange="return checknmbdate(this,nmb_date)"  value="${relief_progCMD.mov_of_adv_party_dt}"> 
	              						       </div>
						    </div>
						           
						    <div class="row form-group">
            								   <div class="col col-md-4">
                                                     <label class=" form-control-label">To Unit SUS No </label>
                                               </div>
                                               <div class="col-12 col-md-8">
               						            <input type="text" id="rplc_by_unit_sus_no" name="rplc_by_unit_sus_no" maxlength="8"  readonly="readonly"  class="form-control autocomplete" value="${relief_progCMD.rplc_by_unit_sus_no}">
             					           </div>
                                         </div>
                                 </div>
                                 
                  	<div class="col-md-6 col-md-offset-1">
                                       <div class="row form-group">
             								<div class="col col-md-4">
               									<label class=" form-control-label"><strong style="color: red;">* </strong>Unit Name </label>
             								</div>
					               <div class="col-12 col-md-8">
               						             <input type="text" id="unit_name" name="unit_name" maxlength="100"  class="form-control autocomplete" value="${unit_name}" autocomplete="off">
             					            </div>
					           	</div>
                                       <div class="row form-group" style="padding-top: 10px;">
                                     <!--   <div class="col col-md-4">
                                            <label class=" form-control-label"><strong style="color: red;">* </strong>Arm Name </label>
                                      </div> -->
                                      <div class="col-12 col-md-8">
                                      			 <input type="hidden" id="arm_name" name="arm_name" maxlength="100" class="form-control" >
               						          <!--  	<select class="form-control-sm form-control" tabindex="1" id="arm_name" name="arm_name">
               						               		<option value="0">--Select--</option>
	                  							<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
	                  								<option value="${item.arm_code}" >${item.arm_desc}</option>
	                  							</c:forEach>
					                       </select> -->	
             					              </div>
             	              </div>
								         <div class="row form-group">
			                                         <div class="col col-md-4">
			                                                <label class=" form-control-label"><strong style="color: red;">* </strong>NMB Date </label>
			                                         </div>
                			                         <div class="col-12 col-md-8">
	                  						             <input type="date" id="nmb_date" name="nmb_date" maxlength="10" class="form-control" onchange = "return checkadvdate(this,mov_of_adv_party_dt)" value="${relief_progCMD.nmb_date}">
										             </div>
              			                </div>
						               
					
						               
						               
										<div class="row form-group" style="padding-top: 30px;">
					                				<div class="col col-md-4">
					                  					<label class=" form-control-label">NRS </label>
					                				</div>
					                				<div class="col-12 col-md-8">
	                  						               <%-- <input type="text" id="nrs" name="nrs" placeholder="" class="form-control" value="${relief_progCMD.nrs}">
	                  						               <input type="hidden" id="nrs_code" name="nrs_code" placeholder="" class="form-control"> --%>
	                  						               <input type="text" id="nrs" name="nrs" maxlength="100"  class="form-control" readonly="readonly" value="${LOC_NRS_TypeLOC_TrnType[1]}">
                 						        			<input type="hidden" id="nrs_code" name="nrs_code"  maxlength="5" class="form-control" readonly="readonly" value="${LOC_NRS_TypeLOC_TrnType[5]}">
                 						        			<input type="hidden" id="typ_of_stn" name="typ_of_stn"  maxlength="100" class="form-control" readonly="readonly" value="${LOC_NRS_TypeLOC_TrnType[4]}">
                 						        			<input type="hidden" id="typ_of_terrain" name="typ_of_terrain"  maxlength="100" class="form-control" readonly="readonly" value="${LOC_NRS_TypeLOC_TrnType[3]}">
	                					            </div>
			              				</div>
			              								          			
				          				<div class="row form-group">
					                				<div class="col col-md-4">
														<label for="text-input" class=" form-control-label">Remarks</label>
					                				</div>
					                				<div class="col-12 col-md-8">
	                  						                <textarea  id="remarks" name="remarks"  maxlength="255" class="form-control char-counter1" value="${relief_progCMD.remarks}"> </textarea>
	                					            </div>
			              				</div> 
				          			
<%-- 			              				<div class="row form-group">
					                				<div class="col col-md-4">
					                  					<label class=" form-control-label">Type of terrain </label>
					                				</div>
					                				<div class="col-12 col-md-8">
	                  						               <input type="text" id="typ_of_terrain" name="typ_of_terrain" class="form-control" value="${relief_progCMD.typ_of_terrain}">
	                  						               <input type="text" id="typ_of_terrain" name="typ_of_terrain"  maxlength="100" class="form-control" readonly="readonly" value="${LOC_NRS_TypeLOC_TrnType[3]}">
	                					            </div>
			              				</div> 
 --%>			              				<div class="row form-group" style="padding-top: 80px;">
                                                    <div class="col col-md-4">
                                                          <label class=" form-control-label">To Unit Name </label>
                                                    </div>
                                                    <div class="col-12 col-md-8">
                                                          <input type="text" id="rplc_by_unit_name" name="rplc_by_unit_name" maxlength="100"  class="form-control autocomplete" value="${rplc_by_unit_name}">
                                                    </div>
                                        </div>    
              			        	</div>
	              				</div>
	                
					            <div class="card-footer" align="center">
				                    <input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();">		
				  					<a href="approved_sd_reliefReport" type="reset" class="btn btn-danger btn-sm"> Cancel </a>
					            </div> 
		            
	        				</div>
	    			</div>
			</div>
	</div>
</form:form>

<c:url value="getDownloadImageRelief" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="id">
	<input type="hidden" name="id1" id="id1" value="0"/>
</form:form> 

<script>

function checknmbdate(obj,validateDate) {
	if(validateDate.value !="")
	{
		var id = obj.id;
		var myDate = document.getElementById(id).value;
		var Date1 = validateDate.value;
		if ((Date.parse(myDate) > Date.parse(Date1))) {
			alert('You cannot select more than NMB Date.');
			obj.value = "";
		}
	}
	else
	{
			alert("Please Select From Date first.");
			obj.value = "";
	}
}

function checkadvdate(obj,validateDate) {
	if(obj.value !="")
	{
		var id = obj.id;
		var myDate = document.getElementById(id).value;
		var Date1 = validateDate.value;
		if ((Date.parse(myDate) > Date.parse(Date1))) {
			alert('You cannot select More than Adv party Date.');
			validateDate.value = "";
		}
	}
	else
	{
			alert("Please Select From Date first.");
			obj.value = "";
	}
}

</script>

<script>

$(function() {
	$("#unit_name").keypress(function(){
		var unit_name = this.value;
		 var susNoAuto=$("#unit_name");
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
		        	  alert("Please Enter Approved Unit Name");
		        	  document.getElementById("unit_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	$(this).val(ui.item.value);
		      	var unit_name = ui.item.value;
		    	$.post("getActiveSusNoFromUnitName?"+key+"="+value,{unit_name:unit_name}, function(j) {
		    		if(j.length != 0){
		    			var length = j.length-1;
						var enc = j[length][0].substring(0,16);
			   			$("#sus_no").val(dec(enc,j[0][0]));
			   	 		$("#arm_name").val(dec(enc,j[0][1]));
		    		}
			   	});
		    } 	     
		});
	}); 
	$("#rplc_by_unit_name").keypress(function(){
		var unit_name = this.value;
		var susNoAuto=$("#rplc_by_unit_name");
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
		        	  alert("Please Enter Approved Unit Name");
		        	  document.getElementById("rplc_by_unit_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	$(this).val(ui.item.value);
		      	var unit_name = ui.item.value;
		      	$.post("getActiveSusNoFromUnitName?"+key+"="+value,{unit_name:unit_name}, function(j) {
		      		if(j.length != 0){
		    			var length = j.length-1;
						var enc = j[length][0].substring(0,16);
			   			$("#rplc_by_unit_sus_no").val(dec(enc,j[0][0]));
		      		}
			   	});
		    } 	     
		});
	}); 
    	   // Source Sus No auto
   		$("input#sus_no").keyup(function(){
   			var sus_no = this.value;
   			var unitNameAuto=$("#sus_no");
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
   			        	  alert("Please Enter Approved SUS NO");
   			        	  $("#unit_name").val("");
   			        	  unitNameAuto.val("");	        	  
   			        	  unitNameAuto.focus();
   			        	  return false;	             
   			          }   	         
   			      }, 
   			      select: function( event, ui ) {
   			      	var sus_no = ui.item.value;
   			      	$.post("getSusNoDetailsList?"+key+"="+value,{sus_no:sus_no}, function(j) {
	   			      	if(j.length != 0){
	   	        			var length = j.length-1;
							var enc = j[length][0].substring(0,16);
				   			$("#unit_name").val(dec(enc,j[0][0]));
				   			$("#arm_name").val(dec(enc,j[0][1]));
	   	        		}
   	   	        	});
   			     }
   			});
   		});
      	$("input#rplc_by_unit_sus_no").keyup(function(){
			var sus_no = this.value;
			var unitNameAuto=$("#rplc_by_unit_sus_no");
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
			        	  alert("Please Enter Approved SUS NO");
			        	  $("#unit_name").val("");
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
				   		$("#rplc_by_unit_name").val(dec(enc,j[0]));
			       	});
			     }
			});
		});
	});
	function getDownloadImage(id)
    {  
	   	document.getElementById("id1").value=id;
      	document.getElementById("getDownloadImageForm").submit();
    }
</script>

<script>
$(document).ready(function() {
	$("#indn_de_indn_pd").val('${relief_progCMD.indn_de_indn_pd}');
    $("#arm_name").val('${relief_progCMD.arm_name}');
	$("select#imdt_fmn").val('${relief_progCMD.imdt_fmn}');
	$("select#type_of_cl").val('${relief_progCMD.type_of_cl}');
	$("textarea#remarks").val('${relief_progCMD.remarks}');
/*     
	$("#typ_of_stn").val('${relief_progCMD.typ_of_stn}');
    $("#typ_of_terrain").val('${relief_progCMD.typ_of_terrain}');
 */    
 
	var mode_of_tpt = '${relief_progCMD.mode_of_tpt}';
    var box = []
	box = mode_of_tpt.split(",");
	for (var i=0; i<box.length; i++){
		$("#"+box[i]).prop("checked", true);
	}
	
      var relief_yesno = '${relief_progCMD.relief_yes_no}';
      
	 	 if(relief_yesno == '1')
	 	{
	 		$("#answer1").prop("checked", true);
     		$("#answer1").attr('checked', 'checked');
	 	}
	 	else
	 	{
	       	$("#answer2").prop("checked", true);
	 	 	$("#answer2").attr('checked', 'checked');
	 	} 
	 		
});
function validate(){
  	if($("#auth_let_no").val() == ""){
  		alert("Please Enter the Auth Letter Number");
  		return false;
  	}
  	if($("#date1").val() == ""){
  		alert("Please Enter the Date");
  		return false;
 	}
  	if($("#period_from").val() == ""){
  		alert("Please Enter the Period From");
  		return false;
   	}
  	if($("#period_to").val() == ""){
  		alert("Please Enter the Period To");
  		return false;
   	}
  	if($("#upload_document1").get(0).files.length === 0){
  		alert("Please Upload the Document");
  		return false;
   	}
  	if($("#sus_no").val() == ""){
		alert("Please select SUS No");
		return false;
  	}
	if($("#imdt_fmn").val() == "0"){
		alert("Please select Imdt Fmn");
		return false;
  	}
  	if($("#arm_name").val() == "0"){
  		alert("Please select Arm Name");
  		return false;
  	}
  	if($("#nmb_date").val() == "" ){
  		alert("Please select NMB Date");
  		return false;
  	}
 	if($("#mov_of_adv_party_dt").val() == "" ){
  		alert("Please select Adv Party Date");
  		return false;
  	}  	
  	if($("#indn_de_indn_pd").val() == ""){
  		alert("Indn/De-Indn pd");
  		return false;
  	}
  	if($("#loc").val() == ""){
  		alert("To Location");
  		return false;
  	}
  	return true;
}
function openLocLOV(url) {
	popupWindow = window.open("locationLOV", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=900,height=600,fullscreen=no");
}
function HandlePopupResult(loc,nrs_name,loc_code,trn_type,typeofloc,nrscode) {
	$("#code").val(loc);
	$("#loc").val(loc_code);
	$("#nrs").val(nrs_name);
	$("#nrs_code").val(nrscode);
	$("#typ_of_terrain").val(trn_type);
	$("#typ_of_stn").val(typeofloc);
}

</script>