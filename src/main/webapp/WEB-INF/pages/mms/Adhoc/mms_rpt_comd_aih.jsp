
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
        HttpSession sess = request.getSession(false);
        if (sess.getAttribute("userId") == null) { response.sendRedirect("~/login"); return; } 
%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> 
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/miso/assets/scss/style.css"> 
<link rel="stylesheet" href="js/common/nrcssp.css">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

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
  <div>                
     <div class="container" align="center"id="headerView" style="display: block;"> 
        <div class="card">
                                <div class="card-header mms-card-header">
                               <b>COMMAND WISE HOLDING</b>
                        </div> 
                   </div>
      </div>
  </div>

<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading">COMMAND WISE HOLDING</div>
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
					<table class="table_collapse report_print" border="1"
						style=" font-size: 9px;" width="100%">
						<thead style="background-color: #9c27b0; color: white;">
							<tr style="font-size: 10px; text-align: center; width: 100%;">
								<th  width="6%" rowspan='2' title='PRF Group'>PRF Group</th>
								<th  width="6%" rowspan='2' title='Census No'>Census No</th>
								<th  width="18%" rowspan='2' title='Item Nomenclature'>Nomenclature</th>
								<th  width="5%" COLSPAN='2' title='Southern Command'>SC</th>
								<th  width="5%" COLSPAN='2' title='Eastern Command'>EC</th>
								<th  width="5%" COLSPAN='2' title='Western Command'>WC</th>
								<th  width="5%" COLSPAN='2' title='Central Command'>CC</th>
								<th  width="5%" COLSPAN='2' title='Northern Command'>NC</th>
								<th  width="5%" COLSPAN='2' title='ARTRAC'>ARTRAC</th>
								<th  width="5%" COLSPAN='2' title='Andaman & Nicobar Command'>ANC</th>
								<th  width="5%" COLSPAN='2' title='South Western Command'>SWC</th>
								<th  width="5%" COLSPAN='2' title='UN Mission'>UN</th>
								<th  width="5%" COLSPAN='2' title='SFC'>SFC</th>
								<th  width="5%" COLSPAN='2' title='Ministry of Defence'>MOD</th>
								<th  width="3%" rowspan='2' title='Sector Stores'>SS</th>
								<th  width="3%" rowspan='2' title='Loan Stores'>LS</th>
								<th  width="3%" rowspan='2' title='ACSFP'>AC</th>
								<th  width="3%" rowspan='2' title='Reserve Stock'>RES</th>
								<th  width="3%" rowspan='2' title='Depot Stock'>DEP</th>
							<tr>
								<th  width="2.5%">UE</th>
								<th  width="2.5%">UH</th>
								<th  width="2.5%">UE</th>
								<th  width="2.5%">UH</th>
								<th  width="2.5%">UE</th>
								<th  width="2.5%">UH</th>
								<th  width="2.5%">UE</th>
								<th  width="2.5%">UH</th>
								<th  width="2.5%">UE</th>
								<th  width="2.5%">UH</th>
								<th  width="2.5%">UE</th>
								<th  width="2.5%">UH</th>
								<th  width="2.5%">UE</th>
								<th  width="2.5%">UH</th>
								<th  width="2.5%">UE</th>
								<th  width="2.5%">UH</th>
								<th  width="2.5%">UE</th>
								<th  width="2.5%">UH</th>
								<th  width="2.5%">UE</th>
								<th  width="2.5%">UH</th>
								<th  width="2.5%">UE</th>
								<th  width="2.5%">UH</th>
							</tr>
						</thead>
						<tbody id="nrTable" class="nrTable" style="width: 100%;">
							<c:if test="${m_12[0][0] == 'NIL'}">
								<tr class='mmsSubHeading'>
									<td colspan='13' style='text-align: center;'>Data Not Available...</td>
									<%
										ntotln = 0;
									%>
								</tr>
							</c:if>

							<c:if test="${m_12[0][0] != 'NIL'}">
								<c:forEach var="item" items="${m_12}" varStatus="num">
									<tr style="font-size: 12px" id="NRRDO" name="NRRDO">
										<td style="width: 6%;">${item[0]}</td>
										<td style="width: 6%;">${item[1]}</td>
										<td style="width: 18%;">${item[2]}</td>
										<td style="width: 2.5%;">${item[3]}</td>
										<td style="text-align: center; width: 2.5%;">${item[4]}</td>
										<td style="width: 2.5%;">${item[5]}</td>
										<td style="width: 2.5%;">${item[6]}</td>
										<td style="width: 2.5%;">${item[7]}</td>
										<td style="width: 2.5%;">${item[8]}</td>
										<td style="text-align: center; width: 2.5%;">${item[9]}</td>
										<td style="text-align: center; width: 2.5%;">${item[10]}</td>
										<td style="width: 2.5%;">${item[11]}</td>
										<td style="width: 2.5%;">${item[12]}</td>
										<td style="width: 2.5%;">${item[13]}</td>
										<td style="text-align: center; width: 2.5%;">${item[14]}</td>
										<td style="width: 2.5%;">${item[15]}</td>
										<td style="width: 2.5%;">${item[16]}</td>
										<td style="width: 2.5%;">${item[17]}</td>
										<td style="width: 2.5%;">${item[18]}</td>
										<td style="text-align: center; width: 2.5%;">${item[19]}</td>
										<td style="text-align: center; width: 2.5%;">${item[20]}</td>
										<td style="width: 2.5%;">${item[21]}</td>
										<td style="width: 2.5%;">${item[22]}</td>
										<td style="width: 2.5%;">${item[23]}</td>
										<td style="text-align: center; width: 2.5%;">${item[24]}</td>
										<td style="width: 3%;">${item[25]}</td>
										<td style="width: 3%;">${item[26]}</td>
										<td style="width: 3%;">${item[27]}</td>
										<td style="width: 3%;">${item[28]}</td>
										<td style="text-align: center; width: 3%;">${item[29]}</td>
										<%
											ntotln++;
										%>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
                   </div>
				</div>
			</div>
		</div>
                <div class="card">
                <div class="card-footer" style="margin-top: 10px;">
                     <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" onclick="exportData('.nrTableDataDiv');">
             <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv();">               
        </div>
        </div>        
</form:form>

<c:url value="WpnEqptdatalist" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m4_unit1" name="m4_unit1" modelAttribute="m4_c_code">
      <input type="hidden" name="m4_c_code" id="m4_c_code"/>
          <input type="hidden" name="m4_q_code" id="m4_q_code"/>
          <input type="hidden" name="m4_d_code" id="m4_d_code"/>
          <input type="hidden" name="m4_b_code" id="m4_b_code"/>
          <input type="hidden" name="m4_p_code" id="m4_p_code"/>
          <input type="hidden" name="m4_d_from" id="m4_d_from"/>
          <input type="hidden" name="m4_d_to" id="m4_d_to"/>
          <input type="hidden" name="m4_hldg" id="m4_hldg"/>
          <input type="hidden" name="m4_prfs" id="m4_prfs"/>
</form:form>

<c:url value="printWpnEqptList" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit1" name="p_unit1" modelAttribute="p_c_code" target="result">
        <input type="hidden" name="p_c_code" id="p_c_code"/>
        <input type="hidden" name="p_q_code" id="p_q_code"/>
        <input type="hidden" name="p_d_code" id="p_d_code"/>
        <input type="hidden" name="p_b_code" id="p_b_code"/>
        <input type="hidden" name="p_p_code" id="p_p_code"/>
        <input type="hidden" name="p_hldg" id="p_hldg"/>
        <input type="hidden" name="p_d_from" id="p_d_from"/>
        <input type="hidden" name="p_d_to" id="p_d_to"/>
</form:form> 


<c:url value="AdhocList" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit3" name="p_unit3" modelAttribute="p_c_code1" target="result">
        <input type="hidden" name="p_c_code1" id="p_c_code1"/>
        <input type="hidden" name="p_q_code1" id="p_q_code1"/>
        <input type="hidden" name="p_d_code1" id="p_d_code1"/>
        <input type="hidden" name="p_b_code1" id="p_b_code1"/>
        <input type="hidden" name="p_p_code1" id="p_p_code1"/>
        <input type="hidden" name="p_hldg1" id="p_hldg1"/>
        <input type="hidden" name="p_d_from1" id="p_d_from1"/>
        <input type="hidden" name="p_d_to1" id="p_d_to1"/>
</form:form> 


<c:url value="ObsList" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit4" name="p_unit4" modelAttribute="p_c_code2" target="result">
        <input type="hidden" name="p_c_code2" id="p_c_code2"/>
        <input type="hidden" name="p_q_code2" id="p_q_code2"/>
        <input type="hidden" name="p_d_code2" id="p_d_code2"/>
        <input type="hidden" name="p_b_code2" id="p_b_code2"/>
        <input type="hidden" name="p_p_code2" id="p_p_code2"/>
        <input type="hidden" name="p_hldg2" id="p_hldg2"/>
        <input type="hidden" name="p_d_from2" id="p_d_from2"/>
        <input type="hidden" name="p_d_to2" id="p_d_to2"/>
</form:form> 


<c:url value="BarrList" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit5" name="p_unit5" modelAttribute="p_c_code3" target="result">
        <input type="hidden" name="p_c_code3" id="p_c_code3"/>
        <input type="hidden" name="p_q_code3" id="p_q_code3"/>
        <input type="hidden" name="p_d_code3" id="p_d_code3"/>
        <input type="hidden" name="p_b_code3" id="p_b_code3"/>
        <input type="hidden" name="p_p_code3" id="p_p_code3"/>
        <input type="hidden" name="p_hldg3" id="p_hldg3"/>
        <input type="hidden" name="p_d_from3" id="p_d_from3"/>
        <input type="hidden" name="p_d_to3" id="p_d_to3"/>
</form:form> 


<c:url value="Hirarueuh" var="printUrl" />
<form:form action="${printUrl}" method="post" id="p_unit6" name="p_unit6" modelAttribute="p_c_code4" target="result">
        <input type="hidden" name="p_c_code4" id="p_c_code4"/>
        <input type="hidden" name="p_q_code4" id="p_q_code4"/>
        <input type="hidden" name="p_d_code4" id="p_d_code4"/>
        <input type="hidden" name="p_b_code4" id="p_b_code4"/>
        <input type="hidden" name="p_p_code4" id="p_p_code4"/>
        <input type="hidden" name="p_hldg4" id="p_hldg4"/>
        <input type="hidden" name="p_d_from4" id="p_d_from4"/>
        <input type="hidden" name="p_d_to4" id="p_d_to4"/>
        <input type="hidden" name="nrflowcontrol" id="nrflowcontrol"/>
</form:form> 

<script>
	function printDiv() {
		$("#SearchViewtr").hide();
		$("#tdheaderView").show();
		$("#headerView").hide();
		let innerContents = document.getElementById('printableArea').innerHTML;
		popupWindow = window.open('','_blank','width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWindow.document.open();
		popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'+ innerContents + '</html>');
		popupWindow.document.close();
		$("#SearchViewtr").show();
		$("#tdheaderView").hide();
		$("#headerView").show();
	}
	function exportData(b) {
		$().tbtoExcel(b);
		b.preventDefault();
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

	function getselected() {
		var checkedVals = $('.nrCheckBox:checkbox:checked').map(function() {
			return $(this).attr("id");
		}).get();
		$("#nrSrcSel").val(checkedVals.join(","));
	}

	$(document).ready(
			function() {

				$("div#divwatermark").val('').addClass('watermarked');
				watermarkreport();
				var y1 = '${m_12[0][0]}';
				if (y1 != "" || '${m_12[0]}'.length > 0) {
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

					$("#ntotln").text(
<%=ntotln%>);        
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

function parent_disable1() {
    if(newWin && !newWin.closed)
         newWin.focus();
}
</script>     