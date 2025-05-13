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

<div id="div_re_call" class="tabcontent" style="display: block;">
		<div class="animated fadeIn">
	    	<div class="container-fluid" align="center">
	    	   <div class="card">
	    		<div class="card-header"><h5> RECALL FROM RESERVE</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by Unit)</span></h6></div>
	          		<div class="card-body card-block">
					<br> 
				 		<div class="col-md-12">	              						              					              				
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>Personal No</label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"  onchange="return personal_number();" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"
						                	   maxlength="9" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)" >
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
				                	<input type="hidden" id="personnel_no_e1" name="personnel_no_e1" class="form-control autocomplete"  > 						                
					                <input type="hidden" id="statusAC" name="statusAC" class="form-control autocomplete"  > 
					                <input type="hidden" id="unit_nameC" name="unit_nameC" class="form-control autocomplete"  > 
					                <input type=hidden id="unit_sus_noC" name="unit_sus_noC" class="form-control autocomplete"  > 			                  
				                    <input type="text" id="authority" name="authority" class="form-control autocomplete" maxlength="50" autocomplete="off" 
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
					                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" style="display: none"
					                 maxlength="8" onkeypress="return AvoidSpace(event)" onkeyup="return specialcharecter(this)"> 
					                 <input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off">
									 <input type="hidden" id="census_id" name="census_id" class="form-control autocomplete" autocomplete="off">
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
				                   <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" style="display: none"
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
									 <input type="text" id="x_sus_no" name="x_sus_no" value="" class="form-control autocomplete" maxlength="8" autocomplete="off">   
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> X List Loc </label>
								</div>
								<div class="col-md-8">
									 <textarea  id="x_list_loc" name="x_list_loc" value="" class="form-control autocomplete" maxlength="250" autocomplete="off"></textarea>
								</div>
							</div>
						</div>
					</div>							
	              	<div class="col-md-12">	
	              		<br>
	              	</div>              				              								            				
	             </div>		              														
					<div class="card-footer" align="center" class="form-control">
						<a href="re_callurl" class="btn btn-success btn-sm" >Clear</a> 
						<input type="button" class="btn btn-primary btn-sm" value="Save" onclick="Re_call_save_fn();"> 	
						<input type="button"  id ="Cancle" class="btn btn-info btn-sm" onclick="Cancel();" style="display:none; " value="Back" >
	            	</div> 			
				</div>
			</div>
		</div>
</div>
<c:url value="Search_Re_Call" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="personnel_no1">
	<input type="hidden" name="personnel_no1" id="personnel_no1" value="0" />
	
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
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
  	
	$("#personnel_no1").val($("#personnel_no_e1").val()) ;
	$("#status1").val($("#statusAC").val()) ;
	$("#unit_name1").val($("#unit_nameC").val()) ;
	$("#unit_sus_no1").val($("#unit_sus_noC").val()) ;
	 
	document.getElementById('searchForm').submit();
}

$(document).ready(function() {	
	
	$("#personnel_no_e1").val('${personnel_no6}');
	$("#unit_sus_noC").val('${unit_sus_no5}');
	$("#unit_nameC").val('${unit_name5}');
	$("#statusAC").val('${statusA5}');
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
	$('#personnel_no').val('${personnel_no}');
	personal_number();
	getRe_CallData();
});

jQuery(function($){ //on document.ready  
	 datepicketDate('auth_dt');
	 datepicketDate('granted_fr_dt');
	 datepicketDate('date_of_tos');
	});

//*************************************MAIN Personel Number Onchange*****************************//
function personal_number()
{
	var personnel_no = $("input#personnel_no").val();
	 $.post('GetRecallnoNoData?' + key + "=" + value,{ personnel_no : personnel_no },function(j) {
	   	   	   	   
		 if(j!=""){
			 	var comm_id =j[0].id;
		    	$("#comm_id").val(j[0].id);	  
		    	$("#rank").text(j[0].description);	  
		    	$("#appt").text(j[0].appt);	  
		    	$("#name").text(j[0].name);
		    	$("#date_of_appt").text(convertDateFormate(j[0].date_of_appointment));
		    	 $.post('GetRecallData?' + key + "=" + value,{ comm_id : comm_id },function(k) {
		    		
		    		 if(k.length > 0)
		    		 {	   
		    		   var census_id =k[0].id; 
		    	       $('#census_id').val(k[0].id); 
		    	  	   getRe_CallData();  
		    		 }
		    		 
		    		 $.post('GetTenureDATA?' + key + "=" + value,{ comm_id : comm_id },function(k) {
		 	    		
		     	    	 if(k.length > 0){
		    		   			$('#tenure_date').val(ParseDateColumncommission(k[0][2]));
		    		   			
		    		   			SetMin();
		    	   		 }	 
		    		 
		    	});
			    		 
			   });
		 }
   	
  }); 	
}


function SetMin(){
	if ($("input#tenure_date").val() != "") {
		var tenure_dt = $("input#tenure_date").val();
		setMinDate('date_of_tos', tenure_dt);
	}
}

function getRe_CallData()
{
	 var comm_id1 = $('#comm_id').val(); 
	 var census_id1 = $('#census_id').val();  
	 if(comm_id1 !="" && census_id1 !="")
		 {		
		 $.post('getRe_CallData?' + key + "=" + value,{ comm_id : comm_id1,ch_id:census_id1  },function(h) { 	    	
    		 if(h.length != 0)
    		 {	    			     			
    			 $('#unit_sus_no').val(h[0].unit_sus_no);    	
    			 $('#unit_name').val(h[0].unit_posted_to); 
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
	
function editData(id,census_id,comm_id,personnel_no)
{
	 $("#rc_id").val(id);
	 $("#census_id").val(census_id);
	 $("#comm_id").val(comm_id); 
	 getRe_CallData();
	 $("#personnel_no").val(personnel_no);
	 personal_number();
}

$("input#personnel_no").keyup(function(){	
	var personel_no = this.value;	
		 var susNoAuto=$("#personnel_no");		
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListRe_Call?"+key+"="+value,
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
	if($("input#personnel_no").val()==""){
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}	
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
	var comm_id = $('#comm_id').val(); 	
	var census_id = $('#census_id').val();	
	var rc_id = $('#rc_id').val();
	var personnel_no = $('#personnel_no').val();
		
	$.post('Re_call_Action?' + key + "=" + value, {rc_id:rc_id,re_emp_select:re_emp_select,granted_fr_dt:granted_fr_dt,unit_sus_no:unit_sus_no,unit_posted_to:unit_posted_to,appt:appt,x_sus_no:x_sus_no,x_list_loc:x_list_loc,date_of_appt:date_of_appt,date_of_tos:date_of_tos,authority:authority,auth_dt:auth_dt,from_dt:from_dt,to_dt:to_dt,cause_of_release:cause_of_release,dt_of_release:dt_of_release,comm_id:comm_id,census_id:census_id,personnel_no:personnel_no}, function(data){
	
        if(data=="update")
      	  alert("Data Updated Successfully");
        else if(parseInt(data)>0)
	       	  {	    	   
	        	alert("Data Saved Successfully");
	        	$("#tab_id").show();
	        	$("#rc_id").val(data)	        	        	
	        	$('#census_id').val(data[0]);	        	 	        	
	          }
	          else
	          {
	        	  alert(data);
	          }
	        	
	 	  	}).fail(function(xhr, textStatus, errorThrown) 
	 	  		{
	 	  					
	  		});

}
//*************************************end CDA ACCOUNT NO & CONTACT DETAIL*****************************//

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

</script>

