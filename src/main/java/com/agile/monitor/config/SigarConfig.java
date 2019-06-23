package com.agile.monitor.config;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.hyperic.jni.ArchNotSupportedException;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

/**
 * 初始化Sigar配置
 * 
 * @author lihaitao
 * @since 2019-04-23
 */
@Configuration
public class SigarConfig {

	private static final Logger log = LoggerFactory.getLogger(SigarConfig.class);

	static {
		try {
			initSigar();
		} catch (IOException e) {
			log.error("Init sigar lib error", e);
			System.exit(-1);
		}
	}

	public static void initSigar() throws IOException {
		SigarLoader loader = new SigarLoader(Sigar.class);
		String lib = null;
		try {
			lib = loader.getLibraryName();
			log.info("Init sigar lib: " + lib);
		} catch (ArchNotSupportedException e) {
			log.error("Init sigar lib %s error, error message: %s", lib, e.getMessage());
			System.exit(-1);
		}
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader
				.getResource("classpath:" + File.separator + "sigar-lib" + File.separator + lib);
		if (resource.exists()) {
			InputStream is = resource.getInputStream();
			File tempDir = new File("." + File.separator + "logs" + File.separator + "monitor-agent"
					+ File.separator + "sigar-lib");
			if (!tempDir.exists()) {
				tempDir.mkdirs();
			}
			File libFile = new File(tempDir, lib);
			if (!libFile.exists()) {
				FileCopyUtils.copy(is, new BufferedOutputStream(new FileOutputStream(libFile, false)));
			}
			System.setProperty("org.hyperic.sigar.path", tempDir.getCanonicalPath());
			log.info("Sigar lib path: " + System.getProperty("org.hyperic.sigar.path"));
		} else {
			throw new IOException(resource.getDescription() + " does not exist.");
		}
	}

	@Bean
	public Sigar sigar() {
		return new Sigar();
	}

}