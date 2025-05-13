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
  <!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<style>
tr.status1 {
    background-color: lightgreen;
}
tr.status0 {
    background-color: whitesmoke;
}
</style>

<div class="animated fadeIn">
	    	<div class="container-fluid" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5>Approve Field Service</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by UNIT)</span></h6></div>
	          			<div class="card-body card-block">
	          			
	          			<div class="col-md-12" id="unit_sus_nodiv" style="display: none">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_nolb" style="background-color: honeydew; padding:5px;" >${listp[0].sus_id}</label>   
						               
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                <label id="unit_name_lb" style="background-color: honeydew; padding:5px;" ></label>
										</div>
						            </div>
	              				</div>
              				</div>
	          			<div class="col-md-12">	
<!-- 	          			<div class="col-md-6"> -->
<!-- 	              					<div class="row form-group"> -->
<!-- 						               <div class="col-md-4"> -->
<!-- 						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Category</label> -->
<!-- 						                </div> -->
<!-- 						                <div class="col-md-8"> -->
<!-- 						                  <select name="service_category" id="service_category" onchange="onCategory()" -->
<!-- 											class="form-control-sm form-control" > -->
<%-- 											<option value="${getServiceCategoryList.get(0)[0]}" name="${getServiceCategoryList.get(0)[1]}">${getServiceCategoryList.get(0)[1]}</option> --%>
<!-- 										</select> -->
<!-- 						                </div> -->
<!-- 						            </div> -->
<!-- 	              				</div>              					 -->
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Area </label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" id="fd_serviceslb"  class="form-control autocomplete" autocomplete="off"  value="${listp[0].fd_services}"/>
						                	 <select name="fd_service1" id="fd_service1"  class="form-control-sm form-control" style="display: none">
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
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Authority </label>
						                </div>
						                <div class="col-md-8">
						                	 <input type="hidden" id="p_id" name="p_id" class="form-control autocomplete" value="${listp[0].id}" autocomplete="off" >
						                   <input type="text" id="authority1" name="authority1" value="${listp[0].authority}" class="form-control autocomplete" autocomplete="off" maxlength="100"> 
						                   
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Authority Date</label>
						                </div>
						                <div class="col-md-8">
						                <input type="hidden" id="authority_datelb"  value="${listp[0].authority_date}"/>
						                  <input type="Date" id="authority_date1" name="authority_date1"  class="form-control autocomplete" autocomplete="off" >
						                </div>
						            </div>
	              				</div>
	              			</div>
	              		<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Unit Location</label>
						                </div>
						                <div class="col-md-8">
							<input type="text" id="unit_location1" name="unit_location1" value="${listp[0].unit_location}" class="form-control autocomplete" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Op Name</label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" id="operationlb"  value="${listp[0].operation}" class="form-control autocomplete" autocomplete="off" />
									<select name="operation1" id="operation1"  class="form-control-sm form-control" style="display: none">
									<option value="0">--Select--</option>
									<c:forEach var="item" items="${getOprationList}" varStatus="num">
										<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
									</c:forEach>
									</select>
									</div>
						            </div>
	              				</div>
	              			</div>
	              			
	              				
	              			</div>
	              			</div>
	              			

<div id="Field_Service" class="tabcontent"  style="display:block;">
  	<div class="card" style="margin-top:20px;">
									<label class=" form-control-label" style="color: red;"><strong
												style="color: red;">Note:</strong>Previously Approved Data Is Highlighted With Green Color</label>
									<div class="card_body">
						<table id="fieldSerivice_table" class="table-bordered watermarked" style="margin-top:10px;">
											
				<thead style=" color: white; text-align: center;">
					<tr>
						<th>Sr No</th>
						<th>Army No</th>
						<th>From Date</th>
						<th>To Date</th>
						<th>Duration(In Month)</th>
						<th>Place</th> 
				   </tr>
				</thead>
			   <tbody data-spy="scroll" id="field_service_tbody" style="min-height:46px; max-height:650px; text-align: center;">
					
					<c:forEach var="item" items="${listch}" varStatus="num">
					<tr class="status${item.status}"> 
					<td class="qua_srno">${num.index+1}</td>
					<td>
					<input type="text"  value="${item.personnel_no}" class="form-control autocomplete"  autocomplete="off" readonly ="readonly">
					</td>
					<td>
					<input type="hidden" class="datefrom"  value="${item.from_date}"/>
						  <input type="Date" id="fromdate${num.index+1}"  class="form-control autocomplete datefromto" autocomplete="off" >
						</td>
						<td> 
						<input type="hidden" class="dateto"  value="${item.to_date}"/>
						   <input type="Date" id="todate${num.index+1}" class="form-control autocomplete datefromto" autocomplete="off" >
						</td>
						<td>
						  <input type="text"  value="${item.duration}" readonly="readonly" class="form-control autocomplete" autocomplete="off" >
						</td>
						<td>
							<input type="text"  value="${item.place}" class="form-control autocomplete" autocomplete="off" > 
						</td>
						</tr>
					</c:forEach>
						
			   </tbody> 
			</table>
										</div>
										</div>
</div>

	        	</div>
	        	 	
	        	
			</div>
<div id="submit_data" class="card-footer" align="center" class="form-control" >
	<button class="btn btn-primary btn-sm"  	onclick="if (confirm('Are You Sure you want to Approve this Data')){return Approved();}else{return false;}">Approve</button>
	
	<input type="button" class="btn btn-primary btn-sm" value="Reject"
		onclick="if (confirm('Are You Sure You Want to Reject Data?')){addRemarkModel('Reject',0)}else{return false;}">


	
	<a href="Search_field_service_Jco_url" id="btn"   class="btn btn-info btn-sm">Back</a> 
</div>


<form:form action="Approve_service_Jco" method="post" id="apvForm" name="apvForm" >
<input type="hidden" name="${_csrf.parameterName}" id="${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="id3" id="id3" value="0"/> 
<!-- 	<input type="hidden" name="fd_servicesapp" id="fd_servicesapp" value="0"/> -->
</form:form>
<form:form action="Reject_FD_service_Jco" method="post" id="RejectForm" name="RejectForm" >
<input type="hidden" name="${_csrf.parameterName}" id="rej${_csrf.parameterName}" value="${_csrf.token}"/>
	<input type="hidden" name="id4" id="id4" value="0"/> 
	<input type="hidden" name="rej_remarks_fd" id="rej_remarks_fd" value="0" />

</form:form>

<script type="text/javascript">
$(document).ready(function() {
	 
	$.ajaxSetup({
		async : false
	});
	
	var r =('${roleAccess}');
	if(r == "MISO"){
		
		 $("#unit_sus_nodiv").show();	
		 var sus_no = '${listp[0].sus_id}';			      	
		 $.post("getTargetUnitNameList?"+key+"="+value, {
			 sus_no:sus_no
		         }, function(j) {
		                
		         }).done(function(j) {
		        	 var length = j.length-1;
		             var enc = j[length].substring(0,16);
		             $("#unit_name_lb").text(dec(enc,j[0]));   
		                 
		         }).fail(function(xhr, textStatus, errorThrown) {
	         });
		
	}
// 	$("#service_category").val('${category}');
	//getFieldService();
	$("#authority_date1").val(formateDate($("#authority_datelb").val()));
	$("#fd_service1").val($("#fd_serviceslb").val());
	$("#operation1").val($("#operationlb").val());
	$("#fd_serviceslb").val($("#fd_service1 option:selected").text());
	$("#operationlb").val($("#operation1 option:selected").text());
	$('.datefrom').each(function(i, obj) {
		$("#fromdate"+(i+1)).val(formateDate(obj.value));
		 });
	$('.dateto').each(function(i, obj) {
		$("#todate"+(i+1)).val(formateDate(obj.value));
		 });
	 $("input").prop("disabled",true);
	   $("select").prop("disabled",true);
	   $("input[type=button]").prop("disabled",false);
	   $(".hide-action").hide();

	   colAdj("fieldSerivice_table");
	   
});
function AreaChange(){
	getFieldService();
}


function AutocalculateMonth(val){

	var from_date=document.getElementById("from_date"+val).value;  
	var to_date=document.getElementById("to_date"+val).value; 
	var joining2=new Date(from_date);
	var dishcharge=new Date(to_date);
	if(to_date!="" && to_date!=""){
	var automonth=diff_months(joining2, dishcharge);
	 $("#duration"+val).val(automonth);
	 
	}
	
}

function diff_months(dt2, dt1) 
{

 var diff =(dt2.getTime() - dt1.getTime()) / 1000;
  diff /= (60 * 60 * 24 * 7 * 4);
 return Math.abs(Math.round(diff));
 
}


function personnelNumber(val){


	
	var personel_no = $("#personnel_no"+val).val();
		 var susNoAuto=$("#personnel_no"+val);
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
		        	  return true;    	            
		          } else {
		        	  alert("Please Enter Approved Army No");
		        	  document.getElementById("personnel_no"+val).value="";
		        	  susNoAuto.val("");	        	  
		        	  susNoAuto.focus();
		        	  return false;	             
		          }   	         
		      }, 
		      select: function( event, ui ) {
		    	
		    	  var personnel_no_1 = ui.item.value;
		    	  document.getElementById("personnel_no"+val).value=personnel_no_1;
		    	  if(parseInt(personnel_no_1.length) > parseInt("7"))
		    	  {
		    		  personal_number(val);
		    	  }
		    	  
		    	
		      } 	     
		}); 			

		  
		 
}





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
 			+'<td><input type="text" id="personnel_no'+qua+'" name="personnel_no'+qua+'" onkeypress="personnelNumber('+qua+');" class="form-control autocomplete" autocomplete="off" ></td>'

			+'<td><input type="Date" id="from_date'+qua+'" onChange="AutocalculateMonth('+qua+');" name="from_date'+qua+'" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td><input type="Date" id="to_date'+qua+'" onChange="AutocalculateMonth('+qua+');" name="to_date'+qua+'" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td><input type="text" id="duration'+qua+'" readonly="readonly" name="duration'+qua+'" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td><input type="text" id="place'+qua+'" name="place'+qua+'" class="form-control autocomplete" autocomplete="off" ></td>'
          
// 			+'<td style="display:none;" ><input type="hidden" id="census_id'+qua+'" name="census_id'+qua+'"   class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="display:none;"><input type="hidden" id="jco_id'+qua+'" name="jco_id'+qua+'"  class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="display:none;"><input type="hidden" id="field_ch_id'+qua+'" name="field_ch_id'+qua+'"  value="0" class="form-control autocomplete" autocomplete="off" ></td>'

			+'<td><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "field_service_save'+qua+'" onclick="field_service_save_fn('+qua+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class="btn btn-success btn-sm" value = "ADD" title = "ADD" id = "field_service_add'+qua+'" onclick="field_service_add_fn('+qua+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "field_service_remove'+qua+'" onclick="field_service_remove_fn('+qua+');"><i class="fa fa-trash"></i></a>' 
			+'</td></tr>');
}


function field_service_remove_fn(R){
	var rc = confirm("Are You Sure! You Want To Delete");
	 if(rc){
	 var field_service_ch_id=$('#field_ch_id'+R).val();
// 	 var census_ch_id=$('#census_id'+R).val();
	 var jco_ch_id=$('#jco_ch_id'+R).val();
	 
	  $.post('fieldService_delete_action_JCO?' + key + "=" + value, {field_service_ch_id:field_service_ch_id,jco_ch_id:jco_ch_id  }, function(data){ 
			  
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
							 alert("Data Deleted SuceessFully");
			   }
				 else{
						 
					 alert("Data not Deleted ");
				 }
			   
	 	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
	  		});
	 }
}

function field_service_save_fn(qs){

	if ($("#authority_date1").val() == "") {
		alert("Please Select Authority Date");
		$("#authority_date1").focus();
		return false;
	}
	if ($("#personnel_no"+qs).val() == "") {
		alert("Please Enter Army Number");
		$("#personnel_no"+qs).focus();
		return false;
	}
	if ($("#from_date"+qs).val() == "") {
			alert("Please Select From Date");
			$("#from_date"+qs).focus();
			return false;
	}
	if ($("#to_date"+qs).val() == "") {
			alert("Please Select To Date");
			$("#to_date"+qs).focus();
			return false;
	}


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
		//
// 		var census_id=$("#census_id"+qs).val();
		  var jco_id=$("#jco_id"+qs).val();    
		
				   $.post('field_serviceAction_JCO?' + key + "=" + value, {personnel_no:personnel_no,fd_service:fd_service,from_date:from_date
					   ,to_date:to_date,duration:duration,place:place,unit_location:unit_location,operation:operation,authority:authority,authority_date:authority_date,ch_id:ch_id,jco_id:jco_id}, function(data){	   
			         
						   if(data=="update"){
			        	  alert("Data Updated Successfully");
			        	  getFieldService();
			          }
			          else if(data=="Data Already Exist")
			        	  alert(data);
			          else if(parseInt(data)>0){
			        	
			        	  
			        	 $("#field_ch_id"+qs).val(data);
			        	 $("#field_service_add"+qs).show();
			        	 $("#field_service_remove"+qs).show();
			        	  alert("Data Saved SuccesFully");
			        	  
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
	 $(".hide-action").hide();
	 var q_id=$('#fd_service1').val(); 
	 var i=0;
	  $.post('getFieldService_Jco?' + key + "=" + value, {q_id:q_id }, function(j){
		  
			var v=j.length;
			if(v!=0){
				$('#field_service_tbody').empty(); 
				
		for(i;i<v;i++){
			qu=i+1;
			

		var authDt=formateDate(j[i].to_date);
		var froDt=formateDate(j[i].from_date);
		var authority_d=formateDate(j[i].authority_date);
			
		$("table#fieldSerivice_table").append('<tr id="tr_field_service'+qu+'">'
			+'<td class="qua_srno">1</td>'
			+'<td><input type="text" id="personnel_no'+qu+'"  readonly="readonly" name="personnel_no'+qu+'" onkeypress="personnelNumber('+qu+');" value="' + j[i].personnel_no +'" class="form-control autocomplete" autocomplete="off" ></td>'
// 			+'<td style="width: 10%;"><select name="fd_service'+qu+'" id="fd_service'+qu+'" class="form-control-sm form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getFdService}" varStatus="num"><option value="${item[1]}" name="${item[0]}">${item[0]}</option></c:forEach></select></td>'
			+'<td><input type="Date" id="from_date'+qu+'"  readonly="readonly" onChange="AutocalculateMonth('+qua+');" value="'+froDt+'" name="from_date'+qu+'" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td><input type="Date" id="to_date'+qu+'"  readonly="readonly" onChange="AutocalculateMonth('+qua+');" value="'+authDt+'" name="to_date'+qu+'" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td><input type="text" id="duration'+qu+'" readonly="readonly" name="duration'+qu+'" value="' + j[i].duration +'" class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td><input type="text" id="place'+qu+'"  readonly="readonly" name="place'+qu+'" value="' + j[i].place +'" class="form-control autocomplete" autocomplete="off" ></td>'
// 			+'<td style="width: 10%;"><input type="text" id="unit_location'+qu+'" value="' + j[i].unit_location +'" name="unit_location'+qu+'" class="form-control autocomplete" autocomplete="off" ></td>'
// 			+'<td style="width: 10%;"><select name="operation'+qu+'" id="operation'+qu+'" class="form-control-sm form-control"><option value="0">--Select--</option><c:forEach var="item" items="${getOprationList}" varStatus="num"><option value="${item[0]}" name="${item[1]}">${item[1]}</option></c:forEach></select></td>'
// 			+'<td style="display:none;"><input type="hidden" id="census_id" name="census_id"   class="form-control autocomplete" autocomplete="off" ></td>'
			+'<td style="display:none;"><input type="hidden" id="field_ch_id'+qu+'" name="field_ch_id'+qu+'"  value="'+j[i].id+'" class="form-control autocomplete" autocomplete="off" ></td>'

			+'<td class="hide-action"><a class="btn btn-primary btn-sm" value = "SAVE" title = "SAVE" id = "field_service_save'+qu+'" onclick="field_service_save_fn('+qu+');" ><i class="fa fa-save"></i></a>'
			+'<a style="display:none;" class=" hide-action btn btn-success btn-sm" value = "ADD" title = "ADD" id = "field_service_add'+qu+'" onclick="field_service_add_fn('+qu+');" ><i class="fa fa-plus"></i></a>'
			+'<a style="display:none;" class="hide-action btn btn-danger btn-sm" value="REMOVE" title = "REMOVE" id = "field_service_remove'+qu+'" onclick="field_service_remove_fn('+qu+');"><i class="fa fa-trash"></i></a>' 
			+'</td></tr>');
					
		  $('select#fd_service1').val( j[i].fd_services );
		  $('#authority1').val( j[i].authority);
		  $('#authority_date1').val(authority_d);
		  $('#unit_location1').val(j[i].unit_location);
		  $('select#operation1').val( j[i].operation );
// 		  $('#institute'+qu).val( j[i].institute );
 		  $("#field_service_remove"+qu).show();
		}
		qua=v;
		qua_srno=v;
		$("#field_service_add"+qua).show();
		 
		}else{
			 $("#field_service_tbody").empty(); 
			 field_service_add_fn(0);
			 $('#authority1').val("");
			 $('#authority_date1').val("");
			 $('#unit_location1').val("");
			 $('select#operation1').val(0);
		 }
			
	  });
	 
} 
// var census_id = null;
function personal_number(val) {
	 
	
// 	census_id = $('#census_id'+val).val();

	var personnel_no = $("input#personnel_no"+val).val();
	if (personnel_no != "") {
		$.post('GetPersonnelNoData?' + key + "=" + value, {
			personnel_no : personnel_no
		}, function(j) {

			var comm_id = j[0][0];
			
			var name = j[0][5];
			var rank = j[0][6];
			var rankcode = j[0][10];

		
			$("#comm_id"+val).val(comm_id);

			$.post('GetCensusDataApprove?' + key + "=" + value,{ comm_id : comm_id },function(k) {
	    		 if(k.length > 0)
	    		 {
	    			  
	    			  $("#census_id"+val).val(k[0][1]);
	    			
	    		 }
	   });
		});
	}
	
}
function Approved(){
	var fd_services = 1;
	var id= $("#p_id").val();
	$("#id3").val(id);
// 	$("#fd_servicesapp").val($("#service_category").val());
	$("#id3").prop("disabled",false);
// 	$("#fd_servicesapp").prop("disabled",false);
	$("#"+'${_csrf.parameterName}').prop("disabled",false);
 	$('#apvForm').submit();
}

function Reject(){
	var fd_services = 1;
	var id= $("#p_id").val();
	$("#id4").val(id);
	$("#rej_remarks_fd").val($("#reject_remarks").val());
	$("#id4").prop("disabled",false);
	$("#rej"+'${_csrf.parameterName}').prop("disabled",false);
	$("#rej_remarks_fd").prop("disabled",false);
 	$('#RejectForm').submit();
}
</script>


