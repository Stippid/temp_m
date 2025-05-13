<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js"></script>
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
	display: none; 
}

.modal.show {
	display: block; 
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

.text-center .btn {
	margin: 0 5px;
}

.custom-btn:hover {
	opacity: 0.8;
	transform: scale(1.1);
	transition: all 0.3s ease;
}

.custom-btn.btn-primary:hover {
	background-color: #0056b3;
}

.custom-btn.btn-success:hover {
	background-color: #218838;
}

.custom-btn.btn-danger:hover {
	background-color: #c82333;
}

.custom-btn.btn-warning:hover {
	background-color: #e0a800;
}

.custom-btn.btn-info:hover {
	background-color: #17a2b8;
}

.custom-btn.btn-secondary:hover {
	background-color: #6c757d;
}

.custom-btn.btn-light:hover {
	background-color: #f8f9fa;
}

.custom-btn.btn-dark:hover {
	background-color: #343a40;
}

</style>

<div class="animated fadeIn">
	<div class="col-md-12" align="center">
		<div class="card">
			<div class="card-header">
				<h5>APPROVE DAILY ANIMAL CENSUS RETURN</h5>
			</div>
			<div class="card-body">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="col col-md-4">
							<label class=" form-control-label">SUS No</label>
						</div>
						<div class="col-12 col-md-8">
							<input type="text" id="sus_no" name="sus_no" placeholder=""
								class="form-control autocomplete" autocomplete="off"
								value="${sus_no}" readonly="readonly">
						</div>
					</div>
					<div class="col-md-6">
						<div class="col col-md-4">
							<label class=" form-control-label">Unit Name</label>
						</div>
						<div class="col-12 col-md-8">
							<textarea id="unit_name" name="unit_name" placeholder=""
								class="form-control autocomplete" style="font-size: 11px;"
								autocomplete="off" readonly="readonly">${unit_name}</textarea>
						</div>
					</div>
				</div>
			</div>
			<div class="card-body"></div>
				<div class="card-body"   id="part1_div">
			<div class="ins_main">
				<div class="ins_item clr1" onclick="openModal('#basic')">Basic Details</div>
				<div class="ins_item clr2" onclick="openModal('#deployment')">Deployment</div>
				<div class="ins_item clr3" onclick="openModal('#utilization')">Utilization</div>
				<div class="ins_item clr4" onclick="openModal('#hm')">Health & Medical</div>
				<div class="ins_item clr5" onclick="openModal('#vc')">Vaccination</div>
				<div class="ins_item clr6" onclick="openModal('#trg')">Training Record</div>
				<div class="ins_item clr9" onclick="openModal('#vd')">Validator Record</div>
				<div class="ins_item clr7" onclick="openModal('#dew')">Deworming</div>
				<div class="ins_item clr8" onclick="openModal('#aw')">Awards Record</div>
			</div>	
		</div>
			<div class="card-body"></div>
			<div class="card-footer" id='aprrove_section' align="center">
				<%-- 	<jsp:include page="../../admin/approve_line.jsp" ></jsp:include> --%>
				<span>I certify that the above data are true to the best of
					my knowledge.</span><br> <br> <input
					type="submit" class="btn btn-success btn-sm" value="Approve"
					onclick="Approved();">
			</div>
		</div>
	</div>
</div>

<!-- Basic Details  -->
<div class="modal fade" id="basic" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">

				<h5 class="modal-title" id="exampleModalLabel">BASIC DETAILS</h5>

				<button class="btn btn-sm " id="next" onclick="openModal('#deployment')"> Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()"> <span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>

						<table id="getBasicTable1"
							class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead>
								<tr>
									<th style="text-align: center;">Ser No</th>
									<th style="text-align: center;">Army No</th>
									<th style="text-align: center;">Name of Dog</th>
									<th style="text-align: center;">DOB</th>
									<th style="text-align: center;">Speciality</th>
									<th style="text-align: center;">Colour</th>
									<th style="text-align: center;">Breed</th>
									<th style="text-align: center;">MCNO</th>
									<th style="text-align: center;">Sex</th>
									<th style="text-align: center;">TOS</th>
									<th style="text-align: center;">Age</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list.size() == 0}">
									<tr>
										<td colspan="7" align="center" style="color: red;">Data
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
<!--Deployment Details -->
<div class="modal fade" id="deployment" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#basic')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">DEPLOYMENT DETAILS</h5>

				<button class="btn btn-sm " id="next" onclick="openModal('#utilization')"> Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()"> <span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>

						<table id="getDeploymentTable1"
							class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead>
								<tr>
									<th style="text-align: center;">Ser No</th>
									<th style="text-align: center;">Army No</th>
									<th style="text-align: center;">Name of Dog</th>
									<th style="text-align: center;">Employment</th>
									<th style="text-align: center;">DogLocation</th>
									<th style="text-align: center;">Fitness</th>
									<th style="text-align: center;">Date Of Admitted</th>
									<th style="text-align: center;">Handler Name</th>
									<th style="text-align: center;">Handler ArmyNo</th>
									<th style="text-align: center;">Handler Unit</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list.size() == 0}">
									<tr>
										<td colspan="7" align="center" style="color: red;">Data
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
<!-- Utilization Details -->
<div class="modal fade" id="utilization" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#deployment')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">UTILIZATION
					DETAILS</h5>
				<button class="btn btn-sm" id="next" onclick="openModal('#hm')">
					Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>
				<button type="button" class="close btn-close" onclick="closeModal()">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<div class="card">
				
				<div class="text-center mb-3">
					<button type="button" class="btn btn-primary custom-btn">ED</button>
					<button type="button" class="btn btn-success custom-btn">MD</button>
					<button type="button" class="btn btn-danger custom-btn">GD</button>
					<button type="button" class="btn btn-secondary custom-btn">IP</button>
					<button type="button" class="btn btn-info custom-btn">SAR</button>
					<button type="button" class="btn btn-secondary custom-btn">ARO</button>
					<button type="button" class="btn btn-danger custom-btn">ASTL</button>
					<button type="button" class="btn btn-success custom-btn">ND</button>
					<button type="button" class="btn btn-primary custom-btn">TR</button>
				</div>
				
				</div>
				<div id="" style="display: none;">
					<table id="getutTable1" >
						</table>
						</div>
				<!-- First Half Table -->
				<div id="firstHalfTable" style="display: none;">
					<table id="getutTable11"
						class="table no-margin table-striped table-hover table-bordered report_print">
						<thead>
							<tr>
								<th>Army No</th>
								<th>Name of Dog</th>
								<th>ROP</th>
								<th>VEH</th>
								<th>S-DUTY</th>
								<th>V-DUTY</th>
								<th>EXPLOSIVE</th>
								<th>ARTICLE</th>
								<th>HIDEOUT ENEMY</th>
								<th>ARMS</th>
								<th>SHBO</th>
								<th>REFRG TRG</th>
								<th>PTRL</th>
								<th>E-SITREP</th>
								<th>REMARKS</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>

				<!-- Second Half Table -->
				<div id="secondHalfTable" style="display: none;">
					<table id="getutTable21"
						class="table no-margin table-striped table-hover table-bordered report_print">
						<thead>
							<tr>
								<th>Army No</th>
								<th>Name of Dog</th>
								<th>ROP</th>
								<th>VEH</th>
								<th>S-DUTY</th>
								<th>PTRL</th>
								<th>EXPLOSIVE</th>
								<th>ARTICLE</th>
								<th>HIDEOUT ENEMY</th>
								<th>ARMS</th>
								<th>SHBO</th>
								<th>REFRG TRG</th>
								<th>MINES</th>
								<th>E-SITREP</th>
								<th>REMARKS</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				
				<!-- 3RD Half Table -->
				<div id="ThirdHalfTable" style="display: none;">
					<table id="getutTable31"
						class="table no-margin table-striped table-hover table-bordered report_print">
						<thead>
							<tr>
								<th>Army No</th>
								<th>Name of Dog</th>
								<th>PTRL</th>
								<th>GUARD</th>
								<th>SHBO</th>
								<th>REFRG TRG</th>
								<th>AREA PTRL</th>
								<th>E-SITREP</th>
								<th>REMARKS</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				
				<!-- 4TH Half Table -->
				<div id="FourthHalfTable" style="display: none;">
					<table id="getutTable41"
						class="table no-margin table-striped table-hover table-bordered report_print">
						<thead>
							<tr>
								<th>Army No</th>
								<th>Name of Dog</th>
								<th>PTRL</th>
								<th>SHBO</th>
								<th>REFRG TRG</th>
								<th>E-SITREP</th>
								<th>REMARKS</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				
				<!-- 5TH Half Table -->
				<div id="FifthHalfTable" style="display: none;">
					<table id="getutTable51"
						class="table no-margin table-striped table-hover table-bordered report_print">
						<thead>
							<tr>
								<th>Army No</th>
								<th>Name of Dog</th>
								<th>SHBO</th>
								<th>REFRG TRG</th>
								<th>DEBRIS</th>
								<th>E-SITREP</th>
								<th>REMARKS</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				
				<!-- 6TH Half Table -->
				<div id="SixthHalfTable" style="display: none;">
					<table id="getutTable61"
						class="table no-margin table-striped table-hover table-bordered report_print">
						<thead>
							<tr>
								<th>Army No</th>
								<th>Name of Dog</th>
								<th>SHBO</th>
								<th>REFRG TRG</th>
								<th>AVALNCHES</th>
								<th>E-SITREP</th>
								<th>REMARKS</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				
				<!-- 7TH Half Table -->
				<div id="SeventhHalfTable" style="display: none;">
					<table id="getutTable71"
						class="table no-margin table-striped table-hover table-bordered report_print">
						<thead>
							<tr>
								<th>Army No</th>
								<th>Name of Dog</th>
								<th>PTRL</th>
								<th>OPS ROOM</th>
								<th>KILLED TERRORIST</th>
								<th>APPREHENDED TERRORIST</th>
								<th>SHBO</th>
								<th>REFRG TRG</th>
								<th>AREA PTRL</th>
								<th>E-SITREP</th>
								<th>REMARKS</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				
		
				<!-- 8TH Half Table -->
				<div id="EighthHalfTable" style="display: none;">
					<table id="getutTable81"
						class="table no-margin table-striped table-hover table-bordered report_print">
						<thead>
							<tr>
								<th>Army No</th>
								<th>Name of Dog</th>
								<th>ARTICLE</th>
								<th>V-DUTY</th>
								<th>SHBO</th>
								<th>REFRG TRG</th>
								<th>E-SITREP</th>
								<th>REMARKS</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				
				<!-- 9TH Half Table -->
				<div id="NinethHalfTable" style="display: none;">
					<table id="getutTable91"
						class="table no-margin table-striped table-hover table-bordered report_print">
						<thead>
							<tr>
								<th>Army No</th>
								<th>Name of Dog</th>
								<th>ROP</th>
								<th>PTRL</th>
								<th>HIDEOUT ENEMY</th>
								<th>ARMS</th>
								<th>SHBO</th>
								<th>REFRG TRG</th>
								<th>ESCAPE</th>
								<th>E-SITREP</th>
								<th>REMARKS</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				

			</div>
		</div>
	</div>
</div>
<!--H&M Details -->
<div class="modal fade" id="hm" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#utilization')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">HEALTH & MEDICAL DETAILS</h5>

				<button class="btn btn-sm " id="next" onclick="openModal('#vc')"> Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()"> <span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>

						<table id="getHealthTable1"
							class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead>
								<tr>
									<th style="text-align: center;">Ser No</th>
									<th style="text-align: center;">Army No</th>
									<th style="text-align: center;">Name of Dog</th>
									<th style="text-align: center;">Hospital</th>
									<th style="text-align: center;">Agr</th>
									<th style="text-align: center;">Albun</th>
									<th style="text-align: center;">Bill</th>
									<th style="text-align: center;">Bun</th>
									<th style="text-align: center;">Creatinine</th>
									<th style="text-align: center;">Dlc</th>
									<th style="text-align: center;">Hemoglobin</th>
									<th style="text-align: center;">Pcv</th>
									<th style="text-align: center;">Platelet</th>
									<th style="text-align: center;">Protein</th>
									<th style="text-align: center;">Sgot</th>
									<th style="text-align: center;">Stool</th>
									<th style="text-align: center;">Tlc</th>
									<th style="text-align: center;">Trbc</th>
									<th style="text-align: center;">Urine</th>
									<th style="text-align: center;">Weight</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list.size() == 0}">
									<tr>
										<td colspan="7" align="center" style="color: red;">Data
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
<!-- Vaccine Details -->
<div class="modal fade" id="vc" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#hm')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">VACCINATION DETAILS</h5>

				<button class="btn btn-sm " id="next" onclick="openModal('#trg')"> Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()"> <span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>

						<table id="getvaccineTable1"
							class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead>
								<tr>
									<th style="text-align: center;">Ser No</th>
									<th style="text-align: center;">Army No</th>
									<th style="text-align: center;">Name of Dog</th>
									<th style="text-align: center;">Vaccine</th>
									<th style="text-align: center;">Frequency</th>
									<th style="text-align: center;">Allergy</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list.size() == 0}">
									<tr>
										<td colspan="7" align="center" style="color: red;">Data
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
<!-- Training Record -->
<div class="modal fade" id="trg" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#vc')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">TRAINING RECORD DETAILS</h5>

				<button class="btn btn-sm " id="next" onclick="openModal('#vd')"> Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()"> <span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>

						<table id="getTRGTable1"
							class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead>
								<tr>
									<th style="text-align: center;">Ser No</th>
									<th style="text-align: center;">Army No</th>
									<th style="text-align: center;">Name of Dog</th>
									<th style="text-align: center;">Trainer unit</th>
									<th style="text-align: center;">Army no</th>
									<th style="text-align: center;">Name</th>
									<th style="text-align: center;">Rank</th>
									<th style="text-align: center;">Mob No</th>
									<th style="text-align: center;">Training status</th>
									<th style="text-align: center;">From</th>
									<th style="text-align: center;">To</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list.size() == 0}">
									<tr>
										<td colspan="7" align="center" style="color: red;">Data
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
<!--Validator Record-->
<div class="modal fade" id="vd" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#trg')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">VALIDATOR RECORD DETAILS</h5>

				<button class="btn btn-sm " id="next" onclick="openModal('#dew')"> Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()"> <span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>

						<table id="getVDTable1"
							class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead>
								<tr>
									<th style="text-align: center;">Ser No</th>
									<th style="text-align: center;">Army No</th>
									<th style="text-align: center;">Name of Dog</th>
									<th style="text-align: center;">Tester unit</th>
									<th style="text-align: center;">Personnel no</th>
									<th style="text-align: center;">Name</th>
									<th style="text-align: center;">Rank</th>
									<th style="text-align: center;">Exam status</th>
									<th style="text-align: center;">Exam remark</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list.size() == 0}">
									<tr>
										<td colspan="7" align="center" style="color: red;">Data
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
<!--DEWORMING DETAILS -->
<div class="modal fade" id="dew" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#vd')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">DEWORMING DETAILS</h5>

				<button class="btn btn-sm " id="next" onclick="openModal('#aw')"> Next <span aria-hidden="true" class="fa fa-arrow-right"></span>
				</button>

				<button type="button" class="close btn-close" onclick="closeModal()"> <span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>

						<table id="getdewormingTable2"
							class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead>
								<tr>
									<th style="text-align: center;">Ser No</th>
									<th style="text-align: center;">Army No</th>
									<th style="text-align: center;">Name of Dog</th>
									<th style="text-align: center;">Used drug</th>
									<th style="text-align: center;">Allergy</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list.size() == 0}">
									<tr>
										<td colspan="7" align="center" style="color: red;">Data
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
<!-- award details  -->
<div class="modal fade" id="aw" tabindex="-1" role="dialog"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button class="btn btn-sm" onclick="openModal('#dew')"
					id="previous">
					<span aria-hidden="true" class="fa fa-arrow-left"></span> Previous
				</button>
				<h5 class="modal-title" id="exampleModalLabel">AWARD DETAILS</h5>

				<button type="button" class="close btn-close" onclick="closeModal()"> <span aria-hidden="true">&times;</span>
				</button>

			</div>
			<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>

						<table id="getawardTable1"
							class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead>
								<tr>
									<th style="text-align: center;">Ser No</th>
									<th style="text-align: center;">Army No</th>
									<th style="text-align: center;">Name of Dog</th>
									<th style="text-align: center;">Award</th>
									<th style="text-align: center;">Remarks</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list.size() == 0}">
									<tr>
										<td colspan="7" align="center" style="color: red;">Data
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

<c:url value="ApprovedAdcrUrl" var="appUrl" />
<form:form action="${appUrl}" method="post" id="appForm" name="appForm"
	modelAttribute="sus_no2">
	<input type="hidden" name="sus_no2" id="sus_no2" value="0" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
	<input type="hidden" name="issue_date1" id="issue_date1" />

</form:form>

<script>
	function Approved() {
		sus = document.getElementById("sus_no").value;
		document.getElementById('sus_no2').value = sus;
		document.getElementById('unit_name1').value = '${unit_name}';
		document.getElementById('issue_date1').value = '${asonDate}';
		document.getElementById('appForm').submit();
	}
</script>

<script type="text/javascript">

$(document).ready(function() {

	mockjax1('getBasicTable1');
	table = dataTable11('getBasicTable1');
	
	mockjax1('getDeploymentTable1');
	table1 = dataTable11('getDeploymentTable1');
	
	mockjax1('getHealthTable1');
	table1 = dataTable11('getHealthTable1');
	
	mockjax1('getvaccineTable1');
	table1 = dataTable11('getvaccineTable1');
	
	mockjax1('getTRGTable1');
	table1 = dataTable11('getTRGTable1');
	
	mockjax1('getVDTable1');
	table1 = dataTable11('getVDTable1');
	
	mockjax1('getdewormingTable2');
	table1 = dataTable11('getdewormingTable2');
	
	mockjax1('getawardTable1');
	table1 = dataTable11('getawardTable1');
	
	if('${roleAccess}' == "Unit"){
		$("#sus_no").attr('readonly','readonly');
		var sus_no=$("#sus_no").val();
	}
	$("#sus_no").val('${sus_no}');
	$("#unit_name").val('${unit_name}');
	
	});


	function data(tableName) {
		jsondata = [];
		var table = $('#' + tableName).DataTable();
		var info = table.page.info();

		var currentPage = info.page;

		var pageLength = -1;
		var startPage = info.start;
		var endPage = info.end;
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];

		var sus_no ='${sus_no}';
		var unitname =$("#unit_name").val(); 
		var asondate ='${asonDate}';
		
		console.log("ason--"+asondate);

		
		 if (tableName == "getBasicTable1") {
			$.post("getBasicdataTableCountPending?" + key + "=" + value, {
				sus_no : sus_no,
				unitname : unitname,
				asondate : asondate
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("getbasicDaTaTablePending?" + key + "=" + value, {
				 startPage: startPage,
				    pageLength: pageLength,
				    orderColunm: orderColunm,
				    orderType: orderType,
				    sus_no: sus_no,
				    unitname: unitname,
				    asondate: asondate
			}, function(j) {

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					jsondata.push([ sr, j[i].army_no,j[i].name,j[i].date_of_birth,j[i].type_splztn,j[i].type_color, j[i].type_breed, j[i].microchip_no, j[i].gender, j[i].date_of_tos, j[i].age]);
				}
			});
			
		}
		 else if (tableName == "getDeploymentTable1") {
				$.post("getDepDaTaTableCountPending?" + key + "=" + value, {
					sus_no : sus_no,
					unitname : unitname,
					asondate : asondate
				}, function(j) {
					FilteredRecords = j;
				});
				$.post("getDepDaTaTablePending?" + key + "=" + value, {
					 startPage: startPage,
					    pageLength: pageLength,
					    orderColunm: orderColunm,
					    orderType: orderType,
					    sus_no: sus_no,
					    unitname: unitname,
					    asondate: asondate
				}, function(j) {

					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;
						jsondata.push([ sr, j[i].army_no,j[i].name,j[i].emp_type,j[i].loc, j[i].fitness_status, j[i].date_of_admited, j[i].emp_name, j[i].emp_no, j[i].unit_name]);
					}
				});
				
			}
		 else if (tableName == "getHealthTable1") {
				$.post("getHMDaTaTableCountPending?" + key + "=" + value, {
					sus_no : sus_no,
					unitname : unitname,
					asondate : asondate
				}, function(j) {
					FilteredRecords = j;
				});
				$.post("getHMDaTaTablePending?" + key + "=" + value, {
					 startPage: startPage,
					    pageLength: pageLength,
					    orderColunm: orderColunm,
					    orderType: orderType,
					    sus_no: sus_no,
					    unitname: unitname,
					    asondate: asondate
				}, function(j) {

					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;
						jsondata.push([ sr, j[i].army_no,j[i].name,j[i].type_hospital,j[i].agr,j[i].albun, j[i].bill, j[i].bun,j[i].creatinine,j[i].dlc, j[i].hemoglobin, j[i].pcv, j[i].platelet,j[i].protein, j[i].sgot, j[i].stool, j[i].tlc,j[i].trbc, j[i].urine, j[i].weight]);
					}
				});
				
			}
		 else if (tableName == "getvaccineTable1") {
				$.post("getVCDaTaTableCountPending?" + key + "=" + value, {
					sus_no : sus_no,
					unitname : unitname,
					asondate : asondate
				}, function(j) {
					FilteredRecords = j;
				});
				$.post("getVCDaTaTablePending?" + key + "=" + value, {
					 startPage: startPage,
					    pageLength: pageLength,
					    orderColunm: orderColunm,
					    orderType: orderType,
					    sus_no: sus_no,
					    unitname: unitname,
					    asondate: asondate
				}, function(j) {

					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;
						jsondata.push([ sr, j[i].army_no, j[i].name, j[i].vaccine_name, j[i].frequency, j[i].allergy]);
					}
				});
				
			}
		 else if (tableName == "getTRGTable1") {
				$.post("getTRGDaTaTableCountPending?" + key + "=" + value, {
					sus_no : sus_no,
					unitname : unitname,
					asondate : asondate
				}, function(j) {
					FilteredRecords = j;
				});
				$.post("getTRGDaTaTablePending?" + key + "=" + value, {
					 startPage: startPage,
					    pageLength: pageLength,
					    orderColunm: orderColunm,
					    orderType: orderType,
					    sus_no: sus_no,
					    unitname: unitname,
					    asondate: asondate
				}, function(j) {

					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;
						jsondata.push([ sr, j[i].army_no, j[i].name, j[i].trainer_unit, j[i].trainer_perno, j[i].trainer_name, j[i].trainer_rank, j[i].trainer_mobno, j[i].trg_status, j[i].trg_from, j[i].trg_to]);
					}
				});
				
			}
		 else if (tableName == "getVDTable1") {
				$.post("getVDDaTaTableCountPending?" + key + "=" + value, {
					sus_no : sus_no,
					unitname : unitname,
					asondate : asondate
				}, function(j) {
					FilteredRecords = j;
				});
				$.post("getVDDaTaTablePending?" + key + "=" + value, {
					 startPage: startPage,
					    pageLength: pageLength,
					    orderColunm: orderColunm,
					    orderType: orderType,
					    sus_no: sus_no,
					    unitname: unitname,
					    asondate: asondate
				}, function(j) {

					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;
						jsondata.push([ sr, j[i].army_no, j[i].name, j[i].tester_unit, j[i].tester_perno, j[i].tester_name, j[i].tester_rank, j[i].exam_status, j[i].tester_remark]);
					}
				});
				
			}
		 else if (tableName == "getdewormingTable2") {
				$.post("getDWDaTaTableCountPending?" + key + "=" + value, {
					sus_no : sus_no,
					unitname : unitname,
					asondate : asondate
				}, function(j) {
					FilteredRecords = j;
				});
				$.post("getDWDaTaTablePending?" + key + "=" + value, {
					 startPage: startPage,
					    pageLength: pageLength,
					    orderColunm: orderColunm,
					    orderType: orderType,
					    sus_no: sus_no,
					    unitname: unitname,
					    asondate: asondate
				}, function(j) {

					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;
						jsondata.push([ sr, j[i].army_no, j[i].name, j[i].used_drug, j[i].allergy]);
					}
				});
				
			}
		 else if (tableName == "getawardTable1") {
				$.post("getawDaTaTableCountPending?" + key + "=" + value, {
					sus_no : sus_no,
					unitname : unitname,
					asondate : asondate
				}, function(j) {
					FilteredRecords = j;
				});
				$.post("getawDaTaTablePending?" + key + "=" + value, {
					 startPage: startPage,
					    pageLength: pageLength,
					    orderColunm: orderColunm,
					    orderType: orderType,
					    sus_no: sus_no,
					    unitname: unitname,
					    asondate: asondate
				}, function(j) {

					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;
						jsondata.push([ sr, j[i].army_no, j[i].name, j[i].award_type, j[i].remarks]);
					}
				});
				
			}
	}
	
	function openModal(modalId) {
	    
	    var modals = document.querySelectorAll('.modal.show');
	    modals.forEach(function(modal) {
	        modal.classList.remove('show');
	        modal.style.display = 'none';
	    });

	    
	    var modalToOpen = document.querySelector(modalId);
	    if (modalToOpen) {
	        modalToOpen.classList.add('show');
	        modalToOpen.style.display = 'block';

	        
	        setTimeout(function () {
	            if (modalId == '#basic') {
	                let table = $('#getBasicTable1').DataTable();
	                table.ajax.reload(null, false);
	                table.columns.adjust().draw();
	            }
	            else if (modalId == '#deployment') {
	                let table = $('#getDeploymentTable1').DataTable();
	                table.ajax.reload(null, false);
	                table.columns.adjust().draw();
	            }
	            else if (modalId == '#hm') {
	                let table = $('#getHealthTable1').DataTable();
	                table.ajax.reload(null, false);
	                table.columns.adjust().draw();
	            }
	            else if (modalId == '#vc') {
	                let table = $('#getvaccineTable1').DataTable();
	                table.ajax.reload(null, false);
	                table.columns.adjust().draw();
	            }
	            else if (modalId == '#trg') {
	                let table = $('#getTRGTable1').DataTable();
	                table.ajax.reload(null, false);
	                table.columns.adjust().draw();
	            }
	            else if (modalId == '#vd') {
	                let table = $('#getVDTable1').DataTable();
	                table.ajax.reload(null, false);
	                table.columns.adjust().draw();
	            }
	            else if (modalId == '#dew') {
	                let table = $('#getdewormingTable2').DataTable();
	                table.ajax.reload(null, false);
	                table.columns.adjust().draw();
	            }
	            else if (modalId == '#aw') {
	                let table = $('#getawardTable1').DataTable();
	                table.ajax.reload(null, false);
	                table.columns.adjust().draw();
	            }
	        }, 100); 
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
<script type="text/javascript">
    $(".custom-btn").on("click", function() {
        var buttonId = $(this).text().trim(); 
        toggleTable(buttonId); 
        fetchDataForButton(buttonId); 
    });

    function toggleTable(button) {
        if (button === 'ED') {
            
            $("#firstHalfTable").show();
            $("#secondHalfTable").hide();
            $("#ThirdHalfTable").hide();
            $("#FourthHalfTable").hide();
            $("#FifthHalfTable").hide();
            $("#SixthHalfTable").hide();
            $("#SeventhHalfTable").hide();
            $("#EighthHalfTable").hide();
            $("#NinethHalfTable").hide();
        } else if (button === 'MD') {
           
        	 $("#firstHalfTable").hide();
             $("#secondHalfTable").show();
             $("#ThirdHalfTable").hide();
             $("#FourthHalfTable").hide();
             $("#FifthHalfTable").hide();
             $("#SixthHalfTable").hide();
             $("#SeventhHalfTable").hide();
             $("#EighthHalfTable").hide();
             $("#NinethHalfTable").hide();
        }else if (button === 'GD') {
           
        	 $("#firstHalfTable").hide();
             $("#secondHalfTable").hide();
             $("#ThirdHalfTable").show();
             $("#FourthHalfTable").hide();
             $("#FifthHalfTable").hide();
             $("#SixthHalfTable").hide();
             $("#SeventhHalfTable").hide();
             $("#EighthHalfTable").hide();
             $("#NinethHalfTable").hide();
        }else if (button === 'IP') {
           
        	 $("#firstHalfTable").hide();
             $("#secondHalfTable").hide();
             $("#ThirdHalfTable").hide();
             $("#FourthHalfTable").show();
             $("#FifthHalfTable").hide();
             $("#SixthHalfTable").hide();
             $("#SeventhHalfTable").hide();
             $("#EighthHalfTable").hide();
             $("#NinethHalfTable").hide();
        }else if (button === 'SAR') {
           
        	 $("#firstHalfTable").hide();
             $("#secondHalfTable").hide();
             $("#ThirdHalfTable").hide();
             $("#FourthHalfTable").hide();
             $("#FifthHalfTable").show();
             $("#SixthHalfTable").hide();
             $("#SeventhHalfTable").hide();
             $("#EighthHalfTable").hide();
             $("#NinethHalfTable").hide();
        }else if (button === 'ARO') {
           
        	 $("#firstHalfTable").hide();
             $("#secondHalfTable").hide();
             $("#ThirdHalfTable").hide();
             $("#FourthHalfTable").hide();
             $("#FifthHalfTable").hide();
             $("#SixthHalfTable").show();
             $("#SeventhHalfTable").hide();
             $("#EighthHalfTable").hide();
             $("#NinethHalfTable").hide();
        }else if (button === 'ASTL') {
           
        	 $("#firstHalfTable").hide();
             $("#secondHalfTable").hide();
             $("#ThirdHalfTable").hide();
             $("#FourthHalfTable").hide();
             $("#FifthHalfTable").hide();
             $("#SixthHalfTable").hide();
             $("#SeventhHalfTable").show();
             $("#EighthHalfTable").hide();
             $("#NinethHalfTable").hide();
        }else if (button === 'ND') {
           
        	 $("#firstHalfTable").hide();
             $("#secondHalfTable").hide();
             $("#ThirdHalfTable").hide();
             $("#FourthHalfTable").hide();
             $("#FifthHalfTable").hide();
             $("#SixthHalfTable").hide();
             $("#SeventhHalfTable").hide();
             $("#EighthHalfTable").show();
             $("#NinethHalfTable").hide();
        }else if (button === 'TR') {
           
        	 $("#firstHalfTable").hide();
             $("#secondHalfTable").hide();
             $("#ThirdHalfTable").hide();
             $("#FourthHalfTable").hide();
             $("#FifthHalfTable").hide();
             $("#SixthHalfTable").hide();
             $("#SeventhHalfTable").hide();
             $("#EighthHalfTable").hide();
             $("#NinethHalfTable").show();
        }
    }

    function fetchDataForButton(button) {
        var spl = '';
        if (button === 'ED') {
            spl = '64'; 
        } else if (button === 'MD') {
            spl = '121'; 
        }else if (button === 'GD') {
            spl = '53'; 
        }else if (button === 'IP') {
            spl = '118'; 
        }else if (button === 'SAR') {
            spl = '124'; 
        }else if (button === 'ARO') {
            spl = '117'; 
        }else if (button === 'ASTL') {
            spl = '52'; 
        }else if (button === 'ND') {
            spl = '131'; 
        }else if (button === 'TR') {
            spl = '121'; 
        }

       
        $("#getutTable11 tbody").empty();  
        $("#getutTable21 tbody").empty();
        $("#getutTable31 tbody").empty(); 
        $("#getutTable41 tbody").empty(); 
        $("#getutTable51 tbody").empty(); 
        $("#getutTable61 tbody").empty(); 
        $("#getutTable71 tbody").empty(); 
        $("#getutTable81 tbody").empty(); 
        $("#getutTable91 tbody").empty(); 
        
       
        $.post("getutDaTaTablePending?" + key + "=" + value, {
            startPage: 0,  
            pageLength: 10, 
            orderColunm: "id", 
            orderType: "asc",  
            sus_no: '${sus_no}',
            unitname: $("#unit_name").val(),
            spl: spl,
            asondate: '${asonDate}'
        }, function(response) {
            
            if (button === 'ED') {
                populateFirstHalfTable(response);
            } else if (button === 'MD') {
                populateSecondHalfTable(response);
            }
            else if (button === 'GD') {
                populateThirdHalfTable(response);
            }
            else if (button === 'IP') {
                populateFourthHalfTable(response);
            }
            else if (button === 'SAR') {
                populateFifthHalfTable(response);
            }
            else if (button === 'ARO') {
                populateSixthHalfTable(response);
            }
            else if (button === 'ASTL') {
                populateSeventhHalfTable(response);
            }
            else if (button === 'ND') {
                populateEighthHalfTable(response);
            }
            else if (button === 'TR') {
                populateNinethHalfTable(response);
            }
        });
    }

   
    function populateFirstHalfTable(data) {
        $("#getutTable11 tbody").empty();  
        data.forEach(function(row) {
            $("#getutTable11 tbody").append(
                '<tr>' +
                '<td>' + row.army_no + '</td>' +
                '<td>' + row.name + '</td>' +
                '<td>' + row.total_rop + '</td>' +
                '<td>' + row.no_of_veh + '</td>' +
                '<td>' + row.sanitisation_duty + '</td>' +
                '<td>' + row.v_duties + '</td>' +
                '<td>' + row.detected_explosive + '</td>' +
                '<td>' + row.no_of_article + '</td>' +
                '<td>' + row.hideout_enemy + '</td>' +
                '<td>' + row.no_of_arms + '</td>' +
                '<td>' + row.no_of_shbo + '</td>' +
                '<td>' + row.no_of_reftrg + '</td>' +
                '<td>' + row.ptr_km + '</td>' +
                '<td>' + row.esitrep_num + '</td>' +
                '<td>' + row.remarks + '</td>' +
                '</tr>'
            );
        });
    }

    
    function populateSecondHalfTable(data) {
        $("#getutTable21 tbody").empty();  
        data.forEach(function(row) {
            $("#getutTable21 tbody").append(
                '<tr>' +
                '<td>' + row.army_no + '</td>' +
                '<td>' + row.name + '</td>' +
                '<td>' + row.total_rop + '</td>' +
                '<td>' + row.no_of_veh + '</td>' +
                '<td>' + row.sanitisation_duty + '</td>' +
                '<td>' + row.ptr_km + '</td>' +
                '<td>' + row.detected_explosive + '</td>' +
                '<td>' + row.no_of_article + '</td>' +
                '<td>' + row.hideout_enemy + '</td>' +
                '<td>' + row.no_of_arms + '</td>' +
                '<td>' + row.no_of_shbo + '</td>' +
                '<td>' + row.no_of_reftrg + '</td>' +
                '<td>' + row.detected_mine + '</td>' +
                '<td>' + row.esitrep_num + '</td>' +
                '<td>' + row.remarks + '</td>' +
                '</tr>'
            );
        });
    }
    
    function populateThirdHalfTable(data) {
        $("#getutTable31 tbody").empty();  
        data.forEach(function(row) {
            $("#getutTable31 tbody").append(
                '<tr>' +
                '<td>' + row.army_no + '</td>' +
                '<td>' + row.name + '</td>' +
                '<td>' + row.ptr_km + '</td>' +
                '<td>' + row.total_guard + '</td>' +
                '<td>' + row.no_of_shbo + '</td>' +
                '<td>' + row.no_of_reftrg + '</td>' +
                '<td>' + row.area_kms + '</td>' +
                '<td>' + row.esitrep_num + '</td>' +
                '<td>' + row.remarks + '</td>' +
                '</tr>'
            );
        });
    }
    
    function populateFourthHalfTable(data) {
        $("#getutTable41 tbody").empty();  
        data.forEach(function(row) {
            $("#getutTable41 tbody").append(
                '<tr>' +
                '<td>' + row.army_no + '</td>' +
                '<td>' + row.name + '</td>' +
                '<td>' + row.ptr_km + '</td>' +
                '<td>' + row.no_of_shbo + '</td>' +
                '<td>' + row.no_of_reftrg + '</td>' +
                '<td>' + row.esitrep_num + '</td>' +
                '<td>' + row.remarks + '</td>' +
                '</tr>'
            );
        });
    }
    
    function populateFifthHalfTable(data) {
        $("#getutTable51 tbody").empty();  
        data.forEach(function(row) {
            $("#getutTable51 tbody").append(
                '<tr>' +
                '<td>' + row.army_no + '</td>' +
                '<td>' + row.name + '</td>' +
                '<td>' + row.no_of_shbo + '</td>' +
                '<td>' + row.no_of_reftrg + '</td>' +
                '<td>' + row.detected_debris + '</td>' +
                '<td>' + row.esitrep_num + '</td>' +
                '<td>' + row.remarks + '</td>' +
                '</tr>'
            );
        });
    }
    
    function populateSixthHalfTable(data) {
        $("#getutTable61 tbody").empty();  
        data.forEach(function(row) {
            $("#getutTable61 tbody").append(
                '<tr>' +
                '<td>' + row.army_no + '</td>' +
                '<td>' + row.name + '</td>' +
                '<td>' + row.no_of_shbo + '</td>' +
                '<td>' + row.no_of_reftrg + '</td>' +
                '<td>' + row.detected_avalnches + '</td>' +
                '<td>' + row.esitrep_num + '</td>' +
                '<td>' + row.remarks + '</td>' +
                '</tr>'
            );
        });
    }
    
    function populateSeventhHalfTable(data) {
        $("#getutTable71 tbody").empty();  
        data.forEach(function(row) {
            $("#getutTable71 tbody").append(
                '<tr>' +
                '<td>' + row.army_no + '</td>' +
                '<td>' + row.name + '</td>' +
                '<td>' + row.ptr_km + '</td>' +
                '<td>' + row.ops_room + '</td>' +
                '<td>' + row.ops_killed + '</td>' +
                '<td>' + row.ops_apprehended + '</td>' +
                '<td>' + row.no_of_shbo + '</td>' +
                '<td>' + row.no_of_reftrg + '</td>' +
                '<td>' + row.area_kms + '</td>' +
                '<td>' + row.esitrep_num + '</td>' +
                '<td>' + row.remarks + '</td>' +
                '</tr>'
            );
        });
    }
    
    function populateEighthHalfTable(data) {
        $("#getutTable81 tbody").empty();  
        data.forEach(function(row) {
            $("#getutTable81 tbody").append(
                '<tr>' +
                '<td>' + row.army_no + '</td>' +
                '<td>' + row.name + '</td>' +
                '<td>' + row.no_of_article + '</td>' +
                '<td>' + row.v_duties + '</td>' +
                '<td>' + row.no_of_shbo + '</td>' +
                '<td>' + row.no_of_reftrg + '</td>' +
                '<td>' + row.esitrep_num + '</td>' +
                '<td>' + row.remarks + '</td>' +
                '</tr>'
            );
        });
    }
    
    function populateNinethHalfTable(data) {
        $("#getutTable91 tbody").empty();  
        data.forEach(function(row) {
            $("#getutTable91 tbody").append(
                '<tr>' +
                '<td>' + row.army_no + '</td>' +
                '<td>' + row.name + '</td>' +
                '<td>' + row.total_rop + '</td>' +
                '<td>' + row.ptr_km + '</td>' +
                '<td>' + row.hideout_enemy + '</td>' +
                '<td>' + row.no_of_arms + '</td>' +
                '<td>' + row.no_of_shbo + '</td>' +
                '<td>' + row.no_of_reftrg + '</td>' +
                '<td>' + row.detected_escape + '</td>' +
                '<td>' + row.esitrep_num + '</td>' +
                '<td>' + row.remarks + '</td>' +
                '</tr>'
            );
        });
    }
</script>
