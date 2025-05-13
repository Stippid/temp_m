	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
	<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
	<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
	<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
	
    <link rel="stylesheet" href="js/cue/cueWatermark.css">
	<script src="js/cue/cueWatermark.js"></script>
	<script src="js/cue/printAllPages.js" type="text/javascript"></script>
	
<div onFocus="parent_disable1();" onclick="parent_disable1();">
<div class="animated fadeIn">
		<div class="container" align="center">
			<div class="card">
				<div class="card-header">
					<strong><u>ANIMALS</u></strong>
					<strong><h3>ANIMAL STATUS</h3></strong>
				</div>
				<div class="card-body card-block">
					<div class="col-md-6">
						<div class="row form-group">
							<div class="col col-md-4">
								<label class=" form-control-label">SUS No </label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="sus_no" name="sus_no" placeholder="" class="form-control" value="${sus_no012}" autocomplete="off" readonly="readonly">
							</div>
						</div>
					</div>
					<div class="col-md-6 col-md-offset-1">
						<div class="row form-group">
							<div class="col col-md-4">
								<label class=" form-control-label">Unit Name </label>
							</div>
							<div class="col-12 col-md-8">
								<input type="text" id="unit_name" name="unit_name" placeholder="" class="form-control " value="${unit_name012}" readonly="readonly">
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	
		<div class="container" id="divPrint">
			<div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
				<span id="ip"></span>
				<table id="SearchAnimalReaport" class="table no-margin table-striped  table-hover  table-bordered report_print">
					<thead style="background-color: #9c27b0; color: white; text-align: center;">
						<tr>
							<th width="5%" >Ser No</th>
							<th width="10%">Army No / Remount No</th>
							<th width="15%">Name</th>
							<th width="15%">Type of Dog / Equines</th>
							<th width="10%">Microchip No</th>
							<th width="7%">Sex</th>
							<th width="10%">TOS</th>
							<th width="10%">TORS</th>
							<th width="10%">SOS</th>
							<th width="8%">Action</th>
						</tr>
					</thead>
					<tbody >
					
						<c:forEach var="item" items="${list}" varStatus="num" >
								<tr>
								    <td width="5%" style="text-align: center;">${num.index+1}</td>
									<td width="10%" style="text-align: center;">${item[0]}</td>
									<td width="15%" style="text-align: left;">${item[1]}</td>
									<td width="15%" style="text-align: left;">${item[2]}</td>
									<td width="10%" style="text-align: center;">${item[3]}</td>
									<td width="7%" style="text-align: left;">${item[4]}</td>
									<td width="10%" style="text-align: center;">${item[5]}</td>
									<td width="10%" style="text-align: center;">${item[6]}</td>
									<td width="10%" style="text-align: center;">${item[7]}</td>
									<td width="8%" style="text-align: center;">${item[9]}</td>
								</tr>
							</c:forEach>
					</tbody>
				</table>
			</div>

			<div class="card-footer" align="center">
	        		<c:if test="${roleType == 'ALL' || roleType == 'APP'}"> 
		        		 <c:if test="${status12 == 0}">      
						  <a href="#" class="btn btn-danger btn-sm" onclick="Reject();">Reject </a>
				          <input type="submit" class="btn btn-success btn-sm" value="Approve" onclick="Approved();">
						 </c:if>
					</c:if>
			</div>
		</div>
	</div>
</div>


		<c:url value="Show_Animal_Details" var="editurl1" />
		<form:form action="${editurl1}" method="post" id="editForm1" name="editForm1" modelAttribute="editId" target="result">
			<input type="hidden" name="editId" id="editId" value="0" />
		</form:form>

		<c:url value="ApprovedanmlUrl" var="appUrl1" />
		<form:form action="${appUrl1}" method="post" id="appForm" name="appForm" modelAttribute="sus3">
			<input type="hidden" name="sus3" id="sus3" value="${sus_no012}"/>
			<input type="hidden" name="unit_name1" id="unit_name1" value="${unit_name012}"/>

		</form:form>
		<c:url value="rejectanmlUrl" var="rejectUrl1" />
		<form:form action="${rejectUrl1}" method="post" id="rejectForm" name="rejectForm" modelAttribute="sus">
			<input type="hidden" name="sus" id="sus" value="${sus_no012}"/>
		</form:form>

<script type="text/javascript">
		jQuery(document).ready(function() {
			$("div#divwatermark").val('').addClass('watermarked');								
			watermarkreport(); 
		});
		
		function Reject()
		{
			document.getElementById('rejectForm').submit();
	    }
		
		function Approved(){
			document.getElementById('appForm').submit();
	   }
</script>

<script>
function open1(id){	
	var x = screen.width/2 - 1100/2;
    var y = screen.height/2 - 900/2;
	newWin = window.open("", 'result', 'height=800,width=1200,left='+x+', top='+y+',resizable=yes,scrollbars=yes,toolbar=no,status=yes');	 
	document.getElementById('editId').value=id;	
	document.getElementById('editForm1').submit();
}

function parent_disable1() {
	if(newWin && !newWin.closed)
		newWin.focus();
}
</script>

