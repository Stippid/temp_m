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
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<form:form name="Bank" id="Bank" action="BankAction" method="post" class="form-horizontal" commandName="BankCMD"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>ADD/UPDATE BANK</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	            			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Bank Name </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="bank_name" name="bank_name" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Bank Abbreviation </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="bank_abb" name="bank_abb" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              			</div>	    
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> IFSC </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="ifsc" name="ifsc" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Bank Address </label>
						                </div>
						                <div class="col-md-8">
						                   <textarea id="bank_address" name="bank_address" class="form-control autocomplete" autocomplete="off" ></textarea> 
						                </div>
						            </div>
	              				</div>
	              			</div>	 
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Bank Phone </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="bank_phone" name="bank_phone" class="form-control autocomplete" maxlength="10" onkeypress="return validateNumber(event, this)" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"><strong style="color: red;">* </strong>Status</label>
										</div>
										<div class="col-md-8">
										<select name="status" id="status" class="form-control">
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getStatusMasterList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>	
	              				
	              				
	              			</div>	           				              			
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="Bank" class="btn btn-success btn-sm" >Clear</a> 
		              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">
		              		<i class="action_icons searchButton"></i><input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search">  
			            </div> 		
	        	</div>
			</div>
	</div>

<div class="container" id="getbankSearch" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getbankSearch" class="table no-margin table-striped  table-hover  table-bordered report_print">
		                 <thead style="text-align: center;">
		                       <tr>
			                         <th style="font-size: 15px ;width: 10%;">Ser No</th>
									<th style="font-size: 15px">Bank Name</th>
									<th style="font-size: 15px">Bank Abbreviation</th>
									<th style="font-size: 15px">IFSC</th>
									<th style="font-size: 15px">Bank Address</th>
									<th style="font-size: 15px">Bank Phone</th>
									<th style="font-size: 15px ;width: 20%;">Action</th>
		                        </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="7">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td style="font-size: 15px;text-align: center ;width: 10%;">${num.index+1}</td> 
										<td style="font-size: 15px;">${item[0]}</td>
										<td style="font-size: 15px;">${item[1]}</td>
										<td style="font-size: 15px;">${item[2]}</td>
										<td style="font-size: 15px;">${item[3]}</td>
										<td style="font-size: 15px;text-align: center;">${item[4]}</td>
										<td style="font-size: 15px;text-align: center ;width: 20%;">${item[5]} ${item[6]}</td> 
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		            </div>				  
			   </div>
</form:form>

<c:url value="getSearchbankMaster" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="bank_name1">
		<input type="hidden" name="bank_name1" id="bank_name1" />
		<input type="hidden" name="bank_abb1" id="bank_abb1" />
		<input type="hidden" name="ifsc1" id="ifsc1" />
		<input type="hidden" name="bank_address1" id="bank_address1" />
		<input type="hidden" name="bank_phone1" id="bank_phone1" />
</form:form> 

<c:url value="Edit_Bank" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2">
</form:form>

<c:url value="deletebankMasterURL" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>


<Script>
$(document).ready(function() {
	
	 if('${list.size()}' == ""){
		   $("div#getbankSearch").hide();
		 }
	 if('${bank_name1}'== null || '${bank_name1}'=='')
		{
			$("#bank_name").val('') ;
		}else
		{
			$("#bank_name").val('${bank_name1}') ;
		} 
	 if('${bank_abb1}'== null || '${bank_abb1}'=='')
		{
			$("#bank_abb").val('') ;
		}else
		{
			$("#bank_abb").val('${bank_abb1}') ;
		} 
	 if('${ifsc1}'== null || '${ifsc1}'=='')
		{
			$("#ifsc").val('') ;
		}else
		{
			$("#ifsc").val('${ifsc1}') ;
		} 
	 if('${bank_address1}'== null || '${bank_address1}'=='')
		{
			$("#bank_address").val('') ;
		}else
		{
			$("#bank_address").val('${bank_address1}') ;
		} 
	 if('${bank_phone1}'== null || '${bank_phone1}'=='')
		{
			$("#bank_phone").val('') ;
		}else
		{
			$("#bank_phone").val('${bank_phone1}') ;
		} 
});

function validate(){
	if($("input#bank_name").val() == ""){
		alert("Please Enter Bank Name")
		$("input#bank_name").focus();
		return false;
	}
	if($("input#bank_abb").val() == ""){
		alert("Please Enter Bank Abbreviation")
		$("input#bank_abb").focus();
		return false;
	}
	if($("input#ifsc").val() == ""){
		alert("Please Enter IFSC")
		$("input#ifsc").focus();
		return false;
	}
	if($("#bank_address").val() == ""){
		alert("Please Enter Bank Address")
		$("#bank_address").focus();
		return false;
	}
	if($("input#bank_phone").val() == ""){
		alert("Please Enter Bank Phone No")
		$("input#bank_phone").focus();
		return false;
	}
	return true;
}

function Search(){
	    $("#bank_name1").val($('#bank_name').val());
		$("#bank_abb1").val($('#bank_abb').val());
		$("#ifsc1").val($('#ifsc').val());
		$("#bank_address1").val($('#bank_address').val());
		$("#bank_phone1").val($('#bank_phone').val());
		
		document.getElementById('searchForm').submit();

}
function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
	}
	
function editData(id){	
	
	$("#id2").val(id);
	$("#updateForm").submit();
}

function validateNumber(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (charCode == 46) {
			return false;
		}
	}
	return true;
} 
</Script>
