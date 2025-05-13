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
		            <h5>HIV/AIDS REPORT</h5>
		      </div> 
               <div class="card-body card-block">
                  <div class="row">          					
  					<div class="col-md-12" >
  					<div class="col-md-8">
							<div class="row form-group">
		                 <div class="col-md-3" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label">Command</label>
	               		 </div>	               		 
	               		 <div class="col-md-9">
	             			  <select name="command" id="command1" class="form-control-sm form-control">
	             			  	<%-- <c:if test="${r_1[0][1] != 'COMMAND'}">
									<option value="ALL">-- All Command --</option>
								</c:if>
								<c:if test="${not empty ml_1[0]}">
									<c:set var="data" value="${ml_1[0]}" />
									<c:set var="datap" value="${fn:split(data,',')}" />
									<c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
										<c:set var="dataf" value="${fn:split(datap[j],':')}" />
										<option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
									</c:forEach>
								</c:if>
								 --%>
								 
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
	                 		  <label for="text-input" class=" form-control-label">Category</label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
						<select name="category" id="category" class="form-control-sm form-control" >
								 <option value="-1">--Select--</option>
								 <c:forEach var="item" items="${ml_4}" varStatus="num">
									 <option value="${item}" name="${item}">${item}</option>
								 </c:forEach>
							 </select>
						<!-- </select> -->
	               		 </div>		
	               		 </div>
	               		 </div>               		 
		            </div> 
		             
		      <div class="col-md-12">
		       <div class="col-md-6">
							<div class="row form-group">     
					    <div class="col-md-4">
						    <label for="text-input" class=" form-control-label">Service</label>
						</div>
							
						<div class="col-md-8">
								<select id="service" name="service" class="form-control-sm form-control">
									<option value="-1">--Select--</option>
									<c:forEach var="item" items="${ml_3}" varStatus="num">
										<option value="${item}" name="${item}">${item}</option>
									</c:forEach>
								</select>
						</div>
						</div>
						</div>
						 <div class="col-md-6">
							<div class="row form-group">     
						<div class="col-md-4" style="text-align: left;">
							 <label for="text-input" class=" form-control-label">Relation</label>
						</div>
						<div class="col-md-8">
							 <select id="relation" name="relation" class="form-control-sm form-control" >
									 <option value="-1">--Select--</option>
									 <option value="SELF">SELF</option>
									 <option value="DEPENDENT">DEPENDENT</option>
									 
							 </select>
						</div>	
						</div>
						</div>
					</div>
	
					</div>


					<div class="col-md-12">
					 <div class="col-md-6">
							<div class="row form-group">
  					     <div class="col-md-4" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>From Date</label>
	               		 </div>
	               		 <div class="col-md-8">
	               			  <input type="date" id="from_dt" name="from_dt" class="form-control-sm form-control"  min="1899-01-01" max="${date}" maxlength="10">
	               			  
						 </div>	 
						 </div>
						 </div>
						 <div class="col-md-6">
							<div class="row form-group">
						 <div class="col-md-4" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>To Date</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			  <input type="date" id="to_dt" name="to_dt" class="form-control-sm form-control" min="1899-01-01" max="${date}" maxlength="10" onchange="return checkdate(this,from_dt)">
	               		 </div>
	               		 </div>
	               		 </div>
  					</div> 	
				  </div>  
				   </div>    
		       <div class="card-footer" align="center">
		        <a href="mnh_hivaids_report" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
		         <i class="fa fa-search"></i>  <input type="button" id="btn_serach" class="btn btn-success btn-sm"    value="Search" onclick="return isvalidData()" />
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
				             <table id="SearchReport" border="1" class="table no-margin table-striped  table-hover  table-bordered report_print">
				                  <thead >
				              
							             <tr style="text-align: center">
								           <th style="width:10%;">Ser No</th>	
                                           <th>CATEGORY</th>	
                                             <th>NO.OF CASES </th>									
								               <th>RATE</th>
                                
							           </tr>
						         </thead> 
						       <tbody>
						               <c:forEach var="item" items="${list}" varStatus="num" >
								            <tr style="text-align: right;" >
									            <th style="text-align: center;width:10%; ">${num.index+1}</th>
									            <th style="text-align: left;">${item.category}</th>
									             <th style="text-align: center;">${item.total}</th>
										 
												<th style="text-align: center;">${item.rate_off}</th> 
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

 

<c:url value="search_hiv_report" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="command12">
		<input type="hidden" name="command12" id="command12"/>
		<input type="hidden" name="category1" id="category1"/>
		<input type="hidden" name="service1" id="service1"/>
		<input type="hidden" name="relation1" id="relation1"/> 
		
		<input type="hidden" name="disease_principal1" id="disease_principal1"/> 
		<input type="hidden" name="disease_type1" id="disease_type1"/> 
		<input type="hidden" name="disease_subtype1" id="disease_subtype1"/> 
		<input type="hidden" name="block_description1" id="block_description1"/> 
		
		
		
		
		<input type="hidden" name="from_dt1" id="from_dt1"/> 
		<input type="hidden" name="to_dt1" id="to_dt1"/> 
		
</form:form> 

<script>

function btn_clc(){
	location.reload(true);
}



function isvalidData(){
	 
	if($("#from_dt").val() == "") {
		alert("Please Select From Date");
		$("#from_dt").focus();
		return false;
	}
	
	if($("#to_dt").val() == "") {
		alert("Please Select To Date");
		$("#to_dt").focus();
		return false;
	}  
	
	 	$("#command12").val($("#command1").val());
	    $("#category1").val($("#category").val()); 
	    $("#service1").val($("#service").val());
	    $("#relation1").val($("#relation").val());
	    
	    $("#disease_principal1").val($("#disease_principal").val());
	    $("#disease_type1").val($("#disease_type").val()); 
	    $("#disease_subtype1").val($("#disease_subtype").val());
	    $("#block_description1").val($("#block_description").val());
	   
	    $("#from_dt1").val($("#from_dt").val());
	    $("#to_dt1").val($("#to_dt").val());
	    
	  	$("#searchForm").submit();
}
function checkdate(obj,from_dt) {
	
	if(from_dt.value !="")
	{
		var id = obj.id;
		var myDate = document.getElementById(id).value;
		var Date1 = from_dt.value;
		if ((Date.parse(myDate) < Date.parse(Date1))) {
			alert('To Date should not be less than From Date');
			 $("input#to_dt").focus();
			obj.value = "";
		}
	}
	
} 
function printDiv() {

 
 
	var printLbl = [];
	var printVal = [];
	var commanddrp = document.getElementById("command1");
	var command = commanddrp.options[commanddrp.selectedIndex].text; 
	 
	var fromdt =$("#from_dt").val();
	var todt =$("#to_dt").val();
	
	$("#searchInput").hide();
	printLbl = ["Command :","From Date :","To Dtae :"];
	 printVal = [command,$("#from_dt").val(),$("#to_dt").val()];
			 
		  printDivOptimize12('divShow','HIV AIDS REPORT ',printLbl,printVal,"");
		  $("#searchInput").show();	
}


</script>

<script>
$(document).ready(function() {
	 $("div#divwatermark").val('').addClass('watermarked');	
		watermarkreport();
	
	$().getCurDt(to_dt); 
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	if ( '${command12}' != ""  ){	
			
			$("#divPrint").show();
			$("#command1").val('${command12}');		
			$("#category").val('${category1}');
			
			$("#service").val('${service1}');			
			$("#relation").val('${relation1}');	
			

			$("#disease_principal").val('${disease_principal1}');			
			$("#disease_type").val('${disease_type1}');	

			$("#disease_subtype").val('${disease_subtype1}');			
			$("#block_description").val('${block_description1}');	
			
			 
			$("#from_dt").val('${from_dt1}');			
			$("#to_dt").val('${to_dt1}');
		
		} 	
	
	if('${size}' != 0){
		document.getElementById("btn_p").disabled = false;
		$("#divPrint").show();
	} 
		if('${size}' == 0 && '${size}' != ""){
			document.getElementById("btn_p").disabled = false;
			$("#divPrint").show();
			
		}
		
		
		
		var q = '${command12}';
		var q2 = '${category1}';
		
		var q3 = '${service1}';
		var q4 = '${relation1}';
		
		var q5 = '${disease_principal1}';		
		var q6 = '${disease_type1}';
		
		var q7 = '${disease_subtype1}';		
		var q8 = '${block_description1}';
		
		var q9 = '${from_dt1}';
		var q10 = '${to_dt1}';

		  if(q != ""){
		    	$("#command1").val(q);
			}
		  if(q2 != ""){
		    	$("#category").val(q2);
			}
		  if(q3 != ""){
		    	$("#service").val(q3);
			}
		  
		  if(q4 != ""){
		    	$("#relation").val(q4);
			}
		  if(q5 != ""){
		    	$("#disease_principal").val(q5);
			}
		  if(q6 != ""){
		    	$("#disease_type").val(q6);
			}
		  if(q7 != ""){
		    	$("#disease_subtype").val(q7);
			}
		  if(q8 != ""){
		    	$("#block_description").val(q8);
			}
		  if(q9 != ""){
		    	$("#from_dt").val(q9);
			}
		  
		  if(q10 != ""){
		    	$("#to_dt").val(q10);
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
<script>
function principle(obj) {

	 
	var data_valuep = obj.value;

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		$.post("getdisease_principal_mmr?"+key+"="+value, {disease_principal :data_valuep,col_name:obj.id}).done(function(j) {


			var options2 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options3 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
			var options4 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
		  	   
			if(data_valuep != "-1"){
  	    	for ( var i = 0; i < j.length ;i++) {
  	    		
	  	    		
  	    		if(j[i][2] != "" && j[i][2] != null && j[i][2] !="null")
	  	    		options2 += '<option value="' + j[i][2] + '" name="' + j[i][2] + '" >' + j[i][2] + '</option>';
	  	    		
	  	    		if(j[i][3] != "" && j[i][3] != null && j[i][3] !="null")
			  	    	options3 += '<option value="' + j[i][3] + '" name="' + j[i][3] + '" >' + j[i][3] + '</option>';
			  	    	
			  	    if(j[i][4] != "" && j[i][4] != null && j[i][4] !="null")
				  	    options4 += '<option value="' + j[i][4] + '" name="' + j[i][4] + '" >' + j[i][4] + '</option>';
  			}
		

  			$("select#block_description").html(options2); 
  			$("select#disease_type").html(options3); 
  			$("select#disease_subtype").html(options4); 
		}
		
  		}); 
		
		} 
		


function block_dis(obj) {

	var data_valuep = obj.value;

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		
		$.post("getdiseaseblock_description?"+key+"="+value, {block_description :data_valuep,col_name:obj.id}).done(function(j) {
			
			if(data_valuep != "-1"){
		 	var options = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options3 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
			var options4 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
		  	   
  	    	for ( var i = 0; i < j.length ;i++) {
  	    		if(j[i][0] != "" && j[i][0] != null && j[i][0] !="null")
	  	    		options += '<option value="' + j[i][0] + '" name="' + j[i][0] + '" >' + j[i][0] + '</option>';

	  	    		if(j[i][3] != "" && j[i][3] != null && j[i][3] !="null")
			  	    	options3 += '<option value="' + j[i][3] + '" name="' + j[i][3] + '" >' + j[i][3] + '</option>';
			  	    	
			  	    if(j[i][4] != "" && j[i][4] != null && j[i][4] !="null")
				  	    options4 += '<option value="' + j[i][4] + '" name="' + j[i][4] + '" >' + j[i][4] + '</option>';
  			}  	    	
  	    	
  			 $("#disease_principal").html(options);   	    	
  			$("select#disease_type").html(options3); 
  			$("select#disease_subtype").html(options4); 
			}
			
  			
  		}); 
			
		
		
		} 
		
function type_dis(obj) {

	var data_valuep = obj.value;

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		
		$.post("getdisease_type?"+key+"="+value, {disease_type :data_valuep,col_name:obj.id}).done(function(j) {
			
			if(data_valuep != "-1"){
		 	var options = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options3 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
			var options4 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
		  	   
  	    	for ( var i = 0; i < j.length ;i++) {
  	    		if(j[i][0] != "" && j[i][0] != null && j[i][0] !="null")
	  	    		options += '<option value="' + j[i][0] + '" name="' + j[i][0] + '" >' + j[i][0] + '</option>';
	  	    		
	  	    	if(j[i][3] != "" && j[i][3] != null && j[i][3] !="null")
		  	    	options3 += '<option value="' + j[i][3] + '" name="' + j[i][3] + '" >' + j[i][3] + '</option>';
		  	    	
		  	    if(j[i][4] != "" && j[i][4] != null && j[i][4] !="null")
			  	    options4 += '<option value="' + j[i][4] + '" name="' + j[i][4] + '" >' + j[i][4] + '</option>';
  			}  	    	
  	    	
  			 $("#disease_principal").html(options);   	    	
  			$("select#block_description").html(options3); 
  			$("select#disease_subtype").html(options4); 
			}
			
  			
  		}); 
			
		
		
		} 
		
function subtype_dis(obj) {

	var data_valuep = obj.value;

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		
		$.post("getdisease_subtype?"+key+"="+value, {disease_subtype :data_valuep,col_name:obj.id}).done(function(j) {
			
			if(data_valuep != "-1"){
		 	var options = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options3 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
			var options4 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
		  	   
  	    	for ( var i = 0; i < j.length ;i++) {
  	    		if(j[i][0] != "" && j[i][0] != null && j[i][0] !="null")
	  	    		options += '<option value="' + j[i][0] + '" name="' + j[i][0] + '" >' + j[i][0] + '</option>';
	 
	  	    	if(j[i][3] != "" && j[i][3] != null && j[i][3] !="null")
		  	    	options3 += '<option value="' + j[i][3] + '" name="' + j[i][3] + '" >' + j[i][3] + '</option>';
		  	    	
		  	    if(j[i][4] != "" && j[i][4] != null && j[i][4] !="null")
			  	    options4 += '<option value="' + j[i][4] + '" name="' + j[i][4] + '" >' + j[i][4] + '</option>';
  			}  	    	
  	    	
  			 $("#disease_principal").html(options);   	    	
  			$("select#block_description").html(options3); 
  			$("select#disease_type").html(options4); 
			}
			
  			
  		}); 
			
		
		
		} 
</script>