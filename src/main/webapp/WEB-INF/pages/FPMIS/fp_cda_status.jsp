<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
 <link rel="stylesheet" href="js/common/nrcss.css">
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<link rel="stylesheet" href="js/common/select2/select2.min.css">
<script src="js/common/select2/select2.min.js" type="text/javascript"></script>
<style>
	.text-right{
		text-align: right;
	}
</style>
<script>
	var roleid = '${roleid}';	
	var role = "${role}";
</script>
</script>
<body onload="setMenu();">
<form:form name="fp_cda_statusForm" id="fp_cda_statusForm" action="fp_cda_statusAction?${_csrf.parameterName}=${_csrf.token}" method="POST" class="form-horizontal fp-form" commandName="fp_cda_statusCMD"> 
      <div class="containerr" align="center">

          <div class="card">
		      <div class="card-header mms-card-header" style="min-height:60px;padding-top: 10px;">
	    	       <b>CDA FUNDS STATUS</b>            		
	    	   </div>
		       <div class="card-body card-block"> 
					<div class="row">		       	
	      				<div class="col-md-6 row form-group">
	  	                       <div class="col-md-12" style="text-align: left;float: left;">
	   	                       	<b>Financial Year&nbsp;:&nbsp;
								<select id="fp_finYr" name="fp_finYr"  class="form-control-sm" title="Select Financial Year">
									<c:forEach var="item" items="${n_finyr}" varStatus="num">
										<c:if test='${item[0] == n_cfinyr}'>
											<option value="${item[0]}" SELECTED>${item[2]}</option>
										</c:if>
										<c:if test='${item[0] != n_cfinyr}'>
											<option value="${item[0]}">${item[2]}</option>
										</c:if>
									</c:forEach>
				                </select>
	  	                       </div>
	  	                 </div>  
	  	                 <div class="col-md-6 row form-group">
	  	                 	<input type="button" class="btn btn-primary btn-sm nGlow" onclick="Search();" value="Get CDA Status" title="Click to Search Result">
	  	                 </div>
	  	            </div>
		      </div>		      
              </div>
      		</div> 
<div  class="watermarked" data-watermark="" id="divwatermark"  >
              <div id="divSerachInput" class="col-md-12">
								<div class="col-md-6">
									<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Head"  size="35" class="form-control-sm" title="Search Head">			
								</div> 
							 </div> 
              <div class="containerr nPopTable" id="divPrint" style="width:100%!important;height:calc(100vh - 300px );overflow: auto;">						     				
			                 <table id="CdaStatusReport" style="width: 100%!important;"> 
			                      <thead style="text-align: center;line-height: 25px;font-size: 1em;">
			                          <tr>
				                         <th style="text-align: center;width:30%">Head</th>
				                         <th style="text-align: center;width:10%;">Expenditure (&#8377;)</th>
				                         <th style="text-align: center;width:10%;">Fwd Date</th>
				                         <th style="text-align: center;width:10%;">Amt Fwd to CDA (&#8377;)</th>
				                         <th style="text-align: center;width:10%;">Amt Booked by CDA (&#8377;)</th>
				                         <th colspan='2' style="text-align: center;width:20%;">Status</th>
			                          </tr>                                                        
			                      </thead> 
			                      <tbody id="nrTable" style="font-size: .90em;text-decoration: none;">
				                       <c:if test="${list.size()==0}">
											<tr>
												<td style="font-size: 15px; text-align: center; color: red;"
													colspan="6">No Transaction Available</td>
											</tr>
										</c:if>
			                           <c:forEach var="item" items="${list}" varStatus="num" >
								          <tr>
											 <td style='width:30%;' title="Head - ${item[0]} - ${item[1]}">${item[0]} - ${item[1]}</td>
											 <td style='text-align:right;width:10%;' title="Expenditure Amount"><script>document.write(Number('${item[2]}').toINR('','RS','INR','RS'))</script></td>
											 <td style='text-align:center;width:10%;' title="Fwd Data">${item[5]}</td>
											 <td style='text-align:right;width:10%;' title="Amt Fwd to CDA"><script>document.write(Number('${item[3]}').toINR('','RS','INR','RS'))</script></td>
											 <td style='text-align:right;width:10%;' title="Amt Bkd by CDA"><script>document.write(Number('${item[4]}').toINR('','RS','INR','RS'))</script></td>
											 <td style="text-align: center;width:20%;" title="Status">
												 <c:if test="${roleid != '359'}">
												 	${item[6]}&nbsp;&nbsp;${item[7]}&nbsp;&nbsp;${item[8]} 
												 </c:if>
											 </td>
								          </tr>
								       </c:forEach>
			                     </tbody>
			                 </table>
			  </div> 
			  </div>
</form:form>
<div class="nPopModelContainer" id="divShow">
<div class="nPopContainer" id="divSdddhow" style="margin-top: 0rem!important;bottom-top: 0rem!important;line-height:1;width:70%;max-height:400px;position:fixed;left:25%;top:5%;border:1px solid gray;padding:0px!important;">
	<div class="nPopHeader">
		Bill Fwd to CDA	
		<span class="nPopClose" onclick="$('#divShow').hide();" title="Close Msg Window">&#10006;</span>
	</div>
	<div class="nPopBody">	
		<input type="hidden" name="exp_amt1" id="exp_amt1"/>
		<input type="hidden" name="period1" id="period1"/>
		<input type="hidden" name="tr_head_to1" id="tr_head_to1"/>											
		<div id="nMsgBoardAction" class=""><center>
			<!-- <table cellpadding="12px" id="nrTableDataHead" class="nrTableDataHead tablecontent" style="width: 95%; height: 100%; margin:10px;" border="0"> -->
			<table cellpadding="12px" id="nrTableDataHead" class="table table-hover" style="width:100%; max-height: 250px; margin:0px!important;padding:0px!important;background-color:azure;" border="1"> 
				<tr>
					<td colspan=2>
						<table id="transDtls" class="table table-hover nPopTable" style="width:98%; max-height:50px;overflow:auto;">
							<thead>
								<tr>
									<th class="text-center" width="5%">Sl No</th>
									<th class="text-center" width="15%">Fwd Date</th>
									<th class="text-left" width="55%">Fwd Remarks</th>
									<th class="text-center" width="20%">Amt Fwd to CDA (&#8377;)</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</td>
				</tr>			
				<tr><td width="40%">&nbsp;&nbsp;<b>Expenditure Amount (incl Taxes)&nbsp;:</td><td>(&#8377;)&nbsp;<input type="hidden" id="total_amt" name="total_amt" placeholder="0.00" class="decimal bold text-left readonly" readonly><label id="lbl_total_amt" class="bold"></label></td>
				<tr><td>&nbsp;&nbsp;<b>Total Amount Already Fwd</b>&nbsp;:</td><td>(&#8377;)&nbsp;<input type="hidden" id="total_fwd_amt" name="total_fwd_amt" placeholder="0.00" class="readonly bold text-left decimal" readonly><label id="lbl_total_fwd_amt" class="bold"></label></td></tr>
				<tr><td>&nbsp;&nbsp;<b><strong style="color: red;">* </strong>Amount to Fwd&nbsp;:</td><td><span id="cda_bal_amt"></span>(&#8377;)&nbsp;<input type="text" id="fwd_amt" name="fwd_amt" size="12" placeholder="0.00" class=" form-control-sm text-right decimal" title="Enter Fwd Amount"><br/>
				</td></tr>
				<tr><td>&nbsp;&nbsp;<b><strong style="color: red;">* </strong>Date of Fwd&nbsp;:</td><td><input type="date" id="fwd_date" name="fwd_date" min="1950-01-01" max="${date}" class="form-control-sm" maxlength="10" value="${today}" title="Select Date of Fwd"></td></tr>														
				<tr><td>&nbsp;&nbsp;<b><strong style="color: red;">* </strong>Reference / Letter No / Remarks&nbsp;:&nbsp;</td><td><input type="text" id="fwd_ref" name="fwd_ref" placeholder="Enter Reference / Remarks" class="form-control-sm" title="Enter Refrence/Remarks"></td>
				<tr><td>&nbsp;&nbsp;<b>Unit Code (as per CDA Office)&nbsp;:&nbsp;</td><td><input type="text" id="cda_unit_code" name="cda_unit_code" placeholder="Unit Code" class="form-control-sm" title="Enter Unit Code issued by CDA"></td>
				<tr><td>&nbsp;&nbsp;<b>CDA Office&nbsp;:&nbsp;</td><td>
					<select id="unit_cda" name="unit_cda" placeholder="CDA Office"  class="form-control-sm" title="Select Concerned CDA">
						<option value="-1">--- Select CDA Office ---</option>
						<c:forEach var="item" items="${cdaoff}" varStatus="num">
							<option value="${item[7]}">${item[6]} - ${item[1]}</option>
				        </c:forEach>
	                </select>				
					<!-- <input type="text" id="unit_cda" name="unit_cda" placeholder="CDA" class="" title="Select Concerned CDA"> -->
				</td>
				<tr><td colspan='2'><center><input type="button" class="btn btn-primary" onclick="javascript:Fwd_cda(fwdid);" value="Save Data" style="width:100px;background-color:blue;color:yellow;float: center;font-size:16px;text-align:center;" title="Click to Fwd CDA"></td>
				</tbody>
			</table>													
		</div>
	</div>
</div>
</div>
<div class="nPopModelContainer" id="divShow1">
<div class="nPopContainer" id="diddddvShow1" style="width:60%;height:600px;position:fixed;left:25%;top:10%;border:1px solid gray;padding:10px;">
	<div class="nPopHeader">
		Bill Booked by CDA	
		<span class="nPopClose" onclick="$('#divShow1').hide();" title="Close Msg Window">&#10006;</span>
	</div>
	<div class="nPopBody">
		<input type="hidden" name="id3" id="id3"/>
		<input type="hidden" name="idd3" id="idd3"/>
		<input type="hidden" name="bal_amt" id="bal_amt"/>
		<input type="hidden" name="fwd_date1" id="fwd_date1"/>											
		<div id="nMsgBoardAction"><center>
			<table cellpadding="10px" id="nrTableDataHead" class="" style="width: 98%; height: 100%; margin:0px;padding:0px;background-color:azure;" border="1">
				<tr>
					<td colspan=2>
						<table id="transDtls1" class="table table-hover nPopTable" style="width:98%;height:50px;overflow:auto;">
							<thead>
								<tr>
									<th class="text-center" width="7%"></th>
									<th class="text-center" width="15%">Fwd Date</th>
									<th width="15%">Fwd Remarks</th>
									<th class="text-center" width="15%">Booked Date</th>
									<th width="15%">Booked Remarks</th>
									<th class="text-center" width="18%">Amt Fwd(&#8377;)</th>
									<th class="text-center" width="18%">Amt Booked(&#8377;)</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</td>
				</tr>
						<tr><td>&nbsp;&nbsp;<b>Fwd Amount&nbsp;:&nbsp;</td><td>(&#8377;)&nbsp;<label id="lbl_fwd_amt1" class="bold"></label> <input type="hidden" id="fwd_amt1" name="fwd_amt1" placeholder="0.00" class="decimal readonly text-left" readonly></td>					
						<tr><td>&nbsp;&nbsp;<b><strong style="color: red;">* </strong>Booked Amount&nbsp;:&nbsp;</td><td>(&#8377;)&nbsp;<input type="text" id="bkd_amt" name="bkd_amt" placeholder="0.00" class="form-control-sm text-right decimal" title="Enter Bkd Amount"><br/><small id="bkd_bal_amt"></small></td></tr>
						<tr><td>&nbsp;&nbsp;<b><strong style="color: red;">* </strong>Booked Date</td><td><input type="date" id="bkd_date" name="bkd_date" min="1950-01-01" class="form-control-sm" max="${date}" maxlength="10" value="${date}" title="Select Bkd Date"></td></tr>
						<tr><td>&nbsp;&nbsp;<b><strong style="color: red;">* </strong>Reference&nbsp;:&nbsp;</td><td><input type="text" id="bkd_ref" name="bkd_ref" placeholder="Enter Reference" class="form-control" title="Enter Reference"></td>
						<tr><td>&nbsp;&nbsp;<b><strong style="color: red;">* </strong>Remarks&nbsp;:&nbsp;</td><td><input type="text" id="bkd_remarks" name="bkd_remarks" placeholder="Enter Remarks" class="form-control" title="Enter Remarks"></td>
						<tr>
							<td colspan='3'>
							<center><input type="button" class="btn btn-primary btn-sm nGlow" onclick="javascript:Bkd_cda(id3);" value="Save" style="background-color:blue;color:yellow;float: center;font-size:20px;" title="Click to Book CDA">
							</td>
						</tr>
				</tbody>
			</table>													
		</div>
	</div>
</div>
</div>
<div class="nPopModelContainer" id="divShow2">
<div class="nPopContainer"  style="margin-top: 0rem!important;bottom-top: 0rem!important;line-height:1.1;width:70%;min-height:100px;position:fixed;left:15%;top:10%;border:1px solid gray;padding:10px;">
	<div class="nPopHeader">
		Deletion of Expenditure	/ CDA Details
		<span class="nPopClose" onclick="$('#divShow2').hide();" title="Close Msg Window">&#10006;</span>
	</div>
	<div class="nPopBody">	
		<div id="nMsgBoardAction" class=""><center>
			<input type="hidden" name="id5" id="id5"/>
			<table cellpadding="12px" id="nrTableDataHead" class="" style="width:100%; height: 250px; margin:0px;padding:0px;background-color:azure;" border="1">
			<tr>
					<td colspan=2>
						<span style="color:red;font-size:1.3em;">Please Note: The Expenditure details shown below along with CDA Details will be <b>DELETED</b>.</span>	
					</td>
			</tr> 
				<tr>
					<td colspan=2>
						<table id="transDtlsdel" class="table table-hover nPopTable" style="width:98%; max-height:50px;overflow:auto;">
							<thead>
								<tr>
									<th class="text-center" width="25px"></th>									
									<th class="text-center" width="150px">Expenditure Details</th>
									<th class="text-center" width="50px">Type</th>
									<th class="text-center" width="150px">Expenditure Amount</th>
									<th class="text-center" width="150px">Fwd to CDA Details</th>
									<th class="text-center" width="150px">Fwd to CDA Amount</th>
									<th class="text-center" width="150px">Booked by CDA Details</th>
									<th class="text-center" width="150px">Booked by CDA Amount</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</td>
				</tr>
				<tr><td colspan='2'>&nbsp;&nbsp;<b><strong style="color: red;">* </strong>Reason for Deletion / Remarks&nbsp;:&nbsp;<input type="text" id="del_remarks" name="bkd_remarks" max-length="140" size="80" placeholder="Enter Remarks" class="form-control-sm" title="Enter a Remarks"></td>			
				<tr><td colspan='2'><center><input type="button" class="btn btn-primary btn-sm nGlow" onclick="javascript:del_exp();" value="Delete Expenditure" style="background-color:blue;color:yellow;float: center;font-size:20px;" title="Delete the selected Expenditure"></td>
				</tbody>
			</table>													
		</div>
	</div>
</div>
</div>
<c:url value="getSearchCDAStatus?${_csrf.parameterName}=${_csrf.token}" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="service1">
</form:form> 

<c:url value="fp_cda_statusAction?${_csrf.parameterName}=${_csrf.token}" var="FwdCdaUrl" />
<form:form action="${FwdCdaUrl}" method="post" id="Fwd_cdaForm" class="fp-form" name="Fwd_cdaForm" modelAttribute="fwcda1">
	<input type="hidden" name="fwdid" id="fwdid"/>
	<input type="hidden" name="fwdid2" id="fwdid2"/>  
	<input type="hidden" name="fp_finYr2" id="fp_finYr2"/>
	<input type="hidden" name="fwd_amt2" id="fwd_amt2" class="decimal"/>
	<input type="hidden" name="fwd_ref2" id="fwd_ref2"/>
	<input type="hidden" name="fwd_date2" id="fwd_date2"/>
	<input type="hidden" name="tr_head_to12" id="tr_head_to12"/>
	<input type="hidden" name="total_amt2" id="total_amt2"/>
	<input type="hidden" name="cda_unit_code2" id="cda_unit_code2"/>
	<input type="hidden" name="unit_cda2" id="unit_cda2"/>
</form:form>

<c:url value="fp_cda_bookAction?${_csrf.parameterName}=${_csrf.token}" var="BkdCdaUrl" />
<form:form action="${BkdCdaUrl}" method="post" id="Bkd_cdaForm" class="fp-form"  name="Bkd_cdaForm" modelAttribute="bkcda1">
	<input type="hidden" name="exp_id" id="exp_id"/>
	<input type="hidden" name="bkd_id1" id="bkd_id1"/> 
	<input type="hidden" name="bkd_amt1" id="bkd_amt1" class="decimal"/>
	<input type="hidden" name="bkd_ref1" id="bkd_ref1"/>
	<input type="hidden" name="bkd_remarks1" id="bkd_remarks1"/>
	<input type="hidden" name="bkd_date1" id="bkd_date1"/>
</form:form>
<c:url value="fp_exp_delAction?${_csrf.parameterName}=${_csrf.token}" var="delCdaUrl" />
<form:form action="${delCdaUrl}" method="post" id="exp_delForm" class="fp-form"  name="exp_delForm" modelAttribute="delda1">
	<input type="hidden" name="exp_id5" id="exp_id5"/>
	<input type="hidden" name="bkd_id5" id="bkd_id5"/> 
	<input type="hidden" name="del_remarks5" id="del_remarks5"/>
</form:form>
			
<Script>
function Search(){
	if ($("#fp_finYr").val() == "" || $("#fp_finYr").val() == "-1" || $("#fp_finYr").val() == null) {
		alert("Please Select Financial Year.");
		$("#fp_finYr").focus();
		return false;
	}
    $("#searchForm").submit();	 
}

function Fwd_cda(idd1){
	var total_amt = $("#total_amt").val();
	var fwd_finyr=$("#fp_finYr").val();
	var fwd_amts=$("#fwd_amt").val();
	var fwd_refs=$("#fwd_ref").val();
	var fwd_dates=$("#fwd_date").val();
	var fwd_idd1=idd1.value;
	var tr_head_to11 = $("#tr_head_to1").val();
	var exp_amt11 = $("#exp_amt1").val();
	var period11 = $("#period1").val();
	var cdauntcd=$("#cda_unit_code").val();
	var untcda=$("#unit_cda").val();
	 if (fwd_amts<=0 || fwd_amts== null || fwd_amts =='') {
			alert("Please Enter Some Amount.");
			//$("#fwd_amt").focus();
			return false;
	}  
	if ($("#fwd_date").val() == "" || $("#fwd_date").val() == "-1" || $("#fwd_date").val() == null) {
		alert("Please Select Date.");
		$("#fwd_date").focus();
		return false;
	}
	if(fwd_dates < period11){
		alert("Fwd Date is less than Expenditure Date");
		$("#fwd_dates").focus();
		return false;
	}
	if(fwd_refs.length == 0 || fwd_refs==null || fwd_refs==''){
		alert("Please enter remarks");
		$("#fwd_ref").focus();
		return false;
	} 
	if ($("#cda_unit_code").val() == "" || $("#cda_unit_code").val() == "-1" || $("#cda_unit_code").val() == null) {
		alert("Please Enter CDA Unit Code.");
		$("#cda_unit_code").focus();
		return false;
	}
	if ($("#unit_cda").val() == "" || $("#unit_cda").val() == "-1" || $("#unit_cda").val() == null) {
		alert("Please Select Concerned CDA.");
		$("#unit_cda").focus();
		return false;
	}
	$("#fp_finYr2").val(fwd_finyr);
	$("#fwd_amt2").val(fwd_amts);
	$("#fwd_ref2").val(fwd_refs);
	$("#fwd_date2").val(fwd_dates);
	$("#fwdid2").val(fwd_idd1);
	$("#cda_unit_code2").val(cdauntcd);
	$("#unit_cda2").val(untcda);
	$("#tr_head_to12").val(tr_head_to11);
	$("#total_amt2").val(Number(total_amt).toINR());	
	$("#Fwd_cdaForm").submit(); 
	
}
$(document).ready(function() {
	$("#divShow1,#divShow,#divShow2").hide();
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#CdaStatusReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	if('${fp_finYr}' != "" || '${fp_finYr}' != ""){
		$("#divPrint").show();
	}
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	} 
	
	$("#divPrint").show();
	
	var q = '${fp_finYr1}';
	if(q != ""){ 
		$("#fp_finYr").val(q);
	}
	
	$("#fwd_amt").blur(function(){
		var bal_amt = Number($("#total_amt").val()) - Number($("#total_fwd_amt").val());
		bal_amt = Number(bal_amt.toFixed(2));
		var fwd_amt = Number($(this).val())
		fwd_amt = Number(fwd_amt.toFixed(2));
		if(fwd_amt > bal_amt){
			$('#fwd_amt').val(0);
			alert("You can't exceed the expenditure amount\nBalance Amount to fwd (₹) "+Number(bal_amt).toINR('','RS','INR','RS'));
			return false;
		} 		
	});

	$("#bkd_amt").blur(function(){
		var bal_amt = Number($("#bal_amt").val());
		bal_amt = Number(bal_amt.toFixed(2));
		var bkd_amt = Number($(this).val());
		if(bkd_amt > bal_amt){
			$('#bkd_amt').val(0);
			alert("You can't exceed the bill amount\nBalance Amount to book for the selected bill (₹) "+Number(bal_amt).toINR('','RS','INR','RS'));
			//$('#bkd_amt').focus();
			return false;
		} 		
	});
	//$('#unit_cda').nselect();
	//$(".select").nselect();
	
});

function editData(expID,tr_head_to,exp_amt,total_fwd_amt){
	$("#divShow1,#divShow").hide();
	$("#fwdid").val(expID);
	$("#tr_head_to1").val(tr_head_to);
	
	exp_amt = Number(exp_amt);
	total_fwd_amt = Number(total_fwd_amt);
	var bal_amt = exp_amt - total_fwd_amt;
	
	$("#total_amt").val(exp_amt);
	$("#total_fwd_amt").val(total_fwd_amt);
	
	
	$('#cda_bal_amt').text("Bal Amt : (₹) "+bal_amt.toINR('','RS','INR','RS'));
	$("#lbl_total_fwd_amt").html(total_fwd_amt.toINR('','RS','INR','RS'));
	$("#lbl_total_amt").html(exp_amt.toINR('','RS','INR','RS'));
	
	$('#fwd_date').attr('max','${date}');
	var html="",totalFwd = 0,totalBooked = 0;
	$("#transDtls tbody").html("");
	
	$.ajax({
		type : "POST",
		url: "getCDAFwdBookingDetails?"+key+"="+value,
		data : {expID : expID},
		success : function(res) {
			$(res).each(function(i,j){
				i++;
				if(j[0]){
					html+="<tr title='Booked Amount: (₹) "+Number(j[4]).toINR('','RS','INR','RS')+"\nBooked Date: "+j[6]+"\nBooking Remarks: "+j[5]+"'><td class='text-center' width='5%'>"+i+"</td><td class='text-center' width='15%'>"+j[3]+"</td><td width='55%'>"+j[2]+"</td><td class='text-right' style='text-align:right;' width='20%'>"+Number(j[1]).toINR('','RS','INR','RS')+"</td></tr>";
					totalFwd+=Number(j[1]);
					totalBooked+=Number(j[4]);
				}
				else{
					html="<tr><td colspan='4' class='text-center'><strong>*** NO TRANSACTION RECORDED ***</td></tr>"
				}
				
			});
			html+="<tr title='Total Fwd : (₹) "+Number(totalFwd).toINR('','RS','INR','RS')+"\nTotal Booked: (₹) "+Number(totalBooked).toINR('','RS','INR','RS')+"'><td colspan='3' class='text-center'><strong>Total</strong></td><td class='text-right' style='text-align:right;'><strong>(₹) "+Number(totalFwd).toINR('','RS','INR','RS')+"</strong></td></tr>";
			$("#transDtls tbody").append(html);
		
		},
		error:function(e,d){
			alert("Error - unable to fetch data");
		},
		complete:function(a,b){		
			$("#WaitLoader,#nrWaitLoader,#Loader").hide();			
			$("#divShow").show();
		}
	});
}

function BookedData(expID){
	$("#fwd_amt1").val(0);
	$("#lbl_fwd_amt1").html("0");	
	$("#bkd_date").val("");
	$("#bkd_ref").val("");
	$("#bkd_remarks").val("");
	$("#divShow1,#divShow").hide();
	$("#transDtls1 tbody").html("");
	$("#id3").val(expID);
	
	var html="",totalFwd=0,totalBooked=0,isDisabled="";
	$.ajax({
		type : "POST",
		url: "getCDAFwdBookingDetails?"+key+"="+value,
		data : {expID : expID},
		success : function(res) {
			$(res).each(function(i,j){
				i++;
				isDisabled = Number(j[1]) == Number(j[4]) ? "disabled" : "";
				html+="<tr title='Fwd Amt: (₹) "+Number(j[1]).toINR('','RS','INR','RS')+"\nBooked Amt: (₹) "+Number(j[4]).toINR('','RS','INR','RS')+"\nFwd Date: "+j[2]+"\nFwd Remarks: "+j[3];
				html+="\nBooked Date: "+j[6]+"\nBooking Remarks: "+j[5]+"'><td class='text-center' style='font-size:20px;'><input onclick=onBillSel("+j[0]+",'"+j[3]+"',"+Number(j[1])+","+Number(j[4])+") type='radio' name='exp_bill' value="+j[0]+" "+isDisabled+"></td><td class='text-center'>"+j[3]+"</td><td>"+j[2]+"</td><td class='text-center'>";
				html+=j[6]+"</td><td>"+j[5]+"</td><td class='text-right'>"+Number(j[1]).toINR('','RS','INR','RS')+"</td><td class='text-right'>"+Number(j[4]).toINR('','RS','INR','RS')+"</td></tr>";
				
				totalFwd+=Number(j[1]);
				totalBooked+=Number(j[4]);
			});
			html+="<tr title='Total Fwd : (₹) "+Number(totalFwd).toINR('','RS','INR','RS')+"\nTotal Booked: (₹) "+Number(totalBooked).toINR('','RS','INR','RS')+"'><td colspan=5' class='text-left'><strong>Total</strong></td><td class='text-right'><strong>(₹) "+Number(totalFwd).toINR('','RS','INR','RS')+"</strong></td>";
			html+="<td class='text-right'><strong> (₹) "+Number(totalBooked).toINR('','RS','INR','RS')+"</strong></td></tr>";
			$("#transDtls1 tbody").append(html);
		},
		error:function(e,d){
			alert("Error - unable to fetch data");
		},
		complete:function(a,b){
			$("#WaitLoader,#nrWaitLoader,#Loader").hide();
			$("#divShow1").show();
		}
	});	
}

function onBillSel(a,b,c,d){
	var dt = b.split("-").reverse().join("-");
	c = Number(c);
	d = Number(d);
	var bal_amt = c - d;
	$("#idd3").val(a);
	$("#fwd_amt1").val(c);
	$("#lbl_fwd_amt1").html(c.toINR('','RS','INR','RS'));
	$("#bkd_date").attr('min',dt);
	$("#bal_amt").val(bal_amt);
	$("#bkd_amt").val("");
	$("#bkd_amt").focus();
	$("#fwd_date1").val(dt);
	$().getCurDt("#bkd_date");
	$('#bkd_bal_amt').text("Bal Amt : (₹) "+bal_amt.toINR('','RS','INR','RS'));
}

$("#clear").click(function(){
	$('#bkd_bal_amt').text("");
	$("#idd3").val("");
	$("#bal_amt").val("");
	$("#lbl_fwd_amt1").html("0");
	$().getCurDt("#bkd_date");
});

function Bkd_cda(expID){
		
	var id4=$("#id3").val();
	var idd4=$("#idd3").val();

	var bkd_amts=$("#bkd_amt").val();
	var bkd_refs=$("#bkd_ref").val();
	var bkd_remarks=$("#bkd_remarks").val();
	var bkd_dates=$("#bkd_date").val();
	var fwd_dates=$("#fwd_date1").val();
	
	if (bkd_amts <= 0 || bkd_amts== null || bkd_amts =='' ) {
			alert("Please Enter Booked Amount");
			//$("#bkd_amt").focus();
			return false;
	}
	if(bkd_dates < fwd_dates){
		$("#bkd_date").focus();
		alert("Booked Date is less than Fwd Date");
		return false;
	}
	if(bkd_refs.length == 0 || bkd_refs==null || bkd_refs==''){
		$("#bkd_ref").focus();
		alert("Please enter booking reference");
		return false;
	} 
	if(bkd_remarks.length == 0 || bkd_remarks==null || bkd_remarks==''){
		$("#bkd_remarks").focus();
		alert("Please enter remarks");
		return false;
	} 	
	$("#bkd_amt1").val(Number(bkd_amts));
	$("#bkd_ref1").val(bkd_refs);
	$("#bkd_remarks1").val(bkd_remarks);
	$("#bkd_date1").val(bkd_dates);
	$("#exp_id").val(Number(id4));
	$("#bkd_id1").val(Number(idd4));
	$("#Bkd_cdaForm").submit(); 
}
function delData(expID,tr_head_to,exp_amt,total_fwd_amt){
	$("#divShow1,#divShow,#divShow2").hide();
	$("#fwdid").val(expID);
	var html;
	var dpl;
	$.ajax({
		type : "POST",
		url: "getexpBookingDetails?"+key+"="+value,
		data : {expID : expID},
		success : function(res) {
			$(res).each(function(i,j){
				i++;				
				console.log(i,j);
				$("#transDtlsdel tbody").text("");
				if(j[0]){
					html+="<tr>";
					if (dpl!=j[0]) {
						/* html+="<td style='font-size:1.5em;'><input type='radio' name='exp_bill' value="+j[0]+"></td>"; */
						//html+="<td style='font-size:1.5em;'>1.</td>";
						html+="<td>"+i+"</td>";						
						html+="<td>"+j[4]+"<BR>"+nFormatDate(j[3],'','dd-mmm-yyyy')+"</td>";
						html+="<td>"+j[13]+"</td>";
						html+="<td style='text-align:right;'>"+Number(parseInt(j[2])).toINR('','RS','INR','RS')+"</td>";
						dpl=j[0];
					} else {
						html+="<td></td><td></td><td></td>";
						html+="<td></td>";
					}					
					html+="<td>"+j[6]+"<BR>"+nFormatDate(j[7],'','dd-mmm-yyyy')+"<td style='text-align:right;'>"+Number(j[5]).toINR('','RS','INR','RS')+"</td>";
					html+="<td>"+j[9]+"<BR>"+nFormatDate(j[10],'','dd-mmm-yyyy')+"<td style='text-align:right;'>"+Number(j[8]).toINR('','RS','INR','RS')+"</td></tr>";
				}
				else{
					html="<tr><td colspan='6' class='text-center'><strong>*** NO TRANSACTION RECORDED ***</td></tr>"
				}				
			});
			$("#transDtlsdel tbody").append(html);
			$("#id5").val(expID);
		},
		error:function(e,d){
			alert("Error - unable to fetch data");
		},
		complete:function(a,b){		
			$("#WaitLoader,#nrWaitLoader,#Loader").hide();			
			$("#divShow2").show();
		}
	});
}

function del_exp(){
	var id5=$("#id5").val();
	var del_remarks=$("#del_remarks").val();
	if(id5.length == 0 || id5==null || id5==''){
		$("#id5").focus();
		alert("Expenditure Not Found. Refresh Data");
		return false;
	}
	if(del_remarks.length == 0 || del_remarks==null || del_remarks==''){
		$("#del_remarks").focus();
		alert("Please enter a remarks");
		return false;
	} 	
	$("#del_remarks5").val(del_remarks);
	$("#exp_id5").val(id5);
	$("#bkd_id5").val(id5);
	$("#exp_delForm").submit(); 
}
</script>