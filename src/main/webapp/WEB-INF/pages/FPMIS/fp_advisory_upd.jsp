<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script>
	var role = "${role}";
</script>
<style type="text/css">
.row {
	margin-bottom: 10px;
	margin-top: 10px;
}

.downloadAdvisory{
	cursor: pointer;
}
</style>
<body onload="setMenu();">
<form:form id="Advisory_Upload" name="Advisory_Upload"	action="Advisory_UploadAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="Advisory_UploadCmd"	class="fp-form">
	<c:if test='${n_4 == "-1"}'>
		<div class="container" align="center">
			<div class="card">
				<div class="card-header mms-card-header"
					style="min-height: 60px; padding-top: 10px;">
					<b>ADVISORY UPLOAD</b>
				</div>
				
				<div class="card-body card-block ncard-body-bg">
					<div class="row">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label"><strong
								style="color: red;">* </strong>Advisory Details</label>
						</div>
						<div class="col-md-10">
							<input type="text" id="advisory_in_details"
								name="advisory_in_details" class="form-control-sm form-control char-counter" size="100" maxlength="255"
								placeholder="Advisory Details" autocomplete="off"
								title="Enter Advisory Details">
						</div>
					</div>
					<div class="row">
						
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label"><strong
								style="color: red;">* </strong>Advisory Type</label>
						</div>
						<div class="col-md-4">
								<select id="adv_type" name="adv_type"
								class="form-control-sm form-control" title="Select Advisory Type">
									<option value="G">General</option>
									<option value="I">Important</option>
								</select>
						</div>
						
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label"><strong
								style="color: red;">* </strong>Choose File</label>
						</div>
						<div class="col-md-4">
							<input type="file" id="adv_file" name="adv_file"
								class="form-control-sm form-control" accept=".pdf" title="Choose File to Upload"/>
						</div>

					</div>
					<div class="row">

						<div class="col-md-2">
							<label for="text-input" class=" form-control-label"><strong
								style="color: red;">* </strong>Valid From</label>
						</div>
						<div class="col-md-4">
							<input type="date" id="frm_dt" name="frm_dt"
								class="form-control-sm form-control" autocomplete="off" value="${today}" title="Select Valid From Date">
						</div>

						<div class="col-md-2" style="">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Valid To</label>
						</div>
						<div class="col-md-4">
							<input type="date" id="to_dt" name="to_dt"
								class="form-control-sm form-control" autocomplete="off" value="${today}" title="Select Valid to Date">
						</div>

					</div>
				</div>

				<div class="card-footer" align="center">
					<input id="btn_upload" type="button" class="btn btn-success btn-sm nGlow" value="Upload Advisory" title="Click to Upload Advisory">
				</div>
			</div>
		</div>
		</c:if>
	</div>
	<div class="container" id="divPrint">
			<c:if test='${n_4 != "-1"}'>
				<div class="card-header mms-card-header"
						style="min-height: 60px; padding-top: 10px;text-align: center">
						<b>ADVISORIES</b>
				</div>			
			</c:if>
			
 			<div id="divSerachInput" style="width:60%;margin-top: 10px;">
				<div class="col-md-6">
					<input id="searchInput" type="text"
						style="font-family: 'fontello', Arial; margin-bottom: 5px;"
						placeholder="Search Advisory" class="form-control-sm" title="Search by Advisory">
				</div>
			</div>
 			<div class="nPopTable" style="height:350px;width:100%;overflow: auto;background-color: white;">
 			<div  class="watermarked" data-watermark="" id="divwatermark"  >
			<table style="width: 100%; margin: 0 auto;margin-bottom:5px;" id="srctable">
				<thead style="text-align: center; line-height: 20px; font-size: 1.2vw;">
					<tr style="text-align: center;" >
						<th style="width:5%;">Ser No</th>
						<th style="width:25px;"></th>
						<th style="">&nbsp;&nbsp;Advisory Details</th>
						<th style="width: 20%;">&nbsp;&nbsp;Date of Issue</th>
						<th style="width: 10%;">Download</th>
					</tr>
				</thead>
				<tbody style="font-size: 1.1vw; text-decoration: none;">
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="5">No Advisory Available</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td class="text-center">${num.index+1}</td>
							<td class="text-center">
								<c:if test='${item[1] == "I"}'>
									<i class="fa fa-flag" title="Important Advisory"></i>
								</c:if>
							</td>
							<td title="Advisory - ${item[0]}">&nbsp;&nbsp;${item[0]}</td>
							<td class="text-center" style="font-size:1vw" title="Date of Issue - ${fn:substring(item[3],0,10)}">&nbsp;&nbsp;${fn:substring(item[3],0,10)}</td>
							<td class="text-center"><i class="fa fa-download downloadAdvisory" data-adv-id='${item[2]}' title='Click to Download'></i></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
		</div>
</form:form>

<c:url value="SearchAdvisory?${_csrf.parameterName}=${_csrf.token}" var="searchUrl" />
<form:form action="${searchUrl}" method="POST" id="searchForm"
	name="searchForm" modelAttribute="service1">
	<input type="hidden" name="advisory_in_details1"
		id="advisory_in_details1" />
	<input type="hidden" name="adv_type1" id="adv_type1" />
	<input type="hidden" name="frm_dt1" id="frm_dt1" />
	<input type="hidden" name="to_dt1" id="to_dt1" />
</form:form>

<c:url value="DownloadAdvisory?${_csrf.parameterName}=${_csrf.token}" var="url" />
<form:form action="${url}" method="POST" id="downloadForm"
	name="downloadForm" modelAttribute="download">
	<input type="hidden" name="file_id" id="file_id" />
</form:form>

<script>
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	
	function isvalidData() {
		if ($("#advisory_in_details").val() == "") {
			alert("Please Enter the Advisory Details");
			$("#advisory_in_details").focus();
			return false;
		}
		if ($("#adv_file").val() == "") {
			alert("Please Select the file");
			$("#adv_file").focus();
			return false;
		}
		if ($("#adv_type").val() == "") {
			alert("Please Select the Advisory Type");
			$("#adv_type").focus();
			return false;
		}
		if ($("#frm_dt").val() == "") {
			alert("Please Select the Valid from date");
			$("#frm_dt").focus();
			return false;
		}
		if ($("#to_dt").val() == "") {
			alert("Please Select the Valid to date");
			$("#to_dt").focus();
			return false;
		}
		return true;
	}
	
	function Search() {
		$("#advisory_in_details1").val($("#advisory_in_details").val());
		$("#adv_type1").val($("#adv_type").val());
		$("#frm_dt1").val($("#frm_dt").val());
		$("#to_dt1").val($("#to_dt").val());
		$("#searchForm").submit();
	}

	$(document).ready(function() {
		$("div#divwatermark").val('').addClass('watermarked');	
		watermarkreport();
		$(".char-counter").charCounter();
		
		var file = document.getElementById('adv_file');
		var n4='${n_4}';
		if (n4=='-1') {
			file.onchange = function(e) {
				var ext = this.value.match(/\.([^\.]+)$/)[1];
				switch (ext) {
				case 'pdf':
					break;
				default:
					alert('Only PDF files are permitted.');
					this.value = '';
				}
			};
		}
		
		$(".downloadAdvisory").click(function(){
			$("#file_id").val($(this).data("adv-id"));			
			$("#downloadForm").submit();
		});
		
		$("#btn_upload").click(function(){
			
			if(isvalidData()){
				var form = jQuery('#Advisory_Upload')[0];
				var data = new FormData(form);
				jQuery.ajax({
					type : "POST",
					enctype : 'multipart/form-data',
					url : "Advisory_UploadAction?"+key+"="+value,
					data : data,
					processData : false,
					contentType : false,
					cache : false,
					timeout : 600000,
					success : function(data) {
						alert(data[0]);
						var wh = window.location.href;
						window.location.href = wh.substring(0,wh.lastIndexOf("/"))+"/fp_advisory_upd";
					}
				});
			}
		});
		
		$("#searchInput").on("keyup", function() {
	    	var value = $(this).val().toLowerCase();
	      	$("#srctable tbody tr").filter(function() {
	      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	        });
	    });
		
		var clearForm = function(){
			$("#to_dt").val("");
			$("#frm_dt").val("");
			$("#adv_file").val("");
			$("#advisory_in_details").val("");
		}
	});
</script>