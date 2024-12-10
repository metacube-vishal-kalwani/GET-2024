

// // object of employee's Details
// const empDetails = {
//     empName: "",
//     empPassword: "",
//     empEmail: "",
//     empContact: "",
//     empGender: "",
//     empId: ""
// }

// // object of vehicle's details
// const vehicleDetails = {
//     vehicleName: "",
//     vehicleModel: "",
//     vehicleType: "",
//     vehicleNumber: "",
//     empId: "",
// }

// // pricing for all type of vehicle
// const pricing = {
//     "cycle": {
//         "daily": 5,
//         "monthly": 100,
//         "yearly": 500
//     },
//     "bike": {
//         "daily": 10,
//         "monthly": 200,
//         "yearly": 1000
//     },
//     "car": {
//         "daily": 20,
//         "monthly": 500,
//         "yearly": 3500
//     }
// }


// // all Regex Expresions
// const NAME_REGEX = RegExp("^([a-zA-Z ]){3,30}$");
// const PASSWORD_REGEX = RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
// const CONTACT_REGEX = RegExp("^\\d{10}$");
// const EMAIL_REGEX = RegExp("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+[.]+[a-zA-Z0-9-.]+$");

// // elements of employee and vehicle's section
// const empSection = document.querySelector('#employee details');
// const vehicleSection = document.querySelector('#vehicle details');

// let registrationNo = 0;

// /**
//  * This function takes the input value and fieldName and varifies whether the input given by user if valid or not
//  * @param {*} value input from user
//  * @param {*} fieldName feildname
//  * @returns returns object with status (true/false) and error message
//  */
// const verify = (value, fieldName) => {
//     switch (fieldName) {
//         case "empName":
//             return {
//                 status: NAME_REGEX.test(value),
//                 message: "Name should not contain special characters and length > 3"
//             }

//         case "empPassword":
//             return {
//                 status: PASSWORD_REGEX.test(value),
//                 message: "At least one letter, one number, one special character, and minimum 8 characters"
//             }
//         case "empEmail":
//             return {
//                 status: EMAIL_REGEX.test(value),
//                 message: 'Enter a valid email (e.g - 123@gmail.com)'
//             }
//         case "empContact":
//             return {
//                 status: CONTACT_REGEX.test(value),
//                 message: "Enter exactly 10 characters"
//             }

//         case "empGender":
//             return {
//                 status: true,
//                 message: ''
//             }

//         case "confirmPassword":
//             return {
//                 status: value === empDetails.empPassword,
//                 message: 'Password not matching'
//             }

//         default:
//             return false;
//     }
// };


// /**
//  * This function pops input feilds one by one upon pressing 'enter key' and stores
//  * employee's data into the empDetails object and produces registration Id
//  */
// const getEmpDetails = () => {
//     registrationNo++;
//     // creating registration Id
//     let registrationId = `MetaId${registrationNo}`;

//     // list of all employee Form Feilds
//     const empForm = document.querySelectorAll('#employeeForm label');

//     // to keep the details tag expended
//     empSection.setAttribute('open', 'true');

//     let currentFieldIndex = 0;
//     empForm[currentFieldIndex].classList.add('displayBlock');

//     function handleKeydown(event) {
//         if (event.key === "Enter") {
//             event.preventDefault();
//             const currentElement = empForm[currentFieldIndex];
//             const inputElement = currentElement.querySelector('input');

//             // if there is not input element!
//             if (!inputElement) return;

//             let inputName = inputElement.name;
//             let inputValue

//             // getting value from radio input
//             if (inputElement.type === 'radio') {
//                 const selectedRadio = currentElement.querySelector('input[type="radio"]:checked');
//                 inputValue = selectedRadio ? selectedRadio.value : "";
//             } else {
//                 inputValue = inputElement.value;
//             }

//             // Validate the current field
//             let verifyResult = verify(inputValue, inputName);
//             if (verifyResult.status && inputValue != "") {

//                 // removing the error message from UI
//                 document.querySelector('.error').classList.remove('displayBlock')

//                 empDetails[inputName] = inputValue; // update empDetails object
//                 currentElement.classList.remove('displayBlock');

//                 // Move to the next field
//                 currentFieldIndex++;
//                 if (currentFieldIndex < empForm.length) {
//                     empForm[currentFieldIndex].classList.add('displayBlock');

//                 } else {
//                     console.log("Employee Details:", empDetails);
//                     empSection.querySelector('summary h2').innerHTML = `Registration Id : ${registrationId} `;
//                     empDetails['empId'] = registrationId;
//                     document.getElementById('empFormSubmitBtn').classList.add('showButton');
//                     document.removeEventListener('keydown', handleKeydown); // Remove listener
//                 }

//             } else if (verifyResult.status == false) {
//                 document.querySelector('.error').classList.add('displayBlock')
//                 document.querySelector('.error').innerHTML = verifyResult.message;
//                 inputElement.focus(); // Focus back on the invalid field
//             }
//             if (empDetails.empName !== "") {
//                 empSection.querySelector('.titleName').innerHTML = "Hi! " + empDetails.empName + ` Add your ${empForm[currentFieldIndex].firstChild.textContent}`
//             }

//             if (currentFieldIndex === empForm.length - 1) {
//                 empSection.querySelector('.titleName').innerHTML = "";
//             }

//         }
//     };

//     // attaching handleKeydown function to keydown event
//     document.addEventListener('keydown', handleKeydown)
// };





// const getVehicleDetails = () => {
//     const vehicleForm = document.querySelectorAll('#vehicleForm label');
//     vehicleSection.setAttribute('open', 'true');
//     empSection.removeAttribute('open', 'true');
//     let currentElementIndex = 0;

//     // Show the first label
//     vehicleForm[currentElementIndex].classList.add('displayBlock');

//     const handleKeydown = (event) => {
//         if (event.key === "Enter") {
//             event.preventDefault();
//             const currentElement = vehicleForm[currentElementIndex];
//             const inputElement = currentElement.querySelector('input');
//             if (!inputElement) return; // Skip if no input found

//             let inputName = inputElement.name;
//             let inputValue;
//             if (inputElement.type === 'radio') {
//                 const selectedRadio = currentElement.querySelector('input[type="radio"]:checked');
//                 inputValue = selectedRadio ? selectedRadio.value : "";
//             } else {
//                 inputValue = inputElement.value;
//             }


//             vehicleDetails[inputName] = inputValue;
//             currentElement.classList.remove('displayBlock');

//             currentElementIndex++;
//             if (currentElementIndex < vehicleForm.length) {
//                 vehicleForm[currentElementIndex].classList.add('displayBlock');
//             } else {
//                 // All fields completed
//                 console.log("Vehicle Details:", vehicleDetails);
//                 document.getElementById('vehicleSubmitBtn').classList.add('showButton'); // Show submit button
//                 document.removeEventListener('keydown', handleKeydown);
//             }
//         }
//     };

//     // Attach the event listener
//     document.addEventListener('keydown', handleKeydown);
// }


// const verifyPassword = () => {
//     let textField = document.getElementById('empPassword');
//     let passwordText = textField.value;
//     let testVar = PASSWORD_REGEX.test(passwordText);
//     if (testVar) {
//         if (passwordText.length > 12) {
//             textField.style.borderColor = "green"
//         } else {
//             textField.style.borderColor = "orange"
//         }
//     } else {
//         textField.style.borderColor = "red"
//     }
// }



// /**
//  * Opens the pricing section and shows the price card of employee's vehicle type
//  */
// const showPricing = () => {
//     const pricingSection = document.querySelector('#prices details')
//     pricingSection.setAttribute('open', 'true');
//     vehicleSection.removeAttribute('open', 'true');
//     // const priceCard = document.querySelector(`#${vehicleDetails.vehicleType} card`);
//     const priceCard = document.querySelector(`#${vehicleDetails.vehicleType}Card`);
//     console.log(priceCard);

//     priceCard.classList.add('displayBlock');
// }


// /**
//  * Takes the plan type and prints the total bill amount
//  * @param {*} plan daily, monthly, yearly
//  */
// const getBill = (plan) => {
//     let element = document.querySelector('.billContainer');
//     element.classList.add("displayBlock");

//     let map = {
//         'daily': 'day',
//         'monthly': 'month',
//         'yearly': 'year'
//     }

//     element.querySelector('#planName').innerHTML = "/" + map[plan];
//     element.querySelector('#billValue').dataset.amount = (pricing['car'][plan.toString()]);
//     element.querySelector('#billValue').innerHTML = currency(pricing['car'][plan.toString()]);
// }



// // Function to convert currency
// const currency = (value) => {
//     const checkedCurrency = document.querySelector(
//         '#currencyRadioContainer input[type="radio"]:checked'
//     );
//     if (!checkedCurrency) return `${value}₹`; // Default if no radio is checked

//     const currencyType = checkedCurrency.value;
//     switch (currencyType) {
//         case "INR":
//             return `${value}₹`;
//         case "USD":
//             return `${parseFloat(value * 0.012).toFixed(2)} $`;
//         case "YEN":
//             return `${parseFloat(value * 1.76).toFixed(2)} YEN`;
//         default:
//             return `${value}₹`;
//     }
// }


// // adding an event listner to radio button of currency change
// document.addEventListener("DOMContentLoaded", function () {
//     const radioContainer = document.getElementById("currencyRadioContainer");
//     const valueSpans = document.querySelectorAll(".value");

//     // Function to update all spans with converted values
//     const updateAllValues = () => {
//         valueSpans.forEach((span) => {
//             const baseAmount = parseFloat(span.dataset.amount) || 0; // Use data-amount as the base value
//             span.innerHTML = currency(baseAmount);
//         });
//     }

//     // Add event listener for radio button changes
//     radioContainer.addEventListener("change", updateAllValues);

//     // Initial update when the page loads
//     updateAllValues();
// });

// // Call the function
// getEmpDetails();


