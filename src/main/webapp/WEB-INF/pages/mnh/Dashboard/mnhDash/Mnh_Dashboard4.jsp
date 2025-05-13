<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>

<script src="js/mnh/mnhDashboard/mnhDashboardJS.js"></script>
<link rel="stylesheet" href="js/mnh/mnhDashboard/mnhDashboardCSS.css"> 

<style>
#Morbidity {
  width: 100%;
  height: 40%;
}
#chartpie {
  width: 100%;
  height: 60%;
}
#chartdivline {
  width: 100%;
  height: 100%;
}
#chartuploadline {
  width: 100%;
  height: 100%;
}
.button {
  /* Green */
  border: none;
  color: white;
  padding: 2px; 
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px; 
  cursor: pointer;
}
.button1 { background-color: gray;border-radius: 5px;}
.button2 {background-color: #672a2a;border-radius: 5px;}

</style>

<div class="animated fadeIn" >
	<div class="row">
	<div class="col-md-12" align="center">
		<div class="card">
		<div id="DASHBOARD_tabs">
		<h5 class="trans_heading"><b><u>MEDICAL AND HEALTH DAILY ACTIVITY DASHBOARD</u></b></h5><br>
			<div class="row">
				 <div class="col-md-12 row form-group">
						 <div class="col-md-2" style="text-align: right;">
                 			   <label for="text-input" class=" form-control-label">Notifiable Diseases</label>
               			 </div>
               			 <div class="col-md-6" style="text-align: right;">
               			        <select id="principal_cause" name="principal_cause" class="form-control-sm form-control">
											<option value="0" >--Select--</option>
										<c:forEach var="item" items="${getmnhDailyDiseaseList}" varStatus="num" >
											<option value="${item}" >${item}</option>
										</c:forEach>
						        </select>
               			 </div>
				  </div>
					 
				  <div class="col-md-12 row form-group" style="margin-top: -10px;">
					     <div class="col-md-2" style="text-align: right;">
	                 		  <label for="text-input" class=" form-control-label">Command</label>
	               		 </div>
	               		 <div class="col-md-6">
	               			   <select name="command" id="command" class="form-control-sm form-control">
									<c:if test="${r_1[0][1] != 'COMMAND'}">
										<option value="ALL">-- All Command --</option>
									</c:if>
									<c:if test="${not empty ml_1[0]}">
										<c:set var="data" value="${ml_1[0]}" />
										<c:set var="datap" value="${fn:split(data,',')}" />
										<c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
											<c:set var="dataf" value="${fn:split(datap[j],':')}" />
											<option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
										</c:forEach>
									</c:if>
							    </select>
						  </div>
				    </div>
					   
					<div class="col-md-12 row form-group" style="margin-top: -10px;">
					       <div class="col-md-2" style="text-align: right;">
					            <label for="text-input" class=" form-control-label">From Date</label>
             			   </div>
             			   <div class="col-md-2">
								   <input type="date" id="from_date" name="from_date" class="form-control-sm form-control">
						   </div>
							
						   <div class="col-md-2" style="text-align: right;">
						        <label for="text-input" class=" form-control-label">To Date</label>
               			   </div> 
               			   <div class="col-md-2">
								  <input type="date" id="to_date" name="to_date" class="form-control-sm form-control">
						   </div>
					 </div>
					
					 <div class="col-md-12 row form-group" align="center">
					        <div class="col-md-4">
					        </div>
					        <div class="col-md-2">
					             <button id="btnClustCmdAct" class="btn btn-primary btn-sm" onclick="Chart1Category();">Refresh Graph</button>
					        </div>
					 </div>
					 
					 <div class="col-md-12 row form-group">
					        <div class="col-md-6">
					             <b style="text-decoration: underline;">CATEGORY WISE </b>
								 <div id="Morbidity" style="width: 80%; height: 250px;"></div>
					        </div>
					        
					        <div class="col-md-6">
					             <b style="text-decoration: underline;">COMMAND WISE </b>
								 <div id="chartpie" style="width: 80%; height: 250px;"></div>
					        </div>
					 </div>
					 
					 <div class="col-md-12 row form-group">
					        <div class="col-md-6">
					             <b style="text-decoration: underline;">DISEASE WISE</b>
						         <div id="chartdivline" style="width: 80%; height: 550px;"></div>
					        </div>   
					 </div>			
					
					 <!--  <div class="col-md-12 row form-group">
					        <div class="col-md-6">
					             <b style="text-decoration: underline;">Low Medical Category</b>
						         <div id="chartuploadline" style="width: 80%; height: 250px;"></div>
					        </div>   
					 </div> -->				
								
			</div>
		</div>
		</div>
	</div>
	</div>
</div>

<script>
$(document).ready(function() {
	//$().getCurDt(to_date);    
	//$().getFirDt(from_date); 
	//chartuploadline();
});	
</script>	
<script>
//Chart 1 Start Here
am4core.useTheme(am4themes_animated);

	var Morbidity = am4core.create("Morbidity", am4charts.PieChart3D);
	Morbidity.hiddenState.properties.opacity = 0; // this creates initial fade-in

	Morbidity.data =${getDash41List};
	
	Morbidity.innerRadius = am4core.percent(40);
	Morbidity.depth = 5;

	//Morbidity.legend = new am4charts.Legend();

	var series = Morbidity.series.push(new am4charts.PieSeries3D());
	series.dataFields.value = "count";
	series.dataFields.depthValue = "count";
	series.dataFields.category = "cat";
	series.slices.template.cornerRadius = 5;
	series.colors.step = 3;

//Chart 1 End Here 


//Chart cmd strength lmc start
function chartuploadline(){
	alert("Chart new");
 	am4core.useTheme(am4themes_animated);
	var chartuploadline = am4core.create("chartuploadline", am4charts.XYChart);
	
	chartuploadline.data = ${getLMCList};
	
	var colorSet = new am4core.ColorSet();
		colorSet.list = ["#4285F4" ,"#34AB53", "#F44336", "#9400D3", "#FBC02D", "#86592d", "#1aff1a"].map(function(color) {
		return new am4core.color(color);
	});
		chartuploadline.colors = colorSet;
	
	// Create axes
	var categoryAxis = chartuploadline.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis.dataFields.category = "Command";
	//categoryAxis.title.text = "Command";
	categoryAxis.renderer.grid.template.location = 0;
	categoryAxis.renderer.minGridDistance = 10;
	categoryAxis.renderer.cellStartLocation = 0.1;
	categoryAxis.renderer.cellEndLocation = 0.7;
	categoryAxis.renderer.labels.template.verticalCenter ="middle";
	categoryAxis.renderer.labels.template.rotation = 270;

	var  valueAxis_cmd = chartuploadline.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cmd.min = 0;
	function createSeries_cmd(field, name, stacked) {
	  	var series1 = chartuploadline.series.push(new am4charts.ColumnSeries());
	  	series1.dataFields.valueY = field;
	  	series1.dataFields.categoryX = "Command";
	  	series1.name = name;
	  	series1.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series1.stacked = stacked;
	  	series1.columns.template.width = am4core.percent(95);

	  	series1.columns.template.adapter.add("fill", function(fill, target) {
			return chartuploadline.colors.getIndex(target.dataItem.index);
		});	   	
	}
	createSeries_cmd("count", "LMC", false); 
	createSeries_cmd("off_str", "OFFICER", false); 
	createSeries_cmd("jco_or_str", "JCO/OR", false);  
}	
//Chart lmc end
//Chart 2 Start Here

var chartpie = am4core.create("chartpie", am4charts.XYChart3D);

chartpie.data = ${getDash42List};

var colorSet2 = new am4core.ColorSet();
	colorSet2.list = ["#4285F4" ,"#34AB53", "#FBBC05", "#EA4335", "#E37400", "#DB4437", "#9400D3"].map(function(color) {
	return new am4core.color(color);
});
chartpie.colors = colorSet2; 

var categoryAxis = chartpie.yAxes.push(new am4charts.CategoryAxis());
categoryAxis.dataFields.category = "Command";
categoryAxis.numberFormatter.numberFormat = "#";
categoryAxis.renderer.inversed = true;

var  valueAxis = chartpie.xAxes.push(new am4charts.ValueAxis()); 

var series1 = chartpie.series.push(new am4charts.ColumnSeries3D());
series1.dataFields.valueX = "count";
series1.dataFields.valueY = "Command";
series1.dataFields.categoryY = "Command";

series1.columns.template.propertyFields.fill = "color";
series1.columns.template.tooltipText = "{categoryY}: [bold]{valueX}[/]"

series1.columns.template.adapter.add("fill", function(fill, target) {
	  return chartpie.colors.getIndex(target.dataItem.index);
	});

//Chart 2 End Here

//Chart 3 Start Here
	var chartdivline = am4core.create("chartdivline", am4charts.XYChart);
	
	chartdivline.data = ${getDash43List};
	
	var colorSet3 = new am4core.ColorSet();
	colorSet3.list = ["#4285F4" ,"#34AB53", "#FBBC05", "#EA4335", "#E37400", "#9400D3", "#DB4437"].map(function(color) {
	return new am4core.color(color);
	});
	chartdivline.colors = colorSet3; 

	// Create axes
	var categoryAxis_mor = chartdivline.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_mor.dataFields.category = "cat";
	//categoryAxis_mor.title.text = "MORBIDITY";
	categoryAxis_mor.renderer.grid.template.location = 0;
	categoryAxis_mor.renderer.minGridDistance = 20;
	categoryAxis_mor.renderer.cellStartLocation = 0.1;
	categoryAxis_mor.renderer.cellEndLocation = 0.7;
	categoryAxis_mor.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_mor.renderer.labels.template.rotation = 310;

	var  valueAxis_mor = chartdivline.yAxes.push(new am4charts.ValueAxis());
	valueAxis_mor.min = 0;
	function createSeries_mor(field, name, stacked) {
	  	var series_mor = chartdivline.series.push(new am4charts.ColumnSeries());
	  	series_mor.dataFields.valueY = field;
	  	series_mor.dataFields.categoryX = "cat";
	  	series_mor.name = name;
	  	series_mor.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	//series_mor.stacked = stacked;
	  	series_mor.columns.template.width = am4core.percent(100);
	  	series_mor.columns.template.adapter.add("fill", function(fill, target) {
			return chartdivline.colors.getIndex(target.dataItem.index);
			});
	  
	}
	createSeries_mor("count", "Count", true);
//Chart 3 End Here
</script>
<script>
function Chart1Category(){
	var d = new Date();
	var c_d = d.getFullYear()+"-"+("0" + (d.getMonth()+1)).slice(-2)+"-"+("0" + d.getDate()).slice(-2);
	
	if($("#principal_cause").val() == ""){
		alert("Please select Disease for Chart");
		$("#principal_cause").focus();
		return false;
	}
	else if($("#from_date").val() == ""){
		alert("Please Enter the Date");
		$("#from_date").focus();
		return false;
	}
	else if($("#from_date").val() > c_d){
		$("#from_date").focus();
		alert("Can't select the Future From Date");
		return false;
	}
	else if($("#to_date").val() == ""){
		alert("Please Enter the Date");
		$("#to_date").focus();
		return false;
	}
	else if($("#to_date").val() > c_d){
		$("#to_date").focus();
		alert("Can't select the Future To Date");
		return false;
	}
	else{
		var principal_cause=$("#principal_cause").val();
		var from_date=$("#from_date").val();
		var to_date=$("#to_date").val();
		var command=$("#command").val();
		
	 $.ajaxSetup({
	    async: false
	});	 
	 
	 //Dash 4 Chart 1 Start Here
	 $.getJSON("getDash41WiseCategory",{principal_cause: principal_cause, from_date: from_date, to_date: to_date, command: command}, function(j) {
			 var cmdArray = new Array();		
		 
		for(var i=0;i<j.length;i++){	  			  				
			var cmdname="";
			if(j[i].cat == "OFFICER"){ cmdname="OFF"};
			if(j[i].cat == "MNS"){ cmdname="MNS"};
			if(j[i].cat == "JCO"){ cmdname="JCO"};
			if(j[i].cat == "OR"){ cmdname="OR"};
			
			//if(cmdname != "")
				cmdArray.push({'cat': cmdname,'count': j[i].count});  				 			
		}	    

		if(cmdArray.length > 0){
		am4core.useTheme(am4themes_animated);
		var Morbidity = am4core.create("Morbidity", am4charts.PieChart3D);
		Morbidity.hiddenState.properties.opacity = 0; // this creates initial fade-in

		Morbidity.data =cmdArray;
		
		Morbidity.innerRadius = am4core.percent(40);
		Morbidity.depth = 5;

		var series = Morbidity.series.push(new am4charts.PieSeries3D());
		series.dataFields.value = "count";
		series.dataFields.depthValue = "count";
		series.dataFields.category = "cat";
		series.slices.template.cornerRadius = 5;
		series.colors.step = 3;
		}
	});  
	//Dash4 Chart 1 End Here
	//Dash4 Chart 2 Start Here
		  	 $.getJSON("getDash42WiseCategory",{principal_cause: principal_cause, from_date: from_date, to_date: to_date, command: command}, function(j) {
    	 var cmdArray1 = new Array();		
 	
			for(var i=0;i<j.length;i++){	  			  				
				var cmdname1="";
			
				if(j[i].command == "HQ SOUTHERN COMMAND"){ cmdname1="SC"};
  				if(j[i].command == "HQ EASTERN COMMAND"){ cmdname1="EC"};
  				if(j[i].command == "HQ WESTERN COMMAND"){ cmdname1="WC"};
  				if(j[i].command == "HQ CENTRAL COMMAND"){ cmdname1="CC"};
  				if(j[i].command == "HQ NORTHERN COMMAND"){ cmdname1="NC"};
  				if(j[i].command == "HQ ARMY TRG COMMAND (ARTRAC)"){ cmdname1="ARTRAC"};
  				if(j[i].command == "HQ ANDAMAN & NICOBAR COMMAND (ANC)"){ cmdname1="ANC"};
  				if(j[i].command == "HQ SOUTH WESTERN COMMAND"){ cmdname1="SWC"};
  				if(j[i].command == "HQ UNITED NATION"){ cmdname1="UN"};
  				if(j[i].command == "MIN OF DEFENCE"){ cmdname1="MOD"};
  				if(j[i].command == "HQ STRATEGIC FORCES COMMAND (SFC)"){ cmdname1="SFC"}; 
				
				
				//if(cmdname1 != "")
					cmdArray1.push({'command': cmdname1,'count': j[i].count});  
				
			}
	
			if(cmdArray1.length > 0){
			am4core.useTheme(am4themes_animated);
			var chartpie = am4core.create("chartpie", am4charts.XYChart3D);
			
			chartpie.data = cmdArray1;

			var colorSet2 = new am4core.ColorSet();
			colorSet2.list = ["#4285F4" ,"#34AB53", "#FBBC05", "#EA4335", "#E37400", "#DB4437", "#9400D3"].map(function(color) {				
			return new am4core.color(color);
			});
			chartpie.colors = colorSet2;

			var categoryAxis = chartpie.yAxes.push(new am4charts.CategoryAxis());
			categoryAxis.dataFields.category = "command";
			categoryAxis.numberFormatter.numberFormat = "#";
			categoryAxis.renderer.inversed = true;

			var  valueAxis = chartpie.xAxes.push(new am4charts.ValueAxis()); 

			var series1 = chartpie.series.push(new am4charts.ColumnSeries3D());
			series1.dataFields.valueX = "count";
			series1.dataFields.categoryY = "command";
			series1.name = "Count";
			series1.columns.template.propertyFields.fill = "color";
			series1.columns.template.tooltipText = "{categoryY}: [bold]{valueX}[/]"

			series1.columns.template.column3D.strokeOpacity = 0.2;
			series1.columns.template.adapter.add("fill", function(fill, target) {
				  return chartpie.colors.getIndex(target.dataItem.index);
				});
				
			}
    }); 
	//Dash4 Chart 2 End Here
	//Dash4 Chart 3 Start Here
	  $.getJSON("getDash43WiseCategory",{principal_cause: principal_cause, from_date: from_date, to_date: to_date, command: command}, function(j) {
			 var cmdArray3 = new Array();		
		 
		for(var i=0;i<j.length;i++){	  			  				
			var cmdname3="";
			/* if(j[i].month == "1"){ cmdname3="Jan"};
			if(j[i].month == "2"){ cmdname3="Feb"};
			if(j[i].month == "3"){ cmdname3="Mar"};
			if(j[i].month == "4"){ cmdname3="Apr"};
			if(j[i].month == "5"){ cmdname3="May"};
			if(j[i].month == "6"){ cmdname3="Jun"};
			if(j[i].month == "7"){ cmdname3="Jul"};
			if(j[i].month == "8"){ cmdname3="Aug"};
			if(j[i].month == "9"){ cmdname3="Sep"};
			if(j[i].month == "10"){ cmdname3="Oct"};
			if(j[i].month == "11"){ cmdname3="Nov"};
			if(j[i].month == "12"){ cmdname3="Dec"}; */
			//if(cmdname != "")
				cmdArray3.push({'cat':cmdname3,'count': j[i].count});  				 			
		}	    

		if(cmdArray3.length > 0){
		 am4core.useTheme(am4themes_animated);
		 var chartdivline = am4core.create("chartdivline", am4charts.XYChart);

		// Add data
		chartdivline.data = cmdArray3; 
		var colorSet3 = new am4core.ColorSet();
		colorSet3.list = ["#4285F4" ,"#34AB53", "#FBBC05", "#EA4335", "#E37400", "#9400D3", "#DB4437"].map(function(color) {
		return new am4core.color(color);
		});
		chartdivline.colors = colorSet3; 
		// Create axes
		var categoryAxis_mor = chartdivline.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis_mor.dataFields.category = "cat";
		//categoryAxis_mor.title.text = "DISEASE";
		categoryAxis_mor.renderer.grid.template.location = 0;
		categoryAxis_mor.renderer.minGridDistance = 20;
		categoryAxis_mor.renderer.cellStartLocation = 0.1;
		categoryAxis_mor.renderer.cellEndLocation = 0.7;
		categoryAxis_mor.renderer.labels.template.verticalCenter ="middle";
		categoryAxis_mor.renderer.labels.template.rotation = 310;

		var  valueAxis_mor = chartdivline.yAxes.push(new am4charts.ValueAxis());
		valueAxis_mor.min = 0;
		function createSeries_mor(field, name, stacked) {
		  	var series_mor = chartdivline.series.push(new am4charts.ColumnSeries());
		  	series_mor.dataFields.valueY = field;
		  	series_mor.dataFields.categoryX = "cat";
		  	series_mor.name = name;
		  	series_mor.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
		  	//series_mor.stacked = stacked;
		  	series_mor.columns.template.width = am4core.percent(100);
		  	series_mor.columns.template.adapter.add("fill", function(fill, target) {
				return chartdivline.colors.getIndex(target.dataItem.index);
				});
		  
		}
		createSeries_mor("count", "count", true);

		}
});
	//Dash4 Chart 3 End Here
	}
}
</script>