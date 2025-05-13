<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcssp.css">
<link rel="stylesheet" href="js/common/commonmethod.js">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script>
var username="${username}";
var curDate = "${curDate}";
function nkprint(nrdiv) {
	
	var nra=$("#"+nrdiv).html();
	var nrl=$("#nrlinks").html();
	popupWindow=window.open('',200,300);
	popupWindow.document.write(nrl+nra);
	popupWindow.window.print();
	popupWindow.document.close();
}
</script>
<!-- <div align="right">
	<a href="javascript:window.print();">Print</a>
</div> -->
<div class="nkpageland" id="printableArea">
	<div id="nrlinks">
		<link rel="stylesheet" href="js/common/nrcssp.css">
	</div>

	<table>
		<tr>
			<td align="center"><img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="width: 30px; height: 30px;"></td>
			<td colspan='11' align="center" class="nrTableHeading">MANAGEMENT INFORMAITON SYSTEMS ORGANISATION (MISO)<BR>MATERIAL MANAGEMENT SYSTEM(MMS)<BR>UE-UH SUMMARY : <span id="nunitname"></span> </td>
			<td align="center"><img src="js/miso/images/dgis-logo.png" style="width: 60px; height: 30px;"></td>
		</tr>
		<tr>
			<td colspan='13'>
		</tr>
		<tr style='font-weight: bold;'>
			<td colspan='8'>SUS No: <span id="nsusno"></span></td>
			<td colspan='5' style="text-align: right;">Last Updated: <span id="nlastpd"></span></td>
		<tr style='font-weight: bold;'>
			<td colspan='10'>ORBAT: <span id="nhqname"></span></td>
				<!-- <tr style='font-weight: bold;'><td colspan='10'>ORBAT: <span id="nhqname"></span></td> 	 -->
		</tr>
	</table>
	<div class="watermarked" data-watermark="" id="divwatermark">
		<span id="ip"></span>
		<table id="ro_view" class="table_collapse nrTableDataHead" border="1"
			width="100%">
			<thead>
				<div class="nrWatermarkBase">
					<p>UE-UH Summary</p>
				</div>
				<tr style="text-align: center">
					<th width="7%">PRF Group</th>
					<th width="30%">Nomenclature</th>
					<th width="21%">Types of Holding</th>
					<th width="8%">UE</th>
					<th width="8%">UH</th>
					<th width="8%"><span id="nstat">Sur/Defi</span></th>
					<!-- <th width="15%">Remarks</th> -->
			</thead>
			<tbody id="nrTablee">
				<c:if test="${m_1[0][0] == 'NIL'}">
					<tr class='nrSubHeading'>
						<td colspan='13' style='text-align: center;'>Data Not
							Available...</td>
					</tr>
				</c:if>
				<% String temp = "";%>
				<c:if test="${m_1 != 'NIL'}">
					<c:forEach var="item" items="${m_1}" varStatus="num">
						<tr style='font-size: 12px' id='NRRDO' name='NRRDO'>
							<c:if test="${item[0] != ''}">
								<c:if test="${temp != item[0]}">
									<tr class="nrGroupHeading">
										<td colspan='8' style='text-align: left;'>PRF
											Group&nbsp;:&nbsp;${item[0]}</td>
										<c:set var="temp" value="${item[0]}"></c:set>
									</tr>
								</c:if>
								<tr class='nrTableLineData'>
									<td width="7%"></td>
									<td width="30%">${item[1]}</td>
									<td width="21%">${item[2]}(${item[3]})</td>
									<%-- <td style='text-align: center;' width="8%">${item[4]}</td> --%>
									<td style='text-align: center;' width="8%;"><fmt:formatNumber value="${item[4]}" maxFractionDigits="2" minFractionDigits="2" /></td>
									<td style='text-align: center;' width="8%">${item[5]}</td>
									<td style='text-align: center;' width="8%">${item[9]}</td>
									<!-- <td width="15%"></td> -->
								</tr>
							</c:if>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</div>
</div>

<div class="col-md-12" align="right">
	<input type="hidden" id="count" name="count"> <input
		type="button" class="btn btn-primary btn-sm" value="Print"
		onclick="nkprint('printableArea')">
</div>


<script>
$(document).ready(function() {   
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var nPara='${m_3}';
	if (nPara=="ALL") {
		$("#nParaDetails").text("UE UH SUMMARY");
		$("#nstat").text("Sur/Defi");		
	}
	if (nPara=="DEFI") {
		$("#nParaDetails").text("UE UH SUMMARY (DEFI ONLY)");
		$("#nstat").text("Defi");
	} 
	if (nPara=="SURP") {
		$("#nParaDetails").text("UE UH SUMMARY (SURPLUS ONLY)");
		$("#nstat").text("Surplus");
	} 
	var sus_no = '${m_2}';
	$("#nsusno").text(sus_no);
	var unit_name = '${m_4[0]}'.split(':');
	$("#nunitname").text(unit_name[4]); 
	$("#nhqname").text('${m_5}');
	
	/* var f_d = new Date('${m_1[0][8]}');
	var l_d = f_d.toLocaleDateString('en-GB', {
    	month: 'short', year: 'numeric'
    }).replace(/ /g, '-'); */
	$("#nlastpd").text('${m_dt[0].update_date}');
});
        
function getunithirar(sus_no){
	$("#nsusno").text(sus_no);

	var FindWhat = "HQ";
	$.post("getMMSHirarNameBySUS?"+key+"="+value,{FindWhat:FindWhat,a:sus_no},function(j) {
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
		}
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++) {
			datap=data[i].split(":");
			$("#nhqname").text(datap[1]);
			$("#nunitname").text(datap[4]);
		}	
	}); 
	
	var FindWhat = "COMMAND";
	$.post("getMMSHirarNameBySUS?"+key+"="+value,{FindWhat:FindWhat,a:sus_no},function(j) {
		var a = [];
		var enc = j[j.length-1].substring(0,16);
		for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
		}
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++) {
			datap=data[i].split(":");
			$("#ncomdname").text(datap[1]);
		}	
	}); 
}

function cl(){
	
	try{
		if(window.location.href.includes("?")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 		
    }
    catch (e) {
    	// TODO: handle exception
    }   
}


function nkprint(divName) {
	//let popupWinindow
    let innerContents = document.getElementById('printableArea').innerHTML;
    popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
    popupWindow.document.open();
    popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
    popupWindow.document.close();
}
</script>

