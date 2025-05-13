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


<div class="animated fadeIn">
	<div class="container" align="center">
		<div class="card">
			<div class="card-header">
				<h5>Non Regular Employee Report</h5>
				<h6 class="enter_by">
					<span style="font-size: 12px; color: red;">(To be entered by
						MISO)</span>
				</h6>
			</div>
			<div class="card-body card-block">
				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> Unit Sus No </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="unit_sus_no" name="unit_sus_no"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50">

							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"> Unit Posted To </label>
							</div>
							<div class="col-md-8">
								<input type="text" id="unit_posted_to" name="sus_no"
									class="form-control autocomplete" autocomplete="off">
							</div>
						</div>
					</div>
				</div>

				<div class="col-md-12">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-4">
								<label class=" form-control-label"><strong
									style="color: red;"> </strong> First Name</label>
							</div>
							<div class="col-md-8">
								<input type="text" id="first_name" name="first_name"
									class="form-control autocomplete" autocomplete="off"
									maxlength="50"> <input type="hidden" id="id" name="id"
									class="form-control autocomplete" value="0" autocomplete="off"
									maxlength="50">

							</div>
						</div>
					</div>
			</div>

			
		</div>
		<div class="card-footer" align="center" class="form-control">
				<a href="Search_civilian_non_regular" class="btn btn-success btn-sm">Clear</a>
				<i class="action_icons searchButton"></i><input type="button"
					class="btn btn-info btn-sm" onclick="Search();" value="Search">
			</div>
	</div>
</div>

<div class="container" id="village_search">
	<div class="">
		<div id="divShow" style="display: block;"></div>
		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>

			<table
				class="table no-margin table-striped  table-hover  table-bordered report_print"
				width="100%">
				<thead style="color: white; text-align: center; font-size: 15px;">
					<tr>
						<th rowspan="2" style="width: 300px; vertical-align: middle;">Category</th>
						<th colspan="2">Industrial</th>
						<th colspan="2">Non Industrial</th>
					</tr>
					<tr>
						<th>Male</th>
						<th>Female</th>
						<th>Male</th>
						<th>Female</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list.size()==0}">
						<tr>
							<td style="font-size: 15px; text-align: center; color: red;"
								colspan="5">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item" items="${list}" varStatus="num">
						<tr>
							<td style="font-size: 15px; text-align: left;">${item[0]}</td>
							<td style="font-size: 15px; text-align: center;">${item[1]}</td>
							<td style="font-size: 15px; text-align: center;">${item[2]}</td>
							<td style="font-size: 15px; text-align: center;">${item[3]}</td>
							<td style="font-size: 15px; text-align: center;">${item[4]}</td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
</div>


<c:url value="deleteEduUrl" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm"
	name="deleteForm">
	<input type="hidden" name="deleteid" id="deleteid" value="0" />

</form:form>

<c:url value="non_regular_report_search" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="country_id1">
	<input type="hidden" name="unit_sus_no1" id="unit_sus_no1" />
	<input type="hidden" name="unit_posted_to1" id="unit_posted_to1" />
	<input type="hidden" name="first_name1" id="first_name1" />
</form:form>

<c:url value="Edit_non_regular_civilian_personnel_url" var="editUrl" />
<form:form action="${editUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="id2">
	<input type="hidden" name="id2" id="id2">
</form:form>
<script>

$(document).ready(function() {
	
	if('${unit_sus_no}'!=""){
		$("#unit_sus_no").val('${unit_sus_no}');
	}
	if('${unit_posted_to}'!=""){
		$("#unit_posted_to").val('${unit_posted_to}');
	}
	if('${first_name}'!=""){
		$("#first_name").val('${first_name}');
	}

	 
	 
});

function editData(id){
	
	document.getElementById('id2').value=id;
	document.getElementById('updateForm').submit();
}

function deleteData(id){
	
	document.getElementById('deleteid').value=id;
	document.getElementById('deleteForm').submit();
}

function Search(){
	$("#unit_sus_no1").val($('#unit_sus_no').val());
	$("#unit_posted_to1").val($('#unit_posted_to').val());
	$("#first_name1").val($('#first_name').val());
	document.getElementById('searchForm').submit();
}
</script>
<script>
	function validate() {
		
	    if ($("input#personnel_no").val() == "") {
			alert("Please Enter Personnel No");
			$("input#personnel_no").focus();
			return false;
				}
		if ($("select#course").val() == "0") {
			alert("Please Select Course");
			$("input#course").focus();
			return false;
		}
		
		if ($("input#name_of_institution").val() == "") {
			alert("Please Enter Name of Institute");
			$("input#name_of_institution").focus();
			return false;
		}
		
		if ($("select#type_of_comm_granted").val() == "0") {
			alert("Please Select Type of Commission Granted");
			$("input#type_of_comm_granted").focus();
			return false;
		}
		if ($("input#date_of_commission").val() == "") {
			alert("Please Enter Date of Commission");
			$("input#date_of_commission").focus();
			return false;
		}
		 
		 if ($("#date_of_seniority").val() == "") {
				alert("Please Enter Date of Seniority");
				$("#date_of_seniority").focus();
		 		return false;
			}
		 if ($("#date_of_birth").val() == "") {
				alert("Please Enter Date of Birth");
				$("#date_of_birth").focus();
		 		return false;
			}
		  if ($("input#parent_arm").val() == "") {
				alert("Please Enter Parent Arm/Service");
				$("input#parent_arm").focus();
				return false;
			}  
		 if ($("input#unit_sus_no").val() == "") {
				alert("Please Enter Unit Sus No");
				$("input#unit_sus_no").focus();
				return false;
			} 
	}
  
	 jQuery(function($){ //on document.ready  
		 datepicketDate('date_of_rank');
		 datepicketDate('date_of_commission');
		 datepicketDate('date_of_seniority');
		 datepicketDate('date_of_birth');
		 datepicketDate('date_of_authority');            
		 datepicketDate('date_appointment'); 
   });

	
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
	             $("#unit_posted_to").val(dec(enc,j[0]));   
	                 
	         }).fail(function(xhr, textStatus, errorThrown) {
	         });
			} 	     
		});	
	});
	
	// unit name
	 $("input#unit_posted_to").keypress(function(){
			var unit_name = this.value;
				 var susNoAuto=$("#unit_posted_to");
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
				        	  document.getElementById("unit_posted_to").value="";
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
	
	
// 	 $("input#parent_arm").keypress(function(){
// 			var arm_desc = this.value;
// 				 var susNoAuto=$("#parent_arm");
// 				  susNoAuto.autocomplete({
// 				      source: function( request, response ) {
// 				        $.ajax({
// 				        	type: 'POST',
// 					    	url: "getParentArmList?"+key+"="+value,
// 				        data: {arm_desc:arm_desc},
// 				          success: function( data ) {
// 				        	 var susval = [];
// 				        	  var length = data.length-1;
// 				        	  var enc = data[length].substring(0,16);
// 					        	for(var i = 0;i<data.length;i++){
// 					        		susval.push(dec(enc,data[i]));
// 					        	}
					        	   	          
// 							response( susval ); 
// 				          }
// 				        });
// 				      },
// 				      minLength: 1,
// 				      autoFill: true,
// 				      change: function(event, ui) {
// 				    	 if (ui.item) {   	        	  
// 				        	  return true;    	            
// 				          } else {
// 				        	  alert("Please Enter Approved Parent Arm.");
// 				        	  document.getElementById("parent_arm").value="";
// 				        	  susNoAuto.val("");	        	  
// 				        	  susNoAuto.focus();
// 				        	  return false;	             
// 				          }   	         
// 				      }, 
// 				      select: function( event, ui ) {
// 				    	// var target_unit_name = ui.item.value;
				    
// 					           /*  $.post("getTargetSUSFromUNITNAME?"+key+"="+value,{target_unit_name:target_unit_name}, function(data) {
// 					            }).done(function(data) {
// 					            	var length = data.length-1;
// 						        	var enc = data[length].substring(0,16);
// 						        	$("#unit_sus_no").val(dec(enc,data[0]));
// 					            }).fail(function(xhr, textStatus, errorThrown) {
// 					            }); */
// 				      } 	     
// 				}); 			
// 		});
	 
	 
	 
	 

	 function calculate_age(obj){    
			
			
	     var from_d=$("#"+obj.id).val();
		 
		 var from_d=$("#"+obj.id).val();
	     from_d = from_d.replaceAll("/", "-");
	  
		 
	     var from_d1=from_d.substring(6,10);
	     var from_d2=from_d.substring(3,5);
	     var from_d3=from_d.substring(0,2);
	     var frm_d = from_d3+"-"+from_d2+"-"+from_d1;         
	   
	     var today=new Date();
	     var to_d3 = today.getDate();
	     var to_d2 = today.getMonth() + 1;
	     var to_d1 = today.getFullYear();        
	     var to_d0 = to_d3+"-"+to_d2+"-"+to_d1;
	     if(to_d2 > from_d2 && to_d3 > from_d3 || to_d3 == from_d3){
	     var year = to_d1 - from_d1        
	     var month = to_d2 - from_d2
	     }
	     if(to_d2 > from_d2 && to_d3 < from_d3){
	             var year = to_d1 - from_d1        
	             var month = to_d2 - from_d2 - 1
	             }
	     if(from_d2 > to_d2){
	             var year = to_d1 - from_d1 - 1
	             var month1 = from_d2 - to_d2
	             var month = 12 - month1
	     }
	     if(from_d2 == to_d2 && from_d3 > to_d3){
	             var year = to_d1 - from_d1 - 1
	             var days = from_d3 - to_d3
	     }
	     if(from_d2 == to_d2 && to_d3 > from_d3){
	             var year = to_d1 - from_d1 
	             var days =  to_d3 - from_d3 
	             
	     }
	     if(from_d2 == to_d2 && to_d3 == from_d3){
	             var year = to_d1 - from_d1 
	             var days = 0
	     }
	     //days
	     if(from_d2 > to_d2 && from_d3 > to_d3){
	             var m = from_d2 - to_d2 
	             var n = m * 30
	             var m1 = from_d3 - to_d3 
	             var m2 = n+m1
	             var days =  m2
	     }
	     if(from_d2 > to_d2 && to_d3 > from_d3){
	             var m = from_d2 - to_d2 
	             var n = m * 30
	             var m1 =  to_d3 - from_d3 
	             var m2 = n+m1
	             var days =  m2
	     }
	     if(to_d2 > from_d2   && to_d3 > from_d3){
	             var m =  to_d2 - from_d2 
	             var n = m * 30
	             var m1 =  to_d3 - from_d3 
	             var m2 = n+m1        
	             var days =  m2 
	     
	     }
	     if(to_d2 >  from_d2 && from_d3 > to_d3){
	             var m = to_d2 - from_d2   
	             var n = m * 30
	             var m1 = from_d3 - to_d3 
	             var m2 = n+m1
	             var days =  m2
	             
	     }
	    
//	     document.getElementById("age_of_joining").value=year+"-"+month+"-"+days; 
	     //document.getElementById('age_year1').value = year;
	     
	     if (month == undefined)
	      {
	             month = 0;
	      }
	    
	     if(year <= 18 || year  >= 60)
	     {
	    	 alert("Please enter age above 18 Years and below 60 Years");
	    	 $("#"+obj.id).val("");
	     }
	 }

	 
	 function onChangePerNo(obj)
	 {
	 	var data = obj.value;

	 	if(data.length == 5)
	 	{
	 		var suffix="";
	 		var summation = 6*parseInt(data[0])+5*parseInt(data[1])+4*parseInt(data[2])+3*parseInt(data[3])+2*parseInt(data[4]);
	 		
	 		summation = parseInt( parseInt(summation) % 11);
	 	
	 		if(summation == 0)
	 		{
	 			suffix="A";
	 		}
	 		if(summation == 1)
	 		{
	 			suffix="F";
	 		}
	 		if(summation == 2)
	 		{
	 			suffix="H";
	 		}
	 		if(summation == 3)
	 		{
	 			suffix="K";
	 		}
	 		if(summation == 4)
	 		{
	 			suffix="L";
	 		}
	 		if(summation == 5)
	 		{
	 			suffix="M";
	 		}
	 		if(summation == 6)
	 		{
	 			suffix="N";
	 		}
	 		if(summation == 7)
	 		{
	 			suffix="P";
	 		}
	 		if(summation == 8)
	 		{
	 			suffix="W";
	 		}
	 		if(summation == 9)
	 		{
	 			suffix="X";
	 		}
	 		if(summation == 10)
	 		{
	 			suffix="Y";
	 		}
	 		$("#persnl_no3").val(suffix);
	 		
	 	}
	 }

	
  </script>

