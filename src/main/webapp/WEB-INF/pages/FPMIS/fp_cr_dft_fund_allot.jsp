<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script>
	var role = "${role}";
</script>
 <style>
.nOnlyTDDisplay {
	border:0px solid transparent;
	background-color: transparent;
}
</style>
<body onload="setMenu();">
<form:form action="cr_dft_fund_Action?${_csrf.parameterName}=${_csrf.token}" id="cr_dft_fund_Action" name="cr_dft_fund_Action"	method="post" class="form-horizontal fp-form" modelAttribute="service5">
	<div class="" width="98%"><center>
		 <div class="container" align="center">
				<div class="card">
					<div class="card-header mms-card-header" style="min-height:60px;padding-top: 10px;">
		                	<b>DRAFT FUND ALLOCATION</b>
			        </div>
					<div class="card-body card-block ncard-body-bg">   
						<div class="row">
							<div class="col-md-4">
									<div class="col-md-12">
										<input type="hidden" name="roleSusNo" id="roleSusNo"/>
										<input type="hidden" name="nPara" id="nPara" value="${conc_req}"/>									
										<label class="form-control-label"><strong style="color: red;">*</strong> Estimate Type </label>&nbsp;&nbsp;									
										<select id="est_type" name="est_type" class="form-control-sm" title="Select Estimate Type">
											<c:forEach var="item" items="${n_rpttype}" varStatus="num">
												<option value="${item[2]}">${item[3]}</option>
											</c:forEach>
										</select>
									</div>
							</div>
							<div class="col-md-4">
									<div class="col-md-12">
										<label class=" form-control-label"><strong style="color: red;">*</strong> For Financial Year</label>&nbsp;&nbsp;
										<select id="fin_year" name="fin_year" class="form-control-sm" title="Select Financial Year">
											<c:forEach var="item" items="${n_finyr}" varStatus="num">
												<c:if test='${item[0] == n_cfinyr}'>
													<option value="${item[0]}" SELECTED>${item[2]}</option>
												</c:if>
												<c:if test='${item[0] != n_cfinyr}'>
													<option value="${item[0]}">${item[2]}</option>
												</c:if>
											</c:forEach>
										</select>
									</div>
							</div>
						<div class="col-md-4">
									<div class="col-md-12">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Amount in </label>&nbsp;&nbsp;
										<select id="amt_rs" name="amt_rs" class="form-control-sm" title="Select Amount Conversion" onchange="javascript:alert('Press Below Buttons to Refresh Data');">
											<c:set var="amt_rss" value="${fn:split(n_sel,':')}"/>																							
												<option value="CR">Crores</option>												
												<option value="RS">Rupees</option>
										</select>
									</div>
						</div>							
					</div>
						<div class="row">
							<div class="col-md-12">
									<div class="col-md-6">
										<input style="float:left;" type="button" id="btn_draft" class="btn btn-primary btn-sm nGlow" value="Last Saved Draft Data" onclick="return isvalidDatad()" title="Click to get Already Saved Allocation"/>&nbsp;&nbsp;
									</div>
									<div class="col-md-6">
										<input style="float:right;" type="button" id="btn_fress" class="btn btn-success btn-sm nGlow" value="Last Approved Allotment" onclick="return isvalidData()" title="Click to Create Fresh Allocation based on Last Approval"/>
									</div>
							</div>
						</div>
					</div>
			</div>
		</div>
	</div>
	
 	  <div class="containerr" id="divPrint" width="98%">
 	  <div  class="watermarked1" data-watermark="" id="divwatermark1"  >
					<span id="ip"></span> 
 			<div class="" align="center" class="form-control-sm" >
					<div id="totbar" class="row" style="background-color:lightgray;color:blue;text-decoration: bold;width:98%;padding:5px;">
						<div class="col-md-4">
							<label class="form-control-label">Total Fund</label>&nbsp;:&nbsp;
							<input id="tfund" type="text" size="20" class="form-control-sm" value="0.00" style="float:right;text-align: right;" readonly>									
						</div>
						<div class="col-md-4">
							<label class="form-control-label">Total Allotted</label>&nbsp;:&nbsp;	
							<input id="tallot" type="text" size="20" class="form-control-sm" value="0.00"  style="float:right;text-align: right;" readonly>								
						</div>
						<div class="col-md-4">
							<label class="form-control-label">Fund Balance</label>&nbsp;:&nbsp;	
							<input id="tbal" type="text" size="20" class="form-control-sm" value="0.00"  style="float:right;text-align: right;" readonly>								
						</div>						
					</div>								
			</div>
			<!-- <div class="" class="form-control-sm" >
	 			<div id="divSerachInput" class="row" style="color:blue;text-decoration: bold;width:98%">
					<div class="col-md-4">
						<input id="searchInput1" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search in Listed Data..."  size="25" class="form-control-sm">			
					</div>
					<div class="col-md-8">
						<div class="col-md-12">
							<span id="nrTableErr" class="blinkTextBgDanger" style="width:100%;font-size:1.2vw;"><b></b></span>
						</div>
					</div>
				</div>
			</div> -->
			<div  class="watermarked" data-watermark="" id="divwatermark"  >
 			<div class="nrTableDataDiv_1 nPopTable" id="nrTableDataDiv" style="height:280px;"> 
					   <table border="1" style="width:100%" id="SearchReport">
                       		<thead style="text-align: center;line-height: 25px;font-size: 1vw;">
								<tr>
									<th style='text-align:center;width:1%;display:none'></th>
									<th style='text-align:center;width:1%;display:none'></th>
									<th style='text-align:center;width:1%;'></th>									
									<th style='text-align:center;width:25%;' colspan='2'>Code Head</th>
									<th style='text-align:center;width:25%;'>Budget Holder</th>
									<th style='text-align:center;width:12%;'>Funds Received</th>
									<th style='text-align:center;width:15%;'>Cur Yr Allocation</th>
									<th style='text-align:center;width:12%;'>Cur Yr Projection</th>
									<th style='text-align:center;width:12%;'>Prv Yr Allocation</th>
								</tr>
							</thead>
  							<tbody id="nrTable" style="font-size: .90vw;text-decoration: none;color:blue">
  							     <c:set var="nhdcd" value=""/>
  							     <c:set var="nhdcd1" value=""/>
  							     <c:set var="nhdcd2" value=""/>
  							     <c:set var="nhdcd3" value=""/>
  							     <c:set var="nhdcd4" value=""/>
  							     <input id="dftlistsize" name="dftlistsize" type="hidden" value="${fn:length(dftlist)}">
  							     <c:forEach var="item" items="${dftlist}" varStatus="num">
								<tr>
									 <c:set var="dataf" value="${fn:split(item[0],':')}"/>
									 <c:set var="data" value="${item[0].concat('_').concat(item[7]).concat('_').concat(item[8]).concat('_').concat(item[11]).concat('_').concat(item[12]).concat('_').concat(item[14]).concat('_').concat(item[15]).concat('_').concat(item[16]).concat('_').concat(item[17])}"/>
									 <c:if test='${dataf[0] != nhdcd1}'>
									 	<tr id="ln_dftlist_${num.index}" name="${item[0]}">
									 	<td id="dftlist_${num.index}_1" colspan='9' style='text-align:left;width:1%;'>${item[1]} - ${item[2]}</td>
									 	<c:set var="nhdcd1" value="${dataf[0]}"/>									 	
									 </c:if>
									 <c:if test='${dataf[1] != nhdcd2}'>
									 	<c:if test='${dataf[1] != ""}'>
										 	<tr id="ln_dftlist_${num.index}" name="${item[0]}">
										 	<td id="dftlist_${num.index}_2" colspan='9' style='text-align:left;width:1%;'>${item[3]} - ${item[4]}</td>
										 	<c:set var="nhdcd2" value="${dataf[1]}"/>
										 </c:if>
									 </c:if>
									 <c:if test='${dataf[2] != nhdcd3}'>
									 	<tr id="ln_dftlist_${num.index}" name="${item[0]}">
									 	<td id="dftlist_${num.index}_3" colspan='9' style='text-align:left;width:1%;'>&nbsp;${item[5]} - ${item[6]}</td>
									 	<c:set var="nhdcd3" value="${dataf[2]}"/>
									 </c:if>									 
									 <tr id="ln_dftlist_${num.index}" name="C${item[0]}">											 
									 	<td id="dftlist_${num.index}_0" style='text-align:left;width:1%;display:none;'>${item[0]}
									 		<input class="nOnlyTDDisplay"  type=text id="ddftlist_${num.index}_0" value='${item[0]}' readonly /></td>									 		
									 		<input class="nOnlyTDDisplay"  type="hidden" name="ddftlist_${num.index}_1" value="${data}" readonly />
									 	<td id="dftlist_${num.index}_2" style='text-align:left;width:1%;' class="nrTrans"></td>
									 	<c:if test='${dataf[3] == nhdcd4}'>
										 	<td id="dftlist_${num.index}_7" style='text-align:left;' class="nrTrans">${item[7]}
										 	</td>											 
										 	<td id="dftlist_${num.index}_8"style='text-align:left;width:15%;' class="nrTrans">${item[8]}
										 	</td>
									 	</c:if>
									 	<c:if test='${dataf[3] != nhdcd4}'>
										 	<td id="dftlist_${num.index}_7"style='text-align:left;'>${item[7]}
										 	</td>											 
										 	<td id="dftlist_${num.index}_8"style='text-align:left;width:15%;'>${item[8]}
										 	</td>										 	
									 	</c:if>									 	
									 	<td id="dftlist_${num.index}_14" style='text-align:left;display:none;'>									 		
									 	</td>											 
									 	<td id="dftlist_${num.index}_16" style='text-align:left;width:1%;display:none'>									 	
									 	</td>
									 	<td id="dftlist_${num.index}_15" style='text-align:left;width:12%;'>${item[15]}
									 	</td>									 	
									 	
										<c:if test='${dataf[3] == nhdcd4}'>
											<td id="dftlist_${num.index}_17" name="0" style='text-align:right;width:12%;'>
										 		
										 	</td>	
										</c:if>										 		
										<c:set var="naltval" value="${item[13]}"/>
										<c:if test='${dataf[3] != nhdcd4}'>
											<td id="dftlist_${num.index}_17" name='${item[17]}' style='text-align:right;width:12%;'>
										 		<input class="nOnlyTDDisplay" size="15" style="text-align:right;" type=text id="ddftlist_${num.index}_17" value="${item[17]}" readonly />
										 		<c:set var="nhdcd4" value="${dataf[3]}"/>
										 		<c:if test ='${fn:contains(item[17],"-")}'>
										 			<c:set var="naltval" value="${item[17]}"/>	
										 		</c:if>
										 	</td>	
										</c:if>									 										 	
									 	<c:set var="nhdcd" value="${item[0]}"/>									 	
									 	<td style='width:12%;bbackground-color: khaki;'>
									 		<c:if test ='${fn:length(item[7])>0}'>
									 			<input type="text" id="ddftlist_${num.index}_13" name="ddftlist_${num.index}_13" value="${naltval}" placeholder="0.00000"
									 			class="form-control-sm decimal negative w-100 text-right" size="12" onblur="showdetl();" maxlength="20" title="Enter Allocation">
									 		</c:if>
									 		<c:if test ='${fn:length(item[7])<=0}'>
									 			<input type="text" id="ddftlist_${num.index}_13" name="ddftlist_${num.index}_13" value="${naltval}" placeholder="0.00000"
									 			class="form-control-sm decimal negative text-right" size="12" style="display:none;" onblur="showdetl();" maxlength="20" readonly>
									 		</c:if>
									 	</td>									 	
									 <td id="dftlist_${num.index}_12" name='${item[12]}' style='text-align:right;width:12%;'>${item[12]}									
									 </td>
									 <td id="dftlist_${num.index}_11" name='${item[11]}' style='text-align:right;width:12%;'>${item[11]}									 	
									 </td>
									  <c:set var="rsfmt" value="${item[19]}"/>
									</td>
								</tr>
							</c:forEach>
							<script>
								//alert('${rsfmt}');
								$("#amt_rs").val('${rsfmt}');
							</script> 
  							</tbody>
  							</table>
						</div>
						</div>
						<div class="card-footer" align="center" class="form-control">
							<div id="divSerachInput" class="row" style="color:blue;text-decoration: bold;width:98%">
								<div class="col-md-8">
									<div class="col-md-4">
										<input id="searchInput1" type="text" style="text-align:left;font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search in Listed Data..."  size="25" class="form-control-sm">
									</div>
									<div class="col-md-8">
										<span id="nrTableErr" class="blinkTextBgDanger" style="width:100%;font-size:1.2vw;padding:7px;"><b></b></span>
									</div>
								</div>
								<div class="col-md-4">
									<div class="col-md-12">
										<!-- <span id="nrTableErr" class="blinkTextBgDanger" style="width:100%;font-size:1.2vw;"><b></b></span> -->
										<input type="submit" id="btn_save" class="btn btn-success btn-sm nGlow" value="Save Draft Allocation" title="Draft Copy Save"/>
									</div>
								</div>
							</div>
							
						</div>
		</div>
		</div>
</form:form>
<c:url value="GetDftFundData?${_csrf.parameterName}=${_csrf.token}" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="service1">
	<input type="hidden" name="sus1" id="sus1">
	<input type="hidden" name="est_type2" id="est_type2">
	<input type="hidden" name="fin_year2" id="fin_year2">
	<input type="hidden" name="para1" id="para1">
	<input type="hidden" name="para2" id="para2">
</form:form>

<c:url value="UploadDataUpd?${_csrf.parameterName}=${_csrf.token}" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="service2">
	<input type="hidden" name="updid" id="updid">
	<input type="hidden" name="cur_allot2" id="cur_allot2">
	<input type="hidden" name="sus11" id="sus11">
	<input type="hidden" name="est_type21" id="est_type21">
	<input type="hidden" name="fin_year21" id="fin_year21">
	<input type="hidden" name="para11" id="para11">	
</form:form> 


<script>

var roleSusNo="${roleSusNo}";

var dftlist=[];

function isvalidData(){
	if ($("#est_type").val() == "" || $("#est_type").val() == "-1") {
		alert("Please Select Estimate Type.");
		$("#est_type").focus();
		return false;
	}

	if ($("#fin_year").val() == "") {
		alert("Please Select Financial Year.");
		$("#fin_year").focus();
		return false;
	}
	var fin_year1=$("#fin_year").val();
	var est_type1=$("#est_type").val();
	var nPara = "FRESS";
	var nPara2 = $("#amt_rs").val();
	
	var a=confirm("Previously Draft Allocation will be Deleted after Saving this Data.\nPress OK to Continue..");
	if (a==true) {		
		$("#para1").val(nPara);
		$("#para2").val(nPara2);
		$("#est_type2").val(est_type1);
		$("#fin_year2").val(fin_year1);
		$("#sus1").val(roleSusNo);
		$("#searchForm").submit();
	}
}

function isvalidDatad(){
	if ($("#est_type").val() == "" || $("#est_type").val() == "-1") {
		alert("Please Select Estimate Type.");
		$("#est_type").focus();
		return false;
	}

	if ($("#fin_year").val() == "") {
		alert("Please Select Financial Year.");
		$("#fin_year").focus();
		return false;
	}
	var fin_year1=$("#fin_year").val();
	var est_type1=$("#est_type").val();
	var nPara = "DRAFT";
	var nPara2 = $("#amt_rs").val();
	$("#para1").val(nPara);
	$("#para2").val(nPara2);
	$("#est_type2").val(est_type1);
	$("#fin_year2").val(fin_year1);
	$("#sus1").val(roleSusNo);
	
	$("#searchForm").submit();
}

function editData(d){
	$("#updid").val(d);
	var cc = $("input#cur_allot"+d).val();
	$("#cur_allot2").val(cc);
	
	var fin_year11=$("#fin_year").val();
	var est_type11=$("#est_type").val();
	var nPara1 = $("#nPara").val();
	
	$("#para11").val(nPara1);
	$("#est_type21").val(est_type11);
	$("#fin_year21").val(fin_year11);
	$("#sus11").val(roleSusNo);
	
	$("#updateForm").submit();
}

function finalsubmit(d){
	var b=confirm('You are Submitting Final Copy of Allotment.\nNo further Amendment / Modification will be allowed.');
	if (b==true) {
		var fin_years=$("#fin_year").val();
		var est_types=$("#est_type").val();
		var nPara1 = $("#nPara").val();	
		$("#paras").val(nPara1);
		$("#est_types").val(est_types);
		$("#fin_years").val(fin_years);
		$("#finalsubmt").submit();
	}
}


function showdetl() {
		var bval=$("#tbal").attr("name");
		if (bval<0) {
			alert("sorry");
			return false;			
		} else {
			calbal();
		}
}

function calbal() {
	var tln=$("#dftlistsize").val();
	var bval=$("#tbal").val();
	var amtcr=$("#amt_rs").val();
	
	if (amtcr=='CR') {
		var amtcrv=5;
	} else {
		var amtcrv=2;
	}
	rsconv=amtcr;
	//$("#amt_rs").val(amtcr);
	$("#nrWaitLoader").show();
		var totfund=0;
		var totallot=0;
		var totproj=0;
		var totprev=0;
		var cproj=0;
		var cprev=0;
		var callot=0;
		var tbbal=0;
		var ctrhd="";
		var trhd="";
		var etrhd="";
		var etrhd1="";
		var ttrhdfund=0;
		var ttrhdalt=0;
		var tothdfund=0;
		var tothdallot=0;
		var toterr=0;
		var ttf="";
		for (var i = 0; i < tln; i++) {
			ctrhd = $("#ddftlist_"+i+"_0").val().replaceAll(',','');
			if (roleSusNo !='A000001') {
				if ((ctrhd != trhd) || (i==tln-1)) {					
					ttrhdfund=tothdfund;
					ttrhdalt=tothdallot;
					
					if (ttrhdalt>ttrhdfund) {
						etrhd="C"+$("#ddftlist_"+(i-1)+"_0").val().replaceAll(',','');				
						etrhd1=$("#ddftlist_"+(i-1));
						$("[name*='"+etrhd+"']").css({'background-color':'#dc3545','color':'yellow'});
						$("[id*='"+etrhd1+"']").css({'background-color':'#dc3545','color':'yellow'});						
						toterr++;
					} else {
						$("[name*='"+ctrhd+"']").css({'background-color':'white','color':'black'});
						$("[id*='#ddftlist_"+(i)+"']").css({'background-color':'white','color':'black'});
					}
					
					
					if ((i==tln-1)) {
						
						ttf=parseFloat($("#ddftlist_"+(i)+"_17").val());
						
						if (!isNaN(ttf) && ttf.length>0) {
							ttrhdfund= ttrhdfund + parseFloat(ttf.replaceAll(',',''));
						}
						
						ttrhdalt= parseFloat($("#ddftlist_"+(i)+"_13").val().replaceAll(',',''));
						ttrhdfund = ttf;
						
						if (ttrhdalt>ttrhdfund) {
							
							etrhd="C"+$("#ddftlist_"+(i)+"_0").val().replaceAll(',','');				
							etrhd1=$("#ddftlist_"+(i));
							$("[name*='"+etrhd+"']").css({'background-color':'#dc3545','color':'yellow'});
							$("[id*='"+etrhd1+"']").css({'background-color':'#dc3545','color':'yellow'});						
							toterr++;
						} else {
							$("[name*='"+ctrhd+"']").css({'background-color':'white','color':'black'});
							$("[id*='#ddftlist_"+(i)+"']").css({'background-color':'white','color':'black'});
						}
					}
					tothdfund=0;
					tothdallot=0;	
					trhd = ctrhd;
				} else {
					$("[name*='"+ctrhd+"']").css({'background-color':'white','color':'black'});
					$("[id*='#ddftlist_"+(i)+"']").css({'background-color':'white','color':'black'});
				}
			}
			totfund0=$("#dftlist_"+i+"_17").attr("name").replaceAll(',','');
			totfund = totfund + parseFloat(totfund0);
			tothdfund = tothdfund + parseFloat(totfund0);
			$("#ddftlist_"+i+"_17").val(Number(totfund0).toINR('',rsconv,'INR',rsconv));
			callot0=$("#ddftlist_"+i+"_13").val().replaceAll(',','');
			callot=parseFloat(callot0);
			$("#dftlist_"+i+"_13").css({'background-color':'transparent','color':'black'});
			totallot = totallot +callot;
			tothdallot = tothdallot +callot;
			cproj0=$("#dftlist_"+i+"_12").html().replaceAll(',','');
			cproj=parseFloat(cproj0);
			$("#ddftlist_"+i+"_12").val(Number(cproj).toINR('',rsconv,'INR',rsconv));			
			$("#dftlist_"+i+"_12").css({'background-color':'transparent','color':'black'});
			totproj = totproj + cproj;
			cprev0=$("#dftlist_"+i+"_11").html().replaceAll(',','');
			cprev=parseFloat(cprev0);		
			$("#ddftlist_"+i+"_11").val(Number(cprev).toINR('',rsconv,'INR',rsconv));					
			$("#dftlist_"+i+"_11").css({'background-color':'transparent','color':'black'});
			totprev = totprev + cprev;			
			$("#dftlist_"+i).css({'background-color':'transparent','color':'black'});
			chkfundval(""+i,callot,cprev,cproj);
		}
		if (toterr>0) {
			$("#nrTableErr").text(" Some Calculation Error found in Head Code Highlighted in Red. ");
		} else {
			$("#nrTableErr").text("");
		}
		console.log(totfund);
		if (!isNaN(totfund)) {
			$("#tfund").val(Number(totfund).toINR('',rsconv,'INR',rsconv));
			tbbal=totfund;
		} else {
			$("#tfund").val(Number(0).toINR('',rsconv,'INR',rsconv));
		}
		if (!isNaN(totallot)) {
			$("#tallot").val(Number(totallot).toINR('',rsconv,'INR',rsconv));
			tbbal= tbbal - totallot;			
		} else {
			$("#tallot").val(Number(0).toINR('',rsconv,'INR',rsconv));
		}
		$("#tbal").val(Number(tbbal).toINR('',rsconv,'INR',rsconv));
		if (tbbal<0) {			
			$("#totbar").css('background-color','#dc3545');
			$("#btn_save").hide();
		} else {
			$("#totbar").css('background-color','lightgray');
			$("#btn_save").show();
		}		
		if (toterr>0 || tbbal<0) {
			$("#btn_save").hide();
		} else {
			$("#btn_save").show();
		}		
		$("#nrWaitLoader").hide();
}

function chkfundval(a,b,c,d){
	var cid="";
	var toterr=0;
	if (c>0 || d>0) {
		if ((c>d*1.1) || (c<d*.9) || (d>c*1.1) || (d<c*.9)) {			
			cid="#dftlist_"+a+"_12";
			cid1="#ddftlist_"+a+"_12";
			$(cid).css({'background-color':'#dc3545','color':'yellow'});
			$(cid1).css({'background-color':'#dc3545','color':'yellow'});
			cid="#dftlist_"+a+"_11";
			cid1="#ddftlist_"+a+"_11";
			$(cid).css({'background-color':'#dc3545','color':'yellow'});
			$(cid1).css({'background-color':'#dc3545','color':'yellow'});
			toterr=1;			
		}
		if (toterr>0) {
			$("#btn_save").hide();
		} else {
			$("#btn_save").show();
		}	
	} 
}

$(document).ready(function() {

	$(".nrTrans").css("color","Transparent");
	$("#searchInput1").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#nrTable tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
		if ($("#searchInput1").val().length>0) {
			$(".nrTrans").css("color","navy");
		} else {
			$(".nrTrans").css("color","Transparent");
		}
	});
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var a='${dftlist[0]}';
	if (a==null || a=='') {
		$('#divPrint').hide();
	} else {
		calbal();
	}
	
	var y='${n_sel}';
	if (y !='') {
		var yy=y.split(":");
		$("#amt_rs").val(yy[3]);
		$("#est_type").val(yy[0]);
		$("#fin_year").val(yy[1]);
	} else {
		$("#amt_rs").val('CR');
	}
	var gz='${rsfmt}';
	if (gz !='') {
		$("#amt_rs").val(gz);
		//alert("draft back-"+gz);
	}
});

$("[idd ^='ddftlist_']").on("keyup", function() {
	var x = $(this).val();
	$(this).val(nFmtNumInput(x));
});

function nFmtNumInput(x) {
	var parts=x.toString().split(".");
	parts[0]=parts[0].replace(/\B(?=(\d{3})+(?!\d))/g,",");	
	return parts.join(".");
}
</script>