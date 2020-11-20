package com.uniacademia.enade.api.utils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.uniacademia.enade.api.response.ResponseError;

public class Messages {

	public static ResponseError getAuthenticationError(final String key, final Object... params) {
		ResourceBundle bundle = ResourceBundle.getBundle("AuthenticationMessages", Locale.getDefault());
		return getErrorMessage(bundle, key, params);
	}
	
	public static ResponseError getUserTypeError(final String key, final Object... params) {
		ResourceBundle bundle = ResourceBundle.getBundle("UseTypeMessages", Locale.getDefault());
		return getErrorMessage(bundle, key, params);
	}

	private static ResponseError getErrorMessage(ResourceBundle bundle, final String key, final Object... params) {
		List<String> keys = convert(key);

		String text = bundle.getString(keys.get(1));
		text = MessageFormat.format(text, params);

		String title = bundle.getString(keys.get(0));
		return ResponseError.convert(title, text);
	}

	private static List<String> convert(String key) {
		String text = key.concat(".text");
		String title = key.concat(".title");
		
		List<String> converted = new ArrayList<String>() {
			private static final long serialVersionUID = 66748189137L;
			{
				add(title);
				add(text);
			}
		};

		return converted;
	}

}
