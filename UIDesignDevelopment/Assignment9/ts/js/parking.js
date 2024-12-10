import { CONTACT_REGEX, EMAIL_REGEX, NAME_REGEX, PASSWORD_REGEX } from "./data.js";
import { empDetails, getEmpDetails } from "./Employee.js";
import { currency } from "./misc.js";
import { getVehicleDetails, vehicleDetails, vehicleSection } from "./Vehicle.js";
// Pricing for all types of vehicles
export const pricing = {
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
export const verify = (value, fieldName) => {
    switch (fieldName) {
        case "empName":
            return {
                status: NAME_REGEX.test(value),
                message: "Name should not contain special characters and length > 3",
            };
        case "empPassword":
            return {
                status: value.length > 7,
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
    const pricingSection = document.querySelector('#prices details');
    pricingSection.setAttribute('open', 'true');
    vehicleSection.removeAttribute('open');
    // const priceCard = document.querySelector(`#${vehicleDetails.vehicleType} card`);
    const priceCard = document.querySelector(`#${vehicleDetails.vehicleType}Card`);
    console.log(priceCard);
    priceCard.classList.add('displayBlock');
};
/**
 * Takes the plan type and prints the total bill amount
 * @param {*} plan daily, monthly, yearly
 */
const getBill = (plan) => {
    let element = document.querySelector('.billContainer');
    element.classList.add("displayBlock");
    let map = {
        'daily': 'day',
        'monthly': 'month',
        'yearly': 'year'
    };
    let planName = element.querySelector('#planName');
    let billValue = element.querySelector('#billValue');
    planName.innerHTML = "/" + map[plan];
    let vehicleType = vehicleDetails.vehicleType;
    billValue.dataset.amount = pricing[vehicleType][plan].toString();
    billValue.innerHTML = currency(pricing[vehicleType][plan]);
};
// adding an event listner to radio button of currency change
document.addEventListener("DOMContentLoaded", () => {
    const radioContainer = document.getElementById("currencyRadioContainer");
    const valueSpans = document.querySelectorAll(".value");
    // Function to update all spans with converted values
    const updateAllValues = () => {
        valueSpans.forEach((span) => {
            const baseAmount = parseFloat(span.dataset.amount || "0"); // Use data-amount as the base value
            span.innerHTML = currency(baseAmount);
        });
    };
    // Add event listener for radio button changes
    radioContainer.addEventListener("change", updateAllValues);
    // Initial update when the page loads
    updateAllValues();
});


export const verifyPassword = () => {
    let textField = document.getElementById('empPassword');
    let passwordText = textField.value;
    console.log(passwordText)
    let testVar = passwordText.length > 7;
    console.log(testVar)
    if (testVar) {
        if (passwordText.length > 12) {
            textField.style.borderColor = "green";
        }
        else {
            textField.style.borderColor = "orange";
        }
    }
    else {
        textField.style.borderColor = "red";
    }
};


window.verifyPassword = verifyPassword;
window.getVehicleDetails = getVehicleDetails;
window.showPricing = showPricing;
window.getBill = getBill;
// Call the function
getEmpDetails();
