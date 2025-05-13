<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script type="text/javascript" src="js/printWatermark/common.js"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<form name="search_avn_daily_receipt_report" id="search_aveh_daily_receipt_report" action="" method="post" class="form-horizontal"> 
	<div class="animated fadeIn">
	    	<div class="container" align="center">
	    		<div class="card">
	    		<div class="card-header" style="border: 1px solid rgba(0,0,0,.125); text-align: center;"><h5>AVN Daily Receipt Report Details : Search</h5></div>
	    			<div class="card-header"></div>
	          			<div class="card-body card-block">	            			 
	            			 <div class="col-md-12">
	         					 <div class="col-md-6">
	         					 	<div class="row form-group">
		                					<div class="col col-md-4">
	                 					          <label for="text-input" class=" form-control-label"><strong style="color: red;">* </strong>Status </label>
	               					        </div>
			               					<div class="col-12 col-md-8">
												<select name="status" id="status" class="form-control-sm form-control">
													<option value="0">Pending</option>
									                <option value="1">Approved</option>
												</select>
											</div> 	
		              					</div>	
	          				     </div>
	                             <div class="col-md-6">
	                             </div>
                             </div>		
							<div class="col-md-12">
	         					 <div class="col-md-6">
	         					      <div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label class=" form-control-label"><strong style="color: red;">* </strong>Date (From)</label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input type="date" id="frm_date" name="frm_date" placeholder="" class="form-control autocomplete" autocomplete="off">
		                					</div>
		              					</div>	
	          				     </div>
	                             <div class="col-md-6">
	                                   <div class="row form-group">
		                					<div class="col col-md-4">
		                  						<label class=" form-control-label">Date (To) </label>
		                					</div>
		                					<div class="col-12 col-md-8">
		                  						<input type="date" id="to_date" name="to_date" placeholder="" class="form-control autocomplete" autocomplete="off">
		                					</div>
		              					</div>									      
	                             </div>
                             </div>	
	            		</div>
	            		
						<div class="form-control card-footer" align="center">
							<a href="search_avn_daily_receipt_report" class="btn btn-success btn-sm" >Clear</a>
		              		<button type="button" class="btn btn-success btn-sm" onclick="return Search();">Search</button>
		              	</div> 		
	        	</div>
			</div>
    </div>
</form>


<div class="col s12" id="tableshow" > 
	<div class="animated fadeIn" >	
	    <div class="container" align="center">
	    	<div align="right"><h6>Total Count : ${list.size()}</h6></div>	    		
				<div id="divShow" style="display: block;">
				</div> 							                  
			<div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>
			<table id="dtBasicExample" class="table no-margin table-striped  table-hover  table-bordered report_print">
				   <thead>
								<tr style="text-align:center;">
								    <th class="th-sm" style="text-align:center;">DRR Ser No</th>
									<th class="th-sm" style="text-align:center;">SUS No</th>  
									<th class="th-sm" style="text-align:center;">Unit Name</th>
								    <th class="th-sm" style="text-align:center;">Date</th>  
								    <th class="th-sm" style="text-align:center;">Status</th> 
									<th class="th-sm" >Action</th>									  									
								</tr>
						</thead>
						   <tbody>
						   		<c:if test="${list.size() == 0}" >
									<tr>
										<td colspan="7" align="center" style="color: red;"> Data Not Available </td>
									</tr>
								</c:if>
								<c:forEach var="item" items="${list}" varStatus="num" >
									<tr>
									   <td style="text-align: left;">${item[1]}</td>
										<td style="text-align: center;">${item[0]}</td>																					
										<td style="text-align: left;">${item[4]}</td>
										<td style="text-align: center;">${item[3]}</td>
									<td style="text-align: center;">${item[2]}</td>
									<td style="text-align: center;">${item[5]}</td>
									</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
 </div>	
</div> 
<c:url value="search_drr_avn" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">
		<input type="hidden" name="status1" id="status1" value="0"/>
		<input type="hidden" name="frm_date1" id="frm_date1" value="0"/>
		<input type="hidden" name="curr_date" id="curr_date" value="0"/>
	</form:form> 
<c:url value="AVNViewReceiveIssue" var="viewUrl" />
<form:form action="${viewUrl}" method="post" id="viewForm" name="viewForm" modelAttribute="viewSerNo">
	<input type="hidden" name="viewSerNo" id="viewSerNo"/>
	<input type="hidden" name="viewStatus" id="viewStatus"/>
	<input type="hidden" name="viewDate" id="viewDate"/>
	<input type="hidden" name="viewStatus1" id="viewStatus1"/>
	<input type="hidden" name="viewSus" id="viewSus"/>
	<input type="hidden" name="viewfrom_dt" id="viewfrom_dt"/>
	<input type="hidden" name="viewto_dt" id="viewto_dt"/>
	<input type="hidden" name="unit_name123" id="unit_name123"/>
	<input type="hidden" name="search_sus_no" id="search_sus_no"/>	
</form:form>
<script>
$(document)
.ready(function() {

});
</script>
<script>
function getCheck(){
    validate();
    if($('#status').val() != null && $("#frm_date").val() != ""){
    	document.getElementById('tableshow').style.display='none'; 
    	getSearchReportList();
    }  
}
function validate(){
	if($("#status").val() == null){
		alert("Please Select the Status");
		return false;
	}
	if($("#frm_date").val() == ""){
		alert("Please Select the From Date");
		return false;
	}
	return true;
}
function Search(){
	$("#status1").val($("#status").val()) ;
    $("#frm_date1").val($("#frm_date").val()) ;
    $("#curr_date").val($("#to_date").val()) ;
    if($("#frm_date").val() == "")
    {
    	alert("Please Select From Date.");
    }
    else
    {
    	document.getElementById('searchForm').submit();
    } 
}

function View(serno,stat,dt,stat1,sus,fdt,tdt,unit_nm){
	document.getElementById('viewSerNo').value=serno;
	document.getElementById('viewStatus').value=stat;
	document.getElementById('viewDate').value=dt;
	document.getElementById('viewStatus1').value=stat1;
	
	document.getElementById('viewSus').value=sus;
	document.getElementById('viewfrom_dt').value=fdt;
	document.getElementById('viewto_dt').value=tdt;
	document.getElementById('unit_name123').value=unit_nm;
	if($("#sus_no1").val() == null)
	{
		document.getElementById('search_sus_no').value="";
	}
	else
	{
		document.getElementById('search_sus_no').value=$("#sus_no1").val();
	}
	
   
	document.getElementById('viewForm').submit();
}
</script>
