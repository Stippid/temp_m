<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<link rel="stylesheet" href="js/common/select2/select2.min.css">
<script>
	var role = "${role}";
</script>

<body onload="setMenu();">
<form:form name="Head_MasterForm" id="Head_MasterForm" action="createHeadAction?${_csrf.parameterName}=${_csrf.token}" method="POST" class="form-horizontal fp-form" commandName="createHeadCMD"> 
      <div class="containerr" align="center">
          <div class="card">
	          <div class="card-header mms-card-header" style="min-height:60px;padding-top: 10px;">
	                <b>CREATE BUDGET HEAD</b>            		
	           </div>	
		       <div class="card-body card-block ncard-body-bg"> 
		       <div class="row">  
		          <div class="col-md-12">
		          <div class="col-md-6">
		          <div class="row form-group">
		              <div class="col-md-3"> 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Head Code</label>
		              </div>
		              <div class="col-md-9">
		             	 	<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
		                    <input type="text" id="head_code" name="head_code" class="form-control char-counter1" autocomplete="off" maxlength="10" title="Enter Head Code">	
		              </div>
		              </div>
		              </div>
		              
		         <div class="col-md-6">
		         <div class="row form-group">
		              <div class="col-md-3"> 
		               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Head Description</label>
		              </div>
		              
		              <div class="col-md-9">
							 <input type="text" id="head_desc" name="head_desc" class="form-control char-counter2" autocomplete="off" size="100" maxlength="200" title="Enter Head Description">	
		              </div>
		         </div>
		         </div>  
		         </div>
		          
		          <div class="col-md-12" >
		          <div class="col-md-6">
		          <div class="row form-group">
		              <div class="col-md-3" > 
		               	   <label class=" form-control-label">Major Head</label>
		              </div>
		              
		              <div class="col-md-9">
		                   <select name="major_head" id="major_head" class="form-control-sm form-control select" title="Select Major Head">	
           						<option value="-1">--Select Major Head--</option>
           						<c:forEach var="item" items="${getMajorHeadCode}">
           						     <option value="${item[2]}">${item[0]} - ${item[1]}</option>	
           						</c:forEach>                   								
					        </select>
		              </div>
		              </div>
		              </div>  
		              
		              <div class="col-md-6">
		          <div class="row form-group">
		              <div class="col-md-3"> 
		               	   <label class=" form-control-label">Minor Head</label>
		              </div>
		              
		              <div class="col-md-9">
		                  <select name="minor_head" id="minor_head" class="form-control-sm form-control select" title="Select Minor Head">	
           						<option value="-1">--Select Minor Head--</option>
           						<c:forEach var="item" items="${getMinorHeadCode}">
           						     <option value="${item[0]}">${item[0]} - ${item[1]}</option>	
           						</c:forEach>                   								
					        </select>
		              </div>
		              </div>
		              </div>
		          </div>
		          
		          <div class="col-md-12" >
		          <div class="col-md-6">
		          <div class="row form-group">
		              <div class="col-md-3" > 
		               	   <label class=" form-control-label">Sub Head</label>
		              </div>
		              
		              <div class="col-md-9">
		                   <select name="sub_head" id="sub_head" class="form-control-sm form-control select" title="Select Sub Head.">	
           						<option value="-1">--Select Sub Head--</option>
           						<c:forEach var="item" items="${getSubHeadCode}">
           						      <option value="${item[0]}">${item[0]} - ${item[1]}</option>	
           						</c:forEach>                   								
					        </select>
		              </div>
		              </div>
		              </div>  
		              
		                <div class="col-md-6">
		          <div class="row form-group">
		              <div class="col-md-3"> 
		               	   <label class=" form-control-label">Status</label>
		              </div>
		              
		              <div class="col-md-9">
		                   <select name="status" id="status" class="form-control-sm form-control select" title="Select Status.">	
           						     <option value="1">Active</option>
           						     <option value="2">Inactive</option>	
   				        </select>
		              </div>
		              </div>
		              </div>
		          </div>
		      </div>
		      
		      <div class="card-footer" align="center">
		         	<input type="submit" id="save_btn" class="btn btn-success btn-sm nGlow" value="Save" onclick="return validate()" title="Click to Save Data."/>
		            <input type="button" id="search_btn" class="btn btn-primary btn-sm nGlow" value="Search" onclick="Search();" title="Click to Search Data."> 
              </div> 
               </div>
      		</div> 
             
              <div class="containerr" id="divPrint">
              <div  class="watermarked" data-watermark="" id="divwatermark">
				     <div id="divSerachInput" class="col-md-6">
						<div class="col-md-12">
							<input id="searchInput" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Code Head"  size="35" class="form-control-sm" title="Search Code Head.">			
						</div> 
					 </div>
					 <div class="nPopTable" style="height:300px;width:100%;overflow: auto;background-color: white;">
		                 <table id="RankMasterReport"> 
		                      <thead>
		                          <tr style="font-size: 1.2vw">
			                         <th style="text-align: center;width:5%">SNo</th>
			                         <th style="text-align: center;width:10%">Code Head</th>
			                         <th style="text-align: center;width:55%">Head </th>
			                         <th style="text-align: center;width:15%">Status </th>
			                         <th style="text-align: center;width:15%">Action</th>
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
										 <td style="text-align: center;">${num.index+1}</td>
										 <td title="Code Head - ${item[3]}">${item[3]}</td>
										 <td title="Head - ${item[1]}">${item[1]}</td>
										 <c:if test="${item[2]==1}">
										 	<td title="Status - Active">Active</td>
										 </c:if>
										 <c:if test="${item[2]!=1}">
										 	<td title="Status - Inactive">Inactive</td>
										 </c:if>										 
										 <td id="thAction1" style="text-align: center;" title="Click to Edit">${item[5]}</td>
							          </tr>
							       </c:forEach>
		                     </tbody>
		                 </table>
		                 </div>
		                 </div>
			  </div> 
</form:form>
<script src="js/miso/miso_js/jquery-2.2.3.min.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/common/select2/select2.min.js" type="text/javascript"></script>

<c:url value="getSearchHeadMaster?${_csrf.parameterName}=${_csrf.token}" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" class="fp-form" name="searchForm" modelAttribute="service1">
		<input type="hidden" name="head_code1" id="head_code1"/>
		<input type="hidden" name="head_desc1" id="head_desc1"/>
		<input type="hidden" name="major_head1" id="major_head1"/>
		<input type="hidden" name="minor_head1" id="minor_head1"/>
		<input type="hidden" name="sub_head11" id="sub_head11"/>
</form:form> 

<c:url value="deleteHeadMasterURL?${_csrf.parameterName}=${_csrf.token}" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" class="fp-form" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1"/> 
</form:form>
			
<Script>
function Search(){
    $("#head_code1").val($("#head_code").val());
    $("#head_desc1").val($("#head_desc").val());
    $("#major_head1").val($("#major_head").val());
    $("#minor_head1").val($("#minor_head").val());
    $("#sub_head11").val($("#sub_head").val());
    $("#searchForm").submit();	 
}


function validate(){
	if($("#head_code").val() == ""){
		alert("Please Enter the Head Code.");
		$("#head_code").focus();
		return false;
	}
	if($("#head_desc").val() == ""){
		alert("Please Enter the Head Description.");
		$("#head_desc").focus();
		return false;
	}
	if($("#major_head").val() == "-1"){
		alert("Please Select the Major Head.");
		$("#major_head").focus();
		return false;
	}
	/* if($("#minor_head").val() == ""){
		alert("Please Select the Minor Head.");
		$("#minor_head").focus();
		return false;
	}
	if($("#sub_head").val() == ""){
		alert("Please Select the Sub Head.");
		$("#sub_head").focus();
		return false;
	} */
}


$(document).ready(function() {
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	
	$(".select").select2();

	$(".char-counter1").charCounter();
	$(".char-counter2").charCounter();
	
	$("#searchInput").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#RankMasterReport tbody tr").filter(function() { 
		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	
	
	if('${major_head}' != "" || '${head_desc}' != ""){
		$("#divPrint").show();
	}
	
	if('${size}' == 0 && '${size}' != ""){
		$("#divPrint").show();
	} 
	
	$("#divPrint").show();
	
	var q = '${head_code1}';
	if(q != ""){ 
		$("#head_code").val(q);
	}
	
	var q1 = '${head_desc1}';
	if(q1 != ""){ 
		$("#head_desc").val(q1);
	}
	
	var q2 = '${major_head1}';
	if(q2 != ""){ 
		$("#major_head").val(q2);
	}
	
	var q3 = '${minor_head1}';
	if(q3 != ""){ 
		$("#minor_head").val(q3);
	}
	
	var q4 = '${sub_head11}';
	if(q4 != ""){ 
		$("#sub_head").val(q4);
	}
});

function editData(id,head_code,head_desc,major_head,minor_head,sub_head,status){
	document.getElementById('save_btn').value = "UPDATE";
	$("#id").val(id);
	head_code = head_code ? (head_code == "null" ? "" : head_code)  : "";
	console.log(sub_head);

	$("#head_code").val(head_code);
	$("#head_desc").val(head_desc);
	$("#major_head").val(major_head);
	//$(".select").trigger("change");
	$("#minor_head").val(minor_head);
	//$(".select").trigger("change");
	$("#sub_head").val(sub_head);
	//$(".select").trigger("change");

	if(status="1"){
		$("#status").val("1");
	}else{
		$("#status").val("2");
	}
	$(".select").trigger("change");
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}
</script>