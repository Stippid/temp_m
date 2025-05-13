<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmn"%>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<link rel="stylesheet" href="js/common/select2/select2.min.css">

<script type="text/javascript">
	var username = "${username}";
	var curDate = "${curDate}";
	var rolesus ="${roleSusNo}";
	var role = "${role}";

	function isNumberPointKey(evt) {
		var charCode = (evt.which) ? evt.which : evt.keyCode;
		if (charCode == 189) {
			return true;
		} else {
			if (charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)) {
				return false;
			} else {
				if (charCode == 46) {
					return false;
				}
			}
			return true;
		}
	}
</script>
<style>
	.nExlntRow {
		border:2px solid blue;
		background-color: #87F717!important;
		color:blue!important;
	}
	.ngoodRow {
		border:2px solid blue;
		background-color: yellow!important;
		color:blue!important;
	}
	.npoorRow {
		border:2px solid blue!important;
		background-color: rgba(255,0,0,0.1)!important;
		color:blue!important;
	}
	.nBox {
		border:1px solid black;
		padding:7px;
		border-spacing: 0px;
	}
	.lgShape {
		padding:5px 10px 5px 10px;
		font-weight: bold;
		border-radius: 7px;
	}
</style>
<body class="mmsbg" onload="setMenu();">
		<input type="hidden" name="tr_head" id="tr_head" />
		<div class="" align="center" style="width:100%; margin:0px;padding:0px;border:0px;">
			<div class="card">
				<div class="card-header mms-card-header ncard-body-bg"
					style="min-height: 50px; padding-top: 10px;"><h5><span>Login Status</span></h5>
				</div>
				<div class="card-body card-block" style="min-height:60vh;background-color: azure;margin:0px;padding:0px;border:5px;">
	            	<div class="row form-group" id="nBase_data"  style="margin-top: -20;background-color: azure;border:1px solid gray;">	
						<div id="divShowDtl" class="" style="width:100%;z-index:300;background-color: azure;font-size:1.1em;">
							<div class="col-md-4">
									<input id="nrInput" type="text"	placeholder="Search ..." style="font-weight: normal;font-size: 14px;border: 1px solid #ddd;padding: 4px;" title="Search Code Head">
							</div>
							<div class="col-md-8" style="font-size:1.1em!important;">
									Budget Holders&nbsp;:&nbsp;
									<select id="flthq" class="select" onchange="showdetl();">
										<!-- <option value="ALL_ALL_ALL">All Budget Holders</option> -->
										<c:forEach var="item" items="${n_hq}" varStatus="num">
				   								<option value="${item[3]}_${item[0]}_${item[1]}">${item[2]}</option>		   						
							        	</c:forEach>
						        	</select>
							</div>							
							<div class="" style="width:100%">
								<input type="hidden" id="nrDetlInput" value="ALL">								
								<div id="nMsgBoard" style="width:100%;">									
	 								<div class="nPopTable" id="nrTableDataDivDtl" style="height:55vh;width:100%;margin-top:10px;"> 
										<table style="width:100%;border-collapse: separate;">
							               	<thead style="text-align: center;line-height:25px;font-size: 1vw;border-spacing: 0 0PX!important;" id="th_nrTableShowDtl">  
							    				<tr>							    					
									  			</tr>
							    		  	</thead>
										    <tbody id="nrTableShowDtl" style="font-size: 1vw;text-decoration: none;color:blue;">											
										    </tbody>
										    <tfoot id="th_nrTableShowtot">
										    <tr>							    					
								  			</tr>											
										    </tfoot>
										    </table>
									</div>																											
							</div>
							</div>
						</div>
			  		</div>				  	
			</div>
			<div class="card-footer" align="left">
				<div class="row form-group">
					<div class="col-md-4">	
						<input type="button" class="btn btn-success btn-sm" value="Export to Excel" id="btn_e" style="background-color: purple;" data-toggle="tooltip" title="Export Data to Excel" onclick="exportData('#nrTableDataDivDtl');">
					</div>
					<!-- <div class="col-md-8">
						<span value="Export" id="btn_e" style="float:right;">Legends :
							<span id="lg1" class="nExlntRow lgShape">Excellent</span>
							<span id="lg2" class="ngoodRow lgShape">Good</span>
							<span id="lg3" class="npoorRow lgShape">Poor</span>
						</span>
					</div> -->
				</div>				
			</div>
		</div>
	
				<div class="nPopCContainer" id="nPrintLetter" style="width:22cm;height:85%;display:none;z-index:499;background-color: white;">
					<c:set var="ltropt" value="${DtlFilterOpt}" />
					<div class="nPopHeader">
						Letter	
						<span class="nPopClose" onclick="$('#nPrintLetter').hide();" title="Close Msg Window">&#10006;</span>
					</div>
					<div class="nPopTablee" id="nPrintLetterDiv" style="min-height:70%;max-height:91%;height:29.7cm;width:21cm;overflow: auto;">
					<style> .nBox {	border:1px solid black;	padding:7px;} </style>
						<div class="nPopBody">
							<div class="row form-group">
								<div class="col-md-12">					
									<table border="0"style="width:100%;border:0px solid gray;line-height: 1.2;table-layout: fixed;border-collapse: collapse;padding-bottom:10px;">
 										<tr id="nPrintLetterDiv_tb" >
 									</table>
 								</div>
							</div>
						</div>
						<div style="align-self:left;">
								<input type="button" class="btn btn-success btn-sm nGlow" value="Export to Word" style="background-color: purple;" data-toggle="tooltip" title="Export Letter to Word" onclick="exportPrintLetter('#nPrintLetterDiv');">
						</div>						
					</div>
				</div>		
		</div>
<script src="js/miso/miso_js/jquery-2.2.3.min.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/common/select2/select2.min.js" type="text/javascript"></script>

<script type="text/javascript">
		$(document).ready(function() {
			$('#flthq').nselect();
			nChgMode('data');
			$("#nrInput").on("keyup",function() {
				var value = $(this).val().toLowerCase();
				$("#nrTableShowDtl tr").filter(function() {
					$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1) 
				});
			});
		    $("div#divwatermark").val('').addClass('watermarked');	
			/* watermarkreport();			
			$('#btn_save').click(function() {
				var trHead = $('[name=code_head]:checked').val();
				var amt = $('#amount').val();
				if (trHead && amt) {
					$('#tr_head').val(trHead);
						
					$.ajax({
						type : "POST",
						url: "fp_check_projection?"+key+"="+value,
						data : {tr_head : trHead},
						success : function(res) {
							if(res){
								var conf = confirm("Projection Amount already available under this head for financial year \n\nDo you want to over write ?");
								if(conf)
									$('#fp_create_projection').submit();
							}
							else
								$('#fp_create_projection').submit();
						},
						error:function(e,d){
							alert("Error - unable to fetch data");
						}
					});
				} 
				else {
					alert("Please enter all mandatory fields");
				}
			}); */
		});
</script>
	
<script type="text/javascript">
function nChgMode(a) {
	$("[id *='nBase_']").hide();
	if (a=='data') {
		showdetl();
	}
	$('#nBase_'+a+"_h").show();
	$('#nBase_'+a).show();		
}

function showdetl() {
	var lok=0;
	var lnok=0;
	var nHed="";
	var nHedt="";
	var slvl=$("#flthq").val();
	//alert(slvl);
	nHed=""+slvl;
	$.post("fplogininfodata?"+key+"="+value, {nPara:nHed}, function(j) {
 	}).done(function(j) {  		
 		//alert(j);
 		var itxts="";
 		var itxtth="";
 		var itxtthtot="";
		var itxt="";
		var tmpv="";
		itxtth += '<tr><th style="width:200px;">HQ</th><th width="200px">Fmn</th><th width="50px">Module</th>';
		itxtth += '<th width="200px">Unit</th><th width="100px">User ID</th><th width="150px">User Name</th>';
		itxtth += '<th width="100px">Last Login</th><th width="100px">Log Status</th><th width="150px">Since</th>';
		$("#th_nrTableShowDtl").html(itxtth);
		//console.log("Loop Started");
 		for (var i=0;i<j.length-1;i++){
 			//console.log(i,"-",j[i][1]);
 			if (j[i][13]=='LOGGED') {
 				itxt += '<tr>';
 				lok++;
 			} else {
 				itxt += '<tr class="npoorRow">';
 				lnok++;
 			}
			itxt += '<td id="L'+j[i][0]+'_'+j[i][8]+'_12" class="ALC">'+j[i][5]+'</td>';
			itxt += '<td id="L'+j[i][0]+'_'+j[i][8]+'_12" class="ALC">'+j[i][7]+'</td>';
			itxt += '<td id="L'+j[i][0]+'_'+j[i][8]+'_12" class="ALC"><center>'+j[i][9]+'</td>';			
			itxt += '<td id="L'+j[i][0]+'_'+j[i][8]+'_12" class="ALC">'+j[i][4]+'</td>';
			itxt += '<td id="L'+j[i][0]+'_'+j[i][8]+'_12" class="ALC">'+j[i][1]+'</td>';
			itxt += '<td id="L'+j[i][0]+'_'+j[i][8]+'_12" class="ALC">'+j[i][2]+'</td>';				
			itxt += '<td id="L'+j[i][0]+'_'+j[i][8]+'_12" class="ALC">'+j[i][12]+'</td>';
			itxt += '<td id="L'+j[i][0]+'_'+j[i][8]+'_12" class="ALC">'+j[i][13]+'</td>';
			itxt += '<td id="L'+j[i][0]+'_'+j[i][8]+'_12" class="ALC">'+j[i][14]+'</td>';
			//$("#nrTableShowDtl").append(itxt);
	 		itxtthtot="";
 		}
			itxtthtot += '<tr style="color:blue!important;font-size:1.5vw;text-align:right;"><th colspan="2">Over All</th>';
			itxtthtot += '<th colspan="2">Logged - '+lok+'</th>';
			itxtthtot += '<th colspan="2" class="">Not Logged - '+lnok+'</th>';
			itxtthtot += '<th colspan="3"></th>';
			$("#th_nrTableShowDtl").html(itxtth); 		
	 		$("#nrTableShowDtl").html(itxt);
			$("#th_nrTableShowtot").html(itxtthtot);			
 	}).fail(function(xhr, textStatus, errorThrown) { });
	$("#divShowDtl").show();
}

function calper(alt,exp) {
	var a=(alt*10000000);
	var a1=(exp*10000000);
	if (parseFloat(a)<=0) {
		var b=a;
	} else {
		var b=(a1*100)/a;
	} 
	var bb="";
	if (parseInt(b)<10) {
		bb="0"+b.toFixed(2);
	} else {
		bb=b.toFixed(2);
	}
	return bb;
}

function exportData(b){
	$().tbtoExcel(b);
	b.preventDefault();
}



function exportPrintLetter(b){
	
	$().tbtoWord(b);
	b.preventDefault();
}

function sortTable(a,c) { 
  var table,table1, rows,rows1, switching, i, x, y, x1,y1,shouldSwitch,nVMax;  
  if (a =='XNX') {
    var nV=document.getElementById("ab").value;    
  } else {
    if(typeof a === 'string') {
      var nV=a;
    } else {
      var nV=a.value;
    }
  }
  var nVa=nV.split(":");
  table = document.getElementById("nrTableShowDtl");
  switching = true;
  while (switching) {
    switching = false;
    rows = table.rows;
    for (i = 1; i < (rows.length - 1); i++) {
      shouldSwitch = false;
      if (c=='D') {
        x = rows[i].getElementsByTagName("TD")[nVa[0]];
        y = rows[i + 1].getElementsByTagName("TD")[nVa[0]];
      } else {
        y = rows[i].getElementsByTagName("TD")[nVa[0]];
        x = rows[i + 1].getElementsByTagName("TD")[nVa[0]];        
      }
      if (nVa[1]=="N") {
          if (parseFloat(x.innerHTML) < parseFloat(y.innerHTML)){
            shouldSwitch = true;
            break;
          }
      } else {
          if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()){
            shouldSwitch = true;
            break;
          }
      }
    }
    if (shouldSwitch) {
      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
      switching = true;
    }
  }
}


</script>
</body>