
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<form:form name="#" id="#" method='POST' commandName="ConvertRequestGstosplVehInsertDTLCMD">
	<div class="animated fadeIn">
		<div class="">
			<div class="container" align="center">
				<div class="card">
					<div class="card-header">
						<strong style="text-decoration: underline;">RESTRICTED</strong>
					</div>
					<div class="card-header">
						<strong><h3>
								MONTHLY STRENGTH RETURN ANIMALS MONTH OF <label for="text-input"
									id="lbladd" name="lbladd" class=" form-control-label"></label>
							</h3></strong>
					</div>
					<div class="card-header">
						<strong>Basic Details</strong>
					</div>
				<div class="card-body card-block">
						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label"><strong style="color: red;">* </strong>SUS No</label>
									</div>
									<div class="col-12 col-md-8">
									    <input type="text" id="sus_no" name="sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8">
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Date (From) </label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="from_date" name="from_date" class="form-control" class="form-control" min='1899-01-01' max='${date}' value="${from_date}">
									</div>
								</div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Unit Name</label>
									</div>
									<div class="col-12 col-md-8">
									    <input type="text" id="unit_file_def" name="unit_file_def" placeholder="" class="form-control autocomplete" autocomplete="off" >
									</div>
								</div>
							</div>

							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Fmn</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="text" id="fmn" name="fmn" placeholder="fmn" class="form-control autocomplete" autocomplete="off" >
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-6"></div>
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label for="text-input" class=" form-control-label">Return As</label>
									</div>
									<div class="col-12 col-md-8">
										<input type="date" id="re_date" name="re_date" class="form-control" class="form-control" min='1899-01-01' max='${date}' value="${re_date}" onchange="return checkdate(this,from_date)">
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="form-control card-footer" align="center">
						<a href="Animal_Dog" type="reset" class="btn btn-success btn-sm"> Clear </a>
						<i class="fa fa-search"></i><input type="button" class="btn btn-primary btn-sm" value="Search" onclick="Search();">
						<input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();" disabled>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container" id="divPrint">
		<div id="divShow" style="display: block;"></div>

		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>
			<table id="getSearchReportAnimalDogtbl"
				class="table no-margin table-striped  table-hover  table-bordered report_print"
				style="display: none;">
				<thead
					style="background-color: #9c27b0; color: white; text-align: center;">
					<tr>
						<th width="10%">Ser No</th>
						<th width="40%">Specialty</th>
						<th width="10%">UE</th>
						<th width="10%">UH</th>
						<th width="10%">Sur</th>
						<th width="10%">Defi</th>
						<th width="10%">Total</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list.size() == 0}">
						<tr>
							<td align="center" style="color: red;">Data Not Available</td>
						</tr>
					</c:if>
					<c:forEach var="item1" items="${list}" varStatus="num">
						<tr>
							<td width="10%" style="text-align: center;">${num.index+1}</td>
							<td width="40%">${item1[0]}</td>
							<td width="10%" style="text-align: center;">${item1[1]}</td>
							<td width="10%" style="text-align: center;">${item1[2]}</td>
							<td width="10%" style="text-align: center;">${item1[3]}</td>
							<td width="10%" style="text-align: center;">${item1[4]}</td>
							<td width="10%" style="text-align: center;">${item1[5]}</td>
						</tr>
					</c:forEach>
					<tr id="total">
						<td width="50%" colspan="2"><B>Total</B></td>
						<td width="10%" align="center"><B>${sumUE}</B></td>
						<td width="10%" align='center'><B>${totalUH}</B></td>
						<td width="10%" align="center"><B>${totalsur}</B></td>
						<td width="10%" align='center'><B>${totaldefi}</B></td>
						<td width="10%" align='center'><B>${totalall}</B></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="card">
					<div class="card-body">
						<table class="col-md-12">
							<tbody style="overflow: hidden;">
								<tr>
									<td align="left" style="font-size: 15px"><label>
											Prepared By :<br> Station :<br> Date :
									</label></td>
									<td align="center" style="font-size: 15px"><label>Checked
											By : </label></td>
									<td align="center" style="font-size: 15px"><label>Approved
											By : </label><br></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script>
$(function() {
	 // Unit SUS No
    $("#sus_no").keyup(function(){
    	var sus_no = this.value;
    	var susNoAuto=$("#sus_no");
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
    	        	  alert("Please Enter Approved Unit SUS No.");
    	        	  document.getElementById("unit_file_def").value="";
    	        	  susNoAuto.val("");	        	  
    	        	  susNoAuto.focus();
    	        	  return false;	             
    	          }   	         
    	    }, 
    		select: function( event, ui ) {
    			var sus_no = ui.item.value;			      	
    			 $.post("getActiveUnitNameFromSusNo?"+key+"="+value, {
    				 sus_no:sus_no
             }, function(j) {
                    
             }).done(function(j) {
            	 var length = j.length-1;
                 var enc = j[length].substring(0,16);
                 $("#unit_file_def").val(dec(enc,j[0]));   
                     
             }).fail(function(xhr, textStatus, errorThrown) {
             });
    		} 	     
    	});	
    });
	 
    $("#unit_file_def").keyup(function(){
		var unit_name = this.value;
	var unit_nameAuto=$("#unit_file_def");

	unit_nameAuto.autocomplete({
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
                        var dataCountry1 = susval.join("|");
                        var myResponse = []; 
	                    var autoTextVal=unit_nameAuto.val();
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
	        	  alert("Please Enter Approved Unit Name.");
	        	  document.getElementById("sus_no").value="";
	        	  unit_nameAuto.val("");	        	  
	        	  unit_nameAuto.focus();
	        	  return false;	             
	          }   	         
	      }, 
	      select: function( event, ui ) {
	    	  var target_unit_name = ui.item.value;
	    		 $.post("getTargetSUSFromUNITNAME?"+key+"="+value, {
	    			 target_unit_name:target_unit_name
             }, function(j) {
                    
             }).done(function(j) {
            	 var length = j.length-1;
                 var enc = j[length].substring(0,16);
                 $("#sus_no").val(dec(enc,j[0]));   
                     
             }).fail(function(xhr, textStatus, errorThrown) {
             });
	      } 	     
	}); 			
});
});	
</script>
<script>
function printDiv() 
{
	var a = ["MONTHLY STRENGTH RETURN ANIMALS MONTH OF  "]
	var b =  document.getElementById('lbladd').innerHTML;
    var c = a + b;
 	var printLbl = ["SUS NO :", "FROM DATE :","UNIT NAME :","FMN :","RETURN AS :"];
 	var printVal = [document.getElementById('sus_no').value,document.getElementById('from_date').value,document.getElementById('unit_file_def').value,document.getElementById('fmn').value,document.getElementById('re_date').value]; 	
 	printDivOptimize('divPrint',c,printLbl,printVal,"");
}
</script>

<Script>
	$(document).ready(function() {	
		
		if('${list.size()}' == 0){
			 $("#total").hide();
			 $("#printId").hide();
		}
		
		if('${sus_no1}' != ""){
				$("#sus_no").val('${sus_no1}');
				$("#unit_name").val('${unit_name1}');
				$("#unit_file_def").val('${unit_file_def1}');
				$("#fmn").val('${fmn1}');
				$("#from_date").val('${from_date1}');
				$("#re_date").val('${re_date1}');
				$('#printDiv').show();
				$("div#divwatermark").val('').addClass('watermarked');								
				watermarkreport(); 
				document.getElementById("printId").disabled = false;
				$("#getSearchReportAnimalDogtbl").show();
			}
	
		var date = new Date();
		var f = date.getMonth()
		var monthName;

		switch (f) {
		case 0:
			monthName = "JAN";
			break;
		case 1:
			monthName = "FEB";
			break;
		case 2:
			monthName = "MAR";
			break;
		case 3:
			monthName = "APR";
			break;
		case 4:
			monthName = "MAY";
			break;
		case 5:
			monthName = "JUN";
			break;
		case 6:
			monthName = "JUL";
			break;
		case 7:
			monthName = "AUG";
			break;
		case 8:
			monthName = "SEP";
			break;
		case 9:
			monthName = "OCT";
			break;
		case 10:
			monthName = "NOV";
			break;
		case 11:
			monthName = "DEC";
			break;
		}
		document.getElementById('lbladd').innerHTML = monthName + " " + date.getFullYear();
	});
 </Script>


<Script>
		function Search(){
			 if ($("input#sus_no").val() == "") {
					alert("Please Select SUS No");
					return false;
				}
			 else{
				    $("#sus_no1").val($("#sus_no").val());
				    $("#unit_name1").val($("#unit_name").val());
				    $("#unit_file_def1").val($("#unit_file_def").val());
				    $("#fmn1").val($("#fmn").val());
				    $("#from_date1").val($("#from_date").val());
				    $("#re_date1").val($("#re_date").val());
					document.getElementById('searchForm').submit();
			 }
		}
		
		function checkdate(obj,from_date) {
			if(from_date.value !="")
			{
				var id = obj.id;
				var myDate = document.getElementById(id).value;
				var Date1 = from_date.value;
				if ((Date.parse(myDate) < Date.parse(Date1))) {
					alert('Return As should not be less than From Date');
					$("input#re_date").focus();
					obj.value = "";
				}
			}
		}  
</Script>

<c:url value="Dogreportlist" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm" name="searchForm" modelAttribute="sus_no1">
	<input type="hidden" name="sus_no1" id="sus_no1" value="0" />
	<input type="hidden" name="unit_name1" id="unit_name1" value="0" />
	<input type="hidden" name="unit_file_def1" id="unit_file_def1" value="0" />
	<input type="hidden" name="fmn1" id="fmn1" value="0" />
	<input type="hidden" name="from_date1" id="from_date1" value="0" />
	<input type="hidden" name="re_date1" id="re_date1" value="0" />
</form:form>
