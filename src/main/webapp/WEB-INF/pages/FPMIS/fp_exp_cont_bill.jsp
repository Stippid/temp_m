<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">
<link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/select2/select2.min.css">
<script src="js/common/select2/select2.min.js" type="text/javascript"></script>

<style type="text/css">
.nPopTable tr{
	height:20px;
}

.blink {
  animation: blinker 1.6s step-start infinite;
  color:red;
  text-shadow:5px red;
}

 @keyframes blinker {
  50% {
    opacity: 0;
  }
} 
</style>
<script>
var role = '${role}';
var roleid='${roleid}';
var sus = '${roleSusNo}';
var scrid='${scr_lvl}';
</script>
<style>

</style>
<style>
.nRibbinShape {
	position:relative;
	margin:0px 0px!important;
	/* background:#E3E4FA; */
	background:smokewhite;
	border-radius:10px;
	color:blue;
	text-align:center;
	text-indent: 0.1em;
	font-size:22px;
	display:inline-table;
	margin:10px;
	padding:1px;
	float:center;
	align-self:center;
	text-align:left;
	border:1px solid blue!important;
	min-height:110px;
}

.nRibbinHead {
	background:blue;
	color:white;
	font-size:1.8vw;
	text-align:center;
	padding:0px 5px;
	position: relative!important;
    width:110%;
    height:50px;
    left:-10%;
    line-height:50px;
}

.nRibbinData {
	font-size: 1.2vw;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	/* box-shadow: inset 0.5px 0.5px lightblue, 0 0px lightblue;
	-webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
	box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6); */
}

.nRibbinHeadPre {
	border-radius: 50%;
	border:4px solid lightgray;
	background: whitesmoke;
	color:blue;
	padding:1px 7px;	
	text-align: center;
	align-content: center;
	float:right;
	height: inherit;
	font-size:1.5vw;
	font-weight: bold;
	vertical-align: middle;
	top:-35px!important;
	margin-right:15px;
}

.nRibbinHeadPre:hover {
	border:4px solid red;
	background: yellow;
	color:blue;
}

.nRibbinData2 {
	padding-top:20px;
	font-size: .9vw!important;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	color:red;
	/* box-shadow: inset 0.5px 0.5px lightblue, 0 0px lightblue;
	-webkit-box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
	box-shadow:inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6); */
}

.nRibbinDataDiv {
	width:90%;
	margin-left:5%;
	float:center;
	color:#800517;
	height:calc(100% - 40px);
	font-weight: bold;
	box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
}

.nRibbinTitle {
	background:whitesmoke;
	display:block;
	color:blue;
	font-size:1.8vw;
	padding:0px 5px;
	position: relative!important;
    top: -20px;    
}</style>
<body onload="nGetAlert();" >
<div class="content mt-3" style="display:inline-flex;height:100%">
	<c:set var="dataf" value="fpTopDashbd"/>	
	<c:set var="nfplay" value="Y"/>
	<c:set var="panelstyle" value="width:calc(100vw - 200px);height:calc(0vh);margin-left:0px;"/>
	<div style="${panelstyle};background-color: azure;display:inline-block;margin-top:10px;border-radius:10px;border:1px solid lightgray;"><center>
				<div class="nPopHeader" style="width:100%!important;text-align: left;">&nbsp;&nbsp;
					Expenditures Status
				</div>
				<input type="hidden" name="imps_nControl" id="imps_nControl" value="${imps_nControl}"/>
				<input type="hidden" name="selid" id="selid" value="0"/>
				<div class="card-body card-block ncard-body-bg">
	   	            	<div class="row">
	    	                 <div class="col-md-12">
	    	                       <div class="col-md-3" style="text-align: left;float: left;">	    	                       
		    	                       	<label class=" form-control-label">Branch&nbsp;:&nbsp;</label>&nbsp;&nbsp;
										<select id="sl_br" name="sl_br"  class="form-control-sm select2" title="Select Financial Year".>
											<c:if test="${scr_lvl=='0'}">
												<option value="-1">---- Select a Branch ----</option>
											</c:if>
											<c:if test="${scr_lvl=='2'}">
												<option value="XNR">---- ALL Branchs ----</option>
											</c:if>											
											<c:forEach var="item" items="${brlist}" varStatus="num">												
												<option value="${item['br_code']}"
												<c:if test="${item['br_code'] == nSel[0]}">
													SELECTED
												</c:if>
												>${item['branch']}</option>
									        </c:forEach>
						                </select>
	    	                       </div>
	    	                       <div class="col-md-4" style="text-align: left;float: left;">	    	                       
		    	                       	<label class=" form-control-label">Dte&nbsp;:&nbsp;</label>&nbsp;&nbsp;
										<select id="sl_dte" name="sl_dte"  class="form-control-sm select2" title="Select Financial Year".>
											<option value="XNR">---- All Dtes ----</option>
											<c:forEach var="item" items="${dtelist}" varStatus="num">												
												<option id="DTE_${item['dte_code']}" value="${item['dte_code']}"
												<c:if test="${item['dte_code'] == nSel[1]}">
													SELECTED
												</c:if>
												>${item['dte']}</option>
									        </c:forEach>
						                </select>
	    	                       </div>
	    	                       	<c:if test="${scr_lvl=='0'}">
				    	                <div class="col-md-3">
										     	<label class=" form-control-label">&nbsp;&nbsp; Status</label>&nbsp;&nbsp;
												<select id="sl_state" name="sl_state" class="form-control-sm  select2" title="Select a Status">
														<option value="XNR">---- All Status ----</option>																							
														<c:forEach var="item" items="${status}" varStatus="num">												
														<option id="stat_${item['codevalue']}" value="${item['codevalue']}"
														<c:if test="${item['codevalue'] == nSel[2]}">
															SELECTED
														</c:if>
														>${item['label']}</option>
											        </c:forEach>
												</select>
										</div>
									</c:if>
								<div class="col-md-2">
									&nbsp;&nbsp; 
									<input id="refbtn" type="button" class="btn btn-primary btn-sm  nGlow" onclick="showrpt();" value="Refresh Data" 
	   	                            title="Click to Refresh DAta">
		               		 	</div>
		               		 	<div class="col-md-1">
									&nbsp;&nbsp; 
									<input id="refbtn" type="button" class="btn btn-primary btn-sm  nGlow" onclick="newbill();" value="New Contingent Bill" 
	   	                            title="Click to Create New Contingent Bill">
		               		 	</div>	 			               		 	
																
							</div>								
	    	             </div>		    	             
	    	      </div>								
					<div class="nPopContainer nRibbinShape" id="nPopContainerDetl" style="width:calc(100vw - 200px);height:100%;position: relative;border:2px solid gray;margin-top:10px!important">
							<div id="divSerachInput" class="row" style="color:blue;text-decoration: bold;width:98%">
									<div class="col-md-12">
										<div class="col-md-3">
											<input id="searchInput1" type="text" style="margin-left:20px;text-align:left;font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search in Listed Data..."  size="25" class="form-control-sm" autocomplete="off">
										</div>
										<div class="col-md-9">
											<span class="blink" style="width:100%;font-size:1.2vw;">Double click anywhere on the row to Find Details / Action.</span>
										</div>
									</div>
							</div>        					
						<div class="nPopTable" id="nrTableDataDiv" style="width:calc(100vw - 200px);overflow:auto;"> 						  
						   <table border="1"  id="SearchReport" style="table-layout:fixed;">
	                       		<thead style="text-align: center;line-height: 25px;font-size: 1vw;">
									<tr>
										<th style='text-align:center;width:50px'>SNo</th>
										<c:if test="${scr_lvl=='2'}">											
											<th style='text-align:center;width:100px'>Form No</th>
											<th style='width:150px;'>STATUS</th>
										</c:if>																																				
										<th style='width:150px'>Branch</th>
										<th style='width:150px'>Dte</th>
										<th style='width:150px'>Auth Post</th>										
										<th style='width:120px'>SLAC No</th>
										<th style='width:200px'>Rank / Designation Hold</th>
										<th style='width:200px'>Name</th>										
										<th style='text-align:center;width:80px'>Emp No</th>										
										<th style='text-align:center;width:100px'>Card</th>
										<th style='text-align:center;width:80px'>Action</th>
										<c:if test="${scr_lvl=='0'}">
											<th style='text-align:center;width:100px'>Form No</th>
											<th style='width:150px;'>STATUS</th>
										</c:if>										
										<th style='width:110px'>Old SLAC No</th>
									</tr>
								</thead>
	  							<!-- <tbody id="nrTableBanDtl" style="font-size: .90vw;text-decoration: none;color:blue">
	  							
	  							
	  							</tbody> -->	  							
	  							<tbody id="nrTableBanDtl">
			                       	<c:if test="${statuslist.size()==0}">
										<tr style="font-size: 1.0vw">
											<td style="font-size: 15px; text-align: center; color: red;"
												colspan="5">Data Not Available</td>
										</tr>
									</c:if>
								   <c:set var="lineclr" value="white"/>
		                           <c:forEach var="item" items="${statuslist}" varStatus="num">
		                           	  <c:set var="slaclr" value="${fn:split(item['to_slac_no'],'-')}"/>		                           	  
							          <tr class="SBG_${item['id']}" id="ARC_${item['id']}" style="font-size: 0.8em;">
										 <td style="text-align: center;">${num.index+1}</td>
										 <%-- <td id="thAction1" style="text-align: center;" title="Click to Edit">${item[26]}</td> --%>
										 <c:if test="${scr_lvl=='2'}">
										 	<td >${item['form_no']}</td>
										 	<td >${item['form_lvl_n']} - ${item['status_n']}</td>										 
										 </c:if>										 
										 <td >${item['branch']}</td>
										 <td >${item['dte']}</td>
										 <td >${item['post']}</td>
										 <td class='slac_${slaclr[0]}'>${item['to_slac_no']}</td>
										 <td class='slac_${slaclr[0]}'>${item['design']}</td>
										 <td class='slac_${slaclr[0]}'>${item['name']}</td>
										 <td class='slac_${slaclr[0]}'>${item['emp_no']}</td>
										 <td >${item['form_type_n']}</td>
										 <td >${item['form_action']}</td>
										 <c:if test="${scr_lvl=='0'}">
										 	<td >${item['form_no']}</td>
										 	<td >${item['form_lvl_n']} - ${item['status_n']}</td>
										 </c:if>
										 <td >${item['from_slac_no']}</td>										
							          </tr>
							       </c:forEach>
		                     </tbody>
	  							
	  							
	  						</table>
	  					</div>
					</div>
	<div class="nPopModelContainer" id="nPopContainerview" style="display1:none;">		
		<div class="nPopCContainer" style="height:85vh;width:94vw;">			
			<input type="hidden" name="imps_id" id="imps_id"/>
			<input type="hidden" name="imps_pno" id="imps_pno"/>
			<input type="hidden" name="imps_tban" id="imps_tban"/>
			<input type="hidden" name="scrlvl" id="scrlvl"/>
			<div  id="imps_view_table_header" class="nPopHeader" style="text-align: center!important">
					<span id="nBillHeader">NEW CONTINGENT BILL</span>					
			</div>
			<div class="nPopTable" style="height:calc(100% - 100px);width:100%;background-color:SeaShell!important;color:navy!important;">
				<div style="height:100%;width:98%">
					<div class="row col-md-12">
    	           		<div class="col-md-2" style="text-align: left;float: left;">
    	           			Code Head&nbsp;:&nbsp;
    	           		</div>
    	           		<div class="col-md-10" style="text-align: left;float: left;">
							<select id="cBillHead" name="cBillHead"  class="form-control select2" title="Select Financial Year".>
								<option value="-1">---- Select a Code Head ----</option>
								<c:forEach var="item" items="${nHeads}" varStatus="num">												
									<option value="${item[0]}"
									<c:if test="${item[0] == nSel[0]}">
										SELECTED
									</c:if>
									>${item[0]} - ${item[1]}</option>
						        </c:forEach>
			                </select>	    	           		
	    	           		</div>	    	           
	    	      	</div>
	    	      	<div class="row col-md-12" style="height:100px;">
						<table border="1" style="table-layout:fixed;">
	                       		<thead style="text-align: center;line-height: 25px;font-size: 1.2vw;">
									<tr><th style='text-align:center;width:30px'></th>
										<th style='width:150px'>Allottee (HLBH)</th>
										<th style='width:250px'>Head</th>
										<th style='width:150px'>Allotment</th>										
									</tr>
								</thead>
	  							<tbody id="cFundList" style="font-size:1.2vw;text-decoration: none;color:blue">
	  							</tbody>	  							
	  					</table>    	           			    	           
	    	      	</div>	
				</div>			
			</div>
			<div  class="card-footer" style="text-align: center!important">
				<!-- <button class="nBtn nGlow" style="float:left;">Approve ARC</button>&nbsp;&nbsp;
				<button class="nBtn nGlow" style="float:left;">Reject ARC</button> -->			
				<button class="nPopModelClose nBtn nGlow" style="float:right;">Close</button>
			</div>
		</div>	
	</div>
	<div class="nPopCContainer" id="nPrintLetter" style="width:22cm;height:85%;display:none;z-index:499;background-color: whitesmoke;border: 7px solid green !important">
		<c:set var="ltropt" value="${DtlFilterOpt}" />
		<div class="nPopHeader">
			Contingent Bill Print 	
			<span class="nPopClose" onclick="$('#nPrintLetter').hide();" title="Close Msg Window">&#10006;</span>
		</div>
		<div class="nPopTablee" id="nPrintLetterDiv" style="min-height:70%;max-height:91%;height:29.7cm;width:21cm;overflow: auto;padding: 15px;">
		<style> .nBox {	border:1px solid black;	padding:7px;} </style>
			<div class="nPopBodyy">
				<div class="row form-group">
					<div class="col-md-12">					
						<table border="0"style="width:100%;border:0px solid gray;line-height: 1.2;table-layout: fixed;border-collapse: collapse;padding-bottom:10px;">
								<tr id="nPrintLetterDiv_tb" >
							</table>
						</div>
				</div>
			</div>
			<div style="align-self:left;">
					<input type="button" class="btn btn-success btn-sm" value="Export to Word" style="background-color: purple;" data-toggle="tooltip" title="Export Letter to Word" onclick="exportPrintLetter('#nPrintLetterDiv');">
					<input type="button" class="btn btn-primary btn-sm" value="Print / Save Pdf" onclick="printDiv('nPrintLetterDiv')">
			</div>						
		</div>
	</div>
<c:if test="${scr_lvl=='0'}">					
	<c:url value="dhq_rac_status?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
</c:if>
<c:if test="${scr_lvl=='2'}">					
	<c:url value="dhq_action_board?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
</c:if>	
<form:form action="${backUrl}" method="post" id="rac_status" name="rac_status" modelAttribute="m1_nomen">
      <input type="hidden" name="m1_br" id="m1_br"/>
	  <input type="hidden" name="m1_dte" id="m1_dte"/>
	  <input type="hidden" name="m1_state" id="m1_state"/>
</form:form>

<c:url value="DownloadAdvisory?${_csrf.parameterName}=${_csrf.token}" var="url" />
<form:form action="${url}" method="POST" id="downloadForm"
	name="downloadForm" modelAttribute="download">
	<input type="hidden" name="file_id" id="file_id" />
</form:form>

<style>	
	.spedoBox {
		height:35px;
		text-overflow:none;
		width:100%;
		text-align: center;
		font-size:1.6vw;
		color:blue;
		padding:0px;
	}	
	.ddbfndalt:hover, .ddbaltexp:hover, .ddbexpbkd:hover {
		border: 1px solid red!important;
		border-radius:7px;
		cursor:pointer;
	}	
	.ntblHt1 {
		height:37vh;
	}
	.ntblHt0 {
		height:47vh;
	}	
	.ntblCt0 {
		margin-top:5px!important;
	}
	.ntblCt1 {
		margin-top:75px!important;
	}	
	</style>
<script>	
	function showrpt() {
		var a=$("#sl_br").val();
		var b=$("#sl_dte").val();
		var c=$("#sl_state").val();
		$("#m1_br").val(a);
		$("#m1_dte").val(b);
		$("#m1_state").val(c);
		$("#rac_status").submit();
	}
	$(document).ready(function() {
		$(".select2").select2();		
		var scrid='${scr_lvl}'
		var scrlvl="";
		$("[id*=nbox]").on("click",function(){
			var a=$(this).attr("id").replaceAll("n","");
			var b=$(this).attr("id").replaceAll("nbox_","");
			$("#imps_nControl").val(b);
			$("#nPopContainerDetl").hide();
			$("#"+a).toggle();
			getnewimps(b,"SC");
		});
		$("#cBillHead").on("change",function(){			
			var a=$(this).val();
			var s = '${roleSusNo}';
			//alert(a);
			getFunds(a,s);
			
			
			
		});
		$("[id*=ARC_]").on("dblclick",function(){
			var a=$(this).attr("id").replaceAll("ARC_","");
			//alert(a);								
			$("#selid").val(a);
			$("#imps_id").val(a);
			getimpsdetlView(a,0);
			$("#nPopContainerview").show();
			/*var b=$(this).attr("id").replaceAll("nbox_","");
			$("#imps_nControl").val(b);
			$("#nPopContainerDetl").hide();
			$("#"+a).toggle();
			getnewimps(b,"SC"); */
			
		});
		
		if ('${nSel[0]}'!='XNR') {
			var a='${nSel[0]}';			
			$("[id*=DTE_]").hide();
			$("[id*=DTE_"+a+"]").show();
		}
		
		
		$("#searchInput1").on("keyup", function() {
			var value = $(this).val().toLowerCase();
			$("#nrTableBanDtl tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
			});
		});
		$(".nPopModelClose").on("click", function() {
			$(".nPopModelContainer").hide();
		});		
		$("#finalappBtn").on("click", function() {
			$("#m1_imps_id").val($("#imps_id").val());
			$("#m1_imps_pno").val($("#imps_pno").val());
			$("#m1_imps_nControl").val($("#imps_nControl").val());
			$("#m1_scr_lvl").val("1");
			$("#m1_banType").val($("#imps_tban").val());
			$("#m1_dvnote").val("");
			$("#m1_dvnotedt").val("");
			$("#m1_imps_conf").submit();
		});
		$("#submitappBtn").on("click", function() {
			var a=true;
			var b="";
			if($("#type_of_ban").val() == "-1"){
				b += "\n-Type of Ban is not Selected.";
				$("#type_of_ban").focus();
				a=false;	
			}
			if($("#conf_ban_note").val().indexOf("_")>0){
				b += "\n-Ban Confirmation Note Not Entered.";
				$("#conf_ban_note").focus();
				a=false;	
			}
			if($("#conf_ban_date").val() == ""){
				b += "\n-Ban Confirmation Date Not Selected.";
				$("#conf_ban_date").focus();
				a=false;				
			}
			if (a) {
				$("#m1_imps_id").val($("#imps_id").val());
				$("#m1_imps_pno").val($("#imps_pno").val());
				$("#m1_imps_nControl").val($("#imps_nControl").val());			
				$("#m1_scr_lvl").val("0");
				$("#m1_banType").val($("#type_of_ban").val());
				$("#m1_dvnote").val($("#conf_ban_note").val());
				$("#m1_dvnotedt").val($("#conf_ban_date").val());
				$("#m1_imps_conf").submit();
			} else {
				$().nAlert("Flowing Error Found:\n"+b);				
				//alert("Flowing Error Found:\n"+b);
				return false;
			}
		});		
		//nCallPrev();
		//alert(sus);
		//nAutoexec(sus);
	});
	
	function dwnld(a){
		var a=$(".downloadAdvisory").data("adv-id");
		$("#file_id").val(a);		
		$("#downloadForm").submit();
	}

	function getFunds(a,s) {
		//alert(a+","+s);
		var rsfmd="CR";
		$.post("getExpFunds?"+key+"="+value, {chead:a,csus:s}, function(j) {
	 	}).done(function(j) {
	 		//alert(j);
	 		var itxt="";
			if (j[0]!='NIL') {
		 		for (var i=0;i<j.length;i++){
	 				var clu='getnewimpsdetl(\"'+j[i].fr_sus_no+'\",\"'+j[i].tr_head_to+'")';
	 				itxt+="<tr>";
	 				itxt+="<td><input id='fndcode_"+j[i].fr_sus_no+":"+j[i].tr_head_to+"' type='radio' value=''</td>";
	 				itxt+="<td>"+j[i].fr_sus_name+"</td>";
	 				itxt+="<td>"+j[i].head_desc+"</td>";
	 				itxt+="<td style='text-align:right;padding-right:10px;'>"+Number(j[i].fndamt).toINR('','RS','INR',rsfmd)+" &nbsp;"+rsfmd+"</td>";
		 		}
		 		if (itxt!="") {
		 			$("#cFundList").html(itxt);	
		 		} else {
		 			$("#cFundList").html("No Data Found");
		 		}
			}
	 	}).fail(function(xhr, textStatus, errorThrown) { });
		$('#nPopContainerMsg').show();
	}	
	
	function getimpsdetlView(c1,c2) {
		var nHed="ID:"+c1;
		var scrlvl='${scr_lvl}';
		var aldata="N";
		//alert(nHed);
		
		$.post("nGetCardViewData?"+key+"="+value, {nPara:nHed}, function(j) {
	 	}).done(function(j) {
	 		//alert(j);
			if (j[0] !='NIL') {
				var b=j[0].split(":");
				$("#imps_id").val(b[0]);
				$("#imps_pno").val(b[1]);
				$("#imps_tban").val(j[3]);	
				$("#type_of_ban").val(j[3]);
				if(!(j[4]=="" || j[4]==null || j[4]=="null")) {
					aldata="Y";
					$("#conf_ban_note").val(j[4]);
					$("#conf_ban_date").val(j[5].substring(0,10));
					if (b[2]=='4') {
						$("#type_of_ban").attr("readonly","readonly");
						$("#conf_ban_note").attr("readonly","readonly");						
						$("#conf_ban_date").attr("readonly","readonly");
					}
				} else {
					aldata="N";
				}
	 			$("#imps_view_table_header ").html(j[1]);
		 		if (j[1]!="") {
		 			//alert(b[2]);
		 			$("#nPopContainerview").show();		 			
		 			$("#imps_view_table tbody ").html(j[2]);
		 			if (b[2]=='3') {
						$("#submitappBtn").hide();
						$("#submitappBtn1").hide();
						$("#finalappBtn").show();	
		 			} else if (b[2]=='2') {
		 				$("#finalappBtn").hide();
						$("#submitappBtn").show();
						$("#submitappBtn1").show();
		 			} else {
		 				$("#finalappBtn").hide();
						$("#submitappBtn").hide();
						$("#submitappBtn1").hide();
		 			}		 			
		 		} else {
		 			$("#ndb1_b"+c1).html("No Data Found");
		 		}
			}
	 	}).fail(function(xhr, textStatus, errorThrown) { });	 
	}	
	
	function getletter(c1,c2) {
		var nHed=c1+":"+c2+":DL";
		var scrlvl='${scr_lvl}';
		var aldata="N";
		//alert(nHed);		
		$.post("nGetBanPrintData?"+key+"="+value, {nPara:nHed}, function(j) {
	 	}).done(function(j) {
	 		//alert(j);
			if (j[0] !='NIL') {
				var b=j[0].split(":");
				$("#imps_id").val(b[0]);
				$("#imps_pno").val(b[1]);
				$("#imps_tban").val(j[3]);	
				$("#type_of_ban").val(j[3]);
				if(!(j[4]=="" || j[4]==null || j[4]=="null")) {
					aldata="Y";
					$("#conf_ban_note").val(j[4]);
					$("#conf_ban_date").val(j[5]);
				} else {
					aldata="N";
				}
		 		if (j[1]!="") {
		 			$("#nPrintLetter").show();		 			
		 			$("#nPrintLetterDiv_tb").html(j[2]);	 			
		 		} else {
		 			$("#nPrintLetterDiv_tb").html("No Data Found");
		 		}
			}
	 	}).fail(function(xhr, textStatus, errorThrown) { });	 
	}	
	
	function exportPrintLetter(b){
		$().tbtoWord(b);
		b.preventDefault();
	}
</script>
 
 