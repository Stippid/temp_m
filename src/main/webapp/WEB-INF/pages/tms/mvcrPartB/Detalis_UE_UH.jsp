<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
 
<div class="animated fadeIn">
	<div class="row">
    	<div  class="col-md-12" align="right">
    		<button class="btn btn-default btn-xs" onclick="openUESummary();" style="background-color: #9c27b0; color: white;" title="UE SUMMARY">UE SUMMARY</button>
    		<button class="btn btn-default btn-xs" onclick="partA('${sus_no}');" style="background-color: #9c27b0; color: white;" title="MVCR PART A">MVCR PART A</button>
    		<button class="btn btn-default btn-xs" onclick="partB('${sus_no}');" style="background-color: #9c27b0; color: white;" title="MVCR PART B">MVCR PART B</button>
    		<button class="btn btn-default btn-xs" onclick="View_C('${sus_no}');" style="background-color: #9c27b0; color: white;" title="MVCR PART C">MVCR PART C</button>
    		<button class="btn btn-default btn-xs" onclick="View_E('${sus_no}');" style="background-color: #9c27b0; color: white;" title="E - ASSET">E - ASSET</button> 
    	</div>
    </div><br>
</div>
 
	<div class="animated fadeIn" id="printableArea">
		<div class="row">
	    	<div class="col-md-12">
	    		<div class="card">
	    			<div class="card-header" align="center"> <strong style="text-decoration: underline;"> RESTRICTED</strong> </div>
	    				<div class="card-header"> 
			   				<table class="col-md-12">
			   				<tbody style="overflow: hidden;">
			   					<tr>
			   						<td align="left">
			   							<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
			   						</td>
			   						<td align="center">
			   							<!-- <h4 style="text-decoration: underline;"><b>DTE GENERAL INFO SYS</b></h4> -->
			   							<h5>MONTHLY VEHICLE CASUALTY RETURN - PART B</h5>
			   						</td>
			   						<td align="right">
			   							<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
			   						</td>
			   					</tr>
			   					</tbody>
			   				</table>
			   			</div> 
	       				<div class="card-body">
							<table class="col-md-12">
							<tbody style="overflow: hidden;">
								<tr style="font-size: 14px;">
									<td style="width: 25%;padding-left: 10%;"><label class="form-control-label">SUS No </label></td>
									<td style="width: 25%;" align="left">${sus_no}</td>
									<td style="width: 20%;padding-left: 5%;"><label class="form-control-label">Unit Name </label></td>
									<td style="width: 30%;" align="left"><label id="unit_name">${unit_name} </label></td>
								</tr>
								<tr style="font-size: 14px;">
									<td style="width: 25%;padding-left: 10%;"><label class=" form-control-label">MVCR as on</label></td>
									<td style="width: 25%;" align="left"><label>${app}</label></td>
									<td style="width: 20%;padding-left: 5%;"><label class=" form-control-label">Orbat</label></td>
									<td style="width: 30%;" align="left"><label>${fmn}</label></td>
								</tr>
								<tr style="font-size: 14px;">
									<td style="width: 25%;padding-left: 10%;"><label class=" form-control-label">Date of Last Return </label></td>
									<td style="width: 25%;" align="left"><label>${modi}</label></td>
									<td style="width: 20%;padding-left: 5%;"><label class=" form-control-label">Modification</label></td>
									<td style="width: 30%;" align="left"><label>${modification}</label> &ensp;|&ensp;<label class=" form-control-label">Location(NRS) : </label><label>&ensp;${loc_nrs}</label></td>
								</tr>
								<tr style="font-size: 14px;">
									<td style="width: 25%;padding-left: 10%;"><label for="text-input" class=" form-control-label">Status </label></td>
									<td style="width: 25%;" align="left"><label id="status">Approved</label></td>
									<td style="width: 20%;padding-left: 5%;"><label class=" form-control-label">WE/PE No.</label></td>
									<td style="width: 30%;" align="left"><label>${wep} </label></td>		
								</tr>
								</tbody>
							</table>
			   			</div> 
	            	</div>
				</div>
			</div>
			  <!--  NITIN V4 18/11/2022 -->  
	<div class="row">
				<div class="col-md-12" >
	    			<div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
						<table id="getpartBReportMvcr" class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;">
							<thead>
								<tr>
					                           <th rowspan="2" style="text-align: center;width:3%;">S No</th>
										       <th rowspan="2" style="text-align: center;width:6%;">MCT</th>
										        <th rowspan="2" style="text-align: center;width:12%;">Nomenclature</th>
										        <th rowspan="2"  style="text-align: center;width:8%;">UE</th>
												<th colspan="8"  style="text-align: center;width:64%;">UH</th>	
												<th rowspan="2" style="width:8%;">Total UH</th>
												<th rowspan="2" style="text-align: center;width:5%;">DEFI</th>
												<th rowspan="2" style="text-align: center;width:5%;">SURPLUS</th>
											</tr>
											<tr>
												<th style="text-align: center;">Vs UE</th>
												<th style="text-align: center;">Over UE</th>
												<th style="text-align: center;">Loan</th>
												<th style="text-align: center;">Sect Store</th>
												<th style="text-align: center;">ACSFP</th>
												<th style="text-align: center;">PBD</th>
												<th style="text-align: center;">Fresh Rel</th>
												<th style="text-align: center;">Gift</th>
											</tr> 
								
							</thead>
							<tbody>
											<c:if test="${partBMvcrDetails_UE_UHList.size() == 0}" >
												<tr>
													<td colspan="15" align="center" style="color: red;"> Data Not Available </td>
												</tr>
											</c:if>
											<c:forEach var="item" items="${partBMvcrDetails_UE_UHList}" varStatus="num" >
												<tr>
												     <td style="width:3%;" align="center"><b>${num.index+1}</b></td>
												     <td style="width:6%;font-size: 10px;"><b>${item[37]}</b></td>
												     <td style="width:12%;font-size: 10px;"><b>${item[0]}</b></td> <!--  -->
												     <td style="width:8%;text-align: center;">${item[6]}</td> <!-- UE -->
												     
													 <td style="width:8%;text-align: center;">${item[7]} / ${item[8]}</td> <!-- Again UE -->
													 <td style="width:8%;text-align: center;">${item[9]} / ${item[10]}</td> <!-- Over UE -->
													 <td style="width:8%;text-align: center;">${item[11]} / ${item[12]}</td> <!-- Loan -->
													 <td style="width:8%;text-align: center;">${item[13]} / ${item[14]}</td> <!-- Sector Store -->
													 <td style="width:8%;text-align: center;">${item[15]} / ${item[16]}</td>  <!-- ACSFP -->
													 <td style="width:8%;text-align: center;">${item[17]} / ${item[18]}</td> <!-- PBD -->
													 <td style="width:8%;text-align: center;">${item[19]}</td> <!-- Fresh Rel  -->
													 <td style="width:8%;text-align: center;">${item[20]} / ${item[21]}</td> <!-- Gift -->
													 
													 <td style="width:8%;text-align: center;"><b>${item[24]}</b></td>
													 <td style="width:5%;text-align: center;">${item[25]}</td> 
													 <td style="width:5%;text-align: center;">${item[26]}</td>
												</tr>
											</c:forEach>
									</tbody>
						</table>
						<table class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;">
										<tbody>
											<tr>
												<td colspan="3" style="width:21%;text-align: center;"><B>Total</B></td>
												<td align="left" style="width:8%;text-align: center;"><B>${sumUE}</B></td>
												
												<td align='left' style="text-align:center;width:8%;"><B>${againUE}</B></td>
												<td align='left' style="text-align:center;width:8%;"><B>${overUE}</B></td>
												<td align='left' style="text-align:center;width:8%;"><B>${loan}</B></td>
												<td align='left' style="text-align:center;width:8%;"><B>${sector}</B></td>
												<td align='left' style="text-align:center;width:8%;"><B>${acsfp}</B></td>
												<td align='left' style="text-align:center;width:8%;"><B>${pbd}</B></td>
												<td align='left' style="text-align:center;width:8%;"><B>${fresh_release}</B></td>
												<td align='left' style="text-align:center;width:8%;"><B>${gift}</B></td>
												
												<td align='left' style="width:8%;text-align:center;"><B>${totalUH}</B></td>
												<td align='left' style="text-align:center;width:5%;"><B></B></td>
												<td align='left' style="text-align:center;width:5%;"><B></B></td>
											</tr>
										</tbody>
									</table>
					</div>
				</div>
			</div>


			<div class="row">
				<div  class="col-md-12">
					<div class="card">
						<div class="card-body"> 
			   				<table class="col-md-12">
			   				<tbody style="overflow: hidden;">
			   					<tr>
			   						<td align="left" style="font-size: 15px">
			   							<label>
											Prepared By<br>
											Station<br>
											Date
										</label>	
			   						</td>
			   						<td align="center" style="font-size: 15px">
			   							<label>Checked  By  </label>
			   						</td>
			   						<td align="center" style="font-size: 15px">
			   							<label>Approved  By </label><br> 
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
			<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')" >
		</div>
		</div>
	</div>
</div>
	
<c:url value="MVCR_partA" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm1" name="viewForm1" modelAttribute="sus_no1">
	<input type="hidden" name="sus_no1" id="sus_no1" value="${sus_no}"/>
	<input type="hidden" name="unit_name1" id="unit_name1" value="${unit_name}"/>
</form:form> 

<c:url value="MVCR_partB" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewFormb" name="viewFormb" modelAttribute="sus_nob">
	<input type="hidden" name="sus_nob" id="sus_nob" value="${sus_no}"/>
	<input type="hidden" name="unit_nameb" id="unit_nameb" value="${unit_name}"/>
</form:form>   

<c:url value="MVCR_partC" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewFormc" name="viewFormc" modelAttribute="sus_noc">
	<input type="hidden" name="sus_noc" id="sus_noc" value="${sus_no}"/>
	<input type="hidden" name="unit_namec" id="unit_namec" value="${unit_name}"/>
</form:form> 

<c:url value="MVCR_E_Asset" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForme" name="viewForme" modelAttribute="sus_noe">
	<input type="hidden" name="sus_noe" id="sus_noe" value="${sus_no}"/>
	<input type="hidden" name="unit_namee" id="unit_namee" value="${unit_name}"/>
</form:form> 
	
	  <div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
	    <div class="modal-dialog">
	      <div class="modal-content">
	        <div class="modal-header">
	          <h4 class="modal-title">BA Number</h4>
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	        </div>
	        <div class="modal-body">
	        	<div class="form-group">	 
					<div class="col-md-12">			
						<div class="row" style="color: maroon; font-size: 16px; font-weight: bold;">
							<div class="col-sm-12">				 
					  			<input id="rejectid_model" name="rejectid_model" placeholder="" class="form-control" type ="hidden" >
								<table class="table no-margin table-striped  table-hover table-bordered report_print" id="addQuantity">
					              	<thead>
					                  	<tr>
					                  		<th>SR. No</th>
					                   		<th>BA No</th>
					                   		<th>Veh Cl</th>
					                	</tr>
				                	</thead>
				            		<tbody>
				            		</tbody>
				        		</table>
							</div>
						</div>
					</div>
				</div>		 
		        <div class="modal-footer">
		          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script>

var newWin;
function openUESummary(){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    newWin = window.open("viewstandardEntitlementForTransport_Trans", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
		//newWin.close();
	}
}
function closeWindow()
{
	newWin.close();   
}

$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
});

function Reject(mct,issue_type){
	var sus =  '${sus_no}'
	document.getElementById('rejectid_model').value=sus;
	var mct1 = mct;
	generate_ba_no(mct1,issue_type);
}
function generate_ba_no(mct1,issue_type){
	var sus_no =document.getElementById('rejectid_model').value; 
	$("table#addQuantity tbody").empty();
	
		   	$.post("generate_ba_no?"+key+"="+value, {sus_no:sus_no,mct:mct1,issue_type:issue_type}).done(function(k) {				
			var i =1;
			for ( var x = 0; x < k.length; x++) {
	    		$("table#addQuantity").append('<tr><td>'+i+'</td>'+'<td>'+k[x]+'</td>'+'<td>'+k[x+1]+'</td>'+'</tr>');
	    		x=x+1;
	    		i=i+1;
	       	}
		}).fail(function(xhr, textStatus, errorThrown) {
		});
}

function printDiv(divName) {
	let popupWinindow
	let innerContents = document.getElementById(divName).innerHTML;
	popupWinindow = window.open('', '_blank', 'width=600,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	popupWinindow.document.open();
	popupWinindow.oncontextmenu = function () {
		return false;
	}
	popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	//popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:8px;} table th{font-size:9px !important;} </style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
   	watermarkreport();
    popupWinindow.document.close();
}

function partA(sus_no){
	 document.getElementById('sus_no1').value = sus_no
	 document.getElementById('unit_name1').value=$("#unit_name").val();
	 document.getElementById('viewForm1').submit();
}
function partB(sus_no){
	document.getElementById('sus_nob').value = sus_no
	document.getElementById('unit_nameb').value=$("#unit_name").val();
	document.getElementById('viewFormb').submit();
}
function View_C(sus_no){
	document.getElementById('sus_noc').value = sus_no
	document.getElementById('unit_namec').value=$("#unit_name").val();
	document.getElementById('viewFormc').submit();
}
function View_E(sus_no){
	document.getElementById('sus_noe').value = sus_no
	document.getElementById('unit_namee').value=$("#unit_name").val();
	document.getElementById('viewForme').submit();
}

</script>
		
		