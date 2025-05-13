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



<form:form action="createRankAction" id="Rank_MasterForm"  method="post" class="form-horizontal" commandName="createRankCMD">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header ">
		           <h5><span id="lbladd"></span> RANK CODE MASTER</h5>
		           <h6>
					   <span style="font-size: 12px; color: red">(To be entered by MISO)  </span>
				  </h6>
		      </div> 
		      
		       <div class="card-body card-block"> 
		       <div class="row">  
		          <div class="col-md-12">
		          <div class="col-md-6">
		          <div class="row form-group">
		              <div class="col-md-4"> 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Service</label>
		              </div>
		              <div class="col-md-8">
		                    <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">	
							<select name="service" id="service" class="form-control-sm form-control">	
           						<option value="-1">--Select the Value--</option>
           						<c:forEach var="item" items="${getMedSystemCode_SERVICE}">
           						     <option value="${item}">${item}</option>	
           						</c:forEach>                   								
					        </select>
		              </div>
		              </div>
		              </div>
		              
		         <div class="col-md-6">
		          <div class="row form-group">
		              <div class="col-md-4"> 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Category</label>
		              </div>
		              
		              <div class="col-md-8">
							<select name="category_code" id="category_code" class="form-control-sm form-control">	
           						<option value="-1">--Select the Value--</option>
           						<c:forEach var="item" items="${getMedSystemCode_CATEGORY}">
           						     <option value="${item}">${item}</option>	
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
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Rank Code</label>
		              </div>
		              
		              <div class="col-md-8">
		                   <input type="text" id="rank_code" name="rank_code" placeholder="Enter Rank Code..." 
		                   class="form-control-sm form-control" maxlength="10" autocomplete="off"/>
		              </div>
		              </div>
		              </div>  
		              
		              <div class="col-md-6">
		          <div class="row form-group">
		              <div class="col-md-4"> 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Rank Desc</label>
		              </div>
		              
		              <div class="col-md-8">
		                   <input type="text" id="rank_desc" name="rank_desc" placeholder="Enter Rank Description..." 
		                   class="form-control-sm form-control" maxlength="100" autocomplete="off"/>
		              </div>
		              </div>
		              </div>
		          </div>
		          </div>
		      </div>
		      
		      
		 <div class="form-control card-footer" align="center">
            <a href="mnh_rank_master" type="reset" class="btn btn-primary btn-sm"> Clear </a>
			 <input type="submit" id="save_btn" class="btn btn-success btn-sm" value="Save" onclick="return validate();" /> 
	      	<i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" onclick="return Search();">
            
		            
		            
              </div> 
              
               </div>
      		</div> 
              
            <div class="nkpageland" id="printableArea">
              <div class="container" id="divPrint" style="display: none;">
                   <div id="divShow" style="display: block;">
                   
                   <div id="divShow1" align="center" style="display: none;"></div>
			 	 
				   <div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
       <table id="SearchReport" border="1" style="width: 100%;" class="table no-margin table-striped  table-hover  table-bordered report_print">		                      <thead>
		                          <tr>
			                         <th style="text-align: center; width:10%;">Ser No</th>
			                         <th style="text-align: center;">Service </th>
			                         <th style="text-align: center;">Category </th>
			                         <th style="text-align: center;">Rank Code </th>
			                         <th style="text-align: center;">Rank Desc</th>
			                         <th style="text-align: center;">Action</th>
		                          </tr>                                                        
		                      </thead> 
		                      <tbody>
			                       	<c:if test="${list.size()==0}">
										<tr>
											<td style="font-size: 15px; text-align: center; color: red;"
												colspan="4">Data Not Available</td>
										</tr>
									</c:if>
		                           <c:forEach var="item" items="${list}" varStatus="num" >
							          <tr>
										 <td style="text-align: center; width:10%;">${num.index+1}</td>
										 <td style="text-align: left;">${item[0]}</td>
										 <td style="text-align: left;">${item[1]}</td>
										 <td style="text-align: left;">${item[2]}</td>
										 <td style="text-align: left;">${item[3]}</td>
										 <td id="thAction1" style="text-align: center;">${item[4]} ${item[5]}</td>
							          </tr>
							       </c:forEach>
		                     </tbody>
		                 </table>
		            </div>	
			  </div> 
			   </div> 
			    </div> 
</form:form>


<c:url value="getSearchRankMaster" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="service1">
		<input type="hidden" name="service1" id="service1"/>
		<input type="hidden" name="category_code1" id="category_code1"/>
		<input type="hidden" name="rank_code1" id="rank_code1"/>
		<input type="hidden" name="rank_desc1" id="rank_desc1"/>
</form:form> 


<c:url value="deleteRankMasterURL" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>
			

<Script>




function Search(){
	if(($("select#service").val() == "-1") && ($("select#category_code").val() == "-1") && ($("#rank_code").val() == "") && ($("#rank_desc").val() == "")){
		alert("Please Select Either Service,Category,Rank Code and Rank Desc");
		$("#service").focus();
		return false;
	}
	
	 $("#service1").val($("#service").val());
	    $("#category_code1").val($("#category_code").val());
	    $("#rank_code1").val($("#rank_code").val());
	    $("#rank_desc1").val($("#rank_desc").val());
	$("#searchForm").submit();


}
function validate(){
	if($("#service").val() == "-1"){
		alert("Please select the Service.");
		$("#service").focus();
		return false;
	}
	else if($("select#category_code").val() == "-1"){
		alert("Please select the Category.");
		$("select#category_code").focus();
		return false;
	}
	else if($("#rank_code").val() == ""){
		alert("Please Enter the Rank Code.");
		$("#rank_code").focus();
		return false;
	}
	else if($("#rank_desc").val() == ""){
		alert("Please Enter the Rank Desc.");
		$("#rank_desc").focus();
		return false;
	}
}


$(document).ready(function() {
	
	
	if('${service1}' != "" || '${category_code1}' != ""){
		$("#divPrint").show();
	}
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	} 
	
	
	
	
	
	
	
	var q = '${service1}';
	if(q != ""){ 
		$("#service").val(q);
	}
	
	var q1 = '${category_code1}';
	if(q1 != ""){ 
		$("#category_code").val(q1);
	}
	
	var q2 = '${rank_code1}';
	if(q2 != ""){ 
		$("#rank_code").val(q2);
	}
	
	var q3 = '${rank_desc1}';
	if(q3 != ""){ 
		$("#rank_desc").val(q3);
	}

});


function editData(id,service,category_code,rank_code,rank_desc){
	document.getElementById('lbladd').innerHTML = "UPDATE";
	$("#id").val(id);;
	$("#service").val(service);
	$("#category_code").val(category_code);
	$("#rank_code").val(rank_code);
	$("#rank_desc").val(rank_desc);
	
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 
</Script>