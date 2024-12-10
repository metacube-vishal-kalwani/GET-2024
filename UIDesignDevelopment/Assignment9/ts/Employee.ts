import { verify } from "./parking";

// Employee Details interface
interface EmployeeDetails {
    empName: string;
    empPassword: string;
    empEmail: string;
    empContact: string;
    empGender: string;
    empId: string;
  }
  
 // Object of employee's details
 const empDetails: EmployeeDetails = {
    empName: "",
    empPassword: "",
    empEmail: "",
    empContact: "",
    empGender: "",
    empId: "",
  };


  // Element of employee and section
  export const empSection = document.querySelector("#employee details") as HTMLElement;

/**
 * This function pops input feilds one by one upon pressing 'enter key' and stores
 * employee's data into the empDetails object and produces registration Id 
 */
let registrationNo = 0;
const getEmpDetails = (): void => {
    registrationNo++;
    const registrationId = `MetaId${registrationNo}`;

    const empForm = document.querySelectorAll<HTMLLabelElement>('#employeeForm label');
    const empSection = document?.getElementById('empSection') as HTMLElement;
    empSection.setAttribute('open', 'true');

    let currentFieldIndex = 0;
    empForm[currentFieldIndex].classList.add('displayBlock');

    const handleKeydown = (event: KeyboardEvent): void => {
        if (event.key === 'Enter') {
            event.preventDefault();
            const currentElement = empForm[currentFieldIndex];
            const inputElement = currentElement.querySelector<HTMLInputElement>('input');

            if (!inputElement) return;

            const inputName = inputElement.name as keyof typeof empDetails;
            let inputValue: string;

            if (inputElement.type === 'radio') {
                const selectedRadio = currentElement.querySelector<HTMLInputElement>('input[type="radio"]:checked');
                inputValue = selectedRadio ? selectedRadio.value : '';
            } else {
                inputValue = inputElement.value;
            }

            const verifyResult = verify(inputValue, inputName);

            if (verifyResult.status) {
                const errorElement = document.querySelector('.error') as HTMLElement;
                errorElement.classList.remove('displayBlock');
                empDetails[inputName] = inputValue;

                currentElement.classList.remove('displayBlock');
                currentFieldIndex++;

                if (currentFieldIndex < empForm.length) {
                    empForm[currentFieldIndex].classList.add('displayBlock');
                } else {
                    console.log('Employee Details:', empDetails);
                    empSection.querySelector('summary h2')!.innerHTML = `Registration Id : ${registrationId}`;
                    empDetails['empId'] = registrationId;
                    document.getElementById('empFormSubmitBtn')!.classList.add('showButton');
                    document.removeEventListener('keydown', handleKeydown);
                }
            } else {
                const errorElement = document.querySelector('.error') as HTMLElement;
                errorElement.classList.add('displayBlock');
                errorElement.innerHTML = verifyResult.message || "invalid Input!";
                inputElement.focus();
            }

            if (empDetails.empName) {
                empSection.querySelector('.titleName')!.innerHTML = `Hi! ${empDetails.empName} Add your ${empForm[currentFieldIndex]?.firstChild?.textContent || ''}`;
            }

            if (currentFieldIndex === empForm.length - 1) {
                empSection.querySelector('.titleName')!.innerHTML = '';
            }
        }
    };

    document.addEventListener('keydown', handleKeydown);
};

export  {empDetails, getEmpDetails}