<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

<div class="animated fadeIn">
	    	<div class="container-fluid" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Field Service</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by unit)</span></h6></div>
	          			<div class="card-body card-block">
	          			<div class="col-md-12" id="unit_sus_nodiv" style="display: none">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_no_hid" style="display: none" ><b> ${sus_no} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off"
						                  maxlength="8" onkeypress="return AvoidSpace(event)" onkeyup="return specialcharecter(this)"> 	     
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off"
						                   maxlength="50" onkeyup="return specialcharecter(this)">	
										   <label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label>  
										</div>
						            </div>
	              				</div>
              				</div>
	          			<div class="col-md-12">	
	          			<input type="hidden" id="p_id" name="p_id" class="form-control autocomplete" value="0" autocomplete="off" >
	          		<%-- 	<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Category</label>
						                </div>
						                <div class="col-md-8">
						                  <select name="service_category" id="service_category" onchange="onCategory()"
											class="form-control-sm form-control" >
											<option value="${getServiceCategoryList.get(0)[0]}" name="${getServiceCategoryList.get(0)[1]}">${getServiceCategoryList.get(0)[1]}</option>
										</select>
						                </div>
						            </div>
	              				</div> --%>
	              				              					
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong> Area </label>
						                </div>
						                <div class="col-md-8">
						                	 <select name="fd_service1" id="fd_service1"  class="form-control-sm form-control" >
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getFdService}" varStatus="num">
										<option value="${item[1]}" name="${item[0]}">${item[0]}</option>
									</c:forEach>
							</select>
						                </div>
						            </div>
	              				</div>
	              				
	              				
	              			</div>
	            			<div class="col-md-12">	              					
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Authority </label>
						                </div>
						                <div class="col-md-8">
						                	 <input type="hidden" id="checkcount" name="checkcount" class="form-control autocomplete" value="0" autocomplete="off" >
						                 <input type="text" id="authority1" name="authority1" class="form-control autocomplete" autocomplete="off" 
						                     maxlength="100" onkeyup="return specialcharecter(this)"> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Authority Date</label>
						                </div>
						                <div class="col-md-8">
<!-- 						                  <input type="Date" id="authority_date1" name="authority_date1" class="form-control autocomplete" autocomplete="off" > -->
						                        <input type="text" name="authority_date1" id="authority_date1" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 94%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              		<div class="col-md-12">	         
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Op Name</label>
						                </div>
						                <div class="col-md-8">
									<select name="operation1" id="operation1" class="form-control-sm form-control">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getOprationList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
									</select>						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Unit Location</label>
						                </div>
						                <div class="col-md-8">
										<input type="text" id="unit_location1" name="unit_location1" class="form-control autocomplete" autocomplete="off" 
										maxlength="50" onkeyup="return specialcharecter(this)"> 
						                </div>
						            </div>
	              				</div>
	              				
	              				</div>
	              			</div>
	              			
	              			</div>
	              			
							 
</div>

<div id="Field_Service" class="tabcontent"  style="display:block;">
  	<div class="card" style="margin-top:20px;">
									
									<div class="card_body">
						<table id="fieldSerivice_table" class="table-bordered watermarked" style="margin-top:10px; width: -webkit-fill-available;">
											
				<thead style=" color: white; text-align: center;">
					<tr>
						<th>Sr No</th>
						<th>Army No</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Duration(In Month)</th>
						<th>Place</th> 
						<th>Action</th>
				   </tr>
				</thead>
			   <tbody data-spy="scroll" id="field_service_tbody" style="min-height:46px; max-height:650px; text-align: center;">
					<tr id="tr_field_service1">
						<td class="qua_srno">1</td>
						<td>		
									 <input type="text" id="personnel_no1" name="personnel_no1"
									 class="form-control autocomplete" onkeypress="personnelNumber(1);" autocomplete="off"
									 maxlength="12" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)">
									</td>	

						<td>
<!-- 						  <input type="Date" id="from_date1" onChange="AutocalculateMonth(1);" name="from_date1" class="form-control autocomplete" autocomplete="off" > -->
						  <input type="text" name="from_date1" id="from_date1" onChange="AutocalculateMonth(1);" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 80%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						</td>
						<td>
<!-- 						   <input type="Date" id="to_date1" name="to_date1" onChange="AutocalculateMonth(1);" class="form-control autocomplete" autocomplete="off" > -->
							<input type="text" name="to_date1" id="to_date1" onChange="AutocalculateMonth(1);" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 80%;display: inline;"
							onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
							onchange="clickrecall(this,'DD/MM/YYYY');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
						</td>
						<td>
								  <input type="text" id="duration1" readonly="readonly" name="duration1" class="form-control autocomplete" autocomplete="off" >
								</td>
								<td>
									<input type="text" id="place1" name="place1" class="form-control autocomplete" autocomplete="off" 
									maxlength="50" onkeyup="return specialcharecter(this)"> 
								</td>

<!-- 						<td style="display:none;"><input type="hidden" id="census_id1" name="census_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td> -->
							<td style="display:none;"><input type="hidden" id="jco_id1" name="jco_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						<td style="display:none;"><input type="hidden" id="field_ch_id1" name="field_ch_id1"  value="0" class="form-control autocomplete" autocomplete="off" ></td>
						<td>
						<a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "field_service_save1" onclick="field_service_save_fn(1);" ><i class="fa fa-save"></i></a>
						   <a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "field_service_add1" onclick="field_service_add_fn(1);" ><i class="fa fa-plus"></i></a>
						    <a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "field_service_remove1" onclick="field_service_remove_fn(1);"><i class="fa fa-trash"></i></a> 
						</td>
						</tr>
			   </tbody> 
						</table>
			
									</div>
									
									</div>
									
									     <input type="hidden" id="personnel_no3v" name="personnel_no3v" class="form-control autocomplete"  > 
						                 <input type="hidden" id="fd_service3v" name="fd_service3v" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_location3v" name="unit_location3v" class="form-control autocomplete"  > 
						                 <input type=hidden id="operation3v" name="operation3v" class="form-control autocomplete"  > 
						                 <input type="hidden" id="status3v" name="status3v" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_name3v" name="unit_name3v" class="form-control autocomplete"  > 
						                 <input type="hidden" id="unit_sus_no3v" name="unit_sus_no3v" class="form-control autocomplete"  > 
						                 
  
  <div class="card-footer" id="bk_id" align="center" class="form-control" style="display:none;">
						<!-- <a href="Search_Field_Service_url" class="btn btn-primary btn-sm">Back</a> -->
						<input type="button"  class="btn btn-info btn-sm"onclick="Cancel();" value="Back"> 
			            </div> 	 
	        	</div>
</div>




<script type="text/javascript">
$(document).ready(function() {
	
	if($("select#fd_service1").val() != 0){
		var f = '${fd_service}';
		$("select#fd_service1").val(f);
	}

	var r =('${roleAccess}');
	if(r == "MISO"){
		
		 $("#unit_sus_nodiv").show();		 
		
	}
	if( '${p_id}' != ""){
		//alert('${p_id}');
		$("#p_id").val('${p_id}');
// 		$("#service_category").val('${category}');
		//$("#fd_service1").val('${fd_service}');
		getFieldService();
		
		}	
	datepicketDate('authority_date1');
	datepicketDate('to_date1');
	datepicketDate('from_date1');
	 $( "#to_date1" ).datepicker( "option", "maxDate", null);
	 $( "#from_date1" ).datepicker( "option", "maxDate", null);
		colAdj("fieldSerivice_table");
});


function onCategory(){

	
}
function AutocalculateMonth(f) {
	if($('#from_date' + f).val().trim() != "" && $('#from_date' + f).val() != "DD/MM/YYYY" && $('#to_date' + f).val().trim() != "" && $('#to_date' + f).val() != "DD/MM/YYYY") {
		if(getformatedDate($('#from_date' + f).val()) > getformatedDate($('#to_date' + f).val())) {
			alert("To Date Should Be Greater Than From Date");
			$("#to_date"+f).val("");
			$("#duration"+f).val("");
			$("input#from_date" + f).focus();
			return false;
		}
		var startDate = getformatedDate($('#from_date' + f).val());
		var endDate = getformatedDate($('#to_date' + f).val());
		 var startYear = startDate.getFullYear();
		    var february = (startYear % 4 === 0 && startYear % 100 !== 0) || startYear % 400 === 0 ? 29 : 28;
		    var daysInMonth = [31, february, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];

		    var yearDiff = endDate.getFullYear() - startYear;
		    var monthDiff = endDate.getMonth() - startDate.getMonth();
		    if (monthDiff < 0) {
		        yearDiff--;
		        monthDiff += 12;
		    }
		    var dayDiff = endDate.getDate() - startDate.getDate();
		    if (dayDiff < 0) {
		        if (monthDiff > 0) {
		            monthDiff--;
		        } else {
		            yearDiff--;
		            monthDiff = 11;
		        }
		        dayDiff += daysInMonth[startDate.getMonth()];
		    }
	    
		var message;
		   if ( (yearDiff == 0) && (monthDiff == 0) && (dayDiff == 0) )  {
			message = "0 years 0 months 1 days";
		} else {
			message = yearDiff + " years "
			message += monthDiff + " months "
			message += dayDiff + " days"
		}
		var mont = yearDiff * 12;
		mont += monthDiff;
		$("#duration"+f).val(mont);
		
	}
}


////////////////////////////////////

function personnelNumber(val){

	var personel_no = $("#personnel_no"+val).val();
	 var susNoAuto=$("#personnel_no"+val);
	var perurl;
		perurl='getpersonnel_noListApproved_JCO?';
	susNoAuto.autocomplete({
		source : function(request, response) {
			$.ajax({
				type : 'POST',
				url : perurl + key + "=" + value,
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
				alert("Please Enter Approved Army No");
				document.getElementById("personnel_no"+val).value = "";
				susNoAuto.val("");
				susNoAuto.focus();
				return false;
			}
		},
		select : function(event, ui) {
			var personnel_no_1 = ui.item.value;
	    	  document.getElementById("personnel_no"+val).value=personnel_no_1;
	    	  /* if(parseInt(personnel_no_1.length) > parseInt("7"))
	    	  { */
	    		  personal_number(val);
	    	 // }
		}
	});


}
/////////////////

qua=1;
qua_srno=1;
function field_service_add_fn(q){
	 qua=q+1;
	
	 if( $('#field_service_add'+q).length )         
	 {
			$("#field_service_add"+q).hide();
	 }
	
	 
	 if(q!=0)
		 qua_srno+=1;
	
	$("table#fieldSerivice_table").append('<tr id="tr_field_service'+qua+'">'
			+'<td class="qua_srno">'+qua+'</td>'
 			+'<td><input type="text" id="personnel_no'+qua+'" name="personnel_no'+qua+'" onkeypress="personnelNumber('+qua+');" class="form-control autocomplete" autocomplete="off" maxlength="12" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)"></td>'

			+'<td>'
			+'<input type="text" name="from_date'+qua+'" id="from_date'+qua+'" onChange="AutocalculateMonth('+qua+');" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 80%;display: inline;"'
			+'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')"' 
			+'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" ></td>'
			+'<td>'
			+'<input type="text" name="to_date'+qua+'" id="to_date'+qua+'" onChange="AutocalculateMonth('+qua+');" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 80%;display: inline;"'
			+'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
			+'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" ></td>'
			+'<td><input type="text" id="duration'+qua+'" readonly="readonly" name="duration'+qua+'" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td><input type="text" id="place'+qua+'" name="place'+qua+'" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)"></td>'
          
// 			+'<td style="display:none;" ><input type="hidden" id="census_id'+qua+'" name="census_id'+qua+'"   class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="display:none;"><input type="hidden" id="jco_id'+qua+'" name="jco_id'+qua+'"  class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="display:none;"><input type="hidden" id="field_ch_id'+qua+'" name="field_ch_id'+qua+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'

			+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "field_service_save'+qua+'" onclick="field_service_save_fn('+qua+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "field_service_add'+qua+'" onclick="field_service_add_fn('+qua+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "field_service_remove'+qua+'" onclick="field_service_remove_fn('+qua+');"><i class="fa fa-trash"></i></a>' 
			+'</td></tr>');
	
	datepicketDate('to_date'+qua);
	datepicketDate('from_date'+qua);
	 $( "#to_date"+qua).datepicker( "option", "maxDate", null);
	 $( "#from_date"+qua).datepicker( "option", "maxDate", null);
}


function field_service_remove_fn(R){
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var field_service_ch_id=$('#field_ch_id'+R).val();
// 	 var cat=$('#service_category').val(); 
	 $('.qua_srno').each(function(i, obj) {
		 if ($("#field_service_remove"+$( this ).text()).css( "display")!="none" ) {
			 index=i+1;
		}
			 });
	 var p_id=$('#p_id').val();
	  $.post('fieldService_delete_action_Jco?' + key + "=" + value, {field_service_ch_id:field_service_ch_id,index:index,p_id:p_id}, function(data){ 
			  
			   if(data=='1'){
				  
					 $("tr#tr_field_service"+R).remove(); 
					 	
					 if(R==qua){
						 R = R-1;
						 var temp=true;
						 for(i=R;i>=1;i--){
						 if( $('#field_service_add'+i).length )         
						 {
							 $("#field_service_add"+i).show();
							 temp=false;
							 qua=i;
							 break;
						 }}
						 if(temp){
							 field_service_add_fn(0);
							}
					 }
					 $('.qua_srno').each(function(i, obj) {
						 
							obj.innerHTML=i+1;
							qua_srno=i+1;
							 });
					 if (index==1) {
						 getFieldService();
					}
					 alert("Data Deleted SuccessFully");
			   }
				 else{
						 
					 alert("Data not Deleted ");
				 }
			   
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
}

function field_service_save_fn(qs){

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

 	if ($("#fd_service1").val() == "0") {
		alert("Please Select Area");
		$("#fd_service1").focus();
		return false;
	}
	 if ($("#authority1").val().trim() == "") {
		alert("Please Enter Authority ");
		$("#authority1").focus();
		return false;
	}
	if ($("#authority_date1").val().trim() == "") {
		alert("Please Select Authority Date");
		$("#authority_date1").focus();
		return false;
	} 
	 if ($("#operation1").val() == "0") {
		alert("Please Select Operation11111111");
		$("#operation1").focus();
		return false;
	} 
	if ($("#personnel_no"+qs).val().trim() == "") {
		alert("Please Enter Army Number"+qs);
		$("#personnel_no"+qs).focus();
		return false;
	}
	if ($("#from_date"+qs).val().trim() == "" ||  $("#from_date"+qs).val() == "DD/MM/YYYY") {
			alert("Please Enter From Date"+qs);
			$("#from_date"+qs).focus();
			return false;
	}
 	if ($("#to_date"+qs).val() == "") {
 			alert("Please Select To Date");
 			$("#to_date"+qs).focus();
 			return false;
 	} 

		var unit_sus_no=$('#unit_sus_no').val();

		var personnel_no=$('#personnel_no'+qs).val();
		var fd_service=$('#fd_service1').val();
		var from_date=$('#from_date'+qs).val();
		var to_date=$('#to_date'+qs).val();
		var duration=$('#duration'+qs).val();
		var place=$('#place'+qs).val();
		var unit_location=$('#unit_location1').val();
		var operation=$('#operation1').val();
		var authority=$('#authority1').val();
		var authority_date=$('#authority_date1').val();
		var ch_id=$('#field_ch_id'+qs).val();
		var p_id=$('#p_id').val();
		
		  var jco_id=$("#jco_id"+qs).val(); 
				   $.post('field_serviceAction_JCO?' + key + "=" + value, {personnel_no:personnel_no,fd_service:fd_service,from_date:from_date,unit_sus_no:unit_sus_no
					   ,to_date:to_date,duration:duration,place:place,unit_location:unit_location,operation:operation,authority:authority,authority_date:authority_date,ch_id:ch_id,jco_id:jco_id,p_id:p_id}, function(data){	   
			         
						   var ids = data.split(",");
						   if(data=="update"){
			        	  alert("Data Updated Successfully");
			        	  getFieldService();
			          }
			          
			          else if(parseInt(ids[0])>0){
			        	
			        	  
			        	 $("#field_ch_id"+qs).val(ids[0]);
			        	 $('#p_id').val(ids[1]);
			        	 $("#field_service_add"+qs).show();
			        	 $("#field_service_remove"+qs).show();
			        	  alert("Data Saved Successfully");
			        	  getFieldService();
			          }
			          else
			        	  alert(data)
			 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
			  		});
}

function formateDate(value){
	var date = new Date(value);
	var formattedDate = date.getFullYear() + '-' + ('0' + (date.getMonth() + 1)).slice(-2) + '-' + ('0' + date.getDate()).slice(-2);
	return formattedDate;
}



function getFieldService(){
	$.ajaxSetup({
		async : false
	});
	 //var q_id=$('#fd_service1').val(); 
	 var q_id=$('#p_id').val(); 
// 	 var cat=$('#service_category').val(); 
	 var p_url="";
		 p_url="getFieldService_Jco?";
		 ch_url="getFieldServicech_Jco?";
	
	
	 
	 $.post(p_url + key + "=" + value, {q_id:q_id }, function(j){
			
			var v=j.length;
			if(v!=0){
				var authority_d=ParseDateColumncommission(j[0].authority_date);
				$('#unit_sus_no').val(j[0].sus_id);
				var sus_no = j[0].sus_id;			      	
				 $.post("getTargetUnitNameList?"+key+"="+value, {
					 sus_no:sus_no
				         }, function(j) {
				                
				         }).done(function(j) {
				        	 var length = j.length-1;
				             var enc = j[length].substring(0,16);
				             $("#unit_name").val(dec(enc,j[0]));   
				                 
				         }).fail(function(xhr, textStatus, errorThrown) {
			         });
				$('select#fd_service1').val( j[0].fd_services );
				
				$('#p_id').val(j[0].id);
				  $('#authority1').val(j[0].authority);
				  $('#authority_date1').val(authority_d);
				
				  $('#unit_location1').val(j[0].unit_location);
				  $('select#operation1').val( j[0].operation );
			}
			else {
				$('#p_id').val("0");
				$('select#fd_service1').val("0");
				  $('#authority1').val("");
				  $('#authority_date1').val("");
				  $('#unit_location1').val("");
				  $('select#operation1').val("0");
			}
	 });
	 p_id = $('#p_id').val();
	 var i=0;
	  $.post(ch_url + key + "=" + value, {p_id:p_id }, function(j){
		 	var v=j.length;
		 //	alert(j);
			if(v!=0){
				$('#field_service_tbody').empty(); 
				
		for(i;i<v;i++){
			qu=i+1;
			var authDt="";
			if(j[i].to_date!=null){
				authDt=ParseDateColumncommission(j[i].to_date);
			}
		var froDt=ParseDateColumncommission(j[i].from_date);
		//alert(j[i].personnel_no);
			
		$("table#fieldSerivice_table").append('<tr id="tr_field_service'+qu+'">'
			+'<td class="qua_srno">'+qu+'</td>'
			+'<td><input type="text" id="personnel_no'+qu+'" name="personnel_no'+qu+'" onkeypress="personnelNumber('+qu+');" value="' + j[i].personnel_no +'" class="form-control autocomplete" autocomplete="off" maxlength="12" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)" readonly></td>'

			+'<td>'
			+'<input type="text" name="from_date'+qu+'" id="from_date'+qu+'" onChange="AutocalculateMonth('+qu+');" value="'+froDt+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 80%;display: inline;"'
			+'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')"' 
			+'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" ></td>'
			+'<td>'
			+'<input type="text" name="to_date'+qu+'" id="to_date'+qu+'" onChange="AutocalculateMonth('+qu+');" value="'+authDt+'" maxlength="10"  onclick="clickclear(this, \'DD/MM/YYYY\')" class="form-control" style="width: 80%;display: inline;"'
			+'onfocus="this.style.color=\'#000000\'" onblur="clickrecall(this,\'DD/MM/YYYY\');" onkeyup="clickclear(this, \'DD/MM/YYYY\')" '
			+'onchange="clickrecall(this,\'DD/MM/YYYY\');" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" ></td>'
			+'<td><input type="text" id="duration'+qu+'" readonly="readonly" name="duration'+qu+'" value="' + j[i].duration +'" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td><input type="text" id="place'+qu+'" name="place'+qu+'" value="' + j[i].place +'" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)"></td>'

// 			+'<td style="display:none;"><input type="hidden" id="census_id'+qu+'" name="census_id'+qu+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="display:none;"><input type="hidden" id="jco_id'+qu+'" name="jco_id'+qu+'"  value="'+j[i].jco_id+'" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="display:none;"><input type="hidden" id="field_ch_id'+qu+'" name="field_ch_id'+qu+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'

			+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "field_service_save'+qu+'" onclick="field_service_save_fn('+qu+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "field_service_add'+qu+'" onclick="field_service_add_fn('+qu+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "field_service_remove'+qu+'" onclick="field_service_remove_fn('+qu+');"><i class="fa fa-trash"></i></a>' 
			+'</td></tr>');
					
 		  $("#field_service_remove"+qu).show();
 		 datepicketDate('to_date'+qu);
 		 datepicketDate('from_date'+qu);
 		 $( "#to_date"+qu).datepicker( "option", "maxDate", null);
 		 $( "#from_date"+qu).datepicker( "option", "maxDate", null);
		}
		qua=v;
		qua_srno=v;
		$("#field_service_add"+qua).show();
		if(parseInt(p_id)>0){
			
			$("#unit_sus_no").prop("readonly",true);
			$("#unit_name").prop("readonly",true);
			$("#fd_service1").prop("disabled",true);
// 			$("#service_category").prop("disabled",true);
			$("#authority1").prop("readonly",true);
			$("#authority_date1").prop("readonly",true);
			$("#unit_location1").prop("readonly",true);
			$("#operation1").prop("disabled",true);
		}
		 
		}else{
			 $("#field_service_tbody").empty(); 
			 field_service_add_fn(0);
// 			 $('#authority1').val("");
// 			 $('#authority_date1').val("");
// 			 $('#unit_location1').val("");
// 			 $('select#operation1').val(0);
		 }
			
	  });
	 
} 
var census_id = null;
function personal_number(val) {
	 
	var perurl;
		perurl='GetArmyNoData?';
	
		var personnel_no = $("input#personnel_no"+val).val();

	
	if (personnel_no != "") {
		$.post(perurl+ key + "=" + value, {
			army_no : personnel_no
		}, function(j) {
			var jco_id = j[0][0];
// 			alert(jco_id);
			 if(j!=""){
				 
			    	$("#jco_id"+val).val(jco_id);
			 }
			
		});
	}
	
}

$("#unit_sus_no").keypress(function(){
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


