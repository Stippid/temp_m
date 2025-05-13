<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script src="js/Calender/datePicketValidation.js"></script>
<script src="js/miso/orbatJs/orbatAll.js" type="text/javascript"></script>
	<div class="animated fadeIn">
		<div class="row">
	    	<div class="container" align="center">
	    		<div class="card">
	            		
	            		<div class="card-header"><h5><b>CREATE USER HTO</b><br></h5></div>
							<div class="card-body">
								<div class="col-md-12">
									<div class="col-md-6">
										<div class="row form-group">
                                              <div class="col col-md-4">
                                                    <label class=" form-control-label"><strong style="color: red;">* </strong>SUS No </label>
                                              </div>
                                             <div class="col-12 col-md-8">
           						               <input type="text" id="sus_no" name="sus_no"  maxlength="8" style="font-family: 'FontAwesome',Arial;" value="${sus_no}" placeholder="&#xF002; Search SUS No" class="form-control" autocomplete="off">
           						               <input type="hidden" id="id" name="id"  class="form-control" >
         					            	</div>
                                         </div>
									</div>
									<div class="col-md-6">
										<div class="row form-group">
               								<div class="col col-md-4">
                 								<label class=" form-control-label">Unit Name </label>
               								</div>
							               <div class="col-12 col-md-8">
                 						   		<input type="text" id="unit_name" name="unit_name" maxlength="100" style="font-family: 'FontAwesome',Arial;" value="${unit_name}" placeholder="&#xF002; Search Unit Name"  class="form-control" disabled="disabled">
               					           </div>
							           	</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-6">
          						    	<div class="row form-group">
            								<div class="col col-md-4">
              									<label class=" form-control-label">Imdt Higher FMN</label>
            								</div>
				                			<div class="col-12 col-md-8">
            						        	<select id="imdt_fmn" name="imdt_fmn" class="form-control" disabled="disabled">
            						            	<option value="0">--Select--</option>
													<c:forEach var="item" items="${getImdtFmnList}" varStatus="num" >
														<option value="${item[0]}" name="${item[1]}">${item[1]}</option>
													</c:forEach>
            						        	</select>
            					           	</div>
				           				</div>
								   </div>
							      <div class="col-md-6">
							      		<div class="row form-group">
                                      <div class="col col-md-4">
                                            <label class=" form-control-label">Arm Name </label>
                                      </div>
                                      <div class="col-12 col-md-8">
               						      		<select id="arm_name" name="arm_name" class="form-control" disabled="disabled">
                						            	<option value="0">--Select--</option>
                  								<c:forEach var="item" items="${getArmNameList}" varStatus="num" >
                  									<option value="${item.arm_code}" >${item.arm_desc}</option>
                  								</c:forEach>
               						          	</select>
             					            	</div>
	              	             	</div>
							      </div>
									      
							 </div>
									 <div class="col-md-12">
										<div class="col-md-6">
												<div class="row form-group">
									                <div class="col col-md-4">
									                      <label class=" form-control-label">Mode of Tpt </label>
									                </div>
									                <div class="col-12 col-md-8">
					                				      <input type="text" id="mode_of_tpt" name="mode_of_tpt" maxlength="25"  class="form-control" disabled="disabled">
	                  						        </div>
	              								</div>
	              						</div>
	              						<div class="col-md-6">
	              							<div class="row form-group">
			                                	<div class="col col-md-4">
			                                    	<label class=" form-control-label">NMB Date </label>
												</div>
                			                    <div class="col-12 col-md-8">
	                  						        <input type="date" id="nmb_date" name="nmb_date"  maxlength="10" class="form-control" readonly="readonly">
										        </div>
              			                	</div>
									   	</div>
									 </div>
									 <div class="col-md-12">
										<div class="col-md-6">
												<div class="row form-group">
									                <div class="col col-md-4">
									                  <label class=" form-control-label">Indn/De-Indn pd</label>
									                </div>
									                <div class="col-12 col-md-8">
									                 	<input type="text" id="indn_de_indn_pd" name="indn_de_indn_pd" maxlength="10"  class="form-control" disabled="disabled">
									                </div>
									          	</div>
										</div>
										<div class="col-md-6">
												<div class="row form-group">
									                <div class="col col-md-4">
									                  <label class=" form-control-label"> On Relief </label>
									                </div>
									                <div class="col-12 col-md-8">
									                 	<input type="text" id="relief_yes_no" name="relief_yes_no" maxlength="10"  class="form-control" readonly="readonly">
									                </div>
									          	</div>
									    </div>
									  </div>
									<div class="col-md-12">
										<div class="col-md-6">
					                          <div class="row form-group">
                								   <div class="col col-md-4">
                                                         <label class=" form-control-label"> To Location </label>
                                                   </div>
                                                   <div class="col-12 col-md-8">
	                  						            <select id="loc" name="loc" class="form-control" disabled="disabled">
	                  						            	<option value="0">--Select--</option>
						                                    <c:forEach var="item" items="${getlocList}" varStatus="num" >
				                  								<option value="${item[0]}">${item[1]}</option>
				                  							</c:forEach>
	                  						            </select>
	                  						            <!-- <input type="text" id="code" name="code" class="form-control"  style="width:100%;display: inline-block;" readonly="readonly" >
							                 			<input type="hidden" id="loc" name="loc" class="form-control" style="width:100%;"> -->
					              				    </div>
                                             </div>
                                      	</div>
                                      	<div class="col-md-6">
                                      		<div class="row form-group">
					                			<div class="col col-md-4">
					                  				<label class=" form-control-label">NRS </label>
					                			</div>
					                			<div class="col-12 col-md-8">
	                  						    	<input type="text" id="nrs" name="nrs" maxlength="100"  class="form-control" disabled="disabled">
	                					    	</div>
			              					</div>
										</div>
									</div>
									 <div class="col-md-12" style="display: none;">
										<div class="col-md-6">
					                         <div class="row form-group">
                                                  <div class="col col-md-4">
                                                           <label class=" form-control-label">Type of stn </label>
                                                  </div>
                                                  <div class="col-12 col-md-8">
	                  						               <input type="text" id="typ_of_stn" name="typ_of_stn" maxlength="100"  class="form-control" disabled="disabled">
	                					          </div>
           	                                </div>
           	                         	</div>
           	                         	<div class="col-md-6">
           	                         		<div class="row form-group">
					                				<div class="col col-md-4">
					                  					<label class=" form-control-label">Type of terrain </label>
					                				</div>
					                				<div class="col-12 col-md-8">
	                  						               <input type="text" id="typ_of_terrain" name="typ_of_terrain" maxlength="100"  class="form-control" disabled="disabled">
	                					            </div>
			              					</div> 
									    </div>
									</div>
									 <div class="col-md-12">
										<div class="col-md-6">
			                                 <div class="row form-group">
                								   <div class="col col-md-4">
                                                         <label class=" form-control-label">Move of Adv Party Date</label>
                                                   </div>
                                                   <div class="col-12 col-md-8">
	                  						             <input type="date" id="mov_of_adv_party_dt" name="mov_of_adv_party_dt"  maxlength="10" class="form-control" disabled="disabled">
										           </div>
                                             </div>
                                        </div>
                                        <div class="col-md-6">
			                                 <div class="row form-group">
                								   <div class="col col-md-4">
                                                         <label class=" form-control-label">Type of Cl</label>
                                                   </div>
                                                   <div class="col-12 col-md-8">
	                  						             <input type="text" id="type_of_cl" name="type_of_cl"  maxlength="10" class="form-control" disabled="disabled">
										           </div>
                                             </div>                                        
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-6">
                                       		<div class="row form-group">
                								<div class="col col-md-4">
                                              		<label class=" form-control-label">To Unit SUS No </label>
                                             	</div>
                                            	<div class="col-12 col-md-8">
	                  						    	<input type="text" id="rplc_by_unit_sus_no" name="rplc_by_unit_sus_no" maxlength="8" class="form-control" disabled="disabled">
	                					    	</div>
                                       		</div>
                                   		</div>
                                   		<div class="col-md-6">
                                   			<div class="row form-group">
                                              	<div class="col col-md-4">
                                                	<label class=" form-control-label">To Unit</label>
                                             	</div>
                                              	<div class="col-12 col-md-8">
                                                	<input type="text" id="rplc_by_unit_name" name="rplc_by_unit_name"  maxlength="100" class="form-control" disabled="disabled">
                                            	</div>
                                        	</div>  
										</div>
									</div>
								</div>
								<div class="col-md-12">
										<div class="col-md-6">
                                       		<div class="row form-group">
                								<div class="col col-md-4">
                                              		<label class=" form-control-label">Remarks </label>
                                             	</div>
                                            	<div class="col-12 col-md-8">
													<textarea  id="remarks" name="remarks"  maxlength="255" class="form-control char-counter1" disabled="disabled"> </textarea>
	                					    	</div>
                                       		</div>
                                   		</div>
                                   		<div class="col-md-6">
										</div>
									</div>
              			    <div class="card-footer" align="center">
			              		<input type="button" class="btn btn-primary btn-sm" value="Nominate offr" onclick="viewDataPopUp()">
			              	</div> 
			        	</div>
					</div>
				</div>
			</div>
			
<div class="modal-open modal" id="getPersonnelDetail12" style="overflow-y: auto;">
	<div class="modal-dialog" style="max-width: 900px;">
		<div class="modal-content">
			<div id="getRODetailsDiv1">
				<div class="modal-header">
					<table style="width: 100%;">
						<tr style="height: 30px;">
							<td align="left"><img src="js/miso/images/indianarmy_smrm5aaa.jpg" style="height: 50px;"></td>
							<td align="center">
								<h5>DETAILS OF PERSONNELS</h5>
							</td>
							<td align="right"><img src="js/miso/images/dgis-logo.png" style="max-width: 155px; height: 50px;"></td>
						</tr>
					</table>
				</div>
				<div class="modal-body">
					<div class="flow-container">

						<div class="card-body card-block">
						
						
							<div class="modal-body">
				<div class="col-md-12" id="getSearch_Letter" style="display: block;">
					<div class="watermarked" data-watermark="" id="divwatermark">
						<span id="ip"></span>

						<table id="getPersonnelDetailsTable"  class="table no-margin table-striped  table-hover  table-bordered report_print" >
									<thead style="font-size: 15px; text-align: center;">
										<tr>
											<th>Select</th>
											<th>Ser No.</th>
											<th>Personnel No</th>
											<th>Name</th>
											<th>Rank</th>
											
										</tr>
									</thead>
									<tbody>
										<c:if test="${list.size() == 0}">
											<tr>
												<td colspan="12" align="center" style="color: red;">Data
													Not Available</td>
											</tr>
										</c:if>
									</tbody>
								</table>
					</div>
				</div>
			</div>
							
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer" align="center">
				<input type="submit" class="btn btn-primary btn-sm" value="Save" onclick="saveOcParty()">
				<input type="button" class="btn btn-danger btn-sm" value="Close" onclick="closeModalView();">
			</div>
		</div>
	</div>
</div>
			
 

<script>
function viewDataPopUp() {debugger;
	var modal = document.getElementById('getPersonnelDetail12');
	modal.style.display = "block";
	table.ajax.reload();
	window.addEventListener('click', function(event) {
		if (event.target === modal) {
			closeModalView();
		}
	});
}

function closeModalView() {
	var modal = document.getElementById('getPersonnelDetail12');
	modal.style.display = "none";
	
	window.removeEventListener('click', function(event) {
		if (event.target == modal) {
			closeModalView();
		}
	});

}



function saveOcParty(){debugger;
var a1 = $("input[name='personnelRadio']:checked").data('personnel-id');
var sus_no = $("#sus_no").val();
 $.post("OC_Adv_Party_Action?"+key+"="+value, {
		a1 : a1,
		sus_no:sus_no}).done(function(j) {
			alert(j);
			
				 //////////////////// close pop up
				 $('.modal').removeClass('in');
				 $('.modal').attr("aria-hidden","true");
				 $('.modal').css("display", "none");
				 $('.modal-backdrop').remove();
				 $('body').removeClass('modal-open');
				 //////////////////// end close pop up
			
  }).fail(function(xhr, textStatus, errorThrown) { }); 	
return true;
}
</script>
<script>
 jQuery(document).ready(function() {   
	if('${roleAccess}' == 'Unit'){
	jQuery("#sus_no").attr('readonly','readonly');
	jQuery("#unit_name").attr('readonly','readonly');
}
	jQuery("#id").val('${list[0][0]}');
	jQuery("#unit_name").val('${list[0][1]}');
	jQuery("select#imdt_fmn").val('${list[0][2]}');
	jQuery("#indn_de_indn_pd").val('${list[0][3]}');
	jQuery("select#arm_name").val('${list[0][4]}');
	jQuery("#mode_of_tpt").val('${list[0][5]}'); 
	jQuery("#nmb_date").val('${list[0][6]}');  
	jQuery("#loc").val('${list[0][7]}');
	jQuery("#nrs").val('${list[0][8]}');
	jQuery("#typ_of_stn").val('${list[0][9]}');
	jQuery("#typ_of_terrain").val('${list[0][10]}');
	jQuery("#mov_of_adv_party_dt").val('${list[0][11]}');
	jQuery("#rplc_by_unit_sus_no").val('${list[0][12]}');
	jQuery("#rplc_by_unit_name").val('${list[0][13]}');
	
	var r;
	if('${list[0][14]}' == '1')
		r="Yes";
	  else
		r="No";
			
	jQuery("#relief_yes_no").val(r);
	jQuery("#type_of_cl").val('${list[0][15]}');
	jQuery("#remarks").val('${list[0][16]}');	
	
	mockjax1('getPersonnelDetailsTable');
	table = dataTable11('getPersonnelDetailsTable');
	
}); 	 

 function data(tableName) {
		jsondata = [];
		var table = $('#' + tableName).DataTable();
		var info = table.page.info();
		var currentPage = info.page;
		var pageLength = -1;
		var startPage = info.start;
		var endPage = info.end;
		var Search = table.search();
		var order = table.order();
		var orderColunm = order[0][0] + 1;
		var orderType = order[0][1];
		var sus_no = $("#sus_no").val();

		if (tableName == "getPersonnelDetailsTable" ) {
			$.post("getPersonnelDetailsCount?" + key + "=" + value, {
				Search : Search,
				sus_no : sus_no,
			}, function(j) {
				FilteredRecords = j;
			});
			$.post("getPersonnelDetailsTable?" + key + "=" + value, {
				startPage : startPage,
				pageLength : pageLength,
				Search : Search,
				orderColunm : orderColunm,
				orderType : orderType,
				sus_no : sus_no,

			}, function(j) {

				for (var i = 0; i < j.length; i++) {
					var sr = i + 1;
						var radioHtml = j[i].radio;
						jsondata.push([ radioHtml, sr, j[i].personnel_no, j[i].name, j[i].description ]);
			

				}
			});
		}
	}
 
</script>