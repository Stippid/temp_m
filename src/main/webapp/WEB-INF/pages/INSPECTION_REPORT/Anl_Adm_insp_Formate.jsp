<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    HttpSession sess = request.getSession(false);
    if (sess == null || sess.getAttribute("userId") == null) {
        response.sendRedirect("/login");
        return;
    }
%>

<dandelion:asset cssExcludes="datatables" />
<dandelion:asset jsExcludes="datatables" />
<dandelion:asset jsExcludes="jquery" />

<head>
<meta charset="UTF-8">
<title>ANNUAL INSPECTION REPORT (Formate)</title>

<!-- Stylesheets -->
<link href="js/JS_CSS/jquery.dataTables.min.css" rel="stylesheet">
<link rel="stylesheet" href="js/miso/autoComplate/autoComplate.css">
<link rel="stylesheet" href="js/miso/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="js/cue/cueWatermark.css">
<link rel="stylesheet" href="js/Calender/Calender_jquery-ui.css">
<link rel="stylesheet" href="js/InfiniteScroll/css/datatables.min.css">
<link href="js/Calender/jquery-ui.css" rel="Stylesheet">
<link rel="stylesheet"
  href="js/assets/adjestable_Col/jquery.resizableColumns.css">


<!-- Scripts -->
<script src="js/JS_CSS/jquery-3.3.1.js"></script>
<script src="js/JS_CSS/jquery.dataTables.js"></script>
<script src="js/cue/cueWatermark.js"></script>
<script src="js/cue/printAllPages.js" type="text/javascript"></script>

<script type="text/javascript" src="js/miso/miso_js/jquery-1.12.3.js"></script>
<script src="js/miso/miso_js/jquery-2.2.3.min.js"></script>
<script src="js/amchart4/core.js"></script>
<script src="js/amchart4/charts.js"></script>
<script src="js/amchart4/animated.js"></script>

<script src="js/Calender/jquery-2.2.3.min.js"></script>
<script src="js/Calender/jquery-ui.js"></script>
<script src="js/Calender/datePicketValidation.js"></script>

<script type="text/javascript" src="js/AES_ENC_DEC/lib/aes.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/lib/pbkdf2.js"></script>
<script type="text/javascript" src="js/AES_ENC_DEC/AesUtil.js"></script>
<script src="js/JS_CSS/jquery-1.10.2.js" type="text/javascript"></script>

<script type="text/javascript"
  src="js/InfiniteScroll/js/datatables.min.js"></script>
<script type="text/javascript"
  src="js/InfiniteScroll/js/jquery.mockjax.min.js"></script>
<script type="text/javascript"
  src="js/InfiniteScroll/js/datatables.mockjax.js"></script>

<script src="js/miso/commonJS/commonmethod.js" type="text/javascript"></script>
<script src="js/assets/adjestable_Col/jquery.resizableColumns.js"
  type="text/javascript"></script>
<script src="js/upload_xls/xlsx.full.min.js"></script>

<style type="text/css" media="print">
@page {
    size: auto;
    margin: 15mm;
    @top-center {
        content: "Restricted";
        text-align: center;
        font-weight: bold;
        width: 100%;
        display: block;
        height: 10mm;       /* Explicit height */
        border-bottom: 1px solid black;
        padding-bottom: 3mm;  /* Padding within the header */ 
    }
    @bottom-center {
        content: "Restricted - Page " counter(page);
        text-align: center;
        font-weight: bold;
        width: 100%;
        display: block;
        height: 05mm;
         border-top: 1px solid black;
        padding-top: 1mm; 
    }
}

body {
    font-family: Arial, sans-serif;
    max-width: 800px;
    margin: 0;  /* Remove default margin */
    padding: 20px; /*General padding arround the body content*/
    padding-top: 20mm; /* Adjust this value carefully */
    counter-reset: page; /* Initialize page counter */
}
  body {
    margin: 0;
    padding: 20px;
    padding-top: 20mm;  /* Adjust this value */
  }

</style>

<style>
body {
  font-family: Arial, sans-serif;
  max-width: 1000px;
  margin: 20px auto;
  padding: 20px;
}

.restricted-header {
  text-align: center;
  text-decoration: underline;
  margin-bottom: 10px;
}

.page-number {
  text-align: center;
  margin-bottom: 20px;
}

.appendix-section {
  text-align: right;
  margin-bottom: 30px;
}

.appendix-title {
  font-weight: bold;
  margin-bottom: 5px;
}

.appendix-reference {
  font-size: 0.9em;
  margin-bottom: 5px;
}

.report-title {
  text-align: center;
  font-weight: bold;
  text-decoration: underline;
  margin-bottom: 30px;
}

.year-field {
  text-align: right;
  margin-bottom: 40px;
}

.form-container {
  margin-top: 20px;
  margin-bottom: 40px;
}

.form-row {
  display: flex;
  margin-bottom: 20px;
  align-items: flex-start;
}

.label {
  flex: 0 0 400px;
  padding-right: 20px;
}

.colon {
  flex: 0 0 20px;
}

.input-field {
  flex: 1;
  min-width: 300px;
}

.input-field input {
  width: 100%;
  padding: 5px;
  border: none;
  border-bottom: 1px solid #000;
}

.multi-input {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

/* Part I Styles */
.part-header {
  text-align: center;
  font-weight: bold;
  margin: 40px 0 20px 0;
}

.info-header {
  text-align: center;
  font-weight: bold;
  margin-bottom: 30px;
  max-width: 800px;
  margin-left: auto;
  margin-right: auto;
}

.establishment-title {
  font-weight: bold;
  text-decoration: underline;
  margin-bottom: 20px;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th, td {
  border: 1px solid black;
  padding: 8px;
  text-align: left;
}

th {
  font-weight: bold;
  text-align: center;
}

.personnel-col {
  width: 30%;
}

.number-col {
  width: 15%;
  text-align: center;
}

.sub-row {
  padding-left: 20px;
}


</style>

</head>

<body>




    <div class="title" style="text-align: center; margin-bottom: 20px;">
        <strong>ANNUAL INSPECTION REPORT EXTRACTS WEF ______________ To ______________</strong>
    </div>
      <div>  
        <table>
         <thead>
    <tr>
        <th rowspan="1" class="personnel-col" style="width: 5%;">Ser No</th>
        <th rowspan="1" class="personnel-col" style="width: 40%;"> </th>
        <th rowspan="1" class="personnel-col" style="width: 20%;">Training Year
________
</th>
        <th rowspan="1" class="personnel-col" style="width: 20%;">Training Year
________
</th>
        <th rowspan="1" class="personnel-col" style="width: 20%;">Training Year
________
</th>
        <th rowspan="1" class="personnel-col" style="width: 20%;">Training Year
________
</th>
        <th rowspan="1" class="personnel-col" style="width: 20%;">Remarks</th>          
    </tr>

</thead>
            <tbody>
                  
     <tr>
        <th colspan="8" class="personnel-col">Trg</th>           
    </tr>
                <tr>
                    <td>1</td>
                    <td> <strong>Individual Training</strong><br>
                    (a) Physical Training<br>
                    (b) Weapon Training<br>
                    (c) Weapon Training Results<br>
                    (d) Night Training<br>
                    (e) Specialist Training<br>
                    (f) YOs Training<br>
                    (g) Officers Training<br>
                    (h) Training of JCOs and NCOs<br>
                    (i) Training for ACC Commission / SCO</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                                          
                    
                </tr>
                   <tr>
                    <td>2</td>
                    <td><strong>Collective Training</strong><br>
                    (a) Training Exercises / Training Camps<br>
                    (b) Field Firing<br>
                    (c) Mobilisation</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                               
                   
                    
                </tr>
                   <tr>
                    <td>3</td>
                    <td> <strong>Use of Training Infrastructure including Training Aids.</strong></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                                 
                   
                    
                </tr>
                    
     <tr>
        <th colspan="8" class="personnel-col">Administration</th>           
    </tr>
    
         <tr>
                    <td>4</td>
                    <td> <strong>Personnel Management</strong><br>
                    (a) Discipline <br>
                    (b) Health of Troops<br>
                    (c) Personal Documentation <br>
                    (d) Interior Economy<br>
                    (e) Morale and Motivation<br>                    
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                                          
                    
                </tr>
                
                         <tr>
                    <td>5</td>
                    <td> <strong>Material Management. </strong><br>
                    (a) Vehicles <br>
                    (b) Equipment<br>
                    (c) Maintenance of Arms  <br>
                    (d) Maintenance of Ammunition <br>
                    (e) Maintenance of Ordnance Stores <br> 
                     (f) Management of Public Funds and Financial Grants <br> </td>                   
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                                          
                    
                </tr>
      <tr>
        <th colspan="8" class="personnel-col">Misc Aspects</th>           
    </tr>
    
     <tr>
                    <td>6</td>
                    <td>Security </td>                    
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                                          
                    
                </tr>
                     <tr>
                    <td>7</td>
                    <td>Adherence to Indian Army Core Values </td>                    
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                                          
                    
                </tr>
                      <tr>
                    <td>8</td>
                    <td>HRD</td>                    
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                                          
                    
                </tr>
                 <tr>
                    <td>9</td>
                    <td>Audit Objections</td>                    
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                                          
                    
                </tr>
                          <tr>
                    <td>10</td>
                    <td>State of Complaints</td>                    
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>                                      
                    
                </tr>
                
                      <tr>
                    <td>11</td>
                    <td> <strong>Additional Information.</strong>(For Station, Sub Area and Area HQ only) <br>
                   (a) State of all Public Funds <br>
                    (b) State of discipline in the formation including state of pending GCMs / DCMs, as applicable <br>
                    (c) State of court cases in the formation <br>
                   (d) State of complaints and petitions,<br>
                    (e) State of works in the formation <br>   
                    (e) State of works in the formation <br>
                    (f) Any peculiar aspects observed <br> </td>               
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                                          
                    
                </tr>
            </tbody>
        </table>
    </div>
	

</body>
</html>
