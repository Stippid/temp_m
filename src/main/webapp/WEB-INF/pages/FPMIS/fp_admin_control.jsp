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
<form:form name="fp_save_admin_control"	action="fp_save_admin_control?${_csrf.parameterName}=${_csrf.token}" class="fp-form" method="POST">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
				<div class="card">
				<div class="card-header mms-card-header" style="min-height:60px;padding-top: 10px;">
	                <b>WINDOW MGT</b>            		
	           </div>

					<div class="card-body card-block ncard-body-bg">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Estimate Type </label>
									</div>
									<div class="col-12 col-md-8">
										<select id="est_type" name="est_type" class="form-control form-control-sm" onclick="CheckDD()" title="Select Estimate Type.">
											<option value="-1">-- Select --</option>
											<c:forEach var="item" items="${n_rpttype}" varStatus="num">
												<option value="${item[2]}">${item[3]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> For Financial Year</label>
									</div>
									<div class="col-12 col-md-8">
										<select id="fin_year" name="fin_year" class="form-control form-control-sm" title="Select Financial Year.">
											<c:forEach var="item" items="${n_finyr}" varStatus="num">
												<option value="${item[0]}">${item[1]}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong	style="color: red;">*</strong> Window Open From</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="date_from" name="date_from"	class="form-control-sm form-control" min="${date}" maxlength="10" title="Select Window Open Date.">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">*</strong> Window Open To</label>
									</div>
									<div class="col-md-8">
										<input type="date" id="date_to"	name="date_to" class="form-control-sm form-control"	min="${date}" maxlength="10" title="Select Window Close Date.">
									</div>
								</div>
							</div>

						</div>
						<div class="col-md-12">
			              <div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong	style="color: red;">*</strong> Status</label>
									</div>
									<div class="col-md-8">
										 <select name="status" id="status" class="form-control-sm form-control" title="Select Status.">	
		          						     <option value="1">Active</option>
		          						     <option value="2">Inactive</option>	
		  				        </select>
									</div>
								</div>
							</div>
		              </div>

					</div>
					<div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-primary btn-sm nGlow" value="Clear" title="Click to Clear Data.">
						<input type="submit" class="btn btn-success btn-sm nGlow" value="Save" onclick="return validate();" title="Click to Save Data.">
					</div>
				</div>
			</div>
		</div>
	</div>
	
	 <div class="containerr nPopTable" id="divPrint" >
	 <div  class="watermarked" data-watermark="" id="divwatermark"  >
		                 <table id="CdaStatusReport" class="table no-margin table-striped  table-hover  table-bordered report_print" style="width:100%;margin-top:10px;"> 
		                      <thead>
		                          <tr>
			                         <th style="text-align: center;">Estimate Type</th>
			                         <th style="text-align: center;">Window Open Date</th>
			                         <th style="text-align: center;">Window Close Date</th>
			                         <th style="text-align: center;">Fin Year</th>
			                         <th style="text-align: center;">Status</th>
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
										 <td style='text-align:left' title="Estimate Type - ${item[0]}">${item[0]}</td>
										 <td style='text-align:center' title="Window Open Date - ${item[1]}">${item[1]}</td>
										 <td style='text-align:center' title="Window Close Date ${item[2]}">${item[2]}</td>
										 <td style='text-align:center' title="Financial Year - ${item[3]} - ${item[3]+1}">${item[3]} - ${item[3]+1}</td>
										  <c:if test="${item[4]==1}">
										 <td style='text-align:center' title="Status is Active">Active</td>
										 </c:if>
										 <c:if test="${item[4]==2}">
										 <td style='text-align:center' title="Status is Inactive">Inactive</td>
										 </c:if>
							          </tr>
							       </c:forEach>
		                     </tbody>
		                 </table>
		                </div>
			  </div> 
</form:form>
<script src="js/miso/miso_js/jquery-2.2.3.min.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script>
$(document).ready(function() {
	$().getCurDt(date_to);
	$().getCurDt(date_from);
	$("div#divwatermark").val('').addClass('watermarked');	
	watermarkreport();
});	
	function validate() {

		if ($("#est_type").val() == "" || $("#est_type").val() == "-1") {
			alert("Please Select Estimate Type.");
			$("#est_type").focus();
			return false;
		}

		if ($("#fin_year").val() == "") {
			alert("Please Select Financial Year.");
			$("#fin_year").focus();
			return false;
		}
		if ($("#date_from").val() == "") {
			alert("Please Select Window Open Date.");
			$("#date_from").focus();
			return false;
		}
		if ($("#date_to").val() == "") {
			alert("Please Select Window Close Date.");
			$("#date_to").focus();
			return false;
		}

		//return true;
	}
	
	function CheckDD(){
		if($("#est_type").val() == "CFY"){
		
		var k = new Date();
		var c_d = k.getFullYear()+"-"+("04")+"-"+("01");
		var c_e = (k.getFullYear()+1)+"-"+("03")+"-"+("31");
		$("#date_from").val(c_d);
		$("#date_to").val(c_e);
		document.getElementById('date_from').readOnly=true;
		document.getElementById('date_to').readOnly=true;
		}
		else{
			$().getCurDt(date_from);
			$().getCurDt(date_to);
			document.getElementById('date_from').readOnly=false;
			document.getElementById('date_to').readOnly=false;
		}
	}
</script>