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





<form name="AuditUpload" id="AuditUpload" action="AuditUploadAction?${_csrf.parameterName}=${_csrf.token}" method="POST" enctype="multipart/form-data">
	<div class="">
	    <div class="container" align="center">
	    	<div class="card">
	    	
	    		<div class="card-header mms-card-header">
		    		<b> AUDIT UPLOAD </b>
	    		</div>
	    		
	    		<div class="card-body card-block">
<!-- 	    		FIRST ROW -->
					<div id="unitSusDiv">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label class=" form-control-label"><strong style="color: red;">* </strong> Unit Name </label>
							</div>
							<div class="col-md-8">
								<input style="display: none" type="text" id="unit_name" name="unit_name" class="form-control autocomplete" autocomplete="off" maxlength="50">
								<label id="unit_name_l" style="display: none"><b>${unit_name}</b></label>
							</div>
						</div>
					</div>
					<input id="roleSusTemp" name="roleSusTemp" type="hidden" value="${roleSusTemp}">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col-md-3">
								<label class=" form-control-label"><strong style="color: red;">* </strong> Unit SUS No </label>
							</div>
							<div class="col-md-8">
	                 			<input style="display: none" type="text" id="unit_sus_no" name="unit_sus_no" class="form-control autocomplete" autocomplete="off" maxlength="8" onkeypress="return AvoidSpace(event)" onkeyup="return specialcharecter(this)">
	                 			<label id="unit_sus_no_hid" style="display: none" ><b> ${roleSusNo} </b></label> 
							</div>
						</div>
					</div>
					</div>

	    			<div class="col-md-6">
						<div class="col-md-3">
							<label class="form-control-label"><strong style="color: red;">* </strong>Upload Audit Document: </label>
						</div>
						<div class="col-md-8">
							<input type = "file" id="u_file" name="u_file" />
							<input type = "hidden" id="id" name="id" />
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="col-md-3">
							<label class=" form-control-label">Type Of Audit: </label>
						</div>
						<div class="col-md-8">
							<select name="type_of_audit" id="type_of_audit" class="form-control" >
								<option value="0">--Select--</option>
								<option value="Internal">Internal</option>
								<option value="External">External</option>
							</select>
						</div>
					</div>
				</div>
<!-- 					SECOND ROW -->
				<div class="card-body card-block">
					<div class="col-md-6">
						<div class="col-md-3">
							<label class=" form-control-label">Cyber Audit Done By: </label>
						</div>
						<div class="col-md-8">
							<input type="text" id="audit_done_by" name="audit_done_by" class="form-control autocomplete" autocomplete="off"></input>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="col-md-3">
							<label class=" form-control-label">Cyber Audit Date: </label>
						</div>
						<div class="col-md-8">
							<input type="text" name="audit_date" id="audit_date"
								maxlength="10" onclick="clickclear(this, 'DD/MM/YYYY')"
								class="form-control" style="width: 90%; display: inline;"
								onfocus="this.style.color='#000000'"
								onblur="clickrecall(this,'DD/MM/YYYY');"
								onkeyup="clickclear(this, 'DD/MM/YYYY')"
								onchange="clickrecall(this,'DD/MM/YYYY');"
								aria-required="true" autocomplete="off"
								style="color: rgb(0, 0, 0);">
						</div>
					</div>
				</div>
	    		
	    	</div>
	    	
	    	<div class="card-footer" align="center" class="form-control">
				<input type="submit" class="btn btn-success btn-sm" value="Submit">
			</div>
	    	
	    </div>
    </div>
	
	<div class="datatablediv" id="reportDiv">
			<div class="">
				<div class="watermarked" data-watermark="" id="divwatermark">
					<span id="ip"></span> 
					
					<table id="getSearch1" class="table no-margin table-striped table-hover table-bordered report_print" >
						<thead style="font-size: 15px; text-align: center;">
							<tr>
								<th>Ser No</th>
								<th>Unit Name</th>
								<th>Audit Date</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
					
				</div>
			</div>
		</div>
    
</form>

 <c:url value="AuditDownload" var="downloadUrl" />
<form:form action="${downloadUrl}" method="post" id="downloadForm" name="downloadForm" modelAttribute="id" >
	<input type="hidden" name="downloadid" id="downloadid" value="0" />
</form:form>

<c:url value="Excel_AuditReport" var="excelUrl" />
<form:form action="${excelUrl}" method="post" id="ExcelForm" name="ExcelForm" modelAttribute="computing_assets_dn1">
	<input type="hidden" name="asset_type3" id="asset_type3" value="0">
</form:form>

<script>
	$(document).ready(function() {
		datepicketDate('audit_date');
		var roleSusTemp = document.getElementById("roleSusTemp").value;
		if(roleSusTemp == ""){
			$('#unitSusDiv').show();
		}
		debugger
		var r =('${roleAccess}');
		if( r == "Unit"){
			 $("#unit_sus_no_hid").show();
			 $("#unit_name_l").show();
		}
		else  {
			 $("input#unit_sus_no").show();		 
			 $("input#unit_name").show();
		}
		
		var sus_nop = '${roleSusNo}';
	  	$.post("getTargetUnitNameList?"+key+"="+value, {sus_nop:sus_nop}).done(function(data) {
	  		var l=data.length-1;
	  		var enc = data[l].substring(0,16);    	   	 
	  	 	$("#unit_name").text(dec(enc,data[0]));
	  		}).fail(function(xhr, textStatus, errorThrown) {
		  });
	  	
	  	
	  	try{
			if(window.location.href.includes("msg=")){
				var url = window.location.href.split("?")[0];
			    window.location = url;
		    } 
		}catch (e) {
		}
	});
	
	 function downloadData(id){
		 $("#downloadid").val(id);
		document.getElementById('downloadForm').submit();
	}
	
	mockjax1('getSearch1');
	table = dataTable('getSearch1');
	
	function data(tableName) {
		jsondata = [];

		var table = $('#'+tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = info.length;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
		
		var u_file = $("#u_file").val();

		if (tableName == "getSearch1") {
		    $.post("AuditUploadCount?" + key + "=" + value,{Search:Search
			 },  function(j) {
				FilteredRecords = j;
			});
			$.post("AuditUploadData?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search:Search,
				orderColunm : orderColunm,
				orderType : orderType
			}, function(j) {

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
					  jsondata.push([sr, j[i].unit_name, j[i].last_cyber_audit_date, j[i].action]);
				}
			});
		}
		
	}
	
	
	
	function downloadData(id) {
		var pdfView="AuditDownload?id="+id;
		    fileName="TEST_DOC";
		    fileURL=pdfView;
		    if (!window.ActiveXObject) {
		        var save = document.createElement('a');
		        save.href = fileURL;
		        save.target = '_blank';
		        var filename = fileURL.substring(fileURL.lastIndexOf('/')+1);
		        save.download = fileName || filename;
			       if ( navigator.userAgent.toLowerCase().match(/(ipad|iphone|safari)/) && navigator.userAgent.search("Chrome") < 0) {
						document.location = save.href; 
					}else{
				        var evt = new MouseEvent('click', {
				            'view': window,
				            'bubbles': true,
				            'cancelable': false
				        });
				        save.dispatchEvent(evt);
				        (window.URL || window.webkitURL).revokeObjectURL(save.href);
					}	
		    }
		    else if ( !! window.ActiveXObject && document.execCommand)     {
		        var _window = window.open(fileURL, '_blank');
		        _window.document.close();
		        _window.document.execCommand('SaveAs', true, fileName || fileURL)
		        _window.close();
		    }
		//location.reload();
	}
	
	

	// Unit SUS No

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
		        	  document.getElementById("unit_name").value="";
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
	             $("#unit_name").val(dec(enc,j[0]));   
	                 
	         }).fail(function(xhr, textStatus, errorThrown) {
	         });
			} 	     
		});	
	});
	// unit name
	 $("input#unit_name").keypress(function(){
			var unit_name = this.value;
				 var susNoAuto=$("#unit_name");
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
				        	  document.getElementById("unit_name").value="";
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
	
	
</script>