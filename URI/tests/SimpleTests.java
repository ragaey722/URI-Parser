package uri.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;



import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uri.Host;
import uri.IPv4Address;
import uri.Uri;
import uri.UriParserFactory;

/**
 * This class provides a very simple example of how to write tests for this
 * project.
 * You can implement your own tests within this class or any other class within
 * this package.
 * Tests in other packages will not be run and considered for completion of the
 * project.
 */
public class SimpleTests {

	/**
	 * Helper function to determine if the given host is an instance of
	 * {@link IPv4Address}.
	 *
	 * @param host the host
	 * @return {@code true} if the host is an instance of {@link IPv4Address}
	 */
	public boolean isIPv4Address(Host host) {
		return host instanceof IPv4Address;
	}

	/**
	 * Helper function to retrieve the byte array representation of a given host
	 * which must be an instance of {@link IPv4Address}.
	 *
	 * @param host the host
	 * @return the byte array representation of the IPv4 address
	 */
	public byte[] getIPv4Octets(Host host) {
		if (!isIPv4Address(host))
			throw new IllegalArgumentException("host must be an IPv4 address");
		return ((IPv4Address) host).getOctets();
	}

	@Test
	public void testNonNull() {
		assertNotNull(UriParserFactory.create("scheme://").parse());
	}

	@Test
	public void testNegativeSimple() {
		assertNull(UriParserFactory.create("").parse());
	}

	@Test
	public void testIPv4AddressSimple() {
		Host host = UriParserFactory.create("scheme://1.2.3.4").parse().getHost();
		assertTrue("host must be an IPv4 address", isIPv4Address(host));
	}

	@Test
	public void testsimple() {

		assertNotNull(UriParserFactory.create("sCheme123://").parse());
		String scheme = UriParserFactory.create("sCheme123://").parse().getScheme();
		assertNotNull(scheme);
		assertTrue(scheme.equals("sCheme123"));
		assertNull(UriParserFactory.create("1scheme://").parse());
		assertNull(UriParserFactory.create("sch%eme://").parse());
		Uri uri = UriParserFactory.create("scheme123://").parse();
		assertNotNull(uri);
		scheme = uri.getScheme();
		assertNotNull(scheme);
		assertTrue(scheme.equals("scheme123"));
		assertNotNull(uri.getPath());
		assertNotNull(uri.getHost());
		assertNull(uri.getQuery());
		assertNull(uri.getUserInfo());
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "");
		assertEquals(uri.getPath(), "");
		

		uri = UriParserFactory.create("s://@1.2.3.455/?").parse();
		assertEquals(uri.getScheme(), "s");
		assertEquals(uri.getQuery(), "");
		assertEquals(uri.getUserInfo(), "");
		assertNotNull(uri.getPath());
		assertNotNull(uri.getHost());
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "1.2.3.455");
		assertEquals(uri.getPath(), "/");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

		uri = UriParserFactory.create("s://@0001.2.3.255/?").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertEquals(uri.getQuery(), "");
		assertEquals(uri.getUserInfo(), "");
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "0001.2.3.255");
		assertEquals(uri.getPath(), "/");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

		uri = UriParserFactory.create("s://@001.2.3.2550/?").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertEquals(uri.getQuery(), "");
		assertEquals(uri.getUserInfo(), "");
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "001.2.3.2550");
		assertEquals(uri.getPath(), "/");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

		uri = UriParserFactory.create("s://@1.2.3.4f/?").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertEquals(uri.getQuery(), "");
		assertEquals(uri.getUserInfo(), "");
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "1.2.3.4f");
		assertEquals(uri.getPath(), "/");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

		uri = UriParserFactory.create("s://@/?").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertEquals(uri.getQuery(), "");
		assertEquals(uri.getUserInfo(), "");
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "");
		assertEquals(uri.getPath(), "/");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

		uri = UriParserFactory.create("s://@?").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertEquals(uri.getQuery(), "");
		assertEquals(uri.getUserInfo(), "");
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "");
		assertEquals(uri.getPath(), "");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

		uri = UriParserFactory.create("s://@Aa1.%5F%af?").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertEquals(uri.getQuery(), "");
		assertEquals(uri.getUserInfo(), "");
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "Aa1.%5F%af");
		assertEquals(uri.getPath(), "");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

		uri = UriParserFactory.create("s:///?").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertEquals(uri.getQuery(), "");
		assertNull(uri.getUserInfo());
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "");
		assertEquals(uri.getPath(), "/");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

		uri = UriParserFactory.create("s://Aa1.%5F%af/?").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertEquals(uri.getQuery(), "");
		assertNull(uri.getUserInfo());
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "Aa1.%5F%af");
		assertEquals(uri.getPath(), "/");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

		uri = UriParserFactory.create("s://?").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertEquals(uri.getQuery(), "");
		assertNull(uri.getUserInfo());
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "");
		assertEquals(uri.getPath(), "");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

		uri = UriParserFactory.create("s://Aa1.%5F%af?").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertEquals(uri.getQuery(), "");
		assertNull(uri.getUserInfo());
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "Aa1.%5F%af");
		assertEquals(uri.getPath(), "");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

		uri = UriParserFactory.create("s:///").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertNull(uri.getQuery());
		assertNull(uri.getUserInfo());
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "");
		assertEquals(uri.getPath(), "/");
		assertNotNull(uri.getHost());

		uri = UriParserFactory.create("s://Aa1.%5F%af/").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertNull(uri.getQuery());
		assertNull(uri.getUserInfo());
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "Aa1.%5F%af");
		assertEquals(uri.getPath(), "/");
		assertNotNull(uri.getHost());

		uri = UriParserFactory.create("s://.../").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "s");
		assertNull(uri.getQuery());
		assertNull(uri.getUserInfo());
		assertTrue(!isIPv4Address(uri.getHost()));
		assertEquals(uri.getHost().toString(), "...");
		assertEquals(uri.getPath(), "/");
		assertNotNull(uri.getHost());

	}

	@Test
	public void test() {
		Uri uri = UriParserFactory.create("Aa1://Aa1.%5Ff:@001.099.199.255/Aa1.%5Ff/?Aa1.%5Ff&=").parse();
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "Aa1");
		assertEquals(uri.getUserInfo(), "Aa1.%5Ff:");
		assertEquals(uri.getPath(), "/Aa1.%5Ff/");
		assertEquals(uri.getQuery(), "Aa1.%5Ff&=");
		assertTrue(isIPv4Address(uri.getHost()));
		byte[] x = getIPv4Octets(uri.getHost());
		assertTrue(x[0] == (byte) 1 && x[1] == (byte) 99 && x[2] == (byte) 199 && x[3] == (byte) 255);
		assertEquals(uri.getHost().toString(), "1.99.199.255");
		assertNotNull(uri.getPath());


		uri = UriParserFactory.create("Aa1://Aa1.%5Ff:@000.099.199.255/Aa1.%5Ff/?Aa1.%5Ff&=").parse();
		assertNotNull(uri);
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "Aa1");
		assertEquals(uri.getUserInfo(), "Aa1.%5Ff:");
		assertEquals(uri.getPath(), "/Aa1.%5Ff/");
		assertEquals(uri.getQuery(), "Aa1.%5Ff&=");
		assertTrue(isIPv4Address(uri.getHost()));
		x = getIPv4Octets(uri.getHost());
		assertTrue(x[0] == (byte) 0 && x[1] == (byte) 99 && x[2] == (byte) 199 && x[3] == (byte) 255);
		assertEquals(uri.getHost().toString(), "0.99.199.255");
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

	}

	@Test
	public void test1() {
		Uri uri = UriParserFactory.create("Aa1://Aa1.%5Ff:@001.099.199.25Aa1.%5Ff5/Aa1.%5Ff/?Aa1.%5Ff&=").parse();
		assertNotNull(uri);
		assertNotNull(uri.getHost());
		assertEquals(uri.getScheme(), "Aa1");
		assertEquals(uri.getUserInfo(), "Aa1.%5Ff:");
		assertEquals(uri.getPath(), "/Aa1.%5Ff/");
		assertEquals(uri.getQuery(), "Aa1.%5Ff&=");
		assertEquals(uri.getHost().toString(), "001.099.199.25Aa1.%5Ff5");
		assertTrue(!isIPv4Address(uri.getHost()));
		assertNotNull(uri.getHost());
		assertNotNull(uri.getPath());

	}

	@Test
	public void test2() {
		Uri uri = UriParserFactory.create("9A-&a1://A-&a1.%5Ff:@0&0-1.099.199.255/Aa&-1.%5Ff/?Aa!-1.%5Ff&=").parse();
		assertNull(uri);

		uri = UriParserFactory.create("sche&me://").parse();
		assertNull(uri);

		uri = UriParserFactory.create("9scheme://").parse();
		assertNull(uri);

		uri = UriParserFactory.create("scheme//").parse();
		assertNull(uri);

		uri = UriParserFactory.create("scheme:/").parse();
		assertNull(uri);

		uri = UriParserFactory.create("scheme:").parse();
		assertNull(uri);

		uri = UriParserFactory.create("scheme://&!@").parse();
		assertNull(uri);

		uri = UriParserFactory.create("scheme://@!&").parse();
		assertNull(uri);

		uri = UriParserFactory.create("scheme://@/!*").parse();
		assertNull(uri);

		uri = UriParserFactory.create("scheme://?^*").parse();
		assertNull(uri);

		uri = UriParserFactory.create("Aa1:/Aa1.%5Ff:@001.099.199.25Aa1.%5Ff5/Aa1.%5Ff/?Aa1.%5Ff&=").parse();
		assertNull(uri);

		uri = UriParserFactory.create("Aa1//Aa1.%5Ff:@001.099.199.25Aa1.%5Ff5/Aa1.%5Ff/?Aa1.%5Ff&=").parse();
		assertNull(uri);

		uri = UriParserFactory.create("Aa1://!*^()Aa1.%5Ff5&=%").parse();
		assertNull(uri);

		uri = UriParserFactory.create("Aa1://%").parse();
		assertNull(uri);

		uri = UriParserFactory.create("Aa1://&=").parse();
		assertNull(uri);

		uri = UriParserFactory.create("Aa1://@&=").parse();
		assertNull(uri);

		uri = UriParserFactory.create("Aa1://!-@").parse();
		assertNull(uri);

		uri = UriParserFactory.create("Aa1://@!-").parse();
		assertNull(uri);

		uri = UriParserFactory.create("Aa1://!-").parse();
		assertNull(uri);
		
		uri = UriParserFactory.create("Aa1://?!-").parse();
		assertNull(uri);

		uri = UriParserFactory.create("Aa1:///!-").parse();
		assertNull(uri);

	}

}