<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmn" %>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">    
<link rel="stylesheet" href="js/common/select2/select2.min.css">
<script>
	var username="${username}";
	var curDate = "${curDate}";  
	var role = "${role}";
	var rolesusno = "${roleSusNo}";
	var nX;
	var nY;
		
	function isAlphaNumeric(evt){
		var charCode = (evt.which) ? evt.which : evt.keyCode;
			if((charCode>=48 && charCode<=57) || (charCode>=65 && charCode<=90) || (charCode>=97 && charCode<=122) || charCode==32 || charCode==46 || charCode == 45  || charCode == 47){
				return true;
			} 
		return false;
	}
	function chgeffect() {
		$("#nrTable").html("<tr><td colspan='9'><center>Press 'Refresh Data' Button ...");
		$("#refdata").addClass("blink");
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
</style>
<style>
	#nrTable tbody {
		height: 300px;
		overflow: auto;
	}
	
	.expheadbar {
		background-color: brown!important;
		color:yellow!important;
	}	
	
	.cdaheadbar {
		background-color: green!important;
		color:yellow!important;
	}	
	
	.row {
		padding-bottom:0px!important;
	}
	
	.decimal{
		text-align : right;
	}
	
</style>
<body class="mmsbg" onload="setMenu();">
<% int ntotln=0; %>
	<div id="divShow" class="tabcontentLvl tabcontent tabcontent-height" style="position:fixed;border:15px groove;display:none;width:68%;min-height:400px;;height:cal(100%-100px);left:16%;z-index: 0;top:30;background-color: lightyellow;overflow: auto;">
		<div class="nrDivHeading" style="width:100%;" id="nrDivHeading">
			<input type="hidden" id="nAltType" value="ST">						
			<div class="card-header mms-card-header" style="text-align:center;float: left;width:100%;"><span id="nAltTypeN"><b>Sub Allotment of Fund</b></span>
		  		<input type="button" class="btn btn-success btn-sm nGlow" value="Close" id="btn_cl" style="background-color: yellow;color:blue;float: right;font-size:18px;" title="Click to CLOSE" onclick="$('#divShow').hide();">
		  	</div>
		</div>
	  	<div class="tablecontent" ><center><br><br>
		  	<table id="nrTableDataHead" class="nrTableDataHead tablecontent" style="width: 90%; height: 97%; margin:0px;" border="1"><b>
				<thead id="nrLvlTableehd"></thead> 						                     
				<tbody id="nrLvlTablee" style='border:0px solid black;cellpadding: 20px;font-size: 18px;text-decoration: bold;'>
					<tr><td colspan='2'><center><b>Allotment from </td>
					<tr><td style="width:35%;">HQ/Dte</td><td id="srcHQ" name=""></td>
					<tr><td>Unit</td><td id="srcUnit" name=""></td>
					<tr><td>Head</td><td id="srcHead" name=""></td>
					<tr><td>Available Fund</td><td id="srcFund"></td>
					<tr><tr>
			</table>
			<br>
			<table id="nrTableDataHead" class="nrTableDataHead tablecontent" style="width: 100%; height: 95%; margin:0px;" border="1"><b>
					<tr><td colspan='3'><center><b>Allotted to</td>
					<tr>
						<td style="width:49%;" >&nbsp;&nbsp;<b>Dte/Fmn HQ/Unit&nbsp;:&nbsp;<input type="text" id="nSelUnitsrc" name="nSelUnitsrc" placeholder="Search in Unit..." class=""  style="float:right;text-transform: uppercase;" 
	                        autofocus autocomplete="off" title="Type Word or Part of Word to Search in Heads" data-toggle="tooltip"  onkeypress="return isAlphaNumeric(evt)"><br>						
							<div style="height:200px;width:100%;overflow: auto;background-color: white;">
							<table id="nSelUnit" name="nSelUnit" style="margin:10px;line-height: 25px;cellmargin:10px;font-size:14px;">
							</table>			     				      
							</div>
						</td>   
						<td>&nbsp;</td>
					<td style="width:49%;" >&nbsp;&nbsp;<b>Head&nbsp;:&nbsp;<input type="text" id="nSelHeadsrc" name="nSelHeadsrc" placeholder="Search in Head..." class=""  style="float:right;text-transform: uppercase;"
	                        autofocus autocomplete="off" title="Type Word or Part of Word to Search in Heads" data-toggle="tooltip" onkeypress="return isAlphaNumeric(evt)"><br>					
							<div style="height:200px;width:100%;overflow: auto;background-color: white;">
							<table id="nSelHead" name="nSelHead" style="margin:10px;line-height: 25px;font-size:14px;border: 1px;" border="0">
								<c:if test="${n_head == 'NIL'}">
    								<tr class='nrSubHeading'><td colspan='9' style='text-align:center;'>Data Not Available...</td></tr>
								</c:if>										   
 							    <c:if test="${n_head != 'NIL'}"> 
								</c:if>
							</table>							
							</div>
						</td>
						<tr><td id="nSelUnitidd" style="background-color: lightgray;color:blue;padding-left: 10px;"></td><td></td><td id="nSelHeadidd" style="background-color: lightgray;color:blue;padding-left: 10px;"></td>						
						<tr><td>&nbsp;&nbsp;<b>Allocated Amount&nbsp;:&nbsp;(&#8377;)<input type="text" id="nFundexp" name="nFundexp" placeholder="0.00" class="form-controll" style="text-align: right;" onkeypress="return isNumberPointKey(event);"></td>
						<td colspan='2'>&nbsp;&nbsp;<b>Reference / Remarks&nbsp;:&nbsp;<input type="text" id="nFundrem" name="nFundrem" placeholder="" class="form-controll" onkeypress="return isAlphaNumeric(evt)"></td>
						<tr><td colspan='3'><center><input type="button" class="btn btn-success btn-sm nGlow" value=" Fund Sub Allot " id="btn_sub_allot" style="background-color:blue;color:yellow;float: center;font-size:20px;display:none;" title="Click to CLOSE" onclick="checkallot();"></td>
				</tbody>
			</table>         
		</div>
	</div> 	
	<div id="divShowExp" class="nPopModelContainereee" style="display:none;">
	<div id="divShowExpfirst" class="nPopCContainer" style="width:inherit; position:fixed;min-height:100px;height:cal(100% - 100px);z-index: 499;background-color: lightyellow;padding: 10px;">
 		<div class="nPopHeader" style="text-align: center;">
			Expenditure Entry	
			<span class="nPopClose" onclick="$('#divShowExp').hide();" title="Close Window">&#10006;</span>
		</div>
		<div class="nPopBody" >	
			<div class="col-md-12 row">
				<div class="col-md-4" style="display:none">HQ/Dte </div>
				<div class="col-md-8" style="display:none"><span id="srcHQx"></span></div>
				<div class="col-md-4">Unit </div>
				<div class="col-md-8"><span id="srcUnitx"></span></div>
				<div class="col-md-4">Head </div>
				<div class="col-md-8"><span id="srcHeadx"></span></div>	
				<div class="col-md-4">Available Fund (&#8377;)</div>
				<div class="col-md-8"><span id="srcFundx"></span></div>						
			</div>
			<div class="col-md-12 row form-group" style="display:none;">
				<div class="col-md-4">HQ/Dte/Unit </div>
				<div class="col-md-8"><span name="nSelUnitidx" id="Unitidx_${item[0]}_${item[1]}"></span></div>
			</div>
			<div class="col-md-12 row form-group">
				<div class="col-md-2">Head </div>
				<div class="col-md-10"><!-- <input type="text" id="nSelHeadsrcx" name="nSelHeadsrcx" placeholder="Search in Head..." class=""  style="float:right;text-transform: uppercase;"
	                        autofocus autocomplete="off" title="Type Word or Part of Word to Search in Heads" data-toggle="tooltip"  onkeypress="return isAlphaNumeric(evt)"> --></div>
	            <div class="col-md-12 nPopTable" style="height:auto;width:inherit;overflow: auto;background-color: white;"> 
	            	<table id="nSelHeadx" name="nSelHeadx" style="width: initial!important;">
	            		<thead style="text-align: center; line-height: 20px; font-size: .9vw;">
							<tr>
								<th width="1%" class="expheadbar"></th>
								<th width="1%" class="expheadbar"></th>
								<th width="1%" class="expheadbar"></th>
								<th width="95%" class="expheadbar">Head</th>
							</tr>
						</thead>
						<tbody id="exceltbl" style="font-size: .90vw; text-decoration: none;">
						    <c:if test="${n_head != 'NIL'}"> 
							<c:forEach var="item" items="${n_head}" varStatus="num">
								<c:set var="dataf" value="${fn:split(item[0],':')}"/>
								<c:set var="datafm" value="${fn:length(dataf)-1}"/>										
								<tr>      
								<c:if test="${item[2] == '1'}">
									<td colspan='4'>${dataf[datafm]} - ${item[1]}</td>
								</c:if>
								<c:if test="${item[2] == '2'}">
									<td></td>
									<td colspan='3'>${dataf[datafm]} - ${item[1]}</td>
								</c:if>
								<c:if test="${item[2] == '3'}">
									<td></td>
									<td></td>
									<td colspan='2'>${dataf[datafm]} - ${item[1]}</td>
								</c:if>
								<c:if test="${item[2] == '4'}">
									<td></td>
									<td></td>
									<td></td>
									<td>&nbsp;<input type="radio" name="nSelHeadidx" id="Headidx_${item[0]}" onclick="showsel('c','${dataf[datafm]} - ${item[1]}');">
									${dataf[datafm]} - ${item[1]}</td>
								</c:if>
					        </c:forEach>
							</c:if>
						</tbody>
					</table>	            
	            </div>
			</div>
			<div class="col-md-12 row form-group">
				<div class="col-md-4">Expense Thru</div>
				<div class="col-md-8">
					<select  id="nFundexpxmed" name="nFundexpxmed" class="form-control-sm" value="GM" title="Select Search in Category">
  							<option value="GM">GEM</option>
  							<option value="NG">Non-GEM</option>
			        </select>
   				</div>
				<div class="col-md-4">Net Amount&nbsp;:&nbsp;</div>
				<div class="col-md-8">(&#8377;)&nbsp;<input type="text"  data-decimal-pt="2" id="nFundexpx" name="nFundexpx" placeholder="0.00" class="form-controll decimal"></span></div>
				<div class="col-md-4">GST&nbsp;:&nbsp;</div>
				<div class="col-md-8">(&#8377;)&nbsp;<input type="text" data-decimal-pt="2"  id="nFundexpxgst" name="nFundexpxgst" placeholder="0.00" class="form-controll decimal"></div>	
				<div class="col-md-4">Excise Duty CESS&nbsp;:&nbsp;</div>
				<div class="col-md-8">(&#8377;)&nbsp;<input type="text" data-decimal-pt="2"  id="nFundexpxexcs" name="nFundexpxexcs" placeholder="0.00" class="form-controll decimal"></div>
				<div class="col-md-4">Others Duties&nbsp;:&nbsp;</div>
				<div class="col-md-8">(&#8377;)&nbsp;<input type="text" data-decimal-pt="2"  id="nFundexpxoth" name="nFundexpxoth" placeholder="0.00" class="form-controll decimal"></div>						
				<div class="col-md-4" style="padding-top: 8px;">Gross Amount&nbsp;:&nbsp;</div>
				<div class="col-md-8">(&#8377;)&nbsp; <input type="text" id="total" placeholder="0.00" class="form-controll" style="text-align:right;color:blue" disabled></div>
				<div class="col-md-4">Reference / Remarks&nbsp;:&nbsp;</div>
				<div class="col-md-8"><input type="text" id="nFundremx" name="nFundremx" placeholder="" class="form-control"  maxlength="100" onkeypress="return isAlphaNumeric(event)"></div>
				<div class="col-md-12"><center><input type="button" class="btn btn-success btn-sm nGlow" value=" Submit Expenditure " id="btn_cl" style="background-color:blue;color:yellow;float: center;font-size:20px;" title="Click to Submit Expenditure" onclick="checkallotx();"></div>																
			</div>
		</div>		
	</div> 
	</div>
<div id="divShowDtl" class="nPopModelContainerddd" style="display:none;">		
<div  class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span>
	<div id="divShowDtlfirst" class="nPopCContainer" style="width:96vw;height:90vh;z-index:400;background-color:azure;border:10px solid gray!important;">
					<div class="nPopHeader">
						<span style="float:center">Transaction Details</span>	
						<span class="nPopClose" onclick="$('#divShowDtl').hide();" title="Close Window">&#10006;</span>
					</div>
					<div class="nPopBody">
						<input type="hidden" id="nrDetlInput" value="ALL">
						<div id="nMsgBoard card" style="width:100%;">						
						<div class="nPopTable" id="nrTableDataDivDtllist" style="height:68vh;"> 
							<table style="width:100%;" id="nrTableDataDivDtlPrint">
				               	<thead style="text-align: center;line-height: 25px;font-size: 1vw;">
				    				<tr>
					   					<th width="22%">Head</th>
					   					<th width="8%">Projection (&#8377;)</th>
					   					<th width="8%">Fund Recd (&#8377;)</th>	   					
					      				<th width="8%">Allocation (&#8377;)</th>
										<th width="8%">Expenditure (&#8377;)</th>
										<th width="8%">Fwd to CDA (&#8377;)</th>
										<th width="8%">Bkd by CDA (&#8377;)</th>										
										<th width="2%">Trans</th>
										<th width="15%">Trans Desc</th>
										<th width="5%">Remarks</th>
										<th width="8%">Trans Date</th>
						  			</tr>
				    		  	</thead>
							    <tbody id="nrTableShowDtl" style="font-size: .90vw;text-decoration: none;color:blue">   							           
							    </tbody>							    
							    </table>							   
						</div>
						<center><input type="button" class="btn-success nBtn" value="Export to Excel" id="btn_e" style="margin-top:5px;" data-toggle="tooltip" title="Export Data to Excel" onclick="exportData('#nrTableDataDivDtllist');">
					</div>
				</div>
			</div>
	</div>
</div>
<form action="" method="post" class="form-horizontal fp-form" >
<div id="nMain" style="z-index:0;">
	<div class="nkpageland" id="printableArea">
	   <div>		
     	<div class="" align="center" id="headerView" style="display: block;z-index:0;">  
	       <div class="containerr" align="center">
	    	      <div class="card">
	    	           <div class="card-header mms-card-header" style="min-height:60px;padding-top: 10px;">
	    	                 <b>STATUS OF FUNDS</b>    		
	    	           </div>	    	           
	    	            <div class="card-body card-block ncard-body-bg" style="font-size: 1.2vw;">
	    	            	<div class="row">
	    	                 <div class="col-md-3">
	    	                       <div class="col-md-12" style="text-align: left;float: left;">
		    	                       	<b>Fin Yr&nbsp;:&nbsp;</b>
										<select id="fp_finYr" name="fp_finYr"  class="form-control-sm" title="Select Financial Year" onchange="chgeffect();">
											<c:forEach var="item" items="${n_finyr}" varStatus="num">									
												<c:if test="${item[0] == n_cfinyr}">																						
													<option value="${item[0]}" selected >${item[2]}</option>
												</c:if>
												<c:if test="${item[0] != n_cfinyr}">																						
													<option value="${item[0]}">${item[2]}</option>
												</c:if>												
									        </c:forEach>
						                </select>
	    	                       </div>            
	    	                 </div>
	    	                 <div class="col-md-4">
	    	                       <div class="col-md-12" style="text-align: right;float: right;">
		    	                       	<b>Head Aggregation Level&nbsp;:&nbsp;</b>
										<select id="fp_headsumm" name="fp_headsumm"  class="form-control-sm" title="Select Aggregation Level of Head" onchange="chgeffect();">
											<c:set var="n_4_cd" value="${fn:split(n_4,'_')}"/>
											<c:forEach var="item" items="${n_4}" varStatus="num">		    	                        		
			    	                        		<%-- <c:if test="${n_4_cd[0] <='1'}"> --%> 
														<option value="1" title="Major Head based Aggregation">Major Head Level</option>
														<option value="2" title="Minor Head based Aggregation">Minor Head Level</option>
														<option value="3" title="Sub Head based Aggregation">Sub Head Level</option>
							   						<%-- </c:if> --%>
						   							<option value="4" title="Code Head based Aggregation">Code Head Level</option>
										    </c:forEach>												      
						                </select>
	    	                       </div>     
	    	                 </div> 
	    	                 <div class="col-md-3">
									<div class="col-md-12">
										<label class=" form-control-label">Amount in </label>&nbsp;&nbsp;
										<select id="amt_rs" name="amt_rs" class="form-control-sm" title="Select Amount Conversion" onchange="chgeffect();">
											<c:set var="amt_rss" value="${fn:split(n_sel,':')}"/>																							
												<option value="CR">Crores</option>												
												<option value="RS">Rupees</option>
										</select>
									</div>
							 </div>
	    	                 <div class="col-md-2">
    	                            <input id="refdata" type="button" class="btn btn-primary btn-sm nGlow" onclick="return FindFundState();" value="Refresh Data" 
    	                            title="Get Refreshed Data">
	   	                     </div>    
	    	                 </div>      
   	                           <input type="hidden" id="nomen" name="nomen" placeholder="Search in Head...">
   	                           <input type="hidden" id="item_status" name="item_status" placeholder="Search in Head..." value="HEADDT">
   	                         <div class="row">
	    	                 <div class="col-md-12">
	    	                       <div class="col-md-3">Aggregation Lvl&nbsp;:&nbsp;
	    	                            <select  id="item_lvl_hq" name="item_lvl_hq"  class="form-control-sm" 
		    	                            title="Select the Calculation Level" onchange="chgeffect();">
		    	                            <c:forEach var="item" items="${n_4}" varStatus="num">
		    	                        		<c:set var="n_4_cd" value="${fn:split(n_4,'_')}"/>
		    	                        		<c:if test="${n_4_cd[0] !='5'}">
		    	                        			<c:if test="${not (fn:contains(role,'app') or fn:contains(role,'ddo') or fn:contains(role,'deo'))}">
						   								<option value="HQ" SELECTED title='Aggregate HQ incl their units'>HQ Level</option>
						   							</c:if>
						   						</c:if>
						   						<c:if test="${role != 'fp_view' or fn:containsIgnoreCase(role,'app') || fn:containsIgnoreCase(role,'app')}">
					   								<option value="UT" title='Aggregate only as Unit'>Unit Level</option>
					   							</c:if>
									        </c:forEach>
	   						            </select>
	    	                       </div>
	    	                       <div class="col-md-9" style="display:flex;">List of Dte/HQ Fmn/Units&nbsp;:&nbsp;
	    	                       		<c:set var="nsell" value="${n_sel}"/>
					   					<c:set var="nsel" value="${fn:split(nsell,':')}"></c:set>
	    	                       		<select  id="item_lvl" name="item_lvl"  class="form-control-sm" 
	    	                            title="Select Level of Report" onchange="chgeffect();" >
		    	                       		<c:forEach var="item" items="${n_hq}" varStatus="num">
		    	                       				<c:set var="optval" value="${item[3]}_${item[0]}_${item[1]}"/>
		    	                       				<c:if test="${nsel[2] == optval}">
					   									<option value="${optval}" selected>
					   								</c:if>			   					
					   								<c:if test="${nsel[2] != optval}">
					   									<option value="${optval}">
					   								</c:if>	   									   										
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
	    	                 </div>
	    	                 </div>
	    	            </div>
	    	      </div>
	  		</div>  		
		</div>
		<div id ="tdheaderView" style="display: none;"  align="center" class="nrTableHeading"> <b></b></div> 		
		<div class="card" style="background: transparent;" id="unit_hid2">		
				<div class="col-md-12" id = "SearchViewtr" title="">	
				    <div align="left" class="col-md-10">
				    	<!-- <label class="nrTableHelp" style="font-size:1vw;"><b>Double Click on Head Details for Filling Expenditure</b></label> -->
				    	<label class="nrTableHelp" style="font-size:1vw;"><b>For Expenditure: 1. Select 'Aggregation Lvl' as 'Unit Level' and 'Head Aggregation Lvl' as 'Code Head Level'<br>2. After that Press 'Refresh Data' Button and Double Click on Head Details.</b></label>
				    </div>
				    <div class="nrTableMain2Search col-md-2" align="left" > 
						<input id="nrInput" type="text" placeholder="Search Head Code." size="15" style="font-weight: normal;font-size: 14px;"  onkeypress="return isAlphaNumeric(event)" title="Search Head Code">
				    </div>		    		
				</div>	
				<div  class="watermarked" data-watermark="" id="divwatermark"  >
					<span id="ip"></span>	               
		               <input type="hidden" id="selectedid" name="selectedid">
		               <input type="hidden" id="selectedpid" name="selectedpid"> 
		               <input type="hidden" id="nrSrcSel" name="nrSrcSel">  
		               <c:set var="hdsm" value="${fp_headsumm}"/>
					   <c:set var="nsell" value="${n_sel}"/>
					   <c:set var="nsel" value="${fn:split(nsell,':')}"></c:set>
					   <c:set var="fmtrs" value="Cr"></c:set>
					    <c:if test="${nsel[5] == 'RS'}">
					    	<c:set var="fmtrs" value="Rs"></c:set>
					    </c:if>
						<div class="nrTableDataDiv_1 nPopTable" id="nrTableDataDiv" style="height:270px;width:100%;"> 
							   <table border="1" class="report_printt" width="96%" style="width:inherit!important" >
	                        		<thead style="text-align: center;line-height: 25px;font-size: 1vw;">
	                          				<tr>
	                          				<c:if test="${nsel[1] == 'HQ'}">
				                  				<th class="expheadbar"><span id="qHeadId">Head</span></th>
				                  				<th class="expheadbar" width="12%">Projection<br>(in ${nsel[5]})</th>
				                  				<!-- <th class="expheadbar" width="12%">Allotter</th> -->
				                  				<th class="expheadbar" width="12%">Fund Recd<br>(in ${nsel[5]})</th>
				                    			<th width="12%" class="expheadbar fp_view">Allocation<br>(in ${nsel[5]})</th>
				            					<th width="12%" class="expheadbar fp_view">Balance<br>(in ${nsel[5]})</th>
				            					<th width="12%" class="cdaheadbar">Expenditure<br>(in ${nsel[5]})</th>
				            					<th width="12%" class="cdaheadbar">Fwd to CDA<br>(in ${nsel[5]})</th>
				            					<th width="12%" class="cdaheadbar">Booked by CDA<br>(in ${nsel[5]})</th>
				            				</c:if>
	                          				<c:if test="${nsel[1] != 'HQ'}">
				                  				<th class="expheadbar">Head</th>
				                  				<th width="12%" class="expheadbar">Projection<br>(in ${nsel[5]})</th>
				                  				<!-- <th class="expheadbar" width="12%">Allotter</th> -->
				                  				<th width="12%" class="expheadbar">Fund Allocated<br>(in ${nsel[5]})</th>
				            					<th width="12%" class="expheadbar">Expenditure<br>(in ${nsel[5]})</th>
				            					<th width="12%" class="expheadbar">Balance<br>(in ${nsel[5]})</th>
				            					<th width="12%" class="cdaheadbar">Fwd to CDA<br>(in ${nsel[5]})</th>
				            					<th width="12%" class="cdaheadbar">Booked by CDA<br>(in ${nsel[5]})</th>
				            				</c:if>
                  			               </tr>
                        		     </thead>
    							     <tbody id="nrTable" style="font-size: .90vw;text-decoration: none;">
    							           <c:if test="${n_1[0][0] == 'NIL'}">
    							                 <tr class='nrSubHeading'>
    							                 	<% ntotln++; %>
													 <td colspan='10' style='text-align:center;'>Data Not Available</td>
												 </tr>
										   </c:if>	
										   <c:set var="nqsell" value="${n_sel}"/>
					   					   <c:set var="nqsel" value="${fn:split(nsell,':')}"></c:set>
					   					   <c:set var="nqsel1" value="${fn:split(nqsel[2],'_')}"></c:set>
					   					   <c:set var="lstbal" value="0"/>									   
										   <% String tmpmjhead = "";%>    
										   <% String tmpic = ""; %>						   										   
										   <c:if test="${n_1[0][0] != 'NIL'}"> 
										       <c:forEach var="item" items="${n_1}" varStatus="num">
										           <c:if test="${tmpmjhead != item[0]}">
										           	   <%-- <c:set var="balamt" value="${item[2]-item[3]-item[4]}"/> --%>
										           	   <c:set var="balamt" value="${item[2]-item[3]}"/>
 										           	   <c:set var="balamt1" value="${item[2]-item[3]}"/>
										           	   <c:if test="${nsel[1] == 'HQ'}"> 
															<c:if test="${nqsel1[0] == '-1'}">
										           	   			<c:if test="${num.index==0}">
										           	   				<c:set var="balamt" value="${item[2]-item[3]}"/>
										           	   			</c:if>
										           	   			<c:if test="${num.index >0}"> 										           	   
										           	   				<c:set var="balamt" value="${lstbal-item[3]}"/>
										           	   			</c:if>
										           	   		</c:if>
										           	   		<c:if test="${nqsel1[0] != '-1'}">										           	   
																<c:set var="balamt" value="${item[2]-item[3]}"/>
																<c:set var="balamt2" value="${item}"/>
													   		</c:if>
													   </c:if>													
													   <c:if test="${nsel[1] != 'HQ'}"> 
															<c:set var="balamt" value="${item[2]-item[4]}"/>
															<c:set var="balamt5" value="${item[2]-item[4]}"/>
													   </c:if>
											           <c:set var="tmpmjhead" value="${item[7]}_${nsel[3]}:${item[0]}_${nsel[2]}"></c:set>
											           <c:set var="headlist" value="${fn:split(item[0],':')}"/>
											           <c:if test="${nsel[1] == 'HQ'}">
												           <tr id="ln${tmpmjhead}" ondblclick="callnPop('ln${tmpmjhead}_${balamt}')" oncclick="callnPop('')" title="Double Click for Options">   
										           		 	<c:if test="${headlist[0]==item[0]}">
										                	   <td  style='text-align:left;'>${headlist[0]} - ${item[1]}</td>										                	  									                	  
										                	</c:if>
										                	<c:set var="tmpval" value="${headlist[0]}:${headlist[1]}"/>
										                	<c:if test="${tmpval==item[0]}">
										                		<td  style='text-align:left;'>${headlist[1]} - ${item[1]}</td>
										                	</c:if>
										                	<c:set var="tmpval" value="${headlist[0]}:${headlist[1]}:${headlist[2]}"/>
										                	<c:if test="${tmpval==item[0]}">
										                		<td  style='text-align:left;'>${headlist[1]}:${headlist[2]} - ${item[1]}</td>
										                	</c:if>
										                	<c:set var="tmpval" value="${headlist[0]}:${headlist[1]}:${headlist[2]}:${headlist[3]}"/>
										                	<c:if test="${tmpval==item[0]}">
										                		<td  style='text-align:left;'>${headlist[3]} - ${item[1]}</td>
										                	</c:if>
										                	<c:set var="tmpval" value="${headlist[0]}:${headlist[1]}:${headlist[2]}:${headlist[3]}:${headlist[4]}"/>
										                	<c:if test="${tmpval==item[0]}">
										                		<td  style='text-align:left;'>${headlist[4]} - ${item[1]}</td>
										                	</c:if>
										                	<c:set var="tmpval" value="${headlist[0]}:${headlist[1]}:${headlist[2]}:${headlist[3]}:${headlist[4]}:${headlist[5]}"/>
										                	<c:if test="${tmpval==item[0]}">
										                		<td  style='text-align:left;'>${headlist[5]} - ${item[1]}</td>
										                	</c:if>										             		
										                    <c:if test="${empty item[8]}">  
										                    	<td style="text-align:right;padding-right:5px;"width="8%">0.00</td>
										                    </c:if>
 										                    <c:if test="${not empty item[8]}">
										                    	<td style="text-align:right;padding-right:5px;"width="8%"><script>document.write(Number('${item[8]}').toINR('','${nsel[5]}','INR'))</script></td>										                    	
										                    </c:if>
										                    <%-- <td>${item[12]}</td> --%>
										                    <c:if test="${empty item[2]}"> 
										                    	<td style="text-align:right;padding-right:5px;"width="8%">0.00</td>
										                    </c:if>
 										                    <c:if test="${not empty item[2]}">
										                    	<td style="text-align:right;padding-right:5px;"width="8%"><script>document.write(Number('${item[2]}').toINR('','${nsel[5]}','INR'))</script></td>
										                    </c:if>
 										                    <c:if test="${empty item[3]}"> 
										                    	<td class="fp_view" style="text-align:right;padding-right:5px;"width="8%">0.00</td>
										                    </c:if>
										                    <c:if test="${not empty item[3]}">
										                    	<td class="fp_view" style="text-align:right;padding-right:5px;"width="8%"><script>document.write(Number('${item[3]}').toINR('','${nsel[5]}','INR'))</script></td>
										                    </c:if>
	 															<td class="fp_view" style="text-align:right;padding-right:5px;"width="8%"> 
	 																<%-- <c:if test="${balamt >= 0}"> --%>
	 																	<script>document.write(Number('${balamt}').toINR('','${nsel[5]}','INR'))</script>	 																
	 																<%-- </c:if> --%>
																</td>
										                    <c:if test="${empty item[4]}">
										                    	<td style="text-align:right;padding-right:5px;"width="8%">0.00</td>
										                    </c:if>
										                    <c:if test="${not empty item[4]}">
										                    	<td style="text-align:right;padding-right:5px;"width="8%"><script>document.write(Number('${item[4]}').toINR('','${nsel[5]}','INR'))</script></td>
										                    </c:if>
															<td class="ccda" style="text-align:right;padding-right:5px;"width="8%"><script>document.write(Number('${item[9]}').toINR('','${nsel[5]}','INR'))</script></td>
															<td class="ccda" style="text-align:right;padding-right:5px;"width="8%"><script>document.write(Number('${item[10]}').toINR('','${nsel[5]}','INR'))</script></td>
															<c:set var="lstbal" value="${balamt}"/>
										                	</tr>
										                </c:if>
   											            <c:if test="${nsel[1] != 'HQ'}">
												           <tr id="ln${tmpmjhead}" ondblclick="callnPop('ln${tmpmjhead}_${balamt}')" oncclick="callnPop('')" title="Double Click for Options">   
										           		 	<c:if test="${headlist[0]==item[0]}">
										                	   <td  style='text-align:left;'>${headlist[0]} - ${item[1]}</td>										                	  									                	  
										                	</c:if>
										                	<c:set var="tmpval" value="${headlist[0]}:${headlist[1]}"/>
										                	<c:if test="${tmpval==item[0]}">
										                		<td  style='text-align:left;'>${headlist[1]} - ${item[1]}</td>
										                	</c:if>
										                	<c:set var="tmpval" value="${headlist[0]}:${headlist[1]}:${headlist[2]}"/>
										                	<c:if test="${tmpval==item[0]}">
										                		<td  style='text-align:left;'>${headlist[2]} - ${item[1]}</td>
										                	</c:if>
										                	<c:set var="tmpval" value="${headlist[0]}:${headlist[1]}:${headlist[2]}:${headlist[3]}"/>
										                	<c:if test="${tmpval==item[0]}">
										                		<td  style='text-align:left;'>${headlist[3]} - ${item[1]}</td>
										                	</c:if>
										                	<c:set var="tmpval" value="${headlist[0]}:${headlist[1]}:${headlist[2]}:${headlist[3]}:${headlist[4]}"/>
										                	<c:if test="${tmpval==item[0]}">
										                		<td  style='text-align:left;'>${headlist[4]} - ${item[1]}</td>
										                	</c:if>
										                	<c:set var="tmpval" value="${headlist[0]}:${headlist[1]}:${headlist[2]}:${headlist[3]}:${headlist[4]}:${headlist[5]}"/>
										                	<c:if test="${tmpval==item[0]}">
										                		<td  style='text-align:left;'>${headlist[5]} - ${item[1]}</td>
										                	</c:if>														                							                											            
										                    <c:if test="${empty item[8]}">  
										                    	<td style="text-align:right;padding-right:5px;"width="8%">0.00</td>
										                    </c:if>
 										                    <c:if test="${not empty item[8]}">
										                    	<td style="text-align:right;padding-right:5px;"width="8%"><script>document.write(Number('${item[8]}').toINR('','${nsel[5]}','INR'))</script></td>										                    	
										                    </c:if>
										                    <%-- <td>${item[12]}</td> --%>
										                    <c:if test="${empty item[2]}">  
										                    	<td style="text-align:right;padding-right:5px;"width="8%">0.00</td>
										                    </c:if>
 										                    <c:if test="${not empty item[2]}">
										                    	<td style="text-align:right;padding-right:5px;"width="8%"><script>document.write(Number('${item[2]}').toINR('','${nsel[5]}','INR'))</script></td>
										                    </c:if>
										                 	<c:if test="${empty item[4]}">
										                    	<td style="text-align:right;padding-right:5px;"width="8%">0.00</td>
										                    </c:if>
										                    <c:if test="${not empty item[4]}">
										                    	<td style="text-align:right;padding-right:5px;"width="8%"><script>document.write(Number('${item[4]}').toINR('','${nsel[5]}','INR'))</script></td>
										                    </c:if>
																<td style="text-align:right;padding-right:5px;"width="8%"> 
	 																<c:if test="${balamt >= 0}"><script>document.write(Number('${balamt}').toINR('','${nsel[5]}','INR'))</script></c:if>
																</td>
															<td class="ccda" style="text-align:right;padding-right:5px;"width="8%"><script>document.write(Number('${item[9]}').toINR('','${nsel[5]}','INR'))</script></td>
															<td class="ccda" style="text-align:right;padding-right:5px;"width="8%"><script>document.write(Number('${item[10]}').toINR('','${nsel[5]}','INR'))</script></td>
										                	</tr>
										                </c:if>										                
										           </c:if> 										           
										       </c:forEach>
										   </c:if>
    							     </tbody>
    							     <div class="nCMenu" id="nCMenu" style="">
										<ul>
											<li><a href="javascript:showdetl();">&nbsp;&nbsp;Show in Details&nbsp;&nbsp;</a></li>
										</ul>
									 </div> 
    							     <div class="nCMenu1" id="nCMenu1" style="">
										<ul>
											<li><a href="javascript:showdetl();">&nbsp;&nbsp;Show in Details&nbsp;&nbsp;</a></li>
											<li><a href="javascript:callnPopExp('expen');">&nbsp;&nbsp;Expenditure&nbsp;&nbsp;</a></li>											
										</ul>
									 </div>
									 <div class="nCMenu1" id="nCMenu2" style="">
										<ul>
											<li><a href="javascript:showdetl();">&nbsp;&nbsp;Show in Details&nbsp;&nbsp;</a></li>
										</ul>
									 </div> 									 
									 <div class="nCMenu1" id="nCMenu3" style="">
										<ul>
											<li><a href="javascript:showdetl();">&nbsp;&nbsp;Show in Details&nbsp;&nbsp;</a></li>
										</ul>
									 </div> 									 
							    </table>
							    </div>       
						   </div> 
						    <div  class="card-footer" style="top:-10px;">
						        <input type="button" class="btn btn-success btn-sm nGlow" value="Export to Excel" id="btn_e" style="background-color: purple;" data-toggle="tooltip" title="Export Data to Excel" onclick="exportData('#nrTableDataDiv');">
				           	</div>
		         		</div> 
		    		</div>
		       </div>            
</div>
</form>

<c:url value="fp_fund_status_flt?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_fund_status" class="fp-form" name="m1_fund_status" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_tryear" id="m1_tryear"/>
	  <input type="hidden" name="m1_nomen" id="m1_nomen"/>
	  <input type="hidden" name="m1_nomenin" id="m1_nomenin"/>
	  <input type="hidden" name="m1_slvl" id="m1_slvl"/>
	  <input type="hidden" name="m1_lvl" id="m1_lvl"/>
	  <input type="hidden" name="m1_hdlvl" id="m1_hdlvl"/>
	  <input type="hidden" name="m1_rsfmt" id="m1_rsfmt"/>
</form:form>

<c:url value="fp_fund_tfr_confirm?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_fund_tfr" class="fp-form" name="m1_fund_tfr" modelAttribute="m1_nomen">
      <input type="hidden" name="n1_trhead" id="n1_trhead"/>
	  <input type="hidden" name="n1_frunt" id="n1_frunt"/>
	  <input type="hidden" name="n1_blamt" id="n1_blamt" class="decimal"/>
	  <input type="hidden" name="n1_tramt" id="n1_tramt" class="decimal"/>
	  <input type="hidden" name="n1_tount" id="n1_tount"/>
	  <input type="hidden" name="n1_tohead" id="n1_tohead"/>
	  <input type="hidden" name="n1_trtype" id="n1_trtype"/>
	  <input type="hidden" name="n1_tryear" id="n1_tryear"/>
	  <input type="hidden" name="n1_trrem" id="n1_trrem"/>
	  <input type="hidden" name="n1_trpid" id="n1_trpid"/>
	  <input type="hidden" name="n1_trAltType" id="n1_trAltType"/>	
	  <input type="hidden" name="n1_slvl" id="n1_slvl"/>
	  <input type="hidden" name="n1_lvl" id="n1_lvl"/>
	  <input type="hidden" name="n1_hdlvl" id="n1_hdlvl"/>
	  <input type="hidden" name="n1_gstval" id="n1_gstval"/>
	  <input type="hidden" name="n1_rsfmt" id="n1_rsfmt"/>
	  <input type="hidden" name="n1_exptype" id="n1_exptype"/>	  
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/common/select2/select2.min.js" type="text/javascript"></script>

<script>
function showdetl() {
	var nHed="";
	nHed=$("#selectedid").val();
	nHed=nHed +"_"+$("#DtlFilterOpt").val()+"_"+$("#item_lvl_hq").val();
	$.post("nFundInDetl?"+key+"="+value, {nPara:nHed}, function(j) {        
 	}).done(function(j) { 		
 		var itxts="";
		var itxt="";
		var fndtot=0;
		var alttot=0;
		var exptot=0;
 		for (var i=0;i<j.length;i++){
 			if (j[i][13] !='HBH') {
				itxt += '<tr style="color:#FFFFFF!important;">';
				var tmpv=j[i][14].split(":");
				itxt += '<td style="font-size:.85vw;">'+tmpv[3]+'-'+j[i][1]+'</td>';
				if (j[i][8]>0) {
					itxt += '<td style="text-align:right;padding-right:10px;">'+Number(j[i][8]).toINR("","RS","INR","RS")+'</td>';
				} else {
					itxt += '<td ></td>';
				}
				if (j[i][2]>0) {
					itxt += '<td style="text-align:right;padding-right:10px;">'+Number(j[i][2]).toINR("","RS","INR","RS")+'</td>';
					fndtot = fndtot + parseInt(j[i][2]);
					
				} else {
					itxt += '<td ></td>';
				}
				//if (j[i][3]>0) {
					itxt += '<td style="text-align:right;padding-right:10px;">'+Number(j[i][3]).toINR("","RS","INR","RS")+'</td>';
					alttot = alttot + parseInt(j[i][3]);
				//} else {
				//	itxt += '<td ></td>';
				//}
				if (j[i][4]>0) {
					itxt += '<td style="text-align:right;padding-right:10px;">'+Number(j[i][4]).toINR("","RS","INR","RS")+'</td>';
					exptot = exptot + parseInt(j[i][4]);
				} else {
					itxt += '<td ></td>';
				}					
				if (j[i][9]>0) {
					itxt += '<td style="text-align:right;padding-right:10px;">'+Number(j[i][9]).toINR("","RS","INR","RS")+'</td>';
				} else {
					itxt += '<td ></td>';
				}					
				if (j[i][10]>0) {
					itxt += '<td style="text-align:right;padding-right:10px;">'+Number(j[i][10]).toINR("","RS","INR","RS")+'</td>';
				} else {
					itxt += '<td ></td>';
				}									
				itxt += '<td style="font-size:.75vw;">'+j[i][13]+'</td>';
				itxt += '<td >'+j[i][11]+'</td>';
				itxt += '<td >'+'</td>';
				itxt += '<td style="font-size:.75vw;">'+j[i][12].substring(0,10)+'</td>';
 			}
 		}
 		$("#nrTableShowDtl").html(itxt);
 		$("#ndtlfund").html(Number(fndtot).toINR());
 		$("#ndtlallot").html(Number(alttot).toINR());
 		$("#ndtlexp").html(Number(exptot).toINR());
 	}).fail(function(xhr, textStatus, errorThrown) { });
	$("#divShow").hide();
	$("#divShowDtl").show();
}

function FindFundState() {
	$("#refdata").removeClass("blink");
	var nYr=$("#fp_finYr").val();
	var nSrcTxt=$("#nomen").val();
	var nSrcIn=$("#item_status").val();     
	var nSmLvl=$("#item_lvl_hq").val();
	var nSmUnt=$("#item_lvl").val();
	var nHdLvl=$("#fp_headsumm").val();
	var nrsfmt=$("#amt_rs").val();

	if (nYr==null || nYr=='' || nYr=='-1') {
		alert("Please Select Financial Year");
		$("#fp_finYr").focus();
		return false;
	}
	
	if (nSmLvl==null || nSmLvl=='' || nSmLvl=='-1') {
		alert("Please Select Aggre Level");
		$("#item_lvl_hq").focus();
		return false;
	}

	if (nSmUnt==null || nSmUnt=='' || nSmUnt=='-1') {
		alert("Please Select Unit");
		$("#item_lvl").focus();
		return false;
	}

	if (nHdLvl==null || nHdLvl=='' || nHdLvl=='-1') {
		alert("Please Select Head Agree Level");
		$("#fp_headsumm").focus();
		return false;
	}
	
	$("#m1_tryear").val(nYr);
	$("#m1_nomen").val(nSrcTxt);
	$("#m1_nomenin").val(nSrcIn);
	$("#m1_slvl").val(nSmLvl);
	$("#m1_lvl").val(nSmUnt);
	$("#m1_hdlvl").val(nHdLvl);
	$("#m1_rsfmt").val(nrsfmt);
	$("#nrWaitLoader").show();
	$("#m1_fund_status").submit();
}

function showsel(a,b,sus){
	if (a=='a') {
		$("#nSelUnitidd").text(b);
		var a1=$("#selectedid").val();
		var lgsus='${roleSusNo}';
		var b1=a1.split("_");
		var nHed=b1[1].replace('ln','');
		var trTy=$("#nAltType").val();
		if (trTy=='ST') {
			updallothead(nHed,trTy);
		} else {
			updallothead(sus,trTy);	
		}	
	}
	else if (a=='b') {
		var c=$('[name=nSelHeadid]').attr('id').split('_');
		$("#nSelHeadidd").text(c[1]+" - "+b);
	}
	else if (a=='c') {
		var c=$('[name=nSelHeadid]').attr('id').split('_');
		$("#nSelHeadiddx").text(c[1]+" - "+b);
	}
	
}

function updallothead(a,b) {
 	$.post("ListHeadDesc?"+key+"="+value, {nPara:a,nParaa:b}, function(j) {
	}).done(function(j) { 
		var itxt="";
		var itxts="";
		$("#nSelHead").html("");
		if (j[0][0]=='NIL') {
			alert('No Budget Head is Available.\nFirst create your Budget Head.');
			$("#btn_sub_allot").hide();
		} else {
    		for (var i=0;i<j.length;i++){
    			itxts=j[i][0].split(":");
    			if (j[i][2]=='1') {
    				itxt += '<tr style="padding:0px;margin:0px;border:1px solid black;"><td><center>Major Head: '+itxts[itxts.length-1]+'-'+j[i][1]+'</td>';
    			}
    			if (j[i][2]=='2') {
    				itxt += '<tr style="padding:0px;margin:0px;border:1px solid black;"><td><center>Minor Head: '+itxts[itxts.length-1]+'-'+j[i][1]+'</td>';
    			}
    			if (j[i][2]=='3') {
    				itxt += '<tr style="padding:0px;margin:0px;border:1px solid black;"><td><center>Sub Head: '+itxts[itxts.length-1]+'-'+j[i][1]+'</td>';
    			}
    			if (j[i][2]=='4') {
    				itxt += '<tr><td>&nbsp;&nbsp;&nbsp;<input type="radio" name="nSelHeadid" id="Headid_'+j[i][0]+'" onclick="showsel(\'b\',\''+itxts[itxts.length-1]+'-'+j[i][1]+'\')">&nbsp;'+itxts[itxts.length-1]+'-'+j[i][1]+'</td>';
    			}     			
    		}
    		$("#nSelHead").html(itxt);
    		$("#btn_sub_allot").show();
		}     		
	}).fail(function(xhr, textStatus, errorThrown) { });		

}

function checkallot() {
	var ntrTy=$("#nAltType").val();
	var nYr=$("#fp_finYr").val();
	var nrem=$("#nFundrem").val();
	var nAvl=$("#srcFund").html();
	var nExp=$("#nFundexp").val();	
	var nAvln=parseFloat(nAvl);
	var nExpn=parseFloat(nExp);
	if (nExpn==0 || nExp== null || nExp =='') {
		alert("Please Enter Some Fund.");
		return false;
	} else if (nExpn>nAvln) {
		alert("Amount is more than Available Fund. Please Check ...");
		return false;
	} 
	var untn=$('input:radio[name=nSelUnitid]:checked').attr('id');
	if (untn==null || untn=='') {       
		alert("Please Select Dte/HQ FMn/Unit to whom fund Transfered.");
		return false;
	} 
	var hedn=$('input:radio[name=nSelHeadid]:checked').attr('id');
	if (hedn==null || hedn=='') {
		alert("Please Select Head to whom fund Transfered.");
		return false;
	} 
	
	var nSmLvl=$("#item_lvl_hq").val();
	var nSmUnt=$("#item_lvl").val();
	var nHdLvl=$("#fp_headsumm").val();
	
	var qtrhead=$('[id=srcHead]').attr('name');
	var qfrsus=$('[id=srcUnit]').attr('name');
	var qhdsumm=$('#fp_headsumm').val();
	var qrsfmt=$('#amt_rs').val();
	var qblamt=nAvl;
	var qtramt=nExp;
	var qtount=untn;
	var qtohead=hedn;
	var qtrtype="TFR";
	var qtrpid=$('#selectedpid').val();
	var qfrfmn=$('[id=srcHQ]').attr('name');
	$("#n1_trhead").val(qtrhead);
	$("#n1_frunt").val(qfrfmn+"_"+qfrsus);
	$("#n1_blamt").val(qblamt);
	$("#n1_tramt").val(qtramt);
	$("#n1_tount").val(qtount);
	$("#n1_tohead").val(qtohead);      
	$("#n1_trtype").val(qtrtype);
	$("#n1_tryear").val(nYr);
	$("#n1_trrem").val(nrem);
	$("#n1_hdsumm").val(qhdsumm);
	$("#n1_trpid").val(qtrpid);
	$("#n1_trAltType").val($("#nAltType").val());
	$("#n1_slvl").val(nSmLvl);
	$("#n1_lvl").val(nSmUnt);
	$("#n1_hdlvl").val(nHdLvl);
	$("#n1_gstval").val(0);
	$("#n1_exptype").val("");
	$('#n1_rsfmt').val(qrsfmt);
	$("#m1_fund_tfr").submit();
}

function checkallotx() {

	var nAvln=0;	

	var nYr=$("#fp_finYr").val();
	var nrem=$("#nFundremx").val();
	var nAvl=$("#srcFundx").html();
	nAvl=nAvl.replaceAll("&#8377;","");	
	nAvl=nAvl.replaceAll(",","");
	if (isNaN(nAvl)){
		nAvln=0;
	} else {
		nAvln=parseFloat(nAvl);
	}
	var nExp=$("#nFundexpx").val();
	
	var nExpgst1=parseFloat($("#nFundexpxgst").val());
	var nExpgst2=parseFloat($("#nFundexpxexcs").val());
	var nExpgst3=parseFloat($("#nFundexpxoth").val());
	if (isNaN(nExpgst1)){
		nExpgst1="0";
	}
	if (isNaN(nExpgst2)){
		nExpgst2="0";
	}
	if (isNaN(nExpgst3)){
		nExpgst3="0";
	}
	var nExpgst=nExpgst1+":"+nExpgst2+":"+nExpgst3;
	
	var nExpn=parseFloat(nExp);
	var nExpngst=parseFloat(nExpgst1)+parseFloat(nExpgst2)+parseFloat(nExpgst3);
	if (nAvln<=0 || nAvl== null || nAvl =='') {
		alert("Insufficient Fund to Expense.\nPlease Check and Try Again.");
		return false;
	}
	if (nExpn<=0 || nExp== null || nExp =='') {
		alert("Please Enter Some Fund.");
		return false;
	} 
	if ((nExpn+nExpngst)>nAvln) {
		alert("Amount is more than Available Fund. Please Check ...");
		return false;
	} 
	var untn=$('[name=nSelUnitidx]').attr('id');
	if (untn==null || untn=='') {
		alert("Please Select Dte/HQ FMn/Unit to whom fund being Expense.");
		return false;
	} 
	var hedn=$('input:radio[name=nSelHeadid]:checked').attr('id');
	if (hedn==null || hedn=='') {
		alert("Please Select Head to whom fund being Expense.");
		return false;
	} 
	
	var nSmLvl=$("#item_lvl_hq").val();
	var nSmUnt=$("#item_lvl").val();
	var nHdLvl=$("#fp_headsumm").val();
	var qrsfmt=$('#amt_rs').val();
	var qtrhead=$('[id=srcHeadx]').attr('name');
	var qfrsus=$('[id=srcUnitx]').attr('name');
	var qhdsumm=$('#fp_headsumm').val();
	var qblamt=nAvl;
	var qtramt=nExp;
	var qtount=untn;
	var qtohead=hedn;
	var qtrtype="EXP";
	var qtrpid=$('#selectedpid').val();
	var qfrfmn=$('[id=srcHQx]').attr('name');
	var nExptype=$('#nFundexpxmed').val();	
	$("#n1_trhead").val(qtrhead);
	$("#n1_frunt").val(qfrfmn+"_"+qfrsus);
	$("#n1_blamt").val(qblamt);
	$("#n1_tramt").val(qtramt);
	$("#n1_tount").val(qtount);
	$("#n1_tohead").val(qtohead);      
	$("#n1_trtype").val(qtrtype);
	$("#n1_tryear").val(nYr);
	$("#n1_trrem").val(nrem);
	$("#n1_hdsumm").val(qhdsumm);
	$("#n1_trpid").val(qtrpid);
	
	$("#n1_slvl").val(nSmLvl);
	$("#n1_lvl").val(nSmUnt);
	$("#n1_hdlvl").val(nHdLvl);
	$("#n1_gstval").val(nExpgst);
	$("#n1_exptype").val(nExptype);
	$('#n1_rsfmt').val(qrsfmt);
	$("#nrWaitLoader").show();
	$("#m1_fund_tfr").submit();
}

function callnPop(a){
	var hqsus='${roleSusNo}';
	$("#selectedid").val(a);
	var b=a.split("_");
	event.preventDefault();
	var d=$("#item_lvl_hq").val();
	var e=$("#fp_headsumm").val();
	if (d=='HQ') {
	    if (e=='4') {
	    	if (hqsus==b[4]) {
	    		if (b[2] !="-1") {
			    	$("#nCMenu").hide();
					$("#nCMenu1").hide();
					$("#nCMenu3").hide();
					$("#nCMenu2").show();
					$("#nCMenu2").offset({'top':nY,'left':nX});	    			
	    		} else {
	    			$("#nCMenu2").hide();
	    			$("#nCMenu1").hide();
	    			$("#nCMenu3").hide();
	    			$("#nCMenu").show();
	    			$("#nCMenu").offset({'top':nY,'left':nX});
	    		}
	    	} else {
		    	$("#nCMenu").hide();
				$("#nCMenu1").hide();
				$("#nCMenu2").hide();
				$("#nCMenu3").show();
				$("#nCMenu3").offset({'top':nY,'left':nX});
	    	}			
	    } else {
	    	$("#nCMenu2").hide();
			$("#nCMenu1").hide();
			$("#nCMenu3").hide();
			$("#nCMenu").show();
			$("#nCMenu").offset({'top':nY,'left':nX});
	    }
	}
	if (d=='UT') {
		 if (e=='4') {
			$("#nCMenu2").hide();
			$("#nCMenu").hide();
			$("#nCMenu3").hide();
			$("#nCMenu1").show();
			$("#nCMenu1").offset({'top':nY,'left':nX});
		 } else {
			$("#nCMenu2").hide();
			$("#nCMenu1").hide();
			$("#nCMenu3").hide();
			$("#nCMenu").show();
			$("#nCMenu").offset({'top':nY,'left':nX});
		 }
	}	
}

function callnPopExp(d){
	var sus='${roleSusNo}';
	var a=$("#selectedid").val();
	var bb=$("#amt_rs").val();
	var a1=a.split("_");
	if (sus!=a1[4]) {
		alert("Expenditure Not Permitted.");
		return false;
	} else {
		if (d.length>0) {		
			$("#nSelHead").html("");
			$("#nSelHeadx").html("");		
			var b=a.split("_");	
			var nHq='HQ_'+b[2]+"_"+b[3]+"_"+b[4];	
			$.post("FindUnitDesc?"+key+"="+value, {nPara:nHq}, function(j) {     
	     		$("#srcHQx").html(b[3]);
	     	}).done(function(j) { 
	     		$("#srcHQx").html(j[0][2]);
	     		$("#srcHQx").attr("name",b[3]);
	     	}).fail(function(xhr, textStatus, errorThrown) { });
			
			var nUnt='Unit_'+b[2]+"_"+b[3]+"_"+b[4];	
			$.post("FindUnitDesc?"+key+"="+value, {nPara:nUnt}, function(j) {
	     		$("#srcUnitx").html(b[4]);
	     	}).done(function(j) { 
	     		$("#srcUnitx").html(j[0][2]);
	     		$("#srcUnitx").attr("name",j[0][1]);
	     	}).fail(function(xhr, textStatus, errorThrown) {});
			
			$("[name=nSelUnitidx]").attr("id","Unitidx_"+b[3]+"_"+b[4]);
			$("[name=nSelUnitidx]").html($("[name=nSelUnitidx]").attr('id'));
			$("#selectedpid").val(b[0].replace('ln',''));
			var nHed=b[1].replace('ln','');
			$.post("FindHeadDesc?"+key+"="+value, {nPara:nHed}, function(j) {
	     		$("#srcHeadx").html(nHed);
	     	}).done(function(j) { 
	     		var nt=nHed.substring(2,nHed.length);
	     		$("#srcHeadx").html(nt+"-"+j[0][1]);  
	     		$("#srcHeadx").attr("name",nt);
	     	}).fail(function(xhr, textStatus, errorThrown) {});
			var trTy="EX";
			$.post("ListHeadDesc?"+key+"="+value, {nPara:nHed,nParaa:trTy}, function(j) {
	     		$("#srcHeadx").html(nHed);
	     	}).done(function(j) { 
	     		var nt=nHed.substring(2,nHed.length);
	     		$("#srcHeadx").html(nt+"-"+j[0][1]);  
	     		$("#srcHeadx").attr("name",nt);
	     		var itxt="";
	     		var itxts="";
	     		for (var i=0;i<j.length;i++){
	     			itxts=j[i][0].split(":");
	     			if (j[i][2]=='1') {
	     				itxt += '<tr style="padding:0px;margin:0px;border:1px solid black;"><td><center>Major Head: '+itxts[itxts.length-1]+'-'+j[i][1]+'</td>';
	     			}
	     			if (j[i][2]=='2') {
	     				itxt += '<tr style="padding:0px;margin:0px;border:1px solid black;"><td><center>Minor Head: '+itxts[itxts.length-1]+'-'+j[i][1]+'</td>';
	     			}
	     			if (j[i][2]=='3') {
	     				itxt += '<tr style="padding:0px;margin:0px;border:1px solid black;"><td><center>Sub Head: '+itxts[itxts.length-1]+'-'+j[i][1]+'</td>';
	     			}
	     			if (j[i][2]=='4') {
	     				itxt += ' <tr><td>&nbsp;&nbsp;&nbsp;<input type="radio" name="nSelHeadid" id="Headid_'+j[i][0]+'" onclick="showsel(\'c\',\''+itxts[itxts.length-1]+'-'+j[i][1]+'\')">&nbsp;'+itxts[itxts.length-1]+'-'+j[i][1]+'</td>';
	     			}     			
	     		}
	     		$("#nSelHeadx").html(itxt);
	     	}).fail(function(xhr, textStatus, errorThrown) { });
			$("#srcFundx").html(Number(b[5]).toINR('',bb,'INR','RS'));		
			$("#divShow").hide();
			$("#divShowExp").show();
		} else {
			$("#selectedid").val('');
			$("#btn_alt").hide();        
			$("#btn_exp").hide();
			$("#divShow").hide();
			$("#divShowExp").hide();
		}
	}
}

/* function callnPopp(d){
	if (d.length>0) {
		if (d=='reall'){
			$("#nAltType").val("RT");
			$("#nAltTypeN").text("Re - Adjustment of Fund");			
		} else {
			$("#nAltType").val("ST");
			$("#nAltTypeN").text("Sub Allotment of Fund");
		}
		$("#nSelHead").html("");
		$("#nSelHeadx").html("");

		var a=$("#selectedid").val();
		var lgsus='${roleSusNo}';
		var b=a.split("_");
		var nHq='HQ_'+b[2]+"_"+b[3]+"_"+b[4];	
		$.post("FindUnitDesc?"+key+"="+value, {nPara:nHq}, function(j) {     
     		$("#srcHQ").html(b[3]);
     	}).done(function(j) { 
     		$("#srcHQ").html(j[0][2]);
     		$("#srcHQ").attr("name",b[3]);
     	}).fail(function(xhr, textStatus, errorThrown) { });
		
		var nUnt='Unit_'+b[2]+"_"+b[3]+"_"+b[4];
				
		$.post("FindUnitDesc?"+key+"="+value, {nPara:nUnt}, function(j) {
     		$("#srcUnit").html(b[4]);
     	}).done(function(j) { 
     		$("#srcUnit").html(j[0][2]);
     		$("#srcUnit").attr("name",j[0][1]);
     	}).fail(function(xhr, textStatus, errorThrown) { });
		
		$("#selectedpid").val(b[0].replace('ln',''));
		var nHed=b[1].replace('ln','');
		$.post("FindHeadDesc?"+key+"="+value, {nPara:nHed}, function(j) {
     		$("#srcHead").html(nHed);
     	}).done(function(j) { 
     		var nt=nHed.substring(2,nHed.length);
     		$("#srcHead").html(nt+"-"+j[0][1]);  
     		$("#srcHead").attr("name",nt);
     	}).fail(function(xhr, textStatus, errorThrown) { });
		 if (b[3]==4) {
			 var nUntt='UNIT_'+b[2]+"_"+b[3]+"_"+b[4];
		} else {			
			var nUntt='HQ_'+b[2]+"_"+b[3]+"_"+b[4];
		}
		var nUnttt=$("#nAltType").val();
 	
		if (b[3]==4) {
			 var nUntt='UNIT_'+b[2]+"_"+b[3]+"_"+b[4];
		} else {			
			var nUntt='HQ_'+b[2]+"_"+b[3]+"_"+b[4];
		}
 		var nUntt='Unit_'+b[2]+"_"+b[3]+"_"+lgsus;
		$.post("ListUnitDesc?"+key+"="+value, {nPara:nUntt}, function(j) {
     	}).done(function(j) { 
     		if (j !='NIL') {
     			var iitxt="";
     			var iitxts="";
     			for (var i=0;i<j.length;i++){
     				iitxts=j[i][0].split(":");     			
     				iitxt += '<tr><td>&nbsp;<input type="radio" name="nSelUnitid" id="Unitid_'+j[i][0]+"_"+j[i][1]+"\" onclick=\"showsel('a','"+j[i][2]+"','"+j[i][1]+"');\">&nbsp;"+j[i][2]+"</td>";
     			}     	
     			$("#nSelUnit").html(iitxt);
     			$("#btn_sub_allot").show();
     		} else {
      			alert('No Budget Holder is Available.\nFirst create your Budget Holder.');
      			$("#btn_sub_allot").hide();
     		}
     	}).fail(function(xhr, textStatus, errorThrown) { });			
		
		$("#srcFund").html(Number(b[5]).toINR('&#8377;'));
		$("#divShow").show();
	} else {      
		$("#selectedid").val('');
		$("#btn_alt").hide();
		$("#btn_exp").hide();
		$("#divShow").hide();
	}
} */

 function printDiv() {
	  $("#SearchViewtr").hide();
	  $("#tdheaderView").show();
	  $("#headerView").hide();
	  $("#btn_e").hide();
	  $("#btn_p").hide();
	  $("#btn_modify").hide();
	  $('.rdView').css('display','none');
	  let innerContents = document.getElementById('printableArea').innerHTML;	 
	  popupWindow = window.open('', '_blank', 'width=850,height=500,scrollbars=yes,menubar=no,toolbar=no,location=no,status=no,titlebar=no');
	  popupWindow.document.open();
	  popupWindow.document.write('<html><head><link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"><link rel="stylesheet" href="js/cue/printWatermark.css"><style> table td{font-size:10px; font-weight:normal !important;} table th{font-size:12px !important;} tbody th{font-size:10px; font-weight:normal !important;}</style></head><body onload="window.focus(); window.print(); window.close();" oncopy="return false" oncut="return false" onpaste="return false" oncontextmenu="return false">' +innerContents+  '</html>');
	  popupWindow.document.close();
	  $("#SearchViewtr").show();
	  $("#tdheaderView").hide();
	  $("#headerView").show();
	  $("#btn_e").show();
	  $("#btn_p").show();
	  $("#btn_modify").show();
	  $('.rdView').css('display','block');
} 

function exportData(b){
	$().tbtoExcel(b);
	b.preventDefault();
}

function setdtlFilter() {
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
		$(".ALC").show();
		$(".EXP").show();
	} else if (value=='ALC'){
		$(".EXP").hide();
		$(".ALC").show();
	} else if (value=='EXP') {		
		$(".ALC").hide();
		$(".EXP").show();
	}
 }

$(document).ready(function() {
	
	$('#item_lvl').nselect();
	$("#nrWaitLoader").hide();
	$(document).mouseover(function (e) {
		nX=e.pageX;
		nY=e.pageY;
		if (nY>500) {
			nY=nY - 90;
		}
	});
	
	$("table tbody tr td").bind("dblclick",function (e){
		if ($(e.target).hasClass("ccda")) {
			alert("'Fwd to CDA' or 'Booked by CDA' is available at\nFUND STATUS -> CDA STATUS");
			return false;
		}		
	});
		
	$(document).bind("click",function (e){
		if(!$(e.length).parents(".nCMenu").length>0){
			$(".nCMenu").hide(100);
		}
		if(!$(e.length).parents(".nCMenu1").length>0){
			$(".nCMenu1").hide(100);
		}
		if(!$(e.length).parents(".nCMenu2").length>0){
			$(".nCMenu2").hide(100);
		}
		if(!$(e.length).parents(".nCMenu3").length>0){
			$(".nCMenu3").hide(100);
		}
		
		
		
	});
	$("#btn_alt").hide();
	$("#btn_exp").hide();	
	if (role=='fp_view') {
		$(".fp_view").hide();
	}
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var r0 = '${r_1[0][8]}';
	var r1 = '${r_1[0][7]}';
	
	if (r0=='ALL' && r1=='MISO') {
		$("#btn_modify").show();
	} else {
		$("#btn_modify").hide();
	}
	
	var y1 = '${n_1[0][0]}';
    var y2 = '${n_3}';
    var y4 = '${n_4}';
    var y5 = '${n_2}';
    if(y1 == ""){
    	$("#unit_hid2").hide();    	
    }
	if(y1 != ""){
		$("#tdheaderView").show();
	} else {
		$("#unit_hid2").hide();
		$("#tdheaderView").hide();
	}
	if(y4 != ""){		
	}
	
	if(y1 == "NIL"){
		$("#btn_p").hide();
	}
	
 	var nsl='${n_sel}';
	var nsl1=nsl.split(":");
	if(nsl.length>0){
		var nslA=nsl1[2].split("_");
		$("#fp_finYr").val(nsl1[0]);
		if ($("#item_lvl_hq").val() != '') {			
			$("#item_lvl_hq").val(nsl1[1]);
		} else {
			$("#item_lvl_hq").listindex(0);
		}			
		if (!($("#item_lvl").val() == '' || $("#item_lvl").val() == 'null' || $("#item_lvl").val() == null)) {
			$("#item_lvl").val(nsl1[2]);
		} else {
			$("#item_lvl").listindex(0);
		}
		if ($("#fp_headsumm").val() != '') {
			$("#fp_headsumm").val(nsl1[3]);
		} else {
			$("#fp_headsumm").listindex(0);
		}
		if ($("#amt_rs").val() != '') {
			$("#amt_rs").val(nsl1[5]);
		} else {
			$("#amt_rs").listindex(0);
		}
 	} 
	
	$("#nrDetlInput").change(function() {
    	var value = $(this).val().toLowerCase();
      	$("#nrTableShowDtl tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
	
	
    $("#nrInput").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#nrTable tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });      
    
    $("#nSelHqsrc").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#nSelHq tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });  
    $("#nSelUnitsrc").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#nSelUnit tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    }); 
    $("#nSelHeadsrc").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#nSelHead tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
    $("#nSelHeadsrcx").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#nSelHeadx tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
    $("#DtlFilterBH_src").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#nrTableShowDtl tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });    
  	try{
  		if(window.location.href.includes("msg=")){
  			var url = window.location.href.split("?msg")[0];
			window.location = url;
		} 	
	}
	catch (e) {
	}
	$("#item_lvl").attr('selectedIndex',0);
	
	
	$(".decimal").keyup(function(){
		var total = 0;
		$(".decimal").each(function(i,j){
			total +=Number($(j).val());
		});
		
		$("#total").val(total.toINR('','RS','INR','RS'));
	});
});
</script>