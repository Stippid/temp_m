<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/common/nrcss.css"> 

<body class="mmsbg">
<% int ntotln=0; %>
<div id="nrWaitLoader" style="display:none;" align="center">
	<span id="">Processing Data.Please Wait ...</span>
</div>
<form:form action="" method="post" enctype="multipart/form-data" class="form-horizontal" commandName="mms_wpn_eqpt_status_viewCMD">
  <div onFocus="parent_disable1();" onclick="parent_disable1();">		
     <div class="container" align="center"> 
        <div class="card">
				<div class="card-header mms-card-header">
		               <b>WASTAGE REPORT</b>
		        </div> 
		</div>
      </div>
  </div>
  
  <div class="card" id="unit_hid2">
    <div class="card-body card-block">	
			<div id="" class="nrTableMainDiv">
						
						<input type="hidden" id="selectedid" name="selectedid">
						<input type="hidden" id="statushid" name="statushid">
						<input type="hidden" id="nrSrcSel" name="nrSrcSel">
						
						<table class="nrTableMain" width="100%" id = "nkm">
			    			<tr style="width:100%;">
			    				<td class="nrTableMain2Search" colspan='2'> 
									<label>Search in Result(<span id="ntotln"></span>)</label>&nbsp;:&nbsp;
									<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal;font-size: 14px;">
							    </td>
					
					<tr id="stmenu" cellspacing="5px">
						<td colspan='2'></td>
						
						
					<tr width="100%" ><td colspan='2' style="text-align:right">
			              			<table class="nrTableDataHead" style="width:calc(100% - 19px);">
			                			<thead>
			                  		<tr class="nrTable1" >
									    <th class="nrBox" width="25%">Census No </th>
				                  		<th class="nrBox" width="25%">Nomenclature</th>
				                  		<th class="nrBox" width="10%">AU</th>
				                  		<th class="nrBox" width="10%">Normal</th>
				                  		<th class="nrBox" width="10%">Batlle Cas</th>
				                  		<th class="nrBox" width="10%">Others</th>
				                  		<th class="nrBox" width="10%">Depot</th>
									</tr>
								</thead>
							</table>
							
							<div class="nrTableDataDiv">
							    <div class="nrWatermarkBase" style="z-index: -1">  
									 <p></p>
								</div>
								<table class="nrTableData" border="1">
									<thead>
									<tr class="nrTable1">
										<th class="nrBox" width="25%">Census No </th>
				                  		<th class="nrBox" width="25%">Nomenclature</th>
				                  		<th class="nrBox" width="10%">AU</th>
				                  		<th class="nrBox" width="10%">Normal</th>
				                  		<th class="nrBox" width="10%">Batlle Cas</th>
				                  		<th class="nrBox" width="10%">Others</th>
				                  		<th class="nrBox" width="10%">Depot</th>
									</tr>
									</thead>                  
									<tbody id="nrTable" style="text-align: center;">
									    <c:if test="${m_12[0][0] == 'NIL'}">
											 <tr class='nrSubHeading'>
												<td colspan='13' style='text-align:center;'>Data Not Available...</td>
											    <% ntotln=0; %>
											 </tr>
										</c:if>
										
										<c:if test="${m_12[0][0] != 'NIL'}"> 
										    <c:forEach var="item" items="${m_12}" varStatus="num">
										        <tr style="font-size: 12px" id="NRRDO" name="NRRDO">
										            <td style="width: 25%">${item[0]}</td>
										            <td style="width: 25%;text-align: left">${item[1]}</td>
										            <td style="width: 10%">${item[2]}</td>
										            <td style="width: 10%">${item[3]}</td>
										            <td style="width: 10%">${item[4]}</td> 
										            <td style="width: 10%">${item[5]}</td>
										            <td style="width: 10%">${item[6]}</td>
										            <% ntotln++; %>
										        </tr>
										    </c:forEach>
										</c:if>
									</tbody>
								</table>
							</div>
				    </table>
			</div>
		</div>
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

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script>
function printDiv(a){
	$().getPrintDiv(a,'WASTAGE REPORT');
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
</script>

<script>

$(document).ready(function() {
	
	var y1 = '${m_12[0][0]}';

	if(y1 != "" || '${m_12[0]}'.length > 0){
		
		$("#obstable").hide(); 
		$("#nkm").show();
		
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
		//nrSetWaterMark(1000);
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
	var x = screen.width/2 - 1100/2;
	var y = screen.height/2 - 900/2;
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
   
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
</script>

<Script>
$(document).ready(function() { 
	
});		
</Script>		