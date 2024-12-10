// All Regex Expressions
export const NAME_REGEX = new RegExp("^([a-zA-Z ]){3,30}$");
export const PASSWORD_REGEX = new RegExp("^(?=.[a-z])(?=.[A-Z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$");
export const CONTACT_REGEX = new RegExp("^\\d{10}$");
export const EMAIL_REGEX = new RegExp("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+[.]+[a-zA-Z0-9-.]+$");
export const INR_TO_DOLLAR = 0.012;
export const INR_TO_YEN = 1.76;
