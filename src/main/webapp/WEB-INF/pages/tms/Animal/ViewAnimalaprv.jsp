	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	
	<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="js/cue/cueWatermark.css">
	<script src="js/cue/cueWatermark.js"></script>
	<script src="js/cue/printAllPages.js" type="text/javascript"></script>
	
		<div class="animated fadeIn" id="printableArea">
			<div class="container">
				<div class="card">
					<div class="card-header" align="center">
						<strong style="text-decoration: underline;">RESTRICTED</strong>
					</div>
					<div class="card-header">
						<div class="col-md-2">
							<img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 85px;">
						</div>
						<div class="col-md-8">
							<h3 style="text-align:center;">APPROVED ANIMAL DETAILS </h3>
						</div>
						<div class="col-md-2">
								<img src="js/miso/images/dgis-logo.png" style="height: 85px;">
						</div>
					</div>
					
					<div class="card-body card-block">
						<div class="col-md-12 col-sm-12 col-12">
							<div class="col-md-6 col-sm-6 col-6">
								<div class="row form-group">
									<div class="col-sm col-md-4">
										<label class=" form-control-label">SUS No </label>
									</div>
									<div class="col-12 col-md-8">
										<label id="sus_no" >${sus_no}</label>
									</div>
								</div>
							</div>
							
							<div class="col-md-6">
								<div class="row form-group">
									<div class="col col-md-4">
										<label class=" form-control-label">Unit Name </label>
									</div>
									<div class="col-12 col-md-8">
										<label id="unit_name" >${unit_name}</label>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
	<div class="animated fadeIn" style="overflow: auto;">
		
			 <div class="container" id="divPrint">
			  <div id="divShow" style="display: block;"></div> 	 
			      <div  class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
					<span id="ip"></span>					 
						<table id="SearchAnimalReaport" class="table no-margin table-striped  table-hover  table-bordered report_print" width ="100%">
							<thead style="background-color: #9c27b0; color: white;text-align: center;">
						     <tr>
							        <th width="5%">Ser No</th>
									<th width="10%">Army No / Remount No</th>
									<th width="10%">Type of Dog / Equines</th>
									<th width="10%">Microchip No</th>
									<th width="10%">Name</th>
									<th width="5%">Age</th>
									<th width="10%">Color</th>
									<th width="10%">Sex</th>
									<th width="10%">Specialisation</th>
									<th width="10%">TOS</th>
									<th width="10%">Fitness Status</th>
							</tr> 					
							</thead> 
							<tbody>
							
							<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
								    <td width="5%" style="text-align: center;">${num.index+1}</td>
									<td width="10%" style="text-align: center;">${item[0]}</td>
									<td width="10%" style="text-align: left;">${item[1]}</td>
									<td width="10%" style="text-align: center;">${item[2]}</td>
									<td width="10%" style="text-align: left;">${item[3]}</td>
									<td width="5%" style="text-align: center;">${item[4]}</td>
									<td width="10%" >${item[5]}</td>
									<td width="10%" >${item[6]}</td>	
									<td width="10%" >${item[7]}</td>
									<td width="10%" style="text-align: center;">${item[8]}</td>
									<td width="10%" >${item[9]}</td>
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
</div>
				
	<div class="animated fadeIn">
			<div class="container" align="center">
				<div class="col-md-12" align="center">
					<input type="button" id="printId" class="btn btn-primary btn-sm btn_report" value="Print" onclick="printDiv();">
				</div>
			</div>
	</div>
<script>
function printDiv() 
{ 
 	var printLbl = ["SUS NO :", "UNIT NAME :"];
    var sus_no = '${sus_no}';
   	var unit_name = '${unit_name}';  
 	var printVal = [sus_no,unit_name]; 	
 	printDivOptimize('divPrint','Approved Animal Details',printLbl,printVal,"");
}
</script>			
			
<script type="text/javascript">
		jQuery(document).ready(function() {
			$("div#divwatermark").val('').addClass('watermarked');								
			watermarkreport(); 
			$('#printDiv').show();
			var sus_no = $("#sus_no").val('${sus_no13}');
			$("#unit_name").val('${unit_name13}');
		});
</script>
	
