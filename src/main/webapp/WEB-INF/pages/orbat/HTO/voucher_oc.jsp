<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
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
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>


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
	cursor: pointer;
}

.ins_item:hover {
	transform: scale(1.1);
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
	background: #f8f9fa; /* Light gray background */
	margin-bottom: 10px;
	box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow */
	border-radius: 8px;
	border: 1px solid #dee2e6; /* Light border */
}

.card-header {
	padding: 12px;
	text-align: center;
	background: #e9ecef; /* Slightly darker gray */
	border-bottom: 1px solid #dee2e6;
	border-radius: 8px 8px 0 0 !important;
}

.card-footer {
	padding: 12px;
	text-align: center;
	background: #e9ecef; /* Slightly darker gray */
	border-top: 1px solid #dee2e6;
	border-radius: 0 0 8px 8px !important;
}

.card-header h5, .card-footer h5 {
	text-align: center;
	font-weight: bold;
	color: #495057; /* Dark gray text */
	font-size: 1.5rem; /* Slightly larger heading */
}

.modal-title h5 {
	text-align: center;
	font-weight: bold;
	color: #495057; /* Dark gray text */
	font-size: 1.25rem; /* Slightly larger heading */
}

.card-body {
	padding: 20px;
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

/* Styles for the tabs */
.tab {
  overflow: hidden;
  background-color: #f8f9fa; /* Background color similar to card */
  border: 1px solid #dee2e6; /* Light border */
  border-bottom: none; /* Remove bottom border for a cleaner look */
  border-radius: 8px 8px 0 0; /* Rounded corners at the top */
}

/* Style the buttons inside the tab */
.tab button {
  background-color: inherit;
  float: left;
  border: none;
  outline: none;
  cursor: pointer;
  padding: 14px 16px;
  transition: 0.3s;
  font-size: 1rem;
  color: #495057; /* Dark gray text */
}

/* Change background color of buttons on hover */
.tab button:hover {
  background-color: #e9ecef; /* Slightly darker gray on hover */
}

/* Create an active/current tablink class */
.tab button.active {
  background-color: #dee2e6; /* A bit darker for active tab */
  color: #212529; /* Even darker text for active tab */
  font-weight: bold; /* Make the active tab text bold */
}

/* Style the tab content */
.tabcontent {
  display: none;
  padding: 20px; /* Increased padding */
  border: 1px solid #dee2e6; /* Light border */
  border-top: none; /* No border at the top */
  border-radius: 0 0 8px 8px; /* Rounded corners at the bottom */
  background-color: #fff; /* White background for content */
}

/* Form styling */
#vehicleSelectionForm {
    margin-bottom: 0; /* Remove default form margin */
}

#vehicleSelectionForm .btn {
    margin: 5px; /* Add some spacing to the buttons */
}

/* Status selection style */
#voucher_status {
  font-family: 'FontAwesome', Arial;
  background-color: #fff;
  border: 1px solid #ced4da;
  border-radius: 0.25rem;
  color: #495057;
  padding: 0.375rem 0.75rem;
  font-size: 1rem;
  line-height: 1.5;
}
</style>
<!-- TODO -->
<div class="container-fluid">
	<div class="card">

		<div class="card-header">
			<h5>
				<b>MANAGE VOUCHER</b><br>
			</h5>
		</div>
		<div class="card-body"></div>
		
		<div class="col-md-12">
			<div id="susDiv" style="display: none;">
				<div class="col-md-6">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>From SUS No</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="sus_no" name="sus_no" maxlength="8"
									style="font-family: 'FontAwesome', Arial;"
									placeholder=" Search SUS No"
									class="form-control autocomplete" autocomplete="off" readonly>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;">* </strong>To SUS No</label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="to_sus_no" name="to_sus_no" maxlength="8"
									style="font-family: 'FontAwesome', Arial;"
									placeholder=" Search SUS No"
									class="form-control autocomplete" autocomplete="off" readonly>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<div class="col-md-6">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col col-md-4">
							<label class=" form-control-label"><strong
								style="color: red;">* </strong>Status</label>
						</div>
						<div class="col-12 col-md-8">
							<select id="voucher_status" name="voucher_status"
								style="font-family: 'FontAwesome', Arial;" class="form-control autocomplete">
								<option value="-1">Prepare Voucher</option>
								<option value="0">Pending</option>
								<option value="1">Approved</option>
								<option value="2">Rejected</option>
							</select>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="card-footer">
			<button type="button" class="btn btn-primary btn-sm" id="voucherSearch" onclick="validate()">Search</button>
			<a href="voucher_oc" class="btn btn-danger btn-sm" id="clearSearch">Clear</a>
		</div>
		
		
		
	<div style="display: none;" id="detailsDiv">
		<div class="card-header">
			<h5>
				<b> ${unit_name} DETAILS</b><br>
			</h5>
		</div>


		<div class="card-body" id="part1_div">
			<!--  Replaced ins_main div  -->
			<div class="tab">
			  <button class="tablinks active" onclick="openTab(event, 'vehA')">Veh A</button>
			  <button class="tablinks" onclick="openTab(event, 'vehB')">Veh B</button>
			  <button class="tablinks" onclick="openTab(event, 'vehC')">Veh C</button>
			</div>

			<div id="vehA" class="tabcontent">
			  <table id="getVehATable"
					class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead style="font-size: 15px; text-align: center;">
						<tr>
							<th>Select</th>
							<th>Ser No.</th>
							<th>Nomenclature</th>
							<th>MCT No</th>
							<th>BA No</th>
							<th>Engine No</th>
							<th>Chassis No</th>
							<th>Veh Km</th>
							<th>Serviceable</th>
							<th>Vintage</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${list.size() == 0}">
							<tr>
								<td colspan="12" align="center" style="color: red;">Data
									Not Available</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>

			<div id="vehB" class="tabcontent">
				<form id="vehicleSelectionForm">
			  		<table id="getVehBTable"
						class="table no-margin table-striped  table-hover  table-bordered report_print">
						<thead style="font-size: 15px; text-align: center;">
							<tr>
								<th>Select</th>
								<th>Ser No.</th>
								<th>Nomenclature</th>
								<th>MCT No</th>
								<th>BA No</th>
								<th>Engine No</th>
								<th>Chassis No</th>
								<th>Veh Km</th>
								<th>Serviceable</th>
								<th>Vintage</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
							<c:if test="${list.size() == 0}">
								<tr>
									<td colspan="12" align="center" style="color: red;">Data
										Not Available</td>
								</tr>
							</c:if>
						</tbody>
					</table>
				</form>
			</div>

			<div id="vehC" class="tabcontent">
			  <table id="getVehCTable"
					class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead style="font-size: 15px; text-align: center;">
						<tr>
							<th>Select</th>
							<th>Ser No.</th>
							<th>Nomenclature</th>
							<th>MCT No</th>
							<th>BA No</th>
							<th>Engine No</th>
							<th>Chassis No</th>
							<th>Veh Km</th>
							<th>Serviceable</th>
							<th>Vintage</th>
							<th>Action</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
<!-- 			HERE -->
			<div class="modal-footer">
				<button id="prepareVoucherID" type="submit" class="btn btn-primary" onclick="return prepareVoucher();">Prepare Selected</button>
				<div id="buttonAppRejDiv">
					<button id="approveVoucherID" type="submit" class="btn btn-success" onclick="return approveVoucher();">Approve Selected</button>
					<button id="rejectVoucherID" type="submit" class="btn btn-danger" onclick="return rejectVoucher();">Reject Selected</button>
				</div>
			</div>
		</div>

	</div>
</div>
</div>

<div class="modal-open modal" id="getRODetail12"
    style="overflow-y: auto;">
    <div class="modal-dialog" style="max-width: 900px;">
        <div class="modal-content">
            <div id="getRODetailsDiv1">
                <div class="modal-header">
                    <table style="width: 100%;">
                        <tr style="height: 30px;">
                            <td align="left"><img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;"></td>
                            <td align="center">
                                <h5>VIEW VOUCHER FOR BA NO.:</h5>
                            </td>
                            <td align="right"><img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-body">
                    <div class="flow-container">

                        <div class="card-body card-block">
                            <div class="col-md-12">
                                <div class="col-md-6">
                                    <div class="row form-group">
                                        <div class="col col-md-4">
                                            <label class=" form-control-label">IV No:</label>
                                        </div>
                                        <div class="col-12 col-md-8">
                                            <input type="text" id="iv_no" name="iv_no" maxlength="250" class="form-control" disabled>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="row form-group">
                                        <div class="col col-md-4">
                                            <label class=" form-control-label"> RV No:</label>
                                        </div>
                                        <div class="col-12 col-md-8">
                                            <input type="text" id="rv_no" name="rv_no" maxlength="250" class="form-control" disabled>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="col-md-6">
                                    <div class="row form-group">
                                        <div class="col col-md-4">
                                            <label for="text-input" class=" form-control-label">Handing Over Unit</label>
                                        </div>
                                        <div class="col-12 col-md-8">
                                            <input type="text" id="to_unit" name="to_unit" class="form-control" disabled>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="row form-group">
                                        <div class="col col-md-4">
                                            <label for="text-input" class=" form-control-label">Taking Over Unit</label>
                                        </div>
                                        <div class="col-12 col-md-8">
                                            <input type="text" id="from_unit" name="from_unit" class="form-control" disabled>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                            	<div class="col-md-6">
                                    <div class="row form-group">
                                        <div class="col col-md-4">
                                            <label for="text-input" class=" form-control-label">Date:</label>
                                        </div>
                                        <div class="col-12 col-md-8">
                                            <input type="date" id="from_date" name="from_date" class="form-control" max='${date}' disabled>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="row form-group">
                                        <div class="col col-md-4">
                                            <label for="text-input" class=" form-control-label">Date:</label>
                                        </div>
                                        <div class="col-12 col-md-8">
                                            <input type="date" id="to_date" name="to_date" class="form-control" max='${date}' disabled>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div style="text-align: center;">
                                <label class="letterData form-control-label">Auth: SD4 relief pgme as per letter No ________ dt___</label>
                            </div>


                            <div class="col-md-12">
                                <div class="col-md-6">
                                    <div class="row form-group">
                                        <div class="col col-md-4">
                                            <label class=" form-control-label">BA No:</label>
                                        </div>
                                        <div class="col-12 col-md-8">
                                            <input type="text" id="ba_no" name="ba_no" maxlength="250" class="form-control" disabled>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="row form-group">
                                        <div class="col col-md-4">
                                            <label class=" form-control-label"> Veh Nomenclature:</label>
                                        </div>
                                        <div class="col-12 col-md-8">
                                            <input type="text" id="veh_nomen" name="veh_nomen" maxlength="250" class="form-control" disabled>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-12">
                                <div class="col-md-6">
                                    <div class="row form-group">
                                        <div class="col col-md-4">
                                            <label class=" form-control-label">Engine No:</label>
                                        </div>
                                        <div class="col-12 col-md-8">
                                            <input type="text" id="engine_no" name="engine_no" maxlength="250" class="form-control" disabled>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="row form-group">
                                        <div class="col col-md-4">
                                            <label class=" form-control-label"> Chassis No:</label>
                                        </div>
                                        <div class="col-12 col-md-8">
                                            <input type="text" id="chassis_no" name="chassis_no"
                                                maxlength="250" class="form-control" disabled>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            
                                
                                <div class="col-md-6">
                                    <div class="col-md-12" style="border: 1px solid #ced4da; padding: 10px; margin-top: 15px;">
                                    <div>
                                        <label class="form-control-label">Handed Over By</label>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">No</label>
                                            <input type="text" id="handed_over_by_no" name="handed_over_by_no" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Rank</label>
                                            <input type="text" id="handed_over_by_rank" name="handed_over_by_rank" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Name</label>
                                            <input type="text" id="handed_over_by_name" name="handed_over_by_name" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Date</label>
                                            <input type="date" id="handed_over_by_dt" name="handed_over_by_dt" class="form-control" disabled>
                                        </div>
                                    </div>
                                    </div>
                                        <div class="col-md-12" style="border: 1px solid #ced4da; padding: 10px; margin-top: 15px;">
                                    <div>
                                        <label class="form-control-label">Collected By</label>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">No</label>
                                            <input type="text" id="collected_by_no" name="collected_by_no" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Rank</label>
                                            <input type="text" id="collected_by_rank" name="collected_by_rank" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Name</label>
                                            <input type="text" id="collected_by_name" name="collected_by_name" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Date</label>
                                            <input type="date" id="collected_by_dt" name="collected_by_dt" class="form-control" disabled>
                                        </div>
                                    </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="col-md-12" style="border: 1px solid #ced4da; padding: 10px; margin-top: 15px;">
                                    <div>
                                        <label class="form-control-label">Taken Over By</label>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">No</label>
                                            <input type="text" id="taken_over_by_no" name="taken_over_by_no" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Rank</label>
                                            <input type="text" id="taken_over_by_rank" name="taken_over_by_rank" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Name</label>
                                            <input type="text" id="taken_over_by_name" name="taken_over_by_name" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Date</label>
                                            <input type="date" id="taken_over_by_dt" name="taken_over_by_dt" class="form-control" disabled>
                                        </div>
                                    </div>
                                                    </div>
                                                        <div class="col-md-12" style="border: 1px solid #ced4da; padding: 10px; margin-top: 15px;">
                                    <div>
                                        <label class="form-control-label">Countersigned By</label>
                                    </div>
                                    <div class="row form-group">
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">No</label>
                                            <input type="text" id="countersigned_by_no" name="countersigned_by_no" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Rank</label>
                                            <input type="text" id="countersigned_by_rank" name="countersigned_by_rank" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Name</label>
                                            <input type="text" id="countersigned_by_name" name="countersigned_by_name" class="form-control" disabled>
                                        </div>
                                        <div class="col-12 col-md-3">
                                            <label class=" form-control-label">Date</label>
                                            <input type="date" id="countersigned_by_dt" name="countersigned_by_dt" class="form-control" disabled>
                                        </div>
                                    </div>
                                    </div>
                                </div>
<!--                             </div> -->

                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer" align="center">
                <button type="button" class="btn btn-danger btn-sm" onclick="closeModalView();">Close</button>
            </div>
        </div>
    </div>
</div>

<input type="hidden" id="role" name="role" value="${role}">
<input type="hidden" id="p_id" name="p_id" value="0">
<input type="hidden" id="CheckVal" name="CheckVal">
<input type="hidden" id="veh_type" name="veh_type" value="0">

<c:url value="downloadVoucherPDF" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="baNoV">
	<input type="hidden" id="baNoV" name="baNoV" value=""/>
	<input type="hidden" id="susNoV" name="susNoV" value=""/>
</form:form>

<script type="text/javascript">
	$(document).ready(function() {

		mockjax1('getVehATable');
		table = dataTable11('getVehATable');

		mockjax1('getVehBTable');
		table1 = dataTable11('getVehBTable');

		mockjax1('getVehCTable');
		table2 = dataTable11('getVehCTable');

		if ('${roleAccess}' == "Unit") {
			$("#sus_no").attr('readonly', 'readonly');
			var sus_no = $("#sus_no").val();
		}
		$("#sus_no").val('${sus_no}');
		$("#unit_name").val('${unit_name}');
		$("#to_sus_no").val('${to_sus_no}');

		openTab(null, 'vehA');
		$("#veh_type").val("A");
		table.ajax.reload();
		
		if($("#role").val() == "oc_adv_party"){
			$("susDiv").hide();
		} else {
			$("susDiv").show();
		}

	});
	
	function print_report(ba_no) {
		
		var role = $("#role").val();
		
		if (role === "unit_deo") {
	        var sus_no = $("#sus_no").val();
	    } else if (role === "oc_adv_party") {
	    	var sus_no = $("#to_sus_no").val();
	    }
		
		$("#baNoV").val(ba_no);
		$("#susNoV").val(sus_no);
	    document.getElementById('downloadForm').submit();
	}

	function data(tableName) {
		jsondata = [];
		var table = $('#' + tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = -1;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
		
		var role = $("#role").val();
		
		if(role == "unit_deo"){
			var sus_no = $("#sus_no").val();
		} else if(role == "oc_adv_party") {
			var sus_no = $("#to_sus_no").val();
		}
		var veh_type = $("#veh_type").val();
		var voucher_status = $("#voucher_status").val();
		
		if (tableName == "getVehATable" || tableName == "getVehBTable" || tableName == "getVehCTable") {
			$.post("getOCPrepareCount?" + key + "=" + value, {
				Search : Search,
				sus_no : sus_no,
				veh_type : veh_type,
				voucher_status : voucher_status

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("getOCPrepareTable?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				sus_no : sus_no,
				veh_type : veh_type,
				voucher_status : voucher_status

			}, function(j) {

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					if(voucher_status == '-1'){
						table1.column(10).visible(false);
						var checkboxHtml = j[i].checkbox;
						jsondata.push([ checkboxHtml, sr, j[i].mct_nomen, j[i].mct, j[i].ba_no,
								j[i].engine_no, j[i].chasis_no, j[i].kms_run,
								j[i].serv_unsv, j[i].vintage, ""]);
						$("#prepareVoucherID").show();
						$("#approveVoucherID").hide();
						$("#rejectVoucherID").hide();
					} else {
						table1.column(10).visible(true);
						var checkboxHtml = j[i].checkbox;
						jsondata.push([checkboxHtml, sr, j[i].mct_nomen, j[i].mct, j[i].ba_no,
								j[i].engine_no, j[i].chasis_no, j[i].kms_run,
								j[i].serv_unsv, j[i].vintage, j[i].action]);
						$("#prepareVoucherID").hide();
						$("#approveVoucherID").show();
						$("#rejectVoucherID").show();
					}
					
				}
				$("#nSelAll").prop('checked', false);
				$('#tregn').text("0");
				$('#tregnn').text(j.length);
			});
			
		}
	}

	function findselected(){
		var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
			return $(this).attr('id');
		}).get();
		var b=nrSel.join(':');
		$("#CheckVal").val(b);
		$('#tregn').text(nrSel.length);
	}

	function setselectall(){
		if ($("#nSelAll").prop("checked")) {
			$(".nrCheckBox").prop('checked', true);
		} else {
			$(".nrCheckBox").prop('checked', false);
		}

		var l = $('[name="cbox"]:checked').length;
		 $("#tregn").val(l);
		document.getElementById('tregn').innerHTML = l;
		check_count = l;
	}

	var check_count = 0;
	function checkbox_count(obj,id) {
		if(document.getElementById(obj.id).checked == true) {
			check_count++;
		}
		if(document.getElementById(obj.id).checked == false) {
			check_count--;
		}
		document.getElementById('tregn').innerHTML = check_count;
	}

	function openTab(evt, tabName) {
		var i, tabcontent, tablinks;
		tabcontent = document.getElementsByClassName("tabcontent");
		for (i = 0; i < tabcontent.length; i++) {
		  tabcontent[i].style.display = "none";
		}
		tablinks = document.getElementsByClassName("tablinks");
		for (i = 0; i < tablinks.length; i++) {
		  tablinks[i].className = tablinks[i].className.replace(" active", "");
		}
		document.getElementById(tabName).style.display = "block";
		if (evt) {
			evt.currentTarget.className += " active";
		}

		if (tabName == 'vehA') {
			$("#veh_type").val("A");
			table.ajax.reload();
		} else if (tabName == 'vehB') {
			$("#veh_type").val("B");
			table1.ajax.reload();
		} else if (tabName == 'vehC') {
			$("#veh_type").val("C");
			table2.ajax.reload();
		}
	}

	function validate(){
		openTab(event, 'vehA');
		$("#detailsDiv").show();
		table.ajax.reload();
		table1.ajax.reload();
		table2.ajax.reload();
	}

	function prepareVoucher(){
		findselected();
		var a1 = $("#CheckVal").val();

		if(a1 == ""){
			alert("Please select atleast 1 data to prepare voucher");
		}else{
			var from_sus = $("#sus_no").val();
			var to_sus = $("#to_sus_no").val();
			var p_id = a1.split(":");
			var p_id1 = $("#p_id").val();

			for(var i=0; i<p_id.length; i++){

				var ba_no = p_id[i];

					$.post("OC_Prepare_Action?"+key+"="+value,
						{ba_no:ba_no,from_sus:from_sus,to_sus:to_sus}).done(function(j) {
					if(j>0){
						
					}
				});
			}
			alert("Voucher Prepared Successfully");
			table.ajax.reload();
			table1.ajax.reload();
			table2.ajax.reload();
			check_count = 0;
		}
	}
	
	
	function approveVoucher(){
		findselected();
		var a1 = $("#CheckVal").val();

		if(a1 == ""){
			alert("Please select atleast 1 data to approve voucher");
		}else{
			var from_sus = $("#sus_no").val();
			var to_sus = $("#to_sus_no").val();
			var p_id = a1.split(":");
			var p_id1 = $("#p_id").val();

			for(var i=0; i<p_id.length; i++){

				var ba_no = p_id[i];

					$.post("OC_Approve_Action?"+key+"="+value,
						{ba_no:ba_no,from_sus:from_sus,to_sus:to_sus}).done(function(j) {
					if(j>0){

					}
				});
			}
			alert("Data Approved Sucessfully");
				table.ajax.reload();
				table1.ajax.reload();
				table2.ajax.reload();
				check_count = 0;
		}
	}
	
	
	function rejectVoucher(){
		findselected();
		var a1 = $("#CheckVal").val();

		if(a1 == ""){
			alert("Please select atleast 1 data to reject voucher");
		}else{
			var from_sus = $("#sus_no").val();
			var to_sus = $("#to_sus_no").val();
			var p_id = a1.split(":");
			var p_id1 = $("#p_id").val();

			for(var i=0; i<p_id.length; i++){

				var ba_no = p_id[i];

					$.post("OC_Reject_Action?"+key+"="+value,
						{ba_no:ba_no,from_sus:from_sus,to_sus:to_sus}).done(function(j) {
					if(j>0){

					}
				});
			}
			alert("Data Rejected Sucessfully");
				table.ajax.reload();
				table1.ajax.reload();
				table2.ajax.reload();
				check_count = 0;
		}
	}
	
	
	
	function viewVoucher(id) {
		var role = $("#role").val();
		var ba_no = id;
	    var sus_no;

	    if (role === "unit_deo") {
	        sus_no = $("#sus_no").val();
	    } else if (role === "oc_adv_party") {
	        sus_no = $("#to_sus_no").val();
	    } else {
	        console.error("Unknown role: " + role);
	        return;
	    }

	    $.get("getViewVoucherUnits?"+key+"="+value,
			{sus_no:sus_no}).done(function(j) {
			if (j && j.length > 0) {
                var handingOverUnit = j[0][0];
                var takingOverUnit = j[0][1];
                var hDate = j[0][2];
                var tDate = j[0][3];
                var letterNo = j[0][4];
                var letterDate = j[0][5];

                $("#to_unit").val(handingOverUnit);
                $("#from_unit").val(takingOverUnit);
                $("#from_date").val(ParseDateColumn(hDate));
                $("#to_date").val(ParseDateColumn(tDate));
				
                $(".letterData").html("Auth: SD4 relief pgme as per letter No <b><u>" + letterNo + "</u></b> dt <b><u>" + letterDate + "</u></b>");
                
            } else {
                console.warn("No data or invalid data format returned from getViewVoucherUnits.");
                $("#to_unit").val("");
                $("#from_unit").val("");
                $("#from_date").val("");
                $("#to_date").val("");
                $(".letterData").html("Auth: SD4 relief pgme as per letter No ________ dt___");
            }
        })
        
        $.get("getViewVoucherData?"+key+"="+value,
			{sus_no:sus_no, ba_no:ba_no}).done(function(j) {
			if (j && j.length > 0) {
                var baNo = j[0][0];
                var vehNomen = j[0][1];
                var engNo = j[0][2];
                var chaNo = j[0][3];

                $("#ba_no").val(baNo);
                $("#veh_nomen").val(vehNomen);
                $("#engine_no").val(engNo);
                $("#chassis_no").val(chaNo);
				
            } else {
                console.warn("No data or invalid data format returned from getViewVoucherData.");
                
                $("#ba_no").val("");
                $("#veh_nomen").val("");
                $("#engine_no").val("");
                $("#chassis_no").val("");
                
            }
        })
        
        .fail(function(jqXHR, textStatus, errorThrown) {
            console.error("Error fetching voucher units:", textStatus, errorThrown);
            alert("Error fetching voucher units. Please try again.");
        });
	    
	    
	    
	}
	
	
	 function viewDataPopUp(id) {
         var modal = document.getElementById('getRODetail12');
         modal.style.display = "block";

         var baNoHeading = document
                 .querySelector('#getRODetail12 .modal-header h5');

         baNoHeading.textContent = "VIEW Voucher FOR BA NO.: " + id;
		
         window.addEventListener('click', function(event) {
             if (event.target === modal) {
                 closeModalView();
             }
         });
         viewVoucher(id);
     }
	
	function closeModalView() {
	    var modal = document.getElementById('getRODetail12');
	    modal.style.display = "none";
	     window.removeEventListener('click', function(event) {
	        if (event.target == modal) {
	        	closeModalView();
	        }
	    });

	}
</script>