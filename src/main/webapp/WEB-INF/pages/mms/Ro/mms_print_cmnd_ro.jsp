<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> 

<h3 style="text-align: center; margin-bottom: 10px;">VIEW COMMAND'S RO</h3>
<div class="animated fadeIn" id="printableArea">
			<div class="card">
				<div class="card-header"> <div class="col-md-2"><img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 85px;"></div><div class="col-md-8"> <h3 style="text-decoration: underline; text-align: center;">COMMAND's RO Print</h3></div> <div class="col-md-2"><img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 85px;"> </div> </div>
				<div class="card-body card-block" id="mainView" style="display: block;" >
					<div class="col-md-12 phone">
					<div class="col-md-6">
						 <div class="row">
							<div class="col col-md-4" align="left">
								<label class=" form-control-label"><strong style="color: red;"> </strong>Phone :</label>
							</div>
							<div class="col-12 col-md-8" align="left">
							 	
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row " >
							<div class="col col-md-4" align="left">
								<label class=" form-control-label"><strong style="color: red;"> </strong>Office Name:</label>
							</div>
							<div class="col-12 col-md-8" align="left">
								<label id="depot_sus_no" ></label>
							</div>
						</div>
						<div class="row " >
							<div class="col col-md-4" align="left">
								<label class=" form-control-label"><strong style="color: red;"> </strong>Office Address:</label>
							</div>
							<div class="col-12 col-md-8" align="left">
								<label id="depot_sus_no" ></label>
							</div>
						</div>
					</div>
					</div>
					<div class="col-md-12 dated">
						  <div class="col-md-6">
						 <div class="row">
							<div class="col col-md-4" align="left">
								<label class=" form-control-label"><strong style="color: red;"> </strong>RO NO :</label>
							</div>
							<div class="col-12 col-md-8" align="left">
							 	${mms_cmnd_ro_approver_screen_viewCMD.ro_no}
							</div>
						</div>
						</div>
						<div class="col-md-6">
						<div class="row">
							<div class="col col-md-4" align="left">
								<label class=" form-control-label"><strong style="color: red;"> </strong>Dated:</label>
							</div>
							<div class="col-12 col-md-8" align="left">
								<label id="depot_sus_no" ></label>
							</div>
						</div>
						</div>
					  </div>
					  <div class="col-md-12">
					   <div class="col-md-6">
					 	<div class="row">
							<div class="col col-md-4" align="left">
								<label class=" form-control-label"><strong style="color: red;"> </strong>Issuing Depot :</label>
							</div>
							<div class="col-12 col-md-8" align="left">
							 	
							</div>
						</div>
						<div class="row">
							<div class="col col-md-4" align="left">
								<label class=" form-control-label"><strong style="color: red;"> </strong>Address :</label>
							</div>
							<div class="col-12 col-md-8" align="left">
							 	
							</div>
						</div>
						<div class="row">
							<div class="col col-md-4" align="left">
								<label class=" form-control-label"><strong style="color: red;"> </strong>Qty:</label>
							</div>
							<div class="col-12 col-md-8" align="left">
								<label id="quantity" >${mms_cmnd_ro_approver_screen_viewCMD.ro_qty} </label>
							</div>
						</div>
						</div> 
					</div>
						</div>
				</div>
				<div class="card" ><strong style="text-decoration: underline; text-align: center; margin: 15px;">ISSUE OF CONT STORES</strong>
				        <div class="col-md-12">
				         <div class="col-md-6">
				         <div class="row">
							<div class="col col-md-4" align="left">
								<label>1. Ref To : </label>
							</div>
							<div class="col-12 col-md-8" align="left">
							 	
							</div>
						</div>
				         </div>
				         </div>
				         <div class="col-md-12">
				         <div class="col-md-6">
				         <div class="row">
							<div class="col col-md-4" align="left">
								<label>2. </label>
							</div>
							<div class="col-12 col-md-8" align="left">
							 	
							</div>
						</div>
				         </div>	
				         </div>
							
					</div> 
			             
			
				
				
				  <div class="col s12" style="">
					<table id="AppTradeReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
						<thead style="background-color: #9c27b0; color: white;">
					<tr>
						<th style="font-size: 15px">Sr No.</th>	
						<th style="font-size: 15px">Item</th>
						<th style="font-size: 15px">Qty</th>
						<th style="font-size: 15px">IO No</th>
					
						
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		    </div>
			   
			
			<div class="card-body card-block" id="mainView" style="display: block;" > 
			   <div class="col-md-12"> 
                  <div class="col-md-6">
								<label class=" form-control-label">Copy To :</label>
				  </div>
				  <div class="col-md-6" align="center">
								<label class=" form-control-label">Sign Block</label>
								<label id="depot_sus_no" ></label>
				</div>
				  </div>
				  <div class="col-md-12">
				    <div class="col-md-6">
								<h6 class="mgo" style="margin-bottom:10px;">MGO/Em(IM)</h6>
							 	<h6 class="unit">(Unit)</h6>
					 </div>
					 </div>
				</div> 
			<div class="col-md-12"  align="right">
				<input type="hidden" id="count" name="count"> 
				<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')">
			</div>
			
	 </div>			
<script>
jQuery(document).ready(function() {
	
	var sus_no = '${tms_viewrio_masterCmd.sus_no}';
	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
		for ( var i = 0; i < j.length; i++) {
	 		$("#unit_name").text(j[i]);
	 	}
	});
	
	var c_sus_no = '${tms_viewrio_masterCmd.command_sus_no}';
	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:c_sus_no}, function(j) {
		for ( var i = 0; i < j.length; i++) {
	 		$("#command_sus_no").text(j[i]);
	 	}
	});
	
	var d_sus_no = '${tms_viewrio_masterCmd.depot_sus_no}';
	var dipo_name = ""; 
	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:d_sus_no}, function(j) {
		for ( var i = 0; i < j.length; i++) {
	 		$("#depot_sus_no").text(j[i]);
	 		dipo_name = j[i];
	 		
	 		$.post("generateRIONo?"+key+"="+value,{mct:mct,dipo_name:dipo_name}, function(j) {
	 			for ( var i = 0; i < j.length; i++) {
	 		 		$("#rio_no").text(j);
	 		 	}
	 		});
	 	}
	});
	
	var mct = '${tms_viewrio_masterCmd.mct}';
	$.post("getStdNomenList?"+key+"="+value,{mct:mct}, function(k) {
        for ( var l = 0; l < k.length; l++) {
                $("#mct_nomen").text(k[l]);
       }
	});   
	
	var inlieu_mct = '${tms_viewrio_masterCmd.inliuemct}';
	$.post("getStdNomenList?"+key+"="+value,{mct:inlieu_mct}, function(k) {
        for ( var l = 0; l < k.length; l++) {
                $("#inlieu_mct_nomen").text(k[l]);
       }
	});  
	
	

	    var RO_DATE = new Date('${tms_viewrio_masterCmd.issue_date}');
	    var month = RO_DATE.getMonth() + 1;
	    var day = RO_DATE.getDate();
	    var year = RO_DATE.getFullYear();
	    $("#date_of_submission").text(   day  + "-" +  month + "-" + year);
	
	    
	    var RIO_DATE = new Date('${tms_viewrio_masterCmd.approved_date}');
	    var month1 = RIO_DATE.getMonth() + 1;
	    var day1 = RIO_DATE.getDate();
	    var year1 = RIO_DATE.getFullYear();
	    $("#roi_date").text(   day1  + "-" +  month1 + "-" + year1);
	
	var d = new Date('${tms_viewrio_masterCmd.issue_date}') ;
	var valid_date = d.addMonths(3);
	$("#valid_up_to1").text(valid_date);
	

});


Date.prototype.addMonths = function (value) {
    var n = this.getDate();
    this.setDate(1);
    this.setMonth(this.getMonth() + value);
    this.setDate(Math.min(n, this.getDaysInMonth()));
    
    var datestring =    ("0" + this.getDate()).slice(-2) + "-" +  ("0"+(this.getMonth()+1)).slice(-2) + "-" + this.getFullYear() ; // + " " + ("0" + this.getHours()).slice(-2); + ":" + ("0" + this.getMinutes()).slice(-2) +":00.0";
    
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
	//let popupWinindow
    let innerContents = document.getElementById('printableArea').innerHTML;
    popupWindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWindow.document.open();
    popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"></head><body onload="window.print()">' + innerContents + '</html>');
    popupWindow.document.close();
}
</script>
<script>
$(document).ready(function() {
	var s= "${tms_viewrio_masterCmd.quantity}"
	$("table#addQuantity tbody").empty(); 
});
</script>

