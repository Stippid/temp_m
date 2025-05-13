<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
<script>
var username="${username}";
</script>
<style>
.row{
  justify-content: center;
}
</style>
<form:form action="Capture_OPD_SP_Dept_MasterAction" id="Capture_OPD_SP_Dept_MasterForm" method="post" class="form-horizontal" 
commandName="Capture_OPD_SP_Dept_MasterCMD">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		             <h5>OPD SPECIAL DEPARTMENT</h5>
		             <h6>
					    <span style="font-size: 12px; color: red">(To be entered by MISO)</span>
					 </h6>	 
		      </div> 
		      
		      <div class="card-body card-block">
		      <div class="row">   
		          <div class="col-md-12 ">
		          <div class="col-md-8 ">
		          <div class="row form-group ">
		              <div class="col-md-4" style="text-align: left;"> 
		               	   <label class="form-control-label"><strong style="color: red;">* </strong>Department Name</label>
		              </div>
		              
		              <div class="col-md-8">
		                 
		                  <div class="col-md-6"></div>
		                    <input type="text" name="dept_name" id="dept_name" class="form-control-sm form-control" autocomplete ="off" maxlength="100"
		                  placeholder="Search Department Name..." onkeypress="return onlyAlphaNumeric(event);">
		              </div> 
		              </div>
		              </div>
		              
		              <div class="col-md-4 ">
		          <div class="row form-group ">
		              <div class="col-md-4"> 
		               	   <label class="form-control-label"><strong style="color: red;">* </strong>Status</label>
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
             <input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">	
		                
            <div class="form-control card-footer" align="center">
             <a href="mnh_opd_dep" type="reset" class="btn btn-primary btn-sm"> Clear </a>
              <input type="submit" id="svbtn" class="btn btn-success btn-sm" value="Save"  onclick="return validate();">
             <i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" onclick="return Search();">
                                </div>
             
             </div>
      	</div>
              
             <div class="container" id="divPrint" style="display: none;">
              <div id="divShow" style="display: block;">
              <div id="divShow1" align="center" style="display: none;"></div>
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="OpdSpDepReport" class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead >
								<tr>
									<th style="text-align: center; width:10%;">Ser No</th>
									<th style="text-align: center;">Department Name</th>
									<th style="text-align: center;">Status</th>
									<th style="text-align: center;">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${list}" varStatus="num">
									<tr>
										<th style="text-align: center; width:10%;">${num.index+1}</th>
										<th style="text-align: left;">${item.dept_name}</th>
										<th style="text-align: left;">${item.status}</th>
										<th id="thAction1" style="text-align: center;">${item.id}</th>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
              </div>
              
          

</form:form>
<c:url value="OpdSpDepaetmentReport" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="dept_name1">
	<input type="hidden" name=dept_name1 id="dept_name1"/>
	<input type="hidden" name="status1" id="status1"/>
</form:form> 
<c:url value="deleteOpdDepart" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>



<script>
function btn_clc(){
	
	$("#dept_name").val('');
	$("#status").val('ACTIVE');
}

function validate(){
	if($("input#dept_name").val().trim() == ""){
		alert("Please Enter the Department Name");
		$("#dept_name").focus();
		return false;
	}
	if($("#status").val().trim() == ""){
		alert("Please Enter the Status");
		$("#status").focus();
		return false;
	}
	return true;
}




function Search(){
	
	
	$("#dept_name1").val($("#dept_name").val());
	$("#status1").val($("#status").val());
	$("#searchForm").submit();
	
	

}
function editData(id,dept_name,status){
	
	$("#id").val(id);;
	$("#dept_name").val(dept_name);
	$("#status").val(status);
}
function deleteData(id) {
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}


$("#dept_name").keyup(function(){
    var d1 = this.value;       
	$().Autocomplete2('GET','getMedAllDepName',dept_name,{a:d1},'','','1','','','');
});

</script>
<script>

$(document).ready(function() {
	
	
	 if('${status1}' == "ACTIVE"){
		$("#divPrint").show();
	}
	 
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	} 
	
	
	
	
	
	
	
	var q = '${dept_name1}';
	if(q != ""){ 
		$("#dept_name").val(q);
	}
	var q1 = '${status1}';
	if(q1 != ""){ 
		$("#status").val(q1);
	}
	
	

});

</script>