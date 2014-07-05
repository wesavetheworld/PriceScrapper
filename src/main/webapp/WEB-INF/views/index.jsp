<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Flipkart | Amazon : search product with lowest price</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="row" style="margin-top: 5%">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<form class="form-inline" role="form">
					<div class="form-group" style="width: 80% !important;">
						<label class="sr-only" for="product">Product</label> <input
							type="text" class="form-control" id="product"
							placeholder="Product Name" style="width: 100% !important;">
					</div>
					<button id="submit" type="button" class="btn btn-primary">Search</button>
				</form>
			</div>
		</div>
	</div>
	<div class="container" id="result"></div>
</body>
<script>
	$("#submit").click(
			function() {
				if($("#product").val() != ""){
					var url = "search/"+ $("#product").val();
					$("#submit").text("Please Wait..");
					$.get(
							url,
							function(response) {
								if(response.length > 0){
									var result = "";
									$.each(response, function(idx, product) {
										result += "<div class='row' style='padding-top:5px;padding-bottom:5px;'><div class='col-md-12'>";
										result += "<div class='col-xs-2'>";
										result += "<img src="+product.img+" /></div>";
										result += "<div class='col-xs-10'>";
										result += "<h4><a href='"+product.url+"'>"+ product.name+ "</a></h4>";
										result += "<div class='col-xs-12'><div class='col-xs-2'><h5> Rs."+product.price+"</h5></div><div class='col-xs-8'>"+product.description+"</div><div class='col-xs-2'><sub>"+product.source+"</sub></div></div>";
										result += "</div></div></div>";
									});
									$("#result").html(result);	
								}else{
									$("#result").html("<h3 align='center'>Sorry no products were found.</h3>");
								}
								
							}).fail(function(response, status, error) {
						alert(error);
						alert(status);
					}).always(function(){
						$("#submit").text("Search");
					});
				}else{
					alert("Please Enter Product to Search")
				}
			});
</script>
</html>