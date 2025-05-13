<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

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
<link rel="stylesheet"
	href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
	type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.17.4/xlsx.full.min.js"></script>
<style>
.print_btn {
	margin: 0 auto;
}

/* .print_btn input { */
/* 	background-color: #9c27b0; */
/* 	border-color: #9c27b0; */
/* } */

.print_btn input:hover {
	background-color: #cb5adf;
	border-color: #cb5adf;
}

</style>

<form:form name="Report_formation" id="Report_formation"
	action="Report_formation_Action" method="post" class="form-horizontal"
	commandName="Report_formation_CMD">

	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5 style="text-transform: capitalize">Reports</h5>
				<h6 class="enter_by"></h6>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-3">
								<label class=" form-control-label">Date From: </label>
							</div>
						

							<div class="col-md-8">
								<input type="text" name="fromDate" id="fromDate" maxlength="10"
									onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
									style="width: 85%; display: inline;"
									onfocus="this.style.color='#000000'"
									onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
									onkeyup="clickclear(this, 'DD/MM/YYYY')"
									onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
									autocomplete="off" style="color: rgb(0, 0, 0);">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-3 ">
								<label class=" form-control-label"> Date To: </label>
							</div>
							<div class="col-md-8">
								<input type="text" name="toDate" id="toDate" maxlength="10"
									onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control"
									style="width: 85%; display: inline;"
									onfocus="this.style.color='#000000'"
									onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);"
									onkeyup="clickclear(this, 'DD/MM/YYYY')"
									onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true"
									autocomplete="off" style="color: rgb(0, 0, 0);" max="${date}">
							</div>
						</div>
					</div>
				</div>

			</div>
			<div class="card-footer" align="center" class="form-control">
				<input type="button" class="btn btn-success btn-sm" id="btn1"
					value="Clear" onclick="return clearAll();"> <input
					type="button" class="btn btn-info btn-sm" onclick="Search();"
					value="Search">

			</div>

		</div>
	</div>

	<div class="datatablediv" id="printableArea">
	<div id="divShow" style="display: block;"></div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h4>
					<u> CUE & ORBAT INPUTS</u>
				</h4>
				<h5>
					1.<u> Arrival Departure Inputs.</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getArrivalDepartureTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th rowspan="2" id="appt" style="text-align: center;">Ser
								No</th>
							<th rowspan="2" id="adunitname">Unit Name</th>
							<th colspan="2">From</th>
							<th colspan="2">To</th>
							<th rowspan="2" id="nmb_date">NMB</th>
							<th rowspan="2" id="adremark">Remarks</th>
						</tr>
						<tr>
							<th id="fromfmn">Fmn</th>
							<th id="fromnrs">NRS</th>
							<th id="tofmn">Fmn</th>
							<th id="tonrs">NRS</th>
						</tr>
					</thead>

				</table>
			</div>
		</div>



		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>
					2. <u>Reorg/Conversion/Disbandment.</u>
				</h5>
			</div>
		</div>

		<div class="col-md-12" id="getSearch_Letter_a" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="getreorg_conv_disdTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Unit</th>
							<th>From</th>
							<th>To</th>
							<th>Type</th>
							<th>Remarks</th>
						</tr>
					</thead>
				</table>

			</div>
		</div>



		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>
					3. <u>Revision/Amdt in WE/WET/PE/PET.</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_revision_amdtTable" class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Unit</th>
							<th>Fnm</th>
							<th>Arm</th>
							<th>WE/PE/WET/PET/FE</th>
							<th>Due By</th>
							<th>Remarks</th>
						</tr>
					</thead>

				</table>
			</div>
		</div>

		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h4>
					<u>TMS Related Inputs</u>
				</h4>
				<h5>
					4.<u> New Rel.</u>
				</h5>
			</div>
		</div>

		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_tms_new_relTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Dt</th>
							<th>Veh Type</th>
							<th>Rel From</th>
							<th>Rel To</th>
							<th>Qty</th>
							<th>Remarks</th>

						</tr>
					</thead>

				</table>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>
					5.<u> Veh Meeting Discard Condition</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_veh_meeting_discard_conTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Veh Cat</th>
							<th>Veh Type</th>
							<th>Veh Nomen</th>
							<th>BA No</th>
							<th>Km</th>
							<th>Vin</th>
							<th>Cl</th>
							<th>S</th>

						</tr>
					</thead>

				</table>
			</div>
		</div>

		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>
					6.<u> IUT Detls.</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_tms_iut_dtlTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Dt</th>
							<th>Veh Type</th>
							<th>From</th>
							<th>To</th>
							<th>Qty</th>
							<th>BA No</th>
							<th>Status</th>
						</tr>
					</thead>

				</table>
			</div>
		</div>

		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>
					7.<u> Backload Veh Detls</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_tms_bkld_veh_dtlTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>

							<th>Ser No</th>
							<th>Dt</th>
							<th>BA No</th>
							<th>Veh Type</th>
							<th>From Unit</th>
							<th>To Depot</th>
							<th>Qty</th>
							<th>Status</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>
					8.<u>Updation State.</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_tms_updation_stateTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Type</th>
							<th>Fmn</th>
							<th>Force</th>
							<th>Cat Type</th>
							<th>Units</th>
							<th>Updated</th>
							<th>Yet to Update</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>
					9.<u>Holding Defi(Critical A,B,C Veh).</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_tms_hld_defiTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th rowspan="2">Ser No</th>
							<th rowspan="2">Fmn</th>
							<th rowspan="2">Force</th>
							<th rowspan="2">Cat Type</th>
							<th rowspan="2">Unit</th>
							<th rowspan="2">Veh</th>
							<th rowspan="2">UE</th>
							<th colspan="2">UH</th>
							<th rowspan="2">Sur</th>
							<th rowspan="2">Defi</th>
						</tr>
						<tr>
							<th>S</th>
							<th>Unsv</th>

						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h5>
					10.<u>OH/MR Due State.</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_tms_oh_mr_due_stateTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th rowspan="2" >Ser No</th>
							<th rowspan="2" >BA No</th>
							<th rowspan="2" >Veh Type</th>
							<th rowspan="2" >Km</th>
							<th rowspan="2" >Vin</th>
							<th rowspan="2" >Cl</th>
							<th rowspan="2" >S/Unsv</th>
                            <th colspan="2" style="width:10%;">OH1</th>
                            <th colspan="2" style="width:10%;">MR1</th>
                            <th colspan="2" style="width:10%;">OH2</th>
                            <th colspan="2" style="width:10%;">MR2</th>
                            <th colspan="2" style="width:10%;">OH3</th>
                            <th colspan="2" style="width:10%;">MR3</th>							
                            <th rowspan="2" >Status</th>
						</tr>
                                                      <tr>                                                       
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                         <td style="width:5%;">Due</td>
                                                         <td style="width:5%;">Done</td>
                                                           
                                                                 </tr>
					</thead>

				</table>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">
				<h4>
					<u>MMS Related inputs</u>
				</h4>
				<h5>
					11.<u>New Rel.</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_mms_new_reltable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Dt</th>
							<th>Wepn cat</th>
							<th>Rel From</th>
							<th>Rel To</th>
							<th>Qty</th>
							<th>Remarks</th>
						</tr>
					<thead>
				</table>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">

				<h5>
					12.<u>Cont Store Serviceability Condition.</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_cont_store_srvc_conTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Dt</th>
							<th>Cont Store type</th>
							<th>Nomen</th>
							<th>Reg No</th>
							<th>Fmn</th>
							<th>Unit</th>
							<th>S/UNSV</th>
						</tr>

					</thead>
				</table>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">

				<h5>
					13.<u>IUT Detls.</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_mms_iut_dtlTbale"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Dt</th>
							<th>Wepn Cat</th>
							<th>From</th>
							<th>To</th>
							<th>Qty</th>
							<th>Regn No</th>
							<th>Status</th>
						</tr>
					</thead>

				</table>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">

				<h5>
					14.<u>Backload Detls.</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_mms_bkld_dtlTbale"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Dt</th>
							<th>Regn No</th>
							<th>Wpn cat</th>
							<th>From unit</th>
							<th>To Depot</th>
							<th>Qty</th>
							<th>Status</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">

				<h5>
					15.<u>Updation State.</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_mms_updation_stateTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
						<tr>
							<th>Ser No</th>
							<th>Type</th>
							<th>Fmn</th>
							<th>force</th>
							<th>Cat Type</th>
							<th>Units</th>
							<th>Updated</th>
							<th>Yet to Updated</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="card-header">

				<h5>
					16.<u>Defi State(Critical Cont Stores).</u>
				</h5>
			</div>
		</div>
		<div class="col-md-12" id="getSearch_Letter" style="display: block;">
			<div class="watermarked" data-watermark="" id="divwatermark">
				<span id="ip"></span>
				<table id="get_mms_defi_stateTable"
					class="table no-margin table-striped  table-hover  table-bordered ">
					<thead>
						<tr>
							<th>Ser No</th>
							<th>Fmn</th>
							<th>force</th>
							<th>Cat Type</th>
							<th>Unit</th>
							<th>Wpn/Store</th>
							<th>UE</th>
							<th>UH</th>
							<th>%Defi</th>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
			<div class="col-md-12">
		<div align="center" class="print_btn">

			<input type="button" value="Print" class="btn-primary"
				onclick="return printDiv('printableArea')"
				style="color: white; padding: 5px 10px 5px 10px; font-size: 14px; font-weight: bold;">
<!-- 			<input type="button" -->
<!-- 				style="background-color: #4CAF50; border: none; color: white; padding: 5px 10px; text-align: center; text-decoration: none; display: inline-block; font-size: 14px; cursor: pointer; font-weight: bold;" -->
<!-- 				onmouseover="this.style.backgroundColor='#45a049'" -->
<!-- 				onmouseout="this.style.backgroundColor='#4CAF50'" -->
<!-- 				value="Export to Excel" -->
<!-- 				onclick="exportToExcel('IT_Assets', 'IT Asset')"> -->

		</div>
	</div>
		
	</div>


</form:form>
<script>
	 function Search() {
	
		
		  if (getformatedDate($("input#toDate").val()) < getformatedDate($("#fromDate").val())) {
		        alert("Date To should be Greater than Date From");
		        $("#toDate").focus();
		        return false;
		    }
			$("#WaitLoader").show();
		table.ajax.reload();
		table2.ajax.reload();
		$("#WaitLoader").hide();
	}

	$(document).ready(function() {

		jQuery(function($) {

			datepicketDate('fromDate');
			datepicketDate('toDate');

		});

		mockjax1('getArrivalDepartureTable');
		table = dataTable('getArrivalDepartureTable');
		mockjax1('getreorg_conv_disdTable');
		table2 = dataTable('getreorg_conv_disdTable');
		mockjax1('get_revision_amdtTable');
		table3 = dataTable('get_revision_amdtTable');
		mockjax1('get_tms_new_relTable');
		table4 = dataTable('get_tms_new_relTable');
		mockjax1('get_veh_meeting_discard_conTable');
		table5 = dataTable('get_veh_meeting_discard_conTable');
		mockjax1('get_tms_iut_dtlTable');
		table6 = dataTable('get_tms_iut_dtlTable');
		mockjax1('get_tms_bkld_veh_dtlTable');
		table7 = dataTable('get_tms_bkld_veh_dtlTable');
		mockjax1('get_tms_updation_stateTable');
		table8 = dataTable('get_tms_updation_stateTable');
		mockjax1('get_tms_hld_defiTable');
		table9 = dataTable('get_tms_hld_defiTable');
		mockjax1('get_tms_oh_mr_due_stateTable');
		table10 = dataTable('get_tms_oh_mr_due_stateTable');
		mockjax1('get_mms_new_reltable');
		table11 = dataTable('get_mms_new_reltable');
		mockjax1('get_cont_store_srvc_conTable');
		table12 = dataTable('get_cont_store_srvc_conTable');
		mockjax1('get_mms_iut_dtlTbale');
		table13 = dataTable('get_mms_iut_dtlTbale');
		mockjax1('get_mms_bkld_dtlTbale');
		table14 = dataTable('get_mms_bkld_dtlTbale');
		mockjax1('get_mms_updation_stateTable');
		table15 = dataTable('get_mms_updation_stateTable');
		mockjax1('get_mms_defi_stateTable');
		table16 = dataTable('get_mms_defi_stateTable');

		$('#btn-reload').on('click', function() {

			table.ajax.reload();
			table2.ajax.reload();
			table3.ajax.reload();
			table4.ajax.reload();
			table5.ajax.reload();
			table6.ajax.reload();
			table7.ajax.reload();
			table8.ajax.reload();
			table9.ajax.reload();
			table10.ajax.reload();
			table11.ajax.reload();
			table13.ajax.reload();
			table14.ajax.reload();
			table15.ajax.reload();
			table16.ajax.reload();

		});

	});

	function data(tableName) {
		$("#WaitLoader").show();
		jsondata = [];

		var table = $('#' + tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id');
		var orderType = order[0][1];

		var fromDate = $("#fromDate").val();
		var toDate = $("#toDate").val();
		
		if (tableName == "getArrivalDepartureTable") {

			$.post("getArrivalDepartureTable_count?" + key + "=" + value, {
				Search : Search,
				fromDate : fromDate,
				toDate : toDate
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("getArrivalDepartureTable_list?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				fromDate : fromDate,
				toDate : toDate
			}, function(j) {
				console.log(j[0])
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					jsondata.push([ sr, j[i].unit_name, j[i].frm_cmd_name,
							j[i].frm_location, j[i].to_cmd_name,
							j[i].to_location, j[i].nmb_date, j[i].remark ]);

				}
			});
			$("#WaitLoader").hide();
		}
////////////--------------2-----------------
		if (tableName == "getreorg_conv_disdTable") {

			$.post("getreorg_conv_disdTable_count?" + key + "=" + value, {
				Search : Search,
				fromDate : fromDate,
				toDate : toDate
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("getreorg_conv_disdTable_list?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				fromDate : fromDate,
				toDate : toDate
			}, function(j) {
				console.log(j[0])
				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;

					jsondata.push([ sr , j[i].unit_name, j[i].from_unit,
							j[i].to_unit, j[i].type, j[i].remarks ]);

				}
			});
			$("#WaitLoader").hide();
		}
	 
		
////////////--------------3-----------------
	if (tableName == "get_revision_amdtTable") {

		$.post("get_revision_amdtTable_count?" + key + "=" + value, {
			Search : Search,
			fromDate : fromDate,
			toDate : toDate
		}, function(j) {
			FilteredRecords = j;
		});
		$.post("get_revision_amdtTable_list?" + key + "=" + value, {
			startPage : startPage,
			pageLength : pageLength,
			Search : Search,
			orderColunm : orderColunm,
			orderType : orderType,
			fromDate : fromDate,
			toDate : toDate
		}, function(j) {
			console.log(j[0])
			for (var i = 0; i < j.length; i++) {
				var sr = i + 1;

				jsondata.push([ sr , j[i].unit_name, j[i].fmn,j[i].arm_desc,
						j[i].we_pe_wet_pet, j[i].due_by, "" ]);

			}
		});
		$("#WaitLoader").hide();
	}
	

////////////--------------4-----------------
if (tableName == "get_tms_new_relTable") {
 
	$.post("get_tms_new_relTable_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_tms_new_relTable_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].dt, j[i].type_of_vehicle,
					j[i].rel_from, j[i].rel_to,j[i].quantity, j[i].remarks ]);

		}
	});
	$("#WaitLoader").hide();
}


////////////--------------5-----------------
if (tableName == "get_veh_meeting_discard_conTable") {

	$.post("get_veh_meeting_discard_conTable_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_veh_meeting_discard_conTable_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].veh_cat,
					j[i].nomen_four_digit, j[i].nomen_ten_digit, j[i].ba_no ,j[i].kms_run ,j[i].vintage,j[i].classification,j[i].serv_unsv ]);

		}
	});
	$("#WaitLoader").hide();
}


////////////--------------6-----------------
if (tableName == "get_tms_iut_dtlTable") {

	$.post("get_tms_iut_dtlTable_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_tms_iut_dtlTable_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].dt, j[i].vehical_type,
					j[i].from_unit, j[i].unit_to,j[i].qty,j[i].ba_no,j[i].remarks ]);

		}
	});
	$("#WaitLoader").hide();
}

////////////--------------7-----------------
if (tableName == "get_tms_bkld_veh_dtlTable") {

	$.post("get_tms_bkld_veh_dtlTable_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_tms_bkld_veh_dtlTable_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].creation_date, j[i].ba_no,
					j[i].veh_type, j[i].unit_name, j[i].depot_name,j[i].qty,'' ]);

		}
	});
	$("#WaitLoader").hide();
}


////////////--------------8-----------------
if (tableName == "get_tms_updation_stateTable") {

	$.post("get_tms_updation_stateTable_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_tms_updation_stateTable_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].cat_type, j[i].fmn,
					j[i].force, j[i].ct_part_i_ii, j[i].unit_name,j[i].total_updated,j[i].total_yet_to_update ]);

		}
	});
	$("#WaitLoader").hide();
}


////////////--------------9-----------------
if (tableName == "get_tms_hld_defiTable") {

	$.post("get_tms_hld_defiTable_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_tms_hld_defiTable_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].fmn, j[i].force,
					j[i].ct_part_i_ii, j[i].unitname, j[i].veh_cat,j[i].ue,j[i].serviceable_count,j[i].unserviceable_count,j[i].sur,j[i].defi]);

		}
	});
	$("#WaitLoader").hide();
}


////////////--------------10-----------------
if (tableName == "get_tms_oh_mr_due_stateTable") {

	$.post("get_tms_oh_mr_due_stateTable_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_tms_oh_mr_due_stateTable_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].ba_no, j[i].mct_nomen,
					j[i].kms_run,j[i].vintage, j[i].classification,"",j[i].oh1_due_date,"","","",j[i].oh2_due_date,"","","",j[i].oh3_due_date,"","","","" ]);

		}
	});
	$("#WaitLoader").hide();
}


////////////--------------11-----------------
if (tableName == "get_mms_new_reltable") {

	$.post("get_mms_new_reltable_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_mms_new_reltable_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].ro_date, j[i].ro_type,
					j[i].from_unit, j[i].to_unit, j[i].ro_qty,"" ]);

		}
	});
	$("#WaitLoader").hide();
}


////////////--------------12-----------------
if (tableName == "get_cont_store_srvc_conTable") {

	$.post("get_cont_store_srvc_conTable_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_cont_store_srvc_conTable_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].dt, j[i].cont_store_type,
					j[i].nomen, j[i].eqpt_regn_no, j[i].hq_name,j[i].unit_name,j[i].service_status ]);

		}
	});
	$("#WaitLoader").hide();
}


////////////--------------13-----------------
if (tableName == "get_mms_iut_dtlTbale") {

	$.post("get_mms_iut_dtlTbale_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_mms_iut_dtlTbale_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].date, j[i].wep_cat,
				j[i].source_unit, j[i].target_unit,j[i].qty, j[i].regn_no, j[i].pstatus]);

		}
	});
	$("#WaitLoader").hide();
}


////////////--------------14-----------------
if (tableName == "get_mms_bkld_dtlTbale") {

	$.post("get_mms_bkld_dtlTbale_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_mms_bkld_dtlTbale_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].rv_date, j[i].eqpt_regn_no,
					j[i].type_of_eqpt_n, j[i].fromunit, j[i].depo,j[i].qty,j[i].remarks ]);

		}
	});
	$("#WaitLoader").hide();
}


////////////--------------15-----------------
if (tableName == "get_mms_updation_stateTable") {

	$.post("get_mms_updation_stateTable_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_mms_updation_stateTable_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;
		
			jsondata.push([ sr , j[i].prf_group, j[i].hq_name,
					j[i].typeofforce, j[i].ct_part_i_ii,  j[i].unit_name, j[i].updated , j[i].yet_to_up ]);

		}
	});
	$("#WaitLoader").hide();
}


////////////--------------16-----------------
if (tableName == "get_mms_defi_stateTable") {

	$.post("get_mms_defi_stateTable_count?" + key + "=" + value, {
		Search : Search,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		FilteredRecords = j;
	});
	$.post("get_mms_defi_stateTable_list?" + key + "=" + value, {
		startPage : startPage,
		pageLength : pageLength,
		Search : Search,
		orderColunm : orderColunm,
		orderType : orderType,
		fromDate : fromDate,
		toDate : toDate
	}, function(j) {
		console.log(j[0])
		for (var i = 0; i < j.length; i++) {
			var sr = i + 1;

			jsondata.push([ sr , j[i].fmn, j[i].force,
					j[i].item_nomen, j[i].unit_name, j[i].type_of_eqpt_n,j[i].totue,j[i].totuh,j[i].totue-j[i].totuh ]);

		}
	});
	$("#WaitLoader").hide();
}

	}


	function clearAll() {
		$('#fromDate').val("");
		$('#toDate').val("");
	}
	
	
	function printDiv(divName) {
		$("div#divwatermark").val('').addClass('watermarked');
		watermarkreport();
		$("div#divShow").empty();
		$("div#divShow").show();

		var row = "";
		row += '<div class="print_content"> ';
		row += '<div class="print_logo"> ';
		row += '<div class="row"> ';
		row += '<div class="col-3 col-sm-3 col-md-3"><img src="js/miso/images/indianarmy_smrm5aaa.jpg"></div> ';
		row += '<div class="col-6 col-sm-6 col-md-6"><h3>CUE AND ORBAT INPUTS</h3> </div> ';
		row += '<div class="col-3 col-sm-3 col-md-3" align="right"><img src="js/miso/images/dgis-logo.png"></div> ';
		row += '</div> ';
		row += '</div> ';
		$("div#divShow").append(row);

		let popupWinindow;
		let innerContents = document.getElementById(divName).innerHTML;
		popupWinindow = window
				.open(
						'',
						'_blank',
						'width=1400,height=700,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
		popupWinindow.document.open();
		popupWinindow.oncontextmenu = function() {
			return false;
		}

		popupWinindow.document
				.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/miso/assets/scss/style.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:12px;font-weight:normal;} table th{font-size:13px !important;} </style></head><body onload="window.focus(); setTimeout(function(){ window.print(); window.close(); }, 100);" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">'
						+ innerContents + '</html>');

		watermarkreport();
		popupWinindow.document.close();
		document.location.reload();
		$("div#divShow").hide();
	}

	function exportToExcel(tableId, fileName) {
		var table = document.getElementById(tableId);
		var ws = XLSX.utils.table_to_sheet(table);
		var wb = XLSX.utils.book_new();
		XLSX.utils.book_append_sheet(wb, ws, "IT Asset");
		XLSX.writeFile(wb, fileName + ".xlsx");
	}
	
</script>






