<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	HttpSession sess = request.getSession(false);
	if (sess.getAttribute("userId") == null){
		sess.invalidate();
		response.sendRedirect("/login"); return; 
	} 
%>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

 <link rel="stylesheet" href="js/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/layout/css/animation.css">
<link rel="stylesheet" href="js/assets/scss/style.css"> 
<link rel="stylesheet" href="js/assets/collapse/collapse.css">

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>

<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>

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
  <link rel="stylesheet" href="js/assets/css/font-awesome.min.css">
  

<div class="container">
	<div class="card"  style="padding-top: 5px; ">	
			<div class="panel-group" id="accordion">
				<div class="panel panel-default" id="insp_div1">
<div class="col-md-12" style="padding-top: 5px; " align="center">
								
											
												<label class=" form-control-label" style="font-size: 20px">Personal No : </label>
											
											
												<label  id="personnel_no" style="font-size: 20px" class="form-control-label "   > </label>						                   
							               		
											
											
											
											
									

									
								</div> </div></div></div>
								
           <div class="watermarked" data-watermark="" id="divwatermark"><span id="ip"></span>
                 <table id="senoritypopuptable" class="table no-margin table-striped  table-hover  table-bordered report_print">        
                                        <thead style="text-align: center;">
                                                <tr>
                                                    <th >Ser No</th>
                                                        <th >Authority</th>
                                                        <th >Date of Authority</th>
                                                        <th >Date From Which Change in Seniority is Effective</th>
                                                        <th >Date of Seniority </th> 											
                                                        <th >Updated By</th>
                                                        <th >Updated Date</th>
                                                        <th >Action</th>
                                                        
                                                </tr> 
                                         </thead>
                                           <tbody id="senoritypopuptbody" align="center">
                                       
                                        </tbody>
                                </table>
                              </div>
                          </div> 
                          
                           
	<div class="animated fadeIn" id="approvedDiv" style="display: none">
	
   </div>



<script>
var key = "${_csrf.parameterName}";
var value = "${_csrf.token}";
$(document).ready(function() {
	var personal_no='${personal_no}';
	
	$("#personnel_no").text(personal_no);
	var status='${status}';
	if(status==0){
		 $("div#approvedDiv").append('<div class="card">'
		+'<div class="panel-group" id="accordion">'
		+'	<div class="panel panel-default">	'
		+'	   <div class="col-md-12" align="center" >	'              					
		+'		    <p><input type="checkbox" id="myCheck" onclick="myFunction();"/> I have checked all the tabs and certify that all the information is correct. </p>' 
		+'	</div>'
		+'	<div class="col-md-12" id = "submit_a" align="center" style="display:none">	  '            					
		+'			     <input type="button" class="btn btn-primary btn-sm" onclick="getApproveHistoryData();" value="Approve">'
		+'	   </div>'
		+'   </div>'
		+'	</div>'
		+'   </div>');
		 $("div#approvedDiv").show();
	}

	
	getHistoryData();

		
	
	} );
	
function getHistoryData(){
	
 	var comm_id='${comm_id}';
	var status='${status}';
    $('#senoritypopuptbody').empty(); 
     $.post('getSeniorityHistoryData?' + key + "=" + value, {status:status,comm_id:comm_id}, function(j){
                   var v=j.length;
                   if(v!=0){
                	   var i=0;          
           for(i;i<v;i++){
                   am=i+1;
             
             $("table#senoritypopuptable").append('<tr><td>'+am+'</td>'
            		 +'<td>'+j[i].authority+'</td>'
            		 +'<td>'+j[i].date_of_authority+'</td>'
            		 +'<td>'+j[i].eff_date_of_authority+'</td>'
            		 +'<td>'+j[i].date_of_seniority+'</td>'
            		 +'<td>'+j[i].modified_by+'</td>'
            		 +'<td>'+j[i].modified_date+'</td>'
            		 +'<td>'+j[i].action+'</td>'
				 +'</tr>');
		            
              
            
           }
          
           }
                   else{
                	   $("table#senoritypopuptable").append('<tr><td colspan="8" align="center">Data Not Available</td>'
                				 +'</tr>');
                	   $("div#approvedDiv").hide();
                   }
     });
} 

function getApproveHistoryData(){
	
 	var comm_id='${comm_id}';

   
     $.post('ApproveSeniorityHistoryData?' + key + "=" + value, {comm_id:comm_id}, function(j){
                  if(j==1){
                	  alert("Data Approved Succesfully");
                	  opener.location.reload();
                	  window.self.close();
                  }
                  else{
                	  alert("Data Approved Succesfully");
                  }
     });
} 


function CancelHisSeniorityData(id){

	var status='${status}';
	var set_status=0
	if(status==-1){
		set_status=0;
	}
	else{
		set_status=2;
	}
	
		
	$.post('CancelHisSeniorityData?' + key + "=" + value,{id:id,set_status:set_status}, function(j){
		
	if(j==0){
		if(set_status==0){
		alert("Unable to Cancel");
		}
		else{
			alert("Unable to Reject");
		}
	}
	if(j==1){
		if(set_status==0)
		alert("Data cancelled Successfully!");
		else
		alert("Data Rejected Successfully!");
		getHistoryData();
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




