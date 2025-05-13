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
  <script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
  <!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>


 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5> Search/Approve Census Data </h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by unit)</span></h6></div>
	          			<div class="card-body card-block">	
	          			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" 
						                 autocomplete="off"   style="display: none" maxlength="8"  onkeypress="return AvoidSpace(event)"  
						                 onkeyup="return specialcharecter(this)"> 
						               
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" 
						                  autocomplete="off" maxlength="50" style="display: none" onkeyup="return specialcharecter(this)" >				
										   <label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label>  
										</div>
						            </div>
	              				</div>
	              			</div>
	          			         			
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Personal No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" maxlength="9" min = "7"  onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)" > 
						                </div>
						            </div>
	              				</div>
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> RANK </label>
						                </div>
						                <div class="col-md-8">
						                   <select name="rank" id="rank" class="form-control "   >
												<option value="">--Select--</option>
												<c:forEach var="item" items="${getTypeofRankList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
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
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Status </label>
						                </div>						               
						                <div class="col-md-8">				           
												
											<select name="status" id="statusA1" class="form-control"  >
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
							<a href="search_censusUrl" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search"> 
<!-- 		              		<a href="search_censusUrl" class="btn btn-danger btn-sm" >Cancel</a>      -->
			            </div> 		
	        	</div>
			</div>
	</div>
	<input type=hidden id="unit_sus" name="unit_sus">
	<div class="container" id="divPrint" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
			
				<div  align="center">
			 <input type="hidden" id="CheckVal" name="CheckVal">
				  
				   <b><input type=checkbox id='nSelAll' name='tregn' 
						onclick='setselectall();'>Select all [<span id="tregn">0</span><span
							id='nTotRow1'>/</span><span id="tregnn"> ${list.size()}</span>]</b> 
						</div>	
		           <table id="getSearch_census" class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
		                 <thead>
		                       <tr>
			                          <th style="text-align: center; width:5%;">Ser No</th>
			                          <th style="text-align: center;"> Cadet No </th>	
			                          <th style="text-align: center;"> Personal No </th>		                        
			                          <th style="text-align: center;"> Rank </th> 
			                          <th style="text-align: center;"> Name </th> 
			                          <th style="text-align: center;"> DOB </th> 	
			                          <th style="text-align: center;"> Unit</th>	
			                          <th style="text-align: center;"> Parent Arm </th>
			                          <c:if test="${status1==3}">
			                          <th style="text-align: center;">Rejected Remarks </th>  
			                          </c:if>	
			                          <th style="text-align: center;"> Select to Approve </th>                         	
			                          <th style="text-align: center; width:10%;">Action</th>
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="11">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									<td style="font-size: 15px;text-align: center ;width: 5%;">${num.index+1}</td> 
									<td style="font-size: 15px;">${item[0]}</td>	
									<td style="font-size: 15px;">${item[1]}</td> 	
									<td style="font-size: 15px;">${item[2]}</td>	
									<td style="font-size: 15px;">${item[3]}</td> 								
									<td style="font-size: 15px;">${item[4]}</td> 	
									<td style="font-size: 15px;">${item[5]}</td>	
									<td style="font-size: 15px;">${item[6]}</td> 
									<c:if test="${status1==3}">
									<td style="font-size: 15px;">${item[8]}</td>
									</c:if>	
									<td style="font-size: 15px;">${item[11]}</td> 								
									<td style="font-size: 15px;text-align: center; width:10%;" >${item[9]} ${item[10]} ${item[12]}</td> 
									
									
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		                 
		                  <c:if test="${roleType == 'APP'  && status1 == '0'}">
		                 <div  align="center">
		                  <input type="button" class="btn btn-success btn-sm" value="Approve"
							onclick="return setApproveStatus();">
							<input type="button" class="btn btn-danger btn-sm" value="Reject"
							onclick="return setRejectStatus();">
							</div>
							</c:if>
		          </div>				  
		</div> 



<c:url value="GetSearch_Census" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no2">
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="0"/>				
		<input type="hidden" name="rank1" id="rank1"/>		
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		
	</form:form> 
	
<c:url value="edit_CensusDetails" var="editUrl" />
	<form:form action="${editUrl}" method="post" id="editForm" name="editForm" >
		<input type="hidden" name="personnel_no_edit" id="personnel_no_edit" value="0"/>
		<input type="hidden" name="personnel_no2" id="personnel_no2" value="0"/>				
		<input type="hidden" name="rank2" id="rank2"/>		
		<input type="hidden" name="status2" id="status2" value="0"/>
		<input type="hidden" name="unit_name2" id="unit_name2"  />
		<input type="hidden" name="unit_sus_no2" id="unit_sus_no2"  />								
	</form:form> 

<%-- <c:url value="view_censusUrl" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="ViewForm" name="ViewForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
</form:form> --%>
<c:url value="view_censusUrl" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="View1Form" name="View1Form" >
	   <input type="hidden" name="personnel_no_view" id="personnel_no_view" value="0">  
	    <input type="hidden" name="app_status" id="app_status" value="0">  
	    
	    <input type="hidden" name="personnel_no3" id="personnel_no3" value="0"/>				
		<input type="hidden" name="rank3" id="rank3"/>		
		<input type="hidden" name="status3" id="status3" value="0"/>
		<input type="hidden" name="unit_name3" id="unit_name3"  />
		<input type="hidden" name="unit_sus_no3" id="unit_sus_no3"  />		
</form:form>
	
	
	
<%-- <c:url value="Approve_Census" var="approveUrl" /> --%>
<%-- <form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" modelAttribute="idA"> --%>
<!-- 	<input type="hidden" name="idA" id="idA" value="0"/> 	 -->
<%-- </form:form>	 --%>

<%-- <c:url value="Reject_Census" var="rejectUrl" /> --%>
<%-- <form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="idR"> --%>
<!-- 	<input type="hidden" name="idR" id="idR" value="0"/>  -->
<%-- </form:form> --%>
<Script>
$(document).ready(function() {
	
	var r =('${roleAccess}');
	
	if( r == "Unit"){
		
		 $("#unit_sus_no_hid").show();
		 $("#unit_name_l").show();
		 
	}
	else  if(r == "MISO"){
		
		 $("input#unit_sus_no").show();		 
		 $("input#unit_name").show();
		
	}
	 if('${list.size()}' == ""){
		   $("div#getSearch_census").hide();
		}			
		$("#personnel_no").val('${personnel_no1}');				
		$("#name").val('${name1}');
		$("#rank").val('${rank1}');
		$("#course").val('${course1}');
		$("#unit_name").val('${unit_name1}');
		$("#unit_sus_no").val('${unit_sus_no1}');
		
		
		var q = '${personnel_no1}';		
		if(q != ""){
			$("#personnel_no").val(q);
		}
		
		var q7 = '${name1}';		
		if(q7 != ""){
			$("#name").val(q7);
		}
		 
		var q1 = '${rank1}';		
		if(q1 != ""){
			$("#rank").val(q1);
		}
		
		var q2 = '${course1}';		
		if(q2 != ""){
			$("#course").val(q2);
		}
		 
		var q3 = '${unit_name1}';		
		if(q3 != ""){
			$("#unit_name").val(q3);
		}
		
		
		if('${status1}' !='0'){
		var q4 = '${status1}';		
		if(q4 != ""){
			$("#statusA1").val(q4);
		}
		}
		
		
		
		
		var q5 = '${unit_sus_no1}';		
		if(q5 != ""){
			$("#unit_sus_no").val(q5);
		}
		
		
		
	    if('${size}' == 0 && '${size}' != ""){
	    	$("#divPrint").show();
		}
	    colAdj("getSearch_census");
});

function Search(){
	
	$("#personnel_no1").val($("#personnel_no").val()) ;	
	$("#status1").val($("#statusA1").val()) ;		
	$("#rank1").val($("#rank").val()) ;		
	$("#unit_name1").val($("#unit_name").val()) ;	
	$("#unit_sus_no1").val($("#unit_sus_no").val()) ;
	document.getElementById('searchForm').submit();
	
	
}

 

function View1Data(comm_id,app_status){	
	
	
	  $.ajaxSetup({
			async : false
		});
	$("#app_status").val(app_status);
	
	$("#personnel_no3").val($("#personnel_no").val()) ;	
	$("#status3").val($("#statusA1").val()) ;		
	$("#rank3").val($("#rank").val()) ;		
	$("#unit_name3").val($("#unit_name").val()) ;	
	$("#unit_sus_no3").val($("#unit_sus_no").val()) ;
	
	if(getPersonnelNo(comm_id,'personnel_no_view','','')){
		
		document.getElementById('View1Form').submit();
	}else{
		return false;
	}
}
function editData(comm_id){	
	
	$("#personnel_no2").val($("#personnel_no").val()) ;	
	$("#status2").val($("#statusA1").val()) ;		
	$("#rank2").val($("#rank").val()) ;		
	$("#unit_name2").val($("#unit_name").val()) ;	
	$("#unit_sus_no2").val($("#unit_sus_no").val()) ;
	
     if(getPersonnelNo(comm_id,'personnel_no_edit','','')){
    	 document.getElementById('editForm').submit();
	}else{
		return false;
	}
}
 function Approved(id){
		$("#idA").val(id);
		document.getElementById('approveForm').submit();
			
	}
 function Reject(id){
		$("#idR").val(id);
		document.getElementById('rejectForm').submit();
	} 
 function partData(id){	
		$("#id3").val(id);
		$("#updateForm").submit();
	}
 
</Script>

<script>	
	$("input#personnel_no").keyup(function(){
		
		var personel_no = this.value;
			 var susNoAuto=$("#personnel_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getpersonnel_noList?"+key+"="+value,
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

<script>
function findselected(){
	var nrSel=$('.nrCheckBox:checkbox:checked').map(function() {
		return $(this).attr('id');
	}).get();
		
	var b=nrSel.join(':');
	$("#CheckVal").val(b);
	$('#tregn').text(nrSel.length);
}


function setselectall(){

	if ($("#nSelAll").prop("checked")) {
		$(".nrCheckBox").prop('checked', true);
	} else {
		$(".nrCheckBox").prop('checked', false);
	}
	
	var l = $('[name="cbox"]:checked').length;
	 $("#tregn").val(l);
	document.getElementById('tregn').innerHTML = l;
	
}



function drawtregn(data) {
	var ii=0;
	$("#nrTable").empty();

	for (var i = 0; i <data.length; i++) {
		 var nkrow="<tr id='nrTable' padding='5px;'>";
		 nkrow=nkrow+"<td>&nbsp;&nbsp;";
		 nkrow=nkrow+ data[i] + "("+data[i]+")</td>";
		 $("#nrTable").append(nkrow);
		 ii=ii+1;
	}
	$("#tregn").text(ii);
}
function setApproveStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Approval"); 
	}else{

			$.post("Approve_CensusData?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
			alert(j);
			Search();
		}); 
	}	
}
function setRejectStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Reject"); 
	}else{

			$.post("Approve_CensusData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
			alert(j);
			Search();
		}); 
	}	
}
var check_count = 0;
function checkbox_count(obj,id)
{
	if(document.getElementById(obj.id).checked == true)
	{ 
		check_count++;
		
	}
	if(document.getElementById(obj.id).checked == false)
	{
		check_count--;
		
	}
	
	document.getElementById('tregn').innerHTML =check_count;
	
}


</script>

