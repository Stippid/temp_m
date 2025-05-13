<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css"> 

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>

<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<%
	String nPara=request.getParameter("Para");

%>
<form:form action="" method="post" enctype="multipart/form-data" class="form-horizontal"  >
  <div class="nkpageland" id="printableArea">
			<div onFocus="parent_disable1();" onclick="parent_disable1();">
				<div class="container" align="center" id="headerView"
					style="display: block;">
					<div class="card">
						<div class="card-header mms-card-header">
							<b> 
							<% if (nPara.equalsIgnoreCase("DIR")) {%> 
								VALIDATION OF DIR UPLOAD 
							<%	} %> 
							<% 	if (nPara.equalsIgnoreCase("DRR")) { %>
								VALIDATION OF DRR UPLOAD 
							<% 	} %> 
							<% 	if (nPara.equalsIgnoreCase("MSR")) { %>
								VALIDATION OF MSR UPLOAD 
							<% 	} %>
							</b>
						</div>
						<div class="card-body card-block">
							<div class="col-md-12 row form-group">
								<div class="col-md-3" style="text-align: left;">
									<label for="text-input" class=" form-control-label" style="margin-left: 13px;">Unit Name</label>
								</div>
								<div class="col-md-9">
									<input type="text" id="unit_name1" name="unit_name1" class="form-control-sm form-control" placeholder="Search..." autocomplete="off">
									<input type="hidden" id="sus_no1" name="sus_no" />
								</div>
							</div>
							<div class="col-md-12 row form-group">
								<div class="col-md-3" style="text-align: left;">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Upload Date From</label>
								</div>
								<div class="col-md-3">
									<input type="date" id="upload_date" name="upload_date" placeholder="" class="form-control-sm form-control" autocomplete="off">
								</div>
								<div class="col-md-3" style="text-align: left;">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Date To</label>
								</div>
								<div class="col-md-3">
									<input type="date" id="to_date" name="to_date" placeholder="" class="form-control-sm form-control" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="card-footer" align="center">
							<input type="button" class="btn btn-success btn-sm" value="Search" onclick="isValid();">
						</div>
					</div>
				</div>
			</div>
			<% if (nPara.equalsIgnoreCase("DIR")) {	%>
				<div id ="tdheaderViewDIR" style="display: none;" align="center" class="nrTableHeading"> <b>VIEW DIR UPLOAD</b> </div>
			<%	}  %>
			<%if (nPara.equalsIgnoreCase("DRR")) { %>
				<div id ="tdheaderViewDRR" style="display: none;" align="center" class="nrTableHeading"> <b>VIEW DRR UPLOAD</b> </div>
			<%	}  %>
			<%if (nPara.equalsIgnoreCase("MSR")) { %>
				<div id ="tdheaderViewMSR" style="display: none;" align="center" class="nrTableHeading"> <b>VIEW MSR UPLOAD</b> </div>
			<%	}  %>
    		
    		<div class="card" id="unit_hid2" style="display: none;background: transparent;">
				<table id="SearchViewtr">
					<tbody style="overflow: hidden;">
						<tr style="width: 100%;">
							<!-- <td class="nrTableLineData" colspan="1">
								<b>&nbsp;<input type=checkbox id='nSelAll' name='tregn'	onclick='setselectall();'>&nbsp; Select all [ <b></b><span id="tregn" style='font-size: 16px;'>0</span>/<span id='nTotRow1'>0</span></b> ]
							</td> -->
							<td class="nrTableMain2Search" colspan='1'>
								<label>Search in Result(<span id="ntotln"></span>) </label>&nbsp;:&nbsp; <input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal; font-size: 14px;" autocomplete="off">
							</td>
						</tr>
					</tbody>
				</table>
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<div id="" class="nrTableMainDiv">
						<!-- <input type="hidden" id="selectedid" name="selectedid"> 
						<input type="hidden" id="statushid" name="statushid">
						<input type="hidden" id="nrSrcSel" name="nrSrcSel"> -->
						<div class="nrTableDataDiv">
						<% if (nPara.equalsIgnoreCase("DIR") || nPara.equalsIgnoreCase("DRR")) {	%>
							<table border="1" width="100%">
								<thead style="text-align: center;">
									<tr>
										<th width="15%">Upload Date</th>
										<th width="15%">Sus No</th>
										<th width="15%">Unit name</th>
										<th width="15%">Total Qty</th>
										<th width="15%">RV No</th>
										<th width="15%">RV Date</th>
									</tr>
								</thead>
								<tbody id="nrTable">
									<c:if test="${m_1[0][0] == 'NIL'}">
										<tr class='nrSubHeading'>
											<td colspan='8' style='text-align: center;'>Data Not Available...</td>
											<% ntotln = 0; %>
										</tr>
									</c:if>
									<c:if test="${m_1[0][0] != 'NIL'}">
										<c:forEach var="item" items="${m_1}" varStatus="num">
											<tr class="nrTableLineData" id="NRRDO" name="NRRDO">
												<td><%-- <input class="nrCheckBox" type="checkbox" id="${item[1]}:${item[4]}" name="n" onclick="setid('${item[1]}','${item[4]}')">&nbsp; --%> ${item[0]}</td>
												<td style="text-align: center;">${item[1]}</td>
												<td>${item[2]}</td>
												<td style="text-align: center;">${item[3]}</td>
												<td style="text-align: center;">${item[4]}</td>
												<td>${item[5]}</td>
												<% ntotln++; %>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<%	}  %>
							<% if (nPara.equalsIgnoreCase("MSR")) {	%>
							<table border="1" width="100%">
								<thead style="text-align: center;">
									<tr>
										<th>Unit Name</th>
										<th>Census No</th>
										<th>Stk Type</th>
										<th>Nomen</th>
										<th>Total Free Stock Held</th>
										<th>Recd Stk DGOF</th>
										<th>Recd Stk Trade Import</th>
										<th>Recd Stk Repair</th>
										<th>Recd Stk Other</th>
										<th>Qty Issued</th>
										<th>Qty UnderIssue</th>
										<th>Vintage Held</th>
										<th>Res Held</th>
										<th>Res Type</th>
										<th>RO Qty</th>
										<th>RIO Qty</th>
										<th>Dues In</th>
									</tr>
								</thead>
								<tbody id="nrTable">
									<c:if test="${m_1[0][0] == 'NIL'}">
										<tr class='nrSubHeading'>
											<td colspan='17' style='text-align: center;'>Data Not Available...</td>
											<% ntotln = 0; %>
										</tr>
									</c:if>
									<c:if test="${m_1[0][0] != 'NIL'}">
										<c:forEach var="item" items="${m_1}" varStatus="num">
											<tr class="nrTableLineData" id="NRRDO" >
												<td>${item[0]}</td>
												<td>${item[1]}</td>
												<td>${item[2]}</td>
												<td>${item[3]}</td>
												<td>${item[4]}</td>
												<td>${item[5]}</td>
												<td>${item[6]}</td>
												<td>${item[7]}</td>
												<td>${item[8]}</td>
												<td>${item[9]}</td>
												<td>${item[10]}</td>
												<td>${item[11]}</td>
												<td>${item[12]}</td>
												<td>${item[13]}</td>
												<td>${item[14]}</td>
												<td>${item[15]}</td>
												<td>${item[16]}</td>
												<% ntotln++; %>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
							<%	}  %>
						</div>
					</div>
				</div>
				<div class="card-footer" align="right">
					<input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;" onclick="exportData('.nrTableDataDiv');"> 
					<input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();"> 
					<!-- <input type="button" class="btn btn-success btn-sm" id="convali" value="Confirm Validation" onclick="return setUpdateStatus();"> -->
				</div>
			</div>
 		</div>
	</form:form>

<c:url value="UploadExcelList" var="backUrl" />
<form:form action="${backUrl}" method="post" id="up_ex" name="up_ex" modelAttribute="m1_sus">
      <input type="hidden" name="m1_sus" id="m1_sus"/>
      <input type="hidden" name="m1_unit" id="m1_unit"/>
      <input type="hidden" name="m1_para" id="m1_para"/>
      <input type="hidden" name="m1_from" id="m1_from"/>
      <input type="hidden" name="m1_to" id="m1_to"/>
</form:form> 
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" src="js/cue/cueWatermark.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
	


<script>
$(document).ready(function() {
	
	$("div#divwatermark").val('').addClass('watermarked');        
    watermarkreport();
	
	
	$().getCurDt(to_date);
	$().getFirDt(upload_date);
	
	var y1 = '${m_1[0][0]}';

	if(y1 != "" || '${m_1[0]}'.length > 0){
		$("#unit_hid2").show();
		$("#sus_no1").val('${m_2}');
		$("#unit_name1").val('${m_3}');
		$("#upload_date").val('${m_4}');
		$("#to_date").val('${m_5}');
		//nrSetWaterMark(1000);
		$("#ntotln").text(<%=ntotln%>);
	}
	
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#nrTable tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
});
</script>
<script>
$("#unit_name1").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";	
	$().Autocomplete2('POST','getMMSRList',unit_name1,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',sus_no1);
});

	function printDiv() {
		$('.nrCheckBox').css('display', 'none');
		$("#headerView").hide();
		$("#SearchViewtr").hide();
		$("#tdheaderViewDIR").show();

		$("#btn_e").hide();
		$("#btn_p").hide();
		$("#btn_upd").hide();
		$("#heder").hide();
		$("#convali").hide();
		$("#selectView").hide();

		//let popupWinindow;
		let innerContents = document.getElementById('printableArea').innerHTML;
		popupWindow = window.open('','_blank','width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWindow.document.open();
		popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'	+ innerContents + '</html>');
		popupWindow.document.close();

		$("#headerView").show();
		$("#SearchViewtr").show();
		$("#btn_e").show();
		$("#btn_p").show();
		$("#btn_upd").show();
		$("#convali").show();
		$('.nrCheckBox').css('display', 'block');
		$("#selectView").show();
		$("#tdheaderViewDIR").hide();
	}

	function nrSetWaterMark(pline) {
		getUserIP(function(ip) {
			var roleid = "${roleid}";
			var username = "${username}";
			var userid = "${userId}";
			var ab = "";

			var today = new Date();
			var date = today.getDate() + '-' + (today.getMonth() + 1) + '-'
					+ today.getFullYear();
			var time = today.getHours() + ":" + today.getMinutes() + ":"
					+ today.getSeconds();
			var currentDate = date + ' ' + time;

			abip = "Generated by " + username + " on " + currentDate
					+ " with IP-" + ip;
			for (var i = 0; i < pline * 1.5; i++) {
				ab = ab + abip + " ";
			}
			$(".nrWatermarkBase p").text(ab);
		});
	}

	function exportData(b) {
		$().tbtoExcel(b);
		b.preventDefault();
	}
	function setid(a, st) {
		$("#selectedid").val(a);
		$("#statushid").val(st);
		$("#nrSrcSel").val(a);
		$("#NRRDOO" + a).attr("background-color", "yellow");
	}

	function isValid() {
		if ($("#upload_date").val() == "") {
			alert("Please Enter Upload Date");
			$("#upload_date").focus();
			return false;
		}
		if ($("#to_date").val() == "") {
			alert("Please Enter Date To");
			$("#to_date").focus();
			return false;
		}
		var d = new Date();
		var c_d = d.getFullYear() + "-" + ("0" + (d.getMonth() + 1)).slice(-2)+ "-" + ("0" + d.getDate()).slice(-2);
		if ($("#to_date").val() > c_d) {
			$("#to_date").focus();
			alert("Can't select the Future Date");
			return false;
		}
		getuploadvalidateList();
		return true;
	}

	function getuploadvalidateList() {
<% if (nPara.equalsIgnoreCase("DIR")) { %>
           var paraA = "DIR";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("DRR")) { %>
	       var paraA = "DRR";
	<% }  %>
	<% if (nPara.equalsIgnoreCase("MSR")) { %>
	       var paraA = "MSR";
	<% }  %>
	
	$("#m1_sus").val($("#sus_no1").val());
	$("#m1_unit").val($("#unit_name1").val());
	$("#m1_para").val(paraA);
	$("#m1_from").val($("#upload_date").val());
	$("#m1_to").val($("#to_date").val());
	$("#nrWaitLoader").show();
	$("#up_ex").submit();	
}

function setselectall(){
	if($("#nSelAll").prop("checked")){
		$(".nrCheckBox").prop('checked', true);
		alert("Checked");
	}else{
		$(".nrCheckBox").prop('checked', false);
		alert("UnChecked");
	}
	findselected();
}

function findselected(){
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		$(this).attr('background-color','yellow');
		return $(this).attr('id');
	}).get();
		
	var b=nrSel.join(',');
	$("#nrSrcSel").val(b);
	$('#tregn').text(nrSel.length);
}

/* function setUpdateStatus(){
	findselected();
	var a = document.getElementById("nrSrcSel").value;
	
	if(a == ""){
		alert("Please Select the Data...");
	}else{
		$.ajax({
			type: 'POST',
	        url: 'ConfirmValidDir?'+key+'='+value,
	        data: {a:a}, 
	        success: function(response) {
	        	alert(response);
	        }
	    });
	}	
} */
</script>