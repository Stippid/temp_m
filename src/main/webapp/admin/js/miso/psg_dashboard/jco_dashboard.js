
const specificChartIds = [
	  'medicalDiv_jco_or',
	  'regtDiv_jco_or',
	  'parentlDiv_jco_or',
	  'bgDiv_jco_or',
	  'comdDiv_jco_or',
	  'genDiv_jco_or',
	  'dosDiv_jco_or',
	  'motDiv_jco_or',
	  'maritalDiv_jco_or',
	  'reliDiv_jco_or'
	];

	// Select chart containers based on specific IDs
	const specificChartContainers = specificChartIds.map(id => document.getElementById(id));

	// Observer logic
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

	// Start observing each specific chart container
	specificChartContainers.forEach((container) => {
	  observer.observe(container);
	});

function hideLoader(loaderId) {	
    var loaderContainer = document.querySelector('[data-loader-id="' + loaderId + '"]');
    if (loaderContainer) {
        loaderContainer.style.display = 'none';
    }
}

var legend_med;
var chart_med;
var legend_regt;
var chartcom;
var legend_parent;
var chartparent;
var legend_bg;
var chartbg;
var chartcomd;
var legend_comd;
var chartgen ;
var legend_gen;
var chartdos;
var legend_dos;
var chartmot;
var legend_mot;
var chartmarital;
var legend_marital;
var chartreli;
var legend_reli;
function initializeAmChart(containerId) {
  // Initialize the amChart here using the containerId
//   const chart = am4core.create(containerId, am4charts.XYChart);
 // alert(containerId);
 
  if(containerId=="medicalDiv_jco_or")
  {
     chart_medicalDiv_jco_or();
    hideLoader('loader_medicalDiv_jco_or');
	legend_med.events.on("click", function (event) {
	    var series = event.target;
	    if (chart_med) {	   
	        update_chart_med(chart_med, series.root,"data");
	    } else {
	        console.error("Chart not found.");
	    }
	});
  }  
  if(containerId=="regtDiv_jco_or")
  {
	  chart_regiment_jco_or();
	  hideLoader('loader_regtDiv_jco_or');	  
	  legend_regt.events.on("click", function (event) {
		    var series = event.target;
		    if (chartcom) {		   
		        update_chart_med(chartcom, series.root,"data2");
		    } else {
		        console.error("Chart not found.");
		    }
		});
  }
  if(containerId=="parentlDiv_jco_or")
  {
	  chart_parentlDiv_jco_or();	
	  hideLoader('loader_parentlDiv_jco_or');
	  legend_parent.events.on("click", function (event) {
		    var series = event.target;
		    if (chartparent) {		   
		        update_chart_med(chartparent, series.root,"datapa");
		    } else {
		        console.error("Chart not found.");
		    }
		});
  }
  if(containerId=="bgDiv_jco_or")
  {
	  chart_bgDiv_jco_or();		 
	  hideLoader('loader_bgDiv_jco_or');	 
	  legend_bg.events.on("click", function (event) {
		    var series = event.target;
		    if (chartbg) {		   
		        update_chart_med(chartbg, series.root,"databg");
		    } else {
		        console.error("Chart not found.");
		    }
		});
  }
  if(containerId=="comdDiv_jco_or")
  {
	  chart_comdDiv_jco_or();
	  hideLoader('loader_comdDiv_jco_or');
	  legend_comd.events.on("click", function (event) {
		    var series = event.target;
		    if (chartcomd) {		   
		        update_chart_med(chartcomd, series.root,"datacomd");
		    } else {
		        console.error("Chart not found.");
		    }
		});
  }
  if(containerId=="genDiv_jco_or")
  {
     chart_gender_jco_or();
     hideLoader('loader_genDiv_jco_or');
     legend_gen.events.on("click", function (event) {
		    var series = event.target;
		    if (chartgen) {		   
		        update_chart_med(chartgen, series.root,"datagen");
		    } else {
		        console.error("Chart not found.");
		    }
		});
  }
  if(containerId=="dosDiv_jco_or")
  {
     chart_dosDiv_jco_or();
     hideLoader('loader_dosDiv_jco_or');     
     
     legend_dos.events.on("click", function (event) {
		    var series = event.target;
		    if (chartdos) {		   
		        update_chart_med(chartdos, series.root,"datados");
		    } else {
		        console.error("Chart not found.");
		    }
		});
		
  }
  if(containerId=="motDiv_jco_or")
  {
     chart_motDiv_jco_or();    
     hideLoader('loader_motDiv_jco_or');
     legend_mot.events.on("click", function (event) {
		    var series = event.target;
		    if (chartmot) {		   
		        update_chart_med(chartmot, series.root,"datamot");
		    } else {
		        console.error("Chart not found.");
		    }
		});
  }
  if(containerId=="maritalDiv_jco_or")
  {
	   chart_maritalDiv_jco_or();
	   hideLoader('loader_maritalDiv_jco_or');	  
	   legend_marital.events.on("click", function (event) {
		    var series = event.target;
		    if (chartmarital) {		   
		        update_chart_med(chartmarital, series.root,"datamarital");
		    } else {
		        console.error("Chart not found.");
		    }
		});
  } 
  if(containerId=="reliDiv_jco_or")
  {
	   chart_reliDiv_jco_or();	  
	   hideLoader('loader_reliDiv_jco_or');
	   legend_reli.events.on("click", function (event) {
		    var series = event.target;
		    if (chartreli) {		   
		        update_chart_med(chartreli, series.root,"datareli");
		    } else {
		        console.error("Chart not found.");
		    }
		});
  } 
  
 
  

}


function getpsg_chartdata( url_var,callback) {
    // Call functions to find selected values
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
    
    $.post(url_var + '?' + key + '=' + value, {
        cont_comd1: cont_comd1,
        cont_corps1: cont_corps1,
        cont_div1: cont_div1,
        cont_bde1: cont_bde1,
        rank1: rnk,
        arm1: arm,
        parm1: parm,
        cmd1: cmd,
        bdes1: check_list_bde,
        div1: check_list_div,
        corps1: check_list_corps,
        regs1: check_list_reg,
        unit1: unit_name1,
        parent_arm1: prntarm,
        unit_name1: unit_name1
    }, function(data) {
    	    if (callback && typeof callback === 'function') {
    	        callback(data);
    	    }
    }).fail(function(xhr, textStatus, errorThrown) {
        alert("Failed to fetch");
    });


}


function chart_medicalDiv_jco_or() {
    am5.ready(function() {
        var root = am5.Root.new("medicalDiv_jco_or");
        root._logo.dispose();
        root.setThemes([
            am5themes_Animated.new(root)
        ]);

        // Create chart
        chart_med = root.container.children.push(am5xy.XYChart.new(root, {
            panX: false,
            panY: false,
            wheelX: "panX",
            wheelY: "zoomX",
            layout: root.verticalLayout
        }));
        chart_med.set("scrollbarX", am5.Scrollbar.new(root, {
            orientation: "horizontal"
        }));
    chart_med.set("scrollbarY", am5.Scrollbar.new(root, {
            orientation: "vertical"
        }));
        getpsg_chartdata("Getrk_medcatlist", function(data) {
            var parsedData = JSON.parse(data);
            var data = parsedData;
if (data.length === 0) { 

  // Set default values when data is empty
  var defaultData = {"data1": "", "data2": 0, "data3": 0, "data4": 0};
  data.push(defaultData);
     var noDataLabel = am5.Label.new(root, {
                    text: "No Data Available",
                  centerX: am5.p5000,
                   centerY: am5.p5000,
						dy :  am5.p5000,
	  				 dx :  am5.p5000,
                  fontSize: 30,
                  fill: root.interfaceColors.get("text")
              });
             chart_med.seriesContainer.children.push(noDataLabel);
               
}
            var xRenderer = am5xy.AxisRendererX.new(root, {});
            var xAxis = chart_med.xAxes.push(am5xy.CategoryAxis.new(root, {
                categoryField: "data1",
                renderer: xRenderer,
                tooltip: am5.Tooltip.new(root, {})
            }));

            xAxis.data.setAll(data);

            var yAxis = chart_med.yAxes.push(am5xy.ValueAxis.new(root, {
                min: 0,
                renderer: am5xy.AxisRendererY.new(root, {})
            }));

            // Add legend
            legend_med = chart_med.children.push(am5.Legend.new(root, {
                centerX: am5.p50,
                x: am5.p50
            }));



            // Add series
            function makeSeries(name, fieldName, total1) {
                var name = name.toUpperCase();
                var series = chart_med.series.push(am5xy.ColumnSeries.new(root, {
                    name: name,
                    stacked: true,
                    xAxis: xAxis,
                    yAxis: yAxis,
                    valueYField: fieldName,
                    categoryXField: "data1"
                }));

                series.columns.template.setAll({
                    tooltipText: "{name}, {categoryX}: {valueY}",
                    tooltipY: am5.percent(10)
                });
             //   series.data.setAll(data);
 if (data.length === 0) {
   
    var defaultData = {"data1": "No Data ", "data2": 0, "data3": 0, "data4": 0};
    series.data.push(defaultData);
  } else {
    series.data.setAll(data);
  }
                // Make stuff animate on load
                series.appear();

                series.bullets.push(function() {
                    return am5.Bullet.new(root, {
                        sprite: am5.Label.new(root, {
                            fill: root.interfaceColors.get("alternativeText"),
                            centerY: am5.p50,
                            centerX: am5.p50
                        })
                    });
                });

                legend_med.data.push(series);

                if (total1) {
                    series.bullets.push(function() {
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
                            var val = Math.abs(dataContext.data2 + dataContext.data3 + dataContext.data4);
                            return "" + val;
                        });

                        return am5.Bullet.new(root, {
                            locationX: 0.5,
                            locationY: 1,
                            sprite: total1Label
                        });
                    });
                }
            }

            makeSeries("fit", "data2", false);
            makeSeries("permanent", "data3", false);
            makeSeries("temporary", "data4", true);
var cursor = chart_med.set("cursor", am5xy.XYCursor.new(root, {
  behavior: "zoomY"
}));
cursor.lineY.set("forceHidden", true);
cursor.lineX.set("forceHidden", true);
var cursor = chart_med.set("cursor", am5xy.XYCursor.new(root, {
  behavior: "zoom"
}));
        });
    });
}



  


//rk vs regiment
function chart_regiment_jco_or() {

 am5.ready(function() {	
var comproot = am5.Root.new("regtDiv_jco_or");
comproot._logo.dispose();
comproot.setThemes([
  am5themes_Animated.new(comproot)
]);


 chartcom = comproot.container.children.push(am5xy.XYChart.new(comproot, {
  panX: false,
  panY: false,
  wheelX: "panX",
  wheelY: "zoomX",
  layout: comproot.verticalLayout
}));

chartcom.set("scrollbarX", am5.Scrollbar.new(comproot, {
  orientation: "horizontal"
}));
chartcom.set("scrollbarY", am5.Scrollbar.new(comproot, {
  orientation: "vertical"
}));

getpsg_chartdata("Getrk_regimentlist", function(data) {
    
	var parsedData = JSON.parse(data);
	var data = parsedData;
if (data.length === 0) {
 
 var defaultData = {
  "data1112": " ",
  "data12": 0,
  "data22": 0,
  "data32": 0,
  "data42": 0,
  "data52": 0,
  "data62": 0,
  "data72": 0,
  "data82": 0,
  "data92": 0,
  "data102": 0,
  "data112": 0,
  "data122": 0,
  "data132": 0,
  "data142": 0,
  "data152": 0,
  "data162": 0,
  "data172": 0,
  "data182": 0,
  "data192": 0,
  "data202": 0,
  "data212": 0,
  "data222": 0,
  "data232": 0,
  "data242": 0,
  "data252": 0,
  "data262": 0,
  "data272": 0
};
  data.push(defaultData);
     var noDataLabel = am5.Label.new(comproot, {
                    text: "No Data Available",
 				  locationX: 1.5,
	                locationY: 2,
                  fontSize: 30,
                  fill: comproot.interfaceColors.get("text")
              });
             chartcom.seriesContainer.children.push(noDataLabel);
               
}
var xAxis = chartcom.xAxes.push(am5xy.CategoryAxis.new(comproot, {
  categoryField: "data1112",
  renderer: am5xy.AxisRendererX.new(comproot, {}),
  tooltip: am5.Tooltip.new(comproot, {})
}));

xAxis.data.setAll(data);

var yAxis = chartcom.yAxes.push(am5xy.ValueAxis.new(comproot, {
  min: 0,
  renderer: am5xy.AxisRendererY.new(comproot, {})
}));


 legend_regt = chartcom.children.push(am5.Legend.new(comproot, {
  centerX: am5.p50,
  x: am5.p50
}));


function makeSeriescom(name, fieldName, total) {
	 var name = name.toUpperCase();
  var series = chartcom.series.push(am5xy.ColumnSeries.new(comproot, {
    name: name,
    stacked: true,
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: fieldName,
    categoryXField: "data1112"
  }));

  series.columns.template.setAll({
    tooltipText: "{name}, {categoryX}: {valueY}",
    tooltipY: am5.percent(10)
  });
//  series.data.setAll(data);
 if (data.length === 0) {
    // Set default values when data is empty
var defaultData = {
  "data1112": " ",
  "data12": 0,
  "data22": 0,
  "data32": 0,
  "data42": 0,
  "data52": 0,
  "data62": 0,
  "data72": 0,
  "data82": 0,
  "data92": 0,
  "data102": 0,
  "data112": 0,
  "data122": 0,
  "data132": 0,
  "data142": 0,
  "data152": 0,
  "data162": 0,
  "data172": 0,
  "data182": 0,
  "data192": 0,
  "data202": 0,
  "data212": 0,
  "data222": 0,
  "data232": 0,
  "data242": 0,
  "data252": 0,
  "data262": 0,
  "data272": 0
};
    series.data.push(defaultData);
  } else {
    series.data.setAll(data);
  }

  series.appear();

  series.bullets.push(function () {
    return am5.Bullet.new(comproot, {
      sprite: am5.Label.new(comproot, {
       // text: "{valueY}",
        fill: comproot.interfaceColors.get("alternativeText"),
        centerY: am5.p50,
        centerX: am5.p50,
       // populateText: true
      })
    });
  });

  legend_regt.data.push(series); 
  
  if (total) {
	    series.bullets.push(function () {
	      var totalLabel = am5.Label.new(comproot, {
	        text: "{valueY}",
	        fill: comproot.interfaceColors.get("text"),
	        centerY: am5.p100,
	        centerX: am5.p50,
	        populateText: true,
	        textAlign: "center"
	      });
	      
	      totalLabel.adapters.add("text", function(text, target) {
	        var dataContext = target.dataItem.dataContext;
	        var val = Math.abs(dataContext.data12 + dataContext.data22 + dataContext.data32 + dataContext.data42 + dataContext.data52
	        		+dataContext.data62 + dataContext.data72 + dataContext.data82 + dataContext.data92 + dataContext.data102
	        		+dataContext.data112 + dataContext.data122 + dataContext.data132 + dataContext.data142 + dataContext.data152
	        		+dataContext.data162 + dataContext.data172 + dataContext.data182 + dataContext.data192 + dataContext.data202
	        		+dataContext.data212 + dataContext.data222 + dataContext.data232 + dataContext.data242 + dataContext.data252
	        		+dataContext.data262 + dataContext.data272);
	        return " "+val;
	      });
	      
	      return am5.Bullet.new(comproot, {
	        locationX: 0.5,
	        locationY: 1,
	        sprite: totalLabel
	      });
	    });
	  }
}


makeSeriescom("gorkharegiment1", "data12",false);
makeSeriescom("gorkharegiment3", "data22",false);
makeSeriescom("gorkharegiment4", "data32",false);
makeSeriescom("gorkharegiment5", "data42",false);
makeSeriescom("gorkharegiment8", "data52",false);
makeSeriescom("gorkharegiment9", "data62",false);
makeSeriescom("gorkharegiment11", "data72",false);
makeSeriescom("parachuteregt", "data82",false);
makeSeriescom("punjab_regt", "data92",false);
makeSeriescom("madrasregt", "data102",false);
makeSeriescom("grenadiers", "data112",false);
makeSeriescom("marathalightinfantry", "data122",false);
makeSeriescom("rajputana_rifles", "data132",false);
makeSeriescom("rajputregt", "data142",false);
makeSeriescom("dograregt", "data152",false);
makeSeriescom("sikhregt", "data162",false);
makeSeriescom("sikhlightinfantry", "data172",false);
makeSeriescom("dograregt", "data182",false);
makeSeriescom("garhwalrifles", "data192",false);
makeSeriescom("kumaonregt", "data202",false);
makeSeriescom("assamregt", "data212",false);
makeSeriescom("biharregt", "data222",false);
makeSeriescom("maharregt", "data232",false);
makeSeriescom("nagaregt", "data242",false);
makeSeriescom("jammuandkashmirrifles", "data252",false);
makeSeriescom("ladakhscouts", "data262",false);
makeSeriescom("jammuandkashmirligthinfregiment", "data272",true);

var cursor = chartcom.set("cursor", am5xy.XYCursor.new(comproot, {
  behavior: "zoomY"
}));
cursor.lineY.set("forceHidden", true);
cursor.lineX.set("forceHidden", true);
var cursor = chartcom.set("cursor", am5xy.XYCursor.new(comproot, {
  behavior: "zoom"
}));


     } );
     
 });
}
    

//rk vs parent arm
function chart_parentlDiv_jco_or() {
    am5.ready(function() {
var parentroot = am5.Root.new("parentlDiv_jco_or");
parentroot._logo.dispose();
// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
parentroot.setThemes([
  am5themes_Animated.new(parentroot)
]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
 chartparent = parentroot.container.children.push(am5xy.XYChart.new(parentroot, {
  panX: false,
  panY: false,
  wheelX: "panX",
  wheelY: "zoomX",
  layout: parentroot.verticalLayout
}));

chartparent.set("scrollbarX", am5.Scrollbar.new(parentroot, {
  orientation: "horizontal"
}));
chartparent.set("scrollbarY", am5.Scrollbar.new(parentroot, {
  orientation: "vertical"
}));
getpsg_chartdata("Getrk_parent_armtlist", function(data) {
    
	var parsedData = JSON.parse(data);
	var data = parsedData;
if (data.length === 0) {
  // Set default values when data is empty
 var defaultData = {
  "datapa": " ",
  "datapa1": 0,
  "datapa2": 0,
  "datapa3": 0,
  "datapa4": 0,
  "datapa5": 0,
  "datapa6": 0,
  "datapa7": 0,
  "datapa8": 0,
  "datapa9": 0,
  "datapa10": 0,
  "datapa11": 0,
  "datapa12": 0,
  "datapa13": 0,
  "datapa14": 0,
  "datapa15": 0,
  "datapa16": 0,
  "datapa17": 0,
  "datapa18": 0,
  "datapa19": 0,
  "datapa20": 0,
  "datapa21": 0,
  "datapa22": 0,
  "datapa23": 0
};

  data.push(defaultData);
     var noDataLabel = am5.Label.new(parentroot, {
                    text: "No Data Available",
                  centerX: am5.p5000,
                   centerY: am5.p5000,
                  fontSize: 30,
                  fill: parentroot.interfaceColors.get("text")
              });
             chartparent.seriesContainer.children.push(noDataLabel);
               
}

// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xAxis = chartparent.xAxes.push(am5xy.CategoryAxis.new(parentroot, {
  categoryField: "datapa",
  renderer: am5xy.AxisRendererX.new(parentroot, {}),
  tooltip: am5.Tooltip.new(parentroot, {})
}));

xAxis.data.setAll(data);

var yAxis = chartparent.yAxes.push(am5xy.ValueAxis.new(parentroot, {
  min: 0,
  renderer: am5xy.AxisRendererY.new(parentroot, {})
}));

// Add legend
// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
 legend_parent = chartparent.children.push(am5.Legend.new(parentroot, {
  centerX: am5.p50,
  x: am5.p50
}));

// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
function makeSeriesparent(name, fieldName, total) {
	 var name = name.toUpperCase();
  var series = chartparent.series.push(am5xy.ColumnSeries.new(parentroot, {
    name: name,
    stacked: true,
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: fieldName,
    categoryXField: "datapa"
  }));

  series.columns.template.setAll({
    tooltipText: "{name}, {categoryX}: {valueY}",
    tooltipY: am5.percent(10)
  });
 if (data.length === 0) {
   
   var defaultData = {
  "datapa": " ",
  "datapa1": 0,
  "datapa2": 0,
  "datapa3": 0,
  "datapa4": 0,
  "datapa5": 0,
  "datapa6": 0,
  "datapa7": 0,
  "datapa8": 0,
  "datapa9": 0,
  "datapa10": 0,
  "datapa11": 0,
  "datapa12": 0,
  "datapa13": 0,
  "datapa14": 0,
  "datapa15": 0,
  "datapa16": 0,
  "datapa17": 0,
  "datapa18": 0,
  "datapa19": 0,
  "datapa20": 0,
  "datapa21": 0,
  "datapa22": 0,
  "datapa23": 0
};

    series.data.push(defaultData);
  } else {
    series.data.setAll(data);
  }

  series.appear();

  series.bullets.push(function () {
    return am5.Bullet.new(parentroot, {
      sprite: am5.Label.new(parentroot, {
       // text: "{valueY}",
       fill: parentroot.interfaceColors.get("alternativeText"),
        centerY: am5.p50,
        centerX: am5.p50,
       // populateText: true
      })
    });
  });

  legend_parent.data.push(series);
  
  
  if (total) {
	    series.bullets.push(function () {
	      var totalLabel = am5.Label.new(parentroot, {
	        text: "{valueY}",
	        fill: parentroot.interfaceColors.get("text"),
	        centerY: am5.p100,
	        centerX: am5.p50,
	        populateText: true,
	        textAlign: "center"
	   });
	      
	      totalLabel.adapters.add("text", function(text, target) {
	        var dataContext = target.dataItem.dataContext;
//		        var val = Math.abs(dataContext.datapa1 + dataContext.datapa2 + dataContext.datapa3 + 
//		        		dataContext.datapa4);
		var val = Math.abs(dataContext.datapa1 + dataContext.datapa2 + dataContext.datapa3 + 
				dataContext.datapa4+dataContext.datapa5+dataContext.datapa6+dataContext.datapa7+
				dataContext.datapa8+dataContext.datapa9+dataContext.datapa10+dataContext.datapa11+
				dataContext.datapa12+dataContext.datapa13+dataContext.datapa14+
				dataContext.datapa15+dataContext.datapa16+dataContext.datapa17+
				dataContext.datapa18+dataContext.datapa19+dataContext.datapa20+
				dataContext.datapa21+dataContext.datapa22+dataContext.datapa23);
				
	        return " "+val;
	      });
	      
	      return am5.Bullet.new(parentroot, {
	        locationX: 0.5,
	        locationY: 1,
	        sprite: totalLabel
	      });
	    });
	  }
}



//	makeSeriesparent("rank", "datapa",false);
makeSeriesparent("armyaviation", "datapa1",false);
makeSeriesparent("engineers", "datapa2",false);
makeSeriesparent("infantry", "datapa3",false);
makeSeriesparent("signals", "datapa4",false);
makeSeriesparent("headquarters", "datapa5",false);
makeSeriesparent("armouredcorps", "datapa6",false);
makeSeriesparent("artillery", "datapa7",false);
makeSeriesparent("armyairdefence", "datapa8",false);
makeSeriesparent("gorkha", "datapa9",false);
makeSeriesparent("electronicsandmechanicalengineers", "datapa10",false);
makeSeriesparent("armypostalservices", "datapa11",false);
makeSeriesparent("armyeducationcorps", "datapa12",false);
makeSeriesparent("intelligencecorps", "datapa13",false);
makeSeriesparent("judgeadvocategeneral", "datapa14",false);
makeSeriesparent("armyphysicaltrainingcorps", "datapa15",false);
makeSeriesparent("pioneers", "datapa16",false);
makeSeriesparent("speciallist", "datapa17",false);
makeSeriesparent("armymedicalcorps", "datapa18",false);
makeSeriesparent("armydentalcorps", "datapa19",false);
makeSeriesparent("corpsofmilitarypolice", "datapa20",false);
makeSeriesparent("defencesecuritycorps", "datapa21",false);
makeSeriesparent("directorategeneralofqualityassurance", "datapa22",false);
makeSeriesparent("brigadeofguards", "datapa23",true);
var cursor = chartparent.set("cursor", am5xy.XYCursor.new(parentroot, {
  behavior: "zoomY"
}));
cursor.lineY.set("forceHidden", true);
cursor.lineX.set("forceHidden", true);
var cursor = chartparent.set("cursor", am5xy.XYCursor.new(parentroot, {
  behavior: "zoom"
}));
});
 });
}

//rk vs blood group 
function chart_bgDiv_jco_or() {
    am5.ready(function() {
var bgroot = am5.Root.new("bgDiv_jco_or");
bgroot._logo.dispose();
// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
bgroot.setThemes([
  am5themes_Animated.new(bgroot)
]);
// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
chartbg = bgroot.container.children.push(am5xy.XYChart.new(bgroot, {
  panX: false,
  panY: false,
  wheelX: "panX",
  wheelY: "zoomX",
  layout: bgroot.verticalLayout
}));

chartbg.set("scrollbarX", am5.Scrollbar.new(bgroot, {
  orientation: "horizontal"
}));
chartbg.set("scrollbarY", am5.Scrollbar.new(bgroot, {
  orientation: "vertical"
}));

getpsg_chartdata("Getrk_blood_grouplist", function(data) {
    
	var parsedData = JSON.parse(data);
	var data = parsedData;
	if (data.length === 0) {
  // Set default values when data is empty
  var defaultData = {
  "databg": " ",
  "databg1": 0,
  "databg2": 0,
  "databg3": 0,
  "databg4": 0,
  "databg5": 0,
  "databg6": 0,
  "databg7": 0,
  "databg8": 0
};
  data.push(defaultData);
     var noDataLabel = am5.Label.new(bgroot, {
                    text: "No Data Available",
                  centerX: am5.p5000,
                   centerY: am5.p5000,
                  fontSize: 30,
                  fill: bgroot.interfaceColors.get("text")
              });
             chartbg.seriesContainer.children.push(noDataLabel);
               
}

	
// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xAxis = chartbg.xAxes.push(am5xy.CategoryAxis.new(bgroot, {
  categoryField: "databg",
  renderer: am5xy.AxisRendererX.new(bgroot, {
	  //cellStartLocation: 0.1,
	  //cellEndLocation: 0.9
  }),
  tooltip: am5.Tooltip.new(bgroot, {})
}));

/* 	
xAxis.get("renderer").labels.template.setAll({
	 oversizedBehavior: "wrap",
	  textAlign: "center",
	  maxWidth: 200
	}); */
xAxis.data.setAll(data);

var yAxis = chartbg.yAxes.push(am5xy.ValueAxis.new(bgroot, {
  min: 0,
  renderer: am5xy.AxisRendererY.new(bgroot, {})
}));


// Add legend
// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
 legend_bg = chartbg.children.push(am5.Legend.new(bgroot, {
  centerX: am5.p50,
  x: am5.p50
}));
// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
function makeSeriesbg(name, fieldName, total) {
	 var name = name.toUpperCase();
  var series = chartbg.series.push(am5xy.ColumnSeries.new(bgroot, {
    name: name,
    stacked: true,
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: fieldName,
    categoryXField: "databg"
  }));

  series.columns.template.setAll({
    tooltipText: "{name}, {categoryX}: {valueY}",
    tooltipY: am5.percent(10)
  });
  series.data.setAll(data);

  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  //series.appear();
if (data.length === 0) {
      // Set default values when data is empty
   var defaultData = {
  "databg": " ",
  "databg1": 0,
  "databg2": 0,
  "databg3": 0,
  "databg4": 0,
  "databg5": 0,
  "databg6": 0,
  "databg7": 0,
  "databg8": 0
};
     series.data.push(defaultData);
    } else {
      series.data.setAll(data);
    }
  series.bullets.push(function () {
    return am5.Bullet.new(bgroot, {
      sprite: am5.Label.new(bgroot, {
       // text: "{valueY}",
        fill: bgroot.interfaceColors.get("text"),
        centerY: am5.p50,
        centerX: am5.p50,
       // populateText: true
      })
    });
  });

  legend_bg.data.push(series);
  
  
  if (total) {
	    series.bullets.push(function () {
	      var totalLabel = am5.Label.new(bgroot, {
	        text: "{valueY}",
	        fill: bgroot.interfaceColors.get("text"),
	        centerY: am5.p100,
	        centerX: am5.p50,
	        populateText: true,
	        textAlign: "center"
	      });
	      
	      totalLabel.adapters.add("text", function(text, target) {
	        var dataContext = target.dataItem.dataContext;
	        var val = Math.abs(dataContext.databg1 + dataContext.databg2 + dataContext.databg3 + dataContext.databg4 
	        		+dataContext.databg5 + dataContext.databg6 + dataContext.databg7 + dataContext.databg8 
	        		//+ dataContext.databg9 + dataContext.databg10
	        		);
	        return " "+val;
	      });
	      
	      return am5.Bullet.new(bgroot, {
	        locationX: 0.5,
	        locationY: 1,
	        sprite: totalLabel
	      });
	    });
	  }
}

makeSeriesbg("warrantofficer", "databg1",false);
makeSeriesbg("submaj", "databg2",false);
makeSeriesbg("sub", "databg3",false);
makeSeriesbg("nbsub", "databg4",false); 
makeSeriesbg("hav", "databg5",false);
makeSeriesbg("nk", "databg6",false);
makeSeriesbg("sep", "databg7",false);
makeSeriesbg("rects", "databg8",true);	
//makeSeriesbg("ltgen", "databg9",false);
//makeSeriesbg("gen", "databg10",true);
var cursor = chartbg.set("cursor", am5xy.XYCursor.new(bgroot, {
  behavior: "zoomY"
}));
cursor.lineY.set("forceHidden", true);
cursor.lineX.set("forceHidden", true);
var cursor = chartbg.set("cursor", am5xy.XYCursor.new(bgroot, {
  behavior: "zoom"
}));



 });
 });
}
 
 
//rk vs comd
function chart_comdDiv_jco_or() {
    am5.ready(function() {
var comdroot = am5.Root.new("comdDiv_jco_or");
comdroot._logo.dispose();
// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
comdroot.setThemes([
  am5themes_Animated.new(comdroot)
]);

// Create chart
// https://www.amcharts.com/docs/v5/charts/xy-chart/
chartcomd = comdroot.container.children.push(am5xy.XYChart.new(comdroot, {
  panX: false,
  panY: false,
  wheelX: "panX",
  wheelY: "zoomX",
  layout: comdroot.verticalLayout
}));

chartcomd.set("scrollbarX", am5.Scrollbar.new(comdroot, {
  orientation: "horizontal"
}));
chartcomd.set("scrollbarY", am5.Scrollbar.new(comdroot, {
  orientation: "vertical"
}));
getpsg_chartdata("Getrk_commandlist", function(data) {
    
	var parsedData = JSON.parse(data);
	var data = parsedData;
	if (data.length === 0) {
  // Set default values when data is empty
var defaultData = {
  "datacomd": " ",
  "datacomd1": 0,
  "datacomd2": 0,
  "datacomd3": 0,
  "datacomd4": 0,
  "datacomd5": 0,
  "datacomd6": 0,
  "datacomd7": 0,
  "datacomd8": 0
};

  data.push(defaultData);
     var noDataLabel = am5.Label.new(comdroot, {
                    text: "No Data Available",
                  centerX: am5.p5000,
                   centerY: am5.p5000,
                  fontSize: 30,
                  fill: comdroot.interfaceColors.get("text")
              });
             chartcomd.seriesContainer.children.push(noDataLabel);
               
}
// Create axes
// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
var xAxis = chartcomd.xAxes.push(am5xy.CategoryAxis.new(comdroot, {
  categoryField: "datacomd",
  renderer: am5xy.AxisRendererX.new(comdroot, {
	  cellStartLocation: 0.1,
	  cellEndLocation: 0.9
  }),
  tooltip: am5.Tooltip.new(comdroot, {})
}));

	
xAxis.get("renderer").labels.template.setAll({
	 oversizedBehavior: "wrap",
	  textAlign: "center",
	  maxWidth: 100
	}); 
xAxis.data.setAll(data);

var yAxis = chartcomd.yAxes.push(am5xy.ValueAxis.new(comdroot, {
  min: 0,
  renderer: am5xy.AxisRendererY.new(comdroot, {})
}));

// Add legend
// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
 legend_comd = chartcomd.children.push(am5.Legend.new(comdroot, {
  centerX: am5.p50,
  x: am5.p50
}));

// Add series
// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
function makeSeriescomd(name, fieldName, total) {
	 var name = name.toUpperCase();
  var series = chartcomd.series.push(am5xy.ColumnSeries.new(comdroot, {
    name: name,
    stacked: true,
    xAxis: xAxis,
    yAxis: yAxis,
    valueYField: fieldName,
    categoryXField: "datacomd"
  }));

  series.columns.template.setAll({
    tooltipText: "{name}, {categoryX}: {valueY}",
    tooltipY: am5.percent(10)
  });
  //series.data.setAll(data);
if (data.length === 0) {
      // Set default values when data is empty
  var defaultData = {
  "datacomd": " ",
  "datacomd1": 0,
  "datacomd2": 0,
  "datacomd3": 0,
  "datacomd4": 0,
  "datacomd5": 0,
  "datacomd6": 0,
  "datacomd7": 0,
  "datacomd8": 0
};

     series.data.push(defaultData);
    } else {
      series.data.setAll(data);
    }
  // Make stuff animate on load
  // https://www.amcharts.com/docs/v5/concepts/animations/
  series.appear();

  series.bullets.push(function () {
    return am5.Bullet.new(comdroot, {
      sprite: am5.Label.new(comdroot, {
       // text: "{valueY}",
       fill: comdroot.interfaceColors.get("alternativeText"),
        centerY: am5.p50,
        centerX: am5.p50
       // populateText: true
      })
    });
  });

  legend_comd.data.push(series);
  
  
  if (total) {
	    series.bullets.push(function () {
	      var totalLabel = am5.Label.new(comdroot, {
	        text: "{valueY}",
	        fill: comdroot.interfaceColors.get("text"),
	        centerY: am5.p100,
	        centerX: am5.p50,
	        populateText: true,
	        textAlign: "center"
	      });
	      
	      totalLabel.adapters.add("text", function(text, target) {
	        var dataContext = target.dataItem.dataContext;
	        var val = Math.abs(dataContext.datacomd1 + dataContext.datacomd2 + dataContext.datacomd3 + dataContext.datacomd4  
	        		+dataContext.datacomd5 + dataContext.datacomd6 + dataContext.datacomd7+dataContext.datacomd8 );
	        return " "+val;
	      });
	      
	      return am5.Bullet.new(comdroot, {
	        locationX: 0.5,
	        locationY: 1,
	        sprite: totalLabel
	      });
	    });
	  }
}

makeSeriescomd("warrantofficer", "datacomd1",false);
makeSeriescomd("submaj", "datacomd2",false);
makeSeriescomd("sub", "datacomd3",false);
makeSeriescomd("nbsub", "datacomd4",false);
 makeSeriescomd("hav", "datacomd5",false);
makeSeriescomd("nk", "datacomd6",false);
makeSeriescomd("sep", "datacomd7",false);
makeSeriescomd("rects", "datacomd8",true);	
var cursor = chartcomd.set("cursor", am5xy.XYCursor.new(comdroot, {
  behavior: "zoomY"
}));
cursor.lineY.set("forceHidden", true);
cursor.lineX.set("forceHidden", true);
var cursor = chartcomd.set("cursor", am5xy.XYCursor.new(comdroot, {
  behavior: "zoom"
}));
}); 

 });
 
}

//rk vs gender
function chart_gender_jco_or() {
    am5.ready(function() {
        var genroot = am5.Root.new("genDiv_jco_or");
	genroot._logo.dispose();
        // Set themes
        // https://www.amcharts.com/docs/v5/concepts/themes/
        genroot.setThemes([
            am5themes_Animated.new(genroot)
        ]);

        // Create chart
        // https://www.amcharts.com/docs/v5/charts/xy-chart/
         chartgen = genroot.container.children.push(am5xy.XYChart.new(genroot, {
            panX: false,
            panY: false,
            wheelX: "panX",
            wheelY: "zoomX",
            layout: genroot.verticalLayout
        }));
    
        chartgen.set("scrollbarY", am5.Scrollbar.new(genroot, {
            orientation: "vertical"
        }));

        getpsg_chartdata("Getrk_genderlist", function(data) {
            var parsedData = JSON.parse(data);
            var data = parsedData;
	if (data.length === 0) {
  // Set default values when data is empty
 var defaultData = {
  "datagen": "",
  "datagen1": 0,
  "datagen2": 0
};

  data.push(defaultData);
     var noDataLabel = am5.Label.new(genroot, {
                    text: "No Data Available",
                  centerX: am5.p5000,
                   centerY: am5.p5000,
                  fontSize: 30,
                  fill: genroot.interfaceColors.get("text")
              });
             chartgen.seriesContainer.children.push(noDataLabel);
               
}
            var yRenderer = am5xy.AxisRendererY.new(genroot, {});
            var yAxis = chartgen.yAxes.push(am5xy.CategoryAxis.new(genroot, {
                categoryField: "datagen",
                renderer: yRenderer,
                tooltip: am5.Tooltip.new(genroot, {}) // Use genroot here
            }));

            yAxis.data.setAll(data);

            var xAxis = chartgen.xAxes.push(am5xy.ValueAxis.new(genroot, {
                min: 0,
                renderer: am5xy.AxisRendererX.new(genroot, {
                    //strokeOpacity: 0.1
                })
            }));

            // Add legend
            // https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
             legend_gen = chartgen.children.push(am5.Legend.new(genroot, {
                centerX: am5.p50,
                x: am5.p50
            }));

            // Add series
            // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
            function makeSeriesgen(name, fieldName, total1) {
            	 var name = name.toUpperCase();
                var series = chartgen.series.push(am5xy.ColumnSeries.new(genroot, {
                    name: name,
                    stacked: true,
                    xAxis: xAxis,
                    yAxis: yAxis,
                    valueXField: fieldName,
                    categoryYField: "datagen"
                }));

                series.columns.template.setAll({
                    tooltipText: "{name}, {categoryY}: {valueX}",
                    tooltipY: am5.percent(10)
                });
             //   series.data.setAll(data);
if (data.length === 0) {
      // Set default values when data is empty
  var defaultData = {
  "datagen": "  ",
  "datagen1": 0,
  "datagen2": 0
};

     series.data.push(defaultData);
    } else {
      series.data.setAll(data);
    }
                // Make stuff animate on load
                // https://www.amcharts.com/docs/v5/concepts/animations/
                series.appear();

                series.bullets.push(function () {
                    return am5.Bullet.new(genroot, {
                        sprite: am5.Label.new(genroot, {
                            fill: genroot.interfaceColors.get("alternativeText"),
                            centerY: am5.p50,
                            centerX: am5.p50,
                        })
                    });
                });

                legend_gen.data.push(series);

                if (total1) {
                    series.bullets.push(function () {
                        var total1Label = am5.Label.new(genroot, {
                            text: "{valueX}",
                            fill: genroot.interfaceColors.get("text"),
                            centerY: am5.p100,
                            centerX: am5.p50,
                            populateText: true,
                            textAlign: "center"
                        });

                        total1Label.adapters.add("text", function(text, target) {
                            var dataContext = target.dataItem.dataContext;
                            var val = Math.abs(dataContext.datagen1 + dataContext.datagen2);
                            return "" + val;
                        });

                        return am5.Bullet.new(genroot, {
                            locationX: 1,
                            locationY: 0.5,
                            sprite: total1Label
                        });
                    });
                }
            }

            makeSeriesgen("Male", "datagen1", false);
            makeSeriesgen("Female", "datagen2", true);
var cursor = chartgen.set("cursor", am5xy.XYCursor.new(genroot, {
  behavior: "zoomY"
}));
cursor.lineY.set("forceHidden", true);
cursor.lineX.set("forceHidden", true);
var cursor = chartgen.set("cursor", am5xy.XYCursor.new(genroot, {
  behavior: "zoom"
}));
        });
    });
}



//rk vs dos
function chart_dosDiv_jco_or() {
    am5.ready(function() {    
        var dosroot = am5.Root.new("dosDiv_jco_or");
        dosroot._logo.dispose();
        // Set themes
        // https://www.amcharts.com/docs/v5/concepts/themes/
        dosroot.setThemes([
          am5themes_Animated.new(dosroot)
        ]);

        // Create chart
        // https://www.amcharts.com/docs/v5/charts/xy-chart/
         chartdos = dosroot.container.children.push(am5xy.XYChart.new(dosroot, {
          panX: false,
          panY: false,
          wheelX: "panX",
          wheelY: "zoomX",
          layout: dosroot.verticalLayout
        }));
        
        chartdos.set("scrollbarX", am5.Scrollbar.new(dosroot, {
          orientation: "horizontal"
        }));
  chartdos.set("scrollbarY", am5.Scrollbar.new(dosroot, {
          orientation: "vertical"
        }));
        getpsg_chartdata("Getrk_doslist", function(data) {
               
            var parsedData = JSON.parse(data);
            var data = parsedData;
            //alert(data);
if (data.length === 0) {
  // Set default values when data is empty
var defaultData = {
  "datados": " ",
  "datados1": 0,
  "datados2": 0,
  "datados3": 0,
  "datados4": 0,
  "datados5": 0,
  "datados6": 0,
  "datados7": 0,
  "datados8": 0
};


  data.push(defaultData);
     var noDataLabel = am5.Label.new(dosroot, {
                    text: "No Data Available",
                  centerX: am5.p5000,
                   centerY: am5.p5000,
                  fontSize: 30,
                  fill: dosroot.interfaceColors.get("text")
              });
             chartdos.seriesContainer.children.push(noDataLabel);
               
}
            // Create axes
            // https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
            var xAxis = chartdos.xAxes.push(am5xy.CategoryAxis.new(dosroot, {
              categoryField: "datados",
              renderer: am5xy.AxisRendererX.new(dosroot, {
                  //cellStartLocation: 0.1,
                  //cellEndLocation: 0.9
              }),
              tooltip: am5.Tooltip.new(dosroot, {})
            }));

            /* 
            xAxis.get("renderer").labels.template.setAll({
                 oversizedBehavior: "wrap",
                  textAlign: "center",
                  maxWidth: 200
                }); */
            xAxis.data.setAll(data);

            var yAxis = chartdos.yAxes.push(am5xy.ValueAxis.new(dosroot, {
              min: 0,
              renderer: am5xy.AxisRendererY.new(dosroot, {})
            }));

            // Add legend
            // https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
             legend_dos = chartdos.children.push(am5.Legend.new(dosroot, {
              centerX: am5.p50,
              x: am5.p50
            }));

            // Add series
            // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
            function makeSeriesdos(name, fieldName, total) {
            	 var name = name.toUpperCase();
                var series = chartdos.series.push(am5xy.ColumnSeries.new(dosroot, {
                    name: name,
                    stacked: true,
                    xAxis: xAxis,
                    yAxis: yAxis,
                    valueYField: fieldName,
                    categoryXField: "datados"
                }));

                series.columns.template.setAll({
                    tooltipText: "{name}, {categoryX}: {valueY}",
                    tooltipY: am5.percent(10)
                });
              //series.data.setAll(data);
if (data.length === 0) {
      // Set default values when data is empty
var defaultData = {
  "datados": " ",
  "datados1": 0,
  "datados2": 0,
  "datados3": 0,
  "datados4": 0,
  "datados5": 0,
  "datados6": 0,
  "datados7": 0,
  "datados8": 0
};

     series.data.push(defaultData);
    } else {
      series.data.setAll(data);
    }
                // Make stuff animate on load
                // https://www.amcharts.com/docs/v5/concepts/animations/
                series.appear();

                series.bullets.push(function () {
                    return am5.Bullet.new(dosroot, {
                        sprite: am5.Label.new(dosroot, {
                            fill: dosroot.interfaceColors.get("alternativeText"),
                            centerY: am5.p50,
                            centerX: am5.p50,
                        })
                    });
                });

                legend_dos.data.push(series);

                if (total) {
                    series.bullets.push(function () {
                        var totalLabel = am5.Label.new(dosroot, {
                            text: "{valueY}",
                            fill: dosroot.interfaceColors.get("text"),
                            centerY: am5.p100,
                            centerX: am5.p50,
                            populateText: true,
                            textAlign: "center"
                        });

                        totalLabel.adapters.add("text", function(text, target) {
                            var dataContext = target.dataItem.dataContext;
                            var val = Math.abs(dataContext.datados1 + dataContext.datados2 + dataContext.datados3 + dataContext.datados4 
                                + dataContext.datados5 + dataContext.datados6 + dataContext.datados7 + dataContext.datados8);
                            return " " + val;
                        });

                        return am5.Bullet.new(dosroot, {
                            locationX: 0.5,
                            locationY: 1,
                            sprite: totalLabel
                        });
                    });
                }
            }

            makeSeriesdos("warrantofficer", "datados1", false);
            makeSeriesdos("submaj", "datados2", false);
            makeSeriesdos("sub", "datados3", false);
            makeSeriesdos("nbsub", "datados4", false);
            makeSeriesdos("hav", "datados5", false);
            makeSeriesdos("nk", "datados6", false);
            makeSeriesdos("sep", "datados7", false);
            makeSeriesdos("rects", "datados8", true);  
            //makeSeriesdos("ltgen", "datados9", false);
            //makeSeriesdos("gen", "datados10", true);
var cursor = chartdos.set("cursor", am5xy.XYCursor.new(dosroot, {
  behavior: "zoomY"
}));
cursor.lineY.set("forceHidden", true);
cursor.lineX.set("forceHidden", true);
var cursor = chartdos.set("cursor", am5xy.XYCursor.new(dosroot, {
  behavior: "zoom"
}));
        });
        
    });
}
//rk vs mother tongue
function chart_motDiv_jco_or() {
    am5.ready(function() { 
     	var motroot = am5.Root.new("motDiv_jco_or");
     	motroot._logo.dispose();
         // Set themes
		// https://www.amcharts.com/docs/v5/concepts/themes/
		motroot.setThemes([
		  am5themes_Animated.new(motroot)
		]);

		// Create chart
		// https://www.amcharts.com/docs/v5/charts/xy-chart/
		 chartmot = motroot.container.children.push(am5xy.XYChart.new(motroot, {
		  panX: false,
		  panY: false,
		  wheelX: "panX",
		  wheelY: "zoomX",
		  layout: motroot.verticalLayout
		}));
		
		chartmot.set("scrollbarX", am5.Scrollbar.new(motroot, {
		  orientation: "horizontal"
		}));
			chartmot.set("scrollbarY", am5.Scrollbar.new(motroot, {
		  orientation: "vertical"
		}));
		//var data =${Getrk_mothertonguelist_jco_or};
	     getpsg_chartdata("Getrk_mothertonguelist", function(data) {
               
            var parsedData = JSON.parse(data);
            var data = parsedData;		
		
if (data.length === 0) {
  // Set default values when data is empty
var defaultData = {
  "datamot": " ",
  "datamot1": 0,
  "datamot2": 0,
  "datamot3": 0,
  "datamot4": 0,
  "datamot5": 0,
  "datamot6": 0,
  "datamot7": 0,
  "datamot8": 0,
  "datamot9": 0,
  "datamot10": 0,
  "datamot11": 0,
  "datamot12": 0,
  "datamot13": 0,
  "datamot14": 0,
  "datamot15": 0,
  "datamot16": 0,
  "datamot17": 0,
  "datamot18": 0,
  "datamot19": 0,
  "datamot20": 0,
  "datamot21": 0,
  "datamot22": 0,
  "datamot23": 0,
  "datamot24": 0
};


  data.push(defaultData);
     var noDataLabel = am5.Label.new(motroot, {
                    text: "No Data Available",
                  centerX: am5.p5000,
                   centerY: am5.p5000,
                  fontSize: 30,
                  fill: motroot.interfaceColors.get("text")
              });
             chartmot.seriesContainer.children.push(noDataLabel);
               
}
		// Create axes
		// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
		var xAxis = chartmot.xAxes.push(am5xy.CategoryAxis.new(motroot, {
		  categoryField: "datamot",
		  renderer: am5xy.AxisRendererX.new(motroot, {
			  cellStartLocation: 0.1,
			  cellEndLocation: 0.9
		  }),
		  tooltip: am5.Tooltip.new(motroot, {})
		}));

	 	
		xAxis.get("renderer").labels.template.setAll({
			 oversizedBehavior: "wrap",
			  textAlign: "center",
			  maxWidth: 100
			}); 
		xAxis.data.setAll(data);

		

		
		var yAxis = chartmot.yAxes.push(am5xy.ValueAxis.new(motroot, {
		  min: 0,
		  renderer: am5xy.AxisRendererY.new(motroot, {})
		}));


		// Add legend
		// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
		 legend_mot = chartmot.children.push(am5.Legend.new(motroot, {
		  centerX: am5.p50,
		  x: am5.p50
		}));


		// Add series
		// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
		function makeSeriesmot(name, fieldName, total) {
			 var name = name.toUpperCase();
		  var series = chartmot.series.push(am5xy.ColumnSeries.new(motroot, {
		    name: name,
		    stacked: true,
		    xAxis: xAxis,
		    yAxis: yAxis,
		    valueYField: fieldName,
		    categoryXField: "datamot"
		  }));

		  series.columns.template.setAll({
		    tooltipText: "{name}, {categoryX}: {valueY}",
		    tooltipY: am5.percent(10)
		  });
		  //series.data.setAll(data);
if (data.length === 0) {
      // Set default values when data is empty
var defaultData = {
  "datamot": " ",
  "datamot1": 0,
  "datamot2": 0,
  "datamot3": 0,
  "datamot4": 0,
  "datamot5": 0,
  "datamot6": 0,
  "datamot7": 0,
  "datamot8": 0,
  "datamot9": 0,
  "datamot10": 0,
  "datamot11": 0,
  "datamot12": 0,
  "datamot13": 0,
  "datamot14": 0,
  "datamot15": 0,
  "datamot16": 0,
  "datamot17": 0,
  "datamot18": 0,
  "datamot19": 0,
  "datamot20": 0,
  "datamot21": 0,
  "datamot22": 0,
  "datamot23": 0,
  "datamot24": 0
};


     series.data.push(defaultData);
    } else {
      series.data.setAll(data);
    }
		  // Make stuff animate on load
		  // https://www.amcharts.com/docs/v5/concepts/animations/
		  series.appear();

		  series.bullets.push(function () {
		    return am5.Bullet.new(motroot, {
		      sprite: am5.Label.new(motroot, {
		       // text: "{valueY}",
		       fill: motroot.interfaceColors.get("text"),
		        centerY: am5.p50,
		        centerX: am5.p50,
		       // populateText: true
		      })
		    });
		  });

		  legend_mot.data.push(series);
		  
		  
		  if (total) {
			    series.bullets.push(function () {
			      var totalLabel = am5.Label.new(motroot, {
			        text: "{valueY}",
			        fill: motroot.interfaceColors.get("text"),
			        centerY: am5.p100,
			        centerX: am5.p50,
			        populateText: true,
			        textAlign: "center"
			      });
			      
			      totalLabel.adapters.add("text", function(text, target) {
			        var dataContext = target.dataItem.dataContext;
			        var val = Math.abs(dataContext.datamot1 + dataContext.datamot2 + dataContext.datamot3 + dataContext.datamot4 
			        		+dataContext.datamot5 + dataContext.datamot6 + dataContext.datamot7 + dataContext.datamot8
			        		+dataContext.datamot9 + dataContext.datamot10 + dataContext.datamot11 + dataContext.datamot12
			        		+dataContext.datamot13 + dataContext.datamot14 + dataContext.datamot15 + dataContext.datamot16
			        		+dataContext.datamot17 + dataContext.datamot18 + dataContext.datamot19 + dataContext.datamot20
			        		+dataContext.datamot21 + dataContext.datamot22 + dataContext.datamot23 + dataContext.datamot24);
			        return " "+val;
			      });
			      
			      return am5.Bullet.new(motroot, {
			        locationX: 0.5,
			        locationY: 1,
			        sprite: totalLabel
			      });
			    });
			  }
		}

		
		makeSeriesmot("assamese", "datamot1",false);
		makeSeriesmot("bengali", "datamot2",false);
		makeSeriesmot("bodo", "datamot3",false);
		makeSeriesmot("dogri", "datamot4",false); 
		makeSeriesmot("english", "datamot5",false);
		makeSeriesmot("gujarati", "datamot6",false);
		makeSeriesmot("hindi", "datamot7",false);	
		makeSeriesmot("kannada", "datamot8",false);
		makeSeriesmot("kashmiri", "datamot9",false);
		makeSeriesmot("konkani", "datamot10",false);
		makeSeriesmot("maithili", "datamot11",false); 
		makeSeriesmot("malayalam", "datamot12",false);
		makeSeriesmot("marathi", "datamot13",false);
		makeSeriesmot("meiteimanipuri", "datamot14",false);	
		makeSeriesmot("nepali", "datamot15",false);
		makeSeriesmot("oriya", "datamot16",false);
		makeSeriesmot("others", "datamot17",false);
		makeSeriesmot("punjabi", "datamot18",false); 
		makeSeriesmot("sanskrit", "datamot19",false);
		makeSeriesmot("santhali", "datamot20",false);
		makeSeriesmot("sindhi", "datamot21",false);	
		makeSeriesmot("tamil", "datamot22",false);
		makeSeriesmot("telugu", "datamot23",false);
		makeSeriesmot("urdu", "datamot24",true);
		var cursor = chartmot.set("cursor", am5xy.XYCursor.new(motroot, {
  behavior: "zoomY"
}));
cursor.lineY.set("forceHidden", true);
cursor.lineX.set("forceHidden", true);
var cursor = chartmot.set("cursor", am5xy.XYCursor.new(motroot, {
  behavior: "zoom"
}));
    });
   });
}



//rk vs marital status
function chart_maritalDiv_jco_or() {
   
    am5.ready(function() { 
        var maritalroot = am5.Root.new("maritalDiv_jco_or");
        maritalroot._logo.dispose();
        // Set themes
        // https://www.amcharts.com/docs/v5/concepts/themes/
        maritalroot.setThemes([
          am5themes_Animated.new(maritalroot)
        ]);

        // Create chart
        // https://www.amcharts.com/docs/v5/charts/xy-chart/
         chartmarital = maritalroot.container.children.push(am5xy.XYChart.new(maritalroot, {
          panX: false,
          panY: false,
          wheelX: "panX",
          wheelY: "zoomX",
          layout: maritalroot.verticalLayout
        }));
        
        
        chartmarital.set("scrollbarY", am5.Scrollbar.new(maritalroot, {
          orientation: "vertical"
        }));

        getpsg_chartdata("Getrk_marital_statuslist", function(data) {
            var parsedData = JSON.parse(data);
            var data = parsedData;
	
if (data.length === 0) {
  // Set default values when data is empty
var defaultData = {
  "datamarital": " ",
  "datamarital1": 0,
  "datamarital2": 0,
  "datamarital3": 0,
  "datamarital4": 0,
  "datamarital5": 0,
  "datamarital6": 0
};

  data.push(defaultData);
     var noDataLabel = am5.Label.new(maritalroot, {
                    text: "No Data Available",
                  centerX: am5.p5000,
                   centerY: am5.p5000,
                  fontSize: 30,
                  fill: maritalroot.interfaceColors.get("text")
              });
             chartmarital.seriesContainer.children.push(noDataLabel);
               
}
            var yRenderer = am5xy.AxisRendererY.new(maritalroot, {});
            var yAxis = chartmarital.yAxes.push(am5xy.CategoryAxis.new(maritalroot, {
              categoryField: "datamarital",
              renderer: yRenderer,
              tooltip: am5.Tooltip.new(maritalroot, {}) // Use maritalroot here
            }));

            yAxis.data.setAll(data);

            var xAxis = chartmarital.xAxes.push(am5xy.ValueAxis.new(maritalroot, {
                min: 0,
                renderer: am5xy.AxisRendererX.new(maritalroot, {
                    //strokeOpacity: 0.1
                })
            }));

            // Add legend
            // https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
             legend_marital= chartmarital.children.push(am5.Legend.new(maritalroot, {
              centerX: am5.p50,
              x: am5.p50
            }));

            // Add series
            // https://www.amcharts.com/docs/v5/charts/xy-chart/series/
            function makeSeriesmarital(name, fieldName, total1) {
            	 var name = name.toUpperCase();
              var series = chartmarital.series.push(am5xy.ColumnSeries.new(maritalroot, {
                name: name,
                stacked: true,
                xAxis: xAxis,
                yAxis: yAxis,
                valueXField: fieldName,
                categoryYField: "datamarital"
              }));

              series.columns.template.setAll({
                tooltipText: "{name}, {categoryY}: {valueX}",
                tooltipY: am5.percent(10)
              });
            //  series.data.setAll(data);
if (data.length === 0) {
      // Set default values when data is empty
var defaultData = {
  "datamarital": " ",
  "datamarital1": 0,
  "datamarital2": 0,
  "datamarital3": 0,
  "datamarital4": 0,
  "datamarital5": 0,
  "datamarital6": 0
};



     series.data.push(defaultData);
    } else {
      series.data.setAll(data);
    }
              // Make stuff animate on load
              // https://www.amcharts.com/docs/v5/concepts/animations/
              series.appear();

              series.bullets.push(function () {
                return am5.Bullet.new(maritalroot, {
                  sprite: am5.Label.new(maritalroot, {
                    fill: maritalroot.interfaceColors.get("alternativeText"),
                    centerY: am5.p50,
                    centerX: am5.p50,
                  })
                });
              });

              legend_marital.data.push(series);
              
              
              if (total1) {
                    series.bullets.push(function () {
                      var total1Label = am5.Label.new(maritalroot, {
                        text: "{valueX}",
                        fill: maritalroot.interfaceColors.get("text"),
                        centerY: am5.p100,
                        centerX: am5.p50,
                        populateText: true,
                        textAlign: "center"
                      });
                      
                      total1Label.adapters.add("text", function(text, target) {
                        var dataContext = target.dataItem.dataContext;
                        var val = Math.abs(dataContext.datamarital1 + dataContext.datamarital2 + dataContext.datamarital3
                            + dataContext.datamarital4 + dataContext.datamarital5 + dataContext.datamarital6);
                        return "" + val;
                      });
                      
                      return am5.Bullet.new(maritalroot, {
                        locationX: 1,
                        locationY: 0.5,
                        sprite: total1Label
                      });
                    });
              }
            }

            makeSeriesmarital("Married", "datamarital1", false);
            makeSeriesmarital("Divorced", "datamarital2", false);
            makeSeriesmarital("Widower", "datamarital3", false);
            makeSeriesmarital("Unmarried", "datamarital4", false);
            makeSeriesmarital("Widow", "datamarital5", false);
            makeSeriesmarital("Separated", "datamarital6", true);
	var cursor = chartmarital.set("cursor", am5xy.XYCursor.new(maritalroot, {
  behavior: "zoomY"
}));
cursor.lineY.set("forceHidden", true);
cursor.lineX.set("forceHidden", true);
var cursor = chartmarital.set("cursor", am5xy.XYCursor.new(maritalroot, {
  behavior: "zoom"
}));
        });
    });
}
      //rk vs religion
	function chart_reliDiv_jco_or() {
   
    am5.ready(function() { 	
		var reliroot = am5.Root.new("reliDiv_jco_or");
		reliroot._logo.dispose();

		// Set themes
		// https://www.amcharts.com/docs/v5/concepts/themes/
		reliroot.setThemes([
		  am5themes_Animated.new(reliroot)
		]);


		// Create chart
		// https://www.amcharts.com/docs/v5/charts/xy-chart/
		 chartreli = reliroot.container.children.push(am5xy.XYChart.new(reliroot, {
		  panX: false,
		  panY: false,
		  wheelX: "panX",
		  wheelY: "zoomX",
		  layout: reliroot.verticalLayout
		}));
	
		chartreli.set("scrollbarX", am5.Scrollbar.new(reliroot, {
		  orientation: "horizontal"
		}));
		chartreli.set("scrollbarY", am5.Scrollbar.new(reliroot, {
			  orientation: "vertical"
			}));




	    getpsg_chartdata("Getrk_religionlist", function(data) {
            var parsedData = JSON.parse(data);
            var data = parsedData;
		

if (data.length === 0) {
  // Set default values when data is empty
var defaultData = {
	"datareli":"",
  "datareli1": 0,
  "datareli2": 0,
  "datareli3": 0,
  "datareli4": 0,
  "datareli5": 0,
  "datareli6": 0,
  "datareli7": 0,
  "datareli8": 0
};


  data.push(defaultData);
     var noDataLabel = am5.Label.new(reliroot, {
                    text: "No Data Available",
                  centerX: am5.p5000,
                   centerY: am5.p5000,
                  fontSize: 30,
                  fill: reliroot.interfaceColors.get("text")
              });
             chartreli.seriesContainer.children.push(noDataLabel);
               
}
		// Create axes
		// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
		var xAxis = chartreli.xAxes.push(am5xy.CategoryAxis.new(reliroot, {
		  categoryField: "datareli",
		  renderer: am5xy.AxisRendererX.new(reliroot, {
			  cellStartLocation: 0.1,
			  cellEndLocation: 0.9
			 
		  }),
		  tooltip: am5.Tooltip.new(reliroot, {})
		}));

	 	
		xAxis.get("renderer").labels.template.setAll({
			 oversizedBehavior: "wrap",
			  textAlign: "center",
			  maxWidth: 100
			}); 
		xAxis.data.setAll(data);

		

		
		var yAxis = chartreli.yAxes.push(am5xy.ValueAxis.new(reliroot, {
			base: 10, 
			min: 0,	
		  renderer: am5xy.AxisRendererY.new(reliroot, {})
		}));


		// Add legend
		// https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
		 legend_reli = chartreli.children.push(am5.Legend.new(reliroot, {
		  centerX: am5.p50,
		  x: am5.p50
		}));


		// Add series
		// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
		function makeSeriesreli(name, fieldName, total) {
			 var name = name.toUpperCase();
		  var series = chartreli.series.push(am5xy.ColumnSeries.new(reliroot, {
		    name: name,
		    stacked: true,
		    xAxis: xAxis,
		    yAxis: yAxis,
		    valueYField: fieldName,
		    categoryXField: "datareli"
		  }));

		  series.columns.template.setAll({
		    tooltipText: "{name}, {categoryX}: {valueY}",
		    tooltipY: am5.percent(10)
		  });
		  //series.data.setAll(data);
if (data.length === 0) {
      // Set default values when data is empty
var defaultData = {
	"datareli":"",
  "datareli1": 0,
  "datareli2": 0,
  "datareli3": 0,
  "datareli4": 0,
  "datareli5": 0,
  "datareli6": 0,
  "datareli7": 0,
  "datareli8": 0
};




     series.data.push(defaultData);
    } else {
      series.data.setAll(data);
    }
		  // Make stuff animate on load
		  // https://www.amcharts.com/docs/v5/concepts/animations/
		  series.appear();

		  series.bullets.push(function () {
		    return am5.Bullet.new(reliroot, {
		      sprite: am5.Label.new(reliroot, {
		       // text: "{valueY}",
		        fill: reliroot.interfaceColors.get("alternativeText"),
		        centerY: am5.p50,
		        centerX: am5.p50,
		       // populateText: true
		      })
		    });
		  });

		  legend_reli.data.push(series);
		  
		  
		  if (total) {
			    series.bullets.push(function () {
			      var totalLabel = am5.Label.new(reliroot, {
			        text: "{valueY}",
			        fill: reliroot.interfaceColors.get("text"),
			        centerY: am5.p100,
			        centerX: am5.p50,
			        populateText: true,
			        textAlign: "center"
			      });
			      
			      totalLabel.adapters.add("text", function(text, target) {
			        var dataContext = target.dataItem.dataContext;
			        var val = Math.abs(dataContext.datareli1 + dataContext.datareli2 + dataContext.datareli3 + dataContext.datareli4 
			        		+ dataContext.datareli5 + dataContext.datareli6 + dataContext.datareli7 + dataContext.datareli8 
			        		);
			        return " "+val;
			      });
			      
			      return am5.Bullet.new(reliroot, {
			        locationX: 0.5,
			        locationY: 1,
			        sprite: totalLabel
			      });
			    });
			  }
		}

		
		makeSeriesreli("warrantofficer", "datareli1",false);
		makeSeriesreli("submaj", "datareli2",false);
		makeSeriesreli("sub", "datareli3",false);
		makeSeriesreli("nbsub", "datareli4",false); 
		makeSeriesreli("hav", "datareli5",false); 
		makeSeriesreli("nk", "datareli6",false);
		makeSeriesreli("sep", "datareli7",false);
		makeSeriesreli("rects", "datareli8",true);	
			var cursor = chartreli.set("cursor", am5xy.XYCursor.new(reliroot, {
  behavior: "zoomY"
}));
cursor.lineY.set("forceHidden", true);
cursor.lineX.set("forceHidden", true);
var cursor = chartreli.set("cursor", am5xy.XYCursor.new(reliroot, {
  behavior: "zoom"
}));
    });
	
    });
}
      
      
 
	function filterOptions(a,b,c) {
        var searchBox = document.getElementById(a);
        var checkboxes = document.querySelectorAll(b);

        checkboxes.forEach(function (checkbox) {
            var labelText = checkbox.textContent || checkbox.innerText;
            var shouldShow = labelText.toLowerCase().includes(searchBox.value.toLowerCase());
            checkbox.style.display = shouldShow ? 'block' : 'none';
            
        });
        searchBox.value = searchBox.value.toUpperCase();
    }
		
	function update_chart_med(chart, root,data_name) {
	 
	    var activeSeries;
	    if (chart.series.length > 0) {
	        var lastSeries = chart.series.values[chart.series.length - 1];
	        lastSeries.bullets.clear();
	    }
	    for (var i = 0; i < chart.series.length; i++) {
	        var currentSeries = chart.series.values[i];

	        if (!currentSeries.isHidden()) {
	            activeSeries = currentSeries;
	            if (i !== 0) {
	                chart.series.values[i - 1].bullets.clear();
	            } else {
	                currentSeries.bullets.clear();
	            }
	        }
	    }

	    if (activeSeries != null) {
	        activeSeries.bullets.push(function () {
	            var totalLabel = am5.Label.new(root, {
	                text: "{valueY}",
	                fill: root.interfaceColors.get("text"),
	                centerY: am5.p100,
	                centerX: am5.p50,
	                populateText: true,
	                textAlign: "center"
	            });

	            totalLabel.adapters.add("text", function (text, target) {
	                var dataContext = target.dataItem.dataContext;
	                var val = 0;

	                for (var i = 0; i < chart.series.length; i++) {
	                    var currentSeries = chart.series.values[i];

	                    if (!currentSeries.isHidden()) {
	                    	if(data_name=="data")
	                    		{
	                    		 val += Math.abs(dataContext[data_name + (i+2)]);
	                    		}
	                    	else if(data_name=="data2")
                    		{
                    		 val += Math.abs(dataContext["data"+ (i+1)+"2"]);
                    		}	                    	                    	
	                    	else
                    		{
                    		 val += Math.abs(dataContext[data_name+ (i+1)]);
                    		}	         
	                     	//(data_name=="datapa"||data_name=="databg" ||datacomd||datagen||datados||datamot||datamarital)
	                    	
	                    }
	                }
	                return " " + val;
	            });

	            return am5.Bullet.new(root, {
	                locationX: 0.5,
	                locationY: 1,
	                sprite: totalLabel
	            });
	        });
	    }
	}
	function getdashboard(id)
	{
	$(".psg_dash #WaitLoader").show();
	window.location.href = id;
		}
