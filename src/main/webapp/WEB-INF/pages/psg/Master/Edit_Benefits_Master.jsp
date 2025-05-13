<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>


<form:form name="eBenefits" id="eBenefits" action="Edit_Benefits_Action" method="post" class="form-horizontal" commandName="Edit_BenefitsCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>UPDATE BENEFITS</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> AGENCY NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <select name="agency_id" id="agency_id" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getAgencyList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> BENEFITS NAME</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="benefits_name" name="benefits_name" class="form-control autocomplete" autocomplete="off"  oninput="this.value = this.value.toUpperCase()" maxlength="30" /> 
						                </div>
						            </div>
	              				</div>
	              			</div>
	              			
	              			<div class="col-md-12">	 
	              			<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>STATUS</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
<!-- 												<option value="0">--Select--</option> -->
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
								</div>
            			</div>
							 <input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" /> 		
						<div class="card-footer" align="center" class="form-control">
							<a href="Benefits_Url" class="btn btn-success btn-sm">cancel</a> 
							
		              		<input type="submit" class="btn btn-primary btn-sm" value="Update"  onclick="return Validate();">
		              		<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">
			            </div> 		
	        	</div>
			</div>
	</div>

</form:form>


<Script>
$(document).ready(function() {
	$("select#agency_id").val('${Edit_BenefitsCMD.agency_id}');
	$("#benefits_name").val('${Edit_BenefitsCMD.benefits_name}');
	$("#status").val('${Edit_BenefitsCMD.status}');
	$("#id").val('${Edit_BenefitsCMD.id}');
});
 
	function Validate() {
		if ($("select#agency_id").val() == "0") {
			alert("Please Enter Agency Name");
			$("select#agency_id").focus();
			return false;
		}
		if ($("input#benefits_name").val().trim() == "") {
			alert("Please Enter Benefits Name");
			$("input#benefits_name").focus();
			return false;
		}
		/* if ($("select#status").val() == "inactive") {
			alert("Only Select Active Status");
			$("select#status").focus();
			return false;
		} */
		return true;
	}

	</Script>

