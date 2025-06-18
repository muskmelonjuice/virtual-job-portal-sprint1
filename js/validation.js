$(document).ready(function () {
  // === LIVE PASSWORD CHECK FOR BOTH FORMS ===
  $('.login-field[type="password"]').on('input', function () {
    const password = $(this).val();
    const rulesContainer = $(this).next('.password-rules');
    rulesContainer.show();

    updateRule(rulesContainer.find('.rule-length'), password.length >= 7);
    updateRule(rulesContainer.find('.rule-letter'), /[A-Za-z]/.test(password));
    updateRule(rulesContainer.find('.rule-number'), /\d/.test(password));
    updateRule(rulesContainer.find('.rule-special'), /[@$!%*?&]/.test(password));
  });

  function updateRule($el, passed) {
    const label = $el.text().substring(2); // remove emoji
    $el.text((passed ? "✅ " : "❌ ") + label)
       .removeClass("text-danger text-success")
       .addClass(passed ? "text-success" : "text-danger");
  }

  // === STUDENT LOGIN FORM ===
  $('#studentLoginModal form').on('submit', function (e) {
    const fields = $(this).find('.login-field');
    const email = fields.eq(0).val().trim();
    const password = fields.eq(1).val();
    let isValid = true;

    if (!validateEmail(email)) {
      alert("⚠ Please enter a valid student email.");
      isValid = false;
    }

    if (!validateStrongPassword(password)) {
      alert("⚠ Password must meet all criteria shown.");
      isValid = false;
    }

    if (!isValid) e.preventDefault();
  });

  // === RECRUITER LOGIN FORM ===
  $('#recruiterLoginModal form').on('submit', function (e) {
    const fields = $(this).find('.login-field');
    const email = fields.eq(0).val().trim();
    const password = fields.eq(1).val();
    let isValid = true;

    if (!validateEmail(email)) {
      alert("⚠ Please enter a valid recruiter email.");
      isValid = false;
    }

    if (!validateStrongPassword(password)) {
      alert("⚠ Password must meet all criteria shown.");
      isValid = false;
    }

    if (!isValid) e.preventDefault();
  });

  // === VALIDATORS ===
  function validateEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  }

  function validateStrongPassword(password) {
    return (
      password.length >= 7 &&
      /[A-Za-z]/.test(password) &&
      /\d/.test(password) &&
      /[@$!%*?&]/.test(password)
    );
  }
});
