<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css"> 

<style>
 .nr-selected {
	 background-color:yellow;
 }
 </style>

<body class="mmsbg">
<%
	String nPara=request.getParameter("Para");
	 
%>
<form:form id="mms_inter_unit_tfr" name="mms_inter_unit_tfr" action="mms_inter_unit_tfr_mmsAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="mms_inter_unit_tfrCMD" enctype="multipart/form-data">
		<div class="">
		     <div class="container" align="center">
		          <div class="card">
		                 <div class="card-header mms-card-header">
				              <b>
									<%			if (nPara.equalsIgnoreCase("CSTK")) { %>
													CHANGE TYPE OF HOLDING
									<%			}  %>
									<%			if (nPara.equalsIgnoreCase("ITFR")) { %>
													INTER UNIT TRANSFER : UNIT TO UNIT
									<%			}  %>			
									<%			if (nPara.equalsIgnoreCase("IDEP")) { %>
													DEPOSIT TO DEPOT : UNIT TO DEPOT
									<%			}  %>			
									<%			if (nPara.equalsIgnoreCase("DDEP")) { %>
													EQPT TRANSTER : DEPOT TO DEPOT
									<%			}  %>	
				              </b>
		                </div>
		                 
		                 <div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: Centers;"> <strong>PARENT UNIT DETAILS</strong></div>
		                 <div class="card-body card-block">         
  					              <div class="col-md-12 row form-group">
			                			 <div class="col-md-2" style="text-align: left;">
			                  				  <label class=" form-control-label"><strong style="color: red;">* </strong><span id='from_sus_Search_label'>Parent Unit</span></label>
			                			 </div>
			                			
			                			 <div class="col-md-2">
			                  				  <input type="text" id="from_sus_Search" name="from_sus_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10"/>
			                			       <input type="hidden" id="update_id" name="update_id" value ="0" />
			                			 </div>
			                			
			                			 <div class="col-md-1">
			                			       <img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromunit($('#from_sus_Search').val());" title="Click to Search" style="cursor:pointer;">
			                			 </div>
			                			
			                			 <div class="col-md-7">
			                			       <select id="from_sus_no" name="from_sus_no" class="form-control-sm form-control" disabled="disabled">
			                                      <option selected value="-1">--Select Unit--</option>     
			                                   </select>
			                                   <input type="hidden" id="from_code" name="from_code">
			                			 </div>
					              </div>
					              
					              <div class="col-md-12 row form-group" style="margin-top: -10px;">
				             	 		 <div class="col-md-2" style="text-align: left;"> 
					               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of Holding</label>
					             		 </div>
					             		 <div class="col-md-4">
					             			  <select id="type_of_hldg" name="type_of_hldg" class="form-control-sm form-control" disabled="disabled">
			                                      <option selected value="-1">--Select Type of Holding--</option>     
			                                  </select>
					               		 </div>
					               		 
					               		 <div class="col-md-1">
					               		 </div>
					             		 								
					               		 <div class="col-md-2" style="text-align: left;">
					                 		  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of Eqpt</label>
					               		 </div>
					               		 <div class="col-md-3">
			                                  <select name="type_of_eqpt" id="type_of_eqpt" class="form-control-sm form-control">	
													<option value="">--Select--</option>
													<c:forEach var="item" items="${ml_1}">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
													</c:forEach>                  							
                                              </select>
										 </div>	    
  					              </div>
  					              
  					              <div class="col-md-12 row form-group" style="margin-top: -10px;">
					           	 		 <div class="col-md-2" style="text-align: left;"> 
					              			  <label class=" form-control-label"><strong style="color: red;">* </strong>PRF Group</label>
					            		 </div>
					            		 <div class="col-md-10">
					            			  <select id="prf_code" name="prf_code" class="form-control-sm form-control" disabled="disabled">
			                                      <option selected value="-1">--Select PRF Group--</option>     
			                                  </select>
					              		 </div>    
		  				         </div>
		  				         
		  				         <div class="col-md-12 row form-group" style="margin-top: -10px;">
					           	 		 <div class="col-md-2" style="text-align: left;"> 
					              			  <label class=" form-control-label"><strong style="color: red;">* </strong>Nomenclature</label>
					            		 </div>
					            		 <div class="col-md-10">
					            			  <select id="census_no" name="census_no" class="form-control-sm form-control" disabled="disabled">
			                                      <option selected value="-1">--Select Census--</option>     
			                                  </select>
					              		 </div>    
		  				         </div>
		  				         <%	if (nPara.equalsIgnoreCase("ITFR") || nPara.equalsIgnoreCase("IDEP")) { %>
				  				       <div class="col-md-12 row form-group" style="margin-top: -10px;">
					             	 		 <div class="col-md-2" style="text-align: left;"> 
						               			  <label class=" form-control-label"><strong style="color: red;">* </strong>HTO No</label>
						             		 </div>
						             		 <div class="col-md-3">
						             			  <input type="text" id="hto_no" name="hto_no" placeholder="Enter HTO No..." class="form-control-sm form-control" autocomplete="off" maxlength="25" required>
						               		 </div>
						               		 
						               		 <div class="col-md-2">
						               		 </div>
						             		 								
						               		 <div class="col-md-2" style="text-align: left;">
						                 		  <label class=" form-control-label"><strong style="color: red;">* </strong>HTO Date</label>
						               		 </div>
						               		 <div class="col-md-3">
						               			  <input type="date" id="hto_date" name="hto_date" placeholder="" class="form-control-sm form-control" autocomplete="off"> 
											 </div>	    
		  					            </div>
  					              
									
										<div class="col-md-12 row form-group" style="margin-top: -10px;">
					             	 		<div class="col-md-2" style="text-align: left;"> 
												 <label class=" form-control-label"><strong style="color: red;">* </strong>Upload HTO</label>
											 </div>
														 <div class="col-md-3">
												 <input type="file" id="doc_upload1" name="doc_upload1" class="form-control-sm form-control" onchange="checkFileExtImage(this)" autocomplete="off">
													 <input type="hidden" id="doc_up" name="doc_up" class="form-control-sm form-control" autocomplete="off" value="NI">
											 </div>   
			  					        </div>
									<%	} else{ %>	
									     <input type="hidden" id="doc_up" name="doc_up" class="form-control-sm form-control" autocomplete="off" value="NIL">
									<% }%>		 
		                 </div>
		                 
		                 <div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: Centers;margin-top: -10px;"> <strong>RECEIVING UNIT DETAILS</strong></div>
		                 <div class="card-body card-block">
		                         <div class="col-md-12 row form-group">
			                			 <div class="col-md-2" style="text-align: left;">
			                  				  <label class=" form-control-label"><strong style="color: red;">* </strong><span id='to_sus_Search_label'>Receiving Unit</span></label>
			                			 </div>
			                			
			                			 <div class="col-md-2">
			                  				  <input type="text" id="to_sus_Search" name="to_sus_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10"/>
			                			 </div>
			                			
			                			 <div class="col-md-1">
			                			       <img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="gettounit($('#to_sus_Search').val());" title="Click to Search" style="cursor:pointer;">
			                			 </div>
			                			
			                			 <div class="col-md-7">
			                			       <select id="to_sus_no" name="to_sus_no" class="form-control-sm form-control" disabled="disabled">
			                                      <option selected value="-1">--Select Unit--</option>     
			                                   </select>
			                                   <input type="hidden" id="to_code" name="to_code">
			                			 </div>
					              </div> 
					              
					              <div class="col-md-12 row form-group" style="margin-top: -10px;">
				             	 		 <div class="col-md-2" style="text-align: left;"> 
					               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of Holding</label> 
					             		 </div>
					             		 <div class="col-md-3">
					             			  <!-- <select id="to_hld_type" name="to_hld_type" class="form-control-sm form-control">
			                                      <option selected value="-1">--Select Type of Holding--</option>     
			                                  </select> -->
			                                  <select name="to_hld_type" id="to_hld_type" class="form-control-sm form-control">	
													<option value="">--Select--</option>
													<c:forEach var="item" items="${ml_2}">
														<option value="${item.codevalue}" name="${item.label}">${item.label}</option>	
													</c:forEach>                  							
                                              </select>
					               		 </div>
					               		 
					               		 <div class="col-md-2">
					               		 </div>
					             		 								
					               		 <div class="col-md-2" style="text-align: left;">
					                 		  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of Eqpt</label>
					               		 </div>
					               		 <div class="col-md-3">
			                                  <select name="to_eqpt_type" id="to_eqpt_type" class="form-control-sm form-control">	
													<option value="">--Select--</option>
													<c:forEach var="item" items="${ml_1}">
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
													</c:forEach>                  							
                                              </select>
										 </div>	    
  					              </div>
		                 </div>
		                 
		                 <div class="card-footer" align="center" style="margin-top: -15px;">
            			        <input type="button" class="btn btn-primary btn-sm" value="Get Regn List" onclick="getregn();"> 
            	         </div>
            	         
            	         <div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: left;display: none;" id="d_reg2"> <strong>Regn No to be Transfer</strong></div>
            	         <div class="card-body card-block" style="display: none;" id="d_reg">
            	              <div class="col-md-12 row form-group" style='background-color:mistyrose;padding:7px;'>
            	                 <div class="col-md-5">
            	                     <b><input type=checkbox id='nSelAll' name='tregn' onclick="callsetall();">&nbsp;Select all (<b></b><span id="sregn" style='font-size:14px;'>0</span>)</b>&nbsp;&nbsp;
            	                     <input id="nrInputa" type="text" placeholder="Search Regd .." size="20">
					             </div>   
					             <div class="col-md-5" align="right">
					                  <b>Selected Regn No-<span id="tregn" style='font-size:14px;'>0</span></b>
					             </div>
					          </div>   
					          
					          <div class="col-md-12 row form-group">
					              <div class="col-md-5" style="height: 200px;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="srctable">
					              </div>
					              
					               <div class="col-md-2">
					                    <!-- <input type="button" class="btn btn-primary btn-sm" onclick="findselected();" value="" disabled="disabled"> -->
					                    <img src="js/miso/images/r_arrow.png" width="40px;" height="40px;">
					               </div>
					              
					              <div class="col-md-5" style="height: 200px;overflow:auto;width:99%;border:1px solid #000;text-align: left;" id="tartable">
					              </div>
					          </div>
					          
					          <input type="hidden" id="c_val" name="c_val" value="0">
					          <input type="hidden" id="regn_seq_no" name="regn_seq_no" placeholder="" class="form-control-sm nrform-control">
		                      <input type="hidden" id="eqpt_regn_no" name="eqpt_regn_no" placeholder="" class="form-control-sm nrform-control"> 
		                      <input type="hidden" id="Para" name="Para" class="form-control-sm nrform-control" value="<%=nPara%>"> 
		                 </div>
		                 
		                 <div class="card-footer" align="center" style="display: none;" id="d_reg3">
            			        <input type="submit" class="btn btn-Success btn-sm" style="background: lightgreen" value="Submit" onclick="return validate();" disabled="disabled" id="btn_sub">
            	         </div>      
		          </div>
          </div>
      </div>    		          
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script>
function validate(){
	 
	if($("#c_val").val() == 0){
		alert("Please Select the Listed Regd No.");
		return false;
	}
	
	return true;
}

function callsetall() {
	var chkclk=$('#nSelAll').prop('checked');
	if (chkclk) {
		$('.nrCheckBox').prop('checked',true);
		
	} else {
		$('.nrCheckBox').prop('checked',false);
	}
	findselected();
}
function setchanges() {
	
}

function nkflashbox() {
	e1=document.getElementById("NKFLASHBOX");
	e1.style.visibility=(e1.style.visibility=="visible") ? "hidden":"visible";			
}

function findselected() {
	$("#srctable tr").css('background-color','white');
	
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb=$(this).attr('id');
		$("#SRC"+bb).css('background-color','yellow');
		return bb;
	}).get();
	var b=nrSel.join(':');
	
	$("#c_val").val(nrSel.length);

	$("#regn_seq_no").val(b);
	
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		var bb1=$(this).attr('name');
		return bb1;
	}).get();
	var c=nrSel.join(':');
	$("#eqpt_regn_no").val(c);
	
	drawtregn(c);
}

function getregn() {
	
	
	if($("#from_sus_Search").val() == ""){
		alert("Please Search From SUS No or Unit Name");
		$("#from_sus_Search").focus();
	    return false;
	}
	if($("#type_of_hldg").val() == ""){
		alert("Please Select Type of Holding");
		$("#type_of_hldg").focus();
	    return false;
	}   
	
	if($("#type_of_eqpt").val() == ""){
		alert("Please Select Type of Eqpt");
		$("#type_of_eqpt").focus();
	    return false;
	}   
	if($("#prf_code").val() == "-1"){
		alert("Please Select the PRF Group");
		$("#prf_code").focus();
	    return false;
	}
	
	if($("#census_no").val() == "-1"){
		alert("Please Select the Census No");
		$("#census_no").focus();
	    return false;
	}
	
	if($("#hto_no").val() == ""){
		alert("Please Enter HTO No");
		$("#hto_no").focus();
	    return false;
	}
	
	if($("#hto_date").val() == ""){
		alert("Please Select the HTO Date");
		$("#hto_date").focus();
	    return false;
	}
	
	 <% if (nPara.equalsIgnoreCase("ITFR") || nPara.equalsIgnoreCase("IDEP")) { %>
	if($("#doc_upload1").get(0).files.length == 0){
		alert("Please Select Upload File.");
		$("#doc_upload1").focus();
		return false;
	} 
	<% } %>
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	
	if($("#hto_date").val() > c_d){
		$("#hto_date").focus();
		alert("Can't select the Future Date");
		return false;
	}
	
	else if($("#from_sus_no").val() == "-1"){
		alert("Please Select From Unit");
		$("#from_sus_no").focus();
	    return false;
	} 
	
	if($("#type_of_hldg").val() == null){
		alert("No Data Found");
		$("#from_sus_Search").focus();
	    return false;
	}    
	

 	if($("#prf_code").val() == "-1"){
		alert("Please Select the PRF Group");
		$("#prf_code").focus();
	    return false;
	}
	
	if($("#census_no").val() == "-1"){
		alert("Please Select the Census No");
		$("#census_no").focus();
	    return false;
	} 
	
	if($("#to_sus_Search").val() == ""){
		alert("Please Search To SUS No or Unit Name");
		$("#to_sus_Search").focus();
	    return false;
	}
	else if($("#to_sus_no").val() == "-1"){
		alert("Please Select To Unit");
		$("#to_sus_no").focus();
	    return false;
	}
	
	if($("#to_hld_type").val() == ""){
		alert("Please Select Type of Holding");
		$("#to_hld_type").focus();
	    return false;
	}   
	
	if($("#to_eqpt_type").val() == ""){
		alert("Please Select Type of Eqpt");
		$("#to_eqpt_type").focus();
	    return false;
	}   
	 
		
	$("#btn_sub").attr('disabled',false);
	
	var nSUSNo=$("#from_sus_no").val();
	var nCensus=$("#census_no").val();
	var nHldType=$("#type_of_hldg").val();
	var nEqptType=$("#type_of_eqpt").val(); 

	

	
	$.post("mms_list_regn_no_tfr?"+key+"="+value, {sus_no:nSUSNo,census_no:nCensus,type_of_hldg:nHldType,regn_seq_no:""}, function(data){ }).done(function(data) {
		if(data == ""){
   			$("#srctable").empty();
   			$("#tartable").empty();
   			alert("No Regd Data Found");
       	}else{
       		$("#d_reg").show();
       		$("#d_reg2").show();
       		$("#d_reg3").show();
       		$("#srctable").val(data);
       		drawregn(data);
       	}
       }).fail(function(xhr, textStatus, errorThrown) {
	});
}

function getfromunit(nValue) {
	    if (nValue.length<=0) {
	    	alert("Enter Search Word...");
	    	return false;
	    } else {
			var Fromcallfn = getUnitType('F');
			
		            $.post(Fromcallfn, {
		            	nValue:nValue
		        }).done(function(j) {
		        	
		        	if(j.length <= 0 || j == null || j == ''){
			 			alert("No Search Result Found");
			 			$("#from_sus_Search").focus();
			 		}else{
			 			
			 			$("#from_sus_no").attr('disabled',false);
			 			var options = '<option value="' + "-1" + '">'+ "--Select Unit--" + '</option>';
			 			
			 			var a = [];
			 			var enc = j[j.length-1].substring(0,16);
			 			for(var i = 0; i < j.length; i++){
							a[i] = dec(enc,j[i]);
							
		                }
			 			
			 			var data=a[0].split(",");
						var datap;
						
						for(var i = 0; i < data.length-1; i++){
							datap=data[i].split(":");
							
							if (datap!=null) {
								if (datap[1].length>90) {
									options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'+ datap[1]+ ' - '+ datap[2].substring(1,50)+ '</option>';
								} else {
									options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'+ datap[1]+ ' - '+ datap[2]+ '</option>';
								}
							}
						
						}
			 		}
		        	$("select#from_sus_no").html(options);
		                
		        }).fail(function(xhr, textStatus, errorThrown) {
		        });
	 	
	    }
		$('#from_sus_no').change(function() {
			var from_code = $(this).find('option:selected').attr("name");
			$("#from_code").val(from_code); //from_code
		});
}

function gettounit(nValue) {
	var Tocallfn = getUnitType('T');

		
	            $.post(Tocallfn, {
	            	nValue:nValue
	        }).done(function(j) {
	  
	        	if(j.length <= 0 || j == null || j == ''){
	     			alert("No Search Result Found");
	     			$("#to_sus_Search").focus();
	     		}else{
	     			$("#to_sus_no").attr('disabled',false);
	     			var options = '<option value="' + "-1" + '">'+ "--Select Unit--" + '</option>';
	     			
	     			var a = [];
	     			var enc = j[j.length-1].substring(0,16);
	     			for(var i = 0; i < j.length; i++){
	    				a[i] = dec(enc,j[i]);
	                }
	     			
	     			var data=a[0].split(",");
	    			var datap;

	    		    for(var i = 0; i < data.length-1; i++){
	    				datap=data[i].split(":");
	    				if (datap!=null) {
	    					if (datap[1].length>90) {
	    						options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'+ datap[1]+ ' - '+ datap[2].substring(1,50)+ '</option>';
	    					} else {
	    						options += '<option value="'+datap[1]+'" name="' + datap[0]+ '" >'+ datap[1]+ ' - '+ datap[2]+ '</option>';
	    					}
	    				}
	    			}
	    		    $("select#to_sus_no").html(options); 
	     		}
	        }).fail(function(xhr, textStatus, errorThrown) {
	        });
 	
 	
 	
	$('#to_sus_no').change(function() {
		var to_code = $(this).find('option:selected').attr("name");
		$("#to_code").val(to_code); //from_code
	});
}

function getUnitType(uname) {


<%			if (nPara.equalsIgnoreCase("CSTK")) { %>
					var Fromcallfn = "getMMSDepotListBySearch?"+key+"="+value;
					var Tocallfn = "getMMSDepotListBySearch?"+key+"="+value;
					var fcodevalue = "D:R";
					var tcodevalue = "D:R";   
<%			}  %>
<%			if (nPara.equalsIgnoreCase("ITFR")) { %>
				    var Fromcallfn = "getMMSUnitListBySearch?"+key+"="+value;
				    var Tocallfn = "getMMSUnitListBySearchToUnit?"+key+"="+value;
				    var fcodevalue = "A:R";
					var tcodevalue = "A:R";
<%			}  %>			
<%			if (nPara.equalsIgnoreCase("IDEP")) { %>
				    var Fromcallfn = "getMMSUnitListBySearch?"+key+"="+value;
				    var Tocallfn = "getMMSUnitListBySearchToUnit?"+key+"="+value;
				    var fcodevalue = "A:R";
					var tcodevalue = "G";
<%			}  %>			
<%			if (nPara.equalsIgnoreCase("DDEP")) { %>
				    var Fromcallfn = "getMMSDepotListBySearch?"+key+"="+value;
				    var Tocallfn = "getMMSUnitListBySearchToUnit?"+key+"="+value;
				    var fcodevalue = "D:R";
					var tcodevalue = "D:R";
<%			}  %>			
			if (uname=='F') {
				return Fromcallfn;
			}
			if (uname=='T') {
				return Tocallfn;
			}
}


$(document).ready(function() {
	
			
<%			if (nPara.equalsIgnoreCase("CSTK")) { %>
				var Fromcallfn = "getMMSDepotListBySearch?"+key+"="+value;
				var Tocallfn = "getMMSDepotListBySearch?"+key+"="+value;
				var fcodevalue = "D:R";
				var tcodevalue = "D:R";   
				$('#from_sus_Search_label').text('Parent Depot');
				$('#to_sus_Search_label').text('Receiving Depot');
<%			}  %>
<%			if (nPara.equalsIgnoreCase("ITFR")) { %>
                $().getCurDt(hto_date);
			    var Fromcallfn = "getMMSUnitListBySearch?"+key+"="+value;
			    var Tocallfn = "getMMSUnitListBySearchToUnit?"+key+"="+value;
			    var fcodevalue = "A:R";
				var tcodevalue = "A:R";
				$('#from_sus_Search_label').text('Parent Unit');
				$('#to_sus_Search_label').text('Receiving Unit');
<%			}  %>			
<%			if (nPara.equalsIgnoreCase("IDEP")) { %>
                $().getCurDt(hto_date);
			    var Fromcallfn = "getMMSUnitListBySearch?"+key+"="+value;
			    var Tocallfn = "getMMSDepotListBySearch?"+key+"="+value;
			    var fcodevalue = "A:R";
			    var tcodevalue = "G";
				$('#from_sus_Search_label').text('Parent Unit');
				$('#to_sus_Search_label').text('Receiving Depot');
<%			}  %>			
<%			if (nPara.equalsIgnoreCase("DDEP")) { %>
			    var Fromcallfn = "getMMSDepotListBySearch?"+key+"="+value;
			    var Tocallfn = "getMMSDepotListBySearch?"+key+"="+value;
			    var fcodevalue = "D:R";
				var tcodevalue = "D:R";
				$('#from_sus_Search_label').text('Parent Depot');
				$('#to_sus_Search_label').text('Receiving Depot');
<%			}  %>	
try{
	 if(window.location.href.includes("msg=")){
		 var url = window.location.href.split("?msg")[0];
	     window.location = url;
    } 
}catch (e) {
	 // TODO: handle exception
}

	 $('#from_sus_no').change(function() {
		var nParaValue ="";
		if ($("#from_sus_no").val()!=null) {
		 	nParaValue = $("#from_sus_no").val();
		}
		var nPara = "SUS";
		$.post("getmms_distinct_type_of_hldg_by_sus_frmtbl?"+key+"="+value, {
		   	nParaValue:nParaValue,nPara:nPara
		}, function(j) {
		}).done(function(j) {
		   	if(j.length<=0 || j==""){
				alert("Sorry! No Type of Holding Found.");
				var options = '<option value="' + "-1" + '">'+ "--Select Type of Hldg--" + '</option>';
				$("select#type_of_hldg").html(options);
				$("#type_of_hldg").attr('disabled',true);
				return false;
			}else{
				var options = '<option value="' + "-1" + '">'+ "--Select Type of Hldg--" + '</option>';
				var a = [];
				var enc = j[j.length-1].substring(0,16);
				for(var i = 0; i < j.length; i++){
					 a[i] = dec(enc,j[i]);
				}
							
				var data=a[0].split(",");
				var datap;
				for(var i = 0; i < data.length-1; i++){
					datap=data[i].split(":");
					if(datap!=null){
						 if(datap[1].length>90){
							 options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1].substring(1,90)+ '</option>';
						 }else{
							 options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1]+ '</option>';
						 }
					}
				}
				$("select#type_of_hldg").html(options);
			}
		}).fail(function(xhr, textStatus, errorThrown) {});
		
		$.post("getmms_distinct_prf_group_by_sus?"+key+"="+value, {
			nParaValue:nParaValue,nPara:nPara
		}, function(j) {}).done(function(j) {
			if (j.length<=0 || j=="") {
				alert("Sorry! No PRF Group Found.");
				var options = '<option value="' + "-1" + '">'+ "--Select PRF Group--" + '</option>';
				$("select#prf_code").html(options);
				$("#prf_code").attr('disabled',true);
				$("#census_no").attr('disabled',true);
				return false;
			}else{
				var options = '<option value="' + "-1" + '">'+ "--Select PRF Group--" + '</option>'; 
				var a = [];
				var enc = j[j.length-1].substring(0,16);
				for(var i = 0; i < j.length; i++){
					a[i] = dec(enc,j[i]);
				}
				var data=a[0].split(",");
				var datap;
				for(var i = 0; i < data.length-1; i++){
					datap=data[i].split(":");
					if (datap!=null) {
						if (datap[1].length>90) {
							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1].substring(1,90)+ '</option>';
						} else {
							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1]+ '</option>';
						}
					}
				}
				$("select#prf_code").html(options); 
					
				var q = '${m_6}';
				var q1 = '${m_2}';
				var pq = $("#from_sus_no").val();
					
				if(q != "" && q1 != ""){
					$("select#prf_code").val(q); 
				}
			}
		}).fail(function(xhr, textStatus, errorThrown) {});
		
		$("#type_of_hldg").attr('disabled',false);
		$("#prf_code").attr('disabled',false);
	});
	 
/*  	 $('#type_of_hldg').change(function() {
	 	 if('${roleAccess}' != 'MISO' ){
			 var type_of_hldg_code = this.value;
			 var type_of_hldg_name = $(this).find('option:selected').attr("name");
			 var options = '<option value="'+type_of_hldg_code +'" name="' + type_of_hldg_name+ '" >'+ type_of_hldg_name + '</option>';
			 $("select#to_hld_type").html(options);
		 } 
	 }); 
	  */
/* 	$('#type_of_eqpt').change(function() {
		if('${roleAccess}' != 'MISO'){
			var to_eqpt_type_code = this.value;
			var to_eqpt_type_name = $(this).find('option:selected').attr("name");
			var options = '<option value="'+to_eqpt_type_code +'" name="' + to_eqpt_type_name+ '" >'+ to_eqpt_type_name + '</option>';
			$("select#to_eqpt_type").html(options);
		}
	}); */
	
	$('#prf_code').change(function() {
		var nPrfCode = $("#prf_code").val();
		var nSusNo = $("#from_sus_no").val();
		
		$.post("getmms_distinct_census_by_sus?"+key+"="+value, {nSusNo:nSusNo,nPrfCode:nPrfCode}, function(j) { }).done(function(j) {
			if (j.length<=0 || j=="") {
				alert("Sorry! Census No Not Found.");
				var options = '<option value="' + "-1" + '">'+ "--Select Census--" + '</option>';
				$("select#census_no").html(options); 
				$("#census_no").attr('disabled',true);
				return false;
			}else{
				var options = '<option value="' + "-1" + '">'+ "--Select Census--" + '</option>';
				var a = [];
				var enc = j[j.length-1].substring(0,16);
		 		for(var i = 0; i < j.length; i++){
						a[i] = dec(enc,j[i]);
		            }
		 			
		 			var data=a[0].split(",");
					var datap;
					
					for(var i = 0; i < data.length-1; i++){
						datap=data[i].split(":");
						if (datap!=null) {
							if (datap[1].length>90) {
								options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1].substring(1,60)+ '</option>';
							} else {
								options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1]+ '</option>';
							}
						}
					}
					$("select#census_no").html(options);	
					
					var q = '${m_3}';
				  
				    var pq = $("#prf_code").val();
				    
				   
				    
				    if(q != "" && nSusNo != "" && nPrfCode != ""){
				    	$("#census_no").val(q); 
				    }
				}
	                
	        }).fail(function(xhr, textStatus, errorThrown) {
	        });
		$("#census_no").attr('disabled',false);
	});
	 

	            
	
	   
	$("#nrInputa").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#srctable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	 try{
		 if(window.location.href.includes("msg=")){
			 var url = window.location.href.split("?msg")[0];
		     window.location = url;
		 } 	
	 }catch (e) {
		 // TODO: handle exception
	 } 
	      		
});

function drawregn(j) {
	var ii=0;
	$("#srctable").empty();
	$("#tartable").empty();
	
	var a = [];
	var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
		a[i] = dec(enc,j[i]);
    }
	
	var data=a[0].split(",");
	var datap;	
	
	for(var i = 0; i < data.length-1; i++){
		datap=data[i].split(":");
		var row="<tr id='SRC"+datap[5]+"' padding='5px;'>";
		row=row+"<td>&nbsp;<input class='nrCheckBox' type=checkbox id='"+datap[5]+"' name='"+datap[4]+"' onclick='findselected();'>&nbsp;";
		row=row+ datap[4] +"</td>";
		$("#srctable").append(row);
		ii=ii+1;
	}
		

	$("#sregn").text(ii);
}

function drawtregn(data) {
	var ii=0;
	
	$("#tartable").empty();
	var datap=data.split(":");
	
	for (var i = 0; i <datap.length; i++) {
		 var nkrow="<tr id='tarTableTr' padding='5px;'>";
		 
		 nkrow=nkrow+"<td>&nbsp;&nbsp;";
		
		 nkrow=nkrow+ datap[i]+"</td>";
		
		 $("#tartable").append(nkrow);
		 ii=ii+1;
	}
	$("#tregn").text(ii);
}

/* function checkFileExtImage(file) {
    var ext = file.value.match(/\.([^\.]+)$/)[1];
  switch (ext) {
            case 'jpg':
            case 'jpeg':
            case 'png':
            case 'pdf':         
           alert('Allowed');
      break;
            default:
              alert('Only *.jpg, *.jpeg *.png and *.pdf  file extensions allowed');
             file.value = "";
  }
} */


        function checkFileExtImage(file) {
                var ext = file.value.match(/\.([^\.]+)$/)[1];
                switch (ext) {
                case 'jpg':
                case 'jpeg':
                case 'png':
                case 'pdf':
                case 'JPG':
                case 'JPEG':
                case 'PNG':
                case 'PDF':
                        alert('Allowed');
                        break;
                default:
                        alert('Only *.jpg, *.jpeg *.png and *.pdf  file extensions allowed');
                        file.value = "";
                }
        }

</script>
