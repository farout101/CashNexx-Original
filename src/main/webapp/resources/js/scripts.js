/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */
    // 
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        // Uncomment Below to persist sidebar toggle between refreshes
        // if (localStorage.getItem('sb|sidebar-toggle') === 'true') {
        //     document.body.classList.toggle('sb-sidenav-toggled');
        // }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});




function togglePopup(id, hide) {
    let popup = document.getElementById(id);
	if(hide==false) {
		popup.classList.remove("hide");
	} else {
		popup.classList.add("hide");
	}
}


var slider = document.getElementById("payLoanRange");
var num = document.getElementById("payLoanValue");
slider.oninput = function updateTextInput() {
    num.value = slider.value;
}



var form_id = document.getElementById("pay_loan_id");
var form_acc_num = document.getElementById("pay_loan_acc_num");
var form_left = document.getElementById("pay_loan_left_amount");
var form_pay_amount = document.getElementById("payLoanRange");

function fillIn(id) {
    let abc = document.getElementById(id);
    let paid_amount = parseFloat(abc.lastElementChild.previousElementSibling.previousElementSibling.previousElementSibling.innerHTML);
    let left_amount = parseFloat(abc.lastElementChild.previousElementSibling.previousElementSibling.innerHTML);
    if (left_amount < 0) {
        left_amount = 0; // Ensure left amount is not negative
    }
    form_id.value = abc.firstElementChild.innerHTML;
    form_acc_num.value = paid_amount; // Display paid amount instead of account number
    form_left.value = left_amount;
    form_pay_amount.max = left_amount;
}

