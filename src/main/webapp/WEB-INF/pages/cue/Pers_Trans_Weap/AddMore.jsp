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
$(document).ready(function() {
	$('body').toggleClass('open');
	$('.nav-item').toggle();
	$("div#divLogoutHidShow").hide();	
	document.getElementById('menuToggle').style.pointerEvents = 'none';
	$('#left-panel , #menuToggle').css("display","none");
	 
});
</script>

<script type="text/javascript">
$(document).ready(function () {
	
	try{
		document.getElementById("getTypeOnclick").value = '${add_moreType}';
		 
		var type = "" ;
		type = document.getElementById("getTypeOnclick").value;		
		
			if(type != null){		
				if(document.getElementById("getTypeOnclick").value == '1') //for PERSONNEL value is 1
				{
					document.getElementById("type_of_cue").value="1"; //for PERSONNEL value is 1
					getViewReportList(); 
				}
				else if(document.getElementById("getTypeOnclick").value == '2') // for TRANSPORT value is 2
				{
					document.getElementById("type_of_cue").value="2"; // for TRANSPORT value is 2
					getViewReportList();
				}
				else if (document.getElementById("getTypeOnclick").value == '3') // for WEAPON value is 3
				{
					document.getElementById("type_of_cue").value="3"; // for WEAPON value is 3
					getViewReportList();
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
		
	
	
});
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
		    			 	   
		document.getElementById('tableStdPerson').style.display='block';  			       
		document.getElementById('tableStdPersonMod').style.display='block'; 		    			      
		document.getElementById('tableStdPersonfot').style.display='block';
		
		
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

</script>

<input type="hidden" name="getTypeOnclick" id="getTypeOnclick" value="${add_moreType}" />
<form:form name="add_moreForm" id="add_moreForm" action="add_moreAction" method='POST' commandName="WePeconditionAddMoreCMD">
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
										<th >Status</th>
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
										<th >Status</th>
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
										<th >Status</th>
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
										<th >Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${list4}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item.item_seq_no}</td>
											<td >${item.item_type}</td>
											<td >${item.auth_weapon}</td>
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
								        <th >Status</th> 
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
										<th >Status</th>
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
											<td >${item.status}</td>
										</tr>				
									</c:forEach>
								</tbody>
							</table>
							</div>
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
										<th >Status</th>
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
											<td >${item.status}</td>
										</tr>		
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>
					
					<div class="col s12" id="tableStdPersonMod" style="display: none;">
						<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					<h3 class="heading_content_inner">Authorisation of Personnel Under Modification</h3>
							<table id="getStdPersonModeSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead >
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Pers Cat</th>
										<th >Parent Arm</th>
										<th >Category</th>
										<th >Common Appt/Trade</th>
										<th >Rank</th>
										<th >Modification</th>
										<th >Base Authorisation</th>
										<th >Amt of Incr/Decr</th>
										<th >Status</th>
									</tr>
								<tbody>
									<c:forEach var="item" items="${list11}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item.cat_per}</td>
											<td >${item.parent_arm}</td>
											<td >${item.rank_cat}</td>
											<td >${item.app_trade}</td>
											<td >${item.rank}</td>
											<td >${item.modification}</td>
											<td >${item.base_amt}</td>
											<td >${item.amt_inc_dec}</td>
											<td >${item.status}</td>
									</tr>	
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>	

	<div class="col s12" id="tableStdPersonfot" style="display: none;">
		<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
	    					
	    					<h3 class="heading_content_inner">Inc/Dec General Notes Of Personnel</h3>
							<table id="getStdPersonfotnoteSearchReport" class="table no-margin table-striped  table-hover  table-bordered" width="100%">
								<thead>
									<tr>
										<th class="tableheadSer">Ser No</th>
										<th >Pers Cat</th>
										<th >Parent Arm</th>
										<th >Category</th>
										<th >Common Appt/ Trade</th>
										<th >Rank</th>
										<th >Gen Notes Condition</th>
										<th >Base Auth</th>
										<th >Amt of Incr/Decr</th>
										<th >Status</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${list12}" varStatus="num" >
										<tr>
											<td class="tableheadSer">${num.index+1}</td>
											<td >${item.cat_per}</td>
											<td >${item.parent_arm}</td>
											<td >${item.rank_cat}</td>
											<td >${item.app_trade}</td>
											<td >${item.rank}</td>
											<td >${item.condition}</td>
											<td >${item.base_amt}</td>
											<td >${item.amt_inc_dec}</td>
											<td >${item.status}</td>
										</tr>	
									</c:forEach>
								</tbody>
							</table>
							</div>
						</div>	
		</div>