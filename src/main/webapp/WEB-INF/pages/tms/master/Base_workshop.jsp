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

<form:form name="#" id="#" action="base_workshopAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="base_workshopCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
				<div class="card-header"><h5>Base WorkShop<br></h5><h6><span style="font-size: 10px;font-size:15px;color:red">(To be entered by MISO)</span></h6></div>
					
					<div class="card-body card-block" id="mainViewSelection" style="display: block;">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No </label>
									</div>
									<div class="col-12 col-md-8">
								
										<input type="text" id="sus_no" name="sus_no"   maxlength="8" class="form-control" autocomplete="off">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>Unit Name  </label>
									</div>							
									<div class="col-12 col-md-8">
										<textarea id="unit_name" name="unit_name" placeholder="" class="form-control autocomplete" style="font-size: 11px;" autocomplete="off"></textarea>
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
									<th style="width: 10%;text-align: center;">SUS No</th>
									<th style="width: 50%;text-align: center;">Unit Name</th>
									<th style="width: 30%;text-align: center;">Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									<td style="width: 10%;text-align: center;">${num.index+1}</td>
									   	<th style="width: 10%;text-align: center;">${item[0]}</th>
									   	 <th style="width: 50%;text-align: center;">${item[1]}</th>
									   	 <th style="width: 30%;text-align: center;">${item[2]} ${item[3]}</th>
										
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
<c:url value="base_workshop_searchURL" var="searchUrlbase" />
	<form:form action="${searchUrlbase}" method="post" id="search3" name="search3" modelAttribute="sus_no1">
		<input type="hidden" name="sus_no1" id="sus_no1" value=""/>
		<input type="hidden" name="unit_name1" id="unit_name1" value=""/>
		
	</form:form>
	
	<c:url value="base_workshop_DeleteURL" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="cdeleteForm" name="cdeleteForm" modelAttribute="cdeleteid">
      <input type="hidden" name="cdeleteid" id="cdeleteid" value="0"/>
</form:form> 

<c:url value="base_workshop_updateURL" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="cupdateForm" name="cupdateForm" modelAttribute="cupdateid">
		<input type="hidden" name="cupdateid" id="cupdateid" value="0"/>
	</form:form>


<script>


$(function() {
	
	 	$("#unit_name").keypress(function(){
			var unit_name = this.value;
			 var susNoAuto=$("#unit_name");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
			    	    url: "getTargetUnitsNameActiveList?"+key+"="+value,
			        data: {unit_name:unit_name},
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
			        	  alert("Please Enter Approved Unit Name.");
			        	  document.getElementById("sus_no").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	  var unit_name = ui.item.value;
			    	 
			        $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
			            }).done(function(data) {
			            	var length = data.length-1;
		    				var enc = data[length].substring(0,16);
		    				$("#sus_no").val(dec(enc,data[0]));
					        
						 
			            }).fail(function(xhr, textStatus, errorThrown) {
			            });
			      
				} 	     
			}); 			
		});
	
	$("input#sus_no").keypress(function(){
		var sus_no = this.value;
		var susNoAuto=$("#sus_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
		    	    url: "getTargetSUSNoList?"+key+"="+value,
		        data: {sus_no:sus_no},
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
		        	  alert("Please Enter Approved SUS No.");
		        	  document.getElementById("unit_name").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	var sus_no = ui.item.value;
		    
		        $.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(data) {
		            }).done(function(data) {
		        		
		  			  	var length =  data.length-1;
			        	var enc =  data[length].substring(0,16);
			        	$("#unit_name").val(dec(enc, data[0]));	
				      
					 
					  
		            }).fail(function(xhr, textStatus, errorThrown) {
		            }); 	
 			     } 	     
		});	
	});	
});
function validate() {
	 $.ajaxSetup({
        async : false
	});
if ($("input#sus_no").val() == "") {
	alert("Please Enter Sus No");
	$("input#sus_no").focus();
	return false;
} 
if ($("input#unit_name").val() == "") {
	alert("Please Enter Unit Name");
	$("input#unit_name").focus();
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
	
	if ($("input#sus_no").val() == "") {
		alert("Please Enter Sus No");
		$("input#sus_no").focus();
		return false;
	} 
	if ($("input#unit_name").val() == "") {
		alert("Please Enter Unit Name");
		$("input#unit_name").focus();
		return false;
	}
	
	$("#sus_no1").val($("#sus_no").val());
    $("#unit_name1").val($("#unit_name").val());
 
    document.getElementById('search3').submit();
}
$(document).ready(function() {
	var q = '${sus_no1}';		
	if(q != ""){
		$("#sus_no").val(q);
	}
	
	var q7 = '${unit_name1}';		
	if(q7 != ""){
		$("#unit_name").val(q7);
	}
	

	
});
	</script>