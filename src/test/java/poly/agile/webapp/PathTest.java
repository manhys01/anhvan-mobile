package poly.agile.webapp;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
	public static void main(String[] args) {
		Path path = Paths.get("src/resources/static/images/brands/");
		System.out.println(path.toAbsolutePath());
	}
}
