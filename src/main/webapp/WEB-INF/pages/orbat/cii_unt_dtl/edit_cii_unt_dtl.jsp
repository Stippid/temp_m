<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/miso/commonJS/commonmethod.js"></script> 
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">


<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/arrival_departure_report.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/ChkDateLessThan.js" type="text/javascript"></script>

<script src="js/Calender/datePicketValidation.js"></script>

<form:form action="ciiEditAction" method='POST' commandName="ciiEditCMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">
					
					<div class="card-header"><h5><b>UPDATE CII Unit Details</b></h5> <strong>Entry Form</strong></div>
					
						
					<div class="card-header" style="border: 1px solid rgba(0, 0, 0, .125);"> <strong>Details Of The Unit </strong> </div>

					<div class="card-body card-block">
					
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label" id="unit"><strong style="color: red;">*</strong>Unit Name </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="hidden" id="id" name="id" class="form-control" value="${ciiEditCMD.id}">
										<input type="text" id="unit_name" name="unit_name" maxlength="100" value="${ciiEditCMD.unit_name}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Unit Name" class="form-control autocomplete" autocomplete="off" readonly="readonly">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group" >
									<div class="col col-md-4">
										<label class=" form-control-label" id="su_no"><strong style="color: red;">*</strong>SUS No </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" maxlength="8" value="${ciiEditCMD.sus_no}" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search SUS No" class="form-control autocomplete" autocomplete="off" readonly="readonly">
									</div>
								</div>
							</div>
						</div>
					
					 
					<div class="col-md-12">
            			<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label" id="fmn"><strong style="color: red;">*</strong>Fmn Name </label>
									</div>
									<div class="col-12 col-md-8">
	
										<input type="text" id="fmn_name" name="fmn_name" value="${ciiEditCMD.fmn_name}" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Fmn Name" class="form-control autocomplete" autocomplete="off" onblur="updateFormCode()">
									</div>
								</div>
							</div>
            			<div class="col-md-6">
            				 <div class="row form-group" >
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong>Formation Code </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="text" id="form_code" name="form_code" value="${ciiEditCMD.fmn_code}" maxlength="8" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Enter Form code" class="form-control autocomplete" autocomplete="off" readonly="readonly">
								</div>
							</div>
            			</div>
            		</div>
					
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"> <strong style="color: red;">*</strong> Loc </label>
								</div>
								<div class="col-12 col-md-8">
									<input type="hidden" id="code" name="code" maxlength="5" class="form-control" value="${ciiEditCMD.loc_code}" style="width:100%;" >
				                 	<input type="text" id="loc" name="loc" maxlength="400" class="form-control" style="width:86%;display: inline-block;" value="${LOC_NRS_TypeLOC_TrnType[0]}" readonly="readonly" >
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
									<input type="hidden" id="nrs_code" name="nrs_code" maxlength="5" class="form-control" value="${ciiEditCMD.nrs_code}">
				                  	<input type="text" id="nrs_name" name="nrs_name" maxlength="400" class="form-control" value="${LOC_NRS_TypeLOC_TrnType[1]}" readonly="readonly" >
								</div>
							</div>
						</div>
					</div>
					
					
				    <div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong>From Date 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="date_from" name="date_from"  maxlength="10" placeholder="" class="form-control" >
									</div>
								</div>
							</div>
							<div class="col-md-6">
			          			<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> To Date 
										</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="date_to" name="date_to" value="${ciiEditCMD.to_date}" class="form-control" max="${date}" >
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
		                			<select id="arm_name" name="arm_name"  class="form-control"  readonly="readonly">
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
												<label for="text-input" class=" form-control-label">Authority</label>
                                            </div>
                                          <div class="col-12 col-md-8">
	                  						    <textarea  id="remarks" name="remarks" value="${ciiEditCMD.remarks}"  maxlength="250"  class="form-control char-counter1" > </textarea>
										  </div>
                                    </div>
		          		</div>
					</div>
						
					
					</div>

					<div class="card-footer" align="center"  class="form-control">
						<!-- <input type="reset" class="btn btn-success btn-sm" value="Clear"> -->
						<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();">
						<a href="search_cii_unt_dtl" type="reset" class="btn btn-danger btn-sm"> Cancel </a>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>



 <script>
    
    	  
    		$("#sus_no").keyup(function(){
    			var sus_no = this.value;
    			var susNoAuto=$("#sus_no");

    			susNoAuto.autocomplete({
    			      source: function( request, response ) {
    			        $.ajax({
    			        type: 'POST',
    			        url: "getTargetSUSNoList?"+key+"="+value,
    			        data: {sus_no:sus_no},
    			          success: function( data ) {
    			        	  var susval = [];
    		                  var length = data.length-1;
    		                  var enc = data[length].substring(0,16);
    		                        for(var i = 0;i<data.length;i++){
    		                                susval.push(dec(enc,data[i]));
    		                        }
    		                        var dataCountry1 = susval.join("|");
    		                        var myResponse = []; 
    						        var autoTextVal=susNoAuto.val();
    						$.each(dataCountry1.toString().split("|"), function(i,e){
    							var newE = e.substring(0, autoTextVal.length);
    							if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
    							  myResponse.push(e);
    							}
    						});      	          
    						response( myResponse ); 
    			          }
    			        });
    			      },
    				  minLength: 1,
    			      autoFill: true,
    			       
    				select: function( event, ui ) {
    					var sus_no = ui.item.value;			      	
    					 $.post("getTargetUnitNameList?"+key+"="+value, {
    						 sus_no:sus_no
    		         }, function(j) {
    		                
    		         }).done(function(j) {
    		        	 var length = j.length-1;
    		             var enc = j[length].substring(0,16);
    		             $("#unit_name").val(dec(enc,j[0]));   
    		                 
    		         }).fail(function(xhr, textStatus, errorThrown) {
    		         });
    					 
   					 $.post("getAllDataByUN?"+key+"="+value,{unit_name:unit_name}, function(data) {
			            }).done(function(j) {
			            	var length = j.length-1;
			            	var enc = j[length][0].substring(0,16);
			            	$("#arm_name").val(dec(enc,j[0][0]));
			            	$("#form_code").val(dec(enc,j[0][1]));
			            	$("#loc").val(dec(enc,j[0][2]));
			            	$("#nrs_name").val(dec(enc,j[0][3]));
			            	$("#date_from").val(dec(enc,j[0][4]).substring(0,10));
				            $("#date_to").val(dec(enc,j[0][5]).substring(0,10));
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
    				} 	     
    			});	
    		});
    		
    		
    		// unit name
    		 $("input#unit_name").keypress(function(){
   				var unit_name = this.value;
   					 var susNoAuto=$("#unit_name");
   					  susNoAuto.autocomplete({
   					      source: function( request, response ) {
   					        $.ajax({
   					        	type: 'POST',
   						    	url: "getTargetUnitsNameActiveList?"+key+"="+value,
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
   					      
   					      select: function( event, ui ) {
   					    	 var unit_name = ui.item.value;
   					    
  						            $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
  						            }).done(function(data) {
  						            	var length = data.length-1;
  							        	var enc = data[length].substring(0,16);
  							        	$("#sus_no").val(dec(enc,data[0]));
  						            }).fail(function(xhr, textStatus, errorThrown) {
  						            });
  						            
  						            $.post("getAllDataByUN?"+key+"="+value,{unit_name:unit_name}, function(data) {
  						            }).done(function(j) {
  						            	var length = j.length-1;
  						            	var enc = j[length][0].substring(0,16);
  						            	$("#arm_name").val(dec(enc,j[0][0]));
  						            	$("#form_code").val(dec(enc,j[0][1]));
  						            	$("#loc").val(dec(enc,j[0][2]));
  						            	$("#nrs_name").val(dec(enc,j[0][3]));
  						            	$("#date_from").val(dec(enc,j[0][4]).substring(0,10));
  						            	$("#date_to").val(dec(enc,j[0][5]).substring(0,10));
  						            }).fail(function(xhr, textStatus, errorThrown) {
  						            });
   					      } 	     
   					}); 			
    			});
    		
    		
    		
    		 $("input#fmn_name").keyup(function() {
   				var fmn_name = this.value;
   				var fmnNameAuto = $("#fmn_name");
   				fmnNameAuto.autocomplete({
   					source : function(request, response) {
   						$.ajax({
   							type : 'POST',
   							url : "getFmnName?" + key + "=" + value,
   							data : {
   								fmn_name : fmn_name
   							},
   							success : function(data) {
   								 var susval = [];
    					        	  var length = data.length-1;
    					        	  var enc = data[length].substring(0,16);
    						        	for(var i = 0;i<data.length;i++){
    						        		susval.push(dec(enc,data[i]));
    						        	}
   								
   								response(susval);
   								
   							}
   						});
   					},
   					minLength : 1,
   					autoFill : true,
   					
   					change: function(event, ui) {
				    	 if (ui.item) {   	        	  
				        	  return true;    	            
				          } else {
				        	  alert("Please Select Approved FMN Name");
				        	  document.getElementById("fmn_name").value="";
				        	  fmnNameAuto.val("");	        	  
				        	  fmnNameAuto.focus();
				        	  return false;	             
				          }    	         
				      },
				      
				      select: function( event, ui ) {
	   				      	$(this).val(ui.item.value);
	   				     updateFormCode($(this).val());
	   				      
	    				}
   				});
   			});
    		
    		 
    		 
    		 
    		
</script> 


<script>
 	   
    var Opcom,Opcorps,Opdiv,Opbde,Contcom,Contcorps,Contdiv,Contbde,Admincom,Admincorps,Admindiv,Adminbde,ParentArm,TypeOfArm;
  
  	function getOrbatDetailsFromUnitName(unitName){
  		 var scnario =$("#type_of_letter").val(); 
		$.post("getUnitDetailsList?"+key+"="+value,{unitName:unitName}, function(j) {
	       	if(j.length > 0) {
	        	var length = j.length-1;
	 			var enc = j[length].substring(0,16);
	 			
	        	$("#sus_no").val(dec(enc,j[1]));
	        	if(scnario=="12"){
			   			check_sus();
			   		}
	        	
	      	 	var forcodeOperation = dec(enc,j[4]);
	      	 	var forcodeControl = dec(enc,j[5]);
	      	 	var forcodeAdmin = dec(enc,j[6]);
	      	 	var Arm = dec(enc,j[1]);
	      	 		
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
	 	        	
	 	        	 	
	 	         $('#op_comd option[value="'+Opcom+'"]').prop("selected", true);
	 	         getOPCorps(Opcorps);
	 	         getOPDiv(Opdiv);
	 	         getOPBde(Opbde);
	 	        	 
	 	    	  
	 	   		$('#cont_comd option[value="'+ Contcom +'"]').prop("selected", true);
	 	 		var cont_cname = $("#cont_comd").find('option:selected').attr("name");    	 
	 			$("#cont_cname").val(cont_cname);
	  	       	getCONTCorps(Contcorps);
	  	    	getCONTDiv(Contdiv);
	  	   		getCONTBde(Contbde);
	  		    
	  	    	$('#adm_comd option[value="'+ Admincom +'"]').prop("selected", true);
	  	      	getADMCorps(Admincorps);
	  	      	getADMDiv(Admindiv);
	  	      	getADMBde(Adminbde);
	  	        
	       		ParentArm = Arm[0] + Arm[1]; 
	 	   		TypeOfArm = Arm[0] + Arm[1] + Arm[2] + Arm[3]; 
	 	        	 
		   		$("#code").val(dec(enc,j[8]));
		   		$("#loc").val(dec(enc,j[9]));
		   		$("#nrs_code").val(dec(enc,j[10]));
		   		$("#nrs_name").val(dec(enc,j[11]));
		   		$('#type_of_location').val(dec(enc,j[12]));
		    	$('#modification').val(dec(enc,j[13]));
			    
	 			$('#arm_code').val(dec(enc,j[7]));
	   	    	$("select#arm_name").val(dec(enc,j[7]));
	   	    	$("select#type_force").val(dec(enc,j[14])); 
	   	    }else{
	   			alert('Data Not Available');
	   		}
		});
	}
     
	
</script>
<script>
function validate(){
	
	if($("#unit_name").val() == ""){
		alert("Please Select Unit Name");
		$("#unit_name").focus();
		return false;
    }
	
	if($("#code").val() == ""){
		alert("Please Select Loc Code");
		$("#code").focus();
		return false;
    }
	
	if($("#form_code").val() == ""){
		alert("Please Enter form Code");
		$("#form_code").focus();
		return false;
    }
	
	return true;
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


	
	$(document).ready(function() {
		$("select#arm_name").val('${ciiEditCMD.arm_code}');
		
		var id = '${ciiEditCMD.id}';
		if (id != '' || id != '0') {
			$("#date_from").val(ParseDateColumn('${ciiEditCMD.from_date}'));
			$("#date_to").val(ParseDateColumn('${ciiEditCMD.to_date}'));
		}
	});
	
	
	function updateFormCode() {
	    var fmnName = document.getElementById("fmn_name").value;

	    
	    var url = "get_fmncode?fmn_name=" + encodeURIComponent(fmnName);

	    
	    $.ajax({
	        url: url,
	        type: "GET",
	        success: function(formCode) {
	            document.getElementById("form_code").value = formCode;
	        },
	        error: function(xhr, status, error) {
	            console.error('Error updating form code:', error);
	        }
	    });
	}
</script>
