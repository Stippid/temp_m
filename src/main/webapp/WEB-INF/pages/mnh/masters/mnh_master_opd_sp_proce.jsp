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
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
 
<form:form action="Capture_OPD_SP_Procedure_MasterAction" id="OPD_SP_Procedure_MasterForm" method="post" class="form-horizontal" 
commandName="Capture_OPD_SP_Procedure_MasterCMD">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		           <h5> <span id="lbladd"></span> OPD SPECIAL PROCEDURE</h5>
		           <h6>
					   <span style="font-size: 12px; color: red">(To be entered by MISO)</span>
				   </h6>	 
		      </div> 
		      
		      <div class="card-body card-block">
		      <div class="row">   
		          <div class="col-md-12">
		          <div class="col-md-8">
		          <div class="row form-group">
		                <div class="col-md-4" style="text-align: left;"> 
		               	     <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Department Name</label>
		                </div>
		                 <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">	
		                <div class="col-md-8"> 
		                     <select name="department.id" id="dept_id" class="form-control-sm form-control">	
           						   <option value="-1">--Select the Value--</option>
           						   <c:forEach var="j" begin="0" end="${fn:length(ml_1)-1}">
           						       <c:set var="datap" value="${fn:split(ml_1[j],':')}"/>
           						       <c:if test="${empty datap[1]}">
           						       </c:if> 
           						   
           						       <c:if test="${not empty datap[1]}">
           						           <option value="${datap[0]}" name="${datap[1]}">${datap[1]}</option>
           						       </c:if>
							       </c:forEach>          								
					         </select>
					    </div>
					    </div>    
		          </div>
		          </div>
		          
		          <div class="col-md-12">
		          <div class="col-md-8">
		          <div class="row form-group">
			            <div class="col-md-4"> 
			               	  <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Procedure Name</label>
			            </div>
		              
		                <div class="col-md-8">
					          <input type="text" name="proc_name" id="proc_name" class="form-control-sm form-control" autocomplete ="off" maxlength="100" 
					          placeholder="Search Procedure Name..." readonly="true" onkeypress="return onlyAlphaNumeric(event);">
		                </div>
		                </div>
		                </div>
		              
		              <div class="col-md-4">
		         	 <div class="row form-group">
		                <div class="col-md-4"> 
		               	      <label for="text-input" class=" form-control-label" >Status</label>
		                </div>
		              
		                <div class="col-md-8">
		                    <select name="status" id="status" class="form-control-sm form-control">
								  <option value="ACTIVE">ACTIVE</option>
								  <option value="DEACTIVE">DEACTIVE</option>
						    </select>
		                </div>
		                </div>
		                </div>
		          </div> 
		          </div>  
		      </div>
		      
		  
              
              <div class="form-control card-footer" align="center">
                     <a href="mnh_opd_proce" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
                     <input type="submit" id="svbtn" class="btn btn-success btn-sm" value="Save" onclick="return Validate();">
                    <i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" onclick="return Search();">
                                </div>
              
              
               </div>
     		 </div>
              
               <div class="container" id="divPrint" style="display: none;">
              <div id="divShow" style="display: block;">
              <div id="divShow1" align="center" style="display: none;"></div>
		           <div class="watermarked" data-watermark="" id="divwatermark">
			            <span id="ip"></span>
                           <table id="spprocedureReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
                                  <thead >
                                      <tr>
	                                  <th style="text-align: center; width:10%;">Ser No</th>
	                                  <th style="text-align: center;">Department Name </th>
	                                  <th style="text-align: center;">Procedure Name</th>
	                                  <th style="text-align: center;">Status</th>
	                                  <th style="text-align: center;">Action</th>
                                      </tr>                                                        
                                  </thead> 
                                  <tbody>
                                   <c:if test="${list.size()==0}">
										<tr>
											<td style="font-size: 15px; text-align: center; color: red;"
												colspan="5">Data Not Available</td>
										</tr>
									</c:if>
		                             <c:forEach var="item" items="${list}" varStatus="num" >
							            <tr>
								            <td style="text-align: center; width:10%;">${num.index+1}</td>
								            <td style="text-align: left;">${item.dept_name}</td>
								            <td style="text-align: left;">${item.proc_name}</td>
								            <td style="text-align: left;">${item.status}</td>
								            <td id="thAction1"  style="text-align: center;">${item.id}</td>
							            </tr>
							         </c:forEach>
                                  </tbody>
                           </table>
       	            </div>	
       	           </div>
		      </div>   
         

</form:form>

<c:url value="spprocedureList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="dept_id1">
	  <input type="hidden" name="dept_id1" id="dept_id1"/>
	  <input type="hidden" name="proc_name1" id="proc_name1"/>
	  <input type="hidden" name="stat" id="stat"/>
</form:form> 


<c:url value="deletespprocedureURL" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>





<script>


function validate(){
	if($("#dept_id").val() == "-1"){
		alert("Please Enter the Department Name");
		$("#dept_id").focus();
		return false;
	}
	else if($("#proc_name").val() == ""){
		alert("Please Enter the Procedure Name");
		$("#proc_name").focus();
		return false;
	}
	
}   

function Search(){
	
	
	
	$("#dept_id1").val($("#dept_id").val());
    $("#proc_name1").val($("#proc_name").val());
    $("#stat").val($("#status").val());
    $("#searchForm").submit();
}

function editData(id,dept_id,proc_name){
	
	$("#id").val(id);
	$("#dept_id").val(dept_id);
	$("#proc_name").val(proc_name);
	$("#proc_name").attr('readonly',false);
}

var id=$("#id").val();
function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 
</script>


<script>
$(document).ready(function() {
	
	 if('${stat}' == "ACTIVE"){
		$("#divPrint").show();
	 }
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	}
	
	
	var q = '${dept_id1}';
	if(q != ""){ 
		$("#dept_id").val(q);
		
		if(q != "-1"){
			$("#proc_name").attr('readonly',false);
			$("#proc_name").keyup(function(){
			    var d1 = '${proc_name1}';
				$().Autocomplete2('GET','getMedDistinctProcName',proc_name,{a:d1,b:q},'','','1','','','');
			});
		}
	}
	
	var q1 = '${proc_name1}';
	if(q1 != ""){ 
		$("#proc_name").val(q1);
	}
	var q3 = '${status1}';
	if(q3 != ""){ 
		$("#stat").val(q3);
	}
	
	$("#dept_id").change(function(){
		var d_id = this.value;
		$("#proc_name").attr('readonly',false);
		
		$("#proc_name").keyup(function(){
		    var d1 = this.value;       
			$().Autocomplete2('GET','getMedDistinctProcName',proc_name,{a:d1,b:d_id},'','','1','','','');
		});
	});

});
</script>