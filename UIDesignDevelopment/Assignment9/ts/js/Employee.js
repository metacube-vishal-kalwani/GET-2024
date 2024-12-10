import { verify } from "./parking.js";
// Object of employee's details
const empDetails = {
    empName: "",
    empPassword: "",
    empEmail: "",
    empContact: "",
    empGender: "",
    empId: "",
};
// Element of employee and section
export const empSection = document.querySelector("#employee details");
/**
 * This function pops input feilds one by one upon pressing 'enter key' and stores
 * employee's data into the empDetails object and produces registration Id
 */
let registrationNo = 0;
const getEmpDetails = () => {
    registrationNo++;
    const registrationId = `MetaId${registrationNo}`;
    const empForm = document.querySelectorAll('#employeeForm label');
    empSection.setAttribute('open', 'true');
    let currentFieldIndex = 0;
    empForm[currentFieldIndex].classList.add('displayBlock');
    const handleKeydown = (event) => {
        var _a, _b;
        if (event.key === 'Enter') {
            event.preventDefault();
            const currentElement = empForm[currentFieldIndex];
            const inputElement = currentElement.querySelector('input');
            if (!inputElement)
                return;
            const inputName = inputElement.name;
            let inputValue;
            if (inputElement.type === 'radio') {
                const selectedRadio = currentElement.querySelector('input[type="radio"]:checked');
                inputValue = selectedRadio ? selectedRadio.value : '';
            }
            else {
                inputValue = inputElement.value;
            }
            const verifyResult = verify(inputValue, inputName);
            if (verifyResult.status) {
                const errorElement = document.querySelector('.error');
                errorElement.classList.remove('displayBlock');
                empDetails[inputName] = inputValue;
                currentElement.classList.remove('displayBlock');
                currentFieldIndex++;
                if (currentFieldIndex < empForm.length) {
                    empForm[currentFieldIndex].classList.add('displayBlock');
                }
                else {
                    console.log('Employee Details:', empDetails);
                    empSection.querySelector('summary h2').innerHTML = `Registration Id : ${registrationId}`;
                    empDetails['empId'] = registrationId;
                    document.getElementById('empFormSubmitBtn').classList.add('showButton');
                    document.removeEventListener('keydown', handleKeydown);
                }
            }
            else {
                const errorElement = document.querySelector('.error');
                errorElement.classList.add('displayBlock');
                errorElement.innerHTML = verifyResult.message || "invalid Input!";
                inputElement.focus();
            }
            if (empDetails.empName) {
                empSection.querySelector('.titleName').innerHTML = `Hi! ${empDetails.empName} Add your ${((_b = (_a = empForm[currentFieldIndex]) === null || _a === void 0 ? void 0 : _a.firstChild) === null || _b === void 0 ? void 0 : _b.textContent) || ''}`;
            }
            if (currentFieldIndex === empForm.length - 1) {
                empSection.querySelector('.titleName').innerHTML = '';
            }
        }
    };
    document.addEventListener('keydown', handleKeydown);
};
export { empDetails, getEmpDetails };
