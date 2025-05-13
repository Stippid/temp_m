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
	function chgeffect() {
		$("#nrTableShowDtl").html("<tr><td colspan='8' style='font-size:1.5em;'><center>Press 'Get Data' Button ...");
		$("#refdata").addClass("blink");
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

</script>
<style type="text/css">
.blink {
  animation: blinker 1.5s step-start infinite;
  color:yellow;
  text-shadow:5px red;
}

 @keyframes blinker {
  50% {
    opacity: 0;
  }
}

.tab1 { text-indent:2em;}
.tab2 { text-indent:4em;}
.tab3 { text-indent:8em;}
.tab4 { text-indent:10em;} 
</style>
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
	div {
		border:0px solid red!important;
	}
</style>
<body class="mmsbg" onload="setMenu();">
	<form:form name="fp_info_board" id="fp_info_board" method='POST'>
		<input type="hidden" name="tr_head" id="tr_head" />
		<div class="" align="center" style="width:99vw; margin:0px;padding:0px;border:0px;overflow: auto;">
			<div class="card">
				<div class="card-header mms-card-header ncard-body-bg"
					style="min-height: 50px; padding-top: 10px;"><h5><span>FUND PERFORMANCE INDICATOR FOR CDRs</span></h5>
					
				</div>
				<div class="card-body card-block" style="height:55vh;background-color: azure;margin:0px;padding:0px;border:5px;width:100%;overflow: auto;">
	            	<div class="row form-group" id="nBase_data"  style="display:none;margin-top: -20;background-color: azure;">	
						<div id="divShowDtl" class="" style="width:100%;display1:none;z-index:3;background-color: azure;">
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
								<c:set var="nSelp" value="${fn:split(nSel,'_')}" />								
								<div id="nMsgBoard" style="width:100%;">									
									<div class="row" style="background-color: azure;width: calc(79vw - 8px);display:inherit;margin-left: 5px;">
										<div class="" style="align-self:center;padding:10px;height: 40px;width: calc(98vw - 8px)!important;display:inline-flex;">
										   <c:set var="amt_rss" value="${fn:split(n_sel,':')}"/>
			    	                       <%-- <div class="col-md-3" style="text-align: left;float: left;">	    	                       
				    	                       	<label class=" form-control-label">Financial Year&nbsp;:&nbsp;</label>&nbsp;&nbsp;
												<select id="fp_finYr" name="fp_finYr"  class="form-control-sm" title="Select Financial Year".>
													<c:forEach var="item" items="${n_finyr}" varStatus="num">												
														<option value="${item[0]}"
														<c:if test="${item[0] == n_cfinyr}">
															SELECTED
														</c:if>>${item[1]}</option>
											        </c:forEach>
								                </select>
			    	                       </div> --%>
											<div class="col-md-3" style="font-size:1.1vw;float:right;width:25%!important" id="nrTableDataDivDtlTitleee"><b>&nbsp;&nbsp;Show&nbsp;&nbsp;</b>								
												<select id="DtlSlvlOpt" class="form-control-sm" aonchange="nChgMode('data');" style="font-size:1.1vw;" title="Select Budget Holders/Heads">
													<option value="BHL">Budget Holders wise Summary</option>
													<!-- <option value="BHD">Budget Heads wise Summary</option> -->																				
												</select>
											</div>
											<!-- <div class="col-md-4">
										     	<label class=" form-control-label">&nbsp;&nbsp; Amount in </label>&nbsp;&nbsp;
												<select id="amt_rs" name="amt_rs" class="form-control-sm" title="Select Amount Conversion" onchange="javascript:alert('Press Below Buttons to Refresh Data');">
														<option value="CR">Crores</option>												
														<option value="RS">Rupees</option>
												</select>
											</div> -->
											
											<div class="col-md-8">												
													<c:set var="issel" value=""></c:set>	
													<b>of HQ&nbsp;:&nbsp;</b>
													<select id="DtlBH" name="DtlBH" style="width:60%;" title="Select Budget Holder to Get Data" onchange="chgeffect();">
														<option value="ALL_ALL_ALL">
															All Fmn HQs															
														</option>																											
														<c:forEach var="item" items="${n_hq}" varStatus="num">
																<c:if test="${item[1]==nSelp[0]}">
							   										<c:set var="issel" value=" selected"></c:set>
							   									</c:if>
							   									<c:if test="${item[1]!=nSelp[0]}">
							   										<c:set var="issel" value=""></c:set>
							   									</c:if>																
								   								<c:if test="${item[3] =='1'}">
								   										<option value="${item[3]}_${item[0]}_${item[1]}" ${issel}>&emsp;${item[2]}</option>
								   								</c:if>
								   								<c:if test="${item[3] =='2'}">
								   										<option value="${item[3]}_${item[0]}_${item[1]}" ${issel}>&emsp;&emsp;${item[2]}</option>
								   								</c:if> 					   								 
								   								<c:if test="${item[3] =='3'}">
								   										<option value="${item[3]}_${item[0]}_${item[1]}" ${issel}>&emsp;&emsp;&emsp;${item[2]}</option>
								   								</c:if> 					   								 
								   								<c:if test="${item[3] =='4'}">
									   									<option value="${item[3]}_${item[0]}_${item[1]}" ${issel}>&emsp;&emsp;&emsp;&emsp;${item[2]}</option>
								   								</c:if>								   												   								
											        	</c:forEach>
										        	</select>
										 	</div>
											
										 	<div class="col-md-1">
										 		<input id="refdata" type="submit" class="btn btn-success btn-sm nGlow" value="Get Data" style="bbackground-color: purple;" data-toggle="tooltip" title="Get Data" oonclick="showdetl();">
										 	</div>
										</div>
									</div>
									<div class="row">
		    	                 		<div class="col-md-12">
										
								</div>								
		    	             </div>
	 								<div class="nPopTable" id="nrTableDataDivDtl" style="height:45vh;width:100%!important;margin-top:10px;overflow: auto;"> 
										<table style="width:100%;border-collapse: separate;table-layout: fixed;">
							               	<thead style="text-align: center;line-height:25px;font-size: 1vw;border-spacing: 0 0PX!important;" id="th_nrTableShowDtl">  
							    				<tr>							    					
								   					<th width="150px" title="Fmn HQ of Budget Holder as per Orbat">Fmn HQ</th>
								      				<th width="150px" title="Who has Allotted Fund (High Level Budget Holder[HLBH])">Allotter (HLBH)</th>
								      				<th width="150px" title="To Whon Fund Allotted (Budget Holder)">Allot tee (BH)</th>
								      				<th width="100px" class="EBK">Minor Head</th>
								      				<th width="250px" class="EBK">Code Head</th>
								      				<th width="110px" title="Fund Allocated">Allocation</th>
													<th width="110px" class="" title="Expenditure against Fund Allocated">Expenditure</th>
													<th width="110px" class="" title="Fwd to CDA against Expenditure">Fwd to CDA</th>
													<th width="110px" class="" title="Amt Confirmed by CDA against Forwarded Amount">Booked by CDA</th>
													<th width="60px" class="" title="%age of Allocation Vs Expenditure">%Age</th>													
									  			</tr>
							    		  	</thead>
										    <tbody id="nrTableShowDtl" style="font-size: 1vw;text-decoration: none;color:blue;">
											   <c:if test="${empty list[0][1]}">
													<tr>
														<td style="font-size: 15px; text-align: center; color: red;"
															colspan="8">No Transaction Available</td>
													</tr>
											   </c:if>
											   <c:if test="${not empty list[0][1]}">
						                           <c:forEach var="item" items="${list}" varStatus="num" >
						                           	<c:if test="${item[0]=='T1'}">
						                           		<c:set var="aaab" value="RLVL_T1"></c:set>
						                           		<c:set var="aaac" value="SUMT1"></c:set>
						                           		<c:set var="aper" value=""></c:set>					                           		
						                           	</c:if>
						                           	<c:if test="${item[0]=='T2'}">
						                           		<c:set var="aaab" value="RLVL_T2"></c:set>
						                           		<c:set var="aaac" value="SUMT2"></c:set>
						                           		<c:set var="aper" value=":1AP${item[22]}"></c:set>					                           		
						                           	</c:if>
						                           	<c:if test="${item[0]=='T3'}">
						                           		<c:set var="aaab" value="RLVL_T3"></c:set>
						                           		<c:set var="aaac" value="SUMT3"></c:set>
						                           		<c:set var="aper" value=":2AP${item[22]}"></c:set>		
						                           		<script></script>			                           		
						                           	</c:if>
						                           	<c:set var="totrow" value=""></c:set>
						                           	<c:if test="${empty item[8]}">													 	
														 	<c:set var="totrow" value="totalval"></c:set>													 
													</c:if>
													<c:if test="${item[0]!='T3'}">
														 	<c:set var="totrow" value="totalval"></c:set>
													</c:if>				  																								                         	
													<c:set var="rowid" value="${aaab}:SUS_${item[7]}:HQ_${item[5]}"></c:set>					
													<c:if test="${rowid!='RLVL_T1:SUS_:HQ_'}">								                         	
											          <tr class="${aaac}${aper}" style="font-size:1.1vw;background-color:transparent;" id="${rowid}">
														 <td>${item[4]}</td>
														 <td>${item[6]}</td>
														 <c:if test="${empty item[8]}">
														 	<td class='nTextRight ${totrow}' data-name="${aaac}${aper}">Total</td>													 
														 </c:if>
														 <c:if test="${not empty item[8]}">
											   			 	<td>${item[8]}</td>													 
														 </c:if>						
														 <c:if test="${item[0]=='T3'}">
														 	<td class="EBK">${item[16]}</td>
														 	<td class="EBK">${item[14]} - ${item[15]}</td>
														 </c:if>
														 <c:if test="${item[0]!='T3'}">
														 	<td colspan='2' class="EBK nTextRight ${totrow}" name="${aaac}${aper}">Sub Total</td>
														 </c:if>			 													 
														 <td class='nTextRight' style="font-size: 1.2vw;"><script>document.write(Number('${item[10]}').toINR('','RS','INR','RS'))</script></td>
														 <td class='nTextRight' style="font-size: 1.2vw;"><script>document.write(Number('${item[12]}').toINR('','RS','INR','RS'))</script></td>
														 <td class='nTextRight' style="font-size: 1.2vw;"><script>document.write(Number('${item[18]}').toINR('','RS','INR','RS'))</script></td>
														 <td class='nTextRight' style="font-size: 1.2vw;"><script>document.write(Number('${item[20]}').toINR('','RS','INR','RS'))</script></td>
														 <td class='nTextRight' style="font-size: 1.2vw;"><script>document.write(Number('${item[22]}').toINR('','RS','INR','RS'))</script></td>
											          </tr>
											         </c:if>
											       </c:forEach>
											     </c:if>									
										    </tbody>
										</table>
									</div>																											
							</div>
							</div>
						</div>
			  		</div>
			</div>
			<div class="card-footer" align="left" style="padding:0px!important;">
				<div class="row" style="font-size:0.8em">
					<div class="col-md-2">	
						<input type="text" id="bhld_slvl" name="nBase_slvl_src" placeholder="Search..." class="form-control-sm"  style="text-transform: uppercase;"
                    autofocus autocomplete="off" size="10" maxlength="20" title="Type Word or Part of Word to Search in Budget Holders" data-toggle="tooltip">
						<input type="button" class="btn btn-success btn-sm nGlow" value="Export" id="btn_e" style="background-color: purple;" data-toggle="tooltip" title="Export Data to Excel" onclick="exportData('#nrTableDataDivDtl');">
					</div>
					<div class="col-md-4">
							<span><b>Fitler Budget Holder&nbsp;:&nbsp;</b></span>
							<select id="DtlFilterBH" onchangeaa="setdtlFilter();" class="form-control" style="font-size:0.8em;width:70%;" title="Select Budget Holder">
								<option value="ALL_ALL_ALL">
									All Budget Holders
								</option>
								<c:forEach var="item" items="${n_unt}" varStatus="num">
		   								<option value="${item[3]}_${item[0]}_${item[1]}">			   							
		   								<c:if test="${item[3] =='1'}">
		   									&ensp;
		   								</c:if>
		   								<c:if test="${item[3] =='2'}">
		   									&ensp;&ensp;
		   								</c:if> 					   								 
		   								<c:if test="${item[3] =='3'}">
		   									&ensp;&ensp;&ensp;
		   								</c:if> 					   								 
		   								<c:if test="${item[3] =='4'}">
		   									&ensp;&ensp;&ensp;&ensp;
		   								</c:if>
		   								<c:if test="${item[3] =='5'}">
		   									&ensp;&ensp;&ensp;&ensp;&ensp;
		   								</c:if>				   								 
		   								${item[2]}					   								
		   							</option>		   						
					        	</c:forEach>
				        	</select>
				 	</div>
				 	<div class="col-md-6">
				 		<div class="col-md-12">
					 		 <span><b></b></span>
		               		 <input type="radio" id="fp_rptorder_1" name="fp_rptorder" value="1" checked>&nbsp;Budget Holder Wise Total
		               		 &emsp;<input type="radio" id="fp_rptorder_2" name="fp_rptorder" value="2">&nbsp;Code Head Wise Total
		               		 <div style="float:right;displayq:none;">
						 	<span><b>Range&nbsp;:&nbsp;</b></span>
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
						</div>
						<div class="col-md-12" style="margin-top:15px;displaya:none;">						
							<span><b>Legends&nbsp;:&nbsp;</b></span>
							<span value="" id="btn_e" style="float:right;">
								<span id="lg1" class="nExlntRow lgShape">Excellent</span>
								<span id="lg2" class="ngoodRow lgShape">Good</span>
								<span id="lg3" class="npoorRow lgShape">Poor</span>
							</span>
						</div>
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
<c:url value="fp_info_board_n?${_csrf.parameterName}=${_csrf.token}" var="searchUrl" />
<form:form action="${searchUrl}" method="POST" id="refeshForm"
	name="refeshForm" modelAttribute="service1">
	<input type="hidden" name="datatype" id="datatype" />
	<input type="hidden" name="hqsus" id="hqsus" />
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/common/select2/select2.min.js" type="text/javascript"></script>

<script type="text/javascript">
		$(document).ready(function() {
			$("#DtlBH").select2();
			$("#DtlFilterBH").select2();
			//$("[id*='fp_rptorder']").trigger("click");
			nChgMode('data');
			$("#nrInput").on("keyup",function() {
				var value = $(this).val().toLowerCase();
				$("#nrTable tr").filter(function() {
					$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
			});

		    $("#bhld_slvl").on("keyup", function() {
		    	var value = $(this).val().toLowerCase();
		      	$("#nrTableShowDtl tr").filter(function() {
		      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		        });
		    });    

		    /* $("#bhd_src").on("keyup", function() {
		    	var value = $(this).val().toLowerCase();
		      	$("#bhd_body tr").filter(function() {
		      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		        });
		    }); */
		    
		    $("[id*='fp_rptorder']").on("change, click", function() {
		    	var value = $(this).val().toLowerCase();
		    	chgData();
		    	//alert(value);
		    	//clearof('LVL');
		    	//showof('LVL',value);
		    	/* if (value=='1'){
		    		$("[id*='RLVL_T1_T2']").show();
		    	}
		    	if (value=='2'){
		    		$("[id*='RLVL_T1_T3']").show();
		    	} */		    	
		    });
		    $("[id*='DtlFilterBH']").on("change", function() {
		    	chgData()
		    	//var value = $(this).val();
		    	//clearof('SUS');
		    	//showof('LVL','1');
		    	//showof('SUS',value);
		    	/* var valu = value.split("_");
		    	$("[id*='fp_rptorder']").trigger("change");
		    	$("[id*=':SUS_']").hide();
		    	if (value=='1'){
		    		$("[id*=':SUS_"+valu[2]+"']").show();
		    	}
		    	if (value=='2'){
		    		$("[id*=':SUS_"+valu[2]+"']").show();
		    	} */		    	
		    	 
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
function refeshdata() {
	var a=$("DtlSlvlOpt").val();
	var b=$("DtlBH").val();
	$("#datatype").val(a);
	$("#hqsus").val(b);
	$("#refeshForm").submit();
}
function chgDataaab1() {
	var nrSel=$("[name*='fp_rptorder']:radio:checked").map(function() {
		var bb=$(this).val();
		return bb;
	}).get();
	var ord=nrSel[0];
	var bh=$("#DtlFilterBH").val();
	$("[class*='SUMT']").css("background-color","transparent");
	$("[id*='RLVL_']").hide();
	$("[id*=':SUS_']").hide();	
	//alert(ord+","+bh);	 
	var valu = bh.split("_");
	//var nk1="[id*=':SUS_"+valu[2]+"'], [id*='RLVL_T1'], [id*='RLVL_T2']";
	//var nk2="[id*=':SUS_"+valu[2]+"'] [id*='RLVL_T1'] [id*='RLVL_T2']";
	var nk1="";
	var nk2="";
	if (ord=='1'){
		nk1 += "[id*='RLVL_T1'], [id*='RLVL_T2']";
		if (valu[2]!='ALL') {
			nk2 +="[id*='_"+valu[2]+"']";
	   		//$("[id*='_"+valu[2]+"']").css("background-color","green");
		}
		//$("[id*='RLVL_T3']").hide();
		if (nk2.length()>0) {
			$(nk1).filter(nk2).css("background-color","green");
			$(nk1).filter(nk2).show();
		} else {
			$(nk1).css("background-color","green");
			$(nk1).show();
		}
		$(".EBK").hide();
   		//$("[class*='SUMT']").css("background-color","transparent");
   		$(".SUMT1").css("background-color","mistyrose");
   	}
   	if (ord=='2'){   		
   		//nk1 += "[id*='RLVL_T2'] [id*='RLVL_T3']";
   		if (valu[2]!='ALL') {
   			$("[id*='_"+valu[2]+"']").css("background-color","green");
		}
   		$("[id*='RLVL_T1']").hide();
   		$(".EBK").show();
   		//   		
   		$(".SUMT2").css("background-color","mistyrose");
   	}  
   	//alert(nk1);
   	//$(nk1).show();
}

function chgData() {	
	var nrSel=$("[name*='fp_rptorder']:radio:checked").map(function() {
		var bb=$(this).val();
		return bb;
	}).get();
	var ord=nrSel[0];
	var bh=$("#DtlFilterBH").val();
	$("[class*='SUMT']").css("background-color","transparent");
	$("[id*='RLVL_']").hide();
	$("[id*=':SUS_']").hide();	
	var valu = bh.split("_");
	var nk1="";
	var nk2="";
	
	var nnn=$(".totalval").parent("tr").attr("id");
	//alert(nnn);	
	
	//alert("chgdata-"+ord);
	//alert("All-"+(valu[2]!='ALL')+"-"+bh);
	if (ord=='1'){
		nk1 +="[id*='RLVL_T1";
		nk2 +="[id*='RLVL_T2";
		 if (valu[2]!='ALL') {
			nk1 +=":SUS_"+valu[2];
			nk2 +=":SUS_"+valu[2];
		}
		nk1 +="']";
		nk2 +="']";
		//alert(nk1);
		$(nk1).show();
		$(nk2).show();
		$("[id*='RLVL_T3']").hide();
		$(".EBK").hide();
   		//$(".SUMT1").css("background-color","lightgray");
   	}
   	if (ord=='2'){   		
   		nk1 +="[id*='RLVL_T2";
		nk2 +="[id*='RLVL_T3";
		 if (valu[2]!='ALL') {
			nk1 +=":SUS_"+valu[2];
			nk2 +=":SUS_"+valu[2];
		} 
		nk1 +="']";
		nk2 +="']";
		//alert(nk1);
		$(nk1).show();
		$(nk2).show();
		$("[id*='RLVL_T1']").hide();
		$(".EBK").show();
   		//$(".SUMT2").css("background-color","lightgray");
   	}   
   	perHQmarker(ord);
   	$(".totalval").parent("tr").css({'background-color':'white','color':'yellow!important'});
}

function chgDataaa() {
	var nrSel=$("[name*='fp_rptorder']:radio:checked").map(function() {
		var bb=$(this).val();
		return bb;
	}).get();
	var ord=nrSel[0];
	var bh=$("#DtlFilterBH").val();
	$("[id*='RLVL_']").show();
	$("[id*=':SUS_']").show();
	$("[class*='SUMT']").css("background-color","transparent");
	//alert(ord+","+bh);	 
	var valu = bh.split("_");
	//var nk1="[id*=':SUS_"+valu[2]+"'] [id*='RLVL_T1'] [id*='RLVL_T2']";
	//var nk2="[id*=':SUS_"+valu[2]+"'] [id*='RLVL_T1'] [id*='RLVL_T2']";	
	if (ord=='1'){
		//nk1 += "[id*='RLVL_T1'] [id*='RLVL_T2']";
		if (valu[2]!='ALL') {
			//alert("a");			
	   		$("[id*='_"+valu[2]+"']").css("background-color","green");
		}
		$("[id*='RLVL_T3']").hide();
		$(".EBK").hide();
   		//$("[class*='SUMT']").css("background-color","transparent");
   		$(".SUMT1").css("background-color","mistyrose");
   	}
   	if (ord=='2'){   		
   		//nk1 += "[id*='RLVL_T2'] [id*='RLVL_T3']";
   		if (valu[2]!='ALL') {
   			//alert("b");
   			$("[id*='_"+valu[2]+"']").css("background-color","green");
		}
   		$("[id*='RLVL_T1']").hide();
   		$(".EBK").show();
   		//   		
   		$(".SUMT2").css("background-color","mistyrose");
   	}  
   	//alert(nk1);
   	//$(nk1).show();
}

function nChgMode(a) {
	$("[id *='nBase_']").hide();
	if (a=='data') {
		chgData();
	}
	$('#nBase_'+a+"_h").show();
	$('#nBase_'+a).show();		
}

function showdetl() {
	$("#refdata").removeClass("blink");	
	$("#fp_rptorder_1").attr("checked");
	$("#nrWaitLoader").show();
	var nHed="";
	var nHedt="";
	var slvl=$("#DtlSlvlOpt").val();	
	nHed="ln_"+slvl;
	var cfy="2020";
	var rolesus=$("#DtlBH").val();
	var ord=$("[name*='fp_rptorder']").val();
	//alert(ord);
	nHed=nHed +"_"+$("#DtlFilterOpt").val();
	
	/* $.ajaxSetup({
    	async: false
	}); */
	$.post("nFundInfoDBDetl_n?"+key+"="+value, {nPara:nHed,rolesus:rolesus,cfy:cfy}, function(j) {
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
		slvl='BHL';
		if (slvl=='BHL') {
			itxtth += '<tr><th width="150px">Fmn HQ</th><th width="150px">HLBH</th><th width="150px">Budget Holder</th><th width="150px" class="EBK">Minor Head</th><th width="70px" class="EBK">Code Head</th><th width="130px">Allocation</th>';
			itxtth += '<th width="130px" class="">Expenditure</th><th width="130px" class="">Fwd to CDA</th>';
			itxtth += '<th width="130px" class="">Bkd by CDA</th>';
			itxtth += '<th width="65px" class="">%age</th>';
			itxtth += '</tr>';			
			$("#nrTableDataDivDtlTitle").html("<h5>Budget Holders wise Summary</h5>");
		}
		/* if (slvl=='BHD') {
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
		} */				
			itxtthtot += '<tr><th width="7%"></th><th width="18%">Total</th><th width="7%">0.00000</th>';
			itxtthtot += '<th width="7%" class="ALC">0.00000</th><th width="7%" class="ALC">0.00000</th><th width="5%" class="ALC">0.00000</th>';
			itxtthtot += '<th width="5%" class="ALC">0.00000</th><th width="7%" class="ALC EXP EBK">0.00000</th><th width="7%" class="EXP FBK">0.00000</th>';
			itxtthtot += '<th width="7%" class="ABC EBK FBK">0.00000</th><th width="4%" class="ALC"></th><th width="1%" class="EXP ABD"></th><th width="1%" class="EBK"></th><th width="1%" class="FBK" ></th><th width="1%"></th></tr>';			
			//$("#th_nrTableShowtot").html(itxtthtot);
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
 			//console.log(i,'-',j[i]);
 				if (j[i][0] !=null) {
					tmpv=j[i][0].split(":");
 				} 
 				var aaab="";
 				var aper="";
 				if (j[i][0]=='T1') {
 					aaab="RLVL_T1";
 					aaac="SUMT1";
 					aper="";
 				}
 				if (j[i][0]=='T2') {
 					aaab="RLVL_T2";
 					aaac="SUMT2";
 					aper = ':1AP'+calper(parseFloat(j[i][10]),(parseFloat(j[i][12])));
 				} 
 				if (j[i][0]=='T3') {
 					aaab="RLVL_T3";
 					aaac="SUMT3";
 					aper = ':2AP'+calper(parseFloat(j[i][10]),(parseFloat(j[i][12])));
 				}
 				var lncolor="transparent";
 					itxt += '<tr class="'+aaac+aper+'" style="font-size:1.1vw;background-color:'+lncolor+';" id="'+aaab+':SUS_'+j[i][7]+':HQ_'+j[i][5]+'">';
	 				/* itxt += '<td id="L'+j[i][1]+'_'+btnid+'_14" style="" class="">'+j[i][0]+'-'+j[i][4]+'</td>'; */
	 				itxt += '<td id="L'+j[i][1]+'_'+btnid+'_14" style="" class="">'+j[i][4]+'</td>';
	 				itxt += '<td id="L'+j[i][1]+'_'+btnid+'_14" style="" class="">'+j[i][6]+'</td>';
	 				if (j[i][8]==null || j[i][8]=='null' || j[i][8]=='') {
	 					itxt += '<td id="L'+j[i][1]+'_'+btnid+'_15" style="text-align:center;padding-right:10px;" class="">Total</td>';
	 				} else {
	 					itxt += '<td id="L'+j[i][1]+'_'+btnid+'_14" style="" class="">'+j[i][8]+'</td>';
	 				}
	 				if (j[i][0]=='T3') {						 										
	 					itxt += '<td id="L'+j[i][1]+'_'+btnid+'_15" style="" class="EBK">'+j[i][16]+'</td>';
						/* itxt += '<td id="L'+j[i][1]+'_'+btnid+'_15" style="" class="EBK">'+j[i][14]+' - '+j[i][15]+'</td>'; */
						itxt += '<td id="L'+j[i][1]+'_'+btnid+'_15" style="text-align:center;" class="EBK nrTextCenter">'+j[i][14]+'</td>';
	 				} else {
	 					itxt += '<td id="L'+j[i][1]+'_'+btnid+'_16" style="text-align:center;padding-right:10px;" class="EBK" colspan="2">Sub Total</td>';
	 					//itxt += '<td id="L'+j[i][1]+'_'+btnid+'_16" style="" class="EBK"></td>';
	 				}
					itxt += '<td id="L'+j[i][1]+'_'+btnid+'_16" style="text-align:right;padding-right:10px;" class="">'+Number(j[i][10]).toINR("","RS","INR","RS")+'</td>';
					itxt += '<td id="L'+j[i][1]+'_'+btnid+'_16" style="text-align:right;padding-right:10px;" class="">'+Number(j[i][12]).toINR("","RS","INR","RS")+'</td>';
					itxt += '<td id="L'+j[i][1]+'_'+btnid+'_16" style="text-align:right;padding-right:10px;" class="">'+Number(j[i][18]).toINR("","RS","INR","RS")+'</td>';
					itxt += '<td id="L'+j[i][1]+'_'+btnid+'_16" style="text-align:right;padding-right:10px;" class="">'+Number(j[i][20]).toINR("","RS","INR","RS")+'</td>';
					itxt += '<td id="L'+j[i][1]+'_'+btnid+'_16" style="text-align:right;padding-right:10px;" class="">'+calper(j[i][10],j[i][12])+' %</td>';
		}
 			$("#th_nrTableShowDtl").html(itxtth);
 			$("#nrTableShowDtl").html(itxt);
	 		/* itxtthtot="";
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
	 		setdtlFilter();*/
		}
 	}).fail(function(xhr, textStatus, errorThrown) { });
	$("#divShow").hide();
	$("#divShowDtl").show();	
	 if (ord=="1" || ord=="2") {
		chgData();
	}
	$("#nrWaitLoader").hide();
}



function setdtlFilter() {	
	/* var perty=$("#DtlFilterPerTy").val();
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
	} 	 */
	var ord=$("[name*='fp_rptorder']").val();
	//alert(ord);
 	perHQmarker(ord);
 	/* $("[class *=':L2']").hide(); */
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
	b=$("[name*='fp_rptorder']").val();
	var bv="";
	var bv1="";
	if (b=='1') {
		bv="A";
		bv1="A";
	}  else {
		bv="A";
		bv1="A";
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
	

	//if (a=='1') {
		$("[class *='AP']").removeClass(" npoorRow ngoodRow nExlntRow");
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
			/* if (iq<10) {
				if (iq<=0) {
					iqt =iq;
				} else {
					iqt ="0"+iq;
				}
			} else { */
				iqt =iq;
			//}			
			if (iq<gdval || iq<=0) {
				$("[class *='"+bv+"P"+iqt+"']").addClass(" npoorRow");
			}
			if (iq>=gdval && iq<exval) {			
				$("[class *='"+bv+"P"+iqt+"']").removeClass(" npoorRow");
				$("[class *='"+bv+"P"+iqt+"']").addClass(" ngoodRow");
			} 
			if (iq>=exval || iq>=perval) {
				/* console.log("Excellent-",perval,exval,iq); */
				$("[class *='"+bv+"P"+iqt+"']").removeClass(" npoorRow");
				$("[class *='"+bv+"P"+iqt+"']").removeClass(" ngoodRow");
				$("[class *='"+bv+"P"+iqt+"']").addClass(" nExlntRow");
			}
		}					
	//}
}

function permarker(a,p) {
	/* var b=$("#DtlFilterOpt").val();
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
	} */
	var b=$("#DtlFilterOpt").val();
	b=$("[name*='fp_rptorder']").val();
	var bv="";
	a=b;
	var bv1="";
	if (b=='1') {
		bv="1A";
		bv1="1A";
	}  else {
		bv="2A";
		bv1="1A";
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
		$("[class *=':1"+bv+"P'],[class *=':2"+bv+"P']").removeClass(" npoorRow  ngoodRow nExlntRow");
		//$("[class *=':2"+bv+"P']").removeClass(" ngoodRow");
		//$("[class *=':"+bv+"P']").removeClass(" nExlntRow");
		var iqt="";
		for (var iq=0;iq<=100;iq++) {
			if (iq<10) {
				iqt ="0"+iq;
			} else if (iq==0){
				iqt=iq;
			} else {
				iqt=iq;
			}			
			$("[class *='AP"+iqt+"']").addClass(" npoorRow");
			if (iq<nen-20 || iq<=0) {
				console.log(iq,"poor");
				$("[class *='AP"+iqt+"']").addClass(" npoorRow");
			}
			if (iq>=(nen-20) && iq<(nen-10)) {
				console.log(iq,"good");
				$("[class *='AP"+iqt+"']").removeClass(" npoorRow");
				$("[class *='AP"+iqt+"']").addClass(" ngoodRow");
			} 
			if (iq>=nen-10 || iq>=perval) {
				console.log(iq,"exln");
				$("[class *='AP"+iqt+"']").removeClass(" npoorRow");
				$("[class *='AP"+iqt+"']").removeClass(" ngoodRow");
				$("[class *='AP"+iqt+"']").addClass(" nExlntRow");
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