<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/common/nrcss.css"> 

<form:form name="" id=""
	action="adhoc_act?${_csrf.parameterName}=${_csrf.token}" method='POST'
	commandName="AdhocgetMMSUnitList" enctype="multipart/form-data">
	<div class="card">
		<div class="card-header">
		<div class="container" align="center">
			<strong>ADHOC QUERY</strong>
		</div>
		</div>
		
		<div class="card">
		<div class="col-md-12" >
			<div class="row form-group">
				<div class="col col-md-3">
					<div style="background-color:mistyrose;border-radius:10px 10px 0px 0px;font-size:20px;border:1px solid lightblue;text-align: center">
						<span>Command</span>
					</div>				
					<div style="background-color:lightblue;">				
						&nbsp;&nbsp;<b><input type=checkbox id='ncSelAll' name='nSelAll' onclick="callsetallC();">&nbsp;All Command(<b></b><span id="ncSel" style='float: right; font-size: 14px;'></span>)</b> 
					&nbsp;&nbsp;<input id="nrCInput" type="text" placeholder="Search Command ..." size="15" autocomplete="off">
					</div>
					<div>
					<div class="col-md-12"
						style="height:150px; overflow: auto; width: 100%; border:1px solid #000; text-align: left;"
						id="nrCList">
						<table id="nrCTable">
							<c:forEach var="item" items="${AdhocUList}" varStatus="num">
								<c:if test="${item[3] == 'COMMAND'}">
									<tr id='nrCLine"+data[i][5]+"'>
										<td>&nbsp;<input class='nrCheckBox' type=checkbox
											id='"+${item[1]}+"' name='"+${item[1]}+"'
											onclick='findCselected();'>&nbsp;${item[2]}</td>
								</c:if>
							</c:forEach>
						</table>
					</div>
					</div>
				</div>
				<div class="col col-md-3">
					<div style="background-color:mistyrose;border-radius:10px 10px 0px 0px;font-size:20px;border:1px solid lightblue;text-align: center">
						<span>Corps</span>
					</div>				
					<div style="background-color:lightblue;">				
						&nbsp;&nbsp;<b><input type=checkbox id='nqSelAll' name='' onclick="callsetallQ();">&nbsp;All Corps(<b></b><span id="ncSel" style='float: right; font-size: 14px;'></span>)</b> 
					&nbsp;&nbsp;<input id="nrQInput" type="text" placeholder="Search Corps ..." size="15" autocomplete="off">
					</div>
					<div>
					<div class="col-md-12"
						style="height:150px; overflow: auto; width: 100%; border:1px solid #000; text-align: left;"
						id="nrQList">
						<table id="nrQTable">
							<c:forEach var="item" items="${AdhocUList}" varStatus="num">
								<c:if test="${item[3] == 'CORPS'}">
									<tr id='nrQLine"+data[i][5]+"'>
										<td>&nbsp;<input class='nrCheckBox' type=checkbox
											id='"+${item[1]}+"' name='"+${item[1]}+"'
											onclick='findQselected();'>&nbsp;${item[2]}</td>
								</c:if>
							</c:forEach>
						</table>
					</div>
					</div>
				</div>
				<div class="col col-md-3">
					<div style="background-color:mistyrose;border-radius:10px 10px 0px 0px;font-size:20px;border:1px solid lightblue;text-align: center">
						<span>Division</span>
					</div>				
					<div style="background-color:lightblue;">				
						&nbsp;&nbsp;<b><input type=checkbox id='ndSelAll' name='' onclick="callsetallD();">&nbsp;All Division(<b></b><span id="ncSel" style='float: right; font-size: 14px;'></span>)</b> 
					&nbsp;&nbsp;<input id="nrDInput" type="text" placeholder="Search Div ..." size="15" autocomplete="off">
					</div>
					<div>
					<div class="col-md-12"
						style="height:150px; overflow: auto; width: 100%; border:1px solid #000; text-align: left;"
						id="nrDList">
						<table id="nrDTable">
							<c:forEach var="item" items="${AdhocUList}" varStatus="num">
								<c:if test="${item[3] == 'DIVISION'}">
									<tr id='nrDLine"+data[i][5]+"'>
										<td>&nbsp;<input class='nrCheckBox' type=checkbox
											id='"+${item[1]}+"' name='"+${item[1]}+"'
											onclick='findDselected();'>&nbsp;${item[2]}</td>
								</c:if>
							</c:forEach>
						</table>
					</div>
					</div>
				</div>
				<div class="col col-md-3">
					<div style="background-color:mistyrose;border-radius:10px 10px 0px 0px;font-size:20px;border:1px solid lightblue;text-align: center">
						<span>Brigade</span>
					</div>				
					<div style="background-color:lightblue;">				
						&nbsp;&nbsp;<b><input type=checkbox id='ncSelAll' name='nSelAll' onclick="callsetallC();">&nbsp;All Command(<b></b><span id="ncSel" style='float: right; font-size: 14px;'></span>)</b> 
					&nbsp;&nbsp;<input id="nrCInput" type="text" placeholder="Search Command ..." size="15" autocomplete="off">
					</div>
					<div>
					<div class="col-md-12"
						style="height:150px; overflow: auto; width: 100%; border:1px solid #000; text-align: left;"
						id="nrCList">
						<table id="nrCTable">
							<c:forEach var="item" items="${AdhocUList}" varStatus="num">
								<c:if test="${item[3] == 'BRIGADE'}">
									<tr id='nrCLine"+data[i][5]+"'>
										<td>&nbsp;<input class='nrCheckBox' type=checkbox
											id='"+${item[1]}+"' name='"+${item[1]}+"'
											onclick='findCselected();'>&nbsp;${item[2]}</td>
								</c:if>
							</c:forEach>
						</table>
					</div>
					</div>
				</div>
				
			</div>
			<div class="row form-group">
				<div class="col col-md-6">
					<div style="background-color:mistyrose;border-radius:10px 10px 0px 0px;font-size:20px;border:1px solid lightblue;text-align: center">
						<span>PRF Group</span>
					</div>				
					<div style="background-color:lightblue;">				
						&nbsp;&nbsp;<b><input type=checkbox id='ncSelAll' name='nSelAll' onclick="callsetallP();">&nbsp;All PRF Group(<b></b><span id="ncSel" style='float: right; font-size: 14px;'></span>)</b> 
					&nbsp;&nbsp;<input id="nrCInput" type="text" placeholder="Search Command ..." size="15" autocomplete="off">
					</div>
					<div>
					<div class="col-md-12"
						style="height:150px; overflow: auto; width: 100%; border:1px solid #000; text-align: left;"
						id="nrCList">
						<table id="nrCTable">
							<c:forEach var="item" items="${AdhocUList}" varStatus="num">
								<c:if test="${item[3] == 'COMMAND'}">
									<tr id='nrCLine"+data[i][5]+"'>
										<td>&nbsp;<input class='nrCheckBox' type=checkbox
											id='"+${item[1]}+"' name='"+${item[1]}+"'
											onclick='findCselected();'>&nbsp;${item[2]}</td>
								</c:if>
							</c:forEach>
						</table>
					</div>
					</div>
				</div>
				<div class="col col-md-6">
					<div style="background-color:mistyrose;border-radius:10px 10px 0px 0px;font-size:20px;border:1px solid lightblue;text-align: center">
						<span>Nomenclature</span>
					</div>				
					<div style="background-color:lightblue;">				
						&nbsp;&nbsp;<b><input type=checkbox id='nqSelAll' name='' onclick="callsetallN();">&nbsp;All Nomenclature(<b></b><span id="ncSel" style='float: right; font-size: 14px;'></span>)</b> 
					&nbsp;&nbsp;<input id="nrQInput" type="text" placeholder="Search Corps ..." size="15" autocomplete="off">
					</div>
					<div>
					<div class="col-md-12"
						style="height:150px; overflow: auto; width: 100%; border:1px solid #000; text-align: left;"
						id="nrQList">
						<table id="nrQTable">
							<c:forEach var="item" items="${AdhocUList}" varStatus="num">
								<c:if test="${item[3] == 'CORPS'}">
									<tr id='nrQLine"+data[i][5]+"'>
										<td>&nbsp;<input class='nrCheckBox' type=checkbox
											id='"+${item[1]}+"' name='"+${item[1]}+"'
											onclick='findQselected();'>&nbsp;${item[2]}</td>
								</c:if>
							</c:forEach>
						</table>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>	
			<div class="card-footer">
		     
			 
			 <input type="button" class="btn btn-primary btn-sm" value="Get Hirarchy" onclick="GetHir();" >
			
		</div>
	</div>
</form:form>


<c:url value="WpnEqptdatalistAdhoc" var="backUrl" />
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

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>				
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<script>
$(document).ready(function() {
	
	$("#nrCInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#nrCList tr").filter(function() {
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
});

function callsetallC() {
	
	var chkclk=$('#ncSelAll').prop('checked');
	if (chkclk) {
		
		$('.nrCheckBox').prop('checked',true);
	} else {
		
		$('.nrCheckBox').prop('checked',false);
	}
	findselected();
}


function viewPrint(){
	var a = $("input#sus_no1").val();
	if(a!= null && a !="" && a!="null"){	
		var statusid = $("#statushid").val();
		var x = screen.width/2 - 1100/ 2;
	    var y = screen.height/2 - 900/2;
	    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
		document.getElementById('printMCRId').value=a;
		document.getElementById('printMCRForm').submit();
	}
}
</script>

<script>

function GetHir(){
	var x = screen.width/2 - 1100/2;
	var y = screen.height/2 - 900/2;
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
    $("#p_c_code1").val($("#command_code").val());
	$("#p_q_code1").val($("#corps_code").val());
	$("#p_d_code1").val($("#div_code").val());
	$("#p_b_code1").val($("#bde_code").val());
	$("#p_p_code1").val($("#prf_code").val());
	$("#p_hldg1").val($("#type_of_hldg").val());
	$("#p_d_from1").val("");
	$("#p_d_to1").val("");
	$("#p_unit3").submit();
}

</script>

