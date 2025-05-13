<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

	<div class="animated fadeIn">
		<div class="row">
    		<div  class="col-md-12" align="right">
    			<button class="btn btn-default btn-xs" onclick="partA('${sus_no}');" style="background-color: #9c27b0; color: white;" title="FMCR PART A">FMCR PART A</button>
    			<button class="btn btn-default btn-xs" onclick="sch('${sus_no}');" style="background-color: #9c27b0; color: white;" title="FMCR REPAIR SCH ">FMCR REPAIR SCH</button>
    			<button class="btn btn-default btn-xs" onclick="ViewD_B('${sus_no}');" style="background-color: #9c27b0; color: white;" title="FMCR PART B">FMCR PART B</button>
    			<button class="btn btn-default btn-xs" onclick="ViewA_Aset('${sus_no}');" style="background-color: #9c27b0; color: white;" title="E - ASSET">E - ASSET</button> 
    		</div>
    	</div><br>
	</div>
	<div class="animated fadeIn" id="printableArea">
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="card">
					<div class="card-header">
						<strong style="text-decoration: underline;"> RESTRICTED</strong>
					</div>
					<div class="card-header">  
	       				<table class="col-md-12">
	       				<tbody style="overflow: hidden;">
		   					<tr>
		   						<td align="left">
		   							<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;">
		   						</td>
		   						<td align="center">
		   						<h5>Four Monthly Census Return : PART A</h5>
		   						</td>
		   						<td align="right">
		   							<img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"> 
		   						</td>
		   					</tr>
		   					<tbody>
		   				</table>
	       			</div>
					<div class="card-body">
						<table class="col-md-12">
						<tbody style="overflow: hidden;">
								<tr style="font-size: 14px;">
									<td style="width: 25%;padding-left: 5%;" ><label class="form-control-label">SUS No </label></td>
									<td style="width: 25%;" align="left">${sus_no}</td>
									<td style="width: 25%;padding-left: 5%;" ><label class="form-control-label">Unit Name </label></td>
									<td style="width: 25%;" align="left"><label id="unit_name"></label></td>
								</tr>
								<tr style="font-size: 14px;">
									<td style="width: 25%;padding-left: 5%;" ><label class=" form-control-label">FMCR as on</label></td>
									<td style="width: 25%;" align="left"><label id="approve_date">${approve_date}</label></td>
									<td style="width: 25%;padding-left: 5%;" ><label class=" form-control-label">Date of Last Return </label></td>
									<td style="width: 25%;" align="left"><label id="modify_date">${modify_date}</label></td>	
								</tr>
								<tr style="font-size: 14px;">
									<td style="width: 25%;padding-left: 5%;" ><label for="text-input" class=" form-control-label">Status </label></td>
									<td style="width: 25%;" align="left"><label id="status">Approved</label></td>
									<td style="width: 25%;padding-left: 5%;" ><label class=" form-control-label">WE/PE No </label></td>
									<td style="width: 25%;" align="left"><label id="we_pe_no" >${we_pe_no} </label></td>	
								</tr>
								<tbody>
							</table>
					</div>
				</div>
			</div>
		</div>
	
	
		<div class="row animated fadeIn" >
			<div class="col-md-12" align="center">
				<div  class="watermarked" data-watermark="" id="divwatermark" >
					<span id="ip"></span>
					<table id="getpartAReportMcr"
						class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead style="text-align: center;">
							<tr>
								<th>Ser No</th>
								<th>BA No</th>
								<th>MCT</th>
								<th>Country of Origin</th>
								<th style="width: 10%;">Description</th>
								<th colspan="4">Vehicle Details</th>
								<th colspan="3">Engine Details</th>
								<th colspan="3">Aux Engine Details</th>
								<th colspan="3">Main Gun Type</th>
								<th>Section Gun Type</th>
								<th colspan="3">Main Radio Set</th>
								<th>Remarks</th>
							</tr>
							<tr>
								<th colspan="5"></th>
								<th>Cl</th>
								<th>Km Run during the Period</th>
								<th>Total Km Run</th>
								<th>Previous Tr Km Run</th>
								<th>Type</th>
								<th>Km Run</th>
								<th>Hrs Run</th>
								<th>Type</th>
								<th>Cl</th>
								<th>Hrs Run</th>
								<th>Type</th>
								<th>EFC</th>
								<th>QR</th>
								<th colspan="1"></th>
								<th>Nomenclature</th>
								<th>UH</th>
								<th>Condition</th>
								<th colspan="1"></th> 
							</tr>
						</thead>
						<tbody>
								<c:if test="${list.size() == 0}" >
									<tr>
										<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
									</tr>
								</c:if>
								<c:forEach items="${ReportListpartA}" var="list"  varStatus="num" >
									<tr>
									    <td style="width: 4%;">${num.index+1}</td> 
									  	<td style="text-align: center;">${list[0]}</td>
									    <td style="text-align: center;">${list[1]}</td>
									  
										<td style="text-align: center;">${list[3]}</td>	
										 <td style="text-align: center;width: 10%;">${list[2]}</td>									
										<td style="text-align: center;">${list[4]}</td>	
										<td style="text-align: center;">${list[5]}</td>
									    <td style="text-align: center;">${list[6]}</td>
										<td style="text-align: center;">${list[7]}</td>
										<td style="text-align: center;">${list[8]}</td>
										<td style="text-align: center;">${list[9]}</td>	
										<td style="text-align: center;">${list[10]}</td>
									    <td style="text-align: center;">${list[11]}</td>
										<td style="text-align: center;">${list[12]}</td>
										<td style="text-align: center;">${list[13]}</td>
										<td style="text-align: center;">${list[14]}</td>	
										<td style="text-align: center;">${list[15]}</td>
									    <td style="text-align: center;">${list[16]}</td>
										<td style="text-align: center;">${list[17]}</td>
										<td style="text-align: center;">${list[18]}</td>
										<td style="text-align: center;">${list[19]}</td>										
										<td style="text-align: center;">${list[20]}</td>
										<td style="text-align: center;">${list[21]}</td>

									</tr>
							</c:forEach>
						</tbody>
					</table>
	
				</div>
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
		   							<label>Checked  By   </label>
		   						</td>
		   						<td align="center" style="font-size: 15px">
		   							<label>Approved  By  </label><br> 
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
		<div class="">
			<div class="col-md-12" align="center">
				<div class="col-md-12" align="center">
					<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')">
				</div>
			</div>
		</div>
	</div>
	
	
	<c:url value="getmcrReportListpartA" var="viewUrl" />
	<form:form action="${viewUrl}" method="post" id="viewForm1" name="viewForm1" modelAttribute="sus_noa">
		<input type="hidden" name="sus_noa" id="sus_noa" value="${sus_no}"/>
		<input type="hidden" name="unit_namea" id="unit_namea" value="${unit_name}"/>
	</form:form>
	  
	<c:url value="getRepairReport" var="schUrl1"/>
	<form:form action="${schUrl1}" method="post" id="viewForm2" name="viewForm2" modelAttribute="sus_nor">
		<input type="hidden" name="sus_nor" id="sus_nor" value="${sus_no}"/>
		<input type="hidden" name="unit_namer" id="unit_namer" value="${unit_name}"/>
	</form:form>
	
	<c:url value="getdetails_ue_uhMvcr" var="viewUrl" />
		<form:form action="${viewUrl}" method="post" id="viewFormD_B" name="viewFormD_B" modelAttribute="sus_nob">
		<input type="hidden" name="sus_nob" id="sus_nob" value="${sus_no}"/>
		<input type="hidden" name="unit_nameb" id="unit_nameb" value="${unit_name}"/>
	</form:form>
	
	<c:url value="getdetailsE_AssetsMvc" var="viewUrl" />
		<form:form action="${viewUrl}" method="post" id="viewFormE_Asset" name="viewFormE_Asset" modelAttribute="sus_noe">
		<input type="hidden" name="sus_noe" id="sus_noe" value="${sus_no}"/>
		<input type="hidden" name="unit_namee" id="unit_namee" value="${unit_name}"/>
	</form:form>
	<script type="text/javascript">
	$(document).ready(function() {
	
		
	
		var sus_no = '${sus_no}';
	   	var unit_name = '${unit_name}';  
	   	$("#sus_no").text(sus_no);
	   	$("#unit_name").text(unit_name);
	 
	
	   	$("div#divwatermark").val('').addClass('watermarked');	
		watermarkreport();
			
		});
			
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
		
					
					var sus_no = '${sus_no}';
				   	var unit_name = '${unit_name}';  
				   	
				   	if(unit_name == "" || unit_name == "undefined"){
						$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {
							var length = j.length-1;
				        	var enc = j[length].substring(0,16);
				        	$("#unit_name").val(dec(enc,j[0]));
						}).fail(function(xhr, textStatus, errorThrown) {
						});

				   	}
	
	
		function ParseDateColumn(s, id) {
			var d = new Date(s);
			var date = ("0" + d.getDate()).slice(-2) + "-" + ("0" + (d.getMonth() + 1)).slice(-2) + "-" + d.getFullYear()  ;
			if (date == "aN-aN-NaN")
				date = "";
			else if (date == "01-01-1990")
				date = "";
			$("#"+id).text(date);
		}
	
	
 	function partA(sus_no) {
 		document.getElementById('sus_noa').value = sus_no
		document.getElementById('unit_namea').value ='${unit_name}';
		document.getElementById('viewForm1').submit();
	}
	function sch(sus_no) {
		document.getElementById('sus_nor').value = sus_no
		document.getElementById('unit_namer').value = '${unit_name}';
		document.getElementById('viewForm2').submit();
	}
	function ViewD_B(sus_no){
		document.getElementById('sus_nob').value = sus_no
		document.getElementById('unit_nameb').value = $("#unit_name").val();
		document.getElementById('viewFormD_B').submit();
	}

	function ViewA_Aset(sus_no){
		document.getElementById('sus_noe').value = sus_no
		document.getElementById('unit_namee').value = $("#unit_name").val();
		document.getElementById('viewFormE_Asset').submit();
	}
</script>	