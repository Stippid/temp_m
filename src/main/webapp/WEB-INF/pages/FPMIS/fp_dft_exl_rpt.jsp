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
<style>
.nrptt thead, .nrptt tbody tr {
    display:table;
    width:100%;
    table-layout:fixed;
}
</style>
<body onload="setMenu();">
<c:set var="nsell" value="${n_sel}"/>
<c:set var="nsel" value="${fn:split(nsell,':')}"></c:set>
<form action="" method="post" enctype="multipart/form-data" class="form-horizontal" >
<div id="nMain">
	<div class="nkpageland" id="printableArea">
	   <div>		
     	<div class="container" align="center" id="headerView" style="display: block;">  
	       <div class="container" align="center">
	    	      <div class="card">
	    	           <div class="card-header mms-card-header" style="min-height:60px;padding-top: 10px;">
	    	                 <b>GENERATION OF FUND REPORT</b>            		
	    	           </div>	    	           
	    	            <div class="card-body card-block ncard-body-bg">
	    	            	<div class="col-md-12 row form-group" style="color:blue;">
	    	            		<span>Please Note: The generated EXCEL File is Protected for Editing except Fund Allocation.</span>
	    	            	</div>
	    	                 <div class="col-md-12 row form-group">
	    	                       <div class="col-md-4">	    	                       
		    	                       	<b>Financial Year&nbsp;:&nbsp;</b>
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
	    	                       <div class="col-md-4">  	                 	    	                 			               		
				               		 <b>Report of &nbsp;:&nbsp;</b>
				               		 <select id="fp_upldfor" name="fp_upldfor"  class="form-control-sm" title="Select Report Level">
				               		 		<c:forEach var="item" items="${n_rpttype}" varStatus="num">
												<option value="${item[2]}"
												<c:if test="${item[2] == nsel[1]}">
													SELECTED
												</c:if>>${item[3]}</option>
									        </c:forEach>
							     	</select>			               		 
	               		 		   </div>
	    	                       <div class="col-md-4">  	                 	    	                 			               		
				               		 	<label class=" form-control-label"><strong style="color: red;">*</strong> Amount in </label>&nbsp;&nbsp;
										<select id="amt_rs" name="amt_rs" class="form-control-sm" title="Select Amount Conversion" >
											<c:set var="amt_rss" value="${fn:split(n_sel,':')}"/>																							
												<option value="CR">Crores</option>												
												<option value="RS">Rupees</option>
										</select>			               		 
	               		 		   </div>
	    	                 </div>
	    	                 
	    	                 <div class="col-md-12  form-group">
	    	                       <div class="col-md-8" style="text-align: left;float: left;">	    	                       
		    	                       	<b>Reports&nbsp;:&nbsp;</b>
										<select id="rptLvl" name="rptLvl"  class="form-control-sm" title="Select Reports">
												<option value="DFNDALLOT" title="Generate Excel based on Last Saved Draft Fund Allocation">Draft Fund Allocation Report</option>												
												<option value="BBHDSUMM" title="Generate Excel based on Last Approved Fund Allocation">Last Approved Fund Allocation Report</option>
												<option value="BHDSUMM" title="Generate Excel of Budget Summary">Budget Head Wise Summary</option>
						                </select>
	    	                       </div>
	    	                 
	    	                <div class="col-md-4 row">  	                 	    	                 
			               		 <div class="col-md-12">
    	                            <input type="button" class="btn btn-primary btn-sm nGlow" onclick="FindBugState();" value="Generate Report" 
    	                            title="Click to Generate Report">
		               		 	</div>	 
	               		 	</div>
	    	            </div>
	    	               </div>
	    	      </div>
	  		</div>  		
		</div>		 		
    		</div>
       </div>            
</div>
		
</form>
<c:url value="fp_cr_dft_exl?${_csrf.parameterName}=${_csrf.token}" var="backUrl" />
<form:form action="${backUrl}" method="post" id="m1_Bug_status" class="fp-formm" name="m1_Bug_status" modelAttribute="filename">
      <input type="hidden" name="m1_tryear" id="m1_tryear"/>
	  <input type="hidden" name="m1_lvl" id="m1_lvl"/>
	  <input type="hidden" name="m1_rptLvl" id="m1_rptLvl"/>
	  <input type="hidden" name="rsfmt" id="rsfmt"/>
</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script>
function FindBugState() {
	var nYr=$("#fp_finYr").val();
	var rsf=$("#amt_rs").val();
	var nSmUnt=$("#fp_upldfor").val();
	var nrptlvl=$("#rptLvl").val();
	
	if ($("#fp_finYr").val() == "" || $("#fp_finYr").val() == "-1" || $("#fp_finYr").val() == null) {
		alert("Please Select the Year.");
		$("#fp_finYr").focus();
		return false;
	}
	if ($("#fp_upldfor").val() == "" || $("#fp_upldfor").val() == "-1" || $("#fp_upldfor").val() == null) {
		alert("Please Select Report of.");
		$("#fp_upldfor").focus();
		return false;
	}
	if ($("#rptLvl").val() == "" || $("#rptLvl").val() == "-1" || $("#rptLvl").val() == null) {
		alert("Please Select Aggregation Level.");
		$("#rptLvl").focus();
		return false;
	}
	$("#m1_tryear").val(nYr);
	$("#m1_lvl").val(nSmUnt);
	$("#m1_rptLvl").val(nrptlvl);
	$("#rsfmt").val(rsf);
	$("#m1_Bug_status").submit();
}
</script>