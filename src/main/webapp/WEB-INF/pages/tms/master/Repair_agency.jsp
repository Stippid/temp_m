<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<form:form name="#" id="#" action="re_agencyAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="re_agencyCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
				<div class="card-header"><h5>Repair Agency<br></h5><h6><span style="font-size: 10px;font-size:15px;color:red">(To be entered by MISO)</span></h6></div>
					
					<div class="card-body card-block" id="mainViewSelection" style="display: block;">
						<div class="col-md-12">
						<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Repair Agency </label>
									</div>							
									<div class="col-12 col-md-8">
										<textarea id="re_agency" name="re_agency" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"></textarea>
									     </div>
									
									
								</div>
							</div>
								<div class="col-md-6">
	              					<div class="row form-group">
					                	<div class="col-md-5">
					                  		<label class=" form-control-label">MCT  (4 Digit)</label>
					                	</div>
					                	<div class="col-md-7">
					                	<input type="hidden" id="mct_main_id" name="mct_main_id" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="100">
					                 		<input type="text" id="sermct_main_nomen" name="sermct_main_nomen" placeholder="" class="form-control autocomplete" autocomplete="off" maxlength="100">
				            			</div>
				        			</div>	
	              				</div>
							
							
								         					
	              				
	              				
						</div>
						</div>
					<div class="card-footer" align="center">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
					<input type="submit"  class="btn btn-primary btn-sm" value="Save" onclick=" return validate();" >
					
					<!-- onclick="return validate();" -->
					<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search()" > 
					</div>
					<div class="card-body card-block" id="tblCon" style="overflow: auto; " >
						<div class="col s12" >
							<table class="table no-margin table-striped  table-hover  table-bordered" id="add_reports">
								<thead style="text-align: center;">
									<tr>
									<th style="width: 10%;text-align: center;">Ser No</th>
									<th style="width: 10%;text-align: center;">MCT  (4 Digit)</th>
									<th style="width: 50%;text-align: center;">Repair Agency</th>
									<th style="width: 30%;text-align: center;">Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									<td style="width: 10%;text-align: center;">${num.index+1}</td>
									 <th style="width: 50%;text-align: center;">${item[2]}</th>
									   	<th style="width: 10%;text-align: center;">${item[1]}</th>
									   	 <th style="width: 30%;text-align: center;">${item[3]} ${item[4]}</th>
										
									</tr>
								</c:forEach>
								
								</tbody>
							</table>
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
</form:form>
<c:url value="repair_agency_search_REPORT" var="searchUrlbase" />
	<form:form action="${searchUrlbase}" method="post" id="search3" name="search3" modelAttribute="sus_no1">
		<input type="hidden" name="sermct_main_nomen1" id="sermct_main_nomen1" value=""/>
		<input type="hidden" name="re_agency1" id="re_agency1" value=""/>
		
	</form:form>
	
	<c:url value="repair_DeleteURL" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="cdeleteForm" name="cdeleteForm" modelAttribute="cdeleteid">
      <input type="hidden" name="cdeleteid" id="cdeleteid" value="0"/>
</form:form> 

<c:url value="repair_agency_updateURL" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="cupdateForm" name="cupdateForm" modelAttribute="cupdateid">
		<input type="hidden" name="cupdateid" id="cupdateid" value="0"/>
	</form:form>


<script>


$("input#sermct_main_nomen").keyup(function(){
	var sermct_main_nomen = this.value;
	
	var mct_nomenAuto=$("#sermct_main_nomen");
	mct_nomenAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getserMctMainnomainTmsList?"+key+"="+value,
	        data: {sermct_main_nomen:sermct_main_nomen},
	          success: function( data ) {
	        	  var susval = [];
	        	  var length = data.length-1;
	        		if(data.length != 0){
		        		var enc = data[length].substring(0,16);
		        	}
		        	for(var i = 0;i<data.length;i++){
		        		susval.push(dec(enc,data[i]));
		        	}
		        	response( susval ); 
	          }
	        });
	      },
	      minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Valid MCT Nomenclature.");
	        	  $("#sermct_main_nomen").val("");
	        	  mct_nomenAuto.val("");	        	  
	        	  mct_nomenAuto.focus();
	        	  return false;	             
	    	}   	         
	    }, 
		select: function( event, ui ) {
	      	var mct_nomen = ui.item.value;
		
			$.post("getserMctMainTmsListclikid?"+key+"="+value, {
				mct_nomen:mct_nomen
			}).done(function(j) {
				 var length = j.length-1;
		          var enc = j[length].substring(0,16);
		          $("#mct_main_id").val(dec(enc,j[0]));
			}).fail(function(xhr, textStatus, errorThrown) {
			});	    	
		}
	});
});
function validate() {
	 $.ajaxSetup({
        async : false
	});

if ($("input#re_agency").val() == "") {
	alert("Please Enter Repair Agency ");
	$("input#re_agency").focus();
	return false;
}

if ($("input#sermct_main_nomen").val() == "") {
	alert("Please Enter MCT  (4 Digit)");
	$("input#sermct_main_nomen").focus();
	return false;
} 

return true;
}
function deleteData(id){

	document.getElementById('cdeleteid').value=id;
	document.getElementById('cdeleteForm').submit();   
} 


function editData(id){
	
	   document.getElementById('cupdateid').value=id;
	   document.getElementById('cupdateForm').submit();
}


function Search(){
	

	if ($("input#re_agency").val() == "") {
		alert("Please Enter Repair Agency ");
		$("input#re_agency").focus();
		return false;
	}
	
	if ($("input#sermct_main_nomen").val() == "") {
		alert("Please Enter MCT  (4 Digit)");
		$("input#sermct_main_nomen").focus();
		return false;
	} 
	
	$("#sermct_main_nomen1").val($("#mct_main_id").val());
    $("#re_agency1").val($("#re_agency").val());
 
    document.getElementById('search3').submit();
}
$(document).ready(function() {
	var q = '${sermct_main_nomen1}';		
	if(q != ""){
		$("#mct_main_id").val(q);
	}
	
	var q7 = '${re_agency1}';		
	if(q7 != ""){
		$("#re_agency").val(q7);
	}
	

	
});
	</script>