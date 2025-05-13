<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>


<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

		<div class="animated fadeIn">
			<div class="col-md-12" align="center">
	    		<div class="card">
	    			<div class="card-header"><h5><b>SEARCH RELIEF PROGRAME</b><br></h5><h6><span style="font-size:12px;color:red;">(To be entered by SD-4 / MISO)</span></h6></div>
    				<div class="card-body">
    					<div class="col-md-12">
							<div class="col-md-6">
	          					<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Name </label>
									</div>
									<div class="col-12 col-md-8">			
										<textarea id="unit_name" name="unit_name" class="form-control autocomplete" style="font-size: 12px;" autocomplete="off" maxlength="100" placeholder="select Unit Name"></textarea>
									</div>
								</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label class=" form-control-label">SUS No</label>
						            </div>
						            <div class="col-12 col-md-8">
										<input type="text" id="sus_no" name="sus_no"  class="form-control autocomplete" maxlength="8" autocomplete="off" placeholder="Select SUS No">
									</div>
              					</div>
	          				</div>
	          			</div>
    					<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
                					<div class="col col-md-4">
                  						<label for="text-input" class=" form-control-label">Period (From)</label>
                					</div>
                					<div class="col-12 col-md-8">
                  						<input type="month" id="period_from" name="period_from" maxlength="10" class="form-control">
									</div>
								</div>
	          				</div>
	          				<div class="col-md-6">
	          					<div class="row form-group">
	               					<div class="col col-md-4">
	                 						<label for="text-input" class=" form-control-label">Period (To)</label>
	               					</div>
	               					<div class="col-12 col-md-8">
	                 						<input type="month" id="period_to" name="period_to" maxlength="10" class="form-control">
									</div>
 								</div>
	          				</div>
	          			</div>
	          			<div class="col-md-12">
	          				<div class="col-md-6">
	          					<div class="row form-group">
               					<div class="col col-md-4">
                 						<label for="text-input" class=" form-control-label">Status</label>
               					</div>
               					<div class="col-12 col-md-8">
              							<select name="status" id="status" class="form-control-sm form-control">
               								<option value="0">Pending</option>
				                    		<option value="1">Approved</option>
				                    		<option value="2">Reject</option>
				                    	</select>
	               					</div>
								</div>
	          				</div>
	          			</div>
           			</div>
					<div class="card-footer" align="center">
						<a href="approved_sd_reliefReport" class="btn btn-success btn-sm" >Clear</a>   
	              		<button class="btn btn-primary btn-sm" onclick="return searchReliefData();"> Search</button>
	              		
					</div> 
	    			<div class="card-body">
    					<div class="col-md-12" id="report1" style="overflow: auto; display: none;">
	    			    	<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered"  style="width: 100%;">
								<thead style="background-color: #9c27b0; color: white;font-size: 12px">
									<tr>
										<th >Auth Letter No</th>
										<th >Ser No of Letter</th>
										<th >Date</th>
										<th >Period (From)</th>
										<th >Period (To)</th>
										<th >No of Records</th>
										<th >ACTION</th>
									</tr>
								</thead>
								<tbody style="font-size: 11px">
									<c:forEach var="item" items="${getReliefReportList}" varStatus="num" >
										<tr>
											<td>${item[0]}</td>
											<td>${item[6]}</td>
											<td>${item[1]}</td>
											<td>${item[2]}</td>
											<td>${item[3]}</td>
											<td>${item[5]}</td>
											<td><button onclick="open1('${item[0]}','${item[6]}');">OPEN</button></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>	
					</div>
					<div class="card-body" id="report2" style="overflow: auto; display: none;">
	    				<div class="col-md-12">
							<table id="SearchReport1" class="table no-margin table-striped  table-hover  table-bordered" style="width: 100%;">
								<thead style="background-color: #9c27b0; color: white;font-size: 12px">
									<tr>
									   	<!-- <th >ID</th> -->
									  <!-- 	<th >SUS No</th> -->
										<th >Unit Name</th>
										<th >Imdt Higher FMN</th>
										<!-- <th >Arm Name</th> -->
										<th >Mode of Tpt</th> 
										<th >NMB Date</th>
										<th >Indn/De-Indn pd</th>
										<th >To Location</th>
										<th >NRS</th>
										<th >Move of Adv Party Date</th>
										<!-- <th >Re-placed by SUS No</th> -->
										<th >To Unit</th>
										<th >On Relief Yes/No</th>
										<th >Type of Cl</th>															
										<th >ACTION</th>
							    	</tr>
							  	</thead>
							 	<tbody style="font-size: 11px">
							 		<c:forEach var="item" items="${getSearchReliefReportList}" varStatus="num" >
										<tr>
											<%-- <td>${item[0]}</td> --%>
											<%-- <td>${item[2]}</td> --%>
											<td>${item[3]}</td>
											<td>${item[4]}</td>
											<%-- <td>${item[5]}</td> --%>
											<td>${item[6]}</td>
											<td>  <c:if test="${item[16] == '0'}">   ${item[7]} </c:if>  <c:if test="${item[16] == '1'}">   On Relief </c:if> </td>
											<td>${item[8]}</td>
											<td>${item[9]}</td>
											<td>${item[10]}</td>
											<td>${item[13]}</td>
											<%-- <td>${item[14]}</td> --%>
											<td>${item[15]}  </td>
											<td>  <c:if test="${item[16] == '0'}">   No  </c:if>   <c:if test="${item[16] == '1'}">   Yes  </c:if> </td> 
											<td>${item[17]}</td> 
											<td>	
												<c:if test="${roleType == 'ALL' || roleType == 'DEO'}"> 
													<c:if test="${item[18] == 0 || item[18] == 2}">
														<i class="action_icons action_update" onclick="edit('${item[0]}');" title="Edit Data"></i>
														<i class="action_icons action_delete" onclick="Delete1('${item[0]}');" title="Delete Data"></i>
													</c:if>
												</c:if>
												<c:if test="${item[18] == 1}">
									    		   <b>Approved</b>
									    		</c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table> 
						</div>
						<div class="col-md-12" align="center" id="approvedBTN">
							<c:if test="${roleType == 'ALL' || roleType == 'APP'}"> 
								<c:if test="${getSearchReliefReportList[0][18] == 0}">	
								     	<input type="submit"  class="btn btn-primary btn-sm" value="Approve" onclick="return approvedReliefData('${getSearchReliefReportList[0][19]}');"> 							
										<input type="submit"  class="btn btn-danger btn-sm" value="Reject" onclick="return rejectReliefData('${getSearchReliefReportList[0][19]}');">
								</c:if>
							</c:if>
	              		</div>
					</div>
				</div>
			</div>
		</div>
	
	<c:url value="approved_sd_reliefReport1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="period_from1" id="period_from1" value="0"/>
		<input type="hidden" name="period_to1" id="period_to1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="auth_letter1" id="auth_letter1" value="0"/>
		<input type="hidden" name="ser_no1" id="ser_no1" value="0"/>		
		<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1" value="0"/>
	</form:form> 
	
	<c:url value="editRelief" var="editurl" />
	<form:form action="${editurl}"  method="post" id="editForm" name="editForm" modelAttribute="editId">
		<input type="hidden" name="editId" id="editId" value="0"/>
	</form:form>
	
	<c:url value="deleteRelief" var="deleteurl" />
	<form:form action="${deleteurl}"  method="post" id="deleteForm" name="deleteForm" modelAttribute="deleteId">
		<input type="hidden" name="deleteId" id="deleteId" value="0"/>
	</form:form>

<script type="text/javascript">

$(document).ready(function() {
	$("#period_from").val('${period_from1}');
	$("#period_to").val('${period_to1}');
	
	$("#sus_no").val('${sus_no1}');
	$("#unit_name").val('${unit_name1}');
	
	if('${status1}' != ""){
		$("#status").val('${status1}');
	}
	
	if('${getReliefReportList.size()}' != "0" && '${getReliefReportList[0][0]}' != "" ){
		$("#report1").show();
	}else{
		$("#report1").hide();
	}
	
	if('${getSearchReliefReportList.size()}' != "0" && '${getSearchReliefReportList[0][0]}' != "" ){
		$("#report2").show();
	}else{
		$("#report2").hide();
	}
});

function searchReliefData() {
	$("#report2").hide();
	var tab = $("#SearchReport > tbody");
	tab.empty(); 
       
	var period_from = $("#period_from").val();
	var period_to = $("#period_to").val();
	var status = $("#status").val();
       
	if($("#status").val() == ""){
		alert("please Select Status");
		$("#status").focus();
		return false;
	}
    
	$("#period_from1").val($("#period_from").val());
    $("#period_to1").val($("#period_to").val());
    $("#status1").val($("#status").val());
    $("#auth_letter1").val("");
    $("#sus_no1").val($("#sus_no").val());
    $("#unit_name1").val($("#unit_name").val());
    $("#WaitLoader").show();
	document.getElementById('searchForm').submit();
}
function open1(auth_letter,serno)
{  
	$("#report2").show();
	var tab = $("#SearchReport1 > tbody");
    tab.empty(); 
    var status = $("#status").val();
      
    $("#period_from1").val($("#period_from").val());
    $("#period_to1").val($("#period_to").val());
    $("#status1").val($("#status").val());
    $("#auth_letter1").val(auth_letter);
    $("#ser_no1").val(serno);
    $("#sus_no1").val($("#sus_no").val());
    $("#unit_name1").val($("#unit_name").val());
 	document.getElementById('searchForm').submit();
}
function edit(id)
{  
	document.getElementById("editId").value=id;	
	document.getElementById("editForm").submit();
}

function Delete1(id)
{  
	document.getElementById("deleteId").value=id;	
	document.getElementById("deleteForm").submit();
}


function approvedReliefData(serno) {
	auth_letter_id = '${auth_letter1}';
	var status = $("#status").val();
	
	$.ajax({
        type: 'POST',
        url: 'approvedSdReliefDetails?'+key+"="+value,
        data: {auth_letter:auth_letter_id,status:status,ser_no:serno},
       	success: function(response) {
        	searchReliefData();
        	alert("Data Approved Successfully");
        	$("#report2").hide();
       	}
	});
}
function rejectReliefData(serno) {
	auth_letter_id = '${auth_letter1}';
	var status = $("#status").val();
	$.ajax({
        type: 'POST',
        url: 'rejectSdReliefDetails?'+key+"="+value,
        data: {auth_letter:auth_letter_id,status:status,serno:serno},
       	success: function(response) {
        	searchReliefData();
        	alert("Data Rejected Successfully");
        	$("#report2").hide();
       	}
	});
}

jQuery(function() {
	// Source SUS No
	jQuery("#sus_no").keypress(function(){
		var sus_no = this.value;
			 var susNoAuto=jQuery("#sus_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        jQuery.ajax({
			        type: 'POST',
			        url: "getTargetSUSNoList?"+key+"="+value,
			        data: {sus_no:sus_no},
			          success: function( data ) {
			        	  var susval = [];
			        	  var length = data.length-1;
			        	  if(data.length != 0){
				        		var enc = data[length].substring(0,16);
				        	}
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
			        	  alert("Please Enter Approved SUS No.");
			        	  document.getElementById("sus_no").value="";
			        	  document.getElementById("unit_name").value="";
			        	  susNoAuto.val("");	        	  
			        	  susNoAuto.focus();
			        	  return false;	             
			          }   	         
			      }, 
			      select: function( event, ui ) {
			    	var sus_no = ui.item.value;
			    	$.post("getActiveUnitNameFromSusNo?"+key+"="+value, {sus_no:sus_no}).done(function(j) {				
			    		var length = j.length-1;
					   	var enc = j[length].substring(0,16);
					   	jQuery("#unit_name").val(dec(enc,j[0]));	
			    	}).fail(function(xhr, textStatus, errorThrown) {
			    });
			} 	     
		});	
	});
	// End
	
	// Source Unit Name
   jQuery("#unit_name").keyup(function(){
 			var unit_name = this.value;
				 var susNoAuto=jQuery("#unit_name");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        jQuery.ajax({
				        type: 'POST',
				        url: "getTargetUnitsNameActiveList?"+key+"="+value,
				        data: {unit_name:unit_name},
				          success: function( data ) {
				        	  var susval = [];
				        	  var length = data.length-1;
				        	  if(data.length != 0){
					        		var enc = data[length].substring(0,16);
					        	}
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
				        	  document.getElementById("sus_no").value="";
				        	  susNoAuto.val("");	        	  
				        	  susNoAuto.focus();
				        	  return false;	             
						}   	         
					}, 
					select: function( event, ui ) {
						var target_unit_name = ui.item.value;
					 	$.post("getTargetSUSFromUNITNAME?"+key+"="+value, {target_unit_name:target_unit_name}).done(function(j) {				
					 		 var length = j.length-1;
	 				         var enc = j[length].substring(0,16);
	 				         jQuery("#sus_no").val(dec(enc,j[0]));	
						}).fail(function(xhr, textStatus, errorThrown) {
					});
				}
			}); 			
 		});
	});
</script>