<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<link href="js/cue/cueWatermark.css" rel="Stylesheet"></link>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet"></link>
<script src="js/Calender/jquery-ui.js" type="text/javascript"></script>
<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<!-- resizable_col -->
<link rel="stylesheet" href="js/assets/adjestable_Col/jquery.resizableColumns.css">
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js" type="text/javascript"></script>
<div class="container" id="getcitySearch">
        <div class="">
                 <div id="divShow" style="display: block;"></div>
                <div class="watermarked" data-watermark="" id="divwatermark" style="display: block;">
                        <span id="ip"></span>
                        <table id="getcitySearch" class="table no-margin table-striped  table-hover  table-bordered report_print" >
                                        <thead style="text-align: center;">
                                                <tr>
                                                        <th style="font-size: 15px ;width: 10%;">Ser No</th>
                                                        <th style="font-size: 15px">Authority</th>
                                                        <th style="font-size: 15px">Date of Authority</th>
                                                        <th style="font-size: 15px">Rank Type</th>
                                                        <th style="font-size: 15px">Date of Rank</th>
                                                        <th style="font-size: 15px">Created By</th>
                                                        <th style="font-size: 15px">Created Date</th>
                                                        <th style="font-size: 15px">Approved By</th>
                                                        <th style="font-size: 15px">Approved Date</th>
                                                        <th style="font-size: 15px">Rank</th>
                                                </tr>
                                        </thead>
                                        <tbody >
                                                <c:forEach var="item" items="${list}" varStatus="num" >
                                                        <tr>
                                                        <td style="font-size: 15px;text-align: center; width: 10%;">${num.index+1}</td> 
                                                        <td style="font-size: 15px;">${item[0]}</td>
                                                        <td style="font-size: 15px;">${item[1]}</td>
                                                        <td style="font-size: 15px;">${item[2]}</td>
                                                        <td style="font-size: 15px;">${item[3]}</td>
                                                        <td style="font-size: 15px;">${item[4]}</td> 
                                                        <td style="font-size: 15px;">${item[5]}</td>
                                                        <td style="font-size: 15px;">${item[6]}</td>
                                                        <td style="font-size: 15px;">${item[7]}</td>
                                                        <td style="font-size: 15px;">${item[8]}</td>
                                                        </tr>
                                                </c:forEach>
                                        </tbody>

			</table>
                        </div>
                </div> 
        </div>
<script>
$(document).ready(function() {
	
	 colAdj("getcitySearch");
});
	 
	 </script>