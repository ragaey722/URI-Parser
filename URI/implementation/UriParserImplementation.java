package uri.implementation;



import uri.Host;
import uri.Uri;
import uri.UriParser;

public class UriParserImplementation implements UriParser {

	public String uri;
	public boolean userinfo;
	public boolean delem;

	@Override
	public Uri parse() {
		int sz = uri.length();
		if (sz < 4 || !delem)
			return null;
		UriImplementation result = new UriImplementation(userinfo);
		if (isalpha(uri.charAt(0)))
			result.scheme += uri.charAt(0);
			else return null;
		int q = 1;
		while (q < sz) {
			if (isalpha(uri.charAt(q)) || isdigit(uri.charAt(q))) {
				result.scheme += uri.charAt(q);
				q++;
			} else if (uri.charAt(q) == ':' && uri.charAt(q + 1) == '/' && uri.charAt(q + 2) == '/') {
				q += 3;
				break;
			} else
				return null;
		}

		if (userinfo)
			while (q < sz) {
				if (isunres(uri.charAt(q)) || uri.charAt(q) == ':') {
					result.userinfo += uri.charAt(q);
					q++;
				} else if (uri.charAt(q) == '%' && q + 2 < sz && ishex(uri.charAt(q + 1)) && ishex(uri.charAt(q + 2))) {
					result.userinfo += uri.charAt(q);
					result.userinfo += uri.charAt(q + 1);
					result.userinfo += uri.charAt(q + 2);
					q += 3;
				} else if (uri.charAt(q) == '@') {
					q++;
					break;
				} else
					return null;
			}
		String tmp = "";
		while (q < sz) {
			if (isunres(uri.charAt(q))) {
				tmp += uri.charAt(q);
				q++;
			} else if (uri.charAt(q) == '%' && q + 2 < sz && ishex(uri.charAt(q + 1)) && ishex(uri.charAt(q + 2))) {
				tmp += uri.charAt(q);
				tmp += uri.charAt(q + 1);
				tmp += uri.charAt(q + 2);
				q += 3;
			} else if (uri.charAt(q) == '/' || uri.charAt(q) == '?') {
				break;
			} else
				return null;

		}
		if (isip(tmp)) {
			result.host = new IPv4AddressImplementation(tmp);
		} else {
			result.host = new HostImplementation(tmp);
		}
		if (q == sz)
			return result;

		if (uri.charAt(q) == '/') {
			while (q < sz) {
				if (uri.charAt(q) == '/' || isunres(uri.charAt(q))) {
					result.path += uri.charAt(q);
					q++;
				} else if (uri.charAt(q) == '%' && q + 2 < sz && ishex(uri.charAt(q + 1)) && ishex(uri.charAt(q + 2))) {
					result.path += uri.charAt(q);
					result.path += uri.charAt(q + 1);
					result.path += uri.charAt(q + 2);
					q += 3;
				} else if (uri.charAt(q) == '?')
					break;
				else
					return null;
			}
		}
		if (q == sz)
			return result;
		if (uri.charAt(q) == '?') {
			q++;
			result.query="";
			while (q < sz) {
				if (uri.charAt(q) == '&' || uri.charAt(q) == '=' || isunres(uri.charAt(q))) {
					result.query += uri.charAt(q);
					q++;
				} else if (uri.charAt(q) == '%' && q + 2 < sz && ishex(uri.charAt(q + 1)) && ishex(uri.charAt(q + 2))) {
					result.query += uri.charAt(q);
					result.query += uri.charAt(q + 1);
					result.query += uri.charAt(q + 2);
					q += 3;
				} else
					return null;

			}
		} else
			return null;

		return result;
	}

	private boolean isalpha(char x) {
		return ((x >= 'A' && x <= 'Z') || (x >= 'a' && x <= 'z'));
	}

	private boolean isdigit(char x) {
		return (x >= '0' && x <= '9');
	}

	private boolean ishex(char x) {
		return (isdigit(x) || ((x >= 'A' && x <= 'F') || (x >= 'a' && x <= 'f')));
	}

	private boolean isunres(char x) {
		return (isdigit(x) || isalpha(x) || x == '.');
	}

	private boolean isip(String s) {
		String[] parts = s.split("\\.");
		if (parts.length != 4)
			return false;
		boolean oct = true;
		for (String w : parts)
			oct = oct && isoct(w);
		return oct;
	}

	private boolean isoct(String s) {
		if (s.length() == 3) {
			if (s.charAt(0) == '0' || s.charAt(0) == '1') {
				return (isdigit(s.charAt(1)) && isdigit(s.charAt(2)));
			} else if (s.charAt(0) == '2') {
				if (s.charAt(1) >= '0' && s.charAt(1) <= '4')
					return isdigit(s.charAt(2));
				else if (s.charAt(1) == '5' && s.charAt(2) >= '0' && s.charAt(2) <= '5')
					return true;
				else
					return false;
			} else
				return false;

		} else if (s.length() == 2) {
			return (isdigit(s.charAt(0)) && isdigit(s.charAt(1)));
		} else if (s.length() == 1) {
			return (isdigit(s.charAt(0)));
		} else
			return false;

	}

}
