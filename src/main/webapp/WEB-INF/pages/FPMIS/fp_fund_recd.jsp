<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmn" %>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<script>
	var role = "${role}";
</script>
<body onload="setMenu();">
<% int ntotln=0; %>
<form id="fp_fund_recd" action="fp_fund_recd" method="post" class="form-horizontal fp-form" >
<div id="nMain">
	<div class="nkpageland" id="printableArea">
	   <div>		
     	<div class="containerr" align="center" id="headerView" style="display: block;color:black;">
     			<input type="hidden" id="nAltType" value="FN">  	       
	    	    <div class="card-header mms-card-header" style="min-height:60px;padding-top: 10px;">
	    	                 <b>ENTER FUNDS RECEIVED</b>                  		
	    	    </div>
	    	    <div class="card-body card-block ncard-body-bg" style="border:1px solid lightgray">
	    	      	<div class="row form-group">
		    	      	<div class="col-md-3">	
			    	      	Fund Received from		    	      
		    	      	</div>
		    	      	<div class="col-md-5">
							<p id="srcHQ" name="srcHQ" value="MOD">
							<b>Ministry of Defence</b></p>		    	      	
		    	      	</div>			    	      	   
						<div class="col-md-4">
							<div style="float:right"><strong style="color: red;">*</strong>
								Financial Year&nbsp;:&nbsp;
								<select id="fp_finYr" name="fp_finYr"  class="form-control-sm" title="Select Financial Year." onchange="javascript:CheckFinYear()">
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
					</div>
 					<div class="row form-group" style="border:1px solid lightgray;background-color: lightgray">
		    	      	<div class="col-md-3">Fund Allocated to			    	      
		    	      	</div>   
						<div class="col-md-5"><strong style="color: red;">*</strong>
						  		Dte/HQ Fmn/Unit
						</div>
						<div class="col-md-4"><strong style="color: red;">*</strong>
						  		Head
						</div>   						
					</div>						
					<div class="row form-group" >
		    	      	<div class="col-md-3">	    	      
		    	      	</div>   
						<div class="col-md-5" style="border:1px solid lightgray;">
								<table id="nSelUnit" name="nSelUnit" style="margin:10px;line-height: 25px;cellmargin:10px;font-size:14px;border:0px solid lightgray!important">
									<c:if test="${n_unt == 'NIL'}">
	    								<tr class='nrSubHeading'><td colspan='9' style='text-align:center;'>Data Not Available...</td></tr>
									</c:if>										   
	 							    <c:if test="${n_unt != 'NIL'}"> 
										<c:forEach var="item" items="${n_hq}" varStatus="num">
											<c:if test="${num.index == '0'}">
												<tr><td>&nbsp;<input type="radio" name="nSelUnitid" id="Unitid_${item[0]}_${item[1]}" onclick="showsel('a','${item[2]}');" title="Select Dte/Hq Fmn/Unit">&nbsp;${item[2]}</td>
											</c:if>
								        </c:forEach>      
									</c:if>
								</table>
						</div>
						<div class="col-md-4" style="border:1px solid lightgray;">
								<table id="nSelHead" name="nSelHead" style="margin:10px;line-height: 25px;font-size:14px;border:0px solid lightgray!important">
									<c:if test="${n_head == 'NIL'}">
	    								<tr class='nrSubHeading'><td colspan='9' style='text-align:center;'>Data Not Available...</td></tr>
									</c:if>										   
	 							    <c:if test="${n_head != 'NIL'}"> 
										<c:forEach var="item" items="${n_head}" varStatus="num">
											<c:set var="dataf" value="${fn:split(item[0],':')}"/>
											<c:set var="datafm" value="${fn:length(dataf)-1}"/>										
											<tr><td>&nbsp;<input type="radio" name="nSelHeadid" id="Headid_${item[0]}" onclick="showsel('b','${dataf[datafm]} - ${item[1]}');" title="Select Head">&nbsp;${dataf[datafm]} - ${item[1]}</td>
								        </c:forEach>
									</c:if>
								</table>
						</div>							
					</div>
					<!-- <div class="row form-group">
		    	      	<div class="col-md-3">	    	      
		    	      	</div>   
						<div class="col-md-5">
							<p id="nSelUnitidd" style="background-color: lightgray;color:blue;padding-left: 10px;">
						</div>
						<div class="col-md-4">
							<p id="nSelHeadidd" style="background-color: lightgray;color:blue;padding-left: 10px;">
						</div>						
					</div> -->
					<div class="row form-group">
		    	      	<div class="col-md-3"><strong style="color: red;">*</strong> Amount Received 		    	      
		    	      	</div>   
						<div class="col-md-9">
							Cr&nbsp;<input type="text" id="nFundexp_cr" name="nFundexp_cr" placeholder="0.00000" class="form-control-sm" style="text-align: right;text-decoration: bold;background-color: lightgray;color:blue;" readonly>
							(&#8377;)&nbsp;<input type="text" id="nFundexp" maxlength="20" name="nFundexp" placeholder="0.00" class="form-control-sm decimal" style="text-align: right;" onblur="showinCr();" onkeyup="showinCr();" onkeydown="showinCr();" title="Enter Amount">
							
						</div>						
					</div>
					<div class="row form-group">
		    	      	<div class="col-md-3">Reference / Remarks 		    	      
		    	      	</div>   
						<div class="col-md-9">
							<input type="text" id="nFundrem" name="nFundrem" placeholder="Reference Letter No and Date" class="form-control-sm char-counter" size="80" maxlength="150" onkeypress="return isAlphaNumeric(event)" title="Enter Refrence/Remarks">
						</div>						
					</div>
					<div class="row form-group">
		    	      	<div class="col-md-4">
		    	      	</div>   
						<div class="col-md-8">
							<input type="button" class="btn btn-success btn-sm nGlow" value="Receive Fund" id="btn_cl" style="background-color:blue;color:yellow;float: center;font-size:20px;" title="Click to Receive Funds" onclick="checkallot();">
						</div>						
					</div>
				</div>				
			</div>	
   </div>
  </div>
</div>
<div  class="watermarked" data-watermark="" id="divwatermark"  >
					<span id="ip"></span> 

<div class="nPopTable" id="divPrint" style="height:30vh;width:100%;overflow: auto;">

			<span style="font-size: 16px">List of Funds Received</span>
            <table id="fndStatusReport" >
            	
                 <thead>
                     <tr>
                     <th style="text-align: center; width:50px;">SNo</th>
                     <th style="text-align: center; width:200px;">Head</th>
                     <th style="text-align: center; width:200px;">Fund in Rs</th>
                     <th style="text-align: center; width:200px;">Fund in Cr</th>
                     <th style="text-align: center; width:200px;">Reference/Remarks</th>
                     <th style="text-align: center; width:100px;">Period</th>
                     </tr>                                                        
                 </thead> 
                 <tbody>
                   <% int fund_t = 0;%>
                     	<c:if test="${list.size()==0}">
					<tr>
						<td style="font-size: 15px; text-align: center; color: red;"
							colspan="5">Data Not Available</td>
					</tr>
				</c:if>
                        <c:forEach var="item" items="${list}" varStatus="num">
		          <tr>
		          	 <td style="text-align: center;">${num.index+1}</td>
					 <td title="Head Code">${item[0]} - ${item[1]}</td>
					 <td style='text-align:right' title="Fund in Rs"><script>document.write(Number('${item[2]}').toINR('','RS','INR','RS'))</script></td>
					 <td style='text-align:right' title="Fund in Cr"><script>document.write(Number('${item[2]}').toINR('','RS','INR','CR'))</script></td>
					 <c:set var="fund_t" value="${fund_t+item[2]}"/>
					 <td title="Refence/Remarks">${item[4]}</td>
					 <td style='text-align:center' title="Period">${item[3]}</td>
		          </tr>
		       </c:forEach>
		       		<tr>
						<td style="text-align: right; font-weight: bold;"></td>
						<th style="text-align: center; font-weight: bold;">Total</th>
						<td style="text-align: right; font-weight: bold;"><script>document.write(Number('${fund_t}').toINR('','RS','INR','RS'))</script></td>
						<td style="text-align: right; font-weight: bold;"><script>document.write(Number('${fund_t}').toINR('','RS','INR','CR'))</script></td>
						<td style="text-align: right; font-weight: bold;"></td> 
					</tr>
                  </tbody>

              </table>
                  </div> 
</div>
</form>

<c:url value="fp_fund_tfr_confirm?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_fund_tfr" class="fp-form" name="m1_fund_tfr" modelAttribute="m1_nomen">
      <input type="hidden" name="n1_trhead" id="n1_trhead"/>
	  <input type="hidden" name="n1_frunt" id="n1_frunt"/>
	  <input type="hidden" name="n1_blamt" id="n1_blamt"/>
	  <input type="hidden" name="n1_tramt" id="n1_tramt" class="decimal"/>
	  <input type="hidden" name="n1_tount" id="n1_tount"/>
	  <input type="hidden" name="n1_tohead" id="n1_tohead"/>
	  <input type="hidden" name="n1_trtype" id="n1_trtype"/>
	  <input type="hidden" name="n1_tryear" id="n1_tryear"  class="decimal"/>
	  <input type="hidden" name="n1_trrem" id="n1_trrem"/>
	  <input type="hidden" name="n1_trpid" id="n1_trpid"/>	
	  <input type="hidden" name="n1_trAltType" id="n1_trAltType"/>
	  <input type="hidden" name="n1_slvl" id="n1_slvl"/>
	  <input type="hidden" name="n1_lvl" id="n1_lvl"/>
	  <input type="hidden" name="n1_hdlvl" id="n1_hdlvl"/>
	  <input type="hidden" name="n1_gstval" id="n1_gstval"/>
	  <input type="hidden" name="nFlowControl" id="nFlowControl"/>
</form:form>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script>
$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
});

function showsel(a,b){
	if (a=='a') {
		$("#nSelUnitidd").text(b);
	}
	if (a=='b') {
		var c=$('[name=nSelHeadid]').attr('id').split('_');
		$("#nSelHeadidd").text(b);
	}
}

function isAlphaNumeric(evt){
	var charCode = (evt.which) ? evt.which : evt.keyCode;
		if((charCode>=48 && charCode<=57) || (charCode>=65 && charCode<=90) || (charCode>=97 && charCode<=122) || charCode==32 || charCode==46 || charCode == 45){
			return true;
		} 
	return false;
}



$(function(){
	$(".char-counter").charCounter();	
});

function CheckFinYear(){
	 var selsus= $("#fp_finYr").val();
	 $.post("getFundYrList?"+key+"="+value,{nPara:selsus}, function(j) {
			drawtregnYr(j);
	 }); 	
}

function drawtregnYr(data) {
	$("#divPrint tbody").empty();
	var s=1;
	var fnd_t = 0;
	for (var i = 0; i <data.length; i++) {
		var  row ="<tr id='tarTableTr' padding='5px;'>";
		row += "<td style='text-align: center;'>"+s+"</td>";
		row += "<td>"+data[i][0]+" - "+data[i][1]+"</td>";		
		row += "<td style='text-align:right'>"+Number(data[i][2]).toINR('','RS','INR',"RS")+"</td>";
		row += "<td style='text-align:right'>"+Number(data[i][2]).toINR('','RS','INR',"CR")+"</td>";
		row += "<td>"+data[i][4]+"</td>";
		row += "<td>"+data[i][3]+"</td>";
		row += "</tr>";
		s = s+1;
		fnd_t = fnd_t + parseInt(data[i][2]);
		$("#divPrint tbody").append(row);
	 }
	var  row ="<tr id='tarTableTr' padding='5px;font-weight: bold;'>";
	row += "<td></td>";
	row += "<td style='text-align:center;font-weight: bold;'>Total</td>";
	row += "<td style='text-align:right;font-weight: bold;'>"+Number(fnd_t).toINR('','RS','INR','RS')+"</td>";
	row += "<td style='text-align:right;font-weight: bold;'>"+Number(fnd_t).toINR('','RS','INR','CR')+"</td>";
	row += "<td></td>";
	row += "</tr>";
	$("#divPrint tbody").append(row);
	
}
function showinCr() {
	var a=$("#nFundexp").val();
	$("#nFundexp_cr").val(Number(a).toINR('','RS','INR','CR'));
}

function checkallot() {
	var nYr=$("#fp_finYr").val();
	var nrem=$("#nFundrem").val();
	var nExp=$("#nFundexp").val();
	var nExpn=parseInt(nExp);
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
	if (nExp==0) {
		alert("Amount can not be 0.");
		return false;		
	}
	var qtrhead=$('[id=srcHead]').attr('name');
	var qfrsus=$('[id=srcUnit]').attr('name');
	var qhdsumm=$('#fp_headsumm').val();
	var qblamt=0;
	var qtramt=nExp;
	var qtount=untn;
	var qtohead=hedn;
	var qtrtype="FND";
	var qtrpid=$('#selectedpid').val();
	$("#n1_trhead").val(qtrhead);
	$("#n1_frunt").val(qfrsus);
	$("#n1_blamt").val(qblamt);
	$("#n1_tramt").val(qtramt);
	$("#n1_tount").val(qtount);
	$("#n1_tohead").val(qtohead);      
	$("#n1_trtype").val(qtrtype);
	$("#n1_tryear").val(nYr);
	$("#n1_trrem").val(nrem);
	
	$("#n1_hdsumm").val(qhdsumm);
	$("#n1_trpid").val("0");
	$("#n1_trAltType").val($("#nAltType").val());
	$("#n1_slvl").val("");
	$("#n1_lvl").val("");
	$("#n1_hdlvl").val("");
	$("#nFlowControl").val("FRECD");
	$("#n1_gstval").val("0:0:0");	
	$("#m1_fund_tfr").submit();
}

function callnPop(a){
	$("#selectedid").val(a);
	var b=a.split("_");
	event.preventDefault();
	var c="#"+b[0]+"_"+b[1];
	$(c).attr('background-color','green');
	$("#nCMenu").show();
	$("#nCMenu").offset({'top':nY,'left':nX});
}
</script>