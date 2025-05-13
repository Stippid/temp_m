<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/Calender/jquery-ui.js"></script>
<script type="text/javascript" src="js/common/commonmethod.js"></script>
<script type="text/javascript" src="js/cue/cueWatermark.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> 
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<script>
var username="${username}"; 
var curDate = "${curDate}";
$(document).ready(function (){
	mockjax1('example');
	table = dataTable('example',[],[]);
	$('#btn_reset').on('click', function(){
    	table.ajax.reload();
    });
	$('#btn_search').on('click', function(){
    	table.ajax.reload();
    });
});
function data(tableName){
	jsondata = [];
	// Default Parameter
	var table = $('#'+tableName).DataTable();
	var info = table.page.info();
	var currentPage = info.page;
	var pageLength = info.length;
	var startPage = info.start;
	var endPage = info.end;
	var Search = table.search();
	var order = table.order();
	var orderColunm = order[0][0] + 1;// for Colunm Id wise Ordering
	//var orderColunm = $(table.column(order[0][0]).header()).html().toLowerCase(); // for Colunm Name wise Ordering
	var orderType = order[0][1];
	// Default Parameter
	//alert("currentPage=="+currentPage  + " \npageLength=="+pageLength + "\nstartPage="+ startPage + "\nendPage="+ endPage + "\nSearch==" + Search +"\n OrderColunm ="+orderColunm +"\n OrderType ="+orderType);

	// No Change
	
	// Advanced Search Parameter
	var sus_no = $("#sus_no").val();
	var month_yr = $("#mnth_year").val();
	var status = $("#statusid").val();
	
	// Advanced Search Parameter
	
	$.post("mms_unit_all_obsn_statusList?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType,sus_no:sus_no,month_yr:month_yr,status:status},function(j) {
		for (var i = 0; i < j.length; i++) {
			jsondata.push([j[i].unit_name,j[i].doc,"O-"+j[i].id,j[i].remark,j[i].created_on,j[i].dt_completion,j[i].completion_by,j[i].miso_reply]);
		}
	});
	$.post("mms_unit_all_obsn_statusCount?"+key+"="+value,{Search:Search,sus_no:sus_no,month_yr:month_yr,status:status},function(j) {
		FilteredRecords = j;
	});
}
</script> 
<body class="mmsbg">
	<%int ntotln = 0;%>
	<div id="nrWaitLoader" style="display: none;" align="center">
		<span id="">Processing Data.Please Wait ...</span>
	</div>
	<%-- <form:form id="unit_obsn" name="unit_obsn" action="#"> --%>
		<div class="nkpageland" id="printableArea">
			<div class="container" align="center" id="headerView" style="display: block;">
				<div class="card">
					<div class="card-header mms-card-header">
						<b>UNIT OBSN STATUS</b>
					</div>
					<div class="col-md-12 row form-group" style="margin-top: 10px;">
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label">Unit Name</label>
						</div>
						<div class="col-md-4">
							<input type="text" id="unit_name1" name="unit_name1" maxlength="100" class="form-control-sm form-control" placeholder="Search..." autocomplete="off"> 
							<input type="hidden" id="sus_no" name="sus_no" />
						</div>
						<div class="col-md-2" style="text-align: left;">
							<label class=" form-control-label">Period</label>
						</div>
						<div class="col-md-3">
							<input type="month" id="mnth_year" name="mnth_year" class="form-control-sm form-control" />
						</div>
					</div>
					<div class="col-md-12 row form-group" style="margin-top: -10px;">
						<div class="col-md-2" style="text-align: left;">
							<label for="text-input" class=" form-control-label">Status</label>
						</div>
						<div class="col-md-4">
							<select name="statusid" id="statusid"
								class="form-control-sm form-control">
								<option value="ALL">-- ALL STATUS --</option>
								<option value="COMPLETED">RESOLVED</option>
								<option value="PENDING">PENDING FROM MISO</option>
							</select>
						</div>
					</div>
					<div class="card-footer" align="center">
						<input type="reset" id="btn_reset" class="btn btn-primary btn-sm" value="Clear"> 
						<input type="button" id="btn_search" class="btn btn-success btn-sm" value="Search"> 
						<a href="mmsDashboard"><input type="button" class="btn btn-danger btn-sm" value="Cancel"></a>
					</div>
				</div>
			</div>
			<div class="card" id="re_tb" style="background: transparent;">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
					<div id="" class="nrTableMainDiv">
						<input type="hidden" id="selectedid" name="selectedid"> 
						<input type="hidden" id="nrSrcSel" name="nrSrcSel">
						<div class="nrTableDataDiv">
							<table id="example" class="display" class="report_print">
					    		<thead>
					        		<tr>
						            	<th width="20%">Unit Name</th>
						            	<th width="5%" style="background-image: none;pointer-events: none;">Uploaded Doc</th>
										<th width="5%">Obsn ID</th>
										<th width="30%" style="background-image: none;pointer-events: none;">Observation</th>
										<th width="10%" align="center">Obsn Date</th>
										<th width="10%" align="center">Date of Completion</th>
										<th width="5%" align="center">Completion by</th>
										<th width="15%" style="background-image: none;pointer-events: none;">MISO Reply</th>
						            </tr>
						    	</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
<%-- 	</form:form> --%>

<c:url value="getDownloadFileOBSNState" var="imageDownloadUrl" />
<form:form action="${imageDownloadUrl}" method="post" id="getDownloadImageForm" name="getDownloadImageForm" modelAttribute="miso_reply_id">
	<input type="hidden" name="miso_reply_id" id="miso_reply_id" value="0"/>
</form:form>



<script>
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

$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	$().getMthYr(mnth_year);
	
	var y1 = '${m_1[0][0]}';
	var y2 = '${m_11[0][0]}';

	if(y1 != "" || '${m_1[0]}'.length > 0){
		$("#re_tb").show();
		$("#rs_obs").hide();
		$("#domainid").val('${m_2}');
	    $("#statusid").val('${m_3}');
		$("#mnth_year").val('${m_4}');
		$("#sus_no").val('${sus_1}');
		$("#unit_name1").val('${unit_1}');
		nrSetWaterMark(<%=ntotln%>);
		$("#ntotln").text(<%=ntotln%>);	
	} 
	
	if(y2 != "" || '${m_11[0]}'.length > 0){
		$("#rs_obs").show();
		$("#re_tb").hide();
		$("#domainid").val('${m_2}');
	    $("#statusid").val('${m_3}');
		$("#mnth_year").val('${m_4}');
		$("#tr_id").val('${m_5}');
		$("#sus_no").val('${m_11[0][0]}');
		$("#unit_name").val('${m_11[0][1]}');
		$("#census_no").val('${m_11[0][2]}');
		$("#nomen").val('${m_11[0][3]}');
		$("#type_of_hldg").val('${m_11[0][4]}');
		$("#type_of_eqpt").val('${m_11[0][5]}');
	} 
	
  	$("#nrInput").on("keyup", function() {
  		var value = $(this).val().toLowerCase();
  		$("#nrTable tr").filter(function() {
  			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
  		});
  	});		
  	
  	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
			window.location = url;
		} 	
	}catch (e) {
		// TODO: handle exception
	}
}); 
</script>

<script>
$("#unit_name1").keyup(function(){
	var unit_name = this.value;
	var para = "";
	var paravalue="";
	$().Autocomplete2('POST','getMMSRList',unit_name1,{a:unit_name,b:para,c:"NAME",d:paravalue},'getMMSSUSNoByUnitName',sus_no);
});


function btn_clc(){
	location.reload(true);
}

function printDiv() {
	  $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#btn_modify").hide();changobsn
	  $("#changobsn").hide();
	  $('.rdView').css('display','none');
	//let popupWinindow
let innerContents = document.getElementById('printableArea').innerHTML;
	 
popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
popupWindow.document.open();
//popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"></head><body onload="window.print();window.close();">' + innerContents + '</html>');
popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
popupWindow.document.close();
$("#SearchViewtr").show();
$("#tdheaderView").hide();
$("#headerView").show();
$("#btn_e").show();
$("#btn_p").show();
$("#btn_modify").show();
$("#changobsn").show();
$('.rdView').css('display','block');

}

function exportData(b){
	$().tbtoExcel(b);
	b.preventDefault();
}

function setid(a){
	$("#nrSrcSel").val(a);
}

function mms_list_obsn_status(){
	$("#rs_obs").hide();
	if($("#mnth_year").val()==""){
		alert("Please Select the Period...");
		$("#mnth_year").focus();
	    return false;
	} 
	
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2);
	
	if($("#mnth_year").val() > c_d){
		$("#mnth_year").focus();
		alert("Can't select the Future Month & Year");
		return false;
	}
	
	$("#m7_domid").val($("#domainid").val());
	$("#m7_statid").val($("#statusid").val());
	$("#m7_mthyr").val($("#mnth_year").val());
	$("#m7_sus").val($("#sus_no").val());
	$("#m7_unit").val($("#unit_name1").val());
	$("#nrWaitLoader").show();
	$("#m7_unit1").submit();	
}

function changeObsn(){
	var id = $("#nrSrcSel").val();
	$("#tr_id").val(id);
	
	if(id != ""){
		$("#m7_id").val(id);
		$("#m7_domid2").val($("#domainid").val());
		$("#m7_statid2").val($("#statusid").val());
		$("#m7_mthyr2").val($("#mnth_year").val());
		$("#nrWaitLoader").show();
		$("#m7_unit2").submit();			
	}else{
		alert("Please Select the Request");
	}
	
}
</script> 