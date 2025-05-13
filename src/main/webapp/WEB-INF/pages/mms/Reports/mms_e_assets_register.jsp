<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<script>
var username="${username}";
var curDate = "${curDate}";
</script>



<body class="mmsbg">
	<% int ntotln=0; %>
	<div id="nrWaitLoader" style="display: none;" align="center">
		<span id="">Processing Data.Please Wait ...</span>
	</div>
	<form:form action="" method="post" enctype="multipart/form-data"
		class="form-horizontal">
		<div class="nkpageland" id="printableArea">
			<div>
				<div class="container" align="center" id="headerView"
					style="display: block;">

					<div class="card">
						<div class="card-header mms-card-header">
							<b>e-Asset Register</b>
						</div>

						<div class="card-body card-block">
							<div class="col-md-12 row form-group">
								<div class="col-md-2" style="text-align: left;">
									<label for="text-input" class=" form-control-label">PRF
										Group</label>
								</div>

								<div class="col-md-2" style="text-align: left;">
									<input type="text" id="from_prf_Search" name="from_prf_Search"
										placeholder="Search..." class="form-control-sm form-control"
										autocomplete="off" size="10" maxlength="10" />
								</div>

								<div class="col-md-1" style="text-align: left;">
									<img src="js/miso/images/searchImg.jpg" width="30px;"
										height="30px;" onclick="getfromprf();" title="Click to Search"
										style="cursor: pointer;">
								</div>

								<div class="col-md-7" style="text-align: left;">


									<select name="prf_code" id="prf_code"
										class="form-control-sm form-control" disabled="disabled">
										<option value="ALL">--All PRF Groups --</option>
										<c:forEach var="item" items="${m_11}">
											<option value="${item[0]}" name="${item[1]}">${item[0]}
												- ${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>

							<div class="col-md-12 row form-group" style="margin-top: -10px;">
								<div class="col-md-2" style="text-align: left;">
									<label for="text-input" class=" form-control-label">Type
										of Holding</label>
								</div>
								<div class="col-md-5">
									<select name="type_of_hldg" id="type_of_hldg"
										class="form-control-sm form-control">
										<option value="ALL">--ALL Type of Holding-</option>
										<c:forEach var="item" items="${ml_2}">
											<option value="${item[0]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>

								<div class="col-md-2" style="text-align: right;">
									<label class=" form-control-label">Month</label>
								</div>
								<div class="col-md-3">
									<input type="month" id="mnth_year" name="mnth_year"
										class="form-control-sm form-control" />
								</div>
							</div>

							<c:if test="${r_1[0][0] ge '4'}">
								<div class="col-md-12 row form-group" style="margin-top: -10px;">
									<c:if test="${r_1[0][0] ge '5'}">
										<div class="col-md-2" style="text-align: left;">
											<label for="text-input" class=" form-control-label">Command</label>
										</div>
										<div class="col-md-4">
											<select name="command_code" id="command_code"
												class="form-control-sm form-control">
												<c:if test="${r_1[0][1] != 'COMMAND'}">
													<option value="ALL">-- All Command --</option>
												</c:if>
												<c:if test="${not empty ml_1[0]}">
													<c:set var="data" value="${ml_1[0]}" />
													<c:set var="datap" value="${fn:split(data,',')}" />
													<c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
														<c:set var="dataf" value="${fn:split(datap[j],':')}" />
														<option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
													</c:forEach>
												</c:if>


											</select>
										</div>
									</c:if>

									<c:if test="${r_1[0][0] ge '4'}">
										<div class="col-md-2" style="text-align: left;"
											style="margin-top: -10px;">
											<label for="text-input" class=" form-control-label">Corps</label>
										</div>
										<div class="col-md-4">
											<select name="corps_code" id="corps_code"
												class="form-control-sm form-control">
												<c:if test="${r_1[0][1] != 'CORPS'}">
													<option value="ALL">-- All Corps --</option>
												</c:if>
												<c:set var="data" value="${ml_3[0]}" />
												<c:set var="datap" value="${fn:split(data,',')}" />
												<c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
													<c:set var="dataf" value="${fn:split(datap[j],':')}" />
													<option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
												</c:forEach>
											</select>
										</div>
									</c:if>
								</div>
							</c:if>

							<c:if test="${r_1[0][0] ge '2'}">
								<div class="col-md-12 row form-group" style="margin-top: -10px;">
									<c:if test="${r_1[0][0] ge '3'}">
										<div class="col-md-2" style="text-align: left;">
											<label for="text-input" class=" form-control-label">Div</label>
										</div>
										<div class="col-md-4">
											<select name="div_code" id="div_code"
												class="form-control-sm form-control">
												<c:if test="${r_1[0][1] != 'DIVISION'}">
													<option value="ALL">-- All Div --</option>
												</c:if>

												<c:set var="data" value="${ml_4[0]}" />
												<c:set var="datap" value="${fn:split(data,',')}" />

												<c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
													<c:set var="dataf" value="${fn:split(datap[j],':')}" />
													<option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
												</c:forEach>


											</select>
										</div>
									</c:if>

									<c:if test="${r_1[0][0] ge '2'}">
										<div class="col-md-2"
											style="text-align: left; margin-top: -10px;">
											<label for="text-input" class=" form-control-label">Bde</label>
										</div>
										<div class="col-md-4">
											<select name="bde_code" id="bde_code"
												class="form-control-sm form-control">
												<c:if test="${r_1[0][1] != 'BRIGADE'}">
													<option value="ALL">-- All Bde --</option>
												</c:if>

												<c:set var="data" value="${ml_5[0]}" />
												<c:set var="datap" value="${fn:split(data,',')}" />

												<c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
													<c:set var="dataf" value="${fn:split(datap[j],':')}" />
													<option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
												</c:forEach>


											</select>
										</div>
									</c:if>
								</div>
							</c:if>

							<div class="col-md-12 row form-group" style="margin-top: -10px;">
								<div class="col-md-2" style="text-align: left;">
									<label class=" form-control-label">SUS No</label>
								</div>
								<div class="col-md-2">
									<input type="text" id="sus_no1" name="sus_no1"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off"
										title="Type SUS No or Part of SUS No to Search" />
								</div>

								<div class="col-md-2" style="text-align: right;">
									<label for="text-input" class=" form-control-label">Unit
										Name</label>
								</div>
								<div class="col-md-6">
									<input type="text" id="unit_name1" name="unit_name1"
										class="form-control-sm form-control" placeholder="Search..."
										autocomplete="off"
										title="Type Unit Name or Part of Unit Name to Search">
								</div>
							</div>
						</div>

						<div class="card-footer" align="center" style="margin-top: -10px;">
							<input type="button" class="btn btn-success btn-sm"
								onclick="return getmmsUeUhist();"
								value="&nbsp;&nbsp;&nbsp;List Eqpt Data&nbsp;&nbsp;&nbsp;">
						</div>
					</div>
				</div>
			</div>

			<div id="tdheaderView" style="display: none;" align="center"
				class="nrTableHeading">
				<b>e-Asset Register</b>
			</div>
			<div class="card" id="unit_hid2"
				style="display: none; background: transparent;">
				<div width="100%">

					<div class="nrTableMain2Search" align="left" id="SearchViewtr">
						<label>Search in Result(<span id="ntotln"></span>)
						</label>&nbsp;:&nbsp; <input id="nrInput" type="text"
							placeholder="Search..." size="35"
							style="font-weight: normal; font-size: 14px;">
					</div>

				</div>

				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<div id="" class="nrTableMainDiv">

						<input type="hidden" id="selectedid" name="selectedid"> <input
							type="hidden" id="nrSrcSel" name="nrSrcSel">

						<div class="nrTableDataDiv">

							<table border="1" class="report_print" width="100%">
								<thead style="text-align: center;">
									<tr>
										<th width="2%" style="text-align: center;">Sr No</th>
										<th width="5%">Material No</th>
										<th width="7%">Census No</th>
										<th width="12%">Nomenclature</th>
										<th width="6%">Induction Year</th>
										<th width="6%">Batch No</th>
										<th width="5%">Cost</th>
										<th width="5%">Total Qty</th>
										<th width="7%">Total Cost</th>
										<th width="7%">Appre / Depreciation</th>
										<th width="7%">Book Value</th>
										<th width="9%">Remarks</th>
									</tr>
								</thead>
								<tbody id="nrTable">
									<c:if test="${m_1[0][0] == 'NIL'}">
										<tr class='nrSubHeading'>
											<td colspan='13' style='text-align: center;'>Data Not
												Available...</td>
											<% ntotln=0; %>
										</tr>
									</c:if>

									<% int j = 1; %>
									<c:if test="${m_1[0][0] != 'NIL'}">
										<c:forEach var="item" items="${m_1}" varStatus="num">
											<tr class='nrTableLineData'>
												<td width="2%" style="text-align: center;"><%=j %></td>
												<td width="5%" style="text-align: center;">${item[0]}</td>
												<td width="7%" style="text-align: left;">${item[1]}</td>
												<td width="12%" style="text-align: left;">${item[2]}</td>
												<td width="6%" style="text-align: center;">${item[3]}</td>
												<td width="6%" style="text-align: center;">${item[4]}</td>
												<td width="5%" style="text-align: center;">${item[5]}</td>
												<td width="5%" style="text-align: center;">${item[6]}</td>
												<td width="7%" style="text-align: center;">${item[7]}</td>
												<td width="7%" style="text-align: center;">${item[8]}</td>
												<td width="7%" style="text-align: center;">${item[9]}</td>
												<td width="9%" style="text-align: center;">${item[10]}</td>
												<% j = j+1;%>
												<% ntotln++; %>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>

					<div class="card-footer">
						<input type="button" class="btn btn-success btn-sm" value="Export"
							id="btn_e" style="background-color: purple;"
							onclick="exportData('.nrTableDataDiv');"> <input
							type="button" class="btn btn-primary btn-sm" value="Print Page"
							id="btn_p" onclick="printDiv('.nrTableDataDiv');"> <input
							type="button" id="ereg" name="ereg" value="Print e-Register"
							class="btn btn-primary btn-sm" onclick="printEAsset();"
							style="float: right;">
					</div>
				</div>
			</div>
		</div>
	</form:form>

	<c:url value="EAssetReglist" var="backUrl" />
	<form:form action="${backUrl}" method="post" id="m4_unit1"
		name="m4_unit1" modelAttribute="m4_c_code">
		<input type="hidden" name="m4_c_code" id="m4_c_code" />
		<input type="hidden" name="m4_q_code" id="m4_q_code" />
		<input type="hidden" name="m4_d_code" id="m4_d_code" />
		<input type="hidden" name="m4_b_code" id="m4_b_code" />
		<input type="hidden" name="m4_p_code" id="m4_p_code" />
		<input type="hidden" name="m4_d_from" id="m4_d_from" />
		<input type="hidden" name="m4_d_to" id="m4_d_to" />
		<input type="hidden" name="m4_hldg" id="m4_hldg" />
		<input type="hidden" name="m4_prfs" id="m4_prfs" />
		<input type="hidden" name="m4_susno" id="m4_susno" />
		<input type="hidden" name="m4_unitname" id="m4_unitname" />
	</form:form>

	<c:url value="PrinEAssetReglist" var="printUrl" />
	<form:form action="${printUrl}" method="post" id="p_unit1"
		name="p_unit1" modelAttribute="p_c_code" target="result">
		<input type="hidden" name="p_c_code" id="p_c_code" />
		<input type="hidden" name="p_q_code" id="p_q_code" />
		<input type="hidden" name="p_d_code" id="p_d_code" />
		<input type="hidden" name="p_b_code" id="p_b_code" />
		<input type="hidden" name="p_p_code" id="p_p_code" />
		<input type="hidden" name="p_hldg" id="p_hldg" />
		<input type="hidden" name="p_d_from" id="p_d_from" />
		<input type="hidden" name="p_d_to" id="p_d_to" />
		<input type="hidden" name="p_susno" id="p_susno" />
		<input type="hidden" name="p_unitname" id="p_unitname" />
		<input type="hidden" name="printMCRId" id="printMCRId" />
	</form:form>

	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
	<script type="text/javascript" src="js/common/commonmethod.js"></script>
	<script type="text/javascript" src="js/cue/cueWatermark.js"></script>

	<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

	<script>
$("#sus_no1").keyup(function(){
	var sus_no = this.value;
	var para = "";
	var paravalue="";
	
	var c_code=$("#command_code").val();
	var q_code=$("#corps_code").val();
	var d_code=$("#div_code").val();
	var b_code=$("#bde_code").val();
	
	if(c_code != "ALL" && c_code != undefined){
		paravalue=c_code.substring(0,1);
	}
	if(q_code != "ALL" && q_code != undefined){
		paravalue=q_code.substring(0,3);;
	}
	if(d_code != "ALL" && d_code != undefined){
		paravalue=d_code.substring(0,6);;
	}	
	if(b_code != "ALL" && b_code != undefined){
		paravalue=b_code;
	}
	$().Autocomplete2('POST','getMMSRList',sus_no1,{a:sus_no,b:para,c:"SUS",d:paravalue},'getMMSUnitNameBySUSNo',unit_name1);
});

$("#unit_name1").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	
	var c_code=$("#command_code").val();
	var q_code=$("#corps_code").val();
	var d_code=$("#div_code").val();
	var b_code=$("#bde_code").val();
	
	if(c_code != "ALL" && c_code != undefined){
		paravalue=c_code.substring(0,1);
	}
	if(q_code != "ALL" && q_code != undefined){
		paravalue=q_code.substring(0,3);;
	}
	if(d_code != "ALL" && d_code != undefined){
		paravalue=d_code.substring(0,6);;
	}	
	if(b_code != "ALL" && b_code != undefined){
		paravalue=b_code;
	}
	$().Autocomplete2('POST','getMMSRList',unit_name1,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',sus_no1);
});

function exportData(b){

	$().tbtoExcel(b);
	b.preventDefault();
}

/* function printDiv(a){
	$().getPrintDiv(a,'e-Asset Register');
}
 */
 
 function printDiv() {
	 
		
	 
	  $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#btn_modify").hide();
	  $("#ereg").hide();
	 
	//let popupWinindow
	let innerContents = document.getElementById('printableArea').innerHTML;
		 
	popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	popupWindow.document.open();
	//popupWinindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"></head><body onload="window.print();window.close();">' + innerContents + '</html>');
	popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	popupWindow.document.close();
	$("#SearchViewtr").show();
	$("#tdheaderView").hide();
	$("#headerView").show();
	$("#btn_e").show();
	$("#btn_p").show();
	$("#btn_modify").show();
	$("#ereg").show();

//document.location.reload();


}

 
function getfromprf(){
	var nParaValue = $("#from_prf_Search").val();
	if(nParaValue.length<=0) {
		alert("Enter Search Word...");
	    return false;
	}else{
		$.post("getMMSPRFtListBySearch?"+key+"="+value,{nParaValue:nParaValue}, function(j) {
			if(j.length <= 0 || j == null || j == ''){
				alert("No Search Result Found");
	 			$("#from_prf_Search").focus();
	 		}else{
	 			$("#prf_code").attr('disabled',false);
	 			var options = '<option value="' + "ALL" + '">'+ "--All PRF Groups --" + '</option>';
				
	 			var a = [];
	 			var enc = j[j.length-1].substring(0,16);
	 			for(var i = 0; i < j.length; i++){
					a[i] = dec(enc,j[i]);
                }
	 			
				var data=a[0].split(",");
				var datap;
				for ( var i = 0; i < data.length-1; i++) {
					datap=data[i].split(":");
					if (datap!=null) {
						if (datap[1].length>60) {
							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1].substring(1,60)+ '</option>';
						} else {
							options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >'+ datap[0]+ ' - '+ datap[1]+ '</option>';
						}
					}
				}	
				$("select#prf_code").html(options); 
				var q = '${m_6}';
				if(q != ""){
					$("#prf_code").val(q);
				}
	 		}
		});
	}
}

/* function bindcommand(code,codelevel){
	$.post("getMMSDistinctHirarName?"+key+"="+value,{nHirar : "COMMAND", nCnd:code, codelevel:codelevel}, function(j) {
		var options = '<option value="ALL">-- All Command --</option>';
		
		var a = [];
		var enc = j[j.length-1].substring(0,16);
			for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
        }
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++){
			datap=data[i].split(":");
			options += '<option value="'+datap[0]+'" name="' + datap[2]+ '" >' + datap[2]+ '</option>';	
		}
		
		$("select#command_code").html(options);
		$("#sus_no1").val('');
		$("#unit_name1").val('');
	});
} */

function bindcoorp(code,codelevel){
	$.post("getMMSDistinctHirarName?"+key+"="+value,{nHirar : "CORPS", nCnd:code, codelevel:codelevel}, function(j) {
		var options = '<option value="ALL">-- All Corps --</option>';
		
		var a = [];
		var enc = j[j.length-1].substring(0,16);
			for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
        }
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++){
			datap=data[i].split(":");
			options += '<option value="'+datap[0]+'" name="' + datap[2]+ '" >' + datap[2]+ '</option>';	
		}
		
		$("select#corps_code").html(options);
		$("#sus_no1").val('');
		$("#unit_name1").val('');
	});
}

function binddiv(code,codelevel){
	$.post("getMMSDistinctHirarName?"+key+"="+value,{nHirar : "DIVISION", nCnd:code, codelevel:codelevel}, function(j) {
		var options = '<option value="ALL">-- All Div --</option>';
		
		var a = [];
		var enc = j[j.length-1].substring(0,16);
			for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
        }
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++){
			datap=data[i].split(":");
			options += '<option value="'+datap[0]+'" name="' + datap[2]+ '" >' + datap[2]+ '</option>';	
		}
		
		$("select#div_code").html(options);
		$("#sus_no1").val('');
		$("#unit_name1").val('');
	});
}

function bindbde(code,codelevel){ 
	$.post("getMMSDistinctHirarName?"+key+"="+value,{nHirar : "BRIGADE", nCnd:code, codelevel:codelevel}, function(j) {	
		var options = '<option value="ALL">-- All Bde --</option>';
		
		var a = [];
		var enc = j[j.length-1].substring(0,16);
			for(var i = 0; i < j.length; i++){
			a[i] = dec(enc,j[i]);
        }
		var data=a[0].split(",");
		var datap;
		
		for(var i = 0; i < data.length-1; i++){
			datap=data[i].split(":");
			options += '<option value="'+datap[0]+'" name="' + datap[2]+ '" >' + datap[2]+ '</option>';	
		}	
		
		$("select#bde_code").html(options);
		$("#sus_no1").val('');
		$("#unit_name1").val('');
	});
}

function getmmsUeUhist(){
	
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	
	if($("#mnth_year").val() > c_d){
		$("#mnth_year").focus();
		alert("Can't select the Future Month & Year");
		return false;
	}
	
	$("#m4_c_code").val($("#command_code").val());
	$("#m4_q_code").val($("#corps_code").val());
	$("#m4_d_code").val($("#div_code").val());
	$("#m4_b_code").val($("#bde_code").val());
	$("#m4_p_code").val($("#prf_code").val());
	$("#m4_d_from").val($("#date_from").val());
	$("#m4_d_to").val($("#date_to").val());
	$("#m4_hldg").val($("#type_of_hldg").val());
	$("#m4_prfs").val($("#from_prf_Search").val());
	$("#m4_susno").val($("#sus_no1").val());	
	$("#m4_unitname").val($("#unit_name1").val());
	$("#nrWaitLoader").show();
	$("#m4_unit1").submit();	
}

function printEAsset(){
	var x = screen.width/2 - 1100/2;
	var y = screen.height/2 - 900/2;
	popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
    window.onfocus = function (){ 
	} 
    
    $("#p_c_code").val($("#command_code").val());
	$("#p_q_code").val($("#corps_code").val());
	$("#p_d_code").val($("#div_code").val());
	$("#p_b_code").val($("#bde_code").val());
	$("#p_p_code").val($("#prf_code").val());
	$("#p_hldg").val($("#type_of_hldg").val());
	$("#p_susno").val($("#sus_no1").val());	
	$("#p_unitname").val($("#unit_name1").val());
	$("#p_d_from").val("");
	$("#p_d_to").val("");
	$("#p_unit1").submit();
}
</script>

	<script>
$(document).ready(function() {
	
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	
	$().getMthYr(mnth_year);
	
	var y0 = '${r_1[0][1]}';
	if(y0 == "UNIT"){
		$("#sus_no1").val('${r_1[0][2]}');
	    $("#unit_name1").val('${r_1[0][3]}');
	}else{
		var a_sus = '${a_sus}';
		var a_unit = '${a_unit}';
		
		if(a_sus != ""){
			$("#sus_no1").val(a_sus);
		}
		
		if(a_unit != ""){
			$("#unit_name1").val(a_unit);
		}
	}
	
	var cc = '${m_2}';
	if(cc != ""){
		$("#command_code").val(cc);
	}
	
	var qc = '${m_3}';
	if(qc != ""){
		$("#corps_code").val(qc);
	}
	
	var dc = '${m_4}';
	if(dc != ""){
		$("#div_code").val(dc);
	}
	
    var bc = '${m_5}';
    if(bc != ""){
    	$("#bde_code").val(bc);
    } 
	
	var pf = '${m_6}';
	if(pf != ""){
		$("#prf_code").val(pf);
	}
	
	var mt = '${m_7}';
	if(mt != ""){
		$("#mnth_year").val(mt);
	}else{
		$().getMthYr(mnth_year);
	}
	
	var hl = '${m_9}';
	if(hl != ""){
		$("#type_of_hldg").val(hl);
	}
	
	var prfs = '${m_10}';
	if(prfs != ""){
		$("#from_prf_Search").val(prfs);
		getfromprf('${m_10}');
	}
	
	var y1 = '${m_1[0][0]}';
	if(y1 != "" || '${m_1[0]}'.length > 0){
		$("#unit_hid2").show();
		nrSetWaterMark(<%=ntotln%>);
		$("#ntotln").text(<%=ntotln%>);
	} 
	

	
	$('#command_code').change(function(){
		var cmdcd=this.value;

		if (cmdcd == "ALL"){
			bindcoorp("ALL","COMMAND");
			binddiv("ALL","COMMAND");
			bindbde("ALL","COMMAND");		
		}else{
			bindcoorp(cmdcd,"COMMAND");
			binddiv(cmdcd,"COMMAND");
			bindbde(cmdcd,"COMMAND");	
		} 
	});   
	
	$('#corps_code').change(function(){
		var cmdcd=$("#command_code").val();
		var corcd=this.value;

		if (corcd=="ALL") {
			corcd=cmdcd;
			binddiv(corcd,"COMMAND");
			bindbde(corcd,"COMMAND");	
		}else{
			binddiv(corcd,"CORPS");
			bindbde(corcd,"CORPS");	
		}
	});   
	
	$('#div_code').change(function(){
		var cmdcd=$("#command_code").val();
		var corcd=$("#corps_code").val();
		var divcd=this.value;
		
		if(divcd=="ALL"){
			if (corcd=="ALL") {
				divcd=cmdcd;
				bindbde(divcd,"COMMAND");	
			} else {
				divcd=corcd;
				bindbde(divcd,"CORPS");
			}
		}else{
			bindbde(divcd,"DIVISION");	
		}
	});   
	
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
	    $("#nrTable tr").filter(function() {
	    	$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
    });

   
});
</script>