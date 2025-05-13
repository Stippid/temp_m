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
		               <b>WASTAGE REPORT</b>
		        </div> 
		</div>
      </div>
  </div>

<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading">WASTAGE REPORT</div>
								<div id = "SearchViewtr" class="nrTableMain2Search" style="text-align: right;" >
			    			
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							    </div>
    <div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span>
			<div id="" class="nrTableMainDiv">
						
						<input type="hidden" id="selectedid" name="selectedid">
						<input type="hidden" id="statushid" name="statushid">
						<input type="hidden" id="nrSrcSel" name="nrSrcSel">
 <div class="nrTableDataDiv">
		                 <table class="table_collapse report_print" border="1" style=" font-size: 9px;" width="100%">
									<thead style="text-align: center;">
										<tr>
										<th  width="25%">Census No </th>
				                  		<th  width="25%">Nomenclature</th>
				                  		<th  width="10%">AU</th>
				                  		<th  width="10%">Normal</th>
				                  		<th  width="10%">Batlle Cas</th>
				                  		<th  width="10%">Others</th>
				                  		<th  width="10%">Depot</th>
									</tr>
									</thead>                  
									<tbody id="nrTable">
									    <c:if test="${m_12[0][0] == 'NIL'}">
										<tr class='mmsSubHeading'>
												<td colspan='13' style='text-align:center;'>Data Not Available...</td>
											    <% ntotln=0; %>
											 </tr>
										</c:if>
										
										<c:if test="${m_12[0][0] != 'NIL'}"> 
										    <c:forEach var="item" items="${m_12}" varStatus="num">
										        <tr style="font-size: 12px" id="NRRDO" name="NRRDO">
										            <td style="width: 25%;text-align: center">${item[0]}</td>
										            <td style="width: 25%;text-align: left">${item[1]}</td>
										            <td style="width: 10%;text-align: center">${item[2]}</td>
										            <td style="width: 10%;text-align: center">${item[3]}</td>
										            <td style="width: 10%;text-align: center">${item[4]}</td> 
										            <td style="width: 10%;text-align: center">${item[5]}</td>
										            <td style="width: 10%;text-align: center">${item[6]}</td>
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
 
<script>

function printDiv() {
	$("#SearchViewtr").hide();
	$("#tdheaderView").show();
	$("#headerView").hide();
	let innerContents = document.getElementById('printableArea').innerHTML;
  	popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
  	popupWindow.document.open();
  	popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
  	popupWindow.document.close();
  	$("#SearchViewtr").show();
  	$("#tdheaderView").hide();
  	$("#headerView").show();
}
function exportData(b){
	$().tbtoExcel(b);
	b.preventDefault();
} 
function nrSetWaterMark(pline){
	getUserIP(function(ip){
		var roleid="${roleid}";
		var username="${username}";
		var userid="${userId}";
		var ab="";
		
		var today = new Date();
		var date = today.getDate()+'-'+ (today.getMonth()+1)+'-'+today.getFullYear();
		var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
		var currentDate = date+' '+time;
		
		abip="Generated by " + username +" on "+currentDate+" with IP-"+ip;
		 for (var i = 0; i <pline*1.5; i++) {
				ab=ab+abip+" ";
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

 function viewPrint(){
	
    $("#p_c_code").val($("#command_code").val());
	$("#p_q_code").val($("#corps_code").val());
	$("#p_d_code").val($("#div_code").val());
	$("#p_b_code").val($("#bde_code").val());
	$("#p_p_code").val($("#prf_code").val());
	$("#p_hldg").val($("#type_of_hldg").val());
	$("#p_d_from").val("");
	$("#p_d_to").val("");
	$("#p_unit1").submit();
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

   function Ustatus(){
	$("#p_unit81").submit();
}   
 	
</Script>	

