<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">

<body class="mmsbg">
	<% int ntotln=0; %>
	<div id="nrWaitLoader" style="display: none;" align="center">
		<span id="">Processing Data.Please Wait ...</span>
	</div>
		<div class="nkpageland" id="printableArea">
			<div onFocus="parent_disable1();" onclick="parent_disable1();">
				<div class="col-md-12" align="center" id="headerView" style="display: block;">
					<div class="card" style="margin-bottom: 0.5rem;">
						<div class="card-header mms-card-header">
							<b>MCR TRANSACTION/OBSERVATION SCREEN</b>
						</div>
						<div class="card-body card-block">
							<div class="col-md-12 row form-group">
								<div class="col-md-2" style="text-align: left;">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Unit Name</label>
								</div>
								<div class="col-md-5">
									<input type="text" id="unit_name1" name="unit_name1" maxlength="100" class="form-control-sm form-control" autocomplete="off" placeholder="Search..." />
									<input type="hidden" id="sus_no" name="sus_no">
								</div>
								<div class="col-md-2" style="text-align: left;">
									<label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Period</label>
								</div>
								<div class="col-md-3">
									<input type="month" id="mnth_year" name="mnth_year" class="form-control-sm form-control">
								</div>
							</div>
						</div>
						<div class="card-footer" align="center">
							<input type="button" class="btn btn-success btn-sm" value="Search" onclick="mms_unit_correct_certi1();">
						</div>
					</div>
				
			<div class="card" id="re_tb" style="display: none; background: transparent;">
				<div class="nrTableMain2Search" align="left" id="SearchViewtr">
					<label>Search in Result(${m_1.size()}) </label>&nbsp;:&nbsp; 
					<input id="nrInput" type="text" placeholder="Search..." size="35" style="font-weight: normal; font-size: 14px;">
				</div>
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<div id="" class="nrTableMainDiv">
						<input type="hidden" id="selectedid" name="selectedid"> 
						<input type="hidden" id="nrSrcSel" name="nrSrcSel">
						<div class="nrTableDataDiv">
							<table border="1" class="nrTableDataHead report_print">
								<thead style="text-align: center;">
									<tr>
										<th width="15%">PRF Group</th>
										<th width="8%">Census No</th>
										<th width="20%">Nomenclature</th>
										<th width="8%">Type of Holdings</th>
										<th width="25%">Activity During Month</th>
									</tr>
								</thead>
								<tbody id="nrTable">
									<c:if test="${m_1.size() == 0}">
										<tr>
											<td colspan='5' align='center'>No transaction made</td>
										</tr>
									</c:if>
									<c:if test="${m_1.size() != 0}">
										<c:forEach var="item" items="${m_1}" varStatus="num">
											<tr>
												<td style="text-align: left;" width="15%">${item[0]}</td>
												<td style="text-align: center;" width="8%">${item[1]} </td>
												<td style="text-align: left;" width="20%">${item[2]} </td>
												<td style="text-align: left;" width="8%">${item[3]}</td>
												<td style="text-align: left;" onclick="testData('${item[5]}');" title='click to Find Regn No.' width="25%">${item[4]}</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="card" id="getObsnDetails" style="display: none; background: transparent;">
				<div class="nrTableMain2Search" align="left" id="SearchViewtr">
					<label>Search in Result(${getUnitUploadedDocDetails.size()}) </label>&nbsp;:&nbsp; 
					<input id="getObsnDetailsInput" type="text" placeholder="Search..." size="35" style="font-weight: normal; font-size: 14px;">
				</div>
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<div id="" class="nrTableMainDiv">
						<input type="hidden" id="selectedid" name="selectedid"> 
						<input type="hidden" id="nrSrcSel" name="nrSrcSel">
						<div class="nrTableDataDiv">
							<table border="1" class="nrTableDataHead report_print">
								<thead style="text-align: center;">
									<tr>
										<th width="10%">Uploaded Doc</th>
										<th width="40%">Observation</th>
										<th width="10%">Obsn Date</th>
										<th width="10%">Date of completion</th>
										<th width="5%">completion by</th>
										<th width="25%">MISO Reply</th>
									</tr>
								</thead>
								<tbody id="getObsnDetailsTable">
									<c:if test="${getUnitUploadedDocDetails.size() == 0}">
										<tr>
											<td colspan='5' align='center'>No transaction made</td>
										</tr>
									</c:if>
									<c:forEach var="item" items="${getUnitUploadedDocDetails}" varStatus="num">
										<tr>
											<td style="text-align: center;" width="10%">${item[0]}</td>
											<td width="40%">${item[1]} </td>
											<td style="text-align: center;" width="10%">${item[2]}</td>
											<td style="text-align: center;" width="10%">${item[3]}</td>
											<td style="text-align: center;" width="5%">${item[4]}</td>
											<td width="25%" valign="top" >${item[5]}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	
	<c:url value="unitCClist" var="backUrl" />
	<form:form action="${backUrl}" method="post" id="m2_unit2"
		name="m2_unit2" modelAttribute="m2_sus">
		<input type="hidden" name="m2_sus" id="m2_sus" />
		<input type="hidden" name="m2_mnth" id="m2_mnth" />
		<input type="hidden" name="m2_unit" id="m2_unit" />
	</form:form>
	<c:url value="mms_cc_view" var="backUrl" />
	<form:form action="${backUrl}" method="post" id="m2_cc" name="m2_cc" modelAttribute="c_id" target="result">
		<input type="hidden" name="c_id" id="c_id" />
	</form:form>
	<c:url value="getDownloadFileOBSNState" var="imageDownloadUrl" />
	<form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="miso_reply_id">
		<input type="hidden" name="miso_reply_id" id="miso_reply_id" value="0"/>
	</form:form>
	
	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
	<script type="text/javascript" src="js/common/commonmethod.js"></script>
	<script type="text/javascript" src="js/cue/cueWatermark.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<Script>
function getDownloadUnitDoc(miso_reply_id){  
	document.getElementById("miso_reply_id").value=miso_reply_id;
	document.getElementById("getDownloadImageForm").submit();
}

function miso_reply(miso_reply_id){
	var miso_reply = $("#miso_reply"+miso_reply_id).val().trim();
	if(miso_reply == ""){
		alert("please enter MISO Reply.");
		$("#miso_reply"+miso_reply_id).focus();
		$("#miso_reply"+miso_reply_id).val("");
		return false;
	}else if(miso_reply_id == ""){
		alert("please enter valid Data.");
		return false;
	}else{
		jQuery.ajax({
			type : 'POST',
			url : "miso_replay_to_unit?"+key+"="+value,
			data : {miso_reply_id:miso_reply_id,miso_reply:miso_reply},
			success : function(data) {
				if(data.length != 0){
					alert(data[0].error);
					if(data[0].error == "MISO Reply Successfully Submitted"){
						$("#miso_reply"+miso_reply_id).attr('disabled','disabled');
						$("#replyBtn").hide();
					}
					return true;
				}
			}
		});
	}
}
function testData(a){
	var t_id = a;
	if(t_id != null || t_id != ""){
		popupWindow = window.open("", 'result', 'height=500,width=1000,left=200, top=100,resizable=yes,scrollbars=yes,toolbar=no,status=yes');
		$("#c_id").val(t_id);
		$("#m2_cc").submit();
	}
}
function mms_unit_correct_certi1(){
	if($("#unit_name1").val()==""){
		alert("Please Select Unit Name...");
		$("#unit_name1").focus();
	    return false;
	} 
	if($("#mnth_year").val()==""){
		alert("Please Select Period...");
		$("#mnth_year").focus();
	    return false;
	} 
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	if($("#mnth_year").val() > c_d){
		alert("Can't select the Future Month & Year");
		$("#mnth_year").focus();
		return false;
	}
	$("#m2_sus").val($("#sus_no").val());
	$("#m2_mnth").val($("#mnth_year").val());
	$("#m2_unit").val($("#unit_name1").val());
	$("#nrWaitLoader").show();
	$("#m2_unit2").submit();
}
$("#unit_name1").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',unit_name1,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',sus_no);
});
</Script>
<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var y1 = '${m_1}';
	if(y1 != ""){
		$("#sus_no").val('${m_2}');
		$("#unit_name1").val('${m_3}');
		var mt = '${mt_y}';
		if(mt != ""){
			$("#mnth_year").val(mt);
		}else{
			$().getMthYr(mnth_year);
		}
		$("#re_tb").show();
		$("#getObsnDetails").show();
		nrSetWaterMark(<%=ntotln+6%>);
	}else{
		$().getMthYr(mnth_year);
	}
	
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
			window.location = url;
	    } 
	}catch (e){
	}
	
	$("#nrInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#nrTable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	}); 
	
	$("#getObsnDetailsInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#getObsnDetailsTable tr").filter(function() {
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	}); 
});
</script>