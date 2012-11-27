Alfresco.forms.validation.decryptFormValidation = function decryptFormValidation(field, args, event, form, silent, message) {
  
    var valid = true;

    var pass = field.form.prop_sc_pass.value;
    var old_pass = document.properties["sc:pass"];
    if( pass.length < 4 ) {
        form.addError(form.getFieldLabel(field.id) + " Password must be longer 4 characters" , field);
        return false;
    }
    if( pass != old_pass ) {
        form.addError(form.getFieldLabel(field.id) + " Password not match !" , field);
        return false;
    }
    return true;
};