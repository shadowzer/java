import javax.swing.filechooser.*;
import java.io.File;
import org.apache.commons.io.FilenameUtils;

public class ImageFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;

        String ext = FilenameUtils.getExtension(f.getAbsolutePath());

        if (ext != null) {
            if (ext.equals("jpeg") || ext.equals("jpg") || ext.equals("gif") || ext.equals("png"))
                return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Only images";
    }
}
