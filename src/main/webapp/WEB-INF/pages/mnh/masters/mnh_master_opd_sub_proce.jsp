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
<form:form action="Capture_OPD_SUB_Procedure_MasterAction" id="Capture_OPD_SUB_Procedure_MasterForm" method="post" 
class="form-horizontal" commandName="Capture_OPD_SUB_Procedure_MasterCMD">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
                  <h5>OPD SPECIAL SUB PROCEDURE</h5>
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
		              
		              <div class="col-md-8">
		                  <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
		                  <select name="department_subproc.id" id="dept_id" class="form-control-sm form-control">	
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
		          
		          <div class="col-md-12" >
		          <div class="col-md-8">
                  <div class="row form-group">
		              <div class="col-md-4"> 
		               	   <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Procedure Name</label>
		              </div>
		              
		              <div class="col-md-8">
		                  <select name="proc_id1" id="proc_id" class="form-control-sm form-control" >	
		                      <option value="-1">--Select the Value--</option>
		                  </select>
		              </div>
		          </div>
		          </div>
		          </div>
		          
		          <div class="col-md-12">
		          <div class="col-md-8">
                  <div class="row form-group">
		              <div class="col-md-4" > 
		               	   <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Sub Procedure Name</label>
		              </div>
		              
		              <div class="col-md-8">
					      <input type="text" name="subproc_name" id="subproc_name" class="form-control-sm form-control" autocomplete ="off" maxlength="100" 
					      placeholder="Search Sub Procedure Name..." onkeypress="return onlyAlphaNumeric(event);">
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
              <a href="mnh_opd_sub_proce" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
               <input type="submit" id="svbtn" class="btn btn-success btn-sm" value="Save" onclick="return Validate();">
              <i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" onclick="return Search();">
                                </div>
              
              
                </div>
      	</div> 
              
              <div class="container" id="divPrint" style="display: none;">
				    <div class="watermarked" data-watermark="" id="divwatermark">
						 <span id="ip"></span>
                         <table id="spsubprocedureReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
                               <thead >
                                      <tr>
			                              <th style="text-align: center; width:10%;">Ser No</th>
			                              <th style="text-align: center;">Department Name </th>
			                              <th style="text-align: center;">Procedure Name</th>
			                              <th style="text-align: center;">Sub Procedure Name</th>
			                              <th style="text-align: center;">Status</th>
			                              <th style="text-align: center;">Action</th>
                                      </tr>                                                        
                              </thead> 
                              <tbody>
                                    <c:if test="${list.size()==0}">
										<tr>
											<td style="font-size: 15px; text-align: center; color: red;"
												colspan="6">Data Not Available</td>
										</tr>
									</c:if>
                                    <c:forEach var="item" items="${list}" varStatus="num" >
									     <tr>
											<td style="text-align: center; width:10%;">${num.index+1}</td>
											<td style="text-align: left;">${item.dept_name}</td>
											<td style="text-align: left;">${item.proc_name}</td>
											<td style="text-align: left;">${item.subproc_name}</td>
											<td style="text-align: left;">${item.status}</td>
											<td id="thAction1" style="text-align: center;">${item.id}</td>
										</tr>
					                </c:forEach>
                              </tbody>
                          </table>
        	        </div>	
        	       
			  </div>    
	    
  	 
</form:form>

<c:url value="spsubprocedureList" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="dept_id1">
	<input type="hidden" name="dept_id1" id="dept_id1"/>
	<input type="hidden" name="proc_id1" id="proc_id1"/>
	<input type="hidden" name="subproc_name1" id="subproc_name1"/>
	<input type="hidden" name="stat" id="stat"/>
</form:form> 

<c:url value="deletespsubprocedureURL" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>



   		
<script>

 function Validate(){
	 
	if($("select#dept_id").val() == "-1"){
		alert("Please select the  Department Name");
		$("#dept_id").focus();
		return false;
	}
	if($("input#subproc_name").val().trim() == ""){
		alert("Please select the  Sub Procedure Name.");
		$("#subproc_name").focus();
		return false;
	}
	
	
	return true;
} 

function Search(){	
	$("#dept_id1").val($("#dept_id").val());
    $("#proc_id1").val($("#proc_id").val());
    $("#subproc_name1").val($("#subproc_name").val());
    $("#stat").val($("#status").val());
    $("#searchForm").submit();
}

function editData(id,dept_id,proc_id,subproc_name){
	$("#id").val(id);
	$("#dept_id").val(dept_id);
	chgMedProceCode(dept_id,proc_id);
	$("#subproc_name").val(subproc_name);
	$("#proc_name").attr('readonly',false);
	$("#subproc_name").attr('readonly',false);
}
	
var id=$("#id").val();

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 

function chgMedProceCode(d_id,p_id){
	
	$.post("getMedProceCode?"+key+"="+value,{d_id:d_id,enc:"1"},function(j){
		if(j.length <= 0 || j == null || j == ''){
			
 			$("#dept_id").focus();
 		}else{
 			
 			var options = '<option value="-1">--Select the Value--</option>';
 			
 			var a = [];
 			var enc = j[j.length-1].substring(0,16);
			for(var i = 0; i < j.length; i++){
				a[i] = dec(enc,j[i]);
            }
			
			for(var i = 0;i < a.length-1;i++){
				datap=a[i].split(":");
				options += '<option value="'+datap[0]+'" name="' + datap[1]+ '" >' + datap[1]+ '</option>';	
			}
 			$("select#proc_id").html(options);
 	
 			var q1 = '${proc_id1}';
 			if(q1 != ""){ 
 				$("#proc_id").val(q1);
 			}
 			if(p_id != ""){
 				$("#proc_id").val(p_id);
 			}
 		}
	});
}
</script>
<script>
$(document).ready(function() {
	if('${list.size()}' != "" ){
		$("#divPrint").show();
	}
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	}
	
	
	
	$('#dept_id').change(function(){
		var d_id=this.value;
		chgMedProceCode(d_id,"");
		
		if(d_id == "-1")
		{
			$("#proc_id").attr('readonly',true);
			$("#proc_id").val("-1");
			$("#subproc_name").attr('readonly',true);
		}
		$("#proc_id").attr('readonly',false);
		$("#subproc_name").attr('readonly',false);
		
	});  
	
	$('#proc_id').change(function(){
		var d_id=$("#dept_id").val();
		var p_id=this.value;
		$("#subproc_name").attr('readonly',false);
		
		$("#subproc_name").keyup(function(){
		    var d1 = this.value;       
			$().Autocomplete2('GET','getMedDistinctSubProcName',subproc_name,{a:d1,b:d_id,c:p_id},'','','1','','','');
		});	
		
		if(p_id == "-1")
		{
			
			$("#subproc_name").val("");
		}
	});  
	
	var q = '${dept_id1}';
	var q1 = '${proc_id1}';
	var q2 = '${subproc_name1}';
	
	if(q != ""){
		if(q != -1){
			chgMedProceCode(q,"");
		}
		$("#dept_id").val(q);
	}
	
	if(q1 != ""){ 
		if(q1 != "-1"){
			$("#subproc_name").attr('readonly',false);
			
			$("#subproc_name").keyup(function(){
			    var d1 = this.value;       
				$().Autocomplete2('GET','getMedDistinctSubProcName',subproc_name,{a:q2,b:q,c:q1},'','','1','','','');
			});	
		}
	}
	
	if(q2 != ""){ 
		$("#subproc_name").val(q2);
	}
	
	var q3 = '${stat}';
	if(q3 != ""){ 
		$("#status").val(q3);
	}
	
});	
</script>