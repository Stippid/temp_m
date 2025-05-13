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
              <div class="card-header mnh-card-header">
		           <% if (nPara.equalsIgnoreCase("D1")) { %>
		          <h5>INVALIDMENT REPORT</h5>
		        <% } %>
		        
		        <% if (nPara.equalsIgnoreCase("D2")) { %>
		           <h5>Mortality/Death Report </h5>
		        <% } %>		          
		            
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
	             			  <select name="command" id="command" class="form-control-sm form-control">
	             			  <%-- 	<c:if test="${r_1[0][1] != 'COMMAND'}">
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
	                 		  <label for="text-input" class=" form-control-label">Category</label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
						<select name="category" id="category" class="form-control-sm form-control" >
								 <option value="-1">--Select--</option>
								 <c:forEach var="item" items="${ml_4}" varStatus="num">
									 <option value="${item}" name="${item}">${item}</option>
								 </c:forEach>
							 </select>
					
	               		 </div>	
	               		 </div>
	               		 </div>	               		 
		            </div> 
		   
		   	        <div class="col-md-12 " >
		          <div class="col-md-6">
		          <div class="row form-group">
		              <div class="col-md-4" > 
		               	   <label for="text-input" class="form-control-label">Disease Principal</label>
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
		              <div class="col-md-4" > 
		               	  <label for="text-input" class="form-control-label">Disease Type</label>
		              </div>
		              
		              <div class="col-md-8">
		                <select name="disease_type" id="disease_type" class="form-control-sm form-control" onchange="onChangeMothod(this);">
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${ml_9}" varStatus="num">
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
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Disease
										Sub Type</label>
								</div>

								<div class="col-md-8">
									<select name="disease_subtype" id="disease_subtype" class="form-control-sm form-control"  onchange="onChangeMothod(this);">
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${ml_10}" varStatus="num">
								<option value="${item}" name="${item}">${item}</option>
							</c:forEach>
						</select>	
								</div>
							</div>
						</div>

						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label for="text-input" class="form-control-label">Block Description</label>
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
	
					<div class="col-md-12">
					<div class="col-md-6">
							<div class="row form-group">
  					     <div class="col-md-4" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>From Date</label>
	               		 </div>
	               		 <div class="col-md-8">
	               			  <input type="date" id="from_date" name="from_date" value="${to_date}"  class="form-control-sm form-control" min="1899-01-01" max="${date}" maxlength="10">
						 </div>	
						 </div>
						 </div>
						 
						 <div class="col-md-6">
							<div class="row form-group"> 						 
						 <div class="col-md-4" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>To Date</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			  <input type="date" id="to_date" name="to_date" class="form-control-sm form-control"  min="1899-01-01" max="${date}" maxlength="10" onchange="return checkdate(this,from_date)">
	             		
	               		 </div>
	               		 </div>
	               		 </div>
  					</div> 	
				  </div>   
		   </div>   	      
	
		       <div class="card-footer" align="center">
		       <% if (nPara.equalsIgnoreCase("D1")) { %>
			      <a href="imb_report" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
			        <% } %>
			        <% if (nPara.equalsIgnoreCase("D2")) { %>
			      <a href="mortality_report" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
			        <% } %>
		        <i class="fa fa-search"></i>   <input type="button" id="btn_serach" class="btn btn-success btn-sm"   value="Search" onclick="return isvalidData()" />  
		          <button type="button" id="btn_p" class="btn btn-primary btn-sm btn_report" onclick="printDiv();">Print</button>	 
              </div>
        
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
                                          	 <th>Category</th>	
                                             <th>No of Cases </th>									
								             <th>Rate</th>
								              
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
		       
		      

        
</form:form>




<% if (nPara.equalsIgnoreCase("D1")) { %>
<c:url value="search_imb_reports" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="search1" name="search1" modelAttribute="cmd1">
		<input type="hidden" name="cmd1" id="cmd1" value=""/>
		<input type="hidden" name="category1" id="category1" value=""/>
		
		<input type="hidden" name="from_date1" id="from_date1" value=""/>
		<input type="hidden" name="to_date1" id="to_date1" value=""/>
		<input type="hidden" name="disease_principal1" id="disease_principal1"/>
		<input type="hidden" name="disease_type1" id="disease_type1"/>
		<input type="hidden" name="disease_subtype1" id="disease_subtype1"/>
		<input type="hidden" name="block_description1" id="block_description1"/>   
	</form:form>
<% } %>	

<% if (nPara.equalsIgnoreCase("D2")) { %>
<c:url value="search_mortality_reports" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="search1" name="search1" modelAttribute="cmd1">
		<input type="hidden" name="cmd1" id="cmd1" value=""/>
		<input type="hidden" name="category1" id="category1" value=""/>
		<input type="hidden" name="from_date1" id="from_date1" value=""/>
		<input type="hidden" name="to_date1" id="to_date1" value=""/>
		<input type="hidden" name="disease_principal1" id="disease_principal1"/>
		<input type="hidden" name="disease_type1" id="disease_type1"/>
		<input type="hidden" name="disease_subtype1" id="disease_subtype1"/>
		<input type="hidden" name="block_description1" id="block_description1"/>   
	</form:form>
<% } %>	



<script>


function printDiv() {
	var printLbl = [];
	var printVal = [];
	var commanddrp = document.getElementById("command");
	var command = commanddrp.options[commanddrp.selectedIndex].text; 
	var fromdt =$("#from_date").val();
	var todt =$("#to_date").val();
	
	$("#searchInput").hide();
	printLbl = ["Command :","From Date :","To Dtae :"];
	 printVal = [command,$("#from_date").val(),$("#to_date").val()];
	 
		
		  <% if (nPara.equalsIgnoreCase("D1")) { %>
		  printDivOptimize12('divShow','INVALIDMENT REPORT ',printLbl,printVal,"");
	 		<% } %>
	 		
	 		  <% if (nPara.equalsIgnoreCase("D2")) { %>
			  printDivOptimize12('divShow','Mortality/Death Report ',printLbl,printVal,"");
		 		<% } %>
		 		$("#searchInput").show();
}




function btn_clc(){
	location.reload(true);
}

function isvalidData(){
	 
	
	if($("#from_date").val() == "") {
		alert("Please Select From Date");
		$("#from_date").focus();
		return false;
	}
	
	if($("#to_date").val() == "") {
		alert("Please Select To Date");
		$("#to_date").focus();
		return false;
	}  
	
	 	$("#cmd1").val($("#command").val());
	    $("#category1").val($("#category").val()); 
	    $("#from_date1").val($("#from_date").val());
	    $("#to_date1").val($("#to_date").val());
	    $("#disease_principal1").val($("#disease_principal").val());
	    $("#disease_type1").val($("#disease_type").val());
	    $("#disease_subtype1").val($("#disease_subtype").val());
	    $("#block_description1").val($("#block_description").val());
	    
	  	$("#search1").submit();
}

function checkdate(obj,from_date) {
	
	if(from_date.value !="")
	{
		var id = obj.id;
		var myDate = document.getElementById(id).value;
		var Date1 = from_date.value;
		if ((Date.parse(myDate) < Date.parse(Date1))) {
			alert('To Date should not be less than From Date');
			 $("input#to_date").focus();
			obj.value = "";
		}
	}
	
}  
</script>

<script>
$(document).ready(function() {
	
	 $("div#divwatermark").val('').addClass('watermarked');	
		watermarkreport();
	
	$().getCurDt(to_date); 
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});


	if ( '${cmd1}' != ""  ){	
			
		$("#divPrint").show();
			$("#command").val('${cmd1}');		
			$("#category").val('${category1}');
			$("#from_date").val('${from_date1}');			
			$("#to_date").val('${to_date1}');
		
		} 
	if('${size}' != 0){
		document.getElementById("btn_p").disabled = false;
		$("#divPrint").show();
	} 
		if('${size}' == 0 && '${size}' != ""){
			document.getElementById("btn_p").disabled = false;
			$("#divPrint").show();
			
		}
		
		
	
		
		var q = '${cmd1}';
		
		var q3='${category1}';
		var q4 = '${from_date1}';
		var q5 = '${to_date1}';
		var q6 = '${disease_principal1}';
		var q7 = '${disease_type1}';
		var q8 = '${disease_subtype1}';
		var q9 = '${block_description1}';
		
		  if(q != ""){
		    	$("#command").val(q);
			}
		  
		  if(q3 != ""){
		    	$("#category").val(q3);
			}
		  
		  if(q4 != ""){
		    	$("#from_date").val(q4);
			}
		  
		  if(q5 != ""){
		    	$("#to_date").val(q5);
			}  
		  if(q6 != ""){
		    	$("#disease_principal").val(q6);
			}  
		  if(q7 != ""){
		    	$("#disease_type").val(q7);
			}  
		  if(q8 != ""){
		    	$("#disease_subtype").val(q8);
			} 
		  if(q9 != ""){
		    	$("#block_description").val(q9);
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
		
		if(obj.id!="disease_type"){
		 $.post("getdisease_type?"+key+"="+value, {col_value :data_valuep,col_name:obj.id}).done(function(j) {
			
			var options = '';
			if(j=="" || j=="null"){
				options += '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			}
			$("#disease_type").html(options); 
			
			if(data_valuep != "-1"){
  	    	for ( var i = 0; i < j.length ;i++) {
  	    		if(j[i] != "" && j[i] != null && j[i] !="null")
	  	    		options += '<option value="' + j[i] + '" name="' + j[i] + '" >' + j[i] + '</option>';
  			}
		}
			$("#disease_type").html(options); }); 
		 
		
		}else{}	
		 
		 
		
		 
		if(obj.id!="disease_subtype"){

			
		 $.post("getdisease_subtype?"+key+"="+value, {col_value :data_valuep,col_name:obj.id}).done(function(j) {
				
				var options = '';
				if(j=="" || j=="null"){
					options += '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
				}
				$("#disease_subtype").html(options); 
				
				if(data_valuep != "-1"){
	  	    	for ( var i = 0; i < j.length ;i++) {
	  	    		if(j[i] != "" && j[i] != null && j[i] !="null")
		  	    		options += '<option value="' + j[i] + '" name="' + j[i] + '" >' + j[i] + '</option>';
	  			}
			}
				$("#disease_subtype").html(options); }); 
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


