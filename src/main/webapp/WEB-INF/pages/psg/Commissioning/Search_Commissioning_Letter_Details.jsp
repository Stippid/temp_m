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
	    		<div class="card-header" align="center"><h5> SEARCH/APPROVE COMMISSIONING DETAIL </h5> <h6 class="enter_by"><span style="font-size:12px;color:red;">(To be entered by MISO)</span></h6></div>
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
						                    <label class=" form-control-label">Unit Posted To </label>
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
						                    <label class=" form-control-label"> Parent Arm </label>
						                </div>
						                <div class="col-md-8">

						               <select name="parent_arm" id="parent_arm" class="form-control-sm form-control"  onchange="chgarm();" >
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
						                    <label class=" form-control-label"> Personal No </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off" maxlength="9" onkeyup="return specialcharecter(this)" onkeypress="return AvoidSpace(event)"> 
						                </div>
						            </div>
	              				</div>
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Name </label>
						                </div>
						                <div class="col-md-8">
						                   <input type="text" id="name" name="name" class="form-control autocomplete" autocomplete="off" maxlength="50" onkeypress="return onlyAlphabets(event);" oninput="this.value = this.value.toUpperCase()"> 
						                </div>
						            </div>
	              				</div>	              				
	              			</div>
	              			<div class="col-md-12">	              					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Type of Commission Granted </label>
						                </div>
						                <div class="col-md-8">
						                   <select name="type_of_comm_granted" id="type_of_comm_granted" class="form-control-sm form-control"   >
												<option value="">--Select--</option>
												<c:forEach var="item" items="${getTypeOfCommissionList}" varStatus="num">
												<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
												</c:forEach>
											</select> 
						                </div>
						            </div>
	              				</div>
	             				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"> Date of Commission </label>
						                </div>
						                <div class="col-md-8">
						                <input type="text" name="date_of_commission" id="date_of_commission" maxlength="10" value="" onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" >
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
													<!-- <option value="">-- Select --</option> -->
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
	                 		  <label for="text-input" class=" form-control-label">Date From </label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
	             			 <%--  <input type="date" name="from_date" id="from_date" value="${to_date}" class="form-control-sm form-control" min="1899-01-01" max="${date}" maxlength="10"> --%>
	             			  <input type="text" name="from_date" id="from_date" maxlength="10"  onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);"  max="${date}">
	               		 </div>	
	               		 </div>
	               		 </div>
	               		   
	               		  <div class="col-md-6">
	             		 <div class="row form-group"> 
	               		  <div class="col-md-4">
	                 		  <label for="text-input" class="form-control-label">Date To </label>
	               		 </div>	               		 
	               		 <div class="col-md-8">
	             			
	               		<input type="text" name="to_date" id="to_date" maxlength="10"   onclick="clickclear(this, 'DD/MM/YYYY')" class="form-control" style="width: 85%;display: inline;"
											onfocus="this.style.color='#000000'" onblur="clickrecall(this,'DD/MM/YYYY');validateDate(this.value,this);" onkeyup="clickclear(this, 'DD/MM/YYYY')" 
											onchange="clickrecall(this,'DD/MM/YYYY')" aria-required="true" autocomplete="off" style="color: rgb(0, 0, 0);" max="${date}"  >
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
							<a href="Search_Commissioning_LetterUrl" class="btn btn-success btn-sm" >Clear</a>  
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
			                        <th style="text-align: center;" id="cadet_no"  >Ser No</th>
			                         <th style="text-align: center;"id="cadet_no" > Cadet No </th> 
			                         <th style="text-align: center;"id="personnel_no" > Personal No </th>
			                         <th style="text-align: center;"id="rank" >Rank </th>
			                         <th style="text-align: center;" id="name" >Name </th>
			                         <th style="text-align: center;"id="gender" >Gender </th>
			                         <th style="text-align: center;"id=type_of_comm_granted > Type of Commission</th>
			                         <th style="text-align: center;width: 10%;" id="date_of_commission" > Date of Commission </th> 
			                         <th style="text-align: center;width: 10%;"id="date_of_birth" > Date of Birth </th> 
			                         <th style="text-align: center;" id="parent_arm" >Parent Arm</th>	
			                         <th style="text-align: center;"id="unit_sus_no" > Unit Posted to</th>	
			                        <th style="text-align: center;"id="action" >Action</th>
		                          </tr>                                                        
		                  </thead> 
		                 
		              </table>
		         </div>				  
		</div> 
		</div>



<c:url value="GetSearch_Com_letter" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="unit_sus_no2">
		<input type="hidden" name="unit_sus_no2" id="unit_sus_no2" value="0"/>
		<input type="hidden" name="unit_posted_to2" id="unit_posted_to2" value="0"/>
		<input type="hidden" name="parent_arm1" id="parent_arm1" value="0"/>
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="0"/>
		<input type="hidden" name="name1" id="name1" value="0"/>
		<input type="hidden" name="status1" id="status1" />
		<input type="hidden" name="type_of_comm_granted2" id="type_of_comm_granted2" />
		<input type="hidden" name="date_of_commission1" id="date_of_commission1" value="0"/>
		<input type="hidden" name="frm_dt1" id="frm_dt1"/> 
		<input type="hidden" name="to_dt1" id="to_dt1"/> 
	</form:form> 


<c:url value="Edit_Commissioning_LetterUrl" var="editUrl"/>
<form:form action="${editUrl}" method="post" id="updateForm" name="updateForm" modelAttribute="id2">
	  <input type="hidden" name="id2" id="id2" value="0">
	  <input type="hidden" name="status6" id="status6" value="0">
	  <input type="hidden" name="unit_sus_no6" id="unit_sus_no6" value="0">
	  <input type="hidden" name="unit_posted_to6" id="unit_posted_to6" value="0">
	  <input type="hidden" name="parent_arm6" id="parent_arm6" value="0">
	  <input type="hidden" name="personnel_no6" id="personnel_no6" value="0">
	  <input type="hidden" name="name6" id="name6" value="0">
	  <input type="hidden" name="type_of_comm_granted6" id="type_of_comm_granted6" value="0">
	  <input type="hidden" name="date_of_commission6" id="date_of_commission6" value="0">
	  <input type="hidden" name="frm_dt6" id="frm_dt6" value="0">
	  
	  
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
<c:url value="Delete_Commissioning_Letter" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="deleteForm" name="deleteForm" modelAttribute="id1">
	<input type="hidden" name="id1" id="id1" value="0"/> 
</form:form>

<c:url value="Relegate_Commissioning_Letter" var="deleteUrl" />
<form:form action="${deleteUrl}" method="post" id="relegateForm" name="relegateForm" modelAttribute="idr">
	<input type="hidden" name="idr" id="idr" value="0"/> 
</form:form>	


<c:url value="Approve_Commissioning_Letter" var="approveUrl" />
<form:form action="${approveUrl}" method="post" id="approveForm" name="approveForm" modelAttribute="id3">
	<input type="hidden" name="id3" id="id3" value="0"/> 
	<input type="hidden" name="status" id="status" value="0"/>
	<input type="hidden" name="CheckVal3" id="CheckVal3" value="0"/>
		<input type="hidden" name="modified_by" id="modified_by" />
		<input type="hidden" name="modified_date" id="modified_date" value="0"/>
		<input type="hidden" name="isMns" id="isMns" value="False"/>
</form:form>	
<c:url value="Reject_Commissioning_Letter" var="rejectUrl" />
<form:form action="${rejectUrl}" method="post" id="rejectForm" name="rejectForm" modelAttribute="id4">
	<input type="hidden" name="id4" id="id4" value="0"/> 
	<input type="hidden" name="rej_remarks4" id="rej_remarks4" value="0"/> 
	<input type="hidden" name="isMns" id="isMns" value="False"/> 
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
	
	/* var month = $('#month').val();
	var year = $('#year').val();	
	var present_return_no=$("#present_return_no").val();
	var present_return_dt=$("#present_return_dt").val();
	var last_return_no=$("#last_return_no").val();
	var last_return_dt=$("#last_return_dt").val();
	var observation=$("#observation").val();
	var unit_sus_no=$("#unit_sus_no").val(); */
	
						
	
	var unit_sus_no =	$("#unit_sus_no").val() ;	
	var unit_posted_to = $("#unit_posted_to").val() ;	
	var parent_arm =	$("#parent_arm").val() ;	
	var personnel_no =	$("#personnel_no").val() ;

	var name =	$("#name").val() ;	
	var status =	$("#status").val() ;	
	var type_of_comm_granted =	$("#type_of_comm_granted").val() ;
	var date_of_commission = $("#date_of_commission").val() ;	
	var frm_dt1 =	$("#from_date").val();
	var to_dt1 =	$("#to_date").val();
	var cr_by =	$("#cr_by").val();
	var IsMns=false;
//	alert(cr_by)
	var cr_date =	$("#cr_date").val();
	//alert(cr_date)				


	///////////serving/////
	if (tableName=="getSearch_Lettertbl") {
		
		$.post("GetSearch_Com_letterCount?"+key+"="+value,{Search:Search,unit_sus_no:unit_sus_no,unit_posted_to:unit_posted_to,parent_arm:parent_arm,
			personnel_no:personnel_no,name:name,status:status,type_of_comm_granted:type_of_comm_granted,date_of_commission:date_of_commission,frm_dt1:frm_dt1,to_dt1:to_dt1,cr_by:cr_by,cr_date:cr_date,IsMns:IsMns},function(j) {
			FilteredRecords = j;
		});
		$.post("GetSearch_Com_letterdata?"+key+"="+value,{startPage:startPage,pageLength:pageLength,Search:Search,orderColunm:orderColunm,orderType:orderType
			,unit_sus_no:unit_sus_no,unit_posted_to:unit_posted_to,parent_arm:parent_arm,
			personnel_no:personnel_no,name:name,status:status,type_of_comm_granted:type_of_comm_granted,date_of_commission:date_of_commission,frm_dt1:frm_dt1,to_dt1:to_dt1,cr_by:cr_by,cr_date:cr_date,IsMns:IsMns},function(j) {
				console.log(j[0])
			for (var i = 0; i < j.length; i++) {
				var sr = i+1;
				 // alert(j[i].lf + "------ ")
				jsondata.push([sr,j[i].cadet_no,j[i].personnel_no,j[i].rank,j[i].name,j[i].gender,
					j[i].type_of_comm_granted,j[i].date_of_commission,j[i].date_of_birth,j[i].parent_arm,j[i].unit_sus_no,j[i].action  
				]);
				
			}
		});
	}
	

	
	
	
	
	
	
	
}
</script>
				
<script>
$(document).ready(function() {
	
	
	$.ajaxSetup({
		async : false
	});
	 colAdj("getSearch_Letter");
		jQuery(function($){ 
		                 datepicketDate('from_date');
		                 datepicketDate('to_date');
		                 datepicketDate('cr_date');
		                 
		               //26-01-1994
// 		                 $("#parent_arm").val(0);

		                 
	 var currentDate = new Date();  
	
// 	 $("#to_date").datepicker("setDate",currentDate);
	});
 	
	
	 if('${list.size()}' == ""){
		   $("div#getSearch_Letter").hide();
		}	
// 		$("#UNIT_SUS_NO").VAL('${UNIT_SUS_NO2}');
// 		$("#UNIT_POSTED_TO").VAL('${UNIT_POSTED_TO2}');
// 		$("#PARENT_ARM").VAL('${PARENT_ARM1}');
// 		$("#PERSONNEL_NO").VAL('${PERSONNEL_NO1}');
// 		$("#NAME").VAL('${NAME1}');
// 		$("#TYPE_OF_COMM_GRANTED").VAL('${TYPE_OF_COMM_GRANTED2}');
// 		$("#DATE_OF_COMMission").val('${date_of_commission1}');
		
		var q = '${unit_sus_no2}';		
		if(q != ""){
			$("#unit_sus_no").val(q);
		}
		
		var q7 = '${unit_posted_to2}';		
		if(q7 != ""){
			$("#unit_posted_to").val(q7);
		}
		//26-01-1994
		var q1 = '${parent_arm1}';		
		if(q1!='0' && q1!=''){
			$("#parent_arm").val(q1);
		}
		else{
			 $("#parent_arm").val(0);
		}
		
		var q2 = '${personnel_no1}';		
		if(q2 != ""){
			$("#personnel_no").val(q2);
		}
		
		var q3 = '${name1}';		
		if(q3 != ""){
			$("#name").val(q3);
		}
		var q4 = '${status1}';	
		
		if(q4 != "" ){
			$("#status").val(q4);
			
		}
	
		var q5 = '${type_of_comm_granted2}';		
		if(q5 != "0"){
			$("#type_of_comm_granted").val(q5);
		}
		
		var q6 = '${date_of_commission1}';		
		//26-01-1994
		if(q6 != "" && q6 != "DD/MM/YYYY"){
			$("#date_of_commission").val(ParseDateColumncommission(q6));	
		}	
		var q11 = '${frm_dt1}';
		if(q11 != "" && q11 != "DD/MM/YYYY"){ 
			$("#from_date").val(ParseDateColumncommission(q11));
		}
		
		var q12 = '${to_dt1}';
		if(q12 != "" && q12 != "DD/MM/YYYY"){ 
			$("#to_date").val(ParseDateColumncommission(q12));
		}
		
	    if('${size}' == 0 && '${size}' != ""){
	    	$("#divPrint").show();
		}
	    
	    mockjax1('getSearch_Lettertbl');
		table = dataTable('getSearch_Lettertbl');
		$('#btn-reload').on('click', function(){
			table.ajax.reload();
		});
		$.ajaxSetup({
			async : false
		});
});



function Search(){
	
	
	 var f_date  = $("#from_date").val();
	 f_date1 =f_date.substring(6,10);
	 var t_date  = $("#to_date").val();
	  t_date1 =t_date.substring(6,10);
	//26-01-1994
	  if (f_date != "DD/MM/YYYY" && t_date != "DD/MM/YYYY" && f_date != "" && t_date != "" && t_date1 < f_date1) {
			alert("To Date can not be less than From Date ");
			$("#to_date").focus();
	 		return false;
		}
	
	$("#unit_posted_to2").val($("#unit_posted_to").val()) ;	
	$("#unit_sus_no2").val($("#unit_sus_no").val()) ;	
	$("#parent_arm1").val($("#parent_arm").val()) ;	
	$("#personnel_no1").val($("#personnel_no").val()) ;	
	$("#name1").val($("#name").val()) ;	
	$("#status1").val($("#status").val()) ;	
	$("#type_of_comm_granted2").val($("#type_of_comm_granted").val()) ;
	$("#date_of_commission1").val($("#date_of_commission").val()) ;	
	$("#frm_dt1").val($("#from_date").val());
	$("#to_dt1").val($("#to_date").val());
	table.ajax.reload();
// 	document.getElementById('searchForm').submit();
}

function editData(id){
	$("#id2").val(id);
	
	$("#unit_posted_to6").val($("#unit_posted_to").val()) ;	
	$("#unit_sus_no6").val($("#unit_sus_no").val()) ;	
	$("#parent_arm6").val($("#parent_arm").val()) ;	
	$("#personnel_no6").val($("#personnel_no").val()) ;	
	$("#name6").val($("#name").val()) ;	
	$("#status6").val($("#status").val()) ;	
	$("#type_of_comm_granted6").val($("#type_of_comm_granted").val()) ;
	$("#date_of_commission6").val($("#date_of_commission").val()) ;	
	$("#frm_dt6").val($("#from_date").val());
	$("#to_dt6").val($("#to_date").val());
	
	document.getElementById('updateForm').submit();		 
}


function viewData(id){
	 	
	$("#id5").val(id);
	$("#unit_posted_to5").val($("#unit_posted_to").val()) ;	
	$("#unit_sus_no5").val($("#unit_sus_no").val()) ;	
	$("#parent_arm5").val($("#parent_arm").val()) ;	
	$("#personnel_no5").val($("#personnel_no").val()) ;	
	$("#name5").val($("#name").val()) ;	
	$("#status5").val($("#status").val()) ;	
	$("#type_of_comm_granted5").val($("#type_of_comm_granted").val()) ;
	$("#date_of_commission5").val($("#date_of_commission").val()) ;
	$("#frm_dt5").val($("#from_date").val());
	$("#to_dt5").val($("#to_date").val());
	 
	document.getElementById('viewForm').submit();		 
}

function deleteData(id){
	$("#id1").val(id);
	document.getElementById('deleteForm').submit();
}  
function relegateData(id){
	$("#idr").val(id);
	document.getElementById('relegateForm').submit();
}

function Approved(id){
	$("#id3").val(id);
	//$("#CheckVal3").val($("#CheckVal3").val());
	
	document.getElementById('approveForm').submit();
	
}

function Reject(id){
	
 	 /* if ($("#reject_remarks").val().trim() == "" || $("#reject_remarks").val().trim() == "DD/MM/YYYY") {
			alert("Please Enter Reject Remarks ");
			$("#reject_remarks").focus();
			return false;
		} 
	 else{ */ 
		$("#id4").val(id);
		$("#rej_remarks4").val($("#reject_remarks").val().toUpperCase()); 
		document.getElementById('rejectForm').submit();
	//}
 
}  

jQuery(function($){ 
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
/* 
			$.post("Approve_Commissioning_Letter?"+key+"="+value, {a:a,status:"1"}).done(function(j) {
			alert(j);
			Search(); */
			
			$("#CheckVal3").val(a);
			
			document.getElementById('approveForm').submit();
			
		
	}	
}
function setRejectStatus(){
	
	findselected();
	
	var a = $("#CheckVal").val();

	if(a == ""){
		alert("Please Select the Data for Reject"); 
	}else{

			$.post("Approve_CommissioningData?"+key+"="+value, {a:a,status:"3"}).done(function(j) {
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
