<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>


<style>

    @keyframes blink {
        0% { opacity: 1; color: red; } /* Start with red color */
        50% { opacity: 0; color: blue; } /* Change to blue color when blinking */
        100% { opacity: 1; color: red; } /* Back to red color */
    }

    .blink {
        animation: blink 1s infinite; /* Adjust the duration as needed */
    }

th {
	text-align: center; /* Center text in th elements */
}

tr {
	text-align: center; /* Center text in tr elements */
}

h5 {
	margin: 8px; /* Add margin to the h5 element */
	color: black;
	font-size: 26px;
}

.white-uppercase {
	color: white;
	text-transform: uppercase;
	font-size: 20px; /* Adjust the size as needed */
}

.p-0 {
	padding: 0;
}

.progress-circle-container {
	justify-content: center;
	align-items: center;
	width: 100%;
}

.progress-circle-container ul {
	list-style: none;
	padding: 0;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	margin: 0 !important;
}

/* .progress-circle-container li { */
/* 	margin: 0 10px; */
/* 	flex: 1 0 auto; */
/* } */
.progress-circle {
	position: relative;
	width: 120px;
	height: 120px;
	border-radius: 50%;
	background: #e6f2ff; /* Initial background */
	display: flex;
	justify-content: center;
	align-items: center;
	cursor: pointer;
	transition: background 0.5s ease;
}

.progress-circle:hover {
	transform: scale(1.05);
}

.progress-circle::before {
	content: "";
	position: absolute;
	width: 90%;
	height: 90%;
	border-radius: 50%;
	background-color: white;
	z-index: 1;
}

.progress-circle.circle-3d {
	box-shadow: 3px 3px 6px rgba(0, 0, 0, 0.15), -3px -3px 6px
		rgba(255, 255, 255, 0.3);
}

.progress-circle.circle-3d::before {
	
}

.progress-circle .text-container {
	position: relative;
	z-index: 2;
	text-align: center;
}

.progress-circle .text-container .percent-text {
	font-size: 22px;
}

.progress-circle .text-container .label-text {
	font-size: 13px;
	font-weight: normal;
	white-space: nowrap;
}

.progress-square {
	position: relative;
	width: 180px;
	height: 108px;
	background-color: #007bff;
	cursor: pointer;
	display: flex;
	align-items: center;
	justify-content: center;
	/* 	margin-top: 33px; */
	/* 	margin-bottom: 10px; */
	transition: transform 0.3s ease;
	border-radius: 5px;
	box-shadow: 3px 3px 6px rgba(0, 0, 0, 0.15), -3px -3px 6px
		rgba(255, 255, 255, 0.3);
}

.progress-square:hover {
	transform: scale(1.05);
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}

.progress-square .text-container {
	position: absolute;
	font-size: 15px;
	text-align: center;
	line-height: 1.2;
	color: #333;
	font-weight: bold;
}

.progress-square .text-container .percent-text {
	font-size: 22px;
}

.progress-square .text-container .label-text {
	font-size: 16px;
	font-weight: normal;
	white-space: nowrap;
}

.square-table-container {
	display: flex;
	width: 100%;
}

.square-container {
	width: auto;
	display: flex;
	flex-direction: column;
	align-items: center;
}

.max-height {
	height: 95%;
	position: relative;
	top: 0;
	margin-bottom: 4px;
}

.bg-b-green {
	background: linear-gradient(45deg, #2ed8b6, #59e0c5);
}

.info-box {
	width: 100%;
	box-shadow: 0 5px 20px rgba(0, 0, 0, 0.1);
	margin-bottom: 10px;
	padding: 10px;
	border-radius: 10px;
	justify-content: center;
}

.info-box-icon.push-bottom {
	margin-top: 20px;
}

.info-box-icon {
	float: left;
	height: 70px;
	width: 70px;
	text-align: center;
	font-size: 30px;
	line-height: 70px;
	background: rgba(0, 0, 0, 0.2);
	border-radius: 100%;
}

.info-box-content {
	
}

.info-box-text, .progress-description {
	display: block;
	font-size: 15px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	font-weight: 300;
}

.info-box-number {
	font-weight: bold;
	font-size: 20px;
}

.info-box:hover {
	transform: scale(1.05);
	transition: transform 0.3s ease;
}

.info-box .progress, .info-box .progress .progress-bar {
	border-radius: 0;
}

.info-box .progress {
	background: rgba(247 246 246/ 20%);
	margin: 0px 0px 0px 0;
	height: 4px;
}

.progress {
	border: 0;
	background-image: none;
	filter: none;
	-webkit-box-shadow: none;
	-moz-box-shadow: none;
	box-shadow: none;
	height: 8px;
	border-radius: 0 !important;
	margin: 0;
}

.progress {
	display: flex;
	height: 1rem;
	overflow: hidden;
	font-size: .75rem;
	background-color: #e9ecef;
	border-radius: 0.25rem;
}

.progress-description {
	margin: 0;
}

.info-box-text, .progress-description {
	display: block;
	font-size: 17px;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	font-weight: bold;
}

.info-box .progress .progress-bar {
	background: #fff;
}

.info-box .progress, .info-box .progress .progress-bar {
	border-radius: 0;
}

.progress-bar {
	display: flex;
	flex-direction: column;
	justify-content: center;
	overflow: hidden;
	color: #fff;
	text-align: center;
	white-space: nowrap;
	background-color: #0d6efd;
	transition: width .6s ease;
}

.bg-b-yellow {
	background: linear-gradient(45deg, #ffb64d, #ffcb80) !important;
}

.bg-b-darkgreen {
	background: linear-gradient(45deg, #1f9097, #1f9097b3) !important;
}

.bg-b-blue {
	background: linear-gradient(45deg, #1c3552, #2e517a) !important;
}

.bg-b-pink {
	background: linear-gradient(45deg, #ff5370, #ff869a) !important;
}

.bg-b-purple {
	background: linear-gradient(45deg, #301545, #4b216ca8) !important;
}

.bg-b-navy_blue {
	background: linear-gradient(45deg, #101c49, #233269a3) !important;
}

.notifications2 {
	width: 100%;
	position: relative;
	color: #000;
	display: block;
	background: #FFF;
	border: solid 1px rgba(100, 100, 100, .20);
	-webkit-box-shadow: 0 3px 8px rgba(0, 0, 0, .20);
	z-index: 1;
	overflow: hidden;
	margin-bottom: 20px;
}

.notification2 {
	margin-bottom: 1.5em;
	opacity: 1;
	animation: FadeInTop .4s ease .2s forwards;
	display: flex;
}

.notification2:hover span, .notification2:hover p {
	color: #5F98CD;
}

.notification2 span {
	position: relative;
	margin: 0;
	padding: 0;
	font-size: .765em;
	cursor: pointer;
	color: #666666;
	width: 122px;
}

.notification2 p {
	position: relative;
	margin: 0;
	font-size: 1rem;
	cursor: pointer;
	color: #666666;
	max-height: 75px;
	overflow: hidden;
}

.notification2  .time {
	color: red;
}

.horizontal-line2 {
	display: none;
	position: absolute;
	left: 15.4em;
	height: 22.5em;
	width: .2em;
	background: #EBEBEB;
}

.card-height {
	min-height: 285px;
}

.dashboard-unit .card-header {
	background-color: #5fbfe5;
	color: white;
}

.mb-2 {
	margin-bottom: 10px;
}

.btn-round {
	border-radius: 5px;
}

.unit_dashboard {
	margin: 10px 0px;
	border: 2px solid #6173da;
	border-radius: 3px;
	box-shadow: 0 -5px 5px -5px #333, 0 5px 5px -5px #333;
}

.unit_dashboard .btn, .unit_dashboard2 .btn {
	padding: 0 10px;
}

.unit_dashboard2 {
	margin: 10px 0px;
	border: 2px solid #6173da;
	border-radius: 3px;
	box-shadow: 0 -5px 5px -5px #333, 0 5px 5px -5px #333;
}

.unit_dashboard table {
	width: 100%;
	border-collapse: collapse;
}

.unit_dashboard2 table {
	width: 100%;
	border-collapse: collapse;
}

.unit_dashboard tr:nth-of-type(odd) {
	background: #eee;
}

.unit_dashboard2 tr:nth-of-type(odd) {
	background: #eee;
}

.unit_dashboard th {

	background: #0d295c;
	color: white;
	font-weight: bold;
}

.unit_dashboard td, .unit_dashboard th {

	border: 1px solid #ccc;
	text-align: left;
	font-size: 16px;
}

.unit_dashboard2 th {
	background: #0d295c;
	color: white;
	font-weight: bold;
}

.unit_dashboard2 td, .unit_dashboard2 th {
	padding: 4.7px 5px;
	border: 1px solid #ccc;
	text-align: left;
	font-size: 16px;
}

.unit_dashboard2 td {
	border: 1px solid #ccc;
	text-align: left;
	font-size: 16px;
}

.unit_dashboard .title_pers {
	background-color: #0e8bdf !important;
}

.unit_dashboard2 .title_mtrls {
	background-color: #0e8bdf !important;
}

.unit_dashboard2 .title_vehs {
	background-color: #0e8bdf !important;
}

.unit_dashboard td, .unit_dashboard th {
	text-align: center;
}

.unit_dashboard2 td, .unit_dashboard2 th {
	text-align: center;
}

.unit_dashboard .table_heading h4 {
	color: white;
	padding: 4px 0px;
	font-size: 22px;
}

.unit_dashboard2 .table_heading h4 {
	color: white;
	padding: 4px 0px;
	font-size: 22px;
}

.title_main h2 {
	padding: 5px 0px;
	background: #051e7d;
	border-radius: 5px;
	font-size: xx-large;
}

.title_main h6 {
	background: #f2b234;
	border-radius: 5px;
}

.mt-10px {
	margin-top: 10px;
}

.mb-10px {
	margin-bottom: 10px;
}

@media ( max-width : 768px) {
	.padding-x-0 {
		padding-left: 0px !important;
		padding-right: 0px !important;
	}
}

.notifi_head {
	background-color: #0e8bdf;
	color: white;
	padding: 10px 10px;
	font-weight: bolder;
	border-radius: 10px;
	height: 45px;
}

.notifi_h {
	background-color: #0e8bdf;
	color: white;
	padding: 10px 0px;
	font-weight: bolder;
	border-radius: 5px;
	height: auto;
}

.notifi_psg {
	background-color: #0d295c;
	color: white;
	padding: 5px 0px;
	border-radius: 5px;
	z-index: 1;
	position: relative;
	font-size: 22px;
}

.notifi_tms {
	background-color: #0d295c;
	color: white;
	padding: 5px 0px;
	border-radius: 5px;
	z-index: 1;
	position: relative;
	font-size: 22px;
}

.notifi_cue {
	background-color: #0d295c;
	color: white;
	padding: 5px 0px;
	border-radius: 5px;
	z-index: 1;
	position: relative;
	font-size: 22px;
}

.btn-modify {
	background-color: #ffffff00;
	color: #2250d7;
	text-decoration: underline;
	font-weight: bolder;
	padding: 0px 10px;
}

.btn-primary {
	background-color: #ffffff00;
	color: #2250d7;
	text-decoration: underline;
	font-weight: bolder;
	padding: 0px 10px;
}

.btn-primary1 {
	background-color: #ffffff00;
	color: #2250d7;
	text-decoration: underline;
	font-weight: bolder;
	padding: 0px 10px;
}

.btn-primary1:hover, .btn-modify:hover {
	background-color: #ffffff00 !important;
	border-color: #2250d7;
	transform: scale(1.2);
}

.btn-primary1.clicked, .btn-modify.clicked {
	background-color: #ffffff00;
}

@
keyframes scrollUp { 0% {
	transform: translateY(325px);
}

100


























%
{
transform


























:


























translateY
























(


























calc
























(


























-100
























%
+
325px


























)


























)
























;
}
}
#appnoti2, #appnoti3, #appnoti4 {
	overflow: hidden;
	position: relative;
}

.highlighted {
	background-color: #f3f3ce;
}

.emoji {
	padding: 20px;
	font-size: 20px;
}

.bg-indigo {
	background-color: #47d2e0 !important;
	color: #fff;
}

.bg-green {
	background-color: #d3c642 !important;
	color: #fff;
}

.bg-red {
	background-color: #f17a38 !important;
	color: #fff;
}

.bg-deep-purple {
	background-color: #12a0e9 !important;
	color: #fff;
}

.info-box-table {
	width: 100%;
	box-shadow: 20px 20px 60px 0px rgba(0, 0, 1, 0.05);
	margin-bottom: 10px;
	padding: 05px;
	border-radius: 20px;
}
/* .content h5 { font-weight: bolder; color: #182e4a; } */
.tbl_scroll {
	overflow: auto;
	max-height: 200px;
	border-radius: 5px;
}

.info-box-table .table thead {
	position: sticky;
	top: 0;
	z-index: 2;
}

.info-box-table .table tbody {
	position: relative;
	z-index: 1;
}

.mb-0 {
	margin-bottom: 0;
}

.info-box-table table tbody td {
	font-size: 16px;
}

.info-box-table .table thead {
	background-color: #0e8bdf;
	color: white;
	text-align: center;
}

.p-5px {
	padding: 5px;
}

#chartdiv {
	width: 100%;
	height: 100%;
	transform: scale(1);
	transition: transform 0.3s ease;
}

.custom-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 15px;
}

.custom-table th, .custom-table td {
	padding: 8px;
	border: 1px solid #ddd;
	text-align: center;
	color: #000;
	width: auto;
}

.custom-table th {
	background-color: #e6f2ff;
	font-weight: bold;
}
</style>


<div class="content mt-3" align="center">
	<div class="animated fadeIn">
		<div class="container-fluid padding-x-0">

			<div class="title_main">
				<div class="row">
					<div class="col-12">
						<h2 class="notifi_head">${unit_name}DASHBOARD</h2>
					</div>
				</div>
				<!-- 			</div> -->

				<div class="row">
					<div class="col-12 col-md-4 padding-x-0">
						<div class="unit_dashboard">
							<div class="table_heading title_pers">
								<h4>PERS</h4>
							</div>
							<div class="table-responsive">
								<table>
									<thead>
										<tr>
											<th></th>
											<th>Auth</th>
											<th>Held</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Offrs</td>
											<td><button class="btn btn-modify btn-round"
													id="countoffauth" onclick="openFormationwiseDetails(this)">0</button></td>
											<td><button class="btn btn-primary1 btn-round"
													id="countoffheld" value="0"
													onclick="openFormationwiseDetails(this)">0</button></td>
										</tr>
										<tr>
											<td>JCOs</td>
											<td><button class="btn btn-modify btn-round"
													id="countjcoauth" onclick="openFormationwiseDetails(this)">0</button></td>
											<td><button class="btn btn-primary1 btn-round"
													id="countjcoheld" onclick="openFormationwiseDetails(this)">0</button></td>
										</tr>
										<tr>
											<td>OR</td>
											<td><button class="btn btn-modify btn-round"
													id="countorauth" onclick="openFormationwiseDetails(this)">0</button></td>
											<td><button class="btn btn-primary1 btn-round"
													id="countorheld" onclick="openFormationwiseDetails(this)">0</button></td>
										</tr>
										<tr>
											<td>Civ</td>
											<td><button class="btn btn-modify btn-round"
													id="countcivauth" onclick="openFormationwiseDetails(this)">0</button></td>
											<td><button class="btn btn-primary1 btn-round"
													id="countcivheld" onclick="openFormationwiseDetails(this)">0</button></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>

					<div class="col-12 col-md-4 padding-x-0">
						<div class="unit_dashboard2">
							<div class="table_heading title_vehs">
								<h4>VEHS</h4>
							</div>
							<div class="table-responsive">
								<table>
									<thead>
										<tr>
											<th></th>
											<th>Auth</th>
											<th>Held</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>A</td>
											<td><button class="btn btn-modify btn-round"
													id="countaauth" onclick="openvehicleAuth(this)">0</button></td>
											<td><button class="btn btn-primary1 btn-round"
													id="countaheld" onclick="openvehicle(this)">0</button></td>
										</tr>
										<tr>
											<td>B</td>
											<td><button class="btn btn-modify btn-round"
													id="countbauth" onclick="openvehicleAuth(this)">0</button></td>
											<td><button class="btn btn-primary1 btn-round"
													id="countbheld" onclick="openvehicle(this)">0</button></td>
										</tr>
										<tr>
											<td>C</td>
											<td><button class="btn btn-modify btn-round"
													id="countcauth" onclick="openvehicleAuth(this)">0</button></td>
											<td><button class="btn btn-primary1 btn-round"
													id="countcheld" onclick="openvehicle(this)">0</button></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="col-12 col-md-4 padding-x-0">
						<div class="unit_dashboard2">
							<div class="table_heading title_mtrls">
								<h4>EQPTS</h4>
							</div>
							<div class="table-responsive">
								<table>
									<thead>
										<tr>
											<th></th>
											<th>Auth</th>
											<th>Held</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>Wpns</td>
											<td><button class="btn btn-modify btn-round"
													id="countwpnsauth" onclick="openmtrls_Auth(this)">0</button></td>
											<td><button class="btn btn-primary1 btn-round"
													id="countwpnsheld" onclick="openmtrls(this)">0</button></td>
										</tr>
										<tr>
											<td>Eqpts</td>
											<td><button class="btn btn-modify btn-round"
													id="countequiauth" onclick="openmtrls_Auth(this)">0</button></td>
											<td><button class="btn btn-primary1 btn-round"
													id="countequiheld" onclick="openmtrls(this)">0</button></td>
										</tr>
										<tr>
											<td>IT Assets</td>
											<td><button class="btn btn-modify btn-round">0</button></td>
											<td><button class="btn btn-primary1 btn-round"
													id="countitassetheld" onclick="openitassets(this)">0</button></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="">

				<div class="custom-main-card">

					<div class="custom-card-header">
						<h5>Unit Wise Data</h5>
					</div>

					<div class="custom-card-body">
						<div>
							<div class="progress-circle-container">
								<div class="custom-blocks">
									<c:forEach var="row" items="${lastupdateList}"
										varStatus="status">
										<div class="custom-card card${status.index + 1}"
											id="CurrentMonthReport${row.unit_name}"
											data-unit-name="${row.unit_name}" data-sus-no="${row.sus_no}"
											onclick="openReport(this)">
											<div class="text-container txt">
												<span class="label-text"></span>
												<h4 class="form-label white-uppercase">${row.unit_name}</h4>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>

							<div class="info-box-table">
								<div class="tbl_scroll">
									<table
										class="table no-margin table-striped table-hover table-bordered">
										<thead>
											<tr>
												<th rowspan='2' width="10%" style="text-align: center; vertical-align: middle;">Unit Name</th>
												<th rowspan='2' width="10%" style="text-align: center; vertical-align: middle;">Unit Sus</th>
												<th colspan="7">Last Update</th>
											</tr>
											<tr>
												<!-- <th width="10%"></th>
												<th width="10%"></th> -->
												<th width="10%">IAFF 3008</th>
												<th width="10%">IAFF 3009</th>
												<th width="10%">Wpn & Eqpt (MCR)</th>
												<th width="10%">A Veh (FMCR)</th>
												<th width="10%">B Veh (MVCR)</th>
												<th width="10%">C Veh (EMAR)</th>
												<th width="10%">IT Asset</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="row" items="${lastupdateList}">
												<tr>
													<td><c:out value="${row.unit_name}" /></td>
													<td><c:out value="${row.sus_no}" /></td>
													<td><c:out value="${row.date_3008}" /></td>
													<td><c:out value="${row.date_3009}" /></td>
													<td><c:out value="${row.mcr_date}" /></td>
													<td><c:out value="${row.aveh_date}" /></td>
													<td><c:out value="${row.bveh_date}" /></td>
													<td><c:out value="${row.cveh_date}" /></td>
													<td><c:out value="${row.it_asset_date}" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>

						</div>
					</div>
				</div>
			</div>
			<div class="custom-main-card">
    <div class="custom-card-header">
        <h5>MONTHLY UPDATION</h5>
    </div>
    <div class="custom-card-body">
        <div id="notifications2" class="custom-inner-card-main">
            <div class="row">
                <div class="col-md-4">
                    <div class="custom-inner-card">
                        <h4 class="custom-inner-card-title">PERS</h4>
                        <div class="custom-inner-card-body">
                            <marquee id="" class="marquee_psg" scrollamount="2" direction="up" loop="true">
                                <ul>
                                    <li>Offrs Total post in: <strong class="blink">${countPostIn}</strong></li>
                                    <li>Offrs Total post out: <strong class="blink">${countPostOut}</strong></li>
                                    <li>JCOs Total post in: <strong class="blink">${countPostIn_jco}</strong></li>
                                    <li>JCOs Total post out: <strong class="blink">${countPostOut_jco}</strong></li>
                                    <li>Civ Total post in: <strong class="blink">${countPostIn_civ}</strong></li>
                                    <li>Civ Total post out: <strong class="blink">${countPostOut_civ}</strong></li>
                                </ul>
                            </marquee>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="custom-inner-card">
                        <h4 class="custom-inner-card-title">VEHS</h4>
                        <div class="custom-inner-card-body">
                            <marquee id="" class="marquee_tms" scrollamount="2" direction="up" loop="true">
                                <ul>
                                    <li>Total A vehicle add in: <strong class="blink">${countA}</strong></li>
                                    <li>Total B vehicle add in: <strong class="blink">${countB}</strong></li>
                                    <li>Total C vehicle add in: <strong class="blink">${countC}</strong></li>
                                </ul>
                            </marquee>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="custom-inner-card">
                        <h4 class="custom-inner-card-title">IT ASSETS</h4>
                        <div class="custom-inner-card-body">
                            <marquee id="" class="marquee_cue" scrollamount="2" direction="up" loop="true">
                                <ul>
                                    <li>Total computing assets: <strong class="blink">${countComputingAssets}</strong></li>
                                    <li>Total peripherals assets: <strong class="blink">${countPeripheralAssets}</strong></li>
                                </ul>
                            </marquee>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 			<div class="custom-main-card"> -->
<!-- 				<div class="custom-card-header"> -->
<!-- 					<h5>MONTHLY UPDATION</h5> -->
<!-- 				</div> -->
<!-- 				<div class="custom-card-body"> -->
<!-- 					<div id="notifications2" class="custom-inner-card-main"> -->
<!-- 						<div class="row"> -->
<!-- 							<div class="col-md-4"> -->
<!-- 								<div class="custom-inner-card"> -->
<!-- 									<h4 class="custom-inner-card-title">PERS</h4> -->
<!-- 									<div class="custom-inner-card-body"> -->
<!-- 										<marquee id="" class="marquee_psg" scrollamount="2" -->
<!-- 											direction="up" loop="true"> -->
<!-- 											<ul> -->
<%-- 												<li>Offrs Total post in: <strong>${countPostIn}</strong></li> --%>
<%-- 												<li>Offrs Total post out: <strong>${countPostOut}</strong></li> --%>
<%-- 												<li>JCOs Total post in: <strong>${countPostIn_jco}</strong></li> --%>
<%-- 												<li>JCOs Total post out: <strong>${countPostOut_jco}</strong></li> --%>
<%-- 												<li>Civ Total post in: <strong>${countPostIn_civ}</strong></li> --%>
<%-- 												<li>Civ Total post out: <strong>${countPostOut_civ}</strong></li> --%>
<!-- 											</ul> -->
<!-- 										</marquee> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-4"> -->
<!-- 								<div class="custom-inner-card"> -->
<!-- 									<h4 class="custom-inner-card-title">VEHS</h4> -->
<!-- 									<div class="custom-inner-card-body"> -->
<!-- 										<marquee id="" class="marquee_tms" scrollamount="2" -->
<!-- 											direction="up" loop="true"> -->
<!-- 											<ul> -->
<%-- 												<li>Total A vehicle add in: <strong>${countA}</strong></li> --%>
<%-- 												<li>Total B vehicle add in: <strong>${countB}</strong></li> --%>
<%-- 												<li>Total C vehicle add in: <strong>${countC}</strong></li> --%>
<!-- 											</ul> -->
<!-- 										</marquee> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="col-md-4"> -->
<!-- 								<div class="custom-inner-card"> -->
<!-- 									<h4 class="custom-inner-card-title">SD ASPECTS</h4> -->
<!-- 									<div class="custom-inner-card-body"> -->
<!-- 										<marquee id="" class="marquee_cue" scrollamount="2" -->
<!-- 											direction="up" loop="true"> -->
<!-- 											<ul> -->
<%-- 												<li>Total computing assets: <strong>${countComputingAssets}</strong></li> --%>
<%-- 												<li>Total peripherals assets: <strong>${countPeripheralAssets}</strong></li> --%>
<!-- 											</ul> -->
<!-- 										</marquee> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</div>
	</div>
</div>

<input type="hidden" name="sus_no" id="sus_no" />
<c:url value="data_table_tms" var="popup_veh" />
<form:form action="${popup_veh}" method="post" id="tableFormoff"
	name="tableFormoff" modelAttribute="cont_comd4" target="result">
	<input type="hidden" name="sus_no_veh" id="sus_no_veh" />
	<input type="hidden" name="type_veh" id="type_veh" />
</form:form>

<c:url value="data_table_mtrls" var="popup_mtrls" />
<form:form action="${popup_mtrls}" method="post" id="tableFormmtrls"
	name="tableFormmtrls" modelAttribute="cont_comd4" target="result">
	<input type="hidden" name="sus_no_mtrls" id="sus_no_mtrls" />
	<input type="hidden" name="type_mtrls" id="type_mtrls" />
</form:form>

<c:url value="data_table_Auth_tms" var="popup_veh" />
<form:form action="${popup_veh}" method="post" id="tableForAuthtms"
	name="tableForAuthtms" modelAttribute="cont_comd4" target="result">
	<input type="hidden" name="sus_no_veh1" id="sus_no_veh1" />
	<input type="hidden" name="type_veh1" id="type_veh1" />
</form:form>

<c:url value="data_table_mtrls_auth" var="popup_mtrls" />
<form:form action="${popup_mtrls}" method="post" id="tableEqptsAuth"
	name="tableFormmtrls" modelAttribute="cont_comd4" target="result">
	<input type="hidden" name="sus_no_mtrls1" id="sus_no_mtrls1" />
	<input type="hidden" name="type_mtrls1" id="type_mtrls1" />
</form:form>

<c:url value="data_table_popup_bde_data" var="popup_bde" />
<form:form action="${popup_bde}" method="post" id="tableFormoff_report"
	name="tableFormoff_report" modelAttribute="cont_comd4" target="result">
	<input type="hidden" name="unitName" id="unitName" />
	<input type="hidden" name="susno" id="susno" />

</form:form>

<script>
      document.addEventListener("DOMContentLoaded", function() {
              const marquees = document.querySelectorAll('marquee');

              marquees.forEach(marquee => {
                      marquee.addEventListener('mouseover', function() {
                              this.stop();
                      });

                      marquee.addEventListener('mouseout', function() {
                              this.start();
                      });
              });
      });
</script>

<script>
$(document).ready(function() {
    const sus_no = '${sus_no}';
    const formCodeControl = '${formCodeControl}';
    const formation_type = '${form_code}';

    $("#sus_no").val(sus_no);    

    fetchInitialData(sus_no, formCodeControl, formation_type);    
    getNotifications2();   
    fetchMonthlyCountData(sus_no); 
    
//     var countA = "${countA}";
//     var countB = "${countB}";
//     var countC = "${countC}";

//     // Update the HTML elements with the counts
//     $('#countA').text(countA);
//     $('#countB').text(countB);
//     $('#countC').text(countC);
  

});

function fetchInitialData(sus_no, formCodeControl, formation_type) {
    const dataRequests = [
        { url: 'Getcount_offHeld?', target: '#countoffheld', process: (i) => (i.length > 0 ? i[0].offrsposted : 0) },
        { url: 'Getcount_JCOHeld?', target: '#countjcoheld', process: (i) => (i.length > 0 ? i[0].jcosposted : 0) },
        { url: 'Getcount_ORHeld?', target: '#countorheld', process: (i) => (i.length > 0 ? i[0].orposted : 0) },
        { url: 'Getcount_CIVHeld?', target: '#countcivheld', process: (i) => (i.length > 0 ? i[0].civheldcount : 0) },
        { url: 'Getcount_offauth?', target: '#countoffauth', process: (i) => (i.length > 0 ? i[0].offrauth : 0) },
        { url: 'Getcount_JCOAuth?', target: '#countjcoauth', process: (i) => (i.length > 0 ? i[0].jcoauth : 0) },
        { url: 'Getcount_ORAuth?', target: '#countorauth', process: (i) => (i.length > 0 ? i[0].ors : 0) },
        { url: 'Getcount_CIVAuth?', target: '#countcivauth', process: (i) => (i.length > 0 ? i[0].civiliancount : 0) },
         { url: 'Getcount_aauth_held?', target: ['#countaauth', '#countaheld'], process: (i) => (i.length > 0 ? [i[0].ue,i[0].total_uh] : [0,0]) },
        { url: 'Getcount_bAuth_held?', target: ['#countbauth', '#countbheld'], process: (i) => (i.length > 0 ? [i[0].ue,i[0].total_uh] : [0,0]) },
         { url: 'Getcount_cAuth_held?', target: ['#countcauth', '#countcheld'], process: (i) => (i.length > 0 ? [i[0].ue,i[0].total_uh] : [0,0]) },
         { url: 'Getcount_wpns_held?', target: ['#countwpnsauth','#countwpnsheld'], process: (i) => (i.length > 0 ? [i[0].ue,i[0].uh]: [0,0]) },
         { url: 'Getcount_equi_held?', target: ['#countequiauth','#countequiheld'], process: (i) => (i.length > 0 ? [i[0].ue,i[0].uh]: [0,0]) },
         { url: 'Getcount_itAssetHeld?', target: '#countitassetheld', process: (i) => i},
       
    ];

    dataRequests.forEach(req => {
        $.post(req.url + key + "=" + value, {
            sus_no: sus_no,
            formCodeControl: formCodeControl,
            formation_type: formation_type
         }, function(data) {
                if (Array.isArray(req.target)) {
                                       req.target.forEach((target, index) => $(target).text(req.process(data)[index] || 0));
                }
                else{
                $(req.target).text(req.process(data) || 0);
                }
                });
    });
}


// function updateCircleFill(elementId, percentage) {
//     const circle = document.getElementById(elementId);

//     if (!circle) {
//         console.error(`Element with ID "${elementId}" not found.`);
//         return;
//     }

//     const numPercentage = parseFloat(percentage);
//     if (isNaN(numPercentage)) {
//         console.error("Invalid percentage value provided. Please use a number or string representation of a number.");
//         return;
//     }

//     const clampedPercentage = Math.min(Math.max(numPercentage, 0), 100);
//     const convdeg = clampedPercentage * 3.6;
//     const endColor = '#e6f2ff';
//     console.log("Setting circle background for:", elementId, " with convdeg:", convdeg);

//      circle.style.background = `conic-gradient(red 0deg, red ${convdeg}deg, ${endColor} ${convdeg}deg, ${endColor} 360deg)`;
// }


// function fetchMonthlyCountData(sus_no) {
//     const monthlyDataRequests = [
//         { url: 'Get3008monthlyavg?', target: '#3008avgid', circle: "CurrentMonthReport3008" },
//         { url: 'Get3009monthlyavg?', target: '#3009avgid', circle: "CurrentMonthReport3009" },
//         { url: 'itassetcount?', target: '#itassetcountid', circle: null, process: (i) => i + " Item Added This Month" },
//         { url: 'mcravg?', target: '#mcravgid', circle: "CurrentMonthReportMcr" },
//         { url: 'GetAvehmonthlyavg?', target: '#Avehavgid', circle: "AvehCurrentMonth" },
//         { url: 'GetBvehmonthlyavg?', target: '#Bvehavgid', circle: "BvehCurrentMonth" },
//         { url: 'GetCvehmonthlyavg?', target: '#Cvehavgid', circle: "CvehCurrentMonth" }
//     ];

//     monthlyDataRequests.forEach(req => {
//         $.post(req.url + key + "=" + value, {
//             sus_no: sus_no
//         }, function(data) {
//             const percentage = parseFloat(data); // Assuming data is a percentage value
//             const displayText = req.process ? req.process(data) : data + " % ";
//             $(req.target).text(displayText);

//             if (req.circle) {
//                 updateCircleFill(req.circle, percentage); // Pass percentage to updateCircleFill
//             }
//         });
//     });
// }

// function updateCircleFill(circleId, percentage) {
//     const circleElement = $("#" + circleId);
    
//     // Change the color based on the percentage
//     let color;
//     if (percentage < 20) {
//         color = 'red'; // Low percentage
//     } else if (percentage < 50) {
//         color = 'blue'; // Medium percentage
//     } else if (percentage < 70) {
//         color = 'orange'; // High percentage
//     }else {
//         color = 'green'; // High percentage
//     }

//     // Assuming the circle is a div or SVG element, change its background color or fill color
//     circleElement.css('background-color', color);// For a div
//     // or for an SVG circle:
//     // circleElement.attr('fill', color); // Uncomment if using SVG
// }
// function getNotifications2() {

//     $.post('getnotificationList_new?' + key + "=" + value, function(j) {

//         $("#appnoti2").empty();
//         $("#appnoti3").empty();
//         $("#appnoti4").empty();
//         var noti_null = '<div class="notification2"   ><i><b></b></i><p><b></b></p><span></span></div>';
//         noti_counts = 0;
//         var v = j.length;

//         if (v != 0) {
//             var count_psg = 0;
//             var count_tms = 0;
//             var count_cue = 0;
//             var count_null_psg = 0;
//             var count_null_tms = 0;
//             var count_null_cue = 0;

//             for (i = 0; i < v; i++) {
//                 var seen = "";
//                 var newtxt = "";

//                 if (j[i][3] != "" && j[i][3] != null && j[i][3].split(",").includes('${userId}')) {
//                     seen = "";
//                 } else {
//                     seen = "fill-circle2";
//                     newtxt = '<i style=" color: #dc3545;"><b>NEW</b></i>';
//                     noti_counts += 1;
//                 }

//                 var onclick = 'getNotiDetails2(' + j[i][5] + ',\'' + seen + '\')';
//                 var toggle = 'data-toggle="modal" data-target="#myModal" ';

//                 if (j[i][6] != "" && j[i][6] != 0) {

//                     if (j[i][1] == "Please Check the Ticket in Manage Ticket Screen") {
//                         onclick = 'print_ticket(' + j[i][6] + ', ' + j[i][5] + ', \'' + seen + '\')';
//                     } else if (j[i][0] == "Tikect Status") {
//                         onclick = 'print_ticket(' + j[i][6] + ', ' + j[i][5] + ', \'' + seen + '\')';
//                     }
//                      else if (j[i][0] == "Unit Raised") {
//                         onclick = 'view(' + j[i][6] + ', ' + j[i][5] + ', \'' + seen + '\')';
//                     } else {
//                         onclick = 'print(' + j[i][6] + ', ' + j[i][5] + ', \'' + seen + '\')';
//                     }


//                     toggle = '';
//                 }


//                 if (j[i][0] == "POST IN" || j[i][0] == "POST OUT") {
//                     var highlightClass = newtxt !== "" ? 'highlighted' : '';
//                     var notificationHTML =
//                         '<div class="notification2 ' + highlightClass + '" ' + toggle + ' onclick="' + onclick + '">' +
//                         newtxt +
//                         '<p><b>' + j[i][0] + '</b> ' + j[i][1] + '</p>' +
//                         '<span class="time">' + j[i][4].substring(0, 16) + '</span>' +
//                         '</div>';

//                     $("#appnoti2").append(notificationHTML);
//                     count_null_tms++;
//                     count_null_cue++;
//                     count_psg++;
//                 }

//                 if (j[i][0] == "Tikect Status" || j[i][0] == "DRR Initiated" ||
//                     j[i][0] == "B Veh DRR Approved" || j[i][0] == "B Veh DRR Received" || j[i][0] == "A Veh DRR Received" || j[i][0] == "A Veh DRR Approved" || j[i][0] == "C Veh DRR Approved" || j[i][0] == "C Veh DRR Received") {
//                     var highlightClass = newtxt !== "" ? 'highlighted' : '';
//                     var notificationHTML =
//                         '<div class="notification2 ' + highlightClass + '" ' + toggle + ' onclick="' + onclick + '">' +
//                         newtxt +
//                         '<p><b>' + j[i][0] + '</b> ' + j[i][1] + '</p>' +
//                         '<span class="time">' + j[i][4].substring(0, 16) + '</span>' +
//                         '</div>';
//                     $("#appnoti3").append(notificationHTML);
//                     count_null_psg++;
//                     count_null_cue++;
//                     count_tms++;
//                 }
//                 if (j[i][0] == "ETRC Uploaded New WET/PET" || j[i][0] == "SD-6 Uploaded New WE/PE" || j[i][0] == "Link Sus No") {
//                     var highlightClass = newtxt !== "" ? 'highlighted' : '';
//                     var notificationHTML =
//                         '<div class="notification2 ' + highlightClass + '" ' + toggle + ' onclick="' + onclick + '">' +
//                         newtxt +
//                         '<p><b>' + j[i][0] + '</b> ' + j[i][1] + '</p>' +
//                         '<span class="time">' + j[i][4].substring(0, 16) + '</span>' +
//                         '</div>';
//                     $("#appnoti4").append(notificationHTML);
//                     count_null_psg++;
//                     count_null_tms++;
//                     count_cue++;
//                 }
//             }


//             if (count_psg == 0) {
//                 $("#appnoti2").append(
//                     '<div>' +
//                     '<p class="emoji"> 😊  HAVE A NICE DAY  😊 </p>' +
//                     '</div>'
//                 );
//             }

//             if (count_tms == 0) {
//                 $("#appnoti3").append(
//                     '<div  >' +
//                     '<p class="emoji"> 😊  HAVE A NICE DAY  😊 </p>' +
//                     '</div>'
//                 );
//             }

//             if (count_cue == 0) {
//                 $("#appnoti4").append(
//                     '<div >' +
//                     '<p class="emoji"> 😊  HAVE A NICE DAY  😊 </p>' +
//                     '</div>'
//                 );
//             }
//             adddumpy(count_null_psg, count_null_tms, count_null_cue);
//              adjustMarqueeSpeed();
//         }
//         $("#notification_counts").text(noti_counts);
//     });

// }
function adddumpy(count_null_psg, count_null_tms, count_null_cue) {
    var noti_null = '<div class="notification2"   ><i><b></b></i><p><b> </b></p><span></span></div>';
    var noti_psg = '';
    var noti_tms = '';
    var noti_cue = '';
    for (var i = 0; i <= count_null_psg; i++) {
        noti_psg += noti_null;
    }
    for (var i = 0; i <= count_null_tms; i++) {
        noti_tms += noti_null;
    }
    for (var i = 0; i < count_null_cue; i++) {
        noti_cue += noti_null;
    }

    $("#appnoti2").append(noti_psg);
    $("#appnoti3").append(noti_tms);
    $("#appnoti4").append(noti_cue);

}

function adjustMarqueeSpeed() {
var marquees = document.querySelectorAll('.marquee_psg, .marquee_tms, .marquee_cue');
var longestMarquee = 0;
for (var i = 0; i < marquees.length; i++) {
    var marqueeLength = marquees[i].scrollWidth;
    if (marqueeLength > longestMarquee) {
        longestMarquee = marqueeLength;
    }
}
var speed = 8;
var duration = longestMarquee / speed * 500;
for (var i = 0; i < marquees.length; i++) {
    marquees[i].style.setProperty('--animation-duration', duration + 'ms');
    }
}
function getNotiDetails2(id, seen) {
   $.post('getnotificationDetails?' + key + "=" + value, {
        id: id,
        seen: seen
    }, function(j) {
        var v = j.length;
        if (v != 0) {
            var parsedate = "";
            var dataT;
            if (j[0].created_date != null && j[0].created_date != "") {
                dataT = new Date(j[0].created_date);
                parsedate = dataT.getFullYear() + "-" + (dataT.getMonth() + 1) + "-" + dataT.getDay() + " " + (dataT.getHours() + 1) + ":" + dataT.getMinutes() + ""
            }
            $("#noti_detailsdiv").html(' <span class="time" style="text-align: right;color: #666666;"><b>' + parsedate + '</b></span>' +
                '<p><b>' + j[0].title + '</b></p>' +
                '<p style="max-height: 250px; overflow: auto;">' + j[0].description + '</p>');
        } else {
            $("#noti_detailsdiv").html(' <p><b>Unable to fetch data</b></p>');
        }

        getNotifications2();

    });

}
var popupWindow = null;
var count =0;
function openFormationwiseDetails(obj) {
	
	var originalColor = obj.style.background;

    // Change the color to the desired one
   
    if (obj.className === 'btn-modify') {
        obj.style.background = '#e85a10';
    } else {
        obj.style.background = '#a129ad';
    }
	
    var url = obj.id + '?' ;   

	if(popupWindow != null && !popupWindow.closed){
		
		popupWindow.close();
		popupWindow = window.open(url, "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
		popupWindow.onunload = function () {
			if( ! restoreOriginalColor(obj))
      	  {
      	  count++;
      	  }
        };
		window.onfocus = function () { 
		}

}
		else
			{
			popupWindow = window.open(url, "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
			popupWindow.onunload = function () {
	          if( ! restoreOriginalColor(obj))
	        	  {
	        	  count++;
	        	  }
	           
	        };
			window.onfocus = function () { 
			}
			}
	
}

function opencurrentMonthdtl(obj) {	
	
//     var url = obj.id + '?' ;   

	
// 			popupWindow = window.open(url, "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
		
// 			window.onfocus = function () { 
// 			}
		
	
}

function openvehicle(obj) {
	var sus_no=$("#sus_no").val();
	const type_map = {
       countaheld: 'A',
        countbheld: 'B',
        countcheld: 'C'
    };

   $("#type_veh").val(type_map[obj.id]);
   $("#sus_no_veh").val(sus_no);
   
   
  openPopUpForm(popupWindow,'tableFormoff')
}

function openReport(obj) {
	debugger
    var unitName = obj.getAttribute('data-unit-name');
    var susno = obj.getAttribute('data-sus-no');
        document.getElementById('unitName').value = unitName;
        document.getElementById('susno').value = susno;
        openPopUpForm1(popupWindow, 'tableFormoff_report');
    
}
function openmtrls(obj) {
  var sus_no=$("#sus_no").val();
     const type_map = {
       countwpnsheld: 'wpn',
        countequiheld: 'eqi'
    };

     $("#type_mtrls").val(type_map[obj.id]);
	$("#sus_no_mtrls").val(sus_no);

   openPopUpForm(popupWindow,'tableFormmtrls')
}

function openitassets	(obj) {
var originalColor = obj.style.background;
if (obj.className.includes('progress-square')) {
    obj.style.background = '#e6f2ff';
} else {
    obj.style.background = '#e6f2ff';
}
var sus_no=$("#sus_no").val();

var url = obj.id + '?' ;
 

if(popupWindow != null && !popupWindow.closed){
	
	popupWindow.close();
	popupWindow = window.open(url, "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
	popupWindow.onunload = function () {
          if( ! restoreOriginalColor(obj))
        	  {
        	  count++;
        	  }
           
        };
	window.onfocus = function () { 
	}

}
	else
		{
		popupWindow = window.open(url, "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
		popupWindow.onunload = function () {
	          if( ! restoreOriginalColor(obj))
	        	  {
	        	  count++;
	        	  }
	           
	        };
		window.onfocus = function () { 
		}

		}

}
function openPopUpForm(popupWindow,formId){
   if(popupWindow != null && !popupWindow.closed){
	popupWindow.close();
	popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
	window.onfocus = function () { 
	}
    document.getElementById(formId).submit();	
}
	else
		{
		popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
	    window.onfocus = function () { 
		}
    document.getElementById(formId).submit();		
		}
}

function openPopUpForm1(popupWindow,formId){
	   if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=300,left=600,width=970,height=280,fullscreen=no");
		window.onfocus = function () { 
		}
	    document.getElementById(formId).submit();	
	}
		else
			{
			popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=300,left=600,width=970,height=280,fullscreen=no");
		    window.onfocus = function () { 
			}
	    document.getElementById(formId).submit();		
			}
	}


function restoreOriginalColor(obj) {
  if (obj ) {
	if(count==1)
		{
		if (obj.className.includes('progress-square')) {
		    obj.style.background = '#e6f2ff';
		} else {
		    obj.style.background = '#e6f2ff';
		}
		count=0;
		 return true;
		}
	else{
		 return false;
	}
    
}
}

function openvehicleAuth(obj) {
var sus_no=$("#sus_no").val();	
    const type_map = {
       countaauth: 'A',
        countbauth: 'B',
        countcauth: 'C'
    };

   $("#type_veh1").val(type_map[obj.id]);
   $("#sus_no_veh1").val(sus_no);
   
   openPopUpForm(popupWindow,'tableForAuthtms')

}

function openmtrls_Auth(obj) {
 var sus_no=$("#sus_no").val();
     const type_map = {
       countwpnsauth: 'wpn',
        countequiauth: 'eqi'
    };
     $("#type_mtrls1").val(type_map[obj.id]);
	$("#sus_no_mtrls1").val(sus_no);
   
	openPopUpForm(popupWindow,'tableEqptsAuth');
}



function getPreviousMonthYear(currentMonth, currentYear) {   
    if (currentMonth === '01') {
        return {
            month: '12',
            year: (parseInt(currentYear) - 1).toString()
        };
    } else {    
        var prevMonth = (parseInt(currentMonth) - 1).toString().padStart(2, '0');
        return {
            month: prevMonth,
            year: currentYear
        };
    }
}


function checkDateMatch(dateStr, columnName) {  
    if (!dateStr || dateStr === 'null' || dateStr === '-' || dateStr.trim() === '') {
        return null;
    }
    
  
    var currentDate = new Date();
    var currentMonth = (currentDate.getMonth() + 1).toString().padStart(2, '0');
    var currentYear = currentDate.getFullYear().toString();    
 
    var prevDate = getPreviousMonthYear(currentMonth, currentYear);
    
    
    var dateParts = dateStr.split('-');
    if (dateParts.length !== 3) return null;
    
    var month = dateParts[1];
    var year = dateParts[2];
    
   
    if (columnName === '3008' || columnName === '3009') {
        var isCurrentMonth = (month === currentMonth && year === currentYear);
        var isPrevMonth = (month === prevDate.month && year === prevDate.year);        
        return isCurrentMonth || isPrevMonth;
    } else {      
        return month === currentMonth && year === currentYear;
    }
}


function getColumnName(cell) {
	debugger;
    var cellIndex = cell.cellIndex;
    var headerCell = cell.closest('table').querySelector('thead tr:last-child th:nth-child(' + (cellIndex - 1) + ')');
    return headerCell ? headerCell.textContent.trim() : '';
}


function updateTableColors() { 
	
    const cells = document.querySelectorAll('.table tbody td:not(:first-child):not(:nth-child(2))');
    console.log('cells name----------'+cells);
    cells.forEach(cell => {
        var dateText = cell.textContent.trim();
        var columnName = getColumnName(cell);
        console.log('clmn name----------'+columnName);
        var isValidDate = checkDateMatch(dateText, columnName);
        
        if (isValidDate !== null) {
            // Set text color based on validity
            cell.style.color = isValidDate ? 'green' : 'red'; // Change text color to green if valid, red if invalid
        }else {           
            cell.style.color = ''; // Reset text color if not valid
        }
        if (columnName=='IT Asset') {
        	cell.style.color = isValidDate ? 'Blue' : 'Blue'; 
        	
        }
    });
}

document.addEventListener('DOMContentLoaded', updateTableColors);


function refreshColors() {
    updateTableColors();
}
</script>