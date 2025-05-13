<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
		          
		          <h5>CONTROL LIMITS</h5>
		                  
		            
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
	             			  <select name="command" id="command" class="form-control-sm form-control">
	             			  	<c:if test="${r_1[0][1] != 'COMMAND'}">
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
	                  		</select> 
	                  		 
	               		 </div>
	               		 </div>
	               		 </div>
	               		 <div class="col-md-4">
							<div class="row form-group">
	               		 <div class="col-md-4" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Category</label>
	               		 </div>	               		 
	               		
	               		 
	               		 <div class="col-md-8">
						<select name="category" id="category" class="form-control-sm form-control" >
								 <option value="-1">--Select--</option>
								 <option value="OFFICER">OFFICER</option>
								 <option value="JCO">JCO</option>
								
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
		                   
		                  <select name="disease_principal" id="disease_principal" class="form-control-sm form-control" onchange="principle(this);"  >
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
		               	   <label for="text-input" class=" form-control-label">Disease MMR</label>
		              </div>
		              
		              <div class="col-md-8">
		                  <select name="disease_mmr" id="disease_mmr" class="form-control-sm form-control" onchange="mmr_dis(this);"  >
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${ml_6}" varStatus="num">
								<option value="${item}" name="${item}">${item}</option>
							</c:forEach>
						</select>
		              </div> 
		              </div>
		              </div>
		             
		           </div> 
	          
	             <div class="col-md-12" >
	              <div class="col-md-6">
		          <div class="row form-group">
		              <div class="col-md-4" > 
		               	  <label for="text-input" class="form-control-label">Disease Type</label>
		              </div>
		              
		              <div class="col-md-8">
		                <select name="disease_type" id="disease_type" class="form-control-sm form-control" onchange="type_dis(this);" >
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
		              <div class="col-md-4" > 
		               	  <label for="text-input" class="form-control-label">Disease Sub Type</label>
		              </div>
		              
		              <div class="col-md-8">
		                
		                   <select name="disease_subtype" id="disease_subtype" class="form-control-sm form-control" onchange="sub_type_dis(this);" >
							<option value="-1">--Select--</option>
							<c:forEach var="item" items="${ml_9}" varStatus="num">
								<option value="${item}" name="${item}">${item}</option>
							</c:forEach>
						</select>
		              </div>
		              </div>
		              </div>
		          </div>
		          <div class="col-md-12" >
	 <div class="col-md-6">
		          <div class="row form-group">
		              <div class="col-md-4" > 
		               	  <label for="text-input" class="form-control-label">Block Description</label>
		              </div>
		              
		              <div class="col-md-8">
		                 <select name="block_description" id="block_description" class="form-control-sm form-control" onchange="block_dis(this);" >
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
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>From Month</label>
	               		 </div>
	               		 <div class="col-md-8">
	               			  <input type="month" id="from_month" name="from_month" value="${from_month}"  class="form-control-sm form-control" min="1899-01-01" max="${date}" }>
						 </div>	
						 </div>
						 </div>
						 
						 <div class="col-md-6">
							<div class="row form-group"> 						 
						 <div class="col-md-4" style="text-align: left;"> 
	               			  <label class=" form-control-label"><strong style="color: red;">* </strong>To Month</label>
	             		 </div>
	             		 <div class="col-md-8">
	             			  <input type="month" id="to_month" name="to_month" class="form-control-sm form-control" value="${to_month}"  min="1899-01-01" max="${date}" maxlength="10">
	             		
	               		 </div>
	               		 </div>
	               		 </div>
  					</div> 
  						
				  </div>   
		   </div>   	      
	
		       <div class="card-footer" align="center">
		      
			      <a href="mnh_report_control_limit" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
			       
			      
		        <i class="fa fa-search"></i>   <input type="button" id="btn_serach" class="btn btn-success btn-sm"   value="Search" onclick="return SearchData()" />  
		          <button type="button" id="btn_p" class="btn btn-primary btn-sm btn_report" onclick="printDiv();">Print</button>	 
           </div>
        
              </div>	                
       </div>
      
		    
                   <div class="nkpageland" id="printableArea">            
                 <div class="container" id="divPrint">
                    <div id="divShow" style="display: block;">
			 	    	<div id="divShow1" align="center" ></div>	                     
				                 <div  class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span> 
							<div id="divSerachInput" class="col-md-12">
					
				</div> 				 
			                     <table id="SearchReport" border="1" class="table no-margin table-striped  table-hover  table-bordered report_print">
				                  <thead >
				                   <tr style="text-align: center">
								           	
                                          	 <th id="">YEAR</th>	
                                             <th id="m1">JAN</th>									
								             <th id="m2">FEB</th>
								             <th id="m3">MAR</th>
								             <th id="m4">APR</th>
								             <th id="m5">MAY</th>
								             <th id="m6">JUN</th>
								             <th id="m7">JUL</th>
								             <th id="m8">AUG</th>
								             <th id="m9">SEP</th>
								             <th id="m10">OCT</th>
								             <th id="m11">NOV</th>
								             <th id="m12">DEC</th>
								              
							           </tr>
						         </thead> 
						           				<c:set var="jan" value="${0}" />
												<c:set var="feb" value="${0}" />
												<c:set var="mar" value="${0}" />
												<c:set var="apr" value="${0}" />
												<c:set var="may" value="${0}" />
												<c:set var="jun" value="${0}" />
												<c:set var="jul" value="${0}" />
												<c:set var="aug" value="${0}" />
												<c:set var="sep" value="${0}" />
												<c:set var="oct" value="${0}" />
												<c:set var="nov" value="${0}" />
												<c:set var="dec" value="${0}" />
												
												
						         <tbody>
						         
						               <c:forEach var="item" items="${list}" varStatus="num" >
						               
								            <tr style="text-align: right;" >
									           
									            <th style="text-align: center;"><fmt:formatNumber value="${item[0]}" /></th>
									            <th id="md1y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[1]}" /></th>
												<th id="md2y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[2]}" /></th> 
												<th id="md3y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[3]}" /></th> 
												<th id="md4y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[4]}" /></th> 
											    <th id="md5y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[5]}" /></th> 
											    <th id="md6y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[6]}" /></th> 
											    <th id="md7y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[7]}" /></th> 
											    <th id="md8y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[8]}" /></th> 
											    <th id="md9y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[9]}" /></th> 
											    <th id="md10y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[10]}" /></th> 
											    <th id="md11y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[11]}" /></th> 
											    <th id="md12y${num.index+1}" style="text-align: center;"><fmt:formatNumber value="${item[12]}" /></th> 
											    <c:set var="jan" value="${jan + item[1]}" />
											    <c:set var="feb" value="${feb + item[2]}" />
											    <c:set var="mar" value="${mar + item[3]}" />
											    <c:set var="apr" value="${apr + item[4]}" />
											    <c:set var="may" value="${may + item[5]}" />
											    <c:set var="jun" value="${jun + item[6]}" />
											    <c:set var="jul" value="${jul + item[7]}" />
											    <c:set var="aug" value="${aug + item[8]}" />
											    <c:set var="sep" value="${sep + item[9]}" />
											    <c:set var="oct" value="${oct + item[10]}" />
											    <c:set var="nov" value="${nov + item[11]}" />
											    <c:set var="dec" value="${dec + item[12]}" />
											      
											      
											  

								</tr>	
					                  </c:forEach>
					                  <c:if test="${list.size()!=0}">
					                    <tr style="text-align: center;" >
					                    
					                       <th>TOTAL</th> 
									       <th id="mt1"><fmt:formatNumber value="${jan}" /></th>  
									       <th id="mt2"><fmt:formatNumber value="${feb}" /></th>  
									       <th id="mt3"><fmt:formatNumber value="${mar}" /></th>  
									       <th id="mt4"><fmt:formatNumber value="${apr}" /></th>  
									       <th id="mt5"><fmt:formatNumber value="${may}" /></th>  
									       <th id="mt6"><fmt:formatNumber value="${jun}" /></th>  
									       <th id="mt7"><fmt:formatNumber value="${jul}" /></th>  
									       <th id="mt8"><fmt:formatNumber value="${aug}" /></th>  
									       <th id="mt9"><fmt:formatNumber value="${sep}" /></th>  
									       <th id="mt10"><fmt:formatNumber value="${oct}" /></th>  
									       <th id="mt11"><fmt:formatNumber value="${nov}" /></th>  
									       <th id="mt12"><fmt:formatNumber value="${dec}" /></th>
									      <%--  <th style="font-size: 17px;">${jan_max}</th>      --%>
											
										 </tr>	
									 <c:forEach var="item" items="${list2}" varStatus="num" >
								            <tr style="text-align: right;" >
									           
									            <th  style="text-align: center;">RANGE</th>
									            <th id="mr1" style="text-align: center;"><fmt:formatNumber value="${item[0]}" /></th>
												<th id="mr2" style="text-align: center;"><fmt:formatNumber value="${item[1]}" /></th> 
												<th id="mr3" style="text-align: center;"><fmt:formatNumber value="${item[2]}" /></th> 
												<th id="mr4" style="text-align: center;"><fmt:formatNumber value="${item[3]}" /></th> 
											    <th id="mr5" style="text-align: center;"><fmt:formatNumber value="${item[4]}" /></th> 
											    <th id="mr6" style="text-align: center;"><fmt:formatNumber value="${item[5]}" /></th> 
											    <th id="mr7" style="text-align: center;"><fmt:formatNumber value="${item[6]}" /></th> 
											    <th id="mr8" style="text-align: center;"><fmt:formatNumber value="${item[7]}" /></th> 
											    <th id="mr9" style="text-align: center;"><fmt:formatNumber value="${item[8]}" /></th> 
											    <th id="mr10" style="text-align: center;"><fmt:formatNumber value="${item[9]}" /></th> 
											    <th id="mr11" style="text-align: center;"><fmt:formatNumber value="${item[10]}" /></th> 
											    <th id="mr12" style="text-align: center;"><fmt:formatNumber value="${item[11]}" /></th> 
											   <c:set var="sum" value="${item[12]}" />
											  

								</tr>	
					                  </c:forEach>	 
					                   <c:set var="list_count" value="${list.size()}" />
					                   <tr style="text-align: center;" >
					                    
					                     <th>X BAR</th> 
									       <th id="mx1"><fmt:formatNumber value="${jan/list_count}" /></th>  
									       <th id="mx2"><fmt:formatNumber value="${feb/list_count}" /></th>  
									       <th id="mx3"><fmt:formatNumber value="${mar/list_count}" /></th>  
									       <th id="mx4"><fmt:formatNumber value="${apr/list_count}" /></th>  
									       <th id="mx5"><fmt:formatNumber value="${may/list_count}" /></th>  
									       <th id="mx6"><fmt:formatNumber value="${jun/list_count}" /></th>  
									       <th id="mx7"><fmt:formatNumber value="${jul/list_count}" /></th>  
									       <th id="mx8"><fmt:formatNumber value="${aug/list_count}" /></th>  
									       <th id="mx9"><fmt:formatNumber value="${sep/list_count}" /></th>  
									       <th id="mx10"><fmt:formatNumber value="${oct/list_count}" /></th>  
									       <th id="mx11"><fmt:formatNumber value="${nov/list_count}" /></th>  
									       <th id="mx12"><fmt:formatNumber value="${dec/list_count}" /></th>
									      <%--  <th style="font-size: 17px;">${jan_max}</th>      --%>
											
										 </tr>	
										 
										  <tr style="text-align: center;" >
					                    
					                     <th>UCL</th> 
									       <th id="my1"><fmt:formatNumber value="${jan/list_count+1.023*sum}" /></th>  
									       <th id="my2"><fmt:formatNumber value="${feb/list_count+1.023*sum}" /></th>  
									       <th id="my3"><fmt:formatNumber value="${mar/list_count+1.023*sum}" /></th>  
									       <th id="my4"><fmt:formatNumber value="${apr/list_count+1.023*sum}" /></th>  
									       <th id="my5"><fmt:formatNumber value="${may/list_count+1.023*sum}" /></th>  
									       <th id="my6"><fmt:formatNumber value="${jun/list_count+1.023*sum}" /></th>  
									       <th id="my7"><fmt:formatNumber value="${jul/list_count+1.023*sum}" /></th>  
									       <th id="my8"><fmt:formatNumber value="${aug/list_count+1.023*sum}" /></th>  
									       <th id="my9"><fmt:formatNumber value="${sep/list_count+1.023*sum}" /></th>  
									       <th id="my10"><fmt:formatNumber value="${oct/list_count+1.023*sum}" /></th>  
									       <th id="my11"><fmt:formatNumber value="${nov/list_count+1.023*sum}" /></th>  
									       <th id="my12"><fmt:formatNumber value="${dec/list_count+1.023*sum}" /></th>
									      <%--  <th style="font-size: 17px;">${jan_max}</th>      --%>
											
										 </tr>	
										 <tr style="text-align: center;" >
										 <th>LCL</th> 
									       <th id="mz1"><fmt:formatNumber value="${jan/list_count-1.023*sum}" /></th>  
									       <th id="mz2"><fmt:formatNumber value="${feb/list_count-1.023*sum}" /></th>  
									       <th id="mz3"><fmt:formatNumber value="${mar/list_count-1.023*sum}" /></th>  
									       <th id="mz4"><fmt:formatNumber value="${apr/list_count-1.023*sum}" /></th>  
									       <th id="mz5"><fmt:formatNumber value="${may/list_count-1.023*sum}" /></th>  
									       <th id="mz6"><fmt:formatNumber value="${jun/list_count-1.023*sum}" /></th>  
									       <th id="mz7"><fmt:formatNumber value="${jul/list_count-1.023*sum}" /></th>  
									       <th id="mz8"><fmt:formatNumber value="${aug/list_count-1.023*sum}" /></th>  
									       <th id="mz9"><fmt:formatNumber value="${sep/list_count-1.023*sum}" /></th>  
									       <th id="mz10"><fmt:formatNumber value="${oct/list_count-1.023*sum}" /></th>  
									       <th id="mz11"><fmt:formatNumber value="${nov/list_count-1.023*sum}" /></th>  
									       <th id="mz12"><fmt:formatNumber value="${dec/list_count-1.023*sum}" /></th>
									      <%--  <th style="font-size: 17px;">${jan_max}</th>      --%>
											
										 </tr>	
										 </c:if>
								
										 
					                  <%-- <td style="font-size: 17px; value="${jan}""></td> --%>
					                 
					                 
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

<c:url value="getControlReport" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="search1" name="search1" modelAttribute="comd1">
	<input type="hidden" name="from_month1" id="from_month1"/>
	<input type="hidden" name="to_month1" id="to_month1"/>
	<input type="hidden" name="command1" id="command1"/>
	<input type="hidden" name="category1" id="category1"/>
	<input type="hidden" name="disease_principal1" id="disease_principal1"/>
	<input type="hidden" name="disease_type1" id="disease_type1"/>
	<input type="hidden" name="disease_subtype1" id="disease_subtype1"/>
	<input type="hidden" name="disease_mmr1" id="disease_mmr1"/>
	<input type="hidden" name="block_description1" id="block_description1"/>
	</form:form>

 <script>
 $(function() {
		$("#from_month").change(function() {
			
			var f_month = $(this).val();
			$("#to_month").attr("min", f_month);

		});
	});
 </script>
 <script>

$(document).ready(function() {
	 if("${list}"==""){
		$('#divwatermark').hide();
	}
	else{
		$('#divwatermark').show();
	} 
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	if('${from_month_withyear}' != 0){
		$("#from_month").val('${from_month_withyear}');
	}
	if('${to_month_withyear}' != 0){
		$("#to_month").val('${to_month_withyear}');
	}
	if('${category1}'!=""){
		$("#category").val('${category1}');
		}
	if('${command1}'!=""){
		$("#command").val('${command1}');
	}
	if('${disease_principal1}'!=""){
		$("#disease_principal").val('${disease_principal1}');
	}
	if('${disease_type1}'!=""){
		$("#disease_type").val('${disease_type1}');
	}
	if('${disease_subtype1}'!=""){
		$("#disease_subtype").val('${disease_subtype1}');
	}
	if('${disease_mmr1}'!=""){
		$("#disease_mmr").val('${disease_mmr1}');
	}
	if('${block_description1}'!=""){
		$("#block_description").val('${block_description1}');
	}
	
	var from_month ='${from_month}';
	var to_month ='${to_month}';
	if(from_month!="" && to_month!=""){
		for(i=1;i<=12;i++){
			
			if(i>=from_month && i<=to_month){
				
			}
			else{
				
				$("#m"+i).hide();
				$("#mx"+i).hide();
				for(j=1;j<=12;j++){
				
					$("#md"+i+"y"+j).hide();
				}
				$("#mt"+i).hide();
				$("#mr"+i).hide();
				$("#my"+i).hide();
				$("#mz"+i).hide();
			}
			
		}
	}
	
});

function SearchData(){
	
	
    $("#from_month1").val($("#from_month").val()); 
    $("#to_month1").val($("#to_month").val()); 
    if ($("select#category").val() =="-1"){
    	alert("PLEASE SELECT CATEGORY NAME")
    	
   	 	return false ;
    }  
    
    if ($("#from_month").val() ==""){
    	alert("PLEASE SELECT MONTH FROM")
    	
   	 	return false ;
    }   
    if ($("#to_month").val() ==""){
    	alert("PLEASE SELECT TO MONTH")
    	
   	 	return false ;
    }   
    
    $("#command1").val($("#command").val()); 
    $("#category1").val($("#category").val()); 
    $("#disease_principal1").val($("#disease_principal").val()); 
    $("#disease_type1").val($("#disease_type").val()); 
    $("#disease_subtype1").val($("#disease_subtype").val()); 
    $("#disease_mmr1").val($("#disease_mmr").val()); 
    $("#block_description1").val($("#block_description").val()); 
    
	document.getElementById('search1').submit();
}






function principle(obj) {

	var data_valuep = obj.value;

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		$.post("getdisease_principal_mmr?"+key+"="+value, {disease_principal :data_valuep,col_name:obj.id}).done(function(j) {
			
		
			var options = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options1 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options2 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options3 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
		  	   
			if(data_valuep != "-1"){
  	    	for ( var i = 0; i < j.length ;i++) {
  	    		if(j[i][0] != "" && j[i][0] != null && j[i][0] !="null")
	  	    		options += '<option value="' + j[i][0] + '" name="' + j[i][0] + '" >' + j[i][0] + '</option>';
	  	    		
  	    		if(j[i][1] != "" && j[i][3] != null && j[i][3] !="null")
	  	    		options1 += '<option value="' + j[i][3] + '" name="' + j[i][3] + '" >' + j[i][3] + '</option>';
	  	    		
  	    		if(j[i][2] != "" && j[i][2] != null && j[i][2] !="null")
	  	    		options2 += '<option value="' + j[i][2] + '" name="' + j[i][2] + '" >' + j[i][2] + '</option>';
	  	    		
	  	    		if(j[i][2] != "" && j[i][4] != null && j[i][4] !="null")
		  	    		options3 += '<option value="' + j[i][4] + '" name="' + j[i][4] + '" >' + j[i][4] + '</option>';
  			}
		
  			$("#disease_mmr").html(options); 
  			$("select#disease_type").html(options1); 
  			$("select#block_description").html(options2); 
  			$("select#disease_subtype").html(options3); 
		}
		
  		}); 
		
		} 
		
function mmr_dis(obj) {

	var data_valuep = obj.value;

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		
		$.post("getdisease_mmr?"+key+"="+value, {disease_mmr :data_valuep,col_name:obj.id}).done(function(j) {
			
			if(data_valuep != "-1"){
		 	var options = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options1 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options2 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
			var options3 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
		  	   
  	    	for ( var i = 0; i < j.length ;i++) {
  	    		if(j[i][0] != "" && j[i][0] != null && j[i][0] !="null")
	  	    		options += '<option value="' + j[i][0] + '" name="' + j[i][0] + '" >' + j[i][0] + '</option>';
	  	    		
  	    		if(j[i][1] != "" && j[i][3] != null && j[i][3] !="null")
	  	    		options1 += '<option value="' + j[i][3] + '" name="' + j[i][3] + '" >' + j[i][3] + '</option>';
	  	    		
  	    		if(j[i][2] != "" && j[i][2] != null && j[i][2] !="null")
	  	    		options2 += '<option value="' + j[i][2] + '" name="' + j[i][2] + '" >' + j[i][2] + '</option>';
	  	    		
	  	    		if(j[i][2] != "" && j[i][4] != null && j[i][4] !="null")
		  	    		options3 += '<option value="' + j[i][4] + '" name="' + j[i][4] + '" >' + j[i][4] + '</option>';
  			}  	    	
  	    	
  			 $("#disease_principal").html(options);   	    	
  			$("select#disease_type").html(options1); 
  			$("select#block_description").html(options2); 
  			$("select#disease_subtype").html(options3); 
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
			var options1 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options2 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
			var options3 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
		  	   
  	    	for ( var i = 0; i < j.length ;i++) {
  	    		if(j[i][0] != "" && j[i][0] != null && j[i][0] !="null")
	  	    		options += '<option value="' + j[i][0] + '" name="' + j[i][0] + '" >' + j[i][0] + '</option>';
	  	    		
  	    		if(j[i][1] != "" && j[i][1] != null && j[i][1] !="null")
	  	    		options1 += '<option value="' + j[i][1] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
	  	    		
  	    		if(j[i][2] != "" && j[i][2] != null && j[i][2] !="null")
	  	    		options2 += '<option value="' + j[i][2] + '" name="' + j[i][2] + '" >' + j[i][2] + '</option>';
	  	    	
	  	    	if(j[i][4] != "" && j[i][4] != null && j[i][4] !="null")
		  	    		options3 += '<option value="' + j[i][4] + '" name="' + j[i][4] + '" >' + j[i][4] + '</option>';
  			}  	    	
  	    	
  			 $("#disease_principal").html(options);   	    	
  			$("select#disease_mmr").html(options1); 
  			$("select#block_description").html(options2); 
  			$("select#disease_subtype").html(options3); 
			}
			
  			
  		}); 
			
		
		
		} 
		
		
function sub_type_dis(obj) {

	var data_valuep = obj.value;

		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		
		$.post("getdisease_type?"+key+"="+value, {disease_type :data_valuep,col_name:obj.id}).done(function(j) {
			
			if(data_valuep != "-1"){
		 	var options = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options1 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options2 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
			var options3 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
		  	   
  	    	for ( var i = 0; i < j.length ;i++) {
  	    		if(j[i][0] != "" && j[i][0] != null && j[i][0] !="null")
	  	    		options += '<option value="' + j[i][0] + '" name="' + j[i][0] + '" >' + j[i][0] + '</option>';
	  	    		
  	    		if(j[i][1] != "" && j[i][1] != null && j[i][1] !="null")
	  	    		options1 += '<option value="' + j[i][1] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
	  	    		
  	    		if(j[i][2] != "" && j[i][3] != null && j[i][3] !="null")
	  	    		options2 += '<option value="' + j[i][3] + '" name="' + j[i][3] + '" >' + j[i][3] + '</option>';
	  	    	
	  	    	if(j[i][4] != "" && j[i][4] != null && j[i][4] !="null")
		  	    		options3 += '<option value="' + j[i][4] + '" name="' + j[i][4] + '" >' + j[i][4] + '</option>';
  			}  	    	
  	    	
  			 $("#disease_principal").html(options);   	    	
  			$("select#disease_mmr").html(options1); 
  			$("select#block_description").html(options2); 
  			$("select#disease_type").html(options3); 
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
			var options1 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>';
			var options2 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
			var options3 = '<option value="' + "-1" + '">'+ "--Select--" + '</option>'; 
		  	   
  	    	for ( var i = 0; i < j.length ;i++) {
  	    		if(j[i][0] != "" && j[i][0] != null && j[i][0] !="null")
	  	    		options += '<option value="' + j[i][0] + '" name="' + j[i][0] + '" >' + j[i][0] + '</option>';
	  	    		
  	    		if(j[i][1] != "" && j[i][1] != null && j[i][1] !="null")
	  	    		options1 += '<option value="' + j[i][1] + '" name="' + j[i][1] + '" >' + j[i][1] + '</option>';
	  	    		
  	    		if(j[i][2] != "" && j[i][3] != null && j[i][3] !="null")
	  	    		options2 += '<option value="' + j[i][3] + '" name="' + j[i][3] + '" >' + j[i][3] + '</option>';
	  	    		
	  	    		if(j[i][2] != "" && j[i][4] != null && j[i][4] !="null")
		  	    		options3 += '<option value="' + j[i][4] + '" name="' + j[i][4] + '" >' + j[i][4] + '</option>';
  			}  	    	
  	    	
  			 $("#disease_principal").html(options);   	    	
  			$("select#disease_mmr").html(options1); 
  			$("select#disease_type").html(options2); 
  			$("select#disease_subtype").html(options3); 
			}
			
  			
  		}); 
			
		
		
		} 
</script>			