<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form id="edit_aveh_daily_ReceiptIssue_Report" name="edit_aveh_daily_ReceiptIssue_Report" action="edit_aveh_daily_ReceiptIssue_ReportAction" method='POST' commandName="edit_aveh_daily_ReceiptIssue_ReportCMD">
  <div class="animated fadeIn">
	 <div class="">
	   	<div class="container" align="center">
	   		<div class="card">
	   		<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5>Edit A Vehicle DAILY RECEIPT/ISSUE REPORT</h5></div>
	   		     <div class="card-header"><strong>Receive / Issue</strong></div>
	   		         <div class="card-body card-block">
	   		             <div class="col-md-12">
	   		                 <div class="row form-group">	
	   		                     <div class="row form-group">	
										<div class="col col-md-12">
												<div class="form-check-inline form-check" style="display: inline-block; width: 700px;">
								                     <label for="inline-radio1" class="form-check-label ">
								                              <input type="radio" id="inline-radios" name="inline-radios" value="1" class="form-check-input">Free Stock
								                     </label>&nbsp;&nbsp;&nbsp;
								                     <label for="inline-radio2" class="form-check-label ">
								                              <input type="radio" id="inline-radios" name="inline-radios" value="2" class="form-check-input">Issue to Unit
								                     </label>&nbsp;&nbsp;&nbsp;
								                     <label for="inline-radio2" class="form-check-label ">
								                              <input type="radio" id="inline-radios" name="inline-radios" value="3" class="form-check-input">Receive From Unit
								                     </label>&nbsp;&nbsp;&nbsp;
								                    
								                     <label for="inline-radio2" class="form-check-label ">
								                              <input type="radio" id="inline-radios" name="inline-radios" value="4" class="form-check-input">War Trophy 
								                     </label>
								                     <input type="hidden" id="hdrdissuecond" name="hdrdissuecond" placeholder="" class="form-control">
                  
								                </div>
										</div>
						          </div>
	   		                 </div>
	   		             
	   		             </div>
	   		         </div>  
<div class="card-header"><strong>Basic Details</strong></div>  
<div class="card-body card-block">
	<div class="col-md-12">
	     <div class="col-md-6">
	     	<div class="row form-group" style="padding-top: 10px;">
                <div class="col col-md-4">
                  	<label class=" form-control-label"><strong style="color: red;">* </strong>DRR/DIR Ser No</label>
                </div>
                <div class="col-12 col-md-8">
                	<input type="hidden" id="id" name="id" placeholder="" class="form-control" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.id}">
                  	<input type="text" id="drr_dir_ser_no" name="drr_dir_ser_no" placeholder="" class="form-control autocomplete" autocomplete="off" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.drr_dir_ser_no}" readonly="readonly" maxlength="50">
                </div>
             </div>
	     </div>
	     <div class="col-md-6">
	     	<div class="row form-group" >
                <div class="col col-md-4">
                  	<label class=" form-control-label">Depot Name</label>
                </div>
                <div class="col-12 col-md-8">
                <textarea id="unit_name" name="unit_name" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"  readonly="readonly" maxlength="100"></textarea>
                </div>
             </div>
	     </div>
   </div>	    			 
	    			 
   <div class="col-md-12">
	   <div class="col-md-6">
	        <div class="row form-group">
                <div class="col col-md-4">
                  	<label class=" form-control-label"><strong style="color: red;">* </strong>Depot SUS No</label>
                </div>
                <div class="col-12 col-md-8">
                  <input type="text" id="sus_no" name="sus_no" placeholder="" class="form-control autocomplete" autocomplete="off" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.sus_no}" readonly="readonly" maxlength="8">
                </div>
             </div>
	    </div>
	    <div class="col-md-6">
	    	<div class="row form-group" id ="AgainIssueUnit" style="padding-top: 10px;display : none;">
			<div class="col col-md-4">
				<label class=" form-control-label"><strong style="color: red;">* </strong> Issue Type</label>
			</div>
			<div class="col-12 col-md-8">
				 <select name="issue_type" id="issue_type" class=" form-control" >
					 <option value="">--Select--</option>
					 <option value="Free Stock">Free Stock</option>
					 <option value="Again Issue to Unit">Again Issue to Unit</option>
						             
				 </select> 
				  <input type="hidden" id="hdissue_type" name="hdissue_type" placeholder="" class="form-control autocomplete" autocomplete="off" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.issue_type}" readonly="readonly">
                 
			</div>
		</div>
	    </div>
   </div>    			 
	 	    			 
</div>
<div class="card-header"><strong>Vehicle Daily Receipt Issue Details</strong></div>	    			
<div class="card-body card-block">
	<div class="col-md-12">
	          <div class="col-md-6">
	              <div class="row form-group">
		              <div class="col col-md-4">
		                  <label class=" form-control-label">Issue Against</label>
		              </div>
		              <div class="col-12 col-md-8">
		                  <input type="text" id="issue_against_rio" name="issue_against_rio" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="10" readonly="readonly" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.issue_agnst_rio}">
		              </div>
		          </div>
	          </div>
	          <div class="col-md-6">
	             <div class="row form-group" > 
			         <div class="col col-md-4">
			             <label class=" form-control-label">Other Agency</label>
			         </div>
			         <div class="col-12 col-md-8">
			              <input type="text" id="other_agency" name="other_agency" placeholder="" class="form-control" readonly="readonly" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.other_agency}" maxlength="50">
			          </div>
			      </div> 
	          </div>
     </div>    					
	  
	  <div class="col-md-12">
	       <div class="col-md-6">
	       		<div class="row form-group">
			         <div class="col col-md-4">
			            <label class=" form-control-label"><strong style="color: red;">* </strong>Unit SUS No</label>
			          </div>
			          <div class="col-12 col-md-8">
			              <input type="text" id="unit_sus_no" name="unit_sus_no" placeholder="" class="form-control autocomplete" autocomplete="off" readonly="readonly" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.unit_sus_no}" maxlength="8">
			          </div>
			    </div> 
	       </div>
	       <div class="col-md-6">
	       		<div class="row form-group">
			           <div class="col col-md-4">
			                 <label class=" form-control-label"><strong style="color: red;">* </strong>Unit Name</label>
			            </div>
			            <div class="col-12 col-md-8">
			             <textarea id="unit_sus_name" name="unit_sus_name" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" readonly="readonly" maxlength="100"></textarea>
			             </div>
			    </div> 
	       </div>
	  </div>  					
	    					
	   <div class="col-md-12">
	        <div class="col-md-6">
	        	<div class="row form-group">
		             <div class="col col-md-4">
		                 <label class=" form-control-label"><strong style="color: red;">* </strong>BA No</label>
		             </div>
		             <div class="col-12 col-md-8">
		                 <input type="text" id="ba_no" name="ba_no" placeholder="" maxlength="10" class="form-control autocomplete" autocomplete="off" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.ba_no}">
		                  <input type="hidden" id="ba_no_curr" name="ba_no_curr" placeholder="" maxlength="10" class="form-control" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.ba_no}">
		             </div>
		        </div>
	        </div>
	        <div class="col-md-6">
	        	<div class="row form-group">
			         <div class="col col-md-4">
			              <label class=" form-control-label">Chassis No</label>
			          </div>
			          <div class="col-12 col-md-8">
			              <input type="text" id="chassis_no" name="chassis_no" placeholder="" class="form-control" readonly="readonly" maxlength="20">
			           </div>
			    </div>
	        </div>
       </div> 					
	    					
	    <div class="col-md-12">
	          <div class="col-md-6">
	            <div class="row form-group">
		             <div class="col col-md-4">
		                  <label class=" form-control-label">Engine No</label>
		             </div>
		             <div class="col-12 col-md-8">
		                  <input type="text" id="engine_no" name="engine_no" placeholder="" maxlength="20" class="form-control autocomplete" autocomplete="off" readonly="readonly">
		              </div>
		         </div>
	          </div>
	          <div class="col-md-6">
	          		<div class="row form-group">
                			<div class="col col-md-4">
                  				<label class=" form-control-label">Kms Run</label>
                			</div>
                			<div class="col-12 col-md-8">
                  				<input type="text" id="kms_run" name="kms_run" placeholder="" class="form-control" maxlength="10" readonly="readonly" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.kms_run}">
                			</div>
			         </div>
	          </div>
       </div>						    
	    <div class="col-md-12">
	          <div class="col-md-6">
	             <div class="row form-group">
                			<div class="col col-md-4">
                  				<label class=" form-control-label">MCT</label>
                			</div>
                			<div class="col-12 col-md-8">
                  				<input type="text" id="mct_no" name="mct_no" placeholder="" class="form-control" readonly="readonly" maxlength="10">
                			</div>
			       </div>
	          	</div>
	          <div class="col-md-6">
	              <div class="row form-group">
              				<div class="col col-md-4">
                						<label class=" form-control-label">Std Nomenclature</label>
              				</div>
              				<div class="col-12 col-md-8">
              					<textarea id="std_nomenclature" name="std_nomenclature" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off" readonly="readonly" maxlength="300"></textarea>
              				</div>
		            </div>
	          </div>
         </div>					
	    
	    <div class="col-md-12">
	          <div class="col-md-6">
	          		<div class="row form-group">
							<div class="col col-md-4">
                 					<label class=" form-control-label">Classification</label>
               				</div>
               				<div class="col-12 col-md-8">
                            		 <input type="text" id="cl" name="cl" placeholder="" class="form-control autocomplete" autocomplete="off" readonly="readonly" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.classification}">
               				</div>
					</div>
	          	</div>
	          

            <div class="col-md-6">
            		<div class="row form-group" style="padding-top: 60px;"> 
                			<div class="col col-md-4">
                  				<label class=" form-control-label"><strong style="color: red;">* </strong>Auth</label>
                			</div>
                			<div class="col-12 col-md-8">
                  				<input type="text" id="authority" name="authority" placeholder="" maxlength="50" class="form-control" value="${edit_aveh_daily_ReceiptIssue_ReportCMD.authority}">
                			</div>
			         </div>   
	          </div>
		</div>				
	    
	    <div class="col-md-12">
	          <div class="col-md-6">
	               <div class="row form-group">
               					<div class="col col-md-4">
                 						<label class=" form-control-label">Remarks</label>
               					</div>
               					<div class="col-12 col-md-8">
                 						<textarea class="form-control" name="remarks" id="remarks" maxlength="255"></textarea>
               					</div>
		            </div>
	          	</div>
	          

            <div class="col-md-6">
	          </div>
       </div>					
	    					
</div>
					
				<div class="form-control card-footer" align="center" >						
					   <input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
					   <input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();">  
					   <a href="search_aveh_daily_receipt_issue_report" class="btn btn-success btn-sm" >Back</a> 
				</div>
			    
	   		</div>
	   	</div>
	 </div>
   </div>
</form:form>

<script>
function validate(){
	if ($('input[name="inline-radios"]:checked').length == 0) {
        alert('Please Choose Receive / Issue. ');
        return false;
	}  
	
	
	if($("#drr_dir_ser_no").val() == ""){
		  alert("Please Enter the DRR/DIR Ser No.");
		  return false;
	} 
	
	if($("#sus_no").val() == ""){
		  alert("Please Enter the Vehicle Depot Sus No.");
		  return false;
	} 
	
	
	a = $("input[name='inline-radios']:checked").val();
	if(a == 4){
		if($("#other_agency").val() == "" && $("#unit_sus_no").val() == ""){
			  alert("Please Enter Either Other Agency or Unit's Sus No.");
			  return false;
		} 
		
		if($("#other_agency").val() == "" && $("#unit_sus_name").val() == ""){
			  alert("Please Enter Either Other Agency or Unit's Sus Name.");
			  return false;
		} 
		
		if($("#other_agency").val() != "" && $("#unit_sus_no").val() != "" && $("#unit_sus_name").val() != ""){
			alert("Only One Entry is allowed (Other Agency or Unit's Sus No or Name.)");
			return false;
		}
	}
	
	if($("#ba_no").val() == ""){
		  alert("Please Enter the BA No.");
		  return false;
	} 
	
	if($("#authority").val() == ""){
		  alert("Please Enter the Authority No.");
		  return false;
	} 
	
    return true;
}
</script>


<script>
$(document).ready(function() {
	value1 = '${edit_aveh_daily_ReceiptIssue_ReportCMD.issue_condition}';
	remarks ='${edit_aveh_daily_ReceiptIssue_ReportCMD.remarks}';

	$("#hdrdissuecond").val(value1);
	
	if(value1 == 1){
		$("input[name=inline-radios][value='1']").prop('checked', true);
	}else if(value1 == 2){
		$("input[name=inline-radios][value='2']").prop('checked', true);
	}else if(value1 == 3){
		$("input[name=inline-radios][value='3']").prop('checked', true);
	}else if(value1 == 4){
		$("input[name=inline-radios][value='4']").prop('checked', true);
	}
	
	$('input[name=inline-radios]').attr("disabled", true);
	
	$("#remarks").val(remarks);
   
	
	if(value1 == 1 ){
		
	}else if(value1 == 2){
		$('#unit_sus_no').attr('readonly', false);  
		$('#unit_sus_name').attr('readonly', false);
		var issue_type = $("#hdissue_type").val();
		$("#AgainIssueUnit").show();
		$("select#issue_type").val(issue_type);
		
	}else if(value1 == 3){
		$('#sus_no').attr('readonly', false);  
		$('#unit_name').attr('readonly', false);
		$('#unit_sus_no').attr('readonly', false);  
		$('#unit_sus_name').attr('readonly', false);
	}else if(value1 == 4){
		$('#filler_1').attr('readonly', false);  
	}
   
	var unit_sus_no = '${edit_aveh_daily_ReceiptIssue_ReportCMD.unit_sus_no}';
	   	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {
	   		sus_no:unit_sus_no
	}).done(function(j) {
		if(j == ""){
      	 	alert("Please Enter Approved Unit SUS No");
   	  		document.getElementById("unit_sus_name").value="";
   	  		susNoAuto.val("");
   	  		susNoAuto.focus();
      	}else{
      		var length = j.length-1;
        	var enc = j[length].substring(0,16);
        	$("#unit_sus_name").val(dec(enc,j[0]));
       		
       	}
	}).fail(function(xhr, textStatus, errorThrown) {
	});
	
	var sus_no = '${edit_aveh_daily_ReceiptIssue_ReportCMD.sus_no}';
	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no
	}).done(function(j) {
		if(j == ""){
      	 	alert("Please Enter Approved Depot SUS No");
   	  		document.getElementById("unit_name").value="";
   	  		document.getElementById("ba_no").value = "";
			document.getElementById("chassis_no").value = "";
			document.getElementById("engine_no").value = "";
			document.getElementById("kms_run").value = "";
			document.getElementById("mct_no").value = "";
			document.getElementById("std_nomenclature").value = "";
   	  		susNoAuto.val("");
   	  		susNoAuto.focus();
      	}else{       		
       		var length = j.length-1;
        	var enc = j[length].substring(0,16);
        	$("#unit_name").val(dec(enc,j[0]));
       	}
	}).fail(function(xhr, textStatus, errorThrown) {
	});

	
	$("#sus_no").keyup(function(){
		var sus_no = this.value;
		var susNoAuto=$("#sus_no");
		var a = $("input[name='inline-radios']:checked").val();
		if(a == undefined){
			alert("Please Select first Receive/Issue");
			document.getElementById("unit_name").value="";
       	  	susNoAuto.val("");
			return false;
		}
		susNoAuto.autocomplete({
			source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
			    url: "getDepotSUSNoDetailList?"+key+"="+value,
		        data: {sus_no:sus_no},
				success: function( data ) {
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
			minLength: 1,
			autoFill: true,
			change: function(event, ui) {
		    	if (ui.item) {   	        	  
		       		return true;    	            
		       	} else {
		       		alert("Please Enter Approved Depot SUS No");
		       	  	document.getElementById("unit_name").value="";
		       	 	document.getElementById("ba_no").value = "";
					document.getElementById("chassis_no").value = "";
					document.getElementById("engine_no").value = "";
					document.getElementById("kms_run").value = "";
					document.getElementById("mct_no").value = "";
					document.getElementById("std_nomenclature").value = "";
		       	  	susNoAuto.val("");	        	  
		       	  	susNoAuto.focus();
		       	  	return false;	             
		       	}   	         
		    }, 
			select: function( event, ui ) {
				var unit_sus_no = ui.item.value;			      	
					
						$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {
							sus_no:unit_sus_no
						}).done(function(j) {
							if(j == ""){
		 			      	 	alert("Please Enter Approved Depot SUS No");
				        	  	document.getElementById("unit_name").value="";
				        	  	document.getElementById("ba_no").value = "";
								document.getElementById("chassis_no").value = "";
								document.getElementById("engine_no").value = "";
								document.getElementById("kms_run").value = "";
								document.getElementById("mct_no").value = "";
								document.getElementById("std_nomenclature").value = "";
				        	  	susNoAuto.val("");
				        	  	susNoAuto.focus();
		 			      	}else{
		 	   	        		
		 	   	        	 var length = j.length-1;
					        	var enc = j[length].substring(0,16);
					        	$("#unit_name").val(dec(enc,j[0]));
		 	   	        		if(a == 1 || a == 2 || a==4){
		 	   	        			Ba_noAutoComplate(sus_no);
		 	   	        		}
		 			      	}
							
						}).fail(function(xhr, textStatus, errorThrown) {
						});	
				
			} 	     
		});	
	});
	
	// Depot Unit Name
 	
	$("#unit_name").keyup(function(){
 		var unit_name = this.value;
		var unit_nameAuto=$("#unit_name");
 		var a = $("input[name='inline-radios']:checked").val();
		if(a == undefined){
			alert("Please Select first Receive/Issue");
			document.getElementById("sus_no").value="";
       	  	unit_nameAuto.val("");
			return false;
		}
		unit_nameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({		   
		        type: 'POST',
			    url: "getDepotNameDetailList?"+key+"="+value,
		        data: {unit_name:unit_name},
		          success: function( data ) {
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
		      minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 	if (ui.item) {   	        	  
		        		return true;    	            
		          	} else {
		        	  	alert("Please Enter Approved Unit Name");
		        	  	document.getElementById("sus_no").value="";
		        	  	document.getElementById("ba_no").value = "";
						document.getElementById("chassis_no").value = "";
						document.getElementById("engine_no").value = "";
						document.getElementById("kms_run").value = "";
						document.getElementById("mct_no").value = "";
						document.getElementById("std_nomenclature").value = "";
		        	  	unit_nameAuto.val("");	        	  
		        	  	unit_nameAuto.focus();
		        	  	return false;	             
		          	}   	         
		      }, 
		      select: function( event, ui ) {
		    	  var target_unit_name = ui.item.value;
			      
							
							$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {
								target_unit_name:target_unit_name
							}).done(function(j) {
								if(j == ""){
					    	 		alert("Please Enter Approved Unit Name");
						        	document.getElementById("sus_no").value="";
						        	document.getElementById("ba_no").value = "";
									document.getElementById("chassis_no").value = "";
									document.getElementById("engine_no").value = "";
									document.getElementById("kms_run").value = "";
									document.getElementById("mct_no").value = "";
									document.getElementById("std_nomenclature").value = "";
						        	unit_nameAuto.val("");	        	  
						        	unit_nameAuto.focus();
					    	 	}else{
						 			
					    	 		var length = j.length-1;
							        	var enc = j[length].substring(0,16);
							        	$("#sus_no").val(dec(enc,j[0]));
					    	 	
						 			if(a == 1 || a == 2 || a==4){
						 				Ba_noAutoComplate(dec(enc,j[0]));
			 	   	        		}
							 	}
								
							}).fail(function(xhr, textStatus, errorThrown) {
							});

 
		  	} 	     
		}); 			
	});
	
	// Unit SUS No
	
	$("#unit_sus_no").keyup(function(){
		var unit_sus_no = this.value;
		var susNoAuto=$("#unit_sus_no");
		var a = $("input[name='inline-radios']:checked").val();
		if(a == undefined){
			alert("Please Select first Receive/Issue");
			document.getElementById("unit_sus_name").value="";
      	  	susNoAuto.val("");
			return false;
		}
		susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({		      
		        type: 'POST',
			    url: "getTargetSUSNoList?"+key+"="+value,
		        data: {sus_no:unit_sus_no},
		          success: function( data ) {
		        	var unitval = [];
	        	for(var i = 0;i<data.length;i++){
	        		unitval.push(hex_to_ascii(data[i]));
	        	}
	        	response(unitval); 
		          }
		        });
		      },
			  minLength: 1,
		      autoFill: true,
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Unit SUS No");
		        	  document.getElementById("unit_sus_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		    }, 
			select: function( event, ui ) {
				var unit_sus_no = ui.item.value;	
				
							
							$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {
								sus_no:unit_sus_no
							}).done(function(j) {
								if(j == ""){
			 			      	 	alert("Please Enter Approved Unit SUS No");
					        	  	document.getElementById("unit_sus_name").value="";
					        	  	susNoAuto.val("");
					        	  	susNoAuto.focus();
			 			      	}else{
			 	   	        		
			 	   	        	 var length = j.length-1;
						        	var enc = j[length].substring(0,16);
						        	$("#unit_sus_name").val(dec(enc,j[0]));
			 	   	        		if(a == 3){
					 					Ba_noAutoComplate(unit_sus_no);
				   	        		}
			 	   	        	}
							}).fail(function(xhr, textStatus, errorThrown) {
							});  	
			} 	     
		});	
	});
	
	// Unit Unit Name
 
	$("#unit_sus_name").keyup(function(){
 		var unit_sus_name = this.value;
		var unit_nameAuto=$("#unit_sus_name");
 		var a = $("input[name='inline-radios']:checked").val();
		if(a == undefined){
			alert("Please Select first Receive/Issue");
			document.getElementById("unit_sus_no").value="";
       	  	unit_nameAuto.val("");	
			return false;
		}
		unit_nameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
				url: "getTargetUnitsNameActiveList?"+key+"="+value,
		        data: {unit_name:unit_sus_name},
		          success: function( data ) {
		        	var unitval = [];
	        	for(var i = 0;i<data.length;i++){
	        		unitval.push(hex_to_ascii(data[i]));
	        	}
	        		response(unitval); 
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
		        	  document.getElementById("unit_sus_no").value="";
		        	  unit_nameAuto.val("");	        	  
		        	  unit_nameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	  var target_unit_name = ui.item.value;
						    	
									$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {
										target_unit_name:target_unit_name
									}).done(function(j) {
											if(j == ""){
								    	 		alert("Please Enter Approved Unit Name");
										        document.getElementById("unit_sus_no").value="";
										        unit_nameAuto.val("");	        	  
										        unit_nameAuto.focus();
								    	 	}else{
										 		
								    	 		var length = j.length-1;
										        	var enc = j[length].substring(0,16);
										        	$("#unit_sus_no").val(dec(enc,j[0]));
								    	 		
										 		if(a == 3){
								 					Ba_noAutoComplate(dec(enc,j[0]));
							   	        		}
								    		}
										
										
									}).fail(function(xhr, textStatus, errorThrown) {
									});
		      }	
	});
	});	
	var ba_no = '${edit_aveh_daily_ReceiptIssue_ReportCMD.ba_no}';
	var a = $("input[name='inline-radios']:checked").val();
	$().BaDetailsList(ba_no,a);
	
	    	  $("#ba_no").keypress(function(){  
	    		  var ba_no = this.value;
	    			var ba_noAuto=$("#ba_no");
	    	 		var a = $("input[name='inline-radios']:checked").val();
	    	 		
	    			if(a == undefined){
	    				alert("Please Select first Receive/Issue");
	    				ba_noAuto.val("");	
	    				return false;
	    			}
	    			var Ba_nourl = "";
	    			var data = "";
	    			
	    			if(a==1){
	    				Ba_nourl = "getACensusBaNoList?"+key+"="+value;
	    				data = {'ba_no':ba_no};
	    			}
	    			if(a==2){
	    				var sus_no = '${edit_aveh_daily_ReceiptIssue_ReportCMD.sus_no}';
	    				if($("select#issue_type").val() != "")
	    				{
	    					if($("select#issue_type").val() == "Free Stock")
	    					{
	    						Ba_nourl = "getACensusVehSusBaNoDtlList?"+key+"="+value;
	    					}
	    					else
	    					{
	    						Ba_nourl = "getACensusVehSusBaNoDtlListCase2AgaiIssue?"+key+"="+value;
	    					}
	    					
	    					
	    					data = {'sus_no':sus_no,'ba_no':ba_no};
	    				}
	    				else
	    				{
	    					alert("Please Select Issue Type.");
	    				}
	    			}
	    			if(a == 3){
	    				var sus_no = $("#unit_sus_no").val();
	    				Ba_nourl = "getACensuscase3BaNoDtlList?"+key+"="+value;
	    				data = {'unit_sus_no':sus_no,'ba_no':ba_no};
	    			}
	    			if(a== 4){
	    				var sus_no = '${edit_aveh_daily_ReceiptIssue_ReportCMD.sus_no}';
	    				Ba_nourl = "getACensuscase4BaNoDtlList?"+key+"="+value;
	    				data = {'sus_no':sus_no,'ba_no':ba_no};
	    			}
	    			ba_noAuto.autocomplete({
	    			      source: function( request, response ) {
	    			        $.ajax({
	    			        type: 'POST',
	    			        url: Ba_nourl,
	    			        data: data,
	    			          success: function( data ) {
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
	    			      minLength: 1,
	    			      autoFill: true,
	    				change: function(event, ui) {
	    			    	if (ui.item) {   	        	  
	    			        	  return true;    	            
	    			        } else {
	    			        	  alert("Please Enter Approved BA No");
	    			        	  ba_noAuto.val("");	        	  
	    			        	  ba_noAuto.focus();
	    			        	  return false;	             
	    			        }   	         
	    			    }, 
	    			    select: function( event, ui ) {
	    			      	var ba_no = ui.item.value;
	    			      	document.getElementById("chassis_no").value = "";
	    					document.getElementById("engine_no").value = "";
	    					document.getElementById("mct_no").value = "";
	    					document.getElementById("kms_run").value = 0;
	    					$().BaDetailsList(ba_no,a);
	    				} 	     
	    			}); 			
	  		});
});

function Ba_noAutoComplate(sus_no){
	
  	$("#ba_no").keypress(function(){
		
  		var ba_no = this.value;
		var ba_noAuto=$("#ba_no");
 		var a = $("input[name='inline-radios']:checked").val();
		if(a == undefined){
			alert("Please Select first Receive/Issue");
			ba_noAuto.val("");	
			return false;
		}
		var Ba_nourl = "";
		var data = "";
		
		if(a==1){
			Ba_nourl = "getACensusBaNoList";
			data = {'ba_no':ba_no};
		}
		if(a==2){			
			if($("select#issue_type").val() != "")
			{
				if($("select#issue_type").val() == "Free Stock")
				{
					Ba_nourl = "getACensusVehSusBaNoDtlList";
				}
				else
				{
					Ba_nourl = "getACensusVehSusBaNoDtlListCase2AgaiIssue";
				}				
				data = {'sus_no':sus_no,'ba_no':ba_no};
			}
			else
			{
				alert("Please Select Issue Type.");
			}			
		}
		if(a == 3){
			Ba_nourl = "getACensuscase3BaNoDtlList";
			data = {'unit_sus_no':sus_no,'ba_no':ba_no};
		}
		if(a== 4){
			Ba_nourl = "getACensuscase4BaNoDtlList";
			data = {'sus_no':sus_no,'ba_no':ba_no};
		}
		ba_noAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "Ba_nourl?"+key+"="+value,
		        data: data,
		          success: function( data ) {
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
		      minLength: 1,
		      autoFill: true,
			change: function(event, ui) {
		    	if (ui.item) {   	        	  
		        	  return true;    	            
		        } else {
		        	  alert("Please Enter Approved BA No");
		        	  ba_noAuto.val("");	        	  
		        	  ba_noAuto.focus();
		        	  return false;	             
		        }   	         
		    }, 
		    select: function( event, ui ) {
		      	var ba_no = ui.item.value;
		      	document.getElementById("chassis_no").value = "";
				document.getElementById("engine_no").value = "";
				document.getElementById("mct_no").value = "";
				document.getElementById("kms_run").value = 0;
				$().BaDetailsList(ba_no,a);
			} 	     
		}); 			
	});  		
}

$.fn.BaDetailsList = function(ba_no,a) {
	
	$.post("getBaDetailsList?"+key+"="+value, {
		ba_no : ba_no
	}).done(function(j) {
		var length = j.length-1;
        var enc = j[length][0].substring(0,16);
        $("#engine_no").val(dec(enc,j[0][0]));
        $("#chassis_no").val(dec(enc,j[0][1]));
        $("#mct_no").val(dec(enc,j[0][2]));
		document.getElementById("std_nomenclature").value = "";
		var mct = dec(enc,j[0][2]);		
				
				$.post("getStdNomenList?"+key+"="+value, {
					mct : dec(enc,j[0][2])
				}).done(function(k) {
					var length = k.length-1;
		        	var enc = k[length].substring(0,16);
		        	$("#std_nomenclature").val(dec(enc,k[0]));
				}).fail(function(xhr, textStatus, errorThrown) {
				});				
	}).fail(function(xhr, textStatus, errorThrown) {
	});	
	if(a==3){
		
		$.post("getACensuscase3KmList?"+key+"="+value, {
			ba_no : ba_no
		}).done(function(k) {
			for (var l = 0; l < k.length; l++) {
				$("#kms_run").val(k[l]);
			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	}	
	if(a == 4){
		
		$.post("getACensuscase6KmList?"+key+"="+value, {
			ba_no : ba_no
		}).done(function(j) {
			for (var l = 0; l < k.length; l++) {
				$("#kms_run").val(k[l]);
			}
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	}	
}

$('select#issue_type').change(function() {
    document.getElementById("ba_no").value = "";
    document.getElementById("chassis_no").value = "";
	document.getElementById("engine_no").value = "";
	document.getElementById("mct_no").value = "";
	document.getElementById("kms_run").value = 0;
	document.getElementById("std_nomenclature").value = "";
});  
</script> 

