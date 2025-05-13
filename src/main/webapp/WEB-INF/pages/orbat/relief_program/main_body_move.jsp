<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%> <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
${jsCSS}

<form:form name="main_bodyForm" id="main_bodyForm" action="mainbody_Action?${_csrf.parameterName}=${_csrf.token}" method="post" class="form-horizontal" commandName="main_bodyCMD" enctype="multipart/form-data"> 
	<div class="animated fadeIn">
		<div class="row">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5><b>MAIN BODY MOVEMENT</b><br></h5><h6><span style="font-size:12px;color:red;">(To be entered by User)</span></h6> <strong>Main Body Move Auth Details</strong></div>
	    				<div class="card-body">
	          				<div class="col-md-12">
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label class=" form-control-label"><strong style="color: red;">* </strong> Auth Letter No </label>
	                					</div>
	                					<div class="col-md-8">
	                  						<input type="text" id="unit_auth_letter_no" name="unit_auth_letter_no"  maxlength="250" class="form-control">
	                					</div>
	              					</div>
								</div>
								<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-4">
	                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Date (From) </label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="date" id="unit_auth_letter_date" name="unit_auth_letter_date" maxlength="10" class="form-control" max="${date}">
										</div>
	  								</div>
								</div>
							</div>
							<!-- </form> -->
	            		</div>
						<div class="card-header" style="border: 1px solid rgba(0,0,0,.125);"> <strong>Main Body Move Details</strong></div>
							<div class="card-body">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
                                              <div class="col col-md-4">
                                                    <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No </label>
                                              </div>
                                             <div class="col-12 col-md-8">
           						               <input type="text" id="sus_no" name="sus_no"  maxlength="8" style="font-family: 'FontAwesome',Arial;" value="${sus_no}" placeholder="&#xF002; Search SUS No" class="form-control" autocomplete="off">
           						               <input type="hidden" id="id" name="id"  class="form-control" >
         					            	</div>
                                         </div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
               								<div class="col col-md-4">
                 								<label class=" form-control-label">Unit Name </label>
               								</div>
							               <div class="col-12 col-md-8">
                 						   		<input type="text" id="unit_name" name="unit_name" maxlength="100" style="font-family: 'FontAwesome',Arial;" value="${unit_name}" placeholder="&#xF002; Search Unit Name"  class="form-control" disabled="disabled">
               					           </div>
							           	</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
          						    	<div class="row form-group">
            								<div class="col col-md-4">
              									<label class=" form-control-label">Imdt Higher FMN</label>
            								</div>
				                			<div class="col-12 col-md-8">
            						        	<select id="imdt_fmn" name="imdt_fmn" class="form-control" disabled="disabled">
            						            	<option value="0">--Select--</option>
													<c:forEach var="item" items="${getImdtFmnList}" varStatus="num" >
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
            						        	</select>
            					           	</div>
				           				</div>
								   </div>
							      <div class="col-md-6">
							      		<div class="row form-group">
                                      <div class="col col-md-4">
                                            <label class=" form-control-label">Arm Name </label>
                                      </div>
                                      <div class="col-12 col-md-8">
               						      		<select id="arm_name" name="arm_name" class="form-control" disabled="disabled">
                						            	<option value="0">--Select--</option>
                  								<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
                  									<option value="${item.arm_code}" >${item.arm_desc}</option>
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
									                      <label class=" form-control-label">Mode of Tpt </label>
									                </div>
									                <div class="col-12 col-md-8">
					                				      <input type="text" id="mode_of_tpt" name="mode_of_tpt" maxlength="25"  class="form-control" disabled="disabled">
	                  						        </div>
	              								</div>
	              						</div>
	              						<div class="col-md-6">
	              							<div class="row form-group">
			                                	<div class="col col-md-4">
			                                    	<label class=" form-control-label">NMB Date </label>
												</div>
                			                    <div class="col-12 col-md-8">
	                  						        <input type="date" id="nmb_date" name="nmb_date"  maxlength="10" class="form-control" readonly="readonly">
										        </div>
              			                	</div>
									   	</div>
									 </div>
									 <div class="col-md-12">
										<div class="col-md-6">
												<div class="row form-group">
									                <div class="col col-md-4">
									                  <label class=" form-control-label">Indn/De-Indn pd</label>
									                </div>
									                <div class="col-12 col-md-8">
									                 	<input type="text" id="indn_de_indn_pd" name="indn_de_indn_pd" maxlength="10"  class="form-control" disabled="disabled">
									                </div>
									          	</div>
										</div>
										<div class="col-md-6">
												<div class="row form-group">
									                <div class="col col-md-4">
									                  <label class=" form-control-label"> On Relief </label>
									                </div>
									                <div class="col-12 col-md-8">
									                 	<input type="text" id="relief_yes_no" name="relief_yes_no" maxlength="10"  class="form-control" readonly="readonly">
									                </div>
									          	</div>
									    </div>
									  </div>
									<div class="col-md-12">
										<div class="col-md-6">
					                          <div class="row form-group">
                								   <div class="col col-md-4">
                                                         <label class=" form-control-label"> To Location </label>
                                                   </div>
                                                   <div class="col-12 col-md-8">
	                  						            <select id="loc" name="loc" class="form-control" disabled="disabled">
	                  						            	<option value="0">--Select--</option>
						                                    <c:forEach var="item" items="${getlocList}" varStatus="num" >
				                  								<option value="${item[0]}">${item[1]}</option>
				                  							</c:forEach>
	                  						            </select>
	                  						            <!-- <input type="text" id="code" name="code" class="form-control"  style="width:100%;display: inline-block;" readonly="readonly" >
							                 			<input type="hidden" id="loc" name="loc" class="form-control" style="width:100%;"> -->
					              				    </div>
                                             </div>
                                      	</div>
                                      	<div class="col-md-6">
                                      		<div class="row form-group">
					                			<div class="col col-md-4">
					                  				<label class=" form-control-label">NRS </label>
					                			</div>
					                			<div class="col-12 col-md-8">
	                  						    	<input type="text" id="nrs" name="nrs" maxlength="100"  class="form-control" disabled="disabled">
	                					    	</div>
			              					</div>
										</div>
									</div>
									 <div class="col-md-12" style="display: none;">
										<div class="col-md-6">
					                         <div class="row form-group">
                                                  <div class="col col-md-4">
                                                           <label class=" form-control-label">Type of stn </label>
                                                  </div>
                                                  <div class="col-12 col-md-8">
	                  						               <input type="text" id="typ_of_stn" name="typ_of_stn" maxlength="100"  class="form-control" disabled="disabled">
	                					          </div>
           	                                </div>
           	                         	</div>
           	                         	<div class="col-md-6">
           	                         		<div class="row form-group">
					                				<div class="col col-md-4">
					                  					<label class=" form-control-label">Type of terrain </label>
					                				</div>
					                				<div class="col-12 col-md-8">
	                  						               <input type="text" id="typ_of_terrain" name="typ_of_terrain" maxlength="100"  class="form-control" disabled="disabled">
	                					            </div>
			              					</div> 
									    </div>
									</div>
									 <div class="col-md-12">
										<div class="col-md-6">
			                                 <div class="row form-group">
                								   <div class="col col-md-4">
                                                         <label class=" form-control-label">Move of Adv Party Date</label>
                                                   </div>
                                                   <div class="col-12 col-md-8">
	                  						             <input type="date" id="mov_of_adv_party_dt" name="mov_of_adv_party_dt"  maxlength="10" class="form-control" disabled="disabled">
										           </div>
                                             </div>
                                        </div>
                                        <div class="col-md-6">
			                                 <div class="row form-group">
                								   <div class="col col-md-4">
                                                         <label class=" form-control-label">Type of Cl</label>
                                                   </div>
                                                   <div class="col-12 col-md-8">
	                  						             <input type="text" id="type_of_cl" name="type_of_cl"  maxlength="10" class="form-control" disabled="disabled">
										           </div>
                                             </div>                                        
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
                                       		<div class="row form-group">
                								<div class="col col-md-4">
                                              		<label class=" form-control-label">To Unit SUS No </label>
                                             	</div>
                                            	<div class="col-12 col-md-8">
	                  						    	<input type="text" id="rplc_by_unit_sus_no" name="rplc_by_unit_sus_no" maxlength="8" class="form-control" disabled="disabled">
	                					    	</div>
                                       		</div>
                                   		</div>
                                   		<div class="col-md-6">
                                   			<div class="row form-group">
                                              	<div class="col col-md-4">
                                                	<label class=" form-control-label">To Unit</label>
                                             	</div>
                                              	<div class="col-12 col-md-8">
                                                	<input type="text" id="rplc_by_unit_name" name="rplc_by_unit_name"  maxlength="100" class="form-control" disabled="disabled">
                                            	</div>
                                        	</div>  
										</div>
									</div>
								</div>
								<div class="col-md-12">
										<div class="col-md-6">
                                       		<div class="row form-group">
                								<div class="col col-md-4">
                                              		<label class=" form-control-label">Remarks </label>
                                             	</div>
                                            	<div class="col-12 col-md-8">
													<textarea  id="remarks" name="remarks"  maxlength="255" class="form-control char-counter1" disabled="disabled"> </textarea>
	                					    	</div>
                                       		</div>
                                   		</div>
                                   		<div class="col-md-6">
										</div>
									</div>
	              	            <div class="card-header" style="border: 1px solid rgba(0,0,0,.125);"> <strong>To be Entered By Unit</strong></div>
              			        <div class="card-body ">
              			        	<div class="col-md-12">
              			            	<div class="col-md-6">
              			                	<div class="row form-group">
	                					    	<div class="col col-md-4">
	                  						    	<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Departure Date</label>
	                					       	</div>
	                					        <div class="col-12 col-md-8">
	                  						    	<input type="date" id="dep_date" name="dep_date" maxlength="10" class="form-control" >
										        </div>
	  								     	</div>
              			            	</div>
              			            
              			            	<div class="col-md-6">
              			                	<div class="row form-group">
	                					    	<div class="col col-md-4">
	                  						    	<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Arrival Date</label>
	                					       	</div>
	                					        <div class="col-12 col-md-8">
	                  						    	<input type="date" id="arr_date" name="arr_date" maxlength="10" onchange="return checkarrdate(this,dep_date)" class="form-control" >
										      	</div>
	  								     	</div> 
	  								   	</div>
	  								</div>
	  								<div class="col-md-12">
	  								  <div class="col-md-6">   
	  								     <div class="row form-group">
               					             <div class="col col-md-4">
			                  				       <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Upload Arr/Dep Report</label>
			                				 </div>
			                				 <div class="col-12 col-md-8">
			                  					   <input type="file" id="upload_document2" name="upload_document2"  maxlength="250" class="form-control">
											 </div>
	  								     </div>   
              			            </div>
              			        </div>
							</div>
              			    <div class="card-footer" align="center">
								<input type="reset" class="btn btn-success btn-sm" value="Clear">   
			              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate()">
			              	</div> 
			        	</div>
					</div>
				</div>
			</div>
</form:form>
 

<script>
function validate(){
	if(jQuery("#unit_auth_letter_no").val() == ""){
		alert("Please Enter the Auth Letter No");
		jQuery("#unit_auth_letter_no").focus();
		return false;
	}
	if(jQuery("#unit_auth_letter_date").val() == ""){
		alert("Please Enter the Date");
		jQuery("#unit_auth_letter_date").focus();
		return false;
    }
	if(jQuery("#sus_no").val() == ""){
		alert("Please Enter the SUS No");
		jQuery("#sus_no").focus();
		return false;
	}
	if(jQuery("#dep_date").val() == ""){
		alert("Please Enter the Departure Date ");
		jQuery("#dep_date").focus();
		return false;
	}
	if(jQuery("#arr_date").val() == ""){
		alert("Please Enter the Arrival Date ");
		jQuery("#arr_date").focus();
		return false;
    }
	if(jQuery("#upload_document2").get(0).files.length === 0){
		alert("Please Upload the Arrival/Departure Report");
		return false;
    }
	/* if(jQuery("#nmb_date").val() != ""){
		var curDate = new Date();
		var nmbDate = new Date(jQuery("#nmb_date").val());
		if (nmbDate > curDate){
			alert("Unit does not move before NMB Date");
			jQuery("#nmb_date").focus();			
			return false;
		}
	} */
	return true;
}
</script>
<script>
jQuery(function() {
	
	jQuery("input#sus_no").keyup(function(){
		var sus_no = this.value;
		var unitNameAuto=jQuery("#sus_no");
		unitNameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getSusNoActiveBySD?"+key+"="+value,
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
		        	  jQuery("#unit_name").val("");
		        	  unitNameAuto.val("");	        	  
		        	  unitNameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	var sus_no = ui.item.value;
		    	$.post("getAllBodyDetailsList?"+key+"="+value,{sus_no:sus_no}, function(j) {
		    		if(j.length > 0){
		    			var length = j.length-1;
		 				var enc = j[length].substring(0,16);
		    		
		 				jQuery("#id").val(dec(enc,j[0]));
			   	 		jQuery("#unit_name").val(dec(enc,j[1]));
			   	  	    jQuery("select#imdt_fmn").val(dec(enc,j[2]));
			   	  		jQuery("#indn_de_indn_pd").val(dec(enc,j[3]));
			   	  	    jQuery("select#arm_name").val(dec(enc,j[4]));
			   	  	    jQuery("#mode_of_tpt").val(dec(enc,j[5])); 
			   	  	    jQuery("#nmb_date").val(dec(enc,j[6]));  
			   	  	    jQuery("#loc").val(dec(enc,j[7]));
			   	  		jQuery("#nrs").val(dec(enc,j[8]));
			   	  	    jQuery("#typ_of_stn").val(dec(enc,j[9]));
			   	  	    jQuery("#typ_of_terrain").val(dec(enc,j[10]));
			   	  	    jQuery("#mov_of_adv_party_dt").val(dec(enc,j[11]));
			   	  	    jQuery("#rplc_by_unit_sus_no").val(dec(enc,j[12]));
			   	  	    jQuery("#rplc_by_unit_name").val(dec(enc,j[13]));
			   	  		jQuery("#relief_yes_no").val(dec(enc,j[14]));
				   	  	jQuery("#type_of_cl").val(dec(enc,j[15]));
				   	 	jQuery("#remarks").val(dec(enc,j[16]));
			   	  		
			   	  		/* jQuery("#dep_date").val(''); */
		   	  	    	jQuery("#arr_date").val('');
		   	  	       /*  document.getElementById("dep_date").min = dec(enc,j[6]); */
			   	  		/* document.getElementById("arr_date").min = dec(enc,j[6]); */
			   	  	}else{
			   	  		alert("Data Not Available");
			   	  	}
			   	});
		     }
		});
	});
});
</script>

<script>

function checkarrdate(obj,validateDate) {
	if(validateDate.value !="")
	{
		var id = obj.id;
		var myDate = document.getElementById(id).value;
		var Date1 = validateDate.value;
		if ((Date.parse(myDate) < Date.parse(Date1))) {
			alert('You cannot select less than Dep Date.');
			obj.value = "";
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

 jQuery(document).ready(function() {   
jQuery("#upload_document2").change(function(){	
	checkFileExt(this);
});
	if('${roleAccess}' == 'Unit'){
	jQuery("#sus_no").attr('readonly','readonly');
	jQuery("#unit_name").attr('readonly','readonly');
}
	jQuery("#id").val('${list[0][0]}');
	jQuery("#unit_name").val('${list[0][1]}');
	jQuery("select#imdt_fmn").val('${list[0][2]}');
	jQuery("#indn_de_indn_pd").val('${list[0][3]}');
	jQuery("select#arm_name").val('${list[0][4]}');
	jQuery("#mode_of_tpt").val('${list[0][5]}'); 
	jQuery("#nmb_date").val('${list[0][6]}');  
	jQuery("#loc").val('${list[0][7]}');
	jQuery("#nrs").val('${list[0][8]}');
	jQuery("#typ_of_stn").val('${list[0][9]}');
	jQuery("#typ_of_terrain").val('${list[0][10]}');
	jQuery("#mov_of_adv_party_dt").val('${list[0][11]}');
	jQuery("#rplc_by_unit_sus_no").val('${list[0][12]}');
	jQuery("#rplc_by_unit_name").val('${list[0][13]}');
	
	var r;
	if('${list[0][14]}' == '1')
		r="Yes";
	  else
		r="No";
			
	jQuery("#relief_yes_no").val(r);
	jQuery("#type_of_cl").val('${list[0][15]}');
	jQuery("#remarks").val('${list[0][16]}');	

	
	/* document.getElementById("dep_date").min = '${list[0][6]}'; */
	/* document.getElementById("arr_date").min = '${list[0][6]}'; */
	var ddd = '${date}';
	
  	/* $("#dep_date").change(function(){
	if($("#dep_date").val() <= $("#nmb_date").val()){
		$("#dep_date").val("");
		alert("You Can't select less than NMB Date!");
		$("#dep_date").focus();
		return false;
	} 
	}); */	 
  	
	$("#arr_letter").change(function(){
	if($("#arr_letter").val() < $("#dep_date").val()){
		$("#arr_letter").val("");
		alert("You Can't select Arrivaal daate beforee Departure date !");
		$("#arr_letter").focus();
		return false;
	}
	});	 
}); 	 

</script>