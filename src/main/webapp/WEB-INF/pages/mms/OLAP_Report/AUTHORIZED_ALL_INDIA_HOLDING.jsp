<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/print_OLAPReport.js"></script>

<form:form action="search_conver_restruc_reorga_redes_actionlist" method="post" enctype="multipart/form-data" class="form-horizontal"> 
	<div class="animated fadeIn" >
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>AUTHORIZED ALL INDIA REPORT</h5></div>
	          			<div class="card-body card-block">
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
				<table style="width:100%;">
				<tr align="center">
				<td></td>
				<td>
				<h5 style="text-decoration: underline;"><b>RESTRICTED</b></h5>
				</td>
				<td></td>
				</tr>
					<tr>
				   		<td align="left" style="padding-right:50px;">
				   			<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
				   		</td>
				   		<td align="center">
				   			<h4 style="text-decoration: underline;"><b>DTE GENERAL INFO SYS</b></h4>
				   			<h5>AUTHORIZED ALL INDIA REPORT<br>
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
										<th style="font-size: 13px" align="center"> COS SECTION </th>	
										<th style="font-size: 13px" align="center"> PRF GROUP</th>
										<th style="font-size: 13px" align="center"> CENSUS NO </th>
										<th style="font-size: 13px" align="center"> ITEM NOMENCLATURE </th>
										<th style="font-size: 13px" align="center"> CAT PART NO </th>
										<th style="font-size: 13px" align="center"> AU </th>
										<th style="font-size: 13px" align="center"> STATUS </th>
										<th style="font-size: 13px" align="center"> TYPE OF HOLDING </th>
										<th style="font-size: 13px" align="center"> MAIN HOLDING </th>
										<th style="font-size: 13px" align="center"> CES HOLDING </th>
										<th style="font-size: 13px" align="center"> TOTAL UE </th>
										<th style="font-size: 13px" align="center"> TOTAL UH </th>
															
									</tr>
									
							</thead>
							<tbody style="font-size: 12px" align="left">
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
									 
									
								</tr>
							</c:forEach>
							</tbody>
					</table>
					</div>
		</div>
			   	<h5 style="text-align:right;text-decoration: underline;"><b>Report Generated On <span id="t_date"> ${t}</span></b></h5>		   			
			   	
			   			<table style="width:100%;">
			   			<tr align="center">
				<td></td>
				<td>
				<h5 style="text-decoration: underline;"><b>RESTRICTED</b></h5>
				</td>
				<td></td>
				</tr>	
				</table>	   			
			   	
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
<c:url value="AUTHORIZED_ALL_INDIA_HOLDING" var="searchUrltms13" />
	<form:form action="${searchUrltms13}" method="post" id="search3" name="search3" modelAttribute="type_of_report1">
		<input type="hidden" name="from_date1" id="from_date1" value=""/>
		<input type="hidden" name="to_date1" id="to_date1" value=""/>
		<input type="hidden" name="type_of_report1" id="type_of_report1" value=""/>
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
	}
	
	$("input#from_date").val('${from_date1}');
	$("input#to_date").val('${to_date1}');
	
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
	
	$("input#from_date1").val($("#from_date").val());
    $("input#to_date1").val($("#to_date").val());
    
    document.getElementById('search3').submit();
}

</script>
