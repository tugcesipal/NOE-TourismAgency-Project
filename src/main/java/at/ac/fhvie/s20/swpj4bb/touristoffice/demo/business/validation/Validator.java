package at.ac.fhvie.s20.swpj4bb.touristoffice.demo.business.validation;

/**
 * Interface for all validation classes.
 * @param <K> Class to be validated
 */
interface Validator<K> {

  String validate(K value);

}
