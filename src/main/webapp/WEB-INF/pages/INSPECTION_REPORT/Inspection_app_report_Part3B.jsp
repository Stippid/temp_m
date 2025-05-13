<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<link rel="stylesheet" href="js/layout/css/animation.css">
<link rel="stylesheet" href="js/assets/collapse/collapse.css">
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<!-- resizable_col -->
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<style>
.sticky {
	position: fixed;
	top: 0;
	z-index: 1000;
	padding-right: 35px;
}

.sticky+.subcontent {
	padding-top: 330px;
}

.ins_item {
	transition: transform 0.3s ease;
	/* Smooth transition for the transform property */
	cursor: pointer; /* Change cursor to pointer on hover */
}

.ins_item:hover {
	transform: scale(1.1); /* Enlarge the item by 10% on hover */
}

.modal {
	align-content: center;
}

.close.btn-close {
	color: red;
	font-size: xxx-large;
	padding-left: 30px;
}

.modal {
	display: none; /* Hidden by default */
}

.modal.show {
	display: block; /* Show the modal */
}

#part1, #part2 {
	font-weight: bold;
	font-size: 16px;
}

.mb_1 {
	margin-bottom: -14px;
}

.mb {
	margin-bottom: 0px;
	max-width: fit-content;
	margin-left: auto;
	margin-right: auto;
}

.card {
	background: #fff;
	margin-bottom: 10px;
	box-shadow: 0px 0px 5px #cfcbcb;
	border-radius: 5px;
}

.card-header {
	padding: 10px;
	text-align: center;
	background: aliceblue;
	border-bottom: 0;
	border-radius: 5px 5px 0 0 !important;
}

.card-footer {
	padding: 10px;
	text-align: center;
	background: aliceblue;
	border-bottom: 0;
	border-radius: 5px 5px 0 0 !important;
}

.card-header h4 {
	text-align: center;
	font-weight: bold;
	color: #864663;
	font-size: xx-large;
}

.modal-title h5 {
	text-align: center;
	font-weight: bold;
	color: #864663;
	font-size: x-large;
}

.card-body {
	padding: 10px;
}

.ins_main {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
}

.ins_item {
	width: calc(( 100%/ 5)- 5px);
	margin: 2px;
	padding: 10px;
	background: #d7e9e1;
	text-align: center;
	font-weight: bold;
	border-radius: 10px;
	min-height: 80px;
	display: flex;
	justify-content: center;
	align-items: center;
	border: 5px double #fff;
	cursor: pointer;
}

.clr1 {
	background: #d7e9e1;
}

.clr2 {
	background: #e9e0d7;
}

.clr3 {
	background: #d7d7e9;
}

.clr4 {
	background: #e8e9d7;
}

.clr5 {
	background: #d7dee9;
}

.clr6 {
	background: #e6d7e9;
}

.clr7 {
	background: #dce9d7;
}

.clr8 {
	background: #e9d7d7;
}

.clr9 {
	background: #d7e7e9;
}

.clr10 {
	background: #e5e9d7;
}

body .modal-dialog {
	max-width: 80%;
}

.modal-header {
	background: aliceblue;
	border-bottom: 0;
}

.modal-header .modal-title {
	font-weight: bold;
	text-align: center;
	width: 100%;
}

.btn, .button {
	margin: 2px;
}

@media ( max-width : 1200px) {
	.ins_item {
		width: calc(( 100%/ 4)- 5px);
	}
}

@media ( max-width : 1024px) {
	.ins_item {
		width: calc(( 100%/ 3)- 5px);
	}
}

@media ( max-width : 768px) {
	.ins_item {
		width: calc(( 100%/ 2)- 5px);
	}
}

.display_none {
	display: none;
}

.width_7 {
	width: 3%;
}
</style>





<div class="container-fluid">
	<div class="card">
		<div class="card-header">
		 <a href="merghesearchreport" class="btn btn-primary btn-sm" style="position: absolute; top: 10px; left: 10px;">Back</a>
			<h4>ADM INSP RPT</h4>
			<div class="col-md-12 mb_1" align="center">
				<div class="col-md-4"></div>
				<div class="col-md-4 ">
					<div class="row form-group mb">
						<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link active" id="part3" data-bs-toggle="tab"data-bs-target="#part3_div" type="button" role="tab"
									aria-controls="profile" aria-selected="false"><b>Part III(B)</b></button>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-md-4"></div>
			</div>
		</div>

		<div class="card-body" id="part3_div">
			<div class="ins_main">
<c:if test="${report.overall_strg_chall == 0 || report.overall_strg_chall == 1}">
				<div class="ins_item clr1" id="Overall_Str_and_Challenges_data" onclick="openModal('#Overall_Str_and_Challenges')">Overall Str and Challenges</div>
</c:if>

			</div>

		</div>

		<div class="modal fade" id="Overall_Str_and_Challenges" tabindex="-1"
			role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title text-left" id="exampleModalLabel">Overall
							Strengths and Challenges</h5>

						<button type="button" class="close btn-close"
							onclick="closeModal()">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label for="strengths"><strong>Strengths:</strong></label>
										<textarea class="form-control" id="strengths" name="strengths"
											rows="5"></textarea>
									</div>
									<div class="form-group mt-3">
										<label for="challenges"><strong>Challenges:</strong></label>
										<textarea class="form-control" id="challenges"
											name="challenges" rows="5"></textarea>
									</div>
									<div class="form-group mt-3">
										<label for="advisories"><strong>Advisories:</strong></label>
										<textarea class="form-control" id="advisories"
											name="advisories" rows="5"></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="col-6 text-end">
							<button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
							
							<c:if test="${report.overall_strg_chall == 0}">
							<button type="button" class="btn btn-primary btn-sm add-to-submit_approve"
								data-context="overall_str_and_challenges_items">Approve</button>
						</c:if>

						<c:if test="${report.overall_strg_chall == 1}">
							<label class=" form-control-label"><strong style="color: red;">Data Approved </strong> </label>
						</c:if>
							
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="card-header" align="center">
			<button type="button" class="btn btn-primary btn-sm"
				id="printGenrItAsset">
				<i class="fa fa-download" id="icon_download"></i> Download PDF
			</button>
		</div>
	</div>
</div>

<c:url value="Download_Form_part3B" var="dwonloadUrl"/>
<form:form action="${dwonloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="downloadid">
	<input type="hidden" name="sus_no6" id="sus_no6" value="0">
</form:form>

<script type="text/javascript">
	$("#printGenrItAsset").click(function() {
		$("#sus_no6").val('${sus_no}');
		$("#downloadForm").submit();
	});

	function set_tb_overall_str_and_challenges_Data(data) {
		if (Array.isArray(data) && data.length > 0) {
			var rowData = data[0];
			$('#strengths').val(rowData.strengths || '0');
			$('#challenges').val(rowData.challenges || '0');
			$('#advisories').val(rowData.advisories || '0');
		}
	}

	function GetData(url, modalId) {
		
		var sus_no = '${sus_no}';
		$("#nrWaitLoader").show();

		$.post(url + "?" + key + "=" + value, {
			sus_no : sus_no
		}, function(j) {

			if (modalId == '#Overall_Str_and_Challenges') {
				debugger;
				set_tb_overall_str_and_challenges_Data(j);
			}

		});
		$("#nrWaitLoader").hide();
	}

	function openModal(modalId) {
		debugger
		if (modalId == '#Overall_Str_and_Challenges') {
			debugger;
			GetData('get_Overall_Str_and_Challenges_data_url', modalId);
		}

		var modals = document.querySelectorAll('.modal.show');
		modals.forEach(function(modal) {
			modal.classList.remove('show');
			modal.style.display = 'none';
		});
		var modalToOpen = document.querySelector(modalId);
		if (modalToOpen) {
			modalToOpen.classList.add('show');
			modalToOpen.style.display = 'block';
		}

	}

	function closeModal() {
		var modals = document.querySelectorAll('.modal.show');
		modals.forEach(function(modal) {
			modal.classList.remove('show');
			modal.style.display = 'none'; // Hide the modal
		});
	}

	$("#part3").click(function() {
		// Show home tab and hide profile tab
		$("#part2_div").hide().addClass('display_none').addClass('active');
		$("#part1_div").hide().addClass('display_none').removeClass('active');
		$("#part3_div").show().removeClass('display_none').addClass('active');

		// Update tab states
		$("#part2").removeClass('active');
		$("#part1").removeClass('active');
		$("#part3").addClass('active');
	});

	$(document).on(
		"click",
		".add-to-submit_approve",
			function() {
				console.log("here In Save Method ---> ");
				var $button = $(this);
				var buttonContext = $button.data("context");
				
					var formData = new FormData();
						formData.append("buttonContext", buttonContext);
							if (buttonContext === "overall_str_and_challenges_items") {
							debugger;
							var strengths = $("#strengths").val();
							var challenges = $("#challenges").val();
							var advisories = $("#advisories").val();
							formData.append("sus_no", '${sus_no}');
							formData.append("strengths", strengths);
							formData.append("challenges", challenges);
							formData.append("advisories", advisories);
						} $.ajax({
							type : "POST",
							url : "formDataSave3BAction",
							data : formData,
							dataType : "json",
							processData : false,
							contentType : false,
							headers : {'X-CSRF-TOKEN' : '${_csrf.token}'},
							success : function(response) {
								if (response.success) {
									alert("Data Approved Successfully");
								} else {
									alert("Data Not Saved Successfully");
									}
								},
							error : function(xhr, status, error) {
								console.error("AJAX error saving data:",status, error,xhr.responseText);
								alert("An error occurred while saving data. Please try again.");
							}
						});
					});
</script>