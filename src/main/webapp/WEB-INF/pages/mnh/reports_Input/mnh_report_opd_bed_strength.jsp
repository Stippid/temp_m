<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
 
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet"> 

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

    <link rel="stylesheet" href="js/cue/cueWatermark.css">
	<script src="js/cue/cueWatermark.js"></script>
	<script src="js/cue/printAllPages.js" type="text/javascript"></script>		

<script>
var username="${username}";
</script>


<%
	
   String nPara=request.getParameter("Para");
    
%>

<form:form action="" id="" method="post" class="form-horizontal" commandName="">
 
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
                    <% if (nPara.equalsIgnoreCase("R1")) { %>
			             <h5>SEARCH OPD DATA</h5> 
			        <% } %>
			        <% if (nPara.equalsIgnoreCase("R2")) { %>
			             <h5>SEARCH BED SERVING DATA</h5>
			        <% } %>
			        <% if (nPara.equalsIgnoreCase("R3")) { %>
			             <h5>SEARCH BED EX-SERVICEMEN DATA</h5> 
			        <% } %>
	                <% if (nPara.equalsIgnoreCase("R4")) { %>
			            <h5>QUTARLY STRENTH REPORT</h5>
			        <% } %>
	          </div> 
	          
	          
	
            <div class="card-body card-block">
             <div class="row">        					
  					<div class="col-md-12" >
  					<div class="col-md-8">
							<div class="row form-group">
		                 <div class="col-md-3" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Command</label>
	               		 </div>	               		 
	               		 <div class="col-md-9">
	               		      <input type="hidden" id="para_val" name="para_val">	
	                  		  <select name="level_c" id="level_c" class="form-control-sm form-control">
	               			       <%--   <c:if test="${r_1[0][1] != 'COMMAND'}">
                                             <option value="ALL">-- All Command --</option>
                                     </c:if>
                                     <c:if test="${not empty ml_1[0]}">
                                             <c:set var="data" value="${ml_1[0]}" />
                                             <c:set var="datap" value="${fn:split(data,',')}" />
                                             <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
                                                     <c:set var="dataf" value="${fn:split(datap[j],':')}" />
                                                     <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
                                             </c:forEach>
                                     </c:if> --%>
                                     
                                          <option value="ALL">-- Select All --</option>
		                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
						      </select>
	               		 </div>	 
</div>
</div>
	               	
	               	<div class="col-md-4">
							<div class="row form-group">	 
	               		 <div class="col-md-4" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Quarter</label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
	             			    <select name="qtr" id="qtr"  class="form-control-sm form-control">
               					      <option value="-1">--Select--</option>
								<c:forEach var="item" items="${ml_2}" varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
					                </select>
	               		 </div>
	               		</div>
	               		</div> 
		            </div>  
		            
		        <div class="col-md-12">  
		        	<div class="col-md-6">
							<div class="row form-group">    		 
	               		 <div class="col-md-4" style="text-align: left;">
	                 		 <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Year</label>
	               		 </div>               		 
	               		 <div class="col-md-8">
	               		   <input type="text" id="year" name="year" class="form-control-sm form-control" onchange="Checkyear(this)" autocomplete="off" maxlength="4" 
	               		       onkeypress="return isNumberPointKey(event);">
	               		 </div>
	               		 </div>
	               		 </div>
		        </div>    
		           
		      </div>		      
	</div>
		      <div class="card-footer" align="center">
		       <% if (nPara.equalsIgnoreCase("R1")) { %>
			              <a href="mnh_opd_report" class="btn btn-primary btn-sm" type="reset" > Clear </a>    
			        <% } %>
			     <% if (nPara.equalsIgnoreCase("R2")) { %>
			              <a href="mnh_bed_serving"  class="btn btn-primary btn-sm" type="reset" > Clear </a>    
			        <% } %>
			         <% if (nPara.equalsIgnoreCase("R3")) { %>
			              <a href="mnh_bed_exserve"  class="btn btn-primary btn-sm" type="reset" > Clear </a>    
			        <% } %>
		         <i class="fa fa-search"></i> <input type="button" id="btn_serach"  class="btn btn-success btn-sm"value="Search" onclick="return isvalidData()" />   
            		<button type="button" id="btn_p" class="btn btn-primary btn-sm btn_report" onclick="printDiv();">Print</button>	 
            
            
              </div>
               </div>
               </div>
  
           <div class="nkpageland" id="printableArea">
              <div  id="divPrint" style="display: none;">
               
                   <div id="divShow" style="display: block;">
                   
                   <div id="divShow1" align="center" style="display: none;"></div>
				       				
				<div id="divSerachInput" class="col-md-12">
					<div class="col-md-6">
						<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Word"  size="35" class="form-control">			
				</div> 
			</div> 
				
				<div class="col-md-12"  id="divPrint" style="display: block;" >
				
		      	<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>		
					  <table id="SearchReport" style="width:100%;" border="1"  class="table no-margin table-striped  table-hover  table-bordered report_print">
				                  <thead >
				                         <% if (nPara.equalsIgnoreCase("R1")) { %>
								             <tr style="text-align:center">
								                <th  rowspan="2" width='15%' >Hospital Name</th> 
									            <th colspan="2">OFFICERS</th>
											    <th colspan="2">JCOs/OR</th>
											    <th colspan="2">Ex-Servicemen</th>
											    <th colspan="2">Para Mil Pers</th>
											    <th colspan="2">Civilians</th>
											    <th rowspan="2">Total All Categories</th>
											    <th rowspan="2">Remarks</th> 
								             </tr>
								             <tr style="text-align:center">
												<th>Self</th>
												<th>Families</th>
												<th>Self</th>
												<th>Families</th>
												<th>Self</th>
												<th>Families</th>
												<th>Self</th>
												<th>Families</th>
												<th>Male</th>
												<th>Female</th>
											 </tr>
								         <% } %>    
								         
								         <% if (nPara.equalsIgnoreCase("R2")) { %>
								              <tr style="text-align:center">
								                <th rowspan="4" width='15%' >Hospital Name</th>
								                <th colspan="5" rowspan="1">Bed Authorized</th>
								                <th colspan="8" rowspan="1">Bed Occupied</th>
								                <th colspan="2" rowspan="1">Total All Category</th>
								                <th colspan="2" rowspan="1">Percentage of Bed Occupied by All Category</th>
								                <th rowspan="4">Remarks</th> 
								             </tr>
								             
								              <tr style="text-align:center">
								                <th rowspan="3">OFFRS</th>
							                    <th rowspan="3">JCOs/OR</th>
								                <th colspan="2" rowspan="1">Families</th>
								                <th rowspan="3">Total</th>
								                <th colspan="2" rowspan="2">OFFRS</th>
								                <th colspan="2" rowspan="2">JCOs/OR</th>
								                <th colspan="4" rowspan="1">Families</th>
								                <th rowspan="3">Max</th>
								                <th rowspan="3">Avg</th>
								                <th rowspan="3">Max</th>
								                <th rowspan="3">Avg</th>
								             </tr>
								             
								              <tr style="text-align:center">
								                <th rowspan="2">OFFRS</th>
							                    <th rowspan="2">JCOs/OR</th>
							                    <th colspan="2" rowspan="1">OFFRS</th>
							                    <th colspan="2" rowspan="1">JCOs/OR</th>
								             </tr>
								             
								              <tr style="text-align:center">
											    <th>Max</th>
											    <th>Avg</th>
											    <th>Max</th>
											    <th>Avg</th>
											    <th>Max</th>
											    <th>Avg</th>
											    <th>Max</th>
											    <th>Avg</th> 
								             </tr>
								         <% } %>    
								         
								         <% if (nPara.equalsIgnoreCase("R3")) { %>
								              <tr style="text-align:center">
								                 <th rowspan="4" width='15%' >Hospital</th> 
								                 <th colspan="8">BED Occupied</th>
							                     <th colspan="2">Total All Category</th>
							                     <th colspan="2">Needy EXSM when not Drawing Pension</th>
							                     <th rowspan="4">No of Families Refused Adm after utilising Comd Res</th>
							                     <th rowspan="4">Remarks</th>
								             </tr>
								             
								             <tr style="text-align:center">
								                <th colspan="2" rowspan="2">OFFRS</th>
							                    <th colspan="2" rowspan="2">JCOs/OR</th>
								                <th colspan="4" rowspan="1">Families</th>
								                <th rowspan="3">Max</th>
								                <th rowspan="3">Avg</th>
								                <th rowspan="3">ADM</th>
								                <th rowspan="3">BED DAYS</th>
								             </tr>
								             
								              <tr style="text-align:center">
								                <th colspan="2" rowspan="1">OFFRS</th>
							                    <th colspan="2" rowspan="1">JCOs/OR</th>
								             </tr>
								             
								              <tr style="text-align:center">
											    <th>Max</th>
											    <th>Avg</th>
											    <th>Max</th>
											    <th>Avg</th>
											    <th>Max</th>
											    <th>Avg</th>
											    <th>Max</th>
											    <th>Avg</th> 
								             </tr>
								         <% } %>    
								         
								         <% if (nPara.equalsIgnoreCase("R4")) { %>
								              <tr style="text-align:center">
								                <th rowspan="2" width='15%' >Command</th> 
								                <th colspan="2" rowspan="1">OFFRS</th>  
								                <th colspan="2" rowspan="1">JCOs/OR</th>  
								                <th colspan="2" rowspan="1">TOTAL ALL CAT</th>  
								                <th colspan="2" rowspan="1">CADET</th>  
								                <th colspan="2" rowspan="1">RECRUIT</th>  
								                <th colspan="2" rowspan="1">TOTAL CADET RECRUIT</th>  
								             </tr>
								             
								              <tr style="text-align:center">
								                <th>TOTAL</th>
											    <th>RATE</th>
											    <th>TOTAL</th>
											    <th>RATE</th>
											    <th>TOTAL</th>
											    <th>RATE</th>
											    <th>TOTAL</th>
											    <th>RATE</th>
											    <th>TOTAL</th>
											    <th>RATE</th>
											    <th>TOTAL</th>
											    <th>RATE</th>
								             </tr>
								         <% } %>    
								          
						         </thead> 
						         <tbody>
						               <c:forEach var="item" items="${list}" varStatus="num">
						                  <% if (nPara.equalsIgnoreCase("R1")) { %>
								             <tr>
									            <td style="text-align: left;" width='15%' >${item.unit_name}</td>
											    <td style="text-align: center;">${item.officer_self}</td>
												<td style="text-align: center;">${item.officer_family}</td>
												<td style="text-align: center;">${item.jco_ors_self}</td>
												<td style="text-align: center;">${item.jco_ors_family}</td>
												<td style="text-align: center;">${item.ex_serv_self}</td>
												<td style="text-align: center;">${item.ex_serv_family}</td>
												<td style="text-align: center;">${item.para_mil_pers_self}</td>
												<td style="text-align: center;">${item.para_mil_pers_family}</td>
												<td style="text-align: center;">${item.civilian_self}</td>
												<td style="text-align: center;">${item.civilian_family}</td>
												<td style="text-align: center;">${item.total_during_month}</td>
												<td style="text-align: center;">${item.remarks}</td>
								             </tr>
								           <% } %>    
								           
								           <% if (nPara.equalsIgnoreCase("R2")) { %>
								              <tr>
								                <td style="text-align: left;" width='15%' >${item.unit_name}</td>
												<td style="text-align: center;">${item.offrs}</td>
												<td style="text-align: center;">${item.jcos}</td>
												<td style="text-align: center;">${item.off_fam_auth}</td>
												<td style="text-align: center;">${item.jco_or_fam_auth}</td>
												<td style="text-align: center;">${item.total_all}</td>
												<td style="text-align: center;">${item.ofcr_max}</td>
												<td style="text-align: center;">${item.ofcr_avg}</td>
												<td style="text-align: center;">${item.jco_max}</td>
												<td style="text-align: center;">${item.jco_avg}</td>
												<td style="text-align: center;">${item.ofcr_fmly_max}</td>
												<td style="text-align: center;">${item.ofcr_fmly_avg}</td>
												<td style="text-align: center;">${item.jco_fmly_max}</td>
												<td style="text-align: center;">${item.jco_fmly_avg}</td>
												<td style="text-align: center;">${item.tot_max}</td>
												<td style="text-align: center;">${item.tot_avg}</td>
												<td style="text-align: center;">${item.per_max}</td>
												<td style="text-align: center;">${item.per_avg}</td>
												<td style="text-align: left;">${item.remarks}</td>
								             </tr> 
								           <% } %>  
								           
								           <% if (nPara.equalsIgnoreCase("R3")) { %> 
								             <tr>
								                <td style="text-align: left;" width='15%' >${item.unit_name}</td>
												<td style="text-align: center;">${item.ofcr_max}</td>
												<td style="text-align: center;">${item.ofcr_avg}</td>
												<td style="text-align: center;">${item.jco_max}</td>
												<td style="text-align: center;">${item.jco_avg}</td>
												<td style="text-align: center;">${item.ofcr_fmly_max}</td>
												<td style="text-align: center;">${item.ofcr_fmly_avg}</td>
												<td style="text-align: center;">${item.jco_fmly_max}</td>
												<td style="text-align: center;">${item.jco_fmly_avg}</td>
												<td style="text-align: center;">${item.tot_max}</td>
												<td style="text-align: center;">${item.tot_avg}</td>
												<td style="text-align: center;">${item.need_exms_adm}</td>
												<td style="text-align: center;">${item.need_exms_bed_days}</td>
												<td style="text-align: center;">${item.no_of_fmly_ref_adm}</td>
												<td style="text-align: left;">${item.remarks}</td>
								             </tr>  
								           <% } %>  
								           
								           <% if (nPara.equalsIgnoreCase("R4")) { %>
								             <tr>
								                <td style="text-align: left;" width='15%' >${item.unit_name}</td>
								                <td style="text-align: center;">${item.total_off}</td>
								                <td style="text-align: center;">${item.rate_off}</td>
								                <td style="text-align: center;">${item.total_jco}</td>
								                <td style="text-align: center;">${item.rate_jco}</td>
								                <td style="text-align: center;">${item.total}</td>
								                <td style="text-align: center;">${item.total_rate}</td>
								                <td style="text-align: center;">${item.total_cadet}</td>
								                <td style="text-align: center;"> ${item.rate_cadet}</td>
								                <td style="text-align: center;">${item.rect}</td>
								                <td style="text-align: center;">${item.rate_rect}</td>
								                <td style="text-align: center;">${item.total_cadet_rect}</td> 
								                <td style="text-align: center;">${item.rate_cadet_rect}</td> 
								             </tr> 
								           <% } %>  
					                  </c:forEach>
					                  	<c:if test="${list.size()==0}">
									<tr>
										<td style="font-size: 15px; text-align: center; color: red;"
											colspan="17">Data Not Available</td>
									</tr>
								</c:if>
			 	                 </tbody>
			                 </table>
		                </div>
		         <!--   </div> -->
		      </div>                          
          </div>
      </div>     
  
     
         </div>
      
</form:form>

<c:url value="search_opd_bed_strength_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="cmd1">
		<input type="hidden" name="cmd1" id="cmd1"/>
		<input type="hidden" name="qtr1" id="qtr1"/>
		<input type="hidden" name="yr1" id="yr1"/>
		<input type="hidden" name="para1" id="para1"/> 
</form:form> 


<!-- for Functions -->
<script>
function btn_clc(){
	$("#level_c").val('ALL');
	$("#qtr").val('-1');
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
}


function printDiv() {
	
	

	
	
	var printLbl = [];
	var printVal = [];
	var commanddrp = document.getElementById("level_c");
	var command = commanddrp.options[commanddrp.selectedIndex].text; 
	var year1 =$("#year").val();
	
	$("#searchInput").hide();
	
	printLbl = ["Command :","Quarter :","Year :"];
	 printVal = [command,$("#qtr").val(),$("#year").val()];
	
	  <% if (nPara.equalsIgnoreCase("R1")) { %>
	  printDivOptimize12('divShow','SEARCH OPD DATA ',printLbl,printVal,"");
 		<% } %>
 		
 		 <% if (nPara.equalsIgnoreCase("R2")) { %>
 		printDivOptimize12('divShow','SEARCH BED SERV DATA ',printLbl,printVal,"");
 	 		<% } %>
 	 		
 	 		 <% if (nPara.equalsIgnoreCase("R3")) { %>
 	 		printDivOptimize12('divShow','SEARCH BED EX-SERV DATA ',printLbl,printVal,"");
 	 	 		<% } %>
 	 	 		
 	 	 	 <% if (nPara.equalsIgnoreCase("R4")) { %>
 	 	 	printDivOptimize12('divShow','QUTARLY STRENTH REPORT',printLbl,printVal,"");
 	 	 		<% } %>
 	 	 		$("#searchInput").show();
	
	

}



function isNumberPointKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if(charCode != 46 && charCode > 31 && (charCode<48 || charCode>57)){
		return false;
	}else{
		if(charCode == 46){
			return false;
		}
	}
	return true;
}

function getPara(){
	<% if (nPara.equalsIgnoreCase("R1")) { %>
	  $("#para_val").val("R1");
	<% } %>
	
	<% if (nPara.equalsIgnoreCase("R2")) { %>
	 $("#para_val").val("R2");
	<% } %>
	
	<% if (nPara.equalsIgnoreCase("R3")) { %>
	 $("#para_val").val("R3");
	<% } %>
	
	<% if (nPara.equalsIgnoreCase("R4")) { %>
	  $("#para_val").val("R4");
	<% } %>
}

function isvalidData(){
	
	var d = new Date();
	var year = d.getFullYear();
	
	if ($("#qtr").val() == "-1") {
		alert("Please Select the Quarter");
		$("#qtr").focus();
		return false;
	}
	else if(quarter_validate($("#qtr").val()) == 0 && $("#year").val() == d.getFullYear()){
		 alert("Future Quarter is not allowed to select");
		 return false;
	}
	else if ($("#year").val() == "") {
		alert("Please Enter the Year");
		$("#year").focus();
		return false;
	}
	 else if($("#year").val().length < 4){
		alert("Year Format Require in YYYY");
		$("#year").focus();
		return false;
	}
	else if($("#year").val() > year) {
		alert("Future Year cannot be allowed");
		$("#year").focus();
		return false;
	} 
	//$("#btn_p").show();
	
	getPara();
	$("#cmd1").val($("#level_c").val());
	$("#qtr1").val($("#qtr").val());
	$("#yr1").val($("#year").val());
	$("#para1").val($("#para_val").val());
	$("#searchForm").submit();
	
}

</script>

<script>
$(document).ready(function() {
 	 $("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
		$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	

	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
	
	 
	if('${size}' != 0){
		document.getElementById("btn_p").disabled = false;
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		document.getElementById("btn_p").disabled = false;
		$("#divPrint").show();
		
	 }
	
	
	 
	
	var q1 = '${cmd1}';
	if(q1 != ""){ 
		$("#level_c").val(q1);
	}
	
	var q2 = '${qtr1}';
	if(q2 != ""){ 
		$("#qtr").val(q2);
		}
	
	var q3 = '${yr1}';
	if(q3 != ""){ 
		$("#yr").val(q3);
		}
	
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?msg")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}	
});
</script>
