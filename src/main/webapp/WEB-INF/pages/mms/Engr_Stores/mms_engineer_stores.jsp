<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css">

<body class="mmsbg">
<form:form name="engr_store" id="engr_store" action="engr_storeAction?${_csrf.parameterName}=${_csrf.token}"  commandName="engr_storeCMD" method="post" enctype="multipart/form-data"> 
	<input type="hidden" id="nrflowcontrol" name="nrflowcontrol" class="form-control">
	
	<div class="">
	      <div class="container" align="center">
	           <div class="card">
	        	    <div class="card-header mms-card-header">
		                 <b>ENGINEER STORES</b>
		                 <br><span class="mms-card-subheader">(To be entered by Unit)</span>
		            </div> 
		            
		    			<div class="card-body card-block">
		    			    <div class="col-md-12 row form-group" style="margin-top: -10px;">	            					
	              				<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>Sanctioning Auth</label>
                				</div>
                				<div class="col-md-3">
                					<input type="hidden" id="store_type" name="store_type" value="ENGAUTHORITY">							
									
									<select name="eng_sanc_auth" id="eng_sanc_auth" class="form-control-sm form-control" required>	
											
											<c:forEach var="item" items="${ml_1}">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
											</c:forEach>                  							
                                    </select> 
								</div>
	  						</div>	
		    			
		    				<div class="col-md-12 row form-group" style="margin-top: -20px;">		 
              					<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>Unit Name</label>
                				</div>
                				<div class="col-md-5">
                  					<input type="text" id="iv_by_unit_name" name="iv_by_unit_name" placeholder="Search Issuing Auth Unit Name..." class="form-control-sm form-control autocomplete" maxlength="100" autocomplete="off" required>
								</div>
	  							
  								<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
                				</div>
                				<div class="col-md-3">
                					<input type="text" id="iv_by_sus_no" name="iv_by_sus_no" placeholder="Search Issuing Auth SUS No..." class="form-control-sm form-control autocomplete" maxlength="8" autocomplete="off" required>
			                  		<input type="hidden" id="from_form_code" name="from_form_code" placeholder="" >
								</div>			
		  					</div>
		    			
		    				<div class="col-md-12 row form-group" style="margin-top: -10px;">		 
              					<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>Auth Letter No</label>
                				</div>
                				<div class="col-md-3">
                  					<input type="text" id="auth_letter_no" name="auth_letter_no" placeholder="Enter Auth Letter No..." class="form-control-sm form-control" maxlength="50" autocomplete="off" required>
								</div>
								
								<div class="col-md-2">
								</div>
	  							
  								<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>Date </label>
                				</div>
                				<div class="col-md-3">
                					<input type="date" id="auth_letter_date" name="auth_letter_date" placeholder="" class="form-control-sm form-control" autocomplete="off" required>
								</div>			
		  					</div>
		  				
		    				<div class="col-md-12 row form-group" style="margin-top: -20px;">		 
              					<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>Eng. Expiry Date </label>
                				</div>
                				<div class="col-md-3">
                  					<input type="date" id="eng_expiry_date" name="eng_expiry_date"  placeholder="" class="form-control-sm form-control" autocomplete="off" required>
								</div>
								
								<div class="col-md-2">
								</div>
	  							
  								<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label" style="font-size: 15px;"><strong style="color: red;">* </strong>Upload Auth Letter</label>
                				</div>
                				<div class="col-md-3">
                					<input type="file" id="upload_auth_letter1" name=upload_auth_letter1  onchange="checkFileExtImage(this)" autocomplete="off" class="form-control-sm form-control" required>
								</div>			
		  					</div>
		    			</div>
		    		<div class="card-header" style="border: 1px solid rgba(0,0,0,.125);margin-top: -15px;">
		               
		            </div>
		    			<div class="card-body card-block">	
		    				<div class="col-md-12 row form-group">		 
              					<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>Unit Name </label>
                				</div>
                				<div class="col-md-5">
                  					<input type="text" id="unit_name1" name="unit_name1" placeholder="Search..." class="form-control-sm form-control autocomplete" maxlength="100" autocomplete="off" required>
								</div>
	  							
  								<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
                				</div>
                				<div class="col-md-3">
                					<input type="text" id="sus_no1" name="sus_no1" placeholder="Search..." class="form-control-sm form-control autocomplete" maxlength="8" autocomplete="off" required>
			                  		<input type="hidden" id="to_form_code" name="to_form_code" placeholder="" >
								</div>			
		  					</div>
		    				
		    				<div class="col-md-12 row form-group" style="margin-top: -10px;">		 
              					<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>IV No </label>			                			
                				</div>
                				<div class="col-md-3">
                  					<input type="text" id="iv_no" name="iv_no" placeholder="Enter IV No..." class="form-control-sm form-control" autocomplete="off" maxlength="50" required>
								</div>
								
								<div class="col-md-2">
								</div>
	  							
  								<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>IV Date </label>
                				</div>
                				<div class="col-md-3">
                					<input type="date" id="iv_date" name="iv_date" placeholder="" class="form-control-sm form-control" autocomplete="off" required>
								</div>			
		  					</div>
		  					
		  					<div class="col-md-12 row form-group" style="margin-top: -10px;">
		                			<div class="col-md-2" style="text-align: left;">
		                  				 <label class=" form-control-label"><strong style="color: red;" >* </strong>PRF</label>
		                			</div>
		                			
		                			<div class="col-md-2">
		                  				 <input type="text" id="from_prf_Search" name="from_prf_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10" required/>
		                			</div>
		                			
		                			<div class="col-md-1">
		                			      <img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromprf();" title="Click to Search" style="cursor:pointer;">
		                			</div>
		                			
		                			<div class="col-md-7">
		                			      <select id="prf_code" name="prf_code" class="form-control-sm form-control" disabled="disabled">
		                                     <option selected value="-1">--Select PRF Group--</option>     
		                                  </select>
		                                  
		                			</div>
					         </div>
		  				
		    				
		  					
		  					<div class="col-md-12 row form-group" style="margin-top: -10px;">
		                			<div class="col-md-2" style="text-align: left;">
		                  				 <label class=" form-control-label" style="margin-left: 13px;">Census No</label>
		                			</div>
		                			<div class="col-md-10">
		                  				 <select name="census_no" id="census_no" class="form-control-sm form-control" disabled="disabled">
			                                    <option selected value="-1">--Select Item Nomenclature--</option>     
			                             </select>
									</div>
					         </div>
		    				
		    				<div class="col-md-12 row form-group" style="margin-top: -10px;">		 
              					<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>Qty </label>		                			
                				</div>
                				<div class="col-md-3">
                  					<input type="text" id="qty" maxlength="4" name="qty" class="form-control-sm form-control" placeholder="Max Four Character" onkeyup="formopen($(this).val())" autocomplete="off" required>
								</div>
								
								<div class="col-md-2">
								</div>
	  							
  								<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label"><strong style="color: red;">* </strong>Upload Voucher </label>
                				</div>
                				<div class="col-md-3">
                					<input type="file" id="upload_voucher1" name="upload_voucher1"  onchange="checkFileExtImage(this)" autocomplete="off" class="form-control-sm form-control" required>
								</div>			
		  					</div>
		  					
		    				<div class="col-md-12 row form-group" style="margin-top: -10px;">		 
              					<div class="col-md-2" style="text-align: left;">
                  					<label class=" form-control-label" style="margin-left: 13px;"> Remarks</label>         			
                				</div>
                				<div class="col-md-10">
                  				
                  					<textarea id="rem" name="rem" class="form-control-sm form-control" placeholder="Enter Remarks..."></textarea>
								</div>		
		  					</div>   				
		    			</div>
	    				
	    				<div class="col-md-12 row form-group" style="width: 100%; overflow: auto;margin-top: -10px;">
		            		<div class="col s12" style="margin-right: 30px;">
			              		<table class="table no-margin table-striped  table-hover  table-bordered" id="addQuantity">
				                	<thead>
				                  		<tr>
				                  		<th style="text-align: center;">Sr. No</th>
				                   		<th style="text-align: center;">Equipment Regd No</th>				                
				                  		</tr>
				                  		<tr style="background-color: white;color: black;">	
				                  		<td style="text-align: center;">1</td>
		        						<td style="text-align: center;"><input id="eqpt_regd1" name="eqpt_regd1" maxlength="20" class="form-control" autocomplete="off"></td>
				                    	</tr>
				                  	</thead>
				                 	<tbody>
				               		</tbody>
				              </table>
			            	</div>
			        	</div>
			        	
			        <input type="hidden" id="count" name="count" >
					<div class="card-footer" align="center">
						<input type="button" class="btn btn-primary btn-sm" value="Clear" onclick="btn_clc();" /> 
		              	<input type="submit" class="btn btn-success btn-sm" value="Submit" onclick="return validate();">
		              	<a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
			        </div>
	    				
	    		</div>
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

function getfromprf(){
	var nParaValue = $("#from_prf_Search").val();
	if(nParaValue.length<=0) {
		alert("Enter Search Word...");
	    return false;
	}else{
	  $.post("getMMSPRFtListBySearch?"+key+"="+value, {
                	nParaValue:nParaValue
            }, function(j) {
                  

            }).done(function(j) {
                    
            	if(j.length <= 0 || j == null || j == ''){
    				alert("No Search Result Found");
    	 			$("#from_prf_Search").focus();
    	 		}else{
    	 			$("#prf_code").attr('disabled',false);
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
    				$("select#prf_code").html(options); 
    	 		}
            }).fail(function(xhr, textStatus, errorThrown) {
            });
	}
}

function formopen(e){
	var max_fields  = e; //maximum input boxes allowed

	if(max_fields < 51){
		
		document.getElementById("count").value = max_fields;
		$("table#addQuantity tbody").empty();
		for(var x = 2; x <= max_fields; x++){ //max input box allowed
			$("table#addQuantity").append('<tr id="'+x+'"><td style="text-align: center;">'+x+'</td>'
        			+'<td style="text-align: center;"><input type="text" name="eqpt_regd'+x+'" id="eqpt_regd'+x+'" class="form-control" autocomplete="off"/></td>'
        			+'</tr>');
			}	
	}else{
		alert("please enter max 50 Quantity");
		$("#qty").val("1");
		formopen(1);
	}
}
function validate(){
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	var q1 = $("#qty").val();
	var ar = [];
	
	if($("#eng_sanc_auth").val() == "-1"){
		alert("Please Enter the Loan Sanction Auth.");
		$("#eng_sanc_auth").focus();
		return false;
	}
	else if($("#iv_by_unit_name").val() == ""){
		alert("Please Enter the Issuing Auth Unit Name.");
		$("#iv_by_unit_name").focus();
		return false;
	}
	else if($("#iv_by_sus_no").val() == ""){
		alert("Please Enter the Issuing Auth SUS No.");
		$("#iv_by_sus_no").focus();
		return false;
	}
	else if($("#auth_letter_no").val() == ""){
		alert("Please Enter the Auth Letter No.");
		$("#auth_letter_no").focus();
		return false;
	}
	else if($("#auth_letter_date").val() == ""){
		alert("Please Enter the Auth Letter Date.");
		$("#auth_letter_date").focus();
		return false;
	}
	else if($("#auth_letter_date").val() > c_d){
		$("#auth_letter_date").focus();
		alert("Can't select the Future Date");
		return false;
	}
	else if($("#eng_expiry_date").val() == ""){
		alert("Please Enter the Loan Expiry Date.");
		$("#eng_expiry_date").focus();
		return false;
	}
	else if($("#upload_auth_letter1").val() == ""){
		alert("Please Upload the Auth Letter.");
		$("#upload_auth_letter1").focus();
		return false;
	} 
	else if($("#sus_no1").val() == ""){
		alert("Please Select the SUS No");
		$("#sus_no1").focus();
		return false;
	}
	else if($("#unit_name1").val() == ""){
		alert("Please Select the UNIT NAME");
		$("#unit_name1").focus();
		return false;
	}
	else if($("#iv_no").val() == ""){
		alert("Please Enter the IV No.");
		$("#iv_no").focus();
		return false;
	}
	else if($("#iv_date").val() == ""){
		alert("Please Enter the IV Date.");
		$("#iv_date").focus();
		return false;
	}
	else if($("#iv_date").val() > c_d){
		$("#iv_date").focus();
		alert("Can't select the Future Date");
		return false;
	}
	else if($("#prf_code").val() == "-1"){
		alert("Please Select the Equipment PRF Group.");
		$("#prf_code").focus();
		return false;
	}
	else if($("#census_no").val() == "-1"){
		alert("Please Select the Census No");
		$("#census_no").focus();
		return false;
	}
	else if($("#qty").val() == ""){
		alert("Please Enter the Quantity.");
		$("#qty").focus();
		return false;
	}
	else if($("#upload_voucher1").val() == ""){
		alert("Please Upload the Voucher.");
		$("#upload_voucher1").focus();
		return false;
	}else{
		for(var i=0;i<q1;i++){
			var d = "#eqpt_regd"+(i+1);
			ar.push($(d).val());
			if($(d).val() == ""){
				alert("Please Enter the Eqpt Regd No.");
				$(d).focus();
				return false;	
			}	
		}
		
		var b = ar.join(":");
	

		
                $.post("CheckmmsRegNoList?"+key+"="+value, {
                	enc:"1",b:b
            }, function(j) {
                   

            }).done(function(j) {
                   
            	if(j.length <= 0 || j == null || j == ''){
    				$("#engr_store").submit();
    			}else{
    				var a = [];
    				var enc = j[j.length-1].substring(0,16);
    				for(var i = 0; i < j.length; i++){
    					a[i] = dec(enc,j[i]);
    		        }
    				alert("Listed Reg NO Already Exists: "+a);
    				return false;
    			}
    			return false;
            }).fail(function(xhr, textStatus, errorThrown) {
        });
	} 
}


 $("#iv_by_sus_no").keyup(function(){
	var sus_no = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',iv_by_sus_no,{a:sus_no,b:para,c:"SUS",d:paravalue},'getMMSUnitNameBySUSNo',iv_by_unit_name);
});


$("#iv_by_unit_name").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',iv_by_unit_name,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',iv_by_sus_no);
});


$("#sus_no1").keyup(function(){
	var sus_no = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',sus_no1,{a:sus_no,b:para,c:"SUS",d:paravalue},'getMMSUnitNameBySUSNo',unit_name1);
});
 

$("#unit_name1").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',unit_name1,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',sus_no1);
}); 

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
<script>
$(document).ready(function() {
	$('#prf_code').change(function() {
		var census = $("#prf_code").val();
		var nPara = "PRFCODE";
		
		
		
		
                $.post("getmmsDistinctMlccs?"+key+"="+value, {
                	nParaValue:census,nPara:nPara
            }, function(j) {
                 
            }).done(function(j) {

            	if(j.length <= 0 || j == null || j == ''){
    				alert("No Search Result Found");
    	 			$("#from_prf_Search").focus();
    	 		}else{
    	 			$("#census_no").attr('disabled',false);
    	 			var options = '<option  value="' + "-1" + '">'+ "--Select Item Nomenclature--" + '</option>';
    	 			
    	 			var a = [];
    	 			var enc = j[j.length-1].substring(0,16);
    	 			for(var i = 0; i < j.length; i++){
    					a[i] = dec(enc,j[i]);
                    }
    	 			
    				var data=a[0].split(",");
    				
    				for ( var i = 0; i < data.length-1; i++) {
    					datap=data[i].split(":");
    					if (datap!=null) {
    						if (datap[1].length>90) {
    							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1].substring(1,90)+ '</option>';
    						} else {
    							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[1]+ '</option>';
    						}
    					}
    				}
    				$("select#census_no").html(options); 
    	 		}
            }).fail(function(xhr, textStatus, errorThrown) {
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
</script>