<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Flipkart : search product with lowest price</title>
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/jquery-2.1.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
	<div class="container">
		<div class="row" style="margin-top: 5%">
			<div class="col-xs-2"></div>
			<div class="col-xs-8">
				<form class="form-inline" role="form">
					<div class="form-group ">
						<label class="sr-only" for="product">Product</label> <input
							type="text" class="form-control" id="product"
							placeholder="Product Name">
					</div>
					<button id="submit" type="button" class="btn btn-default">Search</button>
				</form>
			</div>
			<div class="col-xs-2"></div>
		</div>
		<div id="result" class="row" style="bacground-color: grey;"></div>
	</div>
</body>
<script>
	$("#submit").click(
			function() {
				if($("#product").val() != ""){
					var url = "search/"
							+ $("#product").val();
					$("#submit").text("Please Wait..");
					$.get(
							url,
							function(response) {
								var table ="<table class='table table-condensed'><thead ><tr><th style='width:40%'>Name</th><th style='width:15%'>Offer</th><th style='width:15%'>Price</th><th style='width:30%'>Description</th></tr></thead><tbody>";
								var tbody = "";
								if(response.length > 0){
									$.each(response, function(idx, product) {
										$("#thead").css("display", "block");
										tbody +="<tr><td><a href='"+product.url+"'>"
														+ product.name
														+ "</a></td><td>"
														+ product.offer
														+ "</td><td> Rs. "
														+ product.price
														+ "</td><td>"
														+ product.desciption
														+ "</td></tr>";
									});
									table = table + tbody +"</tbody></table>";
									$("#result").html(table);	
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