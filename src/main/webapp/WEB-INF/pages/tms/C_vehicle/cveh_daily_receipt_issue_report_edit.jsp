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

<form:form id="cveh_daily_receipt_issue_report_edit" name="cveh_daily_receipt_issue_report_edit" action="cveh_daily_receipt_issue_report_editAction" method='POST' commandName="cveh_daily_receipt_issue_report_editCMD">
  <div class="animated fadeIn">
	 <div class="">
	   	<div class="container" align="center">
	   		<div class="card">
	   		<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5>Edit C Vehicle DAILY RECEIPT/ISSUE REPORT</h5></div>
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
								                     <label for="inline-radio1" class="form-check-label ">
								                              <input type="radio" id="inline-radios" name="inline-radios" value="2" class="form-check-input">Issue to Unit
								                     </label>&nbsp;&nbsp;&nbsp;
								                     <label for="inline-radio1" class="form-check-label ">
								                              <input type="radio" id="inline-radios" name="inline-radios" value="3" class="form-check-input">Receive From Unit
								                     </label>&nbsp;&nbsp;&nbsp;
								                     <label for="inline-radio1" class="form-check-label ">
								                              <input type="radio" id="inline-radios" name="inline-radios" value="4" class="form-check-input">Auction Out
								                     </label>&nbsp;&nbsp;&nbsp;
								                     <input type="hidden" id="chkissuecond" name="chkissuecond" placeholder="" class="form-control" >                 						
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
	          				<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">* </strong>DRR/DIR Ser No</label>
                					</div>
                					<div class="col-12 col-md-8">
                						<input type="hidden" id="id" name="id" placeholder="" class="form-control" value="${cveh_daily_receipt_issue_report_editCMD.id}">
                  						<input type="text" id="drr_dir_ser_no" name="drr_dir_ser_no" placeholder="" class="form-control autocomplete" autocomplete="off" value="${cveh_daily_receipt_issue_report_editCMD.drr_dir_ser_no}" readonly="readonly" maxlength="50">
                					</div>
              				</div>
	          				</div>
	          				    <div class="col-md-6">
							    	<div class="row form-group" id ="AgainIssueUnit" style="padding-top: 10px;display : none;">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong> Issue Type</label>
									</div>
									<div class="col-12 col-md-8">
										 <select name="issue_type" id="issue_type" onchange="changeIssueType();" class=" form-control" >
											 <option value="">--Select--</option>
											 <option value="Free Stock">Free Stock</option>
											 <option value="Again Issue to Unit">Again Issue to Unit</option>
												             
										 </select> 
										  <input type="hidden" id="hdissue_type" name="hdissue_type" placeholder="" class="form-control autocomplete" autocomplete="off" value="${cveh_daily_receipt_issue_report_editCMD.issue_type}" readonly="readonly">
						                 
									</div>
								</div>
							    </div>	          				
	          			</div>	    				
	    				<div class="col-md-12">
	          				<div class="col-md-6">	          			            				
              					<div class="row form-group">
                					<div class="col col-md-5">
                						
                  						<label class=" form-control-label">Mother Depot Name</label>
                					</div>                					
	                				<div class="col-12 col-md-7">
	                                     <select id="sus_no" name="sus_no" class="form-control">
										<option value="0">--Select--</option>	
										<c:forEach var="item" items="${getMotherDepoList}" varStatus="num" >
											<option value="${item[0]}" name = "${item[1]}" >${item[1]}</option>
										</c:forEach>
									   </select> 
	                                    <input type="hidden" id="unit_name" name="unit_name" class="form-control" />
	                                     <input type="hidden" id="hd_sus_no" name="hd_sus_no" class="form-control" value="${cveh_daily_receipt_issue_report_editCMD.sus_no}"/>
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
		                  						<input type="text" id="issue_agnst_rio" name="issue_agnst_rio" placeholder="" class="form-control autocomplete" autocomplete="off" readonly="readonly" value="${cveh_daily_receipt_issue_report_editCMD.issue_agnst_rio}" maxlength="10">
		                					</div>
		              				</div>
	          				</div>
	          				<div class="col-md-6">
	          				<div class="row form-group"> 
			                			<div class="col col-md-4">
			                  				<label class=" form-control-label">Other Agency</label>
			                			</div>
			                			<div class="col-12 col-md-8">
			                  				<input type="text" id="other_agency" name="other_agency" placeholder="" class="form-control" readonly="readonly" value="${cveh_daily_receipt_issue_report_editCMD.other_agency}" maxlength="50">
			                			</div>
			              			</div> 
	          				</div>
	          			</div>		          			
	          			 <div class="col-md-12">
	          				<div class="col-md-6">
	          				<div class="row form-group">
			                			<div class="col col-md-4">
			                  				<label class=" form-control-label">Unit SUS No</label>
			                			</div>
			                			<div class="col-12 col-md-8">
			                  				<input type="text" id="unit_sus_no" name="unit_sus_no" placeholder="" class="form-control autocomplete" autocomplete="off" readonly="readonly" value="${cveh_daily_receipt_issue_report_editCMD.unit_sus_no}" maxlength="8">
			                			</div>
			              			</div> 
	          				</div>
	          				<div class="col-md-6">
	          				<div class="row form-group">
			                			<div class="col col-md-4">
			                  				<label class=" form-control-label">Unit Name</label>
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
		                  						<label class=" form-control-label"><strong style="color: red;">* </strong>EM No</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input type="text" id="em_no" name="em_no" placeholder="" class="form-control autocomplete" autocomplete="off" value="${cveh_daily_receipt_issue_report_editCMD.em_no}" maxlength="10">
		                  						<input type="hidden" id="em_no_curr" name="em_no_curr" placeholder="" class="form-control" value="${cveh_daily_receipt_issue_report_editCMD.em_no}">
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
		                  						<input type="text" id="engine_no" name="engine_no" placeholder="" class="form-control autocomplete" autocomplete="off" readonly="readonly" maxlength="20">
		                					</div>
		              				</div>
	          				</div>
	          				<div class="col-md-6">
	          				<div class="row form-group">
			                			<div class="col col-md-4">
			                  				<label class=" form-control-label">Kms Run</label>
			                			</div>
			                			<div class="col-12 col-md-8">
			                  				<input type="text" id="kms_run" name="kms_run" placeholder="" class="form-control" readonly="readonly" value="${cveh_daily_receipt_issue_report_editCMD.kms_run}" maxlength="10">
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
		                  						<label class=" form-control-label">Description of Plant</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input type="text" id="std_nomenclature" name="std_nomenclature" placeholder="" class="form-control" readonly="readonly" maxlength="100">
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
                                			    <input type="text" id="cl" name="cl" placeholder="" class="form-control autocomplete" autocomplete="off" readonly="readonly" value="${cveh_daily_receipt_issue_report_editCMD.classification}">
			                				</div>
						  			</div>
	          				</div>
	          					<div class="col-md-6" id="auction_div" style="display:none;" >
		          					<div class="row form-group">
										<div class="col col-md-5">
		                  					<label class=" form-control-label">Auction Amount (In  <i class="fa fa-inr"></i>)</label>
		                				</div>
		                				<div class="col-12 col-md-7">
		                 					<input type="text" id="auction_amount" name="auction_amount" maxlength="10" placeholder=""  class="form-control" onkeypress="return validateNumber(event, this)">
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
		                  						<textarea class="form-control" name="remarks" id="remarks" maxlength="255"></textarea>
		                					</div>
		              				</div>					
	          				</div>
	          				<div class="col-md-6">
	          				<div class="row form-group"> 
			                			<div class="col col-md-4">
			                  				<label class=" form-control-label"><strong style="color: red;">* </strong>Auth </label>
			                			</div>
			                			<div class="col-12 col-md-8">
			                  				<input type="text" id="authority" name="authority" placeholder="" class="form-control" value="${cveh_daily_receipt_issue_report_editCMD.authority}" maxlength="50">
			                			</div>
			              			</div>   
				
	          				</div>
	          			</div>
				</div>
					
				<div  class="form-control card-footer" align="center" >
					   <input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearAll();">   
					   <input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return validate();">   
					 
			    </div>			    
	   		</div>
	   	</div>
	 </div>
   </div>
</form:form>


<script>


function DepoChange()
{
	document.getElementById("em_no").value = "";
	document.getElementById("chassis_no").value = "";
	document.getElementById("engine_no").value = "";
	document.getElementById("mct_no").value = "";
	document.getElementById("kms_run").value = 0;
	document.getElementById("std_nomenclature").value = "";
}

function changeIssueType()
{
	document.getElementById("em_no").value = "";
	document.getElementById("chassis_no").value = "";
	document.getElementById("engine_no").value = "";
	document.getElementById("mct_no").value = "";
	document.getElementById("kms_run").value = 0;
	document.getElementById("std_nomenclature").value = "";
	
}

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
	if($("#em_no").val() == ""){
		  alert("Please Enter the EM No");
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
	
	var bre = '${cveh_daily_receipt_issue_report_editCMD.sus_no}'
	$("select#sus_no").val(bre);
	
	$('#auction_amount').on('keyup', function() {
	    jQuery(this).val(jQuery(this).val().split(',').join(''));
	    jQuery(this).val((jQuery(this).val()).replace(/\B(?=(?:\d{3})+(?!\d))/g, ","));

	});

	 	$('#sus_no').change(function()
	 	{	
	 		 var sus_no = $("select#sus_no").val();
	 		 Ba_noAutoComplate(sus_no);
	 			   		
	 	 });	
	      
	value1 = '${cveh_daily_receipt_issue_report_editCMD.issue_condition}';
	remarks ='${cveh_daily_receipt_issue_report_editCMD.remarks}';

	if(value1 == 1){
		$("input[name=inline-radios][value='1']").prop('checked', true);
		
	}else if(value1 == 2){
		var issue_type = $("#hdissue_type").val();
		$("#AgainIssueUnit").show();
		$("select#issue_type").val(issue_type);
		$("input[name=inline-radios][value='2']").prop('checked', true);
		
	}else if(value1 == 3){
		$("input[name=inline-radios][value='3']").prop('checked', true);
		
	}else if(value1 == 4){
		$("input[name=inline-radios][value='4']").prop('checked', true);
		
	}
	
	$("#chkissuecond").val(value1);
	
	$('input[name=inline-radios]').attr("disabled", true);
	
	$("#remarks").val(remarks);
    var depo_sus_no = $("#sus_no").val();
   
	if(value1 == 1 ){
		$('#auction_div').hide();
	}else if(value1 == 2 ){
		var unit_sus_no = '${cveh_daily_receipt_issue_report_editCMD.unit_sus_no}';
			
	
 	   	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}, function(j) {
 	   		
		}).done(function(j) {
			
			if(j == ""){
	      	 	alert("Please Enter Approved Unit SUS No.");
	   	  		document.getElementById("unit_sus_name").value="";
	      	}else{
	      		var length = j.length-1;
	        	var enc = j[length].substring(0,16);
	        	$("#unit_sus_name").val(dec(enc,j[0]));
	       		
	       	}
	       		
		}).fail(function(xhr, textStatus, errorThrown) {
		});
		
		$('#auction_div').hide();
		$('#unit_sus_no').attr('readonly', false);  
		$('#unit_sus_name').attr('readonly', false);
	}else if(value1 == 3 ){
		var unit_sus_no = '${cveh_daily_receipt_issue_report_editCMD.unit_sus_no}';
		
		
 	   	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:unit_sus_no}, function(j) {
		}).done(function(j) {
			
			if(j == ""){
				alert("Please Enter Approved Unit SUS No.");
	   	  		document.getElementById("unit_sus_name").value="";
	      	}else{
	      		var length = j.length-1;
	        	var enc = j[length].substring(0,16);
	        	$("#unit_sus_name").val(dec(enc,j[0]));
	       	}
	       		
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	
		$('#auction_div').hide();
		$('#sus_no').attr('readonly', false);  
		$('#unit_name').attr('readonly', false);
		$('#unit_sus_no').attr('readonly', false);  
		$('#unit_sus_name').attr('readonly', false);
	}else if(value1 == 4){
		
		$('#auction_div').show();
		$('#filler_1').attr('readonly', false); 
		
		var em_no = $('#em_no').val();
	
 	   	$.post("getAuctionAmtfromBaNo?"+key+"="+value, {em_no:em_no}, function(j) {

		}).done(function(j) {
			var data = hex_to_ascii(j);
			$('#auction_amount').val(data);		
			
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	}
	
	var sus_no = '${cveh_daily_receipt_issue_report_editCMD.sus_no}';
	
	
		   	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}, function(j) {
		
		}).done(function(j) {
			if(j == ""){
	      	 	alert("Please Enter Approved Depot SUS No");
	   	  		document.getElementById("unit_name").value="";
	   	  		document.getElementById("em_no").value = "";
				document.getElementById("chassis_no").value = "";
				document.getElementById("engine_no").value = "";
				document.getElementById("kms_run").value = "";
				document.getElementById("mct_no").value = "";
				document.getElementById("std_nomenclature").value = "";
	      	}else{
	      		var length = j.length-1;
	        	var enc = j[length].substring(0,16);
	        	$("#unit_name").val(dec(enc,j[0]));
	       		
	       	}		 
			
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	
	// Unit SUS No

	$("#unit_sus_no").keyup(function(){
		var unit_sus_no = this.value;
		var susNoAuto=$("#unit_sus_no");
		var a = $("input[name='inline-radios']:checked").val();
		if(a == undefined){
			alert("Please Select first Receive/Issue.");
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
			
		            $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:unit_sus_no}, function(data) {
		            }).done(function(data) {
		            	if(data == ""){
	 			      	 	alert("Please Enter Approved Unit SUS No.");
			        	  	document.getElementById("unit_sus_name").value="";
			        	  	susNoAuto.val("");
			        	  	susNoAuto.focus();
	 			      	}else{
	 			      		var length = data.length-1;
				        	var enc = data[length].substring(0,16);
				        	$("#unit_sus_name").val(dec(enc,data[0]));
	 	   	        		
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
			alert("Please Select first Receive/Issue.");
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
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
			        	for(var i = 0;i<data.length;i++){
			        		susval.push(dec(enc,data[i]));
			        	}
			        	var dataCountry1 = susval.join("|");
		            var myResponse = []; 
		            var autoTextVal=unit_nameAuto.val();
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
		      change: function(event, ui) {
		    	 if (ui.item) {   	        	  
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Unit Name.");
		        	  document.getElementById("unit_sus_no").value="";
		        	  unit_nameAuto.val("");	        	  
		        	  unit_nameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	  var target_unit_name = ui.item.value;
		    
		          $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(data) {
		            }).done(function(data) {
		             	if(data == ""){
			    	 		alert("Please Enter Approved Unit Name.");
					        document.getElementById("unit_sus_no").value="";
					        unit_nameAuto.val("");	        	  
					        unit_nameAuto.focus();
			    	 	}else{
			    	 		var length = data.length-1;
					        	var enc = data[length].substring(0,16);
					     
			    	 		$("#unit_sus_no").val(dec(enc,data[0]));
					 		if(a == 3){
			 					Ba_noAutoComplate(dec(enc,data[0]));
		   	        		}
			    		}
		            }).fail(function(xhr, textStatus, errorThrown) {
		            });
			} 	     
		}); 			
	});
 
	var ba_no = '${cveh_daily_receipt_issue_report_editCMD.em_no}';
	var a = $("input[name='inline-radios']:checked").val();
	$().BaDetailsList(ba_no,a);

	$("#em_no").keyup(function(){
 		var ba_no = this.value;
		var ba_noAuto=$("#em_no");
 		var a = $("input[name='inline-radios']:checked").val();
 		
		if(a == undefined){
			alert("Please Select first Receive/Issue.");
			ba_noAuto.val("");	
			return false;
		}
		var Ba_nourl = "";
		var data = "";
		
		if(a==1){
			Ba_nourl = "getCvehBaNoAutoList?"+key+"="+value;
			data = {'ba_no':ba_no};
		}
		if(a==2){
			
			var sus_no = '${cveh_daily_receipt_issue_report_editCMD.sus_no}';
			
			if($("select#issue_type").val() != "")
			{
				if($("select#issue_type").val() == "Free Stock")
				{
					Ba_nourl = "getCVehSusBaNoDtlList?"+key+"="+value;
				}
				else
				{
					
					Ba_nourl = "getCVehSusBaNoDtlListCase2AgaiIssue?"+key+"="+value;
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
			Ba_nourl = "getCVehcase3BaNoDtlList?"+key+"="+value;
			data = {'unit_sus_no':sus_no,'ba_no':ba_no};
		}
		if(a== 4){
			var sus_no = '${cveh_daily_receipt_issue_report_editCMD.sus_no}';
			Ba_nourl = "getCVehcase4BaNoDtlList?"+key+"="+value;
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
			        	var dataCountry1 = susval.join("|");
		            var myResponse = []; 
		            var autoTextVal=ba_noAuto.val();
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
			change: function(event, ui) {
		    	if (ui.item) {   	        	  
		        	  return true;    	            
		        } else {
		        	  alert("Please Enter Approved BA No.");
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

	$("#em_no").keyup(function(){
 		var ba_no = this.value;
		var ba_noAuto=$("#em_no").val();
 		var a = $("input[name='inline-radios']:checked").val();
 		
		if(a == undefined){
			alert("Please Select first Receive/Issue.");
			ba_noAuto.val("");	
			return false;
		}
		var Ba_nourl = "";
		var data = "";
	
		if(a==1){
			Ba_nourl = "getCvehBaNoAutoList?"+key+"="+value;
			data = {'ba_no':ba_no};
		}
		if(a==2)
		{
			
			if($("select#issue_type").val() != "")
			{
				if($("select#issue_type").val() == "Free Stock")
				{
					Ba_nourl = "getCVehSusBaNoDtlList?"+key+"="+value;
				}
				else
				{
					
					Ba_nourl = "getCVehSusBaNoDtlListCase2AgaiIssue?"+key+"="+value;
				}
				
				data = {'sus_no':sus_no,'ba_no':ba_no};
			}
			else
			{
				alert("Please Select Issue Type.");
			}
		}
		if(a == 3){
			
			Ba_nourl = "getCVehcase3BaNoDtlList?"+key+"="+value;
			data = {'unit_sus_no':sus_no,'ba_no':ba_no};
		}
		if(a== 4){
			Ba_nourl = "getCVehcase4BaNoDtlList?"+key+"="+value;
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
			        	var dataCountry1 = susval.join("|");
		            var myResponse = []; 
		            var autoTextVal=ba_noAuto.val();
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
			change: function(event, ui) {
		    	if (ui.item) {   	        	  
		        	  return true;    	            
		        } else {
		        	  alert("Please Enter Approved BA No.");
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
	

	   	$.post("getBaDetailsList?"+key+"="+value, {ba_no:ba_no}, function(j) {
	}).done(function(j) {
		
		var length = j.length-1;
        var enc = j[length][0].substring(0,16);
        $("#engine_no").val(dec(enc,j[0][0]));
        $("#chassis_no").val(dec(enc,j[0][1]));
        $("#mct_no").val(dec(enc,j[0][2]));
		document.getElementById("std_nomenclature").value = "";
		var mct = dec(enc,j[0][2]);

	 	   	$.post("getStdNomenList?"+key+"="+value, {mct : dec(enc,j[0][2])}, function(k) {
				
			}).done(function(k) {
				var length = k.length-1;
	        	var enc = k[length].substring(0,16);
	        	$("#std_nomenclature").val(dec(enc,k[0]));
				
			}).fail(function(xhr, textStatus, errorThrown) {
			});
		
	}).fail(function(xhr, textStatus, errorThrown) {
	});
	
	if(a==3){
 	   	$.post("getCVehcase3KmList?"+key+"="+value, {ba_no : ba_no}, function(k) {
		}).done(function(k) {
			for (var l = 0; l < k.length; l++) {
				$("#kms_run").val(k[l]);
			}			
		}).fail(function(xhr, textStatus, errorThrown) {
		});
		
	}
	if(a == 4){
 	   	$.post("getCVehcase4KmList?"+key+"="+value, {ba_no : ba_no}, function(k) {
		}).done(function(k) {
			for (var l = 0; l < k.length; l++) {
				$("#kms_run").val(k[l]);
			}			
		}).fail(function(xhr, textStatus, errorThrown) {
		});
	
	}
};


function validateNumber(evt) {
	  
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (charCode == 46) {
			return false;
		}
	}
	return true;
} 
		
</script>




