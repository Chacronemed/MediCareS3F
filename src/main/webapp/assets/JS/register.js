function afficherChampsSpecifiques() {
    var type = document.getElementById('type').value;
    var champsMedecin = document.getElementById('champsMedecin');
    var champsPatient = document.getElementById('champsPatient');

    if (type === 'medecin') {
        champsMedecin.style.display = 'block';
        champsPatient.style.display = 'none';
    } else if (type === 'patient') {
        champsMedecin.style.display = 'none';
        champsPatient.style.display = 'block';
    }
}

// Event listener to call the function on page load in case the default selection has specific fields
document.addEventListener('DOMContentLoaded', afficherChampsSpecifiques);
