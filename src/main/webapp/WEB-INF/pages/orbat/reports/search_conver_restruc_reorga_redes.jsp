<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/print_OLAPReport.js"></script>

<!-- MONTHLY REPORT -->
<div class="animated fadeIn" >
   	<div class="container" align="center">
   		<div class="card">
   			<div class="card-header"><h5>MONTHLY REPORTS</h5></div> 
        		<div class="card-body">
          			<div class="col-md-12">
					<div class="col-md-6">
         					<div class="row form-group">
               				<div class="col col-md-4">
                 					<label class=" form-control-label">Select Report</label>
               				</div>
               				<div class="col-12 col-md-8">
                					<select id="type_of_report" name="type_of_report" class="form-control-sm form-control" ><!-- onchange="disband_raising(this.value);"> -->
                						<option value="0">--Select--</option>
                						<option value="crrr">CONVERSION/RE-ORGANISATION/RE-STRUCTURING/RE-DESIGNATIONS</option>
                						<option value="d">DISBANDMENTS</option>
                						<option value="rr">RAISING/RE-ACTIVTIONS</option>
                					</select>
               				</div>
            					</div>
        					</div>
        					<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
                 						<label class=" form-control-label">Select Month</label>
               						</div>
               					<div class="col-12 col-md-8">
								<select name="month" id="month" class="form-control-sm form-control" >
								</select>
						  </div>
						</div>
					</div>
        		</div>
        		
        		<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
                 					<label class=" form-control-label">Select Year</label>
               				</div>
               				<div class="col-12 col-md-8">
								 <select id="ddlYears" name="ddlYears" class="form-control-sm form-control" >
								    <option value='0'>--Select Year--</option>
								 </select>
						  </div>
					</div>
					</div>
				</div>
				
			</div>	
			<div class="card-footer" align="center">
				<a href="search_conver_restruc_reorga_redes" class="btn btn-success btn-sm" >Clear</a> 
            	<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();" > 
            
			</div> 
		</div>
	</div>
</div>
	
<c:if test='${type_of_report1 == "crrr"}'>
<div class="animated fadeIn" id="printableArea">
	
		<div class="card-body" id="restricted_id">
			<table class="col-md-12" >
				<tr>
			   		<td align="left">
			   			<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
			   		</td>
			   		<td align="center">
			   			<h5><u><b>RESTRICTED</b></u><br></h5>
			   			<br><br><br>
			   			<h4 style="text-decoration: underline;"><b>DTE GENERAL INFO SYS</b></h4>
			   			<br><br>
			   			<h5>CONVERSION/RE-ORGANISATION/RE-STRUCTURING/<br> RE-DESIGNATIONS <br></h5>
			   			 <br>
			   		</td>
			   		<td align="right">
			   			<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
			   		</td>
				</tr>
			</table>
		</div>
	
	
		<div class="card-body">
			<div class="col-md-12">
   				<div  class="watermarked" data-watermark="" id="divwatermark" >
				<span id="ip"></span>
				<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print" >
					<thead>
						<tr>
								<th style=" text-align: center;width: 6%;"> SUS NO (FROM) </th>
								<th style=" text-align: center;width: 10%;"> UNIT NAME (FROM)</th>
								<th style=" text-align: center;width: 6%;"> SUS NO (TO)</th>
								<th style=" text-align: center;width: 6%;"> UNIT NAME (TO)</th>			
								<th style=" text-align: center;width: 20%;"> COMD (FROM)</th>
								<th style=" text-align: center;width: 20%;"> COMD (TO)</th>
								<th style=" text-align: center;width: 10%;"> ARMS/SERVICE</th>	
								<th style=" text-align: center;width: 10%;"> WITH EFFECT FROM</th>
								<th style=" text-align: center;width: 10%;"> AUTHORITY</th>
							</tr>
					</thead>
					<tbody style="font-size: 12px" align="left">
						<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>	
									<td style=" text-align: center;width: 6%;" >${item[0]}</td>
									<td style=" text-align: left;width: 10%;">${item[1]}</td>
									<td style=" text-align: center;width: 6%;">${item[2]}</td>
									<td style=" text-align: center;width: 6%;">${item[3]}</td>
									<td style=" text-align: left;width: 20%;">${item[4]}</td>
									<td style=" text-align: left;width: 20%;">${item[5]}</td>
									<td style=" text-align: left;width: 10%;">${item[6]}</td>
									<td style=" text-align: center;width: 10%;">${item[7]}</td>
									<td style=" text-align: left;width: 10%;">${item[8]}</td>
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
			   			<h5><u><b>Report Generated On <span id="t_date"> ${t}</span></b></u><br></h5>			   			
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
<c:if test='${type_of_report1 == "d"}'>
 	<div class="animated fadeIn" id="printableArea" >
			
		<div class="card-body">
			<table class="col-md-12" >
				<tr>
			   		<td align="left">
			   			<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
			   		</td>
			   		<td align="center">
			   			<h5><u><b>RESTRICTED</b></u><br></h5>
			   			<br><br><br>
			   			<h4 style="text-decoration: underline;"><b>DTE GENERAL INFO SYS</b></h4>
			   			<br><br>
			   			<h5>DISBANDMENTS  <br></h5> 
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
						<table id="SearchReport1" class="table no-margin table-striped  table-hover  table-bordered report_print" >
							<thead style="background-color: #9c27b0; color: white;" align="center">
									<tr>
										<th colspan="3" align="center"> </th>
										<th colspan="4" style=" text-align: center;width:45%;"> FORMATION </th><!--colspan="4" style="font-size: 13px" align="center"  -->
										<th colspan="2" align="center"> </th>
									</tr> 
									<tr>
							       			<th style="font-size: 13px; text-align:center;"> SUS NO  </th>
											<th style="font-size: 13px; text-align: center;"> UNIT NAME  </th>
											<th style="font-size: 12px; text-align: center;"> ARMS/SERVICE </th>					
											<th style="font-size: 13px; text-align: center;">  COMD  </th>
											<th style="font-size: 13px; text-align:center;">  CORPS  </th>
											<th style="font-size: 13px; text-align:center;">  DIV  </th>
											<th style="font-size: 13px; text-align: center;">  BDE  </th>
											<th style="font-size: 13px; text-align: center;"> WITH EFFECT FROM </th>
											<th style="font-size: 13px; text-align: center;"> AUTHORITY  </th>
										</tr>
							</thead>
							<tbody style="font-size: 12px" align="left">
								<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>										
									<td style=" text-align: center;">${item[0]}</td>									
									<td >${item[1]}</td>
									<td >${item[2]}</td>									
									<td >${item[3]}</td>
									<td >${item[4]}</td>
									<td >${item[5]}</td>
									<td >${item[6]}</td>
									<td >${item[9]}</td>
									<td >${item[10]}</td>
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
			   			<h5><u><b>Report Generated On <span id="t_date"> ${t}</span></b></u><br></h5>			   			
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
	<c:if test='${type_of_report1 == "rr"}'>
	<div class="animated fadeIn" id="printableArea" >
		
		<div class="card-body">
			<table class="col-md-12" >
				<tr>
			   		<td align="left">
			   			<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
			   		</td>
			   		<td align="center">
			   			<h5><u><b>RESTRICTED</b></u><br></h5>
			   			<br><br><br>
			   			<h4 style="text-decoration: underline;"><b>DTE GENERAL INFO SYS</b></h4>
			   			<br><br>
			   			<h5>NEW RAISING/REACTIVATION REPORT <br></h5>
			   			 <br>
			   		</td>
			   		<td align="right">
			   			<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
			   		</td>
				</tr>
			</table>
		</div>
	
		
			<div class="card-body">
					<div class="row">
				<div class="col-md-12" >
		    		<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
						<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead style="background-color: #9c27b0; color: white;" align="left">
									<tr>
										<th style="font-size: 13px; text-align: center; width: 6%;"> SUS NO  </th>
										<th style="font-size: 13px; text-align: center; width: 12%;">UNIT NAME </th>	
										<th style="font-size: 13px; text-align: left; width: 8%;"> ARMS/SERVICE</th>
										<th style="font-size: 13px; text-align: center;">WITH EFFECT FROM</th>	
										<th style="font-size: 13px; text-align: center;"> AUTHORITY </th>
										<th style="font-size: 13px; text-align: center;">COMMAND</th>
									</tr>
							</thead>
							<tbody style="font-size: 12px" align="left">
							<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>										
									<td style="font-size: 13px; text-align: center; width: 6%;">${item[0]}</td>									
									<td style="font-size: 13px; text-align: left; width: 12%;">${item[1]}</td>
									<td style="font-size: 13px; text-align: left; width: 8%;">${item[2]}</td>	
									<td >${item[9]}</td>									
									<td >${item[10]}</td>
									<td >${item[3]}</td>		
							</tr>
							</c:forEach>						
							</tbody>
					</table>
					</div>
				</div>
			</div>
			</div>
		
		
		<div class="card-body">
			<table class="col-md-11" >
				<tr>
			   		<td align="right" >
			   			<h5><u><b>Report Generated On <span id="t_date"> ${t}</span></b></u><br></h5>			   			
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
					<input type="button"   class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')">		
			</div>	
	</div>
	
<c:url value="search_conver_restruc_reorga_redes_actionlist1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="search1" name="search1" modelAttribute="type_of_report1">
		<input type="hidden" name="type_of_report1" id="type_of_report1" value=""/>
		<input type="hidden" name="ddlYears1" id="ddlYears1" value=""/>
		<input type="hidden" name="month1" id="month1" value=""/>
	</form:form> 

<script>
$(document).ready(function() {
	
	 $("#searchInput").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#SearchReport tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
			$("#SearchReport1 tr").filter(function() { 
				$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
		});
	 if('${list.size()}' ==  0){
		 	$("#btnhide").hide();
			$("table#SearchReport").append("<tr><td colspan='9' style='text-align :center;'>Data Not Available</td></tr>");
			$("table#SearchReport1").append("<tr><td colspan='9' style='text-align :center;'>Data Not Available</td></tr>");
		 } 
	 else{
		 $("#btnhide").show();
		 $("#printableArea").show();
	 }
	   
	getyear();
	var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();
    var t = dd+"-"+mm+"-"+yyyy; 
    $("#t_date").text(t);
   
    $("#type_of_report").val('${type_of_report1}');
	$("#month").val('${month1}');
	$("#ddlYears").val('${ddlYears1}');

	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
});

function Search(){
	var report_type=$("#type_of_report").val();
	var month=$("#month").val();
	var ddlYears=$("#ddlYears").val();
    
    if ($("#type_of_report").val() == "0") {
		alert("Please Enter Type of report.");
		return false;
	}
    if ($("#ddlYears").val() == "0") {
		alert("Please Enter Year.");
		return false;
	}
    if ($("#month").val() == "00") {
		alert("Please Enter Month.");
		return false;
	}
    $("input#type_of_report1").val($("#type_of_report").val());
    $("input#month1").val($("#month").val());
    $("input#ddlYears1").val($("#ddlYears").val());
    $("#WaitLoader").show();
    document.getElementById('search1').submit();
}
</script>
<script>
function getyear() 
{
        var ddlYears = document.getElementById("ddlYears");
        var currentYear = (new Date()).getFullYear();
        for (var i = 2000; i <= currentYear; i++) {
            var option = document.createElement("OPTION");
            option.innerHTML = i;
            option.value = i;
            ddlYears.appendChild(option);
        }
}
</script>

<script type="text/javascript">
var d = new Date();
var monthArray = new Array();
monthArray[0] = "--Select Month--"; 
monthArray[1] = "January";
monthArray[2] = "February";
monthArray[3] = "March";
monthArray[4] = "April";
monthArray[5] = "May";
monthArray[6] = "June";
monthArray[7] = "July";
monthArray[8] = "August";
monthArray[9] = "September";
monthArray[10] = "October";
monthArray[11] = "November";
monthArray[12] = "December";
for(m = 0; m <= 12; m++) {
    var optn = document.createElement("OPTION");
    optn.text = monthArray[m];
    if(m<=9)
    {
    	optn.value = "0"+(m);
    }
    else 
 	{
    	optn.value = (m);
 	}
    if ( m == 0) {
        optn.selected = true;
    }
    document.getElementById('month').options.add(optn);
}
</script>
