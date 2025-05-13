<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/JS_CSS/jquery.dataTables.js"></script>

<script type="text/javascript" src="js/miso/tabletoexcel/tabletoexcel.js"></script>
<script src="js/miso/tabletoexcel/jquery.base64.js"></script>
<script src="js/miso/tabletoexcel/jquery.btechco.excelexport.js"></script>
<style type="text/css">
	.dataTables_paginate.paging_full_numbers{
		margin-top: 0.755em;
	}
	.dataTables_paginate.paging_full_numbers a{
		background-color: #9c27b0;
		border: 1px solid #9c27b0;
		color: #fff;
		border-radius: 5px;
		padding: 3px 10px;
		margin-right: 5px;
	}
	.dataTables_paginate.paging_full_numbers a:hover{
		background-color: #cb5adf;
		border-color: #cb5adf;
	}
	.dataTables_info{
		color:#9c27b0 !important;
		font-weight: bold;
	}
</style>

	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    			<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5>SEARCH MCT No</h5></div>
	       				<div class="card-body card-block">
	       					<div  class="col-md-12">
	       						<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-5">
											<label class=" form-control-label"><strong style="color: red;">*</strong>Type of Vehicle
											</label>
										</div>
										<div class="col-md-7">
											<select name="type_of_vehicle" id="type_of_vehicle" onchange="return typeOfVehChange();"
												class="form-control-sm form-control">
												<option value="0">--Select--</option>
												<option value="A">A Vehicle</option>
												<option value="B">B Vehicle</option>
												<option value="C">C Vehicle</option>
											</select>
										</div>
									</div>
								</div>
	       					</div>
	       					<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						            	<div class="col-md-5">
						                	<label class=" form-control-label">MCT (10 Digit)</label>
						                </div>
						                <div class="col-md-7">
						                  <input type="text" id="mct" name="mct" maxlength="10" class="form-control autocomplete" autocomplete="off">
						                </div>
						          	</div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
		                				<div class="col-md-5">
		                  					<label class=" form-control-label">Nomenclature </label>
		                				</div>
						                <div class="col-md-7">
		                					<input type="text" id="std_nomclature" name="std_nomclature" class="form-control autocomplete" autocomplete="off" maxlength="300">
		                				</div>
	              					</div>
	              				</div>
	              			</div>
	       				
	       					<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					 <div class="row form-group">
		                				<div class="col-md-5">
		                  					<label for="text-input" class=" form-control-label">MCT  (4 Digit)</label>
		                				</div>
		                				<div class="col-md-7">
		                  					<input type="text" id="mct_main_id" name="mct_main_id" maxlength="4" class="form-control autocomplete" autocomplete="off">
										</div>
									</div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
					                	<div class="col-md-5">
					                  		<label class=" form-control-label">Nomenclature</label>
					                	</div>
					                	<div class="col-md-7">
					                 		<input type="text" id="sermct_main_nomen" name="sermct_main_nomen" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="100">
				            			</div>
				        			</div>	
	              				</div>
	              			</div>
	       				
	       					<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
	               						<div class="col-md-5">
	                 							<label class=" form-control-label">PRF No</label>
	               						</div>
	               						<div class="col-md-7">
	                							<input type="text" id="prf_group" name="prf_group" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="5">
	               						</div>
	             					</div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
		               					<div class="col-md-6">
		                 					<label class=" form-control-label">Range</label>
		                 					<label id="mctSlotRange"></label>
		               					</div>
		               					<div class="col-md-6">
		                					<label class=" form-control-label">PRF Nomenclature</label>
		                					<label id="prfNomen"></label>
		               					</div>
             						</div>
	              				</div>
	              			</div>	              			
					</div> 
					<div class="card-footer" align="center" class="form-control">
						 <input type="submit" class="btn btn-success btn-sm"  value="Clear" onclick="return getMctMasterReport(0);">      
	              		<button type="button" class="btn btn-success btn-sm" onclick="return getMctMasterReport(1);">Search</button>
	              		<button id="btnExport" type="button" class="btn btn-success btn-sm" >Export</button>
	              	</div> 
		            <div class="card-body" id="mctReport">
         				<datatables:table id="applicanttbl" url="getMctNoReport" serverSide="true" pipelining="true" pipeSize="3"	row="latlon" rowIdBase="applicant_id" rowIdPrefix="latlon_" displayLength="10"  jqueryUI="true" lengthMenu="10,50,100,200,500,1000,2000,3000,3300,4000,5000"
	    					bDestroy="true" filterable="false" sortable="false" processing="true" border="1" scrollY="398" autoWidth="true" pageable="true" paginationType="full_numbers" stateSave="false" deferRender="true"  scrollX="100%" scrollCollapse="true">  
							    <datatables:column title="Sr No." property="id" visible="false" />
							    <datatables:column title="MCT No" property="mct" />
								<datatables:column title="Nomenclature" property="std_nomclature" /> 
								<datatables:column title="PRF Gp" property="prf_group"/>
								<datatables:column title="Veh Class Code" property="veh_class_code"/>
								<datatables:column title="Status" property="status"  />
								<datatables:column title="Action" renderFunction="actionsUnitDetails" display="HTML" />
						</datatables:table>    
                        <br/>
		        	</div>
	        	</div>
			</div>
	
	</div>


	 <c:url value="Update_mct_no" var="editUrl" />
	<form:form action="${editUrl}" method="post" id="editForm" name="editForm" modelAttribute="locationId">
		<input type="hidden" name="locationId" id="locationId" value="0"/>
	</form:form> 
	
	 <c:url value="Delete_mct_no" var="deleteUrl" />
	<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="DeleteId">
		<input type="hidden" name="DeleteId" id="DeleteId" value="0"/>
	</form:form> 

<c:url value="search_MctNopofestional" var="reloadUrl1" />
<form:form action="${reloadUrl1}" method="post" id="reloadReport1" name="reloadReport1">
	<input type="hidden" name="type_of_vehicle1" id="type_of_vehicle1" />
	<input type="hidden" name="mct1" id="mct1" />
	<input type="hidden" name="new_nomencatre1" id="new_nomencatre1" />
	<input type="hidden" name="prf_group1" id="prf_group1" />
	<input type="hidden" name="mct_main_id1" id="mct_main_id1" />
	<input type="hidden" name="sermct_main_nomen1" id="sermct_main_nomen1" />
</form:form>

<script>
function typeOfVehChange()
{
	
	$("#mct").val("");
	$("#std_nomclature").val("");
	$("#mct_main_id").val("");
	$("#mct").val("");
	$("#std_nomclature").val("");
	$("#mct_main_id").val("");
	$("#prf_group").val("");
	$("#prfNomen").text("");
	$("#mctSlotRange").text("");
}
	
	$(function() {

		$("input#mct").keyup(function(){
			var mct = this.value;
			var mct_numberAuto=$("#mct");
			var type_of_vehicle = $("#type_of_vehicle").val();
			if(type_of_vehicle == "0"){
				alert("Please Select  Type of Vehicle.");
				$("#type_of_vehicle").focus();
				$("#std_nomclature").val("");
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
			        	  $("#std_nomclature").val("");
			        	  mct_numberAuto.val("");	        	  
			        	  mct_numberAuto.focus();
			        	  return false;	             
			    	}   	         
			    }, 
				select: function( event, ui ) {
			      	var mct_num = ui.item.value;
			      	
				
					$.post("getMCTsearchmctnoData?"+key+"="+value, {
						mct_num : mct_num
					}).done(function(data) {
						var length = data.length-1;
				          var enc = data[length][0].substring(0,16);
				          $("#std_nomclature").val(dec(enc,data[0][0]));
				          $("#prf_group").val(dec(enc,data[0][1]));
						if(dec(enc,data[0][1]) != 0){
							
									
										$.post("getaddsearchmctnoprfList?"+key+"="+value, {
											prf_group:dec(enc,data[0][1])
										}).done(function(j) {
											var length = j.length-1;
									          var enc = j[length][0].substring(0,16);									      
											var range = dec(enc,j[0][0]) + " - " +  dec(enc,j[0][1]);
						      		   		$("#mctSlotRange").text(range);
						      		  		$("#prfNomen").text(dec(enc,j[0][2]));
										}).fail(function(xhr, textStatus, errorThrown) {
										});

							
						}
						
					}).fail(function(xhr, textStatus, errorThrown) {
					});		
				}
			});
		});
	
		$("input#std_nomclature").keyup(function(){
			var mctNomen = this.value;
			var mct_nomenAuto=$("#std_nomclature");
			var type_of_vehicle = $("#type_of_vehicle").val();
			if(type_of_vehicle == "0"){
				alert("Please Select  Type of Vehicle.");
				$("#type_of_vehicle").focus();
				$("#std_nomclature").val("");
	        	
				return false;
			}
			mct_nomenAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getStdNomenclatureListFromVeh_cat?"+key+"="+value,
			        data: {mctNomen:mctNomen,type_of_vehicle:type_of_vehicle},
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
			        	  alert("Please Enter Valid  Nomenclature.");
			        	  $("#mct").val("");
			        	  mct_nomenAuto.val("");	        	  
			        	  mct_nomenAuto.focus();
			        	  return false;	             
			    	}   	         
			    }, 
				select: function( event, ui ) {
			      	var nomencatre = ui.item.value; 	
				
				
					$.post("getStdNomenclaturetoMctNoList?"+key+"="+value, {
						std_nomclature : nomencatre
					}).done(function(data) {
		
						 var length = data.length-1;
				        	var enc = data[length].substring(0,16);
				        	$("#mct").val(dec(enc,data[0]));
					
							$.post("getMCTsearchmctnoData?"+key+"="+value, {
								mct_num : dec(enc,data[0])
							}).done(function(j) {
							
								 var length = j.length-1;
						          var enc = j[length][0].substring(0,16);
						          $("#std_nomclature").val(dec(enc,j[0][0]));
						          $("#prf_group").val(dec(enc,j[0][1]));
								if(j[0][1] != 0){
								
									$.post("getaddsearchmctnoprfList?"+key+"="+value, {
										prf_group:dec(enc,j[0][1])
									}).done(function(j) {
										var length = j.length-1;
								          var enc = j[length][0].substring(0,16);
								        
										var range = dec(enc,j[0][0]) + " - " +  dec(enc,j[0][1]);
					      		   		$("#mctSlotRange").text(range);
					      		  		$("#prfNomen").text(dec(enc,j[0][2]));
									}).fail(function(xhr, textStatus, errorThrown) {
									});
				 		   		}	
								
							}).fail(function(xhr, textStatus, errorThrown) {
							});
	
						
					}).fail(function(xhr, textStatus, errorThrown) {
					});
	
				}
			});
		});
	
		$("input#mct_main_id").keyup(function(){
			var mct_main_id = this.value;
			var mct_nomenAuto=$("#mct_main_id");
			mct_nomenAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getserMctMainTmsList?"+key+"="+value,
			        data: {mct_main_id:mct_main_id},
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
			        	  $("#mct_main_id").val("");
			        	  mct_nomenAuto.val("");	        	  
			        	  mct_nomenAuto.focus();
			        	  return false;	             
			    	}   	         
			    }, 
			  	select: function( event, ui ) {
			      	var mct_main_id = ui.item.value;
			      	
					
					$.post("getserMctMainTmsListcliknomen?"+key+"="+value, {
						mct_main_id:mct_main_id
					}).done(function(j) {
						  var length = j.length-1;
				          var enc = j[length].substring(0,16);
				          $("#sermct_main_nomen").val(dec(enc,j[0]));
					}).fail(function(xhr, textStatus, errorThrown) {
					});		      	
				}
			});
		});
	
		$("input#sermct_main_nomen").keyup(function(){
			var sermct_main_nomen = this.value;
			
			var mct_nomenAuto=$("#sermct_main_nomen");
			mct_nomenAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getserMctMainnomainTmsList?"+key+"="+value,
			        data: {sermct_main_nomen:sermct_main_nomen},
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
			        	  alert("Please Enter Valid MCT Nomenclature.");
			        	  $("#sermct_main_nomen").val("");
			        	  mct_nomenAuto.val("");	        	  
			        	  mct_nomenAuto.focus();
			        	  return false;	             
			    	}   	         
			    }, 
			  	select: function( event, ui ) {
			      	var mct_nomen = ui.item.value;
				
					$.post("getserMctMainTmsListclikid?"+key+"="+value, {
						mct_nomen:mct_nomen
					}).done(function(j) {
						 var length = j.length-1;
				          var enc = j[length].substring(0,16);
				          $("#mct_main_id").val(dec(enc,j[0]));
					}).fail(function(xhr, textStatus, errorThrown) {
					});	    	
				}
			});
		});
	
		$("input#prf_group").keyup(function(){
			var prf_group = this.value;
			
			var mct_nomenAuto=$("#prf_group");
			mct_nomenAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        type: 'POST',
			        url: "getserPrfgroupTmsList?"+key+"="+value,
			        data: {prf_group:prf_group},
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
			        	  alert("Please Enter Valid PRF No.");
			        	  $("#prf_group").val("");
			        	  mct_nomenAuto.val("");	        	  
			        	  mct_nomenAuto.focus();
			        	  return false;	             
			    	}   	         
			    }, 
			    select: function( event, ui ) {
			      	var prf_group = ui.item.value;
					var prf = ('000' + prf_group).substr(-3);
					if(prf_group != 0){
						
					
						$.post("getaddsearchmctnoprfList?"+key+"="+value, {
							prf_group:prf
						}).done(function(j) {
							  var length = j.length-1;
					          var enc = j[length][0].substring(0,16);					    
							var range = dec(enc,j[0][0]) + " - " +  dec(enc,j[0][1]);
		      		   		$("#mctSlotRange").text(range);
		      		  		$("#prfNomen").text(dec(enc,j[0][2]));
						}).fail(function(xhr, textStatus, errorThrown) {
						});			
	  		   		}	
				}
			});
		});
});

	$(document).ready(function() {
		
		$("#type_of_vehicle").val('${type_of_vehicle1}');
	  	$("#mct").val('${mct1}');
	  	$("#std_nomclature").val('${new_nomencatre1}');
	  	$("#prf_group").val('${prf_group1}');
	  	$("#mct_main_id").val('${mct_main_id1}');
		$("#sermct_main_nomen").val('${mct_main_nomen1}');
		
		if('${type_of_vehicle1}' != "0"){
			$("div#mctReport").show();
			$('#btnExport').show();
		}else{
			$('#btnExport').hide();
			$("div#mctReport").hide();
		}
	
	if('${prf_group1}' != ""){	

 	   	$.post("getaddsearchmctnoprfList?"+key+"="+value, {
 	   	prf_group:'${prf_group1}'
		}, function(j) {			 
		}).done(function(j) {
			var length = j.length-1;
	        var enc = j[length][0].substring(0,16);	        
			var range = dec(enc,j[0][0]) + " - " +  dec(enc,j[0][1]);
	   		$("#mctSlotRange").text(range);
	  		$("#prfNomen").text(dec(enc,j[0][2]));
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	} 
	}); 
       
	function getMctMasterReport(chk) {
		
		if(chk == "0"){
			$('#btnExport').hide();
			$("select#type_of_vehicle").val("0")
			$("#mct").val("");
			$("#std_nomclature").val("");
			$("#prf_group").val("");
			$("#mct_main_id").val("");
			$("#sermct_main_nomen").val("");
			document.getElementById('mctReport').style.display = 'none';
			
		}else{
			if($("select#type_of_vehicle").val() == "0"){
				$('#btnExport').hide();
				$("#mct").val("");
				$("#std_nomclature").val("");
				$("#prf_group").val("");
				$("#mct_main_id").val("");
				$("#sermct_main_nomen").val("");
				document.getElementById('mctReport').style.display = 'none';
				
				alert("Please Select Type of Vehicle.");
				$("select#type_of_vehicle").focus();
				return false;
			}
			
			document.getElementById('type_of_vehicle1').value = $("#type_of_vehicle").val();
		   	document.getElementById('mct1').value = $("#mct").val();
		   	document.getElementById('new_nomencatre1').value = $("#std_nomclature").val();
		   	document.getElementById('prf_group1').value = $("#prf_group").val();
		   	document.getElementById('mct_main_id1').value = $("#mct_main_id").val();
		   	document.getElementById('sermct_main_nomen1').value = $("#sermct_main_nomen").val();
		   	jQuery("#WaitLoader").show();
			document.getElementById('reloadReport1').submit();
		   	return false;
		}
	}
    
    function actionsUnitDetails(data, type, full) {
    	var f="";
    	var Edit = "onclick=\"  viewDetails('"+full.id+"')\"";
		f +='<a hreF="#" '+Edit+' title="Update Data"><i class="fa fa-pencil-square-o" aria-hidden="true" style="font-size:24px;"></i></a> &nbsp; &nbsp;'; 
		
		var Delete = "onclick=\" if (confirm('Are You Sure you want to Inactive this MCT ?') ){ viewDeleteDetails('"+full.id+"') }else{ return false;}\"";
		f +='<a hreF="#" '+Delete+' title="Delete Data"><i class="fa fa-trash" aria-hidden="true" style="font-size:24px;"></i></a> &nbsp; &nbsp;'; 
		return f;
	}
    
    function viewDetails(id) {
    	document.getElementById('locationId').value=id;
    	document.getElementById('editForm').submit();
    }
    
    function viewDeleteDetails(id) {
    	document.getElementById('DeleteId').value=id;
    	document.getElementById('deleteForm').submit();
    }
</script>

<script>
		function clear() {
			var tab = $("#applicanttbl > tbody");
			tab.empty();
	
			if (document.getElementById('reportshowsearchmcr').style.display == 'block') {
				document.getElementById('reportshowsearchmcr').style.display = 'none';
			}
	
			localStorage.clear();
			localStorage.Abandon();
		}

</script>