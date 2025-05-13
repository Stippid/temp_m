<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/print_OLAPReport.js"></script>

<form:form action="search_conver_restruc_reorga_redes_actionlist" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn" >
	    	<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"><h5>TOTAL AUTH : RANK & PARENT ARM WISE</h5></div> 
	          			<div class="card-body card-block">
	          		
	          				<div class="col-md-12">
								<div class="col-md-6">
									<div class="row form-group">
										<div class="col col-md-4">
	                  						<label class=" form-control-label">SUS NO</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                						<input type="text" id="sus_no" name=""  maxlength="8" class="form-control autocomplete" autocomplete="off">	                  				
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
				   			<h5>TOTAL AUTH : RANK & PARENT ARM WISE<br>
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
										<th style="font-size: 13px" align="center"> PARENT ARM DESC</th>
										<th style="font-size: 13px" align="center"> USER ARM DESC </th>	
										<th style="font-size: 13px" align="center"> COMMAND </th>
										<th style="font-size: 13px" align="center"> CORPS </th>
										<th style="font-size: 13px" align="center"> DIV </th>
										<th style="font-size: 13px" align="center"> BDE </th>
										<th style="font-size: 13px" align="center"> USER ARM </th>
										<th style="font-size: 13px" align="center"> USER UNIT SUS NO </th>
										<th style="font-size: 13px" align="center"> UNIT NAME </th>
										<th style="font-size: 13px" align="center"> CT TYPE </th>
										<th style="font-size: 13px" align="center"> AUTHORITY </th>
										<th style="font-size: 13px" align="center"> OFFICER </th>
										<th style="font-size: 13px" align="center"> JCO </th>
										<th style="font-size: 13px" align="center"> JCO/OR </th>
										<th style="font-size: 13px" align="center"> OR </th>
										<th style="font-size: 13px" align="center"> CIVIL CLASS I GAZ </th>
										<th style="font-size: 13px" align="center"> CIVIL CLASS II GAZ </th>
										<th style="font-size: 13px" align="center"> CIVIL CLASS II NON GAZ </th>
										<th style="font-size: 13px" align="center"> CIVIL CLASS III NON GAZ </th>
										<th style="font-size: 13px" align="center"> CIVIL CLASS IV NON GAZ </th>
										<th style="font-size: 13px" align="center"> CIVIL CLASS II NON GAZ INDUSTRIAL </th>
										<th style="font-size: 13px" align="center"> CIVIL CLASS III NON GAZ INDUSTRIAL </th>
										<th style="font-size: 13px" align="center"> CIVIL CLASS IV NON GAZ INDUSTRIAL </th>
									</tr>
							</thead>
							<tbody style="font-size: 12px" align="center">
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>										
									<td >${item[0]}</td>									
									<td >${item[1]}</td>
									<td >${item[2]}</td>
									<td >${item[3]}</td>
									<td >${item[4]}</td>	
									<td >${item[5]}</td>
									<td >${item[6]}</td>
									<td >${item[7]}</td>									
									<td >${item[8]}</td>
									<td >${item[9]}</td>
									<td >${item[10]}</td>
									<td >${item[11]}</td>	
									<td >${item[12]}</td>
									<td >${item[13]}</td>
									<td >${item[14]}</td>									
									<td >${item[15]}</td>
									<td >${item[16]}</td>
									<td >${item[17]}</td>
									<td >${item[18]}</td>
									<td >${item[19]}</td>									
									<td >${item[20]}</td>
									<td >${item[21]}</td>
									<td >${item[22]}</td>
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
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="col-md-12" align="center">
					<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')">
				</div>
			</div>
		</div>
	</div> 	
</form:form>
<c:url value="12_TOTAL_AUTH_RANK_WISE_OARENT_ARM_WISE" var="searchUrl4" />
	<form:form action="${searchUrl4}" method="post" id="search3" name="search3" modelAttribute="sus_no1">
		<input type="hidden" name="from_date1" id="from_date1" value=""/>
		<input type="hidden" name="to_date1" id="to_date1" value=""/>
		<input type="hidden" name="sus_no1" id="sus_no1" value=""/>
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
			$("#sus_no").val('${sus_no1}');
			$("#from_date").val('${from_date1}');
			$("#to_date").val('${to_date1}');
	}
	
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
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
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
});
function Search(){
	var sus_no=$("#sus_no").val();
	var from=$("#from_date").val();
    var to=$("#to_date").val();
    
    if ($("#sus_no").val() == "") {
		alert("Please EnterSUS No.");
		return false;
	}
	    
    if ($("#from_date").val() == "") {
		alert("Please Enter From Date.");
		return false;
	}
    
    if ($("#to_date").val() == "") {
		alert("Please Enter To Date.");
		return false;
	} 
    
    $("input#sus_no1").val($("#sus_no").val());
	$("input#from_date1").val($("#from_date").val());
    $("input#to_date1").val($("#to_date").val());
    $("#WaitLoader").show();
    document.getElementById('search3').submit();
}

$("#sus_no").keypress(function(){
	var sus_no = this.value;
 	var susNoAuto=$("#sus_no");
  	susNoAuto.autocomplete({
      	source: function( request, response ) {
        	$.ajax({
        		type: 'POST',
        		url: "getSusNoActiveList?"+key+"="+value,
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
        		alert("Please Enter Approved SUS No");
        	  	document.getElementById("sus_no").value="";
        	  	susNoAuto.val("");	        	  
        	  	susNoAuto.focus();
        	  	return false;	             
          	}   	         
		},  	     
	});	
});
</script>
