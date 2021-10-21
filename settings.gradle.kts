if (!file("node_modules").exists()) {
	exec {
		if (org.gradle.internal.os.OperatingSystem.current().isWindows()) {
			commandLine("cmd", "/c", "npm", "install")
		} else {
			commandLine("npm", "install")
		}
	}
}