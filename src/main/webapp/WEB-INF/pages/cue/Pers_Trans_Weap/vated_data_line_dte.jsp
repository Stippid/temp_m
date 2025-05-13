	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables"%>
<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>


<script type="text/javascript">
$(document).ready(function () {debugger
	
	var we_pe3 = "${WePeconditionAddMoreCMD.we_pe}";
	console.log("val of we pe 1" + we_pe3);
	
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();

	if("${roleAccess}" === "Line_dte"  && "${roleType}" === 'DEO' ){
		  document.getElementById('fileUploadFormContainer').style.display = 'block';
	}
	if("${roleAccess}" === "Line_dte"  && "${roleType}" === 'APP' ){
		  document.getElementById('appcontainer').style.display = 'block';
	}
	try{
		document.getElementById("getTypeOnclick").value = '${add_moreType}';
		document.getElementById("arm").value = '${arm}';
		document.getElementById("spdir").value = '${spdir}';
		 
		var type = "" ;
		type = document.getElementById("getTypeOnclick").value;		
		 //var backButton = document.querySelector('a.btn.btn-info.btn-sm');
			if(type != null){		
				if(document.getElementById("getTypeOnclick").value == '1') //for PERSONNEL value is 1
				{
					document.getElementById("type_of_cue").value="1"; //for PERSONNEL value is 1
					//backButton.href = "WePecondition_data_pers";
					$('a.btn.btn-info.btn-sm').attr('href','WePecondition_data_pers');
					getViewReportList();  
					$('#dsdiv').show();
				}
				else if(document.getElementById("getTypeOnclick").value == '2') // for TRANSPORT value is 2
				{
					document.getElementById("type_of_cue").value="2"; // for TRANSPORT value is 2
					//backButton.href = "WePecondition_data_trans";
					$('a.btn.btn-info.btn-sm').attr('href','WePecondition_data_trans');
					getViewReportList();
					$('#dsdiv').hide();
				}
				else if (document.getElementById("getTypeOnclick").value == '3') // for WEAPON value is 3
				{
					document.getElementById("type_of_cue").value="3"; // for WEAPON value is 3
					//backButton.href = "WePecondition_data";
					$('a.btn.btn-info.btn-sm').attr('href','WePecondition_data');
					getViewReportList();
					$('#dsdiv').hide();
				}
				
			}
			
		}
		catch (e) {
			// TODO: handle exception
		} 
	
		if ( document.getElementById("we_pe1").value !=null)
		{		
			
		var radioButtons = document.getElementsByName("we_pe");
		if (radioButtons != null) 
		{
		    for (var radioCount = 0; radioCount < radioButtons.length; radioCount++) 
		    {
		        if (radioButtons[radioCount].value ==  document.getElementById("we_pe1").value  ) 
		        {
		            radioButtons[radioCount].checked = true;
		        }
		    }
		}			 
		}	
		
		//var file = document.getElementById('doc_path1');
		var filesecond = document.getElementById('doc_path');

		function validateFile(e) {
		    var ext = this.value.match(/\.([^\.]+)$/)[1];
		    switch (ext.toLowerCase()) {
		        case 'jpg':
		        case 'jpeg':
		        case 'pdf':
		            break;
		        default:
		            alert('Please Only Allowed *.jpeg/.pdf File Extension');
		            this.value = '';
		            return;
		    }

		    if (this.files[0].size > 20 * 1024 * 1024) { // 20MB in bytes
		        alert("File size must be less than 20 MB!");
		        this.value = "";
		    }
		}

		// Add the validation to both file inputs
		//file.onchange = validateFile;
		filesecond.onchange = validateFile;
	
});

function myFunction() {
	  var checkBox = document.getElementById("myCheck");
	  if (checkBox.checked == true){
		  $("#submit_a").show();
	  } else {
		  $("#submit_a").hide();
	  }
}
</script>

<script>
$(document).ready(function() {
	
	 if(document.getElementById("type_of_cue").value ==  "1")  //Personnel //for PERSONNEL value is 1
	{
			
		document.getElementById("h3Id").innerHTML="VIEW FOR PERSONNEL";
		getViewReportList();
		 getArmByWePeNo();
	}
	else if(document.getElementById("type_of_cue").value ==  "2")  //Transport // for TRANSPORT value is 2
	{
		
		document.getElementById("h3Id").innerHTML="VIEW FOR TRANSPORT";
		getViewReportList();
		 getArmByWePeNo();
	}
	else if(document.getElementById("type_of_cue").value ==  "3")  //Weapon // for WEAPON value is 3
	{
		document.getElementById("h3Id").innerHTML="VIEW FOR WEAPON";		
		getViewReportList();
		 getArmByWePeNo();
	}
	
	 if('${list.size()}' == 0 ){
	     $("table#getStdTransSearchReport").append("<tr><td colspan='6' style='text-align:center;'>Data Not Available</td></tr>");
	 } 

	if('${list2.size()}' == 0 ){
	     $("table#getStdTransModeSearchReport").append("<tr><td colspan='8' style='text-align:center;'>Data Not Available</td></tr>");
	 } 

	if('${list3.size()}' == 0 ){
	     $("table#getStdTransfotnoteSearchReport").append("<tr><td colspan='8' style='text-align:center;'>Data Not Available</td></tr>");
	 } 
	
	if('${list4.size()}' == 0 ){
	     $("table#getStdWaeponSearchReport").append("<tr><td colspan='5' style='text-align:center;'>Data Not Available</td></tr>");
	 } 
	
	if('${list6.size()}' == 0 ){
	     $("table#getStdWaeponModeSearchReport").append("<tr><td colspan='7' style='text-align:center;'>Data Not Available</td></tr>");
	 } 
	
	if('${list7.size()}' == 0 ){
	     $("table#getStdWaeponfotnoteSearchReport").append("<tr><td colspan='7' style='text-align:center;'>Data Not Available</td></tr>");
	 } 
	
	if('${list9.size()}' == 0 ){
	     $("table#getStdPersonSearchReport").append("<tr><td colspan='8' style='text-align:center;'>Data Not Available</td></tr>");
	 } 

	if('${list11.size()}' == 0 ){
	     $("table#getStdPersonModeSearchReport").append("<tr><td colspan='10' style='text-align:center;'>Data Not Available</td></tr>");
	 } 
	if('${list12.size()}' == 0 ){
	     $("table#getStdPersonfotnoteSearchReport").append("<tr><td colspan='10' style='text-align:center;'>Data Not Available</td></tr>");
	 } 
	
});
</script>

<script>

function toHex(str) {
	var hex = '';
	for(var i=0;i<str.length;i++) {
		hex += ''+str.charCodeAt(i).toString(16);
	}
   	return hex;    		
}
</script>
<script>
		
	 function getViewReportList(){ 	
		   var e = document.getElementById("type_of_cue").value;
    	
    		if(e  == '2')	 // for TRANSPORT value is 2	
    		{
    			getTransportDetails();	    				
   			}
    		else if(e == '3') // for WEAPON value is 3
    		{	   
    			
    			getWeaponDetails();	    			         
    		}
    		else if(e == '1') //for PERSONNEL value is 1
    		{
    			getPersonnelDetails();
    		}		    		
    		
	}	

	function getTransportDetails()
	{		
	    document.getElementById('tableStdTrans').style.display='block'; 
	  	document.getElementById('tableStdTransMod').style.display='block'; 
	  	document.getElementById('tableStdTransfot').style.display='block'; 
	}
	
	function getWeaponDetails()
	{		
		
	    document.getElementById('tableStdWaepon').style.display='block'; 
	    document.getElementById('tableStdWaeponMod').style.display='block'; 
	    document.getElementById('tableStdWaeponfot').style.display='block'; 

	   
	}

	 function getPersonnelDetails()
	 {
		document.getElementById('tableStdSumPerson').style.display='block';  	
		document.getElementById('tableStdPerson').style.display='block';  			       
		document.getElementById('tableStdPersonMod').style.display='block'; 		    			      
		document.getElementById('tableStdPersonfot').style.display='block';
		
		
		}
	 function RejectW(id, weaponNo, authWeapon, itemType,item_seq_no,type,amt_inc_dec,modification){debugger;
	  document.getElementById('rejectid_model').value=id;
	  document.getElementById('submodule').value='14';
	    
	    var partA;
		  if (type === "base")  {
		        partA = "Raising Obsn on "+item_seq_no+", " + itemType + ", Quantity " + authWeapon + " as per WE/PE No: " + weaponNo ;
		    } else if (type === "mod") {
		        partA = "Raising Obsn on "+item_seq_no+", "  + itemType + ", Base Quantity " + authWeapon + ", Modification " + modification + ", Inc/dec " + amt_inc_dec + " as per WE/PE No: "+ weaponNo;
		    } else if (type === "incdec") {
		        partA = "Raising Obsn on "+item_seq_no+", "  + itemType + ", Base Quantity " + authWeapon + ", Condition " + modification + ", Inc/dec " + amt_inc_dec + " as per WE/PE No: "+ weaponNo;
		    } 
		    document.getElementById('issue_summary').value = partA;
	    
 	  cleardata();	  
} 
	 
	 function RejectP(id, weaponNo, arm,rank,category,auth,type,amt_inc_dec,modification){debugger;
	  document.getElementById('rejectid_model').value=id;
	  document.getElementById('submodule').value='13';
	    
	  			var partA;
			  if (type === "base")  {
			        partA = "Raising Obsn on arm " + arm + ", rank "+rank+", category " +category+", Quantity " + auth + " as per WE/PE No: " + weaponNo ;
			    } else if (type === "mod") {
			        partA = "Raising Obsn on  arm " + arm + ", rank "+rank+", category " +category+ ", Base Quantity " + auth + ", Modification " + modification + ", Inc/dec " + amt_inc_dec + " as per WE/PE No: "+ weaponNo;
			    } else if (type === "incdec") {
			        partA = "Raising Obsn on  arm " + arm + ", rank "+rank+", category " +category+ ", Base Quantity " + auth + ", Condition " + modification + ", Inc/dec " + amt_inc_dec + " as per WE/PE No: "+ weaponNo;
			    } 
		    document.getElementById('issue_summary').value = partA;
	  cleardata();	  
} 
	 function RejectT(id, weaponNo, mctno,nomaneclature,auth,type,amt_inc_dec,modification){debugger;
	  document.getElementById('rejectid_model').value=id;
	  document.getElementById('submodule').value='15';
	    
	  var partA;
	  if (type === "base")  {
	        partA = "Raising Obsn on "+mctno+", "  + nomaneclature + ", Quantity " + auth + " as per WE/PE No: " + weaponNo ;
	    } else if (type === "mod") {
	        partA = "Raising Obsn on " +mctno+", "  + nomaneclature + ", Base Quantity " + auth + ", Modification " + modification + ", Inc/dec " + amt_inc_dec + " as per WE/PE No: " + weaponNo;
	    } else if (type === "incdec") {
	        partA = "Raising Obsn on " +mctno+", "  + nomaneclature + ", Base Quantity " + auth + ", Condition " + modification + ", Inc/dec " + amt_inc_dec + " as per WE/PE No: "+ weaponNo;
	    } 
	    document.getElementById('issue_summary').value = partA;
	  
	  cleardata();	  
} 
	/*  function updatedata() {
	var issue_summary = $("input#issue_summary").val(), description = $("textarea#description").val();
	 var id = document.getElementById('rejectid_model').value;
     var we_pe_no1 = document.getElementById('weaponNo').value;
     var auth_no = document.getElementById('authWeapon').value;
     var item_type1 = document.getElementById('itemType').value;
     var item_seq_no1 = document.getElementById('item_seq_no').value;
     var description1 = document.getElementById('description').value;
     var modification1 =  document.getElementById('modification').value;
	 var amt_inc_dec1 = document.getElementById('amt_inc_dec').value;
	 var condition1 = document.getElementById('condition').value;
		
// 	if(issue_summary == "")
// 	{
// 		alert("Please Enter issue summary");
// 		$("input#issue_summary").focus();
// 		return false;
// 	}
	 if(description == "") {
	        alert("Please enter Reject Remarks");
	        $("textarea#description").focus();
	        return false;
	    }
	else if(description != "" && description != null) {debugger;
// 		var id =document.getElementById('rejectid_model').value; 
		
		var form = jQuery('#fileUploadForm')[0];
		var data = new FormData(form);
		data.append("id",id);
		data.append("we_pe_no1",we_pe_no1);
		data.append("auth_no",auth_no);
		data.append("item_type1",item_type1);
		data.append("item_seq_no1",item_seq_no1); 
		data.append("description1",description1);
		data.append("modification1",modification1);
		data.append("amt_inc_dec1",amt_inc_dec1);
		data.append("condition1",condition1);
		jQuery.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "rejectWePeByLineDteaction?"+key+"="+value,
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success: function(response) {
                // Access the message from the response object
                alert(response.message);

                // Clear form and close modal if successful
                document.getElementById('rejectid_model').value = "";
                document.getElementById('description').value = "";

                // Close popup
                $('.modal').removeClass('in');
                $('.modal').attr("aria-hidden","true");
                $('.modal').css("display", "none");
                $('.modal-backdrop').remove();
                $('body').removeClass('modal-open');
            },
            error: function(xhr, status, error) {
                alert("An error occurred: " + error);
            }
        });

		return true;
	}
	
	return false;
} */



//24-12-2024 

 function updatedata() {debugger;
		    debugger;
		    var description = document.getElementById('description').value;
		    var typecue = document.getElementById("type_of_cue").value;
		    var ct_part = document.getElementById("ct_part").value;
		    if(description == "") {
		        alert("Please enter Reject Remarks");
		        $("textarea#description").focus();
		        return false;
		    }
		     if(typecue=="1"){
		    	if(ct_part == "0") {
			        alert("Please select sd");
			        $("input#ct_part").focus();
			        return false;
			    }
		    } 
		     if(description != "" && description != null) {
		        var id = document.getElementById('rejectid_model').value;
		        var we_pe_no1 = document.getElementById('weaponNo').value;
		        var auth_no = document.getElementById('authWeapon').value;
		        var item_type1 = document.getElementById('itemType').value;
		        var item_seq_no1 = document.getElementById('item_seq_no').value;
		        var description1 = document.getElementById('description').value;
		        var modification1 =  document.getElementById('modification').value;
		  	  	var amt_inc_dec1 = document.getElementById('amt_inc_dec').value;
		  		var submodule = document.getElementById('submodule').value;
		  		var summary1 = document.getElementById('issue_summary').value;

		        $.post("rejectWePeByLineDteaction?" + key + "=" + value, {
		            auth_no: auth_no,
		            we_pe_no1: we_pe_no1,
		            id: id,
		            item_type1: item_type1,
		            item_seq_no1: item_seq_no1,
		            description1: description1,
		            modification1 : modification1,
		            submodule	: submodule,
		            amt_inc_dec1 : amt_inc_dec1,
		            summary1:summary1,
		            typecue:typecue,
		            ct_part :ct_part
		        })
		        .done(function(response) {debugger;
		            // Handle JSON response
		            alert(response.message);
		            
		            if(response.message) {
		                document.getElementById('rejectid_model').value = "";
		                document.getElementById('description').value = "";

		                // Close popup
		                $('.modal').removeClass('in');
		                $('.modal').attr("aria-hidden","true");
		                $('.modal').css("display", "none");
		                $('.modal-backdrop').remove();
		                $('body').removeClass('modal-open');
		                
		            }
		        })
		        .fail(function(xhr, textStatus, errorThrown) {
		            alert("Error occurred while processing request");
		        });
		        return true;
		    }
		    return true;
		}


	 function savedata() {
		   var issue_summary = document.getElementById('we_pe_no').value;
		   var description1 = document.getElementById('descriptionmain').value;
		   var wepe = document.getElementById("type_of_cue").value;
		   if(description1 == "") {
		        alert("Please enter Observation");
		        $("textarea#descriptionmain").focus();
		        return false;
		    }
		   
		var form = jQuery('#fileUploadFormFinal')[0];
		var data = new FormData(form);
		
		data.append("description1",description1);
		data.append("issue_summary",issue_summary);
		data.append("wepe",wepe);
		
		alert(data);
		jQuery.ajax({
			type : "POST",
			enctype : 'multipart/form-data',
			url : "rejectWePeNoByLineDteAction?"+key+"="+value,
			data : data,
			processData : false,
			contentType : false,
			cache : false,
			timeout : 600000,
			success: function(response) {
				jQuery("#descriptionmain").val(null);
				 jQuery("#doc_path").val('');
				 alert(response.message);
				
			}
		});
	 }
	 
	 
</script>   

<script>
	
function getArmByWePeNo()
{
		 $.post("getDetailsByWePeCondiNo?"+key+"="+value, {val : $("#we_pe_no").val()}).done(function(j) {
			 if(j!="" && j!=null)
    		 {
    			document.getElementById("table_title").value=j[0].table_title;
    		 }
   			 else
			 {
			 	document.getElementById("table_title").value="";
			 }
		}).fail(function(xhr, textStatus, errorThrown) { }); 
}

function cleardata()
{
	  		 
	  	document.getElementById("description").value ="";
}

/* function submitData() {	
	debugger;
	 var type = document.getElementById("type_of_cue").value;
     var we_pe_no = document.getElementById("we_pe_no").value;
     var we_pe = "${WePeconditionAddMoreCMD.we_pe}";
     var arm = document.getElementById("arm").value;
     var spdir = document.getElementsByName("spdir");
	 
	 var radio_doc01 = $("input[name='radio_doc']:checked").val();
		$("#type1").val(type);
		$("#we_pe_no1").val(we_pe_no);
		$("#we_pe12").val(we_pe);
		$("#spdir1").val(spdir);
		$("#arm1").val(arm);
	document.getElementById('appForm').submit();
	
} */

function submitData() {	
	debugger;
	 var type = document.getElementById("type_of_cue").value;
     var we_pe_no = document.getElementById("we_pe_no").value;
     var we_pe = "${WePeconditionAddMoreCMD.we_pe}";
     var arm = document.getElementById("arm").value;
     var spdir = document.getElementsByName("spdir");
	 
	 var radio_doc01 = $("input[name='radio_doc']:checked").val();
		$("#type1").val(type);
		$("#we_pe_no1").val(we_pe_no);
		$("#we_pe12").val(we_pe);
		$("#spdir1").val(spdir);
		$("#arm1").val(arm);
	document.getElementById('appForm').submit();
	
}

</script>

<input type="hidden" name="getTypeOnclick" id="getTypeOnclick" value="${add_moreType}" />
<form:form name="add_moreForm" id="add_moreForm" action="rejectWePeByLineDteaction" method='POST' commandName="WePeconditionAddMoreCMD">
	<div class="animated fadeIn">
		     	<div class="container" align="center">
	    		<div class="card">
	            	<div class="card-header"> <h5 id="h3Id"></h5>
	            		<div class="card-body card-block cue_text" >
	            		 <div class="col-md-12">
							<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
						                	<label for="text-input" class=" form-control-label">Type of Document</label>
						                </div>							              
							                <div class="col-12 col-md-6">
							                              <div class="form-check-inline form-check">
							                               <input id="id" type="hidden" name="id" value="${WePeconditionAddMoreCMD.id}">    
							                                  <input type ="hidden" id= "we_pe1" value="${WePeconditionAddMoreCMD.we_pe}">	
							                                <label for="inline-radio1" class="form-check-label ">
							                                  <input type="radio" id="inline-radio1" name="we_pe" value="WE" class="form-check-input" disabled>WE
							                                </label>&nbsp;&nbsp;&nbsp;
							                                <label for="inline-radio2" class="form-check-label ">
							                                  <input type="radio" id="inline-radio2" name="we_pe" value="PE" class="form-check-input" disabled>PE
							                                </label>&nbsp;&nbsp;&nbsp;
							                                <label for="inline-radio3" class="form-check-label ">
							                                  <input type="radio" id="inline-radio3" name="we_pe" value="FE" class="form-check-input" disabled>FE
							                                </label>&nbsp;&nbsp;&nbsp;
							                                <label for="inline-radio4" class="form-check-label ">
							                                  <input type="radio" id="inline-radio4" name="we_pe" value="GSL" class="form-check-input" disabled>GSL
							                                </label>&nbsp;&nbsp;&nbsp;
							                                
							                              </div>
							                            </div>					              
	                				</div>
	                			</div>
	                			<div class="col-md-6">
	              					<div class="row form-group">
	                					<div class="col col-md-6">
	                  						<label class=" form-control-label">MISO WE/PE No</label>
	                					</div>
	                					<div class="col-12 col-md-6">
	                  						<input  id="we_pe_no" name="we_pe_no" placeholder="" class="form-control" disabled value="${WePeconditionAddMoreCMD.we_pe_no}">
	                  						<input type="hidden" id="type_of_cue" name="type_of_cue" placeholder="" class="form-control"  value="${add_moreType}">
	                  						<input type="hidden" id="arm" name="arm" >
	                  						<input type="hidden" id="spdir" name="spdir" >
	                					</div>
	                					</div>
	              					</div>	 
	              			</div>
	              			 <div class="col-md-12">		
	              				<div class="col-md-6">
		             				<div class="row form-group">
		               					<div class="col col-md-6">
		                 					<label class=" form-control-label">Document Title </label>
		               					</div>
		               					<div class="col-12 col-md-6">
		                 					<input  id="table_title" name="table_title" placeholder="" class="form-control"   autocomplete="off" readonly="readonly" value="${WePeconditionAddMoreCMD.table_title}">
		               					</div>
               						</div>
             					</div> 
	              	        </div>
					   </div>
				   </div> 
			     </div>   
			 </div>
	     </div>
</form:form>
<div class="animated fadeIn" id="printableArea1" >
		 <div class="col s12" id="tableStdTrans" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">Standard Authorisation Of Transport</h3>
							<table id="getStdTransSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >MISO WE/PE No</th>
										<th >MCT No</th>
										<th >Veh Nomenclature</th>
										<th >Authorisation</th>
										 <th>Verified By </th>
										<th >Action</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
										<td class="tableheadSer">${num.index+1}</td>
										<td >${item.we_pe_no}</td>
										<td >${item.mct_no}</td>
										<td >${item.std_nomclature}</td>
										<td >${item.auth_amt}</td>
										<td>${item.vetted} </td>
										<td >${item.status}</td>
									</tr>
								</c:forEach>								
								</tbody>
							</table>
							</div>
						</div> 
			 <div class="col s12" id="tableStdTransMod" style="display: none;">
				<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">Authorisation of Transport Under Modification</h3>
							<table id="getStdTransModeSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >MISO WE/PE No</th>
										<th >MCT No</th>	
										<th >Veh Nomenclature</th>
										<th >Modification</th>
										<th >Base Authorisation</th>							
										<th >Amt of Incr/Decr</th>
										<th>Verified By </th>
										<th >Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${list2}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item.we_pe_no}</td>
											<td >${item.mct_no}</td>
											<td >${item.std_nomclature}</td>
											<td >${item.modification}</td>
											<td >${item.auth_amt}</td>
											<td >${item.amt_inc_dec}</td>
											<td>${item.vetted} </td>
											<td >${item.status}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>
					<div class="col s12" id="tableStdTransfot" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">Incr/Decr General Notes Of Transport</h3>
							<table id="getStdTransfotnoteSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >MISO WE/PE No</th>
										<th >MCT No</th>	
										<th >Veh Nomenclature</th>
										<th >Gen Notes Condition</th>
										<th >Base Authorisation</th>							
										<th >Amt of Incr/Decr</th>
										 <th>Verified By </th>
										<th >Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${list3}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item.we_pe_no}</td>
											<td >${item.mct_no}</td>
											<td >${item.std_nomclature}</td>
											<td >${item.condition}</td>
											<td >${item.auth_amt}</td>
											<td >${item.amt_inc_dec}</td>
											<td>${item.vetted} </td>
											<td >${item.status}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>
					
				<div class="col s12" id="tableStdWaepon" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">Standard Authorisation Of Weapon</h3>
							<table id="getStdWaeponSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Item Code</th>
										<th >Nomenclature</th>
										<th >Authorisation</th>
										<th >Verified By</th>										
										<th >Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${list4}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item.item_seq_no}</td>
											<td >${item.item_type}</td>
											<td >${item.auth_weapon}</td>
											<td >${item.vetted}</td>
											<td >${item.status}</td>
										</tr>				
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>		
			
						
			<div class="col s12" id="tableStdWaeponMod" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">Authorisation of Weapon Under Modification</h3>
							<table id="getStdWaeponModeSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Item Code</th>
										<th >Nomenclature</th>
										<th >Modification</th>
										<th >Base Authorisation</th>
								        <th >Amt of Incr/Decr</th>
								        <th>Verified By </th>
								        <th >Action</th> 
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${list6}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item.item_seq_no}</td>
											<td >${item.item_type}</td>
											<td >${item.modification}</td>
											<td >${item.baseauth}</td>
											<td >${item.amt_inc_dec}</td>
											<td>${item.vetted} </td>
											<td >${item.status}</td>
										</tr>				
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>
						
						
				   <div class="col s12" id="tableStdWaeponfot" style="display: none;">
	    					<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					
	    					<h3 class="heading_content_inner">Inc/Dec General Notes Of Weapon</h3>
							<table id="getStdWaeponfotnoteSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Item Code</th>
										<th >Nomenclature</th>
										<th style="font-size:15px">Condition</th>
										<th >Base Authorisation</th>
										<th >Amt of Incr/Decr</th>
										<th>Verified By </th>
										<th >Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${list7}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item.item_seq_no}</td>
											<td >${item.item_type}</td>
											<td >${item.condition}</td>
											<td >${item.baseauth}</td>
											<td >${item.amt_inc_dec}</td>
											<td>${item.vetted} </td>
											<td >${item.status}</td>
										</tr>				
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>
						
						
					<div class="col s12" id="tableStdSumPerson" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
						<h3 class="heading_content_inner">Summary</h3>
						<table id="getSummaryPersonnelEntitlement" class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead >
								<tr>
									<th>Category</th>
									<th>Regimental</th>
									<th>ERE</th>
									<th>Total</th>	
								</tr>
							</thead> 
							<tbody>
							<c:forEach var="item1" items="${list1}" varStatus="num" >
								<tr>
									<td>${item1.rank_cat}</td>
									<td>${item1.regt}</td>
									<td>${item1.ere}</td>
									<td>${item1.total}</td>
								</tr>
							</c:forEach>
						</tbody>
						</table>
	
					</div>
					<div class="col s12" id="tableStdPerson" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    				<h3 class="heading_content_inner">Standard Authorisation Of Personnel</h3>
							<table id="getStdPersonSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Pers Cat</th>
										<th >Parent Arm</th>
										<th >Category</th>
										<th >Rank</th>
										<th >Common Appt/Trade</th>
										<th >Base Authorisation</th>
										<th>Verified By </th>
										<th >Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${list9}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item.person_cat}</td>
											<td >${item.arm_code}</td>
											<td >${item.rank_cat}</td>
											<td >${item.rank}</td>
											<td >${item.appt_trade}</td>
											<td >${item.auth_amt}</td>
											<td>${item.vetted} </td>
											<td >${item.status}</td>
										</tr>		
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>

	<div class="col s12" id="tableStdPersonMod" style="display: none;">
		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>
			<h3 class="heading_content_inner">Authorisation of Personnel Under Modification</h3>
			<table id="getStdPersonModeSearchReport"
				class="table no-margin table-striped  table-hover  table-bordered"
				width="100%">
				<thead>
					<tr>
						<th class="tableheadSer">Ser No</th>
						<th>Pers Cat</th>
						<th>Parent Arm</th>
						<th>Category</th>
						<th>Common Appt/Trade</th>
						<th>Rank</th>
						<th>Modification</th>
						<th>Base Authorisation</th>
						<th>Amt of Incr/Decr</th>
						<th>Verified By </th>
						<th>Action</th>
					</tr>
				<tbody>
					<c:forEach var="item" items="${list11}" varStatus="num">
						<tr>
							<td class="tableheadSer">${num.index+1}</td>
							<td>${item.cat_per}</td>
							<td>${item.parent_arm}</td>
							<td>${item.rank_cat}</td>
							<td>${item.app_trade}</td>
							<td>${item.rank}</td>
							<td>${item.modification}</td>
							<td>${item.base_amt}</td>
							<td>${item.amt_inc_dec}</td>
							<td>${item.vetted} </td>
							<td>${item.status}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

					<div class="col s12" id="tableStdPersonfot" style="display: none;">
							<div class="watermarked" data-watermark="" id="divwatermark"
								style="display: block;">
								<span id="ip"></span>
					
								<h3 class="heading_content_inner">Inc/Dec General Notes Of
									Personnel</h3>
								<table id="getStdPersonfotnoteSearchReport"
									class="table no-margin table-striped  table-hover  table-bordered"
									width="100%">
									<thead>
										<tr>
											<th class="tableheadSer">Ser No</th>
											<th>Pers Cat</th>
											<th>Parent Arm</th>
											<th>Category</th>
											<th>Common Appt/ Trade</th>
											<th>Rank</th>
											<th>Gen Notes Condition</th>
											<th>Base Auth</th>
											<th>Amt of Incr/Decr</th>
											<th>Verified By </th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${list12}" varStatus="num">
											<tr>
												<td class="tableheadSer">${num.index+1}</td>
												<td>${item.cat_per}</td>
												<td>${item.parent_arm}</td>
												<td>${item.rank_cat}</td>
												<td>${item.app_trade}</td>
												<td>${item.rank}</td>
												<td>${item.condition}</td>
												<td>${item.base_amt}</td>
												<td>${item.amt_inc_dec}</td>
												<td>${item.vetted} </td>
												<td>${item.status}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
</div>

<div id="fileUploadFormContainer" style="display:none;">
			<form method="POST" enctype="multipart/form-data"
				id="fileUploadFormFinal">
				<div class="card-footer" align="center">
					<!-- 						<input type="reset" class="btn btn-success btn-sm" value="Clear" onclick="clearall();">    -->
			
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class="form-control-label">Add Any Observation </label>
								</div>
								<div class="col-12 col-md-6">
									<textarea rows="2" cols="250" id="descriptionmain"
										name="descriptionmain" class="form-control char-counter1"
										autocomplete="off" maxlength="1000"></textarea>
								</div>
							</div>
						</div>
			
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class="form-control-label">Upload File</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="file" id="doc_path" name="doc_path"
										class="form-control">
								</div>
							</div>
						</div>
					</div>
			
			
			
					<a href="WePecondition_data" class="btn btn-info btn-sm">Back</a>
					<button type="button" id="btnSubmit" name="submit" class="btn btn-primary login-btn" onclick="return savedata();">Save</button>
				</div>
			</form>
			</div>
			
			<div class="panel panel-default" id="appcontainer" style="display:none;">
					<div class="col-md-12" align="center">
						<p style="font-size: 20px; color:black; font-weight: bold;"> <input type="checkbox" id="myCheck" onclick="myFunction();" style="font-size: 50px; color:red"/> I certify that the above data is accurate and, to the best of my knowledge, corresponds to the WE/PE number. </p>
					</div>
					
					<div class="col-md-12" style="display: flex; justify-content: center;">
    						<a href="WePecondition_data" class="btn btn-info btn-sm" style="margin-right: 10px;">Back</a>

							<div id="submit_a" style="display: none">
								<input type="submit" class="btn btn-primary btn-sm" value="Submit" onclick="submitData();">
							</div>
					</div>
				</div>

		<c:url value="submitDAtaLineDteaction" var="appUrl" />
		<form:form action="${appUrl}" method="post" id="appForm" name="appForm"
			modelAttribute="appid">
			<input type="hidden" name="type1" id="type1" value="0" />
			<input type="hidden" name="we_pe_no1" id="we_pe_no1" value="" />
			<input type="hidden" name="we_pe12" id="we_pe12" value="" />
			<input type="hidden" name="spdir1" id="spdir1" value="" />
			<input type="hidden" name="arm1" id="arm1" value="" />
		
		</form:form>

<form method="POST" enctype="multipart/form-data" id="fileUploadForm">
    <div class="modal fade" id="rejectModal" tabindex="-1" role="dialog" aria-labelledby="rejectModalLabel" aria-hidden="true" data-backdrop="static">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Rejection Remarks/Reason</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                
                <div class="modal-body">
                    <div class="form-group">
                        <div class="col-md-12 form-group">
                            <div class="col-2 col-md-2">
                                <label class="form-control-label">Issue Summary</label>
                            </div>
                            <div class="col-10 col-md-10">
                                <textarea rows="3" id="issue_summary" name="issue_summary" class="form-control char-counter1" autocomplete="off" readonly></textarea>
                            </div>
                        </div>
                        
                        <div class="col-md-12 form-group">
                            <div class="col-2 col-md-2">
                                <label for="text-input" class="form-control-label">
                                    <strong style="color: red;">*</strong> Description<br>
                                    <span style="font-size: 10px; color:red">(Max 1000 words)</span>
                                </label>
                            </div>
                            <div class="col-10 col-md-10">
                                <textarea rows="2" cols="250" id="description" style="height:150px;" name="description" class="form-control char-counter1" autocomplete="off" maxlength="1000"></textarea>
                                
                                <input id="rejectid_model" name="rejectid_model" class="form-control" type="hidden">
                                <input id="weaponNo" name="weaponNo" class="form-control" type="hidden">
                                <input id="authWeapon" name="authWeapon" class="form-control" type="hidden">
                                <input id="itemType" name="itemType" class="form-control" type="hidden">
                                <input id="item_seq_no" name="item_seq_no" class="form-control" type="hidden">
                                <input id="modification" name="modification" class="form-control" type="hidden">
                                <input id="amt_inc_dec" name="amt_inc_dec" class="form-control" type="hidden">
                                <input id="submodule" name="submodule" class="form-control" type="hidden">
                            </div>
                        </div>
                        
                        <div class="col-md-12 form-group" id="dsdiv" style="display:none">
                            <div class="col-2 col-md-2">           		
		                	<label for="text-input" class=" form-control-label"><strong style="color: red;">*</strong> Obsn informed to </label>
		               </div>
                            <div class="col-10 col-md-10">
		                
               				<select  id="ct_part" name="ct_part" class="form-control" > 
               				  <option  value="0">--Select--</option>
							  <option  value="sd9_1">SD-9</option>
							  <option  value="sd5_1">SD-5</option>
						    </select>
						</div>
	             	</div>
                
                        
                        <!-- <div class="col-md-12 form-group">
                            <div class="col-2 col-md-2">
                                <label class="form-control-label">Upload File </label>
                            </div>
                            <div class="col-12 col-md-6">
                                <input type="file" id="doc_path1" name="doc_path1" class="form-control">
                            </div>
                        </div> -->
                    </div>
                    
                    <div align="center">
                        <button type="button" name="submit" class="btn btn-primary login-btn" onclick="return updatedata();">Save</button>
                        <button type="reset" name="reset" class="btn btn-primary login-btn" onclick="cleardata();">Reset</button>
                    </div>
                </div>
                
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal" onclick="$('#rrr').attr('data-target','#')">Close</button>
                </div>
            </div>
        </div>
    </div>
</form>