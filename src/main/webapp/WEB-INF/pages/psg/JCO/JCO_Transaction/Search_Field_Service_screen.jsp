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
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>

<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
	          			

	
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5> Search/Approve Field Service</h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by unit)</span></h6></div>
	          			<div class="card-body card-block">
	            	<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off"   style="display: none"
						                  maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)"> 			 
						               
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
<!-- 	              				</div> -->
	              				              					
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong> Area </label>
						                </div>
						                <div class="col-md-8">
						                	 <select name="fd_service1" id="fd_service1" onchange="AreaChange();" class="form-control-sm form-control">
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
						                    <label class=" form-control-label"> Army No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" 
						                    maxlength="11" onkeyup="return specialcharecter(this)" onclick="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>
	             					<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
						                </div>						               
						                <div class="col-md-8">				           
												<input type="hidden" name="jco_id" id="jco_id" value="0">
											<select name="status" id="statusA" class="form-control"  >
													<option value="0">Pending</option>
												    <option value="1">Approved</option>	
													 <option value="3">Rejected</option>
													 <option value="4">View History/Cancel</option>
											</select>
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
							<input type="text" id="unit_location1" name="unit_location1" class="form-control autocomplete" autocomplete="off" 
							maxlength="50" onkeyup="return specialcharecter(this)">  
						                </div>
						            </div>
	              				</div>
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
	              			</div>
	              			 <div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Created by </label>
						                </div>
						                <div class="col-md-8">
		
						                 
						                 <select name="cr_by" id="cr_by" class="form-control-sm form-control"   >
                                                                                                <option value="">--Select--</option>
                                                                                                <c:forEach var="item" items="${getRoleNameList_dash}" varStatus="num">
                                                                                                <option value="${item.userId}"  name="${item.userName}">${item.userName}</option>
                                                                                                </c:forEach>
                                                                                        </select> 
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Created Date </label>
						                </div>
						                <div class="col-md-8">
						               	<input type="text" name="cr_date" id="cr_date" maxlength="10"   onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"  >
											
										</div>
						            </div>
	              				</div>
	              			</div>
	              				              			              				              			
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="Search_field_service_Jco_url" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search"> 
			            </div> 		
	        	</div>
			
	</div> 
		
	
	<div class="container" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_field_service" class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
		                 <thead>
		                       <tr>
			                         <th style="text-align: center;">Ser No</th>
			                       	  <!-- <th style="text-align: center;"> Personal No </th> -->
			                       	  <th style="text-align: center;"> Unit Name </th>
<!-- 			                       	  <th style="text-align: center;">Category</th>	 -->
			                       	  <th style="text-align: center;">Area</th>
			                       	  <th style="text-align: center;">Authority</th>
			                       	  <th style="text-align: center;">Authority Date</th>
			                       	  <th style="text-align: center;">Op Name</th>
			                       	  <th style="text-align: center;">Unit Location</th>
			                       	  	  <th style="text-align: center;">Reject Remarks</th>
			                         <th style="text-align: center;">Action</th>
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="8">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									<td style="font-size: 15px;text-align: center ;">${num.index+1}</td> 
										<td style="font-size: 15px;">${item[6]}</td> 
									 <td style="font-size: 15px;">${item[1]}</td> 	
									  <td style="font-size: 15px;">${item[2]}</td> 	
									   <td style="font-size: 15px;" id="authdate${num.index+1}">${item[3]}</td> 	
									    <td style="font-size: 15px;">${item[4]}</td> 	
									<td style="font-size: 15px;">${item[5]}</td>									
										<td style="font-size: 15px;">${item[7]}</td>
									<td style="font-size: 15px;text-align: center;" >${item[8]} ${item[9]} ${item[10]}</td> 
									
									
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		 </div>	

<c:url value="GetSearch_field_service_data_Jco" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="personnel_no1">
		<input type="hidden" name="personnel_no1" id="personnel_no1"/>		
		<input type="hidden" name="fd_service2" id="fd_service2" />
		<input type="hidden" name="unit_location2" id="unit_location2" />
		<input type="hidden" name="operation2" id="operation2" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name2" id="unit_name2"  />
		<input type="hidden" name="unit_sus_no2" id="unit_sus_no2"  />
		<input type="hidden" name="cr_date1" id="cr_date1" />
		<input type="hidden" name="cr_by1" id="cr_by1" />
	<!-- 		<input type="hidden" name="service_category1" id="service_category1"  /> -->
	</form:form> 
	
	<c:url value="Edit_field_service_data_JcoUrl" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
<!-- 	  <input type="hidden" name="personnel_no3" id="personnel_no3"/>		 -->
		<input type="hidden" name="fd_service3" id="fd_service3" />
		<input type="hidden" name="cat1" id="cat1" />
<!-- 		<input type="hidden" name="unit_location3" id="unit_location3" /> -->
<!-- 		<input type="hidden" name="operation3" id="operation3" value="0"/> -->
<!-- 		<input type="hidden" name="status3" id="status3" value="0"/> -->
<!-- 		<input type="hidden" name="unit_name3" id="unit_name3"  /> -->
<!-- 		<input type="hidden" name="unit_sus_no3" id="unit_sus_no3"  /> -->
</form:form>

<c:url value="Edit_Appr_field_service_Jco" var="editAppUrl"/>
<form:form action="${editAppUrl}" method="post" id="updateApprForm" name="updateApprForm" modelAttribute="id5">
	  <input type="hidden" name="id5" id="id5" value="0">
		<input type="hidden" name="fd_service5" id="fd_service5" />
<!-- 		<input type="hidden" name="cat5" id="cat5" /> -->
</form:form>

<c:url value="ApproveSearch_field_service_dat_Jco" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0"/> 
	<input type="hidden" name="fd_servicesapp" id="fd_servicesapp" value="0">
<!-- 	<input type="hidden" name="cat3" id="cat3" /> -->
</form:form>

<c:url value="View_field_service_dat_Jco" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="id6">
	<input type="hidden" name="id6" id="id6" value="0"/> 
	<input type="hidden" name="fd_servicesapp6" id="fd_servicesapp6" value="0">
<!-- 	<input type="hidden" name="cat6" id="cat6" /> -->
</form:form>

<c:url value="View_field_service_history_dat_Jco" var="viewHisytoryUrl" />
<form:form action="${viewHisytoryUrl}" method="post" id="viewHistoryForm" name="viewHistoryForm" modelAttribute="id7">
	<input type="hidden" name="id7" id="id7" value="0"/> 
<!-- 	<input type="hidden" name="cat7" id="cat7" value="0"/> -->
</form:form>

<c:url value="Approve_field_service_history_dat_Jco" var="ApprHisytoryUrl" />
<form:form action="${ApprHisytoryUrl}" method="post" id="apprHistoryForm" name="apprHistoryForm" >
	<input type="hidden" name="id_ac" id="id_ac" value="0"/> 
<!-- 	<input type="hidden" name="cat_ac" id="cat_ac" value="0"/> -->
</form:form>
	
	<c:url value="Delete_Search_field_service_data_Jco" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>

<script>
$(document).ready(function() {
	
	 jQuery(function($){ 
	 datepicketDate('cr_date');
    });
	colAdj("getSearch_field_service");
	var r =('${roleAccess}');
// 	if('${msg}' != ""){
// 		   alert('${msg}');
// 		}
	
	if( r == "Unit"){
		
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
		 
	}
	else  if(r == "MISO"){
		
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
		
	}
	 if('${list.size()}' == ""){
		   $("#getSearch_field_service").hide();
		}	
		$("#cr_date").val('${cr_date1}');		
		$("#cr_by").val('${cr_by1}');	
		$("#personnel_no").val('${personnel_no1}');		
		//$("#fd_service1").val('${fd_service2}');
		$("#status").val('${status1}');

// 		var q9 = '${service_category1}';
// 		if(q9 != ""){
// 			$("#service_category").val(q9);
// 		}
		
		var q2 = '${personnel_no1}';
		if(q2 != ""){
			$("#personnel_no").val(q2);
		}
		var q6 = '${fd_service2}';
		if(q6 != ""){
			$("#fd_service1").val(q6);
		}
		var q6 = '${unit_location2}';
		if(q6 != ""){
			$("#unit_location1").val(q6);
		}
		var q6 = '${operation2}';
		if(q6 != ""){
			$("#operation1").val(q6);
		}
		
		var q4 = '${status1}';
		if(q4 != ""){
			$("#statusA").val(q4);
		}
		
		var q5 = '${unit_sus_no2}';		
		if(q5 != ""){
			$("#unit_sus_no").val(q5);
		}
		
		var q3 = '${unit_name2}';		
		if(q3 != ""){
			$("#unit_name").val(q3);
		}
		
	    if('${size}' == 0 && '${size}' != ""){
	    	$("#divPrint").show();
		}
	    
	    if('${list.size()}' != ""){
	    	var lsize='${list.size()}';
	    	for(i = 1; i<=parseInt(lsize); i++){
	    		$("#authdate"+i).html(convertDateFormate($("#authdate"+i).html()));
	    	}
		}
	    
});

function Search(){
	
// 	if($("#service_category").val()==0){
// 		alert("Please Select Category To Search");
// 		$("#service_category").focus();
// 		return false;
// 	}
// 	if($("#fd_service1").val()==0){
// 		alert("Please Select Area To Search");
// 		$("#fd_service1").focus();
// 		return false;
// 	}
	
	
// 	$("#service_category1").val($("#service_category").val()) ;
	$("#personnel_no1").val($("#personnel_no").val()) ;	
	$("#status1").val($("#statusA").val()) ;		
	$("#fd_service2").val($("#fd_service1").val()) ;	
	$("#unit_location2").val($("#unit_location1").val()) ;	
	$("#operation2").val($("#operation1").val()) ;	
	$("#unit_name2").val($("#unit_name").val()) ;	
	$("#unit_sus_no2").val($("#unit_sus_no").val()) ;
	$("#cr_by1").val($("#cr_by").val()) ;
	$("#cr_date1").val($("#cr_date").val()) ;
	document.getElementById('searchForm').submit();
	
}
function editData(id,fd_services){
	$("#id2").val(id);
	$("#fd_service3").val(fd_services);
	
// 	$("#cat1").val($("#service_category").val()) ;	
// 	$("#personnel_no3").val($("#personnel_no").val()) ;	
// 	$("#status3").val($("#statusA").val()) ;		
// 	$("#fd_service3").val($("#fd_service1").val()) ;	
// 	$("#unit_location3").val($("#unit_location1").val()) ;	
// 	$("#operation3").val($("#operation1").val()) ;	
// 	$("#unit_name3").val($("#unit_name").val()) ;	
// 	$("#unit_sus_no3").val($("#unit_sus_no").val()) ;
	
	document.getElementById('updateForm').submit();		 
}

function editApprData(id,fd_services){
	$("#id5").val(id);
	$("#fd_service5").val(fd_services);
// 	$("#cat5").val($("#service_category").val()) ;	
	document.getElementById('updateApprForm').submit();		 
}

// function deleteData(id){
// 	$("#id1").val(id);
// 	document.getElementById('deleteForm').submit();
// }
function Approved(id,fd_services){
	
	$("#id3").val(id);
	$("#fd_servicesapp").val(fd_services);
// 	$("#cat3").val($("#service_category").val()) ;	
	document.getElementById('approveForm').submit();
	
}
function ViewData(id,fd_services){
	
	$("#id6").val(id);
	$("#fd_servicesapp6").val(fd_services);
// 	$("#cat6").val($("#service_category").val()) ;	
	document.getElementById('viewForm').submit();
	
}
function ViewHistoryData(id){
	
	$("#id7").val(id);
// 	$("#cat7").val($("#service_category").val()) ;	
	document.getElementById('viewHistoryForm').submit();
	
}

function ApprHistoryData(id){
	
	$("#id_ac").val(id);
// 	$("#cat_ac").val($("#service_category").val()) ;	
	document.getElementById('apprHistoryForm').submit();
	
}
</script>

<script>	
	$("input#personnel_no").keypress(function(){
		
// 			if($('#service_category').val()=='0'){
// 				alert("Please Select Category First!!");
// 				$("#personnel_no").val("");
// 				$('#service_category').focus();
// 				return false;
// 			}
			var personel_no = $("#personnel_no").val();
			 var susNoAuto=$("#personnel_no");
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
						document.getElementById("personnel_no").value = "";
						susNoAuto.val("");
						susNoAuto.focus();
						return false;
					}
				},
				select : function(event, ui) {
					var personnel_no_1 = ui.item.value;
			    	  document.getElementById("personnel_no").value=personnel_no_1;
// 			    	  if(parseInt(personnel_no_1.length) > parseInt("7"))
// 			    	  {
// 			    		  personal_number(val);
// 			    	  }
				}
			});


					
	});
	
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