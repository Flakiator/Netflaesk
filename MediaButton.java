import javax.swing.*;
import java.awt.*;

class MediaButton extends JButton {
    MediaImpl media;
    MediaButton(Icon icon, MediaImpl media)
    {
        super(icon);
        this.media = media;
    }
    MediaImpl getmedia()
    {
        return media;
    }
}