<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> 
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/miso/assets/scss/style.css"> 
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/nrcssp.css">

<script>
	var username="${username}";
	var curDate = "${curDate}";
</script>
<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<form:form action="" method="post" enctype="multipart/form-data" class="form-horizontal" commandName="mms_wpn_eqpt_status_viewCMD">
	<div class="nkpageland" id="printableArea">
  		<div onFocus="parent_disable1();" onclick="parent_disable1();">		
     		<div class="container" align="center" id="headerView" style="display: block;">  
        		<div class="card">
					<div class="card-header mms-card-header">
		               <b>UNIT STATUS</b>
		        	</div> 
				</div>
      		</div>
  		</div>
		<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading">UNIT STATUS</div>
			<div id = "SearchViewtr" class="nrTableMain2Search" style="text-align: right;" >
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							    </div>

			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<div id="" class="nrTableMainDiv">
					<input type="hidden" id="selectedid" name="selectedid"> <input
						type="hidden" id="statushid" name="statushid"> <input
						type="hidden" id="nrSrcSel" name="nrSrcSel">
 <div class="nrTableDataDiv">
					<table border="1" class="table_collapse report_print" width="100%">
						<thead>
							<tr>
								<th  width="55%">Unit</th>
								<th  width="15%">Status</th>
								<th  width="15%">Updated Month</th>
								<th  width="15%">Observation</th>
							</tr>
						</thead>
						
							<tbody id="nrTable">
									    <c:if test="${m_12[0][0] == 'NIL'}">
											 <tr class='mmsSubHeading'>
												<td colspan='4' style='text-align:center;'>Data Not Available...</td>
											    <% ntotln=0; %>
											 </tr>
										</c:if>

							<c:if test="${m_12[0][0] != 'NIL'}">
								<c:forEach var="item" items="${m_12}" varStatus="num">
									<tr>
										<td width="55%">${item[3]}</td>
										<c:if test="${item[4] == 'APP'}">
											<td style="text-align: center;">Approved</td>
										</c:if>
										<c:if test="${item[4] == 'DEF'}">
											<td style="text-align: center; width: 15%;">Defaulter</td>
										</c:if>
										<td style="text-align: center; width: 15%;">${item[8]}</td>
										<td style="text-align: center; width: 15%;">${item[5]}</td>
										<% ntotln++; %>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					</div>

				</div>
			</div>

		</div>
	<div class="card-footer" style="margin-top: 10px;">
		     <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" onclick="exportData('.nrTableDataDiv');">
             <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();"> 
		</div>	
</form:form>

<script>

function printDiv() {
	$("#SearchViewtr").hide();
	$("#tdheaderView").show();
	$("#headerView").hide();
	$("#btn_p").hide();
	let innerContents = document.getElementById('printableArea').innerHTML;
	popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	popupWindow.document.open();
	popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+ '</html>');
	popupWindow.document.close();
    $("#SearchViewtr").show();
    $("#tdheaderView").hide();
    $("#headerView").show();
    $("#btn_p").show();
}

$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var y1 = '${m_12[0][0]}';

	if(y1 != "" || '${m_12[0]}'.length > 0){
		$("#obstable").hide(); 
		$("#SearchView").show();
		
		$("#unit_hid2").show();
		$("#command_code").val('${m_2}');
	    $("#corps_code").val('${m_3}');
		$("#div_code").val('${m_4}');
		$("#bde_code").val('${m_5}');
		$("#prf_code").val('${m_6}');
		$("#date_from").val('${m_7}');
		$("#date_to").val('${m_8}');
	   	$("#type_of_hldg").val('${m_9}');
	   	$("#from_prf_Search").val('${m_10}');
	   	$("#ntotln").text(<%=ntotln%>);	
	} 
		
	var d = new Date();
	var Fulldate = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	$("#mnth_year").val(Fulldate);
	
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
	    $("#nrTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
    });
    
    
	
});
function exportData(b){

	$().tbtoExcel(b);
	b.preventDefault();
}

function showObslist(a){
    document.getElementById('sus_no1').value=a;
	alert(document.getElementById('sus_no1').value);
	$("#p_unit61").submit();
}

 function parent_disable1() {
	if(newWin && !newWin.closed)
	newWin.focus();
} 

</script>
