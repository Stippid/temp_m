<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">

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
<script>
function isAlphaNumeric(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
		if((charCode>=48 && charCode<=57) || (charCode>=65 && charCode<=90) || (charCode>=97 && charCode<=122) || charCode==32 || charCode==46 || charCode == 45){
			return true;
		} 
	return false;
}
</script>
<body onload="setMenu();">
<form:form id="msg_save" name="msg_save" class="fp-form" action="msg_save?${_csrf.parameterName}=${_csrf.token}" method='POST'>
		<div class="containerr" align="center">
			<div class="card">
				<div class="card-header mms-card-header" style="min-height: 60px; padding-top: 10px;">
					<b>CREATE MESSAGE</b>
				</div>
				
				<div class="card-body card-block ncard-body-bg">
					<div class="row">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label"><strong	style="color: red;">* </strong>Message (Max 150 char)</label>
						</div>
						<div class="col-md-10">
							<input type="hidden" id="sus_no" name="sus_no" value='${roleSusNo}'>
							<input type="hidden" id="msg_id" name="msg_id">
							<input type="hidden" id="msg_type" name="msg_type" value="GEN">
							<input type="text" id="msg1"	name="msg1" class="form-control char-counter" maxlength="250"	placeholder="Type your message" size="150" autocomplete="off" title="Type Your Message" onkeypress="return isAlphaNumeric(event);">
						</div>
					</div>
					<div class="row">

						<div class="col-md-2">
							<label for="text-input" class=" form-control-label"><strong	style="color: red;">* </strong>Msg Valid From</label>
						</div>
						<div class="col-md-4">
							<input type="date" id="date_from" name="date_from"	class="form-control-sm form-control" min="${date}" maxlength="10" title="Message Valid From Date">
						</div>

						<div class="col-md-2" style="">
							<label class=" form-control-label"><strong	style="color: red;">* </strong>Msg Valid To</label>
						</div>
						<div class="col-md-4">
							<input type="date" id="date_to"	name="date_to" class="form-control-sm form-control"	min="${date}" maxlength="10" title="Message Valid To Date">
						</div>

					</div>
					<div class="row">
						<div class="col-md-2">
							<label for="text-input" class=" form-control-label"><strong	style="color: red;">* </strong>To Whom</label>
						</div>
						<div class="col-md-4">
								<select id="msg_to" name="msg_to"	class="form-control-sm form-control" title="Message send to whom">
									<option value="All">All MISO User</option>
									<option value="AFP">All Finance Module User</option>
									<option value="BH">Only Budget Holders</option>
									<option value="SEL">Selected Units</option>
									<option value="UD">Under ALL Adm Control</option>
								</select>
						</div>
					</div>
				</div>

				<div class="card-footer" align="center">
					<input id="btn_upload" type="submit" class="btn btn-success btn-sm nGlow" value="Save" onclick="return isvalidData();" title="Click to Save message">
				</div>
			</div>
		</div>
	</div>
	<div class="container" id="divPrint">
			<c:if test='${n_4 != "-1"}'>
				<div class="card-header mms-card-header"
						style="min-height: 60px; padding-top: 10px;text-align: center">
						<b>MESSAGES</b>
				</div>			
			</c:if>
 			<div id="divSerachInput" style="width:60%;margin-top: 10px;">
				<div class="col-md-6">
					<input id="searchInput" type="text"
						style="font-family: 'fontello', Arial; margin-bottom: 5px;"
						placeholder="Search Message" class="form-control-sm" title="Search Your Message">
				</div>
			</div>
 			<div class="nPopTable" style="height:350px;width:100%;overflow: auto;background-color: white;">
 			<div  class="watermarked" data-watermark="" id="divwatermark"  >
			<table style="width: 100%; margin: 0 auto;margin-bottom:5px;" id="srctable">
				<thead style="text-align: center; line-height: 20px; font-size: 1.2vw;">
					<tr style="text-align: center;" >
						<th style="width:4%;">Ser No</th>
						<th style="">Message Details</th>
						<th style="width: 15%;">Valid From</th>
						<th style="width: 15%;">Valid To</th>
						<th style="width: 15%;">Status</th>
					</tr>
				</thead>
				<tbody style="font-size: 1.1vw; text-decoration: none;">
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="5">No Message Available</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="text-align: center; width: 5%;">${num.index+1}</td>
							<td title="Message Details - ${item[0]}">&nbsp;&nbsp;${item[0]}</td>
							<td title="Message Valid From - ${item[1]}">&nbsp;&nbsp;${item[1]}</td>
							<td title="Message Valid To - ${item[2]}">&nbsp;&nbsp;${item[2]}</td>
							 <c:if test="${item[3]==1}">
							 	<td title="Status - Active">&nbsp;&nbsp;Active</td>
							 </c:if>
							 <c:if test="${item[3]!=1}">
							 	<td title="Status - Inactive">&nbsp;&nbsp;Inactive</td>
							 </c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
		</div>
		</div>
</form:form>

<script>
	var key = "${_csrf.parameterName}";
	var value = "${_csrf.token}";
	
	function isvalidData() {

		if ($("#msg1").val() == "") {
			alert("Please Enter the Message");
			$("#msg1").focus();
			return false;
		}
		if ($("#msg_to").val() == "") {
			alert("Please Select the Message for Whom");
			$("#msg_to").focus();
			return false;
		}
		if ($("#date_from").val() == "") {
			alert("Please Select the Message From Date");
			$("#date_from").focus();
			return false;
		}
		if ($("#date_to").val() == "") {
			alert("Please Select the Valid to date");
			$("#date_to").focus();
			return false;
		}
	}

	$(document).ready(function() {
		$().getCurDt(date_from);
		$().getCurDt(date_to);
		$("div#divwatermark").val('').addClass('watermarked');	
		watermarkreport();
		$(".char-counter").charCounter();

		$("#searchInput").on("keyup", function() {
	    	var value = $(this).val().toLowerCase();
	      	$("#srctable tbody tr").filter(function() {
	      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	        });
	    });
		
		
	});
</script>