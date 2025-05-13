<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="Stylesheet" href="js/Calender/jquery-ui.css">
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
<link rel="stylesheet" href="js/common/nrcss.css">

<%
   String nPara=request.getParameter("nPara");
%>
<script>
	var role = "${role}";
</script>

<body class="mmsbg" onload="setMenu();">
<form:form action="" id="Search_approval"	method="post" class="form-horizontal fp-form" commandName="Search_approvalcmd">
	<span id="ip"></span> 
	<div class=""><center>
		 <div class="container" align="center">
				<div class="card">
					<div class="card-header mms-card-header" style="min-height:60px;padding-top: 10px;">
			                	<b>SECOND LEVEL CHECK AND APPROVAL OF ALLOTMENT</b>
			        </div>
			        <c:set var="n_sel" value="${fn:split(n_sel,':')}"/>
					<div class="card-body card-block ncard-body-bg">   
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Estimate Type </label>
									</div>
									<input type="hidden" name="roleSusNo" id="roleSusNo"/>
									<input type="hidden" name="nPara" id="nPara" value="${conc_req}"/>
									<div class="col-md-8">
										<select id="est_type" name="est_type" class="form-control form-control-sm" title="Select Estimate Type">
											<c:forEach var="item" items="${n_rpttype}" varStatus="num">
												<c:if test="${item[2]==n_sel[0]}">
													<option value="${item[2]}" selected>${item[3]}</option>
												</c:if>
												<c:if test="${item[2]!=n_sel[0]}">
													<option value="${item[2]}">${item[3]}</option>
												</c:if>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> For Financial Year</label>
									</div>
									<div class="col-md-8">
									
										<select id="fin_year" name="fin_year" class="form-control form-control-sm" title="Select Financial Year">
											<option value="-1">--- Select FY ---</option>
											<c:forEach var="item" items="${n_finyr}" varStatus="num">
												<c:if test='${item[0] == n_sel[1]}'>
													<option value="${item[0]}" SELECTED>${item[2]}</option>
												</c:if>
												<c:if test='${item[0] != n_sel[1]}'>
													<c:if test='${item[0] == n_cfinyr}'>					
														<option value="${item[0]}">${item[2]}</option>																						
													</c:if>
													<c:if test='${item[0] != n_cfinyr}'>					
														<option value="${item[0]}">${item[2]}</option>																						
													</c:if>
												</c:if>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
					</div>
					<div class="card-footer" align="center">
				  		<input type="button" id="btn_serach" class="btn btn-success btn-sm nGlow" value="Get Excel Uploaded Fund Allocation Data" onclick="return isvalidData()" title="Click to get Excel Uploaded (First Level) Fund Allocation Data"/> 				 
					</div>
			</div>
		</div>
</div>
</form:form>	
 	  <div class="containerr" id="divPrint" style="margin-top:10px;">
<!--  			<div id="divSerachInput" style="display:inline-flex;">
				<div style="width:40%">
					<input id="searchInput1" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Data..."  size="35" class="form-control-sm">			
				</div> 
				
			</div>
 -->			<div  class="watermarked" data-watermark="" id="divwatermark"  >
 			<div class="nrTableDataDiv_1 nPopTable" id="nrTableDataDiv" style="height:300px;"> 
					   <table border="1" style="width:100%" id="SearchReport">
                       		<thead style="text-align: center;line-height: 25px;font-size: 1vw;">
								<tr>
									<th style='text-align:center;width:5%;'>Code Head</th>
									<th style='text-align:center;width:30%;'>Head</th>
									<th style='text-align:center;width:25%;'>Budget Holder</th>
									<th style='text-align:center;width:15%;'>Current Allocation</th>
									<th style='text-align:center;width:10%;'>Current Projection</th>
									<th style='text-align:center;width:10%;'>Previous Allocation</th>
								</tr>
							</thead>
  							<tbody id="nrTable" style="font-size: .90vw;text-decoration: none;">
  							     <c:set var="nhdcd" value=""/>
  							     <c:forEach var="item" items="${list}" varStatus="num">  							     
  							     	<c:if test="${list[0][0] == 'NIL'}">
    							       <tr class='nrSubHeading'>
											 <td colspan='7' style='text-align:center;'>Data Not Available</td>
	   								 	</tr>
									</c:if>										   
								    <c:if test="${list[0][0] != 'NIL'}"> 
								<tr>
									 <c:set var="dataf" value="${fn:split(item[3],':')}"/>	
									 <c:if test="${item[4] != 'Revenue'}">	 
										 <c:if test="${item[3] == nhdcd}">
										 	<td style='text-align:left;width:15%;color:transparent;'>${dataf[3]}</td>
										 	<td style='text-align:lefft;width:20%;color:transparent;'>${item[4]}</td>											 
										 </c:if>
										 <c:if test="${item[3] != nhdcd}">
										 	<td style='text-align:left;width:15%;'>${dataf[3]}</td>
										 	<td style='text-align:lefft;width:20%;'>${item[4]}</td>
										 	<c:set var="nhdcd" value="${item[3]}"/>
										 </c:if>
										 <td style='text-align:left;width:10%;'>${item[6]}</td>
										 <td style='width:10%;' class="text-right" title="Amount in '${item[12]}'">											
										 	<script>document.write(Number('${item[9]}').toINR('','${item[12]}','INR','${item[12]}'))</script>
										 </td>
										 <td style='text-align:right;width:10%;'>${item[8]}</td>
										 <td style='text-align:right;width:10%;'>${item[7]}</td>
										</td>
									</c:if>
								</tr>
								</c:if>
							</c:forEach> 
  							     </tbody>
  							</table>
  							</div>
						</div>
						<div class="card-footer" align="center">
							<div class="col-md-6">
								<div id="divSerachInput" style="float:left;">
									<div style="width:40%">
										<input id="searchInput1" type="text" style="font-family: 'fontello',Arial; margin-bottom: 5px;" placeholder="Search Data..."  size="25" class="form-control-sm">			
									</div> 				
								</div>
							</div>
							<div class="col-md-6">
  								<input type="button" id="btn_serach" class="btn btn-success btn-sm nGlow" value="Upload Final Allotment" onclick="finalsubmit()" title="Submission of Final Allocation"/>
  							</div>
						</div>
</div>

<c:url value="UploadDataList?${_csrf.parameterName}=${_csrf.token}" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" class="fp-form" name="searchForm" modelAttribute="service1">
	<input type="hidden" name="sus1" id="sus1">
	<input type="hidden" name="est_type2" id="est_type2">
	<input type="hidden" name="fin_year2" id="fin_year2">
	<input type="hidden" name="para1" id="para1">
</form:form>

<c:url value="UploadDataUpd?${_csrf.parameterName}=${_csrf.token}" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="updateForm" class="fp-form" name="updateForm" modelAttribute="service2">
	<input type="hidden" name="updid" id="updid">
	<input type="hidden" name="cur_allot2" id="cur_allot2">
	<input type="hidden" name="sus11" id="sus11">
	<input type="hidden" name="est_type21" id="est_type21">
	<input type="hidden" name="fin_year21" id="fin_year21">
	<input type="hidden" name="para11" id="para11">	
</form:form> 

<c:url value="fp_submit_allot?${_csrf.parameterName}=${_csrf.token}" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="finalsubmt" class="fp-form" name="finalsubmt" modelAttribute="service3">
	<input type="hidden" name="est_types" id="est_types">
	<input type="hidden" name="fin_years" id="fin_years">
	<input type="hidden" name="paras" id="paras">	
</form:form> 
  
<script>
function isvalidData(){
	if ($("#est_type").val() == "" || $("#est_type").val() == "-1" || $("#est_type").val() == null) {
		alert("Please Select Estimate Type.");
		$("#est_type").focus();
		return false;
	}
	if ($("#fin_year").val() == "" || $("#fin_year").val() == "-1" || $("#fin_year").val() == null) {
		alert("Please Select Financial Year.");
		$("#fin_year").focus();
		return false;
	}
	var fin_year1=$("#fin_year").val();
	var est_type1=$("#est_type").val();
	var nPara = $("#nPara").val();
	
	$("#para1").val(nPara);
	$("#est_type2").val(est_type1);
	$("#fin_year2").val(fin_year1);
	$("#sus1").val("");
	
	$("#searchForm").submit();
}


function finalsubmit(d){
	var b=confirm('You are Uploading Final Allotment.\nNo further Amendment / Modification will be allowed.');
	if (b==true) {
		var fin_years=$("#fin_year").val();
		var est_types=$("#est_type").val();
		var nPara1 = $("#nPara").val();	
		$("#paras").val(nPara1);
		$("#est_types").val(est_types);
		$("#fin_years").val(fin_years);
		$("#finalsubmt").submit();
	}
}

$(document).ready(function() {
	$("#searchInput1").on("keyup", function() {
		var value = $(this).val().toLowerCase();
		$("#nrTable tr").filter(function() { 
			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		});
	});
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
	var a='${list[0][0]}';
	if (a==null || a=='') {
		$('#divPrint').hide();
	} 
	
	$("#fin_year").on("change", function() {
		$("#nrTable").text("");
		$('#divPrint').hide();
	});
	
	
});



 function editData(d){
	$("#updid").val(d);
	var cc = $("input#cur_allot"+d).val();
	$("#cur_allot2").val(cc);
	
	var fin_year11=$("#fin_year").val();
	var est_type11=$("#est_type").val();
	var nPara1 = $("#nPara").val();
	
	$("#para11").val(nPara1);
	$("#est_type21").val(est_type11);
	$("#fin_year21").val(fin_year11);
	$("#sus11").val(roleSusNo);
	
	$("#updateForm").submit();
} 
</script>