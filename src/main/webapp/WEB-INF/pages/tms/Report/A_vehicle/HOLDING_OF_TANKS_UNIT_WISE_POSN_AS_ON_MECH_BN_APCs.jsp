<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/miso_js/jquery-1.12.3.js"></script>

<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/print_OLAPReport.js"></script>

	<div class="animated fadeIn" >
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>HOLDING OF TANKS UNIT WISE</h5></div>
	          			<div class="card-body card-block">
	          		         			<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
	                  						<label class=" form-control-label">ARM Name</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                						<input type="text" id="arm_name" name="arm_name" maxlength="100" class="form-control autocomplete" autocomplete="off">
	                					</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
	                  						<label class=" form-control-label">PRF Name</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                						<input type="text" id="prf_name" name="prf_name" maxlength="35" class="form-control autocomplete" autocomplete="off">                  					    
	                					</div>
									</div>
								</div>
							</div>
							
							<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
	                  						<label class=" form-control-label">Unit Name</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                						<input type="text" id="unit_name" name="unit_name" maxlength="100" class="form-control autocomplete" autocomplete="off" >
	                  				
	                					</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
	                  						<label class=" form-control-label">Force Type</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                						<input type="text" id="force_type" name="force_type" maxlength="10" class="form-control autocomplete" autocomplete="off">                  					    
	                					</div>
									</div>
								</div>
							</div>
	          				<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
	                  						<label class=" form-control-label">From Date</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                						<input type="date" id="from_date" name="" class="form-control" max='${date}' placeholder="mm-yy" class="form-control" >
	                  				
	                					</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
	                  						<label class=" form-control-label">To Date</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                						<input type="date" id="to_date" name="" class="form-control" max='${date}' placeholder="mm-yy" class="form-control">
	                  					    
	                					</div>
									</div>
								</div>
							</div>
						</div>	
	           
							
				<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	            	<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search()" > 
				</div> 
			</div>
	        </div>
		
	</div>

        
        <div id="printableArea" >
	<div class="header"><h4 style="margin-top:20px;text-align:center;text-decoration: underline;"><b>RESTRICTED</b></h4><br></div>
				<table style="width:100%;">
					<tr>
				   		<td align="left" style="padding-right:50px;">
				   			<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
				   		</td>
				   		<td align="center">
				   			<h4 style="text-decoration: underline;"><b>DTE GENERAL INFO SYS</b></h4>
				   			<h5>HOLDING OF TANKS UNIT WISE<br>
				   			FROM:<strong><span id="from_d"></span></strong>&nbsp; &nbsp; TO:<strong><span id="to_d"></span></strong></h5>
				   		</td>
				   		<td align="right">
				   			<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
				   		</td>
					</tr>
				</table>
				<div id="in" style="display: none;">
		    		<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
						<table id="SearchReport2" class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead style="background-color: #9c27b0; color: white;" align="center">
									<tr>
										<th style=" text-align: center; width: 2%;">SUS No</th>	
										<th style=" text-align: center; width: 3%;">Unit Name</th>	
										<th style=" text-align: center; width: 2%;">MCT</th>
										<th style=" text-align: center; width: 2%;">TOTAL</th>
										</tr>
								
							</thead>
							<tbody style="font-size: 12px" align="center">
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>										
									<td style=" text-align: center; width: 2%;">${item[0]}</td>									
									<td style=" text-align: left; width: 3%;">${item[1]}</td>
									<td style=" text-align: center; width: 2%;">${item[2]}</td>	
									<td style=" text-align: center; width: 2%;">${item[3]}</td>		
								</tr>
							</c:forEach>
							</tbody>
					</table>
					</div>
		</div>
			   	<h5 style="text-align:right;text-decoration: underline;"><b>Report Generated On <span id="t_date"> ${t}</span></b></h5>		   			
			   	<div class="footer">
			   			<h4 style="margin-top:20px;text-align:center;text-decoration: underline;"><b>RESTRICTED</b></h4><br>		   			
			   	</div>	
	</div>
        

	<div class="animated fadeIn" id="btnhide" style="display: none;">
			<div class="col-md-12" align="center">
				<div class="col-md-12" align="center">
					<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')">
				</div>
			</div>
	</div> 	

<c:url value="HOLDING_OF_TANKS_UNIT_WISE_POSN_AS_ON_MECH_BN_APCs" var="searchUrl3" />
	<form:form action="${searchUrl3}" method="post" id="search3" name="search3" modelAttribute="arm_name1">
		<input type="hidden" name="from_date1" id="from_date1" value=""/>
		<input type="hidden" name="to_date1" id="to_date1" value=""/>
		<input type="hidden" name="arm_name1" id="arm_name1" value=""/>
		<input type="hidden" name="prf_name1" id="prf_name1" value=""/>
		<input type="hidden" name="force_type1" id="force_type1" value=""/>
		<input type="hidden" name="unit_name1" id="unit_name1" value=""/>
	</form:form>

<script>
$(document).ready(function() {
	if('${list.size()}' == 0 ){
		$("#btnhide").hide();
		$("table#SearchReport2").append("<tr><td colspan='9' style='text-align :center;'>Data Not Available</td></tr>");
	 } 
	 else{
		 $("#btnhide").show();
		 $("#printableArea").show();
	 }
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	if('${list.size()}'=="")
	{
		$("#printableArea").hide();
		$("#btnhide").hide();
	}
	else
	{
			$("#from_date").val('${from_date1}');
			$("#to_date").val('${to_date1}');
			$("#arm_name").val('${arm_name}');
			$("#prf_name").val('${prf_name1}');
			$("#force_type").val('${force_type1}');
			$("#unit_name").val('${unit_name1}');
	}
	
	$("#from_date").val('${from_date1}');
	$("#to_date").val('${to_date1}');
	$("#arm_name").val('${arm_name1}');
	$("#prf_name").val('${prf_name1}');
	$("#force_type").val('${force_type1}');
	$("#unit_name").val('${unit_name1}');
	
	var from_d=$("#from_date").val();
	var from_d1=from_d.substring(0,4);
	var from_d2=from_d.substring(7,5);
	var from_d3=from_d.substring(10,8);
	var frm_d = from_d3+"-"+from_d2+"-"+from_d1;

	var to_d=$("#to_date").val();
	var to_d1=to_d.substring(0,4);
	var to_d2=to_d.substring(7,5);
	var to_d3=to_d.substring(10,8);
	var to_d0 = to_d3+"-"+to_d2+"-"+to_d1; 
	
    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); 
    var yyyy = today.getFullYear();
    var t = dd+"-"+mm+"-"+yyyy; 
	
	document.getElementById('from_d').innerHTML = frm_d;
	document.getElementById('to_d').innerHTML = to_d0; 	
	document.getElementById('t_date').innerHTML = t; 

	if('${from_date1}' != "")
	{
		$("#in").show();
		$("#in1").show();
	}
	
	$("#arm_name").keypress(function(){
		var arm_name = this.value;
	 	var susNoAuto=$("#arm_name");
	  	susNoAuto.autocomplete({
	      	source: function( request, response ) {
	        	$.ajax({
	        		type: 'POST',
	        		url: "getarm_name_report?"+key+"="+value,
	        		data: {arm_name:arm_name},
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
	        		alert("Please Enter Approved Arm Name.");
	        	  	document.getElementById("arm_name").value="";
	        	  	susNoAuto.val("");	        	  
	        	  	susNoAuto.focus();
	        	  	return false;	             
	          	}   	         
			},  	     
		});	
	});
	
	
	$("#prf_name").keypress(function(){
		var prf_name = this.value;
	 	var susNoAuto=$("#prf_name");
	  	susNoAuto.autocomplete({
	      	source: function( request, response ) {
	        	$.ajax({
	        		type: 'POST',
	        		url: "getprf_name?"+key+"="+value,
	        		data: {prf_name:prf_name},
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
	        		alert("Please Enter Approved PRF Name.");
	        	  	document.getElementById("prf_name").value="";
	        	  	susNoAuto.val("");	        	  
	        	  	susNoAuto.focus();
	        	  	return false;	             
	          	}   	         
			},  	     
		});	
	});
	
	$("#unit_name").keypress(function(){
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
	        		alert("Please Enter Approved Unit Name.");
	        	  	document.getElementById("unit_name").value="";
	        	  	susNoAuto.val("");	        	  
	        	  	susNoAuto.focus();
	        	  	return false;	             
	          	}   	         
			},  	     
		});	
	});	
	
	$("#force_type").keypress(function(){
		var force_type = this.value;
	 	var susNoAuto=$("#force_type");
	  	susNoAuto.autocomplete({
	      	source: function( request, response ) {
	        	$.ajax({
	        		type: 'POST',
	        		url: "getforce_type_report?"+key+"="+value,
	        		data: {force_type:force_type},
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
	        		alert("Please Enter Approved Force Type.");
	        	  	document.getElementById("force_type").value="";
	        	  	susNoAuto.val("");	        	  
	        	  	susNoAuto.focus();
	        	  	return false;	             
	          	}   	         
			},  	     
		});	
	});
});
function Search(){
	var from=$("#from_date").val();
    var to=$("#to_date").val();  
    if ($("#from_date").val() == "") {
		alert("Please Enter From Date.");
		return false;
	}
    if ($("#to_date").val() == "") {
		alert("Please Enter To Date.");
		return false;
	} 
    if ($("#arm_name").val() == "") {
		alert("Please Enter Arm Name.");
		return false;
	}
	if ($("#prf_name").val() == "") {
		alert("Please Enter PRF Name.");
		return false;
	}if ($("#force_type").val() == "") {
		alert("Please Enter Type of Force.");
		return false;
	}
	if ($("#unit_name").val() == "") {
		alert("Please Enter Unit Name.");
		return false;
	}
	$("input#from_date1").val($("#from_date").val());
    $("input#to_date1").val($("#to_date").val());
    $("input#arm_name1").val($("#arm_name").val());
    $("input#prf_name1").val($("#prf_name").val());
    $("input#force_type1").val($("#force_type").val());
    $("input#unit_name1").val($("#unit_name").val());
    $("#WaitLoader").show();
    document.getElementById('search3').submit();
}
</script>

 