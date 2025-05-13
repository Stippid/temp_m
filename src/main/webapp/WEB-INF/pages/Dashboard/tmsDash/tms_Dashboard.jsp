<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/circle-progress.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
 <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> 

	<div class="animated fadeIn" >
		<div class="row">
			<div class="col-md-12" align="center">
				<div class="card">
					<div id="DASHBOARD_tabs">
	      				<h5 class="trans_heading">Transport Management System</h5>
    					<div class="row">
    						<div class="card-body card-block">
								<div class="col-md-7">
									<div class="col-md-4 mvcr_updation" style="height: 20%;">
										<h5>VCR UPDATION </h5>
										<a href="#">
											<div class="progressbar" id="progressbar" data-animate="false">
												<div class="circle" data-percent="0" id="mvcrProgress">
													<div id="mvcr"></div>
												</div>
											</div>
										</a> 
									</div>
									<div class="col-md-4" style="height: 20%;">
							     		<h5>TRANSACTION</h5>
										<a href="#">
											<div class="progressbarTrans" id="progressbarTrans" data-animate="false">
												<div class="circle2" data-percent="0" id="mvcrProgressTran">
													<div id="trans"></div>
												</div>
											</div>
										</a>
									</div>
									<div class="col-md-4" style="height: 20%;">
										<h5>RIO</h5>
										<a href="#">
											<div class="progressbarRo" id="progressbarRo" data-animate="false">
												<div class="circle3" data-percent="0" id="mvcrProgressRo_Rio">
													<div id="ro_rio"></div>
												</div>
											</div>
										</a>
									</div>
								</div>
								<div class="col-md-5 col-md-offset-1 state_tables">
									<table id="typeOfVehicleTbl" class="table no-margin table-striped  table-hover">
										<thead>
											<tr>
												<td colspan="4"><h4>VCR UPDATION STATE (Current Month)</h4></td>
											</tr>
											<tr >
												<th>TYPE OF VEHICLE</th>
												<th>DATA UPDATED</th>
												<th>YET TO UPDATE</th>
												<th>TOTAL</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="item" items="${gettypeOfVehicleList}" varStatus="num" >
												<tr>
													<td>A</td>
													<td>${item[0]}</td>
													<td>${item[1]}</td>
												 	<td><a href='#' onclick='openAMvcrUpdateReport();' style='color:white'><u> ${item[0] + item[1]} </u></a></td>
												</tr>
												<tr>
													<td>B</td>
													<td>${item[2]}</td>
													<td>${item[3]}</td>
												 	<td><a href='#' onclick='openBMvcrUpdateReport();' style='color:white'><u> ${item[2] + item[3]} </u></a></td> 
												</tr>
												<tr>
													<td>C</td>
													<td>${item[4]}</td>
													<td>${item[5]}</td>
												 	<td><a href='#' onclick='openCMvcrUpdateReport();' style='color:white'><u> ${item[4] + item[5]} </u></a></td> 
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<table id="getPendingApprovedStatus" class="table no-margin table-striped  table-hover">
										<thead>
											<tr>
												<td colspan="5"><h4>TRANSACTION STATE</h4></td>
											</tr>
											<tr>
												<th>TYPE OF VEHICLE</th>
												<th>PENDING</th>
												<th>APPROVED</th>
												<th>REJECTED</th>
												<th>TOTAL</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
									<table id="getPendingApprovedRoRioStatus" class="table no-margin table-striped  table-hover">
										<thead>
											<tr>
												<td colspan="4"><h4>RO/RIO STATE</h4></td>
											</tr>
											<tr>
												<th>STATUS</th>
												<th>RO GEN</th>
												<th>RIO GEN</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
								<div class="col-md-12">
									<div class="col-md-4" align="center"><img alt="Refresh" src="../login_file/referesh.ico" onclick="refreshAVehUeUh();"></div>
									<div class="col-md-4" align="center"><img alt="Refresh" src="../login_file/referesh.ico" onclick="refreshBVehUeUh();"></div>
									<div class="col-md-4" align="center"><img alt="Refresh" src="../login_file/referesh.ico" onclick="refreshCVehUeUh();"></div>
								</div>
								<div class="col-md-12">
									<div id="chartdivUEUH_A_VEH"></div>
									<div id="chartdivUEUH_B_VEH"></div>
									<div id="chartdivUEUH_C_VEH"></div>
								</div>
								<div class="col-md-12">
									<div class="col-md-2">
										<div class="info-box bg-green">
											<div class="content">
												<div><b>MVCR UNITS</b></div>
												<div class="number count-to" data-from="0" data-to="125" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#illegalityMiningCasesView" onclick="openFormationwiseDetails();">
														<%-- <label id="mvctTotal" ><u>  ${getmvcrunitList} </u></label>  --%>
														
														<c:forEach var="item" items="${gettypeOfVehicleList}" varStatus="num" >
															<label id="mvctTotal" ><u> ${item[2] + item[3]} </u></label>
														</c:forEach>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-deep-purple">
											<div class="content">
												<h5>PRF</h5>
												<div class="number count-to" data-from="0" data-to="257" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#minorMineralMiningView" onclick="openPrfwiseueuhDetails();"> 
														<label id="prfTotal"><u>${getNoOfPrfList}</u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-darkyellow">
											<div class="content">
												<h5>MCT</h5>
												<div class="number count-to" data-from="0" data-to="1432" data-speed="1500" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#totalOtherthanMajorMiningActivityView"> 
														<label id="mctTotal">${getMCTdesList}</label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-red">
											<div class="content">
												<h5>MAKE</h5>
												<div class="number count-to" data-from="0" data-to="1432" data-speed="1500" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#otherThanMiningActivityView"> <label id="makeTotal">${getMAKEList}</label></a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-darkgreen">
											<div class="content">
												<h5>MODEL</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#anyOtherCaseView">
														<label id="modelTotal">${getMODELList}</label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-darkblue">
											<div class="content">
												<h5>UNIT HOLDING</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#anyOtherCaseView">
														<label id="unitTotal">${getUNITList}</label>
													</a>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-12">
									
									
									<div class="col-md-2">
										<div class="info-box bg-red-200">
											<div class="content">
												<h5>DEPOT STOCK</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#anyOtherCaseView">
														<label id="depotTotal">${getDEPOTList}</label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-pink-800">
											<div class="content">
												<h5>TOTAL 'B' VEHs</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" data-target="#anyOtherCaseView">
														<label id="totalTotal">${totalTotal}</label>
													</a>
												</div>
											</div>
										</div>
									</div>
									
									<div class="col-md-2">
										<div class="info-box" style="background: #009688;color: white;">
											<div class="content">
												<h5>LOAN STORE</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" onclick="loanStoreList();"> 
														<label><u>${loanStoreTotal}</u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box md-bg-cyan-400">
											<div class="content">
												<h5>SECTOR STORE</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" onclick="SectorStoreList();">
														<label> <u>${sectoreStoreTotal}</u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box md-bg-amber">
											<div class="content">
												<h5>ACSFP</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" onclick="ACSFPStoreList();">
														<label><u>${acsfpTotal}</u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-2">
										<div class="info-box bg-purple">
											<div class="content">
												<h5>OVER UE</h5>
												<div class="number count-to" data-from="0" data-to="117" data-speed="1000" data-fresh-interval="20">
													<a data-toggle="modal" onclick="OverUeStoreList();">
														<label><u>${overUeTotal}</u></label>
													</a>
												</div>
											</div>
										</div>
									</div>
									
								</div>
							</div>	
    					</div>
	    			</div>
	    		</div>
			</div>
		</div>
	</div>

<script>
////////MVCR UPDATION STATE ///////////////////	
var mvcrProgressCount = "";
var transctionCount = "";
var ro_rioCount = "";

mvcrProgressCount = (parseInt('${gettypeOfVehicleList[0][2]}')/parseInt('${gettypeOfVehicleList[0][2]+gettypeOfVehicleList[0][3]}')) * parseInt(100); 
progressBar(mvcrProgressCount,0,0);

var tab = $("#getPendingApprovedStatus > tbody");
tab.empty(); 
		
$.post("getPendingApprovedStatusList?"+key+"="+value, function(data) {
	drawTable_pen(data);
});
function drawTable_pen(data) {
	drawRow_pen(data);
}
function drawRow_pen(rowData) {
	var row = $("<tr />")
	$("#getPendingApprovedStatus").append(row);
	
	row.append($("<td>B</td>")); 
	row.append($("<td>" + rowData[0] + "</td>"));
	row.append($("<td>" + rowData[1] + "</td>"));
	row.append($("<td>" + rowData[2] + "</td>"));
	var sum = parseInt(rowData[0]) + parseInt(rowData[1]) + parseInt(rowData[2]);
	row.append($("<td>" + sum + "</td>"));

	transctionCount = (parseInt(rowData[1])/parseInt(sum)) * parseInt(100);
	progressBar(0,transctionCount,0);
	} 
	
	
	
//RO RIO
	var tab = $("#getPendingApprovedRoRioStatus > tbody");
    tab.empty(); 
    $.post("getPendingApprovedRoRioStatusList?"+key+"="+value, function(data) {
		drawTable_ro(data);
	});
    var sum_ro = "";
    var sum_rio = "";
    var app_rio = "";
	function drawTable_ro(data) {
		for(var i = 0 ;i<data.length;i++){
			drawRow_ro(data[i],i);
			sum_ro = parseInt(data[0][0]) + parseInt(data[1][0]);
			sum_rio = parseInt(data[0][1]) + parseInt(data[1][1]);
			app_rio = parseInt(data[1][1]);
		}
		var row = $("<tr />")
		$("#getPendingApprovedRoRioStatus").append(row);
		row.append($("<td>Total</td>")); 
		row.append($("<td>" + sum_ro+ "</td>"));
		row.append($("<td>" + sum_rio + "</td>"));
		//Ro RIO Progress Bar
		ro_rioCount = (parseInt(app_rio)/parseInt(sum_rio)) * parseInt(100);
		progressBar(0,0,ro_rioCount);
		//Ro RIO Progress Bar
		
	}
	function drawRow_ro(rowData,i) {
		var row = $("<tr />")
		$("#getPendingApprovedRoRioStatus").append(row);
		if(i==0){
			row.append($("<td style='width:33.33%'>Pending</td>"));	
		}
		if(i==1){
			row.append($("<td style='width:33.33%'>Approved</td>"));	
		}  
		row.append($("<td style='width:33.33%'>" + rowData[0] + "</td>"));
		row.append($("<td style='width:33.33%'>" + rowData[1] + "</td>"));
	}
	//////////////////////////////////////////////
	
	/////// Start Three Chart ///////
	function progressBar(mvcrProgressCount,transctionCount,ro_rioCount){
		if(mvcrProgressCount != 0){
			animateElements(mvcrProgressCount);
			$(window).scroll(animateElements);
		}
		if(transctionCount != 0){
			animateElementsTrans(transctionCount);
			$(window).scroll(animateElementsTrans);
		}
		if(ro_rioCount != 0){
			animateElementsRo_Rio(ro_rioCount);
			$(window).scroll(animateElementsRo_Rio);
		}
	}
	function animateElements(mvcrProgressCount) {
		$('#progressbar').each(function () {
			var elementPos = $(this).offset().top;
			var topOfWindow = $(window).scrollTop();
			var percent = mvcrProgressCount;
			var percentage = parseInt(percent, 10) / parseInt(100, 10);
			
			var animate = $(this).data('animate');
			if (elementPos < topOfWindow + $(window).height() - 30 && !animate) {
				$(this).data('animate', true);
				$(this).find('.circle').circleProgress({
					startAngle: -Math.PI / 2,
					value: percent / 100,
					size: 160,
					thickness: 30,
					emptyFill: "rgba(0,0,0, .2)",
					fill: {
						color: '#14385f'
					}
				}).on('circle-animation-progress', function (event, progress, stepValue) {
					$("div#mvcr").text((stepValue*100).toFixed(2) + "%");
				}).stop();
			}
		});
	}

	function animateElementsTrans(transProgressCount) {
		$('#progressbarTrans').each(function () {
			var elementPos = $(this).offset().top;
			var topOfWindow = $(window).scrollTop();
			var percent = transProgressCount;
			var percentage = parseInt(percent, 10) / parseInt(100, 10);
			var animate = $(this).data('animate');
			if (elementPos < topOfWindow + $(window).height() - 30 && !animate) {
				$(this).data('animate', true);
				$(this).find('.circle2').circleProgress({
					startAngle: -Math.PI / 2,
					value: percent / 100,
					size: 120,
					thickness: 10,
					emptyFill: "rgba(0,0,0, .2)",
					fill: {
						color: '#ffa500'
					}
				}).on('circle-animation-progress', function (event, progress, stepValue) {
				
					$("div#trans").text((stepValue*100).toFixed(2) + "%");
				}).stop();
			}
		});
	}
	function animateElementsRo_Rio(ro_rioProgressCount){
		$('#progressbarRo').each(function () {
			var elementPos = $(this).offset().top;
			var topOfWindow = $(window).scrollTop();
			var percent = ro_rioProgressCount;
			var percentage = parseInt(percent, 10) / parseInt(100, 10);
			var animate = $(this).data('animate');
			if (elementPos < topOfWindow + $(window).height() - 30 && !animate) {
				$(this).data('animate', true);
				$(this).find('.circle3').circleProgress({
					startAngle: -Math.PI / 2,
					value: percent / 100,
					size: 120,
					thickness: 10,
					emptyFill: "rgba(0,0,0, .2)",
					fill: {
						color: '#008000'
					}
				}).on('circle-animation-progress', function (event, progress, stepValue) {
					$("div#ro_rio").text((stepValue*100).toFixed(2) + "%");
				}).stop();
			}
		});
	}
	

// Themes end
///// Start UE UH A VEH Bar Chart ////////
	jQuery(document).ready(function() {
	
		refreshAVehUeUh();
		refreshBVehUeUh();
		refreshCVehUeUh();
	});
		
	function refreshAVehUeUh(){
		am4core.useTheme(am4themes_animated);
		var chartdivUEUH_A_VEH = am4core.create("chartdivUEUH_A_VEH", am4charts.XYChart);
		chartdivUEUH_A_VEH.data = ${getCommandWiseUE_UH_A_VEH_List};
		var colorSet = new am4core.ColorSet();
		colorSet.list = ["#388E3C","#F44336"].map(function(color) {
		  return new am4core.color(color);
		});
		chartdivUEUH_A_VEH.colors = colorSet;
	
		var categoryAxis_AVEH = chartdivUEUH_A_VEH.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis_AVEH.dataFields.category = "year";
		categoryAxis_AVEH.title.text = "A Vehicle";
		categoryAxis_AVEH.renderer.grid.template.location = 0;
		categoryAxis_AVEH.renderer.minGridDistance = 20;
		categoryAxis_AVEH.renderer.cellStartLocation = 0.1;
		categoryAxis_AVEH.renderer.cellEndLocation = 0.7;
		categoryAxis_AVEH.renderer.labels.template.verticalCenter ="middle";
		categoryAxis_AVEH.renderer.labels.template.rotation = 270;
		
		var  valueAxis_AVEH = chartdivUEUH_A_VEH.yAxes.push(new am4charts.ValueAxis());
		valueAxis_AVEH.min = 0;
		//valueAxis_AVEH.title.text = "Expenditure (M)";
	
		// Create series
		function createSeries_AVEH(field, name, stacked) {
		  	var series_AVEH = chartdivUEUH_A_VEH.series.push(new am4charts.ColumnSeries());
		  	series_AVEH.dataFields.valueY = field;
		  	series_AVEH.dataFields.categoryX = "year";
		  	series_AVEH.name = name;
		  	series_AVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
		  	series_AVEH.stacked = stacked;
		  	series_AVEH.columns.template.width = am4core.percent(95);
		  	series_AVEH.columns.template.events.on("hit",function(ev){
		  		var selectedComd = ev.target.dataItem.dataContext.comd_code;
		  	 	$.post("getCorpsWiseUE_UH_A_VEH_List?"+key+"="+value,{comnd : selectedComd}, function(j) {
		  	 		if(j.length == 0){
		  	 			//if corps is null then get div under selected command
		  	 			var selectedCorps = ev.target.dataItem.dataContext.comd_code +'00';
		  	 			$.post("getDivWiseUE_UH_A_VEH_List?"+key+"="+value,{Corps:selectedCorps}, function(j) {
				  	 		if(j.length == 0){
				  	 			//if Div is null then get bde under selected command
				  	 			var selectedbde = ev.target.dataItem.dataContext.comd_code +'00000';
				  	 			$.post("getBDEWiseUE_UH_A_VEH_List?"+key+"="+value,{Div:selectedbde}, function(j) {
						  	 		if(j.length == 0){
						  	 			alert("No Data Found");
						  	 		}else{
							  			var bde = new Array();
							  			for(var i=0;i<j.length;i++){
							  				if(i==0){
							  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]});	
							  				}else{
							  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]} );
							  				}
							  			}
							  			UEUHBDEClick_A_Veh(bde);
						  	 		}
						  		});
				  	 		}else{
					  			var div = new Array();
					  			for(var i=0;i<j.length;i++){
					  				if(i==0){
					  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]});	
					  				}else{
					  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]} );
					  				}
					  			}
					  			UEUHCORPSClick_A_Veh(div);
				  	 		}
				  		}); 
		  	 			//if corps is null then get div under selected command
		  	 		}else{
			  			var corps = new Array();
			  			for(var i=0;i<j.length;i++){
			  				if(i==0){
			  					corps.push({year:j[i][0],ue:j[i][1],uh:j[i][2],corps_code:j[i][3]});	
			  				}else{
			  					corps.push({year:j[i][0],ue:j[i][1],uh:j[i][2],corps_code:j[i][3]} );
			  				}
			  			}
			  			UEUHCommandClick_A_Veh(corps);
		  	 		}
		  		});  
		  		
		  	},this);
		}
		createSeries_AVEH("ue", "UE", false);
		createSeries_AVEH("uh", "UH", true);
		chartdivUEUH_A_VEH.legend = new am4charts.Legend();   
	}		
		
	function UEUHCommandClick_A_Veh(corps){
		am4core.useTheme(am4themes_animated);
		var chartdivUEUH_A_VEH = am4core.create("chartdivUEUH_A_VEH", am4charts.XYChart);
		chartdivUEUH_A_VEH.data = corps;
		var colorSet = new am4core.ColorSet();
		colorSet.list = ["#388E3C","#F44336"].map(function(color) {
		  return new am4core.color(color);
		});
		chartdivUEUH_A_VEH.colors = colorSet;
		// Create axes
		var categoryAxis_AVEH = chartdivUEUH_A_VEH.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis_AVEH.dataFields.category = "year";
		categoryAxis_AVEH.title.text = "A Vehicle";
		categoryAxis_AVEH.renderer.grid.template.location = 0;
		categoryAxis_AVEH.renderer.minGridDistance = 20;
		categoryAxis_AVEH.renderer.cellStartLocation = 0.1;
		categoryAxis_AVEH.renderer.cellEndLocation = 0.7;
		categoryAxis_AVEH.renderer.labels.template.verticalCenter ="middle";
		categoryAxis_AVEH.renderer.labels.template.rotation = 270;

		var  valueAxis_AVEH = chartdivUEUH_A_VEH.yAxes.push(new am4charts.ValueAxis());
		valueAxis_AVEH.min = 0;
		function createSeries_AVEH(field, name, stacked) {
		  	var series_AVEH = chartdivUEUH_A_VEH.series.push(new am4charts.ColumnSeries());
		  	series_AVEH.dataFields.valueY = field;
		  	series_AVEH.dataFields.categoryX = "year";
		  	series_AVEH.name = name;
		  	series_AVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
		  	series_AVEH.stacked = stacked;
		  	series_AVEH.columns.template.width = am4core.percent(95);
		  	series_AVEH.columns.template.events.on("hit",function(ev){
		  	 	$.post("getDivWiseUE_UH_A_VEH_List?"+key+"="+value,{Corps:ev.target.dataItem.dataContext.corps_code}, function(j) {
		  	 		if(j.length == 0){
		  	 			//if Div is null then get bde under selected Div
		  	 			var selectedbde = ev.target.dataItem.dataContext.corps_code +'000';
		  	 			$.post("getBDEWiseUE_UH_A_VEH_List?"+key+"="+value,{Div:selectedbde}, function(j) {
				  	 		if(j.length == 0){
				  	 			alert("No Data Found");
				  	 		}else{
					  			var bde = new Array();
					  			for(var i=0;i<j.length;i++){
					  				if(i==0){
					  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]});	
					  				}else{
					  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]} );
					  				}
					  			}
					  			UEUHBDEClick_A_Veh(bde);
				  	 		}
				  		});
		  	 		}else{
			  			var div = new Array();
			  			for(var i=0;i<j.length;i++){
			  				if(i==0){
			  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]});	
			  				}else{
			  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]} );
			  				}
			  			}
			  			UEUHCORPSClick_A_Veh(div);
		  	 		}
		  		});  
		  	},this);
		}
		createSeries_AVEH("ue", "UE", false);
		createSeries_AVEH("uh", "UH", false);
		chartdivUEUH_A_VEH.legend = new am4charts.Legend(); 
	} 
		
	function UEUHCORPSClick_A_Veh(div){
	 	am4core.useTheme(am4themes_animated);
		var chartdivUEUH_A_VEH = am4core.create("chartdivUEUH_A_VEH", am4charts.XYChart);
		chartdivUEUH_A_VEH.data = div;
		var colorSet = new am4core.ColorSet();
		colorSet.list = ["#388E3C","#F44336"].map(function(color) {
		  return new am4core.color(color);
		});
		chartdivUEUH_A_VEH.colors = colorSet;
		
		// Create axes
		var categoryAxis_AVEH = chartdivUEUH_A_VEH.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis_AVEH.dataFields.category = "year";
		categoryAxis_AVEH.title.text = "A Vehicle";
		categoryAxis_AVEH.renderer.grid.template.location = 0;
		categoryAxis_AVEH.renderer.minGridDistance = 20;
		categoryAxis_AVEH.renderer.cellStartLocation = 0.1;
		categoryAxis_AVEH.renderer.cellEndLocation = 0.7;
		categoryAxis_AVEH.renderer.labels.template.verticalCenter ="middle";
		categoryAxis_AVEH.renderer.labels.template.rotation = 270;

		var  valueAxis_AVEH = chartdivUEUH_A_VEH.yAxes.push(new am4charts.ValueAxis());
		valueAxis_AVEH.min = 0;
		function createSeries_AVEH(field, name, stacked) {
		  	var series_AVEH = chartdivUEUH_A_VEH.series.push(new am4charts.ColumnSeries());
		  	series_AVEH.dataFields.valueY = field;
		  	series_AVEH.dataFields.categoryX = "year";
		  	series_AVEH.name = name;
		  	series_AVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
		  	series_AVEH.stacked = stacked;
		  	series_AVEH.columns.template.width = am4core.percent(95);
		    series_AVEH.columns.template.events.on("hit",function(ev){
		  	 	$.post("getBDEWiseUE_UH_A_VEH_List?"+key+"="+value,{Div:ev.target.dataItem.dataContext.div_code}, function(j) {
		  	 		if(j.length == 0){
		  	 			alert("No Data Found");
		  	 		}else{
			  			var bde = new Array();
			  			for(var i=0;i<j.length;i++){
			  				if(i==0){
			  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]});	
			  				}else{
			  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]} );
			  				}
			  			}
			  			UEUHBDEClick_A_Veh(bde);
		  	 		}
		  		});  
		  		
		  	},this); 
		}
		createSeries_AVEH("ue", "UE", false);
		createSeries_AVEH("uh", "UH", false);
		chartdivUEUH_A_VEH.legend = new am4charts.Legend(); 
	} 
		
   function UEUHBDEClick_A_Veh(bde){
		am4core.useTheme(am4themes_animated);
		var chartdivUEUH_A_VEH = am4core.create("chartdivUEUH_A_VEH", am4charts.XYChart);
		chartdivUEUH_A_VEH.data = bde;
		var colorSet = new am4core.ColorSet();
		colorSet.list = ["#388E3C","#F44336"].map(function(color) {
		  return new am4core.color(color);
		});
		chartdivUEUH_A_VEH.colors = colorSet;
		
		// Create axes
		var categoryAxis_AVEH = chartdivUEUH_A_VEH.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis_AVEH.dataFields.category = "year";
		categoryAxis_AVEH.title.text = "A Vehicle";
		categoryAxis_AVEH.renderer.grid.template.location = 0;
		categoryAxis_AVEH.renderer.minGridDistance = 20;
		categoryAxis_AVEH.renderer.cellStartLocation = 0.1;
		categoryAxis_AVEH.renderer.cellEndLocation = 0.7;
		categoryAxis_AVEH.renderer.labels.template.verticalCenter ="middle";
		categoryAxis_AVEH.renderer.labels.template.rotation = 270;

		var  valueAxis_AVEH = chartdivUEUH_A_VEH.yAxes.push(new am4charts.ValueAxis());
		valueAxis_AVEH.min = 0;
		function createSeries_AVEH(field, name, stacked) {
		  	var series_AVEH = chartdivUEUH_A_VEH.series.push(new am4charts.ColumnSeries());
		  	series_AVEH.dataFields.valueY = field;
		  	series_AVEH.dataFields.categoryX = "year";
		  	series_AVEH.name = name;
		  	series_AVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
		  	series_AVEH.stacked = stacked;
		  	series_AVEH.columns.template.width = am4core.percent(95);
		  
		}
		createSeries_AVEH("ue", "UE", false);
		createSeries_AVEH("uh", "UH", false);
		chartdivUEUH_A_VEH.legend = new am4charts.Legend(); 
	} 
///////// End UE UH A VEH Bar Chart //////////////		
	
///// Start UE UH B VEH Bar Chart ////////
function refreshBVehUeUh(){
 	am4core.useTheme(am4themes_animated);
	var chartdivUEUH_B_VEH = am4core.create("chartdivUEUH_B_VEH", am4charts.XYChart);
	chartdivUEUH_B_VEH.data = ${getCommandWiseUE_UH_B_VEH_List};
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C","#F44336"].map(function(color) {
	  return new am4core.color(color);
	});
	chartdivUEUH_B_VEH.colors = colorSet;
	
	// Create axes
	var categoryAxis_BVEH = chartdivUEUH_B_VEH.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_BVEH.dataFields.category = "year";
	categoryAxis_BVEH.title.text = "B Vehicle";
	categoryAxis_BVEH.renderer.grid.template.location = 0;
	categoryAxis_BVEH.renderer.minGridDistance = 20;
	categoryAxis_BVEH.renderer.cellStartLocation = 0.1;
	categoryAxis_BVEH.renderer.cellEndLocation = 0.7;
	categoryAxis_BVEH.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_BVEH.renderer.labels.template.rotation = 270;

	var  valueAxis_BVEH = chartdivUEUH_B_VEH.yAxes.push(new am4charts.ValueAxis());
	valueAxis_BVEH.min = 0;
	function createSeries_BVEH(field, name, stacked) {
	  	var series_BVEH = chartdivUEUH_B_VEH.series.push(new am4charts.ColumnSeries());
	  	series_BVEH.dataFields.valueY = field;
	  	series_BVEH.dataFields.categoryX = "year";
	  	series_BVEH.name = name;
	  	series_BVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_BVEH.stacked = stacked;
	  	series_BVEH.columns.template.width = am4core.percent(95);
	  	series_BVEH.columns.template.events.on("hit",function(ev){
	  		var selectedcomd = ev.target.dataItem.dataContext.comd_code;
	  	 	$.post("getCorpsWiseUE_UH_B_VEH_List?"+key+"="+value,{comnd : selectedcomd}, function(j) {
	  	 		if(j.length == 0){
	  	 			var selectedcorps = ev.target.dataItem.dataContext.comd_code+"00";
	  		  	 	$.post("getDivWiseUE_UH_B_VEH_List?"+key+"="+value,{Corps:selectedcorps}, function(j) {
	  		  	 		if(j.length == 0){
	  		  	 			var selectedbde = ev.target.dataItem.dataContext.comd_code+"00000";
		  		  	 		$.post("getBDEWiseUE_UH_B_VEH_List?"+key+"="+value,{Div:selectedbde}, function(j) {
		  			  	 		if(j.length == 0){
		  			  	 			alert("No Data Found");
		  			  	 		}else{
		  				  			var bde = new Array();
		  				  			for(var i=0;i<j.length;i++){
		  				  				if(i==0){
		  				  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]});	
		  				  				}else{
		  				  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]} );
		  				  				}
		  				  			}
		  				  			UEUHBDEClick_B_Veh(bde);
		  			  	 		}
		  			  		});
	  		  	 		}else{
	  			  			var div = new Array();
	  			  			for(var i=0;i<j.length;i++){
	  			  				if(i==0){
	  			  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]});	
	  			  				}else{
	  			  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]} );
	  			  				}
	  			  			}
	  			  			UEUHCORPSClick_B_Veh(div);
	  		  	 		}
	  		  		});  
	  	 		}else{
		  			var corps = new Array();
		  			for(var i=0;i<j.length;i++){
		  				if(i==0){
		  					corps.push({year:j[i][0],ue:j[i][1],uh:j[i][2],corps_code:j[i][3]});	
		  				}else{
		  					corps.push({year:j[i][0],ue:j[i][1],uh:j[i][2],corps_code:j[i][3]} );
		  				}
		  			}
		  			UEUHCommandClick_B_Veh(corps);
		  		}
	  		});  
	  	},this);
	}
	createSeries_BVEH("ue", "UE", false);
	createSeries_BVEH("uh", "UH", true);
	chartdivUEUH_B_VEH.legend = new am4charts.Legend();   
}	
	
function UEUHCommandClick_B_Veh(corps){
	am4core.useTheme(am4themes_animated);
	var chartdivUEUH_B_VEH = am4core.create("chartdivUEUH_B_VEH", am4charts.XYChart);
	chartdivUEUH_B_VEH.data = corps;
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C","#F44336"].map(function(color) {
	  return new am4core.color(color);
	});
	chartdivUEUH_B_VEH.colors = colorSet;
	
	// Create axes
	var categoryAxis_BVEH = chartdivUEUH_B_VEH.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_BVEH.dataFields.category = "year";
	categoryAxis_BVEH.title.text = "B Vehicle";
	categoryAxis_BVEH.renderer.grid.template.location = 0;
	categoryAxis_BVEH.renderer.minGridDistance = 20;
	categoryAxis_BVEH.renderer.cellStartLocation = 0.1;
	categoryAxis_BVEH.renderer.cellEndLocation = 0.7;
	categoryAxis_BVEH.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_BVEH.renderer.labels.template.rotation = 270;

	var  valueAxis_BVEH = chartdivUEUH_B_VEH.yAxes.push(new am4charts.ValueAxis());
	valueAxis_BVEH.min = 0;
	function createSeries_BVEH(field, name, stacked) {
	  	var series_BVEH = chartdivUEUH_B_VEH.series.push(new am4charts.ColumnSeries());
	  	series_BVEH.dataFields.valueY = field;
	  	series_BVEH.dataFields.categoryX = "year";
	  	series_BVEH.name = name;
	  	series_BVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_BVEH.stacked = stacked;
	  	series_BVEH.columns.template.width = am4core.percent(95);
	  	series_BVEH.columns.template.events.on("hit",function(ev){
	  		var selectedcorps = ev.target.dataItem.dataContext.corps_code;
	  	 	$.post("getDivWiseUE_UH_B_VEH_List?"+key+"="+value,{Corps:selectedcorps}, function(j) {
	  	 		if(j.length == 0){
	  	 			var selectedbde = ev.target.dataItem.dataContext.corps_code+"000";
  		  	 		$.post("getBDEWiseUE_UH_B_VEH_List?"+key+"="+value,{Div:selectedbde}, function(j) {
  			  	 		if(j.length == 0){
  			  	 			alert("No Data Found");
  			  	 		}else{
  				  			var bde = new Array();
  				  			for(var i=0;i<j.length;i++){
  				  				if(i==0){
  				  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]});	
  				  				}else{
  				  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]} );
  				  				}
  				  			}
  				  			UEUHBDEClick_B_Veh(bde);
  			  	 		}
  			  		});
	  	 		}else{
		  			var div = new Array();
		  			for(var i=0;i<j.length;i++){
		  				if(i==0){
		  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]});	
		  				}else{
		  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]} );
		  				}
		  			}
		  			UEUHCORPSClick_B_Veh(div);
	  	 		}
	  		});  
	  		
	  	},this);
	}
	createSeries_BVEH("ue", "UE", false);
	createSeries_BVEH("uh", "UH", false);
	chartdivUEUH_B_VEH.legend = new am4charts.Legend(); 
} 
	
function UEUHCORPSClick_B_Veh(div){
 	am4core.useTheme(am4themes_animated);
	var chartdivUEUH_B_VEH = am4core.create("chartdivUEUH_B_VEH", am4charts.XYChart);
	chartdivUEUH_B_VEH.data = div;
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C","#F44336"].map(function(color) {
	  return new am4core.color(color);
	});
	chartdivUEUH_B_VEH.colors = colorSet;
	
	// Create axes
	var categoryAxis_BVEH = chartdivUEUH_B_VEH.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_BVEH.dataFields.category = "year";
	categoryAxis_BVEH.title.text = "B Vehicle";
	categoryAxis_BVEH.renderer.grid.template.location = 0;
	categoryAxis_BVEH.renderer.minGridDistance = 20;
	categoryAxis_BVEH.renderer.cellStartLocation = 0.1;
	categoryAxis_BVEH.renderer.cellEndLocation = 0.7;
	categoryAxis_BVEH.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_BVEH.renderer.labels.template.rotation = 270;

	var  valueAxis_BVEH = chartdivUEUH_B_VEH.yAxes.push(new am4charts.ValueAxis());
	valueAxis_BVEH.min = 0;
	function createSeries_BVEH(field, name, stacked) {
	  	var series_BVEH = chartdivUEUH_B_VEH.series.push(new am4charts.ColumnSeries());
	  	series_BVEH.dataFields.valueY = field;
	  	series_BVEH.dataFields.categoryX = "year";
	  	series_BVEH.name = name;
	  	series_BVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_BVEH.stacked = stacked;
	  	series_BVEH.columns.template.width = am4core.percent(95);
	    series_BVEH.columns.template.events.on("hit",function(ev){
	  	 	$.post("getBDEWiseUE_UH_B_VEH_List?"+key+"="+value,{Div:ev.target.dataItem.dataContext.div_code}, function(j) {
	  	 		if(j.length == 0){
	  	 			alert("No Data Found");
	  	 		}else{
		  			var bde = new Array();
		  			for(var i=0;i<j.length;i++){
		  				if(i==0){
		  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]});	
		  				}else{
		  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]} );
		  				}
		  			}
		  			UEUHBDEClick_B_Veh(bde);
	  	 		}
	  		});  
	  		
	  	},this); 
	}
	createSeries_BVEH("ue", "UE", false);
	createSeries_BVEH("uh", "UH", false);
	chartdivUEUH_B_VEH.legend = new am4charts.Legend(); 
} 
	
  function UEUHBDEClick_B_Veh(bde){
	am4core.useTheme(am4themes_animated);
	var chartdivUEUH_B_VEH = am4core.create("chartdivUEUH_B_VEH", am4charts.XYChart);
	chartdivUEUH_B_VEH.data = bde;
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C","#F44336"].map(function(color) {
	  return new am4core.color(color);
	});
	chartdivUEUH_B_VEH.colors = colorSet;
	
	// Create axes
	var categoryAxis_BVEH = chartdivUEUH_B_VEH.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_BVEH.dataFields.category = "year";
	categoryAxis_BVEH.title.text = "B Vehicle";
	categoryAxis_BVEH.renderer.grid.template.location = 0;
	categoryAxis_BVEH.renderer.minGridDistance = 20;
	categoryAxis_BVEH.renderer.cellStartLocation = 0.1;
	categoryAxis_BVEH.renderer.cellEndLocation = 0.7;
	categoryAxis_BVEH.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_BVEH.renderer.labels.template.rotation = 270;

	var  valueAxis_BVEH = chartdivUEUH_B_VEH.yAxes.push(new am4charts.ValueAxis());
	valueAxis_BVEH.min = 0;
	function createSeries_BVEH(field, name, stacked) {
	  	var series_BVEH = chartdivUEUH_B_VEH.series.push(new am4charts.ColumnSeries());
	  	series_BVEH.dataFields.valueY = field;
	  	series_BVEH.dataFields.categoryX = "year";
	  	series_BVEH.name = name;
	  	series_BVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_BVEH.stacked = stacked;
	  	series_BVEH.columns.template.width = am4core.percent(95);
	  
	}
	createSeries_BVEH("ue", "UE", false);
	createSeries_BVEH("uh", "UH", false);
	chartdivUEUH_B_VEH.legend = new am4charts.Legend(); 
}
		
	
	
	
///////// End UE UH B VEH Bar Chart //////////////		
	
 ///// Start UE UH C VEH Bar Chart ////////
function refreshCVehUeUh(){
 	am4core.useTheme(am4themes_animated);
	var chartdivUEUH_C_VEH = am4core.create("chartdivUEUH_C_VEH", am4charts.XYChart);
	chartdivUEUH_C_VEH.data =  ${getCommandWiseUE_UH_C_VEH_List}; 
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C","#F44336"].map(function(color) {
	  return new am4core.color(color);
	});
	chartdivUEUH_C_VEH.colors = colorSet;
	var categoryAxis_CVEH = chartdivUEUH_C_VEH.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_CVEH.dataFields.category = "year";
	categoryAxis_CVEH.title.text = "C Vehicle";
	categoryAxis_CVEH.renderer.grid.template.location = 0;
	categoryAxis_CVEH.renderer.minGridDistance = 20;
	categoryAxis_CVEH.renderer.cellStartLocation = 0.1;
	categoryAxis_CVEH.renderer.cellEndLocation = 0.7;
	categoryAxis_CVEH.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_CVEH.renderer.labels.template.rotation = 270;
	var  valueAxis_CVEH = chartdivUEUH_C_VEH.yAxes.push(new am4charts.ValueAxis());
	valueAxis_CVEH.min = 0;
	function createSeries_CVEH(field, name, stacked) {
		var series_CVEH = chartdivUEUH_C_VEH.series.push(new am4charts.ColumnSeries());
	  	series_CVEH.dataFields.valueY = field;
	  	series_CVEH.dataFields.categoryX = "year";
	  	series_CVEH.name = name;
	  	series_CVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_CVEH.stacked = stacked;
	  	series_CVEH.columns.template.width = am4core.percent(95);
	  	series_CVEH.columns.template.events.on("hit",function(ev){
	  		var selectedcomd = ev.target.dataItem.dataContext.comd_code;
	  	 	$.post("getCorpsWiseUE_UH_C_VEH_List?"+key+"="+value,{comnd : selectedcomd}, function(j) {
	  	 		if(j.length == 0){
	  	 			var selectedcorps = ev.target.dataItem.dataContext.comd_code+"00";
	  		  	 	$.post("getDivWiseUE_UH_C_VEH_List?"+key+"="+value,{Corps:selectedcorps}, function(j) {
	  		  	 		if(j.length == 0){
		  		  	 		var selecteddiv = ev.target.dataItem.dataContext.comd_code+"00000";
		  			  	 	$.post("getBDEWiseUE_UH_C_VEH_List?"+key+"="+value,{Div:selecteddiv}, function(j) {
		  			  	 		if(j.length == 0){
		  			  	 			alert("No Data Found");
		  			  	 		}else{
		  				  			var bde = new Array();
		  				  			for(var i=0;i<j.length;i++){
		  				  				if(i==0){
		  				  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]});	
		  				  				}else{
		  				  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]} );
		  				  				}
		  				  			}
		  				  			UEUHBDEClick_C_Veh(bde);
		  			  	 		}
		  			  		});  
	  		  	 		}else{
	  			  			var div = new Array();
	  			  			for(var i=0;i<j.length;i++){
	  			  				if(i==0){
	  			  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]});	
	  			  				}else{
	  			  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]} );
	  			  				}
	  			  			}
	  			  			UEUHCORPSClick_C_Veh(div);
	  		  	 		}
	  		  		});
	  	 		}else{
		  			var corps = new Array();
		  			for(var i=0;i<j.length;i++){
		  				if(i==0){
		  					corps.push({year:j[i][0],ue:j[i][1],uh:j[i][2],corps_code:j[i][3]});	
		  				}else{
		  					corps.push({year:j[i][0],ue:j[i][1],uh:j[i][2],corps_code:j[i][3]} );
		  				}
		  			}
		  			UEUHCommandClick_C_Veh(corps);
	  	 		}
	  		});  
	  		
	  	},this);
	}
	createSeries_CVEH("ue", "UE", false);
	createSeries_CVEH("uh", "UH", true);
	chartdivUEUH_C_VEH.legend = new am4charts.Legend();   
}	

function UEUHCommandClick_C_Veh(corps){
	am4core.useTheme(am4themes_animated);
	var chartdivUEUH_C_VEH = am4core.create("chartdivUEUH_C_VEH", am4charts.XYChart);
	chartdivUEUH_C_VEH.data = corps;
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C","#F44336"].map(function(color) {
	  return new am4core.color(color);
	});
	chartdivUEUH_C_VEH.colors = colorSet;
	
	// Create axes
	var categoryAxis_CVEH = chartdivUEUH_C_VEH.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_CVEH.dataFields.category = "year";
	categoryAxis_CVEH.title.text = "C Vehicle";
	categoryAxis_CVEH.renderer.grid.template.location = 0;
	categoryAxis_CVEH.renderer.minGridDistance = 20;
	categoryAxis_CVEH.renderer.cellStartLocation = 0.1;
	categoryAxis_CVEH.renderer.cellEndLocation = 0.7;
	categoryAxis_CVEH.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_CVEH.renderer.labels.template.rotation = 270;

	var  valueAxis_CVEH = chartdivUEUH_C_VEH.yAxes.push(new am4charts.ValueAxis());
	valueAxis_CVEH.min = 0;
	function createSeries_CVEH(field, name, stacked) {
	  	var series_CVEH = chartdivUEUH_C_VEH.series.push(new am4charts.ColumnSeries());
	  	series_CVEH.dataFields.valueY = field;
	  	series_CVEH.dataFields.categoryX = "year";
	  	series_CVEH.name = name;
	  	series_CVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_CVEH.stacked = stacked;
	  	series_CVEH.columns.template.width = am4core.percent(95);
	  	series_CVEH.columns.template.events.on("hit",function(ev){
	  		var selectedcorps = ev.target.dataItem.dataContext.corps_code;
	  	 	$.post("getDivWiseUE_UH_C_VEH_List?"+key+"="+value,{Corps:selectedcorps}, function(j) {
	  	 		if(j.length == 0){
	  	 			var selecteddiv = ev.target.dataItem.dataContext.corps_code+"000";
  			  	 	$.post("getBDEWiseUE_UH_C_VEH_List?"+key+"="+value,{Div:selecteddiv}, function(j) {
  			  	 		if(j.length == 0){
  			  	 			alert("No Data Found");
  			  	 		}else{
  				  			var bde = new Array();
  				  			for(var i=0;i<j.length;i++){
  				  				if(i==0){
  				  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]});	
  				  				}else{
  				  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]} );
  				  				}
  				  			}
  				  			UEUHBDEClick_C_Veh(bde);
  			  	 		}
  			  		});  
	  	 		}else{
		  			var div = new Array();
		  			for(var i=0;i<j.length;i++){
		  				if(i==0){
		  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]});	
		  				}else{
		  					div.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]} );
		  				}
		  			}
		  			UEUHCORPSClick_C_Veh(div);
	  	 		}
	  		});  
	  	},this);
	}
	createSeries_CVEH("ue", "UE", false);
	createSeries_CVEH("uh", "UH", false);
	chartdivUEUH_C_VEH.legend = new am4charts.Legend(); 
} 
	
function UEUHCORPSClick_C_Veh(div){
 	am4core.useTheme(am4themes_animated);
	var chartdivUEUH_C_VEH = am4core.create("chartdivUEUH_C_VEH", am4charts.XYChart);
	chartdivUEUH_C_VEH.data = div;
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C","#F44336"].map(function(color) {
	  return new am4core.color(color);
	});
	chartdivUEUH_C_VEH.colors = colorSet;
	// Create axes
	var categoryAxis_CVEH = chartdivUEUH_C_VEH.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_CVEH.dataFields.category = "year";
	categoryAxis_CVEH.title.text = "C Vehicle";
	categoryAxis_CVEH.renderer.grid.template.location = 0;
	categoryAxis_CVEH.renderer.minGridDistance = 20;
	categoryAxis_CVEH.renderer.cellStartLocation = 0.1;
	categoryAxis_CVEH.renderer.cellEndLocation = 0.7;
	categoryAxis_CVEH.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_CVEH.renderer.labels.template.rotation = 270;

	var  valueAxis_CVEH = chartdivUEUH_C_VEH.yAxes.push(new am4charts.ValueAxis());
	valueAxis_CVEH.min = 0;
	function createSeries_CVEH(field, name, stacked) {
	  	var series_CVEH = chartdivUEUH_C_VEH.series.push(new am4charts.ColumnSeries());
	  	series_CVEH.dataFields.valueY = field;
	  	series_CVEH.dataFields.categoryX = "year";
	  	series_CVEH.name = name;
	  	series_CVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_CVEH.stacked = stacked;
	  	series_CVEH.columns.template.width = am4core.percent(95);
	    series_CVEH.columns.template.events.on("hit",function(ev){
	    	var selecteddiv = ev.target.dataItem.dataContext.div_code;
	  	 	$.post("getBDEWiseUE_UH_C_VEH_List?"+key+"="+value,{Div:selecteddiv}, function(j) {
	  	 		if(j.length == 0){
	  	 			alert("No Data Found");
	  	 		}else{
		  			var bde = new Array();
		  			for(var i=0;i<j.length;i++){
		  				if(i==0){
		  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],bde_code:j[i][3]});	
		  				}else{
		  					bde.push({year:j[i][0],ue:j[i][1],uh:j[i][2],div_code:j[i][3]} );
		  				}
		  			}
		  			UEUHBDEClick_C_Veh(bde);
	  	 		}
	  		});  
	  	},this); 
	}
	createSeries_CVEH("ue", "UE", false);
	createSeries_CVEH("uh", "UH", false);
	chartdivUEUH_C_VEH.legend = new am4charts.Legend(); 
} 

function UEUHBDEClick_C_Veh(bde){
	am4core.useTheme(am4themes_animated);
	var chartdivUEUH_C_VEH = am4core.create("chartdivUEUH_C_VEH", am4charts.XYChart);
	chartdivUEUH_C_VEH.data = bde;
	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C","#F44336"].map(function(color) {
	  return new am4core.color(color);
	});
	chartdivUEUH_C_VEH.colors = colorSet;
	// Create axes
	var categoryAxis_CVEH = chartdivUEUH_C_VEH.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_CVEH.dataFields.category = "year";
	categoryAxis_CVEH.title.text = "C Vehicle";
	categoryAxis_CVEH.renderer.grid.template.location = 0;
	categoryAxis_CVEH.renderer.minGridDistance = 20;
	categoryAxis_CVEH.renderer.cellStartLocation = 0.1;
	categoryAxis_CVEH.renderer.cellEndLocation = 0.7;
	categoryAxis_CVEH.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_CVEH.renderer.labels.template.rotation = 270;

	var  valueAxis_CVEH = chartdivUEUH_C_VEH.yAxes.push(new am4charts.ValueAxis());
	valueAxis_CVEH.min = 0;
	function createSeries_CVEH(field, name, stacked) {
	  	var series_CVEH = chartdivUEUH_C_VEH.series.push(new am4charts.ColumnSeries());
	  	series_CVEH.dataFields.valueY = field;
	  	series_CVEH.dataFields.categoryX = "year";
	  	series_CVEH.name = name;
	  	series_CVEH.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_CVEH.stacked = stacked;
	  	series_CVEH.columns.template.width = am4core.percent(95);
	}
	createSeries_CVEH("ue", "UE", false);
	createSeries_CVEH("uh", "UH", false);
	chartdivUEUH_C_VEH.legend = new am4charts.Legend(); 
} 
///////// End UE UH C VEH Bar Chart //////////////
		
var popupWindow = null
function openFormationwiseDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("Formationwiseueuh?type=Formationwise", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1000,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("Formationwiseueuh?type=Formationwise", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=yes,resizable=yes,top=100,left=100,width=1000,height=600,fullscreen=no");
}

var popupWindow = null
function openPrfwiseueuhDetails() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("Prfwiseueuh?type=prfwise", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("Prfwiseueuh?type=prfwise", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
}
var popupWindow = null
function openAMvcrUpdateReport() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("AMvcrUpdateReport?type=AMvcrUpdate", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("AMvcrUpdateReport?type=AMvcrUpdate", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");	
}
var popupWindow = null
function openBMvcrUpdateReport() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("MvcrUpdateReport?type=MvcrUpdate", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("MvcrUpdateReport?type=MvcrUpdate", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
}
var popupWindow = null
function openCMvcrUpdateReport() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("CMvcrUpdateReport?type=CMvcrUpdate", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("CMvcrUpdateReport?type=CMvcrUpdate", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1000,height=600,fullscreen=no");
}

var popupWindow = null
function loanStoreList() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("loanStoreReport?type=loanStore", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("loanStoreReport?type=loanStore", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
}
var popupWindow = null
function SectorStoreList() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("SectorStoreReport?type=SectorStore", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("SectorStoreReport?type=SectorStore", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
}
var popupWindow = null
function ACSFPStoreList() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("ACSFPStoreReport?type=ACSFPStore", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("ACSFPStoreReport?type=ACSFPStore", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
}
var popupWindow = null
function OverUeStoreList() {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("OverUeStoreReport?type=OverStore", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
		}
		else
		popupWindow = window.open("OverUeStoreReport?type=OverStore", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=1700,height=600,fullscreen=no");
}
</script>
