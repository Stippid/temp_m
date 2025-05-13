<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<form:form action="D_Surve_MasterAction" id="D_Surve_Master" name="D_Surve_Master" method="post" 
class="form-horizontal" commandName="D_Surve_MasterCMD">
      <div class="container" align="center">
          <div class="card">
              <div class="card-header">
		             <h5><span id="lbladd"></span> DISEASE SURVEILLANCE</h5><h6><span style="font-size: 12px; color: red">(To be entered by MISO)</span></h6>
		      </div> 
		      
		      <div class="card-body card-block">   
		              <div class="col-md-12 form-group">
			              <div class="col-md-2"> 
			               	   <label class="form-control-label"><strong style="color: red;">* </strong>Disease Name</label>
			              </div>
			              <div class="col-md-10">
			                  <input type="text" id="disease_name" name="disease_name" onkeypress="return onlyAlphaNumeric(event);" class="form-control-sm form-control" autocomplete="off" maxlength="150"
			                  placeholder="Search Disease Name...">
			                  <input type="hidden" id="id" name="id" value="0">
			              </div>  
		              </div>
		              
		         <div class="col-md-12">
		            <div class="col-md-6">
		              <div class="row from-group">
	                      <div class="col-md-4"> 
			               	   <label class="form-control-label"><strong style="color: red;">* </strong>Disease Type</label>
			              </div>
		                     
	                      <div class="col-md-8">
	                          <input type="text" id="disease_type" name="disease_type" onkeypress="return onlyAlphaNumeric(event);" class="form-control-sm form-control" maxlength="50"
	                          placeholder="Search..." autocomplete="off">
	                      </div>
		               </div>
				  </div>
		                      
		      <div class="col-md-6">
		          <div class="row from-group">
                      <div class="col-md-4" > 
		               	    <label class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
		              </div>
		              <div class="col-md-8">
		                  <select name="status" id="status" class="form-control-sm form-control">
		                   
								<option value="1">ACTIVE</option>
								<option value="0">DEACTIVE</option>
						 </select>
		              </div> 
                   </div>
	         </div>
		 </div>
     </div>
		      
              <div class="card-footer" align="center" >
              <a href="mnh_daily_dSurve" type="reset" class="btn btn-primary btn-sm"> Clear </a> 
               <input type="submit" id="save_btn" class="btn btn-success btn-sm" value="Save" onclick="return validate();" /> 
	      		<i class="fa fa-search"></i><input type="button" id="btdog" class="btn btn-primary btn-sm" value="Search" onclick="return Search();">    
              
              </div>
              
              
               
              
          </div>
      </div>
</form:form>
              <div class="container" id="divPrint" >
					<div class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
						<table id="dSurveReport10" class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead >
								<tr>
									<th style="text-align: center; width:10%;">Ser No</th>
									<th style="text-align: center;">Disease Name</th>
									<th style="text-align: center;">Disease Type</th>
									<th style="text-align: center;">Status</th>
									<th style="text-align: center;">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${list}" varStatus="num">
									<tr>
										<td style="text-align: center; width:10%;">${num.index+1}</td>
										<td  style="text-align: left;">${item[0]}</td>
										<td  style="text-align: left;">${item[1]}</td>
										<c:if test="${item[2] == '1'}">
									       <td>ACTIVE</td>
								        </c:if>
										 <c:if test="${item[2] == '0'}">
											<td>DEACTIVE</td>			
										</c:if> 
										<td id="thAction1" style="text-align: center;">${item[3]}${item[4]}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
			  </div>
			  
			  
<c:url value="SearchDailyDiseaseSvl" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="disease_name1">
	<input type="hidden" name=disease_name1 id="disease_name1" value="0"/>
	<input type="hidden" name=disease_type1 id="disease_type1" value="0"/>
	<input type="hidden" name="status1" id="status1" value="0"/>
</form:form>

		
<c:url value="deleteDailyDiseaseSvl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>

<script>

function validate(){
	if($("input#disease_name").val().trim() == ""){
		alert("Please Enter the Disease Name");
		$("#disease_name").focus();
		return false;
	}
	if($("input#disease_type").val().trim() == ""){
		alert("Please Enter the Disease type");
		$("#disease_type").focus();
		return false;
	}
	
	return true;
	
	
}

function Search(){
	
	
	
	$("#disease_name1").val($("#disease_name").val());
	$("#disease_type1").val($("#disease_type").val());
	$("#status1").val($("#status").val());
	$("#searchForm").submit();
}

function editData(id,disease_name,disease_type,status){
	document.getElementById('lbladd').innerHTML = "UPDATE";
	 $("#id").val(id);
	 $("#disease_name").val(disease_name);
	 $("#disease_type").val(disease_type);
	 $("#status").val(status);
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
} 

$(document).ready(function() {
	
	 if('${list.size()}' == ""){
		   $("div#divPrint").hide();
		 }
	  
		
		$("#disease_name").val('${disease_name1}');
		$("#disease_type").val('${disease_type1}');
		
		$("#status").val('${status1}');
		if('${status1}' == "")
		{
			$("#status").val('1');
		}
});

$("#disease_name").keyup(function(){
    var d1 = this.value;   
	$().Autocomplete2('GET','getMedDiseaseName',disease_name,{enc:"1",a:d1},'','','1','','','');
});

$("#disease_type").keyup(function(){
    var d1 = this.value;   
	$().Autocomplete2('GET','getMedDiseaseType',disease_type,{enc:"1",a:d1},'','','1','','','');
});
</script>