<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> 


<div class="animated fadeIn" id="printableArea">
	<div class="">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header"> 
	   				<table class="col-md-12">
	   				<tbody style="overflow: hidden;">
	   					<tr>
	   						<td align="left">
	   							<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
	   						</td>
	   						<td align="center">
	   							<strong style="text-decoration: underline;"> 
									<h4>DETAILS OF GENERATED OF RIO</h4> 
									MANAGEMENT INFORMATION SYSTEM ORGANISATION <br> RELEASE ISSUE ORDER  (VALID UP TO - <label id="valid_up_to1" style="text-decoration:  underline;"></label>) 
								</strong>
	   						</td>
	   						<td align="right">
	   							<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
	   						</td>
	   					</tr>
	   						</tbody>
	   				</table>
	   			</div> 
				<div class="card-body" id="mainView" style="display: block;" >
					<table class="col-md-12" border="2">
					<tbody style="overflow: hidden;">
						<tr >
							<th style="width: 20%;">
								<label class=" form-control-label">RIO NO :</label>
							</th>
							<td style="width: 30%;">
								${tms_viewrio_masterCmd.rio_no}
							</td>
							<th style="width: 20%;">
								Depot:
							</th>
							<td style="width: 30%;">
								<label id="depot_sus_no" >${depot_sus_no}</label>
							</td>
						</tr>
						<tr >
							<th style="width: 20%;">
								<label class=" form-control-label">Type of Issue:</label>
							</th>
							<td style="width: 30%;">
								<c:forEach var="item" items="${getMvcrpartCissuetypeList}" varStatus="num" >
									<c:if test="${item[0] == tms_viewrio_masterCmd.type_of_issue}">
										${item[1]}
									</c:if>
								</c:forEach>
							</td>
							<th style="width: 20%;">
								<label class=" form-control-label">Command :</label>
							</th>
							<td style="width: 30%;">
								<label id="command_sus_no" >${command_sus_no}</label>
							</td>
						</tr>
						<tr >
							<th style="width: 20%;">
								<label class=" form-control-label">SUS NO:</label>
							</th>
							<td style="width: 30%;">
								<label  id="sus_no" >${tms_viewrio_masterCmd.sus_no} </label>
							</td>
							<th style="width: 20%;">
								<label class=" form-control-label">Unit Name :</label>
							</th>
							<td style="width: 30%;">
								<label id="mct">${unit_name}</label>
							</td>
						</tr>
						<tr >
							<th style="width: 20%;">
								<label class=" form-control-label">In Lieu MCT :</label>
							</th>
							<td style="width: 30%;">
								<label id="inliuemct" >${tms_viewrio_masterCmd.inliuemct}</label>
							</td>
							<th style="width: 20%;">
								<label class=" form-control-label">Description:</label>
							</th>
							<td style="width: 30%;">
								<label id="mct_nomen">${mct_nomen}</label>
							</td>
						</tr>
						<tr >
							<th style="width: 20%;">
								<label class=" form-control-label">Class:</label>
							</th>
							<td style="width: 30%;">
								<label id="class_vehicle" >${tms_viewrio_masterCmd.class_vehicle}</label>
							</td>
							<th style="width: 20%;">
								<label class=" form-control-label">In Lieu MCT Desc:</label>
							</th>
							<td style="width: 30%;">
								<label id="inlieu_mct_nomen" >${inlieu_mct_nomen}</label>
							</td>
						</tr>
						<tr >
							<th style="width: 20%;">
								<label class="form-control-label">Auth Of AHQ RO NO:</label>
							</th>
							<td style="width: 30%;">
								<label id="ro_no" >${tms_viewrio_masterCmd.ro_no} </label>
							</td>
							<th style="width: 20%;">
								<label class="form-control-label">Accounting:</label>
							</th>
							<td style="width: 30%;">
								<label id="accounting">${tms_viewrio_masterCmd.accounting} </label>
							</td>
						</tr>
						<tr >
							<th style="width: 20%;">
								<label class=" form-control-label"> RO Date:</label>
							</th>
							<td style="width: 30%;">
								<label id="date_of_submission" ></label>
							</td>
							<th style="width: 20%;">
								<label class=" form-control-label">NRS:</label>
							</th>
							<td style="width: 30%;">
								<label  id="nrs">${nrs} </label>
							</td>
						</tr>
						<tr >
							<th style="width: 20%;">
								<label class=" form-control-label">Issue Stx:</label>
							</th>
							<td style="width: 30%;">
								<label id="issue_stock" >${tms_viewrio_masterCmd.issue_stock}</label>
							</td>
							<th style="width: 20%;">
								<label class=" form-control-label">Issue Precedence:</label>
							</th>
							<td style="width: 30%;">
								<label  id="issue_precedence" > ${tms_viewrio_masterCmd.issue_precedence}</label>
							</td>
						</tr>
						<tr >
							<th style="width: 20%;">
								<label class=" form-control-label">RIO Issue Date:</label>
							</th>
							<td style="width: 30%;">
								<label id="rio_date" ></label>
							</td>
							<th style="width: 20%;">
								<label class=" form-control-label">Qty:</label>
							</th>
							<td style="width: 30%;">
								<label id="quantity" >${tms_viewrio_masterCmd.quantity} </label>
							</td>
						</tr>
						</tbody>
					</table> 
				</div>
				<div class="card-body">
					<table class="col-md-12">
					<tbody style="overflow: hidden;">
						<tr>
							<td style="width: 100%;" align="center">
								<label style="text-decoration:  underline;">NOTES</label>
							</td>
						</tr>
						<!-- <tr >
							<td style="width: 100%;">
								&emsp; 1. UNITS SHOULD DESPATCH ESCORTS FOR COLLECTION OF VEHICLE(S) ONLY ON RECIEPT OF CALL LETTER FROM DEPOT
							</td>
						</tr>
						<tr >
							<td style="width: 100%;">
								&emsp; 2. ISSUE ACTION BY THE DEPOTS TO BE COMPLETED WITH IN 30 DAYS FROM THE DATE ISSUE APPROVED
							</td>
						</tr> -->
						<tr >
							<td style="width: 100%;">
								&emsp; 1. The Provisional BA No. of the Veh Issued to the Unit are : &emsp; <label id="baLabel"><b>${tms_viewrio_masterCmd.ba_no}</b></label>
							</td>
						</tr>
						<tr>
							<td style="width: 100%;" align="center">
								<label style="text-decoration:  underline;">DISTRIBUTION</label>
							</td>
						</tr>
						<tr>
							<td style="width: 100%;" >
								&emsp; 1. VEHICLE DEPOT-1 COPY 
							 	&emsp; 2. UNIT-1 COPY 
							 	&emsp; 3. MGS(EM/IM)-1 COPY 
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>       
        </div>	            							
	</div> 			 
</div>	
<div class="animated fadeIn">
	<div class="" >
		<div class="container" align="center">
			<div class="col-md-12"  align="center">
				<input type="hidden" id="count" name="count"> 
				<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')">
			</div>
		</div>
	</div>
</div>
<script>
jQuery(document).ready(function() {
	

	$("#unit_name").val('${unit_name}');
	$("#command_sus_no").val('${command_sus_no}');
	$("#depot_sus_no").val('${depot_sus_no}');
	$("#mct_nomen").val('${mct_nomen}');
	$("#inlieu_mct_nomen").val('${inlieu_mct_nomen}');

	    var RO_DATE = new Date('${tms_viewrio_masterCmd.issue_date}');
	    var month = RO_DATE.getMonth() + 1;
	    var day = RO_DATE.getDate();
	    var year = RO_DATE.getFullYear();
	    $("#date_of_submission").text(   day  + "-" +  month + "-" + year);
	    
	    var RIO_DATE = new Date('${tms_viewrio_masterCmd.approved_date}');
	    var month1 = RIO_DATE.getMonth() + 1;
	    var day1 = RIO_DATE.getDate();
	    var year1 = RIO_DATE.getFullYear();
	    $("#rio_date").text(   day1  + "-" +  month1 + "-" + year1);
	
		var d = new Date('${tms_viewrio_masterCmd.issue_date}') ;
		var valid_date = d.addMonths(3);
		$("#valid_up_to1").text(valid_date);
});

var mct = '${tms_viewrio_masterCmd.mct}';

Date.prototype.addMonths = function (value) {
    var n = this.getDate();
    this.setDate(1);
    this.setMonth(this.getMonth() + value);
    this.setDate(Math.min(n, this.getDaysInMonth()));
    
    var datestring =    ("0" + this.getDate()).slice(-2) + "-" +  ("0"+(this.getMonth()+1)).slice(-2) + "-" + this.getFullYear() ; 
    
    return datestring;
};
Date.isLeapYear = function (year) { 
    return (((year % 4 === 0) && (year % 100 !== 0)) || (year % 400 === 0)); 
};

Date.getDaysInMonth = function (year, month) {
    return [31, (Date.isLeapYear(year) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][month];
};

Date.prototype.isLeapYear = function () { 
    return Date.isLeapYear(this.getFullYear()); 
};

Date.prototype.getDaysInMonth = function () { 
    return Date.getDaysInMonth(this.getFullYear(), this.getMonth());
};


function printDiv(divName) {
	let popupWinindow
    let innerContents = document.getElementById('printableArea').innerHTML;
    popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWinindow.document.open();
    popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()">' + innerContents + '</html>');
    popupWinindow.document.close();
}
</script>
<script>
$(document).ready(function() {

});
</script>

