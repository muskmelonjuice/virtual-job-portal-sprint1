$(document).ready(function () {
  // === Helper Functions ===
  function isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  }

  function isStrongPassword(password) {
    return (
      password.length >= 7 &&
      /[A-Za-z]/.test(password) &&
      /\d/.test(password) &&
      /[@$!%*?&]/.test(password)
    );
  }

  function updateRule(selector, isValid) {
    const label = $(selector).text().substring(2);
    $(selector)
      .text((isValid ? "✅ " : "❌ ") + label)
      .removeClass("text-danger text-success")
      .addClass(isValid ? "text-success" : "text-danger");
  }

  // === Live Password Rules ===
  $('#recruiterPassword').on('input', function () {
    const pwd = $(this).val();
    const rules = $(this).siblings('.password-rules');
    updateRule(rules.find('.rule-length'), pwd.length >= 7);
    updateRule(rules.find('.rule-letter'), /[A-Za-z]/.test(pwd));
    updateRule(rules.find('.rule-number'), /\d/.test(pwd));
    updateRule(rules.find('.rule-special'), /[@$!%*?&]/.test(pwd));
  });

  // === Recruiter Registration Form Validation ===
  $('#recruiterRegistrationForm').on('submit', function (e) {
    let isValid = true;

    const company = $('#companyName').val().trim();
    const email = $('#companyEmail').val().trim();
    const contact = $('#recruiterName').val().trim();
    const pwd = $('#recruiterPassword').val();
    const cpwd = $('#confirmRecruiterPassword').val();
    const site = $('#companyWebsite').val().trim();
    const desc = $('#companyDescription').val().trim();

    if (company === "") { $('#companyName').addClass('is-invalid'); isValid = false; } else { $('#companyName').removeClass('is-invalid'); }
    if (!isValidEmail(email)) { $('#companyEmail').addClass('is-invalid'); isValid = false; } else { $('#companyEmail').removeClass('is-invalid'); }
    if (contact.length < 3) { $('#recruiterName').addClass('is-invalid'); isValid = false; } else { $('#recruiterName').removeClass('is-invalid'); }
    if (!isStrongPassword(pwd)) { $('#recruiterPassword').addClass('is-invalid'); isValid = false; } else { $('#recruiterPassword').removeClass('is-invalid'); }
    if (pwd !== cpwd) { $('#confirmRecruiterPassword').addClass('is-invalid'); isValid = false; } else { $('#confirmRecruiterPassword').removeClass('is-invalid'); }
    if (site && !/^https?:\/\/.+\..+/.test(site)) { $('#companyWebsite').addClass('is-invalid'); isValid = false; } else { $('#companyWebsite').removeClass('is-invalid'); }
    if (desc === "") { $('#companyDescription').addClass('is-invalid'); isValid = false; } else { $('#companyDescription').removeClass('is-invalid'); }

    if (!isValid) e.preventDefault();
  });
});
