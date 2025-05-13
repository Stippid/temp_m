<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/print_OLAPReport.js"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<!-- YEARLY REPORT -->

<form:form action="movement_in_out_command_reportAction1" method="post" class="form-horizontal"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>MOVE DETAILS COMMAND WISE</h5></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">
								<div class="col-md-6">
		          					<div class="row form-group">
		                				<div class="col col-md-4">
		                  					<label class=" form-control-label">Select Report</label>
		                				</div>
		                				<div class="col-12 col-md-8">
		                 					<select id="type_of_report" name="type_of_report" class="form-control-sm form-control" value="${type_of_report}"> 
		                 						<option value="0">--Select--</option>
		                 						<option value="in">MOVEMENT IN TO COMMAND </option>
		                 						<option value="out">MOVEMENT OUT OF COMMAND </option>
		                 						<option value="within">MOVEMENT WITHIN COMMAND </option>
		                 					</select>
		                				</div>
	              					</div>
	          					</div>
<!-- 	          					<div class="col-md-6"> -->
<!-- 		          					<div class="row form-group"> -->
<!-- 		                				<div class="col col-md-4"> -->
<!-- 		                  					<label class=" form-control-label">Select Report</label> -->
<!-- 		                				</div> -->
<!-- 		                				<div class="col-12 col-md-8"> -->
<%-- 		                 					<select id="duration" name="duration" class="form-control-sm form-control" value="${duration}">  --%>
<!-- 		                 						<option value="0">--Select--</option> -->
<!-- 		                 						<option value="1">1st Half</option> -->
<!-- 		                 						<option value="2">2st Half</option> -->
<!-- 		                 					</select> -->
<!-- 		                				</div> -->
<!-- 	              					</div> -->
<!-- 	          					</div> -->
	          				</div>
	          				
	          				<div class="col-md-12">
					
					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">From Date:</label>
						                </div>
						                <div class="col-md-8">
						               <input type="text" name="from_date" id="from_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  max="${date}">
						                </div>
						            </div>
	              				</div>
	              				

							<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">To Date:</label>
						                </div>
						                <div class="col-md-8">
						               <input type="text" name="to_date" id="to_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  max="${date}">
						                </div>
						            </div>
	              				</div>				
	              			</div>
					
					
						</div>	
				<div class="card-footer" align="center">
					<a href="movement_in_out_command_report" type="reset" class="btn btn-success btn-sm"> Clear </a>
	            	<input type="submit" class="btn btn-primary btn-sm" value="Search" onclick="Search();" >
				</div> 
			</div>
	        </div>
		
	</div>
<c:if test='${type_of_report == "in"}'>
<div class="animated fadeIn" id="printableArea" >
	
		<div class="card-body">
			<table class="col-md-12" >
				<tr>
			   		<td align="left">
			   			<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
			   		</td>
			   		<td align="center">
			   			<h5><u><b>RESTRICTED</b></u><br></h5>
			   			<br>
			   			<h4 style="text-decoration: underline;"><b>DTE GENERAL INFO SYS</b></h4>
			   			<br>
			   			<h5>MOVEMENT IN TO COMMAND  <br></h5>
			   			 <br>
			   		</td>
			   		<td align="right">
			   			<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
			   		</td>
				</tr>
			</table>
		</div>
	
		
			<div class="card-body">
				<div class="col-md-12" >
		    		<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
						<table id="SearchReport2" class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead style="background-color: #9c27b0; color: white;" align="center">
									<tr>
										<th style="font-size: 13px; text-align: center; width: 15%;" >COMD </th>
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ SC </th>	
										<th style="font-size: 13px; text-align: center; width: 4%;" >HQ EC </th>
										<th style="text-align: center; width: 4%;">HQ WC</th>	
										<th style="text-align: center; width: 4%;">HQ CC </th>
										<th style="text-align: center; width: 4%;">HQ NC </th>	
										<th style="text-align: center; width: 4%;">HQ  ARTRAC </th>
										<th style="text-align: center; width: 4%;">HQ ANC </th>	
										<th style="text-align: center; width: 4%;">HQ SWCC </th>
										<th style="text-align: center; width: 4%;">HQ UN </th>
										<th style="text-align: center; width: 4%;">HQ SFC </th>	
										<th style="text-align: center; width: 4%;"> HQ MOD </th>	
										<th style="text-align: center; width: 4%;">TOTAL </th>										
									</tr>
							</thead>
							<tbody style="font-size: 12px" align="center">
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>										
									<td style="text-align:left; width: 15%" >${item[0]}</td>									
									<td style="text-align: center; width: 4%;">${item[1]}</td>
									<td style="text-align: center; width: 4%;">${item[2]}</td>	
									<td style="text-align: center; width: 4%;">${item[3]}</td>									
									<td style="text-align: center; width: 4%;">${item[4]}</td>
									<td style="text-align: center; width: 4%;">${item[5]}</td>		
									<td style="text-align: center; width: 4%;">${item[6]}</td>									
									<td style="text-align: center; width: 4%;">${item[7]}</td>
									<td style="text-align: center; width: 4%;">${item[8]}</td>		
									<td style="text-align: center; width: 4%;">${item[9]}</td>									
									<td style="text-align: center; width: 4%;">${item[10]}</td>
									<td style="text-align: center; width: 4%;">${item[11]}</td>	
									<td style="text-align: center; width: 4%;">${item[12]}</td>	
																														
								</tr>
							</c:forEach>					
							</tbody>
					</table>
					</div>
				</div>
			</div>
		
			
		<div class="card-body">
			<table class="col-md-11" >
				<tr>
			   		<td align="right" >
			   			<h5><u><b>Report Generated On <span id="t_date"> ${t}</span></b></u></h5>			   			
			   		</td>
			   			   		
				</tr>
				<tr>
					<td align="center">
			   			<h5><u><b>RESTRICTED</b></u><br></h5>			   			
			   		</td>	
				</tr>
			</table>
		</div>	
	
	</div> 
</c:if>
<c:if test='${type_of_report == "out"}'>
<!-- FOR MOVEMENT OUT COMMAND -->
	<div class="animated fadeIn" id="printableArea" >
			<div class="card-body">
			<table class="col-md-12" >
				<tr>
			   		<td align="left">
			   			<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
			   		</td>
			   		<td align="center">
			   			<h5><u><b>RESTRICTED</b></u><br></h5>
			   			<br>
			   			<h4 style="text-decoration: underline;"><b>DTE GENERAL INFO SYS</b></h4>
			   			<br>
			   			<h5>MOVEMENT OUT OF COMMAND  <br></h5>
			   			 <br>
			   		</td>
			   		<td align="right">
			   			<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
			   		</td>
				</tr>
			</table>
		</div>
		
			<div class="card-body">
				<div class="col-md-12" >
		    		<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
						<table id="SearchReport2" class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead style="background-color: #9c27b0; color: white;" align="center">
																		
									<tr>
										<th style="font-size: 13px; text-align: center; width: 15%;"> COMD  </th>
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ SC </th>	
										<th style="font-size: 13px; text-align: center; width: 4%;"> HQ EC</th>
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ WC</th>	
										<th style="font-size: 13px; text-align: center; width: 4%;"> HQ CC </th>
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ NC </th>	
										<th style="font-size: 13px; text-align: center; width: 4%;"> HQ  ARTRAC </th>
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ ANC </th>	
										<th style="font-size: 13px; text-align: center; width: 4%;"> HQ SWCC </th>
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ UN </th>	
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ SFC </th>
										<th style="font-size: 13px; text-align: center; width: 4%;"> HQ MOD </th>											
										<th style="font-size: 13px; text-align: center; width: 4%;">TOTAL </th>										
									</tr>
							</thead>
							<tbody style="font-size: 12px" align="center">
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>										
									<td style="text-align:left; width: 15%">${item[0]}</td>									
									<td style="text-align:left; width: 4%">${item[1]}</td>
									<td style="text-align:left; width: 4%">${item[2]}</td>	
									<td style="text-align:left; width: 4%">${item[3]}</td>									
									<td style="text-align:left; width: 4%">${item[4]}</td>
									<td style="text-align:left; width: 4%">${item[5]}</td>		
									<td style="text-align:left; width: 4%">${item[6]}</td>									
									<td style="text-align:left; width: 4%">${item[7]}</td>
									<td style="text-align:left; width: 4%">${item[8]}</td>		
									<td style="text-align:left; width: 4%">${item[9]}</td>									
									<td style="text-align:left; width: 4%">${item[10]}</td>
									<td style="text-align:left; width: 4%">${item[11]}</td>	
									<td style="text-align:left; width: 4%">${item[12]}</td>	
								</tr>
							</c:forEach>					
							</tbody>
					</table>
					</div>
				</div>
			</div>
		
				
		<div class="card-body">
			<table class="col-md-11" >
				<tr>
			   		<td align="right" >
			   			<h5><u><b>Report Generated On <span id="t_date"> ${t}</span></b></u></h5>			   			
			   		</td>
				</tr>
				<tr>
					<td align="center">
			   			<h5><u><b>RESTRICTED</b></u><br></h5>			   			
			   		</td>	
				</tr>
			</table>
		</div>	
		
	</div> 
</c:if>
	
	<c:if test='${type_of_report == "within"}'>
	<div class="animated fadeIn" id="printableArea" >
		
		<div class="card-body">
	<div class="row" id="crrr" >
		<div class="card-body">
			<table class="col-md-12" >
				<tr>
			   		<td align="left">
			   			<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
			   		</td>
			   		<td align="center">
			   			<h5><u><b>RESTRICTED</b></u><br></h5>
			   			<br>
			   			<h4 style="text-decoration: underline;"><b>DTE GENERAL INFO SYS</b></h4>
			   			<br>
			   			<h5>MOVEMENT WITHIN COMMAND <br> </h5>
			   			 <br>
			   		</td>
			   		<td align="right">
			   			<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
			   		</td>
				</tr>
			</table>
		</div>
	</div>
				<div class="col-md-12" >
		    		<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
						<table id="SearchReport2" class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead style="background-color: #9c27b0; color: white;" align="center">
									<tr>
										<th style="font-size: 13px; text-align: center; width: 15%;"> ACTION</th>
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ SC </th>	
										<th style="font-size: 13px; text-align: center; width: 4%;"> HQ EC</th>
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ WC</th>	
										<th style="font-size: 13px; text-align: center; width: 4%;"> HQ CC </th>
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ NC </th>	
										<th style="font-size: 13px; text-align: center; width: 4%;"> HQ  ARTRAC </th>
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ ANC </th>	
										<th style="font-size: 13px; text-align: center; width: 4%;"> HQ SWC </th>
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ UN </th>	
										<th style="font-size: 13px; text-align: center; width: 4%;">HQ SFC </th>	
										<th style="font-size: 13px; text-align: center; width: 4%;"> HQ MOD </th>
									</tr>
							</thead>
							<tbody style="font-size: 12px" align="center">
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>										
									<td style="text-align:center; width: 15%">${item[0]}</td>									
									<td style="text-align:left; width: 4%">${item[1]}</td>
									<td style="text-align:left; width: 4%">${item[2]}</td>
									<td style="text-align:left; width: 4%">${item[3]}</td>									
									<td style="text-align:left; width: 4%">${item[4]}</td>
									<td style="text-align:left; width: 4%">${item[5]}</td>			
									<td style="text-align:left; width: 4%">${item[6]}</td>									
									<td style="text-align:left; width: 4%">${item[7]}</td>
									<td style="text-align:left; width: 4%">${item[8]}</td>			
									<td style="text-align:left; width: 4%">${item[9]}</td>									
									<td style="text-align:left; width: 4%">${item[10]}</td>
									<td style="text-align:left; width: 4%">${item[11]}</td>															
								</tr>
							</c:forEach>
						
							</tbody>
					</table>
					</div>
				</div>
			</div>
		
		
		<div class="card-body">
			<table class="col-md-11" >
				<tr>
			   		<td align="right" >
			   			<h5><u><b>Report Generated On <span id="t_date"> ${t}</span></b></u></h5>			   			
			   		</td>
			   			   		
				</tr>
				<tr>
					<td align="center">
			   			<h5><u><b>RESTRICTED</b></u><br></h5>			   			
			   		</td>	
				</tr>
			</table>
		</div>	
		
	</div> 
	</c:if>
	<div class="animated fadeIn" id="btnhide" style="display: none;">
		
			<div class="col-md-12" align="center">
				<div class="col-md-12" align="center">
					<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')">
				</div>
			</div>
	
	</div> 
	
</form:form>
<c:url value="search_movement_in_out_command_actionList1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="search1" name="search1" modelAttribute="type_of_report">
		<input type="hidden" name="type_of_report" id="type_of_report" value=""/>
		<input type="hidden" name="from_date" id="from_date" value=""/>
		<input type="hidden" name="to_date" id="to_date" value=""/>
	</form:form> 
<script>
$(document).ready(function() {	
	
	jQuery(function($){ 
		 datepicketDate('from_date');
		 datepicketDate('to_date');
		
	});
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport2 tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		 
		});
		
	});
 if('${list.size()}' == 0 ){
	 $("#btnhide").hide();
		$("table#SearchReport2").append("<tr><td colspan='9' style='text-align :center;'>Data Not Available</td></tr>");
		
	 } 
 else{
	 $("#btnhide").show();
	 $("#printableArea").show();
 }
	var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); 
    var yyyy = today.getFullYear();
    var t = dd+"-"+mm+"-"+yyyy; 
    $("#t_date").text(t);
    
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport(); 

	 if('${list.size()}'=="")
	{
		$("#type_of_report").val('${type_of_report}');
		$("#from_date").val('${from_date}');
		$("#to_date").val('${to_date}');
		$("#printableArea").hide();
		$("#btnhide").hide();
	}
	else
	{
		if('${type_of_report}' != "")
		{
			$("#type_of_report").val('${type_of_report}');
			$("#from_date").val('${from_date}');
			$("#to_date").val('${to_date}');
		}
	}	 	  
});
</script>

<script>
function Search(){

	var report_type = $("#type_of_report").val();
	var from_date = $("#from_date").val();
	var to_date = $("#to_date").val();
// 	document.getElementById("frm").innerHTML = frm;
    
    if (type_of_report == "0") {
		alert("Please Select Report Type.");
		return false;
	}
    return true;
    $("input#type_of_report").val($("#type_of_report").val());
    $("input#from_date").val($("#from_date").val());
    $("input#to_date").val($("#to_date").val());
    $("#WaitLoader").show();
    document.getElementById('search1').submit();
    
}

</script>
