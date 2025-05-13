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


<form:form name="Search_Com_letter" id="Search_Com_letter" action="Search_Com_letterAction" method="post" class="form-horizontal" commandName="Search_Com_letterCMD"> 
	<div class="animated fadeIn">
	    
	    		<div class="card">
	    		<div class="card-header" align="center"><h5> SEARCH/APPROVE CAPTURE COMD AND CONT INST </h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
	          			<div class="card-body card-block">
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Unit SUS No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)"  onkeyup="return specialcharecter(this)"> 
						                   
						                </div>
						            </div>
	              				</div>
	              				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Unit Name  </label>
						                </div>
						                <div class="col-md-8">
						                  <input type="text" id="unit_posted_to" name="unit_posted_to" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeyup="return specialcharecter(this)">
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
							<a href="search_comd_and_contUrl" class="btn btn-success btn-sm" >Clear</a>  
		              		<input type="button" class="btn btn-info btn-sm" onclick="Search();" value="Search"> 
		              		<!-- <a href="Search_Commissioning_LetterUrl" class="btn btn-danger btn-sm" >Cancel</a>      -->
			            </div> 		
	        	</div>
			
	</div>
	</form:form>
	<div class="datatablediv">
	<div class="col-md-12" id="getSearch_Letter_a" style="display: block;"> 	 
			<div  class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
		           <table id="getSearch_Lettertbl" class="table no-margin table-striped  table-hover  table-bordered ">
		                 <thead>
		                       <tr>
			                        <th style="text-align: center;" id="ser_no"  >Ser No</th>
			                         <th style="text-align: center;"id="auth_letterNo" > Auth Letter No </th> 
			                         <th style="text-align: center;"id="unitName" > Unit Name </th>
			                         <th style="text-align: center;"id="loc" >Loc </th>
			                         <th style="text-align: center;" id="ops" >OPS</th>
			                         <th style="text-align: center;"id="isName" >Is Name </th>
			                         <th style="text-align: center;"id="mil" >MIL </th>
			                           <th style="text-align: center;"id="techCont" >Tech Cont</th>
			                             <th style="text-align: center;"id="discp" >Discp</th>
			                              <th style="text-align: center;"id="localAdm" >Local Adm</th>
			                               <th style="text-align: center;"id="statuslbl" >Status</th>
			                        <th style="text-align: center;"id="action" >Action</th>
		                          </tr>                                                        
		                  </thead> 
		                 
		              </table>
		         </div>				  
		</div> 
		</div>


	<div class="modal-open modal" id="getComdAndContInstDtl"
		style="overflow-y: auto;">
		<div class="modal-dialog" style="max-width: 900px;">
			<div class="modal-content">
				<div id="getRODetailsDiv">
					<div class="modal-header">
						<table style="width: 100%;">
							<tr style="height: 30px;">
								<td align="left" width="33.33%"><img
									src="js/miso/images/indianarmy_smrm5aaa.jpg"
									style="height: 50px;"></td>
								<td align="center" width="33.33%">
									<h5>Comd And Cont Inst Dtl</h5>
								</td>
								<td align="right" width="33.33%"><img
									src="js/miso/images/dgis-logo.png"
									style="max-width: 155px; height: 50px;"></td>
							</tr>
						</table>
					</div>
					<div class="modal-body">
						<div>
							<table style="width: 100%;">
								<tr style="height: 30px;">
									<td width="20%"><b>Auth Letter No </b></td>
									<td width="30%">: <span id="vletter_no"></span></td>
									<td width="20%"><b>Auth Letter Date</b></td>
									<td width="30%">: <span id="vletter_date"></span></td>
								</tr>
								<tr style="height: 30px;">
								<td width="20%"><b>Unit Name</b></td>
									<td width="30%">: <span id="vunit_name"></span></td>
									<td width="20%"><b>Unit Sus No</b></td>
									<td width="30%">: <span id="vunit_susNo"></span></td>
									
								</tr>
									<tr style="height: 30px;">
									<td width="20%"><b>Loc</b></td>
									<td width="30%">: <span id="vloc"></span></td>
									<td width="20%"><b>Nrs</b></td>
									<td width="30%">: <span id="vnrs"></span></td>
									
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>OPS name</b></td>
									<td width="30%">: <span id="vopsName"></span></td>
									<td width="20%"><b>OPS Sus No</b></td>
									<td width="30%">: <span id="vopsSusNo"></span></td>
								</tr>
							
									<tr style="height: 30px;">
									<td width="20%"><b>IS name</b></td>
									<td width="30%">: <span id="visName"></span></td>
									<td width="20%"><b>Is Sus No</b></td>
									<td width="30%">: <span id="visSusNo"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Mil name</b></td>
									<td width="30%">: <span id="vmilName"></span></td>
									<td width="20%"><b>Mil Sus No</b></td>
									<td width="30%">: <span id="vmilSusNo"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Tech Cont name</b></td>
									<td width="30%">: <span id="vtechName"></span></td>
									<td width="20%"><b>Tech Cont Sus No</b></td>
									<td width="30%">: <span id="vtechSusNo"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Discp name</b></td>
									<td width="30%">: <span id="vdiscpName"></span></td>
									<td width="20%"><b>Discp Sus No</b></td>
									<td width="30%">: <span id="vdiscpSusNo"></span></td>
								</tr>
								<tr style="height: 30px;">
									<td width="20%"><b>Local Adm name</b></td>
									<td width="30%">: <span id="vlocalAdmName"></span></td>
									<td width="20%"><b>Local Adm Sus No</b></td>
									<td width="30%">: <span id="vlocalAdmSusNo"></span></td>
								</tr>
							<tr style="height: 30px;">
									<td width="20%"><b>From Date</b></td>
									<td width="30%">: <span id="vfdate"></span></td>
									<td width="20%"><b>To Date</b></td>
									<td width="30%">: <span id="vtodate"></span></td>
								</tr>
						
							</table>
						</div>


					</div>
				</div>
				<div class="modal-footer" align="center">
					<!-- <input type="button" class="btn btn-primary btn-sm" value="Print"
						onclick="printDiv1('getRODetailsDiv')"> -->
					<button type="button" class="btn btn-danger btn-sm"
						onclick="closeModal1();">Close</button>
				</div>
			</div>
		</div>
	</div>

 


<c:url value="EditComdAndContInstUrl" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
	  <input type="hidden" name="status6" id="status6" value="0">
	  <input type="hidden" name="unit_sus_no6" id="unit_sus_no6" value="0"> 
	  
</form:form>
<c:url value="view_Commissioning_LetterUrl" var="viewUrl"/>
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="id5">
	  <input type="hidden" name="id5" id="id5" value="0">
	  <input type="hidden" name="status5" id="status5" value="0">
	  <input type="hidden" name="unit_sus_no5" id="unit_sus_no5" value="0">
	  <input type="hidden" name="unit_posted_to5" id="unit_posted_to5" value="0">
	  <input type="hidden" name="parent_arm5" id="parent_arm5" value="0">
	  <input type="hidden" name="personnel_no5" id="personnel_no5" value="0">
	  <input type="hidden" name="name5" id="name5" value="0">
	  <input type="hidden" name="type_of_comm_granted5" id="type_of_comm_granted5" value="0">
	  <input type="hidden" name="date_of_commission5" id="date_of_commission5" value="0">
	  <input type="hidden" name="frm_dt5" id="frm_dt5" value="0">
	   <input type="hidden" name="to_dt5" id="to_dt5" value="0">
	   
</form:form>


	


<c:url value="ApproveComdAndCont" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
	<input type="hidden" name="status1" id="status1" value="0"/>
	<input type="hidden" name="sus_no1" id="sus_no1" value="0"/>		
</form:form>	
<c:url value="rejectComdAndContInstAction" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0"/> 
	<input type="hidden" name="rej_remarks4" id="rej_remarks4" value="0"/> 	
</form:form>

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
						
	
	var unit_sus_no =	$("#unit_sus_no").val() ;
	var status = $("#status").val() ;
	if (tableName=="getSearch_Lettertbl") {
		
		 $.post("GetSearch_Comd_and_cont_count?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
				,unit_sus_no:unit_sus_no,status:status},function(j) {
			FilteredRecords = j;
		});
		$.post("GetSearch_Comd_and_cont_list?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			,unit_sus_no:unit_sus_no,status:status},function(j) {
				for (var i = 0; i < j.length; i++) {
					var sr = i+1;			
				jsondata.push([sr,j[i].auth_letter_no,j[i].unit_name,j[i].loc_code,j[i].ops,j[i].is_name,
					j[i].mil,j[i].techcont,j[i].discp,j[i].local_adm,j[i].status,j[i].action  
				]);
				
				}
		});
	}	
}
</script>
				
<script>
$(document).ready(function() {                
      
	
	    
	    mockjax1('getSearch_Lettertbl');
		table = dataTable('getSearch_Lettertbl');
		$('#btn-reload').on('click', function(){
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		});
});




	function Search() {
		if ($("#status").val() == 3) {
		    $("#statuslbl").text('Reject Remarks');
		}

		table.ajax.reload();
	}

	function editData(id) {
		$("#id2").val(id);

		$("#unit_posted_to6").val($("#unit_posted_to").val());
		$("#unit_sus_no6").val($("#unit_sus_no").val());
		$("#parent_arm6").val($("#parent_arm").val());
		$("#personnel_no6").val($("#personnel_no").val());
		$("#name6").val($("#name").val());
		$("#status6").val($("#status").val());
		$("#type_of_comm_granted6").val($("#type_of_comm_granted").val());
		$("#date_of_commission6").val($("#date_of_commission").val());
		$("#frm_dt6").val($("#from_date").val());
		$("#to_dt6").val($("#to_date").val());

		document.getElementById('updateForm').submit();
	}

	function viewData(id) {

		$("#id5").val(id);
		$("#unit_posted_to5").val($("#unit_posted_to").val());
		$("#unit_sus_no5").val($("#unit_sus_no").val());
		$("#parent_arm5").val($("#parent_arm").val());
		$("#personnel_no5").val($("#personnel_no").val());
		$("#name5").val($("#name").val());
		$("#status5").val($("#status").val());
		$("#type_of_comm_granted5").val($("#type_of_comm_granted").val());
		$("#date_of_commission5").val($("#date_of_commission").val());
		$("#frm_dt5").val($("#from_date").val());
		$("#to_dt5").val($("#to_date").val());

		document.getElementById('viewForm').submit();
	}

	function Approved(id) {
		$("#id1").val(id);
		$("#status1").val($("#status").val());
		$("#sus_no1").val($("#unit_sus_no").val());
		document.getElementById('approveForm').submit();

	}

	function Reject(id) {
		$("#id4").val(id);
		$("#rej_remarks4").val($("#reject_remarks").val().toUpperCase());
		document.getElementById('rejectForm').submit();

	}

	jQuery(function($) {
		datepicketDate('date_of_commission');
		datepicketDate('from_date');
		datepicketDate('to_date');
	});
</script>

<script>


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
		        	  document.getElementById("unit_sus_no").value="";
		        	  document.getElementById("unit_posted_to").value="";
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
				        	  document.getElementById("unit_sus_no").value="";
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
	
	 $("input#personnel_no").keyup(function(){
			
			var personel_no = this.value;
				 var susNoAuto=$("#personnel_no");
				  susNoAuto.autocomplete({
				      source: function( request, response ) {
				        $.ajax({
				        	type: 'POST',
					    	url: "getpersonnel_no?"+key+"="+value,
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
	 function onlyAlphabets(e, t) {
		    return (e.charCode > 64 && e.charCode < 91) || (e.charCode > 96 && e.charCode < 123) || e.charCode == 32;   
	}
	 
	 
		function closeModal1() {
			var modal = document.getElementById('getComdAndContInstDtl');
			modal.style.display = "none";
		}

		function viewComdAndContDetails(id) {
			var modal = document.getElementById('getComdAndContInstDtl');
			modal.style.display = "block";

			jQuery.ajax({
				type : 'POST',
				url : "getComdAndContInstDetails?" + key + "=" + value,
				data : {
					id : id
				},
				success : function(data) {
					if (data.length != 0) {
						$("#vletter_no").text(data[0].auth_letter_no);
						$("#vletter_date").text(data[0].auth_letter_date);
						$("#vloc").text(data[0].loc);
						$("#vnrs").text(data[0].nrs);
						$("#vunit_susNo").text(data[0].unit_sus_no);
						$("#vunit_name").text(data[0].unit_name);
						$("#vopsSusNo").text(data[0].ops_sus_no);
						$("#vopsName").text(data[0].ops_name);
						$("#visSusNo").text(data[0].is_sus_no);
						$("#visName").text(data[0].is_name);
						$("#vmilSusNo").text(data[0].mil_sus_no);
						$("#vmilName").text(data[0].mil_name);
						$("#vtechSusNo").text(data[0].techcont_sus_no);
						$("#vtechName").text(data[0].techcont_name);
						$("#vdiscpSusNo").text(data[0].discp_sus_no);
						$("#vdiscpName").text(data[0].discp_name);			
						$("#vlocalAdmSusNo").text(data[0].local_adm_sus_no);
						$("#vlocalAdmName").text(data[0].local_adm_name);
						$("#vfdate").text(data[0].from_date);
						$("#vtodate").text(data[0].to_date);					

					}
				}
			});
		}

	 
</script>


