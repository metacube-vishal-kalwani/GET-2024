import { empSection } from "./Employee";

  // Vehicle Details interface
  interface VehicleDetails {
    vehicleName: string;
    vehicleModel: string;
    vehicleType: string  | 'daily' | 'monthly' | 'yearly';
    vehicleNumber: string;
    empId: string;
  }

    // Object of vehicle's details
 export   const vehicleDetails: VehicleDetails = {
        vehicleName: "",
        vehicleModel: "",
        vehicleType: "",
        vehicleNumber: "",
        empId: "",
      };


export const vehicleSection = document.querySelector("#vehicle details") as HTMLElement;

      // Get Vehicle Details
export const getVehicleDetails = (): void => {
    const vehicleForm = document.querySelectorAll<HTMLLabelElement>('#vehicleForm label');
    const vehicleSection = document.getElementById('vehicleSection') as HTMLElement;

    vehicleSection.setAttribute('open', 'true');
    empSection.removeAttribute('open');

    let currentFieldIndex = 0;
    vehicleForm[currentFieldIndex].classList.add('displayBlock');

    const handleKeydown = (event: KeyboardEvent): void => {
        if (event.key === 'Enter') {
            event.preventDefault();
            const currentElement = vehicleForm[currentFieldIndex];
            const inputElement = currentElement.querySelector<HTMLInputElement>('input');

            if (!inputElement) return;

            const inputName = inputElement.name as keyof typeof vehicleDetails;
            let inputValue: string;

            if (inputElement.type === 'radio') {
                const selectedRadio = currentElement.querySelector<HTMLInputElement>('input[type="radio"]:checked');
                inputValue = selectedRadio ? selectedRadio.value : '';
            } else {
                inputValue = inputElement.value;
            }

            vehicleDetails[inputName] = inputValue;
            currentElement.classList.remove('displayBlock');
            currentFieldIndex++;

            if (currentFieldIndex < vehicleForm.length) {
                vehicleForm[currentFieldIndex].classList.add('displayBlock');
            } else {
                console.log('Vehicle Details:', vehicleDetails);
                document.getElementById('vehicleSubmitBtn')!.classList.add('showButton');
                document.removeEventListener('keydown', handleKeydown);
            }
        }
    };

    document.addEventListener('keydown', handleKeydown);
};



