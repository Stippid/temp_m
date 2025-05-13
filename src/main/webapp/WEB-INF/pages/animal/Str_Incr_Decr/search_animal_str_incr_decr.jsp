<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

	
<link rel="stylesheet" href="js/datatable/css/datatables.min.css">
<script type="text/javascript" src="js/datatable/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/datatable/js/jquery.mockjax.js"></script>




<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
			<form>
				<div class="card-header">
					<h5>Search/Approve Incr/Decr</h5>
					<h6 class="enter_by">
						<span style="font-size: 12px; color: red;">(To be entered by unit)</span>
					</h6>
				</div>
				<div class="card-body card-block">	   
				<div class="col-md-12">
						 <div class="col-md-6" id=""  >
							<div class="row form-group">
								<div class="col-md-4">
									<strong style="color: red;">* </strong><label class=" form-control-label">Action </label>
								</div>
								<div class="col-md-8">
									<select name="type" id="type" onchange="fnType()" class="form-control"   >
										<c:forEach var="item" items="${getPostINOutstatusList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
										</c:forEach>
									</select>
								</div>						
							</div>
						</div>	
					</div>
			 </div>
	</form>
			
<!-- POST OUT FORM START  -->
	<div id="div_post_out" class="tabcontent" style="display:none ;">
		<form id="post_out_form">
			
				<div class="card">
						<div class="card-header">
							<h5>STR DECR</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
					<div class="card-body card-block"><br>
			 
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">From SUS No</label>
								</div>
								<div class="col-md-8">
								<input type="text" id="from_sus_no_out" name="from_sus_no_out" value="${roleSusNo}"
										class="form-control autocomplete" autocomplete="off" onkeyup="search_sus1from(this,'from_unit_name_out');specialcharecter(this)" 
										maxlength="8" onkeypress="return AvoidSpace(event)" > 	
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">From Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="from_unit_name_out" name="from_unit_name_out" value="${unit_name}"
										class="form-control autocomplete" autocomplete="off" onkeyup="search_unit1_from(this,'from_sus_no_out');specialcharecter(this)"
										maxlength="50" >  
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To Sus No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="to_sus_no" name="to_sus_no" onkeyup="search_sus(this,'to_unit_name_out');specialcharecter(this)"
										class="form-control autocomplete" autocomplete="off"
										maxlength="8" onkeypress="return AvoidSpace(event)" >
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">To Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="to_unit_name_out" name="to_unit_name_out" value=""
										class="form-control autocomplete" autocomplete="off" onkeyup="search_unit_name(this,'to_sus_no'); specialcharecter(this)"
										maxlength="50" >  
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">  Personal No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="personnel_no" name="personnel_no"
										class="form-control autocomplete" autocomplete="off" 
										maxlength="9" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						
					</div>
					<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
						                </div>						               
						                <div class="col-md-8">				           
												
											<select name="status" id="status" class="form-control-sm form-control"  >
													<option value="0">Pending</option>
												    <option value="1">Approved</option>	
													<option value="3">Rejected</option>
											</select>
										</div>
						            </div>
	              				</div>	             					              				
	              			</div>
	    
            			
				</div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Search_Incr_DecrUrl" class="btn btn-success btn-sm">Clear</a>
					 <input type="button" class="btn btn-info btn-sm" 
						onclick="getAnmldecrData();" value="Search">
				</div>
			</div>
		 


</form>			
</div>
<!-- --------------------INNN---------------------------- -->
<div id="div_post_in" class="tabcontent" style="display:none ;">
		<form id="post_out_in">
			
				<div class="card">
						<div class="card-header">
							<h5>STR INCR</h5>
							<h6 class="enter_by">
								<span style="font-size: 12px; color: red;"></span>
							</h6>
						</div>
					<div class="card-body card-block"><br>
			 
				<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">From SUS No</label>
								</div>
								<div class="col-md-8">
								<input type="text" id="from_sus_no_in" name="from_sus_no_in" 
										class="form-control autocomplete" autocomplete="off" onkeyup="search_sus1from(this,'from_unit_name_in');specialcharecter(this)" 
										maxlength="8" onkeypress="return AvoidSpace(event)"> 	
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">From Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="from_unit_name_in" name="from_unit_name_in" 
										class="form-control autocomplete" autocomplete="off" onkeyup="search_unit1_from(this,'from_sus_no_in');specialcharecter(this)"
										maxlength="50" >  
								</div>
							</div>
						</div>
					</div>
					
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"> To Sus No </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="to_sus_no_in" name="to_sus_no_in" value="${roleSusNo}" onkeyup="search_sus(this,'to_unit_name_in');specialcharecter(this)" 
										maxlength="8" onkeypress="return AvoidSpace(event)" class="form-control autocomplete" autocomplete="off">
								</div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label">To Unit Name </label>
								</div>
								<div class="col-md-8">
									<input type="text" id="to_unit_name_in" name="to_unit_name_in" value="${unit_name}"
										class="form-control autocomplete" autocomplete="off" onkeyup="search_unit_name(this,'to_sus_no_in');specialcharecter(this)"
										maxlength="50" >  
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col-md-4">
									<label class=" form-control-label"><strong
										style="color: red;"></strong> Army No</label>
								</div>
								<div class="col-md-8">
									<input type="text" id="inpersonnel_no" name="personnel_no"
										class="form-control autocomplete" autocomplete="off"
										maxlength="9" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)">
								</div>
							</div>
						</div>
						
					</div>
						<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
						                </div>						               
						                <div class="col-md-8">				           
												
											<select name="instatus" id="instatus" class="form-control-sm form-control"  >
													<option value="0">Pending</option>
												    <option value="1">Approved</option>	
													<option value="3">Rejected</option>
											</select>
										</div>
						            </div>
	              				</div>	             					              				
	              			</div>
	    </div>
				<div class="card-footer" align="center" class="form-control">
					<a href="Search_Incr_DecrUrl" class="btn btn-success btn-sm">Clear</a>
					<!-- <input type="button" class="btn btn-info btn-sm" id="btn-reload"
						value="Search"> -->
						<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" id="btn-reload" value="Search" />
				</div>
			</div> 
	</form>			
</div>

		   <div class="card-body">
					<div class="datatablediv" id="reportDiv">
						<div class="">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id="getSearchAnmReport" 
								class="table no-margin table-striped  table-hover  table-bordered report_print">
									<thead style="font-size: 10px; text-align: center;">
										<tr>
											<th>Ser No.</th>
											<th>Army No</th>
											<th>Name</th>
											<th>From Unit SUS No</th>
											<th>To Unit SUS No</th>
											<th id="dt_of_tos_col">Date Of Tos</th>
											<th>Action</th>
							</tr>
									</thead>
									<tbody>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="12" align="center" style="color: red;">Data
													Not Available</td>
											</tr>
										</c:if>
									</tbody>
								</table>

							</div>
						</div>
					</div>
				</div>



</div>		 
</div>
</div> 



<c:url value="Edit_Anm_Incr_Url" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateFormIncr" name="updateFormIncr" modelAttribute="updateid">
	<input type="hidden" name="edit_id" id="edit_id" value="0" />
	<input type="hidden" name="edit_census_id" id="edit_census_id" value="0" />	
	<input type="hidden" name="edit_scenario" id="edit_scenario" value="0" />
</form:form>



<c:url value="Edit_Anm_Decr_Url" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateFormDecr" name="updateFormDecr" modelAttribute="updateid1">
	<input type="hidden" name="edit_id1" id="edit_id1" value="0" />
	<input type="hidden" name="edit_census_id1" id="edit_census_id1" value="0" />	
	<input type="hidden" name="edit_scenario1" id="edit_scenario1" value="0" />
</form:form>

<script>
jQuery(function($){ 
    
    datepicketDate('cr_date');
    datepicketDate('in_cr_date');
    
    
});
$(document).ready(function() {
		

	$("#type").change(function () {
		
        var selectedValue = $(this).val();

        if (selectedValue === "1") {
            $("#div_post_in").show();
            $("#div_post_out").hide();
            $("#dt_of_tos_col").text("Date Of TOS");
            table1.ajax.reload();
        } else if (selectedValue === "2") {
            $("#div_post_out").show();
            $("#div_post_in").hide();
            $("#dt_of_tos_col").text("Date Of SOS");
            table1.ajax.reload();
        }
    });
	
	

		
  	if ('${roleSusNo}' != "") {
		$('#roleSusNo').val('${roleSusNo}');
	}		
  	
	if('${roleAccess}'=='Unit'){		
		$("#from_sus_no_out").prop('readonly',true);
		$("#from_unit_name_out").prop('readonly',true);
		$("#to_sus_no_in").prop('readonly',true);
		$("#to_unit_name_in").prop('readonly',true);
	}
	
	 if ( $('#type').val() == '2' ) {
   	  	$("#div_post_out").show();
   	  	$("#div_post_in").hide();
     }
     else if ( $('#type').val() == '1' ) {
   	  	$("#div_post_in").show();
   	  	$("#div_post_out").hide();
     }
	 
	 
	 try{
	        if(window.location.href.includes("?"))
	         {
	             var url = window.location.href.split("?")[0];
	            window.history.pushState({}, document.title, url);
	        }
	    }
	    catch (e) {
	        // TODO: handle exception
	    }
	});

function getunit(val,id) {	
        $.post("getTargetUnitNameList?"+key+"="+value, {
        	sus_no : val
    }, function(j) {
            //var json = JSON.parse(data);
            //...

    }).done(function(j) {
   		 	var length = j.length-1;
            var enc = j[length].substring(0,16); 
            $("#"+id).val(dec(enc,j[0])); 
    }).fail(function(xhr, textStatus, errorThrown) {
    });
} 	
 function fnType() {
      if ($('#type').val() == '2' ) {
    	  $("#div_post_out").show();
    	  $("#div_post_in").hide();
      }
      else if ( $('#type').val() == '1' ) {
    	  $("#div_post_in").show();
    	  $("#div_post_out").hide();
      }
      $("#personnel_no").val('');
      $("#inpersonnel_no").val('');
  	  $("#inrank").val('');
  	  $("#from_sus_no_in").val('');
  	  $("#from_unit_name_in").val('');
  	  $("#to_sus_no_out").val('');
  	  $("#to_unit_name_out").val('');
  	  $("#rank").val('');
  	  $("#to_sus_no").val(''); 	 
  	  $("#to_unit_name_out").val('');
  	  $("#divPrint").hide(); 
 }
 
 function getAnmldecrData() {
	 table1.ajax.reload();
     
}

function  search_sus(sus_obj,unit_id){		
	var sus_no = sus_obj.value;
	var susNoAuto = $("#"+sus_obj.id);	
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
	        	  $('#'+unit_id).val('');
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
          
           $('#'+unit_id).val(dec(enc,j[0]));   
               
       }).fail(function(xhr, textStatus, errorThrown) {
       });
		} 	     
	});	
	
	
	}
	
	////Personal No

	$("input#personnel_no").keyup(function() {
		var roleSus=$("#from_sus_no_out").val();
		
		var personel_no = this.value;
		var susNoAuto = $("#personnel_no");
		var perurl;
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url : "getPersonnelNoListAnimal_Census?"+ key + "=" + value,
					data : {
						personel_no : personel_no,army_no:personel_no,roleSus:roleSus
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
		
	
function search_unit_name(unitObj,sus_id){
		var unit_name = unitObj.value;
			 var susNoAuto=$("#"+unitObj.id);
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
			        	  $('#'+sus_id).val('');
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
					        	$("#"+sus_id).val(dec(enc,data[0]));
				            }).fail(function(xhr, textStatus, errorThrown) {
				            });
			      } 	     
			}); 			
	}
	

////Personal No
	//incr army no autocomplte
	$("input#inpersonnel_no").keypress(function() {
		var personel_no = this.value;
		var susNoAuto = $("#inpersonnel_no");
		var roleSus=$("#from_sus_no_in").val();
		var perurl;
		perurl='getPersonnelNoListAnimal_Census?';
		susNoAuto.autocomplete({
			source : function(request, response) {
				$.ajax({
					type : 'POST',
					url: "getPersonnelNoListAnimal_Census?"+key+"="+value,
					data : {
						personel_no : personel_no,army_no:personel_no,roleSus:roleSus
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
					document.getElementById("inpersonnel_no").value = "";
					susNoAuto.val("");
					susNoAuto.focus();
					return false;
				}
			},
			select : function(event, ui) {

			}
		});
	});

</script>
<script>
/* ///bisag 20_03_2023 v2(change invalid status) */
function  search_sus1from(sus_obj,unit_id){
// 	removeMinDate();
	var sus_no = sus_obj.value;
	var susNoAuto = $("#"+sus_obj.id);

	
	
	susNoAuto.autocomplete({
	      source: function( request, response ) {
	        $.ajax({
	        type: 'POST',
	        url: "getSUSNoList_Active_or_Inactive?"+key+"="+value,
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
	        	  $('#'+unit_id).val('');
	        	  susNoAuto.val("");	        	  
	        	  susNoAuto.focus();
	        	  return false;	             
	          }   	         
	    }, 
		select: function( event, ui ) {
			var sus_no = ui.item.value;			      	
			 $.post("getUnitNameList_Active_or_Inactive?"+key+"="+value, {
				 sus_no:sus_no
       }, function(j) {
              
       }).done(function(j) {
      	 var length = j.length-1;
           var enc = j[length].substring(0,16);
          
           $('#'+unit_id).val(dec(enc,j[0]));   
               
       }).fail(function(xhr, textStatus, errorThrown) {
       });
		} 	     
	});	
	
	
	}

function search_unit1_from(obj,id){
// 	removeMinDate();
			var unit_name = obj.value;
				 var susNoAuto=$("#"+obj.id);
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "UnitsNameList_active_or_inactive?"+key+"="+value,
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
				        	  $("#"+id).val('');
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
				          }   	         
				      }, 
				      select: function( event, ui ) {
				    	 var unit_name = ui.item.value;
					            $.post("SUSFromUNITNAMEActive_or_InactiveList?"+key+"="+value,{target_unit_name:unit_name}, function(data) {
					            }).done(function(data) {
					            	var length = data.length-1;
						        	var enc = data[length].substring(0,16);
						        	$("#"+id).val(dec(enc,data[0]));
					            }).fail(function(xhr, textStatus, errorThrown) {
					            });
				      } 	     
				}); 			
		
}
	
	
</script>



<script>

function data(tableName) {
jsondata = [];

var table = $('#'+tableName).DataTable();
var info = table.page.info();
var currentPage = info.page;
var pageLength = info.length;
var startPage = info.start;
var endPage = info.end;
var Search = table.search();
var order = table.order();
var orderColunm = order[0][0] + 1;
var orderType = order[0][1];

var type = $("#type").val();
var from_sus_no = "";  
var to_sus_no = "";
var status= "";
var army_no="";
if(type == "1"){
	from_sus_no=$("#from_sus_no_in").val();
	 to_sus_no=$("#to_sus_no_in").val();
	 status = $("#instatus").val();
	 army_no = $("#inpersonnel_no").val();
}else if (type == "2"){
	from_sus_no=$("#from_sus_no_out").val();
	 to_sus_no=$("#to_sus_no").val();
	 status = $("#status").val();
	 army_no = $("#personnel_no").val();
}


if (tableName=="getSearchAnmReport") {
	
	 $.post("GetSearch_Incr_Decr_count?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			,type:type,army_no:army_no,from_sus_no:from_sus_no,to_sus_no:to_sus_no,status:status},function(j) {
		FilteredRecords = j;
		console.log("filtered recods " + FilteredRecords)
	});
	$.post("GetSearch_Incr_Decr_List?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
		,type:type,army_no:army_no,from_sus_no:from_sus_no,to_sus_no:to_sus_no,status:status},function(j) {
			console.log("j.length " + j.length);
			for (var i = 0; i < j.length; i++) {
				var sr = i+1;			
			jsondata.push([sr,j[i].army_no,j[i].name,j[i].from_sus_no,j[i].to_sus_no,j[i].dt_of_tos,
				j[i].action  
			]);
			
			}
	});
}
}

</script>




<script>


mockjax1('getSearchAnmReport');
table1 = dataTable('getSearchAnmReport');

$('#btn-reload').on('click', function(){
    table1.ajax.reload();
});
</script>



<script>

//buttons Method

function approveData(id, census_id,to_sus_no1) {
        $.post('approveAnimalIncrDecr?'+ key + "=" + value, { id: id, census_id: census_id,to_sus_no: to_sus_no1 }, function(response) {
            alert(response);
            table1.ajax.reload();
        }).fail(function() {
            alert("Error while approving the record!");
        });
}

function deleteData(id, census_id) {
    $.post('deleteAnimalIncrDecr?'+ key + "=" + value, { id: id, census_id: census_id }, function(response) {
        alert(response);
        table1.ajax.reload();
    }).fail(function() {
        alert("Error while approving the record!");
    });
}

var rejectId, rejectCensusId;

function openRejectModal(id, census_id) {
    rejectId = id;
    rejectCensusId = census_id;
    document.getElementById("rejectRemarks").value = "";
    document.getElementById("rejectModal").style.display = "block";
    document.getElementById("overlay").style.display = "block";
}

function closeRejectModal() {
    document.getElementById("rejectModal").style.display = "none";
    document.getElementById("overlay").style.display = "none";
}

function submitReject() {
    var remarks = document.getElementById("rejectRemarks").value;
    
    if (remarks.trim() === "") {
        alert("Please enter remarks before rejecting.");
        return;
    }
    $.post('rejectAnimalIncrDecr?'+ key + "=" + value, { id: rejectId, census_id: rejectCensusId,remarks: remarks }, function(response) {	
		alert(response); 
        closeRejectModal(); 
        table1.ajax.reload();
    }).fail(function() {
        alert("Error while rejecting the record!");
    });
}


</script>


<div id="rejectModal">
    <h3>Reject Record</h3>
    <label>Remarks:</label>
    <textarea id="rejectRemarks" rows="3" style="width: 100%;"></textarea>
    <br><br>
    <button onclick="submitReject()">Reject</button>
    <button onclick="closeRejectModal()">Close</button>
</div>

<div id="overlay" style="display: none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.5); z-index: 999;"></div>

<style>
#rejectModal {
    display: none;
    position: fixed;
    top: 10%; /* Move to top */
    left: 50%;
    transform: translate(-50%, 0); /* Center horizontally */
    width: 60%; /* Increase width */
    max-width: 800px; /* Set max width */
    background: white;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0px 0px 10px gray;
    z-index: 1000;
}

</style>

 <script>
 
 function editData(id, census_id, scenario) {
	debugger;
	if(scenario !="" || scenario != null || scenario != "undefined"){	
		if(scenario =="1"){
			console.log("here is edit 1");
			document.getElementById('edit_id').value = id;
			document.getElementById('edit_census_id').value = census_id;
			document.getElementById('edit_scenario').value = scenario;
			/* $("#edit_id").val(id);h
			$("#edit_census_id").val(census_id);
			$("#edit_scenario").val(scenario); */
			document.getElementById('updateFormIncr').submit();
		}else if(scenario =="2"){
			console.log("here is edit 2");
			
			document.getElementById('edit_id1').value = id;
			document.getElementById('edit_census_id1').value = census_id;
			document.getElementById('edit_scenario1').value = scenario;
			document.getElementById('updateFormDecr').submit();
		}
		
	}
 }
 
 </script>
