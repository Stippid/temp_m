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
		background-color: rgba(255,0,0,0.4)!important;
		color:blue!important;
	}

	.nRowExpandH {
		border:2px solid blue;
		background-color: lavender;
	}
	.nRowExpandC {
		border:1px solid transparent;
		background-color: azure;
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
	input {
		font-size:1.5em;
	}
	
	p {
		color:black;
	}
</style>
<body class="mmsbg" onload="setMenu();">
	<form:form name="fp_info_board" id="fp_info_board" method='POST'>
		<input type="hidden" name="tr_head" id="tr_head" />
		<div class="" align="center" style="width:100%; margin:0px;padding:0px;border:0px;">
			<div class="card">
				<div class="card-header mms-card-header ncard-body-bg"
					style="min-height: 50px; padding-top: 10px;"><h5><span>DO Letters</span></h5>
				</div>
				<div class="row form-group" id=""  style="margin-top: 20;background-color: azure;font-size:1.5em">
						<div class="col-md-3">
							<input type="button" class="btn btn-success btn-sm nViewMoreButton nGlow" value="Staff College DO" id="btn_p" style="float:right;background-color:lightgray;padding:5px 10px;color:blue;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" data-toggle="tooltip" title="Create DO Letters for Selected Eligibles" onclick="selectbtn('1');">
						</div>
						<div class="col-md-3">
							<input type="button" class="btn btn-success btn-sm nViewMoreButton nGlow" value="Condolence DO" id="btn_p" style="float:right;background-color:lightgray;padding:5px 10px;color:blue;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" data-toggle="tooltip" title="Create DO Letters for Veterans" onclick="selectbtn('2');">
						</div>
						<div class="col-md-3">
							<input type="button" class="btn btn-success btn-sm nViewMoreButton nGlow" value="Anniversary DO" id="btn_p" style="float:right;background-color:lightgray;padding:5px 10px;color:blue;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" data-toggle="tooltip" title="Create DO Letters for Aniversary" onclick="selectbtn('3');">
						</div>
						<div class="col-md-3">
							<input type="button" class="btn btn-success btn-sm nViewMoreButton nGlow" value="Birthday DO" id="btn_p" style="float:right;background-color:lightgray;padding:5px 10px;color:blue;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" data-toggle="tooltip" title="Create DO Letters for Birthdays" onclick="selectbtn('4');">
						</div>
				</div>
				<div class="card-body card-block" style="min-height:60vh;background-color: azure;margin:0px;padding:0px;border:5px;">
	            	<div class="row form-group" id="nBase_data"  style="display:none;margin-top: -20;background-color: azure;">	
						<div id="divShowDtl" class="" style="width:100%;display:none;z-index:300;background-color: azure;">
							<!-- <div style="width:100%">
								<div style="font-size:1.1vw;float:right;" id="nrTableDataDivDtlTitleee">&nbsp;&nbsp;Show&nbsp;&nbsp;								
									<select id="DtlSlvlOpt" class="form-control-sm" onchange="nChgMode('data');" style="font-size:1.1vw;" title="Select Budget Holders/Heads">
										<option value="BHL">Budget Holders wise Summary</option>
										<option value="BHD">Budget Heads wise Summary</option>																				
									</select>
								</div>
							</div> -->
							<div class="" style="width:100%">
								<input type="hidden" id="nrDetlInput" value="ALL">								
								<div id="nMsgBoard" style="width:100%;">									
									<div class="row" style="background-color: azure;font-size:1.5em;width: calc(78vw - 18px);display:inherit;margin-left: 5px;">
										<div class="col-md-12">
											<div class="col-md-6">
												<input id="nrInput" type="text"	placeholder="Search ..." style="font-weight: normal;font-size: 14px;border: 1px solid #ddd;padding: 4px;" title="Search Code Head">
											</div>
											<div class="col-md-6">
												&emsp;<input type="radio" value="XNRE" name="whois" id="whois">&nbsp;DSSC Eligibles Only
												&emsp;
												<input type="radio" value="XNR" id="whois" name="whois" checked>&nbsp;All Candidates
											</div>											
										</div>										
									</div> 
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
						<!-- <input type="button" class="btn btn-success btn-sm" value="Export to Excel" id="btn_e" style="background-color: purple;" data-toggle="tooltip" title="Export Data to Excel" onclick="exportData('#nrTableDataDivDtl');"> -->
					</div>
					<div class="col-md-8">
						<!-- <a class="nViewMoreButton" style="float:right;background-color:lightgray;padding:5px 10px;color:blue;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" href="fp_cr_dft_word" onclick="printdo();" title="Create DO Letters for Selected Eligibles">Create DO Letter in Word</a> -->
						<input type="button" class="btn btn-success btn-sm  nGlow nViewMoreButton" value="Generate DO Letter" id="btn_p" style="float:right;background-color:lightgray;padding:5px 10px;color:blue;box-shadow: 0 10px 8px 0 rgba(0,0,0,.8);" data-toggle="tooltip" title="Create DO Letters for Selected Eligibles" onclick="printdo();">
						<span value="Export" id="btn_e" style="float:center;font-size:1.4em;padding:5px 10px;">Legends :
							<span id="lg1" style="">Eligible</span>
							<span id="lg2" style="">Not Eligible</span>
						</span>
					</div>
				</div>
				
			</div>
		</div>
	
				<div class="nPopCContainer" id="nPrintLetter" style="width:22cm;height:85%;display:none;z-index:499;background-color: white;border:5px solid lightgray;">
					<c:set var="ltropt" value="${DtlFilterOpt}" />
					<div class="nPopHeader">
						DO Letter	
						<span class="nPopClose" onclick="$('#nPrintLetter').hide();" title="Close Msg Window">&#10006;</span>
					</div>
					<div class="nPopTablee" id="nPrintLetterDiv" style="min-height:70%;max-height:91%;height:29.7cm;width:21cm;overflow: auto;color:black;">
					<style> .nBox {	border:1px solid black;	padding:7px;} </style>
						<div class="nPopBody">
							<div class="row form-group">
								<div class="col-md-12">					
									<table border="0"style="width:100%;line-height: 1;table-layout: fixed;border-collapse: collapse;padding-bottom:10px;">
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
</form:form>		
<script src="js/miso/miso_js/jquery-2.2.3.min.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/common/select2/select2.min.js" type="text/javascript"></script>

<script type="text/javascript">
		$(document).ready(function() {
			$("#DtlFilterBH").select2();
			
			nChgMode('data');
			$("#nrInput").on("keyup",function() {
				var value = $(this).val().toLowerCase();
				$("#nrTableShowDtl tr").filter(function() {
					$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
			});

		    $("[name *=whois]").click(function(){
		    	//alert("selected"+$(this).val());		    	
		    	var aa=$(this).val();
		    	if(aa=="XNRE") {
		    		showdetl("DSSCE");
		    	} else {
		    		showdetl("DSSC");
		    	}		    	
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
		showdetl("DSSC");
	}
	$('#nBase_'+a+"_h").show();
	$('#nBase_'+a).show();		
}

function showdetl(a) {
	var nHed="";
	var nHedt="";
	var slvl=$("#DtlSlvlOpt").val();
	$("#nrWaitLoader").show();	
	nHed=a;
	bb="";
	$.post("fpdoletterdata?"+key+"="+value, {m1_lvl:nHed,m1_rptLvl:bb}, function(j) {
 	}).done(function(j) { 	
 		//alert(j);
 		var itxts="";
 		var itxtth="";
 		var itxtthtot="";
		var itxt="";
		var tmpv="";
		itxtth += '<tr><th style="width:20px;"></th><th style="width:100px;">IC No</th><th width="70px">Rank</th><th width="250px">Name</th>';
		itxtth += '<th width="150px">Present Appt</th><th width="200px">Present Unit</th><th width="200px">Parent Unit</th>';
		itxtth += '<th width="150px">Serice</th><th width="50px">Part D</th>';
		//$("#nrTableShowDtlHead").text(itxtth);
		tmpv1='background-color:lightgreen;color:blue;';
		tmpv2='background-color:mistyrose;color:black;';
 		for (var i=0;i<j.length;i++){
 			console.log(i,"-",j[i][1]);
 			var expstot=0
 			var lnalt=0;
 			var lnexp=0;
 			var lnfwd=0;
 			var lnbkd=0;
 			var btnid="";
 			tmpv="";
 			if (j[i][9]=='E') {
 				tmpv='style="'+tmpv1+'"';
 			} else {
 				tmpv='style="'+tmpv2+'"';
 			}
			itxt += '<tr '+tmpv+' >';
			itxt += '<td id="L'+j[i][9]+'_'+j[i][0]+'_'+j[i][1]+'_0" >';
			itxt += '<input type="checkbox" name="cndlist_L'+j[i][9]+'" id="'+j[i][0]+'"></td>';
			itxt += '<td id="L'+j[i][9]+'_'+j[i][0]+'_'+j[i][1]+'_1" >'+j[i][0]+'</td>';
			itxt += '<td id="L'+j[i][9]+'_'+j[i][0]+'_'+j[i][1]+'_2" >'+j[i][1]+'</td>';
			itxt += '<td id="L'+j[i][9]+'_'+j[i][0]+'_'+j[i][1]+'_3" >'+j[i][2]+'</td>';
			itxt += '<td id="L'+j[i][9]+'_'+j[i][0]+'_'+j[i][1]+'_4" >'+j[i][3]+'</td>';
			itxt += '<td id="L'+j[i][9]+'_'+j[i][0]+'_'+j[i][1]+'_5" >'+j[i][4]+'</td>';
			itxt += '<td id="L'+j[i][9]+'_'+j[i][0]+'_'+j[i][1]+'_6" >'+j[i][5]+'</td>';
			itxt += '<td id="L'+j[i][9]+'_'+j[i][0]+'_'+j[i][1]+'_7" >'+j[i][6]+'</td>';
			itxt += '<td id="L'+j[i][9]+'_'+j[i][0]+'_'+j[i][1]+'_8" >'+j[i][7]+'</td>';
	 		itxtthtot="";
			/* itxtthtot += '<tr style="color:blue!important;font-size:1vw;text-align:right;"><th width="7%"></th><th width="18%">Over All Total</th><th width="7%">'+Number(altgtot).toINR("","CR","INR","CR")+'</th>';
			itxtthtot += '<th width="7%" class="ALC">'+Number(expgtot).toINR("","CR","INR","CR")+'</th><th width="7%" class="ALC">'+Number(gstgtot).toINR("","CR","INR","CR")+'</th><th width="5%" class="ALC">'+Number(edtgtot).toINR("","CR","INR","CR")+'</th>';
			itxtthtot += '<th width="5%" class="ALC">'+Number(othgtot).toINR("","CR","INR","CR")+'</th><th width="7%" class="ALC EXP EBK">'+Number(expsgtot).toINR("","CR","INR","CR")+'</th><th width="7%" class="EXP FBK">'+Number(fwdgtot).toINR("","CR","INR","CR")+'</th>';
			itxtthtot += '<th width="7%" class="ABC EBK FBK">'+Number(bkdgtot).toINR("","CR","INR","CR")+'</th><th width="4%" class="ALC">'+calper(altgtot,expsgtot)+'</th>';
			itxtthtot += '<th width="1%" class="EXP ABD">'+calper(expsgtot,fwdgtot)+'</th><th width="1%" class="EBK">'+calper(expsgtot,bkdgtot)+'</th>';
			itxtthtot += '<th width="1%" class="FBK" ></th>'+calper(fwdgtot,bkdgtot)+'<th width="3%"></th></tr>'; */
			$("#th_nrTableShowDtl").html(itxtth); 		
	 		$("#nrTableShowDtl").html(itxt);
			$("#th_nrTableShowtot").html(itxtthtot);			
	 		$("[class *=':L2']").hide();
	 		$("#lg1").attr("style",tmpv1);
	 		$("#lg2").attr("style",tmpv2);
	 		//setdtlFilter();
		}
 	}).fail(function(xhr, textStatus, errorThrown) { });
	$("#divShow").hide();
	$("#nrWaitLoader").hide();
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


function setdtlFilter() {	
	var perty=$("#DtlFilterPerTy").val();
	var perval=$("#DtlFilterPerOpt").val();
	var value=""+$("#DtlFilterOpt").val().toUpperCase();
	var valuebh=""+$("#DtlFilterBH").val().toUpperCase();
	var valuebh1=valuebh.split("_");
	$("#DtlFilterBH_src").val("");
	$("[name*=TR_]").hide();
	if (valuebh1[2]=="ALL") {
		$("[name*=TR_]").show();
		$(".ALC").show();
		$(".EXP").show();
	} else {
		$("[name*=TR_"+valuebh1[2]+"]").show();
	} 
 	if (value=="ALL") {		
		$(".FBK").hide();
		$(".EBK").hide();				
		$(".ALC").show();
		$(".ABC").show();
		$(".EXP").show();
		$(".ABD").hide();
	} else if (value=='ALC'){
		$(".EXP").hide();
		$(".FBK").hide();
		$(".EBK").hide();
		$(".ALC").show();
	} else if (value=='EXP') {		
		$(".ALC").hide();
		$(".FBK").hide();
		$(".EBK").hide();
		$(".EXP").show();		
	} else if (value=='EBK') {		
		$(".ALC").hide();			
		$(".EXP").hide();
		$(".FBK").hide();
		$(".EBK").show();
	} else if (value=='FBK') {		
		$(".ALC").hide();			
		$(".EXP").hide();
		$(".EBK").hide();
		$(".FBK").show();
	} 	
 	perHQmarker('1');
 	$("[class *=':L2']").hide();
 }

function exportData(b){
	$().tbtoExcel(b);
	b.preventDefault();
}

function nDetdata(a) {
	var ab=a;	
	$("[class *=':L2_"+ab+"']").toggle();
 	if ($("[class *=':L2_"+ab+"']").is(":visible")) {
		$("[class *=':L1_"+ab+"']").addClass(" nRowExpandH");
		$("[class *=':L2_"+ab+"']").addClass(" nRowExpandC");
		permarker('1',ab);				
	} else {
		$("[class *=':L1_"+ab+"']").removeClass(" nRowExpandH");
		$("[class *=':L2_"+ab+"']").removeClass(" nRowExpandC");
		permarker('0',ab);
	} 
}

function perHQmarker(a) {
	var b=$("#DtlFilterOpt").val();
	var bv="";
	if (b=='ALC') {
		bv="A";
	} else if (b=='EXP') {
		bv="X";
	} else if (b=='EBK') {
		bv="B";
	} else if (b=='FBK') {
		bv="F";
	} else {
		bv="A";
	}
	
	var exval=0;
	var gdval=0;
	var prval=0;
	
	var nst=0;
	var nen=0;
	var perty=$("#DtlFilterPerTy").val();
	var perval=$("#DtlFilterPerOpt").val();
	if (perty=='GE') {
		nst=perval;
		nen=100;
	} else {
		nst=0;
		nen=perval;		
	}
	exval=perval-10;
	gdval=perval-20;
	prval=perval-20;;
	

	if (a=='1') {
		$("[class *=':1"+bv+"P']").removeClass(" npoorRow");
		$("[class *=':1"+bv+"P']").removeClass(" ngoodRow");
		$("[class *=':1"+bv+"P']").removeClass(" nExlntRow");
		$("#lg1").text("Excellent");
		$("#lg2").text("Good");
		$("#lg3").text("Poor");
		if ((perval-1)>0) {
			$("#lg1").text("Excellent : >="+perval+"% - "+(perval-10)+"%");
		}
		if ((perval-11)>0) {
			$("#lg2").text("Good : "+(perval-11)+"% - "+(perval-20)+"%");
		}
		if ((perval-21)>0) {
			$("#lg3").text("Poor : <="+(perval-21));
		} 
		var iqt="";
		for (var iq=0;iq<=100;iq++) {
			if (iq<10) {
				iqt ="0"+iq;
			} else {
				iqt =iq;
			}			
			if (iq<gdval || iq==0) {
				$("[class *=':1"+bv+"P"+iqt+"']").addClass(" npoorRow");
			}
			if (iq>=gdval && iq<exval) {			
				$("[class *=':1"+bv+"P"+iqt+"']").removeClass(" npoorRow");
				$("[class *=':1"+bv+"P"+iqt+"']").addClass(" ngoodRow");
			} 
			if (iq>=exval || iq>=perval) {
				console.log("Excellent-",perval,exval,iq);
				$("[class *=':1"+bv+"P"+iqt+"']").removeClass(" npoorRow");
				$("[class *=':1"+bv+"P"+iqt+"']").removeClass(" ngoodRow");
				$("[class *=':1"+bv+"P"+iqt+"']").addClass(" nExlntRow");
			}
		}					
	}
}

function permarker(a,p) {
	var b=$("#DtlFilterOpt").val();
	var bv="";
	if (b=='ALC') {
		bv="A";
	} else if (b=='EXP') {
		bv="X";
	} else if (b=='EBK') {
		bv="B";
	} else if (b=='FBK') {
		bv="F";
	} else {
		bv="A";
	}
	var nst=0;
	var nen=0;
	var perty=$("#DtlFilterPerTy").val();
	var perval=$("#DtlFilterPerOpt").val();
	if (perty=='GE') {
		nst=perval;
		nen=100;
	} else {
		nst=0;
		nen=perval;		
	}	
	if (a=='1') {
		$("[class *=':2"+bv+"P']").removeClass(" npoorRow");
		$("[class *=':2"+bv+"P']").removeClass(" ngoodRow");
		$("[class *=':2"+bv+"P']").removeClass(" nExlntRow");
		var iqt="";
		for (var iq=0;iq<=100;iq++) {
			if (iq<10) {
				iqt ="0"+iq;
			} else {
				iqt =iq;
			}			
			if (iq<nen-20 || iq==0) {
				$("[class *=':2"+bv+"P"+iqt+"']").addClass(" npoorRow");
			}
			if (iq>=(nen-20) && iq<(nen-10)) {			
				$("[class *=':2"+bv+"P"+iqt+"']").removeClass(" npoorRow");
				$("[class *=':2"+bv+"P"+iqt+"']").addClass(" ngoodRow");
			} 
			if (iq>=nen-10 || iq>=perval) {
				$("[class *=':2"+bv+"P"+iqt+"']").removeClass(" npoorRow");
				$("[class *=':2"+bv+"P"+iqt+"']").removeClass(" ngoodRow");
				$("[class *=':2"+bv+"P"+iqt+"']").addClass(" nExlntRow");
			}
		}
	}
}

function nPrintdata(b) {
	var dtext="";
	var b1=b.split("_");
	var untnm1=$("#L1_"+b1[0]+"_1").html().split('</span>');
	var untnm=untnm1[1];
	dtext +="";
	var abc=$("[id*='L2_"+b+"_3']").html();
	dtext +="<td style='text-align:left' class='nBox' >"+abc+"</td>";
	dtext +="<td style='width:15%;' class='nBox' >"+$("[id*='L2_"+b+"_4']").html()+"</td>";
	dtext +="<td style='width:15%;' class='nBox'>"+$("[id*='L2_"+b+"_9']").html()+"</td>";
	dtext +="<td style='width:15%;' class='nBox'>"+$("[id*='L2_"+b+"_10']").html()+"</td>";
	dtext +="<td style='width:15%;' class='nBox'>"+$("[id*='L2_"+b+"_11']").html()+"</td>";
	dtext +="<td style='width:5%;' class='nBox'>"+$("[id*='L2_"+b+"_12']").html()+"</td>";
	$("#nPrintLetterDiv_unt").text(untnm);
	$("#nPrintLetterDataid").html(dtext);	
	$("#nPrintLetter").show();
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

function nPrintLetter(b,c) {
	$("#nrWaitLoader").show();
	var bopt=$("#DtlFilterOpt").val();
	var dtext="";
	var tmpchk="";
	var b1=b.split("_");
	var untnm1=$("#L1_"+b1[0]+"_1").html().split('</span>');
	var untnm=untnm1[1];
	dtext +="";
	dtext +="<tr><td style='width:20Px;'>To,</td><td style='width:400px;'></td><td style='width:100px;'>Date: ${fn:substring(curDate,0,10)}</td>";
	if (bopt =='FBK') {
		dtext +="<tr><td></td><td>PCDA</td>";
	} else {
		dtext +="<tr><td></td><td>"+untnm+"</td>";
	}
	dtext +="<tr><td></td><td>____________</td>";
	dtext +="<tr><td></td><td>____________</td>";
	dtext +="<tr><td><br></td>";
	dtext +="<tr><td colspan='2'>Sub:- ";
	if (bopt =='EXP') {
		dtext +="Expdr vs Bills Fwd to CDA State.";	
	} else if (bopt =='EBK') {
		dtext +="Expdr vs Booking State.";	
	} else if (bopt =='FBK') {
		dtext +="Bills Fwd to CDA vs Booking State.";	
	} else {
		dtext +="Allotment vs Expdr State.";	
	}
	dtext +="</td>";
	
	dtext +="<tr><td><br></td>";
	dtext +="<tr><td>1.</td><td colspan='2'>Your State as on ${fn:substring(curDate,0,10)} is as under:-</td>";
	dtext +="<tr><td></td><td colspan='2'>";										
	dtext +="<table style='width:100%;border:1px solid black;border-collapse: Collapse!important;box-sizing: border-box!important;padding:10px;>";
	dtext +="<tr style='text-align:center;text-align:center;text-weight:bold;'><td class='nBox'>Head</td><td class='nBox' >Allocation (in Cr)</td>";	
	if (bopt =='EXP') {
		dtext +="<td  class='nBox' >Expenditure (in Cr)</td>";
		dtext +="<td  class='nBox' >Amount Fwd to CDA (in Cr)</td><td class='nBox'>%</td>";
	} else if (bopt =='EBK') {
		dtext +="<td  class='nBox' >Expenditure (in Cr)</td>";
		dtext +="<td  class='nBox' >Amount Booked by CDA (in Cr)</td><td class='nBox'>%</td>";	
	} else if (bopt =='FBK') {
		dtext +="<td  class='nBox' >Amount Fwd to CDA (in Cr)</td>";
		dtext +="<td  class='nBox' >Amount Booked by CDA (in Cr)</td><td class='nBox'>%</td>";	
	} else {
		dtext +="<td  class='nBox' >Expenditure (in Cr)</td>";
		dtext +="<td  class='nBox' >Amount Fwd to CDA (in Cr)</td>";
		dtext +="<td  class='nBox' >Amount Booked by CDA (in Cr)</td><td class='nBox'>%</td>";		
	}
	if (c=='1') {
		table = $("[id*='L2_"+b1[0]+"_']");
	} else {
		table = $("[id*='L2_"+b+"_']");
	}
	switching = true;
  	while (switching) {
    	switching = false;
    	rows = table;
	    for (i = 1; i < (rows.length - 1); i++) {
	      shouldSwitch = false;
          x = $(rows[i]).attr("id");
          x1=x.split("_");
          if (tmpchk !=x1[2]){
			var abc="[id*='L2_"+x1[1]+"_"+x1[2];
			dtext +="<tr style='text-align:right;'>";
			dtext +="<td style='text-align:left' class='nBox' >"+$(abc+"_3']").html()+"</td>";
			dtext +="<td style='width:15%;' class='nBox' >"+$(abc+"_4']").html()+"</td>";
			if (bopt =='EXP') {
				dtext +="<td style='width:15%;' class='nBox'>"+$(abc+"_9']").html()+"</td>";
				dtext +="<td style='width:15%;' class='nBox'>"+$(abc+"_10']").html()+"</td>";
				dtext +="<td style='width:5%;' class='nBox'>"+$(abc+"_14']").html()+"</td>";
			} else if (bopt =='EBK') {
				dtext +="<td style='width:15%;' class='nBox'>"+$(abc+"_9']").html()+"</td>";
				dtext +="<td style='width:15%;' class='nBox'>"+$(abc+"_11']").html()+"</td>";
				dtext +="<td style='width:5%;' class='nBox'>"+$(abc+"_15']").html()+"</td>";
			} else if (bopt =='FBK') {
				dtext +="<td style='width:15%;' class='nBox'>"+$(abc+"_10']").html()+"</td>";
				dtext +="<td style='width:15%;' class='nBox'>"+$(abc+"_11']").html()+"</td>";
				dtext +="<td style='width:5%;' class='nBox'>"+$(abc+"_16']").html()+"</td>";
			} else {
				dtext +="<td style='width:15%;' class='nBox'>"+$(abc+"_9']").html()+"</td>";
				dtext +="<td style='width:15%;' class='nBox'>"+$(abc+"_10']").html()+"</td>";
				dtext +="<td style='width:15%;' class='nBox'>"+$(abc+"_11']").html()+"</td>";
				dtext +="<td style='width:5%;' class='nBox'>"+$(abc+"_12']").html()+"</td>";
			}
			tmpchk=x1[2];
          }
	    }
  	}	
	dtext +="</table>";
	dtext +="</td>";
	dtext +="<tr><td><br></td>";
	
	dtext +="<tr><td>2.</td><td colspan='2'>You are requested to ";
	if (bopt =='EXP') {
		dtext +="expedite Expdr. ";	
	} else if (bopt =='EBK') {
		dtext +="Expdr and Booking State.";	
	} else if (bopt =='FBK') {
		dtext +="expedite Bills Fwd to CDA and Booking State.";	
	} else {
		dtext +="expedite Expdr State.";	
	}
	dtext +="</td>";
	dtext +="<tr><td><br></td>";
	dtext +="<tr><td><br></td>";
	dtext +="<tr><td><br></td>";
	dtext +="<tr><td colspan='2'></td><td>( Col FP )</td>";
	dtext +="<tr><td colspan='2'></td><td>for DGFP</td>";
	$("#nPrintLetterDiv_tb").html(dtext);	
	$("#nrWaitLoader").hide();
	$("#nPrintLetter").show();
}

function printdo1(){
	//alert("Printing...");
	var b="cndlist"
	//var rptyord=$("[name *='cndlist']:checked").val();
	//alert(rptyord);
	var nrSel=$("[name *="+b+"]:checkbox:checked").map(function() {
		return $(this).attr('id');
	}).get();		
	var bb=nrSel.join(',');
	alert(bb);
	var lvl='ICNO';
	$.post("fp_cr_dft_word?"+key+"="+value, {m1_lvl:lvl,m1_rptLvl:bb}, function(j) {
 	}).done(function(j) { 	
 		alert("Pl Find the Created Letter at 'C:\\miso' Folder");
 	}).fail(function(xhr, textStatus, errorThrown) { });
	
	
}

function printdo(){
	//alert("Printing...");
	var b="cndlist"
	//var rptyord=$("[name *='cndlist']:checked").val();
	//alert(rptyord);
	var nrSel=$("[name *="+b+"]:checkbox:checked").map(function() {
		return $(this).attr('id');
	}).get();		
	var bb=nrSel.join(',');
	//alert(bb);
	var lvl='ICNO';
	$.post("fp_cr_dft_wordData?"+key+"="+value, {m1_lvl:lvl,m1_rptLvl:bb}, function(j) {
 	}).done(function(j) { 	
 		//alert(j[0][1]);
 		var dtext="";
 		for (i = 0; i < j.length; i++) {
 			    console.log(i,j[i][1]);
 				dtext +="<p>"+j[i][1]+" "+j[i][2]+"</p>";
 				dtext +="<p>"+j[i][3]+"</p>";
 				dtext +="<p>"+j[i][4]+"</p>";
 				dtext +="<br>";
 				dtext +="<p><b><u><center>DSSC Exam</center></u></b></p>";
 				dtext +="<br><br>";
 				dtext +="<p>1.    DSSC Course is an Important Facet in the career progression of young offrs.</p>";
 				dtext +="<p>2.    As you would be appearing for your first mandatory attempt for the course, I wish to emphasize the importance of diligent and meticulous prep.</p>";
 				dtext +="<p>3.    I am sanguine that you will put in the requisite efforts for clearing the exam. Wishing you the best.</p>";
 				dtext +="<br><br>";
 				dtext +="<br><br>";
 				dtext +="<br><br>";
 				dtext +="<br><br>";
 				dtext +="<br><br>";
 				dtext +="<hr>";
	    }
 		//alert(dtext);
		$("#nPrintLetterDiv_tb").html(dtext);	
		$("#nPrintLetter").show();
 	}).fail(function(xhr, textStatus, errorThrown) { });
	
	
}


function setselectall(a,b){
	var a=$("[name *=whois]").val();
	if (a=='XNRE') {
		//if($("#"+a).prop("checked")){
			$("[name *=cndlist_LE]").prop('checked', true);
			//alert("Checked");
		//}else{
			//$("[name *=cndlist_LE]").prop('checked', false);
			//alert("UnChecked");
		//}
	}
}

function findselected(a,b){
	//alert(a+","+b);
	var nrtSel=$("[name *="+b+"]:checkbox").map(function() {
		return $(this).attr('name');
	}).get();		
	var nrSel=$("[name *="+b+"]:checkbox:checked").map(function() {
		return $(this).attr('name');
	}).get();		
	var b=nrSel.join(',');
	//$("#nrSrcSel").val(b);
	$('#t'+a).text(nrSel.length);
	$('#tt'+a).text(nrtSel.length);
}

function selectbtn(a) {
	if (a=='1') {
		showdetl("DSSC");
	}
	if (a=='2') {
		showdetl("DSSCE");
	}
	if (a=='3') {
		showdetl("DSSC");
	}
	if (a=='4') {
		showdetl("DSSC");
	}
}
</script>
</body>