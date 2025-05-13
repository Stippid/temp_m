<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script> 
    
<div class="animated fadeIn">
	<div class="row">
    	<div  class="col-md-12" align="right">
    		<button class="btn btn-default btn-xs" onclick="partA('${sus_no}');" style="background-color: #9c27b0; color: white;" title="MVCR PART A">MVCR PART A</button>
    		<button class="btn btn-default btn-xs" onclick="partB('${sus_no}');" style="background-color: #9c27b0; color: white;" title="MVCR PART B">MVCR PART B</button>
    		<button class="btn btn-default btn-xs" onclick="View_C('${sus_no}');" style="background-color: #9c27b0; color: white;" title="MVCR PART C">MVCR PART C</button>
    		<button class="btn btn-default btn-xs" onclick="View_E('${sus_no}');" style="background-color: #9c27b0; color: white;" title="E - ASSET">E - ASSET</button> 
    	</div>
    </div><br>
</div>
    
	<div class="animated fadeIn" id="printableArea">
		<div class="row">
	    	<div class="col-md-12" >
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
		   							<h5> MONTHLY VEHICLE CASUALTY RETURN - PART C</h5>
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
								<td style="width: 30%;" align="left"><label id="unit_name">${unit_name}</label></td>
							</tr>
							<tr style="font-size: 14px;">
								<td style="width: 25%;padding-left: 10%;"><label class=" form-control-label">MVCR as on</label></td>
								<td style="width: 25%;" align="left"> ${app}<label id="approve_date"></label></td>
								<td style="width: 20%;padding-left: 5%;"><label class=" form-control-label">Orbat</label></td>
								<td style="width: 30%;" align="left"><label>${fmn}</label></td>
							</tr>
							<tr style="font-size: 14px;">
								<td style="width: 25%;padding-left: 10%;"><label class=" form-control-label">Date of Last Return </label></td>
								<td style="width: 25%;" align="left">${modi}<label id="modify_date"></label></td>
								<td style="width: 20%;padding-left: 5%;"><label class=" form-control-label">Modification</label></td>
								<td style="width: 30%;" align="left"><label>${modification}</label> &ensp;|&ensp;<label class=" form-control-label">Location(NRS) : </label><label>&ensp;${loc_nrs}</label></td>
							</tr>
							<tr style="font-size: 14px;">
								<td style="width: 25%;padding-left: 10%;"><label for="text-input" class=" form-control-label">Status </label></td>
								<td style="width: 25%;" align="left"><label id="status">Approved</label></td>
								<td style="width: 20%;padding-left: 5%;"><label class=" form-control-label">WE/PE No. </label></td>
								<td style="width: 30%;" align="left"><label id="wep">${wep}</label></td>	
							</tr>
							<tr>
								<td style="width: 25%;padding-left: 10%;font-size: 13px;"><label class=" form-control-label">Issue Type<strong style="color: red;">*</strong> </label></td>
								<td>
									<select name="issue_type" id="issue_type" class="form-control-label" style="align: left;font-size: 13px;">
										<c:forEach var="item" items="${getMvcrpartCissuetypeList}" varStatus="num" >
											<option value="${item[0]}" >${item[1]}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							</tbody>
						</table>
		   			</div>  
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12" >
    			<div  class="watermarked" data-watermark="" id="divwatermark" >
				<span id="ip"></span>
					<table id="getPartCReportMvcr"class="table no-margin table-striped  table-hover  table-bordered report_print" style="margin-bottom: 0rem;">
						<thead>
							<tr>
								<th style="width: 3%">Ser No</th>						
								<th style="width: 8%">BA No</th>	
								<th style="width: 5%">MCT </th>	
								<th style="width: 45%">Nomenclature</th>
								<th style="width: 20%">Remark</th>
				            </tr>
						</thead>
						<tbody>
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
				<input type="button" class="btn btn-primary btn-sm" value="Print" onclick="printDiv('printableArea')">
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

<script>
$(document).ready(function() {
 	$('select#issue_type').change(function() {
		var ba_no = this.value;
		var issue_type = $("#issue_type").val();
		if(issue_type != "")
		{
			getSearchReportList();					 
		}			
	});  	
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

</script>

<script>
	function getSearchReportList(){
	
		 if($("#sus_no").val() == ""){
 			alert("Please Enter the sus_no");
 			$("#sus_no").focus();
 			return false;
 		} 
 		var tab = $("#getPartCReportMvcr > tbody");
	    tab.empty(); 
	    var sus_no = '${sus_no}';
	    var issue_type = $("#issue_type").val();
	    	   

			 	$.post("getMvcrpartCList?"+key+"="+value, {sus_no:sus_no,issue_type:issue_type}).done(function(data) {				
			 		drawTable1(data);
				}).fail(function(xhr, textStatus, errorThrown) {
				});
	    
	    function drawTable1(data) {
			if(data.length == 0)
			{
				var row = $("<tr />")
				$("#getPartCReportMvcr").append(row);
				row.append($("<td colspan='6' align='center'>Data Not Available...</td>"));
			}
			else{	
				$("div#divwatermark").val('').addClass('watermarked');	
				watermarkreport();
				var k = 0;
				for (var i = 0; i < data.length; i++) {
				 	var row = $("<tr />")
					k++;
					$("#getPartCReportMvcr").append(row);
					row.append($("<td style='width: 3%' align='center'>" + parseInt(k) + "</td>"));
					row.append($("<td style='width: 8%'>" + data[i] + "</td>"));
					row.append($("<td style='width: 5%'>" + data[i+1] + "</td>"));
					row.append($("<td style='width: 45%'>" + data[i+2] + "</td>"));
					
					if(data[i+3] == null){
						row.append($("<td style='width:20%'></td>"));
					}else{
						row.append($("<td style='width:20%'>" + data[i+3] + "</td>"));
					}
					i=i+4;
				}
				$('#getPartCReportMvcr').DataTable();
			}	   
		}	    
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