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
body {
	font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
		Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji",
		"Segoe UI Symbol";
}

#chartdiv {
	width: 100%;
	height: 300px;
}

.green-style {
	color: green !important;
}

.open-List {
	display: block !important;
}

#parentlDiv {
	width: 100%;
	height: 300px;
}

#stateDiv {
	width: 100%;
	height: 300px;
}

#comdDiv {
	width: 100%;
	height: 300px;
}

#bgDiv {
	width: 100%;
	height: 300px;
}

/* .counter-title { */
/* 	margin-bottom: 10px; */
/* } */

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

.checkboxes label {
	margin-left: 10px;
	text-align: left;
	display: block;
}

.checkboxes label:hover {
	background-color: #1e90ff;
}

.info-box_psg{
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
    height: 150px;
    display: flex;
    cursor: default;
    background-color: #fff;
    position: relative;
    overflow: hidden;
    margin-bottom: 10px;
    border-style: ridge;

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
   span.subspan {
	padding: 5px;
	background-color: #79cece54;
	border-radius: 20px;
	margin: 3px;
	display: block;
	width: fit-content;
}
</style>

<div class="psg_dash">
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
		<div class="card-body card-block" id="divsearchhide"  >
			<div class="col-md-12" >
				<div class="col-md-3">
				<div class="col-md-4">
					<input type="hidden" id="CheckVal" name="CheckVal">
					<label class=" form-control-label">Comd:</label>
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

						<div class="overSelect" ></div>
					</div>

					<div id="checkboxes1"
						class="checkboxes ${not empty selectedGetCommandList}"
						style="max-height: 200px; overflow: auto;">
						<div id="chk_box">
						
						 <input type="text" id="searchBox" name="searchBox"
						class="form-control autocomplete custom_search" autocomplete="off" placeholder="Search Comd"  oninput="filterOptions('searchBox','.quali_subjectlist_cmd')"
						maxlength="40">
							<c:forEach var="item" items="${getCommandList}" varStatus="num">
								<label for="one" class="quali_subjectlist_cmd"> <input
									type="checkbox" name="multisub_cmd" onclick="onChangeCmd();"  class="cmdCheckBox" value="${item[0]}"
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
				<div class="col-md-3" >
				<div class="col-md-4">
				<input type="hidden" id="CheckValcorps" name="CheckValcorps">
					<label class=" form-control-label">Corps:</label>
					</div>
									<div class="col-md-8" >

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
					<div id="checkboxes_cont_corps" class="checkboxes ${not empty selectedCorpsList }"
						style="max-height: 200px; overflow: scroll; overflow-x: hidden;">

						<div></div>



					</div>


				</div>
					
				</div>
				<div class="col-md-3">
				<div class="col-md-4">
				<input type="hidden" id="CheckValdiv" name="CheckValdiv">
					<label class=" form-control-label">Div:</label>
						</div>
						<div class="col-md-8">

					<div class="selectBox" onclick="showcheckbox_div()">
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
					<div id="checkboxes_cont_div" class="checkboxes ${not empty selectedDivsList }"
						style="max-height: 200px; overflow: scroll; overflow-x: hidden;">

						<div></div>

				</div>
						</div>
				</div>
				
				<div class="col-md-3" >
				<div class="col-md-4">
					<label class=" form-control-label">Bde:</label>
						<input type="hidden" id="CheckValbde" name="CheckValbde">
						</div>
										<div class="col-md-8" >


					<div class="selectBox" onclick="showcheckbox_bde()">
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
					<div id="checkboxes_cont_bde" class="checkboxes ${not empty selectedBdesList}"
						style="max-height: 200px; overflow: scroll; overflow-x: hidden;">

						<div></div>

					</div>
				</div>
				
				
				
				</div>
				
				
				</div>
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
			<div class="col-md-12">
				<div class="col-md-3">
				<div class="col-md-4">
					<label class=" form-control-label">Unit:</label>
						</div>
						<div class="col-md-8">
						
					<input type="text" id="unit_name" name="unit_name"
						class="form-control autocomplete" autocomplete="off"
						maxlength="50">
						</div>
				</div>
				<%-- <div class="col-md-3" >
				<div class="col-md-4">
					<label class=" form-control-label">Parent Arm:</label>
						</div>
							<div class="col-md-8" >


					<div class="selectBox" onclick="showCheckboxes_prnt_arm()">
						<!-- <select name="prnt_arm" id="prnt_arm" class=" form-control" >
											<option>--Select--</option>
												</select> -->
												<!-- onchange="chgarm(this,'regiment');" -->
						<select  name="prnt_arm" id="prnt_arm"
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
							<c:forEach var="item" items="${getParentArmList}" varStatus="num">
												<label for="one" class="quali_subjectlist"> <input
													type="checkbox" name="multisub_prnt_arm" value="${item[0]}" />${item[1]}
												</label>
											</c:forEach>
							<c:forEach var="item" items="${getParentArmList}" varStatus="num">
								<label for="one" class="quali_subjectlist"> <input
									type="checkbox" name="multisub_prnt_arm" value="${item[0]}"
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
				</div> --%>
				
			<%-- 	<div class="col-md-3" >
				<div class="col-md-4">
					<label class=" form-control-label">Regiment:</label>
						</div>
						<div class="col-md-8" >


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
					<div id="checkboxes_reg" class="checkboxes ${not empty selectedRegsList}"
						style="max-height: 200px; overflow: scroll; overflow-x: hidden;">

						<div></div>

					</div>
				</div>
				</div> --%>
				<div class="col-md-3" >
				<div class="col-md-4">
					<label class=" form-control-label">Designation:</label>
					
				</div>
							<div class="col-md-8" >

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
						class="checkboxes ${not empty selectedGetTypeofRankList }"
						style="max-height: 200px; overflow: auto;">

						<div>
						<input type="text" id="searchBox_rank" name="searchBox_rank"
						class="form-control autocomplete custom_search" autocomplete="off" placeholder="Search Rank"  oninput="filterOptions('searchBox_rank','.quali_subjectlist_rank')"
						maxlength="40">
							<c:forEach var="item" items="${getDesignationList}"
								varStatus="num">
								<label for="one" class="quali_subjectlist_rank"> <input onclick="addrnk();"
									type="checkbox"  class="rnk" name="multisub_rank" value="${item[0]}"
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
<div  class="row" >

	<div class="col-lg-3 col-md-6 col-sm-6 col-12" id="sub_list" style="display:none;">
		<div class="row form-group">
			<div class="col-md-4 col-12">
				<label class=" form-control-label"><strong
					style="color: black;"></strong>Selected Comd</label>
			</div>
			<div class="col-12 col-md-8">
				<div id="quali_sub_list"
					style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;  margin: auto;">
				</div>
			</div>
		</div>
	</div>
	<div class="col-lg-3 col-md-6 col-sm-6 col-12" id="sub_list_corps" style="display:none;">
		<div class="row form-group">
			<div class="col-md-4 col-12">
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
	<div class="col-lg-3 col-md-6 col-sm-6 col-12" id="sub_list_div" style="display:none;">
		<div class="row form-group">
			<div class="col-md-4 col-12">
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
<div class="col-lg-3 col-md-6 col-sm-6 col-12" id="sub_list_bde" style="display:none;">
		<div class="row form-group">
			<div class="col-md-4 col-12">
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
<!-- </div> -->
<!-- </div> -->




<!-- <div  class="col-md-12" > -->

<!-- 	<div class="col-md-3 col-12" id="sub_list_prntarm" style="display:none;"> -->
<!-- 		<div class="row form-group"> -->
<!-- 			<div class="col-md-4 col-12"> -->
<!-- 				<label class=" form-control-label"><strong -->
<!-- 					style="color: black;"></strong>Selected Parent Arm</label> -->
<!-- 			</div> -->
<!-- 			<div class="col-12 col-md-8"> -->
<!-- 				<div id="quali_sub_list_prntarm" -->
<!-- 					style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;"> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- 	<div class="col-md-3 col-12" id="sub_list_reg" style="display:none;">  -->
<!-- 		<div class="row form-group"> -->
<!-- 			<div class="col-md-4 col-12"> -->
<!-- 				<label class=" form-control-label"><strong -->
<!-- 					style="color: black;"></strong>Selected Regiment</label> -->
<!-- 			</div> -->
<!-- 			<div class="col-12 col-md-8"> -->
<!-- 				<div id="quali_sub_list_reg" -->
<!-- 					style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;"> -->
<!-- 				</div> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
	<div class="col-md-3 col-12"id="sub_list_rnk" style="display:none;">
		<div class="row form-group">
			<div class="col-md-4 col-12">
				<label class=" form-control-label"><strong
					style="color: black;"></strong>Selected Designation</label>
			</div>
			<div class="col-12 col-md-8">
				<div id="quali_sub_list_rnk"
					style="max-height: 200px; overflow: auto; width: 100%; border: 1px solid;">
				</div>
			</div>
		</div>
	</div>


</div>
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 

<div class="mt-2" align="center"  id="divSearch" >
<div >
				<button id="civDashboard" onclick="getdashboard('civDashboard');" class="btn btn-success btn-sm">Reset</button>
						<input type="button" class="btn btn-primary btn-sm" id="b1"
						onclick=" getpsg_dashlist()" value="Search">
					
				</div>
       
</div>
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;


<!-- <!-- ---------- --> 
<!-- <div class="col-md-12"> -->

<!-- 		<div class="col-lg-12 col-xl-12 divide_border"> -->
<!-- 			<div> -->
<!-- 				<h2 style="color: white; background-color: #fba200;">GAZETTED vs NON GAZETTED</h2> -->
<!-- 				<div id="gazeDiv" class="chartsize" -->
<!-- 					style="width: 100%; height: 450px;"></div> -->
<!-- 			</div> -->

<!-- 		</div> -->
<!-- </div> -->
<div class="col-md-12">

		<div class="col-lg-12 col-xl-12 ">
			<div>
				<h2 style="color: white; background-color: #e50461;">CLASSIFICATION  vs  GENDER</h2>
				<div id="regularDiv" class="chartsize"
					style="width: 100%; height: 450px;"></div>
			</div>

		</div>
</div>

<div class="col-md-12">

		<div class="col-lg-12 col-xl-12 ">
			<div>
				<h2 style="color: white; background-color: #ff7304f5;">CLASSIFICATION vs GROUP</h2>
				<div id="groupDiv" class="chartsize"
					style="width: 100%; height: 450px;"></div>
			</div>

		</div>
</div>

<div class="col-md-12">

		<div class="col-lg-12 col-xl-12 ">
			<div>
				<h2 style="color: white; background-color: #fba200;">CLASSIFICATION vs COMD</h2>
				<div id="cmdDiv" class="chartsize"
					style="width: 100%; height: 450px;"></div>
			</div>

		</div>
</div>

</div>
<!-- --------- -->



<c:url value="civ_dashboard_datalist" var="backUrl" />
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

<input type="hidden" id="multiSelect_count_corps"
	name="multiSelect_count_corps" value="0">
<input type="hidden" id="multiSelect_count_div"
	name="multiSelect_count_div" value="0">
<input type="hidden" id="multiSelect_count_bde"
	name="multiSelect_count_bde" value="0">
	<input type="hidden" id="multiSelect_count_reg"
	name="multiSelect_count_reg" value="0">
	

					
<script type="text/javascript">

window.addEventListener("load", function () {
	   

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
	
	
	  

	
// 	 if('${listunit}' != "" && '${listoff}' != ""  && '${listformfilledoff}' != ""  && '${listunitformfilledunit}' != ""  ){
// 		   $("#count1").text('${listoff[0][0]}');
// 		   $("#count2").text('${listunit[0][0]}');
// 		   $("#count3").text('${listformfilledoff[0][0]}');
// 		   $("#count4").text('${listunitformfilledunit[0][0]}');
// 		 }
// 	else
// 		{
// 		 count_no_off();
// 		}
			
// 	var selectedGetCommandList = '${selectedGetCommandList}';
// 	console.log("selectedGetCommandList"+selectedGetCommandList);

// 	var checkparentarm = '${selectedGetParentArmList}';
// 	console.log("checkparentarm"+checkparentarm);
	
// 	var selectedUnit_name = '${selectedUnit_name}';
// 	if(selectedUnit_name != "" && selectedUnit_name != null)
// 		{
		
// 		document.getElementById("unit_name").value = selectedUnit_name;
// 		}
	
// 	if(checkparentarm != "" && checkparentarm != null && checkparentarm != "[]")
// 	  {
		
// 		var selectedRegsList = JSON.parse(JSON.stringify('${selectedStringRegsList}'));
// 		console.log("selectedRegsList"+selectedRegsList);
// 		var selectedGetParentArmList = JSON.parse(JSON.stringify('${selectedStringGetParentArmList}'));
// 		console.log("selectedGetParentArmList"+selectedGetParentArmList);

// 		 var selectedGetParentArmList = JSON.parse(selectedGetParentArmList);
// 		if(selectedRegsList != "")
// 		  {
	
// 		  console.log("in parent arm");
// 	  for (var i = 0; i < selectedGetParentArmList.length; i++) {
// 		  console.log("in for");
		 
// 		  getReg(selectedGetParentArmList[i],selectedRegsList);
// 	  }
// 		  }
// 		else
			
// 			{
// 			 for (var i = 0; i < selectedGetParentArmList.length; i++) {
				 
// 				  getReg(selectedGetParentArmList[i]);
// 			  }
// 			}
// 	  }
	

	
//  if (selectedGetCommandList != "" && selectedGetCommandList != null) {
// 	  console.log("document ready ++");
	  
	 
// 	  var selectedCorpsList = JSON.parse('${selectedCorpsList}');
// 	  var selectedDivsList = JSON.parse('${selectedDivsList}');
// 	  var selectedBdesList = JSON.parse('${selectedBdesList}');
// 	  console.log("selectedCorpsList "+selectedCorpsList+" selectedDivsList "+selectedDivsList+" selectedBdesList "+selectedBdesList);
// 	  if(selectedCorpsList != "")
// 	  {
// 		  console.log("in corps");
// 	  for (var i = 0; i < selectedGetCommandList.length; i++) {
		 
// 	    getCONTCorps(selectedGetCommandList[i],selectedCorpsList);
// 	  }
	  
// 	  }
// 	  else
// 		  {
// 		  for (var i = 0; i < selectedGetCommandList.length; i++) {
				 
// 			    getCONTCorps(selectedGetCommandList[i]);
// 			  }
// 		  }
// 	  if(selectedDivsList != "")
// 	  {
// 	 	 console.log("in div");
	  
// 	  for (var i = 0; i < selectedCorpsList.length; i++) {
	 		 
// 	 	    getCONTDiv(selectedCorpsList[i],selectedDivsList);
	 	  	    
// 	 	  }
	 
// 	  }
// 	  else
// 		  {
// 		  for (var i = 0; i < selectedCorpsList.length; i++) {
		 		 
// 		 	    getCONTDiv(selectedCorpsList[i]);
		 	  	    
// 		 	  }
// 		  }
// 	  if(selectedBdesList != "")
// 	  {
// 	 	 console.log("in bde");
	  
// 	  for (var i = 0; i < selectedDivsList.length; i++) {
	 		 
// 	 	    getCONTBde(selectedDivsList[i],selectedBdesList);
	 	  	    
// 	 	  }
// 	  }
// 	  else
// 		  {
// 		  for (var i = 0; i < selectedDivsList.length; i++) {
		 		 
// 		 	    getCONTBde(selectedDivsList[i]);
		 	  	    
// 		 	  }
	  	    
// 		  }
	 
	 

	  
	  
// 	}
 
 
 
	
		

 
// 	var select = '<option value="' + "0" + '">'
// 			+ "--Select--" + '</option>';
// 	//$('select#cont_comd').change(function() {
		
// 		$("input[type='checkbox'][name='multisub_cmd']").click(function() {
// 		var fcode = this.value;
		
// 		if (fcode == "0") {
// 			$("select#cont_corps").html(select);
// 			$("select#cont_div").html(select);
// 			$("select#cont_bde").html(select);
// 		} else {
// 			$("select#cont_corps").html(select);
// 			$("select#cont_div").html(select);
// 			$("select#cont_bde").html(select);

// 			$("#hd_cmd_sus").val(fcode);

// 			getCONTCorps(fcode);

// 			fcode += "00";
// 			getCONTDiv(fcode);

// 			fcode += "000";
// 			getCONTBde(fcode);
// 		}
// 	});
		
		
		
		//new added
		
// 		$("input[type='checkbox'][name='multisub_prnt_arm']").click(function() {
// 	var fcode = this.value;
// 	getReg(fcode);
// 	$('#chk_box').hide();
// });
		
// 	/* 	function getReg(fcode,data)
// 		{
			
// 			console.log("in arm change selectedRegsList value : "+data);
// 			console.log("fcode : "+fcode);
// 			//console.log("data : "+data);
// 			if (fcode == "0") {
// 				//$("select#prnt_arm").html(select);
// 			} else {
				
			
// 			//	$("select#prnt_arm").html(select);

// 				var options = '<option value="0">' + "--Select--" + '</option>';
// 				//var arm_code = this.value;

// 				$.post("getRegimentFromArmCode?" + key + "=" + value, {
// 					arm_code: fcode
// 				}, function(j) {
// 					//alert(j);
// 					if (j != "") {
// 						var length = j.length - 1;
// 						var enc = j[length][0].substring(0, 16);
// 						var options = '';

// 						 for (var i = 0; i < length; i++) {
							 
// 							 console.log("in i");
// 							//alert(j[i][1]);
// 							/* options += '<label for="one" class="regiment"><input type="checkbox" class="reg_' + parseInt(i + 1) + '" name="multisub_reg_' + parseInt(i + 1) + '" value="' + j[i][0] + '" /> ' + j[i][1] + ' </label> '; */
							
// 							//new
// 							var typeOfTemp = typeof selectedRegsList;
// 			console.log("typeOfTemp"+typeOfTemp);
// 			//console.log("typeOfTemp"+typeOfTemp);
// 						 if (typeof data != 'undefined' ) {
// 							 var selectedRegsList = JSON.parse(data);
						 
// 							 console.log("in undef");
						 
			

// 							 console.log("selectedRegsList"+selectedRegsList);
// 							 console.log("selectedRegsList.length"+selectedRegsList.length);
						 
// 		                console.log("selectedRegsList.length "+selectedRegsList.length)
// 		            	for (let k = 0; k < selectedRegsList.length; k++) {
// 		                    console.log("selectedRegsList[k]  "+selectedRegsList[k]+"    j[i][0]  "+j[i][0]);
// 		                	if (selectedRegsList[k] == j[i][0]) {
// 		                		options += '<label for="one" class="regiment"><input checked type="checkbox" class="reg_' + parseInt(i + 1) + '" name="multisub_reg_' + parseInt(i + 1) + '" value="' + j[i][0] + '" /> ' + j[i][1] + ' </label> ';
// 		                        k = selectedRegsList.length + 1;
		                       
// 		                    } else if (k == selectedRegsList.length-1) {
// 		                    	options += '<label for="one" class="regiment"><input type="checkbox" class="reg_' + parseInt(i + 1) + '" name="multisub_reg_' + parseInt(i + 1) + '" value="' + j[i][0] + '" /> ' + j[i][1] + ' </label> ';
		                       
// 		                    }
// 		                }
// 		            } else {
// 		            	$("#multiSelect_count_reg").val(fcode);
		            
// 		            	options += '<label for="one" class="regiment"><input type="checkbox" class="reg_' + parseInt(i + 1) + '" name="multisub_reg_' + parseInt(i + 1) + '" value="' + j[i][0] + '" /> ' + j[i][1] + ' </label> ';
// 		            }
// 						//upto
							
							
// 						} 
// 					}

// 					$("div#checkboxes_reg").html(options);
// 				});
// 			}
// 		} 
	
		
	/* $('select#cont_div').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {

			$("select#cont_bde").html(select);
			$("#hd_div_sus").val(fcode);
			getCONTBde(fcode);
		}
	});
 */
	/* $('select#cont_bde').change(function() {
		var fcode = this.value;
		if (fcode == "0") {
			$("select#cont_bde").html(select);
		} else {
			$("#hd_bde_sus").val(fcode);
		}
	}); */
	
	
	
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
						console.log("in unit name change");
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

	});
			


	
	
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
	 showselected();
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

	
	
	
	
	
	
	
	
	
	
	
	
// 	function showCheckboxes_cont_div() {
// 		var checkboxes = document.getElementById("checkboxes_cont_div");
// 		$("#checkboxes_cont_div").toggle();
// 		//$("#user_role_id").val('');
// 		$('.cont_div').each(function() {
// 			$(this).show()
// 		})
// 	}

// 	function showCheckboxes_cont_bde() {
// 		var checkboxes = document.getElementById("checkboxes_cont_bde");
// 		$("#checkboxes_cont_bde").toggle();
// 		//$("#user_role_id").val('');
// 		$('.cont_bde').each(function() {
// 			$(this).show()
// 		})
// 	}
	
// 	function showCheckboxes_cont_reg() {
// 		var checkboxes = document.getElementById("checkboxes_reg");
// 		$("#checkboxes_reg").toggle();
// 		//$("#user_role_id").val('');
// 		$('.cont_reg').each(function() {
// 			$(this).show()
// 		})
// 	}

// 	function showCheckboxes_rank() {
// 		var checkboxes = document.getElementById("checkboxes_rank");
// 		$("#checkboxes_rank").toggle();
// 		//$("#user_role_id").val('');
// 		$('.rank').each(function() {
// 			$(this).show()
// 		})
// 	}
// 	function showCheckboxes_prnt_arm() {
// 		var checkboxes = document.getElementById("checkboxes_prnt_arm");
// 		$("#checkboxes_prnt_arm").toggle();
// 		//$("#user_role_id").val('');
// 		$('.prnt_arm').each(function() {
// 			$(this).show()
// 		})
// 	}
// 	function showCheckboxes_user_arm() {
// 		var checkboxes = document.getElementById("checkboxes_user_arm");
// 		$("#checkboxes_user_arm").toggle();
// 		//$("#user_role_id").val('');
// 		$('.user_arm').each(function() {
// 			$(this).show()
// 		})
// 	}	
	/* 
function getCONTCorps(fcode,selectedCorpsList) {
	var fcode1 = fcode[0];
	
	$.post("getCorpsDetailsList?" + key + "=" + value, {
		fcode : fcode1
	}, function(j) {
		
		if (j != "") {
		    var length = j.length - 1;
		    var enc = j[length][0].substring(0, 16);
		    var options = '';

		    for (var i = 0; i < length; i++) {
		        if ('${corp_sus}' === dec(enc, j[i][0])) {
		            options += '<option value="' + dec(enc, j[i][0]) +
		                '" name="' + dec(enc, j[i][1]) +
		                '" selected="selected">' + dec(enc, j[i][1]) +
		                '</option>';
		                console.log("in sus");
		        } else {
		        	console.log("not in sus");
		            if (typeof selectedCorpsList != 'undefined') {
		                console.log("selectedCorpsList.length "+selectedCorpsList.length)
		            	for (let k = 0; k < selectedCorpsList.length; k++) {
		                    console.log("selectedCorpsList[k]  "+selectedCorpsList[k]+"    dec(enc, j[i][0])  "+dec(enc, j[i][0]))
		                	if (selectedCorpsList[k] == dec(enc, j[i][0])) {
		                        options += '<label for="one" class="quali_subjectlist"><input checked type="checkbox" class="multisub_' + parseInt(i + 1) + '" name="multisub_corps_' + parseInt(i + 1) + '" value="' + dec(enc, j[i][0]) + '" /> ' + dec(enc, j[i][1]) + ' </label> ';
		                        k = selectedCorpsList.length + 1;
		                       
		                    } else if (k == selectedCorpsList.length-1) {
		                        options += '<label for="one" class="quali_subjectlist"><input type="checkbox" class="multisub_' + parseInt(i + 1) + '" name="multisub_corps_' + parseInt(i + 1) + '" value="' + dec(enc, j[i][0]) + '" /> ' + dec(enc, j[i][1]) + ' </label> ';
		                       
		                    }
		                }
		            } else {
		                options += '<label for="one" class="quali_subjectlist"><input type="checkbox" class="multisub_' + parseInt(i + 1) + '" name="multisub_corps_' + parseInt(i + 1) + '" value="' + dec(enc, j[i][0]) + '" /> ' + dec(enc, j[i][1]) + ' </label> ';
		            }
		        }
		    }

		    $("div#checkboxes_cont_corps").html(options);

		    // Additional code

		   /*  if (typeof selectedCorpsList !== 'undefined') {
		        for (let j = 1; j <= length; j++) {
		            var checkboxElement = document.getElementById("multisub_corps_" + j);

		            if (checkboxElement) { // Check if the element exists
		                var checkboxValue = checkboxElement.value;
		                var isChecked = false;

		                for (let k = 0; k < selectedCorpsList.length; k++) {
		                    if (selectedCorpsList[k] === checkboxValue) {
		                        isChecked = true;
		                        break; // Exit the loop if a match is found
		                    }
		                }

		                checkboxElement.checked = isChecked; // Check/uncheck the checkbox based on the condition
		            }
		        }
		    }
		}*/
// 		for (var i = 0; i < length; i++) {
			
			
// 		$("input[type='checkbox'][name='multisub_corps_"+parseInt(i+1)+"']").click(function() {
	
	
// 			var corr = this.value;
			
		
// 			//$('select#cont_corps').change(function() {
// 				var select = '<option value="' + "0" + '">'
// 			+ "--Select--" + '</option>';
// 				var fcode = this.value;
// 			if (fcode == "0") {
// 				$("select#cont_div").html(select);
// 				$("select#cont_bde").html(select);
// 			} else {
				
// 				$("input#multiSelect_count_corps").val(corr); 
// 				$("select#cont_div").html(select);
// 				$("select#cont_bde").html(select);
// 				//console.log(fcode);
// 				$("#hd_corp_sus").val(fcode);
// 				console.log("fcode in cont div pass: "+fcode)
// 				getCONTDiv(fcode);
// 				fcode += "000";
// 				getCONTBde(fcode);
				
// 			}
			
// 		});
// 		}
		
// 		/*  var corvar = $('input[name="multisub_corps"]:checkbox:checked').map(function() {
// 				return this.value;
// 			}).get();
// 			var cor = corvar.join(",");
// 			alert("soo-"+cor);  */
// 	});
// }



// function getCONTDiv(fcode,selectedDivsList) {
// 	console.log("fcode in cont div pass edit mode : "+fcode);
// 	console.log("selectedDivsList : "+selectedDivsList);
	
	
// 	//var fcode1 = fcode[0] + fcode[1] + fcode[2];
// 	console.log("fcode by "+fcode)
// 	//alert(fcode1+"sss");
// 	$.post("getDivDetailsList?" + key + "=" + value, {
// 		fcode : fcode
// 	}, function(j) {
// 		//alert(j);
		
// 		if (j != "") {
// 			var length = j.length - 1;
// 			var enc = j[length][0].substring(0, 16);
// 			//var options = '<option value="' + "0" + '">' + "--Select--"
// 					//+ '</option>';
					
// 					 var options = '';
// 			for (var i = 0; i < length; i++) {
// 				if ('${div_sus}' == dec(enc, j[i][0])) {
// 					options += '<option value="' + dec(enc, j[i][0])
// 							+ '" name="' + dec(enc, j[i][1])
// 							+ '" selected=selected >' + dec(enc, j[i][1])
// 							+ '</option>';
// 				} else 
// 				{
// 		        	console.log("not in sus");
// 		            if (typeof selectedDivsList != 'undefined') {
// 		                console.log("selectedDivsList.length "+selectedDivsList.length)
// 		            	for (let k = 0; k < selectedDivsList.length; k++) {
// 		                    console.log("selectedDivsList[k]  "+selectedDivsList[k]+"    dec(enc, j[i][0])  "+dec(enc, j[i][0]))
// 		                	if (selectedDivsList[k] == dec(enc, j[i][0])) {
// 		                		options += '<label for="one" class="quali_subjectlist"><input checked type="checkbox" class="multisub_'+parseInt(i+1)+'" name="multisub_div_'+parseInt(i+1)+'" value="'+ dec(enc, j[i][0])+'" /> '+ dec(enc, j[i][1])+ ' </label> ' ;
// 		                        k = selectedDivsList.length + 1;
		                       
// 		                    } else if (k == selectedDivsList.length-1) {
// 		                    	options += '<label for="one" class="quali_subjectlist"><input type="checkbox" class="multisub_'+parseInt(i+1)+'" name="multisub_div_'+parseInt(i+1)+'" value="'+ dec(enc, j[i][0])+'" /> '+ dec(enc, j[i][1])+ ' </label> ' ;
		                       
// 		                    }
// 		                }
// 		            } else {
// 		            	options += '<label for="one" class="quali_subjectlist"><input type="checkbox" class="multisub_'+parseInt(i+1)+'" name="multisub_div_'+parseInt(i+1)+'" value="'+ dec(enc, j[i][0])+'" /> '+ dec(enc, j[i][1])+ ' </label> ' ;
// 		            }
// 		        }
// 				/* {
// 					//options += '<option value="' + dec(enc, j[i][0])
// 						//	+ '" >' + dec(enc, j[i][1]) + '</option>';
							
							
// 					options += '<label for="one" class="quali_subjectlist"><input type="checkbox" class="multisub_'+parseInt(i+1)+'" name="multisub_div_'+parseInt(i+1)+'" value="'+ dec(enc, j[i][0])+'" /> '+ dec(enc, j[i][1])+ ' </label> ' ;
					
// 				} */
// 			}
			
// 			//$("select#cont_div").html(options);
// 			$("div#checkboxes_cont_div").html(options); 
// 		}
		
// 		for (var i = 0; i < length; i++) {
// 			$("input[type='checkbox'][name='multisub_div_"+parseInt(i+1)+"']").click(function() {
// 				var divv = this.value;
// 					var select = '<option value="' + "0" + '">'
// 				+ "--Select--" + '</option>';
// 					var fcode = this.value;
// 					var fcode = this.value;
// 					if (fcode == "0") {
// 						$("select#cont_bde").html(select);
// 					} else {
// 						$("input#multiSelect_count_div").val(divv); 
// 						$("select#cont_bde").html(select);
// 						$("#hd_div_sus").val(fcode);
// 						getCONTBde(fcode);
// 					}
				
// 			});
// 			}
		
// 	});
// }
// function getCONTBde(fcode,selectedBdesList) {
	

// //	var fcode1 = fcode[0] + fcode[1] + fcode[2] + fcode[3] + fcode[4] + fcode[5];
// 	//alert("sssssssssssssssss0------"+fcode1);
// 	console.log("fcode in bde"+fcode)
// 	$.post("getBdeDetailsList?" + key + "=" + value, {
// 		fcode : fcode
// 	}, function(j) {
// 		//alert("sssssssssjjj----"+j);
	
// 		if (j != "") {
// 			var options = '';
		
// 			var length = j.length - 1;
// 			var enc = j[length][0].substring(0, 16);
// 			//var options = '<option value="' + "0" + '">' + "--Select--"
// 				//	+ '</option>';
// 			//jQuery("select#cont_bde").html(options);
// 			for (var i = 0; i < length; i++) {
// 				if ('${bde_sus}' == dec(enc, j[i][0])) {
// 					options += '<option value="' + dec(enc, j[i][0])
// 							+ '" name="' + dec(enc, j[i][1])
// 							+ '" selected=selected>' + dec(enc, j[i][1])
// 							+ '</option>';
// 					$("#cont_bname").val(dec(enc, j[i][1]));
// 				} else 
// 				{
// 		        	console.log("not in sus");
// 		            if (typeof selectedBdesList != 'undefined') {
// 		                console.log("selectedBdesList.length "+selectedBdesList.length)
// 		            	for (let k = 0; k < selectedBdesList.length; k++) {
// 		                    console.log("selectedBdesList[k]  "+selectedBdesList[k]+"    dec(enc, j[i][0])  "+dec(enc, j[i][0]))
// 		                	if (selectedBdesList[k] == dec(enc, j[i][0])) {
// 		                		options += '<label for="one" class="quali_subjectlist"><input checked type="checkbox" class="multisub_'+parseInt(i+1)+'" name="multisub_bde_'+parseInt(i+1)+'" value="'+ dec(enc, j[i][0])+'" /> '+ dec(enc, j[i][1])+ ' </label> ' ;
		                       
// 		                    } else if (k == selectedBdesList.length-1) {
// 		                    	options += '<label for="one" class="quali_subjectlist"><input type="checkbox" class="multisub_'+parseInt(i+1)+'" name="multisub_bde_'+parseInt(i+1)+'" value="'+ dec(enc, j[i][0])+'" /> '+ dec(enc, j[i][1])+ ' </label> ' ;
		                       
// 		                    }
// 		                }
// 		            } else {
// 		            	options += '<label for="one" class="quali_subjectlist"><input type="checkbox" class="multisub_'+parseInt(i+1)+'" name="multisub_bde_'+parseInt(i+1)+'" value="'+ dec(enc, j[i][0])+'" /> '+ dec(enc, j[i][1])+ ' </label> ' ;
// 		            }
// 		        }
// 				/* {
					
							
// 					options += '<label for="one" class="quali_subjectlist"><input type="checkbox" class="multisub_'+parseInt(i+1)+'" name="multisub_bde_'+parseInt(i+1)+'" value="'+ dec(enc, j[i][0])+'" /> '+ dec(enc, j[i][1])+ ' </label> ' ;
					
// 				} */
// 			}
			
// 			//$("select#cont_bde").html(options);
// 			$("div#checkboxes_cont_bde").html(options);
// 		}
		

		
// 		for (var i = 0; i < length; i++) {
// 			$("input[type='checkbox'][name='multisub_bde_"+parseInt(i+1)+"']").click(function() {
// 				var bdee = this.value;
// 					var select = '<option value="' + "0" + '">'
// 				+ "--Select--" + '</option>';
// 					var fcode = this.value;
// 					var fcode = this.value;
// 					if (fcode == "0") {
// 						$("select#cont_bde").html(select);
// 					} else {
// 						$("#multiSelect_count_bde").val(bdee);
// 						$("#hd_bde_sus").val(fcode);
						
// 					}
				
// 			});
// 			}
// 	});
// }	 
			
			</script>


<script type="text/javascript">


</script>
<script type="text/javascript">
// 		function counter(id, start, end, duration) {
// 			  let obj = document.getElementById(id),
// 			   current = start,
// 			   range = end - start,
// 			   increment = end > start ? 1 : -1,
// 			   step = Math.abs(Math.floor(duration / range)),
// 			   timer = setInterval(() => {
				   
// 				   current += increment;
// 				    obj.textContent = current;
// 				    if (current == end) {
// 				     clearInterval(timer);
// 				    }
// 				   }, step);
// 				 }
		
// 		function count_no_off() {
// 			$.post('Getcount_no_offData?' + key + "=" + value, {
// 			}, function(i) {
// 				if(i.length > 0) {
// 				$('#count1').text(i[0].total);
// 				}
// 			});
//    $.post('Getcount_no_unitData?' + key + "=" + value, {
// 			}, function(i) {
// 				if(i.length > 0) {
// 					$('#count2').text(i[0].unit);
					
// 				}
// 			});
// 				 $.post('Getcount_no_censusFormFilled?' + key + "=" + value, {
// 					}, function(i) {
// 						if(i.length > 0) {
// 							$('#count3').text(i[0].no_of_offrs_census_filled);
							
// 						}
// 					});
// 						 $.post('Getcount_no_censusFormFilled_unit?' + key + "=" + value, {
// 							}, function(i) {
// 								if(i.length > 0) {
// 									$('#count4').text(i[0].no_of_units_census_filled);
								
// 								}
				
				
				
// 			});
//    counterLoad();
// 		} 
		
		
		
		
		
		
// 		function counterLoad(){
// 			$('.count').each(function () {
// 			    $(this).prop('Counter',0).animate({
// 			        Counter: $(this).text()
// 			    }, {
// 			        duration: 4000,
// 			        easing: 'swing',
// 			        step: function (now) {
// 			            $(this).text(Math.ceil(now));
// 			        }
// 			    });
// 			});
// 		}
		
		//rk vs state/////////////////////////////////////////////////////////////////////////////////
		
		function getpsg_dashlist(){
			$("#WaitLoader").show();
		
			//var a = $('input:radio[name=isgazetted1]:checked').val();
			findselectedcmd();
			findselectedcorps();
			findselecteddiv();
			findselectedbde();
			var cmd=$("#CheckVal").val();
			var check_list_corps=$("#CheckValcorps").val();
			var check_list_div=$("#CheckValdiv").val();
			var check_list_bde=$("#CheckValbde").val();

// 			 var cmdvar = $('input[name="multisub_cmd"]:checkbox:checked').map(function() {
// 		return this.value;
// 	}).get();
// 	var cmd = cmdvar.join(",");
	//alert("s-"+cmd); 
	var parent_arm = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
		return this.value;
	}).get();
	var prntarm = parent_arm.join(",");
	var unit_name = $('#unit_name').val();	
		
	
	var corps = $('#multiSelect_count_corps').val();
	console.log("corps "+corps)
	//alert("corps"+corps); 
	var divi = $('#multiSelect_count_div').val();	
	//alert("dddd-"+divi); 
	var bd = $('#multiSelect_count_bde').val();	
	//alert("bbb-"+bd); 
	var reg = $('#multiSelect_count_reg').val();	
	//alert("reg"+reg);
	
	var unit_name = $('#unit_name').val();	
// 	var check_list_c = "";
// 	for(var i = 1; i <= corps; i++){
// 		if ($('input[name="multisub_corps_'+i+'"]:checked').is(':checked')){
// 			check_list_c += $('input[name="multisub_corps_'+i+'"]:checked').val() +",";
// 			var check_list_corps = check_list_c.substring(0, check_list_c.length-1);
// 			//alert("sanaaa"+check_list_corps);
// 			//$("#multiSelect_checkboxes").val(check_list);
// 		}

		
// 	}
	
// 	var check_list_d = "";
// 	for(var i = 1; i <= divi; i++){
// 		if ($('input[name="multisub_div_'+i+'"]:checked').is(':checked')){
// 			check_list_d += $('input[name="multisub_div_'+i+'"]:checked').val() +",";
// 			var check_list_div = check_list_d.substring(0, check_list_d.length-1);
// 			//alert("dd-"+check_list_d);
// 			//$("#multiSelect_checkboxes").val(check_list);
// 		}

		
// 	}
	
	
// 	var check_list_bd = "";
// 	for(var i = 1; i <= bd; i++){
// 		if ($('input[name="multisub_bde_'+i+'"]:checked').is(':checked')){
// 			check_list_bd += $('input[name="multisub_bde_'+i+'"]:checked').val() +",";
// 			var check_list_bde = check_list_bd.substring(0, check_list_bd.length-1);
// 			//alert("bd-"+check_list_bd);
// 			//$("#multiSelect_checkboxes").val(check_list);
// 		}
		
// 	}
	
	var check_list_reg = "";
// 	for(var i = 1; i <= reg; i++){
// 		if ($('input[name="multisub_reg_'+i+'"]:checked').is(':checked')){
// 			check_list_reg += $('input[name="multisub_reg_'+i+'"]:checked').val() +",";
// 			var check_list_reg = check_list_reg.substring(0, check_list_reg.length-1);
// 			//alert("bd-"+check_list_bd);
// 			//$("#multiSelect_checkboxes").val(check_list);
// 		}
		
// 	}
	
	
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
		
		
		var cmdvar = $('input[name="multisub_cmd"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var cmd = cmdvar.join(",");
		
		
		
		var divvar = $('input[name="multisub_divs"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var div = divvar.join(",");
		
		for(var i = 1; i <= corps; i++){

		var corsvar = $('input[name="multisub_corps_'+i+'"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var corps = corsvar.join(",");
		}

		var bdesvar = $('input[name="multisub_bdes"]:checkbox:checked').map(function() {
			return this.value;
		}).get();
		var bdes = bdesvar.join(",");
		
		
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
			console.log("check_list_reg"+check_list_reg)
			$("#regs1").val(check_list_reg);
			$("#unit1").val(document.getElementById("unit_name").value);
			$("#parent_arm1").val(parent_arm);
			$("#unit_name1").val(unit_name);
			$("#unit_view").val("NO");
			
			//$("#nrWaitLoader").show();
			$("#searchForm").submit();
			
			
		}
	

</script>
<script type="text/javascript">

	
		am5.ready(function() {
			
			
// 			//gaze vc gender  
			var root = am5.Root.new("regularDiv");


			// Set themes
			// https://www.amcharts.com/docs/v5/concepts/themes/
			root.setThemes([
			  am5themes_Animated.new(root)
			]);


			// Create chart
			// https://www.amcharts.com/docs/v5/charts/xy-chart/
			var chart = root.container.children.push(am5xy.XYChart.new(root, {
			  panX: false,
			  panY: false,
			  wheelX: "panX",
			  wheelY: "zoomX",
			  layout: root.verticalLayout
			}));
			console.log(chart);
			
	 		chart.set("scrollbarX", am5.Scrollbar.new(root, {
	 		  orientation: "horizontal"
			}));


		
			var data =${Getrk_gen_regular};
			//alert(data);

			var xRenderer = am5xy.AxisRendererX.new(root, {});
			var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
			  categoryField: "datacivgen",
			  renderer: xRenderer,
			  tooltip: am5.Tooltip.new(root, {})
			}));

			xAxis.data.setAll(data);

			var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
			  min: 0,
			  renderer: am5xy.AxisRendererY.new(root, {})
			}));


			// Add legend
			// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
			var legend = chart.children.push(am5.Legend.new(root, {
			  centerX: am5.p50,
			  x: am5.p50
			}));


			// Add series
			// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
			function makeSeries1(name, fieldName, total1) {
				
				 var name = name.toUpperCase();
			  var series = chart.series.push(am5xy.ColumnSeries.new(root, {
			    name: name,
			    stacked: true,
			    xAxis: xAxis,
			    yAxis: yAxis,
			    valueYField: fieldName,
			    categoryXField: "datacivgen"
			  }));

			  series.columns.template.setAll({
			    tooltipText: "{name}, {categoryX}: {valueY}",
			    tooltipY: am5.percent(10)
			  });
			  series.data.setAll(data);

			  // Make stuff animate on load
			  // https://www.amcharts.com/docs/v5/concepts/animations/
			  series.appear();

			  series.bullets.push(function () {
			    return am5.Bullet.new(root, {
			      sprite: am5.Label.new(root, {
			       // text: "{valueY}",
			        fill: root.interfaceColors.get("alternativeText"),
			        centerY: am5.p50,
			        centerX: am5.p50,
			        //populateText: true
			      })
			    });
			  });

			  legend.data.push(series);
			  
			  
			  if (total1) {
				    series.bullets.push(function () {
				      var total1Label = am5.Label.new(root, {
				        text: "{valueY}",
				        fill: root.interfaceColors.get("text"),
				        centerY: am5.p100,
				        centerX: am5.p50,
				        populateText: true,
				        textAlign: "center"
				      });
				      
				      total1Label.adapters.add("text", function(text, target) {
				        var dataContext = target.dataItem.dataContext;
				        var val = Math.abs(dataContext.datacivgen1 + dataContext.datacivgen2);
				        return ""+val;
				      });
				      
				      return am5.Bullet.new(root, {
				        locationX: 0.5,
				        locationY: 1,
				        sprite: total1Label
				      });
				    });
				  }
			}

			makeSeries1("Male", "datacivgen1",false);
			makeSeries1("Female", "datacivgen2",true);
			

			
// 			//rk vs med cat
	
			 var genroot = am5.Root.new("groupDiv");


				// Set themes
				// https://www.amcharts.com/docs/v5/concepts/themes/
				genroot.setThemes([
				  am5themes_Animated.new(genroot)
				]);


				// Create chart
				// https://www.amcharts.com/docs/v5/charts/xy-chart/
				var chartgen = genroot.container.children.push(am5xy.XYChart.new(genroot, {
				  panX: false,
				  panY: false,
				  wheelX: "panX",
				  wheelY: "zoomX",
				  layout: genroot.verticalLayout
				}));
				console.log(chartgen);
				
		 		chartgen.set("scrollbarY", am5.Scrollbar.new(genroot, {
		 		  orientation: "vertical"
				}));



				var data =${Getrk_gazelist};

		

				var yRenderer = am5xy.AxisRendererY.new(genroot, {});
				var yAxis = chartgen.yAxes.push(am5xy.CategoryAxis.new(genroot, {
				  categoryField: "datagrp",
				  renderer: yRenderer,
				  tooltip: am5.Tooltip.new(root, {})
				}));
				
				
				
				
				yAxis.data.setAll(data);

		
				
				var xAxis = chartgen.xAxes.push(am5xy.ValueAxis.new(genroot, {
					  min: 0,
					  renderer: am5xy.AxisRendererX.new(genroot, {
					    //strokeOpacity: 0.1
					  })
					}));
				

				// Add legend
				// https://www.amchartgens.com/docs/v5/chartgens/xy-chartgen/legend-xy-series/
				var legend = chartgen.children.push(am5.Legend.new(genroot, {
				  centerX: am5.p50,
				  x: am5.p50
				}));


				// Add series
				// https://www.amchartgens.com/docs/v5/chartgens/xy-chartgen/series/
				function makeSeriesgen(name, fieldName, total1) {
					 var name = name.toUpperCase();
			
				  var series = chartgen.series.push(am5xy.ColumnSeries.new(genroot, {
				    name: name,
				    stacked: true,
				    xAxis: xAxis,
				    yAxis: yAxis,
				    valueXField: fieldName,
				    categoryYField: "datagrp"
				  }));

				  series.columns.template.setAll({
				    tooltipText: "{name}, {categoryY}: {valueX}",
				    tooltipY: am5.percent(10)
				  });
				  series.data.setAll(data);

				  // Make stuff animate on load
				  // https://www.amchartgens.com/docs/v5/concepts/animations/
				  series.appear();

				  series.bullets.push(function () {
				    return am5.Bullet.new(genroot, {
				      sprite: am5.Label.new(genroot, {
				       // text: "{valueY}",
				        fill: genroot.interfaceColors.get("alternativeText"),
				        centerY: am5.p50,
				        centerX: am5.p50,
				        //populateText: true
				      })
				    });
				  });

				  legend.data.push(series);
				  
				  
				  if (total1) {
					    series.bullets.push(function () {
					      var total1Label = am5.Label.new(genroot, {
					        text: "{valueY}",
					        fill: genroot.interfaceColors.get("text"),
					        centerY: am5.p100,
					        centerX: am5.p50,
					        populateText: true,
					        textAlign: "center"
					      });
					      
					      total1Label.adapters.add("text", function(text, target) {
					        var dataContext = target.dataItem.dataContext;
					        var val = Math.abs(dataContext.datagrp1 + dataContext.datagrp2+ dataContext.datagrp3+ dataContext.datagrp4);
					        return ""+val;
					      });
					      
					      return am5.Bullet.new(genroot, {
					        locationX: 1,
					        locationY: 0.5,
					        sprite: total1Label
					      });
					    });
					  }
				}

// 				makeSeriesgen("Male", "datagen1",false);
// 				makeSeriesgen("Female", "datagen2",true);
			makeSeriesgen("A", "datagrp1",false);
			makeSeriesgen("B", "datagrp2",false);
			makeSeriesgen("C", "datagrp3",false);
			makeSeriesgen("D", "datagrp4",true);	
			

			var root_c = am5.Root.new("cmdDiv");


			// Set themes
			// https://www.amcharts.com/docs/v5/concepts/themes/
			root_c.setThemes([
			  am5themes_Animated.new(root_c)
			]);


			// Create chart
			// https://www.amcharts.com/docs/v5/charts/xy-chart/
			var chart = root_c.container.children.push(am5xy.XYChart.new(root_c, {
			  panX: false,
			  panY: false,
			  wheelX: "panX",
			  wheelY: "zoomX",
			  layout: root.verticalLayout
			}));
			console.log(chart);
			
	 		chart.set("scrollbarX", am5.Scrollbar.new(root_c, {
	 		  orientation: "horizontal"
			}));



	 		var data =${Getrk_cmdlist};
			//alert(data);

			var xRenderer = am5xy.AxisRendererX.new(root_c, {});
			var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root_c, {
			  categoryField: "datacivcomd",
			  renderer: xRenderer,
			  tooltip: am5.Tooltip.new(root_c, {})
			}));

			xAxis.data.setAll(data);

			var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root_c, {
			  min: 0,
			  renderer: am5xy.AxisRendererY.new(root_c, {})
			}));


			// Add legend
			// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
			var legend = chart.children.push(am5.Legend.new(root_c, {
			  centerX: am5.p50,
			  x: am5.p50
			}));


			// Add series
			// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
			function makeSeries(name, fieldName, total1) {
				 var name = name.toUpperCase();
		
			  var series = chart.series.push(am5xy.ColumnSeries.new(root_c, {
			    name: name,
			    stacked: true,
			    xAxis: xAxis,
			    yAxis: yAxis,
			    valueYField: fieldName,
			    categoryXField: "datacivcomd"
			  }));

			  series.columns.template.setAll({
			    tooltipText: "{name}, {categoryX}: {valueY}",
			    tooltipY: am5.percent(10)
			  });
			  series.data.setAll(data);

			  // Make stuff animate on load
			  // https://www.amcharts.com/docs/v5/concepts/animations/
			  series.appear();

			  series.bullets.push(function () {
			    return am5.Bullet.new(root_c, {
			      sprite: am5.Label.new(root_c, {
			       // text: "{valueY}",
			        fill: root_c.interfaceColors.get("alternativeText"),
			        centerY: am5.p50,
			        centerX: am5.p50,
			        //populateText: true
			      })
			    });
			  });

			  legend.data.push(series);
			  
			  
			  if (total1) {
				    series.bullets.push(function () {
				      var total1Label = am5.Label.new(root_c, {
				        text: "{valueY}",
				        fill: root.interfaceColors.get("text"),
				        centerY: am5.p100,
				        centerX: am5.p50,
				        populateText: true,
				        textAlign: "center"
				      });
				      
				      total1Label.adapters.add("text", function(text, target) {
				        var dataContext = target.dataItem.dataContext;
				        var val = Math.abs(dataContext.datacivcomd1 + dataContext.datacivcomd2 );
				        return ""+val;
				      });
				      
				      return am5.Bullet.new(root_c, {
				        locationX: 0.5,
				        locationY: 1,
				        sprite: total1Label
				      });
				    });
				  }
			}

	
			makeSeries("GAZETTED", "datacivcomd1",false);
			makeSeries("NON_GAZETTED", "datacivcomd2",true);	

			
		});			 
				
	</script>

<script>
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

function findselectedcmd(){
	var nrSel = $(".cmdCheckBox:checkbox:checked").map(function() {
		return $(this).attr('value');
	}).get();

	var b = nrSel.join(',');
	$("#CheckVal").val(b);
	
}
function findselectedcorps(){
	var nrSel = $(".corpsCheckBox:checkbox:checked").map(function() {
		return $(this).attr('value');
	}).get();
	var b = nrSel.join(',');
	$("#CheckValcorps").val(b);
}
function findselecteddiv(){
	var nrSel = $(".divCheckBox:checkbox:checked").map(function() {
		return $(this).attr('value');
	}).get();
	var b = nrSel.join(',');
	$("#CheckValdiv").val(b);		
}
function findselectedbde(){
	var nrSel = $(".bdeCheckBox:checkbox:checked").map(function() {
		return $(this).attr('value');
	}).get();
	var b = nrSel.join(',');
	$("#CheckValbde").val(b);
}
function findselectedrnk()
{

	  $(".rnk:checkbox:checked").each(function() {
       
            $("#sub_list_rnk").show();
        
    });
}

function setchecked(val)
{
	const myArray = val.split(",");
	 $(' #checkboxes_cont_corps input[type="checkbox"]').each(function() {   	
		 if(myArray.includes(this.value)){
				$( this ).attr( 'checked', true );
				

		 }
      });
	  
}
function setcheckeddiv(val)
{		
	const myArray = val.split(",");
	 $(' #checkboxes_cont_div input[type="checkbox"]').each(function() {   	
		 if(myArray.includes(this.value)){
				$( this ).attr( 'checked', true );
		 }
      });	  
}
function setcheckedbde(val)
{	
	const myArray = val.split(",");
	 $(' #checkboxes_cont_bde input[type="checkbox"]').each(function() {   	
		 if(myArray.includes(this.value)){
				$( this ).attr( 'checked', true );
		 }
      });	  
}

function showselected()
{
	
findselectedbde();
findselectedcmd();
findselectedcorps();
findselecteddiv();
findselectedrnk()
// findselectedprntarm();
// findselectedreg()
var cmd=$("#CheckVal").val();
var corps=$("#CheckValcorps").val();
var div=$("#CheckValdiv").val();
var bde=$("#CheckValbde").val();
// var parent_arm = $('input[name="multisub_prnt_arm"]:checkbox:checked').map(function() {
// 	return this.value;
// }).get();
	
//  if(parent_arm.includes("0800")||parent_arm.includes("0700"))
// 	{
// 	 var reg=0;
// 	 	$(".reg:checkbox:checked").each(function() {
// 				reg++;
// 			});
// 	 	if(reg!=0)
// 	 		{
// 	 		$('#sub_list_reg').show();
// 	 		}
// 	 	else{
// 	 		$('#sub_list_reg').hide();
// 	 	}
// 	}
// 	 else{
// 		 $('#sub_list_reg').hide();
// 	 }
	


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
	
function showCheckboxes_rank() {
	
	$("#checkboxes_cont_corps").hide();
	$("#checkboxes1").hide();
	$("#checkboxes_cont_div").hide();
	$("#checkboxes_cont_bde").hide();
	$('#sub_list').hide();
	$('#sub_list_corps').hide();
	$('#sub_list_div').hide();
	$('#sub_list_bde').hide();
	

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

function filterOptions(a,b) {
    var searchBox = document.getElementById(a);
    var checkboxes = document.querySelectorAll(b);

    checkboxes.forEach(function (checkbox) {
        var labelText = checkbox.textContent || checkbox.innerText;
        var shouldShow = labelText.toLowerCase().includes(searchBox.value.toLowerCase());
        checkbox.style.display = shouldShow ? 'block' : 'none';
        
    });
    searchBox.value = searchBox.value.toUpperCase();
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

function getdashboard(id)
{
$(".psg_dash #WaitLoader").show();
window.location.href = id;
	}
</script>


