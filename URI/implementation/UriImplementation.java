package uri.implementation;


import uri.Host;
import uri.Uri;

public class UriImplementation implements Uri {

	String scheme;
	String query;
	String userinfo;
	String path;
	Host host;

	public UriImplementation(boolean userinfo){
		if(userinfo)
		this.userinfo="";
		scheme="";
		path="";
	}

	@Override
	public String getScheme() {
		return scheme;
	}

	@Override
	public String getQuery() {
		return query;
	}

	@Override
	public String getUserInfo() {
		return userinfo;
	}

	@Override
	public Host getHost() {
		return host;
	}

	@Override
	public String getPath() {
		return path;
	}

}
