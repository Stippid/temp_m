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
<script>
var username="${username}";
</script>


<form:form action="" id="" method="post" class="form-horizontal" commandName="">

      <div class="container" align="center">
          <div class="card">
              <div class="card-header mnh-card-header">
		            <h5>VIEW AND PRINT SURVEILLANCE COMMAND WISE</h5>		            
		      </div> 
		      <div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;" id="aa"> <strong>View and Print Command Wise Daily Surveillance Report</strong></div>                
               <div class="card-body card-block">    					
  				 <div class="row">      					
  					<div class="col-md-12">
  					<div class="col-md-8">
							<div class="row form-group">
		                 <div class="col-md-3" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Command</label>
	               		 </div>	               		 
	               		 <div class="col-md-9">
	             			  <select name="level_c" id="level_c" class="form-control-sm form-control">
	                  		</select>
	               		 </div>	
	               		 </div>
	               		 </div>
	               		 
	               		      
  					<div class="col-md-4 ">       
  					 <div class="row form-group">  		   
		              <div class="col-md-4" style="text-align: left;">
	                 		  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Type</label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
	             			 <select id="type" name="type" class="form-control-sm form-control">
                						<option value="servicing">SERVING</option>
                						<option value="ex-serviceman">EX-SERVICEMAN</option>
	                         </select>
	               		 </div>	
	               		 </div>
	               		 </div>
	               </div>   
	               </div>            		 
		      </div>		      
	
		      <div class="card-footer" align="center">
			     <a href="opd_spl_proc" type="reset" class="btn btn-primary btn-sm"> Clear </a>  
		        <i class="fa fa-search"></i> <input type="button" id="btn_serach" class="btn btn-success btn-sm"  value="Search" onclick="return isvalidData()" />      		         
              </div>
              </div>      
              <div class="container"  id="divPrint" style="display: none;">
                   
			 	       <div id="divShow1" align="center" style="display: none;"></div>	              
				        <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				             <span id="ip"></span>	
				 <div id="divSerachInput" class="col-md-12">
					<div class="col-md-6">
						<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Word"  size="35" class="form-control">			
				</div> 
				</div>				 
				             <table id="surveillancereport" class="table no-margin table-striped  table-hover  table-bordered report_print">
				                  <thead style="background-color: #9c27b0; color: white;">
							             <tr style="text-align:center">
								            <th >Ser No</th>
								            <th >Investigation</th>
								            <th >Target Disease</th>
								            <th >Target Sub Disease</th>
								            <th >Type</th>
								            <th >Test Serving</th>
								            <th >Positive Serving</th>
								            <th >Test Family</th>
								            <th >Positive Family</th>
								            <th  width="10%">Action</th>
							           </tr>
						         </thead> 
						         <tbody>
						               <c:forEach var="item" items="${list}" varStatus="num" >
								           <tr>
									          <td style="text-align: center;">${num.index+1}</td>
                                              <td style="text-align: left;">${item.username}</td>
									          <td style="text-align: left;">${item.sus_no}</td>
									          <td   style="text-align:left">${item.unit_name}</td>
									          <td id="thAction1" style="text-align: center;" width="10%">${item.id}</td>
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

</form:form>





<script>
$(document).ready(function() {
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#SearchReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	

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