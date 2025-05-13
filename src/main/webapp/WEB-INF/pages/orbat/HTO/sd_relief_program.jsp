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

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>
	

<!-- <script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script> -->
<!--  <script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script> -->
<!--   <script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>  -->

<!-- <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script> -->
<!-- <link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css"> -->
<!-- <link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link> -->
<!-- <script src="js/Calender/jquery-ui.js" type="text/javascript"></script> -->

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
</style>

<div class="container-fluid">
	<div class="card">
	
	<div class="card-header">
					<h5> <b>OC TRANSFER VIEW</b><br> </h5>
				</div>
				<div class="card-body"></div>

			<div class="col-md-6" style="text-align:center">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col col-md-4">
							<label class=" form-control-label"><strong style="color: red;">* </strong>From SUS No</label>
						</div>
						<div class="col-12 col-md-8">
							<input type="text" id="sus_no" name="sus_no" maxlength="8" style="font-family: 'FontAwesome', Arial;"
							 placeholder="&#xF002; Search SUS No" class="form-control autocomplete" autocomplete="off">
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col col-md-4">
							<label class=" form-control-label"><strong style="color: red;">* </strong>To SUS No</label>
						</div>
						<div class="col-12 col-md-8">
							<input type="text" id="to_sus_no" name="to_sus_no" maxlength="8" style="font-family: 'FontAwesome', Arial;"
							 placeholder="&#xF002; Search SUS No" class="form-control autocomplete" autocomplete="off" readonly>
						</div>
					</div>
				</div>
			</div>
	
	
<div class="card-header">
					<h5> <b> ${unit_name} DETAILS</b><br> </h5>
				</div>
	
		
		<div class="card-body"   id="part1_div">
			<div class="ins_main">
				<div class="ins_item clr1" onclick="openModal('#vehA')">Veh A</div>
				<div class="ins_item clr2" onclick="openModal('#vehB')">Veh B</div>
				<div class="ins_item clr3" onclick="openModal('#vehC')">Veh C</div>
				<div class="ins_item clr4" >EQPT</div>
			</div>
			
			
		</div>
		
	</div>
</div>

<div class="modal fade" id="vehA" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<h5 class="modal-title" id="exampleModalLabel">'A' Vehicles</h5>

				<button class="btn btn-sm " id="next" onclick="openModal('#vehB')"> Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()"> <span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>

						<table id="getVehATable"  class="table no-margin table-striped  table-hover  table-bordered report_print" >
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th>Ser No.</th>
											<th>Nomenclature</th>
											<th>Mct No</th>
											<th>Ba No</th>
											<th>Engine No</th>
											<th>Chassis No</th>
											<th>Veh Km</th>
											<th>Serviceable</th>
											<th>Vintage</th>
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
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------EQUIMENT------------------------------ -->

<div class="modal fade" id="vehB" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#vehA')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">'B' Vehicles</h5>
				<!-- Next Button -->
				<button class="btn btn-sm " id="next" onclick="openModal('#vehC')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>

				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
							<table id="getVehBTable"  class="table no-margin table-striped  table-hover  table-bordered report_print" >
								<thead style="font-size: 15px; text-align: center;">
									<tr>
										<th>Ser No.</th>
										<th>Nomenclature</th>
										<th>Mct No</th>
										<th>Ba No</th>
										<th>Engine No</th>
										<th>Chassis No</th>
										<th>Veh Km</th>
										<th>Serviceable</th>
										<th>Vintage</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${list.size() == 0}">
										<tr>
											<td colspan="12" align="center" style="color: red;">Data Not Available</td>
										</tr>
									</c:if>
								</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- -----------------------------vehC------------------------------ -->

<div class="modal fade" id="vehC" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<button class="btn btn-sm" onclick="openModal('#vehB')" id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">'C' Vehicles</h5>

				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="getVehCTable"  class="table no-margin table-striped  table-hover  table-bordered report_print" >
								<thead style="font-size: 15px; text-align: center;">
									<tr>
										<th>Ser No.</th>
										<th>Nomenclature</th>
										<th>Mct No</th>
										<th>Ba No</th>
										<th>Engine No</th>
										<th>Chassis No</th>
										<th>Veh Km</th>
										<th>Serviceable</th>
										<th>Vintage</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${list.size() == 0}">
										<tr>
											<td colspan="12" align="center" style="color: red;">Data Not Available</td>
										</tr>
									</c:if>
								</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<input type="hidden" id="veh_type" name="veh_type" value="0">
		</div>
	</div>
</div>

<script type="text/javascript">

$(document).ready(function() {

	mockjax1('getVehATable');
	table = dataTable11('getVehATable');
	
	mockjax1('getVehBTable');
	table1 = dataTable11('getVehBTable');
	
	mockjax1('getVehCTable');
	table2 = dataTable11('getVehCTable');

	if('${roleAccess}' == "Unit"){
		$("#sus_no").attr('readonly','readonly');
		var sus_no=$("#sus_no").val();
	}
	$("#sus_no").val('${sus_no}');
	$("#unit_name").val('${unit_name}');
	$("#to_sus_no").val('${to_sus_no}');
	});


	function data(tableName) {debugger;
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

		var sus_no = $("#to_sus_no").val();
		var veh_type =$("#veh_type").val();

		 if (tableName == "getVehATable" || tableName == "getVehBTable" || tableName == "getVehCTable") {
			$.post("getVehDeyailsTableCount?" + key + "=" + value, {
				Search : Search,
				sus_no : sus_no,
				veh_type:veh_type

			}, function(j) {
				FilteredRecords = j;
			});
			$.post("getVehDeyailsTable?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				sus_no : sus_no,
				veh_type:veh_type

			}, function(j) {debugger;

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([ sr, j[i].mct_nomen,j[i].mct,j[i].ba_no,j[i].engine_no,j[i].chasis_no, j[i].kms_run, j[i].serv_unsv, j[i].vintage ]);
				}
			});
		}
		
		
	}
	

	function openModal(modalId) {debugger

		if (modalId == '#vehA') {
			$("#veh_type").val("A");
			table.ajax.reload();
		}

		if (modalId == '#vehB') {
			$("#veh_type").val("B");
			table.ajax.reload();
		}
		
		if (modalId == '#vehC') {
			$("#veh_type").val("C");
			table2.ajax.reload();
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
	

</script>