import VuelidateErrorExtractor, { templates } from "vuelidate-error-extractor";
import FormGroup from "../components/shared/FormGroup.vue";
import { boot } from 'quasar/wrappers'
// import FormSummary from "../components/FormSummary.vue";

const validationKeys = {
  // minLength: { // Validation rule name in vuelidate
  //   validationKey: 'min.string', // Validation key inside our deep messages object, Laravel shown here.
  //   params: [
  //     {
  //       vue: 'min', // Vuelidate param name
  //       ext: 'min' // Messages param name
  //     }
  //   ]
  // },
  // sameAs: {
  //   validationKey: 'same',
  //   params: [
  //     {
  //       vue: 'eq', // Vuelidate uses eq for other value 
  //       ext: 'other' // Laravel uses other
  //     }
  //   ]
  // }
}


export default boot(({ app }) => {
  /*
    app.use(VuelidateErrorExtractor, {
      i18n: 'validation',
      validationKeys
    })
  
    app.component("FormGroup", FormGroup);
    app.component("formWrapper", templates.FormWrapper);*/
})
