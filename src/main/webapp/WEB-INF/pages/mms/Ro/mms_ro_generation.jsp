<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
 <link rel="stylesheet" href="js/common/nrcss.css"> 
<link rel="stylesheet" href="js/miso/assets/scss/style.css">

<body class="mmsbg">
<form:form action="ro_generationAction" method="post" class="form-horizontal" commandName="ro_generationCMD"> 
      <div class="">
	    	<div class="container" align="center">
	        	<div class="card">
	     
		             <div class="card-header mms-card-header">
		                  <b>GENERATE NEW RO</b>
		                  <br><span class="mms-card-subheader">(To be entered by MGO EM/IM)</span>
		             </div> 
	        
	          		<div class="card-body card-block">
	          		         <div class="col-md-12 row form-group">
		                			<div class="col-md-2" style="text-align: left;">
		                  				 <label for="text-input" class=" form-control-label"><strong style="color: red;" >* </strong>Section</label>
		                			</div>
		                			<div class="col-md-4">
		                  				  <select name="section" id="section" class="form-control-sm form-control" >	
		                  				        <option value="-1">--Select--</option>
												<c:forEach var="item" items="${ml_1}">
													<option value="${item.label_s}">${item.label}</option>	
												</c:forEach>                  							
										  </select>
									</div>
		  								
		                			<div class="col-md-2" style="text-align: left;">
		                  				<label for="text-input" class=" form-control-label" style="margin-left: 13px;">Ref. No</label>
		                			</div>
		                			<div class="col-md-4">               					 
		                  				<input type="text" id="ref_no" name="ref_no" class="form-control-sm form-control" placeholder="Enter Ref. No..." 
		                  				maxlength="50"  autocomplete="off">
									</div> 		
					         </div>	
	          		
		            		 <div class="col-md-12 row form-group" style="margin-top: -10px;">
		                			<div class="col-md-2" style="text-align: left;">
		                  				 <label for="text-input" class=" form-control-label"><strong style="color: red;" >* </strong>RO No</label>
		                			</div>
		                			<div class="col-md-4">
		                  				 <input type="text" id="ro_no" name="ro_no" maxlength="25" class="form-control-sm form-control" readonly="readonly"/>
									</div>
		  								
		                			<div class="col-md-2" style="text-align: left;">
		                  				<label for="text-input" class=" form-control-label"><strong style="color: red;" >* </strong>RO Date</label>
		                			</div>
		                			<div class="col-md-3">               					 
		                  				<input type="text" id="ro_date" name="ro_date" class="form-control-sm form-control" maxlength="10" readonly="readonly">
									</div> 		
					         </div>	
					         
					          <div class="col-md-12 row form-group" style="margin-top: -10px;">
		                			<div class="col-md-2" style="text-align: left;">
		                  				 <label  class=" form-control-label" ><strong style="color: red;" >* </strong>RO Agency</label>
		                			</div>
		                			<div class="col-md-4">
		                  				  <select name="ro_agency" id="ro_agency" class="form-control-sm form-control" >	
		                  				        <option value="-1">--Select--</option>
												<c:forEach var="item" items="${ml_2}">
													<option value="${item[1]}" name="${item[1]}">${item[1]}</option>	
												</c:forEach>                  							
										  </select>
									</div>
		  								
		                			<div class="col-md-2" style="text-align: left;">
		                  				<label class=" form-control-label" ><strong style="color: red;" >* </strong>RO Type</label>
		                			</div>
		                			<div class="col-md-4">               					 
		                  				 <select name="ro_type" id="ro_type" class="form-control-sm form-control" >	
		                  				        <option value="-1">--Select--</option>
												<c:forEach var="item" items="${ml_3}">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
												</c:forEach>                  							
										 </select>  
									</div> 		
					         </div>		
					         
					         <div class="col-md-12 row form-group" style="margin-top: -10px;">
		                			<div class="col-md-2" style="text-align: left;">
		                  				 <label class=" form-control-label" ><strong style="color: red;" >* </strong>RO Against</label>
		                			</div>
		                			<div class="col-md-4">	 
		                  				 <select name="ro_for" id="ro_for" class="form-control-sm form-control" disabled="disabled">	
		                  				        <option value="-1">--Select--</option>
												<c:forEach var="item" items="${ml_0}">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
												</c:forEach>                 							
										  </select>
									</div>
		  								
		                			<div class="col-md-2" style="text-align: left;">
		                  				<label  class=" form-control-label" ><strong style="color: red;" >* </strong>Stock</label>
		                			</div>
		                			<div class="col-md-4">               					 
		                  				<select name="type_of_stk" id="type_of_stk" class="form-control-sm form-control" >	
		                  				        <option value="-1">--Select--</option>
												<c:forEach var="item" items="${ml_7}">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
												</c:forEach>                  							
										</select>          						  
									</div> 		
					         </div>	
					         
					         <div class="col-md-12 row form-group" style="margin-top: -10px;"> 
					               <div class="col-md-2" style="text-align: left;">
		                  				<label  class=" form-control-label" ><strong style="color: red;" >* </strong>Type of Issue</label>
		                		   </div>
		                		   <div class="col-md-4">               					 
		                  				<select name="type_of_issue" id="type_of_issue" class="form-control-sm form-control" >	
		                  				        <option value="-1">--Select--</option>
												<c:forEach var="item" items="${ml_4}">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
												</c:forEach>                  							
										</select>          						  
								   </div>
								   <div class="col-md-2" style="text-align: left;">
		                  				<label for="text-input" class=" form-control-label"><strong style="color: red;" >* </strong>RO Valid Upto</label>
		                			</div>
		                			<div class="col-md-4">               					 
		                  				<input type="date" id="ro_valid" name="ro_valid" maxlength="10" class="form-control-sm form-control">
									</div>
					         </div>
					         
					         <div class="col-md-12 row form-group" style="display: none;margin-top: -10px;" id="btn_head">
		                			<div class="col-md-2" style="text-align: left;">
		                  				<label for="text-input" class=" form-control-label"><strong style="color: red;" >* </strong>Budget Head</label>
		                			</div>
		                			<div class="col-md-4">               					 
		                  				<select name="pbd_head" id="pbd_head" class="form-control-sm form-control" >	
		                  				        <option value="-1">--Select--</option>
												<c:forEach var="item" items="${ml_5}">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
												</c:forEach>                  							
										</select>
									</div> 	
									
									<div class="col-md-2" style="text-align: left;">	 
		                			</div>
		                			<div class="col-md-4">  
									</div>	
					         </div>	
					         
					         <div class="col-md-12 row form-group" style="margin-top: -10px;">
			               			 <div class="col-md-2" style="text-align: left;">
			                 			  <label class=" form-control-label"><strong style="color: red;">* </strong><span id='ro_unit_label'>Unit</span></label>
			               			 </div>
			               			
			               			 <div class="col-md-2">
			                 			  <input type="text" id="from_sus_Search" name="from_sus_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10" disabled="disabled"/>
			               			 </div>
			               			
			               			 <div class="col-md-1">
			               			       <img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromunit($('#from_sus_Search').val());" 
			               			       title="Click to Search" style="cursor:pointer;" id="im_1">
			               			 </div>
			               			
			               			 <div class="col-md-7">
			               			       <select id="ro_unit" name="ro_unit" class="form-control-sm form-control" disabled="disabled">
			                                     <option selected value="-1">--Select Unit--</option>     
			                               </select>
			                               <input type="hidden" id="to_sus_no" name="to_sus_no" class="form-control" />
			               			 </div>
							 </div>	
					         
					          <div class="col-md-12 row form-group" style="margin-top: -10px;">
		                			<div class="col-md-2" style="text-align: left;">
		                  				 <label class=" form-control-label" style="margin-left: 13px;">Command</label> 
		                			</div>
		                			<div class="col-md-10">
		                  				 <input type="text" id="ro_command" name="ro_command" class="form-control-sm form-control" maxlength="100" readonly="readonly"/>
		                  				 <input type="hidden" id="ro_command_sus" name="ro_command_sus"/>
									</div>
					         </div>	
					         
					         <div class="col-md-12 row form-group" style="margin-top: -10px;">
		                			<div class="col-md-2" style="text-align: left;">
		                  				 <label class=" form-control-label"><strong style="color: red;" >* </strong>PRF</label>
		                			</div>
		                			
		                			<div class="col-md-2">
		                  				 <input type="text" id="from_prf_Search" name="from_prf_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10" disabled="disabled"/>
		                			</div>
		                			
		                			<div class="col-md-1">
		                			      <img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromprf();" title="Click to Search" style="cursor:pointer;">
		                			</div>
		                			
		                			<div class="col-md-7">
		                			      <select id="ro_prf" name="ro_prf" class="form-control-sm form-control" disabled="disabled">
		                                     <option selected value="-1">--Select PRF Group--</option>     
		                                  </select>
		                                  <input type="hidden" id="prf_group" name="prf_group" class="form-control" />
		                			</div>
					         </div>
					         
					         <div class="col-md-12 row form-group" style="margin-top: -10px;">
		                			<div class="col-md-2" style="text-align: left;">
		                  				 <label class=" form-control-label" style="margin-left: 13px;">Census No</label>
		                			</div>
		                			<div class="col-md-10">
		                  				 <select name="census_no" id="census_no" class="form-control-sm form-control"  disabled="disabled">
			                                    <option selected disabled value="-1">--Select Item Nomenclature--</option>     
			                             </select>
									</div>
					         </div>
					         
					         <div align="center" style="margin-bottom: 15px; display: none;" id="u_h">
			                      <input type="button" class="btn btn-success btn-sm" value="Get UE & UH " onclick="get_ue_uh();">
			                 </div>
			                 
			            <div id="unit_l" style="display: none"> 
				                 <div class="col-md-12 row form-group" style="margin-top: -10px;">
				                        <div class="col-md-2" style="text-align: left;">
			                  				 <label class=" form-control-label"><strong style="color: red;">* </strong>UE</label>
			                			</div>
			                			<div class="col-md-4">
			                  				 <input type="text" id="ro_ue" name="ro_ue" style="text-align:center"  class="form-control-sm form-control" maxlength="5" readonly="readonly"/>
										</div>
			  								
			                			<div class="col-md-2" style="text-align: left;">
			                  				<label class=" form-control-label"><strong style="color: red;">* </strong>UH</label> 
			                			</div>
			                			<div class="col-md-4">               					 
			                  				<input type="text" id="ro_uh" name="ro_uh" style="text-align:center" class="form-control-sm form-control" maxlength="5" readonly="readonly"/> 					               
	  					                </div>		
			  		             </div>
		  		             
			  		             <div class="col-md-12 row form-group" style="margin-top: -10px;">
			  		                    <div class="col-md-2" style="text-align: left;">
			                  				 <label class=" form-control-label"><strong style="color: red;">* </strong>Qty</label> 
			                			</div>
			                			<div class="col-md-3">
			                  				 <input type="text" id="ro_qty" name="ro_qty" class="form-control-sm form-control"
			                  				  onkeypress="return validateNumber(event, this)" maxlength="5" placeholder="Enter Qty..."/>
										</div>
			  		             </div>
		  		             
			  		             <div class="col-md-12 row form-group" style="margin-top: -10px;">
			  		                    <div class="col-md-2" style="text-align: left;">
			                  				 <label class=" form-control-label"><strong style="color: red;">* </strong>Depot Name</label> 
			                			</div>
			                			<div class="col-md-10">
		                                     <select name="rel_depot_sus" id="rel_depot_sus" class="form-control-sm form-control" >	
			                  				        <option value="-1">--Select--</option>
													<c:forEach var="item" items="${ml_6}">
														<option value="${item[1]}" name="${item[2]}">${item[1]} - ${item[2]}</option>
													</c:forEach>                  							
										     </select>
		                                     
								             <input type="hidden" id="rel_depot_sus_code" name="rel_depot_sus_code" class="form-control-sm form-control"/> 			      
										</div>
			  		             </div>
			  		             
			  		             <div class="col-md-12 row form-group" style="margin-top: -5px;">
			  		                    <div class="col-md-2" style="text-align: left;">
			                  				 <label class=" form-control-label" style="margin-left:13px;">Instructions</label> 
			                			</div>
			                			<div class="col-md-10">
			                  				 <textarea id="inst_rem" name="inst_rem" class="form-control-sm form-control" placeholder="Enter Any Instructions..."></textarea>
										</div>
			  		             </div>
		  		             
			  		             <div class="col-md-12 row form-group" style="margin-top: -5px;">
			  		                    <div class="col-md-2" style="text-align: left;">
			                  				 <label class=" form-control-label" style="margin-left:13px;">Remarks</label> 
			                			</div>
			                			<div class="col-md-4">
			                  				 <textarea id="remarks" name="remarks" class="form-control-sm form-control" maxlength="255" placeholder="Enter Remarks..."></textarea>
										</div>
			  		             </div>
			  		            
			  		             <div style="margin-bottom: 15px;" align="center">
	  			                        <input type="button" class="btn btn-success btn-sm" value="Add" onclick="return check();">
	  			                 </div>
		  		          </div>   
  					 </div>
  					 
  					<div class="card-footer" align="center" style="margin-top: -15px;">
                       <!--   <input type="button" class="btn btn-primary btn-sm" value="Clear" onclick="btn_clc();" />  -->
                         <input type="button" class="btn btn-primary btn-sm" value="Clear" onclick="btn_clc();" />  
                         <!-- <a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a> -->
                         <a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
	                </div>	   
  			  	</div>	
		      </div>
	      </div>
	      
    <div class="card" id="unit_hid2" style="display: none;">		      
	
					<div class="nrTableMain2Search" align="right" colspan='1' style="padding-right: 20px;" > 
									<label>Search in Result</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							    </div> 
			    	
									<table border="1">
									<thead style="text-align: center;">
		                  			   <tr>
		            							<th  width="10%">Issued To</th>
		            							<th  width="10%" >PRF Group</th>
		            							<th  width="10%" >RO Against</th>
		            							<th  width="10%" >Stock</th>
		            							<th  width="10%" >Type of Issue</th>
		            							<th  width="5%">UE</th>
		            							<th  width="5%">UH</th>
		            							<th  width="5%">Qty to be Issued</th>
		            							<th  width="15%">Depot Name</th>
		            							<th  width="10%">Instructions</th>
		            							<th  width="10%">Remarks</th>             
		                  			   </tr>
		                			</thead>
		                        	<tbody id="nrTable">							
		    					</tbody>
									</table>	
	<div class="card-footer" align="center">
            <input type="submit" class="btn btn-success btn-sm"  value="Submit" id="sbt_btn" style="display: none;" onclick="return chgbtn();">
	    </div>		
	</div>					 
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script>
function btn_clc(){
	location.reload(true);
}

function chgbtn(){
	$("#section").attr('disabled',false);
	$("#ro_agency").attr('disabled',false);
	$("#ro_type").attr('disabled',false);
	$("#ro_for").attr('disabled',false);
}

function getfromunit(nValue) {
    if (nValue.length<=0) {
    	alert("Enter Search Word...");
    	return false;
    } else {
    	var ro_type = $("#ro_type").val();
    	var a_name;
    	if(ro_type == 2){
    		
    		a_name="getMMSDistHirarSingleName?"+key+"="+value;
    	}else{
    		a_name="getMMSUnitListBySearch?"+key+"="+value;
    		
    	}

   
                $.post(a_name, {
                	nValue:nValue
            }, function(data) {
                  

            }).done(function(j) {
                    
            
                    if(j.length <= 0 || j == null || j == ''){
        	 			alert("No Search Result Found");
        	 			$("#from_sus_Search").focus();
        	 		}else{
        	 			$("#ro_unit").attr('disabled',false);
        	 			
        	 			if('${r_1[0][7]}' != "UNIT"){
        	 				var options = '<option value="' + "-1" + '">'+ "--Select Unit--" + '</option>';
        	 			} 
        	 			
        	 			var a = [];
        	 			var enc = j[j.length-1].substring(0,16);
        	 			for(var i = 0; i < j.length; i++){
        					a[i] = dec(enc,j[i]);
                        }
        	 		
        	 			var data;
        	 			var datap;
        	 			if(a_name == "getMMSDistHirarSingleName?"+key+"="+value){
        	 				data=a;
        	 			}else{
        	 				data=a[0].split(",");
        	 			}
        	 			
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
        				$("select#ro_unit").html(options); 
        	 		}
                    
            }).fail(function(xhr, textStatus, errorThrown) {
            });
    }   
}

function getfromprf(){
	var nParaValue = $("#from_prf_Search").val();
	if(nParaValue.length<=0) {
		if($("#from_prf_Search").attr('disabled') != 'disabled'){
			alert("Enter Search Word...");
		}
	    return false;
	}else{

        $.post("getMMSPRFtListBySearch?"+key+"="+value, {
        	nParaValue:nParaValue
    }, function(data) {
            
    }).done(function(j) {
    	if(j.length <= 0 || j == null || j == ''){
			alert("No Search Result Found");
 			$("#from_prf_Search").focus();
 		}else{
 			$("#census_no").val('');
 			$("#census_no").attr('disabled',true);
 			$("#ro_prf").attr('disabled',false);
 			var options = '<option value="' + "-1" + '">'+ "--All PRF Groups--" + '</option>';
			
 			var a = [];
 			var enc = j[j.length-1].substring(0,16);
 			for(var i = 0; i < j.length; i++){
				a[i] = dec(enc,j[i]);
            }
 			
			var data=a[0].split(",");
			var datap;
			for ( var i = 0; i < data.length-1; i++) {
				datap=data[i].split(":");
				if (datap!=null) {
					if (datap[1].length>90) {
						options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1].substring(1,90)+ '</option>';
					} else {
						options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1]+ '</option>';
					}
				}
			}	
			$("select#ro_prf").html(options); 
 		}
            
    }).fail(function(xhr, textStatus, errorThrown) {
    });
		
	}
}

function getnuhval() {
	var nSUSNo=$("#to_sus_no").val();
	var nPRF = $("#ro_prf").val();
	var nCensus = $("#to_sus_no").val();
	var nHldType="A0";
	var nEqptType="1";
	var nWE="ALL";
	var nPara="SUMM";
	

             $.post("getnuhval?"+key+"="+value, {
            	 nSUSNo:nSUSNo,nPRF:nPRF,nHldType:nHldType,nEqptType:nEqptType,nPara:nPara
         }, function(data) {
               
         }).done(function(j) {
        	 if(j == ""){
     			$("#ro_uh").val("0");
     		}else{
     			var enc = j[1].substring(0,16);
     			var a = dec(enc,j[0]);
     			$("#ro_uh").val(a);
     		}
         }).fail(function(xhr, textStatus, errorThrown) {
         });
}

function getnueval() {
	var nPRF = $("#ro_prf").val();
	var nSUSNo=$("#to_sus_no").val();
	var nItemCd="ALL";
	var nWE="ALL";
	var nPara="UE";
	
             $.post("getnueval?"+key+"="+value, {
            	 nSUSNo:nSUSNo,nPRF:nPRF,nWE:nWE,nPara:nPara
         }, function(j) {
                 
         }).done(function(j) {
        	 if(j == ""){
     			$("#ro_ue").val('0');
     		}else{
     			var enc = j[1].substring(0,16);
     			var a = dec(enc,j[0]);
     			$("#ro_ue").val(a);
     		}
         }).fail(function(xhr, textStatus, errorThrown) {
         });
	
}

function get_ue_uh(){
	  $("#unit_l").show();
	  $("#u_h").hide();
	  getnuhval();
	  getnueval(); 
} 

function validateNumber(evt) {
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode != 46 && charCode > 31 && (charCode< 48 || charCode> 57)) { 
            return false;
    } else {
        if (charCode == 46) {
                return false;
        }
    }
    return true;
}

function drawTable1(data){		
	var len = $('#nrTable > tr').length;
	var tab = $("#nrTable");
	catId = '0';
	
	var selectElement = document.getElementById('ro_for');
	var index = selectElement.selectedIndex;		      
    var ro_type_name=selectElement.options[index].text	

    var nkrow="<tr id='NRRDO' name='NRRDO' >";
    nkrow=nkrow+"<td style='text-align:center;' width='10%'>" + $("#ro_unit").val() + "<input type='hidden' name='re_ro_unit_t' value='"+$("#ro_unit").val()+"' class='form-control-sm nrform-control' readonly='readonly'><input type='hidden' name='re_ro_unit_code_t' value='"+$("#to_sus_no").val()+"'></td>";
    nkrow=nkrow+"<td style='text-align:center;' width='10%'>" + $("#prf_group").val() + "<input type='hidden' name='re_ro_prf_t' value='"+$("#prf_group").val()+"'  class='form-control-sm nrform-control' readonly='readonly'><input type='hidden' name='re_ro_prf_code_t' value='"+$("#ro_prf").val()+"'></td>";
    nkrow=nkrow+"<td style='text-align:center;' width='10%'>" + ro_type_name + "<input type='hidden' name='re_ro_type_hld_t' value='"+ro_type_name+"' class='form-control-sm nrform-control' readonly='readonly'><input type='hidden' name='ro_type_hld_code_t' value='"+$("#ro_for").val()+"'></td>";
    nkrow=nkrow+"<td style='text-align:center;' width='10%'>" + $("#type_of_stk option:selected").attr('name') +"</td>";
    nkrow=nkrow+"<td style='text-align:center;' width='10%'>" + $("#type_of_issue option:selected").attr('name') +"</td>";
    nkrow=nkrow+"<td style='text-align:center;' width='5%'>" + $("#ro_ue").val() + "<input style='text-align:center' type='hidden' name='re_ro_ue_t' value='"+$("#ro_ue").val()+"' class='form-control-sm nrform-control' readonly='readonly'></td>";
    nkrow=nkrow+"<td style='text-align:center;' width='5%'>" + $("#ro_uh").val() + "<input style='text-align:center' type='hidden' name='re_ro_uh_t' value='"+$("#ro_uh").val()+"' class='form-control-sm nrform-control' readonly='readonly'></td>";
    nkrow=nkrow+"<td style='text-align:center;' width='5%'>" + $("#ro_qty").val() + "<input style='text-align:center' type='hidden' name='re_ro_qty_t' value='"+$("#ro_qty").val()+"' class='form-control-sm nrform-control' readonly='readonly'></td>";
    nkrow=nkrow+"<td style='text-align:center;' width='15%'>" + $("#rel_depot_sus_code").val() + "<input type='hidden' name='re_ro_depot_sus_t' value='"+$("#rel_depot_sus").val()+"'  class='form-control-sm nrform-control' readonly='readonly'><input type='hidden' name='re_ro_depot_sus_code_t' value='"+$("#rel_depot_sus_code").val()+"'></td>";
    nkrow=nkrow+"<td style='text-align:center;' width='10%'>" + $("#inst_rem").val() + "<input style='text-align:center' type='hidden' name='re_ro_inst_rem' value='"+$("#inst_rem").val()+"' class='form-control-sm nrform-control' readonly='readonly'></td>";
    nkrow=nkrow+"<td style='text-align:center;' width='10%'>" + $("#remarks").val() + "<input type='hidden' name='re_ro_remarks_t'  value='"+$("#remarks").val()+"' class='form-control-sm nrform-control' readonly='readonly'><input type='hidden' name='census_no_hid_t' value='"+$("#census_no").val()+"'><input type='hidden' name='pbd_head_t' value='"+$("#pbd_head").val()+"'><input type='hidden' name='ro_command_sus_t' value='"+$("#ro_command_sus").val()+"'></td>";						
   
    $("#nrTable").append(nkrow); 
    $("#count").val(len);
    
    $("#unit_l").hide();
    
    $("#ro_unit").val('');
  
	$("#ro_ue").val('');
    $("#ro_uh").val('');
    $("#ro_qty").val('');
 
    $("#remarks").val('');
    $("#to_sus_no").val('');
    $("#ro_command").val('');
 
	
}

function check(){
	if($("#section").val() == "-1"){
		alert("Please Select the Section");
		$("select#section").focus();
		return false;
	}
	
	if($("#ro_no").val() == ""){
		alert("RO No Should not be Null");
		$("select#ro_no").focus();
		return false;
	}
	
	if($("#ro_date").val() == ""){
		alert("RO Dated Should not be Null");
		$("#ro_date").focus();
		return false;
	}
	
	if($("#ro_agency").val() == "-1"){
		alert("Please Select the RO Agency");
		$("#ro_agency").focus();
		return false;
	}
	
	if($("#ro_type").val() == "-1"){
		alert("Please Select the RO Type");
		$("#ro_type").focus();
		return false;
	}
	
	if($("#ro_for").val() == "-1"){
		alert("Please Select the RO Against");
		$("#ro_for").focus();
		return false;
	}
	
	if($("#type_of_stk").val() == "-1"){
		alert("Please Select the Type of Stock");
		$("#type_of_stk").focus();
		return false;
	}
	
	if($("#type_of_issue").val() == "-1"){
		alert("Please Select the Type of Issue");
		$("#type_of_issue").focus();
		return false;
	}
	
	if($("#type_of_issue").val() == "5"){
		if($("#pbd_head").val() == "-1"){
			alert("Please Select the Budget Head");
			$("#pbd_head").focus();
			return false;
		}
	}
	
	if($("#ro_valid").val() == ""){
		alert("RO Valid Upto Should not be Null");
		$("#ro_valid").focus();
		return false;
	}
	
	if($("#ro_unit").val() == "-1"){
		alert("Please Select From Unit");
		$("#ro_unit").focus();
	    return false;
	}
	
	if($("#ro_prf").val() == "-1"){
		alert("Please Select the Eqpt PRF Group");
		$("#ro_prf").focus();
		return false;
	}
	
	if($("#ro_ue").val() == ""){
		alert("UE Should not be Null");
		$("#ro_ue").focus();
		return false;
	}
	
	if($("#ro_uh").val() == ""){
		alert("UH Should not be Null");
		$("#ro_uh").focus();
		return false;
	}
	
	if($("#ro_qty").val() == ""){
		alert("Please Enter Qty to be Issued");
		$("#ro_qty").focus();
		return false;
	}
	
	if($("#rel_depot_sus").val() == "-1" ){
		 alert("Please Select RO Depot Name");
		 $("#rel_depot_sus").focus();
		 return false;
	} 
	
	var q1 = $("#ro_type").val();
	var q2 = $("#ro_for").val();
	if(q1 == 1 && q2 == 1){
		var ue=$("#ro_ue").val();
		var uh=$("#ro_uh").val();
		var uhq=$("#ro_qty").val();
		
		if(parseInt(ue) < (parseInt(uh) + parseInt(uhq))){
		   alert("You are Issuing More than Authorization");
		   $("#ro_qty").focus();
		   return false;
		}
	}
	 
	$("#sbt_btn").show();
	$("#unit_hid2").show();  
	 
	drawTable1(); 
	 
	$("#section").attr('disabled',true);
	$("#ref_no").attr('readonly',true);
	$("#ro_agency").attr('disabled',true);
	$("#ro_type").attr('disabled',true);
	$("#ro_for").attr('disabled',true);
}
</script>

<script>
$(document).ready(function() {
	$().getCurDt2(ro_date);
	$().getExDt(ro_valid);
		
	$('#section').change(function() {
		
		
                 $.post("getRo_Nocode?"+key+"="+value,  function(j) {
                     
             }).done(function(j) {
                    
             
                     var a = [];
         			var enc = j[j.length-1].substring(0,16);
          			for(var i = 0; i < j.length; i++){
         				a[i] = dec(enc,j[i]);
                     }
          		
         			var jn = "RO/"+ $("#section").val()+a[0];		 
         			$("#ro_no").val(jn);
                     
             }).fail(function(xhr, textStatus, errorThrown) {
             });
	});
	
	$('#ro_type').change(function() {
		 var ro_type = $("#ro_type").val();
		 $("#from_sus_Search").val('');
		 $("#from_sus_Search").attr("disabled",false);
		 $("#ro_unit").attr("disabled",true);
		 
		 $("#ro_for").attr("disabled",false);
		 $("#ro_for").val('-1').css('color','black');;
		 var rf = $("#ro_type").val();
		 
		 if(rf == 2){
			 $("#ro_for").children("option[value='2']").hide();
		 }else{
			 $("#ro_for").children("option[value='2']").show();
		 } 
		 
		 $("#ro_for option[value=2]").css('color','red');
		 $("#ro_for option[value=-1]").css('color','black');
		 $("#ro_for option[value=-1]").css('color','black'); 
		 
		 if(ro_type == 2){
			 $("#ro_unit_label").text('Command'); 
		 }else{
			 $("#ro_unit_label").text('Unit');
		 }
	 });	 
		 
	 $('#ro_for').change(function() {	 
		 if($("#ro_for").val() == "2"){
			 $("#ro_for").css('color','red');
			 $("#ro_for option[value=1]").css('color','black');
			 $("#ro_for option[value=-1]").css('color','black');
		 }else{
			 $("#ro_for").css('color','black');
		 }
		 
	 });
		
	$('#type_of_issue').change(function() {
		if(this.value == "5"){
			$("#btn_head").show();
		}else{
			$("#btn_head").hide();
		}
	});
	
	$('#ro_unit').change(function(){
		var y = this.value;
		var sus = $("#ro_unit").val();
		
		$("#to_sus_no").val(sus);
		
		var FindWhat = "COMMAND";
		
	
                $.post("getMMSHirarNameBySUS?"+key+"="+value, {
                	FindWhat:FindWhat,a:y
            }, function(j) {
                  
            }).done(function(j) {
                   
            
                    var a = [];
        			var enc = j[j.length-1].substring(0,16);
        			for(var i = 0; i < j.length; i++){
        				a[i] = dec(enc,j[i]);
        			}
        			var data=a[0].split(",");
        			var datap;
        			
        			for(var i = 0; i < data.length-1; i++) {
        				datap=data[i].split(":");
        				$("#ro_command").val(datap[1]);  
        				$("#ro_command_sus").val(datap[5]); 
        			}	 
                    
            }).fail(function(xhr, textStatus, errorThrown) {
            });
		 
		$("#from_prf_Search").attr('disabled',false);
		
		if($("#to_sus_no").val() != "" && $("#ro_prf").val() != ""){
			$("#ro_ue").val('');
			$("#ro_uh").val('');
			$("#u_h").show();
		}
    }); 
	
	$('#ro_prf').change(function() {
		 var nParaValue =  $("#ro_prf").val();
	     $("#census_no").attr('disabled',false);
		 $("#prf_group").val($(this).find('option:selected').attr("name"));
		 var nPara = "PRFCODE";
		 
                 $.post("getmmsDistinctMlccs?"+key+"="+value, {
                	 nParaValue:nParaValue,nPara:nPara
             }, function(j) {
                    
             }).done(function(j) {
            	 if(j.length <= 0 || j == null || j == ''){
    				 alert("No Search Result Found");
    				 $("#census_no").val('');
    				 $("#census_no").attr('disabled',true);
    				 $("#u_h").hide();
    				 $("#ro_ue").val('');
    				 $("#ro_uh").val('');
    		 		 $("#ro_prf").focus();
    		 	 }else{
    		 		 var options = '';
    		 		 var a = [];
    	 			 var enc = j[j.length-1].substring(0,16);
    	 			 for(var i = 0; i < j.length; i++){
    	 				 a[i] = dec(enc,j[i]);
                    }
    	 			
    				 var data=a[0].split(",");
    				
    				 for(var i = 0; i < data.length-1; i++){
    					 datap=data[i].split(":");
    					 if(datap!=null){
    						 if(datap[1].length>90){
    							 options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+datap[0]+ ' - '+ datap[1].substring(1,90)+ '</option>';
    						 }else{
    							 options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+datap[0]+ ' - '+ datap[1]+ '</option>';
    						 }
    					 }
    				 } 
    				 $("select#census_no").html(options); 
    				 
    				 if($("#to_sus_no").val() != "" && $("#ro_prf").val() != ""){
    					 $("#ro_ue").val('');
    					 $("#ro_uh").val('');
    					 $("#u_h").show();
    				 }
    		 	 }
                     
             }).fail(function(xhr, textStatus, errorThrown) {
             });
	});
	
	$('#rel_depot_sus').change(function(){
		$("#rel_depot_sus_code").val('');
		var rel_depot_sus_code = $(this).find('option:selected').attr("name");
		$("#rel_depot_sus_code").val(rel_depot_sus_code);
    });
	
	$("#nrInput").on("keyup", function() {
  		var value = $(this).val().toLowerCase();
  		$("#nrTable tr").filter(function() {
  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
  	    });
  	});
	 
	try{
	 if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		} 	
	}
	catch (e) {
		// TODO: handle exception
	}	  	  
});
</script>