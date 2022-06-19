package uri;

import uri.implementation.UriParserImplementation;

/**
 * A factory class for creating {@link UriParser} instances.
 *
 * Do not change the name or pre-defined publicly visible method signatures of
 * this class
 */
public final class UriParserFactory {

	/**
	 * @param uri
	 *            The URI that will be parsed
	 * @return A parser object for the given uri or {@code null} if {@code uri}
	 *         is {@code null}
	 */




	public static UriParser create(String uri) {
		if (uri == null )
		return null;
		UriParserImplementation result = new UriParserImplementation();
		result.uri = uri;
		result.delem = uri.contains("://");
		result.userinfo=uri.contains("@");
		return result;
	}

}
