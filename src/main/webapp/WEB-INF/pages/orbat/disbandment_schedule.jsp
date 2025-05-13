<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<form:form name="Miso_disbandment_DtlForm" id="Miso_disbandment_Unt_DtlForm" action="misodisbandmentConversionAction?${_csrf.parameterName}=${_csrf.token}" method='POST' commandName="misoConverUntDtlCMD" enctype="multipart/form-data">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="row">
	    		<div class="card">
	        		<div class="card-header"><h5><b>DISBANDMENT SCHEDULE</b><br></h5><h6><span style="font-size:12px;color:red;">(To be entered by SD Dte / MISO)</span></h6><strong>Schedule Details</strong> </div>
	          			<div class="card-body card-block">
	            						<div class="col-md-12">
	          				<div class="col-md-6">
		          				<div class="row form-group">
	                					<div class="col col-md-4">
	                						<label class=" form-control-label"><strong style="color: red;">*</strong> GOI Letter No</label>
	                					</div>
	                					<div class="col-12 col-md-8">
	                  						<input type="text" id="goi_letter_no" name="goi_letter_no" maxlength="250" placeholder="GOI Letter No" class="form-control">
	                					</div>
	              				</div>
	          				</div>
	          				<div class="col-md-6">
	          				<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">*</strong> Auth Letter No</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="text" id="letter_no" name="letter_no" maxlength="250" placeholder="Auth Letter No" class="form-control">
                					</div>
              					</div>
	          				</div>
	          			</div>
	          			
						<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> GOI Letter Date</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="date" id="date_goi_letter" name="date_goi_letter" class="form-control" max='${date}'>
									</div>
								</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Letter Date</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="date" id="sanction_date" name="sanction_date" class="form-control"  max='${date}'>
									</div>
  								</div>
	          				</div>
	          			</div>
	          			<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-5">
                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Upload GOI Letter</label>
                					</div>
                					<div class="col-12 col-md-7">
                  						<input type="file" id="upload_goi_letter" name="upload_goi_letter" maxlength="250" class="form-control">
									</div>
								</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-5">
                  						<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Upload Auth Letter</label>
                					</div>
                					<div class="col-12 col-md-7">
                  						<input type="file" id="upload_auth_letter" name="upload_auth_letter" maxlength="250" class="form-control">
									</div>
  								</div>
	          				</div>
	          			</div>
	            	</div>
					<div class="card-header" style="border: 1px solid rgba(0,0,0,.125);"> <strong>Details Of The Unit</strong> </div>
						<div class="card-body card-block">
	            			<div class="col-md-12">
	          				<div class="col-md-6">
	          				   <div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label"><strong style="color: red;">*</strong> Unit Name</label>
								</div>
								<div class="col-12 col-md-8">

									<input type="text" id="unit_name" name="unit_name" maxlength="100" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search Unit Name" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
	          				</div>
	          				<div class="col-md-6">
          				     	<div class="row form-group">
					                <div class="col col-md-4">
					                  <label class=" form-control-label"><strong style="color: red;">*</strong> SUS No</label>
					                </div>
					               <div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no" maxlength="8" class="form-control autocomplete" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search SUS No" autocomplete="off">
									</div>
					          	</div>
	          				</div>
	          			</div>
	            		<div class="col-md-12">
	          				<div class="col-md-6">
	        					<div class="row form-group">
	              					<div class="col col-md-4">
	                						<label class=" form-control-label">Comd</label>
	              					</div>
	              					<div class="col-12 col-md-8">
                						<select disabled="disabled" id="cont_comd" name="cont_comd"  class="form-control">
                							<option value="">--Select--</option>
                                    		<c:forEach var="item" items="${getCommandList}" varStatus="num" >
                								<option value="${item[0]}"  name="${item[1]}" >${item[1]}</option>
                							</c:forEach>
                						</select>
	              					</div>
	            				</div>
	          				</div>
	          				<div class="col-md-6">
	          				  	<div class="row form-group">
	                				<div class="col col-md-4">
	                  					<label class=" form-control-label">Corps</label>
	                				</div>
	                				<div class="col-12 col-md-8">
	                  					<select disabled="disabled" id="cont_corps" name="cont_corps" class="form-control"></select>
	                				</div>
              					</div>
	          				</div>
	          			</div>		
	            		
						<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">Div</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<select  disabled="disabled" id="cont_div" name="cont_div"  class="form-control"></select>
                					</div>
              					</div>
          					</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
	                				<div class="col col-md-4">
	                  					<label class=" form-control-label">Bde</label>
	                				</div>		
	                				<div class="col-12 col-md-8">
	                  					<select  disabled="disabled" id="cont_bde" name="cont_bde" class="form-control"></select>
	                				</div>
              					</div>
	          				</div>
	          			</div>		
	            			
						<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">Parent Arm </label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<select disabled="disabled" id="parent_arm" name="parent_arm"  class="form-control">
                  							<option value="0">--Select--</option>
	                  						<c:forEach var="item" items="${getPrantArmList}" varStatus="num" >
                  								<option value="${item.code}">${item.code} - ${item.code_value}</option>
                  							</c:forEach>
                  						</select>
                					</div>
              					</div>
          					</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
	                				<div class="col col-md-4">
	                  					<label class=" form-control-label">Type of Arm</label>
	                				</div>	
                					<div class="col-12 col-md-8">
	                  						<select disabled="disabled" name="type_of_arm" id="type_of_arm" class="form-control-sm form-control" ></select>
	                				</div>	
                				</div>
	          				</div>
	          			</div>	
	          			<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
				            	    <div class="col col-md-4">
				                	  <label for="selectLg" class=" form-control-label">Type of Unit</label>
				                	</div>
				               		<div class="col-12 col-md-8">
										<select disabled="disabled" name="type_force" id="type_force" class="form-control-sm form-control">
											<option value="">--Select--</option>
	                           				<c:forEach var="item" items="${getTypeOfUnitList}">
	            								<option value="${item[0]}" >${item[0]} - ${item[1]}</option>
	            							</c:forEach>
										</select>
									</div>
            				  	</div>
	          				</div>
	          				<div class="col-md-6">
	          				</div>
	          			</div>
	          			<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label"><strong style="color: red;">*</strong> Disbandment Date (From)</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input  type="date" id="comm_depart_date" name="comm_depart_date"  class="form-control">
                					</div>
              					</div>
	          				</div>
	          				<div class="col-md-6">
          						<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">Disbandment Date (To)</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="date" id="compltn_arrv_date" name="compltn_arrv_date"  class="form-control">
                					</div>
              					</div>
	          				</div>
	          			</div>
	          			<div class="col-md-12">
	          				<div class="col-md-6">
			          			<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">Address </label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<textarea  id="address" name="address" maxlength="200" class="form-control"></textarea>
                					</div>
	              				</div>
	              			</div>
	              		</div>
						<div class="col-md-5 col-md-offset-1">
							<div class="row form-group" style="display: none;">
	                			<div class="col col-md-12">
	                				<input type="text" id="cont_aname" name="cont_aname"  maxlength="100" class="form-control">
	                				<input type="text" id="cont_bname" name="cont_bname"  maxlength="100" class="form-control">
	                				<input type="text" id="cont_cname" name="cont_cname"  maxlength="100" class="form-control">
	                				<input type="text" id="cont_dname" name="cont_dname"  maxlength="100" class="form-control">
	                			</div>
	                		</div>
          				</div>
					</div>
	              	<div class="card-footer" align="center" class="form-control">
						<input type="reset" class="btn btn-success btn-sm" value="Clear">   
	              		<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="return validate();">
	              	</div> 
	        	</div>
			</div>
		</div>
	</div>
</form:form>

<script>
$(document).ready(function() {    
	$("#upload_auth_letter").change(function(){	
		checkFileExt(this);
	});
	$("#upload_goi_letter").change(function(){	
		checkFileExt(this);
	});
	
	
	$("#unit_name").keypress(function(){
		var unit_name = this.value;
	
		 var susNoAuto=$("#unit_name");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getUnitsNameActiveList?"+key+"="+value,
		        data: {unit_name:unit_name},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  var enc = "";
			        	if(data.length != 0){
			        		enc = data[length].substring(0,16);
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
		        	  alert("Please Enter Approved Unit Name");
		        	  document.getElementById("sus_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	$(this).val(ui.item.value);
		        getOrbatDetailsFromUnitName($(this).val());
		      } 	     
		});
	}); 
	
	
   // Source Sus No auto
	$("input#sus_no").keyup(function(){
		var sus_no = this.value;
		var unitNameAuto=$("#sus_no");
		unitNameAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        type: 'POST',
		        url: "getSusNoActiveList?"+key+"="+value,
		        data: {sus_no:sus_no},
		          success: function( data ) {
		        	  var susval = [];
		        	  var length = data.length-1;
		        	  var enc = "";
			        	if(data.length != 0){
			        		enc = data[length].substring(0,16);
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
		        	  alert("Please Enter Approved SUS NO");
		        	  $("#unit_name").val("");
		        	  unitNameAuto.val("");	        	  
		        	  unitNameAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		      	var sus_no = ui.item.value;
		      	$.post("getActiveUnitNameFromSusNo?"+key+"="+value,{sus_no:sus_no}, function(j) {
		       		var length = j.length-1;
					var enc = j[length].substring(0,16);
			   		$("#unit_name").val(dec(enc,j[0]));
			   		getOrbatDetailsFromUnitName(dec(enc,j[0]))
		       	});
		      	
		     }
		});
	});
});
</script>
<script>
var Contcom,Contcorps,Contdiv,Contbde,ParentArm,TypeOfArm;
function getOrbatDetailsFromUnitName(unitName){	
       $.post("getUnitDetailsList?"+key+"="+value,{unitName:unitName}, function(j) {
       	if(j.length > 0) {
        	var length = j.length-1;
	 		var enc = j[length].substring(0,16);
	 			
	        $("#sus_no").val(dec(enc,j[1]));
       	
       		var forcodeControl = dec(enc,j[5]);
       	 	Contcom =forcodeControl[0];
  	        Contcorps=forcodeControl[0] + forcodeControl[1] + forcodeControl[2];
  	        Contdiv= forcodeControl[0] + forcodeControl[1] + forcodeControl[2] + forcodeControl[3] + forcodeControl[4] + forcodeControl[5];;
  	        Contbde=forcodeControl;
  	        
  	        var type_force = dec(enc,j[14]);
  	        $("select#type_force").val(type_force);
	   	    
   	        
   	    	$('#cont_comd option[value="'+ Contcom +'"]').attr("selected", "selected");
    		var cont_cname = $("#cont_comd").find('option:selected').attr("name");    	 
   			$("#cont_cname").val(cont_cname);
	        getCONTCorps(Contcorps);
		    getCONTDiv(Contdiv);
		    getCONTBde(Contbde);
   	    	    	
   	   	   	var Arm = dec(enc,j[1]);
       	 	var code_location = dec(enc,j[8]);
      	 		
   	        ParentArm = Arm[0] + Arm[1]; 
   	        TypeOfArm = Arm[0] + Arm[1] + Arm[2] + Arm[3]; 
   	        	
   	        $('#parent_arm option[value="'+ ParentArm +'"]').attr("selected", "selected");
	   	     $.post("getTypeOfArmArmList?"+key+"="+value,{code:ParentArm}, function(j) {
	         		var length = j.length-1;
	         		var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
 				for ( var i = 0; i < length; i++) {
 					if( TypeOfArm == dec(j[length][0],j[i][0])){
 						options += '<option value="' + dec(j[length][0],j[i][0]) + '" selected=selected>'+ dec(j[length][0],j[i][0]) +" - "+  dec(j[length][0],j[i][1]) + '</option>';
 					}else{
 						options += '<option value="' + dec(j[length][0],j[i][0]) + '" >'+ dec(j[length][0],j[i][0]) +" - "+  dec(j[length][0],j[i][1]) + '</option>';
 					}
 				}	
 				$("select#type_of_arm").html(options); 
 			});
       	}else{
       		alert('Data Not Available');
       	}
   	});
}
function validate(){
	if($("#goi_letter_no").val() == ""){
		alert("Please Enter the GOI Letter No.");
		$("#goi_letter_no").focus();
		return false;
	}
	if($("#letter_no").val() == ""){
		alert("Please Enter the Auth Letter No.");
		$("#letter_no").focus();
		return false;
	}
	if($("#date_goi_letter").val() == ""){
		alert("Please Select Date of GOI Letter");
		$("#date_goi_letter").focus();
		return false;
    }
	if($("#sanction_date").val() == ""){
		alert("Please Select Date of Letter");
		$("#sanction_date").focus();
		return false;
    }
	if($("#upload_goi_letter").get(0).files.length === 0){
		alert("Please Upload Document of GOI Letter");
		$("#upload_goi_letter").focus();
		return false;
    } 
	if($("#upload_auth_letter").get(0).files.length === 0){
		alert("Please Upload Document of Auth Letter");
		$("#upload_auth_letter").focus();
		return false;
    } 
	if($("#unit_name").val() == ""){
		alert("Please Enter Name Of Unit");
		$("#unit_name").focus();
		return false;
    }
	if($("#sus_no").val() == ""){
		alert("Please Enter Name Of SUS NO");
		$("#sus_no").focus();
		return false;
    }
	if($("#comm_depart_date").val() == ""){
		alert("Please Select Disbandment Date (From)");
		$("#comm_depart_date").focus();
		return false;
    }
	var from = document.getElementById("comm_depart_date").value;
	var to = document.getElementById("compltn_arrv_date").value;
	if ((Date.parse(from) > Date.parse(to))) {
		alert('You cannot select To dete less than From Date.');
		document.getElementById("compltn_arrv_date").focus();
		return false;
	}
	
	return true;
}
</script>
<script>
$(document).ready(function() {    
	var ddd = '${date}';
	$("#sanction_date").change(function(){
		if($("#sanction_date").val() > '${date}'){
			$("#sanction_date").val("");
			alert("You Can't select Future Date!");
			$("#sanction_date").focus();
			return false;
		}
	});	
	$("#date_goi_letter").change(function(){
		if($("#date_goi_letter").val() > '${date}'){
			$("#date_goi_letter").val("");
			alert("You Can't select Future Date!");
			$("#date_goi_letter").focus();
			return false;
		}
	});	
});	
</script>
