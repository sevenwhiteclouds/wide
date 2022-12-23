import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

public class Wide {
  // directory to macos chrome installation, used to check if chrome is installed
  private static final File chromePath = new File("/Applications/Google Chrome.app/Contents/Frameworks/Google Chrome Framework.framework/Libraries/WidevineCdm/");

  // directory to macos ugchromium installation, used to check if ugchromium is installed
  private static final File ugChromiumPath = new File("/Applications/Chromium.app/Contents/Frameworks/Chromium Framework.framework/Libraries/");

  public static void main(String[] args) {
    if (!chromePath.exists()) {
      System.out.println("Chrome is not installed.");
      return;
    }

    if (!ugChromiumPath.exists()) {
      System.out.println("Ungoogled Chromium is not installed.");
      return;
    }

    // FileUtils only copies full directory but does not create the folder
    File wideVineCdmDirectory = new File(ugChromiumPath + "/WidevineCdm");

    try {
      FileUtils.forceMkdir(wideVineCdmDirectory);
    }
    catch (IOException e) {
      System.out.println("The WidevineCdm folder was not able to be created in the");
      System.out.println("Ungoogled Chromium Libraries directory.");
      System.out.println("Applications -> Chromium -> Contents -> Frameworks -> Chromium Framework.framework -> Libraries");
    }

    if (wideVineCdmDirectory.exists()) {
      try {
        FileUtils.copyDirectory(chromePath, wideVineCdmDirectory);
        System.out.println("Success! Widevine has been enabled for Ungoogled Chromium.");
        System.out.println("Make sure to restart your browser.");
      }
      catch (IOException e) {
        System.out.println("Error: unable to copy source folder to destination folder");
      }
    }
  }
}