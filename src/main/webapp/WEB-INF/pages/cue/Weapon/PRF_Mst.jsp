<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script type="text/javascript"
	src="js/AES_ENC_DEC/lib/excluded/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>

<script src="js/JS_CSS/jquery.dataTables.min.js"></script>
<script src="js/JS_CSS/dataTables.bootstrap.min.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/Calender/jquery-ui.css" />
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>

<script src="js/cue/update.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>

<script src="js/cue/printAllPages.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>

<script>
$(function() {
           var wepetext=$("#prf_group");
        
     	  wepetext.autocomplete({
     	      source: function( request, response ) {
     	        $.ajax({
   	        	type: 'POST',
   		  	    url: "getprfList?"+key+"="+value,
     	        data: {prf_group:document.getElementById('prf_group').value},
     	          success: function( data ) {
     	            //response( data );
     	            if(data.length > 1){
     	        	 var susval = [];
     	        	var length = data.length-1;
 	        		 var enc = data[length].columnName1.substring(0,16);
                     for(var i = 0;i<data.length-1;i++){
                      susval.push(dec(enc,data[i].columnName));
	        	  	}
   		        	var dataCountry1 = susval.join("|");

     	           var myResponse = []; 
     	            var autoTextVal=wepetext.val();
     				$.each(dataCountry1.toString().split("|"), function(i,e){
     					var newE = e.substring(0, autoTextVal.length);
     				if (newE.toLowerCase() === autoTextVal.toLowerCase()) {
   					//if (e.toLowerCase().includes(autoTextVal.toLowerCase())) {
     					  myResponse.push(e);
     					}
     				});      	          
     				response( myResponse ); 
     				}
     	          }
     	        });
     	      },
     	      minLength: 1,
     	      autoFill: true,     	     
     	    });
      });
</script>

<form:form action="prf_mstAction" method="post" class="form-horizontal"
	commandName="prf_mstCMD" name="prf_mstname">
	<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<h5>
						<b>PRF MASTER</b><br> <span
							style="font-size: 10px; font-size: 15px; color: red">(To
							be entered by MISO)</span>
					</h5>
					</h5>
				</div>
				<div class="card-body card-block cue_text">
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">COS Section <strong
										style="color: red;">*</strong></label>
								</div>
								<div class="col-4 col-md-2" style="padding-right: 0px;">
									<input type="text" id="cos_ch" name="cos_ch"
										class="form-control" maxlength="1"
										onkeypress="return blockSpecialChar(event);"
										onkeyup=" return only_Alphbets();"
										onchange="clearDescription()">
								</div>
								<div class="col-4 col-md-2">
									<input type="text" id="cos" name="cos" value="-"
										readonly="readonly" class="form-control">
								</div>
								<div class="col-4 col-md-2" style="padding-left: 0px;">
									<input type="text" id="cos_int" name="cos_int"
										class="form-control" maxlength="2"
										onkeypress="return validateNumber(event, this);"
										onchange="prf_code();">
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">PRF Group <strong
										style="color: red;">*</strong></label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="prf_group" name="prf_group"
										maxlength="150" class="form-control"
										onkeypress="return blockSpecialChar(event)"
										style="font-family: 'FontAwesome', Arial;"
										placeholder="&#xF002; Search">
								</div>

							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">PRF Group Code</label>
								</div>
								<div class="col-12 col-md-6">
									<input type="text" id="prf_group_code" name="prf_group_code"
										maxlength="8" class="form-control" readonly="readonly">
								</div>

							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label class=" form-control-label">Nodal Dte <strong
										style="color: red;">*</strong></label>
								</div>
								<div class="col-12 col-md-6">
									<select name="nodal_dte" id="nodal_dte" class="form-control">
									</select>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-12">
						<div class="col-md-6">
							<div class="row form-group">
								<div class="col col-md-6">
									<label for="text-input" class=" form-control-label">Remarks</label>
								</div>
								<div class="col-12 col-md-6">
									<textarea id="remarks" name="remarks" maxlength="255"
										class="form-control char-counter1"></textarea>
								</div>
							</div>
						</div>
					</div>


				</div>


				<div class="card-footer" align="center">
					<input type="reset" class="btn btn-success btn-sm" value="Clear"
						onclick="clearAll();"> <input type="submit"
						class="btn btn-primary btn-sm" value="Save"
						onclick="return isvalidation();"> <i class="fa fa-search"></i><input
						type="button" class="btn btn-primary btn-sm" onclick="Search();"
						value="Search"> <i class="fa fa-print"></i><input
						type="button" id="printId"
						class="btn btn-primary btn-sm btn_report" value="Print"
						onclick="printDiv();" disabled>
				</div>
			</div>
		</div>
	</div>

</form:form>
<div style="width: 20%; padding-left: 1%; display: none;"
	id="divSerachInput">
	<input id="searchInput" type="text"
		style="font-size: 12px; font-family: 'FontAwesome', Arial; margin-bottom: 5px;"
		placeholder="&#xF002; Search Word" size="35" class="form-control">
</div>
<div class="col s12" id="divPrint" style="display: none;">
	<div id="divShow" style="display: block;"></div>
	<div class="watermarked" data-watermark="" id="divwatermark"
		style="display: block;">
		<span id="ip"></span>
		<table id="PrfReport"
			class="table no-margin table-striped  table-hover  table-bordered  report_print">
			<thead>
				<tr>
					<th class="tableheadSer">Ser No</th>
					<th>PRF Group</th>
					<th>COS Section</th>
					<th>Nodal Dte</th>
					<th class='lastCol'>Action</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}" varStatus="num">
					<tr>
						<td class="tableheadSer">${num.index+1}</td>
						<td>${item.prf_group}</td>
						<td>${item.coss_section}</td>
						<td>${item.label}</td>
						<td id="thAction1" class='lastCol'>${item.id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<c:url value="prf_mstUrl1" var="searchUrl" />
<form:form action="${searchUrl}" method="post" id="searchForm"
	name="searchForm" modelAttribute="prf_group1">
	<input type="hidden" name="prf_group1" id="prf_group1" value="0" />
	<input type="hidden" name="coss_section1" id="coss_section1" value="0" />
	<input type="hidden" name="nodal_dte1" id="nodal_dte1" value="0" />
	<input type="hidden" name="status1" id="status1" value="0" />
</form:form>
<c:url value="updateprf_mstUrl" var="updateUrl" />
<form:form action="${updateUrl}" method="post" id="updateForm"
	name="updateForm" modelAttribute="updateid" target="result">
	<input type="hidden" name="updateid" id="updateid" value="0" />
</form:form>
<script>

function Search(){
 	$("#prf_group1").val($("#prf_group").val());
    $("#coss_section1").val($("#coss_section").val());
    $("#nodal_dte1").val($("#nodal_dte").val());
    $("#status1").val($("#status").val());
    document.getElementById('searchForm').submit();
}
var newWin;
function editData(id){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
   
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');
	 window.onfocus = function () { 
		 //newWin.close();
	 }

	document.getElementById('updateid').value=id;
	document.getElementById('updateForm').submit();
}
function closeWindow()
{
	newWin.close();   
}
function deleteData(id){
	$.post("deleteprf_mstUrlAdd?"+key+"="+value, {deleteid : id}).done(function(j) {
		alert(j);
		Search();
	  }).fail(function(xhr, textStatus, errorThrown) { }); 	
}					
</script>
<script>
function printDiv() 
  	{
	 	var printLbl = ["PRF Group :"];
	 	var printVal = [document.getElementById('prf_group').value];
	 	printDivOptimize('divPrint','PRF MASTER',printLbl,printVal,"select#status");
 	 }
</script>
<script>
var size;
$(document).ready(function() {
    	   if('${prf_group1}' != "")
    		{
    		   $("#prf_group").val('${prf_group1}');
    		   
    		}		
    		if('${list.size()}' == 0 ){
      			$("div#divSerachInput").hide(); 
      			document.getElementById("printId").disabled = true;	
      			$("table#PrfReport").append("<tr><td colspan='5' style='text-align :center;'>Data Not Available</td></tr>");
    		}else{
      		 	$("div#divwatermark").val('').addClass('watermarked'); 
      		 	watermarkreport();
        		$("#divPrint").show();
        		$("div#divSerachInput").show();
        		document.getElementById("printId").disabled = false;
      		}	
      		 	
    		
    	   $("#searchInput").on("keyup", function() {
     			var value = $(this).val().toLowerCase();
     			$("#PrfReport tbody tr").filter(function() { 
     			$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
     			});
     		});
    	   $('#remarks').keyup(function() {
   	        this.value = this.value.toUpperCase();
   	    });
    	   
    	   $.post("getnodal_dirList?"+key+"="+value).done(function(j) {
   		   	var length = j.length-1;
  				var enc = j[length].columnName1.substring(0,16); 
   		   var options = '<option value="' + "0" + '">'+ "--Select--" + '</option>'; 
				for ( var i = 0; i < j.length-1; i++) {
					 options += '<option value="' + dec(enc,j[i].columnCode)+ '" >'+ dec(enc,j[i].columnName)+ '</option>';  					
				}	
				$("select#nodal_dte").html(options);  				
			 }).fail(function(xhr, textStatus, errorThrown) { }); 	
   	   
    	   
    	   try{
    		    if(window.location.href.includes("msg="))
   			{
   				var url = window.location.href.split("?msg=")[0];
   			var m=  window.location.href.split("?msg=")[1];
   			window.location = url;
   			
   			if(m.includes("Updated+Successfully")){
   				window.opener.getRefreshReport('prf_weap',m);
   				window.close('','_parent','');
   			}
   			} 	
    		}
    		catch (e) {
    			// TODO: handle exception
    		}   
       });
            
       function validateNumber(evt) {
    	    var charCode = (evt.which) ? evt.which : evt.keyCode;
    	    if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57)) { 
    	            return false;
    	    } else {
    	        if (charCode == 46) {
    	                return false;
    	        }
    	    }
    	    return true;
    	}
       function blockSpecialChar(e){
       
    	   $('#prf_group').keyup(function() {
   	        this.value = this.value.toUpperCase();
   	    });
    	   
    	   $('#cos_ch').keyup(function() {
      	        this.value = this.value.toUpperCase();
      	    }); 
       }
       function clearAll()
       {	
    	   document.getElementById('divPrint').style.display='none';
    	   document.getElementById("printId").disabled = true;
       	 var tab = $("#PrfReport"); 
       	tab.empty();
       	$("#searchInput").val("");
       	$("#searchInput").hide();
      //document.location.reload();
     	localStorage.clear();
    	localStorage.Abandon();
       }   
       
       function clearDescription(){
    		 document.getElementById('prf_group_code').value = "";
    		 $("input#cos_int").val("");
    	}
       
       function isvalidation(){    	   
    	   if($("input#cos_ch").val()=="" ){
   			alert("Please Enter COS Section")
   			 $("input#cos_ch").focus(); 
   			return false;
   		}
    	   if($("input#cos_int").val()=="" ){
      			alert("Please Enter COS Section")
      			 $("input#cos_int").focus(); 
      			return false;
      		}else
   				{
   				var cos=$("input#cos_int").val();
   				if(cos.length==1 )
   					{
   				alert("Please Enter Two-Digit No");
   				 $("input#cos_int").focus();
   				return false;
   					}
   		         }
    	   if($("input#prf_group").val()=="" ){
 			alert("Please Enter PRF Groups ")
 			$("input#prf_group").focus();
 			return false;
 		} 
    	  if($("select#nodal_dte").val()== '0'){
    			alert("Please Select Nodal Dte")
    			$("select#nodal_dte").focus();
    			return false;
    		} 
    	   return true;
       }
function prf_code(){	  
	   
	var cos1= document.getElementById('cos_ch').value;
	   var cos2= document.getElementById('cos_int').value;
	   if(cos2.length < 2){
		   alert("Please enter min 2 digits");
		   document.getElementById('cos_int').value="";
		   $("#cos_int").focus();
	   }
	   else{
		   var cos = cos1+cos2;
		   $.post("getprf_code?"+key+"="+value, {cos:cos}).done(function(j) {
				document.getElementById("prf_group_code").value = j;
		   }).fail(function(xhr, textStatus, errorThrown) { }); 
	   }
}
  	 function only_Alphbets(){  		
  		var regex = /^[a-zA-Z]*$/;
  	  	if (regex.test(document.prf_mstname.cos_ch.value)) {	
  	      return true;
  	  } else {
  	      alert("Alphabets Only");
  	      document.getElementById('cos_ch').value="";
  	      return false;
  	  	}
  	 }
   </script>
