<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables"
	uri="http://github.com/dandelion/datatables"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="js/common/nrcss.css">
<!-- <link rel="stylesheet" href="js/common/select2/select2.min.css"> -->
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/InfiniteScroll/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/miso/orbatJs/ChkDateLessThan.js" type="text/javascript"></script>
<!-- <script src="js/common/select2/select2.min.js" type="text/javascript"></script> -->
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/assets/css/font-awesome.min.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<div class="col-md-10" align="center">
	<div class="card">
		<div class="card-header">
			<h5>
				<b>APPROVED FINAL RELIEF PROGRAMME</b><br>
			</h5>
		</div>
		<div class="card-body">
		   <div class="col-md-12" id="hid_id">  
                     	<div class="col-md-6">
                           <div class="col-md-6">
	            			   <div class="row form-group">
                					<div class="col-12 col-md-6" style="display: none;">
                  						<input type="text" id="sus_no" name="sus_no" style="font-family: 'FontAwesome',Arial;"  value="${roleSusNo}"  placeholder="&#xF002; Search"  class="form-control autocomplete" autocomplete="off" style="display: none;"  >
                  						<input type="text" id="role_access" name="role_access" style="font-family: 'FontAwesome',Arial;"  value="${roleAccess}"  placeholder="&#xF002; Search"  class="form-control autocomplete" autocomplete="off" style="display: none;"  >
                					</div>
              					</div>
							</div>
                     	</div>
               </div>  
<c:if test="${viewArm !=null}">                   	
             <div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col col-md-6">
							<label for="text-input" class=" form-control-label">Select Arm</label>
						</div>
						<c:if test="${viewArm !=null}">
						<div class="col-12 col-md-6">
							<select name="parm" id="parm" value='${viewArm1}' class="form-control-sm form-control nselect" style="text-align: left;">
								<option value="nothing">ALL</option>
								<c:forEach var="item" items="${viewArm}"  varStatus="num">									
									<option value="${item[0]}">${item[1]}</option>
								</c:forEach>
							</select>
						</div>
						</c:if>
						
					</div>
				</div>				
            </div>
</c:if>

               
<c:if test="${listyear !=null}">                   	
             <div class="col-md-12">
				<div class="col-md-6">
					<div class="row form-group">
						<div class="col col-md-6">
							<label for="text-input" class=" form-control-label">Select Year [Year extracted from Date of SD letter]</label>
						</div>
						<c:if test="${listyear !=null}">
						<div class="col-12 col-md-6">
							<select name="lyear" id="lyear" value='${listyear1}' class="form-control-sm form-control nselect" style="text-align: left;">
								<c:forEach var="item" items="${listyear}"  varStatus="num">									
									<option value="${item}">${item}</option>
								</c:forEach>
							</select>
						</div>
						</c:if>
						
					</div>
				</div>
				
				<div class="col-md-6" align="center">
						<div class="row form-group">
								<button class="btn btn-primary btn-sm" onclick="return searchReliefData();">Find Auth Letters</button>
						</div>
				</div>				
            </div>
</c:if>
	    			<div class="card-body">
    					<div class="col-md-12" id="report1" style="overflow: auto; display: none;">
	    			    	<table id="SearchReport" class="table no-margin table-striped  table-hover  table-bordered"  style="width: 100%;">
								<thead style="background-color: #9c27b0; color: white;font-size: 12px">
									<tr>
										<th >Auth Letter No</th>
										<th >Date</th>
										<th >Ser No</th>
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
											<td>${item[1]}</td>
											<td>${item[6]}</td>
											<td>${item[2]}</td>
											<td>${item[3]}</td>
											<td>${item[5]}</td>											
											<td><button onclick="open2('${item[0]}','${item[6]}');">OPEN</button></td>
										</tr>
									</c:forEach>
								</tbody>															
							</table>
						</div>	
					</div>
		</div>	
	</div>
</div>

<div class="modal" id="rejectModal" >
		<div class="modal-dialog" style="margin-left:250px!important" >
	      	<div class="modal-content" style="width:1200px!important;height:auto;overflow:auto;">	      	
		        <div class="modal-header">
		          <h4 class="modal-title">SD Relief Pgme</h4>
		          <button type="button" class="close" data-dismiss="modal"  onclick="closeModal();">&times;</button>
		        </div>
				<div class="modal-body" >
					<div class="form-group">	 
						<div class="col-md-12">
											
							<div class="row" style="color: maroon; font-size: 16px; font-weight: bold; ">
								<div class="col-sm-12" style="max-height:500px; overflow:Auto;">	
    	
									<input id="rejectid_model" name="rejectid_model" placeholder="" class="form-control" type ="hidden" >
									<input id="rejectid_model1" name="rejectid_model1" placeholder="" class="form-control" type ="hidden" >
											<span id="ip"></span>
        							<table class="table no-margin table-striped  table-hover  table-bordered" id="footnotesDetailsTbl" style="height: 100%; width:100%; font-size:12px; font-weight: bold;text-align:left;" >
						            	<thead>
								            	<tr>
												  	<th >S No</th>
													<th >Unit Name</th>
													<th >From</th>
													<th >To</th>
													<th >NMB Date</th>
													<th >Date of Move of Adv Party</th>
													<th >Mode of Tpt</th> 
													<th >Indn/De-Indn pd</th>
													<th >Remarks</th>
									    		</tr>
						               	</thead>
						                <tbody>
						               	</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>		 
			        <div class="modal-footer">
			          <!--  <button type="button" class="btn btn-primary" ><i class="fa fa-file" onclick="printpdf();"></i> Download</button> -->
			          <!-- <button type="button" class="btn btn-danger"  data-dismiss="modal" onclick="printpdf();"> Download</button>  &nbsp;  &nbsp; -->
			          <button type="button" class="btn btn-danger"  data-dismiss="modal" onclick="closeModal();"> Close</button>
			        </div>
				</div>
			</div>
		</div>
	</div>  

	<c:url value="new_gen_final_relief1" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="code_value1">		
		<input type="hidden" name="listyear1" id="listyear1" value="0"/>
		<input type="hidden" name="viewArm1" id="viewArm1" value="0"/>
	</form:form>

	<c:url value="Download_sdrelief_pgme" var="searchUrl" />
	<form:form action="${searchUrl}" method="post" id="searchForm1" name="searchForm1" modelAttribute="code_value1">		
		<input type="hidden" name="sddletter" id="sddletter" value="0"/>
	</form:form> 


<script type="text/javascript">


$(document).ready(function() {

	
	if('${listyear1}' !=="")
		{
		document.getElementById("lyear").value = '${listyear1}';
		$("#report1").show();
		}
	
	
	
});


function searchReliefData() {

	if ('${viewArm}' == null || '${viewArm}' == "")   
		$("#viewArm1").val("null");
	else
	$("#viewArm1").val(document.getElementById("parm").value);	
	
	$("#listyear1").val(document.getElementById("lyear").value);
	document.getElementById('searchForm').submit();	
}


var x;
function open2(sd_letter_no,serno){
		
	var modal = document.getElementById('rejectModal');
	$("#footnotesDetailsTbl tbody").empty();
	modal.style.display = "block";
	
	$("div#divwatermark").val('').addClass('watermarked'); 
	watermarkreport();
	
	$.post("getSDletterdetails?"+key+"="+value,  {authletno:sd_letter_no,sno:serno},function(j) {

		var markup = "";
		if(j.length > 0){					
			markup += "<tr><td colspan='11' style='color: maroon; font-size: 17px; font-weight: bold;' > SD-4 Letter No.  "+j[0][0].toUpperCase()+" dated "+j[0][1].toUpperCase()+"</td></tr>";			
			var a=0;
			var kk=j[0][20];
			for(var i=0;i<j.length;i++){	
				if (kk!=j[i][20])	{ a=0;  kk=j[i][20]; }
				a++;				
				var typeofcl="";
				var twoofcl="";
				if (j[i][19]!="NA" || j[i][19]!="")
					{
					typeofcl=" ("+j[i][19]+") ";
					}

				if (j[i][18]!="NA" || j[i][18]!="")
				{
				twoofcl=" ("+j[i][18]+") ";
				}				
				markup += "<tr align='center'><td>"+j[i][20]+"("+String.fromCharCode(a+'a'.charCodeAt(0)-1)+")"+"</td><td>"+j[i][3]+"</td><td>"+j[i][5]+twoofcl+"/ <br>"+j[i][4] +"</td><td>"+j[i][7]+typeofcl+"/ <br>"+j[i][6] +"</td><td>"+j[i][12]+"</td><td>"+j[i][14]+"</td><td>"+j[i][11]+"</td><td>"+j[i][13]+"</td><td align='left' width='200px'>"+j[i][17]+"</td></tr>";
			  }				
		}else{
			markup += "<tr align='center'><td colspan='5'>No Data Available</td></tr>";
		}
		$("#footnotesDetailsTbl tbody").append(markup);
	}); 
	

	x =sd_letter_no;
}

function closeModal(){
	var modal = document.getElementById('rejectModal');
	modal.style.display = "none";
}

function printpdf() {	

	$.post("Download_sdrelief_pgme?"+key+"="+value,{authletno:x},function(j) {
	});	
	var modal = document.getElementById('rejectModal');
		modal.style.display = "none";
}

function open1(auth_letter)
{  
	$("#report2").show();
	var tab = $("#SearchReport1 > tbody");
    tab.empty(); 
    var status = $("#status").val();
      
    $("#period_from1").val($("#period_from").val());
    $("#period_to1").val($("#period_to").val());
    $("#status1").val($("#status").val());
    $("#auth_letter1").val(auth_letter);
    $("#sus_no1").val($("#sus_no").val());
    $("#unit_name1").val($("#unit_name").val());
 	document.getElementById('searchForm').submit();
}


</script >
	
<!-- 

<script type="text/javascript">

$(document).ready(function() {
	
	if('${from_date1}' !=="")
		{
			$("#from_date").val('${from_date1}');
		}

	if('${to_date1}' !=="")
	{				
		$("#to_date").val('${to_date1}');
	}

	document.getElementById("armstatus").value = '${armstatus1}';
	
});

</script>
<script type="text/javascript">
function searchData()
{
	
	var df=$("#from_date").val();
	var dt=$("#to_date").val();
	var arm_status=document.getElementById("armstatus").value;
	
	if (df=="")  
	{
		alert("Date (from) is not Selected")
		return false;
	}
	
	if (dt=="") 
	{
	alert("Date (to) is not Selected")
	return false;
	}
     
	if (arm_status=="") 
	{
	alert("Please select Arm")
	return false;
	}

    $("#from_date1").val($("#from_date").val());
    $("#to_date1").val($("#to_date").val());
    $("#armstatus1").val($("#armstatus").val());
 	document.getElementById('searchForm').submit();
	
	}

</script>   -->