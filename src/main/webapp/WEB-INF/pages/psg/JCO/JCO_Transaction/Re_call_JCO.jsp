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

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<div id="div_re_call_jco" class="tabcontent" style="display: block;">
	<form:form id="form_div_call_jco" action="re_call__jcoAction" method="post" commandName="re_call__jcoCMD">
		<div class="animated fadeIn">
	    	<div class="container-fluid" align="center">
	    	   <div class="card">
	    		<div class="card-header"><h5>JCO RECALLED FROM RESERVE</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by Unit)</span></h6></div>
	          		<div class="card-body card-block">
					<br> 
				 		<div class="col-md-12">	              						              					              				
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Army No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="army_no" name="army_no" class="form-control autocomplete" autocomplete="off"  onchange="army_number();" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
						                   maxlength="12" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)" > 						                   						               							                   						          
						               </div>
						           </div>
	              			</div>
	              		</div>				
						<div class="col-md-12" >	              					
            				<div class="col-md-6">
									<div class="row form-group">
										<div class="col-md-4">
											<label class=" form-control-label"> Name </label>
										</div>
										<div class="col-md-8">
											<label id="name" name="name"> </label>
										</div>
									</div>
							</div>																
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col-md-4">
										<label class=" form-control-label"> Rank </label>
									</div>
									<div class="col-md-8">
										<label id="rank" name="rank"> </label>
										<input type="hidden" id="rank_id" name="rank_id"> </input>
										
									</div>
								</div>
							</div>								
						</div>
									
					<div class="col-md-12">
				  		<label class=" form-control-label"  style="margin-left:10px;"><h4>AUTHORITY</h4></label>
				 	</div>
					<div class="col-md-12">	              					
             			<div class="col-md-6">
             				<div class="row form-group">
				               <div class="col-md-4">
				                    <label class=" form-control-label"> Authority No </label>
				                </div>
				                <div class="col-md-8">
				                
				                <input type="hidden" id="army_no_e1" name="army_no_e1" class="form-control autocomplete"  > 
				                 <input type="hidden" id="statusAC" name="statusAC" class="form-control autocomplete"  > 
				                 <input type="hidden" id="unit_nameC" name="unit_nameC" class="form-control autocomplete"  > 
				                 <input type=hidden id="unit_sus_noC" name="unit_sus_noC" class="form-control autocomplete"  > 
				                  <input type="hidden" id="cr_byC" name="cr_byC" class="form-control autocomplete"  > 
						          <input type="hidden" id="cr_dateC" name="cr_dateC" class="form-control autocomplete"  > 	
		
				                
				                   
				                    <input type="text" id="authority" name="authority" class="form-control autocomplete" maxlength="100" autocomplete="off" 
				                     onkeyup="return specialcharecter(this)"> 				                   
				                  </div>
				              </div>
             				</div>	            				
             				<div class="col-md-6">
             				   <div class="row form-group">
				                 <div class="col-md-4">
				                    <label class=" form-control-label"> Date of Authority </label>
				                </div>
				                <div class="col-md-8">
				                   <input type="text" name="auth_dt" id="auth_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
				                </div>
				             </div>
             			  </div>	              				
	              		</div>
              		<div class="col-md-12">	
              			<br>
              		</div>	              		
					<div class="col-md-12">
					  	<label class=" form-control-label"  style="margin-left:10px;"><h4>RECALL</h4></label>
					 </div>
					 <div class="col-md-12">	              					
             			<div class="col-md-6">
             				<div class="row form-group">
				                <div class="col-md-4">
				                    <label class=" form-control-label"> Recall From Date </label>
				                </div>
				                <div class="col-md-8">
				                  	<input type="text" name="granted_fr_dt" id="granted_fr_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
				                </div>
				             </div>
             			</div>	
	              		<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> Date of TOS </label>
								</div>
								<div class="col-md-8">
									 <input type="text" name="date_of_tos" id="date_of_tos" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
										onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
										onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
								</div>
							</div>
						</div>
	              	</div>
				 				<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off"   
						                 style="display: none"     maxlength="8" onkeypress="return AvoidSpace(event)" onkeyup="return specialcharecter(this)"> 
						            <input type="hidden" id="jco_id" name="jco_id" class="form-control autocomplete" autocomplete="off">
									<input type="hidden" id="rc_id" name="rc_id" class="form-control autocomplete" autocomplete="off" value="0">
									<input type="hidden" id="tenure_date" name="tenure_date" class="form-control autocomplete" autocomplete="off" >
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off"  style="display: none"
						                   maxlength="50" onkeyup="return specialcharecter(this)">				
										   <label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label> 
										</div>
						            </div>
	              				</div>
	              			</div>
					
										
					<div class="col-md-12" style="display: none;">	 					  
						<div class="col-md-6" >
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> Appt </label>
								</div>
								<div class="col-md-8">
								 	<label id="appt"></label> 
								</div>
							</div>
						</div>						
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">Date of Appt</label>
								</div>
								<div class="col-md-8">
									<label id="date_of_appt"></label>
								</div>
							</div>
						</div>												
					</div>	
					<div class="col-md-12" id="xlist" style="display: none" >
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> X List SUS No </label>
								</div>
								<div class="col-md-8">
									 <input type="text" id="x_sus_no" name="x_sus_no" value="" class="form-control autocomplete" autocomplete="off">   
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> X List Loc </label>
								</div>
								<div class="col-md-8">
									 <textarea  id="x_list_loc" name="x_list_loc" value="" class="form-control autocomplete" autocomplete="off">   </textarea>
								</div>
							</div>
						</div>
					</div>							
	              	<div class="col-md-12">	
	              		<br>
	              	</div>              				              								            				
	             </div>		              														
					<div class="card-footer" align="center" class="form-control">
						<a href="re_call_jcourl" class="btn btn-success btn-sm" >Clear</a> 
						<input type="button" class="btn btn-primary btn-sm" value="Save" onclick="Re_call_save_fn();"> 	
						<input type="button"  id ="Cancle" class="btn btn-info btn-sm" onclick="Cancel();" style="display:none; " value="Back" >
	            	</div> 			
				</div>
			</div>
		</div>
  </form:form>
</div>
<c:url value="Search_Re_CallJCO" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="army_no1">
	<input type="hidden" name="army_no1" id="army_no1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="unit_name1" id="unit_name1"  />
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
	<input type="hidden" name="cr_date1" id="cr_date1"  />
	<input type="hidden" name="cr_by1" id="cr_by1"  />	
</form:form>
<script>
$(function() {
	$("#from_dt").change(function() {
		var f_month = $(this).val();
		$("#to_dt").attr("min", f_month);
	});
});
$(function() {
	$("#to_dt").change(function() {
		var f_month = $(this).val();				
		$("#from_dt").attr("max", f_month);
	});
});
function Cancel(){
  	
	$("#army_no1").val($("#army_no_e1").val()) ;
	$("#status1").val($("#statusAC").val()) ;
	$("#unit_name1").val($("#unit_nameC").val()) ;
	$("#unit_sus_no1").val($("#unit_sus_noC").val()) ;
	$("#cr_by1").val($("#cr_byC").val()) ;
	$("#cr_date1").val($("#cr_dateC").val()) ;
	document.getElementById('searchForm').submit();
}

$(document).ready(function() {	
	//alert('${jco_id}');
	$("#jco_id").val('${jco_id}');
	$("#army_no_e1").val('${army_no6}');
	$("#unit_sus_noC").val('${unit_sus_no5}');
	$("#unit_nameC").val('${unit_name5}');
	$("#statusAC").val('${statusA5}');
	$("#cr_byC").val('${cr_by5}');
	$("#cr_dateC").val('${cr_date5}'); 	
	if('${statusA5}' =="0" || '${statusA5}' =="3"  ){		
		 $("#Cancle").show(); 
	}
	var r =('${roleAccess}');
	if( r == "Unit"){
		
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
		 
	}
	else  if(r == "MISO"){
		
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
		
	}
	
	
   var sus_no = '${roleSusNo}';
  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
  		var l=data.length-1;
  		var enc = data[l].substring(0,16);    	   	 
  	 	$("#unit_name").text(dec(enc,data[0]));
  		}).fail(function(xhr, textStatus, errorThrown) {
	  });
	if ('${roleSusNo}' != "") {
		$('#roleSusNo').val('${roleSusNo}');
	}
	if ('${name}' != "") {
		$('#name').val('${name}');
	}
	if ('${rank}' != "") {
		$('#rank').val('${rank}');
	}	
		
	$("#reserve").hide();	
	$('#army_no').val('${army_no_e}');
	army_number();
	 
	getRe_CallDataJCO();
});

jQuery(function($){ //on document.ready  
	 datepicketDate('auth_dt');
	 datepicketDate('granted_fr_dt');
	 datepicketDate('date_of_tos');
	});

//*************************************MAIN army_no Number Onchange*****************************//


function SetMin(){
	if ($("input#tenure_date").val() != "") {
		var tenure_dt = $("input#tenure_date").val();
		setMinDate('date_of_tos', tenure_dt);
	}
}


	
function editData(id,jco_id,army_no)
{
	 
	 $("#rc_id").val(id);
	 $("#jco_id").val(jco_id);
	 getRe_CallDataJCO();
	 $("#army_no").val(army_no);
	 army_number();
}

$("input#army_no").keypress(function(){	
	var army_no = this.value;	
		 var susNoAuto=$("#army_no");		
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getarmy_noListRe_Call?"+key+"="+value,
		        data: {army_no:army_no},
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
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Army No");
		        	  document.getElementById("army_no").value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {		    	
		      } 	     
		}); 			
});
////unit sus no


$("#x_sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#x_sus_no");
	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTargetSUSNoList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                        for(var i = 0;i<data.length;i++){
                                susval.push(dec(enc,data[i]));
                        }
                        var dataCountry1 = susval.join("|");
                        var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Unit SUS No.");
	        	  document.getElementById("x_sus_no").value="";
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			/* var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#unit_posted_to").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         }); */
		} 	     
	});	
});



 function appNamechng(){
		var appname = $("#appt").find("option:selected").text();
			if(appname == "ON ATTACHMENT" || appname == "ON COURSE ABROAD" || appname == "ON COURSE EXCEEDING 10 WEEKS" 
					|| appname == "ON DEPUTATION" || appname == "ON FURLOUGH LEAVE"
					|| appname == "ON LEAVE PENDING RETIREMENT R/R" || appname == "ON STUDY LEAVE"){
				$("#xlist").show();
				}
			else{
				$("#xlist").hide();
				$("#x_sus_no").val("");
				$("#x_list_loc").val("");
			 
		}
	}
//*************************************END Personel Number Onchange*****************************//
//******************************Start ReEmployment***************************// 

function Re_call_save_fn()
{
	if($("input#army_no").val()  ==  ""){
		alert("Please Select Army No");
		$("input#army_no").focus();
		return false;
	}
	 if($("input#authority").val().trim() == ""){
		alert("Please Enter Authority No.");
		$("input#authority").focus();
		return false;
	}
	if ($("#auth_dt").val().trim() == "" || $("#auth_dt").val().trim() == "DD/MM/YYYY") {
		alert("Please Select Authority Date");
		$("#auth_dt").focus();
		return false;
	}
	if($("input#granted_fr_dt").val().trim() == "" || $("#granted_fr_dt").val().trim() == "DD/MM/YYYY"){
		alert("Please Select Recall From Date");
		$("input#granted_fr_dt").focus();
		return false;
	}
	if($("input#date_of_tos").val().trim() == "" || $("#date_of_tos").val().trim() == "DD/MM/YYYY"){
		alert("Please Select Date of TOS");
		$("input#date_of_tos").focus();
		return false;
	} 
	
	var r =('${roleAccess}');
	if(r == "MISO"){
		
		if ($("#unit_sus_no").val() == "") {
			alert("Please Enter Unit SUS Number ");
			$("#unit_sus_no").focus();
			return false;
		}
		if ($("#unit_name").val() == "") {
			alert("Please Enter Unit Name ");
			$("#unit_name").focus();
			return false;
		}
	}
	var army_no = $('#army_no').val();  
	var unit_sus_no = $('#unit_sus_no').val();  
	var unit_posted_to = $('#unit_name').val();
	var appt = $('#appt').val();
	var x_sus_no = $('#x_sus_no').val();
	var x_list_loc = $('#x_list_loc').val();
	var date_of_appt = $('#date_of_appt').val();
	var date_of_tos = $('#date_of_tos').val();
	var authority = $('#authority').val();  
	var auth_dt = $('#auth_dt').val();  
	var from_dt = $('#from_dt').val();  
	var to_dt = $('#to_dt').val();  
	var cause_of_release = $('#cause_of_release').val();  
	var dt_of_release = $('#dt_of_release').val();  
	var re_emp_select = $('#re_emp_select').val(); 
	var granted_fr_dt = $('#granted_fr_dt').val(); 	
	var jco_id = $('#jco_id').val(); 	
	var rc_id = $('#rc_id').val();
	var rank_id = $('#rank_id').val();
	//alert("appt--"+appt)
		
	 $.post('Re_call_JCOAction?' + key + "=" + value, {rc_id:rc_id,re_emp_select:re_emp_select,granted_fr_dt:granted_fr_dt,
		unit_sus_no:unit_sus_no,unit_posted_to:unit_posted_to,appt:appt,x_sus_no:x_sus_no,x_list_loc:x_list_loc,
		date_of_appt:date_of_appt,date_of_tos:date_of_tos,authority:authority,auth_dt:auth_dt,from_dt:from_dt,to_dt:to_dt,
		cause_of_release:cause_of_release,dt_of_release:dt_of_release,jco_id:jco_id,rank_id:rank_id,army_no:army_no}, function(data){
	
        if(data=="update")
      	  alert("Data Updated Successfully");
        else if(parseInt(data)>0)
	       	  {	    	   
	        	alert("Data Saved Successfully");
	        	$("#tab_id").show();
	        	$("#rc_id").val(data)	        	        	
	        	$('#jco_id').val(data[0]);	        	 	        	
	          }
	          else
	          {
	        	  alert(data);
	          }
	        	
	 	  	}).fail(function(xhr, textStatus, errorThrown) 
	 	  		{
	 	  					
	  		}); 

}
function getRe_CallDataJCO()
{
	
	 var jco_id1 = $('#jco_id').val(); 
	 
	 if(jco_id1 !="" )
		 {		
		 $.post('getRe_CallDataJCO?' + key + "=" + value,{ jco_id : jco_id1},function(h) { 	    	
    		 if(h.length != 0)
    		 {	
    			 
    			 $('#unit_sus_no').val(h[0].unit_sus_no);    	
    			 $('#unit_posted_to').val(h[0].unit_posted_to); 
    			 $('#rc_id').val(h[0].id); 
    			 $('#appt').val(h[0].appt); 
    			 $('#date_of_appt').val(ParseDateColumn(h[0].date_of_appt)); 
    			 $('#date_of_tos').val(ParseDateColumncommission(h[0].date_of_tos)); 
    			 $('#authority').val(h[0].authority);    		   			
    			 $("#auth_dt").val(ParseDateColumncommission(h[0].auth_dt));
    			 $('#granted_fr_dt').val(ParseDateColumncommission(h[0].granted_fr_dt));    			 
    			 $('#re_emp_select').val(h[0].re_emp_select);   
    			  
    			 appNamechng();  
    			 var sus_no = h[0].unit_sus_no;	
				 $.post("getTargetUnitNameList?"+key+"="+value, {
					 sus_no:sus_no
				         }, function(j) {
				                
				         }).done(function(j) {
				        	 var length = j.length-1;
				             var enc = j[length].substring(0,16);
				             $("#unit_name").val(dec(enc,j[0]));   
				                 
				         }).fail(function(xhr, textStatus, errorThrown) {
			         });
    		 }
   		});	
	  }  
   } 
	

</script>

<script>
// Unit SUS No

$("#unit_sus_no").keyup(function(){
	var sus_no = this.value;
	var susNoAuto=$("#unit_sus_no");

	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getTargetSUSNoList?"+key+"="+value,
	        data: {sus_no:sus_no},
	          success: function( data ) {
	        	  var susval = [];
                  var length = data.length-1;
                  var enc = data[length].substring(0,16);
                        for(var i = 0;i<data.length;i++){
                                susval.push(dec(enc,data[i]));
                        }
                        var dataCountry1 = susval.join("|");
                        var myResponse = []; 
				        var autoTextVal=susNoAuto.val();
				$.each(dataCountry1.toString().split("|"), function(i,e){
					var newE = e.substring(0, autoTextVal.length);
					if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
					  myResponse.push(e);
					}
				});      	          
				response( myResponse ); 
	          }
	        });
	      },
		  minLength: 1,
	      autoFill: true,
	      change: function(event, ui) {
	    	 if (ui.item) {   	        	  
	        	  return true;    	            
	          } else {
	        	  alert("Please Enter Approved Unit SUS No.");
	        	  document.getElementById("unit_name").value="";
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getTargetUnitNameList?"+key+"="+value, {
				 sus_no:sus_no
         }, function(j) {
                
         }).done(function(j) {
        	 var length = j.length-1;
             var enc = j[length].substring(0,16);
             $("#unit_name").val(dec(enc,j[0]));   
                 
         }).fail(function(xhr, textStatus, errorThrown) {
         });
		} 	     
	});	
});

// unit name
 $("input#unit_name").keypress(function(){
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
			        	  return true;    	            
			          } else {
			        	  alert("Please Enter Approved Unit Name.");
			        	  document.getElementById("unit_name").value="";
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
					        	$("#unit_sus_no").val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
	});

 function army_number()
 {
 	var army_no = $("input#army_no").val();
 	
 
	 $.post('GetArmyNoDataReCall?' + key + "=" + value,{ army_no : army_no },function(j) {
		// var jco_id =j[0].id;
		//$("#jco_id").val(j[0].id);	 
		 
	   	$("#jco_id").val(j[0][0]);	  
	   	$("#name").text(j[0][3]);
	 	$("#rank").text(j[0][7]);
	 	$("#rank_id").val(j[0][8]);
	 	
    
   	  	   getRe_CallDataJCO();  
   		
   		 
   		/*	 $.post('GetTenureDATA?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		
    	    	 if(k.length > 0){
   		   			$('#tenure_date').val(ParseDateColumncommission(k[0][2]));
   		   			
   		   			SetMin();
   	   		 }	 
   		 
   	});
	    		 
	   }); */
 }); 	
 }

</script>

