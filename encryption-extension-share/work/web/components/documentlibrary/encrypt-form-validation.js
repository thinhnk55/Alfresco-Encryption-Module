Alfresco.forms.validation.encryptFormValidation = function encryptFormValidation(field, args, event, form, silent, message) {
  
    var pass = field.form.prop_sc_pass.value;

    if( pass.length < 4 ) {
        form.addError(form.getFieldLabel(field.id) + " Password must be longer 4 characters" , field);
        return false;
    }

    return true;
};