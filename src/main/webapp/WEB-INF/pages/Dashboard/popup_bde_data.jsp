<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	<link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
	 <link rel="stylesheet" href="js/miso/assets/css/font-awesome.min.css">
	

<style>

 a.fa-bar-chart{
color: #878787 !important;
}

.p-0 {
	padding: 0;
}

.progress-box li {
	opacity: 1;
	animation: itemEnter 500ms 90ms ease-out forwards;
	border-radius: 0 7px 0 7px;
	width: calc(( 100%/ 3)- 0.5em);
	min-height: 50px;
	animation: gradient 15s ease infinite;
	margin: 0 4px;
	align-items: center;
	vertical-align: middle;
	font-size: 20px;
	color: #fff !important;
	cursor: pointer;
}

.progress-box {
	list-style: none;
	margin: 0;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	align-content: space-around;
	width: 100%;
}

.max-height {
	height: 95%; /* Change height to 100% */
	position: relative; /* Add this line */
	top: 0; /* Add this line */
	margin-bottom: 4px; /* Add this line */
}

.bg-b-green {
	background: linear-gradient(45deg, #2ed8b6, #59e0c5);
}

.info-box {
	min-height: 100px;
	background: #899db1;
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
	/*       padding: 10px 10px 10px 0; */
	/*       margin-left: 90px; */
	
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
	background: rgba(0, 0, 0, 0.2);
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
	/* 		padding-left: 3em; */
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
	/* 			  margin-left: 3em;  */
	font-size: .765em;
	cursor: pointer;
	color: #666666;
	width: 122px;
}

.notification2 p {
	position: relative;
	margin: 0;
	/* margin-left:5em;			 */
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
	background: #8c8c8c;
	color: white;
	font-weight: bold;
}

.unit_dashboard td, .unit_dashboard th {
	/* 	padding: 5px;  */
	border: 1px solid #ccc;
	text-align: left;
	font-size: 16px;
}

.unit_dashboard2 th {
	background: #8c8c8c;
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
	/* 	padding: 11px;  */
	border: 1px solid #ccc;
	text-align: left;
	font-size: 16px;
}

.unit_dashboard .title_pers {
	background-color: #333d66 !important;
}

.unit_dashboard2 .title_mtrls {
	background-color: #523565 !important;
}

.unit_dashboard2 .title_vehs {
	background-color: #2c4e75 !important;
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
	text-decoration: underline;
	font-size: 22px;
}

.unit_dashboard2 .table_heading h4 {
	color: white;
	padding: 4px 0px;
	text-decoration: underline;
	font-size: 22px;
}

.title_main h2 {
	padding: 5px 0px;
	background: #f2b234;
	border-radius: 5px;
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
	background-color: #3b5f58;
	color: white;
	padding: 10px 0px;
	font-weight: bold;
	border-radius: 5px;
}

.notifi_psg {
	background-color: #333c64;
	color: white;
	padding: 5px 0px;
	text-decoration: underline;
	border-radius: 5px;
	z-index: 1;
	position: relative;
	font-size: 22px;
}

.notifi_tms {
	background-color: #2c4e75;
	color: white;
	padding: 5px 0px;
	text-decoration: underline;
	border-radius: 5px;
	z-index: 1;
	position: relative;
	font-size: 22px;
}

.notifi_cue {
	background-color: #533463;
	color: white;
	padding: 5px 0px;
	text-decoration: underline;
	border-radius: 5px;
	z-index: 1;
	position: relative;
	font-size: 22px;
}

.btn-modify {
	background-color: #5c4032;
	color: white;
	font-weight: bolder;
	padding: 0px 10px;
}

.btn-primary {
	font-weight: bolder;
}

.btn-primary:hover, .btn-modify:hover {
	background-color: #a129ad !important;
	border-color: #cc5198;
	transform: scale(1.2);
}

.btn-primary.clicked {
	background-color: white;
}

.marquee_psg {
	--animation-duration: 10s;
	animation: scrollUp var(--animation-duration) linear infinite;
}

.marquee_tms {
	--animation-duration: 10s;
	animation: scrollUp var(--animation-duration) linear infinite;
	/* Adjust the duration as needed */
}

.marquee_cue {
	--animation-duration: 10s;
	animation: scrollUp var(--animation-duration) linear infinite;
}

@
keyframes scrollUp {
	/* from { */
	/*     transform: translateY(100%); */
	/*   } */
	/*   to{ */
	/*     transform: translateY(-100%); */
	/*   } */ 0% {
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
.marquee_tms:hover {
	animation-play-state: paused !important;;
}

.marquee_psg:hover {
	animation-play-state: paused !important;;
}

.marquee_cue:hover {
	animation-play-state: paused !important;;
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
	/*     margin-bottom: 10px; */
	padding: 10px;
	border-radius: 10px;
	background-color: #e4e1e1;

	/*     justify-content: center; */
}

.content h5 {
	font-weight: bolder;
	color: #182e4a;
}

.tbl_scroll {
	overflow: auto;
	/* 	max-height: 167px; */
	border-radius: 5px;
}

.mb-0 {
	margin-bottom: 0;
}

.info-box-table table tbody td {
	font-size: 16px;
}

.p-5px {
	padding: 5px;
}

.chart-icon {
	cursor: pointer;
}

.fa.fa-bar-chart {
	font-size: 20px;
}

.fa.fa-bar-chart:hover {
	cursor: pointer;
}

/*  .chart-label { */
/*     font-size: 0.2vw; /* Using viewport width as font size */
*
/
/*   } */
</style>




<div class="content" align="center">
	<div class="animated fadeIn">
		<div class="">

			<div class="title_main">
				<div class="row">
					<div class="col-12">

						<h2 class="notifi_head">${unit_name} DASHBOARD</h2>

					</div>
				</div>
			</div>
			
<div class="card-body card-block">
			<div class="row">
				<div class="col-12 col-md-4 padding-x-0">

					<div class="unit_dashboard"	>

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
										<th>Chart</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Offrs</td>
										<td>
											<button class="btn btn-modify btn-round" id="countoffauth"
												onclick="openFormationwiseDetails(this)">0</button>
										</td>
										<td>
											<button class="btn btn-primary btn-round" id="countoffheld"
												value="0" onclick="openFormationwiseDetails(this)">0</button>
										</td>
										<td><a class="fa fa-bar-chart"
											onclick="get_offrs_dashboard()"></a></td>
									</tr>

									<tr>
										<td>JCOs</td>
										<td><button class="btn btn-modify btn-round"
												id="countjcoauth" onclick="openFormationwiseDetails(this)">0</button></td>
										<td>
											<button class="btn btn-primary btn-round" id="countjcoheld"
												onclick="openFormationwiseDetails(this)">0</button>
										</td>
										<td rowspan="2" style="text-align: center;"><a
											class="fa fa-bar-chart" onclick="get_jco_or_dashboard()"></a></td>
									</tr>
									<tr>
										<td>OR</td>
										<td>
											<button class="btn btn-modify btn-round" id="countorauth"
												onclick="openFormationwiseDetails(this)">0</button>
										</td>
										<td>
											<button class="btn btn-primary btn-round" id="countorheld"
												onclick="openFormationwiseDetails(this)">0</button>
										</td>
									</tr>
									<tr>
										<td>Civ</td>
										<td>
											<button class="btn btn-modify btn-round" id="countcivauth"
												onclick="openFormationwiseDetails(this)">0</button>
										</td>
										<td><button class="btn btn-primary btn-round"
												id="countcivheld" onclick="openFormationwiseDetails(this)">
												0</button></td>
										<td><a class="fa fa-bar-chart"
											onclick="get_civ_dashboard()"></a></td>

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
										<td><button class="btn btn-primary btn-round"
												id="countaheld" onclick="openvehicle(this)">0</button></td>
									</tr>
									<tr>
										<td>B</td>
										<td><button class="btn btn-modify btn-round"
												id="countbauth" onclick="openvehicleAuth(this)">0</button></td>
										<td><button class="btn btn-primary btn-round"
												id="countbheld" onclick="openvehicle(this)">0</button></td>
									</tr>
									<tr>
										<td>C</td>
										<td>
											<button class="btn btn-modify btn-round" id="countcauth"
												onclick="openvehicleAuth(this)">0</button>
										</td>
										<td>
											<button class="btn btn-primary btn-round" id="countcheld"
												onclick="openvehicle(this)">0</button>
										</td>
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
										<td><button class="btn btn-primary btn-round"
												id="countwpnsheld" onclick="openmtrls(this)">0</button></td>
									</tr>
									<tr>
										<td>Eqpts</td>
										<td><button class="btn btn-modify btn-round"
												id="countequiauth" onclick="openmtrls_Auth(this)">0</button></td>
										<td><button class="btn btn-primary btn-round"
												id="countequiheld" onclick="openmtrls(this)">0</button></td>
									</tr>
									<tr>
										<td>IT Assets</td>
										<td><button class="btn btn-modify btn-round">0</button></td>
										<td><button class="btn btn-primary btn-round"
												id="countitassetheld" onclick="openitassets(this)">
												0</button></td>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>
				</div>
			</div>
		</div>
	</div>
</div>

<input type="hidden" name="sus_no" id="sus_no" />
<c:url value="data_table_tms" var="popup_veh" />
<form:form action="${popup_veh}" method="post" id="tableFormoff"
	name="tableFormoff" modelAttribute="cont_comd4" target="result">
	<input type="hidden" name="sus_no_veh1" id="sus_no_veh1" />
	<input type="hidden" name="type_veh" id="type_veh" />
</form:form>

<c:url value="data_table_mtrls" var="popup_mtrls" />
<form:form action="${popup_mtrls}" method="post" id="tableFormmtrls"
	name="tableFormmtrls" modelAttribute="cont_comd4" target="result">
	<input type="hidden" name="sus_no_mtrls1" id="sus_no_mtrls1" />
	<input type="hidden" name="type_mtrls" id="type_mtrls" />
</form:form>

<c:url value="data_table_Auth_tms" var="popup_veh" />
<form:form action="${popup_veh}" method="post" id="tableForAuthtms"
	name="tableForAuthtms" modelAttribute="cont_comd4" target="result">
	<input type="hidden" name="sus_no_veh12" id="sus_no_veh12" />
	<input type="hidden" name="type_veh1" id="type_veh1" />
</form:form>



<c:url value="psg_dashboard_datalist" var="backUrl" />
<form:form action="${backUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="cont_comd1">
	<input type="hidden" name="cont_comd1" id="cont_comd1" />
	<input type="hidden" name="cont_corps1" id="cont_corps1" />
	<input type="hidden" name="cont_div1" id="cont_div1" />
	<input type="hidden" name="cont_bde1" id="cont_bde1" />
	<input type="hidden" name="rank1" id="rank1" />
	<input type="hidden" name="parent_arm1" id="parent_arm1" />
	<input type="hidden" name="arm1" id="arm1" />
	<input type="hidden" name="parm1" id="parm1" />
	<input type="hidden" name="cmd1" id="cmd1" />
	<input type="hidden" name="div1" id="div1" />
	<input type="hidden" name="corps1" id="corps1" />
	<input type="hidden" name="bdes1" id="bdes1" />
	<input type="hidden" name="regs1" id="regs1" />
	<input type="hidden" name="unit1" id="unit1" />
	<input type="hidden" name="unit_name1" id="unit_name1" />
	<input type="hidden" name="unit_view" id="unit_view" />

</form:form>

<c:url value="civ_dashboard_datalist" var="backUrlciv" />
<form:form action="${backUrlciv}" method="post" id="searchcivForm"
	name="searchcivForm" modelAttribute="cont_comd1">
	<input type="hidden" name="cont_comd1" id="cont_comd1" />
	<input type="hidden" name="cont_corps1" id="cont_corps1" />
	<input type="hidden" name="cont_div1" id="cont_div1" />
	<input type="hidden" name="cont_bde1" id="cont_bde1" />
	<input type="hidden" name="rank1" id="rank1" />
	<input type="hidden" name="parent_arm1" id="parent_arm1" />
	<input type="hidden" name="arm1" id="arm1" />
	<input type="hidden" name="parm1" id="parm1" />
	<input type="hidden" name="cmd1" id="cmd1" />
	<input type="hidden" name="div1" id="div1" />
	<input type="hidden" name="corps1" id="corps1" />
	<input type="hidden" name="bdes1" id="bdes1" />
	<input type="hidden" name="regs1" id="regs1" />
	<input type="hidden" name="unit1" id="unit3" />
	<input type="hidden" name="unit_name1" id="unit_name3" />
	<input type="hidden" name="unit_view" id="unit_view3" />


</form:form>

<c:url value="jco_or_dashboard_datalist" var="backUrljco" />
<form:form action="${backUrljco}" method="post" id="searchjcoForm"
	name="searchjcoForm" modelAttribute="cont_comd1">
	<input type="hidden" name="cont_comd1" id="cont_comd1" />
	<input type="hidden" name="cont_corps1" id="cont_corps1" />
	<input type="hidden" name="cont_div1" id="cont_div1" />
	<input type="hidden" name="cont_bde1" id="cont_bde1" />
	<input type="hidden" name="rank1" id="rank1" />
	<input type="hidden" name="parent_arm1" id="parent_arm1" />
	<input type="hidden" name="arm1" id="arm1" />
	<input type="hidden" name="parm1" id="parm1" />
	<input type="hidden" name="cmd1" id="cmd1" />
	<input type="hidden" name="div1" id="div1" />
	<input type="hidden" name="corps1" id="corps1" />
	<input type="hidden" name="bdes1" id="bdes1" />
	<input type="hidden" name="regs1" id="regs1" />
	<input type="hidden" name="unit1" id="unit2" />
	<input type="hidden" name="unit_name1" id="unit_name2" />
	<input type="hidden" name="unit_view" id="unit_view2" />

</form:form>


<c:url value="data_table_mtrls_auth" var="popup_mtrls" />
<form:form action="${popup_mtrls}" method="post" id="tableEqptsAuth"
	name="tableEqptsAuth" modelAttribute="cont_comd4" target="result">
	<input type="hidden" name="sus_no_mtrls12" id="sus_no_mtrls12" />
	<input type="hidden" name="type_mtrls1" id="type_mtrls1" />
</form:form>

<c:url value="countitassetheld" var="popup_mtrls" />
<form:form action="${popup_mtrls}" method="get" id="countitassetheldData"
	name="countitassetheldData" modelAttribute="cont_comd4" target="result">
	<input type="hidden" name="sus_no_itassetheld" id="sus_no_itassetheld" />
	
</form:form>


<script>
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}"; 
	$(document).ready(function() {
		debugger;
		var sus_no = '${sus_no}';
		var formCodeControl = '${formCodeControl}';
		var formation_type = '${form_code}';

		$("#sus_no").val(sus_no);
		getcountoffheld(sus_no, formCodeControl, formation_type);
		getcountoffauth(sus_no, formCodeControl, formation_type);
		getcountvehicleauth(sus_no, formCodeControl, formation_type);
		//  	getcountmtrlsauth(sus_no);
		getcount_wpn_held_auth(sus_no, formCodeControl, formation_type);
		getcount_equi_held_auth(sus_no, formCodeControl, formation_type);
		getcountitassetheld(sus_no, formCodeControl, formation_type);
		getNotifications2();
	});

	function getNotifications2() {

		jQuery
				.post(
						'getnotificationList_new?' + key + "=" + value,
						function(j) {

							jQuery("#appnoti2").empty();
							jQuery("#appnoti3").empty();
							jQuery("#appnoti4").empty();
							var noti_null = '<div class="notification2"   ><i><b></b></i><p><b></b></p><span></span></div>';
							noti_counts = 0;
							var v = j.length;

							if (v != 0) {

								var count_psg = 0;
								var count_tms = 0;
								var count_cue = 0;
								var count_null_psg = 0;
								var count_null_tms = 0;
								var count_null_cue = 0;

								for (i = 0; i < v; i++) {

									//						jQuery("#appnoti").append("<div>"+j[i][0] +"-----------------"+ j[i][1]+"</div>"+"</br>");
									var seen = "";
									var newtxt = "";

									if (j[i][3] != ""
											&& j[i][3] != null
											&& j[i][3].split(",").includes(
													'${userId}')) {
										seen = "";
									} else {
										seen = "fill-circle2";
										newtxt = '<i style=" color: #dc3545;"><b>NEW</b></i>';
										noti_counts += 1;
									}

									var onclick = 'getNotiDetails2(' + j[i][5]
											+ ',\'' + seen + '\')';
									var toggle = 'data-toggle="modal" data-target="#myModal" ';

									if (j[i][6] != "" && j[i][6] != 0) {

										if (j[i][1] == "Please Check the Ticket in Manage Ticket Screen") {
											onclick = 'print_ticket(' + j[i][6]
													+ ', ' + j[i][5] + ', \''
													+ seen + '\')';
										}

										else if (j[i][0] == "Tikect Status") {
											onclick = 'print_ticket(' + j[i][6]
													+ ', ' + j[i][5] + ', \''
													+ seen + '\')';
										}

										/* else if(j[i][0]=="DRR Approved" || j[i][0]=="DRR Received")
										{
										onclick = 'Search()';
										} */
										else if (j[i][0] == "Unit Raised") {
											onclick = 'view(' + j[i][6] + ', '
													+ j[i][5] + ', \'' + seen
													+ '\')';
										} else {
											onclick = 'print(' + j[i][6] + ', '
													+ j[i][5] + ', \'' + seen
													+ '\')';
										}
										//  onclick = 'print(' + j[i][6] + ', "'+ j[i][5] +'", \'' + seen + '\')';

										toggle = '';
									}
									if (j[i][6] == 0
											&& j[i][0] == "B Veh DRR Approved"
											|| j[i][0] == "B Veh DRR Received") {
										onclick = 'Search_notification()';
									}

									if (j[i][0] == "POST IN"
											|| j[i][0] == "POST OUT") {

										var highlightClass = newtxt !== "" ? 'highlighted'
												: '';
										var notificationHTML = '<div class="notification2 ' + highlightClass + '" ' + toggle + ' onclick="' + onclick + '">'
												+ newtxt
												+ '<p><b>'
												+ j[i][0]
												+ '</b> '
												+ j[i][1]
												+ '</p>'
												+ '<span class="time">'
												+ j[i][4].substring(0, 16)
												+ '</span>' + '</div>';

										jQuery("#appnoti2").append(
												notificationHTML);
										count_null_tms++;
										count_null_cue++;

										count_psg++;

									}

									if (j[i][0] == "Tikect Status"
											|| j[i][0] == "DRR Initiated"
											/* || j[i][0] == "Release Issue Order Generated" */
											|| j[i][0] == "B Veh DRR Approved"
											|| j[i][0] == "B Veh DRR Received"
											|| j[i][0] == "A Veh DRR Received"
											|| j[i][0] == "A Veh DRR Approved"
											|| j[i][0] == "C Veh DRR Approved"
											|| j[i][0] == "C Veh DRR Received") {

										var highlightClass = newtxt !== "" ? 'highlighted'
												: '';
										var notificationHTML = '<div class="notification2 ' + highlightClass + '" ' + toggle + ' onclick="' + onclick + '">'
												+ newtxt
												+ '<p><b>'
												+ j[i][0]
												+ '</b> '
												+ j[i][1]
												+ '</p>'
												+ '<span class="time">'
												+ j[i][4].substring(0, 16)
												+ '</span>' + '</div>';
										jQuery("#appnoti3").append(
												notificationHTML);
										// 	    jQuery("#appnoti2").append(noti_null);
										// 	    jQuery("#appnoti4").append(noti_null);
										// 	jQuery("#appnoti3").append(
										// 		    '<div class="notification2" '+toggle+' onclick="' + onclick + '">' + newtxt 		+		   
										// 		  '<p><b>  ' + j[i][0] + '</b> ' +   j[i][1] + '</p>'+  '<span class="time"> ' + j[i][4].substring(0, 16) + '</span>' +				
										// 		  '</div>'
										// 		);
										count_null_psg++;
										count_null_cue++;
										count_tms++;
									}
									if (j[i][0] == "ETRC Uploaded New WET/PET"
											|| j[i][0] == "SD-6 Uploaded New WE/PE"
											|| j[i][0] == "Link Sus No") {
										var highlightClass = newtxt !== "" ? 'highlighted'
												: '';
										var notificationHTML = '<div class="notification2 ' + highlightClass + '" ' + toggle + ' onclick="' + onclick + '">'
												+ newtxt
												+ '<p><b>'
												+ j[i][0]
												+ '</b> '
												+ j[i][1]
												+ '</p>'
												+ '<span class="time">'
												+ j[i][4].substring(0, 16)
												+ '</span>' + '</div>';
										jQuery("#appnoti4").append(
												notificationHTML);
										// 	    jQuery("#appnoti2").append(noti_null);
										// 	    jQuery("#appnoti3").append(noti_null);
										// 	jQuery("#appnoti4").append(
										// 		    '<div class="notification2" '+toggle+' onclick="' + onclick + '">' + newtxt 		+		   
										// 		  '<p><b>  ' + j[i][0] + '</b> ' +   j[i][1] + '</p>'+  '<span class="time"> ' + j[i][4].substring(0, 16) + '</span>' +				
										// 		  '</div>'
										// 		);
										count_null_psg++;
										count_null_tms++
										count_cue++;
									}
								}

								if (count_psg == 0) {
									jQuery("#appnoti2")
											.append(
													'<div >'
															+ '<p class="emoji"> 😊  HAVE A NICE DAY  😊 </p>'
															+ '</div>');
								}

								if (count_tms == 0) {
									jQuery("#appnoti3")
											.append(
													'<div  >'
															+ '<p class="emoji"> 😊  HAVE A NICE DAY  😊 </p>'
															+ '</div>');
								}

								if (count_cue == 0) {
									jQuery("#appnoti4")
											.append(
													'<div >'
															+ '<p class="emoji"> 😊  HAVE A NICE DAY  😊 </p>'
															+ '</div>');
								}
								adddumpy(count_null_psg, count_null_tms,
										count_null_cue);
								adjustMarqueeSpeed();
							}
							jQuery("#notification_counts").text(noti_counts);
						});

	}
	function adddumpy(count_null_psg, count_null_tms, count_null_cue) {
		var noti_null = '<div class="notification2"   ><i><b></b></i><p><b>&nbsp</b></p><span></span></div>';
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

		jQuery("#appnoti2").append(noti_psg);
		jQuery("#appnoti3").append(noti_tms);
		jQuery("#appnoti4").append(noti_cue);

	}
	function adjustMarqueeSpeed() {

		var marquees = document
				.querySelectorAll('.marquee_psg, .marquee_tms, .marquee_cue');
		var longestMarquee = 0;
		for (var i = 0; i < marquees.length; i++) {
			var marqueeLength = marquees[i].scrollWidth;
			if (marqueeLength > longestMarquee) {
				longestMarquee = marqueeLength;
			}
		}
		var speed = 8;
		var duration = longestMarquee / speed * 1000;
		for (var i = 0; i < marquees.length; i++) {
			marquees[i].style.setProperty('--animation-duration', duration
					+ 'ms');
		}

	}

	function getNotiDetails2(id, seen) {

		//dropnoti();
		jQuery
				.post(
						'getnotificationDetails?' + key + "=" + value,
						{
							id : id,
							seen : seen
						},
						function(j) {
							var v = j.length;
							if (v != 0) {
								var parsedate = "";
								var dataT;
								if (j[0].created_date != null
										&& j[0].created_date != "") {
									dataT = new Date(j[0].created_date);
									//						  parsedate= dataT.getHours()+":"+dataT.getMinutes()+" "+dataT.getDay()+"/"+(dataT.getMonth()+1)+"/"+dataT.getFullYear()+""
									parsedate = dataT.getFullYear() + "-"
											+ (dataT.getMonth() + 1) + "-"
											+ dataT.getDay() + " "
											+ (dataT.getHours() + 1) + ":"
											+ dataT.getMinutes() + ""
								}
								jQuery("#noti_detailsdiv")
										.html(
												' <span class="time" style="text-align: right;color: #666666;"><b>'
														+ parsedate
														+ '</b></span>'
														+ '<p><b>'
														+ j[0].title
														+ '</b></p>'
														+ '<p style="max-height: 250px; overflow: auto;">'
														+ j[0].description
														+ '</p>');
							} else {
								jQuery("#noti_detailsdiv").html(
										' <p><b>Unable to fetch data</b></p>');
							}

							getNotifications2();

						});

	}

	function getcountoffheld(sus_no, formCodeControl, formation_type) {
		$.post('Getcount_offHeld?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {

				$("#countoffheld").text(i[0].offrsposted);
				//$('#countoffheld').val(i[0].offrsPosted);
			}
		});

		$.post('Getcount_JCOHeld?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {

				$("#countjcoheld").text(i[0].jcosposted);
				//$('#countoffheld').val(i[0].offrsPosted);
			}
		});
		$.post('Getcount_ORHeld?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {

				$("#countorheld").text(i[0].orposted);
				//$('#countoffheld').val(i[0].offrsPosted);
			}
		});
		$.post('Getcount_CIVHeld?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {

				$("#countcivheld").text(i[0].civheldcount);
				//$('#countoffheld').val(i[0].offrsPosted);
			}
		});

	}

	function getcountoffauth(sus_no, formCodeControl, formation_type) {
		debugger
		$.post('Getcount_offauth?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {
				$("#countoffauth").text(i[0].offrauth);

			}
		});

		$.post('Getcount_JCOAuth?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {
				$("#countjcoauth").text(i[0].jcoauth);
			}
		});
		$.post('Getcount_ORAuth?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {

				$("#countorauth").text(i[0].ors);

			}
		});
		$.post('Getcount_CIVAuth?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {
				$("#countcivauth").text(i[0].civiliancount);
			}
		});

	}

	function getcountvehicleauth(sus_no, formCodeControl, formation_type) {
		$.post('Getcount_aauth_held?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {

				$("#countaauth").text(i[0].ue);
				$("#countaheld").text(i[0].total_uh);

			}
		});

		$.post('Getcount_bAuth_held?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {

				$("#countbauth").text(i[0].ue);
				$("#countbheld").text(i[0].total_uh);

			}
		});
		$.post('Getcount_cAuth_held?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {

				$("#countcauth").text(i[0].ue);
				$("#countcheld").text(i[0].total_uh);
			}
		});

	}

	function getcount_wpn_held_auth(sus_no, formCodeControl, formation_type) {
		$.post('Getcount_wpns_held?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {

				$("#countwpnsheld").text(i[0].uh);
				$("#countwpnsauth").text(i[0].ue);
			}
		});
	}
	function getcount_equi_held_auth(sus_no, formCodeControl, formation_type) {
		$.post('Getcount_equi_held?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {
			if (i.length > 0) {

				$("#countequiheld").text(i[0].uh);
				$("#countequiauth").text(i[0].ue);
			}
		});

	}

	function getcountitassetheld(sus_no, formCodeControl, formation_type) {

		$.post('Getcount_itAssetHeld?' + key + "=" + value, {
			sus_no : sus_no,
			formCodeControl : formCodeControl,
			formation_type : formation_type
		}, function(i) {

			$("#countitassetheld").text(i);

		});

	}

	var popupWindow = null;
	var count = 0;
	function openFormationwiseDetails(obj,formId) {
debugger;
		var originalColor = obj.style.background;
		var sus_no = $("#sus_no").val();
		$("#sus_no_mtrls1").val(sus_no);

		if (obj.className === 'btn-modify') {
			obj.style.background = '#e85a10';
		} else {
			obj.style.background = '#a129ad';
		}

// 		var url = obj.id + '?' /* + key + "=" + value, {sus_no:sus_no}; */
		//var url = obj.id + '?sus_no=' + sus_no;
		//  var url = obj.id + '?sus_no=' + sus_no + '&' + key + '=' + value,
		  var url = obj.id + '?sus_no=' + encodeURIComponent(sus_no) + '&' + key + '=' + encodeURIComponent(value);
		if (popupWindow != null && !popupWindow.closed) {

			popupWindow.close();
			popupWindow = window
					.open(
							url,
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
			popupWindow.onunload = function() {
				if (!restoreOriginalColor(obj)) {
					count++;
				}
			};
			window.onfocus = function() {
			}
			
			 document.getElementById(formId).submit();	
		} else {
			popupWindow = window
					.open(
							url,
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
			popupWindow.onunload = function() {
				if (!restoreOriginalColor(obj)) {
					count++;
				}

			};
			window.onfocus = function() {
			}
			 document.getElementById(formId).submit();	
		}

	}

	function openvehicle(obj) {
		// 	
		debugger;
		var sus_no = $("#sus_no").val();
		if (obj.id == "countaheld") {
			$("#type_veh").val("A");
		}
		if (obj.id == "countbheld") {
			$("#type_veh").val("B");
		}
		if (obj.id == "countcheld") {
			$("#type_veh").val("C");
		}

		$("#sus_no_veh1").val(sus_no);
		if (popupWindow != null && !popupWindow.closed) {

			popupWindow.close();
			popupWindow = window
					.open(
							"",
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");

			window.onfocus = function() {
			}
			document.getElementById('tableFormoff').submit();
		} else {
			popupWindow = window
					.open(
							"",
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");

			window.onfocus = function() {
			}
			document.getElementById('tableFormoff').submit();
		}

	}

	function openmtrls(obj) {

		var sus_no = $("#sus_no").val();

		if (obj.id == "countwpnsheld") {
			$("#type_mtrls").val("wpn");
		} else {
			$("#type_mtrls").val("eqi");
		}
		$("#sus_no_mtrls1").val(sus_no);
		if (popupWindow != null && !popupWindow.closed) {
			popupWindow.close();
			popupWindow = window
					.open(
							"",
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
			window.onfocus = function() {
			}
			document.getElementById('tableFormmtrls').submit();

		} else {
			popupWindow = window
					.open(
							"",
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");

			window.onfocus = function() {
			}
			document.getElementById('tableFormmtrls').submit();

		}

	}

	function openitassets(obj) {
		debugger;
		var originalColor = obj.style.background;
		if (obj.className.includes('btn-modify')) {
			obj.style.background = '#e85a10';
		} else {
			obj.style.background = '#007bff';
		}
		//     obj.style.background = '#a129ad';

		var sus_no = $("#sus_no").val();
		
		$("#sus_no_itassetheld").val(sus_no);

// 		var url = obj.id + '?';

		if (popupWindow != null && !popupWindow.closed) {

			popupWindow.close();
			popupWindow = window
					.open(
							"",
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
			popupWindow.onunload = function() {
				if (!restoreOriginalColor(obj)) {
					count++;
				}

			};
			window.onfocus = function() {
			}
			document.getElementById('countitassetheldData').submit();

		} else {
			popupWindow = window
					.open(
							"",
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
			popupWindow.onunload = function() {
				if (!restoreOriginalColor(obj)) {
					count++;
				}

			};
			window.onfocus = function() {
			}
			document.getElementById('countitassetheldData').submit();

		}

	}

	function restoreOriginalColor(obj) {

		if (obj) {

			if (count == 1) {
				if (obj.className.includes('btn-modify')) {
					obj.style.background = '#e85a10';
				} else {
					obj.style.background = '#007bff';
				}
				count = 0;
				return true;
			} else {
				return false;
			}

		}

	}

	function openvehicleAuth(obj) {
debugger;
		var sus_no = $("#sus_no").val();

		if (obj.id == "countaauth") {
			$("#type_veh1").val("A");
		}
		if (obj.id == "countbauth") {
			$("#type_veh1").val("B");
		}
		if (obj.id == "countcauth") {
			$("#type_veh1").val("C");
		}

		$("#sus_no_veh12").val(sus_no);
		if (popupWindow != null && !popupWindow.closed) {

			popupWindow.close();
			popupWindow = window
					.open(
							"",
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");

			window.onfocus = function() {
			}
			document.getElementById('tableForAuthtms').submit();
		} else {
			popupWindow = window
					.open(
							"",
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");

			window.onfocus = function() {
			}
			document.getElementById('tableForAuthtms').submit();
		}

	}

	function openmtrls_Auth(obj) {

		var sus_no = $("#sus_no").val();

		if (obj.id == "countwpnsauth") {
			$("#type_mtrls1").val("wpn");
		} else {
			$("#type_mtrls1").val("eqi");
		}
		$("#sus_no_mtrls12").val(sus_no);
		if (popupWindow != null && !popupWindow.closed) {
			popupWindow.close();
			popupWindow = window
					.open(
							"",
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
			window.onfocus = function() {
			}
			document.getElementById('tableEqptsAuth').submit();

		} else {
			popupWindow = window
					.open(
							"",
							"result",
							"toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");

			window.onfocus = function() {
			}
			document.getElementById('tableEqptsAuth').submit();

		}

	}
	function get_offrs_dashboard() {
		$("#WaitLoader").show();
		$("#cont_comd1").val("");
		$("#cont_corps1").val("");
		$("#cont_div1").val("");
		$("#cont_bde1").val("");
		$("#rank1").val("");
		$("#arm1").val("");
		$("#parm1").val("");
		$("#cmd1").val("");
		$("#div1").val("");
		$("#corps1").val("");
		$("#bdes1").val("");
		$("#regs1").val("");
		$("#unit1").val('${unit_name}');
		$("#parent_arm1").val("");
		$("#unit_name1").val('${unit_name}');
		$("#unit_view").val('YES');
		$("#searchForm").submit();

	}

	function get_jco_or_dashboard() {
		$("#WaitLoader").show();
		$("#cont_comd1").val("");
		$("#cont_corps1").val("");
		$("#cont_div1").val("");
		$("#cont_bde1").val("");
		$("#rank1").val("");
		$("#arm1").val("");
		$("#parm1").val("");
		$("#cmd1").val("");
		$("#div1").val("");
		$("#corps1").val("");
		$("#bdes1").val("");
		$("#regs1").val("");
		$("#unit2").val('${unit_name}');
		$("#parent_arm1").val("");
		$("#unit_name2").val('${unit_name}');
		$("#unit_view2").val('YES');
		$("#searchjcoForm").submit();

	}

	function get_civ_dashboard() {
		$("#WaitLoader").show();
		$("#cont_comd1").val("");
		$("#cont_corps1").val("");
		$("#cont_div1").val("");
		$("#cont_bde1").val("");
		$("#rank1").val("");
		$("#arm1").val("");
		$("#parm1").val("");
		$("#cmd1").val("");
		$("#div1").val("");
		$("#corps1").val("");
		$("#bdes1").val("");
		$("#regs1").val("");
		$("#unit3").val('${unit_name}');
		$("#parent_arm1").val("");
		$("#unit_name3").val('${unit_name}');
		$("#unit_view3").val('YES');
		$("#searchcivForm").submit();

	}
</script>

