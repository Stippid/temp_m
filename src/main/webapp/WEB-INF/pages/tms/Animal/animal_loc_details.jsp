	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
	<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script> 
	
	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	
	<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
	<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
	<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="js/cue/cueWatermark.css">
	<script src="js/cue/cueWatermark.js"></script>
	<script src="js/cue/printAllPages.js" type="text/javascript"></script>
	
	<div class="animated fadeIn" id="printableArea">
	<div onFocus="parent_disable1();" onclick="parent_disable1();">
			<div class="col-md-12" align="center">
				<div class="card">
					<div class="card-header">
						<strong style="text-decoration: underline;">RESTRICTED</strong>
					</div>
					<div class="card-header">
						<div class="col-md-2">
							<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 85px;">
						</div>
						<div class="col-md-8">
							<h3>LOCATION OF ANIMAL DETAILS</h3>
						</div>
						<div class="col-md-2">
								<img src="js/miso/images/dgis-logo.png" style="height: 85px;">
						</div>
					</div>
					<div class="card-body card-block">
						<div class="col-md-5">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Army No </label>
								</div>
	
	
								<div class="col-12 col-md-8">
									<label id="army_num">${army_num}</label>
								</div>
	
							</div>
						</div>
						<div class="col-md-5 col-md-offset-1">
							<div class="row form-group">
								<div class="col col-md-4">
									<label class=" form-control-label">Microchip No </label>
								</div>
								<div class="col-12 col-md-8">
									<label id="microchip_no">${microchip_no}</label>
									<input type="hidden" id="anml_type" name="anml_type" class="form-control">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
		<div class="col-md-12" style="overflow: auto;">
				<div class="col-md-12" id="divPrint">
					<div id="divShow" style="display: block;"></div>
					<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
						<span id="ip"></span>
						<table id="SearchAnimalReaport" class="table no-margin table-striped  table-hover  table-bordered report_print">
							<thead style="background-color: #9c27b0; color: white; text-align: center;">
								<tr>
									<th width="5%">Ser No</th>
									<th width="5%">Army Dog No</th>
									<th width="10%">Unit Name</th>
									<th width="8%">Microchip No</th>
									<th width="10%">Name</th>
									<th width="5%">Sex</th>
									<th width="5%">Age</th>
									<th width="10%">Type of Dog</th>
									<th width="10%">Specialisation</th>
									<th width="12%">Medal Details</th>
									<th width="5%">TOS</th>
									<th width="5%">TORS</th>
									<th width="5%">SOS</th>
									<th id = "thact1" width="5%">Action</th>
								</tr>
							</thead>
							<tbody >
							<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
								    <td width="5%" style="text-align: center;">${num.index+1}</td>
									<td width="5%" style="text-align: center;">${item[0]}</td>
									<td width="10%" >${item[1]}</td>
									<td width="8%" style="text-align: center;">${item[2]}</td>
									<td width="10%">${item[3]}</td>
									<td width="5%">${item[4]}</td>
									<td width="5%" style="text-align: center;">${item[5]}</td>
									<td width="10%">${item[6]}</td>
									<td width="10%" >${item[7]}</td>
									<td width="12%" >${item[8]}</td>
									<td width="5%" style="text-align: center;">${item[9]}</td>
									<td width="5%" style="text-align: center;">${item[10]}</td>
									<td width="5%" style="text-align: center;">${item[11]}</td> 
									<td id = "tdact" width="5%" style="text-align: center;">${item[13]}</td> 
								</tr>
							</c:forEach>
							</tbody>
						</table>
					</div>
				
					<div class="row">
						<div  class="col-md-12">
							<div class="card">
								<div class="card-body"> 
					   				<table class="col-md-12">
					   				<tbody style="overflow: hidden;">
					   					<tr>
					   						<td align="left" style="font-size: 15px">
					   							<label>
													Prepared By :<br>
													Station :<br>
													Date :
												</label>	
					   						</td>
					   						<td align="center" style="font-size: 15px">
					   							<label>Checked By : </label>
					   						</td>
					   						<td align="center" style="font-size: 15px">
					   							<label>Approved By : </label><br> 
					   						</td>
					   					</tr>
					   					</tbody>
					   				</table>
					   			</div> 
				    		</div>
				   		</div>
			   		</div>
				</div>
		</div>
	
	
	<div class="">
		<div class="container" align="center">
			<input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();"> 
		</div>
	</div>
   </div>	
  </div>	

	<script>
		function printDiv() {
			$('td#tdact').hide();
			$('th#thact1').hide();
			var printLbl = [ "ARMY NO  :", "MICROCHIP NO :" ];
			var army_num = '${army_num8}';
			var microchip_no = $("#microchip_no").text();
			var printVal = [ army_num, microchip_no ];
			printDivOptimize('divPrint','Location of Animal Details', printLbl,printVal, "");
		}
	</script>
	
	<c:url value="Show_Animal_loc_Details" var="editurl123" />
	<form:form action="${editurl123}" method="post" id="editForm1" name="editForm1" modelAttribute="editId" target="result">
		<input type="hidden" name="editId" id="editId" value="0" />
		<input type="hidden" name=anml_type13 id="anml_type13" value="0" />
	</form:form>
	<script>
		function open1(id) {
			var x = screen.width/2 - 1100/2;
		    var y = screen.height/2 - 900/2;
			newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');	 
			$("#anml_type13").val($("#anml_type").val());
			document.getElementById('editId').value = id;
			document.getElementById('editForm1').submit();
		}
		
		function parent_disable1() {
			if(newWin && !newWin.closed)
				newWin.focus();
		}
	</script>
	
	<script>
		$(document).ready(function() {
		
			$("#anml_type").val('${anml_type8}');
			$("#army_num").text('${army_num8}');
			$("div#divwatermark").val('').addClass('watermarked');								
			watermarkreport(); 
				$('#printDiv').show();
				var army_num ='${army_num8}'
				 $("#army_num").text(army_num);
				$("#microchip_no").text('${microchip_no8}');
				getmicro(army_num);
				
				function getmicro(val) {	
		                    $.post("getmicrochipno?"+key+"="+value, {
		                    	army_num : army_num
		                }, function(j) {
		                        //        alert(data);
		                        //var json = JSON.parse(data);
		                        //...

		                }).done(function(j) {
		                       // alert(j);
		                
		                        for (var i = 0; i < j.length; i++) {
									   var length = j.length-1;
				 	                   var enc = j[length].substring(0,16); 
				 	                   $("#microchip_no").text(dec(enc,j[0])); 
								}
		                        
		                }).fail(function(xhr, textStatus, errorThrown) {
		                });
				} 
		});
    </script>