<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<!-- <link rel="stylesheet" href="js/assets/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="js/layout/css/animation.css">
<!-- <link rel="stylesheet" href="js/assets/scss/style.css">  -->
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>



<script>


function Cancel(){
  	
	$("#personnel_no1").val($("#personnel_noV").val()) ;
	$("#status1").val($("#statusV").val()) ;
	$("#rank1").val($("#rankV").val()) ;
	$("#comm_id").val($("#comm_idV").val()) ;
	$("#unit_name1").val($("#unit_nameV").val()) ;
	$("#unit_sus_no1").val($("#unit_sus_noV").val()) ;
	$("#icstatus").val($("#icstatusV").val()) ;
	 
	document.getElementById('searchForm').submit();
}

 


</script>
<c:url value="GetSearch_UpdateOfficerData" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="personnel_no1">
		<input type="hidden" name="personnel_no1" id="personnel_no1" value="0"/>		
		<input type="hidden" name="rank1" id="rank1" value="0"/>
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="unit_name1" id="unit_name1"  />
		<input type="hidden" name="unit_sus_no1" id="unit_sus_no1"  />
		<input type="hidden" name="icstatus" id="icstatus"  />
		<input type="hidden" name="cr_by1" id="cr_by1"   />
        <input type="hidden" name="cr_date1" id="cr_date1"   />
	</form:form> 
	
	
<div class="container-fluid" align="center">
<div class="animated fadeIn">
		<div class="container-fluid" align="center">
			<div class="card">
				<div class="card-header">
						<h5>
							<b>ARMY COURSE</b><br>
						</h5>
					</div>
					<div class="card-body card-block">
								<div class="col-md-12" style="padding-top: 5px; padding-left: 250px;">
									<div class="col-md-6">
										<div class="row form-group">
											<div class="col-md-4">
												<label class=" form-control-label"><strong
													style="color: red;">* </strong>Personal No</label>
											</div>
											<div class="col-md-8">
												<input type="text" id="personnel_no" name="personnel_no" class="form-control autocomplete" autocomplete="off"  > 						                   
							               		 						                   
							                  </div>
										</div>
									</div>

									
								</div> 
								
						<table id="army_course_table" class="table-bordered " style="margin-top:10px; width: -webkit-fill-available;">
								<thead style=" color: white; text-align: center;">
									<tr>
										<th style="width: 2%;">Sr No</th>
										<th style="width: 10%;">Authority</th>
										<th style="width: 10%;">Date of Authority</th>
										<th style="width: 10%;">Course Name</th> 
										<th style="width: 10%;">Course Name Abbreviation</th> 
										<th style="width: 10%;">Course Institute</th> 
										<th style="width: 10%;">Div/Grade</th>
										<th style="width: 10%;">Course Type</th>
										<th style="width: 10%;">Start Date</th>
										<th style="width: 10%;">Date of Completion</th>
										<th style="width: 10%;">Updated By</th>
										<th style="width: 10%;">Updated Date</th>
										<th style="width: 10%;">Action</th>
								   </tr>
								</thead>
							   <tbody data-spy="scroll" id="army_coursetbody" style="min-height:46px; max-height:650px; text-align: center;">
									
							   </tbody> 
									</table>
							</div>
						<!-- </div> -->
					</div>
				</div>
			</div>
		</div>
	
  <form:form name="Approve_armycourse_Data" id="Approve_armycourse_Data" action="Approve_armycourse_DataAction" method="post" class="form-horizontal" >
	<div class="animated fadeIn">
		<div class="card">
		          <div class="panel-group" id="accordion">
				<div class="panel panel-default">
				<input type="hidden" id="comm_id" name="comm_id" class="form-control autocomplete" autocomplete="off"  >
				   <div class="col-md-12" align="center" >	              					
						    <p><input type="checkbox" id="myCheck" onclick="myFunction();"/> I have checked all the tabs and certify that all the information is correct. </p> 
					</div>
					<div class="col-md-12" id = "submit_a" align="center" style="display:none">	              					
						     <input type="submit" class="btn btn-primary btn-sm" value="Approve">
				   </div>
			   </div>
			</div>
	    </div>
   </div>
</form:form>
<script type="text/javascript">
$(document).ready(function() {
	$('#comm_id').val('${comm_id}');
	
	$('#personnel_no').val('${personnel_no}');
	get_army_course_details();
});
function get_army_course_details(){
	cancel_status=0;
	var comm_id=$('#comm_id').val();
	var i=0;
	 $.post('getHisArmyCourseData?' + key + "=" + value, {comm_id:comm_id,cancel_status:cancel_status}, function(j){
			var v=j.length;
			$('#army_coursetbody').empty(); 
			 if(v!=0){
	              
					for(i;i<v;i++){
			     		cor=i+1;
			     		
			     		var dst=convertDateFormate(j[i].start_date);
			     		var doc=convertDateFormate(j[i].date_of_completion);
			     		var dauth=convertDateFormate(j[i].date_of_authority);
			     		var dmod=convertDateFormate(j[i].modify_on);
			     		var div =j[i].div;
			     		var course_type =j[i].course_type;
			     		
			     		if(j[i].div=="OTHERS"){
			     			div=j[i].ar_course_div_ot;
			     		}
			     		if(j[i].course_type=="OTHERS"){
			     			course_type=j[i].course_type_ot;
			     		}
						$("table#army_course_table").append('<tr id="tr_army_course'+cor+'">'
			                     +'<td class="anm_srno">'+cor+'</td>'
			                     +'<td style="width: 10%;">'+j[i].authority+'</td>'
			                     +'<td style="width: 10%;">'+dauth+'</td>'
			                     +'<td style="width: 10%;">'+j[i].course_name+'</td>'
			                     +'<td style="width: 10%;">'+j[i].course_abbreviation+'</td>'
			                     +'<td style="width: 10%;">'+j[i].institute+'</td>'
			                     +'<td style="width: 10%;">'+div+'</td>'
			                     +'<td style="width: 10%;">'+course_type+'</td>'
			                     +'<td style="width: 10%;">'+dst+'</td>'
			                     +'<td style="width: 10%;">'+doc+'</td>'
			                     +'<td style="width: 10%;">'+j[i].modify_by+'</td>'
			                     +'<td style="width: 10%;">'+dmod+'</td>'
			                     +'<td style="display:none;"><input type="text" id="flang_ch_id'+cor+'" name="flang_ch_id'+cor+'"  value="' + j[i].id + '" class="form-control autocomplete" autocomplete="off" ></td>'
			                     /* +'<td style="width: 10%;"><a  class="btn btn-danger btn-sm" value="REMOVE" title = "REJECT" '+j[i].action+'><i class="fa fa-times"></i></a>' */
			                     +'<td>'+j[i].action+'</td>'
			                     /* +'</td> */
			                     +'</tr>');
			}
			 }	
	  });
	
	
}

function CancelHisArmyCourse(id){
	var set_status=2;
	var comm_id=$('#comm_id').val();
	
	$.post('CancelHisArmyCourse?' + key + "=" + value,{id:id,set_status:set_status,comm_id:comm_id}, function(j){
		
	if(j==0){
		alert("Data Rejected Successfully!");
	}
	if(j==1){
		alert("Data Rejected Successfully!");
		get_army_course_details();
	}
		
	  		}).fail(function(xhr, textStatus, errorThrown) {alert("fail to fetch")
		});
}
	                        	
       	function myFunction() {
       		  var checkBox = document.getElementById("myCheck");
       		 
       		  if (checkBox.checked == true){
       			  $("#submit_a").show();
       			 
       		  } else {
       			  $("#submit_a").hide();
       			
       		  }
       	}
 </script>
 

