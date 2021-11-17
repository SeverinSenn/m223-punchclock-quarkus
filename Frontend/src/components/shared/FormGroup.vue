<template>
  <div>
    <slot
      :attrs="{ error: hasErrors, 'error-message': firstErrorMessage }"
     
    />
  </div>
</template>
<script>
import { singleErrorExtractorMixin } from "vuelidate-error-extractor";
export default {
  name: "FormGroup",
  extends: singleErrorExtractorMixin,
  inheritAttrs: false,
};
</script>
