<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<script>
	var role = "${role}";
</script>
<body onload="setMenu();">
<form:form name="FinyrCreateForm" id="FinyrCreateForm" action="FinyrCreateFormAction?${_csrf.parameterName}=${_csrf.token}" method="POST" class="form-horizontal fp-form" commandName="FinyrCreateFormCMD"> 
      <div class="container" align="center">
          <div class="card">
	          	<div class="card-header mms-card-header" style="min-height:60px;padding-top: 10px;">
	                <b>CREATION OF FINANCIAL YEAR</b>            		
	           	</div>	
		       	<div class="card-body card-block ncard-body-bg"> 
			       	<div class="row">  
			              <div class="col-md-3"> 
			               	   <label class=" form-control-label"><strong style="color: red;">* </strong>Financial Year to be Created</label>
			              </div>
			              <div class="col-md-3">
			                     <input type="text" id="year1" name="year1" class="form-control-sm form-control" autocomplete="off" maxlength="9" style='text-align:center' readonly>
			              </div>
			              <div class="col-md-3">
			             	 	<input type="hidden" id="id" name="id" class="form-control" value="0" autocomplete="off">
			                     <input type="text" id="year" name="year" class="form-control-sm form-control char-counter1" onmouseleave="Checkyear()" autocomplete="off" maxlength="4" 
		               		       onkeypress="return isNumberPointKey(event);" title="Enter Financial Year.">
			              </div>		           
			      </div>
               </div>
   		      <div class="card-footer" align="center"> 
		         	<input type="submit" id="save_btn" class="btn btn-success btn-sm nGlow" value="Save" onclick="return validate()" title="Click to Save Data."/>
		            <input type="button" id="search_btn" class="btn btn-primary btn-sm nGlow" value="Search" onclick="Search();" title="Click to Search Data."> 
              </div>                
      		</div> 
      </div>
			<div class="containerr nPopTable" id="divPrint">
			<div  class="watermarked" data-watermark="" id="divwatermark"  >
 			<div id="divSerachInput" style="width:60%;margin-top: 10px;">
				<div class="col-md-6">
					<input id="searchInput" type="text" style="font-family: 'fontello', Arial; margin-bottom: 5px;"	placeholder="Search Year" class="form-control-sm">
				</div>
			</div>
 			<div class="nPopTable" style="height:350px;width:100%;overflow: auto;background-color: white;">
			<table style="width: 100%; margin: 0 auto;margin-bottom:5px;" id="srctable">
				<thead style="text-align: center; line-height: 20px; font-size: 1.2vw;">
					<tr style="text-align: center;" >
						<th style="width:4%;">Ser No</th>
						<th style="">Finacial Year</th>
						<th style="width: 15%;">Action</th>
					</tr>
				</thead>
				<tbody style="font-size: 1.1vw; text-decoration: none;">
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="4">No Message Available</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="text-align: center; width: 5%;">${num.index+1}</td>
							<td style="text-align: center;" title="Financial Year is ${item[2]}">${item[2]}</td>
							<td style="text-align: center;"><input type="button" class="btn btn-success btn-sm nGlow" value="Create Base File"  onclick="CreateTable('${item[1]}');" title="Click to Create Base File."/> </td>
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


<c:url value="getSearchFinYr?${_csrf.parameterName}=${_csrf.token}" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" class="fp-form" name="searchForm" modelAttribute="service1">
		<input type="hidden" name="year2" id="year2"/>
</form:form> 

<c:url value="getCreateTable?${_csrf.parameterName}=${_csrf.token}" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="createForm" class="fp-form" name="createForm" modelAttribute="service2">
		<input type="hidden" name="year3" id="year3"/>
</form:form> 

<script>
function btn_clc(){
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
}

function Checkyear(){
	var y1 = $("#year").val();
	var p1 = y1;
	p1++;
	var p2 = $("#year").val();
	var p4 =" - ";
	var p = p2.concat(p4).concat(p1);
	if(y1.length < 5){
		$("#year1").val(p);
	}
}
function Search(){
    $("#year1").val($("#year").val());
    var year=$("#year").val();
    $("#year2").val(year);
    $("#divPrint").show();
    $("#searchForm").submit();	 
}

function CreateTable(s){
	var yr = s.substring(0,4);
	$("#year3").val(yr);
	$("#createForm").submit();
} 

function validate1111(){
	var d = new Date();
	var year = d.getFullYear();
	if ($("#year").val() == "") {
		alert("Please Enter the Year");
		$("#year").focus();
		return false;
	}
	if($("#year").val().length < 4){
		alert("Year Format Require in YYYY");
		$("#year").focus();
		return false;
	} 
	if($("#year").val() > year) {
		alert("Future Year cannot be allowed");
		$("#year").focus();
		return false;
	}
}

function validate(){
	var d = new Date();
	var year = d.getFullYear()+1;
	if ($("#year").val() == "") {
		alert("Please Enter the Year");
		$("#year").focus();
		return false;
	}
	if($("#year").val().length < 4){
		alert("Year Format Require in YYYY");
		$("#year").focus();
		return false;
	} 
	if($("#year").val() > year) {
		alert("Future Year cannot be allowed");
		$("#year").focus();
		return false;
	}
}


$(document).ready(function() {
	
	$(".char-counter1").charCounter({limit:4});
	$("#searchInput").on("keyup", function() {
    	var value = $(this).val().toLowerCase();
      	$("#srctable tbody tr").filter(function() {
      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var d = new Date();
	var year = d.getFullYear();
	$("#year").val(year);
	
	var p1 = $("#year1").val();
	var p2 = $("#year").val();
	var p3 = year+1;
	var p4 =" - ";

	var p = p1.concat(p2).concat(p4).concat(p3);
	$("#year1").val(p);
	
});
</script>