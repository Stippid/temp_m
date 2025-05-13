<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>


	<div class="animated fadeIn"> 
	    	<div class="container-fluid" align="center">
	    	<br>
	    		<div class="card">
	    			<div class="card-header"><h5>CDA ACCOUNT NO</h5> <h6 class="enter_by">
<!-- 	    			<span style="font-size:12px;color:red;">(To be entered by MISO)</span> -->
	    			</h6></div>
			                <div class="card-body card-block"><br>
							<div class="row">
					 	<div class="col-md-12">
					 		<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Personal No </label>
						                </div>
						                <div class="col-md-8">
				  							<input type="text" id="personnel_no" name="personnel_no"class="form-control autocomplete" maxlength="9" autocomplete="off" onchange="personal_number()" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                     <label class=" form-control-label"><strong style="color: red;">* </strong> CDA Account No</label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="cda_acc_no" name="cda_acc_no" class="form-control autocomplete" maxlength="20" autocomplete="off" onkeyup="return specialcharecterCDA(this)" onkeypress="return AvoidSpace(event)">
						                  <input type="hidden" id="statusV" name="statusV" class="form-control autocomplete"  > 
						                  <input type="hidden" id="unit_sus_noV" name="unit_sus_noV" class="form-control autocomplete"  > 
						                  <input type="hidden" id="unit_nameV" name="unit_nameV" class="form-control autocomplete"  > 
						                  <input type=hidden id="rankV" name="rankV" class="form-control autocomplete"  > 
						                  <input type=hidden id="personnel_noV" name="personnel_noV" class="form-control autocomplete"  > 						                 						                  
						                  <input type="hidden" id="id" name="id" class="form-control autocomplete" autocomplete="off" value="0"> 
						                  <input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off"  value="0"> 						                   
						                  <input type="hidden" id="census_id" name="census_id" value="0" class="form-control autocomplete" autocomplete="off">
						                    <input type="hidden" id="cr_byV" name="cr_byV" class="form-control autocomplete"  > 
						                 <input type="hidden" id="cr_dateV" name="cr_dateV" class="form-control autocomplete"  > 
						             
						                </div>
						            </div>
	              				</div>	            				
	              			</div>
	              		
	              		<div class="col-md-12">
						  <div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Rank </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="p_rank"></label> 
									<input type="hidden" id="hd_p_rank" name="hd_p_rank" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> Name </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="cadet_name"></label>
									<input type="hidden" id="cname" name="cname" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> Unit Name </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="app_unit_name"></label>
									<input type="hidden" id="un" name="un" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;">* </strong> SUS No </label>
								</div>
								<div class="col-md-8">
									<label class=" form-control-label" id="app_sus_no"></label>
								</div>
							</div>
						</div>

					</div>	
							</div>
						  <div class="card-footer" align="center" class="form-control">
							    <a href="cda_acc_no_url" class="btn btn-success btn-sm" id ="clear" >Clear</a> 
			              		<input type="button" class="btn btn-primary btn-sm" value="Save" onclick="return validate_insertcda_save();">
			              		<input type="button" name="save" class="btn btn-secondary btn-sm" id ="popup"  value="View History " onclick="Pop_Up_History('CDA');" />
				            	 <input type="button"  id ="Cancle" class="btn btn-info btn-sm" onclick="Cancel();" style="display:none; " value="Back" >
			            	</div> 
						</div>		
	        	</div>
			</div>
	</div> 
		
<c:url value="GetSearch_CDA" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sea_cda2">
		<input type="hidden" name="personnel_no1" id="personnel_no1" />				
		<input type="hidden" name="rank1" id="rank1"/>		
		<input type="hidden" name="status1" id="status1" />
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="cr_date1" id="cr_date1"  />
	    <input type="hidden" name="cr_by1" id="cr_by1"  />
	</form:form> 
	<c:url value="Popup_CDAUrl" var="Popup_CDAURL" />
<form:form action="${Popup_CDAURL}" method="post" id="popup_cda" name="popup_cda" modelAttribute="id" target="result">
	<input type="hidden" name="comm_id1" id="comm_id1" value="0" />
</form:form>

	<c:url value="Search_CDAUrl" var="search_cda_2" />
<form:form action="${search_cda_2}" method="GET" id="search_cda" name="search_cda" modelAttribute="id" >
	
</form:form>

	
	<Script>
	function Cancel(){
	  	
		$("#status1").val($("#statusV").val()) ;
		$("#unit_sus_no1").val($("#unit_sus_noV").val()) ;
		$("#unit_name1").val($("#unit_nameV").val()) ;
		$("#rank1").val($("#rankV").val()) ;
		$("#personnel_no1").val($("#personnel_noV").val()) ;
		$("#cr_by1").val($("#cr_byV").val()) ;
		$("#cr_date1").val($("#cr_dateV").val()) ;
		
		document.getElementById('searchForm').submit();
	}
	

	
	function get_cda(){
		 var p_id=$('#census_id').val(); 
		 var comm_id=$('#comm_id').val(); 
		
		 var i=0;
		  $.post('cda_GetData?' + key + "=" + value, {p_id:p_id,comm_id:comm_id}, function(j){
				var v=j.length;
				
				if(v!=0){
					
					$('#id').val(j[i].id);
					 $("#cda_acc_no").val(j[i].cda_acc_no);
					 $("#clear").hide();
					 personal_number();
				}
		  });
	}	
	
	</Script>

<script>
//*************************************MAIN Personel Number Onchange*****************************//
$(document).ready(function() {
	if('${personnel_no_e}' != ""){
		
				$("#personnel_no").val('${personnel_no_e}');
				$("#personnel_no").attr("disabled", true);
				
				$("#personnel_noV").val('${personnel_no5}');
				
				$("#statusV").val('${status5}');
				$("#unit_sus_noV").val('${unit_sus_no5}');
				$("#unit_nameV").val('${unit_name5}');
				//26-01-1994
				$("#app_sus_no").text('${unit_sus_no5}');
				$("#app_unit_name").text('${unit_name5}');
				$("#rankV").val('${rank5}');
				$("#cr_byV").val('${cr_by5}');
				$("#cr_dateV").val('${cr_date5}');
				if('${status5}' =="0" || '${status5}' =="2"){
					
					  $("#Cancle").show(); 
			    }
			
				personal_number_retrive();
	}

});

function personal_number_retrive()
{
	
	if($("input#personnel_no").val()==""){
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}
	
	var personnel_no = $("input#personnel_no").val();
	 $.post('GetPersonnelNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
	    	$("#comm_id").val(j[0][0]);
	    	$("#dob").text(convertDateFormate(j[0][4]));
	    	$("#cadet_name").text(j[0][5]);
	    	$("#p_rank").text(j[0][6]);
	    	
	    	var comm_id =j[0][0]; 
	    	 $.post('GetCensusData?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		
	    		 if(k.length > 0)
	    		 {
	    			  $('#census_id').val(k[0].id); 
	    			 
	    		 }
	    		 if('${status5}'=="3"){
	    			 $('#id').val('${authDetails.id}');
	    			 $("#cda_acc_no").val('${authDetails.cda_acc_no}');
	    		 }else{
	    			 get_cda();	 
	    		 }
	    		
	   });
	    	 
  });
	 
	 
	
}

function personal_number() {

	if ($("input#personnel_no").val() == "") {
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	} 
	var personnel_no = $("input#personnel_no").val();
	//alert("hello---" + personnel_no);
	$.post('GetPersonnelNoData?' + key + "=" + value,{personnel_no : personnel_no},function(j) {
						if (j != "") {
							$("#comm_id").val(j[0][0]);
							var comm_id = j[0][0];
							$.post('GetCensusDataApprove?' + key+ "=" + value,{comm_id : comm_id},function(k) {
								if (k.length > 0) {
									$('#census_id').val(k[0][1]);
									$("#cadet_name").text(k[0][2]);
									$("#p_rank").text(k[0][3]);
									$("#hd_p_rank").val(k[0][3]);
									$("#app_sus_no").text(k[0][7]);
									
									$.ajaxSetup({
										async : false
									});
									
									var sus_no = k[0][7];
									getunit(sus_no);
									function getunit(val) {
							                $.post("getTargetUnitNameList?"+ key+ "="+ value,{sus_no : sus_no},function(j) {}).done(function(j) {
												var length = j.length - 1;
												var enc = j[length].substring(0,16);
												//alert("unit---" + dec(enc,j[0]));
												$("#app_unit_name").text(dec(enc,j[0]));
											}).fail(function(xhr,textStatus,errorThrown) {
									        });
									}
									$.ajaxSetup({
										async : false
									});
									var cen_id = k[0][1];
							}
						});
						}
					});

	$("input#personnel_no").attr('readonly', false);
	$("#popup").show();
}






$("input#personnel_no").keyup(function(){
	var personel_no = this.value;
		 var susNoAuto=$("#personnel_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListApproved?"+key+"="+value,
		        data: {personel_no:personel_no},
		          success: function( data ) {
		        	 var susval = [];
		        	  var length = data.length-1;
		        	  var enc = data[length].substring(0,16);
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
		    		 personal_number();
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Personal No");
		        	  document.getElementById("personnel_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	  
		      } 	     
		}); 	
		  
		 
});

function Pop_Up_History(a) {

	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
    popupWindow = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	window.onfocus = function () { 
	}
	comm_id = $("#comm_id").val();

	if(a == "CDA"){
		$('#comm_id1').val(comm_id);
		document.getElementById('popup_cda').submit();
	}
}


//*************************************END Personel Number Onchange*****************************//
</script>




<script>
function validate_insertcda_save(){
	if ($("input#personnel_no").val() == "") {
		alert("Please Enter Personnel Number.");
		$("input#personnel_no").focus();
		return false;
	}  
	if ($("input#cda_acc_no").val() == "") {
			alert("Please Enter CDA Account Number.");
			$("input#cda_acc_no").focus();
			return false;
	}    
	
	
	var personnel_no=$('#personnel_no').val();
	var cda_acc_no=$('#cda_acc_no').val();
	var cda_acc_id=$('#id').val();
	var census_id=$('#census_id').val();
	var comm_id=$('#comm_id').val();
	   $.post('Change_of_cda_action?' + key + "=" + value, {personnel_no:personnel_no,cda_acc_no:cda_acc_no,census_id:census_id,comm_id:comm_id,cda_acc_id:cda_acc_id}, function(data){
		      
	          if(data=="update"){
	        	  alert("Data Updated Successfully");
		          $("#search_cda").submit();
	          }
	          else if(parseInt(data)>0){
	        	 $('#cda_acc_id').val(data);
	        	  alert("Data Saved SuccesFully")
	          } else
	        	  alert(data);
	 	  		}).fail(function(xhr, textStatus, errorThrown) {
	 	  			alert("fail to fetch")
	  		});
}



function specialcharecterCDA(a)
{
    var iChars = "@#^&*$,.:;%!+_-[]";   
    var data = a.value;
    for (var i = 0; i < data.length; i++)
    {      
        if (iChars.indexOf(data.charAt(i)) != -1)
        {    
        alert ("This " +data.charAt(i)+" special characters not allowed.");
        a.value = "";
        return false; 
        } 
    }
    return true;
}



</script>

