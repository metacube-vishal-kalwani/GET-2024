import { CONTACT_REGEX, EMAIL_REGEX, NAME_REGEX, PASSWORD_REGEX } from "./data";
import { empDetails, getEmpDetails } from "./Employee";
import { currency } from "./misc";
import { getVehicleDetails, vehicleDetails, vehicleSection } from "./Vehicle";


interface verifyResult{
  status : boolean,
  message? : string
}

  // Pricing interface
  interface Pricing {
    daily: number;
    monthly: number;
    yearly: number;
  }
  
  // Main pricing object type
  interface PricingDetails {
    [key : string]: Pricing;
  }
  
 
  // Pricing for all types of vehicles
  export const pricing :PricingDetails ={
    'cycle': {
      'daily': 5,
      'monthly': 100,
      'yearly': 500,
    },
    'bike': {
      'daily': 10,
      "monthly": 200,
      'yearly': 1000,
    },
    'car': {
      'daily': 20,
      'monthly': 500,
      'yearly': 3500,
    },
  };

    
  /**
   * This function takes the input value and fieldName and verifies whether the input given by the user is valid or not
   * @param value input from user
   * @param fieldName field name
   * @returns returns object with status (true/false) and error message
   */
 export  const verify  = (value: string, fieldName: String):verifyResult => {
    
    switch (fieldName) {
      case "empName":
        return {
          status: NAME_REGEX.test(value),
          message: "Name should not contain special characters and length > 3",
        };
  
      case "empPassword":
        return {
          status: PASSWORD_REGEX.test(value),
          message: "At least one letter, one number, one special character, and minimum 8 characters",
        };
  
      case "empEmail":
        return {
          status: EMAIL_REGEX.test(value),
          message: "Enter a valid email (e.g - 123@gmail.com)",
        };
  
      case "empContact":
        return {
          status: CONTACT_REGEX.test(value),
          message: "Enter exactly 10 characters",
        };
  
      case "empGender":
        return {
          status: true,
          message: "",
        };
  
      case "confirmPassword":
        return {
          status: value === empDetails.empPassword,
          message: "Password not matching",
        };
  
      default:
        return { status: false, message: "Invalid field name" };
    }
  };

 

/**
 * Opens the pricing section and shows the price card of employee's vehicle type
 */
const showPricing = () => {
    const pricingSection = document.querySelector('#prices details') as HTMLElement
    pricingSection.setAttribute('open', 'true');
    vehicleSection.removeAttribute('open');
    // const priceCard = document.querySelector(`#${vehicleDetails.vehicleType} card`);
    const priceCard = document.querySelector(`#${vehicleDetails.vehicleType}Card`) as HTMLElement;
    console.log(priceCard);

    priceCard.classList.add('displayBlock');
}

/**
 * Takes the plan type and prints the total bill amount
 * @param {*} plan daily, monthly, yearly
 */
const getBill = (plan: keyof Pricing): void => {
  let element = document.querySelector('.billContainer') as HTMLElement;
  element.classList.add("displayBlock");

  let map: Record<keyof Pricing, string> = {
      'daily': 'day',
      'monthly': 'month',
      'yearly': 'year'
  };

  let planName = element.querySelector('#planName') as HTMLElement;
  let billValue = element.querySelector('#billValue') as HTMLElement;
  planName.innerHTML = "/" + map[plan];

  let vehicleType = vehicleDetails.vehicleType as keyof PricingDetails;
  billValue.dataset.amount = pricing[vehicleType][plan].toString();
  billValue.innerHTML = currency(pricing[vehicleType][plan]);
};


// adding an event listner to radio button of currency change
document.addEventListener("DOMContentLoaded",  ()=> {
    const radioContainer = document.getElementById("currencyRadioContainer") as HTMLElement;
    const valueSpans = document.querySelectorAll<HTMLSpanElement>(".value") ;

    // Function to update all spans with converted values
    const updateAllValues = ():void => {
        valueSpans.forEach((span) => {
            const baseAmount = parseFloat(span.dataset.amount || "0"); // Use data-amount as the base value
            span.innerHTML = currency(baseAmount);
        });
    }

    // Add event listener for radio button changes
    radioContainer.addEventListener("change", updateAllValues);

    // Initial update when the page loads
    updateAllValues();
});

export const verifyPassword = () => {
  let textField = document.getElementById('empPassword') as HTMLInputElement;
  let passwordText = textField.value;
  let testVar = PASSWORD_REGEX.test(passwordText);
  if (testVar) {
      if (passwordText.length > 12) {
          textField.style.borderColor = "green"
      } else {
          textField.style.borderColor = "orange"
      }
  } else {
      textField.style.borderColor = "red"
  }
}
(window as any).verifyPassword = verifyPassword;
(window as any).getVehicleDetails = getVehicleDetails;
(window as any).showPricing = showPricing;
(window as any).getBill = getBill;
// Call the function
getEmpDetails();