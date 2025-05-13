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
</style>
<body class="mmsbg" onload="setMenu();">
	<form:form name="fp_info_board" id="fp_info_board" method='POST'>
		<input type="hidden" name="tr_head" id="tr_head" />
		<div class="" align="center" style="width:100%; margin:0px;padding:0px;border:0px;">
			<div class="card">
				<div class="card-header mms-card-header ncard-body-bg"
					style="min-height: 50px; padding-top: 10px;"><h5><span>FIN PERFORMANCE INDICATOR</span></h5>
				</div>
				<div class="card-body card-block" style="min-height:60vh;background-color: azure;margin:0px;padding:0px;border:5px;">
	            	<div class="row form-group" id="nBase_data"  style="display:none;margin-top: -20;background-color: azure;">	
						<div id="divShowDtl" class="" style="width:100%;display:none;z-index:3;background-color: azure;">
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
									<div class="row" style="background-color: azure;width: calc(79vw - 8px);display:inherit;margin-left: 5px;">
										<div class="" style="align-self:center;padding:10px;height: 40px;width: calc(98vw - 8px);display:inline-flex;">
											<div style="width:45%">
													Budget Holders&nbsp;:&nbsp;
													<select id="DtlFilterBH" onchange="setdtlFilter();" style="width:70%" title="Select Budget Holder">
														<option value="ALL_ALL_ALL">
															All Budget Holders
														</option>
														<c:forEach var="item" items="${n_hq}" varStatus="num">
								   								<option value="${item[3]}_${item[0]}_${item[1]}">			   							
								   								<c:if test="${item[3] =='1'}">
								   									&nbsp;
								   								</c:if>
								   								<c:if test="${item[3] =='2'}">
								   									&nbsp;&nbsp;
								   								</c:if> 					   								 
								   								<c:if test="${item[3] =='3'}">
								   									&nbsp;&nbsp;&nbsp;
								   								</c:if> 					   								 
								   								<c:if test="${item[3] =='4'}">
								   									&nbsp;&nbsp;&nbsp;&nbsp;
								   								</c:if>
								   								<c:if test="${item[3] =='5'}">
								   									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								   								</c:if>				   								 
								   								${item[2]}					   								
								   							</option>		   						
											        	</c:forEach>
										        	</select>
										 	</div>
										 	<div style="width:15%">
											 	Filter&nbsp;:
												<select id="DtlFilterOpt" onchange="setdtlFilter();" title="Select Filter">
													<option value="ALL">ALL Tr</option>
													<option value="ALC" title="Allotment vs Expenditure">Alt vs Expdr</option>
													<option value="EXP" title="Expenditure vs Fwd to CDA">Expdr vs Fwd</option>
													<option value="EBK" title="Expenditure vs Booked by CDA">Expdr vs Booking</option>
													<option value="FBK" title="Fwd to CDA vs Booked by CDA">Fwd vs Booking</option>
													<%-- <option value="EXP">CDA Status</</center> --%>
												</select>
										 	</div>
										 	<div style="width:15%">
											 	Range&nbsp;:
											 	<select id="DtlFilterPerTy" onchange="setdtlFilter();" style="display:none;">
													<option value="GE"> >= </option>
													<option value="LE" selected> <= </option>
												</select>
												<select id="DtlFilterPerOpt" onchange="setdtlFilter();" title="Select Range">
													<option value="100"><= 100 %</option>
													<option value="90"><= 90 %</option>
													<option value="80"><= 80 %</option>
													<option value="75"><= 75 %</option>
													<option value="70"><= 70 %</option>
													<option value="60" selected><= 60 %</option>
													<option value="50"><= 50 %</option>
													<option value="40"><= 40 %</option>
													<option value="30"><= 30 %</option>
													<option value="25"><= 25 %</option>
													<option value="20"><= 20 %</option>
													<option value="10"><= 10 %</option>													
												</select>
										 	</div>											 	
										 	<div style="font-size:1.1vw;float:right;width:25%!important" id="nrTableDataDivDtlTitleee">&nbsp;&nbsp;Show&nbsp;&nbsp;								
												<select id="DtlSlvlOpt" class="form-control-sm" onchange="nChgMode('data');" style="font-size:1.1vw;" title="Select Budget Holders/Heads">
													<option value="BHL">Budget Holders wise Summary</option>
													<option value="BHD">Budget Heads wise Summary</option>																				
												</select>
											</div>
										</div>
									</div>
	 								<div class="nPopTable" id="nrTableDataDivDtl" style="height:55vh;width:100%;margin-top:10px;"> 
										<table style="width:100%;border-collapse: separate;">
							               	<thead style="text-align: center;line-height:25px;font-size: 1vw;border-spacing: 0 0PX!important;" id="th_nrTableShowDtl">  
							    				<tr>
							    					
								   					<th width="23%">Head</th>
								      				<th width="7%">Budget Holder</th>
								      				<th width="7%">Allocation</th>
													<th width="7%" class="ALC EXP">Expenditure</th>
													<th width="7%" class="ALC EXP">GST</th>
													<th width="7%" class="ALC EXP">Excise CEES</th>
													<th width="7%" class="ALC EXP">Others Taxes</th>
													<th width="7%" CLASS="ALC EXP EBK">Total Expenditure</th>
													<th width="7%" class="EXP FBK">Fwd to CDA</th>
													<th width="7%" class="EXP EBK FBK">Booked by CDA</th>
													<th width="7%" class="EXP EBK FBK">Booked by CDA</th>
													
									  			</tr>
							    		  	</thead>
										    <tbody id="nrTableShowDtl" style="font-size: .90vw;text-decoration: none;color:blue;">											
										    </tbody>
										    <tfoot id="th_nrTableShowtot">
										    <tr>							    					
								   					<td width="24%"></td>
								      				<td width="7%"></td>
								      				<td width="7%"></td>
													<td width="7%" class="ALC EXP"></td>
													<td width="7%" class="ALC EXP"></td>
													<td width="7%" class="ALC EXP"></td>
													<td width="7%" class="ALC EXP"></td>
													<td width="7%" CLASS="ALC EXP EBK"></td>
													<td width="7%" class="EXP FBK"></td>
													<td width="7%" class="EXP EBK FBK"></td>
													<td width="7%" class="EXP EBK FBK"></td>
													<td width="1%"></td>
																										
									  			</tr>											
										    </tfoot>
										    </table>
									</div>																											
							</div>
							</div>
						</div>
			  		</div>
				  	<div class="row form-group" id="nBase_bhld_h" style="display:none">
						<input type="text" id="bhld_src" name="nBase_bhld_src" placeholder="Search in Head..." class=""  style="float:right;text-transform: uppercase;"
	                    autofocus autocomplete="off" size="30" title="Type Word or Part of Word to Search in Budget Holders" data-toggle="tooltip">
	            	</div>	
	            	<div class="row form-group" id="nBase_bhld"  style="display:none">	
						<div class="nPopTable" id="divPrint" style="height:48vh;width:100%;overflow: auto;">
							<table border="1" id="nSelHead" name="nSelHead"	style="line-height: 25px; font-size: 14px; border: 1px solid lightgray; width: 100%;">
								<thead>
									<th>Budget Holders</th>
								</thead>						
								<tbody id="bhld_body" style="font-size: 1vw; text-decoration: none;">
									<c:if test="${n_unt == 'NIL'}">
										<tr class='nrSubHeading'>
											<td colspan='9' style='text-align: center;'>Data Not Available...</td>
										</tr>
									</c:if>
									<c:if test="${n_unt != 'NIL'}">
										<c:forEach var="item" items="${n_unt}" varStatus="num">
											<tr>
												<td>&nbsp;<input type="checkbox" name="code_head"	id="tr_head_${item[1]}" value="${item[1]}">&nbsp;${item[1]}- ${item[2]}</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				<div class="row form-group" id="nBase_bhd_h"  style="display:none">
					<input type="text" id="bhd_src" name="nBase_bhd_src" placeholder="Search in Head..." class=""  style="float:right;text-transform: uppercase;"
                    autofocus autocomplete="off" size="30" title="Type Word or Part of Word to Search in Budget Heads" data-toggle="tooltip">
            	</div>	
            	<div class="row form-group" id="nBase_bhd"  style="display:none">	
					<div class="nPopTable" id="divPrint" style="height:52vh;width:100%;overflow: auto;">
						<table border="1" id="nSelHead" name="nSelHead"	style="line-height: 25px; font-size: 14px; border: 1px solid lightgray; width: 100%;">
							<thead>
								<th>Budget heads</th>
							</thead>
							<tbody id="bhd_body"	style="font-size: 1vw; text-decoration: none;">
								<c:if test="${n_head == 'NIL'}">
									<tr class='nrSubHeading'>
										<td colspan='9' style='text-align: center;'>Data Not Available...</td>
									</tr>
								</c:if>
								<c:if test="${n_head != 'NIL'}">
									<c:forEach var="item" items="${n_head}" varStatus="num">
										<c:set var="dataf" value="${fn:split(item[0],':')}" />
										<c:set var="datafm" value="${fn:length(dataf)-1}" />
										<c:if test="${item[2] == '4'}">
											<tr>
												<td>&nbsp;<input type="checkbox" name="code_head"	id="tr_head_${item[0]}" value="${item[0]}">&nbsp;${dataf[datafm]}- ${item[1]}</td>
											</tr>
										</c:if>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row form-group" id="nBase_bhld_h" style="display:none">
					<input type="text" id="bhld_slvl" name="nBase_slvl_src" placeholder="Search in Head..." class=""  style="float:right;text-transform: uppercase;"
                    autofocus autocomplete="off" size="30" title="Type Word or Part of Word to Search in Budget Holders" data-toggle="tooltip">
            	</div>	
            	<div class="row form-group" id="nBase_slvl"  style="display:none">	
					<div class="nPopTable" id="divPrint" style="height:45vh;width:100%;overflow: auto;">
						<div class="row form-group">
							<div class="col-md-4">
								&nbsp;&nbsp;Display Order&nbsp;&nbsp;
								<select id="DtlSlvlOpt" class="form-control-sm" onchange="nChgMode('data');">
									<option value="BHD">Budget Heads wise</option>
									<option value="BHL">Budget Holders wise</option>									
								</select>
							</div>
							<div class="col-md-4">							
							</div>
							<div class="col-md-4">							
							</div>							
						</div>
					</div>
				</div>
			</div>
			<div class="card-footer" align="left">
				<div class="row form-group">
					<div class="col-md-4">	
						<input type="button" class="btn btn-success btn-sm nGlow" value="Export to Excel" id="btn_e" style="background-color: purple;" data-toggle="tooltip" title="Export Data to Excel" onclick="exportData('#nrTableDataDivDtl');">
					</div>
					<div class="col-md-8">
						<span value="Export" id="btn_e" style="float:right;">Legends :
							<span id="lg1" class="nExlntRow lgShape">Excellent</span>
							<span id="lg2" class="ngoodRow lgShape">Good</span>
							<span id="lg3" class="npoorRow lgShape">Poor</span>
						</span>
					</div>
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
				$("#nrTable tr").filter(function() {
					$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
			});

		    $("#bhld_src").on("keyup", function() {
		    	var value = $(this).val().toLowerCase();
		      	$("#bhld_body tr").filter(function() {
		      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		        });
		    });    

		    $("#bhd_src").on("keyup", function() {
		    	var value = $(this).val().toLowerCase();
		      	$("#bhd_body tr").filter(function() {
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
	var nHed="";
	var nHedt="";
	var slvl=$("#DtlSlvlOpt").val();
	nHed="ln_"+slvl;
	var cfy="2020";
	nHed=nHed +"_"+$("#DtlFilterOpt").val();	
	$.post("nFundInfoDBDetl?"+key+"="+value, {nPara:nHed,rolesus:rolesus,cfy:cfy}, function(j) {
 	}).done(function(j) { 		
 		var itxts="";
 		var itxtth="";
 		var itxtthtot="";
		var itxt="";
		var alttot=0;
		var exptot=0;
		var gsttot=0;
		var edttot=0;
		var othtot=0;
		var fwdtot=0;
		var bkdtot=0;
		
		var altgtot=0;
		var expgtot=0;
		var gstgtot=0;
		var edtgtot=0;
		var othgtot=0;
		var expsgtot=0;
		var fwdgtot=0;
		var bkdgtot=0;
		var tmpv="";

		if (slvl=='BHL') {
			itxtth += '<tr><th width="3%">Budget Holder</th><th width="26%">Head</th><th width="7%">Allocation (A)</th>';
			itxtth += '<th width="7%" class="ALC">Expdr</th><th width="7%" class="ALC">GST</th><th width="5%" class="ALC">Excise CESS</th>';
			itxtth += '<th width="5%" class="ALC">Others Taxes</th><th width="7%" class="ALC EXP EBK">Total Expdr (B)</th><th width="7%" class="EXP FBK">Fwd to CDA (C)</th>';
			itxtth += '<th width="7%" class="ABC EBK FBK">Bkd by CDA (D)</th>';
			itxtth += '<th width="4%" class="ALC" title="Allotment vrs Expenditure">B/A %</th>';
			itxtth += '<th width="1%" class="EXP ABD" title="Expenditure vrs Amount Fwd to CDA">C/B %</th>';
			itxtth += '<th width="1%" class="EBK" title="Expenditure vrs Booked by CDA">D/B %</th>';
			itxtth += '<th width="1%" class="FBK" title="Amount Fwd vrs Booked by CDA">D/C %</th>';
			itxtth += '<th width="1%"></th></tr>';			
			$("#nrTableDataDivDtlTitle").html("<h5>Budget Holders wise Summary</h5>");
		}
		if (slvl=='BHD') {
			itxtth += '<tr><th width="3%">Head</th><th width="28%">Budget Holder</th><th width="7%">Allocation (A)</th>';
			itxtth += '<th width="7%" class="ALC">Expdr</th><th width="7%" class="ALC">GST</th><th width="5%" class="ALC">Excise CESS</th>';
			itxtth += '<th width="5%" class="ALC">Others Taxes</th><th width="7%" class="ALC EXP EBK">Total Expdr (B)</th><th width="7%" class="EXP FBK">Fwd to CDA (C)</th>';
			itxtth += '<th width="7%" class="ABC EBK FBK">Bkd by CDA (D)</th>';
			itxtth += '<th width="4%" class="ALC" title="Allotment vrs Expenditure">B/A %</th>';
			itxtth += '<th width="1%" class="EXP ABD" title="Expenditure vrs Amount Fwd to CDA">C/B %</th>';
			itxtth += '<th width="1%" class="EBK" title="Expenditure vrs Booked by CDA">D/B %</th>';
			itxtth += '<th width="1%" class="FBK" title="Amount Fwd vrs Booked by CDA">D/C %</th>';
			itxtth += '<th width="1%"></th></tr>';			
			$("#nrTableDataDivDtlTitle").html("<h5>Budget Heads wise Summary</h5>");
		}				
			itxtthtot += '<tr><th width="7%"></th><th width="18%">Total</th><th width="7%">0.00000</th>';
			itxtthtot += '<th width="7%" class="ALC">0.00000</th><th width="7%" class="ALC">0.00000</th><th width="5%" class="ALC">0.00000</th>';
			itxtthtot += '<th width="5%" class="ALC">0.00000</th><th width="7%" class="ALC EXP EBK">0.00000</th><th width="7%" class="EXP FBK">0.00000</th>';
			itxtthtot += '<th width="7%" class="ABC EBK FBK">0.00000</th><th width="4%" class="ALC"></th><th width="1%" class="EXP ABD"></th><th width="1%" class="EBK"></th><th width="1%" class="FBK" ></th><th width="1%"></th></tr>';			
			$("#th_nrTableShowtot").html(itxtthtot);
		if (j[0][1]=='BLANK') {
			itxt += '<tr style="color:blue!important;font-size:1vw;">'
			itxt += '<td colspan="11" style="text-align:center;font-size:.95vw;">No Data Found</td>';
			$("#nrTableShowDtl").html(itxt);
		} else {
			
 		for (var i=0;i<j.length;i++){
 			var expstot=0
 			var lnalt=0;
 			var lnexp=0;
 			var lnfwd=0;
 			var lnbkd=0;
 			var btnid=""; 			
 				if (j[i][0] !=null) {
					tmpv=j[i][0].split(":");
 				} 
				if (slvl=='BHL') {
					itxt += '<tr style="color:blue!important;font-size:1.1vw;'
					if (j[i][13]=='1') {
						itxt += 'text-decoration: bold!important;padding:15px 5px!important"';
						btnid =j[i][7];
					} else {
						itxt += 'text-decoration: none!important;"';
						btnid =j[i][7]+'_'+j[i][0];
					}
					itxt += 'id="L'+j[i][13]+'_'+j[i][7]+'" name="TR_'+j[i][7];
					itxt += '" class=":'+j[i][8]+':L'+j[i][13]+'_'+j[i][7];
					itxt += ':'+j[i][13]+'AP'+calper(parseFloat(j[i][2]),(parseFloat(j[i][3])+parseFloat(j[i][10])+parseFloat(j[i][11])+parseFloat(j[i][12])));
					itxt += ':'+j[i][13]+'XP'+calper((parseFloat(j[i][3])+parseFloat(j[i][10])+parseFloat(j[i][11])+parseFloat(j[i][12])),parseFloat(j[i][4]));
					itxt += ':'+j[i][13]+'BP'+calper((parseFloat(j[i][3])+parseFloat(j[i][10])+parseFloat(j[i][11])+parseFloat(j[i][12])),parseFloat(j[i][5]));
					itxt += ':'+j[i][13]+'FP'+calper(parseFloat(j[i][4]),parseFloat(j[i][5]))+'"';
					if (j[i][13]=='1') {
						itxt += '>';
					}
					if (j[i][13]=='2') {
						itxt += ' >';
					}
					if (j[i][13]=='1') {
						itxt += '<td id="L'+j[i][13]+'_'+btnid+'_1" colspan="2" style="text-align:left;font-size:.95vw;">';
						itxt += '<span onclick="nDetdata(\''+btnid+'\')" title="Click to Expand" style="cursor:pointer;font-size:1.5vw;">&#10010;&nbsp;</span>';
						itxt += j[i][9]+'</td>';
					} else if (j[i][13]=='2') {
						itxt += '<td id="L'+j[i][13]+'_'+btnid+'_2" style="border:0px solid white!important;background-color:white!important;"></td>';
						if (j[i][0] !=null) {
							itxt += '<td id="L'+j[i][13]+'_'+btnid+'_3 "style="font-size:.90vw;">'+tmpv[3]+' - '+j[i][1]+'</td>';
						}
					}
				}
				if (slvl=='BHD') {
					itxt += '<tr style="color:#FFFFFF!important;font-size:1vw;'
					if (j[i][13]=='1') {
						itxt += 'text-decoration: bold!important;"';
						btnid =j[i][0];
					} else {
						itxt += 'text-decoration: none!important;"';
						btnid =j[i][7]+'_'+j[i][0];
					}
					itxt += 'id="L'+j[i][13]+'_'+j[i][0]+'" name="TR_'+j[i][7];
					itxt += '" class=":'+j[i][8]+':L'+j[i][13]+'_'+j[i][0];
					itxt += ':'+j[i][13]+'AP'+calper(parseFloat(j[i][2]),(parseFloat(j[i][3])+parseFloat(j[i][10])+parseFloat(j[i][11])+parseFloat(j[i][12])));
					itxt += ':'+j[i][13]+'XP'+calper((parseFloat(j[i][3])+parseFloat(j[i][10])+parseFloat(j[i][11])+parseFloat(j[i][12])),parseFloat(j[i][4]));
					itxt += ':'+j[i][13]+'BP'+calper((parseFloat(j[i][3])+parseFloat(j[i][10])+parseFloat(j[i][11])+parseFloat(j[i][12])),parseFloat(j[i][5]));
					itxt += ':'+j[i][13]+'FP'+calper(parseFloat(j[i][4]),parseFloat(j[i][5]))+'"';
					if (j[i][13]=='1') {
						itxt += '>';
					}
					if (j[i][13]=='2') {
						itxt += '>';
					}
					if (j[i][13]=='1') {
						itxt += '<td id="L'+j[i][13]+'_'+btnid+'_1" colspan="2" style="font-size:.95vw;">';
						itxt += '<span onclick="nDetdata(\''+btnid+'\')" title="Click to Expand" style="cursor:pointer;font-size:1.5vw;">&#10010;&nbsp;</span>';
						itxt += tmpv[3]+' - '+j[i][1]+'</td>';
					} else if (j[i][13]=='2') {
						itxt += '<td id="L'+j[i][13]+'_'+btnid+'_2" style="border:0px solid white!important;background-color:white!important;"></td>';
						itxt += '<td id="L'+j[i][13]+'_'+btnid+'_3" style="text-align:left;font-size:.90vw;">'+j[i][9]+'</td>';
					}
				}
				if (j[i][2]>0) {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_4" style="text-align:right;padding-right:10px;">'+Number(j[i][2]).toINR('','CR','INR')+'</td>';
					alttot = alttot + parseFloat(j[i][2]);
					if (j[i][13]=='1') {
						altgtot = altgtot + parseFloat(j[i][2]);
					}
					lnalt=parseFloat(j[i][2]);
				} else {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_4" style="text-align:right;padding-right:10px;">'+Number("0").toINR('','CR','INR')+'</td>';
				}				
				if (j[i][3]>0) {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_5" style="text-align:right;padding-right:10px;" class="ALC">'+Number(j[i][3]).toINR('','CR','INR')+'</td>';
					exptot = exptot + parseFloat(j[i][3]);
					if (j[i][13]=='1') {
						expgtot = expgtot + parseFloat(j[i][3]);
					}
					expstot = expstot + parseFloat(j[i][3]);
				} else {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_5" style="text-align:right;padding-right:10px;" class="ALC">'+Number("0").toINR('','CR','INR')+'</td>';
				}
				if (j[i][10]>0) {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_6" style="text-align:right;padding-right:10px;" class="ALC">'+Number(j[i][10]).toINR('','CR','INR')+'</td>';
					gsttot = gsttot + parseFloat(j[i][10]);
					if (j[i][13]=='1') {
						gstgtot = gstgtot + parseFloat(j[i][10]);
					}
					expstot = expstot + parseFloat(j[i][10]);
				} else {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_6" style="text-align:right;padding-right:10px;" class="ALC">'+Number("0").toINR('','CR','INR')+'</td>';
				}
				if (j[i][11]>0) {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_7" style="text-align:right;padding-right:10px;" class="ALC">'+Number(j[i][11]).toINR('','CR','INR')+'</td>';
					edttot = edttot + parseFloat(j[i][11]);
					if (j[i][13]=='1') {
						edtgtot = edtgtot + parseFloat(j[i][11]);
					}
					expstot = expstot + parseFloat(j[i][11]);
				} else {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_7" style="text-align:right;padding-right:10px;" class="ALC">'+Number("0").toINR('','CR','INR')+'</td>';
				}
				if (j[i][12]>0) {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_8" style="text-align:right;padding-right:10px;" class="ALC">'+Number(j[i][12]).toINR('','CR','INR')+'</td>';
					othtot = othtot + parseFloat(j[i][12]);
					if (j[i][13]=='1') {
						othgtot = othgtot + parseFloat(j[i][12]);
					}
					expstot = expstot + parseFloat(j[i][12]);
				} else {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_8" style="text-align:right;padding-right:10px;" class="ALC">'+Number("0").toINR('','CR','INR')+'</td>';
				}

				if (expstot>0) {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_9" style="text-align:right;padding-right:10px;" class="ALC EXP EBK">'+Number(expstot).toINR('','CR','INR')+'</td>';
					if (j[i][13]=='1') {
						expsgtot = expsgtot + expstot;
					}
					lnexp=parseFloat(expstot);
				} else {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_9" style="text-align:right;padding-right:10px;" class="ALC EXP EBK">'+Number("0").toINR('','CR','INR')+'</td>';
				}
				if (j[i][4]>0 ) {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_10" style="text-align:right;padding-right:10px;" class="EXP FBK">'+Number(j[i][4]).toINR('','CR','INR')+'</td>';
					lnfwd = parseFloat(j[i][4]);
					fwdtot = fwdtot + parseFloat(j[i][4]);
					if (j[i][13]=='1') {
						fwdgtot = fwdgtot + parseFloat(j[i][4]);
					}
				} else {
					lnfwd = 0;
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_10" style="text-align:right;padding-right:10px;"  class="EXP FBK">'+Number("0").toINR('','CR','INR')+'</td>';
				}					
				if (j[i][5]>0) {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_11" style="text-align:right;padding-right:10px;" class="ABC EBK FBK">'+Number(j[i][5]).toINR('','CR','INR')+'</td>';
					lnbkd=parseFloat(j[i][5]);
					bkdtot = bkdtot + parseFloat(j[i][5]);
					if (j[i][13]=='1') {
						bkdgtot = bkdgtot + parseFloat(j[i][5]);
					}
				} else {
					lnbkd=0;
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_11" style="text-align:right;padding-right:10px;" class="ABC EBK FBK">'+Number("0").toINR('','CR','INR')+'</td>';
				}				
				
				itxt += '<td id="L'+j[i][13]+'_'+btnid+'_12" style="text-align:right;padding-right:10px;" class="ALC">'+calper(lnalt,lnexp)+'</td>';
				itxt += '<td id="L'+j[i][13]+'_'+btnid+'_14" style="text-align:right;padding-right:10px;" class="EXP ABD">'+calper(lnexp,lnfwd)+'</td>';
				itxt += '<td id="L'+j[i][13]+'_'+btnid+'_15" style="text-align:right;padding-right:10px;" class="EBK">'+calper(lnexp,lnbkd)+'</td>';
				itxt += '<td id="L'+j[i][13]+'_'+btnid+'_16" style="text-align:right;padding-right:10px;" class="FBK">'+calper(lnfwd,lnbkd)+'</td>';
				if (parseInt(j[i][8])<=2) {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_13" style="text-align:right;padding-right:10px;cursor:pointer;font-size:1.5vw;" ondblclick="nPrintLetter(\''+btnid+'\',\''+j[i][13]+'\')" title="Double Click to Print Letter">&#9993;</td>';
				} else {
					itxt += '<td id="L'+j[i][13]+'_'+btnid+'_13"></td>';
				}
		}
	 		itxtthtot="";
			itxtthtot += '<tr style="color:blue!important;font-size:1vw;text-align:right;"><th width="7%"></th><th width="18%">Over All Total</th><th width="7%">'+Number(altgtot).toINR("","CR","INR","CR")+'</th>';
			itxtthtot += '<th width="7%" class="ALC">'+Number(expgtot).toINR("","CR","INR","CR")+'</th><th width="7%" class="ALC">'+Number(gstgtot).toINR("","CR","INR","CR")+'</th><th width="5%" class="ALC">'+Number(edtgtot).toINR("","CR","INR","CR")+'</th>';
			itxtthtot += '<th width="5%" class="ALC">'+Number(othgtot).toINR("","CR","INR","CR")+'</th><th width="7%" class="ALC EXP EBK">'+Number(expsgtot).toINR("","CR","INR","CR")+'</th><th width="7%" class="EXP FBK">'+Number(fwdgtot).toINR("","CR","INR","CR")+'</th>';
			itxtthtot += '<th width="7%" class="ABC EBK FBK">'+Number(bkdgtot).toINR("","CR","INR","CR")+'</th><th width="4%" class="ALC">'+calper(altgtot,expsgtot)+'</th>';
			itxtthtot += '<th width="1%" class="EXP ABD">'+calper(expsgtot,fwdgtot)+'</th><th width="1%" class="EBK">'+calper(expsgtot,bkdgtot)+'</th>';
			itxtthtot += '<th width="1%" class="FBK" ></th>'+calper(fwdgtot,bkdgtot)+'<th width="3%"></th></tr>';
			$("#th_nrTableShowDtl").html(itxtth); 		
	 		$("#nrTableShowDtl").html(itxt);
			$("#th_nrTableShowtot").html(itxtthtot);			
	 		$("[class *=':L2']").hide();
	 		setdtlFilter();
		}
 	}).fail(function(xhr, textStatus, errorThrown) { });
	$("#divShow").hide();
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
</script>
</body>