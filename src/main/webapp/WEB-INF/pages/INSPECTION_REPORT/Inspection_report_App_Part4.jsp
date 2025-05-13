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

#part4 {
	font-weight: bold;
	font-size: 16px;
}

.mb_1
{
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
.card-header{
	padding: 10px;	
	text-align: center;
	background: aliceblue;
    border-bottom: 0;
    border-radius: 5px 5px 0 0 !important;
}

.card-footer
{
	padding: 10px;	
	text-align: center;
	background: aliceblue;
    border-bottom: 0;
    border-radius: 5px 5px 0 0 !important;
}
.card-header h4  {
	text-align:center;
    font-weight: bold;
    color: #864663;
  	 font-size: xx-large;
}
.modal-title h5{
	
		text-align:center;
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
	width: calc((100% / 5) - 5px);
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
@media (max-width: 1200px) {
	.ins_item {
		width: calc((100% / 4) - 5px);
	}
}

@media (max-width: 1024px) {
	.ins_item {
		width: calc((100% / 3) - 5px);
	}
}
@media (max-width: 768px) {
	.ins_item {
		width: calc((100% / 2) - 5px);
	}
}

.display_none {
	display: none;
}
.width_7
{
width:3%;
}

</style>

<div class="container-fluid">
	<div class="card">
		<div class="card-header">
			<h4>FITNESS FOR WAR / DESIGNATED ROLE</h4>

			<div class="col-md-12 mb_1" align="center">
				<div class="col-md-4"></div>
				<div class="col-md-4 ">
					<div class="row form-group mb">
						<ul class="nav nav-tabs" id="myTab" role="tablist">
							<li class="nav-item" role="presentation">
								<button class="nav-link" id="part4" data-bs-toggle="tab"
									data-bs-target="#part4_div" type="button" role="tab"
									aria-controls="profile" aria-selected="false">Part IV
								</button>
							</li>
						</ul>
					</div>
				</div>
				<div class="col-md-4"></div>
			</div>
		</div>

		<div class="card-body" id="part4_div">
			<div class="ins_main">
				<c:if test="${report.unit_fitness_items == 0 || report.unit_fitness_items == 1}">
					<div class="ins_item clr1" id="fitness_for_war_or_designated_role_data_1" onclick="openModal('#fitness_for_war_or_designated_role_1')">Strengths/Challenges for the Unit/Formation HQ/Establishment (with respect to the under mentioned)</div>
				</c:if>

				<c:if test="${report.unit_data_items == 0 || report.unit_data_items == 1}">
					<div class="ins_item clr3" id="fitness_for_war_or_designated_role_data_2" onclick="openModal('#fitness_for_war_or_designated_role_2')">Is the Unit</div>
				</c:if>

				<c:if test="${report.critical_issues_items == 0 || report.critical_issues_items == 1}">
					<div class="ins_item clr6" id="fitness_for_war_or_designated_role_data_3" onclick="openModal('#fitness_for_war_or_designated_role_3')">Critical Issues</div>
				</c:if>
			</div>
			
			<c:if test="${report.unit_fitness_items == 1 && report.unit_data_items == 1 && report.critical_issues_items == 1}">
				<button type="button" class="btn btn-primary btn-sm" id="pritnrepot" onclick="return phase2pdf();" >
					<i class="fa fa-download" id="icon_download"></i> Download Controller PDF
				</button>
			</c:if>
			
		</div>

		<div class="modal fade" id="fitness_for_war_or_designated_role_1" tabindex="-1"
			role="dialog" aria-labelledby="unitFitnessModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title text-left" id="unitFitnessModalLabel">Strengths/ Challenges for the Unit/ Formation HQ/ Establishment (with respect to the under mentioned)</h5>
						<button type="button" class="close btn-close" onclick="closeModal()">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-12">
									<!-- Question 39: Strengths/Challenges -->
									<div class="form-group">
										<label><strong>1. Strengths/Challenges for the Unit/Formation HQ/Establishment</strong></label>
										<div class="mt-2">
											<label for="strengths_training">(a) Training and operational preparation for the assigned role/task:</label>
											<textarea class="form-control" id="strengths_training" name="strengths_training" rows="4" disabled></textarea>
										</div>
										<div class="mt-3">
											<label for="challenges_equipment">(b) Equipment profile as necessitated to undertake the assigned role/task:</label>
											<textarea class="form-control" id="challenges_equipment" name="challenges_equipment" rows="4" disabled></textarea>
										</div>
										<div class="mt-3">
											<label for="admin_welfare">(c) Administration of the unit including troops welfare measures:</label>
											<textarea class="form-control" id="admin_welfare" name="admin_welfare" rows="4" disabled></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="col-6 text-right">
							<button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
	
							<c:if test="${report.unit_fitness_items == 0}">
								<button type="button"
									class="btn btn-primary btn-sm add-to-submit_approve"
									data-context="unit_fitness_items">Approve</button>
							</c:if>
	
							<c:if test="${report.unit_fitness_items == 1}">
								<label class=" form-control-label"><strong
									style="color: red;">Data Approved </strong> </label>
							</c:if>
	
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="fitness_for_war_or_designated_role_2" tabindex="-1"
			role="dialog" aria-labelledby="unitFitnessModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title text-left" id="unitFitnessModalLabel">Is the Unit</h5>
						<button type="button" class="close btn-close" onclick="closeModal()">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group mt-4">
										<label><strong>2. Is the Unit:</strong></label>
										<div class="mt-2">
											<label>(a) Fully fit for war in its operational role/designated role?</label>
											<div class="form-check">
												 <label class="form-check-label" for="fit_for_war_yes">
												 <input class="form-check-input" type="radio" name="fit_for_war" id="fit_for_war_yes" value="Yes" disabled>Yes</label>
											</div>
											<div class="form-check">
												<label class="form-check-label" for="fit_for_war_no">
												<input class="form-check-input" type="radio" name="fit_for_war" id="fit_for_war_no" value="No" disabled>No</label>
											</div>
										</div>
										<div class="mt-3">
											<label>(b) If partially fit or unfit for war/designated role, is it due to:</label>
											<div class="form-check">
												
												<label class="form-check-label" for="unfit_equipment">
													<input class="form-check-input" type="radio" name="unfit_reason" id="unfit_equipment"  value="Lack of Equipment" disabled>
													Lack of Equipment
												</label>
											</div>
											<div class="form-check">
												
												<label class="form-check-label" for="unfit_training">
													<input class="form-check-input" type="radio" name="unfit_reason" id="unfit_training" value="Lack of Training" disabled>
													Lack of Training
												</label>
											</div>
											<div class="form-check">
												 <label class="form-check-label" for="unfit_both">
													<input class="form-check-input" type="radio" name="unfit_reason" id="unfit_both" value="Both" disabled>
													Both
												</label>
											</div>
										</div>
										<div class="mt-3">
											<label for="fitness_recommendations">(c) Recommendations to improve fitness for war/designated role:</label>
											<textarea class="form-control" id="fitness_recommendations" name="fitness_recommendations" rows="4" disabled></textarea>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="col-6 text-right">
							<button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
							<c:if test="${report.unit_data_items == 0}">
								<button type="button"
									class="btn btn-primary btn-sm add-to-submit_approve"
									data-context="unit_data_items">Approve</button>
							</c:if>
	
							<c:if test="${report.unit_data_items == 1}">
								<label class=" form-control-label"><strong
									style="color: red;">Data Approved </strong> </label>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="modal fade" id="fitness_for_war_or_designated_role_3" tabindex="-1"
			role="dialog" aria-labelledby="unitFitnessModalLabel"
			aria-hidden="true">
			<div class="modal-dialog modal-lg" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title text-left" id="unitFitnessModalLabel">Critical Issues</h5>
						<button type="button" class="close btn-close" onclick="closeModal()">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="container-fluid">
							<div class="row">
								<div class="col-md-12">
									<div class="form-group mt-4">
										<label for="critical_issues"><strong>3. Critical Issues:</strong></label>
										<textarea class="form-control" id="critical_issues" name="critical_issues" rows="5"
											placeholder="Issues critical to the performance of the unit (to be filled if required)" disabled></textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<div class="col-6 text-right">
							<button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal" onclick="closeModal()">Close</button>
							<c:if test="${report.critical_issues_items == 0}">
								<button type="button"
									class="btn btn-primary btn-sm add-to-submit_approve"
									data-context="critical_issues_items">Approve</button>
							</c:if>
							
							<c:if test="${report.critical_issues_items == 1}">
								<label class="form-control-label"><strong
									style="color: red;">Data Approved </strong> </label>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>
</div>

<c:url value="Download_Form_part4" var="downloadUrl" /> 
<form:form action="${downloadUrl}" method="post" id="downloadForm"
	name="downloadForm" modelAttribute="cont_comd_tx">
</form:form>

<script type="text/javascript">
function phase2pdf() {
	document.getElementById('downloadForm').submit();
}

	$("#printGenrItAsset").click(function() {
		$("#downloadForm").submit();
	});

	function set_fitness_for_war_or_designated_role_1(data) {
		if (Array.isArray(data) && data.length > 0) {
			var rowData = data[0];
			$('#strengths_training').val(rowData.training_and_operational || '0');
			$('#challenges_equipment').val(rowData.equipment_profile || '0');
			$('#admin_welfare').val(rowData.administration || '0');
		}
	}
	
	function set_fitness_for_war_or_designated_role_2(data) {
	    if (Array.isArray(data) && data.length > 0) {
	        var rowData = data[0];
	        
	        var fitForWarValue = rowData.fully_fit_for_war || '0';
	        $('input[name="fit_for_war"][value="' + fitForWarValue + '"]').prop('checked', true);
	        
	        var unfitReasonValue = rowData.fit_or_unfit || '0';
	        $('input[name="unfit_reason"][value="' + unfitReasonValue + '"]').prop('checked', true);
	        
	        $('#fitness_recommendations').val(rowData.recommendations || '0');
	    }
	}
	
	function set_fitness_for_war_or_designated_role_3(data) {
		if (Array.isArray(data) && data.length > 0) {
			var rowData = data[0];
			$('#critical_issues').val(rowData.critical_issues || '0');
		}
	}

	function GetData(url, modalId) {

		var unit_no = "0";
		$("#nrWaitLoader").show();

		$.post(url + "?" + key + "=" + value, {
			unit_no : unit_no
		}, function(j) {

			if (modalId == '#fitness_for_war_or_designated_role_1') {
				set_fitness_for_war_or_designated_role_1(j);
			}
			
			if (modalId == '#fitness_for_war_or_designated_role_2') {
				set_fitness_for_war_or_designated_role_2(j);
			}
			
			if (modalId == '#fitness_for_war_or_designated_role_3') {
				set_fitness_for_war_or_designated_role_3(j);
			}

		});
		$("#nrWaitLoader").hide();
	}

	function openModal(modalId) {
		
		if (modalId == '#fitness_for_war_or_designated_role_1') {
			
			GetData('get_fitness_for_war_or_designated_role_url', modalId);
		}
		
		if (modalId == '#fitness_for_war_or_designated_role_2') {
			
			GetData('get_fitness_for_war_or_designated_role_unit_url', modalId);
		}
		
		if (modalId == '#fitness_for_war_or_designated_role_3') {
			
			GetData('get_fitness_for_war_or_designated_role_critical_url', modalId);
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

	$("#part4").click(function() {
	
		$("#part4_div").show().removeClass('display_none').addClass('active');

		$("#part4").addClass('active');
	});

	$(document)
			.on(
					"click",
					".add-to-submit_approve",
					function() {
						console.log("here In Save Method ---> ");
						var $button = $(this);
						var buttonContext = $button.data("context");
						console.log("here In Save Method buttonContext---> "
								+ buttonContext);
						var formData = new FormData();

						formData.append("buttonContext", buttonContext);

						if (buttonContext === "unit_fitness_items") {
							
							var strengths_training = $("#strengths_training").val();
							var challenges_equipment = $("#challenges_equipment").val();
							var admin_welfare = $("#admin_welfare").val();

							formData.append("strengths_training", strengths_training);
							formData.append("challenges_equipment", challenges_equipment);
							formData.append("admin_welfare", admin_welfare);
						}
						
						if (buttonContext === "unit_data_items") {
							
							var fit_for_war = $('input[name="fit_for_war"]:checked').val();
							var unfit_reason = $('input[name="unfit_reason"]:checked').val();
							var fitness_recommendations = $("#fitness_recommendations").val();

							formData.append("fit_for_war", fit_for_war);
							formData.append("unfit_reason", unfit_reason);
							formData.append("fitness_recommendations", fitness_recommendations);
						}
						
						if (buttonContext === "critical_issues_items") {
							var critical_issues = $("#critical_issues").val();

							formData.append("critical_issues", critical_issues);
						}

						$.ajax({
									type : "POST",
									url : "formDataPhaseIVSaveAction",
									data : formData,
									dataType : "json",
									processData : false,
									contentType : false,
									headers : {
										'X-CSRF-TOKEN' : '${_csrf.token}'
									},
									success : function(response) {
										if (response.success) {
											alert(response.message);
										} else {
											alert("Data Not Saved Successfully");
										}
									},
									error : function(xhr, status, error) {
										console
												.error(
														"AJAX error saving data:",
														status, error,
														xhr.responseText);
										alert("An error occurred while saving data. Please try again.");
									}
								});
					});
</script>