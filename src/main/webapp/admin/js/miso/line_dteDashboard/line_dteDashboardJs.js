function downloadReport(filename){
	document.getElementById("filename").value=filename;
	document.getElementById("getDownloadDocFMNDashboardForm").submit();
}
function progressBar(mvcrProgressCount,avehProgressCount,cvehProgressCount,mcrProgressCount){
	animateElements(mvcrProgressCount);
	animateElementsAVeh(avehProgressCount);
	animateElementsCVeh(cvehProgressCount);
	animateElementsMCR(mcrProgressCount);
}
function animateElements(mvcrProgressCount) {
	$('#progressbar').data('animate', true);
	$('#progressbar').find('.circle').circleProgress({
		startAngle: -Math.PI / 2,
		value: mvcrProgressCount / 100,
		size: 160,
		thickness: 30,
		emptyFill: "#ffe6ee",
		fill: {
			color: '#990033'
		}
	}).on('circle-animation-progress', function (event, progress, stepValue) {
		$("div#mvcr").text((stepValue*100).toFixed(2) + "%");
	}).stop();
}
function animateElementsAVeh(avehProgressCount) {
	$('#progressbaraveh').data('animate', true);
	$('#progressbaraveh').find('.circle').circleProgress({
		startAngle: -Math.PI / 2,
		value: avehProgressCount / 100,
		size: 160,
		thickness: 30,
		emptyFill: "#d5e5f6",
		fill: {
			color: '#14385f'
		}
	}).on('circle-animation-progress', function (event, progress, stepValue) {
		$("div#aveh").text((stepValue*100).toFixed(2) + "%");
	}).stop();
}
function animateElementsCVeh(cvehProgressCount) {
	$('#progressbarcveh').data('animate', true);
	$('#progressbarcveh').find('.circle').circleProgress({
		startAngle: -Math.PI / 2,
		value: cvehProgressCount / 100,
		size: 160,
		thickness: 30,
		emptyFill: "#d9f2d9",
		fill: {
			color: '#336600'
		}
	}).on('circle-animation-progress', function (event, progress, stepValue) {
		$("div#cveh").text((stepValue*100).toFixed(2) + "%");
	}).stop();
}
function animateElementsMCR(mcrProgressCount) {
	$('#progressbarmcr').data('animate', true);
	$('#progressbarmcr').find('.circle').circleProgress({
		startAngle: -Math.PI / 2,
		value: mcrProgressCount / 100,
		size: 160,
		thickness: 30,
		emptyFill: "#D8BFD8",
		fill: {
			color: '#800080'
		}
	}).on('circle-animation-progress', function (event, progress, stepValue) {
		$("div#mcr").text((stepValue*100).toFixed(2) + "%");
	}).stop();
}

//Start Orbat Dashboard
function chrodChartCommandMov()
{	
	if($("#fromDateChord").val() == "")
	{
		alert("Please select From");
		$("#fromDateChord").focus();
		return false;
	}
	else if($("#toDateChord").val() == "")
	{
		alert("Please select To Date");
		$("#toDateChord").focus();
		return false;
	}	
	else{
		var fromDate=$("#fromDateChord").val();
		var toDate=$("#toDateChord").val();

		$.ajaxSetup({
		    async: false
		});					
		$.post("getCommWiseUnitMovFormation?"+key+"="+value,{fromDate: fromDate, toDate: toDate}).done(function(j) {
				var cmdArray = new Array();	
				var totalArray = new Array();
	  			for(var i=0;i<j.length;i++){	  			  				
	  				var cmdname="";
	  				if(j[i].cmd_code == "1"){ cmdname="SC"};
	  				if(j[i].cmd_code == "2"){ cmdname="EC"};
	  				if(j[i].cmd_code == "3"){ cmdname="WC"};
	  				if(j[i].cmd_code == "4"){ cmdname="CC"};
	  				if(j[i].cmd_code == "5"){ cmdname="NC"};
	  				if(j[i].cmd_code == "6"){ cmdname="ARTRAC"};
	  				if(j[i].cmd_code == "7"){ cmdname="ANC"};
	  				if(j[i].cmd_code == "8"){ cmdname="SWC"};
	  				if(j[i].cmd_code == "9"){ cmdname="UN"};
	  				if(j[i].cmd_code == "0"){ cmdname="MOD"};
	  				if(j[i].cmd_code == "A"){ cmdname="SFC"};	  				
	  				if(!j[i].count1 == 0 && cmdname !="")
  					{
  						cmdArray.push({from: cmdname, to: "SC", value: j[i].count1});
  						totalArray.push(j[i].count1);
  					}
	  				if(!j[i].count2 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "EC", value: j[i].count2});
	  					totalArray.push(j[i].count2);
	  				}
	  				if(!j[i].count3 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "WC", value: j[i].count3});
	  					totalArray.push(j[i].count3);
	  				}
	  				if(!j[i].count4 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "CC", value: j[i].count4});
	  					totalArray.push(j[i].count4);
	  				}
	  				if(!j[i].count5 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "NC", value: j[i].count5});
	  					totalArray.push(j[i].count5);
	  				}
	  				if(!j[i].count6 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "ARTRAC", value: j[i].count6});
	  					totalArray.push(j[i].count6);
	  				}
	  				if(!j[i].count7 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "ANC", value: j[i].count7});
	  					totalArray.push(j[i].count7);
	  				}
	  				if(!j[i].count8 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "SWC", value: j[i].count8});
	  					totalArray.push(j[i].count8);
	  				}
	  				if(!j[i].count9 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "UN", value: j[i].count9});
	  					totalArray.push(j[i].count9);
	  				}
	  				if(!j[i].count10 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "MOD", value: j[i].count10});
	  					totalArray.push(j[i].count10);
	  				}
	  				if(!j[i].count11 == 0 && cmdname !="")
	  				{
	  					cmdArray.push({from: cmdname, to: "SFC", value: j[i].count11});
	  					totalArray.push(j[i].count11);
					}
	  			}	    
	  			var totalVal=totalArray.reduce((a, b) => a + b, 0);	  			
	  			document.getElementById("getActUnitsInFormation").innerHTML=totalVal;
	  		am4core.useTheme(am4themes_animated);
    		var chart = am4core.create("chordCommdMovdiv", am4charts.ChordDiagram);
    		chart.data= cmdArray;
    		
    	    chart.dataFields.fromName = "from";
    		chart.dataFields.toName = "to";
    		chart.dataFields.value = "value";
    		
    		var colorSet = new am4core.ColorSet();
    		colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
   				return new am4core.color(color);
			});
			chart.colors = colorSet;	
			
    		// make nodes draggable
    		var nodeTemplate = chart.nodes.template;
    		nodeTemplate.readerTitle = "Click to show/hide or drag to rearrange";
    		nodeTemplate.showSystemTooltip = true;
    		nodeTemplate.cursorOverStyle = am4core.MouseCursorStyle.pointer;
    		
    		var nodeLink = chart.links.template;
    		nodeLink.opacity = 1;
    		nodeLink.defaultState.properties.opacity = 1;
    		
    		var linkHoverState = nodeLink.states.create("hover");
    		linkHoverState.properties.opacity = 1.5;
    		
    		var bullet = nodeLink.bullets.push(new am4charts.CircleBullet());
    		bullet.fillOpacity = 1.5;
    		bullet.circle.radius = 5;
    		bullet.locationX = 1;	
    	
    		// create animations
    		chart.events.on("ready", function() {
    		    for (var i = 0; i < chart.links.length; i++) {
    		        var link = chart.links.getIndex(i);
    		        var bullet = link.bullets.getIndex(0);
    	
    		        animateBullet(bullet);
    		    }
    		})
    	
    		function animateBullet(bullet) {
    		    var duration = 3000 * Math.random() + 4500;
    		    var animation = bullet.animate([{ property: "locationX", from: 0, to: 1 }], duration)
    		    animation.events.on("animationended", function(event) {
    		    	 animateBullet(event.target.object);
    		    })
    		}
    		chart.innerRadius = -20;
    	
    		var slice = chart.nodes.template.slice;
    		slice.stroke = am4core.color("#000");
    		slice.strokeOpacity = 0.5;
    		slice.strokeWidth = 1;
    		slice.cornerRadius = 8;
    		slice.innerCornerRadius = 0;
    			
    		var label = chart.nodes.template.label;
    		label.fontSize = 11;
    		label.fontWeight="bold";
    		label.fill = am4core.color("#555");
    		label.horizontalCenter = "middle";
    		label.verticalCenter = "middle";
    		label.wrap = true;	
    		//label.truncate = true;
    		label.maxWidth = 100;
    		label.rotation = 360;	
    		
    		chart.nonRibbon = false;	
    		var link = chart.links.template;
    		link.colorMode = "gradient";
    		link.fillOpacity = 0.5;
    		link.middleLine.strokeWidth = 2;
    		link.middleLine.strokeOpacity = 0.4;
	    }).fail(function(xhr, textStatus, errorThrown) {
	}); 
	}	
}

function showUnitMovReport(roleSubAccess111)
{
	if(document.getElementById("getActUnitsInFormation").innerHTML == 0)
	{
		alert("Data Not Available");		
	}	
	else{
		var modal = document.getElementById('myModalUnitMovReport');
		modal.style.display = "block";	
		if(roleSubAccess111  == "Command" || roleSubAccess111 == "HeadQuarter"){
			document.getElementById("h4UnitMovHeadId").innerHTML="Inter Command Move Report";
		}else{
			document.getElementById("h4UnitMovHeadId").innerHTML="Move Report";
		}
		
		var fromDate=$("#fromDateChord").val();
		var toDate=$("#toDateChord").val();
		$.ajaxSetup({
		    async: false
		});					
		$.post("getUnitMovReport?"+key+"="+value,{fromDate: fromDate, toDate: toDate}).done(function(j) {
	    	$("#UnitMovReportTbody").empty();
	    	if(j.length>0)
	    	{
	    		var row="", m=0;
				for(var i=0;i<j.length;i++){
					m=i+1;
					row += "<tr>";
					row += "<th width='5%' style='text-align:center;'>"+m+"</th>";
					row += "<th width='5%' style='text-align:center;'>"+j[i].sus_no+"</th>";
					row += "<th width='13%'>"+j[i].unit_name+"</th>";
					row += "<th width='5%' style='text-align:center;'>"+j[i].nmb_date+"</th>";					
					if(j[i].frm_cmd_name != null){
						row += "<th width='9%'>"+j[i].frm_cmd_name+"</th>";
					}else{
						row += "<th width='9%'></th>";
					}
					if(j[i].frm_coprs_name != null){
						row += "<th width='9%'>"+j[i].frm_coprs_name+"</th>";
					}else{
						row += "<th width='9%'></th>";
					}
					if(j[i].frm_div_name != null){
						row += "<th width='9%'>"+j[i].frm_div_name+"</th>";
					}else{
						row += "<th width='9%'></th>";
					}
					if(j[i].frm_bde_name != null){
						row += "<th width='9%'>"+j[i].frm_bde_name+"</th>";
					}else{
						row += "<th width='9%'></th>";
					}
					if(j[i].to_cmd_name != null){
						row += "<th width='9%'>"+j[i].to_cmd_name+"</th>";
					}else{
						row += "<th width='9%'></th>";
					}
					if(j[i].to_coprs_name != null){
						row += "<th width='9%'>"+j[i].to_coprs_name+"</th>";
					}else{
						row += "<th width='9%'></th>";
					}
					if(j[i].to_div_name != null){
						row += "<th width='9%'>"+j[i].to_div_name+"</th>";
					}else{
						row += "<th width='9%'></th>";
					}
					if(j[i].to_bde_name != null){
						row += "<th width='9%'>"+j[i].to_bde_name+"</th>";
					}else{
						row += "<th width='9%'></th>";
					}
					row += "</tr>";
				}
				$("#UnitMovReportTbody").append(row);
				//$("#UnitMovReport").DataTable();				
				//$("div#divwatermark").val('').addClass('watermarked');		
				//watermarkreport(); 
	    	}else{
	    		row += "<tr>";
				row += "<th colspan='12' style='color: red;text-align: center !important;'> Data Not Available</th>";
				row += "</tr>";
				$("#UnitMovReportTbody").append(row);
	    	}
	    }).fail(function(xhr, textStatus, errorThrown) {
		});	    
	}
}
//end Orbat Dashboard
/*
// Start CUE Dashboard
function ueManpowerTable(){		
	$.post("getUEManpowerData?"+key+"="+value).done(function(j){
		var colArray = new Array();				
		$("#unitEntitlementTbody").empty();
		if(j.length>0)
		{
			var row="";
			for(var i=0;i<j.length;i++){
				var colname=j[i].colname;		
				if(colname != ""){
					row += "<tr>";
					row += "<th width='55%'>"+colname+"</th>";
					row += "<th width='15%' style='text-align:center;'>"+j[i].officer+"</th>";
					row += "<th width='15%' style='text-align:center;'>"+j[i].jco+"</th>";
					row += "<th width='15%' style='text-align:center;'>"+j[i].or+"</th>";					
					row += "</tr>";
				}
			}
			$("#unitEntitlementTbody").append(row);
		}else{
			row += "<tr>";
			row += "<th colspan='12' style='color: red;text-align: center !important;'> Data Not Available</th>";
			row += "</tr>";
			$("#unitEntitlementTbody").append(row);
		}
	}); 
}*/
function ueManpowerChart()
{		
	$.post("getUEManpowerData?"+key+"="+value).done(function(j){
		var colArray = new Array();				
		for(var i=0;i<j.length;i++){	  			  				
			var colname=j[i].colname;		
			if(colname != "")
				colArray.push({'colname': colname,'officer': j[i].officer,'jco': j[i].jco, 'jco_or': j[i].jco_or, 'or': j[i].or});  				 			
		}	    
		if(colArray.length > 0){ 
			am4core.useTheme(am4themes_animated);
			var clusterddiv = am4core.create("ueManpowerdiv", am4charts.XYChart);
			clusterddiv.scrollbarX = new am4core.Scrollbar();
			clusterddiv.data =colArray;
			
			var categoryAxis_cluster = clusterddiv.yAxes.push(new am4charts.CategoryAxis());
			categoryAxis_cluster.dataFields.category = "colname";
			categoryAxis_cluster.title.text =""; //"PRF Name";
			categoryAxis_cluster.renderer.grid.template.location = 0;
			categoryAxis_cluster.renderer.minGridDistance = 30;
			categoryAxis_cluster.renderer.cellStartLocation = 0.1;
			categoryAxis_cluster.renderer.cellEndLocation = 0.9;
			categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
			categoryAxis_cluster.renderer.labels.template.rotation = 360; 
								
			var  valueAxis_cluster = clusterddiv.xAxes.push(new am4charts.ValueAxis());
			valueAxis_cluster.min = 0;
			valueAxis_cluster.title.text =""; // "No. of Units";
			valueAxis_cluster.rangeChangeDuration = 500;
			valueAxis_cluster.calculateTotals = true;
							
			var label = categoryAxis_cluster.renderer.labels.template;
			label.wrap = true;
			label.fontSize = 11;
			label.fontWeight = "bold";
			label.maxWidth = 120;	
			
			var label1 = categoryAxis_cluster.title;
			label1.fontSize = 15;
			label1.fontWeight = "bold";
			label1.stroke=am4core.color("#0000ff");
			label1.strokeWidth = 0.6;
			label1.strokeOpacity = 0.2;
			label1.textDecoration = "underline";
			
			var label12 = valueAxis_cluster.title;
			label12.fontSize = 15;
			label12.fontWeight = "bold";
			label12.stroke=am4core.color("#0000ff");
			label12.strokeWidth = 0.6;
			label12.strokeOpacity = 0.2;
			label12.textDecoration = "underline";
		
			var colorSet = new am4core.ColorSet();
			colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
				return new am4core.color(color);
			});
			clusterddiv.colors = colorSet;	
			clusterddiv.maskBullets = true;
			// Create series
			function createSeries_cluster(field, name) {
			  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
			  	series_cluster.dataFields.valueX = field;
			  	series_cluster.dataFields.categoryY = "colname";
			  	series_cluster.name = name;
			  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueX}[/]";
			  	series_cluster.columns.template.height = am4core.percent(70);
			  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
			  	series_cluster.sequencedInterpolation = true;
			  	series_cluster.stacked = true;				  	
			}
			createSeries_cluster("officer", "Officer", true);
			createSeries_cluster("jco", "JCO", true);	
			createSeries_cluster("or", "OR", true);	
			clusterddiv.legend = new am4charts.Legend();
			clusterddiv.legend.useDefaultMarker = true;	
			var marker = clusterddiv.legend.markers.template.children.getIndex(0);
			marker.cornerRadius(12, 12, 12, 12);
			marker.strokeWidth = 2;
			marker.strokeOpacity = 1;
			marker.stroke = am4core.color("#ccc"); 
		}
    }).fail(function(xhr, textStatus, errorThrown) {
	}); 
}
// End CUE Dashboard

// Start TMS Dashboard
function getPRFList(val)
{
	if(val !="")
	{
	    $.post("getTptSummaryInPRFList?"+key+"="+value,{type : val}).done(function(j) {
			var options = '<option value="0">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length; i++) {
				options += '<option value="'+j[i].prf_code+'" name="' + j[i].group_name+ '" >'+ j[i].group_name+ '</option>';
			}	
			$("select#prfTptList").html(options); 
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	}
	else {
		$("select#prfTptList").html("");
	}
}

function tptSummaryChart(){		
	var type=$("select#typeOfVehicle").val();
	if(type == "")
	{
		alert("Please select Type of Vehicle");
		$("select#typeOfVehicle").focus();
		$("div#tptSummarydiv").empty();
		return false;
	}
	else{
		$.ajaxSetup({
		    async: false
		});
		var colArray = new Array();				
		$.post("getTptSummaryInPrfWithTypeVehData?"+key+"="+value,{type: type, prf_code : $("select#prfTptList").val()}).done(function(j) {
			for(var i=0;i<j.length;i++){	  			  				
	  			var colname=j[i].colname;		
	  			if(colname != "")
	  				colArray.push({'colname': colname,'colcode': j[i].colcode,'ue': j[i].ue, 'uh': j[i].uh});  				 			
	  		}	    
		}).fail(function(xhr, textStatus, errorThrown) {
		});
		
 		if(colArray.length <= 0){
 			alert("Data not available");
 		}
		
 		am4core.useTheme(am4themes_animated);
		var clusterddiv = am4core.create("tptSummarydiv", am4charts.XYChart);
		clusterddiv.scrollbarX = new am4core.Scrollbar();			
		
		clusterddiv.data =colArray;
		
		var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
		categoryAxis_cluster.dataFields.category = "colname";
		categoryAxis_cluster.title.text ="";  // "Command Name";
		categoryAxis_cluster.renderer.grid.template.location = 0;
		categoryAxis_cluster.renderer.minGridDistance = 30;
		categoryAxis_cluster.renderer.cellStartLocation = 0.1;
		categoryAxis_cluster.renderer.cellEndLocation = 0.9;
		categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
		categoryAxis_cluster.renderer.labels.template.rotation = 360; 
							
		var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
		valueAxis_cluster.min = 0;
		valueAxis_cluster.title.text =""; // "No. of Units";
		valueAxis_cluster.rangeChangeDuration = 500;
		
		var label = categoryAxis_cluster.renderer.labels.template;
		label.wrap = true;
		label.fontSize = 11;
		label.fontWeight = "bold";
		label.maxWidth = 120;	
		
		var label1 = categoryAxis_cluster.title;
		label1.fontSize = 15;
		label1.fontWeight = "bold";
		label1.stroke=am4core.color("#0000ff");
		label1.strokeWidth = 0.6;
		label1.strokeOpacity = 0.2;
		label1.textDecoration = "underline";
		
		var label12 = valueAxis_cluster.title;
		label12.fontSize = 15;
		label12.fontWeight = "bold";
		label12.stroke=am4core.color("#0000ff");
		label12.strokeWidth = 0.6;
		label12.strokeOpacity = 0.2;
		label12.textDecoration = "underline";

		var colorSet = new am4core.ColorSet();
		colorSet.list = ["#FBC02D","#388E3C", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
			return new am4core.color(color);
		});
		clusterddiv.colors = colorSet;	
		clusterddiv.maskBullets = true;
		function createSeries_cluster(field, name) {
		  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
		  	series_cluster.dataFields.valueY = field;
		  	series_cluster.dataFields.categoryX = "colname";
		  	series_cluster.name = name;
		  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
		  	series_cluster.columns.template.height = am4core.percent(100);
		  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
		  	series_cluster.sequencedInterpolation = true;
		  	
		  	var bullet = series_cluster.bullets.push(new am4charts.LabelBullet);
		    bullet.label.text = "{colcode}";
		    bullet.label.rotation = 90;
		    bullet.label.truncate = false;
		    bullet.label.hideOversized = false;
		    bullet.label.horizontalCenter = "left";
		    bullet.locationY = 1;
		    bullet.dy = 10;
		    
		    series_cluster.columns.template.events.on("hit",function(ev){
		  		if($("select#prfTptList").val() == "0"){	
		  			alert("Please select PRF Name");
		  			$("select#prfTptList").focus();
		  			return false;
		  		}
		  		else{
		  			var val=ev.target.dataItem.dataContext.colcode;
		  			$.post("getVehWiseAvgKMSData?"+key+"="+value,{type: type,form_code : val, prf_code : $("select#prfTptList").val()}).done(function(j) {	  			
		  				var getVehWiseAvgKMS = new Array();
			  	 		for(var i=0;i<j.length;i++){	  			  				
			  				var avg=j[i].avg;
			  				if(avg != "")
			  					getVehWiseAvgKMS.push(avg);  				 			
			  			}
						if(getVehWiseAvgKMS.length > 0){
			  				var modal = document.getElementById('myModalTptPrf');
			  				modal.style.display = "block";
			  				
			  				document.getElementById("h4HeadId").innerHTML="Avg KMS In "+$("select#prfTptList option:selected").text();
			  				vehiclesWiseAvgKMS(getVehWiseAvgKMS);
			  			}
			  			else
			  			{
			  				var modal = document.getElementById('myModalTptPrf');
			  				modal.style.display = "none";	
			  				document.getElementById("h4HeadId").innerHTML="";
			  			}  		
				    }).fail(function(xhr, textStatus, errorThrown) {
					});
		  		}
		  	},this); 
		}
		createSeries_cluster("ue", "UE", true);
		createSeries_cluster("uh", "UH", true);				
		clusterddiv.legend = new am4charts.Legend();
		clusterddiv.legend.useDefaultMarker = true;	
		var marker = clusterddiv.legend.markers.template.children.getIndex(0);
		marker.cornerRadius(12, 12, 12, 12);
		marker.strokeWidth = 2;
		marker.strokeOpacity = 1;
		marker.stroke = am4core.color("#ccc"); 
	}
}

function vehiclesWiseAvgKMS(getVehWiseAvgKMS)
{
	var getVal=0;
	if(getVehWiseAvgKMS[0] != null && getVehWiseAvgKMS[0] != "null"){
		getVal=getVehWiseAvgKMS[0];
	}
	am4core.useTheme(am4themes_animated);	
	var chart3 = am4core.create("tptSummaryPrfDiv", am4charts.GaugeChart);
	chart3.innerRadius = am4core.percent(82);
	
	var axis3 = chart3.xAxes.push(new am4charts.ValueAxis());
	axis3.strictMinMax = true;
	axis3.renderer.radius = am4core.percent(80);
	axis3.renderer.inside = true;
	axis3.renderer.line.strokeOpacity = 1;
	axis3.renderer.ticks.template.strokeOpacity = 1;
	axis3.renderer.ticks.template.length = 10;
	axis3.renderer.grid.template.disabled = false;
	axis3.renderer.labels.template.radius = 40;
	axis3.renderer.labels.template.adapter.add("text", function(text) {
	  return text + "%";
	})

	var axis2 = chart3.xAxes.push(new am4charts.ValueAxis());
	axis2.min = 0;
	axis2.max = 200000;
	axis2.renderer.innerRadius = 10
	axis2.strictMinMax = true;
	axis2.renderer.labels.template.disabled = false;
	axis2.renderer.ticks.template.disabled = false;
	axis2.renderer.grid.template.disabled = false;

	var range1 = axis2.axisRanges.create();
	range1.value = 0;
	range1.endValue = 50000;
	range1.axisFill.fillOpacity = 1;
	range1.axisFill.fill = am4core.color("#f0d216");
	
	var range2 = axis2.axisRanges.create();
	range2.value = 50000;
	range2.endValue = 70000;
	range2.axisFill.fillOpacity = 1;
	range2.axisFill.fill = am4core.color("#80bd5c");
		
	var range3 = axis2.axisRanges.create();
	range3.value = 70000;
	range3.endValue = 200000;
	range3.axisFill.fillOpacity = 1;
	range3.axisFill.fill = am4core.color("#e8103b");
	
	var label = chart3.radarContainer.createChild(am4core.Label);
	label.isMeasured = false;
	label.fontSize = 20;
	label.fontWeight = "bold";
	label.fill=am4core.color("#1a1617");
	label.stroke=am4core.color("#1a1617");
	label.strokeWidth = 0.6;
	label.strokeOpacity = 0.2;
	label.text =getVal+" kms";
	label.horizontalCenter = "middle";
	
	var hand = chart3.hands.push(new am4charts.ClockHand());
	hand.axis = axis2;
	hand.startWidth = 15;
	hand.pin.disabled = false;
	hand.value =getVal;
	
	var legend = new am4charts.Legend();
	legend.isMeasured = true;
	legend.y = am4core.percent(100);
	legend.verticalCenter = "bottom";
	legend.parent = chart3.chartContainer;
	legend.data = [{
		"name": "Low",
	 	"fill": "#f0d216"
	}, {
	 	"name": "Medium",
	 	"fill": "#80bd5c"
	},{
		"name": "High",
		"fill": "#e8103b"
	}];
}
/* function tptSummaryByPrf(getTptWisePrf)
{	
	am4core.useTheme(am4themes_animated);
	var clusterddiv = am4core.create("tptSummaryPrfDiv", am4charts.XYChart);
	clusterddiv.scrollbarX = new am4core.Scrollbar();			
	
	clusterddiv.data =getTptWisePrf;
	
	var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_cluster.dataFields.category = "group_name";
	categoryAxis_cluster.title.text =""; //"PRF Name";
	categoryAxis_cluster.renderer.grid.template.location = 0;
	categoryAxis_cluster.renderer.minGridDistance = 30;
	categoryAxis_cluster.renderer.cellStartLocation = 0.1;
	categoryAxis_cluster.renderer.cellEndLocation = 0.9;
	categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_cluster.renderer.labels.template.rotation = 360; 
						
	var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cluster.min = 0;
	valueAxis_cluster.title.text =""; // "No. of Units";
	valueAxis_cluster.rangeChangeDuration = 500;
	
	var label = categoryAxis_cluster.renderer.labels.template;
	label.wrap = true;
	label.fontSize = 11;
	label.fontWeight = "bold";
	label.maxWidth = 120;	
	
	var label1 = categoryAxis_cluster.title;
	label1.fontSize = 15;
	label1.fontWeight = "bold";
	label1.stroke=am4core.color("#0000ff");
	label1.strokeWidth = 0.6;
	label1.strokeOpacity = 0.2;
	label1.textDecoration = "underline";
	
	var label12 = valueAxis_cluster.title;
	label12.fontSize = 15;
	label12.fontWeight = "bold";
	label12.stroke=am4core.color("#0000ff");
	label12.strokeWidth = 0.6;
	label12.strokeOpacity = 0.2;
	label12.textDecoration = "underline";

	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	clusterddiv.colors = colorSet;	
	
	function createSeries_cluster(field, name) {
	  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
	  	series_cluster.dataFields.valueY = field;
	  	series_cluster.dataFields.categoryX = "group_name";
	  	series_cluster.name = name;
	  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_cluster.columns.template.height = am4core.percent(100);
	  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
	  	series_cluster.sequencedInterpolation = true;
	}
	
	createSeries_cluster("ue", "UE", true);
	createSeries_cluster("uh", "UH", true);
	
	clusterddiv.legend = new am4charts.Legend();
	clusterddiv.legend.useDefaultMarker = true;	
	var marker = clusterddiv.legend.markers.template.children.getIndex(0);
	marker.cornerRadius(12, 12, 12, 12);
	marker.strokeWidth = 2;
	marker.strokeOpacity = 1;
	marker.stroke = am4core.color("#ccc");
} */
function prfWiseTptClassChart()
{		
	if($("select#typeOfVehicle").val() == "")
	{
		alert("Please select Type of Vehicle");
		$("select#typeOfVehicle").focus();
		return false;
	}
	else{
		var type = $("select#typeOfVehicle").val();
		var prf = $("select#prfTptList").val();
		$.ajaxSetup({
		    async: false
		});					
		$.post("getPRFWiseTptClassData?"+key+"="+value,{type: type,prf : prf}).done(function(j) {
			var colArray = new Array();				
  			for(var i=0;i<j.length;i++){	  			  				
  				var group_name=j[i].group_name;		
  				if(group_name != "")
  					colArray.push({'group_name': group_name,'prf_code': j[i].prf_code,'cl1': j[i].cl1, 'cl2': j[i].cl2, 'cl3': j[i].cl3, 'cl4': j[i].cl4});  				 			
  			}	    
			if(colArray.length > 0){
				am4core.useTheme(am4themes_animated);
				var clusterddiv = am4core.create("prfTptClassdiv", am4charts.XYChart);
				clusterddiv.scrollbarX = new am4core.Scrollbar();			
				clusterddiv.data =colArray;
				
				var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
				categoryAxis_cluster.dataFields.category = "group_name";
				categoryAxis_cluster.title.text =""; //"PRF Name";
				categoryAxis_cluster.renderer.grid.template.location = 0;
				categoryAxis_cluster.renderer.minGridDistance = 30;
				categoryAxis_cluster.renderer.cellStartLocation = 0.1;
				categoryAxis_cluster.renderer.cellEndLocation = 0.9;
				categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
				categoryAxis_cluster.renderer.labels.template.rotation = 360; 
									
				var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
				valueAxis_cluster.min = 0;
				valueAxis_cluster.title.text =""; // "No. of Units";
				valueAxis_cluster.rangeChangeDuration = 500;
				
				var label = categoryAxis_cluster.renderer.labels.template;
				label.wrap = true;
				label.fontSize = 11;
				label.fontWeight = "bold";
				label.maxWidth = 120;	
				
				var label1 = categoryAxis_cluster.title;
				label1.fontSize = 15;
				label1.fontWeight = "bold";
				label1.stroke=am4core.color("#0000ff");
				label1.strokeWidth = 0.6;
				label1.strokeOpacity = 0.2;
				label1.textDecoration = "underline";
				
				var label12 = valueAxis_cluster.title;
				label12.fontSize = 15;
				label12.fontWeight = "bold";
				label12.stroke=am4core.color("#0000ff");
				label12.strokeWidth = 0.6;
				label12.strokeOpacity = 0.2;
				label12.textDecoration = "underline";

				var colorSet = new am4core.ColorSet();
				colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
					return new am4core.color(color);
				});
				clusterddiv.colors = colorSet;	
				clusterddiv.maskBullets = true;
				
				function createSeries_cluster(field, name) {
				  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
				  	series_cluster.dataFields.valueY = field;
				  	series_cluster.dataFields.categoryX = "group_name";
				  	series_cluster.name = name;
				  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
				  	series_cluster.columns.template.height = am4core.percent(100);
				  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
				  	series_cluster.sequencedInterpolation = true;
				  	series_cluster.stacked = true;
				}
				
				createSeries_cluster("cl1", "C-I", true);
				createSeries_cluster("cl2", "C-II", true);	
				createSeries_cluster("cl3", "C-III", true);
				createSeries_cluster("cl4", "C-IV", true);	
				
				clusterddiv.legend = new am4charts.Legend();
				clusterddiv.legend.useDefaultMarker = true;	
				var marker = clusterddiv.legend.markers.template.children.getIndex(0);
				marker.cornerRadius(12, 12, 12, 12);
				marker.strokeWidth = 2;
				marker.strokeOpacity = 1;
				marker.stroke = am4core.color("#ccc"); 
  			}
	    }).fail(function(xhr, textStatus, errorThrown) {
		});
	}
}
//End TMS Dashboard

//Start MMS Dashboard
function wpnHoldingStateChart(getWPNHoldingStateData)
{		
	am4core.useTheme(am4themes_animated);
	var clusterddiv = am4core.create("wpnHoldingStateDiv", am4charts.XYChart);
	clusterddiv.scrollbarX = new am4core.Scrollbar();			
	
	clusterddiv.data =getWPNHoldingStateData;
	
	var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_cluster.dataFields.category = "colname";
	categoryAxis_cluster.title.text ="";  // "Command Name";
	categoryAxis_cluster.renderer.grid.template.location = 0;
	categoryAxis_cluster.renderer.minGridDistance = 30;
	categoryAxis_cluster.renderer.cellStartLocation = 0.1;
	categoryAxis_cluster.renderer.cellEndLocation = 0.9;
	categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_cluster.renderer.labels.template.rotation = 360; 
						
	var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cluster.min = 0;
	valueAxis_cluster.title.text =""; // "No. of Units";
	valueAxis_cluster.rangeChangeDuration = 500;
	
	var label = categoryAxis_cluster.renderer.labels.template;
	label.wrap = true;
	label.fontSize = 11;
	label.fontWeight = "bold";
	label.maxWidth = 80;	
	
	var label1 = categoryAxis_cluster.title;
	label1.fontSize = 15;
	label1.fontWeight = "bold";
	label1.stroke=am4core.color("#0000ff");
	label1.strokeWidth = 0.6;
	label1.strokeOpacity = 0.2;
	label1.textDecoration = "underline";
	
	var label12 = valueAxis_cluster.title;
	label12.fontSize = 15;
	label12.fontWeight = "bold";
	label12.stroke=am4core.color("#0000ff");
	label12.strokeWidth = 0.6;
	label12.strokeOpacity = 0.2;
	label12.textDecoration = "underline";

	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	clusterddiv.colors = colorSet;	
	clusterddiv.maskBullets = true;
	
	// Create series
	function createSeries_cluster(field, name,stkk) {
	  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
	  	series_cluster.dataFields.valueY = field;
	  	series_cluster.dataFields.categoryX = "colname";
	  	series_cluster.name = name;
	  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_cluster.columns.template.height = am4core.percent(100);
	  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
	  	//series_cluster.sequencedInterpolation = true;
	  	series_cluster.sequencedInterpolation = stkk;
	  	series_cluster.stacked = stkk;
	}
	createSeries_cluster("ue", "UE", true);
	createSeries_cluster("uh", "UH", false);	
	createSeries_cluster("ss", "SS", true);
	createSeries_cluster("ls", "LS", true);
	createSeries_cluster("ac", "ACSFP", true);
	createSeries_cluster("res", "RES", true);
	
	clusterddiv.legend = new am4charts.Legend();
	clusterddiv.legend.useDefaultMarker = true;	
	var marker = clusterddiv.legend.markers.template.children.getIndex(0);
	marker.cornerRadius(12, 12, 12, 12);
	marker.strokeWidth = 2;
	marker.strokeOpacity = 1;
	marker.stroke = am4core.color("#ccc"); 
}

function eqptHoldingStateChart(getEQPTHoldingStateData)
{		
	am4core.useTheme(am4themes_animated);
	var clusterddiv = am4core.create("eqptHoldingStateDiv", am4charts.XYChart);
	clusterddiv.scrollbarX = new am4core.Scrollbar();			
	clusterddiv.data =getEQPTHoldingStateData;
	
	var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_cluster.dataFields.category = "colname";
	categoryAxis_cluster.title.text ="";  // "Command Name";
	categoryAxis_cluster.renderer.grid.template.location = 0;
	categoryAxis_cluster.renderer.minGridDistance = 30;
	categoryAxis_cluster.renderer.cellStartLocation = 0.1;
	categoryAxis_cluster.renderer.cellEndLocation = 0.9;
	categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_cluster.renderer.labels.template.rotation = 360; 
						
	var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cluster.min = 0;
	valueAxis_cluster.title.text =""; // "No. of Units";
	valueAxis_cluster.rangeChangeDuration = 500;
	
	var label = categoryAxis_cluster.renderer.labels.template;
	label.wrap = true;
	label.fontSize = 11;
	label.fontWeight = "bold";
	label.maxWidth = 80;	
	
	var label1 = categoryAxis_cluster.title;
	label1.fontSize = 15;
	label1.fontWeight = "bold";
	label1.stroke=am4core.color("#0000ff");
	label1.strokeWidth = 0.6;
	label1.strokeOpacity = 0.2;
	label1.textDecoration = "underline";
	
	var label12 = valueAxis_cluster.title;
	label12.fontSize = 15;
	label12.fontWeight = "bold";
	label12.stroke=am4core.color("#0000ff");
	label12.strokeWidth = 0.6;
	label12.strokeOpacity = 0.2;
	label12.textDecoration = "underline";

	var colorSet = new am4core.ColorSet();
	colorSet.list = ["#388E3C", "#FBC02D", "#0288d1", "#F44336", "#8E24AA", "#1BA68D", "#ff3377", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc"].map(function(color) {
		return new am4core.color(color);
	});
	clusterddiv.colors = colorSet;	
	clusterddiv.maskBullets = true;
	
	function createSeries_cluster(field, name ,stkk) {
	  	var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
	  	series_cluster.dataFields.valueY = field;
	  	series_cluster.dataFields.categoryX = "colname";
	  	series_cluster.name = name;
	  	series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	series_cluster.columns.template.height = am4core.percent(100);
	  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
	  	series_cluster.sequencedInterpolation = stkk;
	  	series_cluster.stacked = stkk;
	}
	
	createSeries_cluster("ue", "UE", true);
	createSeries_cluster("uh", "UH", false);	
	createSeries_cluster("ss", "SS", true);
	createSeries_cluster("ls", "LS", true);
	createSeries_cluster("ac", "ACSFP", true);
	createSeries_cluster("res", "RES", true);
	
	clusterddiv.legend = new am4charts.Legend();
	clusterddiv.legend.useDefaultMarker = true;	
	var marker = clusterddiv.legend.markers.template.children.getIndex(0);
	marker.cornerRadius(12, 12, 12, 12);
	marker.strokeWidth = 2;
	marker.strokeOpacity = 1;
	marker.stroke = am4core.color("#ccc"); 
}
// END MMS Dashboard
/*
// Start MNH Dashboard
function AllLifeStyleCount(list,chartName,divname,avg_color,cur,avg,total){
	am4core.useTheme(am4themes_animated);
	var chartMin = 0;
	var chartMax = total;

	var data = {
	  score: cur,
	  gradingData: [
	    {
	      title: "10 Year Avg",
	      color: avg_color,//"#6495ED",
	      lowScore: 0,
	      highScore: avg
	    },
	    {
	      title: "Max of 10 year",
	      color: "#bfc9ca",
	      lowScore: avg,
	      highScore: total
	    }
	  ]
	};
	  
	function lookUpGrade(lookupScore, grades) {
	  // Only change code below this line
	  for (var i = 0; i < grades.length; i++) {
	    if (
	      grades[i].lowScore < lookupScore &&
	      grades[i].highScore >= lookupScore
	    ) {
	      return grades[i];
	    }
	  }
	  return null;
	}

	// create chart
	var chart = am4core.create(divname, am4charts.GaugeChart);
	chart.hiddenState.properties.opacity = 0;
	chart.fontSize = 11;
	chart.innerRadius = am4core.percent(60);
	chart.resizable = true;
	chart.tooltipText = "Current Year -"+cur+" \n 10 Year Avg - "+avg+" \n 10 Year Max -"+total +" \n View More Details";
	
	//chart.events.on("hit", function(ev) {
	//	alert("hiii");
	//});
	
	var axis = chart.xAxes.push(new am4charts.ValueAxis());
	axis.min = chartMin;
	axis.max = chartMax;
	axis.strictMinMax = true;
	axis.renderer.radius = am4core.percent(120);
	axis.renderer.inside = true;
	axis.renderer.line.strokeOpacity = 0.1;
	axis.renderer.ticks.template.disabled = false;
	axis.renderer.ticks.template.strokeOpacity = 1;
	axis.renderer.ticks.template.strokeWidth = 1.5;
	axis.renderer.ticks.template.length = 4;
	axis.renderer.grid.template.disabled = true;
	axis.renderer.labels.template.radius = am4core.percent(15);
	axis.renderer.labels.template.fontSize = "0.9em";

	var axis2 = chart.xAxes.push(new am4charts.ValueAxis());
	axis2.min = chartMin;
	axis2.max = chartMax;
	axis2.strictMinMax = true;
	axis2.renderer.labels.template.disabled = true;
	axis2.renderer.ticks.template.disabled = true;
	axis2.renderer.grid.template.disabled = false;
	axis2.renderer.grid.template.opacity = 0.5;
	axis2.renderer.labels.template.bent = true;
	//axis2.renderer.labels.template.fill = am4core.color("#000");
	axis2.renderer.labels.template.fontWeight = "bold";
	axis2.renderer.labels.template.fillOpacity = 0.3;

	for (let grading of data.gradingData) {
	  var range = axis2.axisRanges.create();
	  range.axisFill.fill = am4core.color(grading.color);
	  range.axisFill.fillOpacity = 3.8;
	  range.axisFill.zIndex = -1;
	  range.value = grading.lowScore > chartMin ? grading.lowScore : chartMin;
	  range.endValue = grading.highScore < chartMax ? grading.highScore : chartMax;
	  range.grid.strokeOpacity = 0;
	  range.label.inside = true;
	  range.label.location = 0.5;
	  range.label.inside = true;
	  range.label.radius = am4core.percent(10);
	  range.label.paddingBottom = -5; // ~half font size
	  range.label.fontSize = "0.9em";
	  
	}

	var matchingGrade = lookUpGrade(data.score, data.gradingData);

	var label = chart.radarContainer.createChild(am4core.Label);
	label.isMeasured = false;
	label.fontSize = "3em";
	label.x = am4core.percent(50);
	label.paddingBottom = 0;
	label.horizontalCenter = "middle";
	label.verticalCenter = "bottom";
	label.text = data.score;
	
	var hand = chart.hands.push(new am4charts.ClockHand());
	hand.axis = axis2;
	hand.innerRadius = am4core.percent(55);
	hand.startWidth = 8;
	hand.pin.disabled = true;
	hand.value = data.score;
}

function rankWiseHospitalAdmission(list){
	am4core.useTheme(am4themes_animated);
	var chart = am4core.create("rankWiseHospitalAdmission", am4charts.XYChart);
	chart.logo.disabled = true;
	chart.dateFormatter.dateFormat = "dd MMM YYYY";
	chart.numberFormatter.numberFormat = "#.#a";
	chart.numberFormatter.bigNumberPrefixes = [ 
		{ "number": 1e+3, "suffix": "K" },
		{ "number": 1e+6, "suffix": "M" },
		{ "number": 1e+9, "suffix": "B" }];

	var title = chart.titles.create();
	//title.text = "DAILY CASES";
	title.fontSize = 20;
	title.paddingBottom = 5;

	chart.data = [{
		 "date1": new Date(2020, 0, 9),
		 "Col": 112,
		 "Brg": 207,
		 "MajGen": 312,
		 "LeftGen": 77,
		 "Others": 227,
		 "AMCADCMNS": 400,
		 "Unusual": 280
	 }, {
		 "date1": new Date(2020, 0, 16),
		 "Col": 200,
		 "Brg": 97,
		 "MajGen": 386,
		 "LeftGen": 105,
		 "Others": 330,
		 "AMCADCMNS": 350,
		 "Unusual": 170
	 }, {
		 "date1": new Date(2020, 1, 5),
		 "Col": 250,
		 "Brg": 126,
		 "MajGen": 402,
		 "LeftGen": 263,
		 "Others": 400,
		 "AMCADCMNS": 100,
		 "Unusual": 333
	 },
	 {
		 "date1": new Date(2020, 1, 12),
		 "Col": 290,
		 "Brg": 186,
		 "MajGen": 362,
		 "LeftGen": 293,
		 "Others": 346,
		 "AMCADCMNS": 180,
		 "Unusual": 383
	 }
	];

	 var dateAxis = chart.xAxes.push(new am4charts.DateAxis());
	 dateAxis.renderer.grid.template.location = 0;
	 var valueAxis = chart.yAxes.push(new am4charts.ValueAxis());

	 function createSeries(field, name, color, dashed) {
	   var series = chart.series.push(new am4charts.LineSeries());
	   series.dataFields.valueY = field;
	   series.dataFields.dateX = "date1";
	   series.name = name;
	   //series.tooltipText = "{dateX}: [b]{valueY}[/]";
	   series.tooltipText = "[bold]{name}[/]\n{dateX}: [b]{valueY}[/]";
	   series.strokeWidth = 2;
	   series.stroke = color;
	   if (dashed) {
		   series.strokeDasharray = "5 3";
	   }
	   return series;
	 }
	 var series1 = createSeries("Col", "Col",am4core.color("#008080"));
	 var series2 = createSeries("Brg", "Brg", am4core.color("#0000FF"));
	 var series3 = createSeries("MajGen", "Maj Gen", am4core.color("#9ACD32"));
	 var series4 = createSeries("LeftGen", "Left Gen & Gen", am4core.color("#87CEFA"));
	 var series5 = createSeries("Others", "Others", am4core.color("#F4A460"));
	 var series6 = createSeries("AMCADCMNS", "AMC/ADC/MNS", am4core.color("#800000"), true);
	 var series7 = createSeries("Unusual", "Unusual Occ", am4core.color("#e60000"), true);
	 
	 chart.legend = new am4charts.Legend();
	 chart.cursor = new am4charts.XYCursor();
	 chart.scrollbarX = new am4core.Scrollbar();
}

function bedStateChart()
{			
	var colArray = new Array();				
	colArray.push({'colname': 'HQ WC CORPS 21','laidout': '120', 'occupied': '48', 'perbed': '40%'}); 
	colArray.push({'colname': 'HQ WC CORPS 10','laidout': '300', 'occupied': '181', 'perbed': '60.33%'}); 
	colArray.push({'colname': 'HQ WESTERN COMMAND','laidout': '550', 'occupied': '316', 'perbed': '57.45%'}); 		
	
	if(colArray.length <= 0){
		alert("Data not available");
	}
	am4core.useTheme(am4themes_animated);
	var clusterddiv = am4core.create("bedStateSummarydiv", am4charts.XYChart);
	clusterddiv.scrollbarX = new am4core.Scrollbar();			
	clusterddiv.data =colArray;

	var categoryAxis_cluster = clusterddiv.xAxes.push(new am4charts.CategoryAxis());
	categoryAxis_cluster.dataFields.category = "colname";
	categoryAxis_cluster.title.text ="";  // "Command Name";
	categoryAxis_cluster.renderer.grid.template.location = 0;
	categoryAxis_cluster.renderer.minGridDistance = 30;
	categoryAxis_cluster.renderer.cellStartLocation = 0.1;
	categoryAxis_cluster.renderer.cellEndLocation = 0.9;
	categoryAxis_cluster.renderer.labels.template.verticalCenter ="middle";
	categoryAxis_cluster.renderer.labels.template.rotation = 360; 
						
	var  valueAxis_cluster = clusterddiv.yAxes.push(new am4charts.ValueAxis());
	valueAxis_cluster.min = 0;
	valueAxis_cluster.title.text =""; //"Number of Counts";
	valueAxis_cluster.rangeChangeDuration = 500;
	
	var label = categoryAxis_cluster.renderer.labels.template;
	label.wrap = true;
	label.fontSize = 11;
	label.fontWeight = "bold";
	label.maxWidth = 120;	
	
	var label1 = categoryAxis_cluster.title;
	label1.fontSize = 15;
	label1.fontWeight = "bold";
	label1.stroke=am4core.color("#0000ff");
	label1.strokeWidth = 0.6;
	label1.strokeOpacity = 0.2;
	label1.textDecoration = "underline";
	
	var label12 = valueAxis_cluster.title;
	label12.fontSize = 15;
	label12.fontWeight = "bold";
	label12.stroke=am4core.color("#0000ff");
	label12.strokeWidth = 0.6;
	label12.strokeOpacity = 0.2;
	label12.textDecoration = "underline";

	var colorSet = new am4core.ColorSet();
	colorSet.list = [  "#ff3377", "#1BA68D", "#1aff1a", "#86592d", "#bf4080", "#e6e600", "#ff66cc","#FBC02D","#388E3C","#0288d1", "#F44336" ].map(function(color) {
		return new am4core.color(color);
	});
	clusterddiv.colors = colorSet;	
	clusterddiv.maskBullets = true;
	function createSeries_cluster(field, name) {
		var series_cluster = clusterddiv.series.push(new am4charts.ColumnSeries());
	  	series_cluster.dataFields.valueY = field;
	  	series_cluster.dataFields.categoryX = "colname";
	  	series_cluster.name = name;
	  	if(field == "occupied"){
	  		series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY} ({perbed})[/]";
	  	}
	  	else{
	  		series_cluster.columns.template.tooltipText = "{name}: [bold]{valueY}[/]";
	  	}
	  	
	    series_cluster.columns.template.height = am4core.percent(100);
	  	series_cluster.columns.template.cursorOverStyle = am4core.MouseCursorStyle.pointer;
	  	series_cluster.sequencedInterpolation = true;
	  	
	  	var bullet = series_cluster.bullets.push(new am4charts.LabelBullet);
	    bullet.label.text = "{colname}";
	    bullet.label.rotation = 90;
	    bullet.label.truncate = false;
	    bullet.label.hideOversized = false;
	    bullet.label.horizontalCenter = "left";
	    bullet.locationY = 1;
	    bullet.dy = 10;
	} 
	createSeries_cluster("laidout", "Laid Out", true);
	createSeries_cluster("occupied", "Occupied", true);				
	clusterddiv.legend = new am4charts.Legend();
	clusterddiv.legend.useDefaultMarker = true;	
	var marker = clusterddiv.legend.markers.template.children.getIndex(0);
	marker.cornerRadius(12, 12, 12, 12);
	marker.strokeWidth = 2;
	marker.strokeOpacity = 1;
	marker.stroke = am4core.color("#ccc");
}
//end MNH Dashboard

*/
//Popup
var popupWindow = null
function openAMvcrUpdateReport(url) {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("AMvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=700,height=600,fullscreen=no");
	}
	else
		popupWindow = window.open("AMvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=700,height=600,fullscreen=no");
}
function openBMvcrUpdateReport(url) {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("MvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=700,height=600,fullscreen=no");
	}
	else
		popupWindow = window.open("MvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=700,height=600,fullscreen=no");
}
function openCMvcrUpdateReport(url) {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("CMvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=700,height=600,fullscreen=no");
	}
	else
		popupWindow = window.open("CMvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=700,height=600,fullscreen=no");
}
function openMcrUpdateReport(url) {
	if(popupWindow != null && !popupWindow.closed){
		popupWindow.close();
		popupWindow = window.open("CMvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=700,height=600,fullscreen=no");
	}
	else
		popupWindow = window.open("CMvcrUpdateReport", "_blank", "toolbar=no,status=no,location=no,menubar=no,scrollbars=no,resizable=no,top=100,left=100,width=700,height=600,fullscreen=no");
}