$(document).ready(function () {
  $('#forgotForm').on('submit', function (e) {
    e.preventDefault(); // Prevent actual form submission

    const email = $('#emailInput').val().trim();
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (email === '') {
      alert('Please enter your email address.');
    } else if (!emailPattern.test(email)) {
      alert('Please enter a valid email address.');
    } else {
      alert('Your reset link has been sent successfully.');
      $('#emailInput').val(''); // Optional: clear input field
    }
  });
});
