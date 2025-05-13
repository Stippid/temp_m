<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="shortcut icon" href="js/miso/images/dgis-logo_favicon.png" > 
  	<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css"> 
  	<link rel="stylesheet" href="js/miso/assets/css/font-awesome.min.css">
	<link rel="stylesheet" href="js/miso/assets/scss/style.css">
	
	<script type="text/javascript" src="js/miso/assets/js/vendor/jquery-2.1.4.min.js"></script> 
	<script src="js/miso/assets/js/plugins.js"></script> 
	<script src="js/miso/assets/js/main.js"></script> 

<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
<!--  <script src="js/common/commonmethod.js" type="text/javascript"></script>  -->

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>
<!-- <link rel="stylesheet" href="js/miso/tmsDashboard/tmsDashboardCSS.css"> -->


<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
  <script src="js/Calender/jquery-2.2.3.min.js"></script>
  <script src="js/Calender/jquery-ui.js"></script>
  <script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">

<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
	src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<style>

.m{
background-color:#a129ad;
color:white;
}
.card-color{
 background-color:#f2f2f2;
  }
</style>
<div class="animated fadeIn">
	<div class="mt-1">
		<div class="col-md-12">
			<div class="card">
				<div class="card-header " align="center">
					<strong style="text-decoration: underline; ">CASUALTY UPLODED </strong>
				</div>			
			<div class="card-body">			
	 <div class="col-md-12 card-color">	  

	           				
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong>SUS NO</label>
						                </div>
						               
						                <div class="col-md-4">
						                <label id="susno">${getSus_no}</label>
						                
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong>UNIT NAME</label>
						                </div>
						                <div class="col-md-8">	
                                           <label id="unitname">${getUnit_name}</label>

										</div>
						            </div>
	              				</div>

	              			</div>              			
	 <div class="col-md-12 card-color" >	   
	           					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong>AUTH</label>
						                </div>						               
						                <div class="col-md-8">
						                <label id="Present_part">${getPresent_p2_no}</label>					                
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong>AUTH DATE</label>
						                </div>
						                <div class="col-md-8">											
                                           <label id="Present_part_date">${getPresent_p2_date}</label>					                          									 
										</div>
						            </div>
	              				</div>

	              			</div>
	  

	 <div class="col-md-12  card-color" >	   
	           					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> *</strong>ARMY NO</label>
						                </div>
						               
						                <div class="col-md-8">
						                <label id="army_no">${getArmyNo}</label>
						                
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;">* </strong>RANK</label>
						                </div>
						                <div class="col-md-8">
											
                                           <label class=" form-control-label" id="rank">${getRank}</label>
						                          
											 
										</div>
						            </div>
	              				</div>

	              			</div>
	              			 <div class="col-md-12 card-color" >	   
	           					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>NAME</label>
						                </div>
						               
						                <div class="col-md-8">
						                <label id="name">${getName}</label>
						                
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>ARM/SERVICE</label>
						                </div>
						                <div class="col-md-8">
											
                                           <label class=" form-control-label" id="arm_service"    >${getArmyNo}</label>
						                          
											 
										</div>
						            </div>
	              				</div>

	              			</div>
	              			 <div class="col-md-12 card-color"  >	   
	           					
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label"><strong style="color: red;"> </strong>CDA ACNO</label>
						                </div>
						               
						                <div class="col-md-8">
						                <label id="cdaac_no">${getCda_account_no}</label>
						                
						                </div>
						            </div>
	              				</div>
	              				<div class="col-md-6">
	              					<div class="row form-group">
						               <div class="col-md-4">
						                    <label class=" form-control-label">Uploaded On</label>
						                </div>
						                <div class="col-md-8">
	
                                           <label class=" form-control-label" id="pan">${getUploadedOn}</label>

										</div>
						            </div>
	              				</div>

	              			</div>	            	              		
	              			
	              			  	<div class="col-md-12" id="printableArea">
	              			  	  <h5 align="center" class="m ">CASUALITIES</h5> 
		<div id="divShow" style="display: block;"></div>
		<div class="watermarked" data-watermark="" id="divwatermark"
			style="display: block;">
			<span id="ip"></span>

			<div class="datatablediv" id="reportDiv">
				<div class="">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>
						<table id="persOffrs"
							class="table no-margin table-striped  table-hover  table-bordered">
							<thead style="font-size: 15px; text-align: center;">
								<tr>
										
									<th>Ser No</th>						
									<th>Casseqno </th>
									<th>Description</th>
									<th>Fmdate</th> 									
									<th> Rmk1</th>
								
									<th> Remark</th>
									<th> Action</th>
								</tr>
							</thead>
							<tbody>
								
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	
			</div>
					</div>
		</div>
	</div>
	<br>

<input type="hidden" id ="comm_id_casualty" name="comm_id_casualty" value="0">
<input type="hidden" id ="upload_id_casualty"  name="upload_id_casualty"value="0">

	</div>
</div>


	<script>
		var key = "${_csrf.parameterName}";
		var value = "${_csrf.token}";
		$(document).ready(function() {
			$.ajaxSetup({
				async : false
			});
			
			var comm_id_casualty='${comm_id_casualty}';
			var upload_id_casualty='${upload_id_casualty}';
			$("#comm_id_casualty").val(comm_id_casualty);
			$("#upload_id_casualty").val(upload_id_casualty);
			
			colAdj("persOffrs");
		});

		mockjax1('persOffrs');
		table = dataTable11('persOffrs');

		$('#btn-reload').on('click', function() {
			table.ajax.reload();
		});

		function data(tableName) {
			jsondata = [];

			var table = $('#' + tableName).DataTable();
			var info = table.page.info();
			var currentPage = info.page;
			var pageLength = info.length;
			var startPage = info.start;
			var endPage = info.end;
			var Search = table.search();
			var order = table.order();
			var orderColunm = $(table.column(order[0][0]).header()).attr('id');
			var orderType = order[0][1];
			var comm_id_casualty=	$("#comm_id_casualty").val();
			var upload_id_casualty=$("#upload_id_casualty").val();
			if (tableName == "persOffrs") {

				$.post("getcasualitycount?" + key + "=" + value, {
					Search : Search,
					comm_id_casualty:comm_id_casualty,
					upload_id_casualty:upload_id_casualty

				}, function(j) {
					FilteredRecords = j;
				});
				$.post("getcasualitydata?" + key + "=" + value, {
					startPage : startPage,
					pageLength : pageLength,
					Search : Search,
					orderColunm : orderColunm,
					comm_id_casualty:comm_id_casualty,
					upload_id_casualty:upload_id_casualty
				}, function(j) {

					for (var i = 0; i < j.length; i++) {
						var sr = i + 1;
// 						id, xml_file_upload_id, tag_name, tag_value, personnel_no, status, casualty_no)
						jsondata.push([ sr, j[i].casseqno, j[i].description, j[i].fmdate,
								j[i].rmk1,j[i].remark,
								j[i].action]);

					}
				});
			}
		}

function delete_casualty(casualty_no,xml_file_upload_id)
{
	if(confirm('Are you Sure You Want to Delete? '))
		{
		
		
	$.post("delete_casualty?" + key + "=" + value, {
		casualty_no:casualty_no,
		xml_file_upload_id:xml_file_upload_id
	}, function(j) {
		alert(j);
		table.ajax.reload();
	});
		}
	
	}
	</script>