<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<div id="div_extention" class="tabcontent" style="display: block;">
		<div class="animated fadeIn">
	    	<div class="container-fluid" align="center">
	    	   <div class="card">
	    		<div class="card-header"><h5>ADD EXTENSION TO RE-EMPLOYMENT</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by Unit)</span></h6></div>
	          		<div class="card-body card-block">
					<br> 
						 <div class="col-md-12">	              					
	              			 <div class="col-md-6">
	              				 <div class="row form-group">
						             <div class="col-md-4">
						                <label class=" form-control-label"><strong style="color: red;">* </strong>Personal No</label>
						             </div>
						          		<div class="col-md-8">
						          		  <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"  
						          		  onchange="return personal_number();" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)" 
						          		  maxlength="9" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)">
						          		  <input type="hidden" id="statusV" name="statusV" class="form-control autocomplete"  > 
						                  <input type="hidden" id="unit_sus_noV" name="unit_sus_noV" class="form-control autocomplete"  > 
						                  <input type="hidden" id="unit_nameV" name="unit_nameV" class="form-control autocomplete"  > 
						                  <input type="hidden" id="personnel_noV" name="personnel_noV" class="form-control autocomplete"  > 
								          <input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off">
										  <input type="hidden" id="census_id" name="census_id" class="form-control autocomplete" autocomplete="off">
										  <input type="hidden" id="r_id" name="r_id" class="form-control autocomplete" autocomplete="off" >			
										  <input type="hidden" id="ex_id" name="ex_id" value="0" class="form-control autocomplete" autocomplete="off" >								
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
						               <input type="text" id="auth_no" name="auth_no" class="form-control autocomplete" maxlength="50" autocomplete="off" onkeypress="return /[0-9a-zA-Z]/i.test(event.key)"> 
						                   
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
					  		<label class=" form-control-label"  style="margin-left:10px;"><h4>RE EMPLOYMENT</h4></label>
					 	</div>
				 		<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4" id="Re_emp">
						                    <label class=" form-control-label"> Granted From Date </label>
						                </div>
						              
						                <div class="col-md-8">
						                	<label id="granted_fr_dt" name="granted_fr_dt"> </label>						                 	 
						                </div>
						            </div>
	              				</div>	
	              				<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong style="color: red;">* </strong> Date of TOS </label>
								</div>
								<div class="col-md-8">
								 	<input type="hidden" id="dt_of_tos" name="dt_of_tos" class="form-control autocomplete" autocomplete="off" >		
							 		<label id="date_of_tos" name="date_of_tos"> </label>							
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
					                     <b> <label  id="unit_sus_no"  style="display: none"> <b></b></label> </b>					              
					                </div>
					            </div>
              				</div>           				
              				<div class="col-md-6">
              					<div class="row form-group">
					               <div class="col-md-4">
					                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
					                </div>
					                <div class="col-md-8">
					                 <b> <label  id="unit_name"  style="display: none">	<b></b></label> </b>			
									  <b> <label  id="unit_name_l" style="display: none" ><b></b></label> </b>
									</div>
					            </div>
              				</div>
              			</div>
		              	<div class="col-md-12">
					  		<label class=" form-control-label"  style="margin-left:10px;"><h4>EXTENSION</h4></label>
					 	</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> From Date </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" name="from_dt" id="from_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY'); SetMin();" aria-required="true" autocomplete="off"  style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>	            				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> To Date </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" name="to_dt" id="to_dt" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY');SetMin();" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  value="DD/MM/YYYY" >
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	
	              				<br>
	              				</div>
	              			</div>		              								
							<div class="card-footer" align="center" class="form-control">
								<a href="extensionurl" class="btn btn-success btn-sm" >Clear</a> 
								<input type="button" class="btn btn-primary btn-sm" value="Save" onclick="Extention_save_fn();"> 
							   <input type="button"  id ="Cancle" class="btn btn-info btn-sm" onclick="Cancel();" style="display:none; " value="Back" >

			            	</div> 			
						</div>
					</div>
				</div>
	</div>
 	<c:url value="search_extention" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="personnel_no1">
	<input type="hidden" name="personnel_no1" id="personnel_no1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
	<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
</form:form>
<script>

function Cancel(){
  	
	$("#status1").val($("#statusV").val()) ;
	$("#unit_sus_no1").val($("#unit_sus_noV").val()) ;
	$("#unit_name1").val($("#unit_nameV").val()) ;
	$("#personnel_no1").val($("#personnel_noV").val()) ;	
	document.getElementById('searchForm').submit();
}

$(document).ready(function() {
	  var sus_no = '${roleSusNo}';
	  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_no:sus_no}).done(function(data) {
	  		var l=data.length-1;
	  		var enc = data[l].substring(0,16);    	   	 
	  	 	$("#unit_name_l").text(dec(enc,data[0]));
	  			
	  		}).fail(function(xhr, textStatus, errorThrown) {
	  });
	  	
	  	var r =('${roleAccess}');
		if( r == "Unit"){
			
			 $("#unit_sus_no_hid").show();
			 $("#unit_name_l").show();
			 
		}
		else  if(r == "MISO"){
			
			 $("#unit_sus_no").show();		 
			 $("#unit_name").show();
			
		}
	if ('${roleSusNo}' != "") {
		$('#roleSusNo').val('${roleSusNo}');
	}
	if ('${name}' != "") {
		$('#name').val('${name}');
	}
	if ('${rank}' != "") {
		$('#rank').val('${rank}');
	}	
	
	$('#personnel_no').val('${personnel_no}');
	
	
	$("#statusV").val('${status6}');
	
	$("#unit_sus_noV").val('${unit_sus_no6}');
	$("#unit_nameV").val('${unit_name6}');
	
	$("#personnel_noV").val('${personnel_no6}');

		if('${status6}' =="0"  || '${status6}' =="3"){                
		  $("#Cancle").show(); 
		        }

	//$("#personnel_noV").val('${personnel_no_e}');
	personal_number();
	getExtentionData();
	try{
		if(window.location.href.includes("msg=")){
			var url = window.location.href.split("?")[0];
		    window.location = url;
	    } 
	}catch (e) {
		// TODO: handle exception
	}
	
});


jQuery(function($){ //on document.ready  
	 datepicketDate('from_dt');
	 datepicketDate('to_dt');
	 datepicketDate('auth_dt');
	 $( "#to_dt" ).datepicker( "option", "maxDate", null);
	});

//******************************Start save***************************// 
 function Setmin(){
	if ($("input#dt_of_tos").val() != "") {
		var dt_of_tos = $("input#dt_of_tos").val();
		setMinDate('from_dt', dt_of_tos);
		setMinDate('to_dt', dt_of_tos);
	}
}
function Extention_save_fn()
{
	 if($("input#personnel_no").val()==""){
		alert("Please select Personal No");
		$("input#personnel_no").focus();
		return false;
	}
	/* if($("input#auth_no").val()==""){
		alert("Please Enter Authority No");
		$("input#auth_no").focus();
		return false;
	}
	if ($("input#auth_dt").val() == "" || $("#auth_dt").val() == "DD/MM/YYYY") {
		alert("Please Select  Date of Authority ");
		$("input#auth_dt").focus();
		return false;
	} */
	if ($("input#from_dt").val() == "" || $("#from_dt").val() == "DD/MM/YYYY") {
		alert("Please Select  From Date ");
		$("input#from_dt").focus();
		return false;
	} 
	 var personnel_no = $("#personnel_no").val();
	 var from_dt = $("#from_dt").val();
	 var to_dt = $("#to_dt").val();
	 var date_of_tos1 = ParseDateColumncommission($("#date_of_tos").text());
	
	  if(getformatedDate(date_of_tos1) > getformatedDate(from_dt)) {
			alert(" From Date can not be less than Date of TOS");
			$("#from_dt").focus();
			return false;
		}
	   if ($("input#to_dt").val() == "" || $("#to_dt").val() == "DD/MM/YYYY") {
			alert("Please Select  To Date ");
			$("input#To_dt").focus();
			return false;
		}
		 if(getformatedDate(date_of_tos1) > getformatedDate(to_dt)) {
				alert("To Date can not be less than Date of TOS");
				$("#to_dt").focus();
				return false;
			}
		
		 if(getformatedDate(from_dt) > getformatedDate(to_dt)) {
				alert("To Date can not be less than From Date");
				$("#to_dt").focus();
				return false;
			}
		 
	
		var auth_no = $('#auth_no').val();
		var auth_dt = $('#auth_dt').val();
		var comm_id = $('#comm_id').val();
		var census_id = $('#census_id').val();
		var r_id = $('#r_id').val();
		var ex_id = $('#ex_id').val();

		$.post('extention_Action?' + key + "=" + value, {
			r_id : r_id,
			ex_id : ex_id,
			from_dt : from_dt,
			to_dt : to_dt,
			auth_dt : auth_dt,
			comm_id : comm_id,
			census_id : census_id,
			auth_no : auth_no,personnel_no : personnel_no
		}, function(data) {
			if (data == "update")
				alert("Data Updated Successfully");
			else if (parseInt(data) > 0) {
				alert("Data Saved Successfully");

				$("#ex_id").val(data)
				$('#census_id').val(data[0]);
			} else {
				alert(data);
			}

		}).fail(function(xhr, textStatus, errorThrown) {

		});
	}
	//******************************************************************//
	function personal_number() {
		
		var personnel_no = $("input#personnel_no").val();
		$.post('GetRecallnoNoData?' + key + "=" + value, {
			personnel_no : personnel_no
		}, function(j) { 
			
			if (j.length != 0) {
				var comm_id = j[0].id;

				$("#comm_id").val(j[0].id);
				$("#rank").text(j[0].description);
				/* $("#appt").text(j[0].appt);	   */
				$("#name").text(j[0].name);
				/* 	$("#date_of_appt").text(convertDateFormate(j[0].date_of_appointment));	 */
				$.post('GetRecallData?' + key + "=" + value, {
					comm_id : comm_id
				}, function(k) {
					if (k.length > 0) {
						var census_id = k[0].id;
						$('#census_id').val(k[0].id);
						getExtentionData();
					}
				});
			}
			
			
		});
	
	}

	function getExtentionData() {

		var comm_id = $("#comm_id").val();
		var census_id = $("#census_id").val();

		if (comm_id != "") {
			$.post('getExtentionData?' + key + "=" + value, {
				comm_id : comm_id,
				census_id : census_id
			}, function(h) {
				
				if (h.length != 0) {
					$('#unit_sus_no').text(h[0].unit_sus_no);
					 $('#unit_name').text(h[0].unit_name); 

					$('#r_id').val(h[0].remp_id);
					$('#ex_id').val(h[0].ex_id);
					$("#granted_fr_dt").text(
							convertDateFormate(h[0].granted_fr_dt));
					$('#date_of_tos')
							.text(convertDateFormate(h[0].date_of_tos));
					$('#dt_of_tos')
					.val(ParseDateColumncommission(h[0].date_of_tos));
					
					$('#auth_no').val(h[0].auth_no);
					$("#auth_dt").val(ParseDateColumncommission(h[0].auth_dt));
					$("#from_dt").val(ParseDateColumncommission(h[0].from_dt));
					$("#to_dt").val(ParseDateColumncommission(h[0].to_dt));

					$('#re_emp_select').val(h[0].re_emp_select);
					/*  appNamechng(); */
					Setmin();
	    		 
					
				}
				
			});
		}
	}

	function editData(id, census_id, comm_id, personnel_no) {
		$("#ex_id").val(id);
		$("#census_id").val(census_id);
		$("#r_id").val(r_id);
		$("#comm_id").val(comm_id);
		getExtentionData();
		$("#personnel_no").val(personnel_no);
		personal_number();

	}
	//******************************************************************//
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

	//*************************************MAIN Personel Number Onchange*****************************//

	$("input#personnel_no").keyup(function() {
		var personel_no = this.value;
		var susNoAuto = $("#personnel_no");
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getpersonnel_noListEXT?" + key + "=" + value,
					data : {
						personel_no : personel_no
					},
					success : function(data) {
						var susval = [];
						var length = data.length - 1;
						var enc = data[length].substring(0, 16);
						for (var i = 0; i < data.length; i++) {
							susval.push(dec(enc, data[i]));
						}
						response(susval);
					}
				});
			},
			minLength : 1,
			autoFill : true,
			change : function(event, ui) {
				if (ui.item) {
					return true;
				} else {
					alert("Please Enter Approved Personal No");
					document.getElementById("personnel_no").value = "";
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {

			}
		});
	});
	
	
	


	//*************************************END Personel Number Onchange*****************************//
</script>



