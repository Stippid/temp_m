<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 <script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css" >
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
 <script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
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

<form:form action="strength_details_inputAction" id="strength_details_input" method="post"  class="form-horizontal" commandName="strength_details_inputCMD">
	<div class="">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		             <b>STRENGTH DETAILS</b>
		             <h6>
						<span style="font-size: 12px; color: red">(To be entered by MISO)</span>
					 </h6>
		      </div> 
		      
		      <div class="card-body card-block">    
		           <div class="col-md-12">
		                <div class="col-md-2" style="text-align: left;">
                 			 <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Command</label>
               			</div>
		            			  	 
		            	<div class="col-md-4">
		            	
		          
		            	
		            	     <select name="cmd" id="cmd" data-placeholder="Select the value..." class="form-control-sm form-control" onkeyup="totalcal();"  onchange="fn_cmd(this);">
								   <option value="-1">--Select the Command--</option>
								  <%--  <c:if test="${not empty ml_1[0]}"> 
								         <c:set var="data" value="${ml_1[0]}"/>
    								     <c:set var="datap" value="${fn:split(data,',')}"/>
    								    
	    								 <c:forEach var="j" begin="0" end="${fn:length(datap)-1}">
	    								     <c:set var="dataf" value="${fn:split(datap[j],':')}"/>
	    								     <option value="${dataf[0]}" name="${dataf[2]}">${dataf[2]}</option>
	    								 </c:forEach>
								   </c:if> --%>
								  
		                                    <c:forEach var="item" items="${getCommandList}" varStatus="num" >
                  								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                  							</c:forEach>
							 </select>
						</div>
		                							
	               		<div class="col-md-2" style="text-align: left;">
					       <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Quarter</label>
	               		 </div>	               		 
	               		 <div class="col-md-4">
	             			    <select name="qtr" id="qtr"  class="form-control-sm form-control">
               					      <option value="-1">--Select--</option>
								<c:forEach var="item" items="${ml_2}" varStatus="num">
									<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
								</c:forEach>
					                </select>
	               		 </div>
				    </div>
					
					<div class="col-md-12">    
					    <div class="col-md-2" style="text-align: left;"> 
					    <input type="hidden" id="id_hid" name="id_hid"> 
	                 		 <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Year</label>
	               	    </div>
	               	    
	               	    <div class="col-md-2">
					         <input type="text" id="year" name="year" class="form-control-sm form-control" maxlength="4" 
					         autocomplete="off" onkeypress="return isNumberPointKey(event);"  onchange="Checkyear(this)">
					    </div>				           
				    </div>
             </div>
					    
			 <div class="card-header"  id="a1"> <strong>Officer</strong></div>
			 <div class="card-body card-block" >
				    <div class="col-md-12">							
	               		 <div class="col-md-2" style="text-align: left;">
	                 		   <label for="text-input" class=" form-control-label">Male</label>
	               		 </div>
		               	 <div class="col-md-2" style="text-align: left;"> 	  
						       <input type="text" id="off_male" name="off_male1" class="form-control-sm form-control" autocomplete="off" 
						       onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
						 </div>

  						 <div class="col-md-2" style="text-align: left;">
                  			   <label for="text-input" class=" form-control-label">Female</label>
                		 </div>
                	     <div class="col-md-2">
					           <input type="text" id="off_female" name="off_female1" class="form-control-sm form-control"autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
					     </div>
  					
  						 <div class="col-md-2">
	                 		   <label for="text-input" class=" form-control-label">Gentleman Cadet</label>
	               		 </div>
	               		 <div class="col-md-2">
					           <input type="text" id="cadet" name="cadet1" class="form-control-sm form-control" autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
					     </div>
  				    </div>
  					     
				    <div class="col-md-12">							
	               		 <div class="col-md-2" style="text-align: left;">
                  			   <label for="text-input" class=" form-control-label">Lady Cadet</label>
                		 </div>
                		 <div class="col-md-2">
					           <input type="text" id="lady_cadet" name="lady_cadet1" class="form-control-sm form-control" autocomplete="off"  
					           onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
					     </div>
  				    	 
  				    	 <div class="col-md-2">
                 			   <label for="text-input" class=" form-control-label">MNS</label>
               			 </div>
               		     <div class="col-md-2">
				               <input type="text" id="mns" name="mns1" class="form-control-sm form-control" autocomplete="off" 
				               onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
				         </div>

  				         <div class="col-md-2">	
              				   <label for="text-input" class=" form-control-label">MNS Cadet</label>
            			 </div>
              			 <div class="col-md-2">
			                   <input type="text" id="mns_cadet" name="mns_cadet1" class="form-control-sm form-control" autocomplete="off" 
			                   onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
			             </div>
				    </div>
  			</div>
  				       
  		    <div class="card-header"  id="a2"> <strong>JCO/OR</strong></div>
  			<div class="card-body card-block" >
					<div class="col-md-12">							
	               		 <div class="col-md-2" style="text-align: left;">
	                 		   <label for="text-input" class=" form-control-label"> JCO </label>
	               		 </div>
	               	     <div class="col-md-2">
					           <input type="text" id="jco" name="jco1" class="form-control-sm form-control" autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
					     </div>
  						  
  				      	 <div class="col-md-2" style="text-align: left;">
	          				   <label for="text-input" class=" form-control-label"> OR </label>
	        			 </div>
		          		 <div class="col-md-2">
					           <input type="text" id="ort" name="ort1" class="form-control-sm form-control" autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
					     </div>
					
  				    	 <div class="col-md-2" style="text-align: left;">
           					   <label for="text-input" class=" form-control-label"> DSC JCO </label>
         				 </div>
                		 <div class="col-md-2">
					           <input type="text" id="dsc_jco" name="dsc_jco1" class="form-control-sm form-control" autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
					     </div>
  				    </div>
  				
  			        <div class="col-md-12">							
	               		 <div class="col-md-2" style="text-align: left;">
                  			   <label for="text-input" class=" form-control-label"> DSC OR </label>
                		 </div>
                		 <div class="col-md-2">
					           <input type="text" id="dsc_or" name="dsc_or1" class="form-control-sm form-control" autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
					     </div>
  					
  				         <div class="col-md-2" style="text-align: left;">
                  			   <label for="text-input" class=" form-control-label"> Recruit </label>
                		 </div>
                		 <div class="col-md-2" style="text-align: left;">
					           <input type="text" id="rect" name="rect1" class="form-control-sm form-control" autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
					     </div>
  					</div>
  		    </div>
  		
  	        <div class="card-header" id="a3"> <strong>High Altitude</strong></div>
  		    <div class="card-body card-block">	   
  			        <div class="col-md-12">							
	               		 <div class="col-md-2" style="text-align: left;">
	                 		   <label for="text-input" class=" form-control-label"> Officer </label>
	               		 </div>
	               		 <div class="col-md-2">
					           <input type="text" id="high_offr" name="high_offr1" class="form-control-sm form-control" autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
					     </div>
  					
  				         <div class="col-md-2" style="text-align: left;">
	          				   <label for="text-input" class=" form-control-label"> JCO</label>
	        			 </div>
		          		 <div class="col-md-2" style="text-align: left;">
					           <input type="text" id="high_jco" name="high_jco1" class="form-control-sm form-control" autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
					     </div>
  				         <div class="col-md-2" style="text-align: left;">
           					   <label for="text-input" class=" form-control-label"> OR </label>
         			     </div>
                		 <div class="col-md-2" style="text-align: left;">
					           <input type="text" id="high_or" name="high_or1" class="form-control-sm form-control" autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" placeholder="0" onkeyup="totalcal();">
					     </div>
  			        </div>
  				  
  				    <div class="col-md-12">							
	               		 <div class="col-md-2" style="text-align: left;">
                  			   <label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Total </label>
                		 </div>
                	     <div class="col-md-2" style="text-align: left;">
					           <input type="text" id="total" name="total1" class="form-control-sm form-control" autocomplete="off" 
					           onkeypress="return isNumberPointKey(event);" placeholder="0" readonly>
					     </div>
  					</div>
  	        </div> 
  	        
  	        <div class="card-footer" align="center">
				 <a href="mnh_input_strengthDetails" class="btn btn-primary btn-sm" id="btn_clc" >Clear</a>					     
		         <input type="submit" id="btn_save" class="btn btn-success btn-sm" value="Save" onclick="return isvalidData();"/> 		         
           </div>  	
  	   </div>
    </div>
  </div>
</form:form> 
 
<!-- for On Load Methods -->
<script>

function totalcal(){
	//debugger();
	
	var a1 = $("#off_male").val();
	
	if(a1 == "" || a1 == null){
		a1 = 0;
	}
	var a2 = $("#off_female").val();
	if(a2 == "" || a2 == null){
		a2 = 0;
	}
	var a3 = $("#cadet").val();
	if(a3 == "" || a3 == null){
		a3 = 0;
	}
	var a4 = $("#lady_cadet").val();
	if(a4 == "" || a4 == null){
		a4 = 0;
	}
	var a5 = $("#mns").val();
	if(a5 == "" || a5 == null){
		a5 = 0;
	}
	var a6 = $("#mns_cadet").val();
	if(a6 == "" || a6 == null){
		a6 = 0;
	}
	var a7 = $("#jco").val();
	if(a7 == "" || a7 == null){
		a7 = 0;
	}
	
	var a8 = $("#ort").val();
	if(a8 == "" || a8 == null){
		a8 = 0;
	}
	var a9 = $("#dsc_jco").val();
	if(a9 == "" || a9 == null){
		a9 = 0;
	}
	var a10 = $("#dsc_or").val();
	if(a10 == "" || a10 == null){
		a10 = 0;
	}
	var a11 = $("#rect").val();
	if(a11 == "" || a11 == null){
		a11 = 0;
	}
	var a12 = $("#high_offr").val();
	
	if(a12 == "" || a12 == null){
		a12 = 0;
	}
	var a13 = $("#high_jco").val();
	if(a13 == "" || a13 == null){
		a13 = 0;
	}
	var a14 = $("#high_or").val();
	if(a14 == "" || a14 == null){
		a14 = 0;
	}
	
	var t1 = (parseInt(a1)+parseInt(a2)+parseInt(a3)+parseInt(a4)+parseInt(a5)+
			parseInt(a6)+parseInt(a7)+parseInt(a8)+parseInt(a9)+parseInt(a10)+
			parseInt(a11)+parseInt(a12)+parseInt(a13)+parseInt(a14));

	$("#total").val(t1);
}



function isvalidData(){
	var d = new Date();
	var year = d.getFullYear();
	
	if($("#cmd").val() == "-1"){
		alert("Please Select the Command");
		$("#cmd").focus();
		return false;
	}
	 if($("#qtr").val() == "-1"){
		alert("Please select the Quarter");
		$("#qtr").focus();
		return false;
	}	
	 if($("#year").val() == ""){
		alert("Please Enter the Year");
		$("#year").focus();
		return false;
	}
	 if($("#year").val().length < 4){
		alert("Year Format Require in YYYY");
		$("#year").focus();
		return false;
	}
    if($("#year").val() > year){
		alert("Future Year cannot be allowed");
		$("#year").focus();
		return false;
	}
	 if($("#total").val() == ""){
		alert("Please Enter the total");
		$("#total").focus();
		return false;
	}
}
</script>
<script>
$(document).ready(function(){
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
	
	
	
	
	/* 
	$("#off_male").val('${list[0][0]}');
	 $("#off_female").val('${list[0][1]}');
	
	$("#high_offr").val('${list[0][2]}');
	
	$("#jco").val('${list1[0][0]}');
	$("#ort").val('${list2[0][0]}');
	
	 $("#high_jco").val('${list1[0][0]}');
	 $("#high_or").val('${list2[0][0]}');
	
	
	var a = '${list[0][2]}';
	var b = '${list1[0][0]}';
	var c = '${list2[0][0]}';
	var t = (parseInt(a)+parseInt(b)+parseInt(c));
	
	$("#total").val(t); */
});
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

function fn_cmd(obj) {
	
	var ef = $("#cmd").val();
 	
	$.post("GetAlloffDetails?" + key + "=" + value, {
		ef: ef
	}).done(function(j) {
		
		//totalcal();
		var ab = j[0][0];
		var t = j[0][1];
		 var u = j[0][2];
		
		$("#off_male").val(ab);
		$("#off_female").val(t);
		$("#high_offr").val(u);
		totalcal();
	}).fail(function(xhr, textStatus, errorThrown) {});
	
	
	
	
		$.post("GetAlljcoDetails?" + key + "=" + value, {
			ef: ef
		}).done(function(k) {
			
			//totalcal();
			 v = k[0][0];
			 w = k[0][0];
			$("#jco").val(v);
			$("#high_jco").val(w);
			totalcal();
		}).fail(function(xhr, textStatus, errorThrown) {});
		
		
		$.post("GetAllorDetails?" + key + "=" + value, {
			ef: ef
		}).done(function(m) {
		//	totalcal();
			 y = m[0][0];
			 z = m[0][0];
			 $("#ort").val(y);
			 $("#high_or").val(z);
			 totalcal();
		}).fail(function(xhr, textStatus, errorThrown) {});
		
		
	/* 	var i = j[0][2];
		var p = k[0][0];
		var n = m[0][0];
		var w = (parseInt(i)+parseInt(p)+parseInt(n));
		alert(w);
		$("#total").val(w); */
		/* var w = (parseInt(u)+parseInt(v)+parseInt(y));
		alert("www------"+w);
		$("#total").val(w); */
		
		
		
}
</script>