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
  
  <!-- new datatables -->
<link rel="stylesheet" href="js/datatable/css/datatables.min.css">
<script type="text/javascript" src="js/datatable/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/datatable/js/jquery.mockjax.js"></script>

   <!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>

	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5> APPROVE UPDATE CENSUS DETAILS </h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be Approved by Co/Oc Unit)</span></h6></div>
	          			<div class="card-body card-block">
	            	<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off"  maxlength="8" 
						                 onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)"  style="display: none"> 
						               
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off"  style="display: none" maxlength="50" onkeyup="return specialcharecter(this)">				
										   <label  id="unit_name_l" style="display: none" ><b>${unit_name}</b></label>  
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
						                   <input type="text" id="army_no" name="army_no" class="form-control autocomplete" maxlength="11" autocomplete="off"  onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> RANK </label>
						                </div>
						                <div class="col-md-8">
						                    <select name="rank" id="rank" class="form-control-sm form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getRankjcoList}" varStatus="num">
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
												<input type="hidden" name="jco_id" id="jco_id" value="0">
											<select name="status" id="statusA" class="form-control"  >
											<option value="0">Pending</option>
												    <option value="1">Approved</option>	
													 <option value="3">Rejected</option>
											</select>
										</div>
						            </div>
	              				</div>	             					              				
	              			</div>
	              			
	              			<div class="col-md-12">
  						 <div class="col-md-6">
	             		 <div class="row form-group">	
		                 <div class="col-md-4">
	                 		  <label for="text-input" class=" form-control-label">Created By </label>
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
	                 		  <label for="text-input" class="form-control-label">created date </label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
	             			
	               		<input type="text" name="cr_date" id="cr_date" maxlength="10"   onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" max="${date}"  >
	               		 </div>
	               		 </div>
	               		 </div>	               		 	               		 
		             </div>
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="Search_Update_Census_Url_JCO" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search"> 
			            </div> 		
	        	</div>
			</div>
	</div>
	<input type=hidden id="unit_sus" name="unit_sus">
<%-- 	<div class="container" id="divPrint" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_census" class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
		                 <thead>
		                       <tr>
		                       
		                       		  <th style="text-align: center; width:10%;">Ser No</th>
			                          <th style="text-align: center;"> Army No </th>		                        
			                          <th style="text-align: center;"> Rank </th> 		                          
			                          <th style="text-align: center;"> Name </th> 
			                          <th style="text-align: center;"> DOB </th> 	
			                          <th style="text-align: center;"> Parent Arm </th> 
			                          <th style="text-align: center; width:20%;">Action</th> 
		                          </tr>                                                        
		                  </thead> 
		                  <tbody>
			                 <c:if test="${list.size()==0}">
								<tr>
									<td style="font-size: 15px; text-align: center; color: red;" colspan="7">Data Not Available</td>
								</tr>
							</c:if>
		                       <c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									<td style="font-size: 15px;text-align: center ;width: 10%;">${num.index+1}</td> 
									<td style="font-size: 15px;">${item[0]}</td> 	
									<td style="font-size: 15px;">${item[1]}</td>									
									<td style="font-size: 15px;">${item[2]}</td> 	
									<td style="font-size: 15px;">${item[3]}</td>	
									<td style="font-size: 15px;">${item[4]}</td> 
									<td style="font-size: 15px;text-align: center; width:20%;" >${item[5]} ${item[6]} ${item[7]}  ${item[8]}</td> 
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div> 
 --%>
<!--  bisag v2 010922  (converted to Datatable) -->
<div class="datatablediv">
	<div class="col-md-12" id="getSearch_Letter_a" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_census_jco" class="table no-margin table-striped  table-hover  table-bordered ">
		                 <thead>
		                       <tr>
			                       <th style="text-align: center; width:10%;">Ser No</th>
			                          <th style="text-align: center;"> Army No </th>		                        
			                          <th style="text-align: center;"> Rank </th> 		                          
			                          <th style="text-align: center;"> Name </th> 
			                          <th style="text-align: center;"> DOB </th> 	
			                          <th style="text-align: center;"> Parent Arm </th> 
			                          <th style="text-align: center; width:20%;">Action</th> 
		                          </tr>                                                        
		                  </thead> 
		                 
		              </table>
		         </div>				  
		</div> 
		</div>

<c:url value="GetS_C_LData_JCO" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="army_no1">
		<input type="hidden" name="army_no1" id="army_no1" value="0"/>		
		<input type="hidden" name="rank1" id="rank1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="icstatus" id="icstatus" value="0" />
		<input type="hidden" name="cr_date1" id="cr_date1"  />
	<input type="hidden" name="cr_by1" id="cr_by1"  />
	
		
	</form:form> 

<c:url value="Approve_UpadteComm_DataUrl_JCO" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="ViewForm" name="ViewForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
	  <input type="hidden" name="army_no2" id="army_no2" value="0">
</form:form>

<c:url value="View_ApproveUpadteCensusDataUrl_JCO" var="ViewApproveUrl"/>
<form:form action="${ViewApproveUrl}" method="post" id="ap_ViewForm" name="ap_ViewForm" modelAttribute="idV">
	<input type="hidden" name="ap_id" id="ap_id" value="0">
	  <input type="hidden" name="ap_army_no2" id="ap_army_no2" value="0">
	   
</form:form>

<c:url value="Update_Census_Details_JCO" var="editUrl"/>
	<form:form action="${editUrl}" method="get" id="updateForm" name="updateForm" modelAttribute="id2">
	  	<input type="hidden" name="id_edit" id="id_edit" value="0">
	  	<input type="hidden" name="personnel_no_edit_JCO" id="personnel_no_edit_JCO" value="0">
	  	<input type="hidden" name="status" id="status" value="0">
	  	
	  <input type="hidden" name="army_no6" id="army_no6" value="0">
	  <input type="hidden" name="status6" id="status6" value="0">
	  <input type="hidden" name="rank6" id="rank6" value="0">
	  <input type="hidden" name="jco_id6" id="jco_id6" value="0">
	  <input type="hidden" name="unit_name6" id="unit_name6" value="0">
	  <input type="hidden" name="unit_sus_no6" id="unit_sus_no6" value="0">
	</form:form>
	
	
<c:url value="Reject_Upadte_CensusJCODataUrl" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="id3">
	 <input type="hidden" name="id3" id="id3" value="0">
	  <input type="hidden" name="personnel_no3" id="personnel_no3" value="0">
	   <input type="hidden" name="jco_id3" id="jco_id3" value="0">
	   <input type="hidden" name="event3" id="event3" value="0"/>
	   
	   
	  <input type="hidden" name="army_no7" id="army_no7" value="0">
	  <input type="hidden" name="status7" id="status7" value="0">
	  <input type="hidden" name="rank7" id="rank7" value="0">
	  <input type="hidden" name="jco_id7" id="jco_id7" value="0">
	  <input type="hidden" name="unit_name7" id="unit_name7" value="0">
	  <input type="hidden" name="unit_sus_no7" id="unit_sus_no7" value="0">
	   
</form:form>
	
 <Script>
 
 $("input#army_no").keypress(function(){
		var personel_no = this.value;
			 var susNoAuto=$("#army_no");
			  susNoAuto.autocomplete({
			      source: function( request, response ) {
			        $.ajax({
			        	type: 'POST',
				    	url: "getpersonnel_noListApproved_JCO?"+key+"="+value,
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
 

$(document).ready(function() {
	$.ajaxSetup({
		async : false
	});
	
	jQuery(function($){ 
        
        datepicketDate('cr_date');
	});
	
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
		
	 if('${status1}'!=0){
			$("#statusA").val('${status1}');
		}
		
		if('${army_no1}'!=""){
			$("#army_no").val('${army_no1}');
		}
		
		if('${rank1}'!=0){
			$("#rank").val('${rank1}');
		}
		
		
		var q2 = '${army_no1}';
		if(q2 != ""){
			$("#army_no").val(q2);
		}
		var q6 = '${rank1}';
		if(q6 != ""){
			$("#rank").val(q6);
		}
		var q4 = '${status1}';
		if(q4 != ""){
			$("#status").val(q4);
		}
		var sus_no = '${unit_sus_no1}';		
		if(sus_no != ""){
			$("#unit_sus_no").val(sus_no);
		}
		
		
    	getunit(sus_no);
    	function getunit(val) {	
            $.post("getTargetUnitNameList?"+key+"="+value, {
            	sus_no : sus_no
        }, function(j) {
              

        }).done(function(j) {
    				   var length = j.length-1;
    	                   var enc = j[length].substring(0,16); 
    	                    
    	                   $("#unit_name").val(dec(enc,j[0])); 
        }).fail(function(xhr, textStatus, errorThrown) {
        });
    } 
		
	    if('${size}' == 0 && '${size}' != ""){
	    	$("#divPrint").show();
		}
	    $("#cr_date").val('${cr_date1}');		
		$("#cr_by").val('${cr_by1}');	
		
		
	
		
	    colAdj("getSearch_census");
	    
	   //  bisag v2 260822  (converted to Datatable) 
	    
	    
	    mockjax1('getSearch_census_jco');
		table = dataTable('getSearch_census_jco');
		$('#btn-reload').on('click', function(){
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		});
});

function Search(){
	
	$("#army_no1").val($("#army_no").val());	
	$("#status1").val($("#statusA").val());		
	$("#rank1").val($("#rank").val());	
	$("#jco_id").val($("#jco_id").val());
	$("#unit_name1").val($("#unit_name").val());	
	$("#unit_sus_no1").val($("#unit_sus_no").val());
	$("#cr_by1").val($("#cr_by").val()) ;
	$("#cr_date1").val($("#cr_date").val()) ;
	//document.getElementById('searchForm').submit();
	table.ajax.reload();
	
}

function editData(id){		
	
	$("#id_edit").val(id);
	$("#status").val(2);

	 $.ajaxSetup({
			async : false
		});
	if(getArmyNoFromJco(id,'personnel_no_edit_JCO','')){
		$("#status6").val($("#statusA").val());		
		$("#army_no6").val($("#personnel_no_edit_JCO").val());	
		$("#rank6").val($("#rank").val());	
		$("#jco_id6").val($("#jco_id").val());
		$("#unit_name6").val($("#unit_name").val());	
		$("#unit_sus_no6").val($("#unit_sus_no").val());
	document.getElementById('updateForm').submit();
	}else{
		return false;
	}
}

 
 function AppViewData(id,jco_id){        
		$("#ap_id").val(id);
	
		 $.ajaxSetup({
				async : false
			});
		if(getArmyNoFromJco(id,'ap_army_no2','')){
		document.getElementById('ap_ViewForm').submit();
		}else{
			return false;
		}	      
	}

  function ViewData(id){	
	
	$("#id2").val(id);
	 $.ajaxSetup({
			async : false
		});
	if(getArmyNoFromJco(id,'army_no2','')){
	document.getElementById('ViewForm').submit();
	}else{
		return false;
	}	 
}  

function View1Data(id,jco_id){        
    $("#idV").val(id);                
    $("#jco_idV").val(jco_id);
    document.getElementById('View1Form').submit();
             
}



  function Reject(id){
   	
   	$("#jco_id3").val(id);
  
   	
  	 $.ajaxSetup({
  			async : false
  		});
  	if(getArmyNoFromJco(id,'personnel_no3','event3')){
  	 	
  		$("#army_no7").val($("#army_no").val()) ;	
  		$("#status7").val($("#statusA").val()) ;		
  		$("#rank7").val($("#rank").val()) ;	
  		$("#jco_id7").val($("#jco_id").val()) ;
  		$("#unit_name7").val($("#unit_name").val()) ;	
  		$("#unit_sus_no7").val($("#unit_sus_no").val()) ;
  	document.getElementById('rejectForm').submit();
  	}else{
  		return false;
  	}
    }
</Script> 
	
<Script>
<!--  bisag v2 260822  (converted to Datatable) -->
	function data(tableName){
		
		 
		jsondata = [];

		var table = $('#'+tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = $(table.column(order[0][0]).header()).attr('id');
		var orderType = order[0][1];
	

		
		var unit_sus_no =	$("#unit_sus_no").val() ;	
		var unit_name = $("#unit_name").val() ;	
		var personnel_no =	$("#army_no").val() ;	
		var rank =	$("#rank").val() ;	
		var status =	$("#statusA").val() ;	
		var icstatus =	$("#icstatus").val() ;	
							
		var cr_by =	$("#cr_by").val();
        var cr_date =	$("#cr_date").val();
	

		///////////serving/////
		if (tableName=="getSearch_census_jco") {
			
			$.post("Getcensus_detail_data_count?"+key+"="+value,{Search:Search,unit_sus_no:unit_sus_no,unit_name:unit_name,
				personnel_no:personnel_no,rank:rank,status:status,icstatus:icstatus,cr_by:cr_by,cr_date:cr_date},function(j) {
				FilteredRecords = j;
				
				
			});
			
			
		
			$.post("Getcensus_detail_data?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
				,unit_sus_no:unit_sus_no,unit_name:unit_name,
				personnel_no:personnel_no,rank:rank,status:status,icstatus:icstatus,cr_by:cr_by,cr_date:cr_date},function(j) {
					console.log(j[0])
			for (var i = 0; i < j.length; i++) {
				
					var sr = i+1;
		         	var data =[sr,j[i].army_no,j[i].rank,j[i].full_name,j[i].date_of_birth,
						j[i].parent_arm,j[i].action];
		                 jsondata.push(data);
		         }
					
			});
		}
		

	}	
	
</Script>

