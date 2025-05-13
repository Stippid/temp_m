<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 
__
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

	<link rel="stylesheet" href="js/cue/cueWatermark.css">
	<script src="js/cue/cueWatermark.js"></script>
	<script src="js/cue/printAllPages.js" type="text/javascript"></script>	
	
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>

<script src="js/miso/mnhDashboard/mnhDashboardJS.js"></script>
<link rel="stylesheet" href="js/miso/mnhDashboard/mnhDashboardCSS.css">


<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
 
<link href="js/amin_module/rbac/datatables/jquery.dataTables.min.css" rel="stylesheet"> 
<script src="js/amin_module/rbac/datatables/jquery.dataTables.js"></script>
<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>


 <style>
 .verticalTableHeader {
    text-align:center;
    white-space:nowrap;
    transform-origin:50% 50%;
    -webkit-transform: rotate(-180deg);
    -moz-transform: rotate(-180deg);
    -ms-transform: rotate(-180deg);
    -o-transform: rotate(-180deg); 
    transform: rotate(-180deg); 
     writing-mode: tb-rl; 
      /*   transform: rotate(-180deg); */
   
}
.verticalTableHeader:before {
    content:'';
    padding-top:-25%;/* takes width as reference, + 10% for faking some extra padding */
    display:inline-block;
    vertical-align:middle;
}
 
 .table tbody {

	max-height: 100px;
	
	
	
} 
#chartdiv {
width:100%;
	height: 300px;
	
	
	
} 
/*   .table tbody {
	display: block;
	max-height: 100px;
	overflow-y: scroll;
	width: 100%;
	scrollbar-width: thin;
} 

.table thead, .table tbody tr {
	display: table;
	width: 100%;
	table-layout: fixed;
} 
 */


</style> 
<div class="animated fadeIn">
	<div class="row">
		<div class="col-md-12" align="center">
			<div class="card">
				<div id="DASHBOARD_tabs">
					<h5 class="trans_heading">
						<b><u>DGMS ARMY</u></b>
					</h5>
					<div class="col-md-12 row from-group">
						<div class="col-md-2" style="text-align: right;">
							<label for="text-input" class=" form-control-label"><b>DATE</b></label>
						</div>
						<div class="col-md-2">
							<input type="date" id="to_dt" name="to_dt" class="form-control-sm form-control" autocomplete="off" readonly>
						</div>
						
					
					</div>
				</div>
				
				<hr>
				<div class="card-body card-block">
				
					<div class="col-md-12" align="center">
						<div class="col-md-6">
							<b style="text-decoration: underline;">BED STATE</b>
							<div id="bed_state" style="width: 100%; height: 400px;"></div>
						</div>
						<div class="col-md-6">
							<b style="text-decoration: underline;">VIP Admission</b>
							<div id="notifiable_diseases" style="width: 100%; height: 400px;"></div>
						</div>
					</div>
					<hr>
					
				
							<label for="text-input" class=" form-control-label"><b>1.BED STATE</b></label>
						<div class="col-md-12">
							<table style="width: 100%;" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead style="color: white; text-align: center;">
									<tr>
										<th colspan="7" align="center"><h4>COMMAND</h4></th>
									</tr>
									
									<tr>
										
										<th >SC</th>
										<th >WC</th>
										<th >EC</th>
										<th >CC</th>
										<th >NC</th>
										<th >SWC</th>
										<th >ARTRAC</th>
									</tr>
									
			
									
								</thead>
								<tbody>
				                         <tr >
										<c:forEach var="item" items="${daily_bed_statelist_army}"  varStatus="num">
											<tr>
											
												 <td >${item[0]}</td>											
												<td >${item[1]}</td>
												<td >${item[2]}</td>
												<td >${item[3]}</td> 
												<td >${item[4]}</td> 
	                                             <td >${item[5]}</td>
	                                             <td >${item[6]}</td>
	                                          
	                                           
	                                             
	                                        
											</tr>
											</c:forEach>
											</tr>
								</tbody>
							
							</table>
						</div>
						
				
						
					<div class="col-md-12" align="center">
						<label for="text-input" class=" form-control-label"><b>2.VIP Admission</b></label>	
						</div>
						<div class="col-md-12" align="center">
							<table style="width: 100%;" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead style="color: white; text-align: center;">
									<tr>
										<th colspan="7" align="center"><h4>COMMAND</h4></th>
									</tr>
									<tr>
										
										<th >SC</th>
										<th >WC</th>
										<th >EC</th>
										<th >CC</th>
										<th >NC</th>
										<th >SWC</th>
										<th >ARTRAC</th>
										
								
									</tr>
								</thead>
								<tbody>
								   <tr >
										<c:forEach var="item" items="${daily_vip_admission_army}"  varStatus="num">
											<tr>
											
												 <td >${item[0]}</td>											
												<td >${item[1]}</td>
												<td >${item[2]}</td>
												<td >${item[3]}</td> 
												<td >${item[4]}</td> 
	                                             <td >${item[5]}</td>
	                                             <td >${item[6]}</td>

											</tr>
											</c:forEach>
											</tr>
								</tbody>
							</table>
						</div>
					
					
						<div class="col-md-12" align="center">
						<label for="text-input" class=" form-control-label"><b>3.Infectious Diseases </b></label>	
						</div>
						<div class="col-md-12">
						
							<table style="width: 100%;" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead style="color: white; text-align: center;">
									<tr>
										<th colspan="8" align="center"><h4>COMMAND</h4></th>
									</tr>
									<tr>
										<th >DISEASES</th>
										<th >SC</th>
										<th >WC</th>
										<th >EC</th>
										<th >CC</th>
										<th >NC</th>
										<th >SWC</th>
										<th >ARTRAC</th>
								
									</tr>
								</thead>
								<tbody>
								   <tr >
										<c:forEach var="item" items="${daily_infection_disease_army}"  varStatus="num">
											<tr>
											
												 <td >${item[0]}</td>											
												<td >${item[1]}</td>
												<td >${item[2]}</td>
												<td >${item[3]}</td> 
												<td >${item[4]}</td> 
	                                            <td >${item[5]}</td>
	                                            <td >${item[6]}</td>
                                                <td >${item[7]}</td>
											</tr>
											</c:forEach>
											</tr>
								</tbody>
							</table>
						</div>
						
					
								
					
					
					</div>
					<div id ="chartdiv" ></div>
				
					
					
				</div>
			</div>
		</div>
	</div>


<script type="text/javascript">
$(document).ready(function () {
	$().getCurDt(to_dt);
	bed_state();
	notifiable_diseases();
	
	var currentdate = new Date();
	var currentyear = currentdate.getFullYear();
});

//LMC Chart 2
function bed_state(){
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("bed_state", am4charts.PieChart);
	var selected;
	var types = [
		{
	  type: "SC",
// 	  percent:"data11" ,
	  color: chart.colors.getIndex(0),
	},
	{
		  type: "WC",
		  percent:"data21" ,
		  color: chart.colors.getIndex(0),
		},
		{
			  type: "EC",
			  percent:"data31" ,
			  color: chart.colors.getIndex(0),
			},
			{
				  type: "CC",
				  percent:"data41" ,
				  color: chart.colors.getIndex(0),
				},
				{
					  type: "NC",
					  percent:"data51" ,
					  color: chart.colors.getIndex(0),
					},
					{
						  type: "SWC",
						  percent:"data61" ,
						  color: chart.colors.getIndex(0),
						},

	{
	  type: "ARTRAC",
	  percent:"data71" ,
	  color: chart.colors.getIndex(1)
	}];

	// Add data
	

// 		chart.data = ${notifiable_diseaselist};
	
	chart.data = generateChartData();

	// Add and configure Series
	var pieSeries = chart.series.push(new am4charts.PieSeries());
	pieSeries.dataFields.value = "percent";
	pieSeries.dataFields.category = "type";
	pieSeries.slices.template.propertyFields.fill = "color";
	pieSeries.slices.template.propertyFields.isActive = "pulled";
	pieSeries.slices.template.strokeWidth = 0;

	function generateChartData() {
		
	  var chartData = [];
	  for (var i = 0; i < types.length; i++) {
	    if (i == selected) {
	      for (var x = 0; x < types[i].subs.length; x++) {
	        chartData.push({
	          type: types[i].subs[x].type,
	          percent: types[i].subs[x].percent,
	          color: types[i].color,
	          pulled: true
	        });
	      }
	    } else {
	      chartData.push({
	        type: types[i].type,
	        percent: types[i].percent,
	        color: types[i].color,
	        id: i
	      });
	    }
	  }
	  return chartData;
	}

	pieSeries.slices.template.events.on("hit", function(event) {
	  if (event.target.dataItem.dataContext.id != undefined) {
	    selected = event.target.dataItem.dataContext.id;
	  } else {
	    selected = undefined;
	  }
	  chart.data = ${daily_bed_occupied};
	});
}



function notifiable_diseases(){
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("notifiable_diseases", am4charts.XYChart);
	chart.scrollbarX = new am4core.Scrollbar();
	


	chart.data = ${daily_vip_admission_army_bar};
 	
	
	

	var categoryAxis = chart.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.dataFields.category = "data2";
	categoryAxis.renderer.grid.template.location = 0;
	categoryAxis.renderer.minGridDistance = 30;
	categoryAxis.renderer.labels.template.horizontalCenter = "right";
	categoryAxis.renderer.labels.template.verticalCenter = "middle";
	categoryAxis.renderer.labels.template.rotation = 270;
	categoryAxis.tooltip.disabled = true;
	categoryAxis.renderer.minHeight = 110;

	var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());
	valueAxis.renderer.minWidth = 50;

	let label = categoryAxis.renderer.labels.template;
	label.wrap = true;
	label.maxWidth = 130;


	var series = chart.series.push(new am4charts.ColumnSeries());
	series.sequencedInterpolation = true;
	series.dataFields.valueY = "data1";
	series.dataFields.categoryX = "data2";
	series.tooltipText = "[{categoryX}: bold]{valueY}[/]";
	series.columns.template.strokeWidth = 0;

	series.tooltip.pointerOrientation = "vertical";

	series.columns.template.column.cornerRadiusTopLeft = 10;
	series.columns.template.column.cornerRadiusTopRight = 10;
	series.columns.template.column.fillOpacity = 0.8;

	var hoverState = series.columns.template.column.states.create("hover");
	hoverState.properties.cornerRadiusTopLeft = 0;
	hoverState.properties.cornerRadiusTopRight = 0;
	hoverState.properties.fillOpacity = 1;

	series.columns.template.adapter.add("fill", function(fill, target) {
	  return chart.colors.getIndex(target.dataItem.index);
	});
	chart.cursor = new am4charts.XYCursor();
}
</script>

<script>

function daily_disease_fn(ps)
{
	

	document.getElementById('unit_name_h').value=$("#unit_name_id"+ps).val();
	document.getElementById('updateForm').submit();
} 

function vip_fn(ps){

	document.getElementById('vip_h').value=$("#unit_name_id"+ps).val();
	document.getElementById('vip_hForm').submit();
	
}

function adm_amc_fn(ps){
	
	
	document.getElementById('adm_amc_h').value=$("#unit_name_id"+ps).val();
	document.getElementById('adm_amc_hForm').submit();
	
}
function bed_fn(ps){
	
	document.getElementById('bed_state_h').value=$("#unit_name_id"+ps).val();
	document.getElementById('bed_state_hForm').submit();
	
}
function unusual_fn(ps){
	
	
	document.getElementById('unusual_h').value=$("#unit_name_id"+ps).val();
	document.getElementById('unusual_hForm').submit();
	
}
///

am5.ready(function() {

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdiv");


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


// Add legend
// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
var legend = chart.children.push(
  am5.Legend.new(root, {
    centerX: am5.p50,
    x: am5.p50
  })
);
var data = ${daily_infection_disease_army};
	

/* var data = [{
  "year": "disease_name",
  "europe": 2.5,
  "namerica": 2.5,
  "asia": 2.1,
  "lamerica": 1,
  "meast": 0.8,
  "africa": 0.4
}, {
  "year": "2022",
  "europe": 2.6,
  "namerica": 2.7,
  "asia": 2.2,
  "lamerica": 0.5,
  "meast": 0.4,
  "africa": 0.3
}, {
  "year": "2023",
  "europe": 2.8,
  "namerica": 2.9,
  "asia": 2.4,
  "lamerica": 0.3,
  "meast": 0.9,
  "africa": 0.5
}]
 */

// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xAxis = chart.xAxes.push(am5xy.CategoryAxis.new(root, {
  categoryField: "disease_name",
  renderer: am5xy.AxisRendererX.new(root, {
    cellStartLocation: 0.1,
    cellEndLocation: 0.9
  }),
  tooltip: am5.Tooltip.new(root, {})
}));

xAxis.data.setAll(data);

var yAxis = chart.yAxes.push(am5xy.ValueAxis.new(root, {
  renderer: am5xy.AxisRendererY.new(root, {})
}));


// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
function makeSeries(name, fieldName) {
  var series = chart.series.push(am5xy.ColumnSeries.new(root, {
    name: name,
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: fieldName,
    categoryXField: "disease_name"
  }));

  series.columns.template.setAll({
    tooltipText: "{name}, {categoryX}:{valueY}",
    width: am5.percent(90),
    tooltipY: 0
  });

  series.data.setAll(data);

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  series.appear();

  series.bullets.push(function () {
    return am5.Bullet.new(root, {
      locationY: 0,
      sprite: am5.Label.new(root, {
        text: "{valueY}",
        fill: root.interfaceColors.get("alternativeText"),
        centerY: 0,
        centerX: am5.p50,
        populateText: true
      })
    });
  });

  legend.data.push(series);
}

makeSeries("Europe", "europe");
makeSeries("North America", "namerica");
makeSeries("Asia", "asia");
makeSeries("Latin America", "lamerica");
makeSeries("Middle East", "meast");
makeSeries("Africa", "africa");


// Make stuff animate on load
// https://www.amcharts.com/docs/v5/concepts/animations/
chart.appear(50, 60);

}); // end am5.ready()


</script>