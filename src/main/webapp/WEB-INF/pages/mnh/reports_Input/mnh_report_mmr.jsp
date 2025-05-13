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
		          <h5>COMMANDWISE MONTHLY MORBIDITY REPORT</h5>
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
	               			      <%--    <c:if test="${r_1[0][1] != 'COMMAND'}">
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
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong>Month/Year</label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
	             			<input type="month" name="mth_yr" id="mth_yr" class="form-control-sm form-control" autocomplete ="off" onchange ="return quarter_mmr(this.value);">
	               		 </div>	
	               		 </div>
	               		 </div>	
	               		 		 
		            </div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
						<div class="col-md-4" style="text-align: left;">
							<label for="text-input" class=" form-control-label">Principal Cause</label>
						</div>
						<div class="col-md-8">
							<select name="disease_principal" id="disease_principal" class="form-control-sm form-control" onchange="onChangeMothod(this);"  ><!-- onchange="jsFunction(this.value);" -->
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${ml_5}" varStatus="num">
							   <option value="${item}" name="${item}">${item}</option>
							</c:forEach>
						</select>	
						</div>
						</div>
						</div>  
						<div class="col-md-6">
							<div class="row form-group">
				
						<div class="col-md-4 " style="text-align: left;">
							<label for="text-input" class=" form-control-label">Monthly Morbidity</label>
						</div>
						<div class="col-md-8">
							<select name="disease_mmr" id="disease_mmr" class="form-control-sm form-control" onchange="onChangeMothod(this);"   >
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${ml_6}" varStatus="num">
								<option value="${item}" name="${item}">${item}</option>
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
							<label for="text-input" class=" form-control-label">ASO MMR</label>
						</div>
						<div class="col-md-8">
							<select name="disease_aso" id="disease_aso" class="form-control-sm form-control" onchange="onChangeMothod(this);" >
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${ml_7}" varStatus="num">
								<option value="${item}" name="${item}">${item}</option>
							</c:forEach>
						</select>	
						</div>
						</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
						<div class="col-md-4" style="text-align: left;">
							<label for="text-input" class=" form-control-label">Block Description</label>
						</div>
						<div class="col-md-8">
								<select name="block_description" id="block_description" class="form-control-sm form-control" onchange="onChangeMothod(this);" >
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${ml_8}" varStatus="num">
								<option value="${item}" name="${item}">${item}</option>
							</c:forEach>
						</select>	
						</div>
						</div>
						</div>
					</div> 
				</div>		      
	</div>
		      <div class="card-footer" align="center">
			      <a href="mnh_mmr_report" type="reset" class="btn btn-primary btn-sm"> Clear </a>  
		         <i class="fa fa-search"></i><input type="button" id="btn_serach"class="btn btn-success btn-sm"  value="Search" onclick="isvalidData();" /> 
		          <button type="button" id="btn_p" class="btn btn-primary btn-sm btn_report" onclick="printDiv();">Print</button>	 
		          
              </div>
                          
   </div>   
   </div>     
			  <div class="nkpageland" id="printableArea">
               <div class="container" id="divPrint" style="display: none;">
               
                   <div id="divShow" style="display: block;">
                   
                   <div id="divShow1" align="center" style="display: none;"></div>
				<input id="icd_code" name="icd_code" type="hidden" value="">			
			   <div id="divSerachInput" class="col-md-12">
					<div class="col-md-6">
						<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Word"  size="35" class="form-control">			
				</div> 
			</div> 
				
				<div class="col-md-12"  id="divPrint" style="display: block;" >
				
		      	<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>	
						
									<table id="mmrreport" class="table no-margin table-striped  table-hover  table-bordered report_print">
								<thead style="background-color: #9c27b0; color: white;">
								 		 <tr style="text-align:center">
									<th  rowspan="2" >Month/Year</th> 
									            <th colspan="2">OFFICER</th>
											    <th colspan="2">JCOs/OR</th>
											    <th colspan="2">TOTAL</th>
											    </tr>
											    <tr style="text-align:center">
												<th>Admission</th>
												<th>Rate</th>
												<th>Admission</th>
												<th>Rate</th>
												<th>Admission</th>
												<th>Rate</th>
												</tr>
								</thead>

								<tbody>
									<c:forEach var="item" items="${list}" varStatus="num">
										<tr>
										 		     <td style="text-align: left;">${item.monthname}</td>
											    <td style="text-align: center;">${item.off}</td>
												<td style="text-align: center;">${item.rate_off}</td>
												<td style="text-align: center;">${item.jco}</td>
												<td style="text-align: center;">${item.rate_jco}</td>
												<td style="text-align: center;">${item.total}</td>
												<td style="text-align: center;">${item.rate_total}</td>
										</tr>
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
					</div>
				</div>
			</div>
   </div>
</form:form>


<c:url value="search_mmr_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="cmd1">
<input type="hidden" name="cmd1" id="cmd1">
<input type="hidden" name="mth_yr1" id="mth_yr1">
<input type="hidden" name="disease_principal1" id="disease_principal1"/>
<input type="hidden" name="disease_mmr1" id="disease_mmr1"/>
<input type="hidden" name="disease_aso1" id="disease_aso1"/>
<input type="hidden" name="block_description1" id="block_description1"/>   
<input type="hidden" name="hdicd_code" id="hdicd_code"/>   
</form:form>

<script>

function onChangeMothod(obj) {

	var data_valuep = obj.value;

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		
		
		
		
		if(obj.id!="disease_principal"){
			$.post("getdisease_principal_mmr?"+key+"="+value, {col_value :data_valuep,col_name:obj.id}).done(function(j) {
				
				
				
				var options = '';
				if(j=="" || j=="null"){
					options += '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
				}
				$("#disease_principal").html(options); 
				
				if(data_valuep != "-1"){
	  	    	for ( var i = 0; i < j.length ;i++) {
	  	    		if(j[i] != "" && j[i] != null && j[i] !="null")
		  	    		options += '<option value="' + j[i] + '" name="' + j[i]+ '" >' + j[i] + '</option>';
		  	    	
	  			}
			
	  	    	$("#disease_principal").html(options); 
			}
				
			
	  		}); 
		}else{}
		
		
		

		if(obj.id!="disease_aso"){
			$.post("getdisease_aso?"+key+"="+value, {col_value :data_valuep,col_name:obj.id}).done(function(j) {
				
				
				
				var options = '';
				if(j=="" || j=="null"){
					options += '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
				}
				$("#disease_aso").html(options); 
				
				if(data_valuep != "-1"){
	  	    	for ( var i = 0; i < j.length ;i++) {
	  	    		if(j[i] != "" && j[i] != null && j[i] !="null")
		  	    		options += '<option value="' + j[i] + '" name="' + j[i]+ '" >' + j[i] + '</option>';
		  	    	
	  			}
			
	  	    	$("#disease_aso").html(options); 
			}
				
			
	  		}); 
		}else{}
		
		
		 
		 
		if(obj.id!="disease_mmr"){
		 $.post("getdisease_mmr?"+key+"="+value, {col_value :data_valuep,col_name:obj.id}).done(function(j) {
				
				var options = '';
				if(j=="" || j=="null"){
					options += '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
				}
				$("#disease_mmr").html(options); 
			
				if(data_valuep != "-1"){
	  	    	for ( var i = 0; i < j.length ;i++) {
	  	    		if(j[i] != "" && j[i] != null && j[i] !="null")
		  	    		options += '<option value="' + j[i] + '" name="' + j[i] + '" >' + j[i] + '</option>';
	  			}
			}
				$("#disease_mmr").html(options); }); 
		 
		}else{}	
		 
		 
	
		 
		if(obj.id!="block_description"){
		 $.post("getdiseaseblock_description?"+key+"="+value, {col_value :data_valuep,col_name:obj.id}).done(function(j) {
				var options = '';
				if(j=="" || j=="null"){
					options += '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
				}
				$("#block_description").html(options); 
				
				if(data_valuep != "-1"){
	  	    	for ( var i = 0; i < j.length ;i++) {
	  	    		if(j[i] != "" && j[i] != null && j[i] !="null")
		  	    		options += '<option value="' + j[i] + '" name="' + j[i] + '" >' + j[i] + '</option>';
	  			}
			}
				$("#block_description").html(options); }); 
			
		}else{}	
		
		
}
		
function quarter_mmr(u_q){
	
	var d = new Date(u_q);
	
	var year = d.getFullYear();
	
	 var Month = ("0" + (d.getMonth() + 1)).slice(-2);
	
	 var quarter="";
		 
		 if(Month > 0 && Month <=3)
		 {
			 quarter="q1";
		 }
		 else if (Month > 3 && Month <=6)
		 {
			 quarter="q2";
		 }
		 else if (Month > 6 && Month <=9)
		 {
			 quarter="q3";
		 }
		 else
		 { 
			 quarter="q4";
		 }
		 
		 var user_q = u_q.substr(1, 1); 
	     var current_q = quarter.substr(1, 1); 
	   
		if(parseInt(user_q) >  parseInt(current_q))
		{
			 return 0;
		}
		return 1;
		
} 

</script>
<script>

	
function btn_clc(){
	location.reload(true);
}

function isvalidData(){
 	
	if ($("#mth_yr").val() == "") {
		alert("Please Select the Month Year");
		$("#mth_yr").focus();
		return false;
	} 
	
	
		$("input#cmd1").val($("#level_c").val());
		$("input#mth_yr1").val($("#mth_yr").val());
		$("input#disease_principal1").val($("#disease_principal").val());
		$("input#disease_mmr1").val($("#disease_mmr").val());
		$("input#disease_aso1").val($("#disease_aso").val());
		$("input#block_description1").val($("#block_description").val());
		$("input#hdicd_code").val($("#icd_code").val());
		$("#searchForm").submit();
		
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
	 printDivOptimize12('divShow','COMMANDWISE MONTHLY MORBIDITY REPORT',printLbl,printVal,"");
 	 	 	
 	 	 		$("#searchInput").show();
}

</script>

<script>
$(document).ready(function() {	
	
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#mmrreport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	

	 var monthControl = document.querySelector('input[type="month"]');
	  var date= new Date()
	  var month=("0" + (date.getMonth() + 1)).slice(-2)
	  var year=date.getFullYear()
	  monthControl = year+'-'+month;
	  $("#mth_yr").val(monthControl); 
	  
	if ('${cmd1}' != "" ){
		$("#divPrint").show();
		$("#level_c").val('${cmd1}');	
		$("#mth_yr").val('${mth_yr1}');	
		$("#disease_principal").val('${disease_principal1}');		
		$("#disease_mmr").val('${disease_mmr1}');	
		$("#disease_aso ").val('${disease_aso1}');
		$("#block_description ").val('${block_description1}');
		
		}
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	}
	var q6 = '${disease_principal1}';
	var q7 = '${disease_mmr1}';
	var q8 = '${disease_aso1}';
	var q9 = '${block_description1}';
	 if(q6 != ""){
	    	$("#disease_principal").val(q6);
		}  
	  if(q7 != ""){
	    	$("#disease_mmr").val(q7);
		}  
	  if(q8 != ""){
	    	$("#disease_aso").val(q8);
		} 
	  if(q9 != ""){
	    	$("#block_description").val(q9);
		} 
	  
	
try{
		if(window.location.href.includes("msg="))
		{
			var url = window.location.href.split("?")[0];
			var m=  window.location.href.split("?")[1];
			window.location = url;
			if(m.includes("Updated+Successfully")){
				window.opener.getRefreshReport('HospitalDataSearch',m);
				window.close('','_parent','');
				}
			}
		}
	catch (e) {
	}
	
	});
	</script>
	
	
