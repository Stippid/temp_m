<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="js/common/nrcss.css">
<!-- <link rel="stylesheet" href="js/common/select2/select2.min.css"> -->
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<!-- <script src="js/common/select2/select2.min.js" type="text/javascript"></script> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script>
	var ntt='';
</script>

<div class="col-md-10" align="center">
	<div class="card">
		<div class="card-header">
			<h5>
				<b>GENR FINAL RELIEF PROGRAMME</b><br>
			</h5>
			<h6>
				<span style="font-size: 12px; color: red;">(To be used by SD-4 / MISO)</span>
			</h6>
		</div>
		<div class="card-body">
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label">Period From</label>
						</div>
						<div class="col-md-4">
							<input type="month" id="period_from" name="period_from" maxlength="10" class="form-control">
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label">To</label>
						</div>
						<div class="col-md-4">
							<input type="month" id="period_to" name="period_to" maxlength="10" class="form-control">
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col-md-4">
							<label for="text-input" class=" form-control-label">Arm / Services</label>
						</div>
						<div class="col-md-8">
							<select name="status" id="status" class="form-control-sm form-control nselect" style="text-align: left;">
							    ${selectArmNameList}
								<c:forEach var="item" items="${getArmNameList}" varStatus="num">
									<option value="${item[0]}">${item[1]}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-6" align="center">
					<div class="row form-group">
						<div class="col-md-11">
							<button class="btn btn-primary btn-sm" onclick="return searchReliefData();">Find Auth Letters</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<c:set var="N0_0" value="0" />
		<c:if test="${getReliefReportList !=null}">
			<div class="col-md-12">
				<div class="col-md-2">
					<label for="text-input" class=" form-control-label">Auth Letters</label>
				</div>
				<div class="col-md-6">
					<select name="letstatus" id="letstatus" class="form-control-sm form-control nselect" style="text-align: left;">
						<c:set var="letno" value="${getSearchReliefReportList[0][1]}"></c:set>
						<c:forEach var="item" items="${getReliefReportList}" varStatus="num">
							<c:if test="${getReliefReportList == letno}">
								<option value="${item[0]}" selected>${item[0]}</option>
							</c:if>
							<c:if test="${getReliefReportList != letno}">
								<option value="${item[0]}">${item[0]}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-2">
					<button class="btn btn-primary btn-sm" onclick="return open1();">Get Program</button>
				</div>
			</div>
		</c:if>
		<c:if test="${!empty getSearchReliefReportList}">
			<div class="card-body" id="report2" style="height: 35vh; overflow: auto;">
				<div class="col-md-8">
					<table id="SearchReport1" class="table no-margin table-striped  table-hover  table-bordered"
						style="table-layout: fixed;width: 100%;overflow: auto;">
						<thead
							style="background-color: #9c27b0; color: white; font-size: 12px">
							<tr>
								<th width="40px;">S No</th>
								<th width="150px;">Unit Name</th>
								<th width="100px;">From Loc</th>
								<th width="150px;">From Fmn</th>
								<th width="100px;">To Loc</th>
								<th width="150px;">To Fmn</th>
								<th width="100px;">NMB Date</th>
								<th width="100px;">Move of Adv Party Date</th>
								<th width="80px;">Mode of Tpt</th>
								<th width="80px;">Indn/De-Indn pd</th>
								<th width="80px;">Remarks</th>
								<th width="150px;">To Comd</th>
								<th width="150px;">To Corps</th>
								<th width="150px;">To Div</th>
								<th width="150px;">To Bde</th>
							</tr>
						</thead>
						<tbody style="font-size: 11px">
							<c:forEach var="item" items="${getSearchReliefReportList}"
								varStatus="num">
								<tr>
									<c:set var="N0_0" value="${num.index+1}" />
									<td id="N${num.index}_0">${N0_0}</td>
									<td id="N${num.index}_1">${item[3]}</td>
									<td id="N${num.index}_2">${item[10]}</td>
									<td id="N${num.index}_3">${item[20]}</td>
									<td id="N${num.index}_4">${item[18]}</td>
									<td id="N${num.index}_5">${item[21]}</td>
									<td id="N${num.index}_6">${item[7]}</td>
									<td id="N${num.index}_7">${item[13]}</td>
									<td id="N${num.index}_8">${item[6]}</td>
									<td id="N${num.index}_9">${item[8]}</td>
									<td id="N${num.index}_10"></td>
									<td id="N${num.index}_11">${item[25]}</td>
									<td id="N${num.index}_12">${item[26]}</td>
									<td id="N${num.index}_13">${item[27]}</td>
									<td id="N${num.index}_14">${item[28]}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="col-md-12">
				<div class="col-md-2">
					<label for="text-input" class=" form-control-label">Eqpt Instr</label>
				</div>
				<div class="col-md-8">
					<select name="einstr" id="einstr" class="form-control form-control nselect" style="text-align: left;">
						<c:forEach var="item" items="${getEqptInstrList}" varStatus="num">
							<option value="${num.index+1}">${fn:substring(item[(num.index*2)+1],0,80)}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="col-md-12">
				<button class="btn btn-sm nBtn" onclick='nPrintdata("S")'>Generate Exec Instrs</button>
				<button class="btn btn-sm nBtn" onclick='nPrintdata("L")'>Generate Extract</button>
				<button class="btn btn-sm nBtn" onclick='nPrintdata("H")'>Generate Relief Pgme</button>
			</div>
		</c:if>
	</div>
</div>
<div class="nPopCContainer" id="nPrintLetter"
	style="width: 85%; height: 85%; display: none; background-color: smokewhite; border: 10px solid gray !important;">
	<c:set var="ltropt" value="${DtlFilterOpt}" />
	<div class="nPopHeader">
		EXEC MOV INSTRS <span class="nPopClose"
			onclick="$('#nPrintLetter').hide();" title="Close Msg Window">&#10006;</span>
	</div>
	<div class="nPopTablee" id="nPrintLetterDiv"
		style="min-height: 70%; max-height: 91%; height: 29.7cm; width: 100%; overflow: auto; color: black;">
		<style>
.nBox {
	border: 1px solid black;
	padding: 7px;
}
.nBoxT {
	border-top: 1px solid black;
	padding: 7px;
}
</style>
		<div class="nPopBody">
			<div class="row form-group">
				<div class="col-md-12">
					<table border="0"
						style="width: 100%; border: 0px; line-height: 1; table-layout: fixed; border-collapse: collapse; padding-bottom: 10px; border-color: transparent;">
						<tr id="nPrintLetterDiv_tb">
					</table>
				</div>
			</div>
		</div>
		<div style="align-self: right;">
			<input type="button" class="btn btn-success btn-sm"
				value="Export to Word" style="background-color: purple;"
				data-toggle="tooltip" title="Export Letter to Word"
				onclick="exportPrintLetter('#nPrintLetterDiv');">
		</div>
	</div>
</div>
<c:url value="approved_nsd_reliefReport1" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="nsearchForm"
	name="nsearchForm" modelAttribute="code_value1">
	<input type="hidden" name="period_from1" id="period_from11" value="0" />
	<input type="hidden" name="period_to1" id="period_to11" value="0" />
	<input type="hidden" name="status1" id="status11" value="0" />
</form:form>
<c:url value="approved_nsd_reliefReport_lt" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="code_value1">
	<input type="hidden" name="period_from1" id="period_from1" value="0" />
	<input type="hidden" name="period_to1" id="period_to1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="auth_letter1" id="auth_letter1" value="0" />
</form:form>


<script type="text/javascript">

$(document).ready(function() {
	//$(".nselect").select2();
	var df='${period_from1}';
	var dt='${period_to1}';
	if (df == null) {
		//var d = new Date();
		//var df = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
		$().getMthYr("#period_from");
	} else {
		$("#period_from").val(df);	
	}
	if (dt == null) {
		$().getMthYr("#period_to");
	} else {
		$("#period_to").val(dt);	
	}
	if('${status1}' != ""){		
		$("#status").val('${status1}');
	}	
});

function searchReliefData(){
	var period_from = $("#period_from").val();
	var period_to = $("#period_to").val();
	var status = $("#status").val();
       
	if($("#status").val() == ""){
		alert("please Select Status");
		$("#status").focus();
		return false;
	}
    
	$("#period_from11").val($("#period_from").val());
    $("#period_to11").val($("#period_to").val());
    $("#status11").val($("#status").val());
    $("#WaitLoader").show();
	document.getElementById('nsearchForm').submit();
}
function open1()
{  
	auth_letter=$("#letstatus").val();
	$("#report2").show();
	var tab = $("#SearchReport1 > tbody");
    tab.empty(); 
    var status = $("#status").val();
   // alert(status);
    if($("#status").val() == ""){
		alert("please Select Status");
		$("#status").focus();
		return false;
	}
    $("#period_from1").val($("#period_from").val());
    $("#period_to1").val($("#period_to").val());
    $("#status1").val(status);
    $("#auth_letter1").val(auth_letter);
 	document.getElementById('searchForm').submit();
}

function getNVal(a) {
	alert(a);
	var b=$('"'+a+'"').val();
	alert(b);
	return b;
}
function nPrintdata(b) {
	//alert(b);
	var dtext="";
	var armst="${getSearchReliefReportList[0][24]}";
	var ltrst=$("#letstatus").val();
	var ltrno="${getSearchReliefReportList[0][1]}";
	var pdbetween="${getSearchReliefReportList[0][22]} - ${getSearchReliefReportList[0][23]}";
	dtext +="";
	var nb="";
	var arm='';
	var i=0;
	var tptmode="";
	var tptmp="";
	var totelist='${getEqptInstrList}';
	var totvlist='${getVehInstrList}';
	if (b=='S') {
		var cmdn=new Array();
		var nabc="[id*='N"+i+"_";
		var tnabc=${N0_0};
		var nMr1=armst;
		var nMr2="APR 2021/22/23 (YEAR) TO MAR 2022 /23/24 (YEAR) / RE-ORBAT-ING / IN SITU RE-ORBAT-ING(.) REF DGMO / MO-5 LETTER NO 69777/2/MO 5 (ARTY)/B DT SEP 07 (07) ADDSD TO COMDS ONLY (NOT TO ALL)(.)";				
		var nMr4=$(nabc+"4']").html();
		
		dtext +="<tr><td colspan='4' style='text-align:center'>MESSAGE FORM</td>";
		dtext +="<tr class='nBoxT'><td style='text-align:left;width:8%;'></td><td style='text-align:left;width:10%;'>PRECEDENCE ACTION<BR>OP IMMEDIATE</td><td style='text-align:left;width:13%;'></td><td style='text-align:left;width:23%;'>PRECEDENCE INFO<BR>OP IMMEDIATE</td><td style='text-align:left;width:23%;'></td><td style='text-align:left;width:23%;'>DTG: __/____/_____</td>";
		dtext +="<tr class='nBoxT'><td colspan='4' style='text-align:left'>FROM : INDARMY (SD 4)</td><td style='text-align:left'>SECURITY CLASSIFICATION<BR>CONFD</td>";
		//dtext +="<tr><td style='text-align:left'>TO :</td><td style='text-align:left'></td><td style='text-align:left'></td></td><td style='text-align:left'></td>";
		var dtextu="";
		for(var i=0;i<tnabc;i++) {
			var nabc="[id*='N"+i+"_";
			dtextu +="<tr><td style='text-align:left;width:8%;'>";
			if (i==0) {
				dtextu +="<tr class='nBoxT'><td style='text-align:left;width:8%;'>";
				dtextu +="INFO :";
			} else {
				dtextu +="<tr><td style='text-align:left;width:8%;'>";    
			}
			dtextu +="</td><td style='text-align:left;width:23%;'>"+$(nabc+"12']").html()+"</td><td style='text-align:left;width:23%;'>"+$(nabc+"13']").html()+"</td><td style='text-align:left;width:23%;'>"+$(nabc+"14']").html()+"</td><td style='text-align:left;width:23%;'>"+$(nabc+"1']").html()+"</td>";
			if (!cmdn.includes($(nabc+"11']").html())) {			
				cmdn.push($(nabc+"11']").html());				
			}			
		}
		var dtextc="";
		console.log("1");
		for(var i=0;i<cmdn.length;i++) {
			console.log("2",i);
			if (i==0) {
				dtextc +="<tr class='nBoxT'><td style='text-align:left;width:8%;'>";
				dtextc +="TO :</td>";
			} else {
				if (i==4) {
					dtextc +="<tr><td style='text-align:left;width:8%;'></td>";
				}
			}
			dtextc +="<td style='text-align:left;width:23%;'>"+cmdn[i]+"</td>";
		}
		console.log("3");
		console.log(dtextc);
		dtext +=dtextc;
		dtext +=dtextu;
		dtext +="<tr><td colspan='6' style='text-align:left' class='nBox'>AO 13/2016/GS & 14/2016/GS(.) "+armst+" RELIEF PGME</td>";
		dtext +="<tr><td colspan='6' style='text-align:left' class='nBox'>"+nMr2+" EXEC MOV / RE-ORBATING INSTRS/ AMDT TO EXEC MOV INSTRS AS UNDER (.)</td>";
		dtext +="<tr><td style='text-align:left' class='nBox'>FIRSTLY (.)</td>";
		var dtextu="";
		for(var i=0;i<tnabc;i++) {
			var nabc="[id*='N"+i+"_";
			nb=$("[ID*=N"+i+"_]").attr("id").split("_")[0].replaceAll("N","");
			var nMr3=$(nabc+"1']").html()+" (.) "+$(nabc+"2']").html()+" (.) "+$(nabc+"4']").html();
			nMr3 += " (.) NMB (.) "+nFormatDate($(nabc+"6']").html(),'','dd-mmm-yyyy').toUpperCase()+" (.) MOV BY "+$(nabc+"8']").html()
			nMr3 += " (.) ALLOTED TO "+$(nabc+"5']").html()+" (.) ADV PARTY TO MOVE ON "+nFormatDate($(nabc+"7']").html(),'','dd-mmm-yyyy').toUpperCase() +" (.) ";
			if (i>0) {
				dtextu +="<tr><td></td>";
			}
			tptmp=$(nabc+"8']").html().toUpperCase().replaceAll(",","");
			//console.log(tptmode,"==",i,tptmp,tptmode.indexOf("TRAIN"),tptmode.indexOf("ROAD"));
			if (tptmode.indexOf(tptmp)<=-1) {
				tptmode += tptmp+" ";	
			} 
			/* if (tptmode.indexOf("ROAD")<=-1) {
				tptmode += tptmp;	
			} */			
			dtextu +="<td style='text-align:left' class='nBox'>"+nMilCount(parseInt(nb)+1)+" (.)</td>";
			dtextu +="<td style='text-align:left' colspan='4' class='nBox'> "+nMr3.toUpperCase();
		}
		dtext +=dtextu;
		dtext +="<tr><td style='text-align:left' class='nBox' colspan='6'><BR>SECONDLY (.) NATURE OF MOV (.)</td>";
		dtext +="<tr><td style='text-align:left' class='nBox'  colspan='6'><BR>THIRDLY (.)";
		var t1=$("#einstr").val();
		if (t1=='1') {
			totelist='${getEqptInstrList[0][1]}';
		}
		if (t1=='2') {
			totelist='${getEqptInstrList[0][3]}';
		}
		if (t1=='3') {
			totelist='${getEqptInstrList[0][5]}';
		}
		dtext +="<BR>"+totelist+"</td>";
		dtext +="<tr><td style='text-align:left' class='nBox'  colspan='6'><BR>FOURTHLY (.) MODE OF TPT (.) "+tptmode+" (.)";
		if (tptmode.indexOf("TRAIN")>=0) {
			var t='${getVehInstrList[0][1]}';
			dtext +="<BR>"+t;
		}
		if (tptmode.indexOf("ROAD")>=0) {
			var t='${getVehInstrList[0][3]}';
			dtext +="<BR>"+t;
		}
		dtext +="</td>";		
		dtext +="<tr><td style='text-align:left' class='nBox'  colspan='6'><BR>FIFTHLY (.) THIS MOV ORDER MAY BE DESTR AFTER SIX MONTHS ON COMPLETION OF MOV (.) SUBMIT DEP AND ARR REPORTS TO ALL CONCERNED AND MIS (CUE AND ORBAT) COMMAN MODE APPLICABLE TO  NEW LOC INCL IN ARR AND DEP REPORT (.)</td>";		
	}
	if (b=='L') {
		var nabc="[id*='N"+i+"_";
		var tnabc=${N0_0};
		//alert(tnabc);
		var nMr1=armst;
		var nMr2="APR 2021/22/23 (YEAR) TO MAR 2022 /23/24 (YEAR) / RE-ORBAT-ING / IN SITU RE-ORBAT-ING(.) REF DGMO / MO-5 LETTER NO 69777/2/MO 5 (ARTY)/B DT SEP 07 (07) ADDSD TO COMDS ONLY (NOT TO ALL)(.)";				
		var nMr4=$(nabc+"4']").html();
		dtext +="<tr><td width='33%'></td><td width='33%'></td><td width='33%'></td>";
		dtext +="<tr><td>Tele:33469</td><td></td><td>Dte Gen of Staff Duties<br>General Staff Branch (SD 4)<BR>Integrated HQ of MoD (Army)<br>DHQ PO, New Delhi - 110 011<br></td>";
		dtext +="<tr><td><br>40052 (Arty)/16-19/Ser 22/DS 4(A)</td><td></td><td>DD/MM/YYYY</td>";
		var toopt="";
		for(var i=0;i<tnabc;i++) {
			var nabc="[id*='N"+i+"_";
			toopt += $(nabc+"1']").html()+"<BR>";			
		}
		dtext +="<tr><td colspan='3'><br>"+toopt+"</td>";
		dtext +="<tr><td colspan='3'><br><center><u>EXTRACT OF "+nMr1+" RELIEF PGME : "+pdbetween+"</u><BR></td>";
		dtext +="<tr><td colspan='3'><br><br>1. Ref SD Dte / "+ltrno+" added to HQ Comds only. relevant extract in r/o your unit is as per Appx to his letter.<BR></td>";
		dtext +="<tr><td colspan='3'><br>2. Exec mov instrs will be issued separately.<BR></td>";
		dtext +="<tr><td colspan='3'><br><br><br><br><br></td>";
		dtext +="<tr><td colspan='2'></td><td>______________<BR>______________<BR>GSO 1 SD 4<br>for DCOAS (IS & T)</td>";
		dtext +="<tr><td colspan='3'><br>===================================================================================</td>";
		dtext +="<tr><td colspan='2'></td><td>Appx<br>(Refs to Para 1 of ________________ dt _______ )</td>";
		dtext +="<tr class='nrBlankLine'><td>";
		dtext +="<tr><td colspan='3'><br><center><u>EXTRACT OF "+nMr1+" RELIEF PGME : "+pdbetween+"</u><BR></td>";
		dtext +="<tr><td colspan='3'><br>";	
		dtext +="<table><tr><td class='nBox'>S.No</td><td class='nBox'>Unit</td><td class='nBox'>From</td><td class='nBox'>To</td><td class='nBox'>NMB</td><td class='nBox'>Date of Move of Adv Party</td><td class='nBox'>Mode of Tpt</td><td class='nBox'>Indn / Deindn pd to be Given</td><td class='nBox'>Remarks</td>";		
		for(var i=0;i<tnabc;i++) {
			var nabc="[id*='N"+i+"_";
			nb=$("[ID*=N"+i+"_]").attr("id").split("_")[0].replaceAll("N","");
			var nMr3="<td class='nBox'>"+$(nabc+"1']").html()+" </td><td class='nBox'>"+$(nabc+"2']").html()+"/<BR>"+$(nabc+"3']").html()+"</td><td class='nBox'>"+$(nabc+"4']").html()+"/<BR>"+$(nabc+"5']").html()+"</td>";
			nMr3 += "<td class='nBox'>"+nFormatDate($(nabc+"6']").html(),'','dd-mmm-yyyy').toUpperCase()+"</td>";
			nMr3 += "<td class='nBox'>"+nFormatDate($(nabc+"7']").html(),'','dd-mmm-yyyy').toUpperCase() +"</td>";
			nMr3 += "<td class='nBox'>"+$(nabc+"8']").html()+"</td>";
			nMr3 += "<td class='nBox'>"+$(nabc+"9']").html()+"</td>";
			nMr3 += "<td class='nBox'></td>";
			dtext +="<tr><td style='text-align:left' class='nBox'>"+(i+1)+".</td>";
			dtext +=nMr3.toUpperCase();
		}
		dtext +="</table></td>";
	}
	if (b=='H') {
		var nabc="[id*='N"+i+"_";
		var tnabc=${N0_0};
		//alert(tnabc);
		var nMr1=$(nabc+"4']").html();
		var nMr2="APR 2021/22/23 (YEAR) TO MAR 2022 /23/24 (YEAR) / RE-ORBAT-ING / IN SITU RE-ORBAT-ING(.) REF DGMO / MO-5 LETTER NO 69777/2/MO 5 (ARTY)/B DT SEP 07 (07) ADDSD TO COMDS ONLY (NOT TO ALL)(.)";				
		var toopt="";
		dtext +="<tr><td width='50%'></td><td width='50%'></td>";
		dtext +="<tr><td></td><td>Appx<br>(Ref Para 1 of ________________ letter No "+ltrno;
		dtext +="<tr><td colspan='2'><br><center><u>"+armst+" RELIEF PGME : "+pdbetween+"</u></td>";
		dtext +="<tr><td colspan='2'>";
		dtext +="<br><table><tr><td class='nBox'>S.No</td><td class='nBox'>Unit</td><td class='nBox'>From (Unit)</td><td class='nBox'>NMB (Tentative)</td><td class='nBox'>To (Unit)</td><td class='nBox'>Tentative Dt of Move of Adv Party</td><td class='nBox'>Mode of Tpt</td><td class='nBox'>Indn / Deindn pd to be Given</td><td class='nBox'>Reserve</td><td class='nBox'>Remarks</td>";		
		for(var i=0;i<tnabc;i++) {
			var nabc="[id*='N"+i+"_";
			nb=$("[ID*=N"+i+"_]").attr("id").split("_")[0].replaceAll("N","");
			var nMr3="<td class='nBox'>"+$(nabc+"1']").html()+" </td><td class='nBox'>"+$(nabc+"2']").html()+"/<BR>"+$(nabc+"3']").html()+"</td>";
			nMr3 += "<td class='nBox'>"+nFormatDate($(nabc+"6']").html(),'','dd-mmm-yyyy').toUpperCase()+"</td>";
			nMr3 += "<td class='nBox'>"+$(nabc+"4']").html()+"/<BR>"+$(nabc+"5']").html()+"</td>";			
			nMr3 += "<td class='nBox'>"+nFormatDate($(nabc+"7']").html(),'','dd-mmm-yyyy').toUpperCase() +"</td>";
			nMr3 += "<td class='nBox'>"+$(nabc+"8']").html()+"</td>";
			nMr3 += "<td class='nBox'>"+$(nabc+"9']").html()+"</td>";
			nMr3 += "<td class='nBox'></td>";
			nMr3 += "<td class='nBox'></td>";
			dtext +="<tr><td style='text-align:left' class='nBox'>"+(i+1)+".</td>";
			dtext +=nMr3;
			console.log(nMr3);
		}
		dtext +="</table></td>";
		dtext +="</table>";
	}
		//alert(dtext);
		$("#nPrintLetterDiv_tb").html(dtext);
		$("#nPrintLetterDataid").append(dtext);	
		$("#nPrintLetter").show();
}

function exportPrintLetter(b){
	
	$().tbtoWord(b);
	b.preventDefault();
}
</script>