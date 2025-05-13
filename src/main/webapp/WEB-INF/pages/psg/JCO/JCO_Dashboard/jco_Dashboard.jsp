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

<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/map.js"></script>
<script src="https://cdn.amcharts.com/lib/5/geodata/worldLow.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/geodata/data/countries2.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<!-- bisag v2 190922(new screen) -->

<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>

<script src="js/amchart4/circle-progress.min.js"></script>
<link rel="stylesheet"
	href="js/miso/formationDashboard/fornationDashboardCSS.css">
<script src="js/miso/formationDashboard/formationDashboard.js"
	type="text/javascript"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<script src="js/amchart4/index.js"></script>
<script src="js/amchart4/xy.js"></script>
<script src="js/amchart4/Animated5.js"></script>
<script src="js/amchart4/Chart.js"></script>




<style type="text/css">
.chart-container {
	position: relative;
}

.loader-container {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(255, 255, 255, 0.8);
	/* Semi-transparent white background */
	display: flex;
	justify-content: center;
	align-items: center;
	z-index: 1; /* Place the loader container above the chart */
}

.loader {
	border: 8px solid #f3f3f3;
	border-top: 8px solid #3498db;
	border-radius: 50%;
	width: 50px;
	height: 50px;
	animation: spin 1s linear infinite;
}

@keyframes spin { 
  0% { transform: rotate(0deg); }
  100% { transform: rotate(360deg); }
}

.chartsize {
	/* Your chart container styles here */
	width: 100%;
	height: 450px; /* Adjust the height as needed */
	/* Add other necessary styles */
}

body {
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji",
		"Segoe UI Symbol";
}

#chartdiv_jco_or {
	width: 100%;
	height: 300px;
}

.green-style {
	color: green !important;
}

.open-List {
	display: block !important;
}

#parentlDiv_jco_or {
	width: 100%;
	height: 300px;
}

#stateDiv_jco_or {
	width: 100%;
	height: 300px;
}

#comdDiv_jco_or {
	width: 100%;
	height: 300px;
}

#bgDiv_jco_or {
	width: 100%;
	height: 300px;
}

.counter-title {
	margin-bottom: 10px;
}

.counter-count {
	font-weight: 700;
}

.overSelect {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
}

.selectBox {
	position: relative;
}

.selectBox select {
	width: 100%;
	font-weight: bold;
}

.checkboxes {
	display: none;
	border: 1px #dadada solid;
	width: 250px;
	display: none;
	border: 0.5px #000000 solid;
	position: absolute;
	background-color: #FFFFFF;
	width: 100%;
	z-index: 1;
}

.info-box_jco {
	box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
	height: 321px;
	display: flex;
	cursor: default;
	background-color: #fff;
	position: relative;
	overflow: hidden;
	margin-bottom: 10px;
	border-style: ridge;
}

.checkboxes label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.checkboxes label:hover {
	background-color: #1e90ff;
}

.count {
	color: white;
}

.count:hover {
	color: black;
}

span.subspan {
	padding: 5px;
	background-color: #79cece54;
	border-radius: 20px;
	margin: 3px;
	display: block;
	width: fit-content;
}
#searchBox::placeholder,
#searchBox_corps::placeholder ,#searchBox_div::placeholder,#searchBox_bde::placeholder,#searchBox_parent::placeholder,#searchBox_reg::placeholder,#searchBox_rank::placeholder{
  color: black !important;
  opacity: 0.5;
   text-align: center;
}
.custom_search
   {
   position: sticky; 
   top: 0;
    z-index: 1; 
    background-color: white;
   
   }
</style>

<div class="psg_dash">
 	<div class="content mt-3" style="margin-top:-13px !important;"> 
		  		<div id="WaitLoader" style="display:block;" align="center">
					<span id="">Processing Data.Please Wait ...<i style="font-size: 18px;" class="fa fa-hourglass fa-spin"></i></span>
				</div>
				<div id="Loader" style="display: none;" align="center">
					<span id="" style="font-size: 28px;"><img alt="" src="../login_file/loader1.gif">  <br>Please wait while loading...</span>
				</div>
			
		  	</div>
<div class="animated fadeIn">
	<div class="container-fluid" align="center">

		<div class="card-header" id="divtop">
			<h3>
				<u>HELD STR : MISO 3.1</u></b>
			</h3>

				<button id="psgMainDashboard" onclick="getdashboard('psgMainDashboard');" class="btn btn-success btn-sm">OFFICER</button>
				<button id="jcoDashboard" onclick="getdashboard('jcoDashboard');" class="btn btn-primary  btn-sm">JCO/OR</button>
			<button id="civDashboard" onclick="getdashboard('civDashboard');" class="btn btn-danger btn-sm">CIVILIAN</button>
		

		</div>

		&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
		<div class="card-body card-block" id="divsearchhide">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6 col-12">
					<div class="col-md-4">
						<label class=" form-control-label">Comd:</label> <input
							type="hidden" id="CheckVal" name="CheckVal">

					</div>
					<div class="col-md-8">
						<div class="selectBox" onclick="showCheckboxes1()">
							<select name="cont_comd" id="cont_comd"
								class="form-control ${not empty selectedGetCommandList ? 'green-style' : ''}">
								<c:if test="${empty selectedGetCommandList}">
									<option>--Select--</option>
								</c:if>

								<c:if test="${not empty selectedGetCommandList}">
									<option>--Selected--</option>
								</c:if>
							</select>

							<div class="overSelect"></div>
						</div>

						<div id="checkboxes1"
							class="checkboxes ${not empty selectedGetCommandList}"
							style="max-height: 200px; overflow: auto;">
							<div id="chk_box">
							<input type="text" id="searchBox" name="searchBox"
						class="form-control autocomplete custom_search " autocomplete="off" placeholder="Search Comd"  oninput="filterOptions('searchBox','.quali_subjectlist_cmd')"
						maxlength="40">
								<c:forEach var="item" items="${getCommandList}" varStatus="num">
									<label for="one" class="quali_subjectlist_cmd"> <input
										type="checkbox" name="multisub_cmd" onclick="onChangeCmd();"
										class="cmdCheckBox" value="${item[0]}"
										<c:forEach var="selectedGetCommandList" items="${selectedGetCommandList}">
   											<c:if test="${item[0] eq selectedGetCommandList}">
        										checked
    										</c:if>
											</c:forEach> />
										${item[1]}
									</label>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6 col-12">
					<div class="col-md-4">
						<label class=" form-control-label">Corps:</label> <input
							type="hidden" id="CheckValcorps" name="CheckValcorps">
					</div>
					<div class="col-md-8">

						<div class="selectBox" onclick="showcheckbox_corps()">

							<select name="cont_corps" id="cont_corps"
								class="form-control ${not empty selectedCorpsList ? 'green-style' : ''}">
								<c:if test="${empty selectedCorpsList}">
									<option>--Select--</option>
								</c:if>

								<c:if test="${not empty selectedCorpsList}">
									<option>--Selected--</option>
								</c:if>
							</select>
							<div class="overSelect"></div>
						</div>
						<div id="checkboxes_cont_corps"
							class="checkboxes ${not empty selectedCorpsList }"
							style="max-height: 200px; overflow: scroll; overflow-x: hidden;">

							<div></div>



						</div>


					</div>

				</div>
				<div class="col-lg-3 col-md-6 col-sm-6 col-12">
					<div class="col-md-4">
						<label class=" form-control-label">Div:</label> <input
							type="hidden" id="CheckValdiv" name="CheckValdiv">
					</div>
					<div class="col-md-8">

						<div class="selectBox" onclick="showcheckbox_div()">
							<!-- 					onclick="showCheckboxes_cont_div()"> -->
							<!-- <select id="cont_div" class="form-control-sm form-control"
							style="width: 100%">
							<option>--Select--</option>
						</select> -->
							<select name="cont_div" id="cont_div"
								class="form-control ${not empty selectedDivsList ? 'green-style' : ''}">
								<c:if test="${empty selectedDivsList}">
									<option>--Select--</option>
								</c:if>

								<c:if test="${not empty selectedDivsList}">
									<option>--Selected--</option>
								</c:if>
							</select>
							<div class="overSelect"></div>
						</div>
						<div id="checkboxes_cont_div"
							class="checkboxes ${not empty selectedDivsList }"
							style="max-height: 200px; overflow: scroll; overflow-x: hidden;">

							<div></div>

						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 col-sm-6 col-12">
					<div class="col-md-4">
						<label class=" form-control-label">Bde:</label> <input
							type="hidden" id="CheckValbde" name="CheckValbde">
					</div>
					<div class="col-md-8">


						<div class="selectBox" onclick="showcheckbox_bde()">
							<!-- 					onclick="showCheckboxes_cont_bde()"> -->
							<!-- <select id="cont_bde" class="form-control-sm form-control"
							style="width: 100%">
							<option>--Select--</option>
						</select> -->
							<select name="cont_bde" id="cont_bde"
								class="form-control ${not empty selectedBdesList ? 'green-style' : ''}">
								<c:if test="${empty selectedBdesList}">
									<option>--Select--</option>
								</c:if>

								<c:if test="${not empty selectedBdesList}">
									<option>--Selected--</option>
								</c:if>
							</select>
							<div class="overSelect"></div>
						</div>
						<div id="checkboxes_cont_bde"
							class="checkboxes ${not empty selectedBdesList}"
							style="max-height: 200px; overflow: scroll; overflow-x: hidden;">

							<div></div>

						</div>
					</div>



				</div>


			</div>
			&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6 col-12">
					<div class="col-md-4">
						<label class=" form-control-label">Unit:</label>
					</div>
					<div class="col-md-8">

						<input type="text" id="unit_name" name="unit_name"
							class="form-control autocomplete" autocomplete="off"
							maxlength="50">
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6 col-12">
					<div class="col-md-4">
						<label class=" form-control-label">Parent Arm:</label>
					</div>
					<div class="col-md-8">


						<div class="selectBox" onclick="showCheckboxes_prnt_arm()">
							<!-- <select name="prnt_arm" id="prnt_arm" class=" form-control" >
											<option>--Select--</option>
												</select> -->
							<!-- onchange="chgarm(this,'regiment');" -->
							<select name="prnt_arm" id="prnt_arm"
								class="form-control ${not empty selectedGetParentArmList ? 'green-style' : ''}">
								<c:if test="${empty selectedGetParentArmList}">
									<option>--Select--</option>
								</c:if>

								<c:if test="${not empty selectedGetParentArmList}">
									<option>--Selected--</option>
								</c:if>
							</select>

							<div class="overSelect"></div>
						</div>
						<div id="checkboxes_prnt_arm"
							class="checkboxes ${not empty selectedGetParentArmList}"
							style="max-height: 200px; overflow: auto;">

							<div>
								<%-- <c:forEach var="item" items="${getParentArmList}" varStatus="num">
												<label for="one" class="quali_subjectlist"> <input
													type="checkbox" name="multisub_prnt_arm" value="${item[0]}" />${item[1]}
												</label>
											</c:forEach> --%>
							<input type="text" id="searchBox_parent" name="searchBox_parent"
						class="form-control autocomplete custom_search" autocomplete="off" placeholder="Search Parent Arm"  oninput="filterOptions('searchBox_parent','.quali_subjectlist_parent')"
						maxlength="40">
								<c:forEach var="item" items="${getParentArmList}"
									varStatus="num">
									<label for="one" class="quali_subjectlist_parent"> <input
										type="checkbox" name="multisub_prnt_arm" class="prntarm"
										onclick="addprntarm();" value="${item[0]}"
										<c:forEach var="selectedGetParentArmList" items="${selectedGetParentArmList}">
                <c:if test="${item[0] eq selectedGetParentArmList}">
                    checked
                </c:if>
            </c:forEach> />
										${item[1]}
									</label>
								</c:forEach>
							</div>

						</div>
					</div>
				</div>

				<div class="col-lg-3 col-md-6 col-sm-6 col-12">
					<div class="col-md-4">
						<label class=" form-control-label">Regiment:</label>
					</div>
					<div class="col-md-8">


						<div class="selectBox" onclick="showCheckboxes_cont_reg()">

							<select name="cont_reg" id="cont_reg"
								class="form-control ${not empty selectedRegsList ? 'green-style' : ''}">
								<c:if test="${empty selectedRegsList}">
									<option>--Select--</option>
								</c:if>

								<c:if test="${not empty selectedRegsList}">
									<option>--Selected--</option>
								</c:if>
							</select>
							<div class="overSelect"></div>
						</div>
						<div id="checkboxes_reg"
							class="checkboxes ${not empty selectedRegsList}"
							style="max-height: 200px; overflow: scroll; overflow-x: hidden;">

							<div></div>

						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6 col-12">
					<div class="col-md-4">
						<label class=" form-control-label">Rk:</label>

					</div>
					<div class="col-md-8">

						<div class="selectBox" onclick="showCheckboxes_rank()">

							<select name="rank" id="rank"
								class="form-control ${not empty selectedGetTypeofRankList ? 'green-style' : ''}">
								<c:if test="${empty selectedGetTypeofRankList}">
									<option>--Select--</option>
								</c:if>
								<c:if test="${not empty selectedGetTypeofRankList}">
									<option>--Selected--</option>
								</c:if>
							</select>
							<div class="overSelect"></div>
						</div>
						<div id="checkboxes_rank"
							class="checkboxes ${not empty selectedGetTypeofRankList}"
							style="max-height: 200px; overflow: auto;">

							<div>
							 <input type="text" id="searchBox_rank" name="searchBox_rank"
						class="form-control autocomplete custom_search" autocomplete="off" placeholder="Search Rank"  oninput="filterOptions('searchBox_rank','.quali_subjectlist_rank')"
						maxlength="40">
								<c:forEach var="item" items="${getTypeofRankList_jCO}"
									varStatus="num">
									<label for="one" class="quali_subjectlist_rank"> <input
										onclick="addrnk();" type="checkbox" class="rnk"
										name="multisub_rank" value="${item[0]}"
										<c:forEach var="selectedGetTypeofRankList" items="${selectedGetTypeofRankList}">
                                <c:if test="${item[0] eq selectedGetTypeofRankList}">
                                        checked
                                </c:if>
                        </c:forEach> />
										${item[1]}
									</label>
								</c:forEach>
							</div>

						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
<div class="col-md-12">

	<div class="col-lg-3 col-md-6 col-sm-6 col-12" id="sub_list" style="display: none;">
		<div class="row form-group">
			<div class="col-md-4">
				<label class=" form-control-label"><strong
					style="color: black;"></strong>Selected Comd</label>
			</div>
			<div class="col-12 col-md-8">
				<div id="quali_sub_list"
					style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid; margin: auto;">
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 col-12" id="sub_list_corps" style="display: none;">
		<div class="row form-group">
			<div class="col-md-4">
				<label class=" form-control-label"><strong
					style="color: black;"></strong>Selected Corps</label>
			</div>
			<div class="col-12 col-md-8">
				<div id="quali_sub_list_corps"
					style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid; margin: auto;">
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 col-12" id="sub_list_div" style="display: none;">
		<div class="row form-group">
			<div class="col-md-4">
				<label class=" form-control-label"><strong
					style="color: black;"></strong>Selected Div</label>
			</div>
			<div class="col-12 col-md-8">
				<div id="quali_sub_list_div"
					style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 col-12" id="sub_list_bde" style="display: none;">
		<div class="row form-group">
			<div class="col-md-4">
				<label class=" form-control-label"><strong
					style="color: black;"></strong>Selected Bde</label>
			</div>
			<div class="col-12 col-md-8">
				<div id="quali_sub_list_bde"
					style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">
				</div>
			</div>
		</div>
	</div>

</div>



&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;

<div class="col-md-12">

	<div class="col-lg-3 col-md-6 col-sm-6 col-12" id="sub_list_prntarm" style="display: none;">
		<div class="row form-group">
			<div class="col-md-4">
				<label class=" form-control-label"><strong
					style="color: black;"></strong>Selected Parent Arm</label>
			</div>
			<div class="col-12 col-md-8">
				<div id="quali_sub_list_prntarm"
					style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 col-12" id="sub_list_reg" style="display: none;">
		<div class="row form-group">
			<div class="col-md-4">
				<label class=" form-control-label"><strong
					style="color: black;"></strong>Selected Regiment</label>
			</div>
			<div class="col-12 col-md-8">
				<div id="quali_sub_list_reg"
					style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 col-12" id="sub_list_rnk" style="display: none;">
		<div class="row form-group">
			<div class="col-md-4">
				<label class=" form-control-label"><strong
					style="color: black;"></strong>Selected Rank</label>
			</div>
			<div class="col-12 col-md-8">
				<div id="quali_sub_list_rnk"
					style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">
				</div>
			</div>
		</div>
	</div>


</div>




&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
<div class="mt-2" align="center" id="divSearch">
	<div>
	<button id="jcoDashboard" onclick="getdashboard('jcoDashboard');" class="btn btn-success btn-sm">Reset</button>
	
		<input
			type="button" class="btn btn-primary btn-sm" id="b1"
			onclick="return getpsg_dashlist();" value="Search">

	</div>

</div>
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
<div class="col-md-12">
	<div class="col-lg-3 col-md-6 col-sm-6 col-12">
		<div class="info-box_jco bg-purple">
			<!--  <h5 style="font-weight: bold; font-size: 16px;">No Of OFFICER</h5> -->
			<div class="number count-to">
				<div class="icon-card success-shade bg-shape">
					<div class="icon">
						<i class="lni lni-blackboard"></i>
					</div>
					<div class="content">
						<h4 class="counter-title">TOTAL JCO/OR</h4>

						<div style="margin-left: 10px;">
							<a data-toggle="modal" data-target="#illegalityMiningCasesView"
								onclick="openFormationwiseDetails();"> <span
								style="font-size: 18mm" class="count" id="count1"></span>
							</a>
						</div>



						<!-- 						<span style="font-size: 18mm" class="count" id="count1"></span> -->


					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 col-12">
		<div class="info-box_jco bg-green">
			<div class="number count-to">
				<div class="icon-card success-shade bg-shape">
					<div class="icon">
						<i class="lni lni-blackboard"></i>
					</div>
					<div class="content">
						<h4 class="counter-title">UNITS</h4>
						<a data-toggle="modal" data-target="#illegalityMiningCasesView"
							onclick="openUnitwiseDetails();"> <span
							style="font-size: 18mm" class="count" id="count2"></span>
						</a>
						<!-- 						<span style="font-size: 18mm" class="count" id="count2"></span> -->
						<!-- 											<h3 class="counter-count" id="count2"> -->
						<!-- 												<span id="count2" class="display-4" class="h3 font-bold mb-0"></span> -->
						<!-- 											</h3> -->
						<!-- <div style="margin-left:75px;"> -->
						<!-- 						<a data-toggle="modal" data-target="#illegalityMiningCasesView" onclick="openUnitwiseDetails();"> -->
						<!-- 						<span style="font-size: 18mm" class="count" id="count2"></span> -->
						<!-- 						</a> -->
						<!-- 						<i class="fa fa-file-excel-o" id="btnExport" style="font-size: x-large; color: green; text-align: right;" aria-hidden="true" title="EXPORT TO EXCEL" onclick="getExcelUnit();"></i> -->
						<!-- </div> -->

					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-6">
		<div>
			<h2 style="color: white; background-color: #df1d4e;">RK VS HELD (JCO/OR) </h2>
			<div id="chartdiv_jco_or"></div>

		</div>

	</div>

</div>
<div class="col-md-12">
	<div class="row">
		<div class="col-lg-12 col-xl-12 divide_border">
			<div>
				<h2 style="color: white; background-color: #ff7304f5;">RK VS MED CAT (JCO/OR)</h2>
				<div class="loader-container"
					data-loader-id="loader_medicalDiv_jco_or">
					<div class="loader"></div>
				</div>
				<div id="medicalDiv_jco_or" class="chartsize"
					style="width: 100%; height: 450px;"></div>
			</div>

		</div>


	</div>
</div>

<div class="col-md-12">

	<div class="col-lg-12 col-xl-12 divide_border">
		<div>
			<h2 style="color: white; background-color: #fba200;">RK VS REGT (JCO/OR)</h2>
			<div class="loader-container" data-loader-id="loader_regtDiv_jco_or">
				<div class="loader"></div>
			</div>
			<div id="regtDiv_jco_or" class="chartsize"
				style="width: 100%; height: 450px;"></div>
		</div>
	</div>


</div>






<div class="col-md-12">
	<div class="row">
		<div class="col-lg-12 col-xl-12 divide_border">
			<div>
				<h2 style="color: white; background-color: #17bbbbf5;">RK VS PARENT ARMS (JCO/OR)</h2>
				<div class="loader-container"
					data-loader-id="loader_parentlDiv_jco_or">
					<div class="loader"></div>
				</div>
				<div id="parentlDiv_jco_or" style="width: 100%; height: 450px;"></div>
			</div>

		</div>

	</div>
</div>


<div class="col-md-12">
	<div class="row">
		<div class="col-lg-12 col-xl-12 divide_border">
			<div>
				<h2 style="color: white; background-color: #e50461;">RK VS COMD (JCO/OR)</h2>
				<div class="loader-container" data-loader-id="loader_comdDiv_jco_or">
					<div class="loader"></div>
				</div>
				<div id="comdDiv_jco_or" style="width: 100%; height: 350px;"></div>
			</div>

		</div>


	</div>
</div>
<div class="col-md-12">
	<div class="row">
		<div class="col-lg-12 col-xl-12 divide_border">
			<div>
				<h2 style="color: white; background-color: #fb6800;">RK VS BLOOD GP (JCO/OR)</h2>
				<div class="loader-container" data-loader-id="loader_bgDiv_jco_or">
					<div class="loader"></div>
				</div>
				<div id="bgDiv_jco_or" style="width: 100%; height: 350px;"></div>
			</div>
		</div>
	</div>
</div>




<div class="col-md-12">
	<div class="row">
		<div class="col-lg-12 col-xl-12 divide_border">
			<div>
				<h2 style="color: white; background-color: #ff7304f5;">RK VS GENDER (JCO/OR)</h2>
				<div class="loader-container" data-loader-id="loader_genDiv_jco_or">
					<div class="loader"></div>
				</div>
				<div id="genDiv_jco_or" class="chartsize"
					style="width: 100%; height: 450px;"></div>
			</div>
		</div>
	</div>
</div>


<div class="col-md-12">
	<div class="row">
		<div class="col-lg-12 col-xl-12 divide_border">
			<div>
				<h2 style="color: white; background-color: #fba200;">RK VS DOS (JCO/OR)</h2>
				<div class="loader-container" data-loader-id="loader_dosDiv_jco_or">
					<div class="loader"></div>
				</div>
				<div id="dosDiv_jco_or" class="chartsize"
					style="width: 100%; height: 450px;"></div>
			</div>

		</div>

	</div>
</div>


<div class="col-md-12">
	<div class="row">
<!-- 		<div class="col-lg-12 col-xl-6 divide_border" style="display: none;"> -->
<!-- 			<div> -->
<!-- 				<h2 style="color: white; background-color: #17bbbbf5;">RK VS LOC (JCO/OR) -->
<!-- 					</h2> -->
<!-- 				<div class="loader-container" data-loader-id="loader_locDiv_jco_or"> -->
<!-- 					<div class="loader"></div> -->
<!-- 				</div> -->
<!-- 				<div id="locDiv_jco_or" style="width: 100%; height: 350px;"></div> -->
<!-- 			</div> -->

<!-- 		</div> -->

		<div class="col-lg-12 col-xl-12 divide_border">
			<div>
				<h2 style="color: white; background-color: #007bffbf;">RK VS MOTHER TONGUE (JCO/OR)
					</h2>
				<div class="loader-container" data-loader-id="loader_motDiv_jco_or">
					<div class="loader"></div>
				</div>
				<div id="motDiv_jco_or" style="width: 100%; height: 350px;"></div>
			</div>

		</div>
	</div>
</div>


<div class="col-md-12">
	<div class="row">
		<div class="col-lg-12 col-xl-12 divide_border">
			<div>
				<h2 style="color: white; background-color: #ff7304f5;">RK VS MARITAL STATUS (JCO/OR) 
					</h2>
				<div class="loader-container"
					data-loader-id="loader_maritalDiv_jco_or">
					<div class="loader"></div>
				</div>
				<div id="maritalDiv_jco_or" class="chartsize"
					style="width: 100%; height: 450px;"></div>
			</div>
		</div>
	</div>
</div>


<div class="col-md-12">
	<div class="row">
		<div class="col-lg-12 col-xl-12 divide_border">
			<div>
				<h2 style="color: white; background-color: #17bbbbf5;">RK VS RELIGION (JCO/OR)
					</h2>
				<div class="loader-container" data-loader-id="loader_reliDiv_jco_or">
					<div class="loader"></div>
				</div>
				<div id="reliDiv_jco_or" style="width: 100%; height: 350px;"></div>
			</div>
		</div>
	</div>
</div>
</div>

<c:url value="jco_or_dashboard_datalist" var="backUrl" />
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
<c:url value="data_table_jco" var="popup_jco" />
<form:form action="${popup_jco}" method="post" id="tableFormjco"
	name="tableFormjco" modelAttribute="cont_comd4" target="result"> 
	<input type="hidden" name="cont_comd40" id="cont_comd40" />
	<input type="hidden" name="cont_corps40" id="cont_corps40" />
	<input type="hidden" name="cont_div40" id="cont_div40" />
	<input type="hidden" name="cont_bde40" id="cont_bde40" />
	<input type="hidden" name="rank40" id="rank40" />
	<input type="hidden" name="parent_arm40" id="parent_arm40" />
	<input type="hidden" name="arm40" id="arm40" />
	<input type="hidden" name="parm40" id="parm40" />
	<input type="hidden" name="cmd40" id="cmd40" />
	<input type="hidden" name="div40" id="div40" />
	<input type="hidden" name="corps40" id="corps40" />
	<input type="hidden" name="bdes40" id="bdes40" />
	<input type="hidden" name="regs40" id="regs40" />
	<input type="hidden" name="unit40" id="unit40" />
	<input type="hidden" name="unit_name40" id="unit_name40" />
</form:form> 
<c:url value="data_table_unit_jco" var="popup_unit" />
<form:form action="${popup_unit}" method="post" id="tableFormunit_jco"
	name="tableFormunit_jco" modelAttribute="cont_comd5" target="result"> 
	<input type="hidden" name="cont_comd50" id="cont_comd50" />
	<input type="hidden" name="cont_corps50"id="cont_corps50"/>
	<input type="hidden" name="cont_div50"id="cont_div50"/>
	<input type="hidden" name="cont_bde50"id="cont_bde50"/>
	<input type="hidden" name="rank50"id="rank50"/>
	<input type="hidden" name="parent_arm50"id="parent_arm50"/>
	<input type="hidden" name="arm50"id="arm50"/>
	<input type="hidden" name="parm50"id="parm50"/>
	<input type="hidden" name="cmd50"id="cmd50"/>
	<input type="hidden" name="div50"id="div50"/>
	<input type="hidden" name="corps50"id="corps50"/>
	<input type="hidden" name="bdes50"id="bdes50"/>
	<input type="hidden" name="regs50"id="regs50"/>
	<input type="hidden" name="unit50"id="unit50"/>
	<input type="hidden" name="unit_name50"id="unit_name50"/>
</form:form>
<input type="hidden" id="multiSelect_count_corps"
	name="multiSelect_count_corps" value="0">
<input type="hidden" id="multiSelect_count_div"
	name="multiSelect_count_div" value="0">
<input type="hidden" id="multiSelect_count_bde"
	name="multiSelect_count_bde" value="0">
<input type="hidden" id="multiSelect_count_reg"
	name="multiSelect_count_reg" value="0">


<script type="text/javascript">

var popupWindow = null;
function openFormationwiseDetails() {
	
	  var cont_comd1 = $("#CheckVal").val();  
	    var cont_corps1 = $("#CheckValcorps").val(); 
	    var cont_div1 = $("#CheckValdiv").val();  
	    var cont_bde1 =  $("#CheckValbde").val();
	    
	    var cmd = $("#CheckVal").val();
	    var check_list_corps = $("#CheckValcorps").val();
	    var check_list_div = $("#CheckValdiv").val();
	    var check_list_bde = $("#CheckValbde").val();

	    // Get selected parent_arm values and join them into a comma-separated string
	    var parent_arm = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
	                    return this.value;
	    }).get();
	    var prntarm = parent_arm.join(",");
	    
	    var unit_name1 = $('#unit_name').val();
	    
	    // Get selected reg values and join them into a comma-separated string
	    var regs1 = [];
	    $('.reg:checkbox:checked').each(function() {
	                    regs1.push($(this).val());
	    });
	    var check_list_reg = regs1.join(",");

	    // Get selected rank values and join them into a comma-separated string
	    var rankvar = $('input[name="multisub_rank"]:checkbox:checked').map(function() {
	                    return this.value;
	    }).get();
	    var rnk = rankvar.join(",");

	    // Get selected arm values and join them into a comma-separated string
	    var armvar = $('input[name="multisub_user_arm"]:checkbox:checked').map(function() {
	                    return this.value;
	    }).get();
	    var arm = armvar.join(",");

	    // Get selected parent_arm values and join them into a comma-separated string
	    var parmvar = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
	                    return this.value;
	    }).get();
	    var parm = parmvar.join(",");
	
		$("#cont_comd40").val(cont_comd1);
		$("#cont_corps40").val(cont_corps1);
		$("#cont_div40").val(cont_div1);
		$("#cont_bde40").val(cont_bde1);
		$("#rank40").val(rnk);
		$("#arm40").val(arm);
		$("#parm40").val(parm);
		$("#cmd40").val(cmd);
		$("#div40").val(check_list_div);
		$("#corps40").val(check_list_corps);
		$("#bdes40").val(check_list_bde);
		$("#regs40").val(check_list_reg);
		$("#unit40").val(unit_name1);
		$("#parent_arm40").val(parent_arm);
		$("#unit_name40").val(unit_name1);
	
	
		if(popupWindow != null && !popupWindow.closed){
			
			popupWindow.close();
			popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
			window.onfocus = function () { 
			}
			document.getElementById('tableFormjco').submit();
	}
			else
				{
				popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
				window.onfocus = function () { 
				}
				document.getElementById('tableFormjco').submit();
	
				}

}   
function openUnitwiseDetails() {
	
	  var cont_comd1 = $("#CheckVal").val();  
	    var cont_corps1 = $("#CheckValcorps").val(); 
	    var cont_div1 = $("#CheckValdiv").val();  
	    var cont_bde1 =  $("#CheckValbde").val();
	    
	    var cmd = $("#CheckVal").val();
	    var check_list_corps = $("#CheckValcorps").val();
	    var check_list_div = $("#CheckValdiv").val();
	    var check_list_bde = $("#CheckValbde").val();

	    // Get selected parent_arm values and join them into a comma-separated string
	    var parent_arm = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
	                    return this.value;
	    }).get();
	    var prntarm = parent_arm.join(",");
	    
	    var unit_name1 = $('#unit_name').val();
	    
	    // Get selected reg values and join them into a comma-separated string
	    var regs1 = [];
	    $('.reg:checkbox:checked').each(function() {
	                    regs1.push($(this).val());
	    });
	    var check_list_reg = regs1.join(",");

	    // Get selected rank values and join them into a comma-separated string
	    var rankvar = $('input[name="multisub_rank"]:checkbox:checked').map(function() {
	                    return this.value;
	    }).get();
	    var rnk = rankvar.join(",");

	    // Get selected arm values and join them into a comma-separated string
	    var armvar = $('input[name="multisub_user_arm"]:checkbox:checked').map(function() {
	                    return this.value;
	    }).get();
	    var arm = armvar.join(",");

	    // Get selected parent_arm values and join them into a comma-separated string
	    var parmvar = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
	                    return this.value;
	    }).get();
	    var parm = parmvar.join(",");
	
		$("#cont_comd50").val(cont_comd1);
		$("#cont_corps50").val(cont_corps1);
		$("#cont_div50").val(cont_div1);
		$("#cont_bde50").val(cont_bde1);
		$("#rank50").val(rnk);
		$("#arm50").val(arm);
		$("#parm50").val(parm);
		$("#cmd50").val(cmd);
		$("#div50").val(check_list_div);
		$("#corps50").val(check_list_corps);
		$("#bdes50").val(check_list_bde);
		$("#regs50").val(check_list_reg);
		$("#unit50").val(unit_name1);
		$("#parent_arm50").val(parent_arm);
		$("#unit_name50").val(unit_name1);
	
	
		if(popupWindow != null && !popupWindow.closed){
			
			popupWindow.close();
			popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
			window.onfocus = function () { 
			}
			document.getElementById('tableFormunit_jco').submit();
	}
			else
				{
				popupWindow = window.open("", "result", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
				window.onfocus = function () { 
				}
				document.getElementById('tableFormunit_jco').submit();
	
				}
	
	
	
	
// 	if(popupWindow != null && !popupWindow.closed){
// 		popupWindow.close();
// 		popupWindow = window.open("JcounitDetail?type=Formationwise", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1000,height=600,fullscreen=no");
// 		}
// 		else
// 		popupWindow = window.open("JcounitDetail?type=Formationwise", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1000,height=600,fullscreen=no");
}

window.addEventListener("load", function () {
	   
    counterLoad();
	var tempDiv = document.querySelector(".psg_dash  #WaitLoader");
    tempDiv.style.display = "block";
    setTimeout(function () {
        tempDiv.style.display = "none";
    }, 1500);
});

$(document).ready(function() {

	 if('${unit_view}' == "YES"){
		  document.getElementById("divsearchhide").style.display = "none";
		  document.getElementById("divSearch").style.display = "none";
		    document.getElementById("divtop").style.display = "none";  
}
	
	$.ajaxSetup({
		async : false
	});
	
	rkvsheld_jco_or();
	
	 if('${listunit}' != "" && '${listoff}' != ""){
		   $("#count1").text('${listoff[0][0]}');
		   $("#count2").text('${listunit[0][0]}');
		 }
	
	else
		{
		count_no_off();
// 		if('${list_no_jco}' != "" && '${list_no_jco_unit}' != "")
// 			{
// 			$("#count1").text('${list_no_jco}');
// 			   $("#count2").text('${list_no_jco_unit}');
// 			}
// 		else{
// 			count_no_off();
// 		}
		  
		}
			
	var selectedGetCommandList = '${selectedGetCommandList}';
	

	var checkparentarm = '${selectedGetParentArmList}';

	
	var selectedUnit_name = '${selectedUnit_name}';
	if(selectedUnit_name != "" && selectedUnit_name != null)
		{
		
		document.getElementById("unit_name").value = selectedUnit_name;
		}
	
	if(checkparentarm != "" && checkparentarm != null && checkparentarm != "[]")
	  {
		
		var selectedRegsList = JSON.parse(JSON.stringify('${selectedStringRegsList}'));
		
		var selectedGetParentArmList = JSON.parse(JSON.stringify('${selectedStringGetParentArmList}'));
	

		 var selectedGetParentArmList = JSON.parse(selectedGetParentArmList);
		if(selectedRegsList != "")
		  {
	
		  
	  for (var i = 0; i < selectedGetParentArmList.length; i++) {

		 
		  getReg(selectedGetParentArmList[i],selectedRegsList);
	  }
		  }
		else
			
			{
			 for (var i = 0; i < selectedGetParentArmList.length; i++) {
				 
				  getReg(selectedGetParentArmList[i]);
			  }
			}
	  }	
	 $("input[type='checkbox'][name='multisub_cmd']").click(function() {
		// showCheckboxes();
	 	 $("#checkboxes_cont_corps").hide();
		 $("#checkboxes_cont_div").hide();
		 $("#checkboxes_cont_bde").hide();
		 
	 });

	 

			
	 $("input[type='checkbox'][name='multisub_prnt_arm']").click(function() {
			
			if(this.value=="0800" || this.value == "0700")
				{
				var fcode = this.value;
				getReg(fcode);
				}
			
			//$('#chk_box').hide();
		});
			
			function getReg(fcode,data)
			{
				
		
				if (fcode == "0") {
					
				} 	if(fcode=="0800" ||fcode  == "0700"){
					
				
				//	$("select#prnt_arm").html(select);

					var options = '<option value="0">' + "--Select--" + '</option>';
					//var arm_code = this.value;

					$.post("getRegimentFromArmCode?" + key + "=" + value, {
						arm_code: fcode
					}, function(j) {
						//alert(j);
						if (j != "") {
							var length = j.length - 1;
							var enc = j[length][0].substring(0, 16);
							 var options = '<input type="text" id="searchBox_reg" name="searchBox_reg" class="form-control autocomplete custom_search" autocomplete="off" placeholder="Search Regiment" oninput="filterOptions(\'searchBox_reg\', \'.regiment\')" maxlength="40">';


							 for (var i = 0; i < length; i++) {
								 
								
								var typeOfTemp = typeof selectedRegsList;
		
							 if (typeof data != 'undefined' ) {
								 var selectedRegsList = JSON.parse(data);
							
			            	for (let k = 0; k < selectedRegsList.length; k++) {
			                  
			                	if (selectedRegsList[k] == j[i][0]) {
			                		options += '<label for="one" class="regiment"><input checked type="checkbox" onclick="addreg();"  class="reg" name="multisub_reg_' + parseInt(i + 1) + '" value="' + j[i][0] + '" /> ' + j[i][1] + ' </label> ';
			                        k = selectedRegsList.length + 1;
			                       
			                    } else if (k == selectedRegsList.length-1) {
			                    	options += '<label for="one" class="regiment"><input type="checkbox" onclick="addreg();" class="reg" name="multisub_reg_' + parseInt(i + 1) + '" value="' + j[i][0] + '" /> ' + j[i][1] + ' </label> ';
			                       
			                    }
			                }
			            } else {
			            	$("#multiSelect_count_reg").val(fcode);
			            
			            	options += '<label for="one" class="regiment"><input type="checkbox" onclick="addreg();" class="reg" name="multisub_reg_' + parseInt(i + 1) + '" value="' + j[i][0] + '" /> ' + j[i][1] + ' </label> ';
			            }								
							} 
		
						}

						else{
							
						}
						$("div#checkboxes_reg").html(options);
					});
				}
			}
		

		 if ('${cmd_sus}' != "" && '${cmd_sus}' != "0") {
			$("#cont_comd").val('${cmd_sus}');
			$("#hd_cmd_sus").val('${cmd_sus}');
			$("#cont_comd").attr("disabled", true);
			getCONTCorps('${cmd_sus}');
		} 
		if ('${corp_sus}' != "" && '${corp_sus}' != "0") {
			$("#cont_corps").val('${corp_sus}');
			$("#hd_corp_sus").val('${corp_sus}');
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true);
			getCONTDiv('${corp_sus}');
		}
		if ('${div_sus}' != "" && '${div_sus}' != "0") {
			$("#cont_div").val('${div_sus}');
			$("#hd_div_sus").val('${div_sus}');
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true);
			$("#cont_div").attr("disabled", true);
			getCONTBde('${div_sus}');
		}
		if ('${bde_sus}' != "" && '${bde_sus}' != "0") {
			$("#cont_bde").val('${bde_sus}');
			$("#hd_bde_sus").val('${bde_sus}');
			$("#cont_comd").attr("disabled", true);
			$("#cont_corps").attr("disabled", true);
			$("#cont_div").attr("disabled", true);
			$("#cont_bde").attr("disabled", true);
		}

	$("input#unit_name")
			.keypress(
					function() {
						
						var unit_name = this.value;
						var susNoAuto = $("#unit_name");
						susNoAuto
								.autocomplete({
									source : function(
											request,
											response) {
										$
												.ajax({
													type : 'POST',
													url : "getTargetUnitsNameActiveList?"
															+ key
															+ "="
															+ value,
													data : {
														unit_name : unit_name
													},
													success : function(
															data) {
														var susval = [];
														var length = data.length - 1;
														var enc = data[length]
																.substring(
																		0,
																		16);
														for (var i = 0; i < data.length; i++) {
															susval
																	.push(dec(
																			enc,
																			data[i]));
														}

														response(susval);
													}
												});
									},
									minLength : 1,
									autoFill : true,
									change : function(
											event, ui) {
										if (ui.item) {
											return true;
										} else {
											alert("Please Enter Approved Unit Name.");
											document
													.getElementById("unit_name").value = "";
											susNoAuto
													.val("");
											susNoAuto
													.focus();
											return false;
										}
									},
									
								});
					});
	
	

	 onChangeCmd();
	 
	 showfiltererd();
	    var a_corp = '${corps}';
	    var b_div = '${div1}';
	    var c_bde = '${bdes}';
	    
	    if(a_corp != "" && a_corp != "[]" )
	        {
	        findselectedcmd();
	        var cmd=$("#CheckVal").val();
	        getcorpslist(cmd);
	        setchecked(a_corp);
	        addcorps();
	        $("#sub_list_corps").show();
	        if(b_div != ""&& b_div!='')
	            {
	        	 findselectedcorps();
	 	        var corps=$("#CheckValcorps").val();
	    getdivlist(corps);
	        setcheckeddiv(b_div);
	        adddiv();
	        $("#sub_list_div").show();
	             if(c_bde!="" && c_bde!='')
	                 { findselecteddiv();
	 	 	        var div=$("#CheckValdiv").val();
	                getbdelist(div);
	        setcheckedbde(c_bde);
	        addbde();
	        $("#sub_list_bde").show();
	                 }
	             else{
	            	 findselecteddiv();
		 	 	        var div=$("#CheckValdiv").val();
		                getbdelist(div);
	             }
	            }
	        else{
	        	 findselectedcorps();
		 	    var corps=$("#CheckValcorps").val();
	        	 getdivlist(corps);
	        }
	        }
	    $("#checkboxes_reg").hide();

	});
			


	function findselectedcmd(){
		var nrSel = $(".cmdCheckBox:checkbox:checked").map(function() {
			return $(this).attr('value');
		}).get();

		var b = nrSel.join('|');
		$("#CheckVal").val(b);
	}
	function findselectedcorps(){
		var nrSel = $(".corpsCheckBox:checkbox:checked").map(function() {
			return $(this).attr('value');
		}).get();
		var b = nrSel.join('|');
		$("#CheckValcorps").val(b);
	}
	function findselecteddiv(){
		var nrSel = $(".divCheckBox:checkbox:checked").map(function() {
			return $(this).attr('value');
		}).get();
		var b = nrSel.join('|');
		$("#CheckValdiv").val(b);		
	}
	function findselectedbde(){
		var nrSel = $(".bdeCheckBox:checkbox:checked").map(function() {
			return $(this).attr('value');
		}).get();
		var b = nrSel.join('|');
		$("#CheckValbde").val(b);
	}
	
	
	
	
	


	
	function setchecked(val)
	{
		const myArray = val.split("|");
		 $(' #checkboxes_cont_corps input[type="checkbox"]').each(function() {   	
			 if(myArray.includes(this.value)){
					$( this ).attr( 'checked', true );
					

			 }
	      });
		  
	}
	function setcheckeddiv(val)
	{		
		const myArray = val.split("|");
		 $(' #checkboxes_cont_div input[type="checkbox"]').each(function() {   	
			 if(myArray.includes(this.value)){
					$( this ).attr( 'checked', true );
			 }
	      });	  
	}
	function setcheckedbde(val)
	{	
		const myArray = val.split("|");
		 $(' #checkboxes_cont_bde input[type="checkbox"]').each(function() {   	
			 if(myArray.includes(this.value)){
					$( this ).attr( 'checked', true );
			 }
	      });	  
	}
	

	function showCheckboxes_cont_reg() {
		
		
		$("#checkboxes1").hide();
		$("#checkboxes_cont_corps").hide();
		$("#checkboxes_cont_div").hide();
		$("#checkboxes_cont_bde").hide();
		$("#checkboxes_rank").hide();
		$("#checkboxes_prnt_arm").hide();
		$('#sub_list').hide();
		$('#sub_list_corps').hide();
		$('#sub_list_div').hide();
		$('#sub_list_bde').hide();
		$('#sub_list_rnk').hide();
		showselected();
	//	var reg =$('.reg:checkbox:checked').map(function() {)};
 	var reg=0;
 	$(".reg:checkbox:checked").each(function() {
			reg++;
		});
 	
 	var parent_arm = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
		return this.value;
	}).get();
 	
		if(reg==""||reg==0)
			{
			$('#sub_list_prntarm').show();
			$('#sub_list_reg').hide();
			}
		else{
			addreg();
			$('#sub_list_prntarm').show();
			$('#sub_list_reg').show();
	if ($('#checkboxes_reg').is(':hidden')) {
				$("#checkboxes_reg").show();
				} else {
					$("#checkboxes_reg").hide();
				}
			
			
		}
		if(parent_arm==""||parent_arm==0)
 		{
 		alert("Please Select Parent Arm");
 		$('#sub_list_prntarm').hide();
 		$('#sub_list_reg').hide();
 		$("#checkboxes_reg").hide();
 		
 		}
		
		else if(parent_arm.includes("0800")||parent_arm.includes("0700"))
			{
		 if(reg==0||reg=="0")
		{
		$('#sub_list_reg').hide();
		}
		else
		{
		 $('#sub_list_reg').show();
		}
		 var checkboxes = document.getElementById("checkboxes_reg");
			$("#checkboxes_reg").toggle();
			$('.cont_reg').each(function() {
				$(this).show()
			})
			}
		else{
			var options = '<option value="0">' + "--Select--" + '</option>';
			$("div#checkboxes_reg").html(options);
// 			$("#checkboxes_reg").show();
			$('#sub_list_reg').hide();
			var checkboxes = document.getElementById("checkboxes_reg");
			$("#checkboxes_reg").toggle();
			$('.cont_reg').each(function() {
				$(this).show()
			})
		}
		
		
		
		
		
	}


function showCheckboxes_rank() {
		
		$("#checkboxes_cont_corps").hide();
		$("#checkboxes1").hide();
		$("#checkboxes_cont_div").hide();
		$("#checkboxes_cont_bde").hide();
		$("#checkboxes_prnt_arm").hide();
		$("#checkboxes_reg").hide();
		$('#sub_list').hide();
		$('#sub_list_corps').hide();
		$('#sub_list_div').hide();
		$('#sub_list_bde').hide();
		
		$('#sub_list_prntarm').hide();
		$('#sub_list_reg').hide();
		showselected();
		 var rankvar = $('input[name="multisub_rank"]:checkbox:checked').map(function() {
				return this.value;
			}).get();
		 if(rankvar=="")
			{
			 $('#sub_list_rnk').hide();
			}
		else{
			$('#sub_list_rnk').show();
		}
		var checkboxes = document.getElementById("checkboxes_rank");
		$("#checkboxes_rank").toggle();
		$('.rank').each(function() {
			$(this).show()
		})
	}
	function showCheckboxes_prnt_arm() {
		$("#checkboxes_cont_corps").hide();
		$("#checkboxes1").hide();
		$("#checkboxes_cont_div").hide();
		$("#checkboxes_cont_bde").hide();
		$("#checkboxes_rank").hide();
		$("#checkboxes_reg").hide();
		$('#sub_list').hide();
		$('#sub_list_corps').hide();
		$('#sub_list_div').hide();
		$('#sub_list_bde').hide();
		$('#sub_list_rnk').hide();
	
		$('#sub_list_reg').hide();
		//showselected();
		var parent_arm = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		if(parent_arm=="")
			{
			$('#sub_list_prntarm').hide();
			}
// 		else if(parent_arm.includes("0800") ||  parent_arm.includes("0700"))
// 		{
			
// 		$('#sub_list_reg').show();
		
// 		}
		else{
			$('#sub_list_reg').hide();
			$('#sub_list_prntarm').show();
		}
		showselected();
		var checkboxes = document.getElementById("checkboxes_prnt_arm");
		$("#checkboxes_prnt_arm").toggle();
		$('.prnt_arm').each(function() {
			$(this).show()
		})
	}
	function showCheckboxes_user_arm() {
		var checkboxes = document.getElementById("checkboxes_user_arm");
		$("#checkboxes_user_arm").toggle();
		//$("#user_role_id").val('');
		$('.user_arm').each(function() {
			$(this).show()
		})
	}	

	
			</script>


<script type="text/javascript">

function rkvsheld_jco_or(){
	
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("chartdiv_jco_or", am4charts.PieChart);
	chart.hiddenState.properties.opacity = 0; // this creates initial fade-in

	
	var chartData = ${Getrk_heldlist_jco_or};
	if (chartData.length === 0) {
	   
	    chartData = [{"data11": 0, "data21": ""}];
	    var noDataLabel = chart.seriesContainer.createChild(am4core.Label);
	    noDataLabel.text = "No Data Available";
	    noDataLabel.fontSize = 30;
	   // noDataLabel.align = "center";
	   // noDataLabel.valign = "middle";
	    noDataLabel.dy = -25;
	    noDataLabel.dx = -100;// Adjust vertical position as needed
	}

	chart.data = chartData;

	var pieSeries = chart.series.push(new am4charts.PieSeries());
	pieSeries.dataFields.value = "data11";
	pieSeries.dataFields.category = "data21";
	pieSeries.ticks.template.disabled = true;
	pieSeries.labels.template.disabled = true;
	pieSeries.slices.template.stroke = am4core.color("#ccc");
	pieSeries.slices.template.strokeWidth = 0;
	pieSeries.slices.template.strokeOpacity = 1;
	pieSeries.dataFields.radiusValue = "total";    
	pieSeries.slices.template.cornerRadius = 6;
	pieSeries.hiddenState.properties.endAngle = -90;

	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc", "#33E6FF", "#B533FF"].map(function(color) {
	    return new am4core.color(color);
	});
	
	pieSeries.colors = colorSet;


	chart.legend = new am4charts.Legend();
	chart.legend.itemContainers.template.adapter.add("text", function (text, target) {
	    var value = target.dataItem.value;
	    return target.dataItem.category + ": " + (value.percent * 100).toFixed(2) + "%";
	})

}

		
		
		function counter(id, start, end, duration) {
			  let obj = document.getElementById(id),
			   current = start,
			   range = end - start,
			   increment = end > start ? 1 : -1,
			   step = Math.abs(Math.floor(duration / range)),
			   timer = setInterval(() => {
				   
				   current += increment;
				    obj.textContent = current;
				    if (current == end) {
				     clearInterval(timer);
				    }
				   }, step);
				 }
		
		function count_no_off() {
		
			$.post('Getcount_no_Jco_OrData?' + key + "=" + value, {
			}, function(i) {
				if(i !=0) {
				$('#count1').text(i);
				}
			});
   $.post('Getcount_no_unitJco_OrData?' + key + "=" + value, {
			}, function(i) {
				
				if(i !=0) {
					$('#count2').text(i);
					
				}
			});
   //counterLoad();
		} 
		
		
		
		
		
		
		function counterLoad(){
			$('.count').each(function () {
			    $(this).prop('Counter',0).animate({
			        Counter: $(this).text()
			    }, {
			        duration: 1000,
			        easing: 'swing',
			        step: function (now) {
			            $(this).text(Math.ceil(now));
			        }
			    });
			});
		}
		
		//rk vs state/////////////////////////////////////////////////////////////////////////////////
		
		function getpsg_dashlist(){
			$(".psg_dash #WaitLoader").show();

			findselectedcmd();
			findselectedcorps();
			findselecteddiv();
			findselectedbde();
			var cmd=$("#CheckVal").val();
			var check_list_corps=$("#CheckValcorps").val();
			var check_list_div=$("#CheckValdiv").val();
			var check_list_bde=$("#CheckValbde").val();

			var parent_arm = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
					return this.value;
				}).get();
				var prntarm = parent_arm.join(",");
				var unit_name = $('#unit_name').val();	
					
				var reg = $('#multiSelect_count_reg').val();	
				var check_list_reg = "";
// 				for(var i = 1; i <= reg; i++){
// 					if ($('input[name="multisub_reg_'+i+'"]:checked').is(':checked')){
// 						check_list_reg += $('input[name="multisub_reg_'+i+'"]:checked').val() +",";
// 						var check_list_reg = check_list_reg.substring(0, check_list_reg.length-1);
// 						//alert("bd-"+check_list_bd);
// 						//$("#multiSelect_checkboxes").val(check_list);
// 					}
					
// 				}
				$('.reg:checkbox:checked').each(function() { 
					check_list_reg += this.value +",";
					
				});
				
				 var rankvar = $('input[name="multisub_rank"]:checkbox:checked').map(function() {
						return this.value;
					}).get();
					var rnk = rankvar.join(",");
					//alert("s-"+rnk); 
				
				var armvar = $('input[name="multisub_user_arm"]:checkbox:checked').map(function() {
						return this.value;
					}).get();
					var arm = armvar.join(",");
					
					var parmvar = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
						return this.value;
					}).get();
					var parm = parmvar.join(",");
		
		//var unit_name = $('#unit_name').val();
			$("#cont_comd1").val(cmd);
			$("#cont_corps1").val(check_list_corps);
			$("#cont_div1").val(check_list_div);
			$("#cont_bde1").val(check_list_bde);
			$("#rank1").val(rnk);
			$("#arm1").val(arm);
			$("#parm1").val(parm);
			$("#cmd1").val(cmd);
			$("#div1").val(check_list_div);
			$("#corps1").val(check_list_corps);
			$("#bdes1").val(check_list_bde);
			$("#regs1").val(check_list_reg);
			$("#unit1").val(document.getElementById("unit_name").value);
			$("#parent_arm1").val(parent_arm);
			$("#unit_name1").val(unit_name);
			$("#unit_view").val("NO");

			$("#searchForm").submit();
		

			 
		
			
		}
	
				


</script>
<script>
	function showCheckboxes1()
	{
		 $('#sub_list').hide();
		 $('#sub_list_corps').hide();
		$('#sub_list_div').hide();
		$('#sub_list_bde').hide();
		$('#sub_list_rnk').hide();
		$('#sub_list_prntarm').hide();
		$('#sub_list_reg').hide();
	//checkboxhide
	        $("#checkboxes_cont_corps").hide();
			$("#checkboxes_cont_div").hide();
			$("#checkboxes_cont_bde").hide();
			$("#checkboxes_reg").hide();
			$("#checkboxes_rank").hide();
			$("#checkboxes_prnt_arm").hide();
	 //selected hide
	 findselectedcmd();
	 showselected()
	 var cmd=$("#CheckVal").val();
	if(cmd=="")
	{
	$("#sub_list").hide();
	}
	else{
	    $("#sub_list").show();
	}
	var checkboxes = document.getElementById("checkboxes1");
			$("#checkboxes1").toggle();
			$('.cont_comd').each(function() {
				$(this).show()
			})

	}

	function showcheckbox_corps()
	{//checkboxhide
		$("#checkboxes1").hide();
			$("#checkboxes_cont_div").hide();
			$("#checkboxes_cont_bde").hide();
			$("#checkboxes_reg").hide();
			$("#checkboxes_rank").hide();
			$("#checkboxes_prnt_arm").hide();
	     //selected hide
	 $('#sub_list').hide();
		 $('#sub_list_corps').hide();
		$('#sub_list_div').hide();
		$('#sub_list_bde').hide();
		$('#sub_list_rnk').hide();
		$('#sub_list_prntarm').hide();
		$('#sub_list_reg').hide();
	     findselectedcmd();
	     showselected()
	     var cmd=$("#CheckVal").val();
	if(cmd=="")
	{
	$("#sub_list").hide();
	alert("Please Select Comd");
	}
	else{
	$("#sub_list").show();
	findselectedcorps();
	var corps=$("#CheckValcorps").val();
	if(corps=="")
	{
	    $("#sub_list_corps").hide();
	}
	else{
	    $("#sub_list_corps").show();
	}
	var checkboxes = document.getElementById("checkboxes_cont_corps");
	$("#checkboxes_cont_corps").toggle();
	$('.cont_corps').each(function() {
		$(this).show()
	})	

	}
	
	}



	function showcheckbox_div()
	{
		 $('#sub_list').hide();
		 $('#sub_list_corps').hide();
		$('#sub_list_div').hide();
		$('#sub_list_bde').hide();
		$('#sub_list_rnk').hide();
		$('#sub_list_prntarm').hide();
		$('#sub_list_reg').hide();
	    //checkboxhide
	    $("#checkboxes_cont_corps").hide();
			$("#checkboxes1").hide();
			$("#checkboxes_cont_bde").hide();
			$("#checkboxes_reg").hide();
			$("#checkboxes_rank").hide();
			$("#checkboxes_prnt_arm").hide();
	     //selected hide
	     findselectedcmd();
	     showselected();
	    var cmd=$("#CheckVal").val();
	if(cmd=="")
	{
	$("#sub_list").hide();
	alert("Please Select Comd");
	}
	else{
	$("#sub_list").show();
	findselectedcorps();
	var corps=$("#CheckValcorps").val();
	if(corps=="")
	{
	    $("#sub_list_corps").hide();
	alert("Please Select Corps")
	}
	else{
	    findselecteddiv();
	    var div=$("#CheckValdiv").val();
	    $("#sub_list_corps").show();
	    if(div=="")
	    {
	        $("#sub_list_div").hide();
	    }
	    else{
	        $("#sub_list_div").show();
	    }
	    var checkboxes = document.getElementById("checkboxes_cont_div");
		$("#checkboxes_cont_div").toggle();
		$('.cont_div').each(function() {
		    $(this).show()
		})
	}
	

	}

	}

	function showcheckbox_bde()
	{//checkboxhide
	    $("#checkboxes_cont_corps").hide();
			$("#checkboxes1").hide();
			$("#checkboxes_cont_div").hide();
			$("#checkboxes_reg").hide();
			$("#checkboxes_rank").hide();
			$("#checkboxes_prnt_arm").hide();
	     //selected hide
	      $('#sub_list').hide();
		 $('#sub_list_corps').hide();
		$('#sub_list_div').hide();
		$('#sub_list_bde').hide();
		$('#sub_list_rnk').hide();
		$('#sub_list_prntarm').hide();
		$('#sub_list_reg').hide();
	     findselectedcmd();
	     showselected();
	     var cmd=$("#CheckVal").val();
	    if(cmd=="")
	    {
	    $("#sub_list").hide();
	    alert("Please Select Comd");
	    }
	    else{
	    $("#sub_list").show();
	    findselectedcorps();
	    var corps=$("#CheckValcorps").val();
	    if(corps=="")
	    {
	     $("#sub_list_corps").hide();
	    alert("Please Select Corps")
	    }
	    else{
	        findselecteddiv();
	        var div=$("#CheckValdiv").val();
	        $("#sub_list_corps").show();
	        if(div=="")
	        {
	            $("#sub_list_div").hide();
	            alert("Please Select Div")
	        }
	        else{
	        	findselectedbde();
	            var bde=$("#CheckValbde").val();
	            $("#sub_list_div").show();
	            if(bde=="")
	            {
	                $("#sub_list_bde").hide(); 
	            }
	            else{
	                $("#sub_list_bde").show();
	            
	            }
	            var checkboxes = document.getElementById("checkboxes_cont_bde");
				$("#checkboxes_cont_bde").toggle();
				$('.cont_bde').each(function() {
					$(this).show()
				})
	           
	        }
	    }
	   
	    
	}
	}




	function onChangeCmd()
	
	{//close the checkbox cmd
		
	 $("#checkboxes1").hide();
	addcmd();
	findselectedcmd();
	var cmd=$("#CheckVal").val();
	if(cmd=="")
	{
	$("#sub_list").hide();
	$("#sub_list_corps").hide();
	$("#sub_list_div").hide();
	$("#sub_list_bde").hide();
	/////////////////////
	$('#checkboxes_cont_bde').empty();
	$('#checkboxes_cont_div').empty();
	$('#checkboxes_cont_corps').empty();
	}
	else{
		$("#sub_list").show();
	    findselectedcorps();
	    var corps=$("#CheckValcorps").val();
	    getcorpslist(cmd);
	    setchecked(corps);
	    addcorps();
	    findselectedcorps();
	    var corps2=$("#CheckValcorps").val();
	    if(corps2!="")
	    	{
	    	findselecteddiv();
		    var div=$("#CheckValdiv").val();
		    getdivlist(corps2);
		    setcheckeddiv(div);
		    adddiv();
		    findselecteddiv();
		    var div2=$("#CheckValdiv").val();
		    if(div2!="")
		    	{
		    	findselectedbde();
			    var bde=$("#CheckValbde").val();
			    getbdelist(div2);
			    setcheckedbde(bde);
			    addbde();
			    findselectedbde();
			    var bde2=$("#CheckValbde").val();
			    if(bde2=="")
			    	{
			    	$("#sub_list_bde").hide();
			    	}
		    	}		    
	    	}	    
	}
	}



	function onChangeCorps()
	{//close the checkbox corps
	    $("#checkboxes_cont_corps").hide();
	addcorps();
	findselectedcorps();
	var corps=$("#CheckValcorps").val();
	if(corps=="")
	{
	$("#sub_list_corps").hide();
	$("#sub_list_div").hide();
	$("#sub_list_bde").hide();
	$('#checkboxes_cont_bde').empty();
	$('#checkboxes_cont_div').empty();
	}
	else{
		$("#sub_list_corps").show();
	    findselecteddiv();
	    var div=$("#CheckValdiv").val();
	    getdivlist(corps)
	    setcheckeddiv(div);
	    adddiv();
	    findselecteddiv();
	    var div2=$("#CheckValdiv").val();
	    if(div2!="")
	    	{
	    	findselectedbde();
		    var bde=$("#CheckValbde").val();
		    getbdelist(div2);
		    setcheckedbde(bde);
		    addbde();
		    findselectedbde();
		    var bde2=$("#CheckValbde").val();
		    if(bde2=="")
		    	{
		    	$("#sub_list_bde").hide();
		    	}
	    	}
	}
	}

	function onChangeDiv()
	{//close the checkbox div
	    $("#checkboxes_cont_div").hide();
	adddiv();
	findselecteddiv();
	var div=$("#CheckValdiv").val();
	if(div=="")
	{
	$("#sub_list_div").hide();
	$("#sub_list_bde").hide();
	$('#checkboxes_cont_bde').empty();
	}
	else{
		$("#sub_list_div").show();
	    findselectedbde();
	    findselecteddiv();
	    var bde=$("#CheckValbde").val();

	    getbdelist(div);
	    setcheckedbde(bde);
	    addbde();
	    findselectedbde();
	    var bde2=$("#CheckValbde").val();
	    if(bde2=="")
	    	{
	    	$("#sub_list_bde").hide();
	    	}
	}
	}


	function onChangeBde()
	{//close the checkbox bde
	$("#checkboxes_cont_bde").hide();
	addbde();
	findselectedbde();
	var bde=$("#CheckValbde").val();
	if(bde=="")
	{
	$("#sub_list_bde").hide();
	}
	else{
		$("#sub_list_bde").show();
	}

	}


	function removeSubFn(value) 
	{
	    
	    $("input[type='checkbox'][name='multisub_cmd'][value='" + value + "']").attr('checked', false);
		
	    onChangeCmd();
	    onChangeCorps();
	    onChangeDiv();
	    onChangeBde();

	}
	function removeSubFncorps(value) 
	{
	    $(".corpsCheckBox:checkbox[value='" + value + "']").prop('checked', false);
	    onChangeCorps();
	    onChangeDiv();
	    onChangeBde();
	}

	function removeSubFndiv(value) 
	{
	    $(".divCheckBox:checkbox[value='" + value + "']").prop('checked', false);
	    onChangeDiv();
	    onChangeBde();
	}
	function removeSubFnbde(value) 
	{
	    $(".bdeCheckBox:checkbox[value='" + value + "']").prop('checked', false);
	    onChangeBde();

	}

	function addcmd() {	
	    //only add to the quali_sublist
	    arr1 = [];
	    $('#quali_sub_list').empty()
	    $("input[type='checkbox'][name='multisub_cmd']").each(function() {
	        if(this.checked) {
	            $('#quali_sub_list').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFn(\"" + this.value + "\")'></i><span> <br>");
	            arr1.push(this.parentElement.innerText)
	        }
	    });

	}
	function addcorps() {
	    arr1 = [];
	    $('#quali_sub_list_corps').empty()
	    $(".corpsCheckBox:checkbox:checked").each(function() {
	            $('#quali_sub_list_corps').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFncorps(\"" + this.value + "\")'></i><span> <br>");
	            arr1.push(this.parentElement.innerText)				
	          
	    });
	    
	}
	function adddiv() {
		  arr1 = [];
	    $('#quali_sub_list_div').empty()
		$(".divCheckBox:checkbox:checked").each(function() {
				$('#quali_sub_list_div').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFndiv(\"" + this.value + "\")'></i><span> <br>");
				arr1.push(this.parentElement.innerText)
				
		});
	}
	function addbde() {
	    arr1 = [];
		$('#quali_sub_list_bde').empty()
		$(".bdeCheckBox:checkbox:checked").each(function() {
				$('#quali_sub_list_bde').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFnbde(\"" + this.value + "\")'></i><span> <br>");
				arr1.push(this.parentElement.innerText)
		});
	}

	function getcorpslist(selectedcmd){
	    var select = '<option value="' + "0" + '">'
	    + "--Select--" + '</option>';

	if(selectedcmd==""||selectedcmd=="0"||selectedcmd=="null")
	    {
	        var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	        $("div#checkboxes_cont_corps").html(options);
	    }
	else{
	    $.post("CorpsDetailsListget?"+key+"="+value,{selectedcmd:selectedcmd}, function(j) {
	        if (j != "") {
	            var length = j.length;
	            var options = ' <input type="text" id="searchBox_corps" name="searchBox_corps"class="form-control autocomplete custom_search" autocomplete="off" placeholder="Search Corps"  oninput="filterOptions(\'searchBox_corps\',\'.quali_subjectlist_corps\')" maxlength="40">';
		        
	            for (var i = 0; i < length; i++) {			    	
	                  options += '<label for="one" class="quali_subjectlist_corps"><input type="checkbox" class="corpsCheckBox" onclick="onChangeCorps();" name="multisub_corps' + parseInt(i + 1) + '" value="' +  j[i][0] + '" /> ' + j[i][1] + ' </label> ';
	                  
	            }
	            $("div#checkboxes_cont_corps").html(options);
	        }
	            else{
	            var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	             $("div#checkboxes_cont_corps").html(options);
	        }
	        
	    });
	}

	}

	function getdivlist(selectedcorps){
	    var select = '<option value="' + "0" + '">'
	    + "--Select--" + '</option>';
	if(selectedcorps==""||selectedcorps=="0"||selectedcorps=="null") {
	    var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	    $("div#checkboxes_cont_div").html(options);
	}
	else{
	    $.post("DivDetailsListget?"+key+"="+value,{selectedcorps:selectedcorps}, function(j) {
	        if (j != "") {
	            var length = j.length;
	            var options = '<input type="text" id="searchBox_div" name="searchBox_div" class="form-control autocomplete custom_search" autocomplete="off" placeholder="Search Div" oninput="filterOptions(\'searchBox_div\', \'.quali_subjectlist_div\')" maxlength="40">';

	            for (var i = 0; i < length; i++) {
	        
	           options += '<label for="one" class="quali_subjectlist_div"><input type="checkbox" class="divCheckBox" onclick="onChangeDiv();" name="multisub_div_'+parseInt(i+1)+'" value="'+j[i][0]+'" /> '+ j[i][1]+ ' </label> ' ;
	    
	            }
	           $("div#checkboxes_cont_div").html(options); 
	            
	        }
	            else{
	            var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	             $("div#checkboxes_cont_div").html(options);
	        }
	        
	    });
	    
	}

	}

	function getbdelist(selecteddiv){
	    
	var select = '<option value="' + "0" + '">'
	    + "--Select--" + '</option>';
	if(selecteddiv==""||selecteddiv=="0"||selecteddiv=="null") {
	    var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	    $("div#checkboxes_cont_bde").html(options); 
	    }
	else{
	    $.post("BdeDetailsListget?"+key+"="+value,{selecteddiv:selecteddiv}, function(j) {
	        if (j != "") {
	            var length = j.length;
	            var options = '<input type="text" id="searchBox_bde" name="searchBox_bde" class="form-control autocomplete custom_search" autocomplete="off" placeholder="Search Bde" oninput="filterOptions(\'searchBox_bde\', \'.quali_subjectlist_bde\')" maxlength="40">';

	            for (var i = 0; i < length; i++) {			    				 
	                options += '<label for="one" class="quali_subjectlist_bde"><input type="checkbox" onclick="onChangeBde();" class="bdeCheckBox"  name="multisub_bde_'+parseInt(i+1)+'" value="'+ j[i][0]+'" /> '+j[i][1]+ ' </label> ' ;                    
	            }
	         $("div#checkboxes_cont_bde").html(options); 
	        }
	            else{
	            var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
	               $("div#checkboxes_cont_bde").html(options); 
	        }       
	    });
	  
	}
	    
	}
	
	function addprntarm() {		
		
		var num=0;
			 arr1 = [];
			$('#quali_sub_list_prntarm').empty()
			$("input[type='checkbox'][name='multisub_prnt_arm']").each(function() {
				if(this.checked) {
					$('#quali_sub_list_prntarm').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFnprntarm(\"" + this.value + "\")'></i><span> <br>");
					arr1.push(this.parentElement.innerText)
					num++;
				}
			});
			if(num==0)
				{
				$('#sub_list_prntarm').hide();
				}
			else{
				$('#sub_list_prntarm').show();
			}
			$("#checkboxes_prnt_arm").hide();

		}

		
		function addrnk() {		
			
			var num=0;
			 arr1 = [];
			$('#quali_sub_list_rnk').empty()
			$("input[type='checkbox'][name='multisub_rank']").each(function() {
				if(this.checked) {
					$('#quali_sub_list_rnk').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFnrnk(\"" + this.value + "\")'></i><span> <br>");
					arr1.push(this.parentElement.innerText)
					num++;
				}
			});
			if(num==0)
				{
				$('#sub_list_rnk').hide();
				}
			else{
				$('#sub_list_rnk').show();
			}
			$("#checkboxes_rank").hide();
			$("#checkboxes_cont_bde").hide();
			$("#checkboxes_cont_corps").hide();
			$("#checkboxes1").hide();
			$("#checkboxes_cont_div").hide();
			$("#checkboxes_reg").hide();
			$("#checkboxes_prnt_arm").hide();
		}
	
	

		 function addreg() {	
	
			 var num =0;
			 arr1 = [];
			$('#quali_sub_list_reg').empty() 
			$(".reg:checkbox:checked").each(function() {
					$('#quali_sub_list_reg').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFnreg(\"" + this.value + "\")'></i><span> <br>");
					arr1.push(this.parentElement.innerText)
					num++;
				
			});
			
			 if(num==0)
				{
// 					$('#sub_list_prntarm').hide();
					$('#sub_list_reg').hide();
				}
			else{
				
				$('#sub_list_prntarm').show();
				$('#sub_list_reg').show();
			}
			
			$("#checkboxes_cont_bde").hide();
			$("#checkboxes_cont_corps").hide();
			$("#checkboxes1").hide();
			$("#checkboxes_cont_div").hide();
			if ($('#checkboxes_reg').is(':hidden')) {
				$("#checkboxes_reg").show();
				} else {
					$("#checkboxes_reg").hide();
				}
			$("#checkboxes_rank").hide();
			$("#checkboxes_prnt_arm").hide();
		}
	
		 function addprntarm() {		
		
				var num=0;
					 arr1 = [];
					$('#quali_sub_list_prntarm').empty()
					$("input[type='checkbox'][name='multisub_prnt_arm']").each(function() {
						if(this.checked) {
							$('#quali_sub_list_prntarm').append("<span class='subspan'>"+this.parentElement.innerText+"<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFnprntarm(\"" + this.value + "\")'></i><span> <br>");
							arr1.push(this.parentElement.innerText)
							num++;
						}
					});
					if(num==0)
						{
						$('#sub_list_prntarm').hide();
						}
					else{
						$('#sub_list_prntarm').show();
					}
				
					$("#checkboxes_cont_bde").hide();
					$("#checkboxes_cont_corps").hide();
					$("#checkboxes1").hide();
					$("#checkboxes_cont_div").hide();
					$("#checkboxes_reg").hide();
					$("#checkboxes_rank").hide();
					$("#checkboxes_prnt_arm").hide();
				}
		 function removeSubFnreg(value) {
				var num=0;
					$(".reg:checkbox[value='" + value + "']").prop('checked', false);
					$('#quali_sub_list_reg').empty()
					$(".reg:checkbox:checked").each(function() {
					$('#quali_sub_list_reg').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFnreg(\"" + this.value + "\")'></i><span> <br>");
				num++;
						});
					if(num==0)
						{
						$('#sub_list_reg').hide();
						}
					$("#checkboxes_cont_bde").hide();
					$("#checkboxes_cont_corps").hide();
					$("#checkboxes1").hide();
					$("#checkboxes_cont_div").hide();
					$("#checkboxes_reg").hide();
					$("#checkboxes_rank").hide();
					$("#checkboxes_prnt_arm").hide();
						
					}
				function removeSubFnprntarm(value) {
					var num=0;
					$("input[type='checkbox'][name='multisub_prnt_arm'][value='" + value + "']").attr('checked', false);
					$('#quali_sub_list_prntarm').empty()
					$("input[type='checkbox'][name='multisub_prnt_arm']").each(function() {
						if(this.checked) {
							num++;
							$('#quali_sub_list_prntarm').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFnprntarm(\"" + this.value + "\")'></i><span> <br>");
							if(this.value!="0800" || this.value != "0700")
							{
							$("#checkboxes_reg").hide();
							}
						}
					});
					if(num==0)
					{
					$('#sub_list_prntarm').hide();
					$('#sub_list_reg').hide();
					}
					
					var parent_arm = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
						return this.value;
					}).get();
					
					
					if(parent_arm.includes("0800")||parent_arm.includes("0700"))
						{
						var a=0;
						$(".reg:checkbox:checked").each(function() {
							a++;
						});
						if(a!=0)
							{
							$('#sub_list_reg').show();
							}
						else{
							$('#sub_list_reg').hide();
						}
						
						
						}
					else{
						$('#sub_list_reg').hide();
					}
					
					$("#checkboxes_cont_bde").hide();
					$("#checkboxes_cont_corps").hide();
					$("#checkboxes1").hide();
					$("#checkboxes_cont_div").hide();
					$("#checkboxes_reg").hide();
					$("#checkboxes_rank").hide();
					$("#checkboxes_prnt_arm").hide();
				
				}
				
				function removeSubFnrnk(value) {
					var num=0;
					$("input[type='checkbox'][name='multisub_rank'][value='" + value + "']").attr('checked', false);
					$('#quali_sub_list_rnk').empty()
					$("input[type='checkbox'][name='multisub_rank']").each(function() {
						if(this.checked) {
							num++;
							$('#quali_sub_list_rnk').append("<span class='subspan'>" + this.parentElement.innerText + "<i class='fa fa-times' style='margin: 5px;  font-size: 15px;' onclick='removeSubFnrnk(\"" + this.value + "\")'></i><span> <br>");

						}
						
					});
					if(num==0)
					{
					$('#sub_list_rnk').hide();
					}
					$("#checkboxes_cont_corps").hide();
					$("#checkboxes_cont_bde").hide();
					$("#checkboxes1").hide();
					$("#checkboxes_cont_div").hide();
					$("#checkboxes_reg").hide();
					$("#checkboxes_rank").hide();
					$("#checkboxes_prnt_arm").hide();
				
				}
				
				function showfiltererd()
				{
					const e='${selectedGetParentArmList}';
					const f=	'${selectedRegsList}';
					const g='${selectedGetTypeofRankList }';
					
					if(e!=""&&e!="[]"){
						addprntarm();
						//$('#sub_list_prntarms').show();
						
					}
					if(f!="" &&f!="[]")
						{
						setcheckedreg(f)
						addreg();
					//	$('#sub_list_reg').show();
						
						}
					if(g!="" && g!="[]"){
						addrnk();
						//$('#sub_list_rnk').show();
					}
				
				}	
			function setcheckedreg(val)
			{
				const myArray = val.split(",");
				 $(' #checkboxes_cont_bde input[type="checkbox"]').each(function() {   	
					 if(myArray.includes(this.value)){
							$( this ).attr( 'checked', true );
					 }
			      });	
			}
			
			



			function findselectedreg()
			{var num=0;
			 $(".prntarm:checkbox:checked").each(function() {
					num++; 
			          $("#sub_list_prntarm").show();
			       
			    });
				
				if(num==0)
					{
					$("#sub_list_reg").hide();
					}
				else{
					$(".reg:checkbox:checked").each(function() {
				        $("#sub_list_reg").show();
							});
				}
			    
			}
			function findselectedprntarm()
			{
				
			    $(".prntarm:checkbox:checked").each(function() {
			 
			          $("#sub_list_prntarm").show();
			       
			    });
			}
			function findselectedrnk()
			{

				  $(".rnk:checkbox:checked").each(function() {
			       
			            $("#sub_list_rnk").show();
			        
			    });
			}



			function showselected()
			{
				
				
			findselectedbde();
			findselectedcmd();
			findselectedcorps();
			findselecteddiv();
			findselectedrnk()
			findselectedprntarm();
			findselectedreg()
			var cmd=$("#CheckVal").val();
			var corps=$("#CheckValcorps").val();
			var div=$("#CheckValdiv").val();
			var bde=$("#CheckValbde").val();
			var parent_arm = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
				return this.value;
			}).get();
		 	
			 if(parent_arm.includes("0800")||parent_arm.includes("0700"))
				{
				 var reg=0;
				 	$(".reg:checkbox:checked").each(function() {
							reg++;
						});
				 	if(reg!=0)
				 		{
				 		$('#sub_list_reg').show();
				 		}
				 	else{
				 		$('#sub_list_reg').hide();
				 	}
				}
				 else{
					 $('#sub_list_reg').hide();
				 }
				
			
			
			if(cmd!="" &&cmd!="[]")
						{
						$('#sub_list').show();
						}
					if(corps!=""&&corps!="[]" )
						{
						$('#sub_list_corps').show();
						
						}
					if(div!=""&&div!="[]")
						{
						$('#sub_list_div').show();
						
						}
					if(bde!=""&&bde!="[]")
						{
						$('#sub_list_bde').show();
						
						}
			}
	
	</script>

<script type="text/javascript">
/* const chartContainers = document.querySelectorAll('.chartsize');

const observer = new IntersectionObserver((entries) => {
  entries.forEach((entry) => {
    if (entry.isIntersecting) {
      // Load and initialize the chart when it becomes visible
      initializeAmChart(entry.target.id);
      // Stop observing this container to avoid unnecessary initializations
      observer.unobserve(entry.target);
    }
  });
});

chartContainers.forEach((container) => {
  // Start observing each chart container
  observer.observe(container);
}); */

 </script>

<script   type="text/javascript" src="js/miso/psg_dashboard/jco_dashboard.js"></script>
