// Common reusable validation functions
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

function isValidPhone(phone) {
  return /^\+?\d{10,15}$/.test(phone);
}

function updateRule(selector, isValid) {
  const label = $(selector).text().substring(2);
  $(selector)
    .text((isValid ? "✅ " : "❌ ") + label)
    .removeClass("text-danger text-success")
    .addClass(isValid ? "text-success" : "text-danger");
}
