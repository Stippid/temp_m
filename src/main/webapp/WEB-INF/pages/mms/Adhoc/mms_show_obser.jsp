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
		               <b>WEAPON AND EQPT DB UE UH </b>
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
						
						<table class="nrTableMain" width="100%">
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
			                  		<tr style="font-size: 12px;text-align:center;">
			                  	   	    <th class="nrBox" width="5%">Month</th>
				                  		<th class="nrBox" width="5%">Year</th>
										<th class="nrBox" width="10%">Census No</th>
				                  		<th class="nrBox" width="10%">Nomenclature</th>
				                  		<th class="nrBox" width="10%">Type Holding</th>
				                  		<th class="nrBox" width="10%">Type Eqpt</th>
				                  		
				                  		<th class="nrBox" width="10%">Observation</th>
				                  		<th class="nrBox" width="10%">Observation Response</th>
				                  		<th class="nrBox" width="10%">Status</th>
				                  		<th class="nrBox" width="10%">Status Updated</th>
									</tr>
								</thead>
							</table>
							
							<div class="nrTableDataDiv">
							    <div class="nrWatermarkBase" style="z-index: -1">  
									 <p></p>
								</div>
								<table class="nrTableData" border="1">
									<thead>
										<tr class="listheading nr-medium nr-blue box" style="font-size: 12px">
										<th class="nrBox" width="5%">Month</th>
				                  		<th class="nrBox" width="5%">Year</th>
										<th class="nrBox" width="10%">Census No</th>
				                  		<th class="nrBox" width="10%">Nomenclature</th>
				                  		<th class="nrBox" width="10%">Type Holding</th>
				                  		<th class="nrBox" width="10%">Type Eqpt</th>
				                  		
				                  		<th class="nrBox" width="10%">Observation</th>
				                  		<th class="nrBox" width="10%">Observation Response</th>
				                  		<th class="nrBox" width="10%">Status</th>
				                  		<th class="nrBox" width="10%">Status Updated</th>
									</thead>
									<tbody id="nrTable">
									    <c:if test="${m_12[0][0] == 'NIL'}">
											 <tr class='nrSubHeading'>
												<td colspan='13' style='text-align:center;'>Data Not Available...</td>
											    <% ntotln=0; %>
											 </tr>
										</c:if>
										
										<c:if test="${m_12[0][0] != 'NIL'}"> 
										    <c:forEach var="item" items="${m_12}" varStatus="num">
										        <tr style="font-size: 12px" id="NRRDO" name="NRRDO">
										            <td width="5%" >${item[0]}</td>
										            <td width="5%" >${item[1]}</td>
										            <td width="10%" >${item[3]}</td>
											        <td width="10%" >${item[4]}</td>
											        <td width="10%" >${item[6]}</td>
											        <td width="10%" >${item[8]}</td>
											        <td width="10%" >${item[9]}</td>
											       <%--  <td width="10%" >${item[10]}</td> --%>
											        <td width="10%" >${item[11]}</td>
											        <td width="10%" >${item[13]}</td>
											        <td width="10%" >${item[14]}</td>
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
		
		<div class="card">
		<div class="card-footer">
		 
		     <input type="button" class="btn btn-success btn-sm" value="Export" id="btn_e" style="background-color: purple;">
		     
             <input type="button" class="btn btn-primary btn-sm" value="Print Page" id="btn_p" onclick="printDiv('.nrTableDataDiv');"> 
		</div>
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

<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script>
function printDiv(a){
	$().getPrintDiv(a);
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
	var x = screen.width/2 - 1100/2;
	var y = screen.height/2 - 900/2;
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
   
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

function parent_disable1() {
	if(newWin && !newWin.closed)
		newWin.focus();
}


</script>
	
	
		