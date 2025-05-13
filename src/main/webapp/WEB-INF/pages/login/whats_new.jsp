<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript" src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<c:if test='${not empty msg}'>
	<input type='hidden' name='msg' id='msg' value='${msg}' />
</c:if>
<form name="whatsNewForm" id="whatsNewForm">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>WHAT'S NEW</h5>
			</div>
			<div class="card-body card-block">
				<div class='col-md-11'>
					<div class='row form-group'>
						<div class='col col-md-4'>
							<label for="text-input" class="form-control-label">Heading <strong style="color: red;">*</strong></label>
						</div>
						<div class='col-12 col-md-8'>
							<input type="text" id="heading" name="heading" class="form-control" autocomplete='off'>
						</div>
					</div>
				</div>
				<div class='col-md-11'>
					<div class='row form-group'>
						<div class='col col-md-4'>
							<label for="text-input" class="form-control-label">Description <strong style="color: red;">*</strong>
							</label>
						</div>
						<div class='col-12 col-md-8'>
							<textarea rows="3" id="description" name="description" class="form-control" ></textarea>
						</div>
					</div>
				</div>

				<div class='col-md-11'>
					<div class='row form-group'>
						<div class='col col-md-4'>
							<label for="text-input" class="form-control-label">File </label>
						</div>
						<div class='col-12 col-md-8'>
							<input type="file" id="file_upload" name="file_upload" class="form-control" autocomplete='off'>
						</div>
					</div>
				</div>
			</div>
			<div class='card-footer' align='center'>
				<input type='reset' class='btn btn-success btn-sm' value='Clear'>
				<input type='submit' class='btn btn-primary btn-sm' value='Save' onclick="return whatNewValidation();">
			</div>
			<div class="card-body">
				<div class="col-md-12">
					<table id="whatsnewTbl" style="width: 100%;">
						<thead>
							<tr>
								<th>Heading</th>
								<th>Description</th>
								<th style="text-align: center;">Date</th>
								<th style="text-align: center;">Action</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</form>
<c:url value="getDownloadWhatsNew" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="getDownloadWhatsNewForm" name="getDownloadWhatsNewForm" modelAttribute="whats_new_id">
	<input type="hidden" name="whats_new_id" id="whats_new_id" value="0" />
</form:form>

<script>
	$(document).ready(function() {
		mockjax1('whatsnewTbl');
		table = dataTable('whatsnewTbl',[2,3],[]);
		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});
	});
	function data(tableName) {
		jsondata = [];
		var table = $('#whatsnewTbl').DataTable();
		var info = table.page.info();

		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
		$.post("getWhatsNewList?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType
		}, function(j) {
			for (var i = 0; i < j.length; i++) {
				jsondata.push([ j[i].heading,j[i].description,j[i].doc_date,j[i].action]);
			}
		});
		$.post("getWhatsNewCount?" + key + "=" + value, {
			Search : Search
		}, function(j) {
			FilteredRecords = j;
		});
	}

	function whatNewValidation() {
		if ($("#heading").val().trim() == "") {
			alert("Please enter Heading");
			$("#heading").focus();
			return false;
		} else if ($("#description").val().trim() == "") {
			alert("Please enter Description");
			$("#description").focus();
			return false;
		} else {
			var form = jQuery('#whatsNewForm')[0];
			var data = new FormData(form);
			jQuery.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : "whatsNewAction?" + key + "=" + value,
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				success : function(data) {
					alert(data);
					if (data == "Data Saved Successfully.") {
						window.location.href = 'whats_new_url';
					}
				}
			});
			return false;
		}
		return false;
	}

	function downloadDoc(id) {
		$("#whats_new_id").val(id);
		document.getElementById("getDownloadWhatsNewForm").submit();
	}
</script>
