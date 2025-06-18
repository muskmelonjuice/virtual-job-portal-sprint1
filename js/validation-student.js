$(document).ready(function () {
  $('#studentPassword').on('input', function () {
    const pwd = $(this).val();
    const rules = $(this).siblings('.password-rules');
    updateRule(rules.find('.rule-length'), pwd.length >= 7);
    updateRule(rules.find('.rule-letter'), /[A-Za-z]/.test(pwd));
    updateRule(rules.find('.rule-number'), /\d/.test(pwd));
    updateRule(rules.find('.rule-special'), /[@$!%*?&]/.test(pwd));
  });

  $('#studentRegistrationForm').on('submit', function (e) {
    let isValid = true;

    const name = $('#studentName').val().trim();
    const email = $('#studentEmail').val().trim();
    const pwd = $('#studentPassword').val();
    const cpwd = $('#confirmStudentPassword').val();
    const major = $('#studentMajor').val().trim();
    const resume = $('#studentResume').val().trim();

    if (name.length < 3) { $('#studentName').addClass('is-invalid'); isValid = false; } else { $('#studentName').removeClass('is-invalid'); }
    if (!isValidEmail(email)) { $('#studentEmail').addClass('is-invalid'); isValid = false; } else { $('#studentEmail').removeClass('is-invalid'); }
    if (!isStrongPassword(pwd)) { $('#studentPassword').addClass('is-invalid'); isValid = false; } else { $('#studentPassword').removeClass('is-invalid'); }
    if (pwd !== cpwd) { $('#confirmStudentPassword').addClass('is-invalid'); isValid = false; } else { $('#confirmStudentPassword').removeClass('is-invalid'); }
    if (major === "") { $('#studentMajor').addClass('is-invalid'); isValid = false; } else { $('#studentMajor').removeClass('is-invalid'); }
    if (!resume.endsWith('.pdf')) { $('#studentResume').addClass('is-invalid'); isValid = false; } else { $('#studentResume').removeClass('is-invalid'); }

    if (!isValid) e.preventDefault();
  });
});
