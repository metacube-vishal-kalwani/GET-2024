import { empSection } from "./Employee.js";
// Object of vehicle's details
export const vehicleDetails = {
    vehicleName: "",
    vehicleModel: "",
    vehicleType: "",
    vehicleNumber: "",
    empId: "",
};
export const vehicleSection = document.querySelector("#vehicle details");
// Get Vehicle Details
export const getVehicleDetails = () => {
    const vehicleForm = document.querySelectorAll('#vehicleForm label');
    vehicleSection.setAttribute('open', 'true');
    empSection.removeAttribute('open');
    let currentFieldIndex = 0;
    vehicleForm[currentFieldIndex].classList.add('displayBlock');
    const handleKeydown = (event) => {
        if (event.key === 'Enter') {
            event.preventDefault();
            const currentElement = vehicleForm[currentFieldIndex];
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
            vehicleDetails[inputName] = inputValue;
            currentElement.classList.remove('displayBlock');
            currentFieldIndex++;
            if (currentFieldIndex < vehicleForm.length) {
                vehicleForm[currentFieldIndex].classList.add('displayBlock');
            }
            else {
                console.log('Vehicle Details:', vehicleDetails);
                document.getElementById('vehicleSubmitBtn').classList.add('showButton');
                document.removeEventListener('keydown', handleKeydown);
            }
        }
    };
    document.addEventListener('keydown', handleKeydown);
};
