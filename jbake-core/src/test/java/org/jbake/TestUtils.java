package org.jbake;

import org.apache.commons.vfs2.util.Os;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestUtils {

    /**
     * Hides the assets on Windows that start with a dot (e.g. .test.txt but not test.txt) so File.isHidden() returns true for those files.
     */
    public static void hideAssets(File assets) throws IOException, InterruptedException {
        if (isWindows()) {
            final File[] hiddenFiles = assets.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith(".");
                }
            });
            for (File file : hiddenFiles) {
                final Process process = Runtime.getRuntime().exec(new String[]{"attrib", "+h", file.getAbsolutePath()});
                process.waitFor();
            }
        }
    }

    public static boolean isWindows() {
        return Os.isFamily(Os.OS_FAMILY_WINDOWS);
    }

    public static File getTestResourcesAsSourceFolder() {
        Path path = Paths.get( "src","test","resources","fixture");
        return path.toFile();
    }

    public static File newFolder(File base, String folderName) {
        File templateFolder = new File(base,folderName);
        templateFolder.mkdir();
        return templateFolder;
    }


}
