<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="CASUALITY3" id="CASUALITY3" action="Casuality3_Action" method="post" class="form-horizontal" commandName="Casuality3_CMD">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5> ADD/UPDATE CASUALTY3</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by MISO)</span>
					</h6>
				</div>
				<div class="card-body card-block">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
				                    <label class=" form-control-label"><strong style="color: red;">* </strong> Casualty1</label>
				                </div>
				                <div class="col-md-8">
				                    <select name="casuality1_id" id="casuality1_id" class="form-control" onchange="fn_Casuality2();" >
										<option value="0">--Select--</option>
										<c:forEach var="item" items="${getCasuality1}" varStatus="num">
										   <option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select> 
				                </div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> Casualty2</label>
								</div>
								<div class="col-md-8">
									<select name="casuality2_id" id="casuality2_id" class="form-control-sm form-control">
										<option value="0">--Select--</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Casualty3</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="casuality3" name="casuality3" class="form-control autocomplete" autocomplete="off" onkeypress="return onlyAlphabets(event);"> 
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
								</div>
								<div class="col-md-8">
									<select name="status" id="status" class="form-control">
										<!-- 												<option value="0">--Select--</option> -->
										<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
											<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Casuality3_Url" class="btn btn-success btn-sm">Clear</a> 
					<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return Validate();">
					<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
				</div>
			</div>
		</div>
	</div>
	<div align="right" class="container">
		<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcel();"></i>
	</div>
	<br>
	<div class="container" id="Casuality3_Search">
		<div class="">
			<div id="divShow" style="display: block;"></div>
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<table id="Casuality3_Search" class="table no-margin table-striped  table-hover  table-bordered report_print" width="100%">
					<thead style="color: white; text-align: center;">
						<tr>
							<th style="font-size: 15px; width: 10%;">Ser No</th>
							<th style="font-size: 15px">Casualty1</th>
							<th style="font-size: 15px">Casualty2</th>
							<th style="font-size: 15px">Casualty3</th>
							<th style="font-size: 15px; width: 20%;">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${list.size()==0}">
							<tr>
								<td style="font-size: 15px; text-align: center; color: red;" colspan="5">Data Not Available</td>
							</tr>
						</c:if>
						<c:forEach var="item" items="${list}" varStatus="num">
							<tr>
								<td style="font-size: 15px; text-align: center; width: 10%;">${num.index+1}</td>
								<td style="font-size: 15px;">${item[0]}</td>
								<td style="font-size: 15px;">${item[1]}</td>
								<td style="font-size: 15px;">${item[2]}</td>
								<td style="font-size: 15px; text-align: center; width: 20%;">${item[3]} ${item[4]}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</form:form>
<c:url value="getSearch_Casuality3" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="casuality3_v1">
	<input type="hidden" name="casuality1_id1" id="casuality1_id1" />
	<input type="hidden" name="casuality2_id1" id="casuality2_id1" />
	<input type="hidden" name="casuality3_v1" id="casuality3_v1" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>
<c:url value="Edit_Casuality3" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>
<c:url value="delete_Casuality3" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0" />
</form:form>
<c:url value="Report_Casuality3" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="search2" name="search2" modelAttribute="comd1">
	<input type="hidden" name="typeReport1" id="typeReport1" value="0" />
</form:form>
<Script>
	$(document).ready(function() {
		if ('${list.size()}' == "") {
			$("div#Casuality3_Search").hide();
		}
		if ('${casuality1_id1}' != "") {
			$("#casuality1_id").val('${casuality1_id1}');
			
			   $.post("getCasuality2?" + key + "=" + value, {casuality1_id : '${casuality1_id1}'}).done(function(j) {
					var options = '<option   value="0">' + "--Select--" + '</option>';
					for (var i = 0; i < j.length; i++) {
						
						if( j[i][0] == '${casuality2_id1}')
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" selected >'+ j[i][1] + '</option>';
						else
							options += '<option   value="' + j[i][0] + '" name="'+j[i][1]+'" >'+ j[i][1] + '</option>';
							
					}
					$("select#casuality2_id").html(options);
				}).fail(function(xhr, textStatus, errorThrown) {
		     });
		}
		if ('${casuality2_id1}' != "") {
			$("#casuality2_id").val('${casuality2_id1}');
		}
		if ('${casuality3_s1}' != "") {
			$("#casuality3").val('${casuality3_s1}');
		}
		if ('${status1}' != "") {
			$("Select#status").val('${status1}');
		}
	});
	function getExcel() {
		document.getElementById('typeReport1').value = 'excelL';
		document.getElementById('search2').submit();
	}
	function Search() {
		$("#casuality1_id1").val($('#casuality1_id').val());
		$("#casuality2_id1").val($('#casuality2_id').val());
		$("#casuality3_v1").val($('#casuality3').val());
		$("#status1").val($('#status').val());
		document.getElementById('searchForm').submit();
	}
	function deleteData(id) {
		$("#id1").val(id);
		document.getElementById('deleteForm').submit();
	}
	function editData(id) {
		$("#id2").val(id);
		$("#updateForm").submit();
	}
	function Validate() {
		if ($("select#casuality1_id").val() == 0) {
			alert("Please Select Casualty1.");
			$("select#casuality1_id").focus();
			return false;
		}
		if ($("select#casuality2_id").val() == 0) {
			alert("Please Select Casualty2.");
			$("select#casuality2_id").focus();
			return false;
		}
		if ($("input#casuality3").val().trim() == "") {
			alert("Please Enter Casualty3.");
			$("input#casuality3").focus();
			return false;
		}
		if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		}
		return true;
	}
	function fn_Casuality2() {
		var text = $("#casuality1_id option:selected").text();
		var casuality1_id = $('select#casuality1_id').val();
		        $.post("getCasuality2?" + key + "=" + value, {casuality1_id : casuality1_id}).done(function(j) {
							var options = '<option   value="0">' + "--Select--" + '</option>';
							for (var i = 0; i < j.length; i++) {
								options += '<option   value="' + j[i][0] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
							}
							$("select#casuality2_id").html(options);
						}).fail(function(xhr, textStatus, errorThrown) {
				});
	}
</Script>
