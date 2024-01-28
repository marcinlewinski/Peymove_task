import * as Yup from "yup";

export const RegisterSchema = Yup.object({
    name: Yup.string()
        .trim()
        .matches(/\S/, "You can't enter only spaces!").required('Required'),
    email: Yup.string().email('Invalid email format').required('Required'),
    password: Yup.string()
        .trim()
        .matches(/\S/, "You can't enter only spaces!")
        .required('Required')
        .min(8, 'Password must be at least 8 characters')
        .matches(/[a-zA-Z]/, 'Password must contain at least one letter')
        .matches(/[0-9]/, 'Password must contain at least one number'),
});