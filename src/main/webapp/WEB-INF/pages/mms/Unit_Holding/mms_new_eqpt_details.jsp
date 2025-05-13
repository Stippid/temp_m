<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<style>
/* card-footer style start */
.card-footer.d-flex{
display: -webkit-box; 
display: -moz-box;
display: -ms-flexbox;
display: -webkit-flex; 
display: flex;
flex-wrap: wrap;
-moz-justify-content: space-between;  
-webkit-justify-content: space-between;
justify-content: space-between;
}
.card-footer .btn{
	margin:5px 2px;
}
/* card-footer style end */
</style>
<body class="mmsbg">

<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<form:form id="new_eqpt_details" name="new_eqpt_details" action="new_eqpt_detailsActions?${_csrf.parameterName}=${_csrf.token}" method='POST' 
commandName="new_eqpt_detailsCMD" enctype="multipart/form-data">
	 <div class="">
	   	<div class="container" align="center">
	   		<div class="card">
		        <div class="card-header mms-card-header">
		             <b>ADD DETAILS OF NEW EQPT</b>
		        </div> 
	   	
	    		<div class="card-body card-block">   
  					   <div class="col-md-12 row form-group">
	             	 		 <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label"><strong style="color: red;">* </strong>IV No</label>
		             		 </div>
		             		 <div class="col-md-3">
		             			  <input type="text" id="rv_no" name="rv_no" maxlength="25" placeholder="Enter IV No..." class="form-control-sm form-control autocomplete" autocomplete="off" required>
		               		 </div>
		               		 
		               		 <div class="col-md-2">
		               		 </div>
		             		 								
		               		 <div class="col-md-2" style="text-align: left;">
		                 		  <label class=" form-control-label"><strong style="color: red;">* </strong>IV Date</label>
		               		 </div>
		               		 <div class="col-md-3">
		               			  <input type="date" id="rv_date" name="rv_date" placeholder="" class="form-control-sm form-control" autocomplete="off" required>
							 </div>	    
  					   </div>
  					   
  					   <div class="col-md-12 row form-group" style="margin-top: -10px;">
	             	 		 <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Issuing Depot</label>
		             		 </div>
		             		 <div class="col-md-10">
		             			  <select name="from_sus_no" id="from_sus_no" class="form-control-sm form-control" >	
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_4}">
											<option value="${item[1]}" name="${item[0]}">${item[1]} - ${item[2]}</option>	
										</c:forEach>                  							
                                  </select>
		             			  
								  <input type="hidden" id="from_code" name="from_code" placeholder="" class="form-control" required>
		               		 </div>    
  					   </div>
  					   
  					   <div class="col-md-12 row form-group" style="margin-top: -10px;">
  					          <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label"><strong style="color: red;">* </strong>To Unit Name</label>
		             		 </div>   
		             		 <div class="col-md-5">
								 <input type="text" id="to_sus_name" name="to_sus_name" maxlength="100" class="form-control-sm form-control" autocomplete="off" placeholder="Search..." required/>
		               		 </div> 
		               		 
	             	 		 <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label"><strong style="color: red;">* </strong>To Unit SUS</label>
		             		 </div>
		             		 <div class="col-md-3">
								 <input type="text" id="to_sus_no" name="to_sus_no" maxlength="8" class="form-control-sm form-control" autocomplete="off" placeholder="Search..." required/>
								 <input type="hidden" id="to_code" name="to_code" placeholder="" class="form-control"> 
		               		 </div> 
		               		
  					   </div>
  					   
  					   <div class="col-md-12 row form-group" style="margin-top: -10px;">
	             	 		 <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of Holding</label>
		             		 </div>
		             		 <div class="col-md-3">
                                  <select name="type_of_hldg" id="type_of_hldg" class="form-control-sm form-control" required>	
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_3}">
											<option value="${item.codevalue}" name="${item.label}">${item.label}</option>	
										</c:forEach>                  							
                                  </select>
                                  <input type="hidden" id="hldg_name" name="hldg_name" placeholder="" class="form-control">
		               		 </div>
		               		 
		               		 <div class="col-md-2">
		               		 </div>
		             		 								
		               		 <div class="col-md-2" style="text-align: left;">
		                 		  <label class=" form-control-label"><strong style="color: red;">* </strong>Type of Eqpt</label>
		               		 </div>
		               		 <div class="col-md-3">
                                  <select name="type_of_eqpt" id="type_of_eqpt" class="form-control-sm form-control" required >	
										<option value="-1">--Select--</option>
										<c:forEach var="item" items="${ml_1}">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
										</c:forEach>                  							
                                 </select>
							 </div>	    
  					   </div>
		               
		               <div class="col-md-12 row form-group" style="margin-top: -10px;">
	                			<div class="col-md-2" style="text-align: left;">
	                  				 <label class=" form-control-label"><strong style="color: red;" >* </strong>PRF Group</label>
	                			</div>
	                			
	                			<div class="col-md-2">
	                  				 <input type="text" id="from_prf_Search" name="from_prf_Search" placeholder="Search..." class="form-control-sm form-control" autocomplete="off" size="10" maxlength="10" required/>
	                			</div>
	                			
	                			<div class="col-md-1">
	                			      <img src="js/miso/images/searchImg.jpg" width="30px;" height="30px;" onclick="getfromprf();" title="Click to Search" style="cursor:pointer;">
	                			</div>
	                			
	                			<div class="col-md-7">
	                			      <select id="prf_code" name="prf_code" class="form-control-sm form-control" disabled="disabled">
	                                     <option selected value="-1">----Select PRF Group----</option>     
	                                  </select>
	                			</div>
					   </div>
  					   
  					   <div class="col-md-12 row form-group" style="margin-top: -10px;">
	             	 		 <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Census No</label>
		             		 </div>
		             		 <div class="col-md-10">
		             			 <select name="census_no" id="census_no" class="form-control-sm form-control" disabled="disabled" required>
                                        <option selected value="-1">--Select Item Nomenclature--</option>
                                 </select>
                                 <input type="hidden" id="nomen" name="nomen" placeholder="" class="form-control">
		               		 </div>    
  					   </div>
  					   
  					   <div class="col-md-12 row form-group" style="margin-top: -10px;">
	             	 		 <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label"><strong style="color: red;">* </strong>Issued Qty</label>
		             		 </div>
		             		 <div class="col-md-2">
		             			  <input type="text" id="issued_qty" name="issued_qty" maxlength="7" placeholder="Enter Qty..." class="form-control-sm form-control" autocomplete="off" required>
		               		 </div>
		             		 								
		               		 <div class="col-md-2" style="text-align: right;">
		                 		  <label class=" form-control-label" style="margin-left: 13px;">Eqpt Make</label>
		               		 </div>
		               		 <div class="col-md-2">
		               			  <input type="text" id="eqpt_make" name="eqpt_make" maxlength="15" placeholder="Enter Make..." class="form-control-sm form-control" autocomplete="off" required>
							 </div>	 
							 
							  <div class="col-md-2" style="text-align: right;">
		                 		  <label class=" form-control-label">Eqpt Model</label>
		               		 </div>
		               		 <div class="col-md-2">
		               			  <input type="text" id="eqpt_model" name="eqpt_model" maxlength="15" placeholder="Enter Model..." class="form-control-sm form-control" autocomplete="off" required>
							 </div>	   
  					   </div>
  					   
  					   <div class="col-md-12 row form-group" style="margin-top: -10px;">
	             	 		 <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label" style="margin-left: 13px;"><strong style="color: red;"></strong>Unit Price(Rs.)</label>
		             		 </div>
		             		 <div class="col-md-3">
		             			  <input type="text" id="unit_price" name="unit_price" placeholder="Enter Unit Price..." class="form-control-sm form-control" autocomplete="off">
		               		 </div>
		               		 				
		               		 <div class="col-md-4" style="text-align: right;">
		                 		  <label class=" form-control-label" style="margin-left: 13px;">Rate of Depreciation (%)</label>
		               		 </div>
		               		 <div class="col-md-2">
		               			  <input type="text" id="depres_rate" name="depres_rate" placeholder="In %..." class="form-control-sm form-control" autocomplete="off">
							 </div>	    
  					   </div>
  					   
  					   <div class="col-md-12 row form-group" style="margin-top: -10px;">
	             	 		 <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label" style="margin-left: 13px;"><strong style="color: red;"></strong>Life of Asset (Yr)</label>
		             		 </div>
		             		 <div class="col-md-3">
		             			 <input type="text" id="life_of_assets" name="life_of_assets" maxlength="10" placeholder="Enter Life of Assets..." class="form-control-sm form-control" autocomplete="off">
		               		 </div>    
  					   </div>
  					   
  					   <div class="col-md-12 row form-group" style="margin-top: -10px;">
	             	 		 <div class="col-md-2" style="text-align: left;"> 
		               			  <label class=" form-control-label" style="margin-left: 13px;"><strong style="color: red;"></strong>Upload IV</label>
		             		 </div>
		             		 <div class="col-md-3">
		             			 <input type="file" id="doc_upload1" name="doc_upload1" class="form-control-sm form-control" onchange="checkFileExtImage(this)" autocomplete="off">
		               		 </div>    
  					   </div>
	    		</div>
	    		
	    		<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: left;margin-top: -10px;" id="aa"> <strong>Enter Registration No Details</strong></div>
	    		<div class="card-body card-block" id="ab">
			    		<div class="col-md-12 row form-group">
		           	 		 <div class="col-md-3" style="text-align: left;"> 
		              			  <label class=" form-control-label"><strong style="color: red;">* </strong>New Regn No:</label>
		            		 </div>
		            		 <div class="col-md-4">
		            			  <input type="text" name="eqpt_regn_no" id="eqpt_regn_no" maxlength="25" placeholder="Enter New Regn No..." 
		            			  class="form-control-sm form-control" required onkeypress="return AvoidSpace(event)" onkeyup="return specialcharectereqpt_regn_no(this)">
		              		 </div>    
		  				</div>
		  				
		  				<div class="col-md-12 row form-group" style="margin-top: -10px;">
		           	 		 <div class="col-md-3" style="text-align: left;"> 
		              			  <label class=" form-control-label"><strong style="color: red;">* </strong>Serviceability Status</label>
		            		 </div>
		            		 <div class="col-md-4">
			                      <select name="service_status" id="service_status" class="form-control-sm form-control" required >	
										<option value="">--Select--</option>
										<c:forEach var="item" items="${ml_2}">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>	
										</c:forEach>                  							
                                 </select>  
		              		 </div>    
		  				</div>
		  				
		  				<div class="col-md-12 row form-group" style="margin-top: -10px;">
		           	 		 <div class="col-md-3" style="text-align: left;"> 
		              			  <label class=" form-control-label" style="margin-left: 13px;">Barrel-1 Details :</label>
		            		 </div>
		            		 <div class="col-md-9">
		            			  <input type="text" name="barrel1_detl" id="barrel1_detl" maxlength="150" placeholder="Enter Barrel-1 Details..." class="form-control-sm form-control" maxlength="150" required>
		              		 </div>    
		  				</div>
		  				
		  				<div class="col-md-12 row form-group" style="margin-top: -10px;">
		           	 		 <div class="col-md-3" style="text-align: left;"> 
		              			  <label class=" form-control-label" style="margin-left: 13px;"><strong style="color: red;"></strong>Barrel-2 Details :</label>
		            		 </div>
		            		 <div class="col-md-9">
		            			  <input type="text" name="barrel2_detl" id="barrel2_detl" maxlength="150" placeholder="Enter Barrel-2 Details..." class="form-control-sm form-control" maxlength="150" required>
		              		 </div>    
		  				</div>
		  				
		  				<div class="col-md-12 row form-group" style="margin-top: -10px;">
		           	 		 <div class="col-md-3" style="text-align: left;"> 
		              			  <label class=" form-control-label" style="margin-left: 13px;"><strong style="color: red;"></strong>Barrel-3 Details :</label>
		            		 </div>
		            		 <div class="col-md-9">
		            			  <input type="text" name="barrel3_detl" id="barrel3_detl" maxlength="150" placeholder="Enter Barrel-3 Details..." class="form-control-sm form-control" maxlength="150" required>
		              		 </div>    
		  				</div>
		  				
		  				<div class="col-md-12 row form-group" style="margin-top: -10px;">
		           	 		 <div class="col-md-3" style="text-align: left;"> 
		              			  <label class=" form-control-label" style="margin-left: 13px;"> <strong style="color: red;"></strong>Barrel-4 Details :</label>
		            		 </div>
		            		 <div class="col-md-9">
		            			  <input type="text" name="barrel4_detl" id="barrel4_detl" maxlength="150" placeholder="Enter Barrel-4 Details..." class="form-control-sm form-control" maxlength="150" required>
		              		 </div>    
		  				</div>
		  				
		  				<div class="col-md-12 row form-group" style="margin-top: -10px;">
		           	 		 <div class="col-md-3" style="text-align: left;"> 
		              			  <label class=" form-control-label" style="margin-left: 13px;">Remarks :</label>
		            		 </div>
		            		 <div class="col-md-9">
		            			  <textarea class="form-control-sm form-control" placeholder="Enter Remarks..." name="remarks" id="remarks" maxlength="255" required></textarea>
		              		 </div>    
		  				</div>
				</div> 
	    		<!-- KAJAL -->
				<div class="card-footer d-flex"  >
					<div class="btn-left">
						<input type="hidden"  id="count" name="count">
						<input type="button"   class="btn btn-success btn-sm" value="Add Items in List" onclick="return validate();" id="add_more">             		
					</div>            			  
					<div class="btn-right">
						<input type="button"  class="btn btn-primary btn-sm" value="Clear" onclick="btn_clc();" />					
            			<a href="mmsDashboard"><input type="button"  class="btn btn-danger btn-sm" value="Cancel"></a>
					</div>            			
            	 </div>	
            	
	   		</div>
	   	</div>
	 </div>
	 
	  <div class="card" id="ac" style="display: none;">		      
	    <div class="card-body card-block">	
		   <div id="" class="nrTableMainDiv">
		   <div class="nrTableMain2Search" > 
							<label>Search within Result</label>&nbsp;:&nbsp;
							<input id="nrInput" type="text" placeholder="Search Word .." size="35" autocomplete="off">
						</div>
				
		    		       <table  border="1">
		                        		<thead >
	                          				<tr  style="font-size: 12px; text-align: center;" >
		            							<th  width="2%">Sr No</th>
							                    <th  width="8%">Census No</th>
							                    <th  width="15%">Nomenclature</th>
							                    <th  width="10%">Type of Holding</th>
							                    <th  width="10%">Regn No</th>
							                    <th  width="8%">Status</th>             
		                  			   </tr>
		                			</thead>
		    							<tbody id="nrTable">
		    													
		    							</tbody>
									</table>
							  
			
		    </div>  
         </div>	
         
         <div class="card-footer" align="center">
			<input type="hidden" id="count" name="count">
		    <input type="submit" class="btn btn-success btn-sm" value="Submit" style="display: none;" id="btn_submit"> 	
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

$("#to_sus_no").keyup(function(){
	var sus_no = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',to_sus_no,{a:sus_no,b:para,c:"SUS",d:paravalue},'getMMSUnitNameBySUSNo',to_sus_name);
});

$("#to_sus_name").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',to_sus_name,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',to_sus_no);
});

function btn_clc(){
	location.reload(true);
}

function getfromprf(){
	var nParaValue = $("#from_prf_Search").val();
	if(nParaValue.length<=0) {
		alert("Enter Search Word...");
		$("#from_prf_Search").focus();
	    return false;
	}else{

	
                $.post("getMMSPRFtListBySearch?"+key+"="+value, {
                	nParaValue:nParaValue
            }, function(data) {
                   

            }).done(function(data) {
                
                    if(data.length <= 0 || data == null || data == ''){
        				alert("No Search Result Found");
        	 			$("#from_prf_Search").focus();
        	 		}else{
        	 			$("#prf_code").attr('disabled',false);
        	 			var options = '<option value="' + "ALL" + '">'+ "--All PRF Groups --" + '</option>';
        	 			
        	 			var a = [];
        	 			var enc = data[data.length-1].substring(0,16);
        	 			for(var i = 0; i < data.length; i++){
        					a[i] = dec(enc,data[i]);
                        }
        	 			
        				var data=a[0].split(",");
        				var datap;
        				for ( var i = 0; i < data.length-1; i++) {
        					datap=data[i].split(":");
        					if (datap!=null) {
        						if (datap[1].length>60) {
        							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1].substring(1,60)+ '</option>';
        						} else {
        							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1]+ '</option>';
        						}
        					}
        				}	
        				$("select#prf_code").html(options); 
        				var q = '${m_6}';
        				if(q != ""){
        					$("#prf_code").val(q);
        				}
        	 		}
                    
            }).fail(function(xhr, textStatus, errorThrown) {
            });
	}
}



function checkRegNo(){
    var eqpt_regn_no=$("#eqpt_regn_no").val();
    if(eqpt_regn_no.length==0){
    	return;
    }
   
    $.ajax({
        type: 'POST',
        url: 'getSearchRegno?'+key+'='+value,
        data: {e:eqpt_regn_no},
       	success: function(response) {
       		if(response == "Success dtl"){ 
       			alert("Current Reg No Exists");
       			$("#eqpt_regn_no").val('');
       		}
        }
     });  
}

function validate(){
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+d.getDate();
	
 	if($("#rv_no").val() == ""){
		alert("Please Enter the IV Number");
		$("#rv_no").focus();
		return false;
	}
	
	if($("#rv_date").val() == ""){
		alert("Please Select the IV Date");
		$("#rv_date").focus();
		return false;
	}
	
	if($("#rv_date").val() > c_d){
		$("#rv_date").focus();
		alert("Can't select the Future Date");
		return false;
	}
	
	if($("#from_sus_no").val() == -1){
		alert("Please Select the Issuing Depot");
		$("#from_sus_no").focus();
		return false;
	}
	
	if($("#to_sus_name").val() == ""){
		alert("Please Select the To Unit");
		$("#to_sus_name").focus();
		return false;
	}
	
	if($("#to_sus_no").val() == ""){
		alert("Please Select the To Unit");
		$("#to_sus_no").focus();
		return false;
	}
	
	if($("#type_of_hldg").val() == -1){
		alert("Please Select the Type of Holding");
		$("#type_of_hldg").focus();
		return false;
	}
	
	if($("#type_of_eqpt").val() == -1){
		alert("Please Select the Type of Equipment");
		$("#type_of_eqpt").focus();
		return false;
	}
	
	if($("#prf_code").val() == -1){
		alert("Please Select the Equipment PRF Group");
		$("#prf_code").focus();
		return false;
	}
	
	if($("#census_no").val() == -1){
		alert("Please Select the census_no");
		$("#census_no").focus();
		return false;
	}
	
	if($("#issued_qty").val() == ""){
		alert("Please Enter the Issued Quantity");
		$("#issued_qty").focus();
		return false;
	}
	if($("#unit_price").val() == "") {
        $("#unit_price").val('0');
	}
	if($("#depres_rate").val() == "") {
        $("#depres_rate").val('0');
	}
	
	if($("#eqpt_regn_no").val() == ""){
		alert("Please Enter the New Regn No");
		$("#eqpt_regn_no").focus();
		return false;
	}
	
	if($("#service_status").val() == -1){
		alert("Please Select the Serviceable Status");
		$("#service_status").focus();
		return false;
	}
	
	var eqpt_regn_no=$("#eqpt_regn_no").val();
	$("#nrWaitLoader").show();

     $.ajax({
         type: 'POST',
         url: 'getSearchRegno?'+key+'='+value,
         data: {e:eqpt_regn_no},
        	success: function(response) {
        		if(response == "Success dtl"){ 
        			$("#nrWaitLoader").hide();
        			alert("Current Reg No Exists");
        			$("#eqpt_regn_no").val('');
        		}else{
        			$("#nrWaitLoader").hide();
        			addNewEqptDetails();
        			
        		}
         }
      }); 
     
	
	
	return true;
}
var x = 0; //initlal text box count

function addNewEqptDetails(){
	$("#ac").show();
	
	$('#rv_no').attr('readonly', true); 
	$('#rv_date').attr('readonly', true); 
	$('#from_sus_no').attr("readonly", true);
	$('#from_prf_Search').attr("readonly", true);
	$('#prf_code').attr("readonly", true);
	$('#census_no').attr("readonly", true);
	$('#type_of_hldg').attr("readonly", true);
	$('#type_of_eqpt').attr("readonly", true);
	$('#to_sus_no').attr("readonly", true);
	$('#to_sus_name').attr("readonly", true);
	$('#issued_qty').attr('readonly', true); 
	$('#unit_price').attr('readonly', true); 
	$('#eqpt_make').attr('readonly', true); 
	$('#eqpt_model').attr('readonly', true); 
	$('#life_of_assets').attr('readonly', true); 
	$('#depres_rate').attr('readonly', true); 
	$('#doc_upload1').attr('readonly',true);
	
	//Fetch All Data
	var rv_no = $("#rv_no").val();
	var census_no = $("#census_no").val();
	var nomen = $("#nomen").val();
	var type_of_hldg = $("#type_of_hldg").val();
	var hldg_name = $("#hldg_name").val();
	var status = "Pending";
	var eqpt_regn_no = $("#eqpt_regn_no").val();
	var service_status = $("#service_status").val();
    var barrel1_detl = $("#barrel1_detl").val();
    var barrel2_detl = $("#barrel2_detl").val();
    var barrel3_detl = $("#barrel3_detl").val();
    var barrel4_detl = $("#barrel4_detl").val();
	var remarks = $("#remarks").val();
	
	var max_fields  = $("#issued_qty").val(); //maximum input boxes allowed
	var Exist_count = 0;
	
	var count = x;
	count++;
	
	for(i=1;i<count;i++)
	{
	
		
		if(eqpt_regn_no ==  $("#eqpt_regn_no_h"+i).val())
		{
			alert("Already Exist Reg No "+eqpt_regn_no);
			$("#eqpt_regn_no").val("");
			$("#eqpt_regn_no").focus();
			Exist_count = 1;
		}
	} 
	 
	if(Exist_count == 0)
	{

    if(x < max_fields){
    	x++;
        //document.getElementById("count").value = x;
        if(x == max_fields){
        	document.getElementById('add_more').style.display='none'; 
        	document.getElementById('btn_submit').style.display='inline-block'; 
        	$('#eqpt_regn_no').attr('readonly', true); 
        	$('#service_status').attr('readonly', true); 
        	$('#barrel1_detl').attr('readonly', true); 
        	$('#barrel2_detl').attr('readonly', true); 
        	$('#barrel3_detl').attr('readonly', true); 
        	$('#barrel4_detl').attr('readonly', true); 
        	$('#remarks').attr('readonly', true); 
        }
        var len = $('#nrTable > tr').length; 
    	var tab = $("#nrTable");
    	catId = '0';
    	var nkrow="<tr style='font-size: 12px' id='NRRDO' name='NRRDO'>";
    	nkrow=nkrow+"<td style='text-align:center;' width='2%'>" + x + "<input type='hidden' name='barrel1_detl_t' value='"+$("#barrel1_detl").val()+"'></td>"; 
    	nkrow=nkrow+"<td style='text-align:center;' width='8%'>" + census_no + "<input type='hidden' name='barrel2_detl_t' value='"+$("#barrel2_detl").val()+"'></td>"; 
    	nkrow=nkrow+"<td style='text-align:left;' width='15%'>" + nomen + "<input type='hidden' name='barrel3_detl_t' value='"+$("#barrel3_detl").val()+"'></td>"; 
    	nkrow=nkrow+"<td style='text-align:center;' width='10%'>" + hldg_name + "<input type='hidden' name='barrel4_detl_t' value='"+$("#barrel4_detl").val()+"'></td>"; 
    	nkrow=nkrow+"<td style='text-align:center;' width='10%'>" + eqpt_regn_no + "<input type='hidden' name='remarks_t' value='"+$("#remarks").val()+"'></td>"; 
    	nkrow=nkrow+"<td style='text-align:center;' width='8%'>" + status + "<input type='hidden' name='eqpt_regn_no_t' value='"+$("#eqpt_regn_no").val()+"'></td>"; 
    	nkrow=nkrow+"<td style='text-align:center;display:none;' width='8%'><input type='hidden' name='eqpt_regn_no_h"+x+"' id='eqpt_regn_no_h"+x+"' value='"+$("#eqpt_regn_no").val()+"'></td>"; 
    	nkrow=nkrow+"<input type='hidden' name='service_status_t' value='"+$("#service_status").val()+"'></td>"; 
    	
    	$("#nrTable").append(nkrow); 
        $("#count").val(len);
    }else{
    	alert("Max Issued Quantity Limit is Reached");
    	location.reload(true);
    }

	$("#eqpt_regn_no").val("");
	
	$("#barrel1_detl").val("");
	$("#barrel2_detl").val("");
	$("#barrel3_detl").val("");
	$("#barrel4_detl").val("");
	$("#remarks").val("");
	
	
	
	}
}

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
	
	var su = '${m_2}';
	if(su != ""){
		$("#to_sus_no").val(su);
	}
	
	var uni = '${m_3}';
	if(uni != ""){
		$("#to_sus_name").val(uni);
	}

	$().getCurDt(rv_date);
	$('#service_status').val('1');
	
	$('#from_sus_no').change(function() {
		var from_code = $(this).find('option:selected').attr("name");
		$("#from_code").val(from_code); //from_code
	});
	
	$('#type_of_hldg').change(function() {
		var hldg_name = $(this).find('option:selected').attr("name");
		$("#hldg_name").val(hldg_name); 
	});
	
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
    						if (datap.length>90) {
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
	
	$('#census_no').change(function() {
		var nomen = $(this).find('option:selected').attr("name");
		$("#nomen").val(nomen); //from_code	
	});
	
	$("#nrInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#nrTable tr").filter(function() {
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

function specialcharectereqpt_regn_no(a)
{
    //var iChars = "@#^&*$,.:;%!+_-[]";  
    var iChars = "@#^&*$,.:;%!/[]+"; 
    var data = a.value;
    for (var i = 0; i < data.length; i++)
    {      
        if (iChars.indexOf(data.charAt(i)) != -1)
        {    
        alert ("This " +data.charAt(i)+" special characters not allowed.");
        a.value = "";
        return false; 
        } 
    }
    return true;
}
</script>