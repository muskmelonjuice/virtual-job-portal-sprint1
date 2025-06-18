$(document).ready(function () {
  $('#jobApplicationForm').on('submit', function (e) {
    let isValid = true;

    const name = $('#applicantName').val().trim();
    const email = $('#applicantEmail').val().trim();
    const phone = $('#applicantPhone').val().trim();
    const resume = $('#applicantResume').val().trim();

    if (name === "") { $('#applicantName').addClass('is-invalid'); isValid = false; } else { $('#applicantName').removeClass('is-invalid'); }
    if (!isValidEmail(email)) { $('#applicantEmail').addClass('is-invalid'); isValid = false; } else { $('#applicantEmail').removeClass('is-invalid'); }
    if (!isValidPhone(phone)) { $('#applicantPhone').addClass('is-invalid'); isValid = false; } else { $('#applicantPhone').removeClass('is-invalid'); }
    if (!resume.endsWith('.pdf')) { $('#applicantResume').addClass('is-invalid'); isValid = false; } else { $('#applicantResume').removeClass('is-invalid'); }

    if (!isValid) {
      e.preventDefault();
      return;
    }

    // ✅ Application is valid – show success message
    e.preventDefault(); // Stop actual submission
    alert("✅ Application submitted successfully!");

    // Optional: Reset the form
    this.reset();
    $('.is-invalid').removeClass('is-invalid');
  });
});
