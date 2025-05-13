<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmn"%>
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/common/nrcss.css">
<link rel="stylesheet" href="js/common/select2/select2.min.css">
<script>
	var role = "${role}";
</script>
<body onload="setMenu();">
	<form:form name="fp_create_projection" id="fp_create_projection" class="fp-form" action="fp_create_projection?${_csrf.parameterName}=${_csrf.token}" method='POST' >
		<input type="hidden" name="tr_head" id="tr_head" />
		<div class="containerr" align="center">
			<div class="card">
			
			<c:if test="${cur_sus == 'CLOSED'}">
				<div class="" style="background: #3f6eb3;">
					<div class="container" align="center">
						<div class="card" style="background: #3f6eb3;">
							<h3  style="color:white;"><b>${msg1}</b></h3>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${cur_sus != 'CLOSED'}">			
				<div class="card-header mms-card-header"
					style="min-height: 60px; padding-top: 10px;">
					<h5>
						<span>PROJECTION FOR OWN EST</span>
					</h5>
				</div>

				<div class="card-body card-block ncard-body-bg">
					<div class="row form-group">
							<div class="col-md-6">
									<div class="col-md-3">
										<label class=" form-control-label"><strong style="color: red;">*</strong>Year</label>
									</div>
									<div class="col-md-9">
										<select id="fin_year" name="fin_year" class="form-control-sm" title="Select Financial Year.">
												<option value="${n_finyr}">${n_finyr}-${n_finyr+1}</option>
										</select>
										
									</div>
							</div>
							<div class="col-md-6">
									<div class="col-md-3">
										<label class=" form-control-label"><strong style="color: red;">*</strong>Est</label>
									</div>
									<div class="col-md-6">
										<select id="sus_no" name="sus_no"  class="form-control-sm" title="Select Est to Configure Budget Holders" onchange="getProjlist();">
											<c:forEach var="item" items="${n_hq}" varStatus="num">
												<c:if test="${item[1] == cur_sus}">
													<option value="${item[1]}" selected>${item[2]}</option>
												</c:if>
												<c:if test="${item[1] != cur_sus}">
													<option value="${item[1]}">${item[2]}</option>
												</c:if>												
									        </c:forEach>
						            	</select>						            
									</div>
							</div>
					</div>

					<div class="row" id="codehead">
						<div class="col-md-6">
							<div class="col-md-3">
								<label class="form-control-label"><strong	style="color: red;">* </strong>Code Head</label>
							</div>
							<div class="col-md-9">
								<input id="nrInput" type="text"	placeholder="Search Code Head ..." style="font-weight: normal;font-size: 14px;border: 1px solid #ddd;padding: 4px;" title="Search Code Head">
								<div style="height: 150px !important; overflow: auto;">
									<table border="1" id="nSelHead" name="nSelHead"	style="line-height: 25px; font-size: 14px; border: 1px solid lightgray; width: 100%;">
										<tbody id="nrTable"	style="font-size: 1vw; text-decoration: none;">
											<c:if test="${n_head == 'NIL'}">
												<tr class='nrSubHeading'>
													<td colspan='9' style='text-align: center;'>Data Not Available...</td>
												</tr>
											</c:if>
											<c:if test="${n_head != 'NIL'}">
												<c:forEach var="item" items="${n_head}" varStatus="num">
													<c:set var="dataf" value="${fn:split(item[0],':')}" />
													<c:set var="datafm" value="${fn:length(dataf)-1}" />
													<c:if test="${item[2] == '4'}">
														<tr>
															<td>&nbsp;<input type="radio" name="code_head"	id="tr_head_${item[0]}" value="${item[0]}" title="Select the Code Head">&nbsp;${dataf[datafm]}- ${item[1]}</td>
														</tr>
													</c:if>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
						</div>

					<div class="col-md-6">
						<div class="" style="margin-top: 10px;">
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"> <strong	style="color: red;">* </strong>Projected Amount</label>
								</div>
								<div class="col-md-8">
									(&#8377;)&nbsp;<input type="text" id="amount" name="amount" placeholder="0.00"	class="form-control-sm decimal" data-decimal-pt="5" title="Enter Projected Amount">
								</div>
							</div>						
							<div class="row form-group">
								<div class="col-md-4">
									<label class="form-control-label"> Reference /Remarks&nbsp;:&nbsp;</label>
								</div>
								<div class="col-md-8">
									<textarea name="remarks" maxlength="100" rows="2" cols="40" style="resize:none;" placeholder="Reference Letter No and Date"	class="form-control char-counter" onkeypress="return isAlphaNumeric(event);" title="Enter Reference/Remarks"></textarea>
								</div>
							</div>
					</div>
				</div>
				</div>
			</div>
				<div class="card-footer" align="center">
					<input type="button" class="btn btn-primary btn-sm nGlow"	value="Save Projection" id="btn_save" style="color: yellow;" title="click to Save Projection">
				</div>
			</c:if>
			</div>
		</div>
		<div>
		<c:if test="${cur_sus != 'CLOSED'}">
		<div  class="watermarked" data-watermark="" id="divwatermark"  >
			<div class="">
				<input type="text" id="nSelHeadsrcx" name="nSelHeadsrcx" placeholder="Search in Head..." class=""  style="float:left;text-transform: uppercase;"
                    autofocus autocomplete="off" size="30" title="Type Word or Part of Word to Search in Heads" data-toggle="tooltip">
            </div>		
            
		<div class="nPopTable" id="divPrint" style="height:30vh;width:100%;overflow: auto;">
		                 <table id="fundstatusReport" > 
		                      <thead style="text-align: center; line-height: 20px; font-size: 1.1vw;">
		                          <tr>
			                         <th style="text-align: center; width:5%;">Sr No.</th>
			                         <th style="text-align: center; width:10%;">Code Head</th>
			                         <th style="text-align: center; width:50%;">Head Description</th>
			                         <th style="text-align: center; width:15%;">Projected Amount (in Cr)</th>
			                         <th style="text-align: center; width:20%;">Reference / Remarks</th>
		                          </tr>                                                        
		                      </thead> 
		                      <tbody style="font-size:1vw; text-decoration: none;">
			                       	<c:if test="${list.size()==0}">
										<tr>
											<td style="font-size: 15px; text-align: center; color: red;"
												colspan="5">Data Not Available</td>
										</tr>
									</c:if>
		                           <c:forEach var="item" items="${list}" varStatus="num" >
							          <tr>
							          	 <td style="text-align: center;">${num.index+1}</td>
										 <td style='text-align:left' title="Code Head - ${item[0]}">${item[0]}</td>
										 <td style='text-align:left' title="Head Desc - ${item[1]}">${item[1]}</td>
										 <td style='text-align:right' title="Projected Amount"><script>document.write(Number('${item[2]}').toINR('','RS','INR','CR'))</script></td>
									 	 <td style='text-align:left' title="Reference/Remarks - ${item[3]}">${item[3]}</td>
							          </tr>
							       </c:forEach>
		                     </tbody>
		                 </table>
			  </div>
			  </div>
			</c:if>
	</div> 
	</form:form>

<script src="js/miso/miso_js/jquery-2.2.3.min.js" type="text/javascript"></script>
<script src="js/common/commonmethod.js" type="text/javascript"></script>
<script src="js/cue/cueWatermark.js" type="text/javascript"></script>
<script src="js/common/select2/select2.min.js" type="text/javascript"></script>

<script type="text/javascript">

		function isAlphaNumeric(evt){
			var charCode = (evt.which) ? evt.which : evt.keyCode;
				if((charCode>=48 && charCode<=57) || (charCode>=65 && charCode<=90) || (charCode>=97 && charCode<=122) || charCode==32 || charCode==46 || charCode == 45){
					return true;
				} 
			return false;
		}
		
		$(document).ready(function() {
			$("div#divwatermark").val('').addClass('watermarked');	
			watermarkreport();
			
			$("#sus_no").select2();
			$(".char-counter").charCounter();
			
			$("#nrInput").on("keyup",function() {
				var value = $(this).val().toLowerCase();
				$("#nrTable tr").filter(function() {
					$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
				});
			});

		    $("#nSelHeadsrcx").on("keyup", function() {
		    	var value = $(this).val().toLowerCase();
		      	$("#fundstatusReport tbody tr").filter(function() {
		      		$(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		        });
		    });

			
			$('#btn_save').click(function() {
				var trHead = $('[name=code_head]:checked').val();
				var amt = $('#amount').val();
				if (trHead && amt) {
					$('#tr_head').val(trHead);
						
					$.ajax({
						type : "POST",
						url: "fp_check_projection?"+key+"="+value,
						data : {tr_head : trHead},
						success : function(res) {
							if(res){
								var conf = confirm("Projection Amount already available under this head for financial year \n\nDo you want to over write ?");
								if(conf)
									$('#fp_create_projection').submit();
							}
							else
								$('#fp_create_projection').submit();
						},
						error:function(e,d){
							alert("Error - unable to fetch data");
						}
					});
				} 
				else {
					alert("Please enter all mandatory fields");
				}
			});
		});

function getProjlist(){
	$("#nrWaitLoader").show();
	var selsus= $("#sus_no").val();	
	$.post("getProjectionList?"+key+"="+value,{nPara:selsus}, function(j) {
		drawtregn(j);
	});
	$("#nrWaitLoader").hide();
}	

function getHeadList(){
	var selsus= $("#sus_no").val();	
	$.post("getHeadBuglist?"+key+"="+value,{nPara:selsus}, function(j) {
		drawtregn1(j);
	});
}

function drawtregn(data) {
	$("#divPrint tbody").empty();
	for (var i = 0; i <data.length; i++) {
		var  row ="<tr id='tarTableTr' padding='5px;'>";
		row += "<td name='"+data[i][0]+"' style='text-align: center;'>"+(i+1)+"</td>";
		row += "<td name='"+data[i][0]+"'>"+data[i][0]+"</td>";
		row += "<td name='"+data[i][0]+"'>"+data[i][1]+"</td>";
		row += "<td name='"+data[i][0]+"' style='text-align:right'>"+Number(data[i][2]).toINR('','RS','INR','CR')+"</td>";
		if (data[i][3]==null || data[i][3]=='null') {
			row += "<td name='"+data[i][0]+"'></td>";
		} else {
			row += "<td name='"+data[i][0]+"'>"+data[i][3]+"</td>";
		}
		$("#divPrint tbody").append(row);
	 } 
	getHeadList();
}

function drawtregn1(data) {
	$("#nSelHead tbody").empty();
	var row = "";
	for (var i = 0; i <data.length; i++) {
		row = "";
 		row += "<tr id='tarTableTr' padding='5px;'>";
		row += "<td>&nbsp;<input type='radio' name='code_head' value='"+data[i][0]+"'>&nbsp;"+data[i][0]+" - " +data[i][1]+"</td>";
		$("#nSelHead tbody").append(row);
	 } 
}

function refreshProj() {
	$("#divPrint").show();
	getProjlist();
}
</script>