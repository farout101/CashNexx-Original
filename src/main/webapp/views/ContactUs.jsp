<%@ include file="header.jsp"%>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/CStyle.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style>
p {
	line-height: 2;
}

.plogo {
	margin: 0px;
	padding: 0px;
	text-decoration: none;
}

.popup-wrapper {
	position: absolute;
	display: flex;
	align-items: center;
	width: 100%;
	height: 100%;
	flex-wrap: wrap;
	flex-direction: column;
	gap: 10px;
	justify-content: center;
	backdrop-filter: blur(10px);
	z-index: 1100;
}

.popup-center {
	padding: 20px;
	position: absolute;
	align-items: center;
	border-radius: 10px;
	z-index: 6;
	max-height: 100%;
	position: relative;
	box-shadow: 5px 5px 10px 5px #0c03037d;
	margin: auto;
	flex-wrap: wrap;
	flex-direction: column;
	gap: 10px;
	background-color: white;
	justify-content: center;
	max-width: 80vw;
	z-index: 1101;
}

.load-wrapper {
	position: absolute;
	display: flex;
	align-items: center;
	width: 100%;
	height: 100%;
	flex-wrap: wrap;
	flex-direction: column;
	gap: 10px;
	justify-content: center;
	backdrop-filter: blur(10px);
	z-index: 1100;
}

.hide {
	display: none;
}

.tick {
	display: flex;
	justify-content: center;
}
</style>
</head>
<body>
	<div class="load-wrapper hide" id="loading">
		<div class="spinner-border load text-warning"></div>
	</div>

	<div class="popup-wrapper hide" id="contact-us-popup">

		<div class="popup-center m-5 p-5" id="">
			<div class="header">
				<h3 class="title mt-5">
					Your Message is Sent!! <span id="user_name"></span>
				</h3>
				<button type="button" class="btn-close text-reset"
					style="position: absolute; top: 5%; right: 5%;"
					onclick="togglePopup('contact-us-popup', true)"></button>
			</div>
			<div class="bb m-5 tick" id="0912">
				<svg xmlns="http://www.w3.org/2000/svg" viewBox="3 3 16 16"
					height="100px" width="100px">
<g transform="matrix(1.99997 0 0 1.99997-10.994-2071.68)" fill="#da4453">
<rect y="1037.36" x="7" height="8" width="8" fill="#32c671" rx="4" />
<path
						d="m123.86 12.966l-11.08-11.08c-1.52-1.521-3.368-2.281-5.54-2.281-2.173 0-4.02.76-5.541 2.281l-53.45 53.53-23.953-24.04c-1.521-1.521-3.368-2.281-5.54-2.281-2.173 0-4.02.76-5.541 2.281l-11.08 11.08c-1.521 1.521-2.281 3.368-2.281 5.541 0 2.172.76 4.02 2.281 5.54l29.493 29.493 11.08 11.08c1.52 1.521 3.367 2.281 5.54 2.281 2.172 0 4.02-.761 5.54-2.281l11.08-11.08 58.986-58.986c1.52-1.521 2.281-3.368 2.281-5.541.0001-2.172-.761-4.02-2.281-5.54"
						fill="#fff" transform="matrix(.0436 0 0 .0436 8.177 1039.72)"
						stroke="none" stroke-width="9.512" />
</g>
</svg>

			</div>
		</div>

	</div>

	<!-- Below For Eclipse-->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">

		<div class="container">

			<a class="navbar-brand" href="#" id="logo"><span class="brand">C</span>ash<span
				class="brand">N</span>ex</a>

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0" id="navbar-nav">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="home.jsp">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="userLogin.jsp">Dashboard</a>
					</li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							Accounts </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="userLogin.jsp">Loans</a></li>
							<li><a class="dropdown-item" href="userLogin.jsp">Transactions</a></li>
							<li><hr class="dropdown-divider"></li>
							<li><a class="dropdown-item" href="userLogin.jsp">Log in</a></li>
						</ul></li>
					<li class="nav-item"><a class="nav-link" target="_blank" href="https://maps.app.goo.gl/WgniDSKq3K1owfMo9">Location</a>
					</li>

				</ul>
			</div>
		</div>

	</nav>

	<div class="container">
		<h1>Contact Us</h1>
		<div class="contact-form">
			<form action="#" method="POST" onsubmit="sendMessage()" id="myForm">
				<div class="form-group">
					<label for="name">Your Name</label> <input type="text" name="name" id="name"
						required>
				</div>
				<div class="form-group">
					<label for="email">Your Email</label> <input type="email"
						id="email" name="email" required>
				</div>
				<div class="form-group">
					<label for="message">Your Message</label>
					<textarea id="message" name="message" required></textarea>
				</div>
				<div class="form-group">
					<button type="submit">Send Message</button>
					&emsp; or &emsp;<a href="mailto:waiyanhtut@ucsy.edu.mm"
						style="color: grey">mail us via email</a>
				</div>
			</form>
		</div>
	</div>
</body>
</body>
</html>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<script>
	function togglePopup(id, hide) {
		let popup = document.getElementById(id);
		if (hide == false) {
			popup.classList.remove("hide");
		} else {
			popup.classList.add("hide");
		}

	}

	function sendMessage() {
		togglePopup("loading", false);
		console.log("yay");
		setTimeout(function() {
			togglePopup("loading", true);
			togglePopup('contact-us-popup', false);
			console.log("yay in 5s");
		}, 5000);//to be executed after 5 second
		let name = document.getElementById("name");
		let email = document.getElementById("email");
		let message = document.getElementById("message");

		name.value = "";
		email.value = "";
		message.value = "";
	}

	var form = document.getElementById("myForm");
	function handleForm(event) {
		event.preventDefault();
	}
	form.addEventListener('submit', handleForm);
</script>

<!--
                       _oo0oo_
                      o8888888o
                      88" . "88
                      (| -_- |)
                      0\  =  /0
                    ___/`---'\___
                  .' \\|     |// '.
                 / \\|||  :  |||// \
                / _||||| -:- |||||- \
               |   | \\\  -  /// |   |
               | \_|  ''\---/''  |_/ |
               \  .-\__  '-'  ___/-. /
             ___'. .'  /--.--\  `. .'___
          ."" '<  `.___\_<|>_/___.' >' "".
         | | :  - \.;\ _ /;./ -  : | |
         \  \ _.   \_ __\ /__ _/   .- /  /
     =====-.____.___ \_____/___.-`___.-'=====
                       `=---='

     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
            ဘုရား        တရား        သံဃာ
     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    -->