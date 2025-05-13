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
	    	<div class="col-md-12" align="center">
	    		<div class="card">
	    		<div class="card-header"><h5> View JCO/OR Records </h5> <h6 class="enter_by"><span style="font-size:12px;color:red;"></span></h6></div>
	          			<div class="card-body card-block">
	            	
	            	<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                  <label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label>   
						                 <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" 
						                 maxlength="8" 
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
						                  <input type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)" style="display: none">				
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
						                   <input type="text" id="army_no" name="army_no" class="form-control autocomplete" autocomplete="off"  maxlength="12" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Name </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="full_name" name="full_name" class="form-control autocomplete" maxlength="50" autocomplete="off" > 
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	              					
	              				
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> RANK </label>
						                </div>
						                <div class="col-md-8">
						                    <select name="rank" id="rank" class="form-control  form-control"   >
												<option value="0">--Select--</option>
												<c:forEach var="item" items="${getRankjcoList}" varStatus="num">
													<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>	
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Year of Commission </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="year_of_comm" name="year_of_comm" class="form-control autocomplete" maxlength = "4" onkeypress="return isNumber(event)" 
						                   onclick="return AvoidSpace(event)" autocomplete="off" onkeyup="return specialcharecter(this)"> 
						                </div>
						            </div>
	              				</div>              				
	              			</div>
	              			<div class="col-md-12">	
	              			<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Year of Birth </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="year_of_dob" name="year_of_dob" class="form-control autocomplete"  maxlength = "4" onkeypress="return isNumber(event)" 
						                   onclick="return AvoidSpace(event)" autocomplete="off" onkeyup="return specialcharecter(this)" > 
						                </div>
						            </div>
	              				</div>              					
	              			
						            <div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Parent Arm </label>
						                </div>
						                <div class="col-md-8">
						                <select name="parent_arm" id="parent_arm" class="form-control"  >
						                        <option value="0">--Select--</option>
												<c:forEach var="item" items="${getParentArmList}" varStatus="num">
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
						                    <label class=" form-control-label"> Status </label>
						                </div>
						                <div class="col-md-8">
						                    <select name="comm_status" id="comm_status" class="form-control  form-control"   >
												<!-- <option value="0">--Select--</option> -->
												<option value="1">Serving</option>
												<option value="4">Non Effective</option>
												
											</select> 
						                </div>
						            </div>
	              				</div>	
	              				      				
	              			</div>             					              				
	              			</div>
	              				              			              				              			
            			</div>
									
						<div class="card-footer" align="center" class="form-control">
							<a href="View_JCODataUrl" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" id="btn_summ" class="btn btn-info btn-sm" onclick="Search();" value="Search"> 
		              		<a href="View_JCODataUrl" class="btn btn-danger btn-sm" >Cancel</a>     
			            </div> 		
	        	</div>
			
	<input type=hidden id="unit_sus" name="unit_sus">
 <%--  <div class="col-md-12" id="divPrint" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_census" class="table no-margin table-striped  table-hover  table-bordered report_print_scroll">
		                 <thead>
		                       <tr>
		                       
		                       		  <th style="text-align: center;">Ser No</th>
			                         <!--  <th style="text-align: center;"> Cadet No </th>	 -->
			                          <th style="text-align: center;"> Army No </th>		                        
			                          <th style="text-align: center;"> Rank </th> 		                          
			                          <th style="text-align: center;"> Name </th> 
			                          <th style="text-align: center;"> Unit Name </th> 	
			                          <!-- <th style="text-align: center;"> Parent Arm </th>  -->	                         	
			                          <th style="text-align: center;">Action</th> 
		                       
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
									<td style="font-size: 15px;text-align: center ;">${num.index+1}</td> 
									<td style="font-size: 15px;">${item[0]}</td>	
									<td style="font-size: 15px;">${item[1]}</td> 	
									<td style="font-size: 15px;">${item[2]}</td>									
									<td style="font-size: 15px;">${item[3]}</td> 	
									<td style="font-size: 15px;">${item[4]}</td>	
								<td style="font-size: 15px;">${item[5]}</td>  					
									<td style="font-size: 15px;text-align: center;" >${item[5]} </td> 
									
									
									</tr>
								</c:forEach>
		                     </tbody>
		                 </table>
		          </div>				  
		</div>   --%>
		
		<div class="datatablediv">
	<div class="col-md-12" id="getSearch_Letter_a" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_viewrecordtbl" class="table no-margin table-striped  table-hover  table-bordered ">
		                 <thead>
		                       <tr>
			                       <th style="text-align: center;">Ser No</th>
			                         <!--  <th style="text-align: center;"> Cadet No </th>	 -->
			                          <th style="text-align: center;"> Army No </th>		                        
			                          <th style="text-align: center;"> Rank </th> 		                          
			                          <th style="text-align: center;"> Name </th> 
			                          <th style="text-align: center;"> Unit Name </th> 	
			                          <!-- <th style="text-align: center;"> Parent Arm </th>  -->	                         	
			                          <th style="text-align: center;">Action</th> 
		                          </tr>                                                        
		                  </thead> 
		                 
		              </table>
		         </div>				  
		</div> 
		</div>
		</div>
		
		</div>



<c:url value="GetSearch_JCOData_view" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="army_no1">
		<input type="hidden" name="army_no1" id="army_no1" value="0"/>		
		<input type="hidden" name="rank1" id="rank1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="name1" id="name1"  />
		<input type="hidden" name="year_of_comm1" id="year_of_comm1"  />
		<input type="hidden" name="year_of_dob1" id="year_of_dob1"  />
		<input type="hidden" name="p_arm1" id="p_arm1"  />
		<input type="hidden" name="comm_status1" id="comm_status1"  />
		
	</form:form>  


 <c:url value="view_JCOUrl" var="ViewUrl"/>
<form:form action="${ViewUrl}" method="post" id="View1Form" name="View1Form" modelAttribute="idV">
	  <input type="hidden" name="idV" id="idV" value="0">
	   <input type="hidden" name="jco_idV" id="jco_idV" value="0">
	    <input type="hidden" name="sus_noV" id="sus_noV" value="0">
	    <input type="hidden" name="comm_statusV" id="comm_statusV" value="0">
	  
</form:form> 


<Script>

$("input#army_no").keypress(function(){
	 
	var personel_no = this.value;
	
		 var susNoAuto=$("#army_no");
		  susNoAuto.autocomplete({
		      source: function( request, response ) {
		        $.ajax({
		        	type: 'POST',
			    	url: "getpersonnel_noListApproved_JCO?"+key+"="+value, data: {personel_no:personel_no},			    
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

$("#unit_sus_no")
.keyup(
		function() {
			var sus_no = this.value;
			var susNoAuto = $("#unit_sus_no");

			susNoAuto
					.autocomplete({
						source : function(request, response) {
							$
									.ajax({
										type : 'POST',
										url : "getTargetSUSNoList?"
												+ key + "=" + value,
										data : {
											sus_no : sus_no
										},
										success : function(data) {
											var susval = [];
											var length = data.length - 1;
											var enc = data[length]
													.substring(0,
															16);
											for (var i = 0; i < data.length; i++) {
												susval.push(dec(
														enc,
														data[i]));
											}
											var dataCountry1 = susval
													.join("|");
											var myResponse = [];
											var autoTextVal = susNoAuto
													.val();
											$
													.each(
															dataCountry1
																	.toString()
																	.split(
																			"|"),
															function(
																	i,
																	e) {
																var newE = e
																		.substring(
																				0,
																				autoTextVal.length);
																if (e
																		.toLowerCase()
																		.includes(
																				autoTextVal
																						.toLowerCase())) {
																	myResponse
																			.push(e);
																}
															});
											response(myResponse);
										}
									});
						},
						minLength : 1,
						autoFill : true,
						change : function(event, ui) {
							if (ui.item) {
								return true;
							} else {
								alert("Please Enter Approved Unit SUS No.");
								document
										.getElementById("unit_name").value = "";
								susNoAuto.val("");
								susNoAuto.focus();
								return false;
							}
						},
						select : function(event, ui) {
							var sus_no = ui.item.value;
							$.post(
									"getTargetUnitNameList?" + key
											+ "=" + value, {
										sus_no : sus_no
									}, function(j) {

									}).done(
									function(j) {
										var length = j.length - 1;
										var enc = j[length]
												.substring(0, 16);
										$("#unit_name").val(
												dec(enc, j[0]));

									}).fail(
									function(xhr, textStatus,
											errorThrown) {
									});
						}
					});
		});

//unit name
$("input#unit_name").keypress(function() {
var unit_name = this.value;
var susNoAuto = $("#unit_name");
susNoAuto.autocomplete({
source : function(request, response) {
	$.ajax({
		type : 'POST',
		url : "getTargetUnitsNameActiveList?" + key + "=" + value,
		data : {
			unit_name : unit_name
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
		alert("Please Enter Approved Unit Name.");
		document.getElementById("unit_name").value = "";
		susNoAuto.val("");
		susNoAuto.focus();
		return false;
	}
},
select : function(event, ui) {
	var unit_name = ui.item.value;
	$.post("getTargetSUSFromUNITNAME?" + key + "=" + value, {
		target_unit_name : unit_name
	}, function(data) {
	}).done(function(data) {
		var length = data.length - 1;
		var enc = data[length].substring(0, 16);
		$("#unit_sus_no").val(dec(enc, data[0]));
	}).fail(function(xhr, textStatus, errorThrown) {
	});
}
});
});

 $(document).ready(function() {
	
	colAdj("getSearch_census");
	 
	 if('${list.size()}' == ""){
		   $("div#getSearch_census").hide();
		}	
	 var r =('${roleAccess}');
		
		
		if( r == "Unit"){
			
			 $("#unit_sus_no_hid").show();
			 $("#unit_name_l").show();
			 $("#unit_name").val($("#unit_name_l").text());
			 $("#unit_sus_no").val($("#unit_sus_no_hid").text().trim());
			 
		}
		else  if(r == "MISO"){
			
			 $("input#unit_sus_no").show();		 
			 $("input#unit_name").show();
			
		}
	 /*  $("#personnel_no").val('${personnel_no1}');		
		$("#rank").val('${rank1}');  
		
		if('${status1}'!=0){
			$("#statusA").val('${status1}');
		}
		
		if('${personnel_no1}'!=""){
			$("#personnel_no").val('${personnel_no1}');
		}
		
		if('${rank1}'!=0){
			$("#rank").val('${rank1}');
		} */
		
		
	      var q3 = '${unit_name1}';		
			if(q3 != ""){
				$("#unit_name").val(q3);
			}
			var q5 = '${unit_sus_no1}';		
			if(q5 != ""){
				$("#unit_sus_no").val(q5);
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
	    if('${size}' == 0 && '${size}' != ""){
	    	$("#divPrint").show();
		}
	    
	    
    var q3 = '${name1}';		
	if(q3 != ""){
		$("#full_name").val(q3);
	}
	var q5 = '${year_of_comm1}';		
	if(q5 != ""){
		$("#year_of_comm").val(q5);
	}

	var q2 = '${year_of_dob1}';
	if(q2 != ""){
		$("#year_of_dob").val(q2);
	}
	var q6 = '${p_arm1}';
	if(q6 != ""){
		$("#parent_arm").val(q6);
	}
	var q7 = '${comm_status1}';
	if(q7 != ""){
		$("#comm_status").val(q7);
	}
	  /* mockjax1('getSearch_viewrecordtbl');
		table = dataTable('getSearch_viewrecordtbl');
		$('#btn-reload').on('click', function(){
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		}); */ 
	/* mockjax1('getSearch_viewrecordtbl');
	table = dataTable('getSearch_viewrecordtbl');
	$('#btn-reload').on('click', function(){
		table.ajax.reload();
	}); 
	$.ajaxSetup({
		async : false
	});*/
}); 

 /* function Search(){
		
		$("#army_no1").val($("#army_no").val()) ;	
		$("#status1").val($("#statusA").val()) ;		
		$("#rank1").val($("#rank").val()) ;	
		$("#jco_id").val($("#jco_id").val()) ;
		$("#unit_name1").val($("#unit_name").val()) ;	
		$("#unit_sus_no1").val($("#unit_sus_no").val()) ;
		 	
		 	
			$("#p_arm1").val($("#parent_arm").val()) ;		
			$("#name1").val($("#full_name").val()) ;	
			$("#year_of_comm1").val($("#year_of_comm").val()) ;
			$("#year_of_dob1").val($("#year_of_dob").val()) ;
			
			$("#comm_status1").val($("#comm_status").val()) ;
				
		document.getElementById('searchForm').submit();
		
	} */
function Search(){
		
		table.ajax.reload();  
		reloadtable =true;
		
	}


function View1Data(id,jco_id,sus_no,comm_status){    
    $("#idV").val(id);                
    $("#jco_idV").val(jco_id);
    $("#sus_noV").val(sus_no);
    $("#comm_statusV").val(comm_status);
    document.getElementById('View1Form').submit();
             
} 

</Script>


<script type="text/javascript">
////////////////////data table////////////

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
	
	/* var month = $('#month').val();
	var year = $('#year').val();	
	var present_return_no=$("#present_return_no").val();
	var present_return_dt=$("#present_return_dt").val();
	var last_return_no=$("#last_return_no").val();
	var last_return_dt=$("#last_return_dt").val();
	var observation=$("#observation").val();
	var unit_sus_no=$("#unit_sus_no").val(); */
	
			
	
	var unit_name = $("#unit_name").val();
	var unit_sus_no = $("#unit_sus_no").val();
	var army_no = $("#army_no").val();
	var full_name = $("#full_name").val();
	var rank = $("#rank").val();
	var year_of_comm = $("#year_of_comm").val();
	var year_of_dob = $("#year_of_dob").val();
	var parent_arm = $("#parent_arm").val();
	var statusA = $("#comm_status").val();			
	
	
	

	
	
	
	
	///////////serving/////
	if (tableName=="getSearch_viewrecordtbl") {
		
		$.post("GetSearch_JCOrecordCount?"+key+"="+value,{Search:Search,unit_sus_no:unit_sus_no,unit_name:unit_name,army_no:army_no,
			full_name:full_name,rank:rank,year_of_comm:year_of_comm,year_of_dob:year_of_dob,parent_arm:parent_arm,statusA:statusA},function(j) {
			FilteredRecords = j;
		});
		$.post("GetSearch_JCOrecorddata?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			,unit_sus_no:unit_sus_no,unit_name:unit_name,army_no:army_no,
			full_name:full_name,rank:rank,year_of_comm:year_of_comm,year_of_dob:year_of_dob,parent_arm:parent_arm,statusA:statusA},function(j) {
				console.log(j[0])
			for (var i = 0; i < j.length; i++) {
				var sr = i+1;
				 // alert(j[i].lf + "------ ")
				jsondata.push([sr,j[i].army_no,j[i].rank,j[i].full_name,
					j[i].unit_name,j[i].action  
				]);
				
			}
		});
	}
	

	
	
	
	
	
	
	
}
</script> 

<script>
    $(document).ready(function () {
        let tableInitialized = false; // Track if Table 1 is initialized
        let table; // Declare table variables globally for reuse

        // Button to show Summary Table
        $('#btn_summ').on('click', function () {

            if (!tableInitialized) {
                // Initialize mockjax1 and dataTable for Table 2 only on first click
                mockjax1('getSearch_viewrecordtbl');
				table = dataTable('getSearch_viewrecordtbl');
				tableInitialized = true; // Mark Table 2 as initialized
            } else {
                table.ajax.reload(); // Reload Table 2 data if already initialized
            }
        });
    });
</script>