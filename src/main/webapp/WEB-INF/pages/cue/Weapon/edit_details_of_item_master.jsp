<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<script type="text/javascript">
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
	
});	

</script>

<form:form  action="edititem_masterAction" method='POST' commandName="edititem_masterCmd" > 
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"><h5>Edit DETAILS OF ITEM MASTER</h5></div>
	          		<div class="card-body card-block cue_text">
	            		<div class="col-md-12">	  
	            		
	            		<div class="col-md-6">
	  						<div class="row form-group">	           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">PRF Group</label>
             					</div>
             					<div class="col-12 col-md-6">
             						<input type="hidden" id="id" name="id" placeholder="" class="form-control" value="${edititem_masterCmd.id}">
	                  				<input  id="prf_group" name="prf_group" placeholder="" class="form-control"  value="${edititem_masterCmd.prf_group}"  autocomplete="off" readonly="readonly">
             					</div>
             				</div>
             				</div>	           					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">COS Section</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="cos_sec_no" name="cos_sec_no" placeholder="" class="form-control" readonly="readonly"  value="${edititem_masterCmd.cos_sec_no}" >
								</div>
							</div>	 							
	  						</div>
	  						           					
	  					</div>	
	  					
	  					<div class="col-md-12">	              					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
	                  						<label class=" form-control-label">Nomenclature </label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="item_type" name="item_type" maxlength="255" class="form-control" value="${edititem_masterCmd.item_type}"  style="text-transform:uppercase" autocomplete="off"  ></input>
	                					</div>	 							
	  						</div>
	  						</div>
	              			 <div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Item Code</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input  id="item_code" name="item_code" class="form-control"  value="${edititem_masterCmd.item_code}" readonly="readonly" >
								</div>
							</div>	 							
	  						</div> 
	  					</div>
	  					
	  					<div class="col-md-12">	              					
	              			<div class="col-md-6">
	              			<div class="row form-group">	              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Item Group <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
									<select name="item_group" id="item_group" class="form-control"  >
									<option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getitemgroup}" varStatus="num" >
           									<option value="${item[0]}" <c:if test="${item[0] eq edititem_masterCmd.item_group}">selected="selected"</c:if>> ${item[1]}</option>
           								</c:forEach>
									</select>
								</div> 							
	  						</div>
	  						</div>
	  						<div class="col-md-6">
							<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Item Category <strong style="color: red;">*</strong></label>
             					</div>
             					<div class="col-12 col-md-6">
									<select name="category_code" id="category_code" class="form-control"  >
									<option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getitemcat}" varStatus="num" >
           									<option value="${item[1]}"  <c:if test="${item[1] eq edititem_masterCmd.category_code}">selected="selected"</c:if>> ${item[1]}</option>
           								</c:forEach>
									</select>
								</div> 
             				</div>	
             				</div>           					
	  					</div>
	  					<div class="col-md-12">	              					
	              			<div class="col-md-6">
	              			<div class="row form-group">	              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Critical Equipment (Yes/No) <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
               					<input type="hidden"  name="critical_equipment_hid" id="critical_equipment_hid" value="${edititem_masterCmd.critical_equipment}">
									<select name="critical_equipment" id="critical_equipment" class="form-control"  >
									<option value="Yes">Yes</option>
									<option value="No">No</option>
									</select>
								</div> 							
	  						</div>
	  						</div>
	  						<div class="col-md-6">
							<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Broad Category of Stores <strong style="color: red;">*</strong> </label>
             					</div>
             					<div class="col-12 col-md-6">
									<select name="engr_stores_origin" id="engr_stores_origin" class="form-control">
									<option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getbroadcat}" varStatus="num" >
           									<option value="${item[1]}"  <c:if test="${item[1] eq edititem_masterCmd.engr_stores_origin}">selected="selected"</c:if>> ${item[1]}</option>
           								</c:forEach>
									</select>
								</div> 
             				</div>	
             				</div>           					
	  					</div>			  					
	  					<div class="col-md-12">	              					
	              			<div class="col-md-6">
	              			<div class="row form-group">	              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Accounting Unit <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
									<select name="acc_units" id="acc_units" class="form-control">	
									<option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getaccunit}" varStatus="num">
           									<option value="${item[1]}"  <c:if test="${item[1] eq edititem_masterCmd.acc_units}">selected="selected"</c:if>> ${item[1]}</option>
           								</c:forEach>							
									</select>
								</div> 							
	  						</div>
	  						</div>
	  						<div class="col-md-6">
	              			<div class="row form-group">	              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class="form-control-label">Class <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
									<select name="class_item" id="class_item" class="form-control"  >
										<option value="0">--Select--</option>
                 						<c:forEach var="item" items="${getDomainListClassData}" varStatus="num" >
           									<option value="${item}" <c:if test="${item eq edititem_masterCmd.class_item}">selected="selected"</c:if>> ${item}</option>
           								</c:forEach>
									</select>
								</div> 							
	  						</div>
	  						</div>
	  					</div>	
	  					
	  					<div class="col-md-12">	              					
	              			<div class="col-md-6">
	              			<div class="row form-group">	              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Nodal Dte <strong style="color: red;">*</strong></label>
               					</div>
               					<div class="col-12 col-md-6">
								<select name="nodal_dte" id="nodal_dte" class="form-control" >	
	           							<option value="ALL">--Select--</option>
	           							<c:forEach var="item" items="${getLine_DteList}">
	           								<option value="${item.line_dte_sus}" <c:if test="${item.line_dte_sus eq edititem_masterCmd.nodal_dte}">selected="selected"</c:if>> ${item.line_dte_name}</option>
	           							</c:forEach>                  								
							        </select>
								</div> 							
	  						</div>
	  						</div>
	  						
	  					
	  					</div>	
	  				<div class="col-md-12">	
	  					 <div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
	                  				<label class=" form-control-label">Remarks </label>
	                			</div>
               					<div class="col-12 col-md-6">
               						<input type="hidden" id="remarks_hid" name="remarks_hid" class="form-control char-counter1"  value="${edititem_masterCmd.remarks}">
                 						<textarea  id="remarks" name="remarks" maxlength="255" class="form-control char-counter1"  ></textarea>
               					</div>	 							
	  						</div>
	  						</div>
						</div>		
	              	</div>
					<div class="card-footer" align="center">
	              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isValidation();">
	              		<a href="search_item_master" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div>
	        	</div>
			</div>
	</div>
</form:form>
<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled" />
</c:if>
	<c:url value="updateItemArmUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid"  target="result">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>
<script type="text/javascript">

$(document).ready(function() {
	  $('#remarks').keyup(function() {
	        this.value = this.value.toUpperCase();
	    });

	  $("select#critical_equipment").val($("input#critical_equipment_hid").val());
	  $("textarea#remarks").val($("input#remarks_hid").val());
	
	$('#prf_group').change(function() {
	   var prf_group = this.value;
		 $.post("getcosno?"+key+"="+value, {prf_group: val}).done(function(j) {     
	  		for ( var i = 0; i < j.length; i++) {
	  			$("#cos_sec_no").val(j[i].coss_section); 	    			
	  		}	
		 }).fail(function(xhr, textStatus, errorThrown) { }); 
	  });
	   $.ajaxSetup({
	    async: false
	});
	   
	    try{
            if(window.location.href.includes("&msg="))
            {
                    var url = window.location.href.split("?updateid=")[0];
                    var id= window.location.href.split("?updateid=")[1].split("&msg=")[0];
                     document.getElementById('updateid').value=id;
                      document.getElementById('updateForm').submit();
            }
            
    }
    catch (e) {
            // TODO: handle exception
    }

});

function isValidation()
{
	if($("input#prf_group").val() == "")
	{
		alert("Please enter PRF Group");
		$("input#prf_group").focus();
		return false;
	}
	 if($("input#item_type").val() == "")
	{
		alert("Please enter Nomenclature");
		$("input#item_type").focus();
		return false;
	} 
	if($("select#item_group").val() == "0")
	{
		alert("Please enter Item Group");
		$("select#item_group").focus();
		return false;
	}  
	if($("select#category_code").val() == "0")
    {
            alert("Please enter Item Category");
            $("select#category_code").focus();
            return false;
    }
	if($("select#engr_stores_origin").val() == "0")
	{
		alert("Please enter Broad Category of Stores");
		$("select#engr_stores_origin").focus();
		return false;
	}
	if($("select#acc_units").val() == "0")
	{
		alert("Please enter Accountng Unit");
		$("select#acc_units").focus();
		return false;
	}
	if($("select#class_item").val() == "0")
	{
		alert("Please select class");
		$("select#class_item").focus();
		return false;
	}
	return true;
}

</script>