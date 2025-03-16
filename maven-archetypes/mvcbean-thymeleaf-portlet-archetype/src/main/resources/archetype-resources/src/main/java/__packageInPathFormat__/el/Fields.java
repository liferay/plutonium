package ${package}.el;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

@Named
@ApplicationScoped
public class Fields {

	@Inject
	private BindingResult bindingResult;

	public String error(String field) {

		StringBuilder sb = new StringBuilder();
		Collection<String> errors = errors(field);

		boolean first = true;

		for (String error : errors) {

			if (first) {
				first = false;
			}
			else {
				sb.append(" ");
			}

			sb.append(error);
		}

		return sb.toString();
	}

	public Collection<String> errors(String field) {

		if (field == null) {
			return null;
		}

		Set<String> errors = new LinkedHashSet<>();

		Set<ParamError> bindingResultErrors = bindingResult.getErrors(field);

		for (ParamError bindingResultError : bindingResultErrors) {
			errors.add(bindingResultError.getMessage());
		}

		return errors;
	}

	public boolean hasErrors(String field) {

		if (field == null) {
			return false;
		}

		Set<ParamError> bindingResultErrors = bindingResult.getErrors(field);

		return !bindingResultErrors.isEmpty();
	}
}
