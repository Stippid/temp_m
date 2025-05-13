<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



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

<script src="js/Calender/jquery-2.2.3.min.js"></script>

<div class="animated fadeIn">
	<div class="">
		<div class="col-md-12" align="center">
			<div class="card">
				<div class="card-header">
					<h5>PRF WISE ALL INDIA DEFI/SURPLUS OF 'B' VEHICLE STATUS AS ON ${date}</h5>
				</div>



				<div class="card-body">
					<div class="" id="reportDiv">
						<div class="col-md-13">
							<div class="watermarked" data-watermark="" id="divwatermark">
								<span id="ip"></span>
								<table id=""
									class="table no-margin table-striped  table-hover  table-bordered report_print">
									<thead style="font-size: 15px; text-align: center;">
										<tr>
								        	<th width = "9%">PRF</th>
								            <th>NOMENCLATURE</th>
								            <th width = "7%">UE</th>
								            <th width = "11%">RESERVES</th>
								            <th width = "11%">UE WITH <br>RESERVES</th>
								        	<th width = "11%">UH UPTO CL <br>IV (EXCL <br>QFD)</th>
								        	<th width = "12%">SURPLUS / DEFI <br>WITH RESERVES</th>
								        	<th width = "11%">% SURPLUS / <br> DEFI</th>
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
</div>
