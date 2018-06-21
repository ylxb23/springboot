package org.zero.test.utils;

import java.net.URL;
import java.util.Set;

import org.junit.Test;
import org.zero.boot.util.ResourceFinder;

/**
 * 
 * @date 2018年6月14日 下午11:13:44
 * @author zero
 */
public class ResourceFinderTest {
	
	@Test
	public void testFindResource() {
		String source = "META-INF/spring.factories";
		Set<URL> sources = ResourceFinder.findSourceByRelativeClassPath(source);
		sources.stream().forEach(System.out::println);
	}
	
}
