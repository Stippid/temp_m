<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>


<script type="text/javascript">
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
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
</script>

<form:form name="edit_pers_entit" id="edit_pers_entit" action="edit_pers_entitAct" method='POST' commandName="edit_pers_entitCmd" > 
	<div class="animated fadeIn">
			<div class="container" align="center">
	    		<div class="card">
	          		<div class="card-header"><h5>Edit STANDARD Authorisation FOR PERSONNEL</h5></div>
	          		<div class="card-body card-block cue_text">
	          			<div class="col-md-12">	              					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">MISO WE/PE No </label>
               					</div>
               					<div class="col-12 col-md-6">
               					<input type="hidden" id="id" name="id" placeholder="" class="form-control"  value="${edit_pers_entitCmd.id}">
                 					<input  id="we_pe_no" name="we_pe_no" class="form-control"  maxlength="100" autocomplete="off"  value="${edit_pers_entitCmd.we_pe_no}" readonly="readonly" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
								</div>
							</div>	 							
	  						</div>
	  					</div>	
	            		<div class="col-md-12">	              					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Table Title</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="tableTitle" class="form-control" autocomplete="off"  readonly="readonly">
								</div>
							</div>	 							
	  						</div>
             				<div class="col-md-6">
 							<div class="row form-group">  								
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">User Arm</label>
               					</div>
               					<div class="col-12 col-md-6">
               					   <input type="text" name="user_arm_hi" id="user_arm_hi" readonly="readonly" class="form-control">
                 					<input type="hidden" id="user_arm" name="" placeholder="" readonly="readonly" class="form-control">
								</div>
 							</div>
 						</div>     					
	  					</div>	
	  					<div class="col-md-12"><span class="line"></span></div>
	  					<div class="col-md-12">	              					
	              			<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Category of Personnel </label>
               					</div>
               					<div class="col-12 col-md-6">
               					<input type="hidden" id="category_of_persn_hidden" name="category_of_persn" placeholder="" class="form-control"  value="${edit_pers_entitCmd.category_of_persn}">
                 					<input type="text" id="category_of_persn" name="" placeholder="" class="form-control" readonly="readonly">
								</div>	 							
	  						</div>
	  						</div>
	  						<div class="col-md-6">	
	              			<div class="row form-group">              			 	
               					<div class="col col-md-6">
                 					<label for="text-input" class=" form-control-label">Parent Arm</label>
               					</div>
               					<div class="col-12 col-md-6">
                 					<input id="parent_arm_hid"  name="parent_arm_hid" placeholder="" class="form-control" readonly="readonly" style="display: none" >
                 					 <select id="parent_arm1" name="parent_arm1" class="form-control" style="display: none" disabled="disabled"></select>
				                <input type="hidden" name="arm_code" id="arm_code" maxlength="4" value="${edit_pers_entitCmd.arm_code}" >
								</div>	 							
	  						</div>
	  						</div>         					
	  					</div>
	  					<div class="col-md-12">	
		  					<div class="col-md-6">	
		  						<div class="row form-group">           					
	             					<div class="col col-md-6">
	               						<label class=" form-control-label">Category </label>
	             					</div>
	             					<div class="col-12 col-md-6">
	             					   <select  id="rank_cat" name="" class="form-control" disabled ="disabled">
				                		<option value="">--Select--</option>
	                                    <c:forEach var="item" items="${getTypeofRankcategoryListFinal}" varStatus="num" >
                 							<option value="${item.codevalue}"  <c:if test="${item.codevalue eq edit_pers_entitCmd.rank_cat}">selected="selected"</c:if>>${item.label}</option>
                 						</c:forEach>
             					
           								</select>
           								
	             					</div>
	             				</div>
	             				</div>
             				<div class="col-md-6">
  							<div class="row form-group">
				                <div class="col col-md-6">
				                  	<label class=" form-control-label">Common Appt/Trade</label>
				                </div>
				                <div class="col-12 col-md-6">
				                <input type="hidden" id="app_trd_code" name="app_trd_code" value="${edit_pers_entitCmd.app_trd_code}">
				                <input  id="appt_trade" name="appt_trade"  class="form-control" autocomplete="off"   value="${edit_pers_entitCmd.appt_trade}" readonly="readonly" style="font-family: 'FontAwesome',Arial;" placeholder="&#xF002; Search">
				                </div>
				            </div>	  								
  						   </div>          					
	  					</div>
	  					<div class="col-md-12">	
		  					<div class="col-md-6">
		              			<div class="row form-group">	              			 	
	               					<div class="col col-md-6">
	                 					<label for="text-input" class=" form-control-label">Rank </label>
	               					</div>
	               					<div class="col-12 col-md-6">
	               					     <input id="rank" name=""  class="form-control"  readonly="readonly"  >
				              			 <input type="hidden" id="rank_select" name="rank" class="form-control"  value="${edit_pers_entitCmd.rank}"  >
									</div>	 							
		  						</div>
		  						</div>
	  						<div class="col-md-6">
							<div class="row form-group">           					
             					<div class="col col-md-6">
               						<label class=" form-control-label">Authorisation <strong style="color: red;">*</strong> </label>
             					</div>
             					<div class="col-12 col-md-6">
               						<input id="auth_amt1" name="auth_amt1" maxlength="5" class="form-control" value="${edit_pers_entitCmd.auth_amt}" onkeypress="return isNumberKey(event,this)">
             					</div>
             				</div>	
             				</div>				
	  					</div>	 
	              	</div>
					<div class="card-footer" align="center">
	              		<input type="submit" class="btn btn-primary btn-sm" value="Update" onclick="return isValid();">
	              		<a href="search_pers_entitUrl" class="btn btn-danger btn-sm" onClick="javascript:window.close('','_parent','');">  Cancel </a>
		            </div> 
	        	</div>
			</div>
	</div>
</form:form>

<c:if test="${not empty msg}">
	<input type="hidden" name="msg" id="msg" value="${msg}" disabled="disabled"/>
</c:if>

	<c:url value="updatePersonal_EntitalUrl" var="updateUrl" />
	<form:form action="${updateUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="updateid">
		<input type="hidden" name="updateid" id="updateid" value="0"/>
	</form:form>

<script type="text/javascript">
$(document).ready(function() {
	$('#auth_amt1').keyup(function(){
		  if ($(this).val() > 100){
		    alert("Authorisation of manpower entered is more than 100");
		    //$(this).val('100');
		  }
		});
	
	select_rank_cat1();	
	
	getArmByWePeNo(document.getElementById("we_pe_no").value);
	
	var category_of_persn_hidden = $("#category_of_persn_hidden").val();
	
	
	$('input#appt_trade').change(function() {		
		var a = $(this).val();
		
		 $.post("getappt_trade_codelist?"+key+"="+value, {a : a}).done(function(j) {
			 document.getElementById("app_trd_code").value=j[0];	
		 }).fail(function(xhr, textStatus, errorThrown) { }); 
	});
	
	 $.post("getPersonCatList?"+key+"="+value).done(function(j) {
		var length = j.length-1;
		var enc = j[length].columnName1.substring(0,16);
		var options = '<option value="' + " " + '">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length-1; i++) {
				if(category_of_persn_hidden == dec(enc,j[i].columnCode)){
					$("#category_of_persn").val(dec(enc,j[i].columnName));
				}
			}
	 }).fail(function(xhr, textStatus, errorThrown) { }); 
	 
	
     $('select#category_of_persn').change(function() {
        	
        	var code = $(this).find('option:selected').attr("name");  
        	if(code == "Regimental")	        		
        	{
        		$("#parent_arm_hid").val( $("#user_arm_hi").val());
        		document.getElementById("parent_arm_hid").disabled = true; 		
        		$("select#parent_arm1").hide();		
        		$("input#parent_arm_hid").show();
        		$("#arm_code").val($("#user_arm").val());
        		
        	}
        	else
        	{
        		$("#parent_arm_hid").val("");
        		document.getElementById("parent_arm_hid").disabled = false; 
        		$("input#parent_arm_hid").hide();
        		$("select#parent_arm1").show();
        		parentArm();
        		
        	}
   	});
    
	  $('select#parent_arm1').change(function() {			
		$("#arm_code").val($("#parent_arm1").val());
	 });
	

});

	
function ex(){
	var parent_arm_name = document.getElementById("user_arm").value;
	
	 $.post("getArmNameByCode?"+key+"="+value, {parent_arm_name:parent_arm_name}).done(function(j) {
		 $("#user_arm_hi").val(j);  
			regi_arm();
	 }).fail(function(xhr, textStatus, errorThrown) { }); 
}
	
var parent_arm_hid = $("#arm_code").val();

function parentArm()
{
	
	var u_a = document.getElementById("user_arm").value;
				
		 $.post("getArmNameListCue?"+key+"="+value, {u : u_a}).done(function(j) {
			 var length = j.length-1;
				var enc = j[length].columnName1.substring(0,16);
				var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>';
				for ( var i = 0; i < j.length-1; i++) {
					if(parent_arm_hid == dec(enc,j[i].columnCode)){
						
						options += '<option value="'+dec(enc,j[i].columnCode)+'"  name="' + dec(enc,j[i].columnName)+ '" selected=selected>'+ dec(enc,j[i].columnName)+ '</option>';
					}
					else
					options += '<option value="'+dec(enc,j[i].columnCode)+'" name="' + dec(enc,j[i].columnName)+ '" >'+ dec(enc,j[i].columnName)+ '</option>';
				}	
				$("select#parent_arm1").html(options); 
		}).fail(function(xhr, textStatus, errorThrown) { }); 
		 
	
}

function getArmByWePeNo(val)
{
	 $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : val}).done(function(j) {
		 document.getElementById("user_arm").value=j[0].arm;	
			document.getElementById("tableTitle").value=j[0].table_title;
			 ex();
	 }).fail(function(xhr, textStatus, errorThrown) { }); 

}

function select_rank_app_trade(){
	var rnk = $("select#rank_cat").val();
	 $('#appt_trade').val("");
	$('#app_trd_code').val(""); 
	
	}


	 function select_rank_cat(){
		
		var rnk = $("select#rank_cat").val();
		 $('select#rank_select').val("");
		 $.post("getTypeofRankList?"+key+"="+value, {rnk : rnk}).done(function(j) {
				var options = '<option value="">'+ "--Select--" + '</option>';
				for ( var i = 0; i < j.length; i++) {
					options += '<option value="' + j[i][1]+ '" >'+ j[i][0]+ '</option>';	
									
				}	
				$("select#rank_select").html(options);	
		     }).fail(function(xhr, textStatus, errorThrown) { }); 
		 
	}
	 
	 
	 var rnk = document.getElementById("rank_select").value;
	
	 $.post("getTypeofRankList_enti_der?"+key+"="+value, {rnk:rnk}).done(function(j) {
		 $("#rank").val(j[0][0]);
	     }).fail(function(xhr, textStatus, errorThrown) { }); 
	 
	 
function select_rank_cat1(){
	var rnk =  document.getElementById("rank").value;
	
	 $.post("getTypeofRankList_enti_der?"+key+"="+value, {rnk:rnk}).done(function(j) {
		 var options = '<option value="">'+ "--Select--" + '</option>';
			for ( var i = 0; i < j.length; i++) {
				if(rnk ==  j[i][1]){
					options += '<option value="' + j[i][1]+ '" selected=selected >'+ j[i][0]+ '</option>';	
				}
				else options += '<option value="' + j[i][1]+ '" >'+ j[i][0]+ '</option>';	
				
			}	
			$("select#rank_select").html(options);	
	     }).fail(function(xhr, textStatus, errorThrown) { }); 
	 
}
	$('select#rank_select').change(function() {		
		
		var a = $(this).val();
			document.getElementById("rank").value=a;				
			
	});
	
	var regi = document.getElementById("category_of_persn_hidden").value;
	var arm = document.getElementById("arm_code").value;
	function regi_arm(){
			
		if(regi == '2'){
			document.getElementById('parent_arm_hid').style.display = 'block';
			document.getElementById('parent_arm1').style.display = 'none';
			document.getElementById("parent_arm_hid").value = document.getElementById("user_arm_hi").value;
		}
		if(regi == '1'){
			document.getElementById('parent_arm_hid').style.display = 'none';
			document.getElementById('parent_arm1').style.display = 'block';		
			parentArm();
		}
	}

function isValid()
{
	if($("input#auth_amt1").val() == "" || $("input#auth_amt1").val() == "0" )
	{
		alert("Please enter Authorisation");
		$("input#auth_amt1").focus();
		return false;
	}
	return true;
}

//////////////////only numeric and point(.)
function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;
	if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) {
		return false;
	} else {
		if (evt.target.value.search(/\./) > -1 && charCode == 46) {
			return false;
		}
	}
	return true;
}

/////////////////////==== Only 0-9 numeric value
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
</script>
