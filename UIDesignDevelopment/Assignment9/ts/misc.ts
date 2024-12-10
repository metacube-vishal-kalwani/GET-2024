import { INR_TO_DOLLAR, INR_TO_YEN, PASSWORD_REGEX } from "./data";


/**
 * Converts numric value to currency based upon the selected radio value!
 * @param value takes currency in rupees
 * @returns converts and return corresponding dollor and yen value
 */
export const currency = (value: number): string => {
    const checkedCurrency = document.querySelector(
      '#currencyRadioContainer input[type="radio"]:checked'
    ) as HTMLInputElement | null;
  
    if (!checkedCurrency) {
      return `${value}₹`; // Default if no radio is checked
    }
  
    const currencyType = checkedCurrency.value;
  
    switch (currencyType) {
      case "INR":
        return `${value}₹`;
      case "USD":
        return `${(value * INR_TO_DOLLAR).toFixed(2)} $`;
      case "YEN":
        return `${(value * INR_TO_YEN).toFixed(2)} YEN`;
      default:
        return `${value}₹`;
    }
  };


 