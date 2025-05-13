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

<form:form action="" id="" method="post" class="form-horizontal" commandName="">
   
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		          <h5>OPD SPL PROCEDURE REPORT</h5>
		      </div> 
               <div class="card-body card-block">
               <div class="row">      					
  					<div class="col-md-12">
  					<div class="col-md-8">
							<div class="row form-group">
		                 <div class="col-md-3" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Command</label>
	               		 </div>	               		 
	               		 <div class="col-md-9">
	               		     
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
	             			    <select name="quater" id="quater"  class="form-control-sm form-control">
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
			    <a href="opd_spl_proc" type="reset" class="btn btn-primary btn-sm"> Clear </a>  
		         <i class="fa fa-search"></i><input type="button" id="btn_serach"class="btn btn-success btn-sm"  value="Search" onclick="isvalidData();" />
		       <button type="button" id="btn_p" class="btn btn-primary btn-sm btn_report" onclick="printDiv();">Print</button>	 
		          
              </div>
	</div> 
				
				 <div class="nkpageland" id="printableArea">            
                 <div class="container" id="divPrint" style="display: none;">
                   <div id="divShow" style="display: block;">
			 	    	<div id="divShow1" align="center" style="display: none;"></div>	                       
				      
				 
           
				            <div  class="watermarked" data-watermark="" id="divwatermark" >
						<span id="ip"></span> 	
							<div id="divSerachInput" class="col-md-12">
					<div class="col-md-6">
						<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Word"  size="35" class="form-control">			
				</div> 
				</div>			 
				        	<table id="SearchReport" style="width:100%;" border="1" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead >
									 <tr style="text-align:center">
										<th width="7%">Sr No.</th>
										<th width="30%">Command</th>
										<th width="20%">Department</th>
										<th width="20%">Procedure</th>
										<th width="10%">Count</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="item" items="${list}" varStatus="num">
										<tr>
											<td style=" text-align: center;"width="7%">${num.index+1}</td>
											<td style=" text-align: left;"width="30%">${item.command}</td>
											<td style=" text-align: left;"width="20%">${item.dept_name}</td>
											<td style=" text-align: left;"width="20%">${item.procedure}</td>
											<td style=" text-align: center;"width="10%">${item.opd_count}</td>
											<%-- <th style="font-size: 15px;">${item.count}</th> --%>
											
										</tr>
									</c:forEach>
									
									
									<tr>
										  <th width="7%"></th>
										<th width="30%"></th>
										<th width="20%"></th>
										
										<th style=" text-align: center;"width="20%">Total</th>
										<td style=" text-align: center;"width="10%"><B>${total1}</B></td>
									</tr>
										<c:if test="${list.size()==0}">
									<tr>
										<td style="font-size: 15px; text-align: center; color: red;"
											colspan="17">Data Not Available</td>
									</tr>
								</c:if>
								</tbody>
							</table>
		                </div>
		       
		      </div>           
          </div>
      </div>   
			 </div> 
          

</form:form>
 


<c:url value="search_opd_spl_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="cmd1">
<input type="hidden" name="cmd1" id="cmd1">
<input type="hidden" name="qtr1" id="qtr1">
<input type="hidden" name="yr1" id="yr1"/>
  
</form:form>

<script>

function btn_clc(){
	$("#level_c").val('ALL');
	$("#qtr").val('-1');
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
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

function isvalidData(){
	var d = new Date();
	var year = d.getFullYear();
	 
	/*  if ($("#level_c").val() == "ALL") {
		alert("Please Select the Command.");
		$("#level_c").focus();
		return false;
	} 
	 */
	if ($("#quater").val() == "-1") {
		alert("Please Select the Quarter");
		$("#quater").focus();
		return false;
	}
	
	 
	 var a = $("#year").val();
	 
	if(quarter_validate($("#quater").val()) == 0 && (a == d.getFullYear())){
		 alert("Future Quarter is not allowed to select");
		 return false;
	}
	 if ($("#year").val() == "") {
		alert("Please Enter the Year");
		$("#year").focus();
		return false;
	}
	   if($("#year").val().length < 4){
		alert("Year Format Require in YYYY");
		$("#year").focus();
		return false;
	}  
 	 if($("#year").val() == "0000"){
		alert("Year Format 0000 is invalid");
		$("#year").focus();
		return false;
	} 
	 if($("#year").val() > year) {
		alert("Future Year cannot be allowed");
		$("#year").focus();
		return false;
	}
	
	
	$("#cmd1").val($("#level_c").val());
	$("#qtr1").val($("#quater").val());
	$("#yr1").val($("#year").val());
	
	$("#searchForm").submit();
}


</script>

 <script>

function printDiv() {
	



	$("#searchInput").hide();
	var printLbl = [];
	var printVal = [];
	var commanddrp = document.getElementById("level_c");
	var command = commanddrp.options[commanddrp.selectedIndex].text;
	var quater1 =$("#quater").val();
	var year1 =$("#year").val();
	
	
	
	printLbl = ["Command :","Quarter :","Year :"];
	 printVal = [command,$("#quater").val(),$("#year").val()];
	
	  printDivOptimize12('divShow','OPD SPL PROC REPORT ',printLbl,printVal,"");
	  $("#searchInput").show();

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
		$("#divPrint").show();
	} 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
		$("table#SearchReport").append("<tr><td colspan='5' style='text-align: center;font-weight:bold;'>Data Not Available</td></tr>");
	}
	
	 
	
	var q = '${cmd1}';
	if(q != ""){ 
		$("#level_c").val(q);
	}
	
	var q1 = '${qtr1}';
	if(q1 != ""){ 
		$("#quater").val(q1);
	}
	
	var q2 = '${yr1}';
	if(q2 != ""){ 
		$("#year").val(q2);
	}

 	
});
	</script>
